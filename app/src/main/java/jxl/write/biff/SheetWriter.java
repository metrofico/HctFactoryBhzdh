package jxl.write.biff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import jxl.Cell;
import jxl.CellFeatures;
import jxl.Range;
import jxl.SheetSettings;
import jxl.WorkbookSettings;
import jxl.biff.AutoFilter;
import jxl.biff.ConditionalFormat;
import jxl.biff.DataValidation;
import jxl.biff.DataValiditySettingsRecord;
import jxl.biff.WorkspaceInformationRecord;
import jxl.biff.XFRecord;
import jxl.biff.drawing.Chart;
import jxl.biff.drawing.SheetDrawingWriter;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Blank;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableHyperlink;
import jxl.write.WriteException;

final class SheetWriter {
   private static Logger logger = Logger.getLogger(SheetWriter.class);
   private AutoFilter autoFilter;
   private ButtonPropertySetRecord buttonPropertySet;
   private boolean chartOnly;
   private ArrayList columnBreaks;
   private TreeSet columnFormats;
   private ArrayList conditionalFormats;
   private DataValidation dataValidation;
   private SheetDrawingWriter drawingWriter;
   private FooterRecord footer;
   private HeaderRecord header;
   private ArrayList hyperlinks;
   private int maxColumnOutlineLevel;
   private int maxRowOutlineLevel;
   private MergedCells mergedCells;
   private int numCols;
   private int numRows;
   private File outputFile;
   private PLSRecord plsRecord;
   private ArrayList rowBreaks;
   private RowRecord[] rows;
   private SheetSettings settings;
   private WritableSheetImpl sheet;
   private ArrayList validatedCells;
   private WorkbookSettings workbookSettings;
   private WorkspaceInformationRecord workspaceOptions;

   public SheetWriter(File var1, WritableSheetImpl var2, WorkbookSettings var3) {
      this.outputFile = var1;
      this.sheet = var2;
      this.workspaceOptions = new WorkspaceInformationRecord();
      this.workbookSettings = var3;
      this.chartOnly = false;
      this.drawingWriter = new SheetDrawingWriter(var3);
   }

   private Cell[] getColumn(int var1) {
      int var2 = this.numRows - 1;
      byte var4 = 0;
      boolean var3 = false;

      RowRecord var5;
      while(var2 >= 0 && !var3) {
         var5 = this.rows[var2];
         if (var5 != null && var5.getCell(var1) != null) {
            var3 = true;
         } else {
            --var2;
         }
      }

      Cell[] var6 = new Cell[var2 + 1];

      for(int var7 = var4; var7 <= var2; ++var7) {
         var5 = this.rows[var7];
         CellValue var8;
         if (var5 != null) {
            var8 = var5.getCell(var1);
         } else {
            var8 = null;
         }

         var6[var7] = var8;
      }

      return var6;
   }

   private void writeDataValidation() throws IOException {
      if (this.dataValidation != null && this.validatedCells.size() == 0) {
         this.dataValidation.write(this.outputFile);
      } else {
         if (this.dataValidation == null && this.validatedCells.size() > 0) {
            int var1;
            if (this.sheet.getComboBox() != null) {
               var1 = this.sheet.getComboBox().getObjectId();
            } else {
               var1 = -1;
            }

            this.dataValidation = new DataValidation(var1, this.sheet.getWorkbook(), this.sheet.getWorkbook(), this.workbookSettings);
         }

         Iterator var2 = this.validatedCells.iterator();

         while(var2.hasNext()) {
            CellValue var3 = (CellValue)var2.next();
            CellFeatures var4 = var3.getCellFeatures();
            if (!var4.getDVParser().copied()) {
               DataValiditySettingsRecord var5;
               if (!var4.getDVParser().extendedCellsValidation()) {
                  var5 = new DataValiditySettingsRecord(var4.getDVParser());
                  this.dataValidation.add(var5);
               } else if (var3.getColumn() == var4.getDVParser().getFirstColumn() && var3.getRow() == var4.getDVParser().getFirstRow()) {
                  var5 = new DataValiditySettingsRecord(var4.getDVParser());
                  this.dataValidation.add(var5);
               }
            }
         }

         this.dataValidation.write(this.outputFile);
      }
   }

