package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

class CircleImageView extends ImageView {
   private static final int FILL_SHADOW_COLOR = 1023410176;
   private static final int KEY_SHADOW_COLOR = 503316480;
   private static final int SHADOW_ELEVATION = 4;
   private static final float SHADOW_RADIUS = 3.5F;
   private static final float X_OFFSET = 0.0F;
   private static final float Y_OFFSET = 1.75F;
   private Animation.AnimationListener mListener;
   int mShadowRadius;

   CircleImageView(Context var1, int var2) {
      super(var1);
      float var3 = this.getContext().getResources().getDisplayMetrics().density;
      int var5 = (int)(1.75F * var3);
      int var4 = (int)(0.0F * var3);
      this.mShadowRadius = (int)(3.5F * var3);
      ShapeDrawable var6;
      if (this.elevationSupported()) {
         var6 = new ShapeDrawable(new OvalShape());
         ViewCompat.setElevation(this, var3 * 4.0F);
      } else {
         var6 = new ShapeDrawable(new OvalShadow(this, this.mShadowRadius));
         this.setLayerType(1, var6.getPaint());
         var6.getPaint().setShadowLayer((float)this.mShadowRadius, (float)var4, (float)var5, 503316480);
         var4 = this.mShadowRadius;
         this.setPadding(var4, var4, var4, var4);
      }

      var6.getPaint().setColor(var2);
      ViewCompat.setBackground(this, var6);
   }

   private boolean elevationSupported() {
      boolean var1;
      if (VERSION.SDK_INT >= 21) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void onAnimationEnd() {
      super.onAnimationEnd();
      Animation.AnimationListener var1 = this.mListener;
      if (var1 != null) {
         var1.onAnimationEnd(this.getAnimation());
      }

   }

   public void onAnimationStart() {
      super.onAnimationStart();
      Animation.AnimationListener var1 = this.mListener;
      if (var1 != null) {
         var1.onAnimationStart(this.getAnimation());
      }

   }

   protected void onMeasure(int var1, int var2) {
      super.onMeasure(var1, var2);
      if (!this.elevationSupported()) {
         this.setMeasuredDimension(this.getMeasuredWidth() + this.mShadowRadius * 2, this.getMeasuredHeight() + this.mShadowRadius * 2);
      }

   }

   public void setAnimationListener(Animation.AnimationListener var1) {
      this.mListener = var1;
   }

   public void setBackgroundColor(int var1) {
      if (this.getBackground() instanceof ShapeDrawable) {
         ((ShapeDrawable)this.getBackground()).getPaint().setColor(var1);
      }

   }

   public void setBackgroundColorRes(int var1) {
      this.setBackgroundColor(ContextCompat.getColor(this.getContext(), var1));
   }

   private class OvalShadow extends OvalShape {
      private RadialGradient mRadialGradient;
      private Paint mShadowPaint;
      final CircleImageView this$0;

      OvalShadow(CircleImageView var1, int var2) {
         this.this$0 = var1;
         this.mShadowPaint = new Paint();
         var1.mShadowRadius = var2;
         this.updateRadialGradient((int)this.rect().width());
      }

      private void updateRadialGradient(int var1) {
         float var3 = (float)(var1 / 2);
         float var2 = (float)this.this$0.mShadowRadius;
         Shader.TileMode var4 = TileMode.CLAMP;
         RadialGradient var5 = new RadialGradient(var3, var3, var2, new int[]{1023410176, 0}, (float[])null, var4);
         this.mRadialGradient = var5;
         this.mShadowPaint.setShader(var5);
      }

      public void draw(Canvas var1, Paint var2) {
         int var6 = this.this$0.getWidth();
         int var5 = this.this$0.getHeight();
         var6 /= 2;
         float var4 = (float)var6;
         float var3 = (float)(var5 / 2);
         var1.drawCircle(var4, var3, var4, this.mShadowPaint);
         var1.drawCircle(var4, var3, (float)(var6 - this.this$0.mShadowRadius), var2);
      }

      protected void onResize(float var1, float var2) {
         super.onResize(var1, var2);
         this.updateRadialGradient((int)var1);
      }
   }
}
