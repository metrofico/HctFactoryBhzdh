package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aR\u0010\u0000\u001a\u00020\u0001*\u00020\u00022C\b\u0004\u0010\u0003\u001a=\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\u0002\b\fH\u0087\b\u001aR\u0010\r\u001a\u00020\u000e*\u00020\u00022C\b\u0004\u0010\u0003\u001a=\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\u0002\b\fH\u0087\b¨\u0006\u000f"},
   d2 = {"decodeBitmap", "Landroid/graphics/Bitmap;", "Landroid/graphics/ImageDecoder$Source;", "action", "Lkotlin/Function3;", "Landroid/graphics/ImageDecoder;", "Landroid/graphics/ImageDecoder$ImageInfo;", "Lkotlin/ParameterName;", "name", "info", "source", "", "Lkotlin/ExtensionFunctionType;", "decodeDrawable", "Landroid/graphics/drawable/Drawable;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class ImageDecoderKt {
   public static final Bitmap decodeBitmap(ImageDecoder.Source var0, Function3 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$decodeBitmap");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Bitmap var2 = ImageDecoder.decodeBitmap(var0, (ImageDecoder.OnHeaderDecodedListener)(new ImageDecoder.OnHeaderDecodedListener(var1) {
         final Function3 $action;

         public {
            this.$action = var1;
         }

         public final void onHeaderDecoded(ImageDecoder var1, ImageDecoder.ImageInfo var2, ImageDecoder.Source var3) {
            Function3 var4 = this.$action;
            Intrinsics.checkExpressionValueIsNotNull(var1, "decoder");
            Intrinsics.checkExpressionValueIsNotNull(var2, "info");
            Intrinsics.checkExpressionValueIsNotNull(var3, "source");
            var4.invoke(var1, var2, var3);
         }
      }));
      Intrinsics.checkExpressionValueIsNotNull(var2, "ImageDecoder.decodeBitma…ction(info, source)\n    }");
      return var2;
   }

   public static final Drawable decodeDrawable(ImageDecoder.Source var0, Function3 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$decodeDrawable");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Drawable var2 = ImageDecoder.decodeDrawable(var0, (ImageDecoder.OnHeaderDecodedListener)(new ImageDecoder.OnHeaderDecodedListener(var1) {
         final Function3 $action;

         public {
            this.$action = var1;
         }

         public final void onHeaderDecoded(ImageDecoder var1, ImageDecoder.ImageInfo var2, ImageDecoder.Source var3) {
            Function3 var4 = this.$action;
            Intrinsics.checkExpressionValueIsNotNull(var1, "decoder");
            Intrinsics.checkExpressionValueIsNotNull(var2, "info");
            Intrinsics.checkExpressionValueIsNotNull(var3, "source");
            var4.invoke(var1, var2, var3);
         }
      }));
      Intrinsics.checkExpressionValueIsNotNull(var2, "ImageDecoder.decodeDrawa…ction(info, source)\n    }");
      return var2;
   }
}
