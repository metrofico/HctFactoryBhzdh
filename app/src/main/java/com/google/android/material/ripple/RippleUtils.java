package com.google.android.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.util.StateSet;
import androidx.core.graphics.ColorUtils;

public class RippleUtils {
   private static final int[] FOCUSED_STATE_SET;
   private static final int[] HOVERED_FOCUSED_STATE_SET;
   private static final int[] HOVERED_STATE_SET;
   private static final int[] PRESSED_STATE_SET;
   private static final int[] SELECTED_FOCUSED_STATE_SET;
   private static final int[] SELECTED_HOVERED_FOCUSED_STATE_SET;
   private static final int[] SELECTED_HOVERED_STATE_SET;
   private static final int[] SELECTED_PRESSED_STATE_SET;
   private static final int[] SELECTED_STATE_SET;
   public static final boolean USE_FRAMEWORK_RIPPLE;

   static {
      boolean var0;
      if (VERSION.SDK_INT >= 21) {
         var0 = true;
      } else {
         var0 = false;
      }

      USE_FRAMEWORK_RIPPLE = var0;
      PRESSED_STATE_SET = new int[]{16842919};
      HOVERED_FOCUSED_STATE_SET = new int[]{16843623, 16842908};
      FOCUSED_STATE_SET = new int[]{16842908};
      HOVERED_STATE_SET = new int[]{16843623};
      SELECTED_PRESSED_STATE_SET = new int[]{16842913, 16842919};
      SELECTED_HOVERED_FOCUSED_STATE_SET = new int[]{16842913, 16843623, 16842908};
      SELECTED_FOCUSED_STATE_SET = new int[]{16842913, 16842908};
      SELECTED_HOVERED_STATE_SET = new int[]{16842913, 16843623};
      SELECTED_STATE_SET = new int[]{16842913};
   }

   private RippleUtils() {
   }

   public static ColorStateList convertToRippleDrawableColor(ColorStateList var0) {
      int var1;
      int var2;
      int[] var9;
      int[] var10;
      if (USE_FRAMEWORK_RIPPLE) {
         var10 = SELECTED_STATE_SET;
         var2 = getColorForState(var0, SELECTED_PRESSED_STATE_SET);
         var9 = StateSet.NOTHING;
         var1 = getColorForState(var0, PRESSED_STATE_SET);
         return new ColorStateList(new int[][]{var10, var9}, new int[]{var2, var1});
      } else {
         var9 = SELECTED_PRESSED_STATE_SET;
         var2 = getColorForState(var0, var9);
         var10 = SELECTED_HOVERED_FOCUSED_STATE_SET;
         int var7 = getColorForState(var0, var10);
         int[] var15 = SELECTED_FOCUSED_STATE_SET;
         int var5 = getColorForState(var0, var15);
         int[] var12 = SELECTED_HOVERED_STATE_SET;
         int var4 = getColorForState(var0, var12);
         int[] var16 = SELECTED_STATE_SET;
         int[] var17 = PRESSED_STATE_SET;
         int var8 = getColorForState(var0, var17);
         int[] var14 = HOVERED_FOCUSED_STATE_SET;
         var1 = getColorForState(var0, var14);
         int[] var13 = FOCUSED_STATE_SET;
         int var6 = getColorForState(var0, var13);
         int[] var11 = HOVERED_STATE_SET;
         int var3 = getColorForState(var0, var11);
         return new ColorStateList(new int[][]{var9, var10, var15, var12, var16, var17, var14, var13, var11, StateSet.NOTHING}, new int[]{var2, var7, var5, var4, 0, var8, var1, var6, var3, 0});
      }
   }

   private static int doubleAlpha(int var0) {
      return ColorUtils.setAlphaComponent(var0, Math.min(Color.alpha(var0) * 2, 255));
   }

   private static int getColorForState(ColorStateList var0, int[] var1) {
      int var2;
      if (var0 != null) {
         var2 = var0.getColorForState(var1, var0.getDefaultColor());
      } else {
         var2 = 0;
      }

      int var3 = var2;
      if (USE_FRAMEWORK_RIPPLE) {
         var3 = doubleAlpha(var2);
      }

      return var3;
   }
}
