package androidx.core.graphics;

import android.graphics.Path;
import android.graphics.Path.Op;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f¨\u0006\f"},
   d2 = {"and", "Landroid/graphics/Path;", "p", "flatten", "", "Landroidx/core/graphics/PathSegment;", "error", "", "minus", "or", "plus", "xor", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class PathKt {
   public static final Path and(Path var0, Path var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$and");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      Path var2 = new Path();
      var2.op(var0, var1, Op.INTERSECT);
      return var2;
   }

   public static final Iterable flatten(Path var0, float var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$flatten");
      Collection var2 = PathUtils.flatten(var0, var1);
      Intrinsics.checkExpressionValueIsNotNull(var2, "PathUtils.flatten(this, error)");
      return (Iterable)var2;
   }

   // $FF: synthetic method
   public static Iterable flatten$default(Path var0, float var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 0.5F;
      }

      return flatten(var0, var1);
   }

   public static final Path minus(Path var0, Path var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      var0 = new Path(var0);
      var0.op(var1, Op.DIFFERENCE);
      return var0;
   }

   public static final Path or(Path var0, Path var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$or");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      var0 = new Path(var0);
      var0.op(var1, Op.UNION);
      return var0;
   }

   public static final Path plus(Path var0, Path var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      var0 = new Path(var0);
      var0.op(var1, Op.UNION);
      return var0;
   }

   public static final Path xor(Path var0, Path var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$xor");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      var0 = new Path(var0);
      var0.op(var1, Op.XOR);
      return var0;
   }
}
