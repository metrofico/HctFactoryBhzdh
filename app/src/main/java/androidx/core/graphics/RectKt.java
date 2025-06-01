package androidx.core.graphics;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Region.Op;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\r\u0010\u0004\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\u0004\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\r\u0010\u0007\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\u0007\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\r\u0010\b\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\b\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\r\u0010\t\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\t\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0086\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\u000eH\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u0015\u0010\u0012\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\f\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\u0086\n\u001a\r\u0010\u0014\u001a\u00020\u0001*\u00020\u0003H\u0086\b\u001a\r\u0010\u0015\u001a\u00020\u0003*\u00020\u0001H\u0086\b\u001a\r\u0010\u0016\u001a\u00020\u0011*\u00020\u0001H\u0086\b\u001a\r\u0010\u0016\u001a\u00020\u0011*\u00020\u0003H\u0086\b\u001a\u0015\u0010\u0017\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0086\b\u001a\u0015\u0010\u001a\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u0015\u0010\u001a\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\f¨\u0006\u001b"},
   d2 = {"and", "Landroid/graphics/Rect;", "r", "Landroid/graphics/RectF;", "component1", "", "", "component2", "component3", "component4", "contains", "", "p", "Landroid/graphics/Point;", "Landroid/graphics/PointF;", "minus", "xy", "Landroid/graphics/Region;", "or", "plus", "toRect", "toRectF", "toRegion", "transform", "m", "Landroid/graphics/Matrix;", "xor", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class RectKt {
   public static final Rect and(Rect var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$and");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Rect(var0);
      var0.intersect(var1);
      return var0;
   }

   public static final RectF and(RectF var0, RectF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$and");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new RectF(var0);
      var0.intersect(var1);
      return var0;
   }

   public static final float component1(RectF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component1");
      return var0.left;
   }

   public static final int component1(Rect var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component1");
      return var0.left;
   }

   public static final float component2(RectF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component2");
      return var0.top;
   }

   public static final int component2(Rect var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component2");
      return var0.top;
   }

   public static final float component3(RectF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component3");
      return var0.right;
   }

   public static final int component3(Rect var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component3");
      return var0.right;
   }

   public static final float component4(RectF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component4");
      return var0.bottom;
   }

   public static final int component4(Rect var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component4");
      return var0.bottom;
   }

   public static final boolean contains(Rect var0, Point var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      return var0.contains(var1.x, var1.y);
   }

   public static final boolean contains(RectF var0, PointF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      return var0.contains(var1.x, var1.y);
   }

   public static final Rect minus(Rect var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      var0 = new Rect(var0);
      var1 = -var1;
      var0.offset(var1, var1);
      return var0;
   }

   public static final Rect minus(Rect var0, Point var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      Intrinsics.checkParameterIsNotNull(var1, "xy");
      var0 = new Rect(var0);
      var0.offset(-var1.x, -var1.y);
      return var0;
   }

   public static final RectF minus(RectF var0, float var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      var0 = new RectF(var0);
      var1 = -var1;
      var0.offset(var1, var1);
      return var0;
   }

   public static final RectF minus(RectF var0, PointF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      Intrinsics.checkParameterIsNotNull(var1, "xy");
      var0 = new RectF(var0);
      var0.offset(-var1.x, -var1.y);
      return var0;
   }

   public static final Region minus(Rect var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      Region var2 = new Region(var0);
      var2.op(var1, Op.DIFFERENCE);
      return var2;
   }

   public static final Region minus(RectF var0, RectF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      Rect var2 = new Rect();
      var0.roundOut(var2);
      Region var4 = new Region(var2);
      Rect var3 = new Rect();
      var1.roundOut(var3);
      var4.op(var3, Op.DIFFERENCE);
      return var4;
   }

   public static final Rect or(Rect var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$or");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Rect(var0);
      var0.union(var1);
      return var0;
   }

   public static final RectF or(RectF var0, RectF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$or");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new RectF(var0);
      var0.union(var1);
      return var0;
   }

   public static final Rect plus(Rect var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      var0 = new Rect(var0);
      var0.offset(var1, var1);
      return var0;
   }

   public static final Rect plus(Rect var0, Point var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "xy");
      var0 = new Rect(var0);
      var0.offset(var1.x, var1.y);
      return var0;
   }

   public static final Rect plus(Rect var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Rect(var0);
      var0.union(var1);
      return var0;
   }

   public static final RectF plus(RectF var0, float var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      var0 = new RectF(var0);
      var0.offset(var1, var1);
      return var0;
   }

   public static final RectF plus(RectF var0, PointF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "xy");
      var0 = new RectF(var0);
      var0.offset(var1.x, var1.y);
      return var0;
   }

   public static final RectF plus(RectF var0, RectF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new RectF(var0);
      var0.union(var1);
      return var0;
   }

   public static final Rect toRect(RectF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toRect");
      Rect var1 = new Rect();
      var0.roundOut(var1);
      return var1;
   }

   public static final RectF toRectF(Rect var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toRectF");
      return new RectF(var0);
   }

   public static final Region toRegion(Rect var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toRegion");
      return new Region(var0);
   }

   public static final Region toRegion(RectF var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toRegion");
      Rect var1 = new Rect();
      var0.roundOut(var1);
      return new Region(var1);
   }

   public static final RectF transform(RectF var0, Matrix var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$transform");
      Intrinsics.checkParameterIsNotNull(var1, "m");
      var1.mapRect(var0);
      return var0;
   }

   public static final Region xor(Rect var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$xor");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      Region var2 = new Region(var0);
      var2.op(var1, Op.XOR);
      return var2;
   }

   public static final Region xor(RectF var0, RectF var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$xor");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      Rect var2 = new Rect();
      var0.roundOut(var2);
      Region var3 = new Region(var2);
      var2 = new Rect();
      var1.roundOut(var2);
      var3.op(var2, Op.XOR);
      return var3;
   }
}
