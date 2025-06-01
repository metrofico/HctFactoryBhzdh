package jxl.write.biff;

import jxl.WorkbookSettings;
import jxl.biff.EncodedURLHelper;
import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Assert;
import jxl.common.Logger;

class SupbookRecord extends WritableRecordData {
   public static final SupbookType ADDIN = new SupbookType();
   public static final SupbookType EXTERNAL = new SupbookType();
   public static final SupbookType INTERNAL = new SupbookType();
   public static final SupbookType LINK = new SupbookType();
   public static final SupbookType UNKNOWN = new SupbookType();
   private static Logger logger = Logger.getLogger(SupbookRecord.class);
   private byte[] data;
   private String fileName;
   private int numSheets;
   private String[] sheetNames;
   private SupbookType type;
   private WorkbookSettings workbookSettings;

   public SupbookRecord() {
      super(Type.SUPBOOK);
      this.type = ADDIN;
   }

   public SupbookRecord(int var1, WorkbookSettings var2) {
      super(Type.SUPBOOK);
      this.numSheets = var1;
      this.type = INTERNAL;
      this.workbookSettings = var2;
   }

   public SupbookRecord(String var1, WorkbookSettings var2) {
      super(Type.SUPBOOK);
      this.fileName = var1;
      this.numSheets = 1;
      this.sheetNames = new String[0];
      this.workbookSettings = var2;
      this.type = EXTERNAL;
   }

   public SupbookRecord(jxl.read.biff.SupbookRecord var1, WorkbookSettings var2) {
      super(Type.SUPBOOK);
      this.workbookSettings = var2;
      if (var1.getType() == jxl.read.biff.SupbookRecord.INTERNAL) {
         this.type = INTERNAL;
         this.numSheets = var1.getNumberOfSheets();
      } else if (var1.getType() == jxl.read.biff.SupbookRecord.EXTERNAL) {
         this.type = EXTERNAL;
         this.numSheets = var1.getNumberOfSheets();
         this.fileName = var1.getFileName();
         this.sheetNames = new String[this.numSheets];

         for(int var3 = 0; var3 < this.numSheets; ++var3) {
            this.sheetNames[var3] = var1.getSheetName(var3);
         }
      }

      if (var1.getType() == jxl.read.biff.SupbookRecord.ADDIN) {
         logger.warn("Supbook type is addin");
      }

   }

   private void initAddin() {
      this.data = new byte[]{1, 0, 1, 58};
   }

   private void initExternal() {
      byte var3 = 0;
      int var2 = 0;

      int var1;
      for(var1 = 0; var2 < this.numSheets; ++var2) {
         var1 += this.sheetNames[var2].length();
      }

      byte[] var5 = EncodedURLHelper.getEncodedURL(this.fileName, this.workbookSettings);
      int var4 = var5.length;
      var2 = this.numSheets;
      byte[] var6 = new byte[var4 + 6 + var2 * 3 + var1 * 2];
      this.data = var6;
      IntegerHelper.getTwoBytes(var2, var6, 0);
      IntegerHelper.getTwoBytes(var5.length + 1, this.data, 2);
      var6 = this.data;
      var6[4] = 0;
      var6[5] = 1;
      System.arraycopy(var5, 0, var6, 6, var5.length);
      var2 = var5.length + 4 + 2;
      var1 = var3;

      while(true) {
         String[] var7 = this.sheetNames;
         if (var1 >= var7.length) {
            return;
         }

         IntegerHelper.getTwoBytes(var7[var1].length(), this.data, var2);
         var5 = this.data;
         var5[var2 + 2] = 1;
         StringHelper.getUnicodeBytes(this.sheetNames[var1], var5, var2 + 3);
         var2 += this.sheetNames[var1].length() * 2 + 3;
         ++var1;
      }
   }

   private void initInternal() {
      byte[] var1 = new byte[4];
      this.data = var1;
      IntegerHelper.getTwoBytes(this.numSheets, var1, 0);
      var1 = this.data;
      var1[2] = 1;
      var1[3] = 4;
      this.type = INTERNAL;
   }

   private void initInternal(jxl.read.biff.SupbookRecord var1) {
      this.numSheets = var1.getNumberOfSheets();
      this.initInternal();
   }

   void adjustInternal(int var1) {
      boolean var2;
      if (this.type == INTERNAL) {
         var2 = true;
      } else {
         var2 = false;
      }

      Assert.verify(var2);
      this.numSheets = var1;
      this.initInternal();
   }

   public byte[] getData() {
      SupbookType var1 = this.type;
      if (var1 == INTERNAL) {
         this.initInternal();
      } else if (var1 == EXTERNAL) {
         this.initExternal();
      } else if (var1 == ADDIN) {
         this.initAddin();
      } else {
         logger.warn("unsupported supbook type - defaulting to internal");
         this.initInternal();
      }

      return this.data;
   }

   public String getFileName() {
      return this.fileName;
   }

   public int getNumberOfSheets() {
      return this.numSheets;
   }

   public int getSheetIndex(String var1) {
      int var2 = 0;
      boolean var3 = false;

      while(true) {
         String[] var5 = this.sheetNames;
         if (var2 >= var5.length || var3) {
            if (var3) {
               return 0;
            } else {
               String[] var4 = new String[var5.length + 1];
               System.arraycopy(var5, 0, var4, 0, var5.length);
               var4[this.sheetNames.length] = var1;
               this.sheetNames = var4;
               return var4.length - 1;
            }
         }

         if (var5[var2].equals(var1)) {
            var3 = true;
         }

         ++var2;
      }
   }

   public String getSheetName(int var1) {
      return this.sheetNames[var1];
   }

   public SupbookType getType() {
      return this.type;
   }

   private static class SupbookType {
      private SupbookType() {
      }

      // $FF: synthetic method
      SupbookType(Object var1) {
         this();
      }
   }
}
