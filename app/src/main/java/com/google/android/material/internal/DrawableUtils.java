package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.util.Log;
import java.lang.reflect.Method;

public class DrawableUtils {
   private static final String LOG_TAG = "DrawableUtils";
   private static Method setConstantStateMethod;
   private static boolean setConstantStateMethodFetched;

   private DrawableUtils() {
   }

   public static boolean setContainerConstantState(DrawableContainer var0, Drawable.ConstantState var1) {
      return setContainerConstantStateV9(var0, var1);
   }

   private static boolean setContainerConstantStateV9(DrawableContainer var0, Drawable.ConstantState var1) {
      Method var2;
      if (!setConstantStateMethodFetched) {
         try {
            var2 = DrawableContainer.class.getDeclaredMethod("setConstantState", DrawableContainer.DrawableContainerState.class);
            setConstantStateMethod = var2;
            var2.setAccessible(true);
         } catch (NoSuchMethodException var3) {
            Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
         }

         setConstantStateMethodFetched = true;
      }

      var2 = setConstantStateMethod;
      if (var2 != null) {
         try {
            var2.invoke(var0, var1);
            return true;
         } catch (Exception var4) {
            Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
         }
      }

      return false;
   }
}
