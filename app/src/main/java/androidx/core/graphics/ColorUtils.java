package androidx.core.graphics;

import android.graphics.Color;
import java.util.Objects;

public final class ColorUtils {
   private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
   private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
   private static final ThreadLocal TEMP_ARRAY = new ThreadLocal();
   private static final double XYZ_EPSILON = 0.008856;
   private static final double XYZ_KAPPA = 903.3;
   private static final double XYZ_WHITE_REFERENCE_X = 95.047;
   private static final double XYZ_WHITE_REFERENCE_Y = 100.0;
   private static final double XYZ_WHITE_REFERENCE_Z = 108.883;

   private ColorUtils() {
   }

   public static int HSLToColor(float[] var0) {
      float var1 = var0[0];
      float var2 = var0[1];
      float var3 = var0[2];
      var2 = (1.0F - Math.abs(var3 * 2.0F - 1.0F)) * var2;
      var3 -= 0.5F * var2;
      float var4 = (1.0F - Math.abs(var1 / 60.0F % 2.0F - 1.0F)) * var2;
      int var5;
      int var6;
      int var7;
      switch ((int)var1 / 60) {
         case 0:
            var5 = Math.round((var2 + var3) * 255.0F);
            var6 = Math.round((var4 + var3) * 255.0F);
            var7 = Math.round(var3 * 255.0F);
            break;
         case 1:
            var5 = Math.round((var4 + var3) * 255.0F);
            var6 = Math.round((var2 + var3) * 255.0F);
            var7 = Math.round(var3 * 255.0F);
            break;
         case 2:
            var5 = Math.round(var3 * 255.0F);
            var6 = Math.round((var2 + var3) * 255.0F);
            var7 = Math.round((var4 + var3) * 255.0F);
            break;
         case 3:
            var5 = Math.round(var3 * 255.0F);
            var6 = Math.round((var4 + var3) * 255.0F);
            var7 = Math.round((var2 + var3) * 255.0F);
            break;
         case 4:
            var5 = Math.round((var4 + var3) * 255.0F);
            var6 = Math.round(var3 * 255.0F);
            var7 = Math.round((var2 + var3) * 255.0F);
            break;
         case 5:
         case 6:
            var5 = Math.round((var2 + var3) * 255.0F);
            var6 = Math.round(var3 * 255.0F);
            var7 = Math.round((var4 + var3) * 255.0F);
            break;
         default:
            var7 = 0;
            var5 = 0;
            var6 = var5;
      }

      return Color.rgb(constrain(var5, 0, 255), constrain(var6, 0, 255), constrain(var7, 0, 255));
   }

   public static int LABToColor(double var0, double var2, double var4) {
      double[] var6 = getTempDouble3Array();
      LABToXYZ(var0, var2, var4, var6);
      return XYZToColor(var6[0], var6[1], var6[2]);
   }

   public static void LABToXYZ(double var0, double var2, double var4, double[] var6) {
      double var9 = (var0 + 16.0) / 116.0;
      double var11 = var2 / 500.0 + var9;
      double var7 = var9 - var4 / 200.0;
      var2 = Math.pow(var11, 3.0);
      if (!(var2 > 0.008856)) {
         var2 = (var11 * 116.0 - 16.0) / 903.3;
      }

      if (var0 > 7.9996247999999985) {
         var0 = Math.pow(var9, 3.0);
      } else {
         var0 /= 903.3;
      }

      var4 = Math.pow(var7, 3.0);
      if (!(var4 > 0.008856)) {
         var4 = (var7 * 116.0 - 16.0) / 903.3;
      }

      var6[0] = var2 * 95.047;
      var6[1] = var0 * 100.0;
      var6[2] = var4 * 108.883;
   }

