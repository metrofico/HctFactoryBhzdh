package jxl.read.biff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jxl.Cell;
import jxl.CellFeatures;
import jxl.CellReferenceHelper;
import jxl.CellType;
import jxl.HeaderFooter;
import jxl.Range;
import jxl.SheetSettings;
import jxl.WorkbookSettings;
import jxl.biff.AutoFilter;
import jxl.biff.AutoFilterInfoRecord;
import jxl.biff.AutoFilterRecord;
import jxl.biff.ConditionalFormat;
import jxl.biff.ConditionalFormatRangeRecord;
import jxl.biff.ConditionalFormatRecord;
import jxl.biff.ContinueRecord;
import jxl.biff.DataValidation;
import jxl.biff.DataValidityListRecord;
import jxl.biff.DataValiditySettingsRecord;
import jxl.biff.FilterModeRecord;
import jxl.biff.FormattingRecords;
import jxl.biff.Type;
import jxl.biff.WorkspaceInformationRecord;
import jxl.biff.drawing.Button;
import jxl.biff.drawing.Chart;
import jxl.biff.drawing.CheckBox;
import jxl.biff.drawing.ComboBox;
import jxl.biff.drawing.Comment;
import jxl.biff.drawing.Drawing;
import jxl.biff.drawing.Drawing2;
import jxl.biff.drawing.DrawingData;
import jxl.biff.drawing.DrawingDataException;
import jxl.biff.drawing.MsoDrawingRecord;
import jxl.biff.drawing.NoteRecord;
import jxl.biff.drawing.ObjRecord;
import jxl.biff.drawing.TextObjectRecord;
import jxl.biff.formula.FormulaException;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.PageOrder;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;

final class SheetReader {
   private static Logger logger = Logger.getLogger(SheetReader.class);
   private AutoFilter autoFilter;
   private ButtonPropertySetRecord buttonPropertySet;
   private Cell[][] cells;
   private ArrayList charts;
   private int[] columnBreaks;
   private ArrayList columnInfosArray;
   private ArrayList conditionalFormats;
   private DataValidation dataValidation;
   private DrawingData drawingData;
   private ArrayList drawings;
   private File excelFile;
   private FormattingRecords formattingRecords;
   private ArrayList hyperlinks;
   private int maxColumnOutlineLevel;
   private int maxRowOutlineLevel;
   private Range[] mergedCells;
   private boolean nineteenFour;
   private int numCols;
   private int numRows;
   private ArrayList outOfBoundsCells;
   private PLSRecord plsRecord;
   private int[] rowBreaks;
   private ArrayList rowProperties;
   private SheetSettings settings;
   private ArrayList sharedFormulas;
   private SSTRecord sharedStrings;
   private SheetImpl sheet;
   private BOFRecord sheetBof;
   private int startPosition;
   private WorkbookParser workbook;
   private BOFRecord workbookBof;
   private WorkbookSettings workbookSettings;
   private WorkspaceInformationRecord workspaceOptions;

   SheetReader(File var1, SSTRecord var2, FormattingRecords var3, BOFRecord var4, BOFRecord var5, boolean var6, WorkbookParser var7, int var8, SheetImpl var9) {
      this.excelFile = var1;
      this.sharedStrings = var2;
      this.formattingRecords = var3;
      this.sheetBof = var4;
      this.workbookBof = var5;
      this.columnInfosArray = new ArrayList();
      this.sharedFormulas = new ArrayList();
      this.hyperlinks = new ArrayList();
      this.conditionalFormats = new ArrayList();
      this.rowProperties = new ArrayList(10);
      this.charts = new ArrayList();
      this.drawings = new ArrayList();
      this.outOfBoundsCells = new ArrayList();
      this.nineteenFour = var6;
      this.workbook = var7;
      this.startPosition = var8;
      this.sheet = var9;
      this.settings = new SheetSettings(var9);
      this.workbookSettings = this.workbook.getSettings();
   }

   private void addCell(Cell var1) {
      if (var1.getRow() < this.numRows && var1.getColumn() < this.numCols) {
         if (this.cells[var1.getRow()][var1.getColumn()] != null) {
            StringBuffer var2 = new StringBuffer();
            CellReferenceHelper.getCellReference(var1.getColumn(), var1.getRow(), var2);
            logger.warn("Cell " + var2.toString() + " already contains data");
         }

         this.cells[var1.getRow()][var1.getColumn()] = var1;
      } else {
         this.outOfBoundsCells.add(var1);
      }

   }

   private void addCellComment(int var1, int var2, String var3, double var4, double var6) {
      Cell var8 = this.cells[var2][var1];
      CellFeatures var11;
      if (var8 == null) {
         logger.warn("Cell at " + CellReferenceHelper.getCellReference(var1, var2) + " not present - adding a blank");
         MulBlankCell var12 = new MulBlankCell(var2, var1, 0, this.formattingRecords, this.sheet);
         var11 = new CellFeatures();
         var11.setReadComment(var3, var4, var6);
         var12.setCellFeatures(var11);
         this.addCell(var12);
      } else {
         if (var8 instanceof CellFeaturesAccessor) {
            CellFeaturesAccessor var10 = (CellFeaturesAccessor)var8;
            CellFeatures var9 = var10.getCellFeatures();
            var11 = var9;
            if (var9 == null) {
               var11 = new CellFeatures();
               var10.setCellFeatures(var11);
            }

            var11.setReadComment(var3, var4, var6);
         } else {
            logger.warn("Not able to add comment to cell type " + var8.getClass().getName() + " at " + CellReferenceHelper.getCellReference(var1, var2));
         }

      }
   }

   private void addCellValidation(int var1, int var2, int var3, int var4, DataValiditySettingsRecord var5) {
      while(var2 <= var4) {
         for(int var6 = var1; var6 <= var3; ++var6) {
            CellFeatures var8 = null;
            Cell[][] var9 = this.cells;
            Cell var7 = var8;
            if (var9.length > var2) {
               Cell[] var12 = var9[var2];
               var7 = var8;
               if (var12.length > var6) {
                  var7 = var12[var6];
               }
            }

            if (var7 == null) {
               MulBlankCell var10 = new MulBlankCell(var2, var6, 0, this.formattingRecords, this.sheet);
               var8 = new CellFeatures();
               var8.setValidationSettings(var5);
               var10.setCellFeatures(var8);
               this.addCell(var10);
            } else if (var7 instanceof CellFeaturesAccessor) {
               CellFeaturesAccessor var13 = (CellFeaturesAccessor)var7;
               var8 = var13.getCellFeatures();
               CellFeatures var11 = var8;
               if (var8 == null) {
                  var11 = new CellFeatures();
                  var13.setCellFeatures(var11);
               }

               var11.setValidationSettings(var5);
            } else {
               logger.warn("Not able to add comment to cell type " + var7.getClass().getName() + " at " + CellReferenceHelper.getCellReference(var6, var2));
            }
         }

         ++var2;
      }

   }

   private boolean addToSharedFormulas(BaseSharedFormulaRecord var1) {
      int var3 = this.sharedFormulas.size();
      int var2 = 0;

      boolean var4;
      for(var4 = false; var2 < var3 && !var4; ++var2) {
         var4 = ((SharedFormulaRecord)this.sharedFormulas.get(var2)).add(var1);
      }

      return var4;
   }

