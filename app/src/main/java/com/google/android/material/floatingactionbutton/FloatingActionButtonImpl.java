package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MatrixEvaluator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.internal.CircularBorderDrawable;
import com.google.android.material.internal.StateListAnimator;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowDrawableWrapper;
import com.google.android.material.shadow.ShadowViewDelegate;
import java.util.ArrayList;
import java.util.Iterator;

class FloatingActionButtonImpl {
   static final int ANIM_STATE_HIDING = 1;
   static final int ANIM_STATE_NONE = 0;
   static final int ANIM_STATE_SHOWING = 2;
   static final long ELEVATION_ANIM_DELAY = 100L;
   static final long ELEVATION_ANIM_DURATION = 100L;
   static final TimeInterpolator ELEVATION_ANIM_INTERPOLATOR;
   static final int[] EMPTY_STATE_SET;
   static final int[] ENABLED_STATE_SET;
   static final int[] FOCUSED_ENABLED_STATE_SET;
   private static final float HIDE_ICON_SCALE = 0.0F;
   private static final float HIDE_OPACITY = 0.0F;
   private static final float HIDE_SCALE = 0.0F;
   static final int[] HOVERED_ENABLED_STATE_SET;
   static final int[] HOVERED_FOCUSED_ENABLED_STATE_SET;
   static final int[] PRESSED_ENABLED_STATE_SET;
   private static final float SHOW_ICON_SCALE = 1.0F;
   private static final float SHOW_OPACITY = 1.0F;
   private static final float SHOW_SCALE = 1.0F;
   int animState = 0;
   CircularBorderDrawable borderDrawable;
   Drawable contentBackground;
   Animator currentAnimator;
   private MotionSpec defaultHideMotionSpec;
   private MotionSpec defaultShowMotionSpec;
   float elevation;
   private ArrayList hideListeners;
   MotionSpec hideMotionSpec;
   float hoveredFocusedTranslationZ;
   float imageMatrixScale = 1.0F;
   int maxImageSize;
   private ViewTreeObserver.OnPreDrawListener preDrawListener;
   float pressedTranslationZ;
   Drawable rippleDrawable;
   private float rotation;
   ShadowDrawableWrapper shadowDrawable;
   final ShadowViewDelegate shadowViewDelegate;
   Drawable shapeDrawable;
   private ArrayList showListeners;
   MotionSpec showMotionSpec;
   private final StateListAnimator stateListAnimator;
   private final Matrix tmpMatrix = new Matrix();
   private final Rect tmpRect = new Rect();
   private final RectF tmpRectF1 = new RectF();
   private final RectF tmpRectF2 = new RectF();
   final VisibilityAwareImageButton view;

   static {
      ELEVATION_ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
      PRESSED_ENABLED_STATE_SET = new int[]{16842919, 16842910};
      HOVERED_FOCUSED_ENABLED_STATE_SET = new int[]{16843623, 16842908, 16842910};
      FOCUSED_ENABLED_STATE_SET = new int[]{16842908, 16842910};
      HOVERED_ENABLED_STATE_SET = new int[]{16843623, 16842910};
      ENABLED_STATE_SET = new int[]{16842910};
      EMPTY_STATE_SET = new int[0];
   }

