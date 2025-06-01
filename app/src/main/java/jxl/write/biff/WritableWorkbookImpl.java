package jxl.write.biff;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.BuiltInName;
import jxl.biff.CellReferenceHelper;
import jxl.biff.CountryCode;
import jxl.biff.Fonts;
import jxl.biff.FormattingRecords;
import jxl.biff.IndexMapping;
import jxl.biff.IntegerHelper;
import jxl.biff.RangeImpl;
import jxl.biff.WorkbookMethods;
import jxl.biff.XCTRecord;
import jxl.biff.drawing.Drawing;
import jxl.biff.drawing.DrawingGroup;
import jxl.biff.drawing.DrawingGroupObject;
import jxl.biff.drawing.Origin;
import jxl.biff.formula.ExternalSheet;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.Colour;
import jxl.format.RGB;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WritableWorkbookImpl extends WritableWorkbook implements ExternalSheet, WorkbookMethods {
   private static Object SYNCHRONIZER = new Object();
   private static Logger logger = Logger.getLogger(WritableWorkbookImpl.class);
   private String[] addInFunctionNames;
   private ButtonPropertySetRecord buttonPropertySet;
   private boolean closeStream;
   private boolean containsMacros;
   private CountryRecord countryRecord;
   private DrawingGroup drawingGroup;
   private ExternalSheetRecord externSheet;
   private Fonts fonts;
   private FormattingRecords formatRecords;
   private HashMap nameRecords;
   private ArrayList names;
   private File outputFile;
   private ArrayList rcirCells;
   private WorkbookSettings settings;
   private SharedStrings sharedStrings;
   private ArrayList sheets;
   private Styles styles;
   private ArrayList supbooks;
   private boolean wbProtected;
   private XCTRecord[] xctRecords;

   public WritableWorkbookImpl(OutputStream param1, Workbook param2, boolean param3, WorkbookSettings param4) throws IOException {
      // $FF: Couldn't be decompiled
   }

   public WritableWorkbookImpl(OutputStream param1, boolean param2, WorkbookSettings param3) throws IOException {
      // $FF: Couldn't be decompiled
   }

   private void copyWorkbook(Workbook var1) {
      int var3 = var1.getNumberOfSheets();
      this.wbProtected = var1.isProtected();

      for(int var2 = 0; var2 < var3; ++var2) {
         Sheet var4 = var1.getSheet(var2);
         ((WritableSheetImpl)this.createSheet(var4.getName(), var2, false)).copy(var4);
      }

   }

   private WritableSheet createSheet(String var1, int var2, boolean var3) {
      WritableSheetImpl var5 = new WritableSheetImpl(var1, this.outputFile, this.formatRecords, this.sharedStrings, this.settings, this);
      if (var2 <= 0) {
         this.sheets.add(0, var5);
         var2 = 0;
      } else if (var2 > this.sheets.size()) {
         var2 = this.sheets.size();
         this.sheets.add(var5);
      } else {
         this.sheets.add(var2, var5);
      }

      if (var3) {
         ExternalSheetRecord var4 = this.externSheet;
         if (var4 != null) {
            var4.sheetInserted(var2);
         }
      }

      ArrayList var6 = this.supbooks;
      if (var6 != null && var6.size() > 0) {
         SupbookRecord var7 = (SupbookRecord)this.supbooks.get(0);
         if (var7.getType() == SupbookRecord.INTERNAL) {
            var7.adjustInternal(this.sheets.size());
         }
      }

      return var5;
   }

   private int getInternalSheetIndex(String var1) {
      String[] var3 = this.getSheetNames();
      int var2 = 0;

      while(true) {
         if (var2 >= var3.length) {
            var2 = -1;
            break;
         }

         if (var1.equals(var3[var2])) {
            break;
         }

         ++var2;
      }

      return var2;
   }

   private void rationalize() {
      IndexMapping var4 = this.formatRecords.rationalizeFonts();
      IndexMapping var2 = this.formatRecords.rationalizeDisplayFormats();
      IndexMapping var3 = this.formatRecords.rationalize(var4, var2);

      for(int var1 = 0; var1 < this.sheets.size(); ++var1) {
         ((WritableSheetImpl)this.sheets.get(var1)).rationalize(var3, var4, var2);
      }

   }

   void addDrawing(DrawingGroupObject var1) {
      if (this.drawingGroup == null) {
         this.drawingGroup = new DrawingGroup(Origin.WRITE);
      }

      this.drawingGroup.add(var1);
   }

   public void addNameArea(String var1, WritableSheet var2, int var3, int var4, int var5, int var6) {
      this.addNameArea(var1, var2, var3, var4, var5, var6, true);
   }

   void addNameArea(String var1, WritableSheet var2, int var3, int var4, int var5, int var6, boolean var7) {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      int var8 = this.getExternalSheetIndex(var2.getName());
      NameRecord var9 = new NameRecord(var1, this.names.size(), var8, var4, var6, var3, var5, var7);
      this.names.add(var9);
      this.nameRecords.put(var1, var9);
   }

   void addNameArea(BuiltInName var1, WritableSheet var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, boolean var11) {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      NameRecord var12 = new NameRecord(var1, this.getInternalSheetIndex(var2.getName()), this.getExternalSheetIndex(var2.getName()), var8, var10, var7, var9, var4, var6, var3, var5, var11);
      this.names.add(var12);
      this.nameRecords.put(var1, var12);
   }

   void addNameArea(BuiltInName var1, WritableSheet var2, int var3, int var4, int var5, int var6, boolean var7) {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      NameRecord var8 = new NameRecord(var1, this.getInternalSheetIndex(var2.getName()), this.getExternalSheetIndex(var2.getName()), var4, var6, var3, var5, var7);
      this.names.add(var8);
      this.nameRecords.put(var1, var8);
   }

   void addRCIRCell(CellValue var1) {
      this.rcirCells.add(var1);
   }

   public void close() throws IOException, JxlWriteException {
      this.outputFile.close(this.closeStream);
   }

   void columnInserted(WritableSheetImpl var1, int var2) {
      int var3 = this.getExternalSheetIndex(var1.getName());
      Iterator var4 = this.rcirCells.iterator();

      while(var4.hasNext()) {
         ((CellValue)var4.next()).columnInserted(var1, var3, var2);
      }

      ArrayList var5 = this.names;
      if (var5 != null) {
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            ((NameRecord)var6.next()).columnInserted(var3, var2);
         }
      }

   }

   void columnRemoved(WritableSheetImpl var1, int var2) {
      int var3 = this.getExternalSheetIndex(var1.getName());
      Iterator var4 = this.rcirCells.iterator();

      while(var4.hasNext()) {
         ((CellValue)var4.next()).columnRemoved(var1, var3, var2);
      }

      ArrayList var6 = new ArrayList();
      ArrayList var8 = this.names;
      if (var8 != null) {
         Iterator var5 = var8.iterator();

         while(var5.hasNext()) {
            NameRecord var9 = (NameRecord)var5.next();
            if (var9.columnRemoved(var3, var2)) {
               var6.add(var9);
            }
         }

         var4 = var6.iterator();

         while(var4.hasNext()) {
            NameRecord var7 = (NameRecord)var4.next();
            Assert.verify(this.names.remove(var7), "Could not remove name " + var7.getName());
         }
      }

   }

   public void copySheet(int var1, String var2, int var3) {
      WritableSheet var4 = this.getSheet(var1);
      ((WritableSheetImpl)this.createSheet(var2, var3)).copy(var4);
   }

   public void copySheet(String var1, String var2, int var3) {
      WritableSheet var4 = this.getSheet(var1);
      ((WritableSheetImpl)this.createSheet(var2, var3)).copy(var4);
   }

   DrawingGroup createDrawingGroup() {
      if (this.drawingGroup == null) {
         this.drawingGroup = new DrawingGroup(Origin.WRITE);
      }

      return this.drawingGroup;
   }

   public WritableSheet createSheet(String var1, int var2) {
      return this.createSheet(var1, var2, true);
   }

   public Range[] findByName(String var1) {
      NameRecord var4 = (NameRecord)this.nameRecords.get(var1);
      if (var4 == null) {
         return null;
      } else {
         NameRecord.NameRange[] var5 = var4.getRanges();
         Range[] var3 = new Range[var5.length];

         for(int var2 = 0; var2 < var5.length; ++var2) {
            var3[var2] = new RangeImpl(this, this.getExternalSheetIndex(var5[var2].getExternalSheet()), var5[var2].getFirstColumn(), var5[var2].getFirstRow(), this.getLastExternalSheetIndex(var5[var2].getExternalSheet()), var5[var2].getLastColumn(), var5[var2].getLastRow());
         }

         return var3;
      }
   }

   public WritableCell findCellByName(String var1) {
      NameRecord var2 = (NameRecord)this.nameRecords.get(var1);
      if (var2 == null) {
         return null;
      } else {
         NameRecord.NameRange[] var3 = var2.getRanges();
         return this.getSheet(this.getExternalSheetIndex(var3[0].getExternalSheet())).getWritableCell(var3[0].getFirstColumn(), var3[0].getFirstRow());
      }
   }

   public RGB getColourRGB(Colour var1) {
      return this.formatRecords.getColourRGB(var1);
   }

   DrawingGroup getDrawingGroup() {
      return this.drawingGroup;
   }

   public int getExternalSheetIndex(int var1) {
      ExternalSheetRecord var3 = this.externSheet;
      if (var3 == null) {
         return var1;
      } else {
         boolean var2;
         if (var3 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Assert.verify(var2);
         return this.externSheet.getFirstTabIndex(var1);
      }
   }

   public int getExternalSheetIndex(String var1) {
      if (this.externSheet == null) {
         this.externSheet = new ExternalSheetRecord();
         ArrayList var7 = new ArrayList();
         this.supbooks = var7;
         var7.add(new SupbookRecord(this.getNumberOfSheets(), this.settings));
      }

      Iterator var12 = this.sheets.iterator();
      int var4 = 0;
      boolean var3 = false;
      int var2 = 0;

      while(var12.hasNext() && !var3) {
         if (((WritableSheetImpl)var12.next()).getName().equals(var1)) {
            var3 = true;
         } else {
            ++var2;
         }
      }

      if (var3) {
         SupbookRecord var14 = (SupbookRecord)this.supbooks.get(0);
         if (var14.getType() != SupbookRecord.INTERNAL || var14.getNumberOfSheets() != this.getNumberOfSheets()) {
            logger.warn("Cannot find sheet " + var1 + " in supbook record");
         }

         return this.externSheet.getIndex(0, var2);
      } else {
         var2 = var1.lastIndexOf(93);
         int var5 = var1.lastIndexOf(91);
         int var10 = -1;
         if (var2 != -1 && var5 != -1) {
            String var13 = var1.substring(var2 + 1);
            String var8 = var1.substring(var5 + 1, var2);
            var1 = var1.substring(0, var5);
            var8 = var1 + var8;
            SupbookRecord var9 = null;
            boolean var11 = false;

            for(var2 = var4; var2 < this.supbooks.size() && !var11; var10 = var4) {
               var9 = (SupbookRecord)this.supbooks.get(var2);
               boolean var6 = var11;
               var4 = var10;
               if (var9.getType() == SupbookRecord.EXTERNAL) {
                  var6 = var11;
                  var4 = var10;
                  if (var9.getFileName().equals(var8)) {
                     var6 = true;
                     var4 = var2;
                  }
               }

               ++var2;
               var11 = var6;
            }

            if (!var11) {
               var9 = new SupbookRecord(var8, this.settings);
               var10 = this.supbooks.size();
               this.supbooks.add(var9);
            }

            var2 = var9.getSheetIndex(var13);
            return this.externSheet.getIndex(var10, var2);
         } else {
            logger.warn("Square brackets");
            return -1;
         }
      }
   }

   public String getExternalSheetName(int var1) {
      int var2 = this.externSheet.getSupbookIndex(var1);
      SupbookRecord var3 = (SupbookRecord)this.supbooks.get(var2);
      var1 = this.externSheet.getFirstTabIndex(var1);
      if (var3.getType() == SupbookRecord.INTERNAL) {
         return this.getSheet(var1).getName();
      } else if (var3.getType() == SupbookRecord.EXTERNAL) {
         return var3.getFileName() + var3.getSheetName(var1);
      } else {
         logger.warn("Unknown Supbook 1");
         return "[UNKNOWN]";
      }
   }

   public int getLastExternalSheetIndex(int var1) {
      ExternalSheetRecord var3 = this.externSheet;
      if (var3 == null) {
         return var1;
      } else {
         boolean var2;
         if (var3 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Assert.verify(var2);
         return this.externSheet.getLastTabIndex(var1);
      }
   }

   public int getLastExternalSheetIndex(String var1) {
      if (this.externSheet == null) {
         this.externSheet = new ExternalSheetRecord();
         ArrayList var5 = new ArrayList();
         this.supbooks = var5;
         var5.add(new SupbookRecord(this.getNumberOfSheets(), this.settings));
      }

      Iterator var7 = this.sheets.iterator();
      boolean var4 = true;
      boolean var3 = false;
      int var2 = 0;

      while(var7.hasNext() && !var3) {
         if (((WritableSheetImpl)var7.next()).getName().equals(var1)) {
            var3 = true;
         } else {
            ++var2;
         }
      }

      if (!var3) {
         return -1;
      } else {
         SupbookRecord var6 = (SupbookRecord)this.supbooks.get(0);
         if (var6.getType() != SupbookRecord.INTERNAL || var6.getNumberOfSheets() != this.getNumberOfSheets()) {
            var4 = false;
         }

         Assert.verify(var4);
         return this.externSheet.getIndex(0, var2);
      }
   }

   public String getLastExternalSheetName(int var1) {
      int var2 = this.externSheet.getSupbookIndex(var1);
      SupbookRecord var3 = (SupbookRecord)this.supbooks.get(var2);
      var1 = this.externSheet.getLastTabIndex(var1);
      if (var3.getType() == SupbookRecord.INTERNAL) {
         return this.getSheet(var1).getName();
      } else {
         if (var3.getType() == SupbookRecord.EXTERNAL) {
            Assert.verify(false);
         }

         logger.warn("Unknown Supbook 2");
         return "[UNKNOWN]";
      }
   }

   public String getName(int var1) {
      boolean var2;
      if (var1 >= 0 && var1 < this.names.size()) {
         var2 = true;
      } else {
         var2 = false;
      }

      Assert.verify(var2);
      return ((NameRecord)this.names.get(var1)).getName();
   }

   public int getNameIndex(String var1) {
      NameRecord var3 = (NameRecord)this.nameRecords.get(var1);
      int var2;
      if (var3 != null) {
         var2 = var3.getIndex();
      } else {
         var2 = -1;
      }

      return var2;
   }

   public int getNumberOfSheets() {
      return this.sheets.size();
   }

   public String[] getRangeNames() {
      ArrayList var2 = this.names;
      int var1 = 0;
      if (var2 == null) {
         return new String[0];
      } else {
         String[] var3;
         for(var3 = new String[var2.size()]; var1 < this.names.size(); ++var1) {
            var3[var1] = ((NameRecord)this.names.get(var1)).getName();
         }

         return var3;
      }
   }

   public Sheet getReadSheet(int var1) {
      return this.getSheet(var1);
   }

   WorkbookSettings getSettings() {
      return this.settings;
   }

   public WritableSheet getSheet(int var1) {
      return (WritableSheet)this.sheets.get(var1);
   }

   public WritableSheet getSheet(String var1) {
      Iterator var6 = this.sheets.iterator();
      Object var4 = null;
      boolean var2 = false;
      WritableSheet var3 = null;

      while(var6.hasNext() && !var2) {
         WritableSheet var5 = (WritableSheet)var6.next();
         var3 = var5;
         if (var5.getName().equals(var1)) {
            var2 = true;
            var3 = var5;
         }
      }

      WritableSheet var7 = (WritableSheet)var4;
      if (var2) {
         var7 = var3;
      }

      return var7;
   }

   public String[] getSheetNames() {
      int var2 = this.getNumberOfSheets();
      String[] var3 = new String[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = this.getSheet(var1).getName();
      }

      return var3;
   }

   public WritableSheet[] getSheets() {
      WritableSheet[] var2 = new WritableSheet[this.getNumberOfSheets()];

      for(int var1 = 0; var1 < this.getNumberOfSheets(); ++var1) {
         var2[var1] = this.getSheet(var1);
      }

      return var2;
   }

   Styles getStyles() {
      return this.styles;
   }

   public jxl.read.biff.BOFRecord getWorkbookBof() {
      return null;
   }

   public WritableCell getWritableCell(String var1) {
      return this.getSheet(CellReferenceHelper.getSheet(var1)).getWritableCell(var1);
   }

   public WritableSheet importSheet(String var1, int var2, Sheet var3) {
      WritableSheet var4 = this.createSheet(var1, var2);
      ((WritableSheetImpl)var4).importSheet(var3);
      return var4;
   }

   public WritableSheet moveSheet(int var1, int var2) {
      var1 = Math.min(Math.max(var1, 0), this.sheets.size() - 1);
      var2 = Math.min(Math.max(var2, 0), this.sheets.size() - 1);
      WritableSheet var3 = (WritableSheet)this.sheets.remove(var1);
      this.sheets.add(var2, var3);
      return var3;
   }

   void removeDrawing(Drawing var1) {
      boolean var2;
      if (this.drawingGroup != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      Assert.verify(var2);
      this.drawingGroup.remove(var1);
   }

   public void removeRangeName(String var1) {
      Iterator var4 = this.names.iterator();
      boolean var3 = false;
      int var2 = 0;

      while(var4.hasNext() && !var3) {
         if (((NameRecord)var4.next()).getName().equals(var1)) {
            var3 = true;
         } else {
            ++var2;
         }
      }

      if (var3) {
         this.names.remove(var2);
         if (this.nameRecords.remove(var1) == null) {
            logger.warn("Could not remove " + var1 + " from index lookups");
         }
      }

   }

   public void removeSheet(int var1) {
      ArrayList var5;
      if (var1 <= 0) {
         this.sheets.remove(0);
         var1 = 0;
      } else if (var1 >= this.sheets.size()) {
         var1 = this.sheets.size() - 1;
         var5 = this.sheets;
         var5.remove(var5.size() - 1);
      } else {
         this.sheets.remove(var1);
      }

      ExternalSheetRecord var6 = this.externSheet;
      if (var6 != null) {
         var6.sheetRemoved(var1);
      }

      var5 = this.supbooks;
      if (var5 != null && var5.size() > 0) {
         SupbookRecord var7 = (SupbookRecord)this.supbooks.get(0);
         if (var7.getType() == SupbookRecord.INTERNAL) {
            var7.adjustInternal(this.sheets.size());
         }
      }

      var5 = this.names;
      if (var5 != null && var5.size() > 0) {
         for(int var2 = 0; var2 < this.names.size(); ++var2) {
            NameRecord var8 = (NameRecord)this.names.get(var2);
            int var4 = var8.getSheetRef();
            int var3 = var1 + 1;
            if (var4 == var3) {
               var8.setSheetRef(0);
            } else if (var4 > var3) {
               var3 = var4;
               if (var4 < 1) {
                  var3 = 1;
               }

               var8.setSheetRef(var3 - 1);
            }
         }
      }

   }

   void rowInserted(WritableSheetImpl var1, int var2) {
      int var3 = this.getExternalSheetIndex(var1.getName());
      Iterator var4 = this.rcirCells.iterator();

      while(var4.hasNext()) {
         ((CellValue)var4.next()).rowInserted(var1, var3, var2);
      }

      ArrayList var5 = this.names;
      if (var5 != null) {
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            ((NameRecord)var6.next()).rowInserted(var3, var2);
         }
      }

   }

   void rowRemoved(WritableSheetImpl var1, int var2) {
      int var3 = this.getExternalSheetIndex(var1.getName());
      Iterator var4 = this.rcirCells.iterator();

      while(var4.hasNext()) {
         ((CellValue)var4.next()).rowRemoved(var1, var3, var2);
      }

      ArrayList var6 = new ArrayList();
      ArrayList var8 = this.names;
      if (var8 != null) {
         Iterator var5 = var8.iterator();

         while(var5.hasNext()) {
            NameRecord var9 = (NameRecord)var5.next();
            if (var9.rowRemoved(var3, var2)) {
               var6.add(var9);
            }
         }

         var4 = var6.iterator();

         while(var4.hasNext()) {
            NameRecord var7 = (NameRecord)var4.next();
            Assert.verify(this.names.remove(var7), "Could not remove name " + var7.getName());
         }
      }

   }

   public void setColourRGB(Colour var1, int var2, int var3, int var4) {
      this.formatRecords.setColourRGB(var1, var2, var3, var4);
   }

   public void setOutputFile(java.io.File var1) throws IOException {
      FileOutputStream var2 = new FileOutputStream(var1);
      this.outputFile.setOutputFile(var2);
   }

   public void setProtected(boolean var1) {
      this.wbProtected = var1;
   }

   public void write() throws IOException {
      byte var4 = 0;

      int var1;
      for(var1 = 0; var1 < this.getNumberOfSheets(); ++var1) {
         WritableSheetImpl var5 = (WritableSheetImpl)this.getSheet(var1);
         var5.checkMergedBorders();
         Range var6 = var5.getSettings().getPrintArea();
         if (var6 != null) {
            this.addNameArea((BuiltInName)BuiltInName.PRINT_AREA, var5, var6.getTopLeft().getColumn(), var6.getTopLeft().getRow(), var6.getBottomRight().getColumn(), var6.getBottomRight().getRow(), false);
         }

         Range var7 = var5.getSettings().getPrintTitlesRow();
         var6 = var5.getSettings().getPrintTitlesCol();
         if (var7 != null && var6 != null) {
            this.addNameArea(BuiltInName.PRINT_TITLES, var5, var7.getTopLeft().getColumn(), var7.getTopLeft().getRow(), var7.getBottomRight().getColumn(), var7.getBottomRight().getRow(), var6.getTopLeft().getColumn(), var6.getTopLeft().getRow(), var6.getBottomRight().getColumn(), var6.getBottomRight().getRow(), false);
         } else if (var7 != null) {
            this.addNameArea((BuiltInName)BuiltInName.PRINT_TITLES, var5, var7.getTopLeft().getColumn(), var7.getTopLeft().getRow(), var7.getBottomRight().getColumn(), var7.getBottomRight().getRow(), false);
         } else if (var6 != null) {
            this.addNameArea((BuiltInName)BuiltInName.PRINT_TITLES, var5, var6.getTopLeft().getColumn(), var6.getTopLeft().getRow(), var6.getBottomRight().getColumn(), var6.getBottomRight().getRow(), false);
         }
      }

      if (!this.settings.getRationalizationDisabled()) {
         this.rationalize();
      }

      BOFRecord var8 = new BOFRecord(BOFRecord.workbookGlobals);
      this.outputFile.write(var8);
      if (this.settings.getTemplate()) {
         TemplateRecord var9 = new TemplateRecord();
         this.outputFile.write(var9);
      }

      InterfaceHeaderRecord var10 = new InterfaceHeaderRecord();
      this.outputFile.write(var10);
      MMSRecord var11 = new MMSRecord(0, 0);
      this.outputFile.write(var11);
      InterfaceEndRecord var12 = new InterfaceEndRecord();
      this.outputFile.write(var12);
      WriteAccessRecord var14 = new WriteAccessRecord(this.settings.getWriteAccess());
      this.outputFile.write(var14);
      CodepageRecord var16 = new CodepageRecord();
      this.outputFile.write(var16);
      DSFRecord var18 = new DSFRecord();
      this.outputFile.write(var18);
      if (this.settings.getExcel9File()) {
         Excel9FileRecord var19 = new Excel9FileRecord();
         this.outputFile.write(var19);
      }

      TabIdRecord var20 = new TabIdRecord(this.getNumberOfSheets());
      this.outputFile.write(var20);
      if (this.containsMacros) {
         ObjProjRecord var21 = new ObjProjRecord();
         this.outputFile.write(var21);
      }

      ButtonPropertySetRecord var22 = this.buttonPropertySet;
      if (var22 != null) {
         this.outputFile.write(var22);
      }

      FunctionGroupCountRecord var23 = new FunctionGroupCountRecord();
      this.outputFile.write(var23);
      WindowProtectRecord var24 = new WindowProtectRecord(this.settings.getWindowProtected());
      this.outputFile.write(var24);
      ProtectRecord var25 = new ProtectRecord(this.wbProtected);
      this.outputFile.write(var25);
      PasswordRecord var26 = new PasswordRecord((String)null);
      this.outputFile.write(var26);
      Prot4RevRecord var27 = new Prot4RevRecord(false);
      this.outputFile.write(var27);
      Prot4RevPassRecord var28 = new Prot4RevPassRecord();
      this.outputFile.write(var28);
      var1 = 0;
      byte var3 = 0;

      int var2;
      for(var2 = var3; var1 < this.getNumberOfSheets() && var3 == 0; ++var1) {
         if (((WritableSheetImpl)this.getSheet(var1)).getSettings().isSelected()) {
            var2 = var1;
            var3 = 1;
         }
      }

      if (var3 == 0) {
         ((WritableSheetImpl)this.getSheet(0)).getSettings().setSelected(true);
         var2 = 0;
      }

      Window1Record var29 = new Window1Record(var2);
      this.outputFile.write(var29);
      BackupRecord var30 = new BackupRecord(false);
      this.outputFile.write(var30);
      HideobjRecord var31 = new HideobjRecord(this.settings.getHideobj());
      this.outputFile.write(var31);
      NineteenFourRecord var32 = new NineteenFourRecord(false);
      this.outputFile.write(var32);
      PrecisionRecord var33 = new PrecisionRecord(false);
      this.outputFile.write(var33);
      RefreshAllRecord var34 = new RefreshAllRecord(this.settings.getRefreshAll());
      this.outputFile.write(var34);
      BookboolRecord var35 = new BookboolRecord(true);
      this.outputFile.write(var35);
      this.fonts.write(this.outputFile);
      this.formatRecords.write(this.outputFile);
      if (this.formatRecords.getPalette() != null) {
         this.outputFile.write(this.formatRecords.getPalette());
      }

      UsesElfsRecord var36 = new UsesElfsRecord();
      this.outputFile.write(var36);
      int[] var17 = new int[this.getNumberOfSheets()];

      for(var1 = 0; var1 < this.getNumberOfSheets(); ++var1) {
         var17[var1] = this.outputFile.getPos();
         WritableSheet var13 = this.getSheet(var1);
         BoundsheetRecord var37 = new BoundsheetRecord(var13.getName());
         if (var13.getSettings().isHidden()) {
            var37.setHidden();
         }

         if (((WritableSheetImpl)this.sheets.get(var1)).isChartOnly()) {
            var37.setChartOnly();
         }

         this.outputFile.write(var37);
      }

      if (this.countryRecord == null) {
         CountryCode var15 = CountryCode.getCountryCode(this.settings.getExcelDisplayLanguage());
         CountryCode var38 = var15;
         if (var15 == CountryCode.UNKNOWN) {
            logger.warn("Unknown country code " + this.settings.getExcelDisplayLanguage() + " using " + CountryCode.USA.getCode());
            var38 = CountryCode.USA;
         }

         var15 = CountryCode.getCountryCode(this.settings.getExcelRegionalSettings());
         this.countryRecord = new CountryRecord(var38, var15);
         if (var15 == CountryCode.UNKNOWN) {
            logger.warn("Unknown country code " + this.settings.getExcelDisplayLanguage() + " using " + CountryCode.UK.getCode());
            var38 = CountryCode.UK;
         }
      }

      this.outputFile.write(this.countryRecord);
      String[] var39 = this.addInFunctionNames;
      if (var39 != null && var39.length > 0) {
         for(var1 = 0; var1 < this.addInFunctionNames.length; ++var1) {
            ExternalNameRecord var40 = new ExternalNameRecord(this.addInFunctionNames[var1]);
            this.outputFile.write(var40);
         }
      }

      if (this.xctRecords != null) {
         var1 = 0;

         while(true) {
            XCTRecord[] var41 = this.xctRecords;
            if (var1 >= var41.length) {
               break;
            }

            this.outputFile.write(var41[var1]);
            ++var1;
         }
      }

      if (this.externSheet != null) {
         for(var1 = 0; var1 < this.supbooks.size(); ++var1) {
            SupbookRecord var42 = (SupbookRecord)this.supbooks.get(var1);
            this.outputFile.write(var42);
         }

         this.outputFile.write(this.externSheet);
      }

      if (this.names != null) {
         for(var1 = 0; var1 < this.names.size(); ++var1) {
            NameRecord var43 = (NameRecord)this.names.get(var1);
            this.outputFile.write(var43);
         }
      }

      DrawingGroup var44 = this.drawingGroup;
      if (var44 != null) {
         var44.write(this.outputFile);
      }

      this.sharedStrings.write(this.outputFile);
      EOFRecord var45 = new EOFRecord();
      this.outputFile.write(var45);

      for(var1 = var4; var1 < this.getNumberOfSheets(); ++var1) {
         File var46 = this.outputFile;
         var46.setData(IntegerHelper.getFourBytes(var46.getPos()), var17[var1] + 4);
         ((WritableSheetImpl)this.getSheet(var1)).write();
      }

   }
}
