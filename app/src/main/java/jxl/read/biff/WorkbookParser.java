package jxl.read.biff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.BuiltInName;
import jxl.biff.CellReferenceHelper;
import jxl.biff.EmptyCell;
import jxl.biff.FontRecord;
import jxl.biff.Fonts;
import jxl.biff.FormatRecord;
import jxl.biff.FormattingRecords;
import jxl.biff.NameRangeException;
import jxl.biff.NumFormatRecordsException;
import jxl.biff.RangeImpl;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WorkbookMethods;
import jxl.biff.XCTRecord;
import jxl.biff.XFRecord;
import jxl.biff.drawing.DrawingGroup;
import jxl.biff.drawing.MsoDrawingGroupRecord;
import jxl.biff.drawing.Origin;
import jxl.biff.formula.ExternalSheet;
import jxl.common.Assert;
import jxl.common.Logger;

public class WorkbookParser extends Workbook implements ExternalSheet, WorkbookMethods {
   private static Logger logger = Logger.getLogger(WorkbookParser.class);
   private ArrayList addInFunctions;
   private int bofs;
   private ArrayList boundsheets;
   private ButtonPropertySetRecord buttonPropertySet;
   private boolean containsMacros;
   private CountryRecord countryRecord;
   private DrawingGroup drawingGroup;
   private File excelFile;
   private ExternalSheetRecord externSheet;
   private Fonts fonts;
   private FormattingRecords formattingRecords;
   private SheetImpl lastSheet;
   private int lastSheetIndex;
   private MsoDrawingGroupRecord msoDrawingGroup;
   private ArrayList nameTable;
   private HashMap namedRecords;
   private boolean nineteenFour;
   private WorkbookSettings settings;
   private SSTRecord sharedStrings;
   private ArrayList sheets;
   private ArrayList supbooks;
   private boolean wbProtected;
   private BOFRecord workbookBof;
   private ArrayList xctRecords;

   public WorkbookParser(File var1, WorkbookSettings var2) {
      this.excelFile = var1;
      this.boundsheets = new ArrayList(10);
      this.fonts = new Fonts();
      this.formattingRecords = new FormattingRecords(this.fonts);
      this.sheets = new ArrayList(10);
      this.supbooks = new ArrayList(10);
      this.namedRecords = new HashMap();
      this.lastSheetIndex = -1;
      this.wbProtected = false;
      this.containsMacros = false;
      this.settings = var2;
      this.xctRecords = new ArrayList(10);
   }

   final void addSheet(Sheet var1) {
      this.sheets.add(var1);
   }

   public void close() {
      SheetImpl var1 = this.lastSheet;
      if (var1 != null) {
         var1.clear();
      }

      this.excelFile.clear();
      if (!this.settings.getGCDisabled()) {
         System.gc();
      }

   }

   public boolean containsMacros() {
      return this.containsMacros;
   }

   public Range[] findByName(String var1) {
      NameRecord var4 = (NameRecord)this.namedRecords.get(var1);
      if (var4 == null) {
         return null;
      } else {
         NameRecord.NameRange[] var3 = var4.getRanges();
         Range[] var5 = new Range[var3.length];

         for(int var2 = 0; var2 < var3.length; ++var2) {
            var5[var2] = new RangeImpl(this, this.getExternalSheetIndex(var3[var2].getExternalSheet()), var3[var2].getFirstColumn(), var3[var2].getFirstRow(), this.getLastExternalSheetIndex(var3[var2].getExternalSheet()), var3[var2].getLastColumn(), var3[var2].getLastRow());
         }

         return var5;
      }
   }

   public Cell findCellByName(String var1) {
      NameRecord var5 = (NameRecord)this.namedRecords.get(var1);
      if (var5 == null) {
         return null;
      } else {
         NameRecord.NameRange[] var4 = var5.getRanges();
         Sheet var6 = this.getSheet(this.getExternalSheetIndex(var4[0].getExternalSheet()));
         int var3 = var4[0].getFirstColumn();
         int var2 = var4[0].getFirstRow();
         return (Cell)(var3 <= var6.getColumns() && var2 <= var6.getRows() ? var6.getCell(var3, var2) : new EmptyCell(var3, var2));
      }
   }

