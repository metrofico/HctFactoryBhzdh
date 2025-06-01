package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0012\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0016\u0010\u0005\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0013"},
   d2 = {"Lkotlin/ranges/ComparableRange;", "T", "", "Lkotlin/ranges/ClosedRange;", "start", "endInclusive", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "getEndInclusive", "()Ljava/lang/Comparable;", "Ljava/lang/Comparable;", "getStart", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
class ComparableRange implements ClosedRange {
   private final Comparable endInclusive;
   private final Comparable start;

   public ComparableRange(Comparable var1, Comparable var2) {
      Intrinsics.checkNotNullParameter(var1, "start");
      Intrinsics.checkNotNullParameter(var2, "endInclusive");
      super();
      this.start = var1;
      this.endInclusive = var2;
   }

   public boolean contains(Comparable var1) {
      return DefaultImpls.contains(this, var1);
   }

   public boolean equals(Object var1) {
      boolean var2;
      label27: {
         if (var1 instanceof ComparableRange) {
            if (this.isEmpty() && ((ComparableRange)var1).isEmpty()) {
               break label27;
            }

            Comparable var3 = this.getStart();
            ComparableRange var4 = (ComparableRange)var1;
            if (Intrinsics.areEqual((Object)var3, (Object)var4.getStart()) && Intrinsics.areEqual((Object)this.getEndInclusive(), (Object)var4.getEndInclusive())) {
               break label27;
            }
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public Comparable getEndInclusive() {
      return this.endInclusive;
   }

   public Comparable getStart() {
      return this.start;
   }

   public int hashCode() {
      int var1;
      if (this.isEmpty()) {
         var1 = -1;
      } else {
         var1 = this.getStart().hashCode() * 31 + this.getEndInclusive().hashCode();
      }

      return var1;
   }

   public boolean isEmpty() {
      return DefaultImpls.isEmpty(this);
   }

   public String toString() {
      return this.getStart() + ".." + this.getEndInclusive();
   }
}
