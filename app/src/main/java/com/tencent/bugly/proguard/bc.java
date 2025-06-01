package com.tencent.bugly.proguard;

import android.util.Base64;

public final class bc {
   private static String a;
   private static String b;

   public static String a() {
      try {
         String var0 = new String(Base64.decode(b, 0));
         return var0;
      } catch (Throwable var2) {
         if (!al.a(var2)) {
            var2.printStackTrace();
         }

         return null;
      }
   }

   public static String b() {
      return a;
   }
}
