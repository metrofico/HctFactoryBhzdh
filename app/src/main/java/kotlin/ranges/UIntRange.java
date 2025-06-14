package kotlin.ranges;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\t\u0010\bø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0018"},
   d2 = {"Lkotlin/ranges/UIntRange;", "Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/UInt;", "start", "endInclusive", "(IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive-pVg5ArA", "()I", "getStart-pVg5ArA", "contains", "", "value", "contains-WZ4Q5Ns", "(I)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UIntRange extends UIntProgression implements ClosedRange {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final UIntRange EMPTY = new UIntRange(-1, 0, (DefaultConstructorMarker)null);

   private UIntRange(int var1, int var2) {
      super(var1, var2, 1, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public UIntRange(int var1, int var2, DefaultConstructorMarker var3) {
      this(var1, var2);
   }

   public boolean contains_WZ4Q5Ns(int var1) {
      boolean var2;
      if (UnsignedKt.uintCompare(this.getFirst_pVg5ArA(), var1) <= 0 && UnsignedKt.uintCompare(var1, this.getLast_pVg5ArA()) <= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public boolean equals(Object var1) {
      boolean var3;
      label27: {
         if (var1 instanceof UIntRange) {
            if (this.isEmpty() && ((UIntRange)var1).isEmpty()) {
               break label27;
            }

            int var2 = this.getFirst_pVg5ArA();
            UIntRange var4 = (UIntRange)var1;
            if (var2 == var4.getFirst_pVg5ArA() && this.getLast_pVg5ArA() == var4.getLast_pVg5ArA()) {
               break label27;
            }
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public int getEndInclusive_pVg5ArA() {
      return this.getLast_pVg5ArA();
   }

   public int getStart_pVg5ArA() {
      return this.getFirst_pVg5ArA();
   }

   public int hashCode() {
      int var1;
      if (this.isEmpty()) {
         var1 = -1;
      } else {
         var1 = this.getFirst_pVg5ArA() * 31 + this.getLast_pVg5ArA();
      }

      return var1;
   }

   public boolean isEmpty() {
      boolean var1;
      if (UnsignedKt.uintCompare(this.getFirst_pVg5ArA(), this.getLast_pVg5ArA()) > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public String toString() {
      return UInt.toString_impl(this.getFirst_pVg5ArA()) + ".." + UInt.toString_impl(this.getLast_pVg5ArA());
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
      d2 = {"Lkotlin/ranges/UIntRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/UIntRange;", "getEMPTY", "()Lkotlin/ranges/UIntRange;", "kotlin-stdlib"},
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

      public final UIntRange getEMPTY() {
         return UIntRange.EMPTY;
      }
   }
}
