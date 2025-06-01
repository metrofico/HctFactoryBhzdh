package jxl.read.biff;

import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.StringHelper;
import jxl.common.Logger;

public class SupbookRecord extends RecordData {
   public static final Type ADDIN = new Type();
   public static final Type EXTERNAL = new Type();
   public static final Type INTERNAL = new Type();
   public static final Type LINK = new Type();
   public static final Type UNKNOWN = new Type();
   private static Logger logger = Logger.getLogger(SupbookRecord.class);
   private String fileName;
   private int numSheets;
   private String[] sheetNames;
   private Type type;

   SupbookRecord(Record var1, WorkbookSettings var2) {
      super(var1);
      byte[] var4 = this.getRecord().getData();
      if (var4.length == 4) {
         byte var3 = var4[2];
         if (var3 == 1 && var4[3] == 4) {
            this.type = INTERNAL;
         } else if (var3 == 1 && var4[3] == 58) {
            this.type = ADDIN;
         } else {
            this.type = UNKNOWN;
         }
      } else if (var4[0] == 0 && var4[1] == 0) {
         this.type = LINK;
      } else {
         this.type = EXTERNAL;
      }

      if (this.type == INTERNAL) {
         this.numSheets = IntegerHelper.getInt(var4[0], var4[1]);
      }

      if (this.type == EXTERNAL) {
         this.readExternal(var4, var2);
      }

   }

   private String getEncodedFilename(byte[] var1, int var2, int var3) {
      StringBuffer var6 = new StringBuffer();

      for(int var5 = var3; var5 < var2 + var3; ++var5) {
         char var4 = (char)var1[var5];
         if (var4 == 1) {
            ++var5;
            var6.append((char)var1[var5]);
            var6.append(":\\\\");
         } else if (var4 == 2) {
            var6.append('\\');
         } else if (var4 == 3) {
            var6.append('\\');
         } else if (var4 == 4) {
            var6.append("..\\");
         } else {
            var6.append(var4);
         }
      }

      return var6.toString();
   }

   private String getUnicodeEncodedFilename(byte[] var1, int var2, int var3) {
      StringBuffer var6 = new StringBuffer();

      for(int var5 = var3; var5 < var2 * 2 + var3; var5 += 2) {
         char var4 = (char)IntegerHelper.getInt(var1[var5], var1[var5 + 1]);
         if (var4 == 1) {
            var5 += 2;
            var6.append((char)IntegerHelper.getInt(var1[var5], var1[var5 + 1]));
            var6.append(":\\\\");
         } else if (var4 == 2) {
            var6.append('\\');
         } else if (var4 == 3) {
            var6.append('\\');
         } else if (var4 == 4) {
            var6.append("..\\");
         } else {
            var6.append(var4);
         }
      }

      return var6.toString();
   }

   private void readExternal(byte[] var1, WorkbookSettings var2) {
      int var5 = 0;
      this.numSheets = IntegerHelper.getInt(var1[0], var1[1]);
      int var4 = IntegerHelper.getInt(var1[2], var1[3]) - 1;
      int var6 = var1[4];
      int var3 = 6;
      if (var6 == 0) {
         if (var1[5] == 0) {
            this.fileName = StringHelper.getString(var1, var4, 6, var2);
         } else {
            this.fileName = this.getEncodedFilename(var1, var4, 6);
         }
      } else {
         var6 = IntegerHelper.getInt(var1[5], var1[6]);
         var3 = 7;
         if (var6 == 0) {
            this.fileName = StringHelper.getUnicodeString(var1, var4, 7);
         } else {
            this.fileName = this.getUnicodeEncodedFilename(var1, var4, 7);
         }

         var4 *= 2;
      }

      var4 += var3;
      this.sheetNames = new String[this.numSheets];

      for(var3 = var5; var3 < this.sheetNames.length; var4 = var5) {
         label31: {
            int var7 = IntegerHelper.getInt(var1[var4], var1[var4 + 1]);
            byte var8 = var1[var4 + 2];
            if (var8 == 0) {
               this.sheetNames[var3] = StringHelper.getString(var1, var7, var4 + 3, var2);
               var5 = var7 + 3;
            } else {
               var5 = var4;
               if (var8 != 1) {
                  break label31;
               }

               this.sheetNames[var3] = StringHelper.getUnicodeString(var1, var7, var4 + 3);
               var5 = var7 * 2 + 3;
            }

            var5 += var4;
         }

         ++var3;
      }

   }

   public byte[] getData() {
      return this.getRecord().getData();
   }

   public String getFileName() {
      return this.fileName;
   }

   public int getNumberOfSheets() {
      return this.numSheets;
   }

   public String getSheetName(int var1) {
      return this.sheetNames[var1];
   }

   public Type getType() {
      return this.type;
   }

   private static class Type {
      private Type() {
      }

      // $FF: synthetic method
      Type(Object var1) {
         this();
      }
   }
}
