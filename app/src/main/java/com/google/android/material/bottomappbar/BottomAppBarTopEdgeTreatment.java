package com.google.android.material.bottomappbar;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

public class BottomAppBarTopEdgeTreatment extends EdgeTreatment {
   private static final int ANGLE_LEFT = 180;
   private static final int ANGLE_UP = 270;
   private static final int ARC_HALF = 180;
   private static final int ARC_QUARTER = 90;
   private float cradleVerticalOffset;
   private float fabDiameter;
   private float fabMargin;
   private float horizontalOffset;
   private float roundedCornerRadius;

   public BottomAppBarTopEdgeTreatment(float var1, float var2, float var3) {
      this.fabMargin = var1;
      this.roundedCornerRadius = var2;
      this.cradleVerticalOffset = var3;
      if (!(var3 < 0.0F)) {
         this.horizontalOffset = 0.0F;
      } else {
         throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
      }
   }

   float getCradleVerticalOffset() {
      return this.cradleVerticalOffset;
   }

   public void getEdgePath(float var1, float var2, ShapePath var3) {
      float var4 = this.fabDiameter;
      if (var4 == 0.0F) {
         var3.lineTo(var1, 0.0F);
      } else {
         var4 = (this.fabMargin * 2.0F + var4) / 2.0F;
         float var6 = var2 * this.roundedCornerRadius;
         float var5 = var1 / 2.0F + this.horizontalOffset;
         var2 = this.cradleVerticalOffset * var2 + (1.0F - var2) * var4;
         if (var2 / var4 >= 1.0F) {
            var3.lineTo(var1, 0.0F);
         } else {
            float var7 = var4 + var6;
            float var9 = var2 + var6;
            float var10 = (float)Math.sqrt((double)(var7 * var7 - var9 * var9));
            var7 = var5 - var10;
            float var8 = var5 + var10;
            var10 = (float)Math.toDegrees(Math.atan((double)(var10 / var9)));
            float var11 = 90.0F - var10;
            float var12 = var7 - var6;
            var3.lineTo(var12, 0.0F);
            var9 = var6 * 2.0F;
            var3.addArc(var12, 0.0F, var7 + var6, var9, 270.0F, var10);
            var3.addArc(var5 - var4, -var4 - var2, var5 + var4, var4 - var2, 180.0F - var11, var11 * 2.0F - 180.0F);
            var3.addArc(var8 - var6, 0.0F, var8 + var6, var9, 270.0F - var10, var10);
            var3.lineTo(var1, 0.0F);
         }
      }
   }

   float getFabCradleMargin() {
      return this.fabMargin;
   }

   float getFabCradleRoundedCornerRadius() {
      return this.roundedCornerRadius;
   }

   float getFabDiameter() {
      return this.fabDiameter;
   }

   float getHorizontalOffset() {
      return this.horizontalOffset;
   }

   void setCradleVerticalOffset(float var1) {
      this.cradleVerticalOffset = var1;
   }

   void setFabCradleMargin(float var1) {
      this.fabMargin = var1;
   }

   void setFabCradleRoundedCornerRadius(float var1) {
      this.roundedCornerRadius = var1;
   }

   void setFabDiameter(float var1) {
      this.fabDiameter = var1;
   }

   void setHorizontalOffset(float var1) {
      this.horizontalOffset = var1;
   }
}
