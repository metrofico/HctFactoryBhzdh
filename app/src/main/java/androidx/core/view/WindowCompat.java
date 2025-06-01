package androidx.core.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

public final class WindowCompat {
   public static final int FEATURE_ACTION_BAR = 8;
   public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
   public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

   private WindowCompat() {
   }

   public static WindowInsetsControllerCompat getInsetsController(Window var0, View var1) {
      return VERSION.SDK_INT >= 30 ? WindowCompat.Impl30.getInsetsController(var0) : new WindowInsetsControllerCompat(var0, var1);
   }

   public static View requireViewById(Window var0, int var1) {
      if (VERSION.SDK_INT >= 28) {
         return var0.requireViewById(var1);
      } else {
         View var2 = var0.findViewById(var1);
         if (var2 != null) {
            return var2;
         } else {
            throw new IllegalArgumentException("ID does not reference a View inside this Window");
         }
      }
   }

   public static void setDecorFitsSystemWindows(Window var0, boolean var1) {
      if (VERSION.SDK_INT >= 30) {
         var0.setDecorFitsSystemWindows(var1);
      } else if (VERSION.SDK_INT >= 16) {
         WindowCompat.Impl16.setDecorFitsSystemWindows(var0, var1);
      }

   }

   private static class Impl16 {
      static void setDecorFitsSystemWindows(Window var0, boolean var1) {
         View var3 = var0.getDecorView();
         int var2 = var3.getSystemUiVisibility();
         if (var1) {
            var2 &= -1793;
         } else {
            var2 |= 1792;
         }

         var3.setSystemUiVisibility(var2);
      }
   }

   private static class Impl30 {
      static WindowInsetsControllerCompat getInsetsController(Window var0) {
         WindowInsetsController var1 = var0.getInsetsController();
         return var1 != null ? WindowInsetsControllerCompat.toWindowInsetsControllerCompat(var1) : null;
      }

      static void setDecorFitsSystemWindows(Window var0, boolean var1) {
         var0.setDecorFitsSystemWindows(var1);
      }
   }
}
