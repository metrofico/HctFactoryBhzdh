package com.hzbhd.canbus.canCustom.canBaseUtil;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class StatusUtil {
   public static void fullScreen(AppCompatActivity var0, boolean var1) {
      WindowManager.LayoutParams var2;
      if (var1) {
         var2 = var0.getWindow().getAttributes();
         var2.flags |= 1024;
         var0.getWindow().setAttributes(var2);
         var0.getWindow().addFlags(512);
      } else {
         var2 = var0.getWindow().getAttributes();
         var2.flags &= -1025;
         var0.getWindow().setAttributes(var2);
         var0.getWindow().clearFlags(512);
      }

   }

   public static int getNavigationBarHeight(AppCompatActivity var0) {
      Resources var1 = var0.getResources();
      return var1.getDimensionPixelSize(var1.getIdentifier("navigation_bar_height", "dimen", "android"));
   }

   public static int getStatusBarHeight(AppCompatActivity var0) {
      Resources var1 = var0.getResources();
      return var1.getDimensionPixelSize(var1.getIdentifier("status_bar_height", "dimen", "android"));
   }

   public static void hideNavigationBar(AppCompatActivity var0) {
      if (VERSION.SDK_INT >= 21) {
         Window var1 = var0.getWindow();
         var1.addFlags(Integer.MIN_VALUE);
         var1.getDecorView().setSystemUiVisibility(1282);
      }

   }

   public static void hideStatusBar(AppCompatActivity var0) {
      if (VERSION.SDK_INT >= 23) {
         var0.getWindow().setFlags(1024, 1024);
         var0.getWindow().getDecorView().setSystemUiVisibility(8192);
      }

   }

   public static void immersiveNavigationBar(AppCompatActivity var0, boolean var1, int var2) {
      if (VERSION.SDK_INT >= 21) {
         Window var3 = var0.getWindow();
         if (var1) {
            var3.getDecorView().setSystemUiVisibility(512);
            var3.addFlags(Integer.MIN_VALUE);
         }

         var3.setNavigationBarColor(var2);
      }

   }

   public static void immersiveStatusView(AppCompatActivity var0, boolean var1, boolean var2, int var3) {
      if (VERSION.SDK_INT >= 21) {
         Window var4 = var0.getWindow();
         if (var1) {
            var4.addFlags(Integer.MIN_VALUE);
            if (var2) {
               var4.getDecorView().setSystemUiVisibility(9472);
            } else {
               var4.getDecorView().setSystemUiVisibility(1280);
            }
         } else if (var2) {
            var0.getWindow().getDecorView().setSystemUiVisibility(8192);
         }

         var4.setStatusBarColor(var3);
      }

   }

   public static void setStatusBar(AppCompatActivity var0) {
      if (VERSION.SDK_INT >= 23) {
         var0.getWindow().addFlags(67108864);
         var0.getWindow().getDecorView().setSystemUiVisibility(8192);
      }

   }
}
