package androidx.core.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.DisplayCutout;
import androidx.core.graphics.Insets;
import androidx.core.os.BuildCompat;
import androidx.core.util.ObjectsCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DisplayCutoutCompat {
   private final Object mDisplayCutout;

   public DisplayCutoutCompat(Rect var1, List var2) {
      DisplayCutout var3;
      if (VERSION.SDK_INT >= 28) {
         var3 = new DisplayCutout(var1, var2);
      } else {
         var3 = null;
      }

      this(var3);
   }

   public DisplayCutoutCompat(Insets var1, Rect var2, Rect var3, Rect var4, Rect var5, Insets var6) {
      this(constructDisplayCutout(var1, var2, var3, var4, var5, var6));
   }

   private DisplayCutoutCompat(Object var1) {
      this.mDisplayCutout = var1;
   }

   private static DisplayCutout constructDisplayCutout(Insets var0, Rect var1, Rect var2, Rect var3, Rect var4, Insets var5) {
      if (BuildCompat.isAtLeastR()) {
         return new DisplayCutout(var0.toPlatformInsets(), var1, var2, var3, var4, var5.toPlatformInsets());
      } else if (VERSION.SDK_INT >= 29) {
         return new DisplayCutout(var0.toPlatformInsets(), var1, var2, var3, var4);
      } else if (VERSION.SDK_INT >= 28) {
         Rect var7 = new Rect(var0.left, var0.top, var0.right, var0.bottom);
         ArrayList var6 = new ArrayList();
         if (var1 != null) {
            var6.add(var1);
         }

         if (var2 != null) {
            var6.add(var2);
         }

         if (var3 != null) {
            var6.add(var3);
         }

         if (var4 != null) {
            var6.add(var4);
         }

         return new DisplayCutout(var7, var6);
      } else {
         return null;
      }
   }

   static DisplayCutoutCompat wrap(Object var0) {
      DisplayCutoutCompat var1;
      if (var0 == null) {
         var1 = null;
      } else {
         var1 = new DisplayCutoutCompat(var0);
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         DisplayCutoutCompat var2 = (DisplayCutoutCompat)var1;
         return ObjectsCompat.equals(this.mDisplayCutout, var2.mDisplayCutout);
      } else {
         return false;
      }
   }

   public List getBoundingRects() {
      return VERSION.SDK_INT >= 28 ? ((DisplayCutout)this.mDisplayCutout).getBoundingRects() : Collections.emptyList();
   }

   public int getSafeInsetBottom() {
      return VERSION.SDK_INT >= 28 ? ((DisplayCutout)this.mDisplayCutout).getSafeInsetBottom() : 0;
   }

   public int getSafeInsetLeft() {
      return VERSION.SDK_INT >= 28 ? ((DisplayCutout)this.mDisplayCutout).getSafeInsetLeft() : 0;
   }

   public int getSafeInsetRight() {
      return VERSION.SDK_INT >= 28 ? ((DisplayCutout)this.mDisplayCutout).getSafeInsetRight() : 0;
   }

   public int getSafeInsetTop() {
      return VERSION.SDK_INT >= 28 ? ((DisplayCutout)this.mDisplayCutout).getSafeInsetTop() : 0;
   }

   public Insets getWaterfallInsets() {
      return BuildCompat.isAtLeastR() ? Insets.toCompatInsets(((DisplayCutout)this.mDisplayCutout).getWaterfallInsets()) : Insets.NONE;
   }

   public int hashCode() {
      Object var2 = this.mDisplayCutout;
      int var1;
      if (var2 == null) {
         var1 = 0;
      } else {
         var1 = var2.hashCode();
      }

      return var1;
   }

   public String toString() {
      return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
   }

   DisplayCutout unwrap() {
      return (DisplayCutout)this.mDisplayCutout;
   }
}
