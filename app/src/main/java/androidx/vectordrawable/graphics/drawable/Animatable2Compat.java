package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;

public interface Animatable2Compat extends Animatable {
   void clearAnimationCallbacks();

   void registerAnimationCallback(AnimationCallback var1);

   boolean unregisterAnimationCallback(AnimationCallback var1);

   public abstract static class AnimationCallback {
      Animatable2.AnimationCallback mPlatformCallback;

      Animatable2.AnimationCallback getPlatformCallback() {
         if (this.mPlatformCallback == null) {
            this.mPlatformCallback = new Animatable2.AnimationCallback(this) {
               final AnimationCallback this$0;

               {
                  this.this$0 = var1;
               }

               public void onAnimationEnd(Drawable var1) {
                  this.this$0.onAnimationEnd(var1);
               }

               public void onAnimationStart(Drawable var1) {
                  this.this$0.onAnimationStart(var1);
               }
            };
         }

         return this.mPlatformCallback;
      }

      public void onAnimationEnd(Drawable var1) {
      }

      public void onAnimationStart(Drawable var1) {
      }
   }
}
