package jxl.write.biff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;
import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Hyperlink;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Range;
import jxl.Sheet;
import jxl.WorkbookSettings;
import jxl.biff.AutoFilter;
import jxl.biff.CellReferenceHelper;
import jxl.biff.ConditionalFormat;
import jxl.biff.DataValidation;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.NumFormatRecordsException;
import jxl.biff.SheetRangeImpl;
import jxl.biff.XFRecord;
import jxl.biff.drawing.Button;
import jxl.biff.drawing.Chart;
import jxl.biff.drawing.CheckBox;
import jxl.biff.drawing.ComboBox;
import jxl.biff.drawing.Comment;
import jxl.biff.drawing.Drawing;
import jxl.biff.drawing.DrawingGroupObject;
import jxl.biff.formula.FormulaException;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.CellFormat;
import jxl.read.biff.SheetImpl;
import jxl.read.biff.WorkbookParser;
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

class SheetCopier {
   private static Logger logger = Logger.getLogger(SheetCopier.class);
   private AutoFilter autoFilter;
   private ButtonPropertySetRecord buttonPropertySet;
   private boolean chartOnly;
   private ArrayList columnBreaks;
   private TreeSet columnFormats;
   private ComboBox comboBox;
   private ArrayList conditionalFormats;
   private DataValidation dataValidation;
   private ArrayList drawings;
   private HashMap fonts;
   private FormattingRecords formatRecords;
   private HashMap formats;
   private SheetImpl fromSheet;
   private ArrayList hyperlinks;
   private ArrayList images;
   private int maxColumnOutlineLevel;
   private int maxRowOutlineLevel;
   private MergedCells mergedCells;
   private int numRows;
   private PLSRecord plsRecord;
   private ArrayList rowBreaks;
   private SheetWriter sheetWriter;
   private WritableSheetImpl toSheet;
   private ArrayList validatedCells;
   private WorkbookSettings workbookSettings;
   private HashMap xfRecords;