   void checkMergedBorders() {
      Range[] var6 = this.mergedCells.getMergedCells();
      ArrayList var5 = new ArrayList();

      label343:
      for(int var1 = 0; var1 < var6.length; ++var1) {
         Range var9 = var6[var1];
         Cell var7 = var9.getTopLeft();
         XFRecord var8 = (XFRecord)var7.getCellFormat();
         if (var8 != null && var8.hasBorders() && !var8.isRead()) {
            WriteException var10000;
            label346: {
               CellXFRecord var4;
               boolean var10001;
               Cell var54;
               try {
                  var4 = new CellXFRecord(var8);
                  var54 = var9.getBottomRight();
                  var4.setBorder(Border.ALL, BorderLineStyle.NONE, Colour.BLACK);
                  var4.setBorder(Border.LEFT, var8.getBorderLine(Border.LEFT), var8.getBorderColour(Border.LEFT));
                  var4.setBorder(Border.TOP, var8.getBorderLine(Border.TOP), var8.getBorderColour(Border.TOP));
                  if (var7.getRow() == var54.getRow()) {
                     var4.setBorder(Border.BOTTOM, var8.getBorderLine(Border.BOTTOM), var8.getBorderColour(Border.BOTTOM));
                  }
               } catch (WriteException var53) {
                  var10000 = var53;
                  var10001 = false;
                  break label346;
               }

               try {
                  if (var7.getColumn() == var54.getColumn()) {
                     var4.setBorder(Border.RIGHT, var8.getBorderLine(Border.RIGHT), var8.getBorderColour(Border.RIGHT));
                  }
               } catch (WriteException var52) {
                  var10000 = var52;
                  var10001 = false;
                  break label346;
               }

               int var2;
               try {
                  var2 = var5.indexOf(var4);
               } catch (WriteException var51) {
                  var10000 = var51;
                  var10001 = false;
                  break label346;
               }

               if (var2 != -1) {
                  try {
                     var4 = (CellXFRecord)var5.get(var2);
                  } catch (WriteException var50) {
                     var10000 = var50;
                     var10001 = false;
                     break label346;
                  }
               } else {
                  try {
                     var5.add(var4);
                  } catch (WriteException var49) {
                     var10000 = var49;
                     var10001 = false;
                     break label346;
                  }
               }

               int var3;
               Blank var10;
               WritableSheetImpl var11;
               WritableSheetImpl var55;
               Blank var56;
               label347: {
                  label348: {
                     try {
                        ((WritableCell)var7).setCellFormat(var4);
                        if (var54.getRow() <= var7.getRow()) {
                           break label347;
                        }

                        if (var54.getColumn() == var7.getColumn()) {
                           break label348;
                        }

                        var4 = new CellXFRecord(var8);
                        var4.setBorder(Border.ALL, BorderLineStyle.NONE, Colour.BLACK);
                        var4.setBorder(Border.LEFT, var8.getBorderLine(Border.LEFT), var8.getBorderColour(Border.LEFT));
                        var4.setBorder(Border.BOTTOM, var8.getBorderLine(Border.BOTTOM), var8.getBorderColour(Border.BOTTOM));
                        var2 = var5.indexOf(var4);
                     } catch (WriteException var48) {
                        var10000 = var48;
                        var10001 = false;
                        break label346;
                     }

                     if (var2 != -1) {
                        try {
                           var4 = (CellXFRecord)var5.get(var2);
                        } catch (WriteException var46) {
                           var10000 = var46;
                           var10001 = false;
                           break label346;
                        }
                     } else {
                        try {
                           var5.add(var4);
                        } catch (WriteException var45) {
                           var10000 = var45;
                           var10001 = false;
                           break label346;
                        }
                     }

                     try {
                        var11 = this.sheet;
                        var10 = new Blank(var7.getColumn(), var54.getRow(), var4);
                        var11.addCell(var10);
                     } catch (WriteException var44) {
                        var10000 = var44;
                        var10001 = false;
                        break label346;
                     }
                  }

                  try {
                     var2 = var7.getRow() + 1;
                  } catch (WriteException var43) {
                     var10000 = var43;
                     var10001 = false;
                     break label346;
                  }

                  while(true) {
                     try {
                        if (var2 >= var54.getRow()) {
                           break;
                        }

                        var4 = new CellXFRecord(var8);
                        var4.setBorder(Border.ALL, BorderLineStyle.NONE, Colour.BLACK);
                        var4.setBorder(Border.LEFT, var8.getBorderLine(Border.LEFT), var8.getBorderColour(Border.LEFT));
                        if (var7.getColumn() == var54.getColumn()) {
                           var4.setBorder(Border.RIGHT, var8.getBorderLine(Border.RIGHT), var8.getBorderColour(Border.RIGHT));
                        }
                     } catch (WriteException var47) {
                        var10000 = var47;
                        var10001 = false;
                        break label346;
                     }

                     try {
                        var3 = var5.indexOf(var4);
                     } catch (WriteException var42) {
                        var10000 = var42;
                        var10001 = false;
                        break label346;
                     }

                     if (var3 != -1) {
                        try {
                           var4 = (CellXFRecord)var5.get(var3);
                        } catch (WriteException var41) {
                           var10000 = var41;
                           var10001 = false;
                           break label346;
                        }
                     } else {
                        try {
                           var5.add(var4);
                        } catch (WriteException var40) {
                           var10000 = var40;
                           var10001 = false;
                           break label346;
                        }
                     }

                     try {
                        var55 = this.sheet;
                        var56 = new Blank(var7.getColumn(), var2, var4);
                        var55.addCell(var56);
                     } catch (WriteException var39) {
                        var10000 = var39;
                        var10001 = false;
                        break label346;
                     }

                     ++var2;
                  }
               }

               label352: {
                  label353: {
                     try {
                        if (var54.getColumn() <= var7.getColumn()) {
                           break label352;
                        }

                        if (var54.getRow() == var7.getRow()) {
                           break label353;
                        }

                        var4 = new CellXFRecord(var8);
                        var4.setBorder(Border.ALL, BorderLineStyle.NONE, Colour.BLACK);
                        var4.setBorder(Border.RIGHT, var8.getBorderLine(Border.RIGHT), var8.getBorderColour(Border.RIGHT));
                        var4.setBorder(Border.TOP, var8.getBorderLine(Border.TOP), var8.getBorderColour(Border.TOP));
                        var2 = var5.indexOf(var4);
                     } catch (WriteException var38) {
                        var10000 = var38;
                        var10001 = false;
                        break label346;
                     }

                     if (var2 != -1) {
                        try {
                           var4 = (CellXFRecord)var5.get(var2);
                        } catch (WriteException var35) {
                           var10000 = var35;
                           var10001 = false;
                           break label346;
                        }
                     } else {
                        try {
                           var5.add(var4);
                        } catch (WriteException var34) {
                           var10000 = var34;
                           var10001 = false;
                           break label346;
                        }
                     }

                     try {
                        var11 = this.sheet;
                        var10 = new Blank(var54.getColumn(), var7.getRow(), var4);
                        var11.addCell(var10);
                     } catch (WriteException var33) {
                        var10000 = var33;
                        var10001 = false;
                        break label346;
                     }
                  }

                  try {
                     var2 = var7.getRow() + 1;
                  } catch (WriteException var32) {
                     var10000 = var32;
                     var10001 = false;
                     break label346;
                  }

                  while(true) {
                     try {
                        if (var2 >= var54.getRow()) {
                           break;
                        }

                        var4 = new CellXFRecord(var8);
                        var4.setBorder(Border.ALL, BorderLineStyle.NONE, Colour.BLACK);
                        var4.setBorder(Border.RIGHT, var8.getBorderLine(Border.RIGHT), var8.getBorderColour(Border.RIGHT));
                        var3 = var5.indexOf(var4);
                     } catch (WriteException var37) {
                        var10000 = var37;
                        var10001 = false;
                        break label346;
                     }

                     if (var3 != -1) {
                        try {
                           var4 = (CellXFRecord)var5.get(var3);
                        } catch (WriteException var31) {
                           var10000 = var31;
                           var10001 = false;
                           break label346;
                        }
                     } else {
                        try {
                           var5.add(var4);
                        } catch (WriteException var30) {
                           var10000 = var30;
                           var10001 = false;
                           break label346;
                        }
                     }

                     try {
                        var55 = this.sheet;
                        var56 = new Blank(var54.getColumn(), var2, var4);
                        var55.addCell(var56);
                     } catch (WriteException var29) {
                        var10000 = var29;
                        var10001 = false;
                        break label346;
                     }

                     ++var2;
                  }

                  try {
                     var2 = var7.getColumn() + 1;
                  } catch (WriteException var28) {
                     var10000 = var28;
                     var10001 = false;
                     break label346;
                  }

                  while(true) {
                     try {
                        if (var2 >= var54.getColumn()) {
                           break;
                        }

                        var4 = new CellXFRecord(var8);
                        var4.setBorder(Border.ALL, BorderLineStyle.NONE, Colour.BLACK);
                        var4.setBorder(Border.TOP, var8.getBorderLine(Border.TOP), var8.getBorderColour(Border.TOP));
                        if (var7.getRow() == var54.getRow()) {
                           var4.setBorder(Border.BOTTOM, var8.getBorderLine(Border.BOTTOM), var8.getBorderColour(Border.BOTTOM));
                        }
                     } catch (WriteException var36) {
                        var10000 = var36;
                        var10001 = false;
                        break label346;
                     }

                     try {
                        var3 = var5.indexOf(var4);
                     } catch (WriteException var27) {
                        var10000 = var27;
                        var10001 = false;
                        break label346;
                     }

                     if (var3 != -1) {
                        try {
                           var4 = (CellXFRecord)var5.get(var3);
                        } catch (WriteException var26) {
                           var10000 = var26;
                           var10001 = false;
                           break label346;
                        }
                     } else {
                        try {
                           var5.add(var4);
                        } catch (WriteException var25) {
                           var10000 = var25;
                           var10001 = false;
                           break label346;
                        }
                     }

                     try {
                        var55 = this.sheet;
                        var56 = new Blank(var2, var7.getRow(), var4);
                        var55.addCell(var56);
                     } catch (WriteException var24) {
                        var10000 = var24;
                        var10001 = false;
                        break label346;
                     }

                     ++var2;
                  }
               }

               try {
                  if (var54.getColumn() <= var7.getColumn() && var54.getRow() <= var7.getRow()) {
                     continue;
                  }
               } catch (WriteException var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label346;
               }

               try {
                  var4 = new CellXFRecord(var8);
                  var4.setBorder(Border.ALL, BorderLineStyle.NONE, Colour.BLACK);
                  var4.setBorder(Border.RIGHT, var8.getBorderLine(Border.RIGHT), var8.getBorderColour(Border.RIGHT));
                  var4.setBorder(Border.BOTTOM, var8.getBorderLine(Border.BOTTOM), var8.getBorderColour(Border.BOTTOM));
                  if (var54.getRow() == var7.getRow()) {
                     var4.setBorder(Border.TOP, var8.getBorderLine(Border.TOP), var8.getBorderColour(Border.TOP));
                  }
               } catch (WriteException var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label346;
               }

               try {
                  if (var54.getColumn() == var7.getColumn()) {
                     var4.setBorder(Border.LEFT, var8.getBorderLine(Border.LEFT), var8.getBorderColour(Border.LEFT));
                  }
               } catch (WriteException var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label346;
               }

               try {
                  var2 = var5.indexOf(var4);
               } catch (WriteException var20) {
                  var10000 = var20;
                  var10001 = false;
                  break label346;
               }

               if (var2 != -1) {
                  try {
                     var4 = (CellXFRecord)var5.get(var2);
                  } catch (WriteException var19) {
                     var10000 = var19;
                     var10001 = false;
                     break label346;
                  }
               } else {
                  try {
                     var5.add(var4);
                  } catch (WriteException var18) {
                     var10000 = var18;
                     var10001 = false;
                     break label346;
                  }
               }

               try {
                  var11 = this.sheet;
                  var10 = new Blank(var54.getColumn(), var54.getRow(), var4);
                  var11.addCell(var10);
                  var2 = var7.getColumn() + 1;
               } catch (WriteException var17) {
                  var10000 = var17;
                  var10001 = false;
                  break label346;
               }

               while(true) {
                  try {
                     if (var2 >= var54.getColumn()) {
                        continue label343;
                     }

                     var4 = new CellXFRecord(var8);
                     var4.setBorder(Border.ALL, BorderLineStyle.NONE, Colour.BLACK);
                     var4.setBorder(Border.BOTTOM, var8.getBorderLine(Border.BOTTOM), var8.getBorderColour(Border.BOTTOM));
                     if (var7.getRow() == var54.getRow()) {
                        var4.setBorder(Border.TOP, var8.getBorderLine(Border.TOP), var8.getBorderColour(Border.TOP));
                     }
                  } catch (WriteException var16) {
                     var10000 = var16;
                     var10001 = false;
                     break;
                  }

                  try {
                     var3 = var5.indexOf(var4);
                  } catch (WriteException var15) {
                     var10000 = var15;
                     var10001 = false;
                     break;
                  }

                  if (var3 != -1) {
                     try {
                        var4 = (CellXFRecord)var5.get(var3);
                     } catch (WriteException var14) {
                        var10000 = var14;
                        var10001 = false;
                        break;
                     }
                  } else {
                     try {
                        var5.add(var4);
                     } catch (WriteException var13) {
                        var10000 = var13;
                        var10001 = false;
                        break;
                     }
                  }

                  try {
                     var11 = this.sheet;
                     var10 = new Blank(var2, var54.getRow(), var4);
                     var11.addCell(var10);
                  } catch (WriteException var12) {
                     var10000 = var12;
                     var10001 = false;
                     break;
                  }

                  ++var2;
               }
            }

            WriteException var57 = var10000;
            logger.warn(var57.toString());
         }
      }

   }

