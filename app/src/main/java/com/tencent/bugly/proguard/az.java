package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.os.Process;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public final class az {
   public static ActivityManager.ProcessErrorStateInfo a(ActivityManager var0, long var1) {
      if (var0 == null) {
         al.c("get anr state, ActivityManager is null");
         return null;
      } else {
         al.c("get anr state, timeout:%d", var1);
         var1 /= 500L;
         int var3 = 0;

         while(true) {
            ActivityManager.ProcessErrorStateInfo var5 = a(var0.getProcessesInErrorState());
            if (var5 == null) {
               al.c("found proc state is null");
            } else {
               if (var5.condition == 2) {
                  al.c("found proc state is anr! proc:%s", var5.processName);
                  return var5;
               }

               if (var5.condition == 1) {
                  al.c("found proc state is crashed!");
                  return null;
               }
            }

            int var4 = var3 + 1;
            if ((long)var3 >= var1) {
               return a("Find process anr, but unable to get anr message.");
            }

            al.c("try the %s times:", var4);
            ap.b(500L);
            var3 = var4;
         }
      }
   }

   private static ActivityManager.ProcessErrorStateInfo a(String var0) {
      ActivityManager.ProcessErrorStateInfo var1 = new ActivityManager.ProcessErrorStateInfo();
      var1.pid = Process.myPid();
      var1.processName = z.a(Process.myPid());
      var1.shortMsg = var0;
      return var1;
   }

   private static ActivityManager.ProcessErrorStateInfo a(List var0) {
      if (var0 != null && !var0.isEmpty()) {
         int var1 = Process.myPid();
         Iterator var3 = var0.iterator();

         ActivityManager.ProcessErrorStateInfo var2;
         do {
            if (!var3.hasNext()) {
               al.c("current proc not in the error state");
               return null;
            }

            var2 = (ActivityManager.ProcessErrorStateInfo)var3.next();
         } while(var2.pid != var1);

         if (TextUtils.isEmpty(var2.longMsg)) {
            return null;
         } else {
            al.c("found current proc in the error state");
            return var2;
         }
      } else {
         al.c("error state info list is null");
         return null;
      }
   }
}
