package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.ImageView.ScaleType;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatImageHelper;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.TintableBackgroundView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TintableImageSourceView;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.expandable.ExpandableTransformationWidget;
import com.google.android.material.expandable.ExpandableWidgetHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.stateful.ExtendableSavedState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class FloatingActionButton extends VisibilityAwareImageButton implements TintableBackgroundView, TintableImageSourceView, ExpandableTransformationWidget {
   private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
   private static final String EXPANDABLE_WIDGET_HELPER_KEY = "expandableWidgetHelper";
   private static final String LOG_TAG = "FloatingActionButton";
   public static final int NO_CUSTOM_SIZE = 0;
   public static final int SIZE_AUTO = -1;
   public static final int SIZE_MINI = 1;
   public static final int SIZE_NORMAL = 0;
   private ColorStateList backgroundTint;
   private PorterDuff.Mode backgroundTintMode;
   private int borderWidth;
   boolean compatPadding;
   private int customSize;
   private final ExpandableWidgetHelper expandableWidgetHelper;
   private final AppCompatImageHelper imageHelper;
   private PorterDuff.Mode imageMode;
   private int imagePadding;
   private ColorStateList imageTint;
   private FloatingActionButtonImpl impl;
   private int maxImageSize;
   private ColorStateList rippleColor;
   final Rect shadowPadding;
   private int size;
   private final Rect touchArea;

   public FloatingActionButton(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public FloatingActionButton(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.floatingActionButtonStyle);
   }

   public FloatingActionButton(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.shadowPadding = new Rect();
      this.touchArea = new Rect();
      TypedArray var8 = ThemeEnforcement.obtainStyledAttributes(var1, var2, R.styleable.FloatingActionButton, var3, R.style.Widget_Design_FloatingActionButton);
      this.backgroundTint = MaterialResources.getColorStateList(var1, var8, R.styleable.FloatingActionButton_backgroundTint);
      this.backgroundTintMode = ViewUtils.parseTintMode(var8.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1), (PorterDuff.Mode)null);
      this.rippleColor = MaterialResources.getColorStateList(var1, var8, R.styleable.FloatingActionButton_rippleColor);
      this.size = var8.getInt(R.styleable.FloatingActionButton_fabSize, -1);
      this.customSize = var8.getDimensionPixelSize(R.styleable.FloatingActionButton_fabCustomSize, 0);
      this.borderWidth = var8.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
      float var4 = var8.getDimension(R.styleable.FloatingActionButton_elevation, 0.0F);
      float var5 = var8.getDimension(R.styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0F);
      float var6 = var8.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, 0.0F);
      this.compatPadding = var8.getBoolean(R.styleable.FloatingActionButton_useCompatPadding, false);
      this.maxImageSize = var8.getDimensionPixelSize(R.styleable.FloatingActionButton_maxImageSize, 0);
      MotionSpec var7 = MotionSpec.createFromAttribute(var1, var8, R.styleable.FloatingActionButton_showMotionSpec);
      MotionSpec var9 = MotionSpec.createFromAttribute(var1, var8, R.styleable.FloatingActionButton_hideMotionSpec);
      var8.recycle();
      AppCompatImageHelper var10 = new AppCompatImageHelper(this);
      this.imageHelper = var10;
      var10.loadFromAttributes(var2, var3);
      this.expandableWidgetHelper = new ExpandableWidgetHelper(this);
      this.getImpl().setBackgroundDrawable(this.backgroundTint, this.backgroundTintMode, this.rippleColor, this.borderWidth);
      this.getImpl().setElevation(var4);
      this.getImpl().setHoveredFocusedTranslationZ(var5);
      this.getImpl().setPressedTranslationZ(var6);
      this.getImpl().setMaxImageSize(this.maxImageSize);
      this.getImpl().setShowMotionSpec(var7);
      this.getImpl().setHideMotionSpec(var9);
      this.setScaleType(ScaleType.MATRIX);
   }

   private FloatingActionButtonImpl createImpl() {
      return (FloatingActionButtonImpl)(VERSION.SDK_INT >= 21 ? new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl(this)) : new FloatingActionButtonImpl(this, new ShadowDelegateImpl(this)));
   }

   private FloatingActionButtonImpl getImpl() {
      if (this.impl == null) {
         this.impl = this.createImpl();
      }

      return this.impl;
   }

   private int getSizeDimension(int var1) {
      int var2 = this.customSize;
      if (var2 != 0) {
         return var2;
      } else {
         Resources var3 = this.getResources();
         if (var1 != -1) {
            return var1 != 1 ? var3.getDimensionPixelSize(R.dimen.design_fab_size_normal) : var3.getDimensionPixelSize(R.dimen.design_fab_size_mini);
         } else {
            if (Math.max(var3.getConfiguration().screenWidthDp, var3.getConfiguration().screenHeightDp) < 470) {
               var1 = this.getSizeDimension(1);
            } else {
               var1 = this.getSizeDimension(0);
            }

            return var1;
         }
      }
   }

   private void offsetRectWithShadow(Rect var1) {
      var1.left += this.shadowPadding.left;
      var1.top += this.shadowPadding.top;
      var1.right -= this.shadowPadding.right;
      var1.bottom -= this.shadowPadding.bottom;
   }

   private void onApplySupportImageTint() {
      Drawable var4 = this.getDrawable();
      if (var4 != null) {
         ColorStateList var2 = this.imageTint;
         if (var2 == null) {
            DrawableCompat.clearColorFilter(var4);
         } else {
            int var1 = var2.getColorForState(this.getDrawableState(), 0);
            PorterDuff.Mode var3 = this.imageMode;
            PorterDuff.Mode var5 = var3;
            if (var3 == null) {
               var5 = Mode.SRC_IN;
            }

            var4.mutate().setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(var1, var5));
         }
      }
   }

   private static int resolveAdjustedSize(int var0, int var1) {
      int var2 = MeasureSpec.getMode(var1);
      var1 = MeasureSpec.getSize(var1);
      if (var2 != Integer.MIN_VALUE) {
         if (var2 != 0) {
            if (var2 != 1073741824) {
               throw new IllegalArgumentException();
            }

            var0 = var1;
         }
      } else {
         var0 = Math.min(var0, var1);
      }

      return var0;
   }

   private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(OnVisibilityChangedListener var1) {
      return var1 == null ? null : new FloatingActionButtonImpl.InternalVisibilityChangedListener(this, var1) {
         final FloatingActionButton this$0;
         final OnVisibilityChangedListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onHidden() {
            this.val$listener.onHidden(this.this$0);
         }

         public void onShown() {
            this.val$listener.onShown(this.this$0);
         }
      };
   }

   public void addOnHideAnimationListener(Animator.AnimatorListener var1) {
      this.getImpl().addOnHideAnimationListener(var1);
   }

   public void addOnShowAnimationListener(Animator.AnimatorListener var1) {
      this.getImpl().addOnShowAnimationListener(var1);
   }

   public void clearCustomSize() {
      this.setCustomSize(0);
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      this.getImpl().onDrawableStateChanged(this.getDrawableState());
   }

   public ColorStateList getBackgroundTintList() {
      return this.backgroundTint;
   }

   public PorterDuff.Mode getBackgroundTintMode() {
      return this.backgroundTintMode;
   }

   public float getCompatElevation() {
      return this.getImpl().getElevation();
   }

   public float getCompatHoveredFocusedTranslationZ() {
      return this.getImpl().getHoveredFocusedTranslationZ();
   }

   public float getCompatPressedTranslationZ() {
      return this.getImpl().getPressedTranslationZ();
   }

   public Drawable getContentBackground() {
      return this.getImpl().getContentBackground();
   }

   @Deprecated
   public boolean getContentRect(Rect var1) {
      if (ViewCompat.isLaidOut(this)) {
         var1.set(0, 0, this.getWidth(), this.getHeight());
         this.offsetRectWithShadow(var1);
         return true;
      } else {
         return false;
      }
   }

   public int getCustomSize() {
      return this.customSize;
   }

   public int getExpandedComponentIdHint() {
      return this.expandableWidgetHelper.getExpandedComponentIdHint();
   }

   public MotionSpec getHideMotionSpec() {
      return this.getImpl().getHideMotionSpec();
   }

   public void getMeasuredContentRect(Rect var1) {
      var1.set(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
      this.offsetRectWithShadow(var1);
   }

   @Deprecated
   public int getRippleColor() {
      ColorStateList var2 = this.rippleColor;
      int var1;
      if (var2 != null) {
         var1 = var2.getDefaultColor();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public ColorStateList getRippleColorStateList() {
      return this.rippleColor;
   }

   public MotionSpec getShowMotionSpec() {
      return this.getImpl().getShowMotionSpec();
   }

   public int getSize() {
      return this.size;
   }

   int getSizeDimension() {
      return this.getSizeDimension(this.size);
   }

   public ColorStateList getSupportBackgroundTintList() {
      return this.getBackgroundTintList();
   }

   public PorterDuff.Mode getSupportBackgroundTintMode() {
      return this.getBackgroundTintMode();
   }

   public ColorStateList getSupportImageTintList() {
      return this.imageTint;
   }

   public PorterDuff.Mode getSupportImageTintMode() {
      return this.imageMode;
   }

   public boolean getUseCompatPadding() {
      return this.compatPadding;
   }

   public void hide() {
      this.hide((OnVisibilityChangedListener)null);
   }

   public void hide(OnVisibilityChangedListener var1) {
      this.hide(var1, true);
   }

   void hide(OnVisibilityChangedListener var1, boolean var2) {
      this.getImpl().hide(this.wrapOnVisibilityChangedListener(var1), var2);
   }

   public boolean isExpanded() {
      return this.expandableWidgetHelper.isExpanded();
   }

   public boolean isOrWillBeHidden() {
      return this.getImpl().isOrWillBeHidden();
   }

   public boolean isOrWillBeShown() {
      return this.getImpl().isOrWillBeShown();
   }

   public void jumpDrawablesToCurrentState() {
      super.jumpDrawablesToCurrentState();
      this.getImpl().jumpDrawableToCurrentState();
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.getImpl().onAttachedToWindow();
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.getImpl().onDetachedFromWindow();
   }

   protected void onMeasure(int var1, int var2) {
      int var3 = this.getSizeDimension();
      this.imagePadding = (var3 - this.maxImageSize) / 2;
      this.getImpl().updatePadding();
      var1 = Math.min(resolveAdjustedSize(var3, var1), resolveAdjustedSize(var3, var2));
      this.setMeasuredDimension(this.shadowPadding.left + var1 + this.shadowPadding.right, var1 + this.shadowPadding.top + this.shadowPadding.bottom);
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof ExtendableSavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         ExtendableSavedState var2 = (ExtendableSavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.expandableWidgetHelper.onRestoreInstanceState((Bundle)var2.extendableStates.get("expandableWidgetHelper"));
      }
   }

   protected Parcelable onSaveInstanceState() {
      ExtendableSavedState var1 = new ExtendableSavedState(super.onSaveInstanceState());
      var1.extendableStates.put("expandableWidgetHelper", this.expandableWidgetHelper.onSaveInstanceState());
      return var1;
   }

   public boolean onTouchEvent(MotionEvent var1) {
      return var1.getAction() == 0 && this.getContentRect(this.touchArea) && !this.touchArea.contains((int)var1.getX(), (int)var1.getY()) ? false : super.onTouchEvent(var1);
   }

   public void removeOnHideAnimationListener(Animator.AnimatorListener var1) {
      this.getImpl().removeOnHideAnimationListener(var1);
   }

   public void removeOnShowAnimationListener(Animator.AnimatorListener var1) {
      this.getImpl().removeOnShowAnimationListener(var1);
   }

   public void setBackgroundColor(int var1) {
      Log.i("FloatingActionButton", "Setting a custom background is not supported.");
   }

   public void setBackgroundDrawable(Drawable var1) {
      Log.i("FloatingActionButton", "Setting a custom background is not supported.");
   }

   public void setBackgroundResource(int var1) {
      Log.i("FloatingActionButton", "Setting a custom background is not supported.");
   }

   public void setBackgroundTintList(ColorStateList var1) {
      if (this.backgroundTint != var1) {
         this.backgroundTint = var1;
         this.getImpl().setBackgroundTintList(var1);
      }

   }

   public void setBackgroundTintMode(PorterDuff.Mode var1) {
      if (this.backgroundTintMode != var1) {
         this.backgroundTintMode = var1;
         this.getImpl().setBackgroundTintMode(var1);
      }

   }

   public void setCompatElevation(float var1) {
      this.getImpl().setElevation(var1);
   }

   public void setCompatElevationResource(int var1) {
      this.setCompatElevation(this.getResources().getDimension(var1));
   }

   public void setCompatHoveredFocusedTranslationZ(float var1) {
      this.getImpl().setHoveredFocusedTranslationZ(var1);
   }

   public void setCompatHoveredFocusedTranslationZResource(int var1) {
      this.setCompatHoveredFocusedTranslationZ(this.getResources().getDimension(var1));
   }

   public void setCompatPressedTranslationZ(float var1) {
      this.getImpl().setPressedTranslationZ(var1);
   }

   public void setCompatPressedTranslationZResource(int var1) {
      this.setCompatPressedTranslationZ(this.getResources().getDimension(var1));
   }

   public void setCustomSize(int var1) {
      if (var1 >= 0) {
         this.customSize = var1;
      } else {
         throw new IllegalArgumentException("Custom size must be non-negative");
      }
   }

   public boolean setExpanded(boolean var1) {
      return this.expandableWidgetHelper.setExpanded(var1);
   }

   public void setExpandedComponentIdHint(int var1) {
      this.expandableWidgetHelper.setExpandedComponentIdHint(var1);
   }

   public void setHideMotionSpec(MotionSpec var1) {
      this.getImpl().setHideMotionSpec(var1);
   }

   public void setHideMotionSpecResource(int var1) {
      this.setHideMotionSpec(MotionSpec.createFromResource(this.getContext(), var1));
   }

   public void setImageDrawable(Drawable var1) {
      super.setImageDrawable(var1);
      this.getImpl().updateImageMatrixScale();
   }

   public void setImageResource(int var1) {
      this.imageHelper.setImageResource(var1);
   }

   public void setRippleColor(int var1) {
      this.setRippleColor(ColorStateList.valueOf(var1));
   }

   public void setRippleColor(ColorStateList var1) {
      if (this.rippleColor != var1) {
         this.rippleColor = var1;
         this.getImpl().setRippleColor(this.rippleColor);
      }

   }

   public void setShowMotionSpec(MotionSpec var1) {
      this.getImpl().setShowMotionSpec(var1);
   }

   public void setShowMotionSpecResource(int var1) {
      this.setShowMotionSpec(MotionSpec.createFromResource(this.getContext(), var1));
   }

   public void setSize(int var1) {
      this.customSize = 0;
      if (var1 != this.size) {
         this.size = var1;
         this.requestLayout();
      }

   }

   public void setSupportBackgroundTintList(ColorStateList var1) {
      this.setBackgroundTintList(var1);
   }

   public void setSupportBackgroundTintMode(PorterDuff.Mode var1) {
      this.setBackgroundTintMode(var1);
   }

   public void setSupportImageTintList(ColorStateList var1) {
      if (this.imageTint != var1) {
         this.imageTint = var1;
         this.onApplySupportImageTint();
      }

   }

   public void setSupportImageTintMode(PorterDuff.Mode var1) {
      if (this.imageMode != var1) {
         this.imageMode = var1;
         this.onApplySupportImageTint();
      }

   }

   public void setUseCompatPadding(boolean var1) {
      if (this.compatPadding != var1) {
         this.compatPadding = var1;
         this.getImpl().onCompatShadowChanged();
      }

   }

   public void show() {
      this.show((OnVisibilityChangedListener)null);
   }

   public void show(OnVisibilityChangedListener var1) {
      this.show(var1, true);
   }

   void show(OnVisibilityChangedListener var1, boolean var2) {
      this.getImpl().show(this.wrapOnVisibilityChangedListener(var1), var2);
   }

   protected static class BaseBehavior extends CoordinatorLayout.Behavior {
      private static final boolean AUTO_HIDE_DEFAULT = true;
      private boolean autoHideEnabled;
      private OnVisibilityChangedListener internalAutoHideListener;
      private Rect tmpRect;

      public BaseBehavior() {
         this.autoHideEnabled = true;
      }

      public BaseBehavior(Context var1, AttributeSet var2) {
         super(var1, var2);
         TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.FloatingActionButton_Behavior_Layout);
         this.autoHideEnabled = var3.getBoolean(R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
         var3.recycle();
      }

      private static boolean isBottomSheet(View var0) {
         ViewGroup.LayoutParams var1 = var0.getLayoutParams();
         return var1 instanceof CoordinatorLayout.LayoutParams ? ((CoordinatorLayout.LayoutParams)var1).getBehavior() instanceof BottomSheetBehavior : false;
      }

      private void offsetIfNeeded(CoordinatorLayout var1, FloatingActionButton var2) {
         Rect var7 = var2.shadowPadding;
         if (var7 != null && var7.centerX() > 0 && var7.centerY() > 0) {
            CoordinatorLayout.LayoutParams var8 = (CoordinatorLayout.LayoutParams)var2.getLayoutParams();
            int var3 = var2.getRight();
            int var5 = var1.getWidth();
            int var6 = var8.rightMargin;
            int var4 = 0;
            if (var3 >= var5 - var6) {
               var3 = var7.right;
            } else if (var2.getLeft() <= var8.leftMargin) {
               var3 = -var7.left;
            } else {
               var3 = 0;
            }

            if (var2.getBottom() >= var1.getHeight() - var8.bottomMargin) {
               var4 = var7.bottom;
            } else if (var2.getTop() <= var8.topMargin) {
               var4 = -var7.top;
            }

            if (var4 != 0) {
               ViewCompat.offsetTopAndBottom(var2, var4);
            }

            if (var3 != 0) {
               ViewCompat.offsetLeftAndRight(var2, var3);
            }
         }

      }

      private boolean shouldUpdateVisibility(View var1, FloatingActionButton var2) {
         CoordinatorLayout.LayoutParams var3 = (CoordinatorLayout.LayoutParams)var2.getLayoutParams();
         if (!this.autoHideEnabled) {
            return false;
         } else if (var3.getAnchorId() != var1.getId()) {
            return false;
         } else {
            return var2.getUserSetVisibility() == 0;
         }
      }

      private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout var1, AppBarLayout var2, FloatingActionButton var3) {
         if (!this.shouldUpdateVisibility(var2, var3)) {
            return false;
         } else {
            if (this.tmpRect == null) {
               this.tmpRect = new Rect();
            }

            Rect var4 = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(var1, var2, var4);
            if (var4.bottom <= var2.getMinimumHeightForVisibleOverlappingContent()) {
               var3.hide(this.internalAutoHideListener, false);
            } else {
               var3.show(this.internalAutoHideListener, false);
            }

            return true;
         }
      }

      private boolean updateFabVisibilityForBottomSheet(View var1, FloatingActionButton var2) {
         if (!this.shouldUpdateVisibility(var1, var2)) {
            return false;
         } else {
            CoordinatorLayout.LayoutParams var3 = (CoordinatorLayout.LayoutParams)var2.getLayoutParams();
            if (var1.getTop() < var2.getHeight() / 2 + var3.topMargin) {
               var2.hide(this.internalAutoHideListener, false);
            } else {
               var2.show(this.internalAutoHideListener, false);
            }

            return true;
         }
      }

      public boolean getInsetDodgeRect(CoordinatorLayout var1, FloatingActionButton var2, Rect var3) {
         Rect var4 = var2.shadowPadding;
         var3.set(var2.getLeft() + var4.left, var2.getTop() + var4.top, var2.getRight() - var4.right, var2.getBottom() - var4.bottom);
         return true;
      }

      public boolean isAutoHideEnabled() {
         return this.autoHideEnabled;
      }

      public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams var1) {
         if (var1.dodgeInsetEdges == 0) {
            var1.dodgeInsetEdges = 80;
         }

      }

      public boolean onDependentViewChanged(CoordinatorLayout var1, FloatingActionButton var2, View var3) {
         if (var3 instanceof AppBarLayout) {
            this.updateFabVisibilityForAppBarLayout(var1, (AppBarLayout)var3, var2);
         } else if (isBottomSheet(var3)) {
            this.updateFabVisibilityForBottomSheet(var3, var2);
         }

         return false;
      }

      public boolean onLayoutChild(CoordinatorLayout var1, FloatingActionButton var2, int var3) {
         List var6 = var1.getDependencies(var2);
         int var5 = var6.size();

         for(int var4 = 0; var4 < var5; ++var4) {
            View var7 = (View)var6.get(var4);
            if (var7 instanceof AppBarLayout) {
               if (this.updateFabVisibilityForAppBarLayout(var1, (AppBarLayout)var7, var2)) {
                  break;
               }
            } else if (isBottomSheet(var7) && this.updateFabVisibilityForBottomSheet(var7, var2)) {
               break;
            }
         }

         var1.onLayoutChild(var2, var3);
         this.offsetIfNeeded(var1, var2);
         return true;
      }

      public void setAutoHideEnabled(boolean var1) {
         this.autoHideEnabled = var1;
      }

      public void setInternalAutoHideListener(OnVisibilityChangedListener var1) {
         this.internalAutoHideListener = var1;
      }
   }

   public static class Behavior extends BaseBehavior {
      public Behavior() {
      }

      public Behavior(Context var1, AttributeSet var2) {
         super(var1, var2);
      }
   }

   public abstract static class OnVisibilityChangedListener {
      public void onHidden(FloatingActionButton var1) {
      }

      public void onShown(FloatingActionButton var1) {
      }
   }

   private class ShadowDelegateImpl implements ShadowViewDelegate {
      final FloatingActionButton this$0;

      ShadowDelegateImpl(FloatingActionButton var1) {
         this.this$0 = var1;
      }

      public float getRadius() {
         return (float)this.this$0.getSizeDimension() / 2.0F;
      }

      public boolean isCompatPaddingEnabled() {
         return this.this$0.compatPadding;
      }

      public void setBackgroundDrawable(Drawable var1) {
         this.this$0.setBackgroundDrawable(var1);
      }

      public void setShadowPadding(int var1, int var2, int var3, int var4) {
         this.this$0.shadowPadding.set(var1, var2, var3, var4);
         FloatingActionButton var5 = this.this$0;
         var5.setPadding(var1 + var5.imagePadding, var2 + this.this$0.imagePadding, var3 + this.this$0.imagePadding, var4 + this.this$0.imagePadding);
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Size {
   }
}
