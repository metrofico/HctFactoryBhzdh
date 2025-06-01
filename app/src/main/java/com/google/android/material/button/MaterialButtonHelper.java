package com.google.android.material.button;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;

class MaterialButtonHelper {
   private static final float CORNER_RADIUS_ADJUSTMENT = 1.0E-5F;
   private static final int DEFAULT_BACKGROUND_COLOR = -1;
   private static final boolean IS_LOLLIPOP;
   private GradientDrawable backgroundDrawableLollipop;
   private boolean backgroundOverwritten = false;
   private ColorStateList backgroundTint;
   private PorterDuff.Mode backgroundTintMode;
   private final Rect bounds = new Rect();
   private final Paint buttonStrokePaint = new Paint(1);
   private GradientDrawable colorableBackgroundDrawableCompat;
   private int cornerRadius;
   private int insetBottom;
   private int insetLeft;
   private int insetRight;
   private int insetTop;
   private GradientDrawable maskDrawableLollipop;
   private final MaterialButton materialButton;
   private final RectF rectF = new RectF();
   private ColorStateList rippleColor;
   private GradientDrawable rippleDrawableCompat;
   private ColorStateList strokeColor;
   private GradientDrawable strokeDrawableLollipop;
   private int strokeWidth;
   private Drawable tintableBackgroundDrawableCompat;
   private Drawable tintableRippleDrawableCompat;

   static {
      boolean var0;
      if (VERSION.SDK_INT >= 21) {
         var0 = true;
      } else {
         var0 = false;
      }

      IS_LOLLIPOP = var0;
   }

   public MaterialButtonHelper(MaterialButton var1) {
      this.materialButton = var1;
   }

