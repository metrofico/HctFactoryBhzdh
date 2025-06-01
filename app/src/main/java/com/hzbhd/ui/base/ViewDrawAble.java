package com.hzbhd.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010'\u001a\u00020(J\b\u0010)\u001a\u00020(H\u0016J\u0006\u0010*\u001a\u00020(J\u0010\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020(H\u0016R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u000e\u0010\u0018\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R\u001c\u0010$\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0012\"\u0004\b&\u0010\u0014¨\u0006/"},
   d2 = {"Lcom/hzbhd/ui/base/ViewDrawAble;", "Lcom/hzbhd/ui/base/BhdViewUtil$ViewUtilListener;", "view", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;)V", "focusFg", "", "getFocusFg", "()I", "setFocusFg", "(I)V", "focusFgDrawable", "Lcom/hzbhd/ui/base/PressedDrawable;", "getFocusFgDrawable", "()Lcom/hzbhd/ui/base/PressedDrawable;", "setFocusFgDrawable", "(Lcom/hzbhd/ui/base/PressedDrawable;)V", "focusId", "getFocusId", "setFocusId", "id_d", "id_d1", "id_n", "id_n1", "id_p", "id_p1", "id_s", "id_s1", "mView", "pressedDrawable", "getPressedDrawable", "setPressedDrawable", "pressedDrawable1", "getPressedDrawable1", "setPressedDrawable1", "onAttachedToWindow", "", "onColorChange", "onDetachedFromWindow", "onFocusChange", "isFocus", "", "resetBackground", "bhdview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public final class ViewDrawAble implements BhdViewUtil.ViewUtilListener {
   private int focusFg;
   private PressedDrawable focusFgDrawable;
   private int focusId;
   private final int id_d;
   private final int id_d1;
   private final int id_n;
   private final int id_n1;
   private final int id_p;
   private final int id_p1;
   private final int id_s;
   private final int id_s1;
   private final View mView;
   private PressedDrawable pressedDrawable;
   private PressedDrawable pressedDrawable1;

   public ViewDrawAble(View var1, Context var2, AttributeSet var3) {
      Intrinsics.checkNotNullParameter(var1, "view");
      Intrinsics.checkNotNullParameter(var2, "context");
      Intrinsics.checkNotNullParameter(var3, "attrs");
      super();
      this.mView = var1;
      TypedArray var8 = var2.obtainStyledAttributes(var3, R.styleable.color_attr);
      this.focusId = var8.getInt(R.styleable.color_attr_focusid, 0);
      this.focusFg = var8.getResourceId(R.styleable.color_attr_focusFg, 0);
      int var4 = var8.getResourceId(R.styleable.color_attr_n, 0);
      this.id_n = var4;
      int var5 = var8.getResourceId(R.styleable.color_attr_p, 0);
      this.id_p = var5;
      int var6 = var8.getResourceId(R.styleable.color_attr_s, 0);
      this.id_s = var6;
      int var7 = var8.getResourceId(R.styleable.color_attr_d, 0);
      this.id_d = var7;
      if (var4 != 0) {
         this.pressedDrawable = new PressedDrawable(var2, var4, var5, var6, var7);
      }

      var6 = var8.getResourceId(R.styleable.color_attr_n1, 0);
      this.id_n1 = var6;
      var5 = var8.getResourceId(R.styleable.color_attr_p1, 0);
      this.id_p1 = var5;
      var7 = var8.getResourceId(R.styleable.color_attr_s1, 0);
      this.id_s1 = var7;
      var4 = var8.getResourceId(R.styleable.color_attr_d1, 0);
      this.id_d1 = var4;
      if (var6 != 0) {
         this.pressedDrawable1 = new PressedDrawable(var2, var6, var5, var7, var4);
      }

      if (this.focusFg != 0) {
         this.focusFgDrawable = new PressedDrawable(var2, R.drawable.transparent, 0, this.focusFg, 0);
      }

      var8.recycle();
   }

   public final int getFocusFg() {
      return this.focusFg;
   }

   public final PressedDrawable getFocusFgDrawable() {
      return this.focusFgDrawable;
   }

   public final int getFocusId() {
      return this.focusId;
   }

   public final PressedDrawable getPressedDrawable() {
      return this.pressedDrawable;
   }

   public final PressedDrawable getPressedDrawable1() {
      return this.pressedDrawable1;
   }

   public final void onAttachedToWindow() {
      BhdViewUtil.INSTANCE.addListener((BhdViewUtil.ViewUtilListener)this, this.focusId);
   }

   public void onColorChange() {
      this.resetBackground();
   }

   public final void onDetachedFromWindow() {
      BhdViewUtil.INSTANCE.removeListener((BhdViewUtil.ViewUtilListener)this, this.focusId);
   }

   public void onFocusChange(boolean var1) {
      if (this.mView.isSelected() != var1) {
         this.mView.setSelected(var1);
      }

   }

   public void resetBackground() {
      PressedDrawable var1;
      if (BhdViewUtil.INSTANCE.getColorStyle() == 1) {
         var1 = this.focusFgDrawable;
         if (var1 != null) {
            this.mView.setForeground((Drawable)var1);
         }

         var1 = this.pressedDrawable1;
         if (var1 != null) {
            this.mView.setBackground((Drawable)var1);
         }
      } else {
         var1 = this.focusFgDrawable;
         if (var1 != null) {
            this.mView.setForeground((Drawable)var1);
         }

         var1 = this.pressedDrawable;
         if (var1 != null) {
            this.mView.setBackground((Drawable)var1);
         }
      }

   }

   public final void setFocusFg(int var1) {
      this.focusFg = var1;
   }

   public final void setFocusFgDrawable(PressedDrawable var1) {
      this.focusFgDrawable = var1;
   }

   public final void setFocusId(int var1) {
      this.focusId = var1;
   }

   public final void setPressedDrawable(PressedDrawable var1) {
      this.pressedDrawable = var1;
   }

   public final void setPressedDrawable1(PressedDrawable var1) {
      this.pressedDrawable1 = var1;
   }
}
