package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.view.View;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.internal.CircularBorderDrawable;
import com.google.android.material.internal.CircularBorderDrawableLollipop;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowDrawableWrapper;
import com.google.android.material.shadow.ShadowViewDelegate;
import java.util.ArrayList;

class FloatingActionButtonImplLollipop extends FloatingActionButtonImpl {
   private InsetDrawable insetDrawable;

   FloatingActionButtonImplLollipop(VisibilityAwareImageButton var1, ShadowViewDelegate var2) {
      super(var1, var2);
   }

   private Animator createElevationAnimator(float var1, float var2) {
      AnimatorSet var3 = new AnimatorSet();
      var3.play(ObjectAnimator.ofFloat(this.view, "elevation", new float[]{var1}).setDuration(0L)).with(ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Z, new float[]{var2}).setDuration(100L));
      var3.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
      return var3;
   }

   public float getElevation() {
      return this.view.getElevation();
   }

   void getPadding(Rect var1) {
      if (this.shadowViewDelegate.isCompatPaddingEnabled()) {
         float var2 = this.shadowViewDelegate.getRadius();
         float var3 = this.getElevation() + this.pressedTranslationZ;
         int var4 = (int)Math.ceil((double)ShadowDrawableWrapper.calculateHorizontalPadding(var3, var2, false));
         int var5 = (int)Math.ceil((double)ShadowDrawableWrapper.calculateVerticalPadding(var3, var2, false));
         var1.set(var4, var5, var4, var5);
      } else {
         var1.set(0, 0, 0, 0);
      }

   }

   void jumpDrawableToCurrentState() {
   }

   CircularBorderDrawable newCircularDrawable() {
      return new CircularBorderDrawableLollipop();
   }

   GradientDrawable newGradientDrawableForShape() {
      return new AlwaysStatefulGradientDrawable();
   }

   void onCompatShadowChanged() {
      this.updatePadding();
   }

   void onDrawableStateChanged(int[] var1) {
      if (VERSION.SDK_INT == 21) {
         if (this.view.isEnabled()) {
            this.view.setElevation(this.elevation);
            if (this.view.isPressed()) {
               this.view.setTranslationZ(this.pressedTranslationZ);
            } else if (!this.view.isFocused() && !this.view.isHovered()) {
               this.view.setTranslationZ(0.0F);
            } else {
               this.view.setTranslationZ(this.hoveredFocusedTranslationZ);
            }
         } else {
            this.view.setElevation(0.0F);
            this.view.setTranslationZ(0.0F);
         }
      }

   }

   void onElevationsChanged(float var1, float var2, float var3) {
      if (VERSION.SDK_INT == 21) {
         this.view.refreshDrawableState();
      } else {
         StateListAnimator var6 = new StateListAnimator();
         var6.addState(PRESSED_ENABLED_STATE_SET, this.createElevationAnimator(var1, var3));
         var6.addState(HOVERED_FOCUSED_ENABLED_STATE_SET, this.createElevationAnimator(var1, var2));
         var6.addState(FOCUSED_ENABLED_STATE_SET, this.createElevationAnimator(var1, var2));
         var6.addState(HOVERED_ENABLED_STATE_SET, this.createElevationAnimator(var1, var2));
         AnimatorSet var5 = new AnimatorSet();
         ArrayList var4 = new ArrayList();
         var4.add(ObjectAnimator.ofFloat(this.view, "elevation", new float[]{var1}).setDuration(0L));
         if (VERSION.SDK_INT >= 22 && VERSION.SDK_INT <= 24) {
            var4.add(ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Z, new float[]{this.view.getTranslationZ()}).setDuration(100L));
         }

         var4.add(ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Z, new float[]{0.0F}).setDuration(100L));
         var5.playSequentially((Animator[])var4.toArray(new Animator[0]));
         var5.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
         var6.addState(ENABLED_STATE_SET, var5);
         var6.addState(EMPTY_STATE_SET, this.createElevationAnimator(0.0F, 0.0F));
         this.view.setStateListAnimator(var6);
      }

      if (this.shadowViewDelegate.isCompatPaddingEnabled()) {
         this.updatePadding();
      }

   }

   void onPaddingUpdated(Rect var1) {
      if (this.shadowViewDelegate.isCompatPaddingEnabled()) {
         this.insetDrawable = new InsetDrawable(this.rippleDrawable, var1.left, var1.top, var1.right, var1.bottom);
         this.shadowViewDelegate.setBackgroundDrawable(this.insetDrawable);
      } else {
         this.shadowViewDelegate.setBackgroundDrawable(this.rippleDrawable);
      }

   }

   boolean requirePreDrawListener() {
      return false;
   }

   void setBackgroundDrawable(ColorStateList var1, PorterDuff.Mode var2, ColorStateList var3, int var4) {
      this.shapeDrawable = DrawableCompat.wrap(this.createShapeDrawable());
      DrawableCompat.setTintList(this.shapeDrawable, var1);
      if (var2 != null) {
         DrawableCompat.setTintMode(this.shapeDrawable, var2);
      }

      Object var5;
      if (var4 > 0) {
         this.borderDrawable = this.createBorderDrawable(var4, var1);
         var5 = new LayerDrawable(new Drawable[]{this.borderDrawable, this.shapeDrawable});
      } else {
         this.borderDrawable = null;
         var5 = this.shapeDrawable;
      }

      this.rippleDrawable = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(var3), (Drawable)var5, (Drawable)null);
      this.contentBackground = this.rippleDrawable;
      this.shadowViewDelegate.setBackgroundDrawable(this.rippleDrawable);
   }

   void setRippleColor(ColorStateList var1) {
      if (this.rippleDrawable instanceof RippleDrawable) {
         ((RippleDrawable)this.rippleDrawable).setColor(RippleUtils.convertToRippleDrawableColor(var1));
      } else {
         super.setRippleColor(var1);
      }

   }

   static class AlwaysStatefulGradientDrawable extends GradientDrawable {
      public boolean isStateful() {
         return true;
      }
   }
}