   private void handleObjectRecord(ObjRecord var1, MsoDrawingRecord var2, HashMap var3) {
      if (var2 == null) {
         logger.warn("Object record is not associated with a drawing  record - ignoring");
      } else {
         DrawingDataException var10000;
         label446: {
            boolean var10001;
            label451: {
               DrawingData var61;
               try {
                  if (var1.getType() == ObjRecord.PICTURE) {
                     if (this.drawingData == null) {
                        var61 = new DrawingData();
                        this.drawingData = var61;
                     }
                     break label451;
                  }
               } catch (DrawingDataException var55) {
                  var10000 = var55;
                  var10001 = false;
                  break label446;
               }

               ObjRecord.ObjType var9;
               ObjRecord.ObjType var10;
               try {
                  var10 = var1.getType();
                  var9 = ObjRecord.EXCELNOTE;
               } catch (DrawingDataException var47) {
                  var10000 = var47;
                  var10001 = false;
                  break label446;
               }

               boolean var5 = false;
               boolean var6 = false;
               boolean var7 = false;
               boolean var8 = false;
               boolean var4;
               Record var56;
               MsoDrawingRecord var57;
               Record var58;
               TextObjectRecord var59;
               ContinueRecord var60;
               ContinueRecord var65;
               if (var10 == var9) {
                  try {
                     if (this.drawingData == null) {
                        DrawingData var75 = new DrawingData();
                        this.drawingData = var75;
                     }
                  } catch (DrawingDataException var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label446;
                  }

                  Comment var76;
                  label280: {
                     label454: {
                        try {
                           var76 = new Comment(var2, var1, this.drawingData, this.workbook.getDrawingGroup(), this.workbookSettings);
                           var58 = this.excelFile.next();
                           if (var58.getType() == Type.MSODRAWING) {
                              break label454;
                           }
                        } catch (DrawingDataException var25) {
                           var10000 = var25;
                           var10001 = false;
                           break label446;
                        }

                        var56 = var58;

                        try {
                           if (var58.getType() != Type.CONTINUE) {
                              break label280;
                           }
                        } catch (DrawingDataException var24) {
                           var10000 = var24;
                           var10001 = false;
                           break label446;
                        }
                     }

                     try {
                        var57 = new MsoDrawingRecord(var58);
                        var76.addMso(var57);
                        var56 = this.excelFile.next();
                     } catch (DrawingDataException var23) {
                        var10000 = var23;
                        var10001 = false;
                        break label446;
                     }
                  }

                  label265: {
                     label264: {
                        try {
                           if (var56.getType() == Type.TXO) {
                              break label264;
                           }
                        } catch (DrawingDataException var21) {
                           var10000 = var21;
                           var10001 = false;
                           break label446;
                        }

                        var4 = false;
                        break label265;
                     }

                     var4 = true;
                  }

                  try {
                     Assert.verify(var4);
                     var59 = new TextObjectRecord(var56);
                     var76.setTextObject(var59);
                     var58 = this.excelFile.next();
                  } catch (DrawingDataException var20) {
                     var10000 = var20;
                     var10001 = false;
                     break label446;
                  }

                  var4 = var8;

                  label253: {
                     try {
                        if (var58.getType() != Type.CONTINUE) {
                           break label253;
                        }
                     } catch (DrawingDataException var19) {
                        var10000 = var19;
                        var10001 = false;
                        break label446;
                     }

                     var4 = true;
                  }

                  try {
                     Assert.verify(var4);
                     var60 = new ContinueRecord(var58);
                     var76.setText(var60);
                     var56 = this.excelFile.next();
                     if (var56.getType() == Type.CONTINUE) {
                        var65 = new ContinueRecord(var56);
                        var76.setFormatting(var65);
                     }
                  } catch (DrawingDataException var18) {
                     var10000 = var18;
                     var10001 = false;
                     break label446;
                  }

                  try {
                     Integer var69 = new Integer(var76.getObjectId());
                     var3.put(var69, var76);
                     return;
                  } catch (DrawingDataException var13) {
                     var10000 = var13;
                     var10001 = false;
                     break label446;
                  }
               }

               label455: {
                  try {
                     if (var1.getType() == ObjRecord.COMBOBOX) {
                        if (this.drawingData == null) {
                           var61 = new DrawingData();
                           this.drawingData = var61;
                        }
                        break label455;
                     }
                  } catch (DrawingDataException var54) {
                     var10000 = var54;
                     var10001 = false;
                     break label446;
                  }

                  label457: {
                     try {
                        if (var1.getType() != ObjRecord.CHECKBOX) {
                           break label457;
                        }

                        if (this.drawingData == null) {
                           var61 = new DrawingData();
                           this.drawingData = var61;
                        }
                     } catch (DrawingDataException var53) {
                        var10000 = var53;
                        var10001 = false;
                        break label446;
                     }

                     CheckBox var62;
                     label422: {
                        label421: {
                           try {
                              var62 = new CheckBox(var2, var1, this.drawingData, this.workbook.getDrawingGroup(), this.workbookSettings);
                              var58 = this.excelFile.next();
                              if (var58.getType() != Type.MSODRAWING && var58.getType() != Type.CONTINUE) {
                                 break label421;
                              }
                           } catch (DrawingDataException var52) {
                              var10000 = var52;
                              var10001 = false;
                              break label446;
                           }

                           var4 = true;
                           break label422;
                        }

                        var4 = false;
                     }

                     label412: {
                        label458: {
                           try {
                              Assert.verify(var4);
                              if (var58.getType() == Type.MSODRAWING) {
                                 break label458;
                              }
                           } catch (DrawingDataException var51) {
                              var10000 = var51;
                              var10001 = false;
                              break label446;
                           }

                           var56 = var58;

                           try {
                              if (var58.getType() != Type.CONTINUE) {
                                 break label412;
                              }
                           } catch (DrawingDataException var50) {
                              var10000 = var50;
                              var10001 = false;
                              break label446;
                           }
                        }

                        try {
                           var57 = new MsoDrawingRecord(var58);
                           var62.addMso(var57);
                           var56 = this.excelFile.next();
                        } catch (DrawingDataException var46) {
                           var10000 = var46;
                           var10001 = false;
                           break label446;
                        }
                     }

                     label402: {
                        label401: {
                           try {
                              if (var56.getType() == Type.TXO) {
                                 break label401;
                              }
                           } catch (DrawingDataException var49) {
                              var10000 = var49;
                              var10001 = false;
                              break label446;
                           }

                           var4 = false;
                           break label402;
                        }

                        var4 = true;
                     }

                     try {
                        Assert.verify(var4);
                        var59 = new TextObjectRecord(var56);
                        var62.setTextObject(var59);
                        if (var59.getTextLength() == 0) {
                           return;
                        }
                     } catch (DrawingDataException var48) {
                        var10000 = var48;
                        var10001 = false;
                        break label446;
                     }

                     try {
                        var58 = this.excelFile.next();
                     } catch (DrawingDataException var33) {
                        var10000 = var33;
                        var10001 = false;
                        break label446;
                     }

                     var4 = var5;

                     label319: {
                        try {
                           if (var58.getType() != Type.CONTINUE) {
                              break label319;
                           }
                        } catch (DrawingDataException var32) {
                           var10000 = var32;
                           var10001 = false;
                           break label446;
                        }

                        var4 = true;
                     }

                     try {
                        Assert.verify(var4);
                        var60 = new ContinueRecord(var58);
                        var62.setText(var60);
                        var58 = this.excelFile.next();
                        if (var58.getType() == Type.CONTINUE) {
                           var60 = new ContinueRecord(var58);
                           var62.setFormatting(var60);
                        }
                     } catch (DrawingDataException var22) {
                        var10000 = var22;
                        var10001 = false;
                        break label446;
                     }

                     try {
                        this.drawings.add(var62);
                        return;
                     } catch (DrawingDataException var11) {
                        var10000 = var11;
                        var10001 = false;
                        break label446;
                     }
                  }

                  label459: {
                     try {
                        if (var1.getType() == ObjRecord.BUTTON) {
                           if (this.drawingData == null) {
                              var61 = new DrawingData();
                              this.drawingData = var61;
                           }
                           break label459;
                        }
                     } catch (DrawingDataException var45) {
                        var10000 = var45;
                        var10001 = false;
                        break label446;
                     }

                     ObjRecord.ObjType var63;
                     try {
                        var63 = var1.getType();
                        var9 = ObjRecord.TEXT;
                     } catch (DrawingDataException var35) {
                        var10000 = var35;
                        var10001 = false;
                        break label446;
                     }

                     Logger var64;
                     StringBuilder var73;
                     if (var63 == var9) {
                        try {
                           var64 = logger;
                           var73 = new StringBuilder();
                           var64.warn(var73.append(var1.getType()).append(" Object on sheet \"").append(this.sheet.getName()).append("\" not supported - omitting").toString());
                           if (this.drawingData == null) {
                              var61 = new DrawingData();
                              this.drawingData = var61;
                           }
                        } catch (DrawingDataException var31) {
                           var10000 = var31;
                           var10001 = false;
                           break label446;
                        }

                        Record var74;
                        label310: {
                           label309: {
                              try {
                                 this.drawingData.addData(var2.getData());
                                 var74 = this.excelFile.next();
                                 if (var74.getType() != Type.MSODRAWING && var74.getType() != Type.CONTINUE) {
                                    break label309;
                                 }
                              } catch (DrawingDataException var30) {
                                 var10000 = var30;
                                 var10001 = false;
                                 break label446;
                              }

                              var4 = true;
                              break label310;
                           }

                           var4 = false;
                        }

                        Record var66;
                        label299: {
                           label461: {
                              try {
                                 Assert.verify(var4);
                                 if (var74.getType() == Type.MSODRAWING) {
                                    break label461;
                                 }
                              } catch (DrawingDataException var29) {
                                 var10000 = var29;
                                 var10001 = false;
                                 break label446;
                              }

                              var66 = var74;

                              try {
                                 if (var74.getType() != Type.CONTINUE) {
                                    break label299;
                                 }
                              } catch (DrawingDataException var28) {
                                 var10000 = var28;
                                 var10001 = false;
                                 break label446;
                              }
                           }

                           try {
                              MsoDrawingRecord var67 = new MsoDrawingRecord(var74);
                              this.drawingData.addRawData(var67.getData());
                              var66 = this.excelFile.next();
                           } catch (DrawingDataException var27) {
                              var10000 = var27;
                              var10001 = false;
                              break label446;
                           }
                        }

                        var4 = var7;

                        label286: {
                           try {
                              if (var66.getType() != Type.TXO) {
                                 break label286;
                              }
                           } catch (DrawingDataException var26) {
                              var10000 = var26;
                              var10001 = false;
                              break label446;
                           }

                           var4 = true;
                        }

                        try {
                           Assert.verify(var4);
                           if (this.workbook.getDrawingGroup() != null) {
                              this.workbook.getDrawingGroup().setDrawingsOmitted(var2, var1);
                           }

                           return;
                        } catch (DrawingDataException var14) {
                           var10000 = var14;
                           var10001 = false;
                           break label446;
                        }
                     } else {
                        try {
                           if (var1.getType() == ObjRecord.CHART) {
                              return;
                           }

                           var64 = logger;
                           var73 = new StringBuilder();
                           var64.warn(var73.append(var1.getType()).append(" Object on sheet \"").append(this.sheet.getName()).append("\" not supported - omitting").toString());
                           if (this.drawingData == null) {
                              var61 = new DrawingData();
                              this.drawingData = var61;
                           }
                        } catch (DrawingDataException var34) {
                           var10000 = var34;
                           var10001 = false;
                           break label446;
                        }

                        try {
                           this.drawingData.addData(var2.getData());
                           if (this.workbook.getDrawingGroup() != null) {
                              this.workbook.getDrawingGroup().setDrawingsOmitted(var2, var1);
                           }

                           return;
                        } catch (DrawingDataException var16) {
                           var10000 = var16;
                           var10001 = false;
                           break label446;
                        }
                     }
                  }

                  Button var68;
                  label376: {
                     label375: {
                        try {
                           var68 = new Button(var2, var1, this.drawingData, this.workbook.getDrawingGroup(), this.workbookSettings);
                           var58 = this.excelFile.next();
                           if (var58.getType() == Type.MSODRAWING || var58.getType() == Type.CONTINUE) {
                              break label375;
                           }
                        } catch (DrawingDataException var44) {
                           var10000 = var44;
                           var10001 = false;
                           break label446;
                        }

                        var4 = false;
                        break label376;
                     }

                     var4 = true;
                  }

                  label366: {
                     label463: {
                        try {
                           Assert.verify(var4);
                           if (var58.getType() == Type.MSODRAWING) {
                              break label463;
                           }
                        } catch (DrawingDataException var43) {
                           var10000 = var43;
                           var10001 = false;
                           break label446;
                        }

                        var56 = var58;

                        try {
                           if (var58.getType() != Type.CONTINUE) {
                              break label366;
                           }
                        } catch (DrawingDataException var42) {
                           var10000 = var42;
                           var10001 = false;
                           break label446;
                        }
                     }

                     try {
                        var57 = new MsoDrawingRecord(var58);
                        var68.addMso(var57);
                        var56 = this.excelFile.next();
                     } catch (DrawingDataException var41) {
                        var10000 = var41;
                        var10001 = false;
                        break label446;
                     }
                  }

                  label354: {
                     label353: {
                        try {
                           if (var56.getType() == Type.TXO) {
                              break label353;
                           }
                        } catch (DrawingDataException var40) {
                           var10000 = var40;
                           var10001 = false;
                           break label446;
                        }

                        var4 = false;
                        break label354;
                     }

                     var4 = true;
                  }

                  try {
                     Assert.verify(var4);
                     var59 = new TextObjectRecord(var56);
                     var68.setTextObject(var59);
                     var56 = this.excelFile.next();
                  } catch (DrawingDataException var39) {
                     var10000 = var39;
                     var10001 = false;
                     break label446;
                  }

                  var4 = var6;

                  label342: {
                     try {
                        if (var56.getType() != Type.CONTINUE) {
                           break label342;
                        }
                     } catch (DrawingDataException var38) {
                        var10000 = var38;
                        var10001 = false;
                        break label446;
                     }

                     var4 = true;
                  }

                  try {
                     Assert.verify(var4);
                     var65 = new ContinueRecord(var56);
                     var68.setText(var65);
                     var56 = this.excelFile.next();
                     if (var56.getType() == Type.CONTINUE) {
                        var65 = new ContinueRecord(var56);
                        var68.setFormatting(var65);
                     }
                  } catch (DrawingDataException var37) {
                     var10000 = var37;
                     var10001 = false;
                     break label446;
                  }

                  try {
                     this.drawings.add(var68);
                     return;
                  } catch (DrawingDataException var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label446;
                  }
               }

               try {
                  ComboBox var71 = new ComboBox(var2, var1, this.drawingData, this.workbook.getDrawingGroup(), this.workbookSettings);
                  this.drawings.add(var71);
                  return;
               } catch (DrawingDataException var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label446;
               }
            }

            try {
               Drawing var72 = new Drawing(var2, var1, this.drawingData, this.workbook.getDrawingGroup(), this.sheet);
               this.drawings.add(var72);
               return;
            } catch (DrawingDataException var17) {
               var10000 = var17;
               var10001 = false;
            }
         }

         DrawingDataException var70 = var10000;
         logger.warn(var70.getMessage() + "...disabling drawings for the remainder of the workbook");
         this.workbookSettings.setDrawingsDisabled(true);
      }
   }

