package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a@\u0010\u0006\u001a\u00020\u0003\"\b\b\u0000\u0010\u0007*\u00020\b\"\u0018\b\u0001\u0010\t*\b\u0012\u0004\u0012\u0002H\u00070\n*\b\u0012\u0004\u0012\u0002H\u00070\u000b*\u0002H\t2\b\u0010\f\u001a\u0004\u0018\u0001H\u0007H\u0087\n¢\u0006\u0002\u0010\r\u001a0\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000b\"\u000e\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u000f*\u0002H\u00072\u0006\u0010\u0010\u001a\u0002H\u0007H\u0086\u0002¢\u0006\u0002\u0010\u0011\u001a\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0013H\u0087\u0002\u001a\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00140\u0012*\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0014H\u0087\u0002¨\u0006\u0015"},
   d2 = {"checkStepIsPositive", "", "isPositive", "", "step", "", "contains", "T", "", "R", "", "Lkotlin/ranges/ClosedRange;", "element", "(Ljava/lang/Iterable;Ljava/lang/Object;)Z", "rangeTo", "", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Lkotlin/ranges/ClosedRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/ranges/RangesKt"
)
class RangesKt__RangesKt {
   public RangesKt__RangesKt() {
   }

   public static final void checkStepIsPositive(boolean var0, Number var1) {
      Intrinsics.checkNotNullParameter(var1, "step");
      if (!var0) {
         throw new IllegalArgumentException("Step must be positive, was: " + var1 + '.');
      }
   }

   private static final boolean contains(Iterable var0, Object var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var2;
      if (var1 != null && ((ClosedRange)var0).contains((Comparable)var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final ClosedFloatingPointRange rangeTo(double var0, double var2) {
      return (ClosedFloatingPointRange)(new ClosedDoubleRange(var0, var2));
   }

   public static final ClosedFloatingPointRange rangeTo(float var0, float var1) {
      return (ClosedFloatingPointRange)(new ClosedFloatRange(var0, var1));
   }

   public static final ClosedRange rangeTo(Comparable var0, Comparable var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "that");
      return (ClosedRange)(new ComparableRange(var0, var1));
   }
}
