package com.tencent.bugly.crashreport.inner;

import com.tencent.bugly.proguard.al;
import com.tencent.bugly.proguard.au;
import java.util.Map;

public class InnerApi {
   public static void postCocos2dxCrashAsync(int var0, String var1, String var2, String var3) {
      if (var1 != null && var2 != null && var3 != null) {
         if (var0 != 5 && var0 != 6) {
            al.e("post cocos2d-x fail category illeagle: %d", var0);
         } else {
            al.a("post cocos2d-x crash %s %s", var1, var2);
            au.a(Thread.currentThread(), var0, var1, var2, var3, (Map)null);
         }
      } else {
         al.e("post cocos2d-x fail args null");
      }
   }

   public static void postH5CrashAsync(Thread var0, String var1, String var2, String var3, Map var4) {
      if (var1 != null && var2 != null && var3 != null) {
         al.a("post h5 crash %s %s", var1, var2);
         au.a(var0, 8, var1, var2, var3, var4);
      } else {
         al.e("post h5 fail args null");
      }
   }

   public static void postU3dCrashAsync(String var0, String var1, String var2) {
      if (var0 == null || var1 == null || var2 == null) {
         al.e("post u3d fail args null");
      }

      al.a("post u3d crash %s %s", var0, var1);
      au.a(Thread.currentThread(), 4, var0, var1, var2, (Map)null);
   }
}
