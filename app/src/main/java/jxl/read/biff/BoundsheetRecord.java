package jxl.read.biff;

import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.StringHelper;

class BoundsheetRecord extends RecordData {
   public static Biff7 biff7 = new Biff7();
   private int length;
   private String name;
   private int offset;
   private byte typeFlag;
   private byte visibilityFlag;

   public BoundsheetRecord(Record var1, WorkbookSettings var2) {
      super(var1);
      byte[] var5 = this.getRecord().getData();
      this.offset = IntegerHelper.getInt(var5[0], var5[1], var5[2], var5[3]);
      this.typeFlag = var5[5];
      this.visibilityFlag = var5[4];
      byte var3 = var5[6];
      this.length = var3;
      if (var5[7] == 0) {
         byte[] var4 = new byte[var3];
         System.arraycopy(var5, 8, var4, 0, var3);
         this.name = StringHelper.getString(var4, this.length, 0, var2);
      } else {
         byte[] var6 = new byte[var3 * 2];
         System.arraycopy(var5, 8, var6, 0, var3 * 2);
         this.name = StringHelper.getUnicodeString(var6, this.length, 0);
      }

   }

   public BoundsheetRecord(Record var1, Biff7 var2) {
      super(var1);
      byte[] var4 = this.getRecord().getData();
      this.offset = IntegerHelper.getInt(var4[0], var4[1], var4[2], var4[3]);
      this.typeFlag = var4[5];
      this.visibilityFlag = var4[4];
      byte var3 = var4[6];
      this.length = var3;
      byte[] var5 = new byte[var3];
      System.arraycopy(var4, 7, var5, 0, var3);
      this.name = new String(var5);
   }

   public String getName() {
      return this.name;
   }

   public boolean isChart() {
      boolean var1;
      if (this.typeFlag == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isHidden() {
      boolean var1;
      if (this.visibilityFlag != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isSheet() {
      boolean var1;
      if (this.typeFlag == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private static class Biff7 {
      private Biff7() {
      }

      // $FF: synthetic method
      Biff7(Object var1) {
         this();
      }
   }
}
