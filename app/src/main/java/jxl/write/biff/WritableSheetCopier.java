package jxl.write.biff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Range;
import jxl.WorkbookSettings;
import jxl.biff.CellReferenceHelper;
import jxl.biff.DataValidation;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.NumFormatRecordsException;
import jxl.biff.SheetRangeImpl;
import jxl.biff.WorkspaceInformationRecord;
import jxl.biff.XFRecord;
import jxl.biff.drawing.Drawing;
import jxl.biff.formula.FormulaException;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.CellFormat;
import jxl.write.Blank;
import jxl.write.Boolean;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableHyperlink;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

class WritableSheetCopier {
   private static Logger logger = Logger.getLogger(SheetCopier.class);
   private boolean chartOnly;
   private HashMap fonts;
   private FormattingRecords formatRecords;
   private HashMap formats;
   private ButtonPropertySetRecord fromButtonPropertySet;
   private ArrayList fromColumnBreaks;
   private TreeSet fromColumnFormats;
   private DataValidation fromDataValidation;
   private ArrayList fromDrawings;
   private ArrayList fromHyperlinks;
   private MergedCells fromMergedCells;
   private PLSRecord fromPLSRecord;
   private ArrayList fromRowBreaks;
   private RowRecord[] fromRows;
   private WritableSheetImpl fromSheet;
   private WorkspaceInformationRecord fromWorkspaceOptions;
   private int maxColumnOutlineLevel;
   private int maxRowOutlineLevel;
   private int numRows;
   private SheetWriter sheetWriter;
   private ButtonPropertySetRecord toButtonPropertySet;
   private ArrayList toColumnBreaks;
   private TreeSet toColumnFormats;
   private DataValidation toDataValidation;
   private ArrayList toDrawings;
   private ArrayList toHyperlinks;
   private ArrayList toImages;
   private MergedCells toMergedCells;
   private PLSRecord toPLSRecord;
   private ArrayList toRowBreaks;
   private WritableSheetImpl toSheet;
   private ArrayList validatedCells;
   private WorkbookSettings workbookSettings;
   private HashMap xfRecords;

   public WritableSheetCopier(WritableSheet var1, WritableSheet var2) {
      this.fromSheet = (WritableSheetImpl)var1;
      WritableSheetImpl var3 = (WritableSheetImpl)var2;
      this.toSheet = var3;
      this.workbookSettings = var3.getWorkbook().getSettings();
      this.chartOnly = false;
   }

   private WritableCellFormat copyCellFormat(CellFormat var1) {
      try {
         XFRecord var3 = (XFRecord)var1;
         WritableCellFormat var8 = new WritableCellFormat(var3);
         this.formatRecords.addStyle(var8);
         int var2 = var3.getXFIndex();
         HashMap var5 = this.xfRecords;
         Integer var4 = new Integer(var2);
         var5.put(var4, var8);
         var2 = var3.getFontIndex();
         var5 = this.fonts;
         Integer var6 = new Integer(var2);
         var4 = new Integer(var8.getFontIndex());
         var5.put(var6, var4);
         var2 = var3.getFormatRecord();
         HashMap var9 = this.formats;
         Integer var10 = new Integer(var2);
         var4 = new Integer(var8.getFormatRecord());
         var9.put(var10, var4);
         return var8;
      } catch (NumFormatRecordsException var7) {
         logger.warn("Maximum number of format records exceeded.  Using default format.");
         return WritableWorkbook.NORMAL_STYLE;
      }
   }

   private WritableCell deepCopyCell(Cell var1) {
      WritableCell var4 = this.shallowCopyCell(var1);
      if (var4 == null) {
         return var4;
      } else {
         Object var3 = var4;
         if (var4 instanceof ReadFormulaRecord) {
            ReadFormulaRecord var5 = (ReadFormulaRecord)var4;
            var3 = var4;
            if (var5.handleImportedCellReferences(this.fromSheet.getWorkbook(), this.fromSheet.getWorkbook(), this.workbookSettings) ^ true) {
               try {
                  Logger var9 = logger;
                  StringBuilder var8 = new StringBuilder();
                  var9.warn(var8.append("Formula ").append(var5.getFormula()).append(" in cell ").append(CellReferenceHelper.getCellReference(var1.getColumn(), var1.getRow())).append(" cannot be imported because it references another ").append(" sheet from the source workbook").toString());
               } catch (FormulaException var6) {
                  logger.warn("Formula  in cell " + CellReferenceHelper.getCellReference(var1.getColumn(), var1.getRow()) + " cannot be imported:  " + var6.getMessage());
               }

               var3 = new Formula(var1.getColumn(), var1.getRow(), "\"ERROR\"");
            }
         }

         CellFormat var11 = ((WritableCell)var3).getCellFormat();
         int var2 = ((XFRecord)var11).getXFIndex();
         WritableCellFormat var10 = (WritableCellFormat)this.xfRecords.get(new Integer(var2));
         WritableCellFormat var7 = var10;
         if (var10 == null) {
            var7 = this.copyCellFormat(var11);
         }

         ((WritableCell)var3).setCellFormat(var7);
         return (WritableCell)var3;
      }
   }

