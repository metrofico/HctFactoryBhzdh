package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class PasswordRecord extends WritableRecordData {
   private byte[] data;
   private String password;

   public PasswordRecord(int var1) {
      super(Type.PASSWORD);
      byte[] var2 = new byte[2];
      this.data = var2;
      IntegerHelper.getTwoBytes(var1, var2, 0);
   }

   public PasswordRecord(String var1) {
      super(Type.PASSWORD);
      this.password = var1;
      byte[] var5;
      if (var1 == null) {
         var5 = new byte[2];
         this.data = var5;
         IntegerHelper.getTwoBytes(0, var5, 0);
      } else {
         var5 = var1.getBytes();
         int var3 = 0;

         int var2;
         byte var4;
         for(var2 = 0; var3 < var5.length; var2 ^= this.rotLeft15Bit(var4, var3)) {
            var4 = var5[var3];
            ++var3;
         }

         var3 = var5.length;
         var5 = new byte[2];
         this.data = var5;
         IntegerHelper.getTwoBytes(var3 ^ var2 ^ '칋', var5, 0);
      }

   }

   private int rotLeft15Bit(int var1, int var2) {
      for(var1 &= 32767; var2 > 0; --var2) {
         if ((var1 & 16384) != 0) {
            var1 = (var1 << 1 & 32767) + 1;
         } else {
            var1 = var1 << 1 & 32767;
         }
      }

      return var1;
   }

   public byte[] getData() {
      return this.data;
   }
}
