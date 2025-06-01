package androidx.transition;

import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewGroupUtilsApi18 {
   private static final String TAG = "ViewUtilsApi18";
   private static Method sSuppressLayoutMethod;
   private static boolean sSuppressLayoutMethodFetched;

   private ViewGroupUtilsApi18() {
   }

   private static void fetchSuppressLayoutMethod() {
      if (!sSuppressLayoutMethodFetched) {
         try {
            Method var0 = ViewGroup.class.getDeclaredMethod("suppressLayout", Boolean.TYPE);
            sSuppressLayoutMethod = var0;
            var0.setAccessible(true);
         } catch (NoSuchMethodException var1) {
            Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", var1);
         }

         sSuppressLayoutMethodFetched = true;
      }

   }

   static void suppressLayout(ViewGroup var0, boolean var1) {
      fetchSuppressLayoutMethod();
      Method var2 = sSuppressLayoutMethod;
      if (var2 != null) {
         try {
            var2.invoke(var0, var1);
         } catch (IllegalAccessException var3) {
            Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", var3);
         } catch (InvocationTargetException var4) {
            Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", var4);
         }
      }

   }
}
