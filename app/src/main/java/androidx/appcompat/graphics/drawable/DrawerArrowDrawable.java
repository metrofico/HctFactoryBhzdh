package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.R;
import androidx.core.graphics.drawable.DrawableCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DrawerArrowDrawable extends Drawable {
   public static final int ARROW_DIRECTION_END = 3;
   public static final int ARROW_DIRECTION_LEFT = 0;
   public static final int ARROW_DIRECTION_RIGHT = 1;
   public static final int ARROW_DIRECTION_START = 2;
   private static final float ARROW_HEAD_ANGLE = (float)Math.toRadians(45.0);
   private float mArrowHeadLength;
   private float mArrowShaftLength;
   private float mBarGap;
   private float mBarLength;
   private int mDirection;
   private float mMaxCutForBarSize;
   private final Paint mPaint;
   private final Path mPath;
   private float mProgress;
   private final int mSize;
   private boolean mSpin;
   private boolean mVerticalMirror;

   public DrawerArrowDrawable(Context var1) {
      Paint var2 = new Paint();
      this.mPaint = var2;
      this.mPath = new Path();
      this.mVerticalMirror = false;
      this.mDirection = 2;
      var2.setStyle(Style.STROKE);
      var2.setStrokeJoin(Join.MITER);
      var2.setStrokeCap(Cap.BUTT);
      var2.setAntiAlias(true);
      TypedArray var3 = var1.getTheme().obtainStyledAttributes((AttributeSet)null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
      this.setColor(var3.getColor(R.styleable.DrawerArrowToggle_color, 0));
      this.setBarThickness(var3.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0F));
      this.setSpinEnabled(var3.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true));
      this.setGapSize((float)Math.round(var3.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0F)));
      this.mSize = var3.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
      this.mBarLength = (float)Math.round(var3.getDimension(R.styleable.DrawerArrowToggle_barLength, 0.0F));
      this.mArrowHeadLength = (float)Math.round(var3.getDimension(R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0F));
      this.mArrowShaftLength = var3.getDimension(R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0F);
      var3.recycle();
   }

   private static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   public void draw(Canvas var1) {
      Rect var16 = this.getBounds();
      int var15 = this.mDirection;
      boolean var14 = false;
      boolean var13 = var14;
      if (var15 != 0) {
         label39: {
            if (var15 != 1) {
               if (var15 != 3) {
                  var13 = var14;
                  if (DrawableCompat.getLayoutDirection(this) != 1) {
                     break label39;
                  }
               } else {
                  var13 = var14;
                  if (DrawableCompat.getLayoutDirection(this) != 0) {
                     break label39;
                  }
               }
            }

            var13 = true;
         }
      }

      float var6 = this.mArrowHeadLength;
      var6 = (float)Math.sqrt((double)(var6 * var6 * 2.0F));
      float var11 = lerp(this.mBarLength, var6, this.mProgress);
      float var9 = lerp(this.mBarLength, this.mArrowShaftLength, this.mProgress);
      float var8 = (float)Math.round(lerp(0.0F, this.mMaxCutForBarSize, this.mProgress));
      float var10 = lerp(0.0F, ARROW_HEAD_ANGLE, this.mProgress);
      if (var13) {
         var6 = 0.0F;
      } else {
         var6 = -180.0F;
      }

      float var7;
      if (var13) {
         var7 = 180.0F;
      } else {
         var7 = 0.0F;
      }

      var6 = lerp(var6, var7, this.mProgress);
      double var4 = (double)var11;
      double var2 = (double)var10;
      var10 = (float)Math.round(Math.cos(var2) * var4);
      var7 = (float)Math.round(var4 * Math.sin(var2));
      this.mPath.rewind();
      var11 = lerp(this.mBarGap + this.mPaint.getStrokeWidth(), -this.mMaxCutForBarSize, this.mProgress);
      float var12 = -var9 / 2.0F;
      this.mPath.moveTo(var12 + var8, 0.0F);
      this.mPath.rLineTo(var9 - var8 * 2.0F, 0.0F);
      this.mPath.moveTo(var12, var11);
      this.mPath.rLineTo(var10, var7);
      this.mPath.moveTo(var12, -var11);
      this.mPath.rLineTo(var10, -var7);
      this.mPath.close();
      var1.save();
      var8 = this.mPaint.getStrokeWidth();
      var9 = (float)var16.height();
      var7 = this.mBarGap;
      var9 = (float)((int)(var9 - 3.0F * var8 - 2.0F * var7) / 4 * 2);
      var1.translate((float)var16.centerX(), var9 + var8 * 1.5F + var7);
      if (this.mSpin) {
         byte var17;
         if (this.mVerticalMirror ^ var13) {
            var17 = -1;
         } else {
            var17 = 1;
         }

         var1.rotate(var6 * (float)var17);
      } else if (var13) {
         var1.rotate(180.0F);
      }

      var1.drawPath(this.mPath, this.mPaint);
      var1.restore();
   }

   public float getArrowHeadLength() {
      return this.mArrowHeadLength;
   }

   public float getArrowShaftLength() {
      return this.mArrowShaftLength;
   }

   public float getBarLength() {
      return this.mBarLength;
   }

   public float getBarThickness() {
      return this.mPaint.getStrokeWidth();
   }

   public int getColor() {
      return this.mPaint.getColor();
   }

   public int getDirection() {
      return this.mDirection;
   }

   public float getGapSize() {
      return this.mBarGap;
   }

   public int getIntrinsicHeight() {
      return this.mSize;
   }

   public int getIntrinsicWidth() {
      return this.mSize;
   }

   public int getOpacity() {
      return -3;
   }

   public final Paint getPaint() {
      return this.mPaint;
   }

   public float getProgress() {
      return this.mProgress;
   }

   public boolean isSpinEnabled() {
      return this.mSpin;
   }

   public void setAlpha(int var1) {
      if (var1 != this.mPaint.getAlpha()) {
         this.mPaint.setAlpha(var1);
         this.invalidateSelf();
      }

   }

   public void setArrowHeadLength(float var1) {
      if (this.mArrowHeadLength != var1) {
         this.mArrowHeadLength = var1;
         this.invalidateSelf();
      }

   }

   public void setArrowShaftLength(float var1) {
      if (this.mArrowShaftLength != var1) {
         this.mArrowShaftLength = var1;
         this.invalidateSelf();
      }

   }

   public void setBarLength(float var1) {
      if (this.mBarLength != var1) {
         this.mBarLength = var1;
         this.invalidateSelf();
      }

   }

   public void setBarThickness(float var1) {
      if (this.mPaint.getStrokeWidth() != var1) {
         this.mPaint.setStrokeWidth(var1);
         this.mMaxCutForBarSize = (float)((double)(var1 / 2.0F) * Math.cos((double)ARROW_HEAD_ANGLE));
         this.invalidateSelf();
      }

   }

   public void setColor(int var1) {
      if (var1 != this.mPaint.getColor()) {
         this.mPaint.setColor(var1);
         this.invalidateSelf();
      }

   }

   public void setColorFilter(ColorFilter var1) {
      this.mPaint.setColorFilter(var1);
      this.invalidateSelf();
   }

   public void setDirection(int var1) {
      if (var1 != this.mDirection) {
         this.mDirection = var1;
         this.invalidateSelf();
      }

   }

   public void setGapSize(float var1) {
      if (var1 != this.mBarGap) {
         this.mBarGap = var1;
         this.invalidateSelf();
      }

   }

   public void setProgress(float var1) {
      if (this.mProgress != var1) {
         this.mProgress = var1;
         this.invalidateSelf();
      }

   }

   public void setSpinEnabled(boolean var1) {
      if (this.mSpin != var1) {
         this.mSpin = var1;
         this.invalidateSelf();
      }

   }

   public void setVerticalMirror(boolean var1) {
      if (this.mVerticalMirror != var1) {
         this.mVerticalMirror = var1;
         this.invalidateSelf();
      }

   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ArrowDirection {
   }
}
