package androidx.core.graphics;

import android.graphics.Canvas;
import android.graphics.Picture;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086\b¨\u0006\n"},
   d2 = {"record", "Landroid/graphics/Picture;", "width", "", "height", "block", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "", "Lkotlin/ExtensionFunctionType;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class PictureKt {
   public static final Picture record(Picture var0, int var1, int var2, Function1 var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$record");
      Intrinsics.checkParameterIsNotNull(var3, "block");
      Canvas var4 = var0.beginRecording(var1, var2);

      try {
         Intrinsics.checkExpressionValueIsNotNull(var4, "c");
         var3.invoke(var4);
      } finally {
         InlineMarker.finallyStart(1);
         var0.endRecording();
         InlineMarker.finallyEnd(1);
      }

      return var0;
   }
}
