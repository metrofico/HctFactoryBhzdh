package com.google.android.material.internal;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class DescendantOffsetUtils {
   private static final ThreadLocal matrix = new ThreadLocal();
   private static final ThreadLocal rectF = new ThreadLocal();

   public static void getDescendantRect(ViewGroup var0, View var1, Rect var2) {
      var2.set(0, 0, var1.getWidth(), var1.getHeight());
      offsetDescendantRect(var0, var1, var2);
   }

   private static void offsetDescendantMatrix(ViewParent var0, View var1, Matrix var2) {
      ViewParent var3 = var1.getParent();
      if (var3 instanceof View && var3 != var0) {
         View var4 = (View)var3;
         offsetDescendantMatrix(var0, var4, var2);
         var2.preTranslate((float)(-var4.getScrollX()), (float)(-var4.getScrollY()));
      }

      var2.preTranslate((float)var1.getLeft(), (float)var1.getTop());
      if (!var1.getMatrix().isIdentity()) {
         var2.preConcat(var1.getMatrix());
      }

   }

   public static void offsetDescendantRect(ViewGroup var0, View var1, Rect var2) {
      ThreadLocal var4 = matrix;
      Matrix var3 = (Matrix)var4.get();
      if (var3 == null) {
         var3 = new Matrix();
         var4.set(var3);
      } else {
         var3.reset();
      }

      offsetDescendantMatrix(var0, var1, var3);
      var4 = rectF;
      RectF var6 = (RectF)var4.get();
      RectF var5 = var6;
      if (var6 == null) {
         var5 = new RectF();
         var4.set(var5);
      }

      var5.set(var2);
      var3.mapRect(var5);
      var2.set((int)(var5.left + 0.5F), (int)(var5.top + 0.5F), (int)(var5.right + 0.5F), (int)(var5.bottom + 0.5F));
   }
}
