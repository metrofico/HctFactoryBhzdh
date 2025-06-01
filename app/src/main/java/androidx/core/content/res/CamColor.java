package androidx.core.content.res;

import androidx.core.graphics.ColorUtils;

class CamColor {
   private static final float CHROMA_SEARCH_ENDPOINT = 0.4F;
   private static final float DE_MAX = 1.0F;
   private static final float DL_MAX = 0.2F;
   private static final float LIGHTNESS_SEARCH_ENDPOINT = 0.01F;
   private final float mAstar;
   private final float mBstar;
   private final float mChroma;
   private final float mHue;
   private final float mJ;
   private final float mJstar;
   private final float mM;
   private final float mQ;
   private final float mS;

   CamColor(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9) {
      this.mHue = var1;
      this.mChroma = var2;
      this.mJ = var3;
      this.mQ = var4;
      this.mM = var5;
      this.mS = var6;
      this.mJstar = var7;
      this.mAstar = var8;
      this.mBstar = var9;
   }

   private static CamColor findCamByJ(float var0, float var1, float var2) {
      float var7 = 1000.0F;
      float var3 = 0.0F;
      CamColor var14 = null;
      float var4 = 100.0F;
      float var6 = 1000.0F;

      CamColor var15;
      while(true) {
         var15 = var14;
         if (!(Math.abs(var3 - var4) > 0.01F)) {
            break;
         }

         float var8 = (var4 - var3) / 2.0F + var3;
         int var13 = fromJch(var8, var1, var0).viewedInSrgb();
         float var12 = CamUtils.lStarFromInt(var13);
         float var11 = Math.abs(var2 - var12);
         float var9 = var7;
         float var5 = var6;
         var15 = var14;
         if (var11 < 0.2F) {
            CamColor var16 = fromColor(var13);
            float var10 = var16.distance(fromJch(var16.getJ(), var16.getChroma(), var0));
            var9 = var7;
            var5 = var6;
            var15 = var14;
            if (var10 <= 1.0F) {
               var15 = var16;
               var9 = var11;
               var5 = var10;
            }
         }

         if (var9 == 0.0F && var5 == 0.0F) {
            break;
         }

         if (var12 < var2) {
            var3 = var8;
            var7 = var9;
            var6 = var5;
            var14 = var15;
         } else {
            var4 = var8;
            var7 = var9;
            var6 = var5;
            var14 = var15;
         }
      }

      return var15;
   }

   static CamColor fromColor(int var0) {
      return fromColorInViewingConditions(var0, ViewingConditions.DEFAULT);
   }

