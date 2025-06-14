package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.CharIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\t\u0010\u0015\u001a\u00020\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0011\u0010\b\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"},
   d2 = {"Lkotlin/ranges/CharProgression;", "", "", "start", "endInclusive", "step", "", "(CCI)V", "first", "getFirst", "()C", "last", "getLast", "getStep", "()I", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "Lkotlin/collections/CharIterator;", "toString", "", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class CharProgression implements Iterable, KMappedMarker {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final char first;
   private final char last;
   private final int step;

   public CharProgression(char var1, char var2, int var3) {
      if (var3 != 0) {
         if (var3 != Integer.MIN_VALUE) {
            this.first = var1;
            this.last = (char)ProgressionUtilKt.getProgressionLastElement(var1, var2, var3);
            this.step = var3;
         } else {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
         }
      } else {
         throw new IllegalArgumentException("Step must be non-zero.");
      }
   }

   public boolean equals(Object var1) {
      boolean var3;
      label29: {
         if (var1 instanceof CharProgression) {
            if (this.isEmpty() && ((CharProgression)var1).isEmpty()) {
               break label29;
            }

            char var2 = this.first;
            CharProgression var4 = (CharProgression)var1;
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

   public final char getFirst() {
      return this.first;
   }

   public final char getLast() {
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
         if (Intrinsics.compare(this.first, this.last) > 0) {
            return var2;
         }
      } else if (Intrinsics.compare(this.first, this.last) < 0) {
         return var2;
      }

      var2 = false;
      return var2;
   }

   public CharIterator iterator() {
      return (CharIterator)(new CharProgressionIterator(this.first, this.last, this.step));
   }

   public String toString() {
      int var1;
      StringBuilder var2;
      if (this.step > 0) {
         var2 = (new StringBuilder()).append(this.first).append("..").append(this.last).append(" step ");
         var1 = this.step;
      } else {
         var2 = (new StringBuilder()).append(this.first).append(" downTo ").append(this.last).append(" step ");
         var1 = -this.step;
      }

      return var2.append(var1).toString();
   }

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"},
      d2 = {"Lkotlin/ranges/CharProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/CharProgression;", "rangeStart", "", "rangeEnd", "step", "", "kotlin-stdlib"},
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

      public final CharProgression fromClosedRange(char var1, char var2, int var3) {
         return new CharProgression(var1, var2, var3);
      }
   }
}