   Chart[] getCharts() {
      return this.drawingWriter.getCharts();
   }

   final FooterRecord getFooter() {
      return this.footer;
   }

   final HeaderRecord getHeader() {
      return this.header;
   }

   WorkspaceInformationRecord getWorkspaceOptions() {
      return this.workspaceOptions;
   }

   void setAutoFilter(AutoFilter var1) {
      this.autoFilter = var1;
   }

   void setButtonPropertySet(ButtonPropertySetRecord var1) {
      this.buttonPropertySet = var1;
   }

   void setChartOnly() {
      this.chartOnly = true;
   }

   void setCharts(Chart[] var1) {
      this.drawingWriter.setCharts(var1);
   }

   void setConditionalFormats(ArrayList var1) {
      this.conditionalFormats = var1;
   }

   void setDataValidation(DataValidation var1, ArrayList var2) {
      this.dataValidation = var1;
      this.validatedCells = var2;
   }

   void setDimensions(int var1, int var2) {
      this.numRows = var1;
      this.numCols = var2;
   }

   void setDrawings(ArrayList var1, boolean var2) {
      this.drawingWriter.setDrawings(var1, var2);
   }

   void setPLS(PLSRecord var1) {
      this.plsRecord = var1;
   }

   void setSettings(SheetSettings var1) {
      this.settings = var1;
   }