   private Drawable createBackgroundCompat() {
      GradientDrawable var1 = new GradientDrawable();
      this.colorableBackgroundDrawableCompat = var1;
      var1.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
      this.colorableBackgroundDrawableCompat.setColor(-1);
      Drawable var2 = DrawableCompat.wrap(this.colorableBackgroundDrawableCompat);
      this.tintableBackgroundDrawableCompat = var2;
      DrawableCompat.setTintList(var2, this.backgroundTint);
      PorterDuff.Mode var3 = this.backgroundTintMode;
      if (var3 != null) {
         DrawableCompat.setTintMode(this.tintableBackgroundDrawableCompat, var3);
      }

      var1 = new GradientDrawable();
      this.rippleDrawableCompat = var1;
      var1.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
      this.rippleDrawableCompat.setColor(-1);
      var2 = DrawableCompat.wrap(this.rippleDrawableCompat);
      this.tintableRippleDrawableCompat = var2;
      DrawableCompat.setTintList(var2, this.rippleColor);
      return this.wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.tintableBackgroundDrawableCompat, this.tintableRippleDrawableCompat}));
   }

   private Drawable createBackgroundLollipop() {
      GradientDrawable var1 = new GradientDrawable();
      this.backgroundDrawableLollipop = var1;
      var1.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
      this.backgroundDrawableLollipop.setColor(-1);
      this.updateTintAndTintModeLollipop();
      var1 = new GradientDrawable();
      this.strokeDrawableLollipop = var1;
      var1.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
      this.strokeDrawableLollipop.setColor(0);
      this.strokeDrawableLollipop.setStroke(this.strokeWidth, this.strokeColor);
      InsetDrawable var3 = this.wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.backgroundDrawableLollipop, this.strokeDrawableLollipop}));
      GradientDrawable var2 = new GradientDrawable();
      this.maskDrawableLollipop = var2;
      var2.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
      this.maskDrawableLollipop.setColor(-1);
      return new MaterialButtonBackgroundDrawable(RippleUtils.convertToRippleDrawableColor(this.rippleColor), var3, this.maskDrawableLollipop);
   }

   private GradientDrawable unwrapBackgroundDrawable() {
      return IS_LOLLIPOP && this.materialButton.getBackground() != null ? (GradientDrawable)((LayerDrawable)((InsetDrawable)((RippleDrawable)this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(0) : null;
   }

   private GradientDrawable unwrapStrokeDrawable() {
      return IS_LOLLIPOP && this.materialButton.getBackground() != null ? (GradientDrawable)((LayerDrawable)((InsetDrawable)((RippleDrawable)this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(1) : null;
   }

   private void updateStroke() {
      boolean var1 = IS_LOLLIPOP;
      if (var1 && this.strokeDrawableLollipop != null) {
         this.materialButton.setInternalBackground(this.createBackgroundLollipop());
      } else if (!var1) {
         this.materialButton.invalidate();
      }

   }

   private void updateTintAndTintModeLollipop() {
      GradientDrawable var1 = this.backgroundDrawableLollipop;
      if (var1 != null) {
         DrawableCompat.setTintList(var1, this.backgroundTint);
         PorterDuff.Mode var2 = this.backgroundTintMode;
         if (var2 != null) {
            DrawableCompat.setTintMode(this.backgroundDrawableLollipop, var2);
         }
      }

   }

   private InsetDrawable wrapDrawableWithInset(Drawable var1) {
      return new InsetDrawable(var1, this.insetLeft, this.insetTop, this.insetRight, this.insetBottom);
   }

   void drawStroke(Canvas var1) {
      if (var1 != null && this.strokeColor != null && this.strokeWidth > 0) {
         this.bounds.set(this.materialButton.getBackground().getBounds());
         this.rectF.set((float)this.bounds.left + (float)this.strokeWidth / 2.0F + (float)this.insetLeft, (float)this.bounds.top + (float)this.strokeWidth / 2.0F + (float)this.insetTop, (float)this.bounds.right - (float)this.strokeWidth / 2.0F - (float)this.insetRight, (float)this.bounds.bottom - (float)this.strokeWidth / 2.0F - (float)this.insetBottom);
         float var2 = (float)this.cornerRadius - (float)this.strokeWidth / 2.0F;
         var1.drawRoundRect(this.rectF, var2, var2, this.buttonStrokePaint);
      }

   }

   int getCornerRadius() {
      return this.cornerRadius;
   }

   ColorStateList getRippleColor() {
      return this.rippleColor;
   }

   ColorStateList getStrokeColor() {
      return this.strokeColor;
   }

   int getStrokeWidth() {
      return this.strokeWidth;
   }

   ColorStateList getSupportBackgroundTintList() {
      return this.backgroundTint;
   }

   PorterDuff.Mode getSupportBackgroundTintMode() {
      return this.backgroundTintMode;
   }

   boolean isBackgroundOverwritten() {
      return this.backgroundOverwritten;
   }

   public void loadFromAttributes(TypedArray var1) {
      int var3 = R.styleable.MaterialButton_android_insetLeft;
      int var2 = 0;
      this.insetLeft = var1.getDimensionPixelOffset(var3, 0);
      this.insetRight = var1.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
      this.insetTop = var1.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
      this.insetBottom = var1.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
      this.cornerRadius = var1.getDimensionPixelSize(R.styleable.MaterialButton_cornerRadius, 0);
      this.strokeWidth = var1.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
      this.backgroundTintMode = ViewUtils.parseTintMode(var1.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), Mode.SRC_IN);
      this.backgroundTint = MaterialResources.getColorStateList(this.materialButton.getContext(), var1, R.styleable.MaterialButton_backgroundTint);
      this.strokeColor = MaterialResources.getColorStateList(this.materialButton.getContext(), var1, R.styleable.MaterialButton_strokeColor);
      this.rippleColor = MaterialResources.getColorStateList(this.materialButton.getContext(), var1, R.styleable.MaterialButton_rippleColor);
      this.buttonStrokePaint.setStyle(Style.STROKE);
      this.buttonStrokePaint.setStrokeWidth((float)this.strokeWidth);
      Paint var7 = this.buttonStrokePaint;
      ColorStateList var6 = this.strokeColor;
      if (var6 != null) {
         var2 = var6.getColorForState(this.materialButton.getDrawableState(), 0);
      }

      var7.setColor(var2);
      var2 = ViewCompat.getPaddingStart(this.materialButton);
      int var4 = this.materialButton.getPaddingTop();
      var3 = ViewCompat.getPaddingEnd(this.materialButton);
      int var5 = this.materialButton.getPaddingBottom();
      MaterialButton var9 = this.materialButton;
      Drawable var8;
      if (IS_LOLLIPOP) {
         var8 = this.createBackgroundLollipop();
      } else {
         var8 = this.createBackgroundCompat();
      }

      var9.setInternalBackground(var8);
      ViewCompat.setPaddingRelative(this.materialButton, var2 + this.insetLeft, var4 + this.insetTop, var3 + this.insetRight, var5 + this.insetBottom);
   }

   void setBackgroundColor(int var1) {
      boolean var2 = IS_LOLLIPOP;
      GradientDrawable var3;
      if (var2) {
         var3 = this.backgroundDrawableLollipop;
         if (var3 != null) {
            var3.setColor(var1);
            return;
         }
      }

      if (!var2) {
         var3 = this.colorableBackgroundDrawableCompat;
         if (var3 != null) {
            var3.setColor(var1);
         }
      }

   }

   void setBackgroundOverwritten() {
      this.backgroundOverwritten = true;
      this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
      this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
   }

   void setCornerRadius(int var1) {
      if (this.cornerRadius != var1) {
         this.cornerRadius = var1;
         boolean var3 = IS_LOLLIPOP;
         float var2;
         GradientDrawable var4;
         if (var3 && this.backgroundDrawableLollipop != null && this.strokeDrawableLollipop != null && this.maskDrawableLollipop != null) {
            if (VERSION.SDK_INT == 21) {
               var4 = this.unwrapBackgroundDrawable();
               var2 = (float)var1 + 1.0E-5F;
               var4.setCornerRadius(var2);
               this.unwrapStrokeDrawable().setCornerRadius(var2);
            }

            var4 = this.backgroundDrawableLollipop;
            var2 = (float)var1 + 1.0E-5F;
            var4.setCornerRadius(var2);
            this.strokeDrawableLollipop.setCornerRadius(var2);
            this.maskDrawableLollipop.setCornerRadius(var2);
         } else if (!var3) {
            var4 = this.colorableBackgroundDrawableCompat;
            if (var4 != null && this.rippleDrawableCompat != null) {
               var2 = (float)var1 + 1.0E-5F;
               var4.setCornerRadius(var2);
               this.rippleDrawableCompat.setCornerRadius(var2);
               this.materialButton.invalidate();
            }
         }
      }

   }

   void setRippleColor(ColorStateList var1) {
      if (this.rippleColor != var1) {
         this.rippleColor = var1;
         boolean var2 = IS_LOLLIPOP;
         if (var2 && this.materialButton.getBackground() instanceof RippleDrawable) {
            ((RippleDrawable)this.materialButton.getBackground()).setColor(var1);
         } else if (!var2) {
            Drawable var3 = this.tintableRippleDrawableCompat;
            if (var3 != null) {
               DrawableCompat.setTintList(var3, var1);
            }
         }
      }

   }

   void setStrokeColor(ColorStateList var1) {
      if (this.strokeColor != var1) {
         this.strokeColor = var1;
         Paint var3 = this.buttonStrokePaint;
         int var2 = 0;
         if (var1 != null) {
            var2 = var1.getColorForState(this.materialButton.getDrawableState(), 0);
         }

         var3.setColor(var2);
         this.updateStroke();
      }

   }

   void setStrokeWidth(int var1) {
      if (this.strokeWidth != var1) {
         this.strokeWidth = var1;
         this.buttonStrokePaint.setStrokeWidth((float)var1);
         this.updateStroke();
      }

   }

   void setSupportBackgroundTintList(ColorStateList var1) {
      if (this.backgroundTint != var1) {
         this.backgroundTint = var1;
         if (IS_LOLLIPOP) {
            this.updateTintAndTintModeLollipop();
         } else {
            Drawable var2 = this.tintableBackgroundDrawableCompat;
            if (var2 != null) {
               DrawableCompat.setTintList(var2, var1);
            }
         }
      }

   }

   void setSupportBackgroundTintMode(PorterDuff.Mode var1) {
      if (this.backgroundTintMode != var1) {
         this.backgroundTintMode = var1;
         if (IS_LOLLIPOP) {
            this.updateTintAndTintModeLollipop();
         } else {
            Drawable var2 = this.tintableBackgroundDrawableCompat;
            if (var2 != null && var1 != null) {
               DrawableCompat.setTintMode(var2, var1);
            }
         }
      }

   }

   void updateMaskBounds(int var1, int var2) {
      GradientDrawable var3 = this.maskDrawableLollipop;
      if (var3 != null) {
         var3.setBounds(this.insetLeft, this.insetTop, var2 - this.insetRight, var1 - this.insetBottom);
      }

   }
}
