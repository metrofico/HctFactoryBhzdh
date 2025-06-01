package androidx.core.content.res;

import android.graphics.Color;
import androidx.core.graphics.ColorUtils;

final class CamUtils {
   static final float[][] CAM16RGB_TO_XYZ;
   static final float[][] SRGB_TO_XYZ;
   static final float[] WHITE_POINT_D65;
   static final float[][] XYZ_TO_CAM16RGB;

   static {
      float[] var0 = new float[]{0.401288F, 0.650173F, -0.051461F};
      float[] var1 = new float[]{-0.002079F, 0.048952F, 0.953127F};
      XYZ_TO_CAM16RGB = new float[][]{var0, {-0.250268F, 1.204414F, 0.045854F}, var1};
      CAM16RGB_TO_XYZ = new float[][]{{1.8620678F, -1.0112547F, 0.14918678F}, {0.38752654F, 0.62144744F, -0.00897398F}, {-0.0158415F, -0.03412294F, 1.0499644F}};
      WHITE_POINT_D65 = new float[]{95.047F, 100.0F, 108.883F};
      var0 = new float[]{0.2126F, 0.7152F, 0.0722F};
      SRGB_TO_XYZ = new float[][]{{0.41233894F, 0.35762063F, 0.18051042F}, var0, {0.01932141F, 0.11916382F, 0.9503448F}};
   }

   private CamUtils() {
   }

   static int intFromLStar(float var0) {
      if (var0 < 1.0F) {
         return -16777216;
      } else if (var0 > 99.0F) {
         return -1;
      } else {
         float var3 = (var0 + 16.0F) / 116.0F;
         boolean var4;
         if (var0 > 8.0F) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (var4) {
            var0 = var3 * var3 * var3;
         } else {
            var0 /= 903.2963F;
         }

         float var2 = var3 * var3 * var3;
         if (var2 > 0.008856452F) {
            var4 = true;
         } else {
            var4 = false;
         }

         float var1;
         if (var4) {
            var1 = var2;
         } else {
            var1 = (var3 * 116.0F - 16.0F) / 903.2963F;
         }

         if (!var4) {
            var2 = (var3 * 116.0F - 16.0F) / 903.2963F;
         }

         float[] var5 = WHITE_POINT_D65;
         return ColorUtils.XYZToColor((double)(var1 * var5[0]), (double)(var0 * var5[1]), (double)(var2 * var5[2]));
      }
   }

   static float lStarFromInt(int var0) {
      return lStarFromY(yFromInt(var0));
   }

   static float lStarFromY(float var0) {
      var0 /= 100.0F;
      return var0 <= 0.008856452F ? var0 * 903.2963F : (float)Math.cbrt((double)var0) * 116.0F - 16.0F;
   }

   static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   static float linearized(int var0) {
      float var1 = (float)var0 / 255.0F;
      if (var1 <= 0.04045F) {
         var1 /= 12.92F;
      } else {
         var1 = (float)Math.pow((double)((var1 + 0.055F) / 1.055F), 2.4000000953674316);
      }

      return var1 * 100.0F;
   }

   static float[] xyzFromInt(int var0) {
      float var1 = linearized(Color.red(var0));
      float var5 = linearized(Color.green(var0));
      float var6 = linearized(Color.blue(var0));
      float[][] var10 = SRGB_TO_XYZ;
      float[] var11 = var10[0];
      float var2 = var11[0];
      float var4 = var11[1];
      float var8 = var11[2];
      var11 = var10[1];
      float var3 = var11[0];
      float var9 = var11[1];
      float var7 = var11[2];
      float[] var12 = var10[2];
      return new float[]{var2 * var1 + var4 * var5 + var8 * var6, var3 * var1 + var9 * var5 + var7 * var6, var1 * var12[0] + var5 * var12[1] + var6 * var12[2]};
   }

   static float yFromInt(int var0) {
      float var3 = linearized(Color.red(var0));
      float var1 = linearized(Color.green(var0));
      float var2 = linearized(Color.blue(var0));
      float[] var4 = SRGB_TO_XYZ[1];
      return var3 * var4[0] + var1 * var4[1] + var2 * var4[2];
   }

   static float yFromLStar(float var0) {
      if (var0 > 8.0F) {
         var0 = (float)Math.pow(((double)var0 + 16.0) / 116.0, 3.0);
      } else {
         var0 /= 903.2963F;
      }

      return var0 * 100.0F;
   }
}
