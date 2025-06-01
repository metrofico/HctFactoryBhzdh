package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreUtil {
   public static String sharePreference;

   public static boolean getBoolValue(Context var0, String var1, boolean var2) {
      return var0.getSharedPreferences(sharePreference, 0).getBoolean(var1, var2);
   }

   public static float getFloatValue(Context var0, String var1, float var2) {
      return var0.getSharedPreferences(sharePreference, 0).getFloat(var1, var2);
   }

   public static int getIntValue(Context var0, String var1, int var2) {
      return var0.getSharedPreferences(sharePreference, 0).getInt(var1, var2);
   }

   public static String getStringValue(Context var0, String var1, String var2) {
      String var3 = var0.getSharedPreferences(sharePreference, 0).getString(var1, (String)null);
      return var3 == null ? var2 : var3;
   }

   public static boolean setBoolValue(Context var0, String var1, boolean var2) {
      SharedPreferences.Editor var3 = var0.getSharedPreferences(sharePreference, 0).edit();
      var3.putBoolean(var1, var2);
      return var3.commit();
   }

   public static boolean setFloatValue(Context var0, String var1, float var2) {
      SharedPreferences.Editor var3 = var0.getSharedPreferences(sharePreference, 0).edit();
      var3.putFloat(var1, var2);
      return var3.commit();
   }

   public static boolean setIntValue(Context var0, String var1, int var2) {
      SharedPreferences.Editor var3 = var0.getSharedPreferences(sharePreference, 0).edit();
      var3.putInt(var1, var2);
      return var3.commit();
   }

   public static boolean setStringValue(Context var0, String var1, String var2) {
      SharedPreferences.Editor var3 = var0.getSharedPreferences(sharePreference, 0).edit();
      var3.putString(var1, var2);
      return var3.commit();
   }
}
