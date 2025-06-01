package com.hzbhd.canbus.adapter.util;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import java.lang.reflect.Method;

public final class DispUtility {
   public static Resources disabledDisplayDpiChange(Resources var0) {
      Configuration var1 = var0.getConfiguration();
      if (VERSION.SDK_INT >= 23) {
         if (var0.getConfiguration().fontScale != 1.0F) {
            var1.fontScale = 1.0F;
         }

         var1.densityDpi = getDefaultDisplayDensity();
         var0.updateConfiguration(var1, var0.getDisplayMetrics());
      } else if (var0.getConfiguration().fontScale != 1.0F) {
         var1.fontScale = 1.0F;
         var0.updateConfiguration(var1, var0.getDisplayMetrics());
      }

      return var0;
   }

   private static int getDefaultDisplayDensity() {
      try {
         Class var1 = Class.forName("android.view.WindowManagerGlobal");
         Method var2 = var1.getMethod("getWindowManagerService");
         var2.setAccessible(true);
         Object var5 = var2.invoke(var1);
         Method var4 = var5.getClass().getMethod("getInitialDisplayDensity", Integer.TYPE);
         var4.setAccessible(true);
         int var0 = (Integer)var4.invoke(var5, 0);
         return var0;
      } catch (Exception var3) {
         var3.printStackTrace();
         return -1;
      }
   }
}
