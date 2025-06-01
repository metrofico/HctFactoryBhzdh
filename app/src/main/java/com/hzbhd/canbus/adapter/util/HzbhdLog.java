package com.hzbhd.canbus.adapter.util;

import android.os.SystemProperties;
import android.util.Log;

public class HzbhdLog {
   private static String createTag() {
      StackTraceElement[] var2 = (new Throwable()).getStackTrace();
      String var1 = var2[2].getFileName();
      int var0 = var2[2].getLineNumber();
      StringBuffer var3 = new StringBuffer();
      var3.append("(").append(var1).append(":").append(var0).append(")");
      return var3.toString();
   }

   public static void d(String var0) {
      boolean var1 = false;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var1 = true;
      }

      if (SystemConstant.LOG_DEBUG) {
         Log.d(createTag(), getThreadNameAndId(var0));
      } else if (var1) {
         Log.d(createTag(), getThreadNameAndId(var0));
      }

   }

   public static void d(String var0, String var1) {
      boolean var3 = false;
      boolean var2;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (!var0.contains("Tuner")) {
         if (var0.contains("opengl")) {
            var2 = var3;
            if (SystemProperties.getInt("opengl", 0) == 1) {
               var2 = true;
            }

            if (var2) {
               Log.d(var0, getThreadNameAndId(var1));
            }
         } else if (SystemConstant.LOG_DEBUG) {
            Log.d(createTag(), getThreadNameAndId(var1));
         } else if (var2) {
            Log.d(var0, getThreadNameAndId(var1));
         }

      }
   }

   public static void e(String var0, String var1) {
      boolean var2 = false;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var2 = true;
      }

      if (SystemConstant.LOG_ERROR) {
         Log.e(createTag(), getThreadNameAndId(var1));
      } else if (var2) {
         Log.e(var0, getThreadNameAndId(var1));
      }

   }

   public static void e(String var0, String var1, Throwable var2) {
      boolean var3 = false;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var3 = true;
      }

      if (SystemConstant.LOG_ERROR) {
         Log.e(createTag(), getThreadNameAndId(var1));
      } else if (var3) {
         Log.e(var0, getThreadNameAndId(var1));
      }

   }

   public static String getThreadNameAndId(String var0) {
      return "[" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "] " + var0;
   }

   public static void i(String var0, String var1) {
      boolean var2 = false;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var2 = true;
      }

      if (SystemConstant.LOG_INFO) {
         Log.i(createTag(), getThreadNameAndId(var1));
      } else if (var2) {
         Log.i(var0, getThreadNameAndId(var1));
      }

   }

   public static void logError(String var0, String var1) {
      boolean var2 = false;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var2 = true;
      }

      if (SystemConstant.DEBUG) {
         Log.e(createTag(), getThreadNameAndId(var1));
      } else if (var2) {
         Log.e(var0, getThreadNameAndId(var1));
      }

   }

   public static void logInfo(String var0, String var1) {
      boolean var2 = false;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var2 = true;
      }

      if (SystemConstant.LOG_INFO) {
         Log.i(createTag(), getThreadNameAndId(var1));
      } else if (var2) {
         Log.i(var0, getThreadNameAndId(var1));
      }

   }

   public static void logWarn(String var0, String var1) {
      boolean var2 = false;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var2 = true;
      }

      if (SystemConstant.LOG_WARM) {
         Log.w(createTag(), getThreadNameAndId(var1));
      } else if (var2) {
         Log.w(var0, getThreadNameAndId(var1));
      }

   }

   public static void v(String var0, String var1) {
      boolean var2 = false;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var2 = true;
      }

      if (SystemConstant.LOG_VERBOSE) {
         Log.v(createTag(), getThreadNameAndId(var1));
      } else if (var2) {
         Log.v(var0, getThreadNameAndId(var1));
      }

   }

   public static void w(String var0, String var1) {
      boolean var2 = false;
      if (SystemProperties.getInt("PrintLogFlag", 0) == 1) {
         var2 = true;
      }

      if (SystemConstant.LOG_WARM) {
         Log.w(createTag(), getThreadNameAndId(var1));
      } else if (var2) {
         Log.w(var0, getThreadNameAndId(var1));
      }

   }
}
