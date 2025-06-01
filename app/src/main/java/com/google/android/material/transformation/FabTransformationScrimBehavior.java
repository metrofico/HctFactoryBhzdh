package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {
   public static final long COLLAPSE_DELAY = 0L;
   public static final long COLLAPSE_DURATION = 150L;
   public static final long EXPAND_DELAY = 75L;
   public static final long EXPAND_DURATION = 150L;
   private final MotionTiming collapseTiming = new MotionTiming(0L, 150L);
   private final MotionTiming expandTiming = new MotionTiming(75L, 150L);

   public FabTransformationScrimBehavior() {
   }

   public FabTransformationScrimBehavior(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private void createScrimAnimation(View var1, boolean var2, boolean var3, List var4, List var5) {
      MotionTiming var7;
      if (var2) {
         var7 = this.expandTiming;
      } else {
         var7 = this.collapseTiming;
      }

      ObjectAnimator var6;
      if (var2) {
         if (!var3) {
            var1.setAlpha(0.0F);
         }

         var6 = ObjectAnimator.ofFloat(var1, View.ALPHA, new float[]{1.0F});
      } else {
         var6 = ObjectAnimator.ofFloat(var1, View.ALPHA, new float[]{0.0F});
      }

      var7.apply(var6);
      var4.add(var6);
   }

   public boolean layoutDependsOn(CoordinatorLayout var1, View var2, View var3) {
      return var3 instanceof FloatingActionButton;
   }

   protected AnimatorSet onCreateExpandedStateChangeAnimation(View var1, View var2, boolean var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      this.createScrimAnimation(var2, var3, var4, var5, new ArrayList());
      AnimatorSet var6 = new AnimatorSet();
      AnimatorSetCompat.playTogether(var6, var5);
      var6.addListener(new AnimatorListenerAdapter(this, var3, var2) {
         final FabTransformationScrimBehavior this$0;
         final View val$child;
         final boolean val$expanded;

         {
            this.this$0 = var1;
            this.val$expanded = var2;
            this.val$child = var3;
         }

         public void onAnimationEnd(Animator var1) {
            if (!this.val$expanded) {
               this.val$child.setVisibility(4);
            }

         }

         public void onAnimationStart(Animator var1) {
            if (this.val$expanded) {
               this.val$child.setVisibility(0);
            }

         }
      });
      return var6;
   }

   public boolean onTouchEvent(CoordinatorLayout var1, View var2, MotionEvent var3) {
      return super.onTouchEvent(var1, var2, var3);
   }
}
