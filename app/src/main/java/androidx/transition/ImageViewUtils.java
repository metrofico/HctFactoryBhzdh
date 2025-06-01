package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ImageViewUtils {
   private static final String TAG = "ImageViewUtils";
   private static Method sAnimateTransformMethod;
   private static boolean sAnimateTransformMethodFetched;

   private ImageViewUtils() {
   }

   static void animateTransform(ImageView var0, Matrix var1) {
      if (VERSION.SDK_INT < 21) {
         var0.setImageMatrix(var1);
      } else {
         fetchAnimateTransformMethod();
         Method var2 = sAnimateTransformMethod;
         if (var2 != null) {
            try {
               var2.invoke(var0, var1);
            } catch (IllegalAccessException var3) {
            } catch (InvocationTargetException var4) {
               throw new RuntimeException(var4.getCause());
            }
         }
      }

   }

   private static void fetchAnimateTransformMethod() {
      if (!sAnimateTransformMethodFetched) {
         try {
            Method var0 = ImageView.class.getDeclaredMethod("animateTransform", Matrix.class);
            sAnimateTransformMethod = var0;
            var0.setAccessible(true);
         } catch (NoSuchMethodException var1) {
            Log.i("ImageViewUtils", "Failed to retrieve animateTransform method", var1);
         }

         sAnimateTransformMethodFetched = true;
      }

   }

   static void reserveEndAnimateTransform(ImageView var0, Animator var1) {
      if (VERSION.SDK_INT < 21) {
         var1.addListener(new AnimatorListenerAdapter(var0) {
            final ImageView val$view;

            {
               this.val$view = var1;
            }

            public void onAnimationEnd(Animator var1) {
               ImageView.ScaleType var2 = (ImageView.ScaleType)this.val$view.getTag(R.id.save_scale_type);
               this.val$view.setScaleType(var2);
               this.val$view.setTag(R.id.save_scale_type, (Object)null);
               if (var2 == ScaleType.MATRIX) {
                  ImageView var3 = this.val$view;
                  var3.setImageMatrix((Matrix)var3.getTag(R.id.save_image_matrix));
                  this.val$view.setTag(R.id.save_image_matrix, (Object)null);
               }

               var1.removeListener(this);
            }
         });
      }

   }

   static void startAnimateTransform(ImageView var0) {
      if (VERSION.SDK_INT < 21) {
         ImageView.ScaleType var1 = var0.getScaleType();
         var0.setTag(R.id.save_scale_type, var1);
         if (var1 == ScaleType.MATRIX) {
            var0.setTag(R.id.save_image_matrix, var0.getImageMatrix());
         } else {
            var0.setScaleType(ScaleType.MATRIX);
         }

         var0.setImageMatrix(MatrixUtils.IDENTITY_MATRIX);
      }

   }
}