   static CamColor fromColorInViewingConditions(int var0, ViewingConditions var1) {
      float[] var21 = CamUtils.xyzFromInt(var0);
      float[][] var20 = CamUtils.XYZ_TO_CAM16RGB;
      float var13 = var21[0];
      float[] var22 = var20[0];
      float var8 = var22[0];
      float var9 = var21[1];
      float var18 = var22[1];
      float var14 = var21[2];
      float var19 = var22[2];
      var21 = var20[1];
      float var16 = var21[0];
      float var15 = var21[1];
      float var17 = var21[2];
      float[] var23 = var20[2];
      float var11 = var23[0];
      float var12 = var23[1];
      float var10 = var23[2];
      var8 = var1.getRgbD()[0] * (var8 * var13 + var18 * var9 + var19 * var14);
      var15 = var1.getRgbD()[1] * (var16 * var13 + var15 * var9 + var17 * var14);
      var10 = var1.getRgbD()[2] * (var13 * var11 + var9 * var12 + var14 * var10);
      var12 = (float)Math.pow((double)(var1.getFl() * Math.abs(var8)) / 100.0, 0.42);
      var11 = (float)Math.pow((double)(var1.getFl() * Math.abs(var15)) / 100.0, 0.42);
      var9 = (float)Math.pow((double)(var1.getFl() * Math.abs(var10)) / 100.0, 0.42);
      var8 = Math.signum(var8) * 400.0F * var12 / (var12 + 27.13F);
      var13 = Math.signum(var15) * 400.0F * var11 / (var11 + 27.13F);
      var9 = Math.signum(var10) * 400.0F * var9 / (var9 + 27.13F);
      double var4 = (double)var8;
      double var6 = (double)var13;
      double var2 = (double)var9;
      var12 = (float)(var4 * 11.0 + var6 * -12.0 + var2) / 11.0F;
      var11 = (float)((double)(var8 + var13) - var2 * 2.0) / 9.0F;
      var10 = var13 * 20.0F;
      var18 = (var8 * 20.0F + var10 + 21.0F * var9) / 20.0F;
      var13 = (var8 * 40.0F + var10 + var9) / 20.0F;
      var9 = (float)Math.atan2((double)var11, (double)var12) * 180.0F / 3.1415927F;
      if (var9 < 0.0F) {
         var8 = var9 + 360.0F;
      } else {
         var8 = var9;
         if (var9 >= 360.0F) {
            var8 = var9 - 360.0F;
         }
      }

      var10 = 3.1415927F * var8 / 180.0F;
      var13 = (float)Math.pow((double)(var13 * var1.getNbb() / var1.getAw()), (double)(var1.getC() * var1.getZ())) * 100.0F;
      var17 = 4.0F / var1.getC();
      var15 = (float)Math.sqrt((double)(var13 / 100.0F));
      var14 = var1.getAw();
      var16 = var1.getFlRoot();
      if ((double)var8 < 20.14) {
         var9 = 360.0F + var8;
      } else {
         var9 = var8;
      }

      var9 = (float)(Math.cos((double)var9 * Math.PI / 180.0 + 2.0) + 3.8) * 0.25F * 3846.1538F * var1.getNc() * var1.getNcb() * (float)Math.sqrt((double)(var12 * var12 + var11 * var11)) / (var18 + 0.305F);
      var12 = (float)Math.pow(1.64 - Math.pow(0.29, (double)var1.getN()), 0.73) * (float)Math.pow((double)var9, 0.9);
      var9 = var12 * (float)Math.sqrt((double)var13 / 100.0);
      var11 = var9 * var1.getFlRoot();
      var12 = (float)Math.sqrt((double)(var12 * var1.getC() / (var1.getAw() + 4.0F)));
      var18 = 1.7F * var13 / (0.007F * var13 + 1.0F);
      var19 = (float)Math.log((double)(0.0228F * var11 + 1.0F)) * 43.85965F;
      var2 = (double)var10;
      return new CamColor(var8, var9, var13, var16 * var17 * var15 * (var14 + 4.0F), var11, var12 * 50.0F, var18, var19 * (float)Math.cos(var2), var19 * (float)Math.sin(var2));
   }

   private static CamColor fromJch(float var0, float var1, float var2) {
      return fromJchInFrame(var0, var1, var2, ViewingConditions.DEFAULT);
   }

   private static CamColor fromJchInFrame(float var0, float var1, float var2, ViewingConditions var3) {
      float var14 = 4.0F / var3.getC();
      double var4 = (double)var0 / 100.0;
      float var11 = (float)Math.sqrt(var4);
      float var9 = var3.getAw();
      float var10 = var3.getFlRoot();
      float var13 = var1 * var3.getFlRoot();
      float var6 = (float)Math.sqrt((double)(var1 / (float)Math.sqrt(var4) * var3.getC() / (var3.getAw() + 4.0F)));
      float var12 = 3.1415927F * var2 / 180.0F;
      float var8 = 1.7F * var0 / (0.007F * var0 + 1.0F);
      float var7 = (float)Math.log((double)var13 * 0.0228 + 1.0) * 43.85965F;
      var4 = (double)var12;
      return new CamColor(var2, var1, var0, var14 * var11 * (var9 + 4.0F) * var10, var13, var6 * 50.0F, var8, var7 * (float)Math.cos(var4), var7 * (float)Math.sin(var4));
   }

   static int toColor(float var0, float var1, float var2) {
      return toColor(var0, var1, var2, ViewingConditions.DEFAULT);
   }

   static int toColor(float var0, float var1, float var2, ViewingConditions var3) {
      if (!((double)var1 < 1.0) && !((double)Math.round(var2) <= 0.0) && !((double)Math.round(var2) >= 100.0)) {
         float var4;
         if (var0 < 0.0F) {
            var4 = 0.0F;
         } else {
            var4 = Math.min(360.0F, var0);
         }

         CamColor var7 = null;
         boolean var6 = true;
         float var5 = 0.0F;

         for(var0 = var1; Math.abs(var5 - var1) >= 0.4F; var0 = (var1 - var5) / 2.0F + var5) {
            CamColor var8 = findCamByJ(var4, var0, var2);
            if (var6) {
               if (var8 != null) {
                  return var8.viewed(var3);
               }

               var6 = false;
            } else if (var8 == null) {
               var1 = var0;
            } else {
               var7 = var8;
               var5 = var0;
            }
         }

         if (var7 == null) {
            return CamUtils.intFromLStar(var2);
         } else {
            return var7.viewed(var3);
         }
      } else {
         return CamUtils.intFromLStar(var2);
      }
   }

