package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;

public final class n {
   private static final byte[] a;
   private static final byte[] b;

   static {
      byte[] var1 = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
      byte[] var3 = new byte[256];
      byte[] var2 = new byte[256];

      for(int var0 = 0; var0 < 256; ++var0) {
         var3[var0] = var1[var0 >>> 4];
         var2[var0] = var1[var0 & 15];
      }

      a = var3;
      b = var2;
   }

   public static boolean a(int var0, int var1) {
      return var0 == var1;
   }

   public static boolean a(long var0, long var2) {
      return var0 == var2;
   }

   public static boolean a(Object var0, Object var1) {
      return var0.equals(var1);
   }

   public static boolean a(boolean var0, boolean var1) {
      return var0 == var1;
   }

   public static byte[] a(ByteBuffer var0) {
      int var1 = var0.position();
      byte[] var2 = new byte[var1];
      System.arraycopy(var0.array(), 0, var2, 0, var1);
      return var2;
   }
}
