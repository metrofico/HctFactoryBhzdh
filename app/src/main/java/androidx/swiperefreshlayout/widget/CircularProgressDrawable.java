package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path.FillType;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.core.util.Preconditions;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularProgressDrawable extends Drawable implements Animatable {
   private static final int ANIMATION_DURATION = 1332;
   private static final int ARROW_HEIGHT = 5;
   private static final int ARROW_HEIGHT_LARGE = 6;
   private static final int ARROW_WIDTH = 10;
   private static final int ARROW_WIDTH_LARGE = 12;
   private static final float CENTER_RADIUS = 7.5F;
   private static final float CENTER_RADIUS_LARGE = 11.0F;
   private static final int[] COLORS = new int[]{-16777216};
   private static final float COLOR_CHANGE_OFFSET = 0.75F;
   public static final int DEFAULT = 1;
   private static final float GROUP_FULL_ROTATION = 216.0F;
   public static final int LARGE = 0;
   private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
   private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
   private static final float MAX_PROGRESS_ARC = 0.8F;
   private static final float MIN_PROGRESS_ARC = 0.01F;
   private static final float RING_ROTATION = 0.20999998F;
   private static final float SHRINK_OFFSET = 0.5F;
   private static final float STROKE_WIDTH = 2.5F;
   private static final float STROKE_WIDTH_LARGE = 3.0F;
   private Animator mAnimator;
   boolean mFinishing;
   private Resources mResources;
   private final Ring mRing;
   private float mRotation;
   float mRotationCount;

   public CircularProgressDrawable(Context var1) {
      this.mResources = ((Context)Preconditions.checkNotNull(var1)).getResources();
      Ring var2 = new Ring();
      this.mRing = var2;
      var2.setColors(COLORS);
      this.setStrokeWidth(2.5F);
      this.setupAnimators();
   }

   private void applyFinishTranslation(float var1, Ring var2) {
      this.updateRingColor(var1, var2);
      float var3 = (float)(Math.floor((double)(var2.getStartingRotation() / 0.8F)) + 1.0);
      var2.setStartTrim(var2.getStartingStartTrim() + (var2.getStartingEndTrim() - 0.01F - var2.getStartingStartTrim()) * var1);
      var2.setEndTrim(var2.getStartingEndTrim());
      var2.setRotation(var2.getStartingRotation() + (var3 - var2.getStartingRotation()) * var1);
   }

   private int evaluateColorChange(float var1, int var2, int var3) {
      int var6 = var2 >> 24 & 255;
      int var4 = var2 >> 16 & 255;
      int var5 = var2 >> 8 & 255;
      var2 &= 255;
      return var6 + (int)((float)((var3 >> 24 & 255) - var6) * var1) << 24 | var4 + (int)((float)((var3 >> 16 & 255) - var4) * var1) << 16 | var5 + (int)((float)((var3 >> 8 & 255) - var5) * var1) << 8 | var2 + (int)(var1 * (float)((var3 & 255) - var2));
   }

   private float getRotation() {
      return this.mRotation;
   }

   private void setRotation(float var1) {
      this.mRotation = var1;
   }

   private void setSizeParameters(float var1, float var2, float var3, float var4) {
      Ring var6 = this.mRing;
      float var5 = this.mResources.getDisplayMetrics().density;
      var6.setStrokeWidth(var2 * var5);
      var6.setCenterRadius(var1 * var5);
      var6.setColorIndex(0);
      var6.setArrowDimensions(var3 * var5, var4 * var5);
   }

   private void setupAnimators() {
      Ring var2 = this.mRing;
      ValueAnimator var1 = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F});
      var1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this, var2) {
         final CircularProgressDrawable this$0;
         final Ring val$ring;

         {
            this.this$0 = var1;
            this.val$ring = var2;
         }

         public void onAnimationUpdate(ValueAnimator var1) {
            float var2 = (Float)var1.getAnimatedValue();
            this.this$0.updateRingColor(var2, this.val$ring);
            this.this$0.applyTransformation(var2, this.val$ring, false);
            this.this$0.invalidateSelf();
         }
      });
      var1.setRepeatCount(-1);
      var1.setRepeatMode(1);
      var1.setInterpolator(LINEAR_INTERPOLATOR);
      var1.addListener(new Animator.AnimatorListener(this, var2) {
         final CircularProgressDrawable this$0;
         final Ring val$ring;

         {
            this.this$0 = var1;
            this.val$ring = var2;
         }

         public void onAnimationCancel(Animator var1) {
         }

         public void onAnimationEnd(Animator var1) {
         }

         public void onAnimationRepeat(Animator var1) {
            this.this$0.applyTransformation(1.0F, this.val$ring, true);
            this.val$ring.storeOriginals();
            this.val$ring.goToNextColor();
            if (this.this$0.mFinishing) {
               this.this$0.mFinishing = false;
               var1.cancel();
               var1.setDuration(1332L);
               var1.start();
               this.val$ring.setShowArrow(false);
            } else {
               CircularProgressDrawable var2 = this.this$0;
               ++var2.mRotationCount;
            }

         }

         public void onAnimationStart(Animator var1) {
            this.this$0.mRotationCount = 0.0F;
         }
      });
      this.mAnimator = var1;
   }

   void applyTransformation(float var1, Ring var2, boolean var3) {
      if (this.mFinishing) {
         this.applyFinishTranslation(var1, var2);
      } else if (var1 != 1.0F || var3) {
         float var6 = var2.getStartingRotation();
         float var4;
         float var5;
         if (var1 < 0.5F) {
            var5 = var1 / 0.5F;
            var4 = var2.getStartingStartTrim();
            var5 = MATERIAL_INTERPOLATOR.getInterpolation(var5) * 0.79F + 0.01F + var4;
         } else {
            var4 = (var1 - 0.5F) / 0.5F;
            var5 = var2.getStartingStartTrim() + 0.79F;
            var4 = var5 - ((1.0F - MATERIAL_INTERPOLATOR.getInterpolation(var4)) * 0.79F + 0.01F);
         }

         float var7 = this.mRotationCount;
         var2.setStartTrim(var4);
         var2.setEndTrim(var5);
         var2.setRotation(var6 + 0.20999998F * var1);
         this.setRotation((var1 + var7) * 216.0F);
      }

   }

   public void draw(Canvas var1) {
      Rect var2 = this.getBounds();
      var1.save();
      var1.rotate(this.mRotation, var2.exactCenterX(), var2.exactCenterY());
      this.mRing.draw(var1, var2);
      var1.restore();
   }

   public int getAlpha() {
      return this.mRing.getAlpha();
   }

   public boolean getArrowEnabled() {
      return this.mRing.getShowArrow();
   }

   public float getArrowHeight() {
      return this.mRing.getArrowHeight();
   }

   public float getArrowScale() {
      return this.mRing.getArrowScale();
   }

   public float getArrowWidth() {
      return this.mRing.getArrowWidth();
   }

   public int getBackgroundColor() {
      return this.mRing.getBackgroundColor();
   }

   public float getCenterRadius() {
      return this.mRing.getCenterRadius();
   }

   public int[] getColorSchemeColors() {
      return this.mRing.getColors();
   }

   public float getEndTrim() {
      return this.mRing.getEndTrim();
   }

   public int getOpacity() {
      return -3;
   }

   public float getProgressRotation() {
      return this.mRing.getRotation();
   }

   public float getStartTrim() {
      return this.mRing.getStartTrim();
   }

   public Paint.Cap getStrokeCap() {
      return this.mRing.getStrokeCap();
   }

   public float getStrokeWidth() {
      return this.mRing.getStrokeWidth();
   }

   public boolean isRunning() {
      return this.mAnimator.isRunning();
   }

   public void setAlpha(int var1) {
      this.mRing.setAlpha(var1);
      this.invalidateSelf();
   }

   public void setArrowDimensions(float var1, float var2) {
      this.mRing.setArrowDimensions(var1, var2);
      this.invalidateSelf();
   }

   public void setArrowEnabled(boolean var1) {
      this.mRing.setShowArrow(var1);
      this.invalidateSelf();
   }

   public void setArrowScale(float var1) {
      this.mRing.setArrowScale(var1);
      this.invalidateSelf();
   }

   public void setBackgroundColor(int var1) {
      this.mRing.setBackgroundColor(var1);
      this.invalidateSelf();
   }

   public void setCenterRadius(float var1) {
      this.mRing.setCenterRadius(var1);
      this.invalidateSelf();
   }

   public void setColorFilter(ColorFilter var1) {
      this.mRing.setColorFilter(var1);
      this.invalidateSelf();
   }

   public void setColorSchemeColors(int... var1) {
      this.mRing.setColors(var1);
      this.mRing.setColorIndex(0);
      this.invalidateSelf();
   }

   public void setProgressRotation(float var1) {
      this.mRing.setRotation(var1);
      this.invalidateSelf();
   }

   public void setStartEndTrim(float var1, float var2) {
      this.mRing.setStartTrim(var1);
      this.mRing.setEndTrim(var2);
      this.invalidateSelf();
   }

   public void setStrokeCap(Paint.Cap var1) {
      this.mRing.setStrokeCap(var1);
      this.invalidateSelf();
   }

   public void setStrokeWidth(float var1) {
      this.mRing.setStrokeWidth(var1);
      this.invalidateSelf();
   }

   public void setStyle(int var1) {
      if (var1 == 0) {
         this.setSizeParameters(11.0F, 3.0F, 12.0F, 6.0F);
      } else {
         this.setSizeParameters(7.5F, 2.5F, 10.0F, 5.0F);
      }

      this.invalidateSelf();
   }

   public void start() {
      this.mAnimator.cancel();
      this.mRing.storeOriginals();
      if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
         this.mFinishing = true;
         this.mAnimator.setDuration(666L);
         this.mAnimator.start();
      } else {
         this.mRing.setColorIndex(0);
         this.mRing.resetOriginals();
         this.mAnimator.setDuration(1332L);
         this.mAnimator.start();
      }

   }

   public void stop() {
      this.mAnimator.cancel();
      this.setRotation(0.0F);
      this.mRing.setShowArrow(false);
      this.mRing.setColorIndex(0);
      this.mRing.resetOriginals();
      this.invalidateSelf();
   }

   void updateRingColor(float var1, Ring var2) {
      if (var1 > 0.75F) {
         var2.setColor(this.evaluateColorChange((var1 - 0.75F) / 0.25F, var2.getStartingColor(), var2.getNextColor()));
      } else {
         var2.setColor(var2.getStartingColor());
      }

   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ProgressDrawableSize {
   }

   private static class Ring {
      int mAlpha;
      Path mArrow;
      int mArrowHeight;
      final Paint mArrowPaint;
      float mArrowScale;
      int mArrowWidth;
      final Paint mCirclePaint;
      int mColorIndex;
      int[] mColors;
      int mCurrentColor;
      float mEndTrim;
      final Paint mPaint;
      float mRingCenterRadius;
      float mRotation;
      boolean mShowArrow;
      float mStartTrim;
      float mStartingEndTrim;
      float mStartingRotation;
      float mStartingStartTrim;
      float mStrokeWidth;
      final RectF mTempBounds = new RectF();

      Ring() {
         Paint var1 = new Paint();
         this.mPaint = var1;
         Paint var2 = new Paint();
         this.mArrowPaint = var2;
         Paint var3 = new Paint();
         this.mCirclePaint = var3;
         this.mStartTrim = 0.0F;
         this.mEndTrim = 0.0F;
         this.mRotation = 0.0F;
         this.mStrokeWidth = 5.0F;
         this.mArrowScale = 1.0F;
         this.mAlpha = 255;
         var1.setStrokeCap(Cap.SQUARE);
         var1.setAntiAlias(true);
         var1.setStyle(Style.STROKE);
         var2.setStyle(Style.FILL);
         var2.setAntiAlias(true);
         var3.setColor(0);
      }

      void draw(Canvas var1, Rect var2) {
         RectF var6 = this.mTempBounds;
         float var4 = this.mRingCenterRadius;
         float var3 = this.mStrokeWidth / 2.0F + var4;
         if (var4 <= 0.0F) {
            var3 = (float)Math.min(var2.width(), var2.height()) / 2.0F - Math.max((float)this.mArrowWidth * this.mArrowScale / 2.0F, this.mStrokeWidth / 2.0F);
         }

         var6.set((float)var2.centerX() - var3, (float)var2.centerY() - var3, (float)var2.centerX() + var3, (float)var2.centerY() + var3);
         var3 = this.mStartTrim;
         var4 = this.mRotation;
         var3 = (var3 + var4) * 360.0F;
         var4 = (this.mEndTrim + var4) * 360.0F - var3;
         this.mPaint.setColor(this.mCurrentColor);
         this.mPaint.setAlpha(this.mAlpha);
         float var5 = this.mStrokeWidth / 2.0F;
         var6.inset(var5, var5);
         var1.drawCircle(var6.centerX(), var6.centerY(), var6.width() / 2.0F, this.mCirclePaint);
         var5 = -var5;
         var6.inset(var5, var5);
         var1.drawArc(var6, var3, var4, false, this.mPaint);
         this.drawTriangle(var1, var3, var4, var6);
      }

      void drawTriangle(Canvas var1, float var2, float var3, RectF var4) {
         if (this.mShowArrow) {
            Path var9 = this.mArrow;
            if (var9 == null) {
               var9 = new Path();
               this.mArrow = var9;
               var9.setFillType(FillType.EVEN_ODD);
            } else {
               var9.reset();
            }

            float var5 = Math.min(var4.width(), var4.height()) / 2.0F;
            float var8 = (float)this.mArrowWidth * this.mArrowScale / 2.0F;
            this.mArrow.moveTo(0.0F, 0.0F);
            this.mArrow.lineTo((float)this.mArrowWidth * this.mArrowScale, 0.0F);
            var9 = this.mArrow;
            float var6 = (float)this.mArrowWidth;
            float var7 = this.mArrowScale;
            var9.lineTo(var6 * var7 / 2.0F, (float)this.mArrowHeight * var7);
            this.mArrow.offset(var5 + var4.centerX() - var8, var4.centerY() + this.mStrokeWidth / 2.0F);
            this.mArrow.close();
            this.mArrowPaint.setColor(this.mCurrentColor);
            this.mArrowPaint.setAlpha(this.mAlpha);
            var1.save();
            var1.rotate(var2 + var3, var4.centerX(), var4.centerY());
            var1.drawPath(this.mArrow, this.mArrowPaint);
            var1.restore();
         }

      }

      int getAlpha() {
         return this.mAlpha;
      }

      float getArrowHeight() {
         return (float)this.mArrowHeight;
      }

      float getArrowScale() {
         return this.mArrowScale;
      }

      float getArrowWidth() {
         return (float)this.mArrowWidth;
      }

      int getBackgroundColor() {
         return this.mCirclePaint.getColor();
      }

      float getCenterRadius() {
         return this.mRingCenterRadius;
      }

      int[] getColors() {
         return this.mColors;
      }

      float getEndTrim() {
         return this.mEndTrim;
      }

      int getNextColor() {
         return this.mColors[this.getNextColorIndex()];
      }

      int getNextColorIndex() {
         return (this.mColorIndex + 1) % this.mColors.length;
      }

      float getRotation() {
         return this.mRotation;
      }

      boolean getShowArrow() {
         return this.mShowArrow;
      }

      float getStartTrim() {
         return this.mStartTrim;
      }

      int getStartingColor() {
         return this.mColors[this.mColorIndex];
      }

      float getStartingEndTrim() {
         return this.mStartingEndTrim;
      }

      float getStartingRotation() {
         return this.mStartingRotation;
      }

      float getStartingStartTrim() {
         return this.mStartingStartTrim;
      }

      Paint.Cap getStrokeCap() {
         return this.mPaint.getStrokeCap();
      }

      float getStrokeWidth() {
         return this.mStrokeWidth;
      }

      void goToNextColor() {
         this.setColorIndex(this.getNextColorIndex());
      }

      void resetOriginals() {
         this.mStartingStartTrim = 0.0F;
         this.mStartingEndTrim = 0.0F;
         this.mStartingRotation = 0.0F;
         this.setStartTrim(0.0F);
         this.setEndTrim(0.0F);
         this.setRotation(0.0F);
      }

      void setAlpha(int var1) {
         this.mAlpha = var1;
      }

      void setArrowDimensions(float var1, float var2) {
         this.mArrowWidth = (int)var1;
         this.mArrowHeight = (int)var2;
      }

      void setArrowScale(float var1) {
         if (var1 != this.mArrowScale) {
            this.mArrowScale = var1;
         }

      }

      void setBackgroundColor(int var1) {
         this.mCirclePaint.setColor(var1);
      }

      void setCenterRadius(float var1) {
         this.mRingCenterRadius = var1;
      }

      void setColor(int var1) {
         this.mCurrentColor = var1;
      }

      void setColorFilter(ColorFilter var1) {
         this.mPaint.setColorFilter(var1);
      }

      void setColorIndex(int var1) {
         this.mColorIndex = var1;
         this.mCurrentColor = this.mColors[var1];
      }

      void setColors(int[] var1) {
         this.mColors = var1;
         this.setColorIndex(0);
      }

      void setEndTrim(float var1) {
         this.mEndTrim = var1;
      }

      void setRotation(float var1) {
         this.mRotation = var1;
      }

      void setShowArrow(boolean var1) {
         if (this.mShowArrow != var1) {
            this.mShowArrow = var1;
         }

      }

      void setStartTrim(float var1) {
         this.mStartTrim = var1;
      }

      void setStrokeCap(Paint.Cap var1) {
         this.mPaint.setStrokeCap(var1);
      }

      void setStrokeWidth(float var1) {
         this.mStrokeWidth = var1;
         this.mPaint.setStrokeWidth(var1);
      }

      void storeOriginals() {
         this.mStartingStartTrim = this.mStartTrim;
         this.mStartingEndTrim = this.mEndTrim;
         this.mStartingRotation = this.mRotation;
      }
   }
}
