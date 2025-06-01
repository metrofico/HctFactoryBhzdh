package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.CheckedTextView;
import java.lang.reflect.Field;

public final class CheckedTextViewCompat {
   private static final String TAG = "CheckedTextViewCompat";

   private CheckedTextViewCompat() {
   }

   public static Drawable getCheckMarkDrawable(CheckedTextView var0) {
      return VERSION.SDK_INT >= 16 ? var0.getCheckMarkDrawable() : CheckedTextViewCompat.Api14Impl.getCheckMarkDrawable(var0);
   }

   public static ColorStateList getCheckMarkTintList(CheckedTextView var0) {
      if (VERSION.SDK_INT >= 21) {
         return var0.getCheckMarkTintList();
      } else {
         return var0 instanceof TintableCheckedTextView ? ((TintableCheckedTextView)var0).getSupportCheckMarkTintList() : null;
      }
   }

   public static PorterDuff.Mode getCheckMarkTintMode(CheckedTextView var0) {
      if (VERSION.SDK_INT >= 21) {
         return var0.getCheckMarkTintMode();
      } else {
         return var0 instanceof TintableCheckedTextView ? ((TintableCheckedTextView)var0).getSupportCheckMarkTintMode() : null;
      }
   }

   public static void setCheckMarkTintList(CheckedTextView var0, ColorStateList var1) {
      if (VERSION.SDK_INT >= 21) {
         var0.setCheckMarkTintList(var1);
      } else if (var0 instanceof TintableCheckedTextView) {
         ((TintableCheckedTextView)var0).setSupportCheckMarkTintList(var1);
      }

   }

   public static void setCheckMarkTintMode(CheckedTextView var0, PorterDuff.Mode var1) {
      if (VERSION.SDK_INT >= 21) {
         var0.setCheckMarkTintMode(var1);
      } else if (var0 instanceof TintableCheckedTextView) {
         ((TintableCheckedTextView)var0).setSupportCheckMarkTintMode(var1);
      }

   }

   private static class Api14Impl {
      private static Field sCheckMarkDrawableField;
      private static boolean sResolved;

      static Drawable getCheckMarkDrawable(CheckedTextView var0) {
         Field var1;
         if (!sResolved) {
            try {
               var1 = CheckedTextView.class.getDeclaredField("mCheckMarkDrawable");
               sCheckMarkDrawableField = var1;
               var1.setAccessible(true);
            } catch (NoSuchFieldException var2) {
               Log.i("CheckedTextViewCompat", "Failed to retrieve mCheckMarkDrawable field", var2);
            }

            sResolved = true;
         }

         var1 = sCheckMarkDrawableField;
         if (var1 != null) {
            try {
               Drawable var4 = (Drawable)var1.get(var0);
               return var4;
            } catch (IllegalAccessException var3) {
               Log.i("CheckedTextViewCompat", "Failed to get check mark drawable via reflection", var3);
               sCheckMarkDrawableField = null;
            }
         }

         return null;
      }
   }

   private static class Api16Impl {
      static Drawable getCheckMarkDrawable(CheckedTextView var0) {
         return var0.getCheckMarkDrawable();
      }
   }

   private static class Api21Impl {
      static ColorStateList getCheckMarkTintList(CheckedTextView var0) {
         return var0.getCheckMarkTintList();
      }

      static PorterDuff.Mode getCheckMarkTintMode(CheckedTextView var0) {
         return var0.getCheckMarkTintMode();
      }

      static void setCheckMarkTintList(CheckedTextView var0, ColorStateList var1) {
         var0.setCheckMarkTintList(var1);
      }

      static void setCheckMarkTintMode(CheckedTextView var0, PorterDuff.Mode var1) {
         var0.setCheckMarkTintMode(var1);
      }
   }
}
