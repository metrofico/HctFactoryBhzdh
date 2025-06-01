package jxl.biff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import jxl.WorkbookSettings;
import jxl.biff.formula.ExternalSheet;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.write.biff.File;

public class DataValidation {
   public static final int DEFAULT_OBJECT_ID = -1;
   private static final int MAX_NO_OF_VALIDITY_SETTINGS = 65533;
   private static Logger logger = Logger.getLogger(DataValidation.class);
   private int comboBoxObjectId;
   private boolean copied;
   private ExternalSheet externalSheet;
   private DataValidityListRecord validityList;
   private ArrayList validitySettings;
   private WorkbookMethods workbook;
   private WorkbookSettings workbookSettings;

   public DataValidation(int var1, ExternalSheet var2, WorkbookMethods var3, WorkbookSettings var4) {
      this.workbook = var3;
      this.externalSheet = var2;
      this.workbookSettings = var4;
      this.validitySettings = new ArrayList();
      this.comboBoxObjectId = var1;
      this.copied = false;
   }

   public DataValidation(DataValidation var1, ExternalSheet var2, WorkbookMethods var3, WorkbookSettings var4) {
      this.workbook = var3;
      this.externalSheet = var2;
      this.workbookSettings = var4;
      this.copied = true;
      this.validityList = new DataValidityListRecord(var1.getDataValidityList());
      this.validitySettings = new ArrayList();
      DataValiditySettingsRecord[] var6 = var1.getDataValiditySettings();

      for(int var5 = 0; var5 < var6.length; ++var5) {
         this.validitySettings.add(new DataValiditySettingsRecord(var6[var5], this.externalSheet, this.workbook, this.workbookSettings));
      }

   }

   public DataValidation(DataValidityListRecord var1) {
      this.validityList = var1;
      this.validitySettings = new ArrayList(this.validityList.getNumberOfSettings());
      this.copied = false;
   }

   public void add(DataValiditySettingsRecord var1) {
      this.validitySettings.add(var1);
      var1.setDataValidation(this);
      if (this.copied) {
         boolean var2;
         if (this.validityList != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Assert.verify(var2);
         this.validityList.dvAdded();
      }

   }

   public int getComboBoxObjectId() {
      return this.comboBoxObjectId;
   }

   public DataValidityListRecord getDataValidityList() {
      return this.validityList;
   }

   public DataValiditySettingsRecord getDataValiditySettings(int var1, int var2) {
      Iterator var6 = this.validitySettings.iterator();
      boolean var3 = false;
      DataValiditySettingsRecord var4 = null;

      while(var6.hasNext() && !var3) {
         DataValiditySettingsRecord var5 = (DataValiditySettingsRecord)var6.next();
         if (var5.getFirstColumn() == var1 && var5.getFirstRow() == var2) {
            var3 = true;
            var4 = var5;
         }
      }

      return var4;
   }

   public DataValiditySettingsRecord[] getDataValiditySettings() {
      return (DataValiditySettingsRecord[])this.validitySettings.toArray(new DataValiditySettingsRecord[0]);
   }

   public void insertColumn(int var1) {
      Iterator var2 = this.validitySettings.iterator();

      while(var2.hasNext()) {
         ((DataValiditySettingsRecord)var2.next()).insertColumn(var1);
      }

   }

   public void insertRow(int var1) {
      Iterator var2 = this.validitySettings.iterator();

      while(var2.hasNext()) {
         ((DataValiditySettingsRecord)var2.next()).insertRow(var1);
      }

   }

   public void removeColumn(int var1) {
      Iterator var2 = this.validitySettings.iterator();

      while(true) {
         while(var2.hasNext()) {
            DataValiditySettingsRecord var3 = (DataValiditySettingsRecord)var2.next();
            if (var3.getFirstColumn() == var1 && var3.getLastColumn() == var1) {
               var2.remove();
               this.validityList.dvRemoved();
            } else {
               var3.removeColumn(var1);
            }
         }

         return;
      }
   }

   public void removeDataValidation(int var1, int var2) {
      Iterator var4 = this.validitySettings.iterator();

      while(var4.hasNext()) {
         DataValiditySettingsRecord var3 = (DataValiditySettingsRecord)var4.next();
         if (var3.getFirstColumn() == var1 && var3.getLastColumn() == var1 && var3.getFirstRow() == var2 && var3.getLastRow() == var2) {
            var4.remove();
            this.validityList.dvRemoved();
            break;
         }
      }

   }

   public void removeRow(int var1) {
      Iterator var2 = this.validitySettings.iterator();

      while(true) {
         while(var2.hasNext()) {
            DataValiditySettingsRecord var3 = (DataValiditySettingsRecord)var2.next();
            if (var3.getFirstRow() == var1 && var3.getLastRow() == var1) {
               var2.remove();
               this.validityList.dvRemoved();
            } else {
               var3.removeRow(var1);
            }
         }

         return;
      }
   }

   public void removeSharedDataValidation(int var1, int var2, int var3, int var4) {
      Iterator var6 = this.validitySettings.iterator();

      while(var6.hasNext()) {
         DataValiditySettingsRecord var5 = (DataValiditySettingsRecord)var6.next();
         if (var5.getFirstColumn() == var1 && var5.getLastColumn() == var3 && var5.getFirstRow() == var2 && var5.getLastRow() == var4) {
            var6.remove();
            this.validityList.dvRemoved();
            break;
         }
      }

   }

   public void write(File var1) throws IOException {
      if (this.validitySettings.size() > 65533) {
         logger.warn("Maximum number of data validations exceeded - truncating...");
         ArrayList var3 = this.validitySettings;
         boolean var2 = false;
         var3 = new ArrayList(var3.subList(0, 65532));
         this.validitySettings = var3;
         if (var3.size() <= 65533) {
            var2 = true;
         }

         Assert.verify(var2);
      }

      if (this.validityList == null) {
         this.validityList = new DataValidityListRecord(new DValParser(this.comboBoxObjectId, this.validitySettings.size()));
      }

      if (this.validityList.hasDVRecords()) {
         var1.write(this.validityList);
         Iterator var4 = this.validitySettings.iterator();

         while(var4.hasNext()) {
            var1.write((DataValiditySettingsRecord)var4.next());
         }

      }
   }
}
