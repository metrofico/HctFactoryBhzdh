package androidx.transition;

import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewUtilsApi19 extends ViewUtilsBase {
   private static final String TAG = "ViewUtilsApi19";
   private static Method sGetTransitionAlphaMethod;
   private static boolean sGetTransitionAlphaMethodFetched;
   private static Method sSetTransitionAlphaMethod;
   private static boolean sSetTransitionAlphaMethodFetched;

   private void fetchGetTransitionAlphaMethod() {
      if (!sGetTransitionAlphaMethodFetched) {
         try {
            Method var1 = View.class.getDeclaredMethod("getTransitionAlpha");
            sGetTransitionAlphaMethod = var1;
            var1.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi19", "Failed to retrieve getTransitionAlpha method", var2);
         }

         sGetTransitionAlphaMethodFetched = true;
      }

   }

   private void fetchSetTransitionAlphaMethod() {
      if (!sSetTransitionAlphaMethodFetched) {
         try {
            Method var1 = View.class.getDeclaredMethod("setTransitionAlpha", Float.TYPE);
            sSetTransitionAlphaMethod = var1;
            var1.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi19", "Failed to retrieve setTransitionAlpha method", var2);
         }

         sSetTransitionAlphaMethodFetched = true;
      }

   }

   public void clearNonTransitionAlpha(View var1) {
   }

   public float getTransitionAlpha(View var1) {
      this.fetchGetTransitionAlphaMethod();
      Method var3 = sGetTransitionAlphaMethod;
      if (var3 != null) {
         try {
            float var2 = (Float)var3.invoke(var1);
            return var2;
         } catch (IllegalAccessException var4) {
         } catch (InvocationTargetException var5) {
            throw new RuntimeException(var5.getCause());
         }
      }

      return super.getTransitionAlpha(var1);
   }

   public void saveNonTransitionAlpha(View var1) {
   }

   public void setTransitionAlpha(View var1, float var2) {
      this.fetchSetTransitionAlphaMethod();
      Method var3 = sSetTransitionAlphaMethod;
      if (var3 != null) {
         try {
            var3.invoke(var1, var2);
         } catch (IllegalAccessException var4) {
         } catch (InvocationTargetException var5) {
            throw new RuntimeException(var5.getCause());
         }
      } else {
         var1.setAlpha(var2);
      }

   }
}
