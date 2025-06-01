package kotlin;

import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0087\f¢\u0006\u0002\u0010\u0005¨\u0006\u0006"},
   d2 = {"compareTo", "", "T", "", "other", "(Ljava/lang/Comparable;Ljava/lang/Object;)I", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class CompareToKt {
   private static final int compareTo(Comparable var0, Object var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.compareTo(var1);
   }
}
