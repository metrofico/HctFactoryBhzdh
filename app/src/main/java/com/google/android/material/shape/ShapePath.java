package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

public class ShapePath {
   public float endX;
   public float endY;
   private final List operations = new ArrayList();
   public float startX;
   public float startY;

   public ShapePath() {
      this.reset(0.0F, 0.0F);
   }

   public ShapePath(float var1, float var2) {
      this.reset(var1, var2);
   }

   public void addArc(float var1, float var2, float var3, float var4, float var5, float var6) {
      PathArcOperation var10 = new PathArcOperation(var1, var2, var3, var4);
      var10.startAngle = var5;
      var10.sweepAngle = var6;
      this.operations.add(var10);
      float var9 = (var3 - var1) / 2.0F;
      double var7 = (double)(var5 + var6);
      this.endX = (var1 + var3) * 0.5F + var9 * (float)Math.cos(Math.toRadians(var7));
      this.endY = (var2 + var4) * 0.5F + (var4 - var2) / 2.0F * (float)Math.sin(Math.toRadians(var7));
   }

   public void applyToPath(Matrix var1, Path var2) {
      int var4 = this.operations.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         ((PathOperation)this.operations.get(var3)).applyToPath(var1, var2);
      }

   }

   public void lineTo(float var1, float var2) {
      PathLineOperation var3 = new PathLineOperation();
      var3.x = var1;
      var3.y = var2;
      this.operations.add(var3);
      this.endX = var1;
      this.endY = var2;
   }

   public void quadToPoint(float var1, float var2, float var3, float var4) {
      PathQuadOperation var5 = new PathQuadOperation();
      var5.controlX = var1;
      var5.controlY = var2;
      var5.endX = var3;
      var5.endY = var4;
      this.operations.add(var5);
      this.endX = var3;
      this.endY = var4;
   }

   public void reset(float var1, float var2) {
      this.startX = var1;
      this.startY = var2;
      this.endX = var1;
      this.endY = var2;
      this.operations.clear();
   }

   public static class PathArcOperation extends PathOperation {
      private static final RectF rectF = new RectF();
      public float bottom;
      public float left;
      public float right;
      public float startAngle;
      public float sweepAngle;
      public float top;

      public PathArcOperation(float var1, float var2, float var3, float var4) {
         this.left = var1;
         this.top = var2;
         this.right = var3;
         this.bottom = var4;
      }

      public void applyToPath(Matrix var1, Path var2) {
         Matrix var3 = this.matrix;
         var1.invert(var3);
         var2.transform(var3);
         RectF var4 = rectF;
         var4.set(this.left, this.top, this.right, this.bottom);
         var2.arcTo(var4, this.startAngle, this.sweepAngle, false);
         var2.transform(var1);
      }
   }

   public static class PathLineOperation extends PathOperation {
      private float x;
      private float y;

      public void applyToPath(Matrix var1, Path var2) {
         Matrix var3 = this.matrix;
         var1.invert(var3);
         var2.transform(var3);
         var2.lineTo(this.x, this.y);
         var2.transform(var1);
      }
   }

   public abstract static class PathOperation {
      protected final Matrix matrix = new Matrix();

      public abstract void applyToPath(Matrix var1, Path var2);
   }

   public static class PathQuadOperation extends PathOperation {
      public float controlX;
      public float controlY;
      public float endX;
      public float endY;

      public void applyToPath(Matrix var1, Path var2) {
         Matrix var3 = this.matrix;
         var1.invert(var3);
         var2.transform(var3);
         var2.quadTo(this.controlX, this.controlY, this.endX, this.endY);
         var2.transform(var1);
      }
   }
}