   private WritableCell shallowCopyCell(Cell var1) {
      CellType var2 = var1.getType();
      Object var3;
      if (var2 == CellType.LABEL) {
         var3 = new Label((LabelCell)var1);
      } else if (var2 == CellType.NUMBER) {
         var3 = new Number((NumberCell)var1);
      } else if (var2 == CellType.DATE) {
         var3 = new DateTime((DateCell)var1);
      } else if (var2 == CellType.BOOLEAN) {
         var3 = new Boolean((BooleanCell)var1);
      } else if (var2 == CellType.NUMBER_FORMULA) {
         var3 = new ReadNumberFormulaRecord((FormulaData)var1);
      } else if (var2 == CellType.STRING_FORMULA) {
         var3 = new ReadStringFormulaRecord((FormulaData)var1);
      } else if (var2 == CellType.BOOLEAN_FORMULA) {
         var3 = new ReadBooleanFormulaRecord((FormulaData)var1);
      } else if (var2 == CellType.DATE_FORMULA) {
         var3 = new ReadDateFormulaRecord((FormulaData)var1);
      } else if (var2 == CellType.FORMULA_ERROR) {
         var3 = new ReadErrorFormulaRecord((FormulaData)var1);
      } else if (var2 == CellType.EMPTY && var1.getCellFormat() != null) {
         var3 = new Blank(var1);
      } else {
         var3 = null;
      }

      return (WritableCell)var3;
   }

   public void copySheet() {
      this.shallowCopyCells();
      Iterator var3 = this.fromColumnFormats.iterator();

      while(var3.hasNext()) {
         ColumnInfoRecord var2 = new ColumnInfoRecord((ColumnInfoRecord)var3.next());
         this.toColumnFormats.add(var2);
      }

      Range[] var7 = this.fromMergedCells.getMergedCells();

      int var1;
      for(var1 = 0; var1 < var7.length; ++var1) {
         this.toMergedCells.add(new SheetRangeImpl((SheetRangeImpl)var7[var1], this.toSheet));
      }

      var1 = 0;

      while(true) {
         label75: {
            label93: {
               boolean var10001;
               RowRecord[] var8;
               try {
                  var8 = this.fromRows;
                  if (var1 >= var8.length) {
                     break;
                  }
               } catch (RowsExceededException var6) {
                  var10001 = false;
                  break label93;
               }

               RowRecord var9 = var8[var1];
               if (var9 == null) {
                  break label75;
               }

               try {
                  if (var9.isDefaultHeight() && !var9.isCollapsed()) {
                     break label75;
                  }
               } catch (RowsExceededException var5) {
                  var10001 = false;
                  break label93;
               }

               try {
                  this.toSheet.getRowRecord(var1).setRowDetails(var9.getRowHeight(), var9.matchesDefaultFontHeight(), var9.isCollapsed(), var9.getOutlineLevel(), var9.getGroupStart(), var9.getStyle());
                  break label75;
               } catch (RowsExceededException var4) {
                  var10001 = false;
               }
            }

            Assert.verify(false);
            break;
         }

         ++var1;
      }

      this.toRowBreaks = new ArrayList(this.fromRowBreaks);
      this.toColumnBreaks = new ArrayList(this.fromColumnBreaks);
      if (this.fromDataValidation != null) {
         this.toDataValidation = new DataValidation(this.fromDataValidation, this.toSheet.getWorkbook(), this.toSheet.getWorkbook(), this.toSheet.getWorkbook().getSettings());
      }

      this.sheetWriter.setCharts(this.fromSheet.getCharts());
      Iterator var10 = this.fromDrawings.iterator();

      while(var10.hasNext()) {
         Object var11 = var10.next();
         if (var11 instanceof Drawing) {
            WritableImage var12 = new WritableImage((Drawing)var11, this.toSheet.getWorkbook().getDrawingGroup());
            this.toDrawings.add(var12);
            this.toImages.add(var12);
         }
      }

      this.sheetWriter.setWorkspaceOptions(this.fromWorkspaceOptions);
      if (this.fromPLSRecord != null) {
         this.toPLSRecord = new PLSRecord(this.fromPLSRecord);
      }

      if (this.fromButtonPropertySet != null) {
         this.toButtonPropertySet = new ButtonPropertySetRecord(this.fromButtonPropertySet);
      }

      var3 = this.fromHyperlinks.iterator();

      while(var3.hasNext()) {
         WritableHyperlink var13 = new WritableHyperlink((WritableHyperlink)var3.next(), this.toSheet);
         this.toHyperlinks.add(var13);
      }

   }

