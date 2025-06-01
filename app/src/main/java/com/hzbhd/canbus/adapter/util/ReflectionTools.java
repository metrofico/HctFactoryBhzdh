package com.hzbhd.canbus.adapter.util;

public class ReflectionTools {
   public static Boolean getFieldBoolean(Object var0, Class var1, String var2) {
      boolean var3;
      try {
         var3 = var1.getField(var2).getBoolean(var0);
      } catch (Exception var4) {
         var4.printStackTrace();
         return false;
      }

      return var3;
   }

   public static Integer getFieldInteger(Object var0, Class var1, String var2) {
      int var3;
      try {
         var3 = var1.getField(var2).getInt(var0);
      } catch (Exception var4) {
         var4.printStackTrace();
         return null;
      }

      return var3;
   }
}
