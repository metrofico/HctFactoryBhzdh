package com.hct;

import java.lang.reflect.Method;

public class RkSystemProp {
   private static final String TAG = "RkSystemProp";
   private static Class mClassType = null;
   private static Method mGetIntMethod = null;
   private static Method mGetMethod = null;
   private static Method mSetMethod = null;

   public static String get(String var0, String var1) {
      init();

      try {
         var0 = (String)mGetMethod.invoke(mClassType, var0);
      } catch (Exception var2) {
         var2.printStackTrace();
         var0 = var1;
      }

      return var0;
   }

   public static int getInt(String var0, int var1) {
      init();

      try {
         var1 = (Integer)mGetIntMethod.invoke(mClassType, var0, var1);
      } catch (Exception var3) {
         var3.printStackTrace();
         var1 = var1;
      }

      return var1;
   }

   public static int getSdkVersion() {
      return getInt("ro.build.version.sdk", -1);
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

   public static void set(String var0, String var1) {
      init();

      try {
         mSetMethod.invoke(mClassType, var0);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }
}
