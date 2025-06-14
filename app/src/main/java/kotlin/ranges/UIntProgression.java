package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\"\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\u0012\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0086\u0002ø\u0001\u0000J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0013\u0010\b\u001a\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0013\u0010\n\u001a\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0018"},
   d2 = {"Lkotlin/ranges/UIntProgression;", "", "Lkotlin/UInt;", "start", "endInclusive", "step", "", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "I", "last", "getStep", "()I", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "", "toString", "", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class UIntProgression implements Iterable, KMappedMarker {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final int first;
   private final int last;
   private final int step;

   private UIntProgression(int var1, int var2, int var3) {
      if (var3 != 0) {
         if (var3 != Integer.MIN_VALUE) {
            this.first = var1;
            this.last = UProgressionUtilKt.getProgressionLastElement_Nkh28Cs(var1, var2, var3);
            this.step = var3;
         } else {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
         }
      } else {
         throw new IllegalArgumentException("Step must be non-zero.");
      }
   }

   // $FF: synthetic method
   public UIntProgression(int var1, int var2, int var3, DefaultConstructorMarker var4) {
      this(var1, var2, var3);
   }

   public boolean equals(Object var1) {
      boolean var3;
      label29: {
         if (var1 instanceof UIntProgression) {
            if (this.isEmpty() && ((UIntProgression)var1).isEmpty()) {
               break label29;
            }

            int var2 = this.first;
            UIntProgression var4 = (UIntProgression)var1;
            if (var2 == var4.first && this.last == var4.last && this.step == var4.step) {
               break label29;
            }
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public final int getFirst_pVg5ArA() {
      return this.first;
   }

   public final int getLast_pVg5ArA() {
      return this.last;
   }

   public final int getStep() {
      return this.step;
   }

   public int hashCode() {
      int var1;
      if (this.isEmpty()) {
         var1 = -1;
      } else {
         var1 = (this.first * 31 + this.last) * 31 + this.step;
      }

      return var1;
   }

   public boolean isEmpty() {
      int var1 = this.step;
      boolean var2 = true;
      if (var1 > 0) {
         if (UnsignedKt.uintCompare(this.first, this.last) > 0) {
            return var2;
         }
      } else if (UnsignedKt.uintCompare(this.first, this.last) < 0) {
         return var2;
      }

      var2 = false;
      return var2;
   }

   public final Iterator iterator() {
      return (Iterator)(new UIntProgressionIterator(this.first, this.last, this.step, (DefaultConstructorMarker)null));
   }

   public String toString() {
      int var1;
      StringBuilder var2;
      if (this.step > 0) {
         var2 = (new StringBuilder()).append(UInt.toString_impl(this.first)).append("..").append(UInt.toString_impl(this.last)).append(" step ");
         var1 = this.step;
      } else {
         var2 = (new StringBuilder()).append(UInt.toString_impl(this.first)).append(" downTo ").append(UInt.toString_impl(this.last)).append(" step ");
         var1 = -this.step;
      }

      return var2.append(var1).toString();
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"},
      d2 = {"Lkotlin/ranges/UIntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/UIntProgression;", "rangeStart", "Lkotlin/UInt;", "rangeEnd", "step", "", "fromClosedRange-Nkh28Cs", "(III)Lkotlin/ranges/UIntProgression;", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      public final UIntProgression fromClosedRange_Nkh28Cs(int var1, int var2, int var3) {
         return new UIntProgression(var1, var2, var3, (DefaultConstructorMarker)null);
      }
   }
}