   void deepCopyCells() {
      int var4 = this.fromSheet.getRows();

      for(int var1 = 0; var1 < var4; ++var1) {
         Cell[] var5 = this.fromSheet.getRow(var1);

         for(int var2 = 0; var2 < var5.length; ++var2) {
            WritableCell var6 = this.deepCopyCell(var5[var2]);
            if (var6 != null) {
               label42: {
                  boolean var10001;
                  boolean var3;
                  label41: {
                     label40: {
                        try {
                           this.toSheet.addCell(var6);
                           if (var6.getCellFeatures() != null) {
                              break label40;
                           }
                        } catch (WriteException var8) {
                           var10001 = false;
                           break label42;
                        }

                        var3 = false;
                        break label41;
                     }

                     var3 = true;
                  }

                  try {
                     if (var3 & var6.getCellFeatures().hasDataValidation()) {
                        this.validatedCells.add(var6);
                     }
                     continue;
                  } catch (WriteException var7) {
                     var10001 = false;
                  }
               }

               Assert.verify(false);
            }
         }
      }

   }

   ButtonPropertySetRecord getButtonPropertySet() {
      return this.toButtonPropertySet;
   }

   DataValidation getDataValidation() {
      return this.toDataValidation;
   }

   public int getMaxColumnOutlineLevel() {
      return this.maxColumnOutlineLevel;
   }

   public int getMaxRowOutlineLevel() {
      return this.maxRowOutlineLevel;
   }

   PLSRecord getPLSRecord() {
      return this.toPLSRecord;
   }

   boolean isChartOnly() {
      return this.chartOnly;
   }

   void setButtonPropertySetRecord(ButtonPropertySetRecord var1) {
      this.fromButtonPropertySet = var1;
   }

   void setColumnBreaks(ArrayList var1, ArrayList var2) {
      this.fromColumnBreaks = var1;
      this.toColumnBreaks = var2;
   }

   void setColumnFormats(TreeSet var1, TreeSet var2) {
      this.fromColumnFormats = var1;
      this.toColumnFormats = var2;
   }

   void setDataValidation(DataValidation var1) {
      this.fromDataValidation = var1;
   }

   void setDrawings(ArrayList var1, ArrayList var2, ArrayList var3) {
      this.fromDrawings = var1;
      this.toDrawings = var2;
      this.toImages = var3;
   }

   void setHyperlinks(ArrayList var1, ArrayList var2) {
      this.fromHyperlinks = var1;
      this.toHyperlinks = var2;
   }

   void setMergedCells(MergedCells var1, MergedCells var2) {
      this.fromMergedCells = var1;
      this.toMergedCells = var2;
   }

   void setPLSRecord(PLSRecord var1) {
      this.fromPLSRecord = var1;
   }

   void setRowBreaks(ArrayList var1, ArrayList var2) {
      this.fromRowBreaks = var1;
      this.toRowBreaks = var2;
   }

   void setRows(RowRecord[] var1) {
      this.fromRows = var1;
   }

   void setSheetWriter(SheetWriter var1) {
      this.sheetWriter = var1;
   }

   void setValidatedCells(ArrayList var1) {
      this.validatedCells = var1;
   }

   void setWorkspaceOptions(WorkspaceInformationRecord var1) {
      this.fromWorkspaceOptions = var1;
   }

   void shallowCopyCells() {
      int var4 = this.fromSheet.getRows();

      for(int var1 = 0; var1 < var4; ++var1) {
         Cell[] var5 = this.fromSheet.getRow(var1);

         for(int var2 = 0; var2 < var5.length; ++var2) {
            WritableCell var6 = this.shallowCopyCell(var5[var2]);
            if (var6 != null) {
               label42: {
                  boolean var10001;
                  boolean var3;
                  label41: {
                     label40: {
                        try {
                           this.toSheet.addCell(var6);
                           if (var6.getCellFeatures() != null) {
                              break label40;
                           }
                        } catch (WriteException var8) {
                           var10001 = false;
                           break label42;
                        }

                        var3 = false;
                        break label41;
                     }

                     var3 = true;
                  }

                  try {
                     if (var3 & var6.getCellFeatures().hasDataValidation()) {
                        this.validatedCells.add(var6);
                     }
                     continue;
                  } catch (WriteException var7) {
                     var10001 = false;
                  }
               }

               Assert.verify(false);
            }
         }
      }

      this.numRows = this.toSheet.getRows();
   }
}
