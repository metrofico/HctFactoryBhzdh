package com.google.android.material.shape;

public class CutCornerTreatment extends CornerTreatment {
   private final float size;

   public CutCornerTreatment(float var1) {
      this.size = var1;
   }

   public void getCornerPath(float var1, float var2, ShapePath var3) {
      var3.reset(0.0F, this.size * var2);
      double var10 = (double)var1;
      double var4 = Math.sin(var10);
      double var6 = (double)this.size;
      double var8 = (double)var2;
      var3.lineTo((float)(var4 * var6 * var8), (float)(Math.cos(var10) * (double)this.size * var8));
   }
}
