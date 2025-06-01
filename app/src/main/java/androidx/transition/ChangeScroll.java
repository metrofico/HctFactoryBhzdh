package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeScroll extends Transition {
   private static final String[] PROPERTIES = new String[]{"android:changeScroll:x", "android:changeScroll:y"};
   private static final String PROPNAME_SCROLL_X = "android:changeScroll:x";
   private static final String PROPNAME_SCROLL_Y = "android:changeScroll:y";

   public ChangeScroll() {
   }

   public ChangeScroll(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private void captureValues(TransitionValues var1) {
      var1.values.put("android:changeScroll:x", var1.view.getScrollX());
      var1.values.put("android:changeScroll:y", var1.view.getScrollY());
   }

   public void captureEndValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public void captureStartValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public Animator createAnimator(ViewGroup var1, TransitionValues var2, TransitionValues var3) {
      View var9 = null;
      Object var8 = null;
      Animator var10 = var9;
      if (var2 != null) {
         if (var3 == null) {
            var10 = var9;
         } else {
            var9 = var3.view;
            int var6 = (Integer)var2.values.get("android:changeScroll:x");
            int var5 = (Integer)var3.values.get("android:changeScroll:x");
            int var7 = (Integer)var2.values.get("android:changeScroll:y");
            int var4 = (Integer)var3.values.get("android:changeScroll:y");
            ObjectAnimator var11;
            if (var6 != var5) {
               var9.setScrollX(var6);
               var11 = ObjectAnimator.ofInt(var9, "scrollX", new int[]{var6, var5});
            } else {
               var11 = null;
            }

            ObjectAnimator var12 = (ObjectAnimator)var8;
            if (var7 != var4) {
               var9.setScrollY(var7);
               var12 = ObjectAnimator.ofInt(var9, "scrollY", new int[]{var7, var4});
            }

            var10 = TransitionUtils.mergeAnimators(var11, var12);
         }
      }

      return var10;
   }

   public String[] getTransitionProperties() {
      return PROPERTIES;
   }
}
