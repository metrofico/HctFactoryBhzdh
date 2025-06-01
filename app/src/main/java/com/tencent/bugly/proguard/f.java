package com.tencent.bugly.proguard;

public final class f {
   public static final byte[] a = new byte[0];
   private static final char[] b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

   public static String a(byte[] var0) {
      if (var0 != null && var0.length != 0) {
         char[] var4 = new char[var0.length * 2];

         for(int var1 = 0; var1 < var0.length; ++var1) {
            byte var3 = var0[var1];
            int var2 = var1 * 2;
            char[] var5 = b;
            var4[var2 + 1] = var5[var3 & 15];
            var4[var2 + 0] = var5[(byte)(var3 >>> 4) & 15];
         }

         return new String(var4);
      } else {
         return null;
      }
   }
}
