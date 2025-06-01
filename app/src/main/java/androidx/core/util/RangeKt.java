package androidx.core.util;

import android.util.Range;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\f\u001a6\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0007\u001a7\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\n\u001a0\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\u0002H\u00022\u0006\u0010\t\u001a\u0002H\u0002H\u0087\f¢\u0006\u0002\u0010\n\u001a(\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a(\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\fH\u0007¨\u0006\u000e"},
   d2 = {"and", "Landroid/util/Range;", "T", "", "other", "plus", "value", "(Landroid/util/Range;Ljava/lang/Comparable;)Landroid/util/Range;", "rangeTo", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Landroid/util/Range;", "toClosedRange", "Lkotlin/ranges/ClosedRange;", "toRange", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class RangeKt {
   public static final Range and(Range var0, Range var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$and");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      var0 = var0.intersect(var1);
      Intrinsics.checkExpressionValueIsNotNull(var0, "intersect(other)");
      return var0;
   }

   public static final Range plus(Range var0, Range var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      var0 = var0.extend(var1);
      Intrinsics.checkExpressionValueIsNotNull(var0, "extend(other)");
      return var0;
   }

   public static final Range plus(Range var0, Comparable var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "value");
      var0 = var0.extend(var1);
      Intrinsics.checkExpressionValueIsNotNull(var0, "extend(value)");
      return var0;
   }

   public static final Range rangeTo(Comparable var0, Comparable var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$rangeTo");
      Intrinsics.checkParameterIsNotNull(var1, "that");
      return new Range(var0, var1);
   }

   public static final ClosedRange toClosedRange(Range var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toClosedRange");
      return (ClosedRange)(new ClosedRange(var0) {
         final Range $this_toClosedRange;

         {
            this.$this_toClosedRange = var1;
         }

         public boolean contains(Comparable var1) {
            Intrinsics.checkParameterIsNotNull(var1, "value");
            return ClosedRange.DefaultImpls.contains(this, var1);
         }

         public Comparable getEndInclusive() {
            return this.$this_toClosedRange.getUpper();
         }

         public Comparable getStart() {
            return this.$this_toClosedRange.getLower();
         }

         public boolean isEmpty() {
            return ClosedRange.DefaultImpls.isEmpty(this);
         }
      });
   }

   public static final Range toRange(ClosedRange var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toRange");
      return new Range(var0.getStart(), var0.getEndInclusive());
   }
}
