package com.hzbhd.commontools.utils;

public class FgeString {
   public static String Int2HexString(int[] var0, int var1) {
      String var3 = "";

      for(int var2 = 0; var2 < var1; ++var2) {
         String var5 = Integer.toHexString(var0[var2]);
         String var4 = var5;
         if (var5.length() == 1) {
            var4 = '0' + var5;
         }

         var3 = var3 + var4.toUpperCase() + " ";
      }

      return var3;
   }

   public static String bytes2BcdString(byte[] var0) {
      StringBuffer var2 = new StringBuffer(var0.length * 2);

      for(int var1 = 0; var1 < var0.length; ++var1) {
         var2.append((byte)((var0[var1] & 240) >>> 4));
         var2.append((byte)(var0[var1] & 15));
      }

      return var2.toString();
   }

   public static String bytes2HexString(byte[] var0, int var1) {
      String var3 = "";

      for(int var2 = 0; var2 < var1; ++var2) {
         String var5 = Integer.toHexString(var0[var2] & 255);
         String var4 = var5;
         if (var5.length() == 1) {
            var4 = '0' + var5;
         }

         var3 = var3 + var4.toUpperCase() + " ";
      }

      return var3;
   }

   public static String bytes2HexString(int[] var0, int var1) {
      String var3 = "";

      for(int var2 = 0; var2 < var1; ++var2) {
         String var5 = Integer.toHexString(var0[var2] & 255);
         String var4 = var5;
         if (var5.length() == 1) {
            var4 = '0' + var5;
         }

         var3 = var3 + var4.toUpperCase() + " ";
      }

      return var3;
   }

   public static String bytetoHexString(byte var0) {
      String var2 = Integer.toHexString(var0 & 255);
      String var1 = var2;
      if (var2.length() == 1) {
         var1 = '0' + var2;
      }

      return var1.toUpperCase() + " ";
   }
}
