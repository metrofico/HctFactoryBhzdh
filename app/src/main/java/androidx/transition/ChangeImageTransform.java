package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Map;

public class ChangeImageTransform extends Transition {
   private static final Property ANIMATED_TRANSFORM_PROPERTY = new Property(Matrix.class, "animatedTransform") {
      public Matrix get(ImageView var1) {
         return null;
      }

      public void set(ImageView var1, Matrix var2) {
         ImageViewUtils.animateTransform(var1, var2);
      }
   };
   private static final TypeEvaluator NULL_MATRIX_EVALUATOR = new TypeEvaluator() {
      public Matrix evaluate(float var1, Matrix var2, Matrix var3) {
         return null;
      }
   };
   private static final String PROPNAME_BOUNDS = "android:changeImageTransform:bounds";
   private static final String PROPNAME_MATRIX = "android:changeImageTransform:matrix";
   private static final String[] sTransitionProperties = new String[]{"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};

   public ChangeImageTransform() {
   }

   public ChangeImageTransform(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private void captureValues(TransitionValues var1) {
      View var3 = var1.view;
      if (var3 instanceof ImageView && var3.getVisibility() == 0) {
         ImageView var2 = (ImageView)var3;
         if (var2.getDrawable() == null) {
            return;
         }

         Map var4 = var1.values;
         var4.put("android:changeImageTransform:bounds", new Rect(var3.getLeft(), var3.getTop(), var3.getRight(), var3.getBottom()));
         var4.put("android:changeImageTransform:matrix", copyImageMatrix(var2));
      }

   }

   private static Matrix centerCropMatrix(ImageView var0) {
      Drawable var8 = var0.getDrawable();
      int var6 = var8.getIntrinsicWidth();
      float var2 = (float)var0.getWidth();
      float var3 = (float)var6;
      float var5 = var2 / var3;
      var6 = var8.getIntrinsicHeight();
      float var4 = (float)var0.getHeight();
      float var1 = (float)var6;
      var5 = Math.max(var5, var4 / var1);
      int var7 = Math.round((var2 - var3 * var5) / 2.0F);
      var6 = Math.round((var4 - var1 * var5) / 2.0F);
      Matrix var9 = new Matrix();
      var9.postScale(var5, var5);
      var9.postTranslate((float)var7, (float)var6);
      return var9;
   }

   private static Matrix copyImageMatrix(ImageView var0) {
      int var1 = null.$SwitchMap$android$widget$ImageView$ScaleType[var0.getScaleType().ordinal()];
      if (var1 != 1) {
         return var1 != 2 ? new Matrix(var0.getImageMatrix()) : centerCropMatrix(var0);
      } else {
         return fitXYMatrix(var0);
      }
   }

   private ObjectAnimator createMatrixAnimator(ImageView var1, Matrix var2, Matrix var3) {
      return ObjectAnimator.ofObject(var1, ANIMATED_TRANSFORM_PROPERTY, new TransitionUtils.MatrixEvaluator(), new Matrix[]{var2, var3});
   }

   private ObjectAnimator createNullAnimator(ImageView var1) {
      return ObjectAnimator.ofObject(var1, ANIMATED_TRANSFORM_PROPERTY, NULL_MATRIX_EVALUATOR, new Matrix[]{null, null});
   }

   private static Matrix fitXYMatrix(ImageView var0) {
      Drawable var2 = var0.getDrawable();
      Matrix var1 = new Matrix();
      var1.postScale((float)var0.getWidth() / (float)var2.getIntrinsicWidth(), (float)var0.getHeight() / (float)var2.getIntrinsicHeight());
      return var1;
   }

   public void captureEndValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public void captureStartValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public Animator createAnimator(ViewGroup var1, TransitionValues var2, TransitionValues var3) {
      if (var2 != null && var3 != null) {
         Rect var7 = (Rect)var2.values.get("android:changeImageTransform:bounds");
         Rect var8 = (Rect)var3.values.get("android:changeImageTransform:bounds");
         if (var7 != null && var8 != null) {
            Matrix var11 = (Matrix)var2.values.get("android:changeImageTransform:matrix");
            Matrix var6 = (Matrix)var3.values.get("android:changeImageTransform:matrix");
            boolean var4;
            if ((var11 != null || var6 != null) && (var11 == null || !var11.equals(var6))) {
               var4 = false;
            } else {
               var4 = true;
            }

            if (var7.equals(var8) && var4) {
               return null;
            }

            ImageView var13 = (ImageView)var3.view;
            Drawable var9 = var13.getDrawable();
            int var5 = var9.getIntrinsicWidth();
            int var14 = var9.getIntrinsicHeight();
            ImageViewUtils.startAnimateTransform(var13);
            ObjectAnimator var10;
            if (var5 != 0 && var14 != 0) {
               Matrix var12 = var11;
               if (var11 == null) {
                  var12 = MatrixUtils.IDENTITY_MATRIX;
               }

               var11 = var6;
               if (var6 == null) {
                  var11 = MatrixUtils.IDENTITY_MATRIX;
               }

               ANIMATED_TRANSFORM_PROPERTY.set(var13, var12);
               var10 = this.createMatrixAnimator(var13, var12, var11);
            } else {
               var10 = this.createNullAnimator(var13);
            }

            ImageViewUtils.reserveEndAnimateTransform(var13, var10);
            return var10;
         }
      }

      return null;
   }

   public String[] getTransitionProperties() {
      return sTransitionProperties;
   }
}
