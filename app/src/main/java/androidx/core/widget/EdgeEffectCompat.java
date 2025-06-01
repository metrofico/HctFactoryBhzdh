package androidx.core.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.EdgeEffect;
import androidx.core.os.BuildCompat;

public final class EdgeEffectCompat {
   private EdgeEffect mEdgeEffect;

   @Deprecated
   public EdgeEffectCompat(Context var1) {
      this.mEdgeEffect = new EdgeEffect(var1);
   }

   public static EdgeEffect create(Context var0, AttributeSet var1) {
      return BuildCompat.isAtLeastS() ? EdgeEffectCompat.Api31Impl.create(var0, var1) : new EdgeEffect(var0);
   }

   public static float getDistance(EdgeEffect var0) {
      return BuildCompat.isAtLeastS() ? EdgeEffectCompat.Api31Impl.getDistance(var0) : 0.0F;
   }

   public static void onPull(EdgeEffect var0, float var1, float var2) {
      if (VERSION.SDK_INT >= 21) {
         var0.onPull(var1, var2);
      } else {
         var0.onPull(var1);
      }

   }

   public static float onPullDistance(EdgeEffect var0, float var1, float var2) {
      if (BuildCompat.isAtLeastS()) {
         return EdgeEffectCompat.Api31Impl.onPullDistance(var0, var1, var2);
      } else {
         onPull(var0, var1, var2);
         return var1;
      }
   }

   @Deprecated
   public boolean draw(Canvas var1) {
      return this.mEdgeEffect.draw(var1);
   }

   @Deprecated
   public void finish() {
      this.mEdgeEffect.finish();
   }

   @Deprecated
   public boolean isFinished() {
      return this.mEdgeEffect.isFinished();
   }

   @Deprecated
   public boolean onAbsorb(int var1) {
      this.mEdgeEffect.onAbsorb(var1);
      return true;
   }

   @Deprecated
   public boolean onPull(float var1) {
      this.mEdgeEffect.onPull(var1);
      return true;
   }

   @Deprecated
   public boolean onPull(float var1, float var2) {
      onPull(this.mEdgeEffect, var1, var2);
      return true;
   }

   @Deprecated
   public boolean onRelease() {
      this.mEdgeEffect.onRelease();
      return this.mEdgeEffect.isFinished();
   }

   @Deprecated
   public void setSize(int var1, int var2) {
      this.mEdgeEffect.setSize(var1, var2);
   }

   private static class Api31Impl {
      public static EdgeEffect create(Context var0, AttributeSet var1) {
         try {
            EdgeEffect var4 = new EdgeEffect(var0, var1);
            return var4;
         } finally {
            return new EdgeEffect(var0);
         }
      }

      public static float getDistance(EdgeEffect var0) {
         try {
            float var1 = var0.getDistance();
            return var1;
         } finally {
            ;
         }
      }

      public static float onPullDistance(EdgeEffect var0, float var1, float var2) {
         try {
            float var3 = var0.onPullDistance(var1, var2);
            return var3;
         } finally {
            var0.onPull(var1, var2);
            return 0.0F;
         }
      }
   }
}
