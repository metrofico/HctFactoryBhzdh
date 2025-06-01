package com.hzbhd.ui.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tB/\b\u0016\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u000f¨\u0006\u0010"},
   d2 = {"Lcom/hzbhd/ui/base/PressedDrawable;", "Landroid/graphics/drawable/StateListDrawable;", "context", "Landroid/content/Context;", "nId", "", "pId", "sId", "dId", "(Landroid/content/Context;IIII)V", "n", "Landroid/graphics/drawable/Drawable;", "p", "s", "d", "(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V", "bhdview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public final class PressedDrawable extends StateListDrawable {
   public PressedDrawable(Context var1, int var2, int var3, int var4, int var5) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      Drawable var6;
      if (var3 != 0) {
         var6 = ContextCompat.getDrawable(var1, var3);
         this.addState(new int[]{16842919}, var6);
      }

      if (var4 != 0) {
         var6 = ContextCompat.getDrawable(var1, var4);
         this.addState(new int[]{16842913}, var6);
      }

      if (var5 != 0) {
         var6 = ContextCompat.getDrawable(var1, var5);
         this.addState(new int[]{-16842910}, var6);
      }

      if (var2 != 0) {
         var6 = ContextCompat.getDrawable(var1, var2);
         this.addState(new int[]{-16842919}, var6);
      }

      if (var2 != 0) {
         Drawable var7 = ContextCompat.getDrawable(var1, var2);
         this.addState(new int[]{-16842913}, var7);
      }

   }

   public PressedDrawable(Drawable var1, Drawable var2, Drawable var3, Drawable var4) {
      if (var2 != null) {
         this.addState(new int[]{16842919}, var2);
      }

      if (var3 != null) {
         this.addState(new int[]{16842913}, var3);
      }

      if (var4 != null) {
         this.addState(new int[]{-16842910}, var4);
      }

      if (var1 != null) {
         this.addState(new int[]{-16842919}, var1);
      }

      if (var1 != null) {
         this.addState(new int[]{-16842913}, var1);
      }

   }
}
