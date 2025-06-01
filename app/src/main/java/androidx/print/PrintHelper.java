package androidx.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Build.VERSION;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.PrintAttributes.MediaSize;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class PrintHelper {
   public static final int COLOR_MODE_COLOR = 2;
   public static final int COLOR_MODE_MONOCHROME = 1;
   static final boolean IS_MIN_MARGINS_HANDLING_CORRECT;
   private static final String LOG_TAG = "PrintHelper";
   private static final int MAX_PRINT_SIZE = 3500;
   public static final int ORIENTATION_LANDSCAPE = 1;
   public static final int ORIENTATION_PORTRAIT = 2;
   static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION;
   public static final int SCALE_MODE_FILL = 2;
   public static final int SCALE_MODE_FIT = 1;
   int mColorMode = 2;
   final Context mContext;
   BitmapFactory.Options mDecodeOptions = null;
   final Object mLock = new Object();
   int mOrientation = 1;
   int mScaleMode = 2;

   static {
      int var0 = VERSION.SDK_INT;
      boolean var2 = false;
      boolean var1;
      if (var0 >= 20 && VERSION.SDK_INT <= 23) {
         var1 = false;
      } else {
         var1 = true;
      }

      PRINT_ACTIVITY_RESPECTS_ORIENTATION = var1;
      var1 = var2;
      if (VERSION.SDK_INT != 23) {
         var1 = true;
      }

      IS_MIN_MARGINS_HANDLING_CORRECT = var1;
   }

   public PrintHelper(Context var1) {
      this.mContext = var1;
   }

   static Bitmap convertBitmapForColorMode(Bitmap var0, int var1) {
      if (var1 != 1) {
         return var0;
      } else {
         Bitmap var3 = Bitmap.createBitmap(var0.getWidth(), var0.getHeight(), Config.ARGB_8888);
         Canvas var4 = new Canvas(var3);
         Paint var2 = new Paint();
         ColorMatrix var5 = new ColorMatrix();
         var5.setSaturation(0.0F);
         var2.setColorFilter(new ColorMatrixColorFilter(var5));
         var4.drawBitmap(var0, 0.0F, 0.0F, var2);
         var4.setBitmap((Bitmap)null);
         return var3;
      }
   }

   private static PrintAttributes.Builder copyAttributes(PrintAttributes var0) {
      PrintAttributes.Builder var1 = (new PrintAttributes.Builder()).setMediaSize(var0.getMediaSize()).setResolution(var0.getResolution()).setMinMargins(var0.getMinMargins());
      if (var0.getColorMode() != 0) {
         var1.setColorMode(var0.getColorMode());
      }

      if (VERSION.SDK_INT >= 23 && var0.getDuplexMode() != 0) {
         var1.setDuplexMode(var0.getDuplexMode());
      }

      return var1;
   }

   static Matrix getMatrix(int var0, int var1, RectF var2, int var3) {
      Matrix var6 = new Matrix();
      float var4 = var2.width();
      float var5 = (float)var0;
      var4 /= var5;
      if (var3 == 2) {
         var4 = Math.max(var4, var2.height() / (float)var1);
      } else {
         var4 = Math.min(var4, var2.height() / (float)var1);
      }

      var6.postScale(var4, var4);
      var6.postTranslate((var2.width() - var5 * var4) / 2.0F, (var2.height() - (float)var1 * var4) / 2.0F);
      return var6;
   }

   static boolean isPortrait(Bitmap var0) {
      boolean var1;
      if (var0.getWidth() <= var0.getHeight()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private Bitmap loadBitmap(Uri var1, BitmapFactory.Options var2) throws FileNotFoundException {
      if (var1 != null) {
         Context var4 = this.mContext;
         if (var4 != null) {
            Object var3 = null;
            boolean var13 = false;

            InputStream var19;
            try {
               var13 = true;
               var19 = var4.getContentResolver().openInputStream(var1);
               var13 = false;
            } finally {
               if (var13) {
                  var2 = (BitmapFactory.Options)var3;
                  if (var3 != null) {
                     try {
                        var2.close();
                     } catch (IOException var14) {
                        Log.w("PrintHelper", "close fail ", var14);
                     }
                  }

               }
            }

            Bitmap var18;
            try {
               var18 = BitmapFactory.decodeStream(var19, (Rect)null, var2);
            } finally {
               ;
            }

            if (var19 != null) {
               try {
                  var19.close();
               } catch (IOException var15) {
                  Log.w("PrintHelper", "close fail ", var15);
               }
            }

            return var18;
         }
      }

      throw new IllegalArgumentException("bad argument to loadBitmap");
   }

   public static boolean systemSupportsPrint() {
      boolean var0;
      if (VERSION.SDK_INT >= 19) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public int getColorMode() {
      return this.mColorMode;
   }

   public int getOrientation() {
      return VERSION.SDK_INT >= 19 && this.mOrientation == 0 ? 1 : this.mOrientation;
   }

   public int getScaleMode() {
      return this.mScaleMode;
   }

   Bitmap loadConstrainedBitmap(Uri param1) throws FileNotFoundException {
      // $FF: Couldn't be decompiled
   }

   public void printBitmap(String var1, Bitmap var2) {
      this.printBitmap(var1, (Bitmap)var2, (OnPrintFinishCallback)null);
   }

   public void printBitmap(String var1, Bitmap var2, OnPrintFinishCallback var3) {
      if (VERSION.SDK_INT >= 19 && var2 != null) {
         PrintManager var5 = (PrintManager)this.mContext.getSystemService("print");
         PrintAttributes.MediaSize var4;
         if (isPortrait(var2)) {
            var4 = MediaSize.UNKNOWN_PORTRAIT;
         } else {
            var4 = MediaSize.UNKNOWN_LANDSCAPE;
         }

         PrintAttributes var6 = (new PrintAttributes.Builder()).setMediaSize(var4).setColorMode(this.mColorMode).build();
         var5.print(var1, new PrintBitmapAdapter(this, var1, this.mScaleMode, var2, var3), var6);
      }

   }

   public void printBitmap(String var1, Uri var2) throws FileNotFoundException {
      this.printBitmap(var1, (Uri)var2, (OnPrintFinishCallback)null);
   }

   public void printBitmap(String var1, Uri var2, OnPrintFinishCallback var3) throws FileNotFoundException {
      if (VERSION.SDK_INT >= 19) {
         PrintUriAdapter var7 = new PrintUriAdapter(this, var1, var2, var3, this.mScaleMode);
         PrintManager var6 = (PrintManager)this.mContext.getSystemService("print");
         PrintAttributes.Builder var5 = new PrintAttributes.Builder();
         var5.setColorMode(this.mColorMode);
         int var4 = this.mOrientation;
         if (var4 != 1 && var4 != 0) {
            if (var4 == 2) {
               var5.setMediaSize(MediaSize.UNKNOWN_PORTRAIT);
            }
         } else {
            var5.setMediaSize(MediaSize.UNKNOWN_LANDSCAPE);
         }

         var6.print(var1, var7, var5.build());
      }
   }

   public void setColorMode(int var1) {
      this.mColorMode = var1;
   }

   public void setOrientation(int var1) {
      this.mOrientation = var1;
   }

   public void setScaleMode(int var1) {
      this.mScaleMode = var1;
   }

   void writeBitmap(PrintAttributes var1, int var2, Bitmap var3, ParcelFileDescriptor var4, CancellationSignal var5, PrintDocumentAdapter.WriteResultCallback var6) {
      PrintAttributes var7;
      if (IS_MIN_MARGINS_HANDLING_CORRECT) {
         var7 = var1;
      } else {
         var7 = copyAttributes(var1).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
      }

      (new AsyncTask(this, var5, var7, var3, var1, var2, var4, var6) {
         final PrintHelper this$0;
         final PrintAttributes val$attributes;
         final Bitmap val$bitmap;
         final CancellationSignal val$cancellationSignal;
         final ParcelFileDescriptor val$fileDescriptor;
         final int val$fittingMode;
         final PrintAttributes val$pdfAttributes;
         final PrintDocumentAdapter.WriteResultCallback val$writeResultCallback;

         {
            this.this$0 = var1;
            this.val$cancellationSignal = var2;
            this.val$pdfAttributes = var3;
            this.val$bitmap = var4;
            this.val$attributes = var5;
            this.val$fittingMode = var6;
            this.val$fileDescriptor = var7;
            this.val$writeResultCallback = var8;
         }

         protected Throwable doInBackground(Void... param1) {
            // $FF: Couldn't be decompiled
         }

         protected void onPostExecute(Throwable var1) {
            if (this.val$cancellationSignal.isCanceled()) {
               this.val$writeResultCallback.onWriteCancelled();
            } else if (var1 == null) {
               this.val$writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
            } else {
               Log.e("PrintHelper", "Error writing printed content", var1);
               this.val$writeResultCallback.onWriteFailed((CharSequence)null);
            }

         }
      }).execute(new Void[0]);
   }

   public interface OnPrintFinishCallback {
      void onFinish();
   }

   private class PrintBitmapAdapter extends PrintDocumentAdapter {
      private PrintAttributes mAttributes;
      private final Bitmap mBitmap;
      private final OnPrintFinishCallback mCallback;
      private final int mFittingMode;
      private final String mJobName;
      final PrintHelper this$0;

      PrintBitmapAdapter(PrintHelper var1, String var2, int var3, Bitmap var4, OnPrintFinishCallback var5) {
         this.this$0 = var1;
         this.mJobName = var2;
         this.mFittingMode = var3;
         this.mBitmap = var4;
         this.mCallback = var5;
      }

      public void onFinish() {
         OnPrintFinishCallback var1 = this.mCallback;
         if (var1 != null) {
            var1.onFinish();
         }

      }

      public void onLayout(PrintAttributes var1, PrintAttributes var2, CancellationSignal var3, PrintDocumentAdapter.LayoutResultCallback var4, Bundle var5) {
         this.mAttributes = var2;
         var4.onLayoutFinished((new PrintDocumentInfo.Builder(this.mJobName)).setContentType(1).setPageCount(1).build(), var2.equals(var1) ^ true);
      }

      public void onWrite(PageRange[] var1, ParcelFileDescriptor var2, CancellationSignal var3, PrintDocumentAdapter.WriteResultCallback var4) {
         this.this$0.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, var2, var3, var4);
      }
   }

   private class PrintUriAdapter extends PrintDocumentAdapter {
      PrintAttributes mAttributes;
      Bitmap mBitmap;
      final OnPrintFinishCallback mCallback;
      final int mFittingMode;
      final Uri mImageFile;
      final String mJobName;
      AsyncTask mLoadBitmap;
      final PrintHelper this$0;

      PrintUriAdapter(PrintHelper var1, String var2, Uri var3, OnPrintFinishCallback var4, int var5) {
         this.this$0 = var1;
         this.mJobName = var2;
         this.mImageFile = var3;
         this.mCallback = var4;
         this.mFittingMode = var5;
         this.mBitmap = null;
      }

      void cancelLoad() {
         Object var2 = this.this$0.mLock;
         synchronized(var2){}

         Throwable var10000;
         boolean var10001;
         label197: {
            label196: {
               try {
                  if (this.this$0.mDecodeOptions == null) {
                     break label196;
                  }

                  if (VERSION.SDK_INT < 24) {
                     this.this$0.mDecodeOptions.requestCancelDecode();
                  }
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label197;
               }

               try {
                  this.this$0.mDecodeOptions = null;
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label197;
               }
            }

            label189:
            try {
               return;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               break label189;
            }
         }

         while(true) {
            Throwable var1 = var10000;

            try {
               throw var1;
            } catch (Throwable var19) {
               var10000 = var19;
               var10001 = false;
               continue;
            }
         }
      }

      public void onFinish() {
         super.onFinish();
         this.cancelLoad();
         AsyncTask var1 = this.mLoadBitmap;
         if (var1 != null) {
            var1.cancel(true);
         }

         OnPrintFinishCallback var2 = this.mCallback;
         if (var2 != null) {
            var2.onFinish();
         }

         Bitmap var3 = this.mBitmap;
         if (var3 != null) {
            var3.recycle();
            this.mBitmap = null;
         }

      }

      public void onLayout(PrintAttributes param1, PrintAttributes param2, CancellationSignal param3, PrintDocumentAdapter.LayoutResultCallback param4, Bundle param5) {
         // $FF: Couldn't be decompiled
      }

      public void onWrite(PageRange[] var1, ParcelFileDescriptor var2, CancellationSignal var3, PrintDocumentAdapter.WriteResultCallback var4) {
         this.this$0.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, var2, var3, var4);
      }
   }
}