   void setWorkspaceOptions(WorkspaceInformationRecord var1) {
      if (var1 != null) {
         this.workspaceOptions = var1;
      }

   }

   void setWriteData(RowRecord[] var1, ArrayList var2, ArrayList var3, ArrayList var4, MergedCells var5, TreeSet var6, int var7, int var8) {
      this.rows = var1;
      this.rowBreaks = var2;
      this.columnBreaks = var3;
      this.hyperlinks = var4;
      this.mergedCells = var5;
      this.columnFormats = var6;
      this.maxRowOutlineLevel = var7;
      this.maxColumnOutlineLevel = var8;
   }

   public void write() throws IOException {
      boolean var10;
      if (this.rows != null) {
         var10 = true;
      } else {
         var10 = false;
      }

      Assert.verify(var10);
      if (this.chartOnly) {
         this.drawingWriter.write(this.outputFile);
      } else {
         BOFRecord var11 = new BOFRecord(BOFRecord.sheet);
         this.outputFile.write(var11);
         int var3 = this.numRows;
         int var2 = var3 / 32;
         int var1 = var2;
         if (var3 - var2 * 32 != 0) {
            var1 = var2 + 1;
         }

         int var7 = this.outputFile.getPos();
         IndexRecord var19 = new IndexRecord(0, this.numRows, var1);
         this.outputFile.write(var19);
         CalcModeRecord var12;
         if (this.settings.getAutomaticFormulaCalculation()) {
            var12 = new CalcModeRecord(CalcModeRecord.automatic);
            this.outputFile.write(var12);
         } else {
            var12 = new CalcModeRecord(CalcModeRecord.manual);
            this.outputFile.write(var12);
         }

         CalcCountRecord var20 = new CalcCountRecord(100);
         this.outputFile.write(var20);
         RefModeRecord var21 = new RefModeRecord();
         this.outputFile.write(var21);
         IterationRecord var22 = new IterationRecord(false);
         this.outputFile.write(var22);
         DeltaRecord var24 = new DeltaRecord(0.001);
         this.outputFile.write(var24);
         SaveRecalcRecord var25 = new SaveRecalcRecord(this.settings.getRecalculateFormulasBeforeSave());
         this.outputFile.write(var25);
         PrintHeadersRecord var26 = new PrintHeadersRecord(this.settings.getPrintHeaders());
         this.outputFile.write(var26);
         PrintGridLinesRecord var27 = new PrintGridLinesRecord(this.settings.getPrintGridLines());
         this.outputFile.write(var27);
         GridSetRecord var28 = new GridSetRecord(true);
         this.outputFile.write(var28);
         GuttersRecord var29 = new GuttersRecord();
         var29.setMaxColumnOutline(this.maxColumnOutlineLevel + 1);
         var29.setMaxRowOutline(this.maxRowOutlineLevel + 1);
         this.outputFile.write(var29);
         var2 = this.settings.getDefaultRowHeight();
         if (this.settings.getDefaultRowHeight() != 255) {
            var10 = true;
         } else {
            var10 = false;
         }

         DefaultRowHeightRecord var30 = new DefaultRowHeightRecord(var2, var10);
         this.outputFile.write(var30);
         if (this.maxRowOutlineLevel > 0) {
            this.workspaceOptions.setRowOutlines(true);
         }

         if (this.maxColumnOutlineLevel > 0) {
            this.workspaceOptions.setColumnOutlines(true);
         }

         this.workspaceOptions.setFitToPages(this.settings.getFitToPages());
         this.outputFile.write(this.workspaceOptions);
         int[] var31;
         if (this.rowBreaks.size() > 0) {
            var3 = this.rowBreaks.size();
            var31 = new int[var3];

            for(var2 = 0; var2 < var3; ++var2) {
               var31[var2] = (Integer)this.rowBreaks.get(var2);
            }

            HorizontalPageBreaksRecord var32 = new HorizontalPageBreaksRecord(var31);
            this.outputFile.write(var32);
         }

         if (this.columnBreaks.size() > 0) {
            var3 = this.columnBreaks.size();
            var31 = new int[var3];

            for(var2 = 0; var2 < var3; ++var2) {
               var31[var2] = (Integer)this.columnBreaks.get(var2);
            }

            VerticalPageBreaksRecord var34 = new VerticalPageBreaksRecord(var31);
            this.outputFile.write(var34);
         }

         HeaderRecord var35 = new HeaderRecord(this.settings.getHeader().toString());
         this.outputFile.write(var35);
         FooterRecord var36 = new FooterRecord(this.settings.getFooter().toString());
         this.outputFile.write(var36);
         HorizontalCentreRecord var37 = new HorizontalCentreRecord(this.settings.isHorizontalCentre());
         this.outputFile.write(var37);
         VerticalCentreRecord var38 = new VerticalCentreRecord(this.settings.isVerticalCentre());
         this.outputFile.write(var38);
         if (this.settings.getLeftMargin() != this.settings.getDefaultWidthMargin()) {
            LeftMarginRecord var39 = new LeftMarginRecord(this.settings.getLeftMargin());
            this.outputFile.write(var39);
         }

         if (this.settings.getRightMargin() != this.settings.getDefaultWidthMargin()) {
            RightMarginRecord var40 = new RightMarginRecord(this.settings.getRightMargin());
            this.outputFile.write(var40);
         }

         if (this.settings.getTopMargin() != this.settings.getDefaultHeightMargin()) {
            TopMarginRecord var41 = new TopMarginRecord(this.settings.getTopMargin());
            this.outputFile.write(var41);
         }

         if (this.settings.getBottomMargin() != this.settings.getDefaultHeightMargin()) {
            BottomMarginRecord var42 = new BottomMarginRecord(this.settings.getBottomMargin());
            this.outputFile.write(var42);
         }

         PLSRecord var43 = this.plsRecord;
         if (var43 != null) {
            this.outputFile.write(var43);
         }

         SetupRecord var44 = new SetupRecord(this.settings);
         this.outputFile.write(var44);
         if (this.settings.isProtected()) {
            ProtectRecord var45 = new ProtectRecord(this.settings.isProtected());
            this.outputFile.write(var45);
            ScenarioProtectRecord var46 = new ScenarioProtectRecord(this.settings.isProtected());
            this.outputFile.write(var46);
            ObjectProtectRecord var47 = new ObjectProtectRecord(this.settings.isProtected());
            this.outputFile.write(var47);
            PasswordRecord var48;
            if (this.settings.getPassword() != null) {
               var48 = new PasswordRecord(this.settings.getPassword());
               this.outputFile.write(var48);
            } else if (this.settings.getPasswordHash() != 0) {
               var48 = new PasswordRecord(this.settings.getPasswordHash());
               this.outputFile.write(var48);
            }
         }

         var19.setDataStartPosition(this.outputFile.getPos());
         DefaultColumnWidth var49 = new DefaultColumnWidth(this.settings.getDefaultColumnWidth());
         this.outputFile.write(var49);
         WritableCellFormat var50 = this.sheet.getWorkbook().getStyles().getNormalStyle();
         WritableCellFormat var14 = this.sheet.getWorkbook().getStyles().getDefaultDateFormat();
         Iterator var13 = this.columnFormats.iterator();

         while(var13.hasNext()) {
            ColumnInfoRecord var16 = (ColumnInfoRecord)var13.next();
            if (var16.getColumn() < 256) {
               this.outputFile.write(var16);
            }

            XFRecord var15 = var16.getCellFormat();
            if (var15 != var50 && var16.getColumn() < 256) {
               Cell[] var33 = this.getColumn(var16.getColumn());

               for(var2 = 0; var2 < var33.length; ++var2) {
                  Cell var17 = var33[var2];
                  if (var17 != null && (var17.getCellFormat() == var50 || var33[var2].getCellFormat() == var14)) {
                     ((WritableCell)var33[var2]).setCellFormat(var15);
                  }
               }
            }
         }

         AutoFilter var51 = this.autoFilter;
         if (var51 != null) {
            var51.write(this.outputFile);
         }

         DimensionRecord var52 = new DimensionRecord(this.numRows, this.numCols);
         this.outputFile.write(var52);

         for(var2 = 0; var2 < var1; ++var2) {
            DBCellRecord var23 = new DBCellRecord(this.outputFile.getPos());
            int var4 = this.numRows;
            var3 = var2 * 32;
            int var8 = Math.min(32, var4 - var3);
            boolean var5 = true;
            var4 = var3;

            while(true) {
               int var9 = var3 + var8;
               int var6 = var3;
               if (var4 >= var9) {
                  for(; var6 < var9; ++var6) {
                     if (this.rows[var6] != null) {
                        var23.addCellRowPosition(this.outputFile.getPos());
                        this.rows[var6].writeCells(this.outputFile);
                     }
                  }

                  var19.addBlockPosition(this.outputFile.getPos());
                  var23.setPosition(this.outputFile.getPos());
                  this.outputFile.write(var23);
                  break;
               }

               RowRecord var53 = this.rows[var4];
               boolean var18 = var5;
               if (var53 != null) {
                  var53.write(this.outputFile);
                  var18 = var5;
                  if (var5) {
                     var23.setCellOffset(this.outputFile.getPos());
                     var18 = false;
                  }
               }

               ++var4;
               var5 = var18;
            }
         }

         if (!this.workbookSettings.getDrawingsDisabled()) {
            this.drawingWriter.write(this.outputFile);
         }

         Window2Record var54 = new Window2Record(this.settings);
         this.outputFile.write(var54);
         SelectionRecord var56;
         if (this.settings.getHorizontalFreeze() == 0 && this.settings.getVerticalFreeze() == 0) {
            var56 = new SelectionRecord(SelectionRecord.upperLeft, 0, 0);
            this.outputFile.write(var56);
         } else {
            PaneRecord var55 = new PaneRecord(this.settings.getHorizontalFreeze(), this.settings.getVerticalFreeze());
            this.outputFile.write(var55);
            var56 = new SelectionRecord(SelectionRecord.upperLeft, 0, 0);
            this.outputFile.write(var56);
            if (this.settings.getHorizontalFreeze() != 0) {
               var56 = new SelectionRecord(SelectionRecord.upperRight, this.settings.getHorizontalFreeze(), 0);
               this.outputFile.write(var56);
            }

            if (this.settings.getVerticalFreeze() != 0) {
               var56 = new SelectionRecord(SelectionRecord.lowerLeft, 0, this.settings.getVerticalFreeze());
               this.outputFile.write(var56);
            }

            if (this.settings.getHorizontalFreeze() != 0 && this.settings.getVerticalFreeze() != 0) {
               var56 = new SelectionRecord(SelectionRecord.lowerRight, this.settings.getHorizontalFreeze(), this.settings.getVerticalFreeze());
               this.outputFile.write(var56);
            }

            Weird1Record var57 = new Weird1Record();
            this.outputFile.write(var57);
         }

         if (this.settings.getZoomFactor() != 100) {
            SCLRecord var58 = new SCLRecord(this.settings.getZoomFactor());
            this.outputFile.write(var58);
         }

         this.mergedCells.write(this.outputFile);
         var13 = this.hyperlinks.iterator();

         while(var13.hasNext()) {
            WritableHyperlink var59 = (WritableHyperlink)var13.next();
            this.outputFile.write(var59);
         }

         ButtonPropertySetRecord var60 = this.buttonPropertySet;
         if (var60 != null) {
            this.outputFile.write(var60);
         }

         if (this.dataValidation != null || this.validatedCells.size() > 0) {
            this.writeDataValidation();
         }

         ArrayList var61 = this.conditionalFormats;
         if (var61 != null && var61.size() > 0) {
            Iterator var62 = this.conditionalFormats.iterator();

            while(var62.hasNext()) {
               ((ConditionalFormat)var62.next()).write(this.outputFile);
            }
         }

         EOFRecord var63 = new EOFRecord();
         this.outputFile.write(var63);
         this.outputFile.setData(var19.getData(), var7 + 4);
      }
   }
}
