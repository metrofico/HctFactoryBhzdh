package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapePathModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
   private static final long ANIMATION_DURATION = 300L;
   public static final int FAB_ALIGNMENT_MODE_CENTER = 0;
   public static final int FAB_ALIGNMENT_MODE_END = 1;
   private Animator attachAnimator;
   private int fabAlignmentMode;
   AnimatorListenerAdapter fabAnimationListener;
   private boolean fabAttached;
   private final int fabOffsetEndMode;
   private boolean hideOnScroll;
   private final MaterialShapeDrawable materialShapeDrawable;
   private Animator menuAnimator;
   private Animator modeAnimator;
   private final BottomAppBarTopEdgeTreatment topEdgeTreatment;

   public BottomAppBar(Context var1) {
      this(var1, (AttributeSet)null, 0);
   }

   public BottomAppBar(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.bottomAppBarStyle);
   }

   public BottomAppBar(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.fabAttached = true;
      this.fabAnimationListener = new AnimatorListenerAdapter(this) {
         final BottomAppBar this$0;

         {
            this.this$0 = var1;
         }

         public void onAnimationStart(Animator var1) {
            BottomAppBar var2 = this.this$0;
            var2.maybeAnimateAttachChange(var2.fabAttached);
            var2 = this.this$0;
            var2.maybeAnimateMenuView(var2.fabAlignmentMode, this.this$0.fabAttached);
         }
      };
      TypedArray var9 = ThemeEnforcement.obtainStyledAttributes(var1, var2, R.styleable.BottomAppBar, var3, R.style.Widget_MaterialComponents_BottomAppBar);
      ColorStateList var8 = MaterialResources.getColorStateList(var1, var9, R.styleable.BottomAppBar_backgroundTint);
      float var6 = (float)var9.getDimensionPixelOffset(R.styleable.BottomAppBar_fabCradleMargin, 0);
      float var5 = (float)var9.getDimensionPixelOffset(R.styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
      float var4 = (float)var9.getDimensionPixelOffset(R.styleable.BottomAppBar_fabCradleVerticalOffset, 0);
      this.fabAlignmentMode = var9.getInt(R.styleable.BottomAppBar_fabAlignmentMode, 0);
      this.hideOnScroll = var9.getBoolean(R.styleable.BottomAppBar_hideOnScroll, false);
      var9.recycle();
      this.fabOffsetEndMode = this.getResources().getDimensionPixelOffset(R.dimen.mtrl_bottomappbar_fabOffsetEndMode);
      BottomAppBarTopEdgeTreatment var10 = new BottomAppBarTopEdgeTreatment(var6, var5, var4);
      this.topEdgeTreatment = var10;
      ShapePathModel var7 = new ShapePathModel();
      var7.setTopEdge(var10);
      MaterialShapeDrawable var11 = new MaterialShapeDrawable(var7);
      this.materialShapeDrawable = var11;
      var11.setShadowEnabled(true);
      var11.setPaintStyle(Style.FILL);
      DrawableCompat.setTintList(var11, var8);
      ViewCompat.setBackground(this, var11);
   }

   private void addFabAnimationListeners(FloatingActionButton var1) {
      this.removeFabAnimationListeners(var1);
      var1.addOnHideAnimationListener(this.fabAnimationListener);
      var1.addOnShowAnimationListener(this.fabAnimationListener);
   }

   private void cancelAnimations() {
      Animator var1 = this.attachAnimator;
      if (var1 != null) {
         var1.cancel();
      }

      var1 = this.menuAnimator;
      if (var1 != null) {
         var1.cancel();
      }

      var1 = this.modeAnimator;
      if (var1 != null) {
         var1.cancel();
      }

   }

   private void createCradleShapeAnimation(boolean var1, List var2) {
      if (var1) {
         this.topEdgeTreatment.setHorizontalOffset(this.getFabTranslationX());
      }

      float var4 = this.materialShapeDrawable.getInterpolation();
      float var3;
      if (var1) {
         var3 = 1.0F;
      } else {
         var3 = 0.0F;
      }

      ValueAnimator var5 = ValueAnimator.ofFloat(new float[]{var4, var3});
      var5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
         final BottomAppBar this$0;

         {
            this.this$0 = var1;
         }

         public void onAnimationUpdate(ValueAnimator var1) {
            this.this$0.materialShapeDrawable.setInterpolation((Float)var1.getAnimatedValue());
         }
      });
      var5.setDuration(300L);
      var2.add(var5);
   }

   private void createCradleTranslationAnimation(int var1, List var2) {
      if (this.fabAttached) {
         ValueAnimator var3 = ValueAnimator.ofFloat(new float[]{this.topEdgeTreatment.getHorizontalOffset(), (float)this.getFabTranslationX(var1)});
         var3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            final BottomAppBar this$0;

            {
               this.this$0 = var1;
            }

            public void onAnimationUpdate(ValueAnimator var1) {
               this.this$0.topEdgeTreatment.setHorizontalOffset((Float)var1.getAnimatedValue());
               this.this$0.materialShapeDrawable.invalidateSelf();
            }
         });
         var3.setDuration(300L);
         var2.add(var3);
      }
   }

   private void createFabTranslationXAnimation(int var1, List var2) {
      ObjectAnimator var3 = ObjectAnimator.ofFloat(this.findDependentFab(), "translationX", new float[]{(float)this.getFabTranslationX(var1)});
      var3.setDuration(300L);
      var2.add(var3);
   }

   private void createFabTranslationYAnimation(boolean var1, List var2) {
      FloatingActionButton var3 = this.findDependentFab();
      if (var3 != null) {
         ObjectAnimator var4 = ObjectAnimator.ofFloat(var3, "translationY", new float[]{this.getFabTranslationY(var1)});
         var4.setDuration(300L);
         var2.add(var4);
      }
   }

   private void createMenuViewTranslationAnimation(int var1, boolean var2, List var3) {
      ActionMenuView var6 = this.getActionMenuView();
      if (var6 != null) {
         ObjectAnimator var4 = ObjectAnimator.ofFloat(var6, "alpha", new float[]{1.0F});
         if ((this.fabAttached || var2 && this.isVisibleFab()) && (this.fabAlignmentMode == 1 || var1 == 1)) {
            ObjectAnimator var5 = ObjectAnimator.ofFloat(var6, "alpha", new float[]{0.0F});
            var5.addListener(new AnimatorListenerAdapter(this, var6, var1, var2) {
               public boolean cancelled;
               final BottomAppBar this$0;
               final ActionMenuView val$actionMenuView;
               final boolean val$targetAttached;
               final int val$targetMode;

               {
                  this.this$0 = var1;
                  this.val$actionMenuView = var2;
                  this.val$targetMode = var3;
                  this.val$targetAttached = var4;
               }

               public void onAnimationCancel(Animator var1) {
                  this.cancelled = true;
               }

               public void onAnimationEnd(Animator var1) {
                  if (!this.cancelled) {
                     this.this$0.translateActionMenuView(this.val$actionMenuView, this.val$targetMode, this.val$targetAttached);
                  }

               }
            });
            AnimatorSet var7 = new AnimatorSet();
            var7.setDuration(150L);
            var7.playSequentially(new Animator[]{var5, var4});
            var3.add(var7);
         } else if (var6.getAlpha() < 1.0F) {
            var3.add(var4);
         }

      }
   }

   private FloatingActionButton findDependentFab() {
      if (!(this.getParent() instanceof CoordinatorLayout)) {
         return null;
      } else {
         Iterator var2 = ((CoordinatorLayout)this.getParent()).getDependents(this).iterator();

         View var1;
         do {
            if (!var2.hasNext()) {
               return null;
            }

            var1 = (View)var2.next();
         } while(!(var1 instanceof FloatingActionButton));

         return (FloatingActionButton)var1;
      }
   }

   private ActionMenuView getActionMenuView() {
      for(int var1 = 0; var1 < this.getChildCount(); ++var1) {
         View var2 = this.getChildAt(var1);
         if (var2 instanceof ActionMenuView) {
            return (ActionMenuView)var2;
         }
      }

      return null;
   }

   private float getFabTranslationX() {
      return (float)this.getFabTranslationX(this.fabAlignmentMode);
   }

   private int getFabTranslationX(int var1) {
      int var2 = ViewCompat.getLayoutDirection(this);
      int var3 = 0;
      byte var4 = 1;
      boolean var7;
      if (var2 == 1) {
         var7 = true;
      } else {
         var7 = false;
      }

      if (var1 == 1) {
         var3 = this.getMeasuredWidth() / 2;
         int var5 = this.fabOffsetEndMode;
         byte var6 = var4;
         if (var7) {
            var6 = -1;
         }

         var3 = (var3 - var5) * var6;
      }

      return var3;
   }

   private float getFabTranslationY() {
      return this.getFabTranslationY(this.fabAttached);
   }

   private float getFabTranslationY(boolean var1) {
      FloatingActionButton var6 = this.findDependentFab();
      if (var6 == null) {
         return 0.0F;
      } else {
         Rect var7 = new Rect();
         var6.getContentRect(var7);
         float var3 = (float)var7.height();
         float var2 = var3;
         if (var3 == 0.0F) {
            var2 = (float)var6.getMeasuredHeight();
         }

         var3 = (float)(var6.getHeight() - var7.bottom);
         float var4 = (float)(var6.getHeight() - var7.height());
         var2 = -this.getCradleVerticalOffset() + var2 / 2.0F + var3;
         float var5 = (float)var6.getPaddingBottom();
         var3 = (float)(-this.getMeasuredHeight());
         if (!var1) {
            var2 = var4 - var5;
         }

         return var3 + var2;
      }
   }

   private boolean isAnimationRunning() {
      Animator var2 = this.attachAnimator;
      boolean var1;
      if (var2 == null || !var2.isRunning()) {
         var2 = this.menuAnimator;
         if (var2 == null || !var2.isRunning()) {
            var2 = this.modeAnimator;
            if (var2 == null || !var2.isRunning()) {
               var1 = false;
               return var1;
            }
         }
      }

      var1 = true;
      return var1;
   }

   private boolean isVisibleFab() {
      FloatingActionButton var2 = this.findDependentFab();
      boolean var1;
      if (var2 != null && var2.isOrWillBeShown()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void maybeAnimateAttachChange(boolean var1) {
      if (ViewCompat.isLaidOut(this)) {
         Animator var3 = this.attachAnimator;
         if (var3 != null) {
            var3.cancel();
         }

         ArrayList var4 = new ArrayList();
         boolean var2;
         if (var1 && this.isVisibleFab()) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.createCradleShapeAnimation(var2, var4);
         this.createFabTranslationYAnimation(var1, var4);
         AnimatorSet var5 = new AnimatorSet();
         var5.playTogether(var4);
         this.attachAnimator = var5;
         var5.addListener(new AnimatorListenerAdapter(this) {
            final BottomAppBar this$0;

            {
               this.this$0 = var1;
            }

            public void onAnimationEnd(Animator var1) {
               this.this$0.attachAnimator = null;
            }
         });
         this.attachAnimator.start();
      }
   }

   private void maybeAnimateMenuView(int var1, boolean var2) {
      if (ViewCompat.isLaidOut(this)) {
         Animator var3 = this.menuAnimator;
         if (var3 != null) {
            var3.cancel();
         }

         ArrayList var5 = new ArrayList();
         if (!this.isVisibleFab()) {
            var1 = 0;
            var2 = false;
         }

         this.createMenuViewTranslationAnimation(var1, var2, var5);
         AnimatorSet var4 = new AnimatorSet();
         var4.playTogether(var5);
         this.menuAnimator = var4;
         var4.addListener(new AnimatorListenerAdapter(this) {
            final BottomAppBar this$0;

            {
               this.this$0 = var1;
            }

            public void onAnimationEnd(Animator var1) {
               this.this$0.menuAnimator = null;
            }
         });
         this.menuAnimator.start();
      }
   }

   private void maybeAnimateModeChange(int var1) {
      if (this.fabAlignmentMode != var1 && ViewCompat.isLaidOut(this)) {
         Animator var2 = this.modeAnimator;
         if (var2 != null) {
            var2.cancel();
         }

         ArrayList var3 = new ArrayList();
         this.createCradleTranslationAnimation(var1, var3);
         this.createFabTranslationXAnimation(var1, var3);
         AnimatorSet var4 = new AnimatorSet();
         var4.playTogether(var3);
         this.modeAnimator = var4;
         var4.addListener(new AnimatorListenerAdapter(this) {
            final BottomAppBar this$0;

            {
               this.this$0 = var1;
            }

            public void onAnimationEnd(Animator var1) {
               this.this$0.modeAnimator = null;
            }
         });
         this.modeAnimator.start();
      }

   }

   private void removeFabAnimationListeners(FloatingActionButton var1) {
      var1.removeOnHideAnimationListener(this.fabAnimationListener);
      var1.removeOnShowAnimationListener(this.fabAnimationListener);
   }

   private void setCutoutState() {
      this.topEdgeTreatment.setHorizontalOffset(this.getFabTranslationX());
      FloatingActionButton var3 = this.findDependentFab();
      MaterialShapeDrawable var2 = this.materialShapeDrawable;
      float var1;
      if (this.fabAttached && this.isVisibleFab()) {
         var1 = 1.0F;
      } else {
         var1 = 0.0F;
      }

      var2.setInterpolation(var1);
      if (var3 != null) {
         var3.setTranslationY(this.getFabTranslationY());
         var3.setTranslationX(this.getFabTranslationX());
      }

      ActionMenuView var4 = this.getActionMenuView();
      if (var4 != null) {
         var4.setAlpha(1.0F);
         if (!this.isVisibleFab()) {
            this.translateActionMenuView(var4, 0, false);
         } else {
            this.translateActionMenuView(var4, this.fabAlignmentMode, this.fabAttached);
         }
      }

   }

   private void translateActionMenuView(ActionMenuView var1, int var2, boolean var3) {
      boolean var5;
      if (ViewCompat.getLayoutDirection(this) == 1) {
         var5 = true;
      } else {
         var5 = false;
      }

      int var7 = 0;

      int var6;
      int var8;
      for(var6 = 0; var7 < this.getChildCount(); var6 = var8) {
         View var10 = this.getChildAt(var7);
         boolean var9;
         if (var10.getLayoutParams() instanceof Toolbar.LayoutParams && (((Toolbar.LayoutParams)var10.getLayoutParams()).gravity & 8388615) == 8388611) {
            var9 = true;
         } else {
            var9 = false;
         }

         var8 = var6;
         if (var9) {
            if (var5) {
               var8 = var10.getLeft();
            } else {
               var8 = var10.getRight();
            }

            var8 = Math.max(var6, var8);
         }

         ++var7;
      }

      int var11;
      if (var5) {
         var11 = var1.getRight();
      } else {
         var11 = var1.getLeft();
      }

      float var4;
      if (var2 == 1 && var3) {
         var4 = (float)(var6 - var11);
      } else {
         var4 = 0.0F;
      }

      var1.setTranslationX(var4);
   }

   public ColorStateList getBackgroundTint() {
      return this.materialShapeDrawable.getTintList();
   }

   public CoordinatorLayout.Behavior getBehavior() {
      return new Behavior();
   }

   public float getCradleVerticalOffset() {
      return this.topEdgeTreatment.getCradleVerticalOffset();
   }

   public int getFabAlignmentMode() {
      return this.fabAlignmentMode;
   }

   public float getFabCradleMargin() {
      return this.topEdgeTreatment.getFabCradleMargin();
   }

   public float getFabCradleRoundedCornerRadius() {
      return this.topEdgeTreatment.getFabCradleRoundedCornerRadius();
   }

   public boolean getHideOnScroll() {
      return this.hideOnScroll;
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      super.onLayout(var1, var2, var3, var4, var5);
      this.cancelAnimations();
      this.setCutoutState();
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.fabAlignmentMode = var2.fabAlignmentMode;
         this.fabAttached = var2.fabAttached;
      }
   }

   protected Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState(super.onSaveInstanceState());
      var1.fabAlignmentMode = this.fabAlignmentMode;
      var1.fabAttached = this.fabAttached;
      return var1;
   }

   public void replaceMenu(int var1) {
      this.getMenu().clear();
      this.inflateMenu(var1);
   }

   public void setBackgroundTint(ColorStateList var1) {
      DrawableCompat.setTintList(this.materialShapeDrawable, var1);
   }

   public void setCradleVerticalOffset(float var1) {
      if (var1 != this.getCradleVerticalOffset()) {
         this.topEdgeTreatment.setCradleVerticalOffset(var1);
         this.materialShapeDrawable.invalidateSelf();
      }

   }

   public void setFabAlignmentMode(int var1) {
      this.maybeAnimateModeChange(var1);
      this.maybeAnimateMenuView(var1, this.fabAttached);
      this.fabAlignmentMode = var1;
   }

   public void setFabCradleMargin(float var1) {
      if (var1 != this.getFabCradleMargin()) {
         this.topEdgeTreatment.setFabCradleMargin(var1);
         this.materialShapeDrawable.invalidateSelf();
      }

   }

   public void setFabCradleRoundedCornerRadius(float var1) {
      if (var1 != this.getFabCradleRoundedCornerRadius()) {
         this.topEdgeTreatment.setFabCradleRoundedCornerRadius(var1);
         this.materialShapeDrawable.invalidateSelf();
      }

   }

   void setFabDiameter(int var1) {
      float var2 = (float)var1;
      if (var2 != this.topEdgeTreatment.getFabDiameter()) {
         this.topEdgeTreatment.setFabDiameter(var2);
         this.materialShapeDrawable.invalidateSelf();
      }

   }

   public void setHideOnScroll(boolean var1) {
      this.hideOnScroll = var1;
   }

   public void setSubtitle(CharSequence var1) {
   }

   public void setTitle(CharSequence var1) {
   }

   public static class Behavior extends HideBottomViewOnScrollBehavior {
      private final Rect fabContentRect = new Rect();

      public Behavior() {
      }

      public Behavior(Context var1, AttributeSet var2) {
         super(var1, var2);
      }

      private boolean updateFabPositionAndVisibility(FloatingActionButton var1, BottomAppBar var2) {
         ((CoordinatorLayout.LayoutParams)var1.getLayoutParams()).anchorGravity = 17;
         var2.addFabAnimationListeners(var1);
         return true;
      }

      public boolean onLayoutChild(CoordinatorLayout var1, BottomAppBar var2, int var3) {
         FloatingActionButton var4 = var2.findDependentFab();
         if (var4 != null) {
            this.updateFabPositionAndVisibility(var4, var2);
            var4.getMeasuredContentRect(this.fabContentRect);
            var2.setFabDiameter(this.fabContentRect.height());
         }

         if (!var2.isAnimationRunning()) {
            var2.setCutoutState();
         }

         var1.onLayoutChild(var2, var3);
         return super.onLayoutChild(var1, var2, var3);
      }

      public boolean onStartNestedScroll(CoordinatorLayout var1, BottomAppBar var2, View var3, View var4, int var5, int var6) {
         boolean var7;
         if (var2.getHideOnScroll() && super.onStartNestedScroll(var1, var2, var3, var4, var5, var6)) {
            var7 = true;
         } else {
            var7 = false;
         }

         return var7;
      }

      protected void slideDown(BottomAppBar var1) {
         super.slideDown(var1);
         FloatingActionButton var3 = var1.findDependentFab();
         if (var3 != null) {
            var3.getContentRect(this.fabContentRect);
            float var2 = (float)(var3.getMeasuredHeight() - this.fabContentRect.height());
            var3.clearAnimation();
            var3.animate().translationY((float)(-var3.getPaddingBottom()) + var2).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setDuration(175L);
         }

      }

      protected void slideUp(BottomAppBar var1) {
         super.slideUp(var1);
         FloatingActionButton var2 = var1.findDependentFab();
         if (var2 != null) {
            var2.clearAnimation();
            var2.animate().translationY(var1.getFabTranslationY()).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setDuration(225L);
         }

      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface FabAlignmentMode {
   }

   static class SavedState extends AbsSavedState {
      public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1, (ClassLoader)null);
         }

         public SavedState createFromParcel(Parcel var1, ClassLoader var2) {
            return new SavedState(var1, var2);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      int fabAlignmentMode;
      boolean fabAttached;

      public SavedState(Parcel var1, ClassLoader var2) {
         super(var1, var2);
         this.fabAlignmentMode = var1.readInt();
         boolean var3;
         if (var1.readInt() != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.fabAttached = var3;
      }

      public SavedState(Parcelable var1) {
         super(var1);
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeInt(this.fabAlignmentMode);
         var1.writeInt(this.fabAttached);
      }
   }
}
