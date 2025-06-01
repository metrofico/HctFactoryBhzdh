package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\nH\u0016R\u0012\u0010\u0004\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u000e"},
   d2 = {"Lkotlin/ranges/ClosedRange;", "T", "", "", "endInclusive", "getEndInclusive", "()Ljava/lang/Comparable;", "start", "getStart", "contains", "", "value", "(Ljava/lang/Comparable;)Z", "isEmpty", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public interface ClosedRange {
   boolean contains(Comparable var1);

   Comparable getEndInclusive();

   Comparable getStart();

   boolean isEmpty();

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class DefaultImpls {
      public static boolean contains(ClosedRange var0, Comparable var1) {
         Intrinsics.checkNotNullParameter(var1, "value");
         boolean var2;
         if (var1.compareTo(var0.getStart()) >= 0 && var1.compareTo(var0.getEndInclusive()) <= 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public static boolean isEmpty(ClosedRange var0) {
         boolean var1;
         if (var0.getStart().compareTo(var0.getEndInclusive()) > 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }
   }
}
