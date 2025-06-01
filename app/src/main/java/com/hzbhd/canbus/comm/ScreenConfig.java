package com.hzbhd.canbus.comm;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class ScreenConfig {
   public static int screenHeight;
   public static int screenWidth;

   public static void initScreenConfig(Application var0) {
      try {
         WindowManager var3 = (WindowManager)var0.getSystemService("window");
         DisplayMetrics var1 = new DisplayMetrics();
         var3.getDefaultDisplay().getMetrics(var1);
         screenWidth = var1.widthPixels;
         screenHeight = var1.heightPixels;
      } catch (Exception var2) {
         Log.e("ScreenConfig", var2.toString());
      }

   }
}
