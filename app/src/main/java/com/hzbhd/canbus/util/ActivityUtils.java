package com.hzbhd.canbus.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.hzbhd.canbus.activity.MainActivity;
import com.hzbhd.canbus.config.CanbusConfig;

public class ActivityUtils {
   private static boolean temp_tempshow_10_000_ms;

   public static ComponentName getTopActivityInfo(Context var0) {
      return ((ActivityManager.RunningTaskInfo)((ActivityManager)var0.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity;
   }

   public static boolean isAuxinActivity(Context var0) {
      if (getTopActivityInfo(var0) != null) {
         Log.d("mww", "getTopActivityInfo().getClassName()= " + getTopActivityInfo(var0).getClassName());
         if ("com.hzbhd.misc.auxin.MainActivity".equals(getTopActivityInfo(var0).getClassName())) {
            return true;
         }
      }

      return false;
   }

   public static boolean isPrecameraActivity(Context var0) {
      if (getTopActivityInfo(var0) != null) {
         Log.d("mww", "getTopActivityInfo().getClassName()= " + getTopActivityInfo(var0).getClassName());
         if ("com.hzbhd.misc.camera.PreCameraActivity".equals(getTopActivityInfo(var0).getClassName())) {
            return true;
         }
      }

      return false;
   }

   public static boolean isTemp_tempshow_10_000_ms() {
      return temp_tempshow_10_000_ms;
   }

   public static Intent newIntent(Context var0, Class var1) {
      Intent var2 = new Intent(var0, var1);
      var2.putExtra("temp_show_10_000_ms", true);
      var2.addFlags(268435456);
      return var2;
   }

   public static void setTemp_tempshow_10_000_ms(boolean var0) {
      temp_tempshow_10_000_ms = var0;
   }

   public static void startCanbusMainActivity(Context var0) {
      int var1 = CanbusConfig.INSTANCE.getCanType();
      Class var2;
      if (var1 != 283) {
         if (var1 != 291) {
            var2 = MainActivity.class;
         } else {
            var2 = com.hzbhd.canbus.car_cus._291.MainActivity.class;
         }
      } else {
         var2 = com.hzbhd.canbus.car_cus._283.MainActivity.class;
      }

      Intent var3 = new Intent(var0, var2);
      var3.setFlags(268435456);
      var0.startActivity(var3);
   }
}
