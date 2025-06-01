package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Bitmap.Config;
import android.graphics.ColorSpace.Named;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a&\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b\u001a\u0015\u0010\u0011\u001a\u00020\b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\n\u001a\u0015\u0010\u0011\u001a\u00020\b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0014H\u0086\n\u001a\u001d\u0010\u0015\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0086\n\u001a'\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\bH\u0086\b\u001a'\u0010\u001a\u001a\u00020\u000f*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\b\b\u0001\u0010\u001b\u001a\u00020\u0003H\u0086\n¨\u0006\u001c"},
   d2 = {"createBitmap", "Landroid/graphics/Bitmap;", "width", "", "height", "config", "Landroid/graphics/Bitmap$Config;", "hasAlpha", "", "colorSpace", "Landroid/graphics/ColorSpace;", "applyCanvas", "block", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "", "Lkotlin/ExtensionFunctionType;", "contains", "p", "Landroid/graphics/Point;", "Landroid/graphics/PointF;", "get", "x", "y", "scale", "filter", "set", "color", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class BitmapKt {
   public static final Bitmap applyCanvas(Bitmap var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$applyCanvas");
      Intrinsics.checkParameterIsNotNull(var1, "block");
      var1.invoke(new Canvas(var0));
      return var0;
   }

   public static final boolean contains(Bitmap var0, Point var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      boolean var2;
      if (var1.x >= 0 && var1.x < var0.getWidth() && var1.y >= 0 && var1.y < var0.getHeight()) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean contains(Bitmap var0, PointF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      float var3 = var1.x;
      boolean var5 = false;
      float var2 = (float)0;
      boolean var4 = var5;
      if (var3 >= var2) {
         var4 = var5;
         if (var1.x < (float)var0.getWidth()) {
            var4 = var5;
            if (var1.y >= var2) {
               var4 = var5;
               if (var1.y < (float)var0.getHeight()) {
                  var4 = true;
               }
            }
         }
      }

      return var4;
   }

   public static final Bitmap createBitmap(int var0, int var1, Bitmap.Config var2) {
      Intrinsics.checkParameterIsNotNull(var2, "config");
      Bitmap var3 = Bitmap.createBitmap(var0, var1, var2);
      Intrinsics.checkExpressionValueIsNotNull(var3, "Bitmap.createBitmap(width, height, config)");
      return var3;
   }

   public static final Bitmap createBitmap(int var0, int var1, Bitmap.Config var2, boolean var3, ColorSpace var4) {
      Intrinsics.checkParameterIsNotNull(var2, "config");
      Intrinsics.checkParameterIsNotNull(var4, "colorSpace");
      Bitmap var5 = Bitmap.createBitmap(var0, var1, var2, var3, var4);
      Intrinsics.checkExpressionValueIsNotNull(var5, "Bitmap.createBitmap(widt…ig, hasAlpha, colorSpace)");
      return var5;
   }

   // $FF: synthetic method
   public static Bitmap createBitmap$default(int var0, int var1, Bitmap.Config var2, int var3, Object var4) {
      if ((var3 & 4) != 0) {
         var2 = Config.ARGB_8888;
      }

      Intrinsics.checkParameterIsNotNull(var2, "config");
      Bitmap var5 = Bitmap.createBitmap(var0, var1, var2);
      Intrinsics.checkExpressionValueIsNotNull(var5, "Bitmap.createBitmap(width, height, config)");
      return var5;
   }

   // $FF: synthetic method
   public static Bitmap createBitmap$default(int var0, int var1, Bitmap.Config var2, boolean var3, ColorSpace var4, int var5, Object var6) {
      if ((var5 & 4) != 0) {
         var2 = Config.ARGB_8888;
      }

      if ((var5 & 8) != 0) {
         var3 = true;
      }

      if ((var5 & 16) != 0) {
         var4 = ColorSpace.get(Named.SRGB);
         Intrinsics.checkExpressionValueIsNotNull(var4, "ColorSpace.get(ColorSpace.Named.SRGB)");
      }

      Intrinsics.checkParameterIsNotNull(var2, "config");
      Intrinsics.checkParameterIsNotNull(var4, "colorSpace");
      Bitmap var7 = Bitmap.createBitmap(var0, var1, var2, var3, var4);
      Intrinsics.checkExpressionValueIsNotNull(var7, "Bitmap.createBitmap(widt…ig, hasAlpha, colorSpace)");
      return var7;
   }

   public static final int get(Bitmap var0, int var1, int var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$get");
      return var0.getPixel(var1, var2);
   }

   public static final Bitmap scale(Bitmap var0, int var1, int var2, boolean var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$scale");
      var0 = Bitmap.createScaledBitmap(var0, var1, var2, var3);
      Intrinsics.checkExpressionValueIsNotNull(var0, "Bitmap.createScaledBitma…s, width, height, filter)");
      return var0;
   }

   // $FF: synthetic method
   public static Bitmap scale$default(Bitmap var0, int var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = true;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$scale");
      var0 = Bitmap.createScaledBitmap(var0, var1, var2, var3);
      Intrinsics.checkExpressionValueIsNotNull(var0, "Bitmap.createScaledBitma…s, width, height, filter)");
      return var0;
   }

   public static final void set(Bitmap var0, int var1, int var2, int var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$set");
      var0.setPixel(var1, var2, var3);
   }
}
