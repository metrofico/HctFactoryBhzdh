package androidx.core.graphics;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\n2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001aF\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001aF\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001a0\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001aD\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\f2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001a&\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001aN\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\f2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001a:\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\f2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b\u001a:\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\f2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b¨\u0006\u001e"},
   d2 = {"withClip", "", "Landroid/graphics/Canvas;", "clipPath", "Landroid/graphics/Path;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "clipRect", "Landroid/graphics/Rect;", "Landroid/graphics/RectF;", "left", "", "top", "right", "bottom", "", "withMatrix", "matrix", "Landroid/graphics/Matrix;", "withRotation", "degrees", "pivotX", "pivotY", "withSave", "withScale", "x", "y", "withSkew", "withTranslation", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class CanvasKt {
   public static final void withClip(Canvas var0, float var1, float var2, float var3, float var4, Function1 var5) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withClip");
      Intrinsics.checkParameterIsNotNull(var5, "block");
      int var6 = var0.save();
      var0.clipRect(var1, var2, var3, var4);

      try {
         var5.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var6);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withClip(Canvas var0, int var1, int var2, int var3, int var4, Function1 var5) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withClip");
      Intrinsics.checkParameterIsNotNull(var5, "block");
      int var6 = var0.save();
      var0.clipRect(var1, var2, var3, var4);

      try {
         var5.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var6);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withClip(Canvas var0, Path var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withClip");
      Intrinsics.checkParameterIsNotNull(var1, "clipPath");
      Intrinsics.checkParameterIsNotNull(var2, "block");
      int var3 = var0.save();
      var0.clipPath(var1);

      try {
         var2.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var3);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withClip(Canvas var0, Rect var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withClip");
      Intrinsics.checkParameterIsNotNull(var1, "clipRect");
      Intrinsics.checkParameterIsNotNull(var2, "block");
      int var3 = var0.save();
      var0.clipRect(var1);

      try {
         var2.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var3);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withClip(Canvas var0, RectF var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withClip");
      Intrinsics.checkParameterIsNotNull(var1, "clipRect");
      Intrinsics.checkParameterIsNotNull(var2, "block");
      int var3 = var0.save();
      var0.clipRect(var1);

      try {
         var2.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var3);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withMatrix(Canvas var0, Matrix var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withMatrix");
      Intrinsics.checkParameterIsNotNull(var1, "matrix");
      Intrinsics.checkParameterIsNotNull(var2, "block");
      int var3 = var0.save();
      var0.concat(var1);

      try {
         var2.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var3);
         InlineMarker.finallyEnd(1);
      }

   }

   // $FF: synthetic method
   public static void withMatrix$default(Canvas var0, Matrix var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = new Matrix();
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$withMatrix");
      Intrinsics.checkParameterIsNotNull(var1, "matrix");
      Intrinsics.checkParameterIsNotNull(var2, "block");
      var3 = var0.save();
      var0.concat(var1);

      try {
         var2.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var3);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withRotation(Canvas var0, float var1, float var2, float var3, Function1 var4) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withRotation");
      Intrinsics.checkParameterIsNotNull(var4, "block");
      int var5 = var0.save();
      var0.rotate(var1, var2, var3);

      try {
         var4.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var5);
         InlineMarker.finallyEnd(1);
      }

   }

   // $FF: synthetic method
   public static void withRotation$default(Canvas var0, float var1, float var2, float var3, Function1 var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = 0.0F;
      }

      if ((var5 & 2) != 0) {
         var2 = 0.0F;
      }

      if ((var5 & 4) != 0) {
         var3 = 0.0F;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$withRotation");
      Intrinsics.checkParameterIsNotNull(var4, "block");
      var5 = var0.save();
      var0.rotate(var1, var2, var3);

      try {
         var4.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var5);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withSave(Canvas var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withSave");
      Intrinsics.checkParameterIsNotNull(var1, "block");
      int var2 = var0.save();

      try {
         var1.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var2);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withScale(Canvas var0, float var1, float var2, float var3, float var4, Function1 var5) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withScale");
      Intrinsics.checkParameterIsNotNull(var5, "block");
      int var6 = var0.save();
      var0.scale(var1, var2, var3, var4);

      try {
         var5.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var6);
         InlineMarker.finallyEnd(1);
      }

   }

   // $FF: synthetic method
   public static void withScale$default(Canvas var0, float var1, float var2, float var3, float var4, Function1 var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var1 = 1.0F;
      }

      if ((var6 & 2) != 0) {
         var2 = 1.0F;
      }

      if ((var6 & 4) != 0) {
         var3 = 0.0F;
      }

      if ((var6 & 8) != 0) {
         var4 = 0.0F;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$withScale");
      Intrinsics.checkParameterIsNotNull(var5, "block");
      var6 = var0.save();
      var0.scale(var1, var2, var3, var4);

      try {
         var5.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var6);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withSkew(Canvas var0, float var1, float var2, Function1 var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withSkew");
      Intrinsics.checkParameterIsNotNull(var3, "block");
      int var4 = var0.save();
      var0.skew(var1, var2);

      try {
         var3.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var4);
         InlineMarker.finallyEnd(1);
      }

   }

   // $FF: synthetic method
   public static void withSkew$default(Canvas var0, float var1, float var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0.0F;
      }

      if ((var4 & 2) != 0) {
         var2 = 0.0F;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$withSkew");
      Intrinsics.checkParameterIsNotNull(var3, "block");
      var4 = var0.save();
      var0.skew(var1, var2);

      try {
         var3.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var4);
         InlineMarker.finallyEnd(1);
      }

   }

   public static final void withTranslation(Canvas var0, float var1, float var2, Function1 var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withTranslation");
      Intrinsics.checkParameterIsNotNull(var3, "block");
      int var4 = var0.save();
      var0.translate(var1, var2);

      try {
         var3.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var4);
         InlineMarker.finallyEnd(1);
      }

   }

   // $FF: synthetic method
   public static void withTranslation$default(Canvas var0, float var1, float var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0.0F;
      }

      if ((var4 & 2) != 0) {
         var2 = 0.0F;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$withTranslation");
      Intrinsics.checkParameterIsNotNull(var3, "block");
      var4 = var0.save();
      var0.translate(var1, var2);

      try {
         var3.invoke(var0);
      } finally {
         InlineMarker.finallyStart(1);
         var0.restoreToCount(var4);
         InlineMarker.finallyEnd(1);
      }

   }
}
