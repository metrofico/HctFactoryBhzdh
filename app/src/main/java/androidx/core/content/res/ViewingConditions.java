package androidx.core.content.res;

final class ViewingConditions {
   static final ViewingConditions DEFAULT;
   private final float mAw;
   private final float mC;
   private final float mFl;
   private final float mFlRoot;
   private final float mN;
   private final float mNbb;
   private final float mNc;
   private final float mNcb;
   private final float[] mRgbD;
   private final float mZ;

   static {
      DEFAULT = make(CamUtils.WHITE_POINT_D65, (float)((double)CamUtils.yFromLStar(50.0F) * 63.66197723675813 / 100.0), 50.0F, 2.0F, false);
   }

   private ViewingConditions(float var1, float var2, float var3, float var4, float var5, float var6, float[] var7, float var8, float var9, float var10) {
      this.mN = var1;
      this.mAw = var2;
      this.mNbb = var3;
      this.mNcb = var4;
      this.mC = var5;
      this.mNc = var6;
      this.mRgbD = var7;
      this.mFl = var8;
      this.mFlRoot = var9;
      this.mZ = var10;
   }

   static ViewingConditions make(float[] var0, float var1, float var2, float var3, boolean var4) {
      float[][] var13 = CamUtils.XYZ_TO_CAM16RGB;
      float var10 = var0[0];
      float[] var14 = var13[0];
      float var9 = var14[0];
      float var7 = var0[1];
      float var8 = var14[1];
      float var11 = var0[2];
      var8 = var9 * var10 + var8 * var7 + var14[2] * var11;
      var14 = var13[1];
      var9 = var14[0] * var10 + var14[1] * var7 + var14[2] * var11;
      float[] var15 = var13[2];
      var11 = var10 * var15[0] + var7 * var15[1] + var11 * var15[2];
      var10 = var3 / 10.0F + 0.8F;
      if ((double)var10 >= 0.9) {
         var7 = CamUtils.lerp(0.59F, 0.69F, (var10 - 0.9F) * 10.0F);
      } else {
         var7 = CamUtils.lerp(0.525F, 0.59F, (var10 - 0.8F) * 10.0F);
      }

      if (var4) {
         var3 = 1.0F;
      } else {
         var3 = (1.0F - (float)Math.exp((double)((-var1 - 42.0F) / 92.0F)) * 0.2777778F) * var10;
      }

      double var5 = (double)var3;
      if (var5 > 1.0) {
         var3 = 1.0F;
      } else if (var5 < 0.0) {
         var3 = 0.0F;
      }

      var15 = new float[]{100.0F / var8 * var3 + 1.0F - var3, 100.0F / var9 * var3 + 1.0F - var3, 100.0F / var11 * var3 + 1.0F - var3};
      var3 = 1.0F / (5.0F * var1 + 1.0F);
      float var12 = var3 * var3 * var3 * var3;
      var3 = 1.0F - var12;
      var1 = var12 * var1 + 0.1F * var3 * var3 * (float)Math.cbrt((double)var1 * 5.0);
      var3 = CamUtils.yFromLStar(var2) / var0[1];
      var5 = (double)var3;
      var2 = (float)Math.sqrt(var5);
      var12 = 0.725F / (float)Math.pow(var5, 0.2);
      var0 = new float[]{(float)Math.pow((double)(var15[0] * var1 * var8) / 100.0, 0.42), (float)Math.pow((double)(var15[1] * var1 * var9) / 100.0, 0.42), 0.0F};
      var8 = (float)Math.pow((double)(var15[2] * var1 * var11) / 100.0, 0.42);
      var0[2] = var8;
      var9 = var0[0];
      var9 = var9 * 400.0F / (var9 + 27.13F);
      var11 = var0[1];
      return new ViewingConditions(var3, (var9 * 2.0F + var11 * 400.0F / (var11 + 27.13F) + 400.0F * var8 / (var8 + 27.13F) * 0.05F) * var12, var12, var12, var7, var10, var15, var1, (float)Math.pow((double)var1, 0.25), var2 + 1.48F);
   }

   float getAw() {
      return this.mAw;
   }

   float getC() {
      return this.mC;
   }

   float getFl() {
      return this.mFl;
   }

   float getFlRoot() {
      return this.mFlRoot;
   }

   float getN() {
      return this.mN;
   }

   float getNbb() {
      return this.mNbb;
   }

   float getNc() {
      return this.mNc;
   }

   float getNcb() {
      return this.mNcb;
   }

   float[] getRgbD() {
      return this.mRgbD;
   }

   float getZ() {
      return this.mZ;
   }
}
