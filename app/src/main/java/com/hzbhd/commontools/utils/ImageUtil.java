package com.hzbhd.commontools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import com.hzbhd.util.LogUtil;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ImageUtil {
   public static final int BOTTOM = 1;
   public static final int CENTER = 8;
   public static final int LEFT = 2;
   public static final int LEFT_BOTTOM = 5;
   public static final int LEFT_TOP = 4;
   public static final int RIGHT = 3;
   public static final int RIGHT_BOTTOM = 7;
   public static final int RIGHT_TOP = 6;
   private static final String TAG = "ImageUtil";
   public static final int TOP = 0;

   public static byte[] addBMPImageHeader(int var0) {
      return new byte[]{66, 77, (byte)(var0 >> 0), (byte)(var0 >> 8), (byte)(var0 >> 16), (byte)(var0 >> 24), 0, 0, 0, 0, 54, 0, 0, 0};
   }

   public static byte[] addBMPImageInfosHeader(int var0, int var1) {
      return new byte[]{40, 0, 0, 0, (byte)(var0 >> 0), (byte)(var0 >> 8), (byte)(var0 >> 16), (byte)(var0 >> 24), (byte)(var1 >> 0), (byte)(var1 >> 8), (byte)(var1 >> 16), (byte)(var1 >> 24), 1, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   }

   public static byte[] addBMP_RGB_888(int[] var0, int var1, int var2) {
      int var3 = var0.length;
      System.out.println(var0.length);
      byte[] var7 = new byte[var2 * var1 * 4];
      var2 = var3 - 1;

      int var5;
      for(int var4 = 0; var2 >= var1; var2 = var5) {
         var5 = var2 - var1;

         for(var3 = var5 + 1; var3 <= var2; ++var3) {
            int var6 = var0[var3];
            var7[var4] = (byte)(var6 >> 0);
            var7[var4 + 1] = (byte)(var6 >> 8);
            var7[var4 + 2] = (byte)(var6 >> 16);
            var7[var4 + 3] = (byte)(var6 >> 24);
            var4 += 4;
         }
      }

      return var7;
   }

   public static byte[] bitmapToByte(Bitmap var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      var0.compress(CompressFormat.PNG, 100, var1);
      return var1.toByteArray();
   }

   public static Drawable bitmapToDrawable(Bitmap var0) {
      return new BitmapDrawable(var0);
   }

   public static Bitmap byteToBitmap(byte[] var0) {
      BitmapFactory.Options var1 = new BitmapFactory.Options();
      var1.inJustDecodeBounds = false;
      return BitmapFactory.decodeByteArray(var0, 0, var0.length, var1);
   }

   public static Bitmap composeBitmap(int var0, Bitmap... var1) {
      if (var1.length < 2) {
         return null;
      } else {
         int var2 = 0;

         Bitmap var3;
         for(var3 = var1[0]; var2 < var1.length; ++var2) {
            var3 = composeBitmap(var3, var1[var2], var0);
         }

         return var3;
      }
   }

   private static Bitmap composeBitmap(Bitmap var0, Bitmap var1, int var2) {
      Bitmap var7 = null;
      if (var0 == null) {
         return null;
      } else if (var1 == null) {
         return var0;
      } else {
         int var4 = var0.getWidth();
         int var6 = var0.getHeight();
         int var3 = var1.getWidth();
         int var5 = var1.getHeight();
         Canvas var8;
         if (var2 == 0) {
            var2 = var4;
            if (var3 > var4) {
               var2 = var3;
            }

            var7 = Bitmap.createBitmap(var2, var6 + var5, Config.ARGB_8888);
            var8 = new Canvas(var7);
            var8.drawBitmap(var1, 0.0F, 0.0F, (Paint)null);
            var8.drawBitmap(var0, 0.0F, (float)var5, (Paint)null);
            var0 = var7;
         } else if (var2 == 1) {
            if (var4 <= var3) {
               var4 = var3;
            }

            var7 = Bitmap.createBitmap(var4, var5 + var6, Config.ARGB_8888);
            var8 = new Canvas(var7);
            var8.drawBitmap(var0, 0.0F, 0.0F, (Paint)null);
            var8.drawBitmap(var1, 0.0F, (float)var6, (Paint)null);
            var0 = var7;
         } else if (var2 == 2) {
            var2 = var6;
            if (var5 > var6) {
               var2 = var5;
            }

            var7 = Bitmap.createBitmap(var4 + var3, var2, Config.ARGB_8888);
            var8 = new Canvas(var7);
            var8.drawBitmap(var1, 0.0F, 0.0F, (Paint)null);
            var8.drawBitmap(var0, (float)var3, 0.0F, (Paint)null);
            var0 = var7;
         } else {
            if (var2 != 3) {
               return var7;
            }

            if (var6 > var5) {
               var5 = var6;
            }

            var7 = Bitmap.createBitmap(var3 + var4, var5, Config.ARGB_8888);
            var8 = new Canvas(var7);
            var8.drawBitmap(var0, 0.0F, 0.0F, (Paint)null);
            var8.drawBitmap(var1, (float)var4, 0.0F, (Paint)null);
            var0 = var7;
         }

         var7 = var0;
         return var7;
      }
   }

   private static int computeInitialSampleSize(BitmapFactory.Options var0, int var1, int var2) {
      double var5 = (double)var0.outWidth;
      double var7 = (double)var0.outHeight;
      int var9;
      if (var2 == -1) {
         var9 = 1;
      } else {
         var9 = (int)Math.ceil(Math.sqrt(var5 * var7 / (double)var2));
      }

      int var10;
      if (var1 == -1) {
         var10 = 128;
      } else {
         double var3 = (double)var1;
         var10 = (int)Math.min(Math.floor(var5 / var3), Math.floor(var7 / var3));
      }

      if (var10 < var9) {
         return var9;
      } else if (var2 == -1 && var1 == -1) {
         return 1;
      } else {
         return var1 == -1 ? var9 : var10;
      }
   }

   public static int computeSampleSize(BitmapFactory.Options var0, int var1, int var2) {
      int var3 = computeInitialSampleSize(var0, var1, var2);
      if (var3 <= 8) {
         var1 = 1;

         while(true) {
            var2 = var1;
            if (var1 >= var3) {
               break;
            }

            var1 <<= 1;
         }
      } else {
         var2 = 8 * ((var3 + 7) / 8);
      }

      return var2;
   }

   private static Bitmap createBitmapForFotoMix(Bitmap var0, Bitmap var1, int var2) {
      Bitmap var7 = null;
      if (var0 == null) {
         return null;
      } else if (var1 == null) {
         return var0;
      } else {
         int var4 = var0.getWidth();
         int var6 = var0.getHeight();
         int var3 = var1.getWidth();
         int var5 = var1.getHeight();
         Canvas var8;
         if (var2 == 2) {
            if (var6 > var5) {
               var5 = var6;
            }

            var7 = Bitmap.createBitmap(var4 + var3, var5, Config.ARGB_8888);
            var8 = new Canvas(var7);
            var8.drawBitmap(var0, (float)var3, 0.0F, (Paint)null);
            var8.drawBitmap(var1, 0.0F, 0.0F, (Paint)null);
            var0 = var7;
         } else if (var2 == 3) {
            if (var6 <= var5) {
               var6 = var5;
            }

            var7 = Bitmap.createBitmap(var3 + var4, var6, Config.ARGB_8888);
            var8 = new Canvas(var7);
            var8.drawBitmap(var0, 0.0F, 0.0F, (Paint)null);
            var8.drawBitmap(var1, (float)var4, 0.0F, (Paint)null);
            var0 = var7;
         } else if (var2 == 0) {
            var2 = var4;
            if (var3 > var4) {
               var2 = var3;
            }

            var7 = Bitmap.createBitmap(var2, var6 + var5, Config.ARGB_8888);
            var8 = new Canvas(var7);
            var8.drawBitmap(var0, 0.0F, (float)var5, (Paint)null);
            var8.drawBitmap(var1, 0.0F, 0.0F, (Paint)null);
            var0 = var7;
         } else if (var2 == 1) {
            var2 = var4;
            if (var3 > var4) {
               var2 = var3;
            }

            var7 = Bitmap.createBitmap(var2, var5 + var6, Config.ARGB_8888);
            var8 = new Canvas(var7);
            var8.drawBitmap(var0, 0.0F, 0.0F, (Paint)null);
            var8.drawBitmap(var1, 0.0F, (float)var6, (Paint)null);
            var0 = var7;
         } else {
            if (var2 != 8) {
               return var7;
            }

            var7 = Bitmap.createBitmap(Math.max(var4, var3), Math.max(var6, var5), Config.ARGB_8888);
            var8 = new Canvas(var7);
            var8.drawBitmap(var0, (float)(var7.getWidth() - var4) / 2.0F, (float)(var7.getHeight() - var6) / 2.0F, (Paint)null);
            var8.drawBitmap(var1, (float)(var7.getWidth() - var3) / 2.0F, (float)(var7.getHeight() - var5) / 2.0F, (Paint)null);
            var0 = var7;
         }

         var7 = var0;
         return var7;
      }
   }

   public static Bitmap createGreyBitmap(Bitmap var0) {
      Bitmap var1 = Bitmap.createBitmap(var0.getWidth(), var0.getHeight(), Config.ARGB_8888);
      Canvas var4 = new Canvas(var1);
      Paint var2 = new Paint();
      ColorMatrix var3 = new ColorMatrix();
      var3.setSaturation(0.0F);
      var2.setColorFilter(new ColorMatrixColorFilter(var3));
      var4.drawBitmap(var0, 0.0F, 0.0F, var2);
      return var1;
   }

   public static Bitmap createReflectionBitmap(Bitmap var0) {
      int var3 = var0.getWidth();
      int var5 = var0.getHeight();
      int var2 = var5 / 2;
      int var4 = var5 + var2 + 4;
      Bitmap var6 = Bitmap.createBitmap(var3, var4, Config.ARGB_8888);
      Matrix var7 = new Matrix();
      var7.setScale(1.0F, -1.0F);
      Bitmap var8 = Bitmap.createBitmap(var0, 0, var2, var3, var2, var7, true);
      Canvas var9 = new Canvas(var6);
      Paint var10 = new Paint();
      var9.drawBitmap(var0, 0.0F, 0.0F, var10);
      var5 += 4;
      float var1 = (float)var5;
      var9.drawBitmap(var8, 0.0F, var1, var10);
      var10.setShader(new LinearGradient(0.0F, var1, 0.0F, (float)(var5 + var2), 1895825407, 16777215, TileMode.MIRROR));
      var10.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
      var9.drawRect(0.0F, var1, (float)var3, (float)var4, var10);
      return var6;
   }

   public static Bitmap createReflectionBitmapForSingle(Bitmap var0) {
      int var3 = var0.getWidth();
      int var2 = var0.getHeight() / 2;
      Bitmap var4 = Bitmap.createBitmap(var3, var2, Config.ARGB_8888);
      Matrix var5 = new Matrix();
      var5.setScale(1.0F, -1.0F);
      Bitmap var8 = Bitmap.createBitmap(var0, 0, var2, var3, var2, var5, true);
      Canvas var7 = new Canvas(var4);
      Paint var6 = new Paint();
      var7.drawBitmap(var8, 0.0F, 0.0F, var6);
      float var1 = (float)var2;
      var6.setShader(new LinearGradient(0.0F, 0.0F, 0.0F, var1, 1895825407, 16777215, TileMode.MIRROR));
      var6.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
      var7.drawRect(0.0F, 0.0F, (float)var3, var1, var6);
      return var4;
   }

   public static Bitmap createRoundedCornerBitmap(Bitmap var0, int var1) {
      int var3 = var0.getWidth();
      int var4 = var0.getHeight();
      Bitmap var9 = Bitmap.createBitmap(var3, var4, Config.ARGB_8888);
      Paint var8 = new Paint();
      Canvas var6 = new Canvas(var9);
      var6.drawARGB(0, 0, 0, 0);
      var8.setColor(-12434878);
      var8.setFilterBitmap(true);
      Rect var5 = new Rect(0, 0, var3, var4);
      RectF var7 = new RectF(var5);
      float var2 = (float)var1;
      var6.drawRoundRect(var7, var2, var2, var8);
      var8.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
      var6.drawBitmap(var0, var5, var5, var8);
      return var9;
   }

   public static Drawable createSelectedTip(Context var0, int var1, int var2) {
      Bitmap var3 = BitmapFactory.decodeResource(var0.getResources(), var1);
      Bitmap var6 = BitmapFactory.decodeResource(var0.getResources(), var2);
      var1 = var3.getWidth();
      Bitmap var5 = Bitmap.createBitmap(var1, var3.getHeight(), Config.ARGB_8888);
      Paint var4 = new Paint();
      Canvas var7 = new Canvas(var5);
      var7.drawBitmap(var3, 0.0F, 0.0F, var4);
      var7.drawBitmap(var6, (float)(var1 - var6.getWidth()), 0.0F, var4);
      return bitmapToDrawable(var5);
   }

   public static Bitmap createSolidColorBitmap(int var0, int var1, String var2) {
      Bitmap var3 = Bitmap.createBitmap(var0, var1, Config.ARGB_8888);
      var3.eraseColor(Color.parseColor(var2));
      return var3;
   }

   public static Bitmap createWatermark(Bitmap var0, Bitmap var1, int var2, int var3) {
      int var5 = var0.getWidth();
      int var6 = var0.getHeight();
      Bitmap var8 = Bitmap.createBitmap(var5, var6, Config.ARGB_8888);
      Canvas var7 = new Canvas(var8);
      var7.drawBitmap(var0, 0.0F, 0.0F, (Paint)null);
      if (var2 == 4) {
         float var4 = (float)var3;
         var7.drawBitmap(var1, var4, var4, (Paint)null);
      } else if (var2 == 5) {
         var7.drawBitmap(var1, (float)var3, (float)(var6 - var1.getHeight() - var3), (Paint)null);
      } else if (var2 == 6) {
         var7.drawBitmap(var1, (float)(var5 - var1.getWidth() - var3), (float)var3, (Paint)null);
      } else if (var2 == 7) {
         var7.drawBitmap(var1, (float)(var5 - var1.getWidth() - var3), (float)(var6 - var1.getHeight() - var3), (Paint)null);
      }

      return var8;
   }

   public static byte[] decodeBitmap(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static Bitmap drawableToBitmap(Drawable var0) {
      return ((BitmapDrawable)var0).getBitmap();
   }

   public static void drawableToFile(Drawable var0, String var1, Bitmap.CompressFormat var2) {
      if (var0 != null) {
         try {
            File var3 = new File(var1);
            if (var3.exists()) {
               var3.delete();
            }

            if (!var3.exists()) {
               var3.createNewFile();
            }

            FileOutputStream var5 = new FileOutputStream(var3);
            ((BitmapDrawable)var0).getBitmap().compress(var2, 100, var5);
            var5.close();
         } catch (IOException var4) {
            var4.printStackTrace();
         }

      }
   }

   public static void execCommand(String var0) throws IOException {
      Process var5 = Runtime.getRuntime().exec(var0);
      BufferedReader var1 = new BufferedReader(new InputStreamReader(var5.getInputStream()));
      StringBuilder var2 = new StringBuilder("");

      while(true) {
         String var3 = var1.readLine();
         if (var3 == null) {
            try {
               if (var5.waitFor() != 0) {
                  PrintStream var6 = System.err;
                  var2 = new StringBuilder();
                  var6.println(var2.append("exit value = ").append(var5.exitValue()).toString());
               }
            } catch (InterruptedException var4) {
               System.err.println(var4);
            }

            return;
         }

         var2.append(var3);
         var2.append('\n');
      }
   }

   public static Bitmap getPathBitmap(InputStream var0) {
      return BitmapFactory.decodeStream(var0);
   }

   private static double getScaling(int var0, int var1) {
      return Math.sqrt((double)var1 / (double)var0);
   }

   public static Bitmap potoMix(int var0, Bitmap... var1) {
      if (var1.length <= 0) {
         return null;
      } else {
         int var3 = var1.length;
         int var2 = 1;
         if (var3 == 1) {
            return var1[0];
         } else {
            Bitmap var4;
            for(var4 = var1[0]; var2 < var1.length; ++var2) {
               var4 = createBitmapForFotoMix(var4, var1[var2], var0);
            }

            return var4;
         }
      }
   }

   public static Bitmap rotateBitmap(Bitmap var0, float var1) {
      if (var0 == null) {
         return null;
      } else {
         int var2 = var0.getWidth();
         int var3 = var0.getHeight();
         Matrix var4 = new Matrix();
         var4.setRotate(var1);
         Bitmap var5 = Bitmap.createBitmap(var0, 0, 0, var2, var3, var4, false);
         var5.equals(var0);
         return var5;
      }
   }

   public static void rotateFile(String var0, int var1) {
      String var2 = "./system/bin/convert " + var0 + "  -rotate " + var1 + "  " + var0;
      Log.d("lvjinhua", var0 + " , z-----------rotateFile=" + var1);

      try {
         execCommand(var2);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

      SystemClock.sleep(100L);
   }

   public static String saveBitmapToBmp(String var0, Bitmap var1) throws Exception {
      if (LogUtil.log5()) {
         LogUtil.d("saveBitmap: Ready to save");
      }

      int var2 = var1.getWidth();
      int var3 = var1.getHeight();
      if (LogUtil.log5()) {
         LogUtil.d("saveBitmap: bitmap[w,h] = [" + var2 + "," + var3 + "]");
      }

      int[] var4 = new int[var2 * var3];
      var1.getPixels(var4, 0, var2, 0, 0, var2, var3);
      byte[] var5 = addBMP_RGB_888(var4, var2, var3);
      byte[] var6 = addBMPImageHeader(var5.length);
      byte[] var8 = addBMPImageInfosHeader(var2, var3);
      byte[] var7 = new byte[var5.length + 54];
      System.arraycopy(var6, 0, var7, 0, var6.length);
      System.arraycopy(var8, 0, var7, 14, var8.length);
      System.arraycopy(var5, 0, var7, 54, var5.length);
      FileOutputStream var9 = new FileOutputStream(var0);
      var9.write(var7);
      var9.flush();
      var9.close();
      return var0;
   }

   public static boolean saveImage(Bitmap var0, String var1, Bitmap.CompressFormat var2) {
      File var3 = new File(var1);

      try {
         FileOutputStream var6 = new FileOutputStream(var3);
         if (var0.compress(var2, 100, var6)) {
            var6.flush();
         }

         var6.close();
      } catch (FileNotFoundException var4) {
         var4.printStackTrace();
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      return false;
   }

   public static Bitmap showImage(String var0, int var1) {
      Bitmap var4;
      if (var0 != null) {
         try {
            BitmapFactory.Options var2 = new BitmapFactory.Options();
            var2.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(var0, var2);
            var2.inSampleSize = var2.outHeight / var1;
            var2.inPreferredConfig = Config.ARGB_4444;
            var2.inPurgeable = true;
            var2.inInputShareable = true;
            var2.inJustDecodeBounds = false;
            var4 = BitmapFactory.decodeFile(var0, var2);
            return var4;
         } catch (Error var3) {
         }
      }

      var4 = null;
      return var4;
   }

   public static String toCopyImage(Bitmap var0, int var1, int var2, String var3, int var4) throws Exception {
      if (LogUtil.log5()) {
         LogUtil.d("toCopyImage: Ready Start");
      }

      var0 = rotateBitmap(var0, (float)var4);
      if (LogUtil.log5()) {
         LogUtil.d("toCopyImage: <rotateBitmap> [w,h] = [" + var0.getWidth() + "," + var0.getHeight());
      }

      var0 = zoomBimtap(var0, (float)var0.getWidth(), (float)var0.getHeight(), (float)var1, (float)var2);
      if (LogUtil.log5()) {
         LogUtil.d("toCopyImage: <zoomBimtap> [w,h] = [" + var0.getWidth() + "," + var0.getHeight() + "]");
      }

      var0 = potoMix(8, createSolidColorBitmap(var1, var2, "#000000"), var0);
      if (LogUtil.log5()) {
         LogUtil.d("toCopyImage: <potoMix> [w,h] = [" + var0.getWidth() + "," + var0.getHeight() + "]");
      }

      return saveBitmapToBmp(var3, var0);
   }

   public static Bitmap zoomBimtap(Bitmap var0, float var1, float var2, float var3, float var4) {
      float var5 = var1 / var3;
      float var6 = var2 / var4;
      if (var5 > var6) {
         var4 = var2 / var5;
      } else {
         var3 = var1 / var6;
      }

      return Bitmap.createScaledBitmap(var0, (int)var3, (int)var4, true);
   }

   public static Bitmap zoomBitmap(Bitmap var0, float var1, float var2) {
      Matrix var3 = new Matrix();
      var3.setScale(var1, var2);
      return Bitmap.createBitmap(var0, 0, 0, var0.getWidth(), var0.getHeight(), var3, true);
   }

   public Bitmap scaleBitmap(Bitmap var1, int var2, int var3) {
      if (var1 == null) {
         return null;
      } else {
         int var6 = var1.getHeight();
         int var7 = var1.getWidth();
         float var4 = (float)var2 / (float)var7;
         float var5 = (float)var3 / (float)var6;
         Matrix var8 = new Matrix();
         var8.postScale(var4, var5);
         return Bitmap.createBitmap(var1, 0, 0, var7, var6, var8, false);
      }
   }
}