   public String[] getAddInFunctionNames() {
      return (String[])this.addInFunctions.toArray(new String[0]);
   }

   public ButtonPropertySetRecord getButtonPropertySet() {
      return this.buttonPropertySet;
   }

   public Cell getCell(String var1) {
      return this.getSheet(CellReferenceHelper.getSheet(var1)).getCell(var1);
   }

   public CompoundFile getCompoundFile() {
      return this.excelFile.getCompoundFile();
   }

   public CountryRecord getCountryRecord() {
      return this.countryRecord;
   }

   public DrawingGroup getDrawingGroup() {
      return this.drawingGroup;
   }

   public int getExternalSheetIndex(int var1) {
      if (this.workbookBof.isBiff7()) {
         return var1;
      } else {
         boolean var2;
         if (this.externSheet != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Assert.verify(var2);
         return this.externSheet.getFirstTabIndex(var1);
      }
   }

   public int getExternalSheetIndex(String var1) {
      return 0;
   }

   public String getExternalSheetName(int var1) {
      if (this.workbookBof.isBiff7()) {
         return ((BoundsheetRecord)this.boundsheets.get(var1)).getName();
      } else {
         int var2 = this.externSheet.getSupbookIndex(var1);
         SupbookRecord var3 = (SupbookRecord)this.supbooks.get(var2);
         var2 = this.externSheet.getFirstTabIndex(var1);
         var1 = this.externSheet.getLastTabIndex(var1);
         SupbookRecord.Type var5 = var3.getType();
         SupbookRecord.Type var6 = SupbookRecord.INTERNAL;
         String var4 = "#REF";
         if (var5 == var6) {
            String var7;
            if (var2 == 65535) {
               var7 = "#REF";
            } else {
               var7 = ((BoundsheetRecord)this.boundsheets.get(var2)).getName();
            }

            if (var1 != 65535) {
               var4 = ((BoundsheetRecord)this.boundsheets.get(var1)).getName();
            }

            if (var2 != var1) {
               var7 = var7 + ':' + var4;
            }

            if (var7.indexOf(39) != -1) {
               var7 = StringHelper.replace(var7, "'", "''");
            }

            if (var7.indexOf(32) != -1) {
               var7 = '\'' + var7 + '\'';
            }

            return var7;
         } else if (var3.getType() == SupbookRecord.EXTERNAL) {
            StringBuffer var8 = new StringBuffer();
            java.io.File var9 = new java.io.File(var3.getFileName());
            var8.append("'");
            var8.append(var9.getAbsolutePath());
            var8.append("[");
            var8.append(var9.getName());
            var8.append("]");
            if (var2 != 65535) {
               var4 = var3.getSheetName(var2);
            }

            var8.append(var4);
            if (var1 != var2) {
               var8.append(var3.getSheetName(var1));
            }

            var8.append("'");
            return var8.toString();
         } else {
            logger.warn("Unknown Supbook 3");
            return "[UNKNOWN]";
         }
      }
   }

   public ExternalSheetRecord getExternalSheetRecord() {
      return this.externSheet;
   }

   public Fonts getFonts() {
      return this.fonts;
   }

   public FormattingRecords getFormattingRecords() {
      return this.formattingRecords;
   }

   public int getIndex(Sheet var1) {
      String var4 = var1.getName();
      Iterator var5 = this.boundsheets.iterator();
      int var2 = 0;
      int var3 = -1;

      while(var5.hasNext() && var3 == -1) {
         if (((BoundsheetRecord)var5.next()).getName().equals(var4)) {
            var3 = var2;
         } else {
            ++var2;
         }
      }

      return var3;
   }

   public int getLastExternalSheetIndex(int var1) {
      if (this.workbookBof.isBiff7()) {
         return var1;
      } else {
         boolean var2;
         if (this.externSheet != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Assert.verify(var2);
         return this.externSheet.getLastTabIndex(var1);
      }
   }

   public int getLastExternalSheetIndex(String var1) {
      return 0;
   }

   public String getLastExternalSheetName(int var1) {
      if (this.workbookBof.isBiff7()) {
         return ((BoundsheetRecord)this.boundsheets.get(var1)).getName();
      } else {
         int var2 = this.externSheet.getSupbookIndex(var1);
         SupbookRecord var4 = (SupbookRecord)this.supbooks.get(var2);
         var1 = this.externSheet.getLastTabIndex(var1);
         SupbookRecord.Type var5 = var4.getType();
         SupbookRecord.Type var6 = SupbookRecord.INTERNAL;
         String var3 = "#REF";
         if (var5 == var6) {
            return var1 == 65535 ? "#REF" : ((BoundsheetRecord)this.boundsheets.get(var1)).getName();
         } else if (var4.getType() == SupbookRecord.EXTERNAL) {
            StringBuffer var7 = new StringBuffer();
            java.io.File var8 = new java.io.File(var4.getFileName());
            var7.append("'");
            var7.append(var8.getAbsolutePath());
            var7.append("[");
            var7.append(var8.getName());
            var7.append("]");
            if (var1 != 65535) {
               var3 = var4.getSheetName(var1);
            }

            var7.append(var3);
            var7.append("'");
            return var7.toString();
         } else {
            logger.warn("Unknown Supbook 4");
            return "[UNKNOWN]";
         }
      }
   }

   public MsoDrawingGroupRecord getMsoDrawingGroupRecord() {
      return this.msoDrawingGroup;
   }

   public String getName(int var1) throws NameRangeException {
      if (var1 >= 0 && var1 < this.nameTable.size()) {
         return ((NameRecord)this.nameTable.get(var1)).getName();
      } else {
         throw new NameRangeException();
      }
   }

   public int getNameIndex(String var1) {
      NameRecord var3 = (NameRecord)this.namedRecords.get(var1);
      int var2;
      if (var3 != null) {
         var2 = var3.getIndex();
      } else {
         var2 = 0;
      }

      return var2;
   }

   public NameRecord[] getNameRecords() {
      NameRecord[] var1 = new NameRecord[this.nameTable.size()];
      return (NameRecord[])this.nameTable.toArray(var1);
   }

   public int getNumberOfSheets() {
      return this.sheets.size();
   }

   public String[] getRangeNames() {
      Object[] var1 = this.namedRecords.keySet().toArray();
      String[] var2 = new String[var1.length];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      return var2;
   }

   public Sheet getReadSheet(int var1) {
      return this.getSheet(var1);
   }

   public WorkbookSettings getSettings() {
      return this.settings;
   }

   public Sheet getSheet(int var1) {
      SheetImpl var2 = this.lastSheet;
      if (var2 != null && this.lastSheetIndex == var1) {
         return var2;
      } else {
         if (var2 != null) {
            var2.clear();
            if (!this.settings.getGCDisabled()) {
               System.gc();
            }
         }

         var2 = (SheetImpl)this.sheets.get(var1);
         this.lastSheet = var2;
         this.lastSheetIndex = var1;
         var2.readSheet();
         return this.lastSheet;
      }
   }

   public Sheet getSheet(String var1) {
      Iterator var4 = this.boundsheets.iterator();
      boolean var3 = false;
      int var2 = 0;

      while(var4.hasNext() && !var3) {
         if (((BoundsheetRecord)var4.next()).getName().equals(var1)) {
            var3 = true;
         } else {
            ++var2;
         }
      }

      Sheet var5;
      if (var3) {
         var5 = this.getSheet(var2);
      } else {
         var5 = null;
      }

      return var5;
   }

   public String[] getSheetNames() {
      int var2 = this.boundsheets.size();
      String[] var3 = new String[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = ((BoundsheetRecord)this.boundsheets.get(var1)).getName();
      }

      return var3;
   }

   public Sheet[] getSheets() {
      Sheet[] var1 = new Sheet[this.getNumberOfSheets()];
      return (Sheet[])this.sheets.toArray(var1);
   }

   public SupbookRecord[] getSupbookRecords() {
      SupbookRecord[] var1 = new SupbookRecord[this.supbooks.size()];
      return (SupbookRecord[])this.supbooks.toArray(var1);
   }

   public BOFRecord getWorkbookBof() {
      return this.workbookBof;
   }

   public XCTRecord[] getXCTRecords() {
      return (XCTRecord[])this.xctRecords.toArray(new XCTRecord[0]);
   }

   public boolean isProtected() {
      return this.wbProtected;
   }

   protected void parse() throws BiffException, PasswordException {
      BOFRecord var5 = new BOFRecord(this.excelFile.next());
      this.workbookBof = var5;
      ++this.bofs;
      if (!var5.isBiff8() && !var5.isBiff7()) {
         throw new BiffException(BiffException.unrecognizedBiffVersion);
      } else if (!var5.isWorkbookGlobals()) {
         throw new BiffException(BiffException.expectedGlobals);
      } else {
         ArrayList var6 = new ArrayList();
         ArrayList var4 = new ArrayList();
         this.nameTable = new ArrayList();
         this.addInFunctions = new ArrayList();
         Record var1 = null;

         while(true) {
            Record var2;
            BoundsheetRecord var11;
            NameRecord var28;
            while(this.bofs == 1) {
               Record var3 = this.excelFile.next();
               if (var3.getType() == Type.SST) {
                  var6.clear();

                  for(var1 = this.excelFile.peek(); var1.getType() == Type.CONTINUE; var1 = this.excelFile.peek()) {
                     var6.add(this.excelFile.next());
                  }

                  this.sharedStrings = new SSTRecord(var3, (Record[])var6.toArray(new Record[var6.size()]), this.settings);
                  var1 = var3;
               } else {
                  if (var3.getType() == Type.FILEPASS) {
                     throw new PasswordException();
                  }

                  if (var3.getType() == Type.NAME) {
                     if (var5.isBiff8()) {
                        var28 = new NameRecord(var3, this.settings, this.nameTable.size());
                     } else {
                        var28 = new NameRecord(var3, this.settings, this.nameTable.size(), NameRecord.biff7);
                     }

                     this.nameTable.add(var28);
                     if (var28.isGlobal()) {
                        this.namedRecords.put(var28.getName(), var28);
                        var1 = var3;
                     } else {
                        var4.add(var28);
                        var1 = var3;
                     }
                  } else if (var3.getType() == Type.FONT) {
                     FontRecord var27;
                     if (var5.isBiff8()) {
                        var27 = new FontRecord(var3, this.settings);
                     } else {
                        var27 = new FontRecord(var3, this.settings, FontRecord.biff7);
                     }

                     this.fonts.addFont(var27);
                     var1 = var3;
                  } else if (var3.getType() == Type.PALETTE) {
                     jxl.biff.PaletteRecord var26 = new jxl.biff.PaletteRecord(var3);
                     this.formattingRecords.setPalette(var26);
                     var1 = var3;
                  } else if (var3.getType() == Type.NINETEENFOUR) {
                     this.nineteenFour = (new NineteenFourRecord(var3)).is1904();
                     var1 = var3;
                  } else if (var3.getType() == Type.FORMAT) {
                     FormatRecord var25;
                     if (var5.isBiff8()) {
                        var25 = new FormatRecord(var3, this.settings, FormatRecord.biff8);
                     } else {
                        var25 = new FormatRecord(var3, this.settings, FormatRecord.biff7);
                     }

                     try {
                        this.formattingRecords.addFormat(var25);
                     } catch (NumFormatRecordsException var7) {
                        Assert.verify(false, var7.getMessage());
                        var1 = var3;
                        continue;
                     }

                     var1 = var3;
                  } else if (var3.getType() == Type.XF) {
                     XFRecord var24;
                     if (var5.isBiff8()) {
                        var24 = new XFRecord(var3, this.settings, XFRecord.biff8);
                     } else {
                        var24 = new XFRecord(var3, this.settings, XFRecord.biff7);
                     }

                     try {
                        this.formattingRecords.addStyle(var24);
                     } catch (NumFormatRecordsException var8) {
                        Assert.verify(false, var8.getMessage());
                        var1 = var3;
                        continue;
                     }

                     var1 = var3;
                  } else if (var3.getType() == Type.BOUNDSHEET) {
                     if (var5.isBiff8()) {
                        var11 = new BoundsheetRecord(var3, this.settings);
                     } else {
                        var11 = new BoundsheetRecord(var3, BoundsheetRecord.biff7);
                     }

                     if (var11.isSheet()) {
                        this.boundsheets.add(var11);
                        var1 = var3;
                     } else {
                        var1 = var3;
                        if (var11.isChart()) {
                           var1 = var3;
                           if (!this.settings.getDrawingsDisabled()) {
                              this.boundsheets.add(var11);
                              var1 = var3;
                           }
                        }
                     }
                  } else if (var3.getType() == Type.EXTERNSHEET) {
                     if (var5.isBiff8()) {
                        this.externSheet = new ExternalSheetRecord(var3, this.settings);
                        var1 = var3;
                     } else {
                        this.externSheet = new ExternalSheetRecord(var3, this.settings, ExternalSheetRecord.biff7);
                        var1 = var3;
                     }
                  } else if (var3.getType() == Type.XCT) {
                     XCTRecord var23 = new XCTRecord(var3);
                     this.xctRecords.add(var23);
                     var1 = var3;
                  } else if (var3.getType() == Type.CODEPAGE) {
                     CodepageRecord var22 = new CodepageRecord(var3);
                     this.settings.setCharacterSet(var22.getCharacterSet());
                     var1 = var3;
                  } else if (var3.getType() != Type.SUPBOOK) {
                     if (var3.getType() == Type.EXTERNNAME) {
                        ExternalNameRecord var10 = new ExternalNameRecord(var3, this.settings);
                        var1 = var3;
                        if (var10.isAddInFunction()) {
                           this.addInFunctions.add(var10.getName());
                           var1 = var3;
                        }
                     } else if (var3.getType() == Type.PROTECT) {
                        this.wbProtected = (new ProtectRecord(var3)).isProtected();
                        var1 = var3;
                     } else if (var3.getType() == Type.OBJPROJ) {
                        this.containsMacros = true;
                        var1 = var3;
                     } else if (var3.getType() == Type.COUNTRY) {
                        this.countryRecord = new CountryRecord(var3);
                        var1 = var3;
                     } else if (var3.getType() == Type.MSODRAWINGGROUP) {
                        var1 = var3;
                        if (!this.settings.getDrawingsDisabled()) {
                           this.msoDrawingGroup = new MsoDrawingGroupRecord(var3);
                           if (this.drawingGroup == null) {
                              this.drawingGroup = new DrawingGroup(Origin.READ);
                           }

                           this.drawingGroup.add(this.msoDrawingGroup);
                           var2 = this.excelFile.peek();

                           while(true) {
                              var1 = var3;
                              if (var2.getType() != Type.CONTINUE) {
                                 break;
                              }

                              this.drawingGroup.add(this.excelFile.next());
                              var2 = this.excelFile.peek();
                           }
                        }
                     } else if (var3.getType() == Type.BUTTONPROPERTYSET) {
                        this.buttonPropertySet = new ButtonPropertySetRecord(var3);
                        var1 = var3;
                     } else if (var3.getType() == Type.EOF) {
                        --this.bofs;
                        var1 = var3;
                     } else if (var3.getType() == Type.REFRESHALL) {
                        RefreshAllRecord var13 = new RefreshAllRecord(var3);
                        this.settings.setRefreshAll(var13.getRefreshAll());
                        var1 = var3;
                     } else if (var3.getType() == Type.TEMPLATE) {
                        TemplateRecord var15 = new TemplateRecord(var3);
                        this.settings.setTemplate(var15.getTemplate());
                        var1 = var3;
                     } else if (var3.getType() == Type.EXCEL9FILE) {
                        Excel9FileRecord var17 = new Excel9FileRecord(var3);
                        this.settings.setExcel9File(var17.getExcel9File());
                        var1 = var3;
                     } else if (var3.getType() == Type.WINDOWPROTECT) {
                        WindowProtectedRecord var18 = new WindowProtectedRecord(var3);
                        this.settings.setWindowProtected(var18.getWindowProtected());
                        var1 = var3;
                     } else if (var3.getType() == Type.HIDEOBJ) {
                        HideobjRecord var19 = new HideobjRecord(var3);
                        this.settings.setHideobj(var19.getHideMode());
                        var1 = var3;
                     } else {
                        var1 = var3;
                        if (var3.getType() == Type.WRITEACCESS) {
                           WriteAccessRecord var20 = new WriteAccessRecord(var3, var5.isBiff8(), this.settings);
                           this.settings.setWriteAccess(var20.getWriteAccess());
                           var1 = var3;
                        }
                     }
                  } else {
                     for(var1 = this.excelFile.peek(); var1.getType() == Type.CONTINUE; var1 = this.excelFile.peek()) {
                        var3.addContinueRecord(this.excelFile.next());
                     }

                     SupbookRecord var9 = new SupbookRecord(var3, this.settings);
                     this.supbooks.add(var9);
                     var1 = var3;
                  }
               }
            }

            BOFRecord var12;
            BOFRecord var16;
            if (this.excelFile.hasNext()) {
               var2 = this.excelFile.next();
               var1 = var2;
               if (var2.getType() == Type.BOF) {
                  var12 = new BOFRecord(var2);
                  var1 = var2;
                  var16 = var12;
               } else {
                  var16 = null;
               }
            } else {
               var16 = null;
            }

            while(true) {
               while(var16 != null && this.getNumberOfSheets() < this.boundsheets.size()) {
                  if (!var16.isBiff8() && !var16.isBiff7()) {
                     throw new BiffException(BiffException.unrecognizedBiffVersion);
                  }

                  SheetImpl var14;
                  if (var16.isWorksheet()) {
                     var14 = new SheetImpl(this.excelFile, this.sharedStrings, this.formattingRecords, var16, this.workbookBof, this.nineteenFour, this);
                     var11 = (BoundsheetRecord)this.boundsheets.get(this.getNumberOfSheets());
                     var14.setName(var11.getName());
                     var14.setHidden(var11.isHidden());
                     this.addSheet(var14);
                  } else if (var16.isChart()) {
                     var14 = new SheetImpl(this.excelFile, this.sharedStrings, this.formattingRecords, var16, this.workbookBof, this.nineteenFour, this);
                     var11 = (BoundsheetRecord)this.boundsheets.get(this.getNumberOfSheets());
                     var14.setName(var11.getName());
                     var14.setHidden(var11.isHidden());
                     this.addSheet(var14);
                  } else {
                     logger.warn("BOF is unrecognized");
                     var2 = var1;

                     while(true) {
                        var1 = var2;
                        if (!this.excelFile.hasNext()) {
                           break;
                        }

                        var1 = var2;
                        if (var2.getType() == Type.EOF) {
                           break;
                        }

                        var2 = this.excelFile.next();
                     }
                  }

                  if (this.excelFile.hasNext()) {
                     var2 = this.excelFile.next();
                     var1 = var2;
                     if (var2.getType() == Type.BOF) {
                        var12 = new BOFRecord(var2);
                        var1 = var2;
                        var16 = var12;
                        continue;
                     }
                  }

                  var16 = null;
               }

               Iterator var21 = var4.iterator();

               while(true) {
                  while(var21.hasNext()) {
                     var28 = (NameRecord)var21.next();
                     if (var28.getBuiltInName() == null) {
                        logger.warn("Usage of a local non-builtin name");
                     } else if (var28.getBuiltInName() == BuiltInName.PRINT_AREA || var28.getBuiltInName() == BuiltInName.PRINT_TITLES) {
                        ((SheetImpl)this.sheets.get(var28.getSheetRef() - 1)).addLocalName(var28);
                     }
                  }

                  return;
               }
            }
         }
      }
   }
}
