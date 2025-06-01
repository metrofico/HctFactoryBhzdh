package com.android.common;

import android.content.SharedPreferences;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SharedPreferencesCompat {
   private static Method sApplyMethod;

   static {
      try {
         sApplyMethod = SharedPreferences.Editor.class.getMethod("apply");
      } catch (NoSuchMethodException var1) {
         sApplyMethod = null;
      }

   }

   public static void apply(SharedPreferences.Editor var0) {
      Method var1 = sApplyMethod;
      if (var1 != null) {
         try {
            var1.invoke(var0);
            return;
         } catch (InvocationTargetException var2) {
         } catch (IllegalAccessException var3) {
         }
      }

      var0.commit();
   }
}
