package jxl.biff;

public class DoubleHelper {
   private DoubleHelper() {
   }

   public static void getIEEEBytes(double var0, byte[] var2, int var3) {
      long var4 = Double.doubleToLongBits(var0);
      var2[var3] = (byte)((int)(255L & var4));
      var2[var3 + 1] = (byte)((int)((65280L & var4) >> 8));
      var2[var3 + 2] = (byte)((int)((16711680L & var4) >> 16));
      var2[var3 + 3] = (byte)((int)((-16777216L & var4) >> 24));
      var2[var3 + 4] = (byte)((int)((1095216660480L & var4) >> 32));
      var2[var3 + 5] = (byte)((int)((280375465082880L & var4) >> 40));
      var2[var3 + 6] = (byte)((int)((71776119061217280L & var4) >> 48));
      var2[var3 + 7] = (byte)((int)((var4 & -72057594037927936L) >> 56));
   }

   public static double getIEEEDouble(byte[] var0, int var1) {
      int var6 = IntegerHelper.getInt(var0[var1], var0[var1 + 1], var0[var1 + 2], var0[var1 + 3]);
      int var7 = IntegerHelper.getInt(var0[var1 + 4], var0[var1 + 5], var0[var1 + 6], var0[var1 + 7]);
      boolean var14;
      if ((Integer.MIN_VALUE & var7) != 0) {
         var14 = true;
      } else {
         var14 = false;
      }

      long var12 = (long)(var7 & Integer.MAX_VALUE);
      long var10 = (long)var6;
      long var8 = var10;
      if (var6 < 0) {
         var8 = var10 + 4294967296L;
      }

      double var4 = Double.longBitsToDouble(var12 * 4294967296L + var8);
      double var2 = var4;
      if (var14) {
         var2 = -var4;
      }

      return var2;
   }
}