   private void handleOutOfBoundsCells() {
      int var2 = this.numRows;
      int var1 = this.numCols;

      Cell var4;
      for(Iterator var5 = this.outOfBoundsCells.iterator(); var5.hasNext(); var1 = Math.max(var1, var4.getColumn() + 1)) {
         var4 = (Cell)var5.next();
         var2 = Math.max(var2, var4.getRow() + 1);
      }

      int var3;
      if (var1 > this.numCols) {
         for(var3 = 0; var3 < this.numRows; ++var3) {
            Cell[] var6 = new Cell[var1];
            Cell[] var9 = this.cells[var3];
            System.arraycopy(var9, 0, var6, 0, var9.length);
            this.cells[var3] = var6;
         }
      }

      if (var2 > this.numRows) {
         Cell[][] var7 = new Cell[var2][];
         Cell[][] var10 = this.cells;
         System.arraycopy(var10, 0, var7, 0, var10.length);
         this.cells = var7;

         for(var3 = this.numRows; var3 < var2; ++var3) {
            var7[var3] = new Cell[var1];
         }
      }

      this.numRows = var2;
      this.numCols = var1;
      Iterator var8 = this.outOfBoundsCells.iterator();

      while(var8.hasNext()) {
         this.addCell((Cell)var8.next());
      }

      this.outOfBoundsCells.clear();
   }

