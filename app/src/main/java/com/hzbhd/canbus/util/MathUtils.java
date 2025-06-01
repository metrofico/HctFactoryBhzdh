package com.hzbhd.canbus.util;

public class MathUtils {
   public static int am_tens_units(String var0) {
      int var1 = (int)Float.parseFloat(var0);
      return tens(var1) * 10 + units(var1);
   }

   public static int am_thousands_hundreads(String var0) {
      int var1 = Integer.parseInt(var0);
      return thousands(var1) * 10 + hundreads(var1);
   }

   public static int fm_thousands_hundreads(String var0) {
      int var1 = (int)(Float.parseFloat(var0) * 100.0F);
      return thousands(var1) * 10 + hundreads(var1);
   }

   public static int hundreads(int var0) {
      return var0 / 100 % 10;
   }

   public static int tens(int var0) {
      return var0 / 10 % 10;
   }

   public static int tens_units(String var0) {
      int var1 = (int)(Float.parseFloat(var0) * 100.0F);
      return tens(var1) * 10 + units(var1);
   }

   public static int thousands(int var0) {
      return var0 / 1000 % 10;
   }

   public static int units(int var0) {
      return var0 % 10;
   }
}