   public static void RGBToHSL(int var0, int var1, int var2, float[] var3) {
      float var9 = (float)var0 / 255.0F;
      float var10 = (float)var1 / 255.0F;
      float var4 = (float)var2 / 255.0F;
      float var8 = Math.max(var9, Math.max(var10, var4));
      float var6 = Math.min(var9, Math.min(var10, var4));
      float var5 = var8 - var6;
      float var7 = (var8 + var6) / 2.0F;
      if (var8 == var6) {
         var5 = 0.0F;
         var4 = 0.0F;
      } else {
         if (var8 == var9) {
            var4 = (var10 - var4) / var5 % 6.0F;
         } else if (var8 == var10) {
            var4 = (var4 - var9) / var5 + 2.0F;
         } else {
            var4 = 4.0F + (var9 - var10) / var5;
         }

         var6 = var5 / (1.0F - Math.abs(2.0F * var7 - 1.0F));
         var5 = var4;
         var4 = var6;
      }

      var6 = var5 * 60.0F % 360.0F;
      var5 = var6;
      if (var6 < 0.0F) {
         var5 = var6 + 360.0F;
      }

      var3[0] = constrain(var5, 0.0F, 360.0F);
      var3[1] = constrain(var4, 0.0F, 1.0F);
      var3[2] = constrain(var7, 0.0F, 1.0F);
   }

   public static void RGBToLAB(int var0, int var1, int var2, double[] var3) {
      RGBToXYZ(var0, var1, var2, var3);
      XYZToLAB(var3[0], var3[1], var3[2], var3);
   }

   public static void RGBToXYZ(int var0, int var1, int var2, double[] var3) {
      if (var3.length == 3) {
         double var4 = (double)var0 / 255.0;
         if (var4 < 0.04045) {
            var4 /= 12.92;
         } else {
            var4 = Math.pow((var4 + 0.055) / 1.055, 2.4);
         }

         double var6 = (double)var1 / 255.0;
         if (var6 < 0.04045) {
            var6 /= 12.92;
         } else {
            var6 = Math.pow((var6 + 0.055) / 1.055, 2.4);
         }

         double var8 = (double)var2 / 255.0;
         if (var8 < 0.04045) {
            var8 /= 12.92;
         } else {
            var8 = Math.pow((var8 + 0.055) / 1.055, 2.4);
         }

         var3[0] = (0.4124 * var4 + 0.3576 * var6 + 0.1805 * var8) * 100.0;
         var3[1] = (0.2126 * var4 + 0.7152 * var6 + 0.0722 * var8) * 100.0;
         var3[2] = (var4 * 0.0193 + var6 * 0.1192 + var8 * 0.9505) * 100.0;
      } else {
         throw new IllegalArgumentException("outXyz must have a length of 3.");
      }
   }

   public static int XYZToColor(double var0, double var2, double var4) {
      double var8 = (3.2406 * var0 + -1.5372 * var2 + -0.4986 * var4) / 100.0;
      double var6 = (-0.9689 * var0 + 1.8758 * var2 + 0.0415 * var4) / 100.0;
      var4 = (0.0557 * var0 + -0.204 * var2 + 1.057 * var4) / 100.0;
      if (var8 > 0.0031308) {
         var0 = Math.pow(var8, 0.4166666666666667) * 1.055 - 0.055;
      } else {
         var0 = var8 * 12.92;
      }

      if (var6 > 0.0031308) {
         var2 = Math.pow(var6, 0.4166666666666667) * 1.055 - 0.055;
      } else {
         var2 = var6 * 12.92;
      }

      if (var4 > 0.0031308) {
         var4 = Math.pow(var4, 0.4166666666666667) * 1.055 - 0.055;
      } else {
         var4 *= 12.92;
      }

      return Color.rgb(constrain((int)Math.round(var0 * 255.0), 0, 255), constrain((int)Math.round(var2 * 255.0), 0, 255), constrain((int)Math.round(var4 * 255.0), 0, 255));
   }

