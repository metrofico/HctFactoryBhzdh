package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

public final class al {
   public static String a;
   public static String b;
   public static boolean c;

   private static boolean a(int var0, String var1, Object... var2) {
      if (!c) {
         return false;
      } else {
         String var3;
         if (var1 == null) {
            var3 = "null";
         } else {
            var3 = var1;
            if (var2 != null) {
               if (var2.length == 0) {
                  var3 = var1;
               } else {
                  var3 = String.format(Locale.US, var1, var2);
               }
            }
         }

         if (var0 != 0) {
            if (var0 != 1) {
               if (var0 != 2) {
                  if (var0 != 3) {
                     if (var0 != 5) {
                        return false;
                     } else {
                        Log.i(a, var3);
                        return true;
                     }
                  } else {
                     Log.e(b, var3);
                     return true;
                  }
               } else {
                  Log.w(b, var3);
                  return true;
               }
            } else {
               Log.d(b, var3);
               return true;
            }
         } else {
            Log.i(b, var3);
            return true;
         }
      }
   }

   private static boolean a(int var0, Throwable var1) {
      return !c ? false : a(var0, ap.a(var1));
   }

   public static boolean a(Class var0, String var1, Object... var2) {
      return a(0, String.format(Locale.US, "[%s] %s", var0.getSimpleName(), var1), var2);
   }

   public static boolean a(String var0, Object... var1) {
      return a(0, var0, var1);
   }

   public static boolean a(Throwable var0) {
      return a(2, var0);
   }

   public static boolean b(String var0, Object... var1) {
      return a(5, var0, var1);
   }

   public static boolean b(Throwable var0) {
      return a(3, var0);
   }

   public static boolean c(String var0, Object... var1) {
      return a(1, var0, var1);
   }

   public static boolean d(String var0, Object... var1) {
      return a(2, var0, var1);
   }

   public static boolean e(String var0, Object... var1) {
      return a(3, var0, var1);
   }
}
