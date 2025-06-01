package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R;

public final class ThemeEnforcement {
   private static final int[] APPCOMPAT_CHECK_ATTRS;
   private static final String APPCOMPAT_THEME_NAME = "Theme.AppCompat";
   private static final int[] MATERIAL_CHECK_ATTRS;
   private static final String MATERIAL_THEME_NAME = "Theme.MaterialComponents";

   static {
      APPCOMPAT_CHECK_ATTRS = new int[]{R.attr.colorPrimary};
      MATERIAL_CHECK_ATTRS = new int[]{R.attr.colorSecondary};
   }

   private ThemeEnforcement() {
   }

   public static void checkAppCompatTheme(Context var0) {
      checkTheme(var0, APPCOMPAT_CHECK_ATTRS, "Theme.AppCompat");
   }

   private static void checkCompatibleTheme(Context var0, AttributeSet var1, int var2, int var3) {
      TypedArray var5 = var0.obtainStyledAttributes(var1, R.styleable.ThemeEnforcement, var2, var3);
      boolean var4 = var5.getBoolean(R.styleable.ThemeEnforcement_enforceMaterialTheme, false);
      var5.recycle();
      if (var4) {
         checkMaterialTheme(var0);
      }

      checkAppCompatTheme(var0);
   }

   public static void checkMaterialTheme(Context var0) {
      checkTheme(var0, MATERIAL_CHECK_ATTRS, "Theme.MaterialComponents");
   }

   private static void checkTextAppearance(Context var0, AttributeSet var1, int[] var2, int var3, int var4, int... var5) {
      TypedArray var8 = var0.obtainStyledAttributes(var1, R.styleable.ThemeEnforcement, var3, var4);
      int var6 = R.styleable.ThemeEnforcement_enforceTextAppearance;
      boolean var7 = false;
      if (!var8.getBoolean(var6, false)) {
         var8.recycle();
      } else {
         if (var5 != null && var5.length != 0) {
            var7 = isCustomTextAppearanceValid(var0, var1, var2, var3, var4, var5);
         } else if (var8.getResourceId(R.styleable.ThemeEnforcement_android_textAppearance, -1) != -1) {
            var7 = true;
         }

         var8.recycle();
         if (!var7) {
            throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
         }
      }
   }

   private static void checkTheme(Context var0, int[] var1, String var2) {
      if (!isTheme(var0, var1)) {
         throw new IllegalArgumentException("The style on this component requires your app theme to be " + var2 + " (or a descendant).");
      }
   }

   public static boolean isAppCompatTheme(Context var0) {
      return isTheme(var0, APPCOMPAT_CHECK_ATTRS);
   }

   private static boolean isCustomTextAppearanceValid(Context var0, AttributeSet var1, int[] var2, int var3, int var4, int... var5) {
      TypedArray var6 = var0.obtainStyledAttributes(var1, var2, var3, var4);
      var4 = var5.length;

      for(var3 = 0; var3 < var4; ++var3) {
         if (var6.getResourceId(var5[var3], -1) == -1) {
            var6.recycle();
            return false;
         }
      }

      var6.recycle();
      return true;
   }

   public static boolean isMaterialTheme(Context var0) {
      return isTheme(var0, MATERIAL_CHECK_ATTRS);
   }

   private static boolean isTheme(Context var0, int[] var1) {
      TypedArray var3 = var0.obtainStyledAttributes(var1);
      boolean var2 = var3.hasValue(0);
      var3.recycle();
      return var2;
   }

   public static TypedArray obtainStyledAttributes(Context var0, AttributeSet var1, int[] var2, int var3, int var4, int... var5) {
      checkCompatibleTheme(var0, var1, var3, var4);
      checkTextAppearance(var0, var1, var2, var3, var4, var5);
      return var0.obtainStyledAttributes(var1, var2, var3, var4);
   }

   public static TintTypedArray obtainTintedStyledAttributes(Context var0, AttributeSet var1, int[] var2, int var3, int var4, int... var5) {
      checkCompatibleTheme(var0, var1, var3, var4);
      checkTextAppearance(var0, var1, var2, var3, var4, var5);
      return TintTypedArray.obtainStyledAttributes(var0, var1, var2, var3, var4);
   }
}
