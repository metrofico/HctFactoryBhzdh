package com.hzbhd.util;

import android.os.SystemProperties;
import android.util.Log;

public class LogUtil {
   private static final int LEVEL_1 = 1;
   private static final int LEVEL_2 = 2;
   private static final int LEVEL_3 = 3;
   private static final int LEVEL_4 = 4;
   private static final int LEVEL_5 = 5;
   private static final int LEVEL_6 = 6;
   private static final int LEVEL_7 = 7;
   private static final int LEVEL_8 = 8;
   private static final int LEVEL_9 = 9;

   private static String createMsgPre() {
      StackTraceElement[] var2 = (new Throwable()).getStackTrace();
      String var1 = var2[2].getFileName();
      int var0 = var2[2].getLineNumber();
      StringBuffer var3 = new StringBuffer();
      var3.append("(").append(var1).append(":").append(var0).append(")");
      return var3.toString();
   }

   private static String createTag() {
      return Thread.currentThread().getName();
   }

   public static void d(String var0) {
      Log.d(createTag(), createMsgPre() + var0);
   }

   public static void e(String var0) {
      Log.e(createTag(), createMsgPre() + var0);
   }

   public static int getLogLevel() {
      return SystemProperties.getInt("persist.log", 5);
   }

   public static void i(String var0) {
      Log.i(createTag(), createMsgPre() + var0);
   }

   public static boolean log0() {
      int var0 = getLogLevel();
      boolean var1 = true;
      if (var0 >= 1) {
         var1 = false;
      }

      return var1;
   }

   public static boolean log1() {
      boolean var0;
      if (getLogLevel() < 2) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public static boolean log2() {
      boolean var0;
      if (getLogLevel() < 3) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public static boolean log3() {
      boolean var0;
      if (getLogLevel() < 4) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public static boolean log4() {
      boolean var0;
      if (getLogLevel() < 5) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public static boolean log5() {
      boolean var0;
      if (getLogLevel() < 6) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public static boolean log6() {
      boolean var0;
      if (getLogLevel() < 7) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public static boolean log7() {
      boolean var0;
      if (getLogLevel() < 8) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public static boolean log8() {
      boolean var0;
      if (getLogLevel() < 9) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public static boolean log9() {
      return true;
   }

   public static void v(String var0) {
      Log.v(createTag(), createMsgPre() + var0);
   }

   public static void w(String var0) {
      Log.w(createTag(), createMsgPre() + var0);
   }
}
