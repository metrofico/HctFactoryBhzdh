package androidx.core.view;

import android.os.Build.VERSION;
import android.view.ViewGroup;

public final class MarginLayoutParamsCompat {
   private MarginLayoutParamsCompat() {
   }

   public static int getLayoutDirection(ViewGroup.MarginLayoutParams var0) {
      int var1 = VERSION.SDK_INT;
      byte var2 = 0;
      if (var1 >= 17) {
         var1 = var0.getLayoutDirection();
      } else {
         var1 = 0;
      }

      if (var1 != 0 && var1 != 1) {
         var1 = var2;
      }

      return var1;
   }

   public static int getMarginEnd(ViewGroup.MarginLayoutParams var0) {
      return VERSION.SDK_INT >= 17 ? var0.getMarginEnd() : var0.rightMargin;
   }

   public static int getMarginStart(ViewGroup.MarginLayoutParams var0) {
      return VERSION.SDK_INT >= 17 ? var0.getMarginStart() : var0.leftMargin;
   }

   public static boolean isMarginRelative(ViewGroup.MarginLayoutParams var0) {
      return VERSION.SDK_INT >= 17 ? var0.isMarginRelative() : false;
   }

   public static void resolveLayoutDirection(ViewGroup.MarginLayoutParams var0, int var1) {
      if (VERSION.SDK_INT >= 17) {
         var0.resolveLayoutDirection(var1);
      }

   }

   public static void setLayoutDirection(ViewGroup.MarginLayoutParams var0, int var1) {
      if (VERSION.SDK_INT >= 17) {
         var0.setLayoutDirection(var1);
      }

   }

   public static void setMarginEnd(ViewGroup.MarginLayoutParams var0, int var1) {
      if (VERSION.SDK_INT >= 17) {
         var0.setMarginEnd(var1);
      } else {
         var0.rightMargin = var1;
      }

   }

   public static void setMarginStart(ViewGroup.MarginLayoutParams var0, int var1) {
      if (VERSION.SDK_INT >= 17) {
         var0.setMarginStart(var1);
      } else {
         var0.leftMargin = var1;
      }

   }
}
