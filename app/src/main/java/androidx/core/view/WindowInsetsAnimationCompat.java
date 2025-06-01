package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.R;
import androidx.core.graphics.Insets;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class WindowInsetsAnimationCompat {
   private static final boolean DEBUG = false;
   private static final String TAG = "WindowInsetsAnimCompat";
   private Impl mImpl;

   public WindowInsetsAnimationCompat(int var1, Interpolator var2, long var3) {
      if (VERSION.SDK_INT >= 30) {
         this.mImpl = new Impl30(var1, var2, var3);
      } else if (VERSION.SDK_INT >= 21) {
         this.mImpl = new Impl21(var1, var2, var3);
      } else {
         this.mImpl = new Impl(0, var2, var3);
      }

   }

   private WindowInsetsAnimationCompat(WindowInsetsAnimation var1) {
      this(0, (Interpolator)null, 0L);
      if (VERSION.SDK_INT >= 30) {
         this.mImpl = new Impl30(var1);
      }

   }

   static void setCallback(View var0, Callback var1) {
      if (VERSION.SDK_INT >= 30) {
         WindowInsetsAnimationCompat.Impl30.setCallback(var0, var1);
      } else if (VERSION.SDK_INT >= 21) {
         WindowInsetsAnimationCompat.Impl21.setCallback(var0, var1);
      }

   }

   static WindowInsetsAnimationCompat toWindowInsetsAnimationCompat(WindowInsetsAnimation var0) {
      return new WindowInsetsAnimationCompat(var0);
   }

   public float getAlpha() {
      return this.mImpl.getAlpha();
   }

   public long getDurationMillis() {
      return this.mImpl.getDurationMillis();
   }

   public float getFraction() {
      return this.mImpl.getFraction();
   }

   public float getInterpolatedFraction() {
      return this.mImpl.getInterpolatedFraction();
   }

   public Interpolator getInterpolator() {
      return this.mImpl.getInterpolator();
   }

   public int getTypeMask() {
      return this.mImpl.getTypeMask();
   }

   public void setAlpha(float var1) {
      this.mImpl.setAlpha(var1);
   }

   public void setFraction(float var1) {
      this.mImpl.setFraction(var1);
   }

   public static final class BoundsCompat {
      private final Insets mLowerBound;
      private final Insets mUpperBound;

      private BoundsCompat(WindowInsetsAnimation.Bounds var1) {
         this.mLowerBound = WindowInsetsAnimationCompat.Impl30.getLowerBounds(var1);
         this.mUpperBound = WindowInsetsAnimationCompat.Impl30.getHigherBounds(var1);
      }

      public BoundsCompat(Insets var1, Insets var2) {
         this.mLowerBound = var1;
         this.mUpperBound = var2;
      }

      public static BoundsCompat toBoundsCompat(WindowInsetsAnimation.Bounds var0) {
         return new BoundsCompat(var0);
      }

      public Insets getLowerBound() {
         return this.mLowerBound;
      }

      public Insets getUpperBound() {
         return this.mUpperBound;
      }

      public BoundsCompat inset(Insets var1) {
         return new BoundsCompat(WindowInsetsCompat.insetInsets(this.mLowerBound, var1.left, var1.top, var1.right, var1.bottom), WindowInsetsCompat.insetInsets(this.mUpperBound, var1.left, var1.top, var1.right, var1.bottom));
      }

      public WindowInsetsAnimation.Bounds toBounds() {
         return WindowInsetsAnimationCompat.Impl30.createPlatformBounds(this);
      }

      public String toString() {
         return "Bounds{lower=" + this.mLowerBound + " upper=" + this.mUpperBound + "}";
      }
   }

   public abstract static class Callback {
      public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;
      public static final int DISPATCH_MODE_STOP = 0;
      WindowInsets mDispachedInsets;
      private final int mDispatchMode;

      public Callback(int var1) {
         this.mDispatchMode = var1;
      }

      public final int getDispatchMode() {
         return this.mDispatchMode;
      }

      public void onEnd(WindowInsetsAnimationCompat var1) {
      }

      public void onPrepare(WindowInsetsAnimationCompat var1) {
      }

      public abstract WindowInsetsCompat onProgress(WindowInsetsCompat var1, List var2);

      public BoundsCompat onStart(WindowInsetsAnimationCompat var1, BoundsCompat var2) {
         return var2;
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface DispatchMode {
      }
   }

   private static class Impl {
      private float mAlpha;
      private final long mDurationMillis;
      private float mFraction;
      private final Interpolator mInterpolator;
      private final int mTypeMask;

      Impl(int var1, Interpolator var2, long var3) {
         this.mTypeMask = var1;
         this.mInterpolator = var2;
         this.mDurationMillis = var3;
      }

      public float getAlpha() {
         return this.mAlpha;
      }

      public long getDurationMillis() {
         return this.mDurationMillis;
      }

      public float getFraction() {
         return this.mFraction;
      }

      public float getInterpolatedFraction() {
         Interpolator var1 = this.mInterpolator;
         return var1 != null ? var1.getInterpolation(this.mFraction) : this.mFraction;
      }

      public Interpolator getInterpolator() {
         return this.mInterpolator;
      }

      public int getTypeMask() {
         return this.mTypeMask;
      }

      public void setAlpha(float var1) {
         this.mAlpha = var1;
      }

      public void setFraction(float var1) {
         this.mFraction = var1;
      }
   }

   private static class Impl21 extends Impl {
      Impl21(int var1, Interpolator var2, long var3) {
         super(var1, var2, var3);
      }

      static int buildAnimationMask(WindowInsetsCompat var0, WindowInsetsCompat var1) {
         int var3 = 1;

         int var2;
         int var4;
         for(var2 = 0; var3 <= 256; var2 = var4) {
            var4 = var2;
            if (!var0.getInsets(var3).equals(var1.getInsets(var3))) {
               var4 = var2 | var3;
            }

            var3 <<= 1;
         }

         return var2;
      }

      static BoundsCompat computeAnimationBounds(WindowInsetsCompat var0, WindowInsetsCompat var1, int var2) {
         Insets var3 = var0.getInsets(var2);
         Insets var4 = var1.getInsets(var2);
         return new BoundsCompat(Insets.of(Math.min(var3.left, var4.left), Math.min(var3.top, var4.top), Math.min(var3.right, var4.right), Math.min(var3.bottom, var4.bottom)), Insets.of(Math.max(var3.left, var4.left), Math.max(var3.top, var4.top), Math.max(var3.right, var4.right), Math.max(var3.bottom, var4.bottom)));
      }

      private static View.OnApplyWindowInsetsListener createProxyListener(View var0, Callback var1) {
         return new Impl21OnApplyWindowInsetsListener(var0, var1);
      }

      static void dispatchOnEnd(View var0, WindowInsetsAnimationCompat var1) {
         Callback var3 = getCallback(var0);
         if (var3 != null) {
            var3.onEnd(var1);
            if (var3.getDispatchMode() == 0) {
               return;
            }
         }

         if (var0 instanceof ViewGroup) {
            ViewGroup var4 = (ViewGroup)var0;

            for(int var2 = 0; var2 < var4.getChildCount(); ++var2) {
               dispatchOnEnd(var4.getChildAt(var2), var1);
            }
         }

      }

      static void dispatchOnPrepare(View var0, WindowInsetsAnimationCompat var1, WindowInsets var2, boolean var3) {
         Callback var6 = getCallback(var0);
         int var4 = 0;
         boolean var5 = var3;
         if (var6 != null) {
            var6.mDispachedInsets = var2;
            var5 = var3;
            if (!var3) {
               var6.onPrepare(var1);
               if (var6.getDispatchMode() == 0) {
                  var5 = true;
               } else {
                  var5 = false;
               }
            }
         }

         if (var0 instanceof ViewGroup) {
            for(ViewGroup var7 = (ViewGroup)var0; var4 < var7.getChildCount(); ++var4) {
               dispatchOnPrepare(var7.getChildAt(var4), var1, var2, var5);
            }
         }

      }

      static void dispatchOnProgress(View var0, WindowInsetsCompat var1, List var2) {
         Callback var5 = getCallback(var0);
         WindowInsetsCompat var4 = var1;
         if (var5 != null) {
            var4 = var5.onProgress(var1, var2);
            if (var5.getDispatchMode() == 0) {
               return;
            }
         }

         if (var0 instanceof ViewGroup) {
            ViewGroup var6 = (ViewGroup)var0;

            for(int var3 = 0; var3 < var6.getChildCount(); ++var3) {
               dispatchOnProgress(var6.getChildAt(var3), var4, var2);
            }
         }

      }

      static void dispatchOnStart(View var0, WindowInsetsAnimationCompat var1, BoundsCompat var2) {
         Callback var4 = getCallback(var0);
         if (var4 != null) {
            var4.onStart(var1, var2);
            if (var4.getDispatchMode() == 0) {
               return;
            }
         }

         if (var0 instanceof ViewGroup) {
            ViewGroup var5 = (ViewGroup)var0;

            for(int var3 = 0; var3 < var5.getChildCount(); ++var3) {
               dispatchOnStart(var5.getChildAt(var3), var1, var2);
            }
         }

      }

      static WindowInsets forwardToViewIfNeeded(View var0, WindowInsets var1) {
         return var0.getTag(R.id.tag_on_apply_window_listener) != null ? var1 : var0.onApplyWindowInsets(var1);
      }

      static Callback getCallback(View var0) {
         Object var1 = var0.getTag(R.id.tag_window_insets_animation_callback);
         Callback var2;
         if (var1 instanceof Impl21OnApplyWindowInsetsListener) {
            var2 = ((Impl21OnApplyWindowInsetsListener)var1).mCallback;
         } else {
            var2 = null;
         }

         return var2;
      }

      static WindowInsetsCompat interpolateInsets(WindowInsetsCompat var0, WindowInsetsCompat var1, float var2, int var3) {
         WindowInsetsCompat.Builder var7 = new WindowInsetsCompat.Builder(var0);

         for(int var6 = 1; var6 <= 256; var6 <<= 1) {
            if ((var3 & var6) == 0) {
               var7.setInsets(var6, var0.getInsets(var6));
            } else {
               Insets var8 = var0.getInsets(var6);
               Insets var9 = var1.getInsets(var6);
               float var5 = (float)(var8.left - var9.left);
               float var4 = 1.0F - var2;
               var7.setInsets(var6, WindowInsetsCompat.insetInsets(var8, (int)((double)(var5 * var4) + 0.5), (int)((double)((float)(var8.top - var9.top) * var4) + 0.5), (int)((double)((float)(var8.right - var9.right) * var4) + 0.5), (int)((double)((float)(var8.bottom - var9.bottom) * var4) + 0.5)));
            }
         }

         return var7.build();
      }

      static void setCallback(View var0, Callback var1) {
         Object var2 = var0.getTag(R.id.tag_on_apply_window_listener);
         if (var1 == null) {
            var0.setTag(R.id.tag_window_insets_animation_callback, (Object)null);
            if (var2 == null) {
               var0.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener)null);
            }
         } else {
            View.OnApplyWindowInsetsListener var3 = createProxyListener(var0, var1);
            var0.setTag(R.id.tag_window_insets_animation_callback, var3);
            if (var2 == null) {
               var0.setOnApplyWindowInsetsListener(var3);
            }
         }

      }

      private static class Impl21OnApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
         private static final int COMPAT_ANIMATION_DURATION = 160;
         final Callback mCallback;
         private WindowInsetsCompat mLastInsets;

         Impl21OnApplyWindowInsetsListener(View var1, Callback var2) {
            this.mCallback = var2;
            WindowInsetsCompat var3 = ViewCompat.getRootWindowInsets(var1);
            if (var3 != null) {
               var3 = (new WindowInsetsCompat.Builder(var3)).build();
            } else {
               var3 = null;
            }

            this.mLastInsets = var3;
         }

         public WindowInsets onApplyWindowInsets(View var1, WindowInsets var2) {
            if (!var1.isLaidOut()) {
               this.mLastInsets = WindowInsetsCompat.toWindowInsetsCompat(var2, var1);
               return WindowInsetsAnimationCompat.Impl21.forwardToViewIfNeeded(var1, var2);
            } else {
               WindowInsetsCompat var4 = WindowInsetsCompat.toWindowInsetsCompat(var2, var1);
               if (this.mLastInsets == null) {
                  this.mLastInsets = ViewCompat.getRootWindowInsets(var1);
               }

               if (this.mLastInsets == null) {
                  this.mLastInsets = var4;
                  return WindowInsetsAnimationCompat.Impl21.forwardToViewIfNeeded(var1, var2);
               } else {
                  Callback var5 = WindowInsetsAnimationCompat.Impl21.getCallback(var1);
                  if (var5 != null && Objects.equals(var5.mDispachedInsets, var2)) {
                     return WindowInsetsAnimationCompat.Impl21.forwardToViewIfNeeded(var1, var2);
                  } else {
                     int var3 = WindowInsetsAnimationCompat.Impl21.buildAnimationMask(var4, this.mLastInsets);
                     if (var3 == 0) {
                        return WindowInsetsAnimationCompat.Impl21.forwardToViewIfNeeded(var1, var2);
                     } else {
                        WindowInsetsCompat var9 = this.mLastInsets;
                        WindowInsetsAnimationCompat var6 = new WindowInsetsAnimationCompat(var3, new DecelerateInterpolator(), 160L);
                        var6.setFraction(0.0F);
                        ValueAnimator var8 = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F}).setDuration(var6.getDurationMillis());
                        BoundsCompat var7 = WindowInsetsAnimationCompat.Impl21.computeAnimationBounds(var4, var9, var3);
                        WindowInsetsAnimationCompat.Impl21.dispatchOnPrepare(var1, var6, var2, false);
                        var8.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this, var6, var4, var9, var3, var1) {
                           final Impl21OnApplyWindowInsetsListener this$0;
                           final WindowInsetsAnimationCompat val$anim;
                           final int val$animationMask;
                           final WindowInsetsCompat val$startingInsets;
                           final WindowInsetsCompat val$targetInsets;
                           final View val$v;

                           {
                              this.this$0 = var1;
                              this.val$anim = var2;
                              this.val$targetInsets = var3;
                              this.val$startingInsets = var4;
                              this.val$animationMask = var5;
                              this.val$v = var6;
                           }

                           public void onAnimationUpdate(ValueAnimator var1) {
                              this.val$anim.setFraction(var1.getAnimatedFraction());
                              WindowInsetsCompat var3 = WindowInsetsAnimationCompat.Impl21.interpolateInsets(this.val$targetInsets, this.val$startingInsets, this.val$anim.getInterpolatedFraction(), this.val$animationMask);
                              List var2 = Collections.singletonList(this.val$anim);
                              WindowInsetsAnimationCompat.Impl21.dispatchOnProgress(this.val$v, var3, var2);
                           }
                        });
                        var8.addListener(new AnimatorListenerAdapter(this, var6, var1) {
                           final Impl21OnApplyWindowInsetsListener this$0;
                           final WindowInsetsAnimationCompat val$anim;
                           final View val$v;

                           {
                              this.this$0 = var1;
                              this.val$anim = var2;
                              this.val$v = var3;
                           }

                           public void onAnimationEnd(Animator var1) {
                              this.val$anim.setFraction(1.0F);
                              WindowInsetsAnimationCompat.Impl21.dispatchOnEnd(this.val$v, this.val$anim);
                           }
                        });
                        OneShotPreDrawListener.add(var1, new Runnable(this, var1, var6, var7, var8) {
                           final Impl21OnApplyWindowInsetsListener this$0;
                           final WindowInsetsAnimationCompat val$anim;
                           final BoundsCompat val$animationBounds;
                           final ValueAnimator val$animator;
                           final View val$v;

                           {
                              this.this$0 = var1;
                              this.val$v = var2;
                              this.val$anim = var3;
                              this.val$animationBounds = var4;
                              this.val$animator = var5;
                           }

                           public void run() {
                              WindowInsetsAnimationCompat.Impl21.dispatchOnStart(this.val$v, this.val$anim, this.val$animationBounds);
                              this.val$animator.start();
                           }
                        });
                        this.mLastInsets = var4;
                        return WindowInsetsAnimationCompat.Impl21.forwardToViewIfNeeded(var1, var2);
                     }
                  }
               }
            }
         }
      }
   }

   private static class Impl30 extends Impl {
      private final WindowInsetsAnimation mWrapped;

      Impl30(int var1, Interpolator var2, long var3) {
         this(new WindowInsetsAnimation(var1, var2, var3));
      }

      Impl30(WindowInsetsAnimation var1) {
         super(0, (Interpolator)null, 0L);
         this.mWrapped = var1;
      }

      public static WindowInsetsAnimation.Bounds createPlatformBounds(BoundsCompat var0) {
         return new WindowInsetsAnimation.Bounds(var0.getLowerBound().toPlatformInsets(), var0.getUpperBound().toPlatformInsets());
      }

      public static Insets getHigherBounds(WindowInsetsAnimation.Bounds var0) {
         return Insets.toCompatInsets(var0.getUpperBound());
      }

      public static Insets getLowerBounds(WindowInsetsAnimation.Bounds var0) {
         return Insets.toCompatInsets(var0.getLowerBound());
      }

      public static void setCallback(View var0, Callback var1) {
         ProxyCallback var2;
         if (var1 != null) {
            var2 = new ProxyCallback(var1);
         } else {
            var2 = null;
         }

         var0.setWindowInsetsAnimationCallback(var2);
      }

      public long getDurationMillis() {
         return this.mWrapped.getDurationMillis();
      }

      public float getFraction() {
         return this.mWrapped.getFraction();
      }

      public float getInterpolatedFraction() {
         return this.mWrapped.getInterpolatedFraction();
      }

      public Interpolator getInterpolator() {
         return this.mWrapped.getInterpolator();
      }

      public int getTypeMask() {
         return this.mWrapped.getTypeMask();
      }

      public void setFraction(float var1) {
         this.mWrapped.setFraction(var1);
      }

      private static class ProxyCallback extends WindowInsetsAnimation.Callback {
         private final HashMap mAnimations = new HashMap();
         private final Callback mCompat;
         private List mRORunningAnimations;
         private ArrayList mTmpRunningAnimations;

         ProxyCallback(Callback var1) {
            super(var1.getDispatchMode());
            this.mCompat = var1;
         }

         private WindowInsetsAnimationCompat getWindowInsetsAnimationCompat(WindowInsetsAnimation var1) {
            WindowInsetsAnimationCompat var3 = (WindowInsetsAnimationCompat)this.mAnimations.get(var1);
            WindowInsetsAnimationCompat var2 = var3;
            if (var3 == null) {
               var2 = WindowInsetsAnimationCompat.toWindowInsetsAnimationCompat(var1);
               this.mAnimations.put(var1, var2);
            }

            return var2;
         }

         public void onEnd(WindowInsetsAnimation var1) {
            this.mCompat.onEnd(this.getWindowInsetsAnimationCompat(var1));
            this.mAnimations.remove(var1);
         }

         public void onPrepare(WindowInsetsAnimation var1) {
            this.mCompat.onPrepare(this.getWindowInsetsAnimationCompat(var1));
         }

         public WindowInsets onProgress(WindowInsets var1, List var2) {
            ArrayList var4 = this.mTmpRunningAnimations;
            if (var4 == null) {
               var4 = new ArrayList(var2.size());
               this.mTmpRunningAnimations = var4;
               this.mRORunningAnimations = Collections.unmodifiableList(var4);
            } else {
               var4.clear();
            }

            for(int var3 = var2.size() - 1; var3 >= 0; --var3) {
               WindowInsetsAnimation var5 = (WindowInsetsAnimation)var2.get(var3);
               WindowInsetsAnimationCompat var6 = this.getWindowInsetsAnimationCompat(var5);
               var6.setFraction(var5.getFraction());
               this.mTmpRunningAnimations.add(var6);
            }

            return this.mCompat.onProgress(WindowInsetsCompat.toWindowInsetsCompat(var1), this.mRORunningAnimations).toWindowInsets();
         }

         public WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation var1, WindowInsetsAnimation.Bounds var2) {
            return this.mCompat.onStart(this.getWindowInsetsAnimationCompat(var1), WindowInsetsAnimationCompat.BoundsCompat.toBoundsCompat(var2)).toBounds();
         }
      }
   }
}
