package com.hzbhd.canbus.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageDataObserver;
import android.util.Log;
import java.util.List;

public class SystemUtil {
   public static void cleanLauncher(Context var0) {
      try {
         Log.i("clearData", "Clear launcher3 data");
         ((ActivityManager)var0.getSystemService("activity")).clearApplicationUserData("com.android.launcher3", (IPackageDataObserver)null);
         Intent var1 = new Intent();
         var1.setAction("android.intent.action.MAIN");
         var1.addCategory("android.intent.category.HOME");
         var1.addCategory("android.intent.category.DEFAULT");
         var1.addCategory("android.intent.category.MONKEY");
         var0.startActivity(var1);
      } catch (Exception var2) {
         Log.i("clearData", "Exception e" + var2.toString());
      }

   }

   public static String getTopActivityPackageName(Context var0) {
      ActivityManager.RunningAppProcessInfo var1 = (ActivityManager.RunningAppProcessInfo)((ActivityManager)var0.getSystemService("activity")).getRunningAppProcesses().get(0);
      return var1 != null && var1.importance == 100 ? var1.processName : "";
   }

   public static boolean isForeground(Context var0, String var1) {
      List var2 = ((ActivityManager)var0.getSystemService("activity")).getRunningTasks(1);
      return var2.isEmpty() ? false : ((ActivityManager.RunningTaskInfo)var2.get(0)).topActivity.getClassName().equals(var1);
   }

   public static boolean isForeground(Context var0, String[] var1) {
      List var5 = ((ActivityManager)var0.getSystemService("activity")).getRunningTasks(1);
      if (var5.isEmpty()) {
         return false;
      } else {
         ComponentName var6 = ((ActivityManager.RunningTaskInfo)var5.get(0)).topActivity;
         int var3 = var1.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            String var4 = var1[var2];
            if (var6.getClassName().equals(var4)) {
               return true;
            }
         }

         return false;
      }
   }

   public static void printCanbusParameter() {
   }
}
