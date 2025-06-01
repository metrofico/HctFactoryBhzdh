package androidx.core.view;

import android.os.Build.VERSION;
import android.view.WindowInsetsAnimationController;
import androidx.core.graphics.Insets;

public final class WindowInsetsAnimationControllerCompat {
   private final Impl mImpl;

   WindowInsetsAnimationControllerCompat() {
      if (VERSION.SDK_INT < 30) {
         this.mImpl = new Impl();
      } else {
         throw new UnsupportedOperationException("On API 30+, the constructor taking a " + WindowInsetsAnimationController.class.getSimpleName() + " as parameter");
      }
   }

   WindowInsetsAnimationControllerCompat(WindowInsetsAnimationController var1) {
      this.mImpl = new Impl30(var1);
   }

   public void finish(boolean var1) {
      this.mImpl.finish(var1);
   }

   public float getCurrentAlpha() {
      return this.mImpl.getCurrentAlpha();
   }

   public float getCurrentFraction() {
      return this.mImpl.getCurrentFraction();
   }

   public Insets getCurrentInsets() {
      return this.mImpl.getCurrentInsets();
   }

   public Insets getHiddenStateInsets() {
      return this.mImpl.getHiddenStateInsets();
   }

   public Insets getShownStateInsets() {
      return this.mImpl.getShownStateInsets();
   }

   public int getTypes() {
      return this.mImpl.getTypes();
   }

   public boolean isCancelled() {
      return this.mImpl.isCancelled();
   }

   public boolean isFinished() {
      return this.mImpl.isFinished();
   }

   public boolean isReady() {
      boolean var1;
      if (!this.isFinished() && !this.isCancelled()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void setInsetsAndAlpha(Insets var1, float var2, float var3) {
      this.mImpl.setInsetsAndAlpha(var1, var2, var3);
   }

   private static class Impl {
      Impl() {
      }

      void finish(boolean var1) {
      }

      public float getCurrentAlpha() {
         return 0.0F;
      }

      public float getCurrentFraction() {
         return 0.0F;
      }

      public Insets getCurrentInsets() {
         return Insets.NONE;
      }

      public Insets getHiddenStateInsets() {
         return Insets.NONE;
      }

      public Insets getShownStateInsets() {
         return Insets.NONE;
      }

      public int getTypes() {
         return 0;
      }

      boolean isCancelled() {
         return true;
      }

      boolean isFinished() {
         return false;
      }

      public boolean isReady() {
         return false;
      }

      public void setInsetsAndAlpha(Insets var1, float var2, float var3) {
      }
   }

   private static class Impl30 extends Impl {
      private final WindowInsetsAnimationController mController;

      Impl30(WindowInsetsAnimationController var1) {
         this.mController = var1;
      }

      void finish(boolean var1) {
         this.mController.finish(var1);
      }

      public float getCurrentAlpha() {
         return this.mController.getCurrentAlpha();
      }

      public float getCurrentFraction() {
         return this.mController.getCurrentFraction();
      }

      public Insets getCurrentInsets() {
         return Insets.toCompatInsets(this.mController.getCurrentInsets());
      }

      public Insets getHiddenStateInsets() {
         return Insets.toCompatInsets(this.mController.getHiddenStateInsets());
      }

      public Insets getShownStateInsets() {
         return Insets.toCompatInsets(this.mController.getShownStateInsets());
      }

      public int getTypes() {
         return this.mController.getTypes();
      }

      boolean isCancelled() {
         return this.mController.isCancelled();
      }

      boolean isFinished() {
         return this.mController.isFinished();
      }

      public boolean isReady() {
         return this.mController.isReady();
      }

      public void setInsetsAndAlpha(Insets var1, float var2, float var3) {
         WindowInsetsAnimationController var4 = this.mController;
         android.graphics.Insets var5;
         if (var1 == null) {
            var5 = null;
         } else {
            var5 = var1.toPlatformInsets();
         }

         var4.setInsetsAndAlpha(var5, var2, var3);
      }
   }
}
