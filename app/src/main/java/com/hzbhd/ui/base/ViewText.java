package com.hzbhd.ui.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u0006\u0010\u001b\u001a\u00020\u0019J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0019R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "},
   d2 = {"Lcom/hzbhd/ui/base/ViewText;", "Lcom/hzbhd/ui/base/BhdViewUtil$ViewUtilListener;", "view", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/widget/TextView;Landroid/content/Context;Landroid/util/AttributeSet;)V", "color", "Landroid/content/res/ColorStateList;", "color1", "color_d", "", "color_d1", "color_n", "color_n1", "color_p", "color_p1", "color_s", "color_s1", "mView", "shadowColor", "shadowColor1", "onAttachedToWindow", "", "onColorChange", "onDetachedFromWindow", "onFocusChange", "isFocus", "", "resetTextColor", "bhdview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public final class ViewText implements BhdViewUtil.ViewUtilListener {
   private ColorStateList color;
   private ColorStateList color1;
   private final int color_d;
   private final int color_d1;
   private final int color_n;
   private final int color_n1;
   private final int color_p;
   private final int color_p1;
   private final int color_s;
   private final int color_s1;
   private final TextView mView;
   private final int shadowColor;
   private final int shadowColor1;

   public ViewText(TextView var1, Context var2, AttributeSet var3) {
      Intrinsics.checkNotNullParameter(var1, "view");
      Intrinsics.checkNotNullParameter(var2, "context");
      Intrinsics.checkNotNullParameter(var3, "attrs");
      super();
      this.mView = var1;
      TypedArray var10 = var2.obtainStyledAttributes(var3, R.styleable.text_color_attr);
      int var5 = var10.getColor(R.styleable.text_color_attr_textColor_n, 0);
      this.color_n = var5;
      int var7 = var10.getColor(R.styleable.text_color_attr_textColor_p, var5);
      this.color_p = var7;
      int var6 = var10.getColor(R.styleable.text_color_attr_textColor_s, var5);
      this.color_s = var6;
      int var4 = var10.getColor(R.styleable.text_color_attr_textColor_d, var5);
      this.color_d = var4;
      int[] var8;
      int[] var11;
      int[] var12;
      if (var5 != 0) {
         var12 = new int[]{16842919};
         var11 = new int[]{-16842910};
         var8 = new int[]{-16842913};
         this.color = new ColorStateList(new int[][]{{16842913}, var12, var11, {-16842919}, var8}, new int[]{var6, var7, var4, var5, var5});
      }

      var4 = var10.getColor(R.styleable.text_color_attr_textColor_n1, 0);
      this.color_n1 = var4;
      var6 = var10.getColor(R.styleable.text_color_attr_textColor_p1, var4);
      this.color_p1 = var6;
      var7 = var10.getColor(R.styleable.text_color_attr_textColor_s1, var4);
      this.color_s1 = var7;
      var5 = var10.getColor(R.styleable.text_color_attr_textColor_d1, var4);
      this.color_d1 = var5;
      if (var4 != 0) {
         int[] var9 = new int[]{16842913};
         var12 = new int[]{16842919};
         var11 = new int[]{-16842919};
         var8 = new int[]{-16842913};
         this.color1 = new ColorStateList(new int[][]{var9, var12, {-16842910}, var11, var8}, new int[]{var7, var6, var5, var4, var4});
      }

      this.shadowColor = var10.getColor(R.styleable.text_color_attr_shadowColor, 0);
      this.shadowColor1 = var10.getColor(R.styleable.text_color_attr_shadowColor1, 0);
      var10.recycle();
   }

   public final void onAttachedToWindow() {
      if (this.color_n1 != 0) {
         BhdViewUtil.addListener$default(BhdViewUtil.INSTANCE, (BhdViewUtil.ViewUtilListener)this, 0, 2, (Object)null);
      }

   }

   public void onColorChange() {
      this.resetTextColor();
   }

   public final void onDetachedFromWindow() {
      if (this.color_n1 != 0) {
         BhdViewUtil.removeListener$default(BhdViewUtil.INSTANCE, (BhdViewUtil.ViewUtilListener)this, 0, 2, (Object)null);
      }

   }

   public void onFocusChange(boolean var1) {
   }

   public final void resetTextColor() {
      int var1;
      ColorStateList var2;
      if (BhdViewUtil.INSTANCE.getColorStyle() == 1) {
         var2 = this.color1;
         if (var2 != null) {
            this.mView.setTextColor(var2);
         }

         var1 = this.shadowColor1;
         if (var1 != 0) {
            this.mView.setShadowLayer(5.0F, 0.0F, 2.0F, var1);
         }
      } else {
         var2 = this.color;
         if (var2 != null) {
            this.mView.setTextColor(var2);
         }

         var1 = this.shadowColor;
         if (var1 != 0) {
            this.mView.setShadowLayer(5.0F, 0.0F, 2.0F, var1);
         }
      }

   }
}