   FloatingActionButtonImpl(VisibilityAwareImageButton var1, ShadowViewDelegate var2) {
      this.view = var1;
      this.shadowViewDelegate = var2;
      StateListAnimator var3 = new StateListAnimator();
      this.stateListAnimator = var3;
      var3.addState(PRESSED_ENABLED_STATE_SET, this.createElevationAnimator(new ElevateToPressedTranslationZAnimation(this)));
      var3.addState(HOVERED_FOCUSED_ENABLED_STATE_SET, this.createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation(this)));
      var3.addState(FOCUSED_ENABLED_STATE_SET, this.createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation(this)));
      var3.addState(HOVERED_ENABLED_STATE_SET, this.createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation(this)));
      var3.addState(ENABLED_STATE_SET, this.createElevationAnimator(new ResetElevationAnimation(this)));
      var3.addState(EMPTY_STATE_SET, this.createElevationAnimator(new DisabledElevationAnimation(this)));
      this.rotation = var1.getRotation();
   }

   private void calculateImageMatrixFromScale(float var1, Matrix var2) {
      var2.reset();
      Drawable var5 = this.view.getDrawable();
      if (var5 != null && this.maxImageSize != 0) {
         RectF var4 = this.tmpRectF1;
         RectF var6 = this.tmpRectF2;
         var4.set(0.0F, 0.0F, (float)var5.getIntrinsicWidth(), (float)var5.getIntrinsicHeight());
         int var3 = this.maxImageSize;
         var6.set(0.0F, 0.0F, (float)var3, (float)var3);
         var2.setRectToRect(var4, var6, ScaleToFit.CENTER);
         var3 = this.maxImageSize;
         var2.postScale(var1, var1, (float)var3 / 2.0F, (float)var3 / 2.0F);
      }

   }

   private AnimatorSet createAnimator(MotionSpec var1, float var2, float var3, float var4) {
      ArrayList var5 = new ArrayList();
      ObjectAnimator var6 = ObjectAnimator.ofFloat(this.view, View.ALPHA, new float[]{var2});
      var1.getTiming("opacity").apply(var6);
      var5.add(var6);
      var6 = ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{var3});
      var1.getTiming("scale").apply(var6);
      var5.add(var6);
      var6 = ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{var3});
      var1.getTiming("scale").apply(var6);
      var5.add(var6);
      this.calculateImageMatrixFromScale(var4, this.tmpMatrix);
      var6 = ObjectAnimator.ofObject(this.view, new ImageMatrixProperty(), new MatrixEvaluator(), new Matrix[]{new Matrix(this.tmpMatrix)});
      var1.getTiming("iconScale").apply(var6);
      var5.add(var6);
      AnimatorSet var7 = new AnimatorSet();
      AnimatorSetCompat.playTogether(var7, var5);
      return var7;
   }

   private ValueAnimator createElevationAnimator(ShadowAnimatorImpl var1) {
      ValueAnimator var2 = new ValueAnimator();
      var2.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
      var2.setDuration(100L);
      var2.addListener(var1);
      var2.addUpdateListener(var1);
      var2.setFloatValues(new float[]{0.0F, 1.0F});
      return var2;
   }

   private void ensurePreDrawListener() {
      if (this.preDrawListener == null) {
         this.preDrawListener = new ViewTreeObserver.OnPreDrawListener(this) {
            final FloatingActionButtonImpl this$0;

            {
               this.this$0 = var1;
            }

            public boolean onPreDraw() {
               this.this$0.onPreDraw();
               return true;
            }
         };
      }

   }

   private MotionSpec getDefaultHideMotionSpec() {
      if (this.defaultHideMotionSpec == null) {
         this.defaultHideMotionSpec = MotionSpec.createFromResource(this.view.getContext(), R.animator.design_fab_hide_motion_spec);
      }

      return this.defaultHideMotionSpec;
   }

   private MotionSpec getDefaultShowMotionSpec() {
      if (this.defaultShowMotionSpec == null) {
         this.defaultShowMotionSpec = MotionSpec.createFromResource(this.view.getContext(), R.animator.design_fab_show_motion_spec);
      }

      return this.defaultShowMotionSpec;
   }

   private boolean shouldAnimateVisibilityChange() {
      boolean var1;
      if (ViewCompat.isLaidOut(this.view) && !this.view.isInEditMode()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void updateFromViewRotation() {
      if (VERSION.SDK_INT == 19) {
         if (this.rotation % 90.0F != 0.0F) {
            if (this.view.getLayerType() != 1) {
               this.view.setLayerType(1, (Paint)null);
            }
         } else if (this.view.getLayerType() != 0) {
            this.view.setLayerType(0, (Paint)null);
         }
      }

      ShadowDrawableWrapper var1 = this.shadowDrawable;
      if (var1 != null) {
         var1.setRotation(-this.rotation);
      }

      CircularBorderDrawable var2 = this.borderDrawable;
      if (var2 != null) {
         var2.setRotation(-this.rotation);
      }

   }

   public void addOnHideAnimationListener(Animator.AnimatorListener var1) {
      if (this.hideListeners == null) {
         this.hideListeners = new ArrayList();
      }

      this.hideListeners.add(var1);
   }

   void addOnShowAnimationListener(Animator.AnimatorListener var1) {
      if (this.showListeners == null) {
         this.showListeners = new ArrayList();
      }

      this.showListeners.add(var1);
   }

   CircularBorderDrawable createBorderDrawable(int var1, ColorStateList var2) {
      Context var3 = this.view.getContext();
      CircularBorderDrawable var4 = this.newCircularDrawable();
      var4.setGradientColors(ContextCompat.getColor(var3, R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(var3, R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(var3, R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(var3, R.color.design_fab_stroke_end_outer_color));
      var4.setBorderWidth((float)var1);
      var4.setBorderTint(var2);
      return var4;
   }

   GradientDrawable createShapeDrawable() {
      GradientDrawable var1 = this.newGradientDrawableForShape();
      var1.setShape(1);
      var1.setColor(-1);
      return var1;
   }

   final Drawable getContentBackground() {
      return this.contentBackground;
   }

   float getElevation() {
      return this.elevation;
   }

   final MotionSpec getHideMotionSpec() {
      return this.hideMotionSpec;
   }

   float getHoveredFocusedTranslationZ() {
      return this.hoveredFocusedTranslationZ;
   }

   void getPadding(Rect var1) {
      this.shadowDrawable.getPadding(var1);
   }

   float getPressedTranslationZ() {
      return this.pressedTranslationZ;
   }

   final MotionSpec getShowMotionSpec() {
      return this.showMotionSpec;
   }

   void hide(InternalVisibilityChangedListener var1, boolean var2) {
      if (!this.isOrWillBeHidden()) {
         Animator var4 = this.currentAnimator;
         if (var4 != null) {
            var4.cancel();
         }

         if (this.shouldAnimateVisibilityChange()) {
            MotionSpec var7 = this.hideMotionSpec;
            if (var7 == null) {
               var7 = this.getDefaultHideMotionSpec();
            }

            AnimatorSet var8 = this.createAnimator(var7, 0.0F, 0.0F, 0.0F);
            var8.addListener(new AnimatorListenerAdapter(this, var2, var1) {
               private boolean cancelled;
               final FloatingActionButtonImpl this$0;
               final boolean val$fromUser;
               final InternalVisibilityChangedListener val$listener;

               {
                  this.this$0 = var1;
                  this.val$fromUser = var2;
                  this.val$listener = var3;
               }

               public void onAnimationCancel(Animator var1) {
                  this.cancelled = true;
               }

               public void onAnimationEnd(Animator var1) {
                  this.this$0.animState = 0;
                  this.this$0.currentAnimator = null;
                  if (!this.cancelled) {
                     VisibilityAwareImageButton var4 = this.this$0.view;
                     boolean var3 = this.val$fromUser;
                     byte var2;
                     if (var3) {
                        var2 = 8;
                     } else {
                        var2 = 4;
                     }

                     var4.internalSetVisibility(var2, var3);
                     InternalVisibilityChangedListener var5 = this.val$listener;
                     if (var5 != null) {
                        var5.onHidden();
                     }
                  }

               }

               public void onAnimationStart(Animator var1) {
                  this.this$0.view.internalSetVisibility(0, this.val$fromUser);
                  this.this$0.animState = 1;
                  this.this$0.currentAnimator = var1;
                  this.cancelled = false;
               }
            });
            ArrayList var5 = this.hideListeners;
            if (var5 != null) {
               Iterator var6 = var5.iterator();

               while(var6.hasNext()) {
                  var8.addListener((Animator.AnimatorListener)var6.next());
               }
            }

            var8.start();
         } else {
            VisibilityAwareImageButton var9 = this.view;
            byte var3;
            if (var2) {
               var3 = 8;
            } else {
               var3 = 4;
            }

            var9.internalSetVisibility(var3, var2);
            if (var1 != null) {
               var1.onHidden();
            }
         }

      }
   }

   boolean isOrWillBeHidden() {
      int var1 = this.view.getVisibility();
      boolean var3 = false;
      boolean var2 = false;
      if (var1 == 0) {
         if (this.animState == 1) {
            var2 = true;
         }

         return var2;
      } else {
         var2 = var3;
         if (this.animState != 2) {
            var2 = true;
         }

         return var2;
      }
   }

   boolean isOrWillBeShown() {
      int var1 = this.view.getVisibility();
      boolean var3 = false;
      boolean var2 = false;
      if (var1 != 0) {
         if (this.animState == 2) {
            var2 = true;
         }

         return var2;
      } else {
         var2 = var3;
         if (this.animState != 1) {
            var2 = true;
         }

         return var2;
      }
   }

   void jumpDrawableToCurrentState() {
      this.stateListAnimator.jumpToCurrentState();
   }

   CircularBorderDrawable newCircularDrawable() {
      return new CircularBorderDrawable();
   }

   GradientDrawable newGradientDrawableForShape() {
      return new GradientDrawable();
   }

   void onAttachedToWindow() {
      if (this.requirePreDrawListener()) {
         this.ensurePreDrawListener();
         this.view.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
      }

   }

   void onCompatShadowChanged() {
   }

   void onDetachedFromWindow() {
      if (this.preDrawListener != null) {
         this.view.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
         this.preDrawListener = null;
      }

   }

   void onDrawableStateChanged(int[] var1) {
      this.stateListAnimator.setState(var1);
   }

   void onElevationsChanged(float var1, float var2, float var3) {
      ShadowDrawableWrapper var4 = this.shadowDrawable;
      if (var4 != null) {
         var4.setShadowSize(var1, this.pressedTranslationZ + var1);
         this.updatePadding();
      }

   }

   void onPaddingUpdated(Rect var1) {
   }

   void onPreDraw() {
      float var1 = this.view.getRotation();
      if (this.rotation != var1) {
         this.rotation = var1;
         this.updateFromViewRotation();
      }

   }

   public void removeOnHideAnimationListener(Animator.AnimatorListener var1) {
      ArrayList var2 = this.hideListeners;
      if (var2 != null) {
         var2.remove(var1);
      }
   }

   void removeOnShowAnimationListener(Animator.AnimatorListener var1) {
      ArrayList var2 = this.showListeners;
      if (var2 != null) {
         var2.remove(var1);
      }
   }

   boolean requirePreDrawListener() {
      return true;
   }

   void setBackgroundDrawable(ColorStateList var1, PorterDuff.Mode var2, ColorStateList var3, int var4) {
      Drawable var7 = DrawableCompat.wrap(this.createShapeDrawable());
      this.shapeDrawable = var7;
      DrawableCompat.setTintList(var7, var1);
      if (var2 != null) {
         DrawableCompat.setTintMode(this.shapeDrawable, var2);
      }

      Drawable var9 = DrawableCompat.wrap(this.createShapeDrawable());
      this.rippleDrawable = var9;
      DrawableCompat.setTintList(var9, RippleUtils.convertToRippleDrawableColor(var3));
      Drawable[] var8;
      if (var4 > 0) {
         CircularBorderDrawable var11 = this.createBorderDrawable(var4, var1);
         this.borderDrawable = var11;
         var8 = new Drawable[]{var11, this.shapeDrawable, this.rippleDrawable};
      } else {
         this.borderDrawable = null;
         var8 = new Drawable[]{this.shapeDrawable, this.rippleDrawable};
      }

      this.contentBackground = new LayerDrawable(var8);
      Context var13 = this.view.getContext();
      Drawable var10 = this.contentBackground;
      float var6 = this.shadowViewDelegate.getRadius();
      float var5 = this.elevation;
      ShadowDrawableWrapper var12 = new ShadowDrawableWrapper(var13, var10, var6, var5, var5 + this.pressedTranslationZ);
      this.shadowDrawable = var12;
      var12.setAddPaddingForCorners(false);
      this.shadowViewDelegate.setBackgroundDrawable(this.shadowDrawable);
   }

   void setBackgroundTintList(ColorStateList var1) {
      Drawable var2 = this.shapeDrawable;
      if (var2 != null) {
         DrawableCompat.setTintList(var2, var1);
      }

      CircularBorderDrawable var3 = this.borderDrawable;
      if (var3 != null) {
         var3.setBorderTint(var1);
      }

   }

   void setBackgroundTintMode(PorterDuff.Mode var1) {
      Drawable var2 = this.shapeDrawable;
      if (var2 != null) {
         DrawableCompat.setTintMode(var2, var1);
      }

   }

   final void setElevation(float var1) {
      if (this.elevation != var1) {
         this.elevation = var1;
         this.onElevationsChanged(var1, this.hoveredFocusedTranslationZ, this.pressedTranslationZ);
      }

   }

   final void setHideMotionSpec(MotionSpec var1) {
      this.hideMotionSpec = var1;
   }

   final void setHoveredFocusedTranslationZ(float var1) {
      if (this.hoveredFocusedTranslationZ != var1) {
         this.hoveredFocusedTranslationZ = var1;
         this.onElevationsChanged(this.elevation, var1, this.pressedTranslationZ);
      }

   }

   final void setImageMatrixScale(float var1) {
      this.imageMatrixScale = var1;
      Matrix var2 = this.tmpMatrix;
      this.calculateImageMatrixFromScale(var1, var2);
      this.view.setImageMatrix(var2);
   }

   final void setMaxImageSize(int var1) {
      if (this.maxImageSize != var1) {
         this.maxImageSize = var1;
         this.updateImageMatrixScale();
      }

   }

   final void setPressedTranslationZ(float var1) {
      if (this.pressedTranslationZ != var1) {
         this.pressedTranslationZ = var1;
         this.onElevationsChanged(this.elevation, this.hoveredFocusedTranslationZ, var1);
      }

   }

   void setRippleColor(ColorStateList var1) {
      Drawable var2 = this.rippleDrawable;
      if (var2 != null) {
         DrawableCompat.setTintList(var2, RippleUtils.convertToRippleDrawableColor(var1));
      }

   }

   final void setShowMotionSpec(MotionSpec var1) {
      this.showMotionSpec = var1;
   }

   void show(InternalVisibilityChangedListener var1, boolean var2) {
      if (!this.isOrWillBeShown()) {
         Animator var3 = this.currentAnimator;
         if (var3 != null) {
            var3.cancel();
         }

         if (this.shouldAnimateVisibilityChange()) {
            if (this.view.getVisibility() != 0) {
               this.view.setAlpha(0.0F);
               this.view.setScaleY(0.0F);
               this.view.setScaleX(0.0F);
               this.setImageMatrixScale(0.0F);
            }

            MotionSpec var6 = this.showMotionSpec;
            if (var6 == null) {
               var6 = this.getDefaultShowMotionSpec();
            }

            AnimatorSet var7 = this.createAnimator(var6, 1.0F, 1.0F, 1.0F);
            var7.addListener(new AnimatorListenerAdapter(this, var2, var1) {
               final FloatingActionButtonImpl this$0;
               final boolean val$fromUser;
               final InternalVisibilityChangedListener val$listener;

               {
                  this.this$0 = var1;
                  this.val$fromUser = var2;
                  this.val$listener = var3;
               }

               public void onAnimationEnd(Animator var1) {
                  this.this$0.animState = 0;
                  this.this$0.currentAnimator = null;
                  InternalVisibilityChangedListener var2 = this.val$listener;
                  if (var2 != null) {
                     var2.onShown();
                  }

               }

               public void onAnimationStart(Animator var1) {
                  this.this$0.view.internalSetVisibility(0, this.val$fromUser);
                  this.this$0.animState = 2;
                  this.this$0.currentAnimator = var1;
               }
            });
            ArrayList var4 = this.showListeners;
            if (var4 != null) {
               Iterator var5 = var4.iterator();

               while(var5.hasNext()) {
                  var7.addListener((Animator.AnimatorListener)var5.next());
               }
            }

            var7.start();
         } else {
            this.view.internalSetVisibility(0, var2);
            this.view.setAlpha(1.0F);
            this.view.setScaleY(1.0F);
            this.view.setScaleX(1.0F);
            this.setImageMatrixScale(1.0F);
            if (var1 != null) {
               var1.onShown();
            }
         }

      }
   }

   final void updateImageMatrixScale() {
      this.setImageMatrixScale(this.imageMatrixScale);
   }

   final void updatePadding() {
      Rect var1 = this.tmpRect;
      this.getPadding(var1);
      this.onPaddingUpdated(var1);
      this.shadowViewDelegate.setShadowPadding(var1.left, var1.top, var1.right, var1.bottom);
   }

   private class DisabledElevationAnimation extends ShadowAnimatorImpl {
      final FloatingActionButtonImpl this$0;

      DisabledElevationAnimation(FloatingActionButtonImpl var1) {
         super(var1, null);
         this.this$0 = var1;
      }

      protected float getTargetShadowSize() {
         return 0.0F;
      }
   }

   private class ElevateToHoveredFocusedTranslationZAnimation extends ShadowAnimatorImpl {
      final FloatingActionButtonImpl this$0;

      ElevateToHoveredFocusedTranslationZAnimation(FloatingActionButtonImpl var1) {
         super(var1, null);
         this.this$0 = var1;
      }

      protected float getTargetShadowSize() {
         return this.this$0.elevation + this.this$0.hoveredFocusedTranslationZ;
      }
   }

   private class ElevateToPressedTranslationZAnimation extends ShadowAnimatorImpl {
      final FloatingActionButtonImpl this$0;

      ElevateToPressedTranslationZAnimation(FloatingActionButtonImpl var1) {
         super(var1, null);
         this.this$0 = var1;
      }

      protected float getTargetShadowSize() {
         return this.this$0.elevation + this.this$0.pressedTranslationZ;
      }
   }

   interface InternalVisibilityChangedListener {
      void onHidden();

      void onShown();
   }

   private class ResetElevationAnimation extends ShadowAnimatorImpl {
      final FloatingActionButtonImpl this$0;

      ResetElevationAnimation(FloatingActionButtonImpl var1) {
         super(var1, null);
         this.this$0 = var1;
      }

      protected float getTargetShadowSize() {
         return this.this$0.elevation;
      }
   }

   private abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
      private float shadowSizeEnd;
      private float shadowSizeStart;
      final FloatingActionButtonImpl this$0;
      private boolean validValues;

      private ShadowAnimatorImpl(FloatingActionButtonImpl var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      ShadowAnimatorImpl(FloatingActionButtonImpl var1, Object var2) {
         this(var1);
      }

      protected abstract float getTargetShadowSize();

      public void onAnimationEnd(Animator var1) {
         this.this$0.shadowDrawable.setShadowSize(this.shadowSizeEnd);
         this.validValues = false;
      }

      public void onAnimationUpdate(ValueAnimator var1) {
         if (!this.validValues) {
            this.shadowSizeStart = this.this$0.shadowDrawable.getShadowSize();
            this.shadowSizeEnd = this.getTargetShadowSize();
            this.validValues = true;
         }

         ShadowDrawableWrapper var3 = this.this$0.shadowDrawable;
         float var2 = this.shadowSizeStart;
         var3.setShadowSize(var2 + (this.shadowSizeEnd - var2) * var1.getAnimatedFraction());
      }
   }
}
