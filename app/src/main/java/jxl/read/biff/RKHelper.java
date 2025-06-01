package jxl.read.biff;

final class RKHelper {
   private RKHelper() {
   }

   public static double getDouble(int var0) {
      double var1;
      double var3;
      if ((var0 & 2) != 0) {
         var3 = (double)(var0 >> 2);
         var1 = var3;
         if ((var0 & 1) != 0) {
            var1 = var3 / 100.0;
         }

         return var1;
      } else {
         var3 = Double.longBitsToDouble((long)(var0 & -4) << 32);
         var1 = var3;
         if ((var0 & 1) != 0) {
            var1 = var3 / 100.0;
         }

         return var1;
      }
   }
}
