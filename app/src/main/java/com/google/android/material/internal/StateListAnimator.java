package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

public final class StateListAnimator {
   private final Animator.AnimatorListener animationListener = new AnimatorListenerAdapter(this) {
      final StateListAnimator this$0;

      {
         this.this$0 = var1;
      }

      public void onAnimationEnd(Animator var1) {
         if (this.this$0.runningAnimator == var1) {
            this.this$0.runningAnimator = null;
         }

      }
   };
   private Tuple lastMatch = null;
   ValueAnimator runningAnimator = null;
   private final ArrayList tuples = new ArrayList();

   private void cancel() {
      ValueAnimator var1 = this.runningAnimator;
      if (var1 != null) {
         var1.cancel();
         this.runningAnimator = null;
      }

   }

   private void start(Tuple var1) {
      ValueAnimator var2 = var1.animator;
      this.runningAnimator = var2;
      var2.start();
   }

   public void addState(int[] var1, ValueAnimator var2) {
      Tuple var3 = new Tuple(var1, var2);
      var2.addListener(this.animationListener);
      this.tuples.add(var3);
   }

   public void jumpToCurrentState() {
      ValueAnimator var1 = this.runningAnimator;
      if (var1 != null) {
         var1.end();
         this.runningAnimator = null;
      }

   }

   public void setState(int[] var1) {
      int var3 = this.tuples.size();
      int var2 = 0;

      Tuple var4;
      Tuple var5;
      while(true) {
         if (var2 >= var3) {
            var5 = null;
            break;
         }

         var4 = (Tuple)this.tuples.get(var2);
         if (StateSet.stateSetMatches(var4.specs, var1)) {
            var5 = var4;
            break;
         }

         ++var2;
      }

      var4 = this.lastMatch;
      if (var5 != var4) {
         if (var4 != null) {
            this.cancel();
         }

         this.lastMatch = var5;
         if (var5 != null) {
            this.start(var5);
         }

      }
   }

   static class Tuple {
      final ValueAnimator animator;
      final int[] specs;

      Tuple(int[] var1, ValueAnimator var2) {
         this.specs = var1;
         this.animator = var2;
      }
   }
}
