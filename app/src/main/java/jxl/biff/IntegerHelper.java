package jxl.biff;

public final class IntegerHelper {
   private IntegerHelper() {
   }

   public static void getFourBytes(int var0, byte[] var1, int var2) {
      byte[] var3 = getFourBytes(var0);
      var1[var2] = var3[0];
      var1[var2 + 1] = var3[1];
      var1[var2 + 2] = var3[2];
      var1[var2 + 3] = var3[3];
   }

   public static byte[] getFourBytes(int var0) {
      byte[] var1 = new byte[4];
      getTwoBytes('\uffff' & var0, var1, 0);
      getTwoBytes((var0 & -65536) >> 16, var1, 2);
      return var1;
   }

   public static int getInt(byte var0, byte var1) {
      return var0 & 255 | (var1 & 255) << 8;
   }

   public static int getInt(byte var0, byte var1, byte var2, byte var3) {
      return getInt(var0, var1) | getInt(var2, var3) << 16;
   }

   public static short getShort(byte var0, byte var1) {
      return (short)((short)(var0 & 255) | (short)(var1 & 255) << 8);
   }

   public static void getTwoBytes(int var0, byte[] var1, int var2) {
      var1[var2] = (byte)(var0 & 255);
      var1[var2 + 1] = (byte)((var0 & '\uff00') >> 8);
   }

   public static byte[] getTwoBytes(int var0) {
      return new byte[]{(byte)(var0 & 255), (byte)((var0 & '\uff00') >> 8)};
   }
}
