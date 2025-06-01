package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimationUtils;

public class HideBottomViewOnScrollBehavior extends CoordinatorLayout.Behavior {
   protected static final int ENTER_ANIMATION_DURATION = 225;
   protected static final int EXIT_ANIMATION_DURATION = 175;
   private static final int STATE_SCROLLED_DOWN = 1;
   private static final int STATE_SCROLLED_UP = 2;
   private ViewPropertyAnimator currentAnimator;
   private int currentState = 2;
   private int height = 0;

   public HideBottomViewOnScrollBehavior() {
   }

   public HideBottomViewOnScrollBehavior(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private void animateChildTo(View var1, int var2, long var3, TimeInterpolator var5) {
      this.currentAnimator = var1.animate().translationY((float)var2).setInterpolator(var5).setDuration(var3).setListener(new AnimatorListenerAdapter(this) {
         final HideBottomViewOnScrollBehavior this$0;

         {
            this.this$0 = var1;
         }

         public void onAnimationEnd(Animator var1) {
            this.this$0.currentAnimator = null;
         }
      });
   }

   public boolean onLayoutChild(CoordinatorLayout var1, View var2, int var3) {
      this.height = var2.getMeasuredHeight();
      return super.onLayoutChild(var1, var2, var3);
   }

   public void onNestedScroll(CoordinatorLayout var1, View var2, View var3, int var4, int var5, int var6, int var7) {
      var4 = this.currentState;
      if (var4 != 1 && var5 > 0) {
         this.slideDown(var2);
      } else if (var4 != 2 && var5 < 0) {
         this.slideUp(var2);
      }

   }

   public boolean onStartNestedScroll(CoordinatorLayout var1, View var2, View var3, View var4, int var5) {
      boolean var6;
      if (var5 == 2) {
         var6 = true;
      } else {
         var6 = false;
      }

      return var6;
   }

   protected void slideDown(View var1) {
      ViewPropertyAnimator var2 = this.currentAnimator;
      if (var2 != null) {
         var2.cancel();
         var1.clearAnimation();
      }

      this.currentState = 1;
      this.animateChildTo(var1, this.height, 175L, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
   }

   protected void slideUp(View var1) {
      ViewPropertyAnimator var2 = this.currentAnimator;
      if (var2 != null) {
         var2.cancel();
         var1.clearAnimation();
      }

      this.currentState = 2;
      this.animateChildTo(var1, 0, 225L, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
   }
}