   public static void XYZToLAB(double var0, double var2, double var4, double[] var6) {
      if (var6.length == 3) {
         var0 = pivotXyzComponent(var0 / 95.047);
         var2 = pivotXyzComponent(var2 / 100.0);
         var4 = pivotXyzComponent(var4 / 108.883);
         var6[0] = Math.max(0.0, 116.0 * var2 - 16.0);
         var6[1] = (var0 - var2) * 500.0;
         var6[2] = (var2 - var4) * 200.0;
      } else {
         throw new IllegalArgumentException("outLab must have a length of 3.");
      }
   }

   public static int blendARGB(int var0, int var1, float var2) {
      float var3 = 1.0F - var2;
      float var9 = (float)Color.alpha(var0);
      float var5 = (float)Color.alpha(var1);
      float var7 = (float)Color.red(var0);
      float var6 = (float)Color.red(var1);
      float var4 = (float)Color.green(var0);
      float var11 = (float)Color.green(var1);
      float var10 = (float)Color.blue(var0);
      float var8 = (float)Color.blue(var1);
      return Color.argb((int)(var9 * var3 + var5 * var2), (int)(var7 * var3 + var6 * var2), (int)(var4 * var3 + var11 * var2), (int)(var10 * var3 + var8 * var2));
   }

   public static void blendHSL(float[] var0, float[] var1, float var2, float[] var3) {
      if (var3.length == 3) {
         float var4 = 1.0F - var2;
         var3[0] = circularInterpolate(var0[0], var1[0], var2);
         var3[1] = var0[1] * var4 + var1[1] * var2;
         var3[2] = var0[2] * var4 + var1[2] * var2;
      } else {
         throw new IllegalArgumentException("result must have a length of 3.");
      }
   }

   public static void blendLAB(double[] var0, double[] var1, double var2, double[] var4) {
      if (var4.length == 3) {
         double var5 = 1.0 - var2;
         var4[0] = var0[0] * var5 + var1[0] * var2;
         var4[1] = var0[1] * var5 + var1[1] * var2;
         var4[2] = var0[2] * var5 + var1[2] * var2;
      } else {
         throw new IllegalArgumentException("outResult must have a length of 3.");
      }
   }

