package com.google.android.material.shape;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.TintAwareDrawable;

public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable {
   private int alpha;
   private final ShapePath[] cornerPaths;
   private final Matrix[] cornerTransforms;
   private final Matrix[] edgeTransforms;
   private float interpolation;
   private final Matrix matrix;
   private final Paint paint;
   private Paint.Style paintStyle;
   private final Path path;
   private final PointF pointF;
   private float scale;
   private final float[] scratch;
   private final float[] scratch2;
   private final Region scratchRegion;
   private int shadowColor;
   private int shadowElevation;
   private boolean shadowEnabled;
   private int shadowRadius;
   private final ShapePath shapePath;
   private ShapePathModel shapedViewModel;
   private float strokeWidth;
   private PorterDuffColorFilter tintFilter;
   private ColorStateList tintList;
   private PorterDuff.Mode tintMode;
   private final Region transparentRegion;
   private boolean useTintColorForShadow;

   public MaterialShapeDrawable() {
      this((ShapePathModel)null);
   }

   public MaterialShapeDrawable(ShapePathModel var1) {
      this.paint = new Paint();
      this.cornerTransforms = new Matrix[4];
      this.edgeTransforms = new Matrix[4];
      this.cornerPaths = new ShapePath[4];
      this.matrix = new Matrix();
      this.path = new Path();
      this.pointF = new PointF();
      this.shapePath = new ShapePath();
      this.transparentRegion = new Region();
      this.scratchRegion = new Region();
      this.scratch = new float[2];
      this.scratch2 = new float[2];
      this.shapedViewModel = null;
      int var2 = 0;
      this.shadowEnabled = false;
      this.useTintColorForShadow = false;
      this.interpolation = 1.0F;
      this.shadowColor = -16777216;
      this.shadowElevation = 5;
      this.shadowRadius = 10;
      this.alpha = 255;
      this.scale = 1.0F;
      this.strokeWidth = 0.0F;
      this.paintStyle = Style.FILL_AND_STROKE;
      this.tintMode = Mode.SRC_IN;
      this.tintList = null;

      for(this.shapedViewModel = var1; var2 < 4; ++var2) {
         this.cornerTransforms[var2] = new Matrix();
         this.edgeTransforms[var2] = new Matrix();
         this.cornerPaths[var2] = new ShapePath();
      }

   }

   private float angleOfCorner(int var1, int var2, int var3) {
      this.getCoordinatesOfCorner((var1 - 1 + 4) % 4, var2, var3, this.pointF);
      float var8 = this.pointF.x;
      float var6 = this.pointF.y;
      this.getCoordinatesOfCorner((var1 + 1) % 4, var2, var3, this.pointF);
      float var7 = this.pointF.x;
      float var4 = this.pointF.y;
      this.getCoordinatesOfCorner(var1, var2, var3, this.pointF);
      float var9 = this.pointF.x;
      float var5 = this.pointF.y;
      var5 = (float)Math.atan2((double)(var6 - var5), (double)(var8 - var9)) - (float)Math.atan2((double)(var4 - var5), (double)(var7 - var9));
      var4 = var5;
      if (var5 < 0.0F) {
         var4 = (float)((double)var5 + 6.283185307179586);
      }

      return var4;
   }

   private float angleOfEdge(int var1, int var2, int var3) {
      this.getCoordinatesOfCorner(var1, var2, var3, this.pointF);
      float var4 = this.pointF.x;
      float var6 = this.pointF.y;
      this.getCoordinatesOfCorner((var1 + 1) % 4, var2, var3, this.pointF);
      float var5 = this.pointF.x;
      return (float)Math.atan2((double)(this.pointF.y - var6), (double)(var5 - var4));
   }

   private void appendCornerPath(int var1, Path var2) {
      this.scratch[0] = this.cornerPaths[var1].startX;
      this.scratch[1] = this.cornerPaths[var1].startY;
      this.cornerTransforms[var1].mapPoints(this.scratch);
      float[] var3;
      if (var1 == 0) {
         var3 = this.scratch;
         var2.moveTo(var3[0], var3[1]);
      } else {
         var3 = this.scratch;
         var2.lineTo(var3[0], var3[1]);
      }

      this.cornerPaths[var1].applyToPath(this.cornerTransforms[var1], var2);
   }

   private void appendEdgePath(int var1, Path var2) {
      int var4 = (var1 + 1) % 4;
      this.scratch[0] = this.cornerPaths[var1].endX;
      this.scratch[1] = this.cornerPaths[var1].endY;
      this.cornerTransforms[var1].mapPoints(this.scratch);
      this.scratch2[0] = this.cornerPaths[var4].startX;
      this.scratch2[1] = this.cornerPaths[var4].startY;
      this.cornerTransforms[var4].mapPoints(this.scratch2);
      float[] var6 = this.scratch;
      float var3 = var6[0];
      float[] var5 = this.scratch2;
      var3 = (float)Math.hypot((double)(var3 - var5[0]), (double)(var6[1] - var5[1]));
      this.shapePath.reset(0.0F, 0.0F);
      this.getEdgeTreatmentForIndex(var1).getEdgePath(var3, this.interpolation, this.shapePath);
      this.shapePath.applyToPath(this.edgeTransforms[var1], var2);
   }

   private void getCoordinatesOfCorner(int var1, int var2, int var3, PointF var4) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               var4.set(0.0F, 0.0F);
            } else {
               var4.set(0.0F, (float)var3);
            }
         } else {
            var4.set((float)var2, (float)var3);
         }
      } else {
         var4.set((float)var2, 0.0F);
      }

   }

   private CornerTreatment getCornerTreatmentForIndex(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? this.shapedViewModel.getTopLeftCorner() : this.shapedViewModel.getBottomLeftCorner();
         } else {
            return this.shapedViewModel.getBottomRightCorner();
         }
      } else {
         return this.shapedViewModel.getTopRightCorner();
      }
   }

   private EdgeTreatment getEdgeTreatmentForIndex(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? this.shapedViewModel.getTopEdge() : this.shapedViewModel.getLeftEdge();
         } else {
            return this.shapedViewModel.getBottomEdge();
         }
      } else {
         return this.shapedViewModel.getRightEdge();
      }
   }

   private void getPath(int var1, int var2, Path var3) {
      this.getPathForSize(var1, var2, var3);
      if (this.scale != 1.0F) {
         this.matrix.reset();
         Matrix var5 = this.matrix;
         float var4 = this.scale;
         var5.setScale(var4, var4, (float)(var1 / 2), (float)(var2 / 2));
         var3.transform(this.matrix);
      }
   }

   private static int modulateAlpha(int var0, int var1) {
      return var0 * (var1 + (var1 >>> 7)) >>> 8;
   }

   private void setCornerPathAndTransform(int var1, int var2, int var3) {
      this.getCoordinatesOfCorner(var1, var2, var3, this.pointF);
      float var4 = this.angleOfCorner(var1, var2, var3);
      this.getCornerTreatmentForIndex(var1).getCornerPath(var4, this.interpolation, this.cornerPaths[var1]);
      var4 = this.angleOfEdge((var1 - 1 + 4) % 4, var2, var3);
      this.cornerTransforms[var1].reset();
      this.cornerTransforms[var1].setTranslate(this.pointF.x, this.pointF.y);
      this.cornerTransforms[var1].preRotate((float)Math.toDegrees((double)(var4 + 1.5707964F)));
   }

   private void setEdgeTransform(int var1, int var2, int var3) {
      this.scratch[0] = this.cornerPaths[var1].endX;
      this.scratch[1] = this.cornerPaths[var1].endY;
      this.cornerTransforms[var1].mapPoints(this.scratch);
      float var4 = this.angleOfEdge(var1, var2, var3);
      this.edgeTransforms[var1].reset();
      Matrix var6 = this.edgeTransforms[var1];
      float[] var5 = this.scratch;
      var6.setTranslate(var5[0], var5[1]);
      this.edgeTransforms[var1].preRotate((float)Math.toDegrees((double)var4));
   }

   private void updateTintFilter() {
      ColorStateList var2 = this.tintList;
      if (var2 != null && this.tintMode != null) {
         int var1 = var2.getColorForState(this.getState(), 0);
         this.tintFilter = new PorterDuffColorFilter(var1, this.tintMode);
         if (this.useTintColorForShadow) {
            this.shadowColor = var1;
         }

      } else {
         this.tintFilter = null;
      }
   }

   public void draw(Canvas var1) {
      this.paint.setColorFilter(this.tintFilter);
      int var2 = this.paint.getAlpha();
      this.paint.setAlpha(modulateAlpha(var2, this.alpha));
      this.paint.setStrokeWidth(this.strokeWidth);
      this.paint.setStyle(this.paintStyle);
      int var3 = this.shadowElevation;
      if (var3 > 0 && this.shadowEnabled) {
         this.paint.setShadowLayer((float)this.shadowRadius, 0.0F, (float)var3, this.shadowColor);
      }

      if (this.shapedViewModel != null) {
         this.getPath(var1.getWidth(), var1.getHeight(), this.path);
         var1.drawPath(this.path, this.paint);
      } else {
         var1.drawRect(0.0F, 0.0F, (float)var1.getWidth(), (float)var1.getHeight(), this.paint);
      }

      this.paint.setAlpha(var2);
   }

   public float getInterpolation() {
      return this.interpolation;
   }

   public int getOpacity() {
      return -3;
   }

   public Paint.Style getPaintStyle() {
      return this.paintStyle;
   }

   public void getPathForSize(int var1, int var2, Path var3) {
      var3.rewind();
      if (this.shapedViewModel != null) {
         byte var6 = 0;
         int var4 = 0;

         while(true) {
            int var5 = var6;
            if (var4 >= 4) {
               while(var5 < 4) {
                  this.appendCornerPath(var5, var3);
                  this.appendEdgePath(var5, var3);
                  ++var5;
               }

               var3.close();
               return;
            }

            this.setCornerPathAndTransform(var4, var1, var2);
            this.setEdgeTransform(var4, var1, var2);
            ++var4;
         }
      }
   }

   public float getScale() {
      return this.scale;
   }

   public int getShadowElevation() {
      return this.shadowElevation;
   }

   public int getShadowRadius() {
      return this.shadowRadius;
   }

   public ShapePathModel getShapedViewModel() {
      return this.shapedViewModel;
   }

   public float getStrokeWidth() {
      return this.strokeWidth;
   }

   public ColorStateList getTintList() {
      return this.tintList;
   }

   public Region getTransparentRegion() {
      Rect var1 = this.getBounds();
      this.transparentRegion.set(var1);
      this.getPath(var1.width(), var1.height(), this.path);
      this.scratchRegion.setPath(this.path, this.transparentRegion);
      this.transparentRegion.op(this.scratchRegion, Op.DIFFERENCE);
      return this.transparentRegion;
   }

   public boolean isPointInTransparentRegion(int var1, int var2) {
      return this.getTransparentRegion().contains(var1, var2);
   }

   public boolean isShadowEnabled() {
      return this.shadowEnabled;
   }

   public void setAlpha(int var1) {
      this.alpha = var1;
      this.invalidateSelf();
   }

   public void setColorFilter(ColorFilter var1) {
      this.paint.setColorFilter(var1);
      this.invalidateSelf();
   }

   public void setInterpolation(float var1) {
      this.interpolation = var1;
      this.invalidateSelf();
   }

   public void setPaintStyle(Paint.Style var1) {
      this.paintStyle = var1;
      this.invalidateSelf();
   }

   public void setScale(float var1) {
      this.scale = var1;
      this.invalidateSelf();
   }

   public void setShadowColor(int var1) {
      this.shadowColor = var1;
      this.useTintColorForShadow = false;
      this.invalidateSelf();
   }

   public void setShadowElevation(int var1) {
      this.shadowElevation = var1;
      this.invalidateSelf();
   }

   public void setShadowEnabled(boolean var1) {
      this.shadowEnabled = var1;
      this.invalidateSelf();
   }

   public void setShadowRadius(int var1) {
      this.shadowRadius = var1;
      this.invalidateSelf();
   }

   public void setShapedViewModel(ShapePathModel var1) {
      this.shapedViewModel = var1;
      this.invalidateSelf();
   }

   public void setStrokeWidth(float var1) {
      this.strokeWidth = var1;
      this.invalidateSelf();
   }

   public void setTint(int var1) {
      this.setTintList(ColorStateList.valueOf(var1));
   }

   public void setTintList(ColorStateList var1) {
      this.tintList = var1;
      this.updateTintFilter();
      this.invalidateSelf();
   }

   public void setTintMode(PorterDuff.Mode var1) {
      this.tintMode = var1;
      this.updateTintFilter();
      this.invalidateSelf();
   }

   public void setUseTintColorForShadow(boolean var1) {
      this.useTintColorForShadow = var1;
      this.invalidateSelf();
   }
}
