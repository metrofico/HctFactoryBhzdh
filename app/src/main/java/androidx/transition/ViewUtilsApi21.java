package androidx.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewUtilsApi21 extends ViewUtilsApi19 {
   private static final String TAG = "ViewUtilsApi21";
   private static Method sSetAnimationMatrixMethod;
   private static boolean sSetAnimationMatrixMethodFetched;
   private static Method sTransformMatrixToGlobalMethod;
   private static boolean sTransformMatrixToGlobalMethodFetched;
   private static Method sTransformMatrixToLocalMethod;
   private static boolean sTransformMatrixToLocalMethodFetched;

   private void fetchSetAnimationMatrix() {
      if (!sSetAnimationMatrixMethodFetched) {
         try {
            Method var1 = View.class.getDeclaredMethod("setAnimationMatrix", Matrix.class);
            sSetAnimationMatrixMethod = var1;
            var1.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi21", "Failed to retrieve setAnimationMatrix method", var2);
         }

         sSetAnimationMatrixMethodFetched = true;
      }

   }

   private void fetchTransformMatrixToGlobalMethod() {
      if (!sTransformMatrixToGlobalMethodFetched) {
         try {
            Method var1 = View.class.getDeclaredMethod("transformMatrixToGlobal", Matrix.class);
            sTransformMatrixToGlobalMethod = var1;
            var1.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToGlobal method", var2);
         }

         sTransformMatrixToGlobalMethodFetched = true;
      }

   }

   private void fetchTransformMatrixToLocalMethod() {
      if (!sTransformMatrixToLocalMethodFetched) {
         try {
            Method var1 = View.class.getDeclaredMethod("transformMatrixToLocal", Matrix.class);
            sTransformMatrixToLocalMethod = var1;
            var1.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToLocal method", var2);
         }

         sTransformMatrixToLocalMethodFetched = true;
      }

   }

   public void setAnimationMatrix(View var1, Matrix var2) {
      this.fetchSetAnimationMatrix();
      Method var3 = sSetAnimationMatrixMethod;
      if (var3 != null) {
         try {
            var3.invoke(var1, var2);
         } catch (InvocationTargetException var4) {
         } catch (IllegalAccessException var5) {
            throw new RuntimeException(var5.getCause());
         }
      }

   }

   public void transformMatrixToGlobal(View var1, Matrix var2) {
      this.fetchTransformMatrixToGlobalMethod();
      Method var3 = sTransformMatrixToGlobalMethod;
      if (var3 != null) {
         try {
            var3.invoke(var1, var2);
         } catch (IllegalAccessException var4) {
         } catch (InvocationTargetException var5) {
            throw new RuntimeException(var5.getCause());
         }
      }

   }

   public void transformMatrixToLocal(View var1, Matrix var2) {
      this.fetchTransformMatrixToLocalMethod();
      Method var3 = sTransformMatrixToLocalMethod;
      if (var3 != null) {
         try {
            var3.invoke(var1, var2);
         } catch (IllegalAccessException var4) {
         } catch (InvocationTargetException var5) {
            throw new RuntimeException(var5.getCause());
         }
      }

   }
}
