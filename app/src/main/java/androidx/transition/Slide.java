package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;

public class Slide extends Visibility {
   private static final String PROPNAME_SCREEN_POSITION = "android:slide:screenPosition";
   private static final TimeInterpolator sAccelerate = new AccelerateInterpolator();
   private static final CalculateSlide sCalculateBottom = new CalculateSlideVertical() {
      public float getGoneY(ViewGroup var1, View var2) {
         return var2.getTranslationY() + (float)var1.getHeight();
      }
   };
   private static final CalculateSlide sCalculateEnd = new CalculateSlideHorizontal() {
      public float getGoneX(ViewGroup var1, View var2) {
         int var5 = ViewCompat.getLayoutDirection(var1);
         boolean var4 = true;
         if (var5 != 1) {
            var4 = false;
         }

         float var3;
         if (var4) {
            var3 = var2.getTranslationX() - (float)var1.getWidth();
         } else {
            var3 = var2.getTranslationX() + (float)var1.getWidth();
         }

         return var3;
      }
   };
   private static final CalculateSlide sCalculateLeft = new CalculateSlideHorizontal() {
      public float getGoneX(ViewGroup var1, View var2) {
         return var2.getTranslationX() - (float)var1.getWidth();
      }
   };
   private static final CalculateSlide sCalculateRight = new CalculateSlideHorizontal() {
      public float getGoneX(ViewGroup var1, View var2) {
         return var2.getTranslationX() + (float)var1.getWidth();
      }
   };
   private static final CalculateSlide sCalculateStart = new CalculateSlideHorizontal() {
      public float getGoneX(ViewGroup var1, View var2) {
         int var5 = ViewCompat.getLayoutDirection(var1);
         boolean var4 = true;
         if (var5 != 1) {
            var4 = false;
         }

         float var3;
         if (var4) {
            var3 = var2.getTranslationX() + (float)var1.getWidth();
         } else {
            var3 = var2.getTranslationX() - (float)var1.getWidth();
         }

         return var3;
      }
   };
   private static final CalculateSlide sCalculateTop = new CalculateSlideVertical() {
      public float getGoneY(ViewGroup var1, View var2) {
         return var2.getTranslationY() - (float)var1.getHeight();
      }
   };
   private static final TimeInterpolator sDecelerate = new DecelerateInterpolator();
   private CalculateSlide mSlideCalculator;
   private int mSlideEdge;

   public Slide() {
      this.mSlideCalculator = sCalculateBottom;
      this.mSlideEdge = 80;
      this.setSlideEdge(80);
   }

   public Slide(int var1) {
      this.mSlideCalculator = sCalculateBottom;
      this.mSlideEdge = 80;
      this.setSlideEdge(var1);
   }

   public Slide(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mSlideCalculator = sCalculateBottom;
      this.mSlideEdge = 80;
      TypedArray var4 = var1.obtainStyledAttributes(var2, Styleable.SLIDE);
      int var3 = TypedArrayUtils.getNamedInt(var4, (XmlPullParser)var2, "slideEdge", 0, 80);
      var4.recycle();
      this.setSlideEdge(var3);
   }

   private void captureValues(TransitionValues var1) {
      View var3 = var1.view;
      int[] var2 = new int[2];
      var3.getLocationOnScreen(var2);
      var1.values.put("android:slide:screenPosition", var2);
   }

   public void captureEndValues(TransitionValues var1) {
      super.captureEndValues(var1);
      this.captureValues(var1);
   }

   public void captureStartValues(TransitionValues var1) {
      super.captureStartValues(var1);
      this.captureValues(var1);
   }

   public int getSlideEdge() {
      return this.mSlideEdge;
   }

   public Animator onAppear(ViewGroup var1, View var2, TransitionValues var3, TransitionValues var4) {
      if (var4 == null) {
         return null;
      } else {
         int[] var9 = (int[])var4.values.get("android:slide:screenPosition");
         float var5 = var2.getTranslationX();
         float var8 = var2.getTranslationY();
         float var6 = this.mSlideCalculator.getGoneX(var1, var2);
         float var7 = this.mSlideCalculator.getGoneY(var1, var2);
         return TranslationAnimationCreator.createAnimation(var2, var4, var9[0], var9[1], var6, var7, var5, var8, sDecelerate);
      }
   }

   public Animator onDisappear(ViewGroup var1, View var2, TransitionValues var3, TransitionValues var4) {
      if (var3 == null) {
         return null;
      } else {
         int[] var9 = (int[])var3.values.get("android:slide:screenPosition");
         float var5 = var2.getTranslationX();
         float var6 = var2.getTranslationY();
         float var7 = this.mSlideCalculator.getGoneX(var1, var2);
         float var8 = this.mSlideCalculator.getGoneY(var1, var2);
         return TranslationAnimationCreator.createAnimation(var2, var3, var9[0], var9[1], var5, var6, var7, var8, sAccelerate);
      }
   }

   public void setSlideEdge(int var1) {
      if (var1 != 3) {
         if (var1 != 5) {
            if (var1 != 48) {
               if (var1 != 80) {
                  if (var1 != 8388611) {
                     if (var1 != 8388613) {
                        throw new IllegalArgumentException("Invalid slide direction");
                     }

                     this.mSlideCalculator = sCalculateEnd;
                  } else {
                     this.mSlideCalculator = sCalculateStart;
                  }
               } else {
                  this.mSlideCalculator = sCalculateBottom;
               }
            } else {
               this.mSlideCalculator = sCalculateTop;
            }
         } else {
            this.mSlideCalculator = sCalculateRight;
         }
      } else {
         this.mSlideCalculator = sCalculateLeft;
      }

      this.mSlideEdge = var1;
      SidePropagation var2 = new SidePropagation();
      var2.setSide(var1);
      this.setPropagation(var2);
   }

   private interface CalculateSlide {
      float getGoneX(ViewGroup var1, View var2);

      float getGoneY(ViewGroup var1, View var2);
   }

   private abstract static class CalculateSlideHorizontal implements CalculateSlide {
      private CalculateSlideHorizontal() {
      }

      // $FF: synthetic method
      CalculateSlideHorizontal(Object var1) {
         this();
      }

      public float getGoneY(ViewGroup var1, View var2) {
         return var2.getTranslationY();
      }
   }

   private abstract static class CalculateSlideVertical implements CalculateSlide {
      private CalculateSlideVertical() {
      }

      // $FF: synthetic method
      CalculateSlideVertical(Object var1) {
         this();
      }

      public float getGoneX(ViewGroup var1, View var2) {
         return var2.getTranslationX();
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface GravityFlag {
   }
}
