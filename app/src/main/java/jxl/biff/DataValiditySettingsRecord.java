package jxl.biff;

import jxl.WorkbookSettings;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.read.biff.Record;

public class DataValiditySettingsRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(DataValiditySettingsRecord.class);
   private byte[] data;
   private DataValidation dataValidation;
   private DVParser dvParser;
   private ExternalSheet externalSheet;
   private WorkbookMethods workbook;
   private WorkbookSettings workbookSettings;

   public DataValiditySettingsRecord(DVParser var1) {
      super(Type.DV);
      this.dvParser = var1;
   }

   DataValiditySettingsRecord(DataValiditySettingsRecord var1) {
      super(Type.DV);
      this.data = var1.getData();
   }

   DataValiditySettingsRecord(DataValiditySettingsRecord var1, ExternalSheet var2, WorkbookMethods var3, WorkbookSettings var4) {
      super(Type.DV);
      this.workbook = var3;
      this.externalSheet = var2;
      this.workbookSettings = var4;
      boolean var6 = true;
      boolean var5;
      if (var3 != null) {
         var5 = true;
      } else {
         var5 = false;
      }

      Assert.verify(var5);
      if (var2 != null) {
         var5 = var6;
      } else {
         var5 = false;
      }

      Assert.verify(var5);
      byte[] var7 = new byte[var1.data.length];
      this.data = var7;
      System.arraycopy(var1.data, 0, var7, 0, var7.length);
   }

   public DataValiditySettingsRecord(Record var1, ExternalSheet var2, WorkbookMethods var3, WorkbookSettings var4) {
      super(var1);
      this.data = var1.getData();
      this.externalSheet = var2;
      this.workbook = var3;
      this.workbookSettings = var4;
   }

   private void initialize() {
      if (this.dvParser == null) {
         this.dvParser = new DVParser(this.data, this.externalSheet, this.workbook, this.workbookSettings);
      }

   }

   DVParser getDVParser() {
      return this.dvParser;
   }

   public byte[] getData() {
      DVParser var1 = this.dvParser;
      return var1 == null ? this.data : var1.getData();
   }

   public int getFirstColumn() {
      if (this.dvParser == null) {
         this.initialize();
      }

      return this.dvParser.getFirstColumn();
   }

   public int getFirstRow() {
      if (this.dvParser == null) {
         this.initialize();
      }

      return this.dvParser.getFirstRow();
   }

   public int getLastColumn() {
      if (this.dvParser == null) {
         this.initialize();
      }

      return this.dvParser.getLastColumn();
   }

   public int getLastRow() {
      if (this.dvParser == null) {
         this.initialize();
      }

      return this.dvParser.getLastRow();
   }

   public String getValidationFormula() {
      try {
         if (this.dvParser == null) {
            this.initialize();
         }

         String var1 = this.dvParser.getValidationFormula();
         return var1;
      } catch (FormulaException var2) {
         logger.warn("Cannot read drop down range " + var2.getMessage());
         return "";
      }
   }

   public void insertColumn(int var1) {
      if (this.dvParser == null) {
         this.initialize();
      }

      this.dvParser.insertColumn(var1);
   }

   public void insertRow(int var1) {
      if (this.dvParser == null) {
         this.initialize();
      }

      this.dvParser.insertRow(var1);
   }

   public void removeColumn(int var1) {
      if (this.dvParser == null) {
         this.initialize();
      }

      this.dvParser.removeColumn(var1);
   }

   public void removeRow(int var1) {
      if (this.dvParser == null) {
         this.initialize();
      }

      this.dvParser.removeRow(var1);
   }

   void setDataValidation(DataValidation var1) {
      this.dataValidation = var1;
   }
}