   public static double calculateContrast(int var0, int var1) {
      if (Color.alpha(var1) == 255) {
         int var6 = var0;
         if (Color.alpha(var0) < 255) {
            var6 = compositeColors(var0, var1);
         }

         double var4 = calculateLuminance(var6) + 0.05;
         double var2 = calculateLuminance(var1) + 0.05;
         return Math.max(var4, var2) / Math.min(var4, var2);
      } else {
         throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(var1));
      }
   }

   public static double calculateLuminance(int var0) {
      double[] var1 = getTempDouble3Array();
      colorToXYZ(var0, var1);
      return var1[1] / 100.0;
   }

   public static int calculateMinimumAlpha(int var0, int var1, float var2) {
      int var7 = Color.alpha(var1);
      int var8 = 255;
      if (var7 != 255) {
         throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(var1));
      } else {
         double var5 = calculateContrast(setAlphaComponent(var0, 255), var1);
         double var3 = (double)var2;
         if (var5 < var3) {
            return -1;
         } else {
            var7 = 0;

            for(int var9 = 0; var7 <= 10 && var8 - var9 > 1; ++var7) {
               int var10 = (var9 + var8) / 2;
               if (calculateContrast(setAlphaComponent(var0, var10), var1) < var3) {
                  var9 = var10;
               } else {
                  var8 = var10;
               }
            }

            return var8;
         }
      }
   }

   static float circularInterpolate(float var0, float var1, float var2) {
      float var4 = var0;
      float var3 = var1;
      if (Math.abs(var1 - var0) > 180.0F) {
         if (var1 > var0) {
            var4 = var0 + 360.0F;
            var3 = var1;
         } else {
            var3 = var1 + 360.0F;
            var4 = var0;
         }
      }

      return (var4 + (var3 - var4) * var2) % 360.0F;
   }

   public static void colorToHSL(int var0, float[] var1) {
      RGBToHSL(Color.red(var0), Color.green(var0), Color.blue(var0), var1);
   }

   public static void colorToLAB(int var0, double[] var1) {
      RGBToLAB(Color.red(var0), Color.green(var0), Color.blue(var0), var1);
   }

   public static void colorToXYZ(int var0, double[] var1) {
      RGBToXYZ(Color.red(var0), Color.green(var0), Color.blue(var0), var1);
   }

   private static int compositeAlpha(int var0, int var1) {
      return 255 - (255 - var1) * (255 - var0) / 255;
   }

   public static int compositeColors(int var0, int var1) {
      int var4 = Color.alpha(var1);
      int var2 = Color.alpha(var0);
      int var3 = compositeAlpha(var2, var4);
      return Color.argb(var3, compositeComponent(Color.red(var0), var2, Color.red(var1), var4, var3), compositeComponent(Color.green(var0), var2, Color.green(var1), var4, var3), compositeComponent(Color.blue(var0), var2, Color.blue(var1), var4, var3));
   }

   public static Color compositeColors(Color var0, Color var1) {
      if (!Objects.equals(var0.getModel(), var1.getModel())) {
         throw new IllegalArgumentException("Color models must match (" + var0.getModel() + " vs. " + var1.getModel() + ")");
      } else {
         if (!Objects.equals(var1.getColorSpace(), var0.getColorSpace())) {
            var0 = var0.convert(var1.getColorSpace());
         }

         float[] var9 = var0.getComponents();
         float[] var10 = var1.getComponents();
         float var5 = var0.alpha();
         float var4 = var1.alpha() * (1.0F - var5);
         int var8 = var1.getComponentCount() - 1;
         float var6 = var5 + var4;
         var10[var8] = var6;
         float var3 = var4;
         float var2 = var5;
         if (var6 > 0.0F) {
            var2 = var5 / var6;
            var3 = var4 / var6;
         }

         for(int var7 = 0; var7 < var8; ++var7) {
            var10[var7] = var9[var7] * var2 + var10[var7] * var3;
         }

         return Color.valueOf(var10, var1.getColorSpace());
      }
   }

   private static int compositeComponent(int var0, int var1, int var2, int var3, int var4) {
      return var4 == 0 ? 0 : (var0 * 255 * var1 + var2 * var3 * (255 - var1)) / (var4 * 255);
   }

   private static float constrain(float var0, float var1, float var2) {
      if (!(var0 < var1)) {
         var1 = var0;
         if (var0 > var2) {
            var1 = var2;
         }
      }

      return var1;
   }

   private static int constrain(int var0, int var1, int var2) {
      if (var0 >= var1) {
         var1 = var0;
         if (var0 > var2) {
            var1 = var2;
         }
      }

      return var1;
   }

   public static double distanceEuclidean(double[] var0, double[] var1) {
      return Math.sqrt(Math.pow(var0[0] - var1[0], 2.0) + Math.pow(var0[1] - var1[1], 2.0) + Math.pow(var0[2] - var1[2], 2.0));
   }

   private static double[] getTempDouble3Array() {
      ThreadLocal var2 = TEMP_ARRAY;
      double[] var1 = (double[])var2.get();
      double[] var0 = var1;
      if (var1 == null) {
         var0 = new double[3];
         var2.set(var0);
      }

      return var0;
   }

   private static double pivotXyzComponent(double var0) {
      if (var0 > 0.008856) {
         var0 = Math.pow(var0, 0.3333333333333333);
      } else {
         var0 = (var0 * 903.3 + 16.0) / 116.0;
      }

      return var0;
   }

   public static int setAlphaComponent(int var0, int var1) {
      if (var1 >= 0 && var1 <= 255) {
         return var0 & 16777215 | var1 << 24;
      } else {
         throw new IllegalArgumentException("alpha must be between 0 and 255.");
      }
   }
}