   private Cell revertSharedFormula(BaseSharedFormulaRecord var1) {
      int var2 = this.excelFile.getPos();
      this.excelFile.setPos(var1.getFilePos());
      Record var3 = var1.getRecord();
      File var5 = this.excelFile;
      FormattingRecords var11 = this.formattingRecords;
      WorkbookParser var4 = this.workbook;
      FormulaRecord var16 = new FormulaRecord(var3, var5, var11, var4, var4, FormulaRecord.ignoreSharedFormula, this.sheet, this.workbookSettings);

      FormulaException var10000;
      label44: {
         CellValue var14;
         boolean var10001;
         try {
            var14 = var16.getFormula();
         } catch (FormulaException var10) {
            var10000 = var10;
            var10001 = false;
            break label44;
         }

         Object var12 = var14;

         label45: {
            NumberFormulaRecord var17;
            try {
               if (var16.getFormula().getType() != CellType.NUMBER_FORMULA) {
                  break label45;
               }

               var17 = (NumberFormulaRecord)var16.getFormula();
            } catch (FormulaException var9) {
               var10000 = var9;
               var10001 = false;
               break label44;
            }

            var12 = var14;

            try {
               if (this.formattingRecords.isDate(var16.getXFIndex())) {
                  FormattingRecords var15 = this.formattingRecords;
                  WorkbookParser var6 = this.workbook;
                  var12 = new DateFormulaRecord(var17, var15, var6, var6, this.nineteenFour, this.sheet);
               }
            } catch (FormulaException var8) {
               var10000 = var8;
               var10001 = false;
               break label44;
            }
         }

         try {
            this.excelFile.setPos(var2);
            return (Cell)var12;
         } catch (FormulaException var7) {
            var10000 = var7;
            var10001 = false;
         }
      }

      FormulaException var13 = var10000;
      logger.warn(CellReferenceHelper.getCellReference(var16.getColumn(), var16.getRow()) + " " + var13.getMessage());
      return null;
   }

   final AutoFilter getAutoFilter() {
      return this.autoFilter;
   }

   final ButtonPropertySetRecord getButtonPropertySet() {
      return this.buttonPropertySet;
   }

   final Cell[][] getCells() {
      return this.cells;
   }

   final ArrayList getCharts() {
      return this.charts;
   }

   final int[] getColumnBreaks() {
      return this.columnBreaks;
   }

   final ArrayList getColumnInfosArray() {
      return this.columnInfosArray;
   }

   final ArrayList getConditionalFormats() {
      return this.conditionalFormats;
   }

   final DataValidation getDataValidation() {
      return this.dataValidation;
   }

   DrawingData getDrawingData() {
      return this.drawingData;
   }

   final ArrayList getDrawings() {
      return this.drawings;
   }

   final ArrayList getHyperlinks() {
      return this.hyperlinks;
   }

   public int getMaxColumnOutlineLevel() {
      return this.maxColumnOutlineLevel;
   }

   public int getMaxRowOutlineLevel() {
      return this.maxRowOutlineLevel;
   }

   final Range[] getMergedCells() {
      return this.mergedCells;
   }

   final int getNumCols() {
      return this.numCols;
   }

   final int getNumRows() {
      return this.numRows;
   }

   final PLSRecord getPLS() {
      return this.plsRecord;
   }

   final int[] getRowBreaks() {
      return this.rowBreaks;
   }

   final ArrayList getRowProperties() {
      return this.rowProperties;
   }

   final SheetSettings getSettings() {
      return this.settings;
   }

   final WorkspaceInformationRecord getWorkspaceOptions() {
      return this.workspaceOptions;
   }

