package com.tencent.bugly.crashreport;

import android.util.Log;
import com.tencent.bugly.proguard.ao;
import com.tencent.bugly.proguard.p;

public class BuglyLog {
   public static void d(String var0, String var1) {
      String var2 = var0;
      if (var0 == null) {
         var2 = "";
      }

      var0 = var1;
      if (var1 == null) {
         var0 = "null";
      }

      if (p.c) {
         Log.d(var2, var0);
      }

      ao.a("D", var2, var0);
   }

   public static void e(String var0, String var1) {
      String var2 = var0;
      if (var0 == null) {
         var2 = "";
      }

      var0 = var1;
      if (var1 == null) {
         var0 = "null";
      }

      if (p.c) {
         Log.e(var2, var0);
      }

      ao.a("E", var2, var0);
   }

   public static void e(String var0, String var1, Throwable var2) {
      String var3 = var0;
      if (var0 == null) {
         var3 = "";
      }

      var0 = var1;
      if (var1 == null) {
         var0 = "null";
      }

      if (p.c) {
         Log.e(var3, var0, var2);
      }

      ao.a("E", var3, var2);
   }

   public static void i(String var0, String var1) {
      String var2 = var0;
      if (var0 == null) {
         var2 = "";
      }

      var0 = var1;
      if (var1 == null) {
         var0 = "null";
      }

      if (p.c) {
         Log.i(var2, var0);
      }

      ao.a("I", var2, var0);
   }

   public static void setCache(int var0) {
      ao.a(var0);
   }

   public static void v(String var0, String var1) {
      String var2 = var0;
      if (var0 == null) {
         var2 = "";
      }

      var0 = var1;
      if (var1 == null) {
         var0 = "null";
      }

      if (p.c) {
         Log.v(var2, var0);
      }

      ao.a("V", var2, var0);
   }

   public static void w(String var0, String var1) {
      String var2 = var0;
      if (var0 == null) {
         var2 = "";
      }

      var0 = var1;
      if (var1 == null) {
         var0 = "null";
      }

      if (p.c) {
         Log.w(var2, var0);
      }

      ao.a("W", var2, var0);
   }
}
