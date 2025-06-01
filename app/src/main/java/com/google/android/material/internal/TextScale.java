package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import java.util.Map;

public class TextScale extends Transition {
   private static final String PROPNAME_SCALE = "android:textscale:scale";

   private void captureValues(TransitionValues var1) {
      if (var1.view instanceof TextView) {
         TextView var2 = (TextView)var1.view;
         var1.values.put("android:textscale:scale", var2.getScaleX());
      }

   }

   public void captureEndValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public void captureStartValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public Animator createAnimator(ViewGroup var1, TransitionValues var2, TransitionValues var3) {
      TextView var6 = null;
      ValueAnimator var7 = var6;
      if (var2 != null) {
         var7 = var6;
         if (var3 != null) {
            var7 = var6;
            if (var2.view instanceof TextView) {
               if (!(var3.view instanceof TextView)) {
                  var7 = var6;
               } else {
                  var6 = (TextView)var3.view;
                  Map var9 = var2.values;
                  Map var10 = var3.values;
                  Object var8 = var9.get("android:textscale:scale");
                  float var5 = 1.0F;
                  float var4;
                  if (var8 != null) {
                     var4 = (Float)var9.get("android:textscale:scale");
                  } else {
                     var4 = 1.0F;
                  }

                  if (var10.get("android:textscale:scale") != null) {
                     var5 = (Float)var10.get("android:textscale:scale");
                  }

                  if (var4 == var5) {
                     return null;
                  }

                  var7 = ValueAnimator.ofFloat(new float[]{var4, var5});
                  var7.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this, var6) {
                     final TextScale this$0;
                     final TextView val$view;

                     {
                        this.this$0 = var1;
                        this.val$view = var2;
                     }

                     public void onAnimationUpdate(ValueAnimator var1) {
                        float var2 = (Float)var1.getAnimatedValue();
                        this.val$view.setScaleX(var2);
                        this.val$view.setScaleY(var2);
                     }
                  });
               }
            }
         }
      }

      return var7;
   }
}
