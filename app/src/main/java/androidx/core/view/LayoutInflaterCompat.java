package androidx.core.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Field;

public final class LayoutInflaterCompat {
   private static final String TAG = "LayoutInflaterCompatHC";
   private static boolean sCheckedField;
   private static Field sLayoutInflaterFactory2Field;

   private LayoutInflaterCompat() {
   }

   private static void forceSetFactory2(LayoutInflater var0, LayoutInflater.Factory2 var1) {
      Field var2;
      if (!sCheckedField) {
         try {
            var2 = LayoutInflater.class.getDeclaredField("mFactory2");
            sLayoutInflaterFactory2Field = var2;
            var2.setAccessible(true);
         } catch (NoSuchFieldException var4) {
            Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field 'mFactory2' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", var4);
         }

         sCheckedField = true;
      }

      var2 = sLayoutInflaterFactory2Field;
      if (var2 != null) {
         try {
            var2.set(var0, var1);
         } catch (IllegalAccessException var3) {
            Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + var0 + "; inflation may have unexpected results.", var3);
         }
      }

   }

   @Deprecated
   public static LayoutInflaterFactory getFactory(LayoutInflater var0) {
      LayoutInflater.Factory var1 = var0.getFactory();
      return var1 instanceof Factory2Wrapper ? ((Factory2Wrapper)var1).mDelegateFactory : null;
   }

   @Deprecated
   public static void setFactory(LayoutInflater var0, LayoutInflaterFactory var1) {
      int var2 = VERSION.SDK_INT;
      Factory2Wrapper var3 = null;
      Object var4 = null;
      if (var2 >= 21) {
         var3 = (Factory2Wrapper)var4;
         if (var1 != null) {
            var3 = new Factory2Wrapper(var1);
         }

         var0.setFactory2(var3);
      } else {
         if (var1 != null) {
            var3 = new Factory2Wrapper(var1);
         }

         var0.setFactory2(var3);
         LayoutInflater.Factory var5 = var0.getFactory();
         if (var5 instanceof LayoutInflater.Factory2) {
            forceSetFactory2(var0, (LayoutInflater.Factory2)var5);
         } else {
            forceSetFactory2(var0, var3);
         }
      }

   }

   public static void setFactory2(LayoutInflater var0, LayoutInflater.Factory2 var1) {
      var0.setFactory2(var1);
      if (VERSION.SDK_INT < 21) {
         LayoutInflater.Factory var2 = var0.getFactory();
         if (var2 instanceof LayoutInflater.Factory2) {
            forceSetFactory2(var0, (LayoutInflater.Factory2)var2);
         } else {
            forceSetFactory2(var0, var1);
         }
      }

   }

   static class Factory2Wrapper implements LayoutInflater.Factory2 {
      final LayoutInflaterFactory mDelegateFactory;

      Factory2Wrapper(LayoutInflaterFactory var1) {
         this.mDelegateFactory = var1;
      }

      public View onCreateView(View var1, String var2, Context var3, AttributeSet var4) {
         return this.mDelegateFactory.onCreateView(var1, var2, var3, var4);
      }

      public View onCreateView(String var1, Context var2, AttributeSet var3) {
         return this.mDelegateFactory.onCreateView((View)null, var1, var2, var3);
      }

      public String toString() {
         return this.getClass().getName() + "{" + this.mDelegateFactory + "}";
      }
   }
}
