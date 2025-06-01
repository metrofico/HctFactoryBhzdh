package com.microntek.controlsettings;

import java.lang.reflect.Method;

public class RkSystemProp {
   private static Class mClassType;
   private static Method mGetIntMethod;
   private static Method mGetMethod;
   private static Method mSetMethod;

   public static String get(String var0, String var1) {
      init();

      try {
         var0 = (String)mGetMethod.invoke(mClassType, var0);
      } catch (Exception var2) {
         var2.printStackTrace();
         return var1;
      }

      var1 = var0;
      return var1;
   }

   private static void init() {
      try {
         if (mClassType == null) {
            mClassType = Class.forName("android.os.SystemProperties");
            mGetMethod = mClassType.getDeclaredMethod("get", String.class);
            mGetIntMethod = mClassType.getDeclaredMethod("getInt", String.class, Integer.TYPE);
            mSetMethod = mClassType.getDeclaredMethod("set", String.class, String.class);
         }
      } catch (Exception var1) {
         var1.printStackTrace();
      }

   }
}
