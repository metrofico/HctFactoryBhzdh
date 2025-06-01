package androidx.core.graphics;

import android.graphics.Point;
import android.graphics.PointF;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\n\u001a\r\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0086\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0086\n\u001a\r\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\b\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\b\u001a\u00020\u0003H\u0086\n\u001a\r\u0010\n\u001a\u00020\u0002*\u00020\u0004H\u0086\b\u001a\r\u0010\u000b\u001a\u00020\u0004*\u00020\u0002H\u0086\b\u001a\r\u0010\f\u001a\u00020\u0002*\u00020\u0002H\u0086\n\u001a\r\u0010\f\u001a\u00020\u0004*\u00020\u0004H\u0086\n¨\u0006\r"},
   d2 = {"component1", "", "Landroid/graphics/Point;", "", "Landroid/graphics/PointF;", "component2", "minus", "p", "xy", "plus", "toPoint", "toPointF", "unaryMinus", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class PointKt {
   public static final float component1(PointF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component1");
      return var0.x;
   }

   public static final int component1(Point var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component1");
      return var0.x;
   }

   public static final float component2(PointF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component2");
      return var0.y;
   }

   public static final int component2(Point var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component2");
      return var0.y;
   }

   public static final Point minus(Point var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      var0 = new Point(var0.x, var0.y);
      var1 = -var1;
      var0.offset(var1, var1);
      return var0;
   }

   public static final Point minus(Point var0, Point var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      var0 = new Point(var0.x, var0.y);
      var0.offset(-var1.x, -var1.y);
      return var0;
   }

   public static final PointF minus(PointF var0, float var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      var0 = new PointF(var0.x, var0.y);
      var1 = -var1;
      var0.offset(var1, var1);
      return var0;
   }

   public static final PointF minus(PointF var0, PointF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      var0 = new PointF(var0.x, var0.y);
      var0.offset(-var1.x, -var1.y);
      return var0;
   }

   public static final Point plus(Point var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      var0 = new Point(var0.x, var0.y);
      var0.offset(var1, var1);
      return var0;
   }

   public static final Point plus(Point var0, Point var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      var0 = new Point(var0.x, var0.y);
      var0.offset(var1.x, var1.y);
      return var0;
   }

   public static final PointF plus(PointF var0, float var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      var0 = new PointF(var0.x, var0.y);
      var0.offset(var1, var1);
      return var0;
   }

   public static final PointF plus(PointF var0, PointF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      var0 = new PointF(var0.x, var0.y);
      var0.offset(var1.x, var1.y);
      return var0;
   }

   public static final Point toPoint(PointF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toPoint");
      return new Point((int)var0.x, (int)var0.y);
   }

   public static final PointF toPointF(Point var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toPointF");
      return new PointF(var0);
   }

   public static final Point unaryMinus(Point var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$unaryMinus");
      return new Point(-var0.x, -var0.y);
   }

   public static final PointF unaryMinus(PointF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$unaryMinus");
      return new PointF(-var0.x, -var0.y);
   }
}
