package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bg\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0005H\u0016J\u001d\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\f¨\u0006\r"},
   d2 = {"Lkotlin/ranges/ClosedFloatingPointRange;", "T", "", "Lkotlin/ranges/ClosedRange;", "contains", "", "value", "(Ljava/lang/Comparable;)Z", "isEmpty", "lessThanOrEquals", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Z", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public interface ClosedFloatingPointRange extends ClosedRange {
   boolean contains(Comparable var1);

   boolean isEmpty();

   boolean lessThanOrEquals(Comparable var1, Comparable var2);

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class DefaultImpls {
      public static boolean contains(ClosedFloatingPointRange var0, Comparable var1) {
         Intrinsics.checkNotNullParameter(var1, "value");
         boolean var2;
         if (var0.lessThanOrEquals(var0.getStart(), var1) && var0.lessThanOrEquals(var1, var0.getEndInclusive())) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public static boolean isEmpty(ClosedFloatingPointRange var0) {
         return var0.lessThanOrEquals(var0.getStart(), var0.getEndInclusive()) ^ true;
      }
   }
}
