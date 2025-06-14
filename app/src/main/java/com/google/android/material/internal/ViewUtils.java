package com.google.android.material.internal;

import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import androidx.core.view.ViewCompat;

public class ViewUtils {
   public static boolean isLayoutRtl(View var0) {
      int var1 = ViewCompat.getLayoutDirection(var0);
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public static PorterDuff.Mode parseTintMode(int var0, PorterDuff.Mode var1) {
      if (var0 != 3) {
         if (var0 != 5) {
            if (var0 != 9) {
               switch (var0) {
                  case 14:
                     return Mode.MULTIPLY;
                  case 15:
                     return Mode.SCREEN;
                  case 16:
                     return Mode.ADD;
                  default:
                     return var1;
               }
            } else {
               return Mode.SRC_ATOP;
            }
         } else {
            return Mode.SRC_IN;
         }
      } else {
         return Mode.SRC_OVER;
      }
   }
}
