package com.hzbhd.canbus.util;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AnimationUtil {
   private View mView;
   private WeightAnimation mWeightAnimation = new WeightAnimation(this);
   private WidthAnimation mWidthAnimation = new WidthAnimation(this);

   public boolean playWeightAnimation(float var1, long var2) {
      WeightAnimation var4 = this.mWeightAnimation;
      return var4 == null ? false : var4.play(var1, var2);
   }

   public boolean playWidthAnimation(int var1, long var2) {
      WidthAnimation var4 = this.mWidthAnimation;
      return var4 == null ? false : var4.play(var1, var2);
   }

   public void setView(View var1) {
      this.mView = var1;
   }

   private class WeightAnimation {
      private float mCurrentValue;
      final AnimationUtil this$0;

      private WeightAnimation(AnimationUtil var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      WeightAnimation(AnimationUtil var1, Object var2) {
         this(var1);
      }

      private ValueAnimator getWeightAnimator(float var1, long var2) {
         ValueAnimator var4 = ValueAnimator.ofFloat(new float[]{this.mCurrentValue, var1});
         var4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            final WeightAnimation this$1;

            {
               this.this$1 = var1;
            }

            public void onAnimationUpdate(ValueAnimator var1) {
               this.this$1.mCurrentValue = (Float)var1.getAnimatedValue();
               LinearLayout.LayoutParams var2 = new LinearLayout.LayoutParams(0, -1, this.this$1.mCurrentValue);
               this.this$1.this$0.mView.setLayoutParams(var2);
            }
         });
         var4.setDuration(var2);
         return var4;
      }

      private boolean play(float var1, long var2) {
         if (var1 == this.mCurrentValue) {
            return false;
         } else {
            this.getWeightAnimator(var1, var2).start();
            return true;
         }
      }
   }

   private class WidthAnimation {
      private int mCurrentValue;
      final AnimationUtil this$0;

      private WidthAnimation(AnimationUtil var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      WidthAnimation(AnimationUtil var1, Object var2) {
         this(var1);
      }

      private ValueAnimator getWidthAnimator(int var1, long var2) {
         ValueAnimator var4 = ValueAnimator.ofFloat(new float[]{(float)this.mCurrentValue, (float)var1});
         var4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            final WidthAnimation this$1;

            {
               this.this$1 = var1;
            }

            public void onAnimationUpdate(ValueAnimator var1) {
               this.this$1.mCurrentValue = Math.round((Float)var1.getAnimatedValue());
               RelativeLayout.LayoutParams var2 = new RelativeLayout.LayoutParams(this.this$1.mCurrentValue, -1);
               this.this$1.this$0.mView.setLayoutParams(var2);
            }
         });
         var4.setDuration(var2);
         return var4;
      }

      private boolean play(int var1, long var2) {
         if (var1 == this.mCurrentValue) {
            return false;
         } else {
            this.getWidthAnimator(var1, var2).start();
            return true;
         }
      }
   }
}
