package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {
   private AnimatorSet currentAnimation;

   public ExpandableTransformationBehavior() {
   }

   public ExpandableTransformationBehavior(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   protected abstract AnimatorSet onCreateExpandedStateChangeAnimation(View var1, View var2, boolean var3, boolean var4);

   protected boolean onExpandedStateChange(View var1, View var2, boolean var3, boolean var4) {
      AnimatorSet var6 = this.currentAnimation;
      boolean var5;
      if (var6 != null) {
         var5 = true;
      } else {
         var5 = false;
      }

      if (var5) {
         var6.cancel();
      }

      AnimatorSet var7 = this.onCreateExpandedStateChangeAnimation(var1, var2, var3, var5);
      this.currentAnimation = var7;
      var7.addListener(new AnimatorListenerAdapter(this) {
         final ExpandableTransformationBehavior this$0;

         {
            this.this$0 = var1;
         }

         public void onAnimationEnd(Animator var1) {
            this.this$0.currentAnimation = null;
         }
      });
      this.currentAnimation.start();
      if (!var4) {
         this.currentAnimation.end();
      }

      return true;
   }
}
