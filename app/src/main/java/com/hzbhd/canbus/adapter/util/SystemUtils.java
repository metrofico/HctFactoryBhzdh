package com.hzbhd.canbus.adapter.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import android.text.TextUtils;
import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class SystemUtils {
   private static final FileFilter CPU_FILTER = new FileFilter() {
      public boolean accept(File var1) {
         String var3 = var1.getName();
         if (!var3.startsWith("cpu")) {
            return false;
         } else {
            for(int var2 = 3; var2 < var3.length(); ++var2) {
               if (var3.charAt(var2) < '0' || var3.charAt(var2) > '9') {
                  return false;
               }
            }

            return true;
         }
      }
   };
   private static final String HOURS_12 = "12";
   private static final String HOURS_24 = "24";

   public static boolean getAirPlaneMode(Context var0) {
      int var1 = VERSION.SDK_INT;
      boolean var2 = true;
      boolean var3 = true;
      if (var1 <= 16) {
         if (System.getInt(var0.getContentResolver(), "airplane_mode_on", 0) == 1) {
            var2 = var3;
         } else {
            var2 = false;
         }

         return var2;
      } else {
         if (Global.getInt(var0.getContentResolver(), "airplane_mode_on", 0) != 1) {
            var2 = false;
         }

         return var2;
      }
   }

   public static int getCPUCoreNumber(int var0) {
      int var1;
      try {
         File var2 = new File("/sys/devices/system/cpu/");
         var1 = var2.listFiles(CPU_FILTER).length;
      } catch (NullPointerException | SecurityException var3) {
         return var0;
      }

      var0 = var1;
      return var0;
   }

   public static boolean isForeground(Context var0, String var1) {
      if (var0 != null && !TextUtils.isEmpty(var1)) {
         List var2 = ((ActivityManager)var0.getSystemService("activity")).getRunningTasks(1);
         if (var2 != null && var2.size() > 0 && var1.equals(((ActivityManager.RunningTaskInfo)var2.get(0)).topActivity.getClassName())) {
            return true;
         }
      }

      return false;
   }

   public static boolean isNaviForeground(Context var0, String var1) {
      HzbhdLog.d("isNaviForeground", var0 + " , isNaviForeground , " + var1);
      if (var0 != null && !TextUtils.isEmpty(var1)) {
         List var2 = ((ActivityManager)var0.getSystemService("activity")).getRunningTasks(1);
         if (var2 != null && var2.size() > 0) {
            ComponentName var3 = ((ActivityManager.RunningTaskInfo)var2.get(0)).topActivity;
            HzbhdLog.d("isNaviForeground", var3.getPackageName() + " , isNaviForeground , " + var1);
            if (var3.getPackageName().equals(var1)) {
               return true;
            }
         }
      }

      return false;
   }

   public static void set24Hour(Context var0, boolean var1) {
      ContentResolver var2 = var0.getContentResolver();
      String var3;
      if (var1) {
         var3 = "24";
      } else {
         var3 = "12";
      }

      System.putString(var2, "time_12_24", var3);
   }

   public static void setAirPlaneMode(Context var0, boolean var1) {
      if (VERSION.SDK_INT <= 16) {
         System.putInt(var0.getContentResolver(), "airplane_mode_on", var1);
      } else {
         Global.putInt(var0.getContentResolver(), "airplane_mode_on", var1);
      }

      Intent var2 = new Intent("android.intent.action.AIRPLANE_MODE");
      var2.putExtra("state", (boolean)var1);
      var0.sendBroadcast(var2);
   }
}
