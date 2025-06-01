package androidx.core.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"},
   d2 = {"toAdaptiveIcon", "Landroid/graphics/drawable/Icon;", "Landroid/graphics/Bitmap;", "toIcon", "Landroid/net/Uri;", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class IconKt {
   public static final Icon toAdaptiveIcon(Bitmap var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toAdaptiveIcon");
      Icon var1 = Icon.createWithAdaptiveBitmap(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Icon.createWithAdaptiveBitmap(this)");
      return var1;
   }

   public static final Icon toIcon(Bitmap var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toIcon");
      Icon var1 = Icon.createWithBitmap(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Icon.createWithBitmap(this)");
      return var1;
   }

   public static final Icon toIcon(Uri var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toIcon");
      Icon var1 = Icon.createWithContentUri(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Icon.createWithContentUri(this)");
      return var1;
   }

   public static final Icon toIcon(byte[] var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toIcon");
      Icon var1 = Icon.createWithData(var0, 0, var0.length);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Icon.createWithData(this, 0, size)");
      return var1;
   }
}
