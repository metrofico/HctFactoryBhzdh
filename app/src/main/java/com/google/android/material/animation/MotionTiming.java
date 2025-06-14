package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class MotionTiming {
   private long delay;
   private long duration;
   private TimeInterpolator interpolator;
   private int repeatCount;
   private int repeatMode;

   public MotionTiming(long var1, long var3) {
      this.interpolator = null;
      this.repeatCount = 0;
      this.repeatMode = 1;
      this.delay = var1;
      this.duration = var3;
   }

   public MotionTiming(long var1, long var3, TimeInterpolator var5) {
      this.repeatCount = 0;
      this.repeatMode = 1;
      this.delay = var1;
      this.duration = var3;
      this.interpolator = var5;
   }

   static MotionTiming createFromAnimator(ValueAnimator var0) {
      MotionTiming var1 = new MotionTiming(var0.getStartDelay(), var0.getDuration(), getInterpolatorCompat(var0));
      var1.repeatCount = var0.getRepeatCount();
      var1.repeatMode = var0.getRepeatMode();
      return var1;
   }

   private static TimeInterpolator getInterpolatorCompat(ValueAnimator var0) {
      TimeInterpolator var1 = var0.getInterpolator();
      if (!(var1 instanceof AccelerateDecelerateInterpolator) && var1 != null) {
         if (var1 instanceof AccelerateInterpolator) {
            return AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
         } else {
            TimeInterpolator var2 = var1;
            if (var1 instanceof DecelerateInterpolator) {
               var2 = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
            }

            return var2;
         }
      } else {
         return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
      }
   }

   public void apply(Animator var1) {
      var1.setStartDelay(this.getDelay());
      var1.setDuration(this.getDuration());
      var1.setInterpolator(this.getInterpolator());
      if (var1 instanceof ValueAnimator) {
         ValueAnimator var2 = (ValueAnimator)var1;
         var2.setRepeatCount(this.getRepeatCount());
         var2.setRepeatMode(this.getRepeatMode());
      }

   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MotionTiming var2 = (MotionTiming)var1;
         if (this.getDelay() != var2.getDelay()) {
            return false;
         } else if (this.getDuration() != var2.getDuration()) {
            return false;
         } else if (this.getRepeatCount() != var2.getRepeatCount()) {
            return false;
         } else {
            return this.getRepeatMode() != var2.getRepeatMode() ? false : this.getInterpolator().getClass().equals(var2.getInterpolator().getClass());
         }
      } else {
         return false;
      }
   }

   public long getDelay() {
      return this.delay;
   }

   public long getDuration() {
      return this.duration;
   }

   public TimeInterpolator getInterpolator() {
      TimeInterpolator var1 = this.interpolator;
      if (var1 == null) {
         var1 = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
      }

      return var1;
   }

   public int getRepeatCount() {
      return this.repeatCount;
   }

   public int getRepeatMode() {
      return this.repeatMode;
   }

   public int hashCode() {
      return ((((int)(this.getDelay() ^ this.getDelay() >>> 32) * 31 + (int)(this.getDuration() ^ this.getDuration() >>> 32)) * 31 + this.getInterpolator().getClass().hashCode()) * 31 + this.getRepeatCount()) * 31 + this.getRepeatMode();
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append('\n');
      var1.append(this.getClass().getName());
      var1.append('{');
      var1.append(Integer.toHexString(System.identityHashCode(this)));
      var1.append(" delay: ");
      var1.append(this.getDelay());
      var1.append(" duration: ");
      var1.append(this.getDuration());
      var1.append(" interpolator: ");
      var1.append(this.getInterpolator().getClass());
      var1.append(" repeatCount: ");
      var1.append(this.getRepeatCount());
      var1.append(" repeatMode: ");
      var1.append(this.getRepeatMode());
      var1.append("}\n");
      return var1.toString();
   }
}