   float distance(CamColor var1) {
      float var4 = this.getJStar() - var1.getJStar();
      float var2 = this.getAStar() - var1.getAStar();
      float var3 = this.getBStar() - var1.getBStar();
      return (float)(Math.pow(Math.sqrt((double)(var4 * var4 + var2 * var2 + var3 * var3)), 0.63) * 1.41);
   }

   float getAStar() {
      return this.mAstar;
   }

   float getBStar() {
      return this.mBstar;
   }

   float getChroma() {
      return this.mChroma;
   }

   float getHue() {
      return this.mHue;
   }

   float getJ() {
      return this.mJ;
   }

   float getJStar() {
      return this.mJstar;
   }

   float getM() {
      return this.mM;
   }

   float getQ() {
      return this.mQ;
   }

   float getS() {
      return this.mS;
   }

   int viewed(ViewingConditions var1) {
      float var4;
      if ((double)this.getChroma() != 0.0 && (double)this.getJ() != 0.0) {
         var4 = this.getChroma() / (float)Math.sqrt((double)this.getJ() / 100.0);
      } else {
         var4 = 0.0F;
      }

      float var8 = (float)Math.pow((double)var4 / Math.pow(1.64 - Math.pow(0.29, (double)var1.getN()), 0.73), 1.1111111111111112);
      double var2 = (double)(this.getHue() * 3.1415927F / 180.0F);
      float var6 = (float)(Math.cos(2.0 + var2) + 3.8);
      var4 = var1.getAw();
      float var9 = (float)Math.pow((double)this.getJ() / 100.0, 1.0 / (double)var1.getC() / (double)var1.getZ());
      float var7 = var1.getNc();
      float var5 = var1.getNcb();
      var4 = var4 * var9 / var1.getNbb();
      var9 = (float)Math.sin(var2);
      float var10 = (float)Math.cos(var2);
      var6 = (0.305F + var4) * 23.0F * var8 / (var6 * 0.25F * 3846.1538F * var7 * var5 * 23.0F + 11.0F * var8 * var10 + var8 * 108.0F * var9);
      var5 = var10 * var6;
      var7 = var6 * var9;
      var8 = var4 * 460.0F;
      var4 = (451.0F * var5 + var8 + 288.0F * var7) / 1403.0F;
      var6 = (var8 - 891.0F * var5 - 261.0F * var7) / 1403.0F;
      var10 = (var8 - var5 * 220.0F - var7 * 6300.0F) / 1403.0F;
      var7 = (float)Math.max(0.0, (double)Math.abs(var4) * 27.13 / (400.0 - (double)Math.abs(var4)));
      var4 = Math.signum(var4);
      var5 = 100.0F / var1.getFl();
      var8 = (float)Math.pow((double)var7, 2.380952380952381);
      var9 = (float)Math.max(0.0, (double)Math.abs(var6) * 27.13 / (400.0 - (double)Math.abs(var6)));
      var6 = Math.signum(var6);
      var7 = 100.0F / var1.getFl();
      var9 = (float)Math.pow((double)var9, 2.380952380952381);
      float var12 = (float)Math.max(0.0, (double)Math.abs(var10) * 27.13 / (400.0 - (double)Math.abs(var10)));
      var10 = Math.signum(var10);
      float var11 = 100.0F / var1.getFl();
      var12 = (float)Math.pow((double)var12, 2.380952380952381);
      var4 = var4 * var5 * var8 / var1.getRgbD()[0];
      var5 = var6 * var7 * var9 / var1.getRgbD()[1];
      float var15 = var10 * var11 * var12 / var1.getRgbD()[2];
      float[][] var17 = CamUtils.CAM16RGB_TO_XYZ;
      float[] var16 = var17[0];
      var7 = var16[0];
      var12 = var16[1];
      var6 = var16[2];
      var16 = var17[1];
      float var13 = var16[0];
      var11 = var16[1];
      var9 = var16[2];
      float[] var18 = var17[2];
      var10 = var18[0];
      float var14 = var18[1];
      var8 = var18[2];
      return ColorUtils.XYZToColor((double)(var7 * var4 + var12 * var5 + var6 * var15), (double)(var13 * var4 + var11 * var5 + var9 * var15), (double)(var4 * var10 + var5 * var14 + var15 * var8));
   }

   int viewedInSrgb() {
      return this.viewed(ViewingConditions.DEFAULT);
   }
}
