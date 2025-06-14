package com.google.android.material.internal;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;

public class CircularBorderDrawable extends Drawable {
   private static final float DRAW_STROKE_WIDTH_MULTIPLE = 1.3333F;
   private ColorStateList borderTint;
   float borderWidth;
   private int bottomInnerStrokeColor;
   private int bottomOuterStrokeColor;
   private int currentBorderTintColor;
   private boolean invalidateShader = true;
   final Paint paint;
   final Rect rect = new Rect();
   final RectF rectF = new RectF();
   private float rotation;
   final CircularBorderState state = new CircularBorderState(this);
   private int topInnerStrokeColor;
   private int topOuterStrokeColor;

   public CircularBorderDrawable() {
      Paint var1 = new Paint(1);
      this.paint = var1;
      var1.setStyle(Style.STROKE);
   }

   private Shader createGradientShader() {
      Rect var10 = this.rect;
      this.copyBounds(var10);
      float var1 = this.borderWidth / (float)var10.height();
      int var5 = ColorUtils.compositeColors(this.topOuterStrokeColor, this.currentBorderTintColor);
      int var4 = ColorUtils.compositeColors(this.topInnerStrokeColor, this.currentBorderTintColor);
      int var6 = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.topInnerStrokeColor, 0), this.currentBorderTintColor);
      int var9 = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.bottomInnerStrokeColor, 0), this.currentBorderTintColor);
      int var7 = ColorUtils.compositeColors(this.bottomInnerStrokeColor, this.currentBorderTintColor);
      int var8 = ColorUtils.compositeColors(this.bottomOuterStrokeColor, this.currentBorderTintColor);
      float var2 = (float)var10.top;
      float var3 = (float)var10.bottom;
      Shader.TileMode var11 = TileMode.CLAMP;
      return new LinearGradient(0.0F, var2, 0.0F, var3, new int[]{var5, var4, var6, var9, var7, var8}, new float[]{0.0F, var1, 0.5F, 0.5F, 1.0F - var1, 1.0F}, var11);
   }

   public void draw(Canvas var1) {
      if (this.invalidateShader) {
         this.paint.setShader(this.createGradientShader());
         this.invalidateShader = false;
      }

      float var2 = this.paint.getStrokeWidth() / 2.0F;
      RectF var3 = this.rectF;
      this.copyBounds(this.rect);
      var3.set(this.rect);
      var3.left += var2;
      var3.top += var2;
      var3.right -= var2;
      var3.bottom -= var2;
      var1.save();
      var1.rotate(this.rotation, var3.centerX(), var3.centerY());
      var1.drawOval(var3, this.paint);
      var1.restore();
   }

   public Drawable.ConstantState getConstantState() {
      return this.state;
   }

   public int getOpacity() {
      byte var1;
      if (this.borderWidth > 0.0F) {
         var1 = -3;
      } else {
         var1 = -2;
      }

      return var1;
   }

   public boolean getPadding(Rect var1) {
      int var2 = Math.round(this.borderWidth);
      var1.set(var2, var2, var2, var2);
      return true;
   }

   public boolean isStateful() {
      ColorStateList var2 = this.borderTint;
      boolean var1;
      if ((var2 == null || !var2.isStateful()) && !super.isStateful()) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   protected void onBoundsChange(Rect var1) {
      this.invalidateShader = true;
   }

   protected boolean onStateChange(int[] var1) {
      ColorStateList var3 = this.borderTint;
      if (var3 != null) {
         int var2 = var3.getColorForState(var1, this.currentBorderTintColor);
         if (var2 != this.currentBorderTintColor) {
            this.invalidateShader = true;
            this.currentBorderTintColor = var2;
         }
      }

      if (this.invalidateShader) {
         this.invalidateSelf();
      }

      return this.invalidateShader;
   }

   public void setAlpha(int var1) {
      this.paint.setAlpha(var1);
      this.invalidateSelf();
   }

   public void setBorderTint(ColorStateList var1) {
      if (var1 != null) {
         this.currentBorderTintColor = var1.getColorForState(this.getState(), this.currentBorderTintColor);
      }

      this.borderTint = var1;
      this.invalidateShader = true;
      this.invalidateSelf();
   }

   public void setBorderWidth(float var1) {
      if (this.borderWidth != var1) {
         this.borderWidth = var1;
         this.paint.setStrokeWidth(var1 * 1.3333F);
         this.invalidateShader = true;
         this.invalidateSelf();
      }

   }

   public void setColorFilter(ColorFilter var1) {
      this.paint.setColorFilter(var1);
      this.invalidateSelf();
   }

   public void setGradientColors(int var1, int var2, int var3, int var4) {
      this.topOuterStrokeColor = var1;
      this.topInnerStrokeColor = var2;
      this.bottomOuterStrokeColor = var3;
      this.bottomInnerStrokeColor = var4;
   }

   public final void setRotation(float var1) {
      if (var1 != this.rotation) {
         this.rotation = var1;
         this.invalidateSelf();
      }

   }

   private class CircularBorderState extends Drawable.ConstantState {
      final CircularBorderDrawable this$0;

      private CircularBorderState(CircularBorderDrawable var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      CircularBorderState(CircularBorderDrawable var1, Object var2) {
         this(var1);
      }

      public int getChangingConfigurations() {
         return 0;
      }

      public Drawable newDrawable() {
         return this.this$0;
      }
   }
}
