package androidx.core.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback {
   private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
   private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
   private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
   private static final int MAX_IMAGE_SIZE = 1048576;
   private Matrix mTempMatrix;

   private static Bitmap createDrawableBitmap(Drawable var0) {
      int var2 = var0.getIntrinsicWidth();
      int var3 = var0.getIntrinsicHeight();
      if (var2 > 0 && var3 > 0) {
         float var1 = Math.min(1.0F, 1048576.0F / (float)(var2 * var3));
         if (var0 instanceof BitmapDrawable && var1 == 1.0F) {
            return ((BitmapDrawable)var0).getBitmap();
         } else {
            var2 = (int)((float)var2 * var1);
            int var5 = (int)((float)var3 * var1);
            Bitmap var8 = Bitmap.createBitmap(var2, var5, Config.ARGB_8888);
            Canvas var10 = new Canvas(var8);
            Rect var9 = var0.getBounds();
            int var4 = var9.left;
            int var7 = var9.top;
            int var6 = var9.right;
            var3 = var9.bottom;
            var0.setBounds(0, 0, var2, var5);
            var0.draw(var10);
            var0.setBounds(var4, var7, var6, var3);
            return var8;
         }
      } else {
         return null;
      }
   }

   public Parcelable onCaptureSharedElementSnapshot(View var1, Matrix var2, RectF var3) {
      Drawable var8;
      if (var1 instanceof ImageView) {
         ImageView var7 = (ImageView)var1;
         Drawable var9 = var7.getDrawable();
         var8 = var7.getBackground();
         if (var9 != null && var8 == null) {
            Bitmap var15 = createDrawableBitmap(var9);
            if (var15 != null) {
               Bundle var10 = new Bundle();
               var10.putParcelable("sharedElement:snapshot:bitmap", var15);
               var10.putString("sharedElement:snapshot:imageScaleType", var7.getScaleType().toString());
               if (var7.getScaleType() == ScaleType.MATRIX) {
                  Matrix var13 = var7.getImageMatrix();
                  float[] var12 = new float[9];
                  var13.getValues(var12);
                  var10.putFloatArray("sharedElement:snapshot:imageMatrix", var12);
               }

               return var10;
            }
         }
      }

      int var6 = Math.round(var3.width());
      int var5 = Math.round(var3.height());
      var8 = null;
      Bitmap var14 = var8;
      if (var6 > 0) {
         var14 = var8;
         if (var5 > 0) {
            float var4 = Math.min(1.0F, 1048576.0F / (float)(var6 * var5));
            var6 = (int)((float)var6 * var4);
            var5 = (int)((float)var5 * var4);
            if (this.mTempMatrix == null) {
               this.mTempMatrix = new Matrix();
            }

            this.mTempMatrix.set(var2);
            this.mTempMatrix.postTranslate(-var3.left, -var3.top);
            this.mTempMatrix.postScale(var4, var4);
            var14 = Bitmap.createBitmap(var6, var5, Config.ARGB_8888);
            Canvas var11 = new Canvas(var14);
            var11.concat(this.mTempMatrix);
            var1.draw(var11);
         }
      }

      return var14;
   }

   public View onCreateSnapshotView(Context var1, Parcelable var2) {
      boolean var3 = var2 instanceof Bundle;
      ImageView var4 = null;
      if (var3) {
         Bundle var6 = (Bundle)var2;
         Bitmap var9 = (Bitmap)var6.getParcelable("sharedElement:snapshot:bitmap");
         if (var9 == null) {
            return null;
         }

         ImageView var5 = new ImageView(var1);
         var5.setImageBitmap(var9);
         var5.setScaleType(ScaleType.valueOf(var6.getString("sharedElement:snapshot:imageScaleType")));
         var4 = var5;
         if (var5.getScaleType() == ScaleType.MATRIX) {
            float[] var10 = var6.getFloatArray("sharedElement:snapshot:imageMatrix");
            Matrix var7 = new Matrix();
            var7.setValues(var10);
            var5.setImageMatrix(var7);
            var4 = var5;
         }
      } else if (var2 instanceof Bitmap) {
         Bitmap var8 = (Bitmap)var2;
         var4 = new ImageView(var1);
         var4.setImageBitmap(var8);
      }

      return var4;
   }

   public void onMapSharedElements(List var1, Map var2) {
   }

   public void onRejectSharedElements(List var1) {
   }

   public void onSharedElementEnd(List var1, List var2, List var3) {
   }

   public void onSharedElementStart(List var1, List var2, List var3) {
   }

   public void onSharedElementsArrived(List var1, List var2, OnSharedElementsReadyListener var3) {
      var3.onSharedElementsReady();
   }

   public interface OnSharedElementsReadyListener {
      void onSharedElementsReady();
   }
}
