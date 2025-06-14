package com.google.android.material.shape;

public class RoundedCornerTreatment extends CornerTreatment {
   private final float radius;

   public RoundedCornerTreatment(float var1) {
      this.radius = var1;
   }

   public void getCornerPath(float var1, float var2, ShapePath var3) {
      var3.reset(0.0F, this.radius * var2);
      float var4 = this.radius;
      var3.addArc(0.0F, 0.0F, var4 * 2.0F * var2, var4 * 2.0F * var2, var1 + 180.0F, 90.0F);
   }
}
