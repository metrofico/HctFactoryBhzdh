package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;

public class ChangeClipBounds extends Transition {
   private static final String PROPNAME_BOUNDS = "android:clipBounds:bounds";
   private static final String PROPNAME_CLIP = "android:clipBounds:clip";
   private static final String[] sTransitionProperties = new String[]{"android:clipBounds:clip"};

   public ChangeClipBounds() {
   }

   public ChangeClipBounds(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private void captureValues(TransitionValues var1) {
      View var3 = var1.view;
      if (var3.getVisibility() != 8) {
         Rect var2 = ViewCompat.getClipBounds(var3);
         var1.values.put("android:clipBounds:clip", var2);
         if (var2 == null) {
            var2 = new Rect(0, 0, var3.getWidth(), var3.getHeight());
            var1.values.put("android:clipBounds:bounds", var2);
         }

      }
   }

   public void captureEndValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public void captureStartValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public Animator createAnimator(ViewGroup var1, TransitionValues var2, TransitionValues var3) {
      Rect var5 = null;
      ObjectAnimator var7 = var5;
      if (var2 != null) {
         var7 = var5;
         if (var3 != null) {
            var7 = var5;
            if (var2.values.containsKey("android:clipBounds:clip")) {
               if (!var3.values.containsKey("android:clipBounds:clip")) {
                  var7 = var5;
               } else {
                  Rect var6 = (Rect)var2.values.get("android:clipBounds:clip");
                  var5 = (Rect)var3.values.get("android:clipBounds:clip");
                  boolean var4;
                  if (var5 == null) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  if (var6 == null && var5 == null) {
                     return null;
                  }

                  Rect var8;
                  Rect var9;
                  if (var6 == null) {
                     var8 = (Rect)var2.values.get("android:clipBounds:bounds");
                     var9 = var5;
                  } else {
                     var8 = var6;
                     var9 = var5;
                     if (var5 == null) {
                        var9 = (Rect)var3.values.get("android:clipBounds:bounds");
                        var8 = var6;
                     }
                  }

                  if (var8.equals(var9)) {
                     return null;
                  }

                  ViewCompat.setClipBounds(var3.view, var8);
                  RectEvaluator var11 = new RectEvaluator(new Rect());
                  ObjectAnimator var10 = ObjectAnimator.ofObject(var3.view, ViewUtils.CLIP_BOUNDS, var11, new Rect[]{var8, var9});
                  var7 = var10;
                  if (var4) {
                     var10.addListener(new AnimatorListenerAdapter(this, var3.view) {
                        final ChangeClipBounds this$0;
                        final View val$endView;

                        {
                           this.this$0 = var1;
                           this.val$endView = var2;
                        }

                        public void onAnimationEnd(Animator var1) {
                           ViewCompat.setClipBounds(this.val$endView, (Rect)null);
                        }
                     });
                     var7 = var10;
                  }
               }
            }
         }
      }

      return var7;
   }

   public String[] getTransitionProperties() {
      return sTransitionProperties;
   }
}
