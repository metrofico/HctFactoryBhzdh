package androidx.core.os;

import android.os.Build.VERSION;

public class BuildCompat {
   private BuildCompat() {
   }

   @Deprecated
   public static boolean isAtLeastN() {
      boolean var0;
      if (VERSION.SDK_INT >= 24) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   @Deprecated
   public static boolean isAtLeastNMR1() {
      boolean var0;
      if (VERSION.SDK_INT >= 25) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   @Deprecated
   public static boolean isAtLeastO() {
      boolean var0;
      if (VERSION.SDK_INT >= 26) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   @Deprecated
   public static boolean isAtLeastOMR1() {
      boolean var0;
      if (VERSION.SDK_INT >= 27) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   @Deprecated
   public static boolean isAtLeastP() {
      boolean var0;
      if (VERSION.SDK_INT >= 28) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   protected static boolean isAtLeastPreReleaseCodename(String var0, String var1) {
      boolean var3 = "REL".equals(var1);
      boolean var2 = false;
      if (var3) {
         return false;
      } else {
         if (var1.compareTo(var0) >= 0) {
            var2 = true;
         }

         return var2;
      }
   }

   @Deprecated
   public static boolean isAtLeastQ() {
      boolean var0;
      if (VERSION.SDK_INT >= 29) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   @Deprecated
   public static boolean isAtLeastR() {
      boolean var0;
      if (VERSION.SDK_INT >= 30) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public static boolean isAtLeastS() {
      boolean var0;
      if (VERSION.SDK_INT < 31 && !isAtLeastPreReleaseCodename("S", VERSION.CODENAME)) {
         var0 = false;
      } else {
         var0 = true;
      }

      return var0;
   }

   public static boolean isAtLeastT() {
      return isAtLeastPreReleaseCodename("T", VERSION.CODENAME);
   }

   public @interface PrereleaseSdkCheck {
   }
}
