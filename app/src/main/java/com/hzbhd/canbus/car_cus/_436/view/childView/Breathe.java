package com.hzbhd.canbus.car_cus._436.view.childView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;

public class Breathe extends View {
   private static final int DEFAULT_DELAY = 15;
   private static final int DEFAULT_RADIUS = 20;
   private static final int DEFAULT_STROKE_WIDTH = 3;
   private static final String TAG = "OkView";
   private int currentFrame;
   private boolean isPlay;
   private int mDelay;
   private int mInitInnerRadius;
   private int mInitOuterRadius;
   private int mInnerAlpha;
   private float mInnerCx;
   private float mInnerCy;
   private Paint mInnerPaint;
   private float mInnerRadius;
   private int mOuterAlpha;
   private float mOuterCx;
   private float mOuterCy;
   private Paint mOuterPaint;
   private float mOuterRadius;
   private float mOuterStrokeWidth;
   private Runnable mRunnable;
   private int mWidth;

   public Breathe(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public Breathe(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public Breathe(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mInnerAlpha = 0;
      this.mOuterAlpha = 0;
      this.isPlay = false;
      this.mOuterStrokeWidth = 3.0F;
      this.mInitInnerRadius = 20;
      this.mDelay = 15;
      this.mRunnable = new Runnable(this) {
         final Breathe this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            if (this.this$0.isPlay && this.this$0.getAlpha() != 0.0F) {
               this.this$0.play();
               this.this$0.invalidate();
               Breathe var1 = this.this$0;
               var1.postDelayed(var1.mRunnable, (long)this.this$0.mDelay);
            } else {
               this.this$0.removeCallbacks(this);
            }

         }
      };
      this.currentFrame = 1;
   }

   private void initPaints() {
      Paint var1 = new Paint();
      this.mInnerPaint = var1;
      var1.setColor(-65536);
      this.mInnerPaint.setStyle(Style.FILL);
      this.mInnerPaint.setAntiAlias(true);
      var1 = new Paint();
      this.mOuterPaint = var1;
      var1.setColor(-65536);
      this.mOuterPaint.setStrokeWidth(this.mOuterStrokeWidth);
      this.mOuterPaint.setStyle(Style.STROKE);
      this.mOuterPaint.setAntiAlias(true);
   }

   private boolean innerCircleHide() {
      int var1 = (int)((double)this.mInnerAlpha - 15.3);
      this.mInnerAlpha = var1;
      if (var1 < 0) {
         this.mInnerAlpha = 0;
         return true;
      } else {
         return false;
      }
   }

   private boolean innerCircleShow() {
      int var1 = (int)((double)this.mInnerAlpha + 15.3);
      this.mInnerAlpha = var1;
      if ((float)var1 > 229.5F) {
         this.mInnerAlpha = 229;
         return true;
      } else {
         return false;
      }
   }

   private boolean outerCircleEnlarge() {
      float var3 = this.mOuterRadius;
      double var1 = (double)var3;
      int var4 = this.mInitOuterRadius;
      if (var1 <= (double)var4 * 1.5) {
         this.mOuterRadius = var3 + (float)var4 / 30.0F;
         return false;
      } else {
         return true;
      }
   }

   private boolean outerCircleHide() {
      int var1 = this.mOuterAlpha - 17;
      this.mOuterAlpha = var1;
      if (var1 < 0) {
         this.mOuterAlpha = 0;
         return true;
      } else {
         return false;
      }
   }

   private boolean outerCircleShow() {
      int var1 = this.mOuterAlpha + 17;
      this.mOuterAlpha = var1;
      if (var1 > 255) {
         this.mOuterAlpha = 255;
         return true;
      } else {
         return false;
      }
   }

   private void play() {
      int var1 = this.currentFrame;
      boolean var2;
      boolean var3;
      if (var1 != 1) {
         if (var1 == 2) {
            var2 = this.innerCircleHide();
            var3 = this.outerCircleHide();
            boolean var4 = this.outerCircleEnlarge();
            if (var2 && var3 && var4) {
               this.mOuterRadius = (float)this.mInitOuterRadius;
               this.resumeInit();
               this.currentFrame = 1;
            }
         }
      } else {
         var3 = this.innerCircleShow();
         var2 = this.outerCircleShow();
         if (var3 && var2) {
            ++this.currentFrame;
         }
      }

   }

   private void resumeInit() {
      this.mInnerAlpha = 0;
      this.mOuterRadius = (float)this.mInitInnerRadius;
      this.mInnerRadius = (float)this.mInitOuterRadius;
      this.mOuterAlpha = 0;
      this.invalidate();
   }

   public void hide() {
      Log.d("OkView", "hide: .......................");
      this.isPlay = false;
      this.currentFrame = 1;
      this.removeCallbacks(this.mRunnable);
      this.resumeInit();
   }

   protected void onDraw(Canvas var1) {
      super.onDraw(var1);
      var1.save();
      this.mInnerPaint.setAlpha(this.mInnerAlpha);
      var1.drawCircle(this.mInnerCx, this.mInnerCy, this.mInnerRadius, this.mInnerPaint);
      var1.restore();
      var1.save();
      this.mOuterPaint.setAlpha(this.mOuterAlpha);
      var1.drawCircle(this.mOuterCx, this.mOuterCy, this.mOuterRadius, this.mOuterPaint);
      var1.restore();
   }

   protected void onMeasure(int var1, int var2) {
      var1 = MeasureSpec.getSize(var1);
      var2 = MeasureSpec.getSize(var2);
      var1 = var1 - this.getPaddingLeft() - this.getPaddingRight();
      this.setMeasuredDimension(var1, var2 - this.getPaddingTop() - this.getPaddingBottom());
      this.mWidth = var1;
      int var3 = var1 / 4;
      this.mInitInnerRadius = var3;
      var2 = (int)(((float)(var1 / 2) + this.mOuterStrokeWidth / 2.0F) / 2.0F);
      this.mInitOuterRadius = var2;
      this.mInnerRadius = (float)var3;
      this.mOuterRadius = (float)var2;
      this.mInnerCx = (float)var1 / 2.0F;
      this.mInnerCy = (float)var1 / 2.0F;
      this.mOuterCx = (float)var1 / 2.0F;
      this.mOuterCy = (float)var1 / 2.0F;
      this.initPaints();
   }

   public void setDelay(int var1) {
      this.mDelay = var1;
   }

   public void setOuterStrokeWidth(float var1) {
      this.mOuterStrokeWidth = var1;
      this.mInitOuterRadius = (int)(((float)(this.mWidth / 2) + var1 / 2.0F) / 2.0F);
   }

   public void show() {
      Log.d("OkView", "show: ..............................");
      this.isPlay = true;
      this.post(this.mRunnable);
   }
}
