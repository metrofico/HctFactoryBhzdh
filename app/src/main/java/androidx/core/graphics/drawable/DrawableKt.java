package androidx.core.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0003\u0010\u0003\u001a\u00020\u00042\b\b\u0003\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a2\u0010\b\u001a\u00020\t*\u00020\u00022\b\b\u0003\u0010\n\u001a\u00020\u00042\b\b\u0003\u0010\u000b\u001a\u00020\u00042\b\b\u0003\u0010\f\u001a\u00020\u00042\b\b\u0003\u0010\r\u001a\u00020\u0004¨\u0006\u000e"},
   d2 = {"toBitmap", "Landroid/graphics/Bitmap;", "Landroid/graphics/drawable/Drawable;", "width", "", "height", "config", "Landroid/graphics/Bitmap$Config;", "updateBounds", "", "left", "top", "right", "bottom", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class DrawableKt {
   public static final Bitmap toBitmap(Drawable var0, int var1, int var2, Bitmap.Config var3) {
      label28: {
         Intrinsics.checkParameterIsNotNull(var0, "$this$toBitmap");
         if (var0 instanceof BitmapDrawable) {
            if (var3 == null) {
               break label28;
            }

            Bitmap var8 = ((BitmapDrawable)var0).getBitmap();
            Intrinsics.checkExpressionValueIsNotNull(var8, "bitmap");
            if (var8.getConfig() == var3) {
               break label28;
            }
         }

         Rect var12 = var0.getBounds();
         int var4 = var12.left;
         int var6 = var12.top;
         int var7 = var12.right;
         int var5 = var12.bottom;
         if (var3 == null) {
            var3 = Config.ARGB_8888;
         }

         Bitmap var11 = Bitmap.createBitmap(var1, var2, var3);
         var0.setBounds(0, 0, var1, var2);
         var0.draw(new Canvas(var11));
         var0.setBounds(var4, var6, var7, var5);
         Intrinsics.checkExpressionValueIsNotNull(var11, "bitmap");
         return var11;
      }

      BitmapDrawable var9 = (BitmapDrawable)var0;
      Bitmap var10;
      if (var1 == var9.getIntrinsicWidth() && var2 == var9.getIntrinsicHeight()) {
         var10 = var9.getBitmap();
         Intrinsics.checkExpressionValueIsNotNull(var10, "bitmap");
         return var10;
      } else {
         var10 = Bitmap.createScaledBitmap(var9.getBitmap(), var1, var2, true);
         Intrinsics.checkExpressionValueIsNotNull(var10, "Bitmap.createScaledBitma…map, width, height, true)");
         return var10;
      }
   }

   // $FF: synthetic method
   public static Bitmap toBitmap$default(Drawable var0, int var1, int var2, Bitmap.Config var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.getIntrinsicWidth();
      }

      if ((var4 & 2) != 0) {
         var2 = var0.getIntrinsicHeight();
      }

      if ((var4 & 4) != 0) {
         var3 = null;
         Bitmap.Config var6 = (Bitmap.Config)null;
      }

      return toBitmap(var0, var1, var2, var3);
   }

   public static final void updateBounds(Drawable var0, int var1, int var2, int var3, int var4) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$updateBounds");
      var0.setBounds(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void updateBounds$default(Drawable var0, int var1, int var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = var0.getBounds().left;
      }

      if ((var5 & 2) != 0) {
         var2 = var0.getBounds().top;
      }

      if ((var5 & 4) != 0) {
         var3 = var0.getBounds().right;
      }

      if ((var5 & 8) != 0) {
         var4 = var0.getBounds().bottom;
      }

      updateBounds(var0, var1, var2, var3, var4);
   }
}