   final void read() {
      this.excelFile.setPos(this.startPosition);
      HashMap var8 = new HashMap();
      ArrayList var9 = new ArrayList();
      boolean var2 = true;
      boolean var3 = true;
      boolean var6 = false;
      AutoFilterInfoRecord var15 = null;
      FilterModeRecord var10 = null;
      ObjRecord var12 = null;
      ConditionalFormat var11 = null;
      Object var18 = null;
      MsoDrawingRecord var13 = null;
      Window2Record var19 = null;

      int var1;
      boolean var32;
      for(ContinueRecord var16 = null; var2; var2 = var32) {
         Record var20 = this.excelFile.next();
         Type var21 = var20.getType();
         if (var21 == Type.UNKNOWN && var20.getCode() == 0) {
            logger.warn("Biff code zero found");
            if (var20.getLength() == 10) {
               logger.warn("Biff code zero found - trying a dimension record.");
               var20.setType(Type.DIMENSION);
            } else {
               logger.warn("Biff code zero found - Ignoring.");
            }
         }

         Object var50;
         label579: {
            AutoFilterInfoRecord var38;
            label578: {
               label613: {
                  boolean var7;
                  Object var39;
                  AutoFilterInfoRecord var49;
                  label574: {
                     if (var21 == Type.DIMENSION) {
                        DimensionRecord var14;
                        if (this.workbookBof.isBiff8()) {
                           var14 = new DimensionRecord(var20);
                        } else {
                           var14 = new DimensionRecord(var20, DimensionRecord.biff7);
                        }

                        this.numRows = var14.getNumberOfRows();
                        var1 = var14.getNumberOfColumns();
                        this.numCols = var1;
                        this.cells = new Cell[this.numRows][var1];
                        var38 = var15;
                     } else if (var21 == Type.LABELSST) {
                        this.addCell(new LabelSSTRecord(var20, this.sharedStrings, this.formattingRecords, this.sheet));
                        var38 = var15;
                     } else {
                        if (var21 == Type.RK || var21 == Type.RK2) {
                           var49 = var15;
                           RKRecord var98 = new RKRecord(var20, this.formattingRecords, this.sheet);
                           if (this.formattingRecords.isDate(var98.getXFIndex())) {
                              this.addCell(new DateRecord(var98, var98.getXFIndex(), this.formattingRecords, this.nineteenFour, this.sheet));
                              var39 = var18;
                              var7 = var6;
                           } else {
                              this.addCell(var98);
                              var7 = var6;
                              var39 = var18;
                           }
                           break label574;
                        }

                        if (var21 == Type.HLINK) {
                           HyperlinkRecord var40 = new HyperlinkRecord(var20, this.sheet, this.workbookSettings);
                           this.hyperlinks.add(var40);
                           var38 = var15;
                        } else if (var21 == Type.MERGEDCELLS) {
                           MergedCellsRecord var41 = new MergedCellsRecord(var20, this.sheet);
                           Range[] var17 = this.mergedCells;
                           if (var17 == null) {
                              this.mergedCells = var41.getRanges();
                              var38 = var15;
                           } else {
                              Range[] var60 = new Range[var17.length + var41.getRanges().length];
                              var17 = this.mergedCells;
                              System.arraycopy(var17, 0, var60, 0, var17.length);
                              System.arraycopy(var41.getRanges(), 0, var60, this.mergedCells.length, var41.getRanges().length);
                              this.mergedCells = var60;
                              var38 = var15;
                           }
                        } else {
                           int var4;
                           int var5;
                           if (var21 == Type.MULRK) {
                              MulRKRecord var44 = new MulRKRecord(var20);
                              var1 = var44.getNumberOfColumns();
                              var4 = 0;

                              while(true) {
                                 var38 = var15;
                                 if (var4 >= var1) {
                                    break;
                                 }

                                 var5 = var44.getXFIndex(var4);
                                 NumberValue var45 = new NumberValue(var44.getRow(), var44.getFirstColumn() + var4, RKHelper.getDouble(var44.getRKNumber(var4)), var5, this.formattingRecords, this.sheet);
                                 if (this.formattingRecords.isDate(var5)) {
                                    this.addCell(new DateRecord(var45, var5, this.formattingRecords, this.nineteenFour, this.sheet));
                                 } else {
                                    var45.setNumberFormat(this.formattingRecords.getNumberFormat(var5));
                                    this.addCell(var45);
                                 }

                                 ++var4;
                              }
                           } else {
                              if (var21 == Type.NUMBER) {
                                 NumberRecord var46 = new NumberRecord(var20, this.formattingRecords, this.sheet);
                                 if (this.formattingRecords.isDate(var46.getXFIndex())) {
                                    this.addCell(new DateRecord(var46, var46.getXFIndex(), this.formattingRecords, this.nineteenFour, this.sheet));
                                 } else {
                                    this.addCell(var46);
                                 }
                              } else if (var21 == Type.BOOLERR) {
                                 BooleanRecord var51 = new BooleanRecord(var20, this.formattingRecords, this.sheet);
                                 if (var51.isError()) {
                                    this.addCell(new ErrorRecord(var51.getRecord(), this.formattingRecords, this.sheet));
                                 } else {
                                    this.addCell(var51);
                                 }
                              } else if (var21 == Type.PRINTGRIDLINES) {
                                 PrintGridLinesRecord var53 = new PrintGridLinesRecord(var20);
                                 this.settings.setPrintGridLines(var53.getPrintGridLines());
                              } else if (var21 == Type.PRINTHEADERS) {
                                 PrintHeadersRecord var56 = new PrintHeadersRecord(var20);
                                 this.settings.setPrintHeaders(var56.getPrintHeaders());
                              } else {
                                 if (var21 == Type.WINDOW2) {
                                    Window2Record var99;
                                    if (this.workbookBof.isBiff8()) {
                                       var99 = new Window2Record(var20);
                                    } else {
                                       var99 = new Window2Record(var20, Window2Record.biff7);
                                    }

                                    this.settings.setShowGridLines(var99.getShowGridLines());
                                    this.settings.setDisplayZeroValues(var99.getDisplayZeroValues());
                                    this.settings.setSelected(true);
                                    this.settings.setPageBreakPreviewMode(var99.isPageBreakPreview());
                                    var19 = var99;
                                    break label613;
                                 }

                                 if (var21 != Type.PANE) {
                                    if (var21 == Type.CONTINUE) {
                                       var16 = new ContinueRecord(var20);
                                       break label613;
                                    }

                                    label547: {
                                       if (var21 == Type.NOTE) {
                                          if (!this.workbookSettings.getDrawingsDisabled()) {
                                             NoteRecord var47 = new NoteRecord(var20);
                                             Comment var61 = (Comment)var8.remove(new Integer(var47.getObjectId()));
                                             if (var61 == null) {
                                                logger.warn(" cannot find comment for note id " + var47.getObjectId() + "...ignoring");
                                             } else {
                                                var61.setNote(var47);
                                                this.drawings.add(var61);
                                                this.addCellComment(var61.getColumn(), var61.getRow(), var61.getText(), var61.getWidth(), var61.getHeight());
                                             }
                                          }
                                       } else {
                                          var50 = var18;
                                          var32 = var2;
                                          var38 = var15;
                                          if (var21 != Type.ARRAY) {
                                             if (var21 == Type.PROTECT) {
                                                ProtectRecord var65 = new ProtectRecord(var20);
                                                this.settings.setProtected(var65.isProtected());
                                             } else {
                                                if (var21 == Type.SHAREDFORMULA) {
                                                   SharedFormulaRecord var95;
                                                   label407: {
                                                      if (var18 == null) {
                                                         logger.warn("Shared template formula is null - trying most recent formula template");
                                                         ArrayList var93 = this.sharedFormulas;
                                                         var95 = (SharedFormulaRecord)var93.get(var93.size() - 1);
                                                         if (var95 != null) {
                                                            var39 = var95.getTemplateFormula();
                                                            break label407;
                                                         }
                                                      }

                                                      var39 = var18;
                                                   }

                                                   WorkbookParser var54 = this.workbook;
                                                   var95 = new SharedFormulaRecord(var20, (BaseSharedFormulaRecord)var39, var54, var54, this.sheet);
                                                   this.sharedFormulas.add(var95);
                                                   var50 = null;
                                                   var15 = var15;
                                                   break label579;
                                                }

                                                if (var21 == Type.FORMULA || var21 == Type.FORMULA2) {
                                                   File var67 = this.excelFile;
                                                   FormattingRecords var84 = this.formattingRecords;
                                                   WorkbookParser var80 = this.workbook;
                                                   FormulaRecord var69 = new FormulaRecord(var20, var67, var84, var80, var80, this.sheet, this.workbookSettings);
                                                   if (var69.isShared()) {
                                                      var18 = (BaseSharedFormulaRecord)var69.getFormula();
                                                      var7 = this.addToSharedFormulas((BaseSharedFormulaRecord)var18);
                                                      if (var7) {
                                                         var18 = var50;
                                                      }

                                                      var39 = var18;
                                                      var6 = var7;
                                                      if (!var7) {
                                                         var39 = var18;
                                                         var6 = var7;
                                                         if (var50 != null) {
                                                            this.addCell(this.revertSharedFormula((BaseSharedFormulaRecord)var50));
                                                            var39 = var18;
                                                            var6 = var7;
                                                         }
                                                      }
                                                   } else {
                                                      CellValue var70 = var69.getFormula();
                                                      var39 = var70;
                                                      var18 = var70;

                                                      label461: {
                                                         FormulaException var10000;
                                                         label599: {
                                                            boolean var10001;
                                                            label600: {
                                                               try {
                                                                  if (var69.getFormula().getType() != CellType.NUMBER_FORMULA) {
                                                                     break label600;
                                                                  }
                                                               } catch (FormulaException var31) {
                                                                  var10000 = var31;
                                                                  var10001 = false;
                                                                  break label599;
                                                               }

                                                               var18 = var70;

                                                               NumberFormulaRecord var71;
                                                               try {
                                                                  var71 = (NumberFormulaRecord)var69.getFormula();
                                                               } catch (FormulaException var29) {
                                                                  var10000 = var29;
                                                                  var10001 = false;
                                                                  break label599;
                                                               }

                                                               var39 = var70;
                                                               var18 = var70;

                                                               try {
                                                                  if (!this.formattingRecords.isDate(var71.getXFIndex())) {
                                                                     break label600;
                                                                  }
                                                               } catch (FormulaException var30) {
                                                                  var10000 = var30;
                                                                  var10001 = false;
                                                                  break label599;
                                                               }

                                                               var18 = var70;

                                                               try {
                                                                  var39 = new DateFormulaRecord;
                                                               } catch (FormulaException var28) {
                                                                  var10000 = var28;
                                                                  var10001 = false;
                                                                  break label599;
                                                               }

                                                               var18 = var70;

                                                               FormattingRecords var22;
                                                               try {
                                                                  var22 = this.formattingRecords;
                                                               } catch (FormulaException var27) {
                                                                  var10000 = var27;
                                                                  var10001 = false;
                                                                  break label599;
                                                               }

                                                               var18 = var70;

                                                               WorkbookParser var23;
                                                               try {
                                                                  var23 = this.workbook;
                                                               } catch (FormulaException var26) {
                                                                  var10000 = var26;
                                                                  var10001 = false;
                                                                  break label599;
                                                               }

                                                               var18 = var70;

                                                               try {
                                                                  var39.<init>(var71, var22, var23, var23, this.nineteenFour, this.sheet);
                                                               } catch (FormulaException var25) {
                                                                  var10000 = var25;
                                                                  var10001 = false;
                                                                  break label599;
                                                               }
                                                            }

                                                            var18 = var39;

                                                            try {
                                                               this.addCell((Cell)var39);
                                                               break label461;
                                                            } catch (FormulaException var24) {
                                                               var10000 = var24;
                                                               var10001 = false;
                                                            }
                                                         }

                                                         FormulaException var87 = var10000;
                                                         logger.warn(CellReferenceHelper.getCellReference(((Cell)var18).getColumn(), ((Cell)var18).getRow()) + " " + var87.getMessage());
                                                      }

                                                      var39 = var50;
                                                   }

                                                   var49 = var15;
                                                   var7 = var6;
                                                   break label574;
                                                }

                                                if (var21 == Type.LABEL) {
                                                   LabelRecord var68;
                                                   if (this.workbookBof.isBiff8()) {
                                                      var68 = new LabelRecord(var20, this.formattingRecords, this.sheet, this.workbookSettings);
                                                   } else {
                                                      var68 = new LabelRecord(var20, this.formattingRecords, this.sheet, this.workbookSettings, LabelRecord.biff7);
                                                   }

                                                   this.addCell(var68);
                                                } else if (var21 == Type.RSTRING) {
                                                   Assert.verify(this.workbookBof.isBiff8() ^ true);
                                                   this.addCell(new RStringRecord(var20, this.formattingRecords, this.sheet, this.workbookSettings, RStringRecord.biff7));
                                                } else if (var21 != Type.NAME) {
                                                   if (var21 == Type.PASSWORD) {
                                                      PasswordRecord var73 = new PasswordRecord(var20);
                                                      this.settings.setPasswordHash(var73.getPasswordHash());
                                                   } else if (var21 == Type.ROW) {
                                                      RowRecord var74 = new RowRecord(var20);
                                                      if (!var74.isDefaultHeight() || !var74.matchesDefaultFontHeight() || var74.isCollapsed() || var74.hasDefaultFormat() || var74.getOutlineLevel() != 0) {
                                                         this.rowProperties.add(var74);
                                                      }
                                                   } else if (var21 == Type.BLANK) {
                                                      if (!this.workbookSettings.getIgnoreBlanks()) {
                                                         this.addCell(new BlankCell(var20, this.formattingRecords, this.sheet));
                                                      }
                                                   } else if (var21 == Type.MULBLANK) {
                                                      if (!this.workbookSettings.getIgnoreBlanks()) {
                                                         MulBlankRecord var76 = new MulBlankRecord(var20);
                                                         var1 = var76.getNumberOfColumns();

                                                         for(var4 = 0; var4 < var1; ++var4) {
                                                            var5 = var76.getXFIndex(var4);
                                                            this.addCell(new MulBlankCell(var76.getRow(), var76.getFirstColumn() + var4, var5, this.formattingRecords, this.sheet));
                                                         }
                                                      }
                                                   } else if (var21 == Type.SCL) {
                                                      SCLRecord var77 = new SCLRecord(var20);
                                                      this.settings.setZoomFactor(var77.getZoomFactor());
                                                   } else if (var21 == Type.COLINFO) {
                                                      ColumnInfoRecord var79 = new ColumnInfoRecord(var20);
                                                      this.columnInfosArray.add(var79);
                                                   } else {
                                                      HeaderFooter var82;
                                                      if (var21 == Type.HEADER) {
                                                         HeaderRecord var81;
                                                         if (this.workbookBof.isBiff8()) {
                                                            var81 = new HeaderRecord(var20, this.workbookSettings);
                                                         } else {
                                                            var81 = new HeaderRecord(var20, this.workbookSettings, HeaderRecord.biff7);
                                                         }

                                                         var82 = new HeaderFooter(var81.getHeader());
                                                         this.settings.setHeader(var82);
                                                      } else if (var21 == Type.FOOTER) {
                                                         FooterRecord var83;
                                                         if (this.workbookBof.isBiff8()) {
                                                            var83 = new FooterRecord(var20, this.workbookSettings);
                                                         } else {
                                                            var83 = new FooterRecord(var20, this.workbookSettings, FooterRecord.biff7);
                                                         }

                                                         var82 = new HeaderFooter(var83.getFooter());
                                                         this.settings.setFooter(var82);
                                                      } else if (var21 == Type.SETUP) {
                                                         SetupRecord var85 = new SetupRecord(var20);
                                                         if (var85.getInitialized()) {
                                                            if (var85.isPortrait()) {
                                                               this.settings.setOrientation(PageOrientation.PORTRAIT);
                                                            } else {
                                                               this.settings.setOrientation(PageOrientation.LANDSCAPE);
                                                            }

                                                            if (var85.isRightDown()) {
                                                               this.settings.setPageOrder(PageOrder.RIGHT_THEN_DOWN);
                                                            } else {
                                                               this.settings.setPageOrder(PageOrder.DOWN_THEN_RIGHT);
                                                            }

                                                            this.settings.setPaperSize(PaperSize.getPaperSize(var85.getPaperSize()));
                                                            this.settings.setHeaderMargin(var85.getHeaderMargin());
                                                            this.settings.setFooterMargin(var85.getFooterMargin());
                                                            this.settings.setScaleFactor(var85.getScaleFactor());
                                                            this.settings.setPageStart(var85.getPageStart());
                                                            this.settings.setFitWidth(var85.getFitWidth());
                                                            this.settings.setFitHeight(var85.getFitHeight());
                                                            this.settings.setHorizontalPrintResolution(var85.getHorizontalPrintResolution());
                                                            this.settings.setVerticalPrintResolution(var85.getVerticalPrintResolution());
                                                            this.settings.setCopies(var85.getCopies());
                                                            WorkspaceInformationRecord var86 = this.workspaceOptions;
                                                            if (var86 != null) {
                                                               this.settings.setFitToPages(var86.getFitToPages());
                                                            }
                                                         }
                                                      } else if (var21 == Type.WSBOOL) {
                                                         this.workspaceOptions = new WorkspaceInformationRecord(var20);
                                                      } else if (var21 == Type.DEFCOLWIDTH) {
                                                         DefaultColumnWidthRecord var88 = new DefaultColumnWidthRecord(var20);
                                                         this.settings.setDefaultColumnWidth(var88.getWidth());
                                                      } else if (var21 == Type.DEFAULTROWHEIGHT) {
                                                         DefaultRowHeightRecord var89 = new DefaultRowHeightRecord(var20);
                                                         if (var89.getHeight() != 0) {
                                                            this.settings.setDefaultRowHeight(var89.getHeight());
                                                         }
                                                      } else {
                                                         if (var21 == Type.CONDFMT) {
                                                            var11 = new ConditionalFormat(new ConditionalFormatRangeRecord(var20));
                                                            this.conditionalFormats.add(var11);
                                                            break label547;
                                                         }

                                                         if (var21 == Type.CF) {
                                                            var11.addCondition(new ConditionalFormatRecord(var20));
                                                         } else {
                                                            if (var21 == Type.FILTERMODE) {
                                                               var10 = new FilterModeRecord(var20);
                                                               break label547;
                                                            }

                                                            if (var21 == Type.AUTOFILTERINFO) {
                                                               var15 = new AutoFilterInfoRecord(var20);
                                                               break label579;
                                                            }

                                                            if (var21 == Type.AUTOFILTER) {
                                                               if (!this.workbookSettings.getAutoFilterDisabled()) {
                                                                  AutoFilterRecord var55 = new AutoFilterRecord(var20);
                                                                  if (this.autoFilter == null) {
                                                                     this.autoFilter = new AutoFilter(var10, var15);
                                                                     var15 = null;
                                                                     var10 = null;
                                                                  } else {
                                                                     var15 = var15;
                                                                  }

                                                                  this.autoFilter.add(var55);
                                                                  break label579;
                                                               }
                                                            } else if (var21 == Type.LEFTMARGIN) {
                                                               LeftMarginRecord var90 = new LeftMarginRecord(var20);
                                                               this.settings.setLeftMargin(var90.getMargin());
                                                            } else if (var21 == Type.RIGHTMARGIN) {
                                                               RightMarginRecord var91 = new RightMarginRecord(var20);
                                                               this.settings.setRightMargin(var91.getMargin());
                                                            } else if (var21 == Type.TOPMARGIN) {
                                                               TopMarginRecord var92 = new TopMarginRecord(var20);
                                                               this.settings.setTopMargin(var92.getMargin());
                                                            } else if (var21 == Type.BOTTOMMARGIN) {
                                                               BottomMarginRecord var94 = new BottomMarginRecord(var20);
                                                               this.settings.setBottomMargin(var94.getMargin());
                                                            } else if (var21 == Type.HORIZONTALPAGEBREAKS) {
                                                               HorizontalPageBreaksRecord var96;
                                                               if (this.workbookBof.isBiff8()) {
                                                                  var96 = new HorizontalPageBreaksRecord(var20);
                                                               } else {
                                                                  var96 = new HorizontalPageBreaksRecord(var20, HorizontalPageBreaksRecord.biff7);
                                                               }

                                                               this.rowBreaks = var96.getRowBreaks();
                                                            } else if (var21 == Type.VERTICALPAGEBREAKS) {
                                                               VerticalPageBreaksRecord var97;
                                                               if (this.workbookBof.isBiff8()) {
                                                                  var97 = new VerticalPageBreaksRecord(var20);
                                                               } else {
                                                                  var97 = new VerticalPageBreaksRecord(var20, VerticalPageBreaksRecord.biff7);
                                                               }

                                                               this.columnBreaks = var97.getColumnBreaks();
                                                            } else {
                                                               if (var21 != Type.PLS) {
                                                                  if (var21 == Type.DVAL) {
                                                                     if (!this.workbookSettings.getCellValidationDisabled()) {
                                                                        DataValidityListRecord var42 = new DataValidityListRecord(var20);
                                                                        if (var42.getObjectId() == -1) {
                                                                           if (var13 != null && var12 == null) {
                                                                              if (this.drawingData == null) {
                                                                                 this.drawingData = new DrawingData();
                                                                              }

                                                                              Drawing2 var37 = new Drawing2(var13, this.drawingData, this.workbook.getDrawingGroup());
                                                                              this.drawings.add(var37);
                                                                              this.dataValidation = new DataValidation(var42);
                                                                              var13 = null;
                                                                           } else {
                                                                              this.dataValidation = new DataValidation(var42);
                                                                           }
                                                                        } else if (var9.contains(new Integer(var42.getObjectId()))) {
                                                                           this.dataValidation = new DataValidation(var42);
                                                                        } else {
                                                                           logger.warn("object id " + var42.getObjectId() + " referenced " + " by data validity list record not found - ignoring");
                                                                        }

                                                                        var15 = var15;
                                                                        break label579;
                                                                     }
                                                                  } else {
                                                                     CentreRecord var43;
                                                                     if (var21 == Type.HCENTER) {
                                                                        var43 = new CentreRecord(var20);
                                                                        this.settings.setHorizontalCentre(var43.isCentre());
                                                                     } else if (var21 == Type.VCENTER) {
                                                                        var43 = new CentreRecord(var20);
                                                                        this.settings.setVerticalCentre(var43.isCentre());
                                                                     } else {
                                                                        if (var21 != Type.DV) {
                                                                           ContinueRecord var57;
                                                                           if (var21 == Type.OBJ) {
                                                                              ObjRecord var62 = new ObjRecord(var20);
                                                                              if (!this.workbookSettings.getDrawingsDisabled()) {
                                                                                 if (var13 == null && var16 != null) {
                                                                                    logger.warn("Cannot find drawing record - using continue record");
                                                                                    var13 = new MsoDrawingRecord(var16.getRecord());
                                                                                    var16 = null;
                                                                                 }

                                                                                 this.handleObjectRecord(var62, var13, var8);
                                                                                 var9.add(new Integer(var62.getObjectId()));
                                                                              }

                                                                              var12 = var62;
                                                                              var57 = var16;
                                                                              var2 = var2;
                                                                              if (var62.getType() != ObjRecord.CHART) {
                                                                                 var12 = null;
                                                                                 var13 = null;
                                                                                 var15 = var15;
                                                                                 break label579;
                                                                              }
                                                                           } else {
                                                                              if (var21 == Type.MSODRAWING) {
                                                                                 var39 = var18;
                                                                                 var49 = var15;
                                                                                 var7 = var6;
                                                                                 if (!this.workbookSettings.getDrawingsDisabled()) {
                                                                                    if (var13 != null) {
                                                                                       this.drawingData.addRawData(var13.getData());
                                                                                    }

                                                                                    var13 = new MsoDrawingRecord(var20);
                                                                                    if (var3) {
                                                                                       var13.setFirst();
                                                                                       var3 = false;
                                                                                       var15 = var15;
                                                                                       break label579;
                                                                                    }
                                                                                    break label578;
                                                                                 }
                                                                                 break label574;
                                                                              }

                                                                              if (var21 == Type.BUTTONPROPERTYSET) {
                                                                                 this.buttonPropertySet = new ButtonPropertySetRecord(var20);
                                                                                 var39 = var18;
                                                                                 var49 = var15;
                                                                                 var7 = var6;
                                                                                 break label574;
                                                                              }

                                                                              if (var21 == Type.CALCMODE) {
                                                                                 CalcModeRecord var78 = new CalcModeRecord(var20);
                                                                                 this.settings.setAutomaticFormulaCalculation(var78.isAutomatic());
                                                                                 var39 = var18;
                                                                                 var49 = var15;
                                                                                 var7 = var6;
                                                                                 break label574;
                                                                              }

                                                                              if (var21 == Type.SAVERECALC) {
                                                                                 SaveRecalcRecord var75 = new SaveRecalcRecord(var20);
                                                                                 this.settings.setRecalculateFormulasBeforeSave(var75.getRecalculateOnSave());
                                                                                 var39 = var18;
                                                                                 var49 = var15;
                                                                                 var7 = var6;
                                                                                 break label574;
                                                                              }

                                                                              if (var21 == Type.GUTS) {
                                                                                 GuttersRecord var72 = new GuttersRecord(var20);
                                                                                 if (var72.getRowOutlineLevel() > 0) {
                                                                                    var1 = var72.getRowOutlineLevel() - 1;
                                                                                 } else {
                                                                                    var1 = 0;
                                                                                 }

                                                                                 this.maxRowOutlineLevel = var1;
                                                                                 if (var72.getColumnOutlineLevel() > 0) {
                                                                                    var1 = var72.getRowOutlineLevel() - 1;
                                                                                 } else {
                                                                                    var1 = 0;
                                                                                 }

                                                                                 this.maxColumnOutlineLevel = var1;
                                                                                 var39 = var18;
                                                                                 var49 = var15;
                                                                                 var7 = var6;
                                                                                 break label574;
                                                                              }

                                                                              if (var21 != Type.BOF) {
                                                                                 var39 = var18;
                                                                                 var49 = var15;
                                                                                 var7 = var6;
                                                                                 if (var21 == Type.EOF) {
                                                                                    var32 = false;
                                                                                    var15 = var15;
                                                                                    break label579;
                                                                                 }
                                                                                 break label574;
                                                                              }

                                                                              BOFRecord var63 = new BOFRecord(var20);
                                                                              Assert.verify(var63.isWorksheet() ^ true);
                                                                              var4 = this.excelFile.getPos();
                                                                              int var33 = var20.getLength();

                                                                              for(Record var59 = this.excelFile.next(); var59.getCode() != Type.EOF.value; var59 = this.excelFile.next()) {
                                                                              }

                                                                              ObjRecord var64;
                                                                              MsoDrawingRecord var66;
                                                                              if (var63.isChart()) {
                                                                                 if (!this.workbook.getWorkbookBof().isBiff8()) {
                                                                                    logger.warn("only biff8 charts are supported");
                                                                                 } else {
                                                                                    if (this.drawingData == null) {
                                                                                       this.drawingData = new DrawingData();
                                                                                    }

                                                                                    if (!this.workbookSettings.getDrawingsDisabled()) {
                                                                                       Chart var36 = new Chart(var13, var12, this.drawingData, var4 - var33 - 4, this.excelFile.getPos(), this.excelFile, this.workbookSettings);
                                                                                       this.charts.add(var36);
                                                                                       if (this.workbook.getDrawingGroup() != null) {
                                                                                          this.workbook.getDrawingGroup().add(var36);
                                                                                       }
                                                                                    }
                                                                                 }

                                                                                 var64 = null;
                                                                                 var66 = null;
                                                                              } else {
                                                                                 var66 = var13;
                                                                                 var64 = var12;
                                                                              }

                                                                              var12 = var64;
                                                                              var13 = var66;
                                                                              var57 = var16;
                                                                              var2 = var2;
                                                                              if (this.sheetBof.isChart()) {
                                                                                 var2 = false;
                                                                                 var12 = var64;
                                                                                 var13 = var66;
                                                                                 var57 = var16;
                                                                              }
                                                                           }

                                                                           var16 = var57;
                                                                           var32 = var2;
                                                                           break label578;
                                                                        }

                                                                        if (!this.workbookSettings.getCellValidationDisabled()) {
                                                                           WorkbookParser var48 = this.workbook;
                                                                           DataValiditySettingsRecord var52 = new DataValiditySettingsRecord(var20, var48, var48, var48.getSettings());
                                                                           var18 = this.dataValidation;
                                                                           if (var18 != null) {
                                                                              ((DataValidation)var18).add(var52);
                                                                              this.addCellValidation(var52.getFirstColumn(), var52.getFirstRow(), var52.getLastColumn(), var52.getLastRow(), var52);
                                                                           } else {
                                                                              logger.warn("cannot add data validity settings");
                                                                           }
                                                                        }
                                                                     }
                                                                  }

                                                                  var39 = var18;
                                                                  var49 = var15;
                                                                  var7 = var6;
                                                                  break label574;
                                                               }

                                                               this.plsRecord = new PLSRecord(var20);

                                                               while(this.excelFile.peek().getType() == Type.CONTINUE) {
                                                                  var20.addContinueRecord(this.excelFile.next());
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }

                                       var39 = var18;
                                       var49 = var15;
                                       var7 = var6;
                                       break label574;
                                    }

                                    var15 = var15;
                                    break label579;
                                 }

                                 PaneRecord var58 = new PaneRecord(var20);
                                 if (var19 != null && var19.getFrozen()) {
                                    this.settings.setVerticalFreeze(var58.getRowsVisible());
                                    this.settings.setHorizontalFreeze(var58.getColumnsVisible());
                                 }
                              }

                              var38 = var15;
                           }
                        }
                     }

                     var39 = var18;
                     var49 = var38;
                     var7 = var6;
                  }

                  var32 = var2;
                  var6 = var7;
                  var38 = var49;
                  var50 = var39;
                  break label578;
               }

               var50 = var18;
               var32 = var2;
               break label579;
            }

            var15 = var38;
         }

         var18 = var50;
      }

      this.excelFile.restorePos();
      if (this.outOfBoundsCells.size() > 0) {
         this.handleOutOfBoundsCells();
      }

      Iterator var34 = this.sharedFormulas.iterator();

      while(var34.hasNext()) {
         Cell[] var35 = ((SharedFormulaRecord)var34.next()).getFormulas(this.formattingRecords, this.nineteenFour);

         for(var1 = 0; var1 < var35.length; ++var1) {
            this.addCell(var35[var1]);
         }
      }

      if (!var6 && var18 != null) {
         this.addCell(this.revertSharedFormula((BaseSharedFormulaRecord)var18));
      }

      if (var13 != null && this.workbook.getDrawingGroup() != null) {
         this.workbook.getDrawingGroup().setDrawingsOmitted(var13, var12);
      }

      if (!var8.isEmpty()) {
         logger.warn("Not all comments have a corresponding Note record");
      }

   }
}
