package com.hzbhd.commontools.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import java.util.List;

public class SystemUtils {
   public static boolean isForeground(Context var0, String var1) {
      if (var0 != null && !TextUtils.isEmpty(var1)) {
         List var2 = ((ActivityManager)var0.getSystemService("activity")).getRunningTasks(1);
         if (var2 != null && var2.size() > 0) {
            return var1.equals(((ActivityManager.RunningTaskInfo)var2.get(0)).topActivity.getClassName());
         }
      }

      return false;
   }
}
