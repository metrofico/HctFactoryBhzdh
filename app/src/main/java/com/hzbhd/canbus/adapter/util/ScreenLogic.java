package com.hzbhd.canbus.adapter.util;

import android.content.Context;
import android.content.res.Configuration;
import android.provider.Settings.System;
import android.util.Log;

public class ScreenLogic {
   static String TAG;
   static int orientation;

   public static int getScreenOrientation(Context var0) {
      return System.getInt(var0.getContentResolver(), "user_rotation", -1);
   }

   public static boolean isLandScreen(Context var0) {
      int var1 = getScreenOrientation(var0);
      return var1 != 1 && var1 != 3;
   }

   public static boolean isLandUIAndPortUI(String var0) {
      return var0.contains("IKWE0101-PE5");
   }

   public static boolean isPortScreen(Context var0) {
      int var1 = getScreenOrientation(var0);
      return var1 == 1 || var1 == 3;
   }

   public static boolean isRotateScreen() {
      return false;
   }

   public static boolean isRotateScreenByHand() {
      return false;
   }

   public static boolean isRotateScreenOrRotateScreenByHand() {
      boolean var0;
      if (!isRotateScreen() && !isRotateScreenByHand()) {
         var0 = false;
      } else {
         var0 = true;
      }

      return var0;
   }

   public static boolean isScreenOreitaionChanged(Configuration var0) {
      if (orientation != var0.orientation) {
         orientation = var0.orientation;
         return true;
      } else {
         Log.d(TAG, "isScreenOreitaionChanged  " + var0.orientation);
         return false;
      }
   }

   public static void setOrientation(int var0) {
      orientation = var0;
   }
}