   public SheetCopier(Sheet var1, WritableSheet var2) {
      this.fromSheet = (SheetImpl)var1;
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
         HashMap var6 = this.fonts;
         Integer var10 = new Integer(var2);
         var4 = new Integer(var8.getFontIndex());
         var6.put(var10, var4);
         var2 = var3.getFormatRecord();
         HashMap var9 = this.formats;
         var10 = new Integer(var2);
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
                  Logger var8 = logger;
                  StringBuilder var9 = new StringBuilder();
                  var8.warn(var9.append("Formula ").append(var5.getFormula()).append(" in cell ").append(CellReferenceHelper.getCellReference(var1.getColumn(), var1.getRow())).append(" cannot be imported because it references another ").append(" sheet from the source workbook").toString());
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

   private void importNames() {
      WorkbookParser var8 = this.fromSheet.getWorkbook();
      WritableWorkbookImpl var7 = this.toSheet.getWorkbook();
      int var3 = var8.getIndex(this.fromSheet);
      jxl.read.biff.NameRecord[] var5 = var8.getNameRecords();
      String[] var6 = var7.getRangeNames();

      for(int var1 = 0; var1 < var5.length; ++var1) {
         jxl.read.biff.NameRecord.NameRange[] var4 = var5[var1].getRanges();

         for(int var2 = 0; var2 < var4.length; ++var2) {
            if (var3 == var8.getExternalSheetIndex(var4[var2].getExternalSheet())) {
               String var9 = var5[var1].getName();
               if (Arrays.binarySearch(var6, var9) < 0) {
                  var7.addNameArea(var9, this.toSheet, var4[var2].getFirstColumn(), var4[var2].getFirstRow(), var4[var2].getLastColumn(), var4[var2].getLastRow());
               } else {
                  logger.warn("Named range " + var9 + " is already present in the destination workbook");
               }
            }
         }
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
      jxl.read.biff.ColumnInfoRecord[] var7 = this.fromSheet.getColumnInfos();
      byte var3 = 0;

      int var1;
      for(var1 = 0; var1 < var7.length; ++var1) {
         jxl.read.biff.ColumnInfoRecord var5 = var7[var1];

         for(int var2 = var5.getStartColumn(); var2 <= var5.getEndColumn(); ++var2) {
            ColumnInfoRecord var6 = new ColumnInfoRecord(var5, var2, this.formatRecords);
            var6.setHidden(var5.getHidden());
            this.columnFormats.add(var6);
         }
      }

      Hyperlink[] var14 = this.fromSheet.getHyperlinks();

      for(var1 = 0; var1 < var14.length; ++var1) {
         WritableHyperlink var11 = new WritableHyperlink(var14[var1], this.toSheet);
         this.hyperlinks.add(var11);
      }

      Range[] var12 = this.fromSheet.getMergedCells();

      for(var1 = 0; var1 < var12.length; ++var1) {
         this.mergedCells.add(new SheetRangeImpl((SheetRangeImpl)var12[var1], this.toSheet));
      }

      label138: {
         label164: {
            boolean var10001;
            jxl.read.biff.RowRecord[] var18;
            try {
               var18 = this.fromSheet.getRowProperties();
            } catch (RowsExceededException var10) {
               var10001 = false;
               break label164;
            }

            var1 = 0;

            while(true) {
               XFRecord var13;
               RowRecord var15;
               label130: {
                  try {
                     if (var1 >= var18.length) {
                        break label138;
                     }

                     var15 = this.toSheet.getRowRecord(var18[var1].getRowNumber());
                     if (var18[var1].hasDefaultFormat()) {
                        var13 = this.formatRecords.getXFRecord(var18[var1].getXFIndex());
                        break label130;
                     }
                  } catch (RowsExceededException var9) {
                     var10001 = false;
                     break;
                  }

                  var13 = null;
               }

               try {
                  var15.setRowDetails(var18[var1].getRowHeight(), var18[var1].matchesDefaultFontHeight(), var18[var1].isCollapsed(), var18[var1].getOutlineLevel(), var18[var1].getGroupStart(), var13);
                  this.numRows = Math.max(this.numRows, var18[var1].getRowNumber() + 1);
               } catch (RowsExceededException var8) {
                  var10001 = false;
                  break;
               }

               ++var1;
            }
         }

         Assert.verify(false);
      }

      int[] var16 = this.fromSheet.getRowPageBreaks();
      if (var16 != null) {
         for(var1 = 0; var1 < var16.length; ++var1) {
            this.rowBreaks.add(new Integer(var16[var1]));
         }
      }

      var16 = this.fromSheet.getColumnPageBreaks();
      if (var16 != null) {
         for(var1 = 0; var1 < var16.length; ++var1) {
            this.columnBreaks.add(new Integer(var16[var1]));
         }
      }

      this.sheetWriter.setCharts(this.fromSheet.getCharts());
      DrawingGroupObject[] var20 = this.fromSheet.getDrawings();

      for(var1 = 0; var1 < var20.length; ++var1) {
         DrawingGroupObject var17 = var20[var1];
         if (var17 instanceof Drawing) {
            WritableImage var19 = new WritableImage(var20[var1], this.toSheet.getWorkbook().getDrawingGroup());
            this.drawings.add(var19);
            this.images.add(var19);
         } else if (var17 instanceof Comment) {
            Comment var21 = new Comment(var20[var1], this.toSheet.getWorkbook().getDrawingGroup(), this.workbookSettings);
            this.drawings.add(var21);
            CellValue var22 = (CellValue)this.toSheet.getWritableCell(var21.getColumn(), var21.getRow());
            boolean var4;
            if (var22.getCellFeatures() != null) {
               var4 = true;
            } else {
               var4 = false;
            }

            Assert.verify(var4);
            var22.getWritableCellFeatures().setCommentDrawing(var21);
         } else if (var17 instanceof Button) {
            Button var24 = new Button(var20[var1], this.toSheet.getWorkbook().getDrawingGroup(), this.workbookSettings);
            this.drawings.add(var24);
         } else if (var17 instanceof ComboBox) {
            ComboBox var25 = new ComboBox(var20[var1], this.toSheet.getWorkbook().getDrawingGroup(), this.workbookSettings);
            this.drawings.add(var25);
         } else if (var17 instanceof CheckBox) {
            CheckBox var27 = new CheckBox(var20[var1], this.toSheet.getWorkbook().getDrawingGroup(), this.workbookSettings);
            this.drawings.add(var27);
         }
      }

      DataValidation var23 = this.fromSheet.getDataValidation();
      if (var23 != null) {
         var23 = new DataValidation(var23, this.toSheet.getWorkbook(), this.toSheet.getWorkbook(), this.workbookSettings);
         this.dataValidation = var23;
         var1 = var23.getComboBoxObjectId();
         if (var1 != 0) {
            this.comboBox = (ComboBox)this.drawings.get(var1);
         }
      }

      ConditionalFormat[] var26 = this.fromSheet.getConditionalFormats();
      if (var26.length > 0) {
         for(var1 = var3; var1 < var26.length; ++var1) {
            this.conditionalFormats.add(var26[var1]);
         }
      }

      this.autoFilter = this.fromSheet.getAutoFilter();
      this.sheetWriter.setWorkspaceOptions(this.fromSheet.getWorkspaceOptions());
      if (this.fromSheet.getSheetBof().isChart()) {
         this.chartOnly = true;
         this.sheetWriter.setChartOnly();
      }

      if (this.fromSheet.getPLS() != null) {
         if (this.fromSheet.getWorkbookBof().isBiff7()) {
            logger.warn("Cannot copy Biff7 print settings record - ignoring");
         } else {
            this.plsRecord = new PLSRecord(this.fromSheet.getPLS());
         }
      }

      if (this.fromSheet.getButtonPropertySet() != null) {
         this.buttonPropertySet = new ButtonPropertySetRecord(this.fromSheet.getButtonPropertySet());
      }

      this.maxRowOutlineLevel = this.fromSheet.getMaxRowOutlineLevel();
      this.maxColumnOutlineLevel = this.fromSheet.getMaxColumnOutlineLevel();
   }

   public void copyWritableSheet() {
      this.shallowCopyCells();
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

   AutoFilter getAutoFilter() {
      return this.autoFilter;
   }

   ButtonPropertySetRecord getButtonPropertySet() {
      return this.buttonPropertySet;
   }

   ComboBox getComboBox() {
      return this.comboBox;
   }

   DataValidation getDataValidation() {
      return this.dataValidation;
   }

   public int getMaxColumnOutlineLevel() {
      return this.maxColumnOutlineLevel;
   }

   public int getMaxRowOutlineLevel() {
      return this.maxRowOutlineLevel;
   }

   PLSRecord getPLSRecord() {
      return this.plsRecord;
   }

   int getRows() {
      return this.numRows;
   }

   public void importSheet() {
      this.xfRecords = new HashMap();
      this.fonts = new HashMap();
      this.formats = new HashMap();
      this.deepCopyCells();
      jxl.read.biff.ColumnInfoRecord[] var8 = this.fromSheet.getColumnInfos();

      int var1;
      int var2;
      for(var1 = 0; var1 < var8.length; ++var1) {
         jxl.read.biff.ColumnInfoRecord var6 = var8[var1];

         for(var2 = var6.getStartColumn(); var2 <= var6.getEndColumn(); ++var2) {
            ColumnInfoRecord var5 = new ColumnInfoRecord(var6, var2);
            int var3 = var5.getXfIndex();
            WritableCellFormat var7 = (WritableCellFormat)this.xfRecords.get(new Integer(var3));
            if (var7 == null) {
               this.copyCellFormat(this.fromSheet.getColumnView(var2).getFormat());
            }

            var5.setCellFormat(var7);
            var5.setHidden(var6.getHidden());
            this.columnFormats.add(var5);
         }
      }

      Hyperlink[] var18 = this.fromSheet.getHyperlinks();

      for(var1 = 0; var1 < var18.length; ++var1) {
         WritableHyperlink var15 = new WritableHyperlink(var18[var1], this.toSheet);
         this.hyperlinks.add(var15);
      }

      Range[] var16 = this.fromSheet.getMergedCells();

      for(var1 = 0; var1 < var16.length; ++var1) {
         this.mergedCells.add(new SheetRangeImpl((SheetRangeImpl)var16[var1], this.toSheet));
      }

      label148: {
         label174: {
            boolean var10001;
            jxl.read.biff.RowRecord[] var22;
            try {
               var22 = this.fromSheet.getRowProperties();
            } catch (RowsExceededException var14) {
               var10001 = false;
               break label174;
            }

            var1 = 0;

            while(true) {
               RowRecord var28;
               try {
                  if (var1 >= var22.length) {
                     break label148;
                  }

                  var28 = this.toSheet.getRowRecord(var22[var1].getRowNumber());
               } catch (RowsExceededException var12) {
                  var10001 = false;
                  break;
               }

               WritableCellFormat var17 = null;
               jxl.read.biff.RowRecord var9 = var22[var1];

               label140: {
                  WritableCellFormat var21;
                  try {
                     if (!var9.hasDefaultFormat()) {
                        break label140;
                     }

                     HashMap var19 = this.xfRecords;
                     Integer var20 = new Integer(var9.getXFIndex());
                     var21 = (WritableCellFormat)var19.get(var20);
                  } catch (RowsExceededException var13) {
                     var10001 = false;
                     break;
                  }

                  var17 = var21;
                  if (var21 == null) {
                     try {
                        var2 = var9.getRowNumber();
                        this.copyCellFormat(this.fromSheet.getRowView(var2).getFormat());
                     } catch (RowsExceededException var11) {
                        var10001 = false;
                        break;
                     }

                     var17 = var21;
                  }
               }

               try {
                  var28.setRowDetails(var9.getRowHeight(), var9.matchesDefaultFontHeight(), var9.isCollapsed(), var9.getOutlineLevel(), var9.getGroupStart(), var17);
                  this.numRows = Math.max(this.numRows, var22[var1].getRowNumber() + 1);
               } catch (RowsExceededException var10) {
                  var10001 = false;
                  break;
               }

               ++var1;
            }
         }

         Assert.verify(false);
      }

      int[] var24 = this.fromSheet.getRowPageBreaks();
      if (var24 != null) {
         for(var1 = 0; var1 < var24.length; ++var1) {
            this.rowBreaks.add(new Integer(var24[var1]));
         }
      }

      var24 = this.fromSheet.getColumnPageBreaks();
      if (var24 != null) {
         for(var1 = 0; var1 < var24.length; ++var1) {
            this.columnBreaks.add(new Integer(var24[var1]));
         }
      }

      Chart[] var29 = this.fromSheet.getCharts();
      if (var29 != null && var29.length > 0) {
         logger.warn("Importing of charts is not supported");
      }

      DrawingGroupObject[] var31 = this.fromSheet.getDrawings();
      if (var31.length > 0 && this.toSheet.getWorkbook().getDrawingGroup() == null) {
         this.toSheet.getWorkbook().createDrawingGroup();
      }

      for(var1 = 0; var1 < var31.length; ++var1) {
         DrawingGroupObject var23 = var31[var1];
         if (var23 instanceof Drawing) {
            WritableImage var25 = new WritableImage(var31[var1].getX(), var31[var1].getY(), var31[var1].getWidth(), var31[var1].getHeight(), var31[var1].getImageData());
            this.toSheet.getWorkbook().addDrawing(var25);
            this.drawings.add(var25);
            this.images.add(var25);
         } else if (var23 instanceof Comment) {
            Comment var26 = new Comment(var31[var1], this.toSheet.getWorkbook().getDrawingGroup(), this.workbookSettings);
            this.drawings.add(var26);
            CellValue var27 = (CellValue)this.toSheet.getWritableCell(var26.getColumn(), var26.getRow());
            boolean var4;
            if (var27.getCellFeatures() != null) {
               var4 = true;
            } else {
               var4 = false;
            }

            Assert.verify(var4);
            var27.getWritableCellFeatures().setCommentDrawing(var26);
         } else if (var23 instanceof Button) {
            Button var30 = new Button(var31[var1], this.toSheet.getWorkbook().getDrawingGroup(), this.workbookSettings);
            this.drawings.add(var30);
         } else if (var23 instanceof ComboBox) {
            ComboBox var32 = new ComboBox(var31[var1], this.toSheet.getWorkbook().getDrawingGroup(), this.workbookSettings);
            this.drawings.add(var32);
         }
      }

      DataValidation var33 = this.fromSheet.getDataValidation();
      if (var33 != null) {
         var33 = new DataValidation(var33, this.toSheet.getWorkbook(), this.toSheet.getWorkbook(), this.workbookSettings);
         this.dataValidation = var33;
         var1 = var33.getComboBoxObjectId();
         if (var1 != 0) {
            this.comboBox = (ComboBox)this.drawings.get(var1);
         }
      }

      this.sheetWriter.setWorkspaceOptions(this.fromSheet.getWorkspaceOptions());
      if (this.fromSheet.getSheetBof().isChart()) {
         this.chartOnly = true;
         this.sheetWriter.setChartOnly();
      }

      if (this.fromSheet.getPLS() != null) {
         if (this.fromSheet.getWorkbookBof().isBiff7()) {
            logger.warn("Cannot copy Biff7 print settings record - ignoring");
         } else {
            this.plsRecord = new PLSRecord(this.fromSheet.getPLS());
         }
      }

      if (this.fromSheet.getButtonPropertySet() != null) {
         this.buttonPropertySet = new ButtonPropertySetRecord(this.fromSheet.getButtonPropertySet());
      }

      this.importNames();
      this.maxRowOutlineLevel = this.fromSheet.getMaxRowOutlineLevel();
      this.maxColumnOutlineLevel = this.fromSheet.getMaxColumnOutlineLevel();
   }

   boolean isChartOnly() {
      return this.chartOnly;
   }

   void setColumnBreaks(ArrayList var1) {
      this.columnBreaks = var1;
   }

   void setColumnFormats(TreeSet var1) {
      this.columnFormats = var1;
   }

   void setConditionalFormats(ArrayList var1) {
      this.conditionalFormats = var1;
   }

   void setDrawings(ArrayList var1) {
      this.drawings = var1;
   }

   void setFormatRecords(FormattingRecords var1) {
      this.formatRecords = var1;
   }

   void setHyperlinks(ArrayList var1) {
      this.hyperlinks = var1;
   }

   void setImages(ArrayList var1) {
      this.images = var1;
   }

   void setMergedCells(MergedCells var1) {
      this.mergedCells = var1;
   }

   void setRowBreaks(ArrayList var1) {
      this.rowBreaks = var1;
   }

   void setSheetWriter(SheetWriter var1) {
      this.sheetWriter = var1;
   }

   void setValidatedCells(ArrayList var1) {
      this.validatedCells = var1;
   }

   void shallowCopyCells() {
      int var3 = this.fromSheet.getRows();

      for(int var1 = 0; var1 < var3; ++var1) {
         Cell[] var4 = this.fromSheet.getRow(var1);

         for(int var2 = 0; var2 < var4.length; ++var2) {
            WritableCell var5 = this.shallowCopyCell(var4[var2]);
            if (var5 != null) {
               try {
                  this.toSheet.addCell(var5);
                  if (var5.getCellFeatures() != null && var5.getCellFeatures().hasDataValidation()) {
                     this.validatedCells.add(var5);
                  }
               } catch (WriteException var6) {
                  Assert.verify(false);
               }
            }
         }
      }

      this.numRows = this.toSheet.getRows();
   }
}
