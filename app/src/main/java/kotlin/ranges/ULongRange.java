package kotlin.ranges;

import kotlin.Metadata;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\t\u0010\bø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0018"},
   d2 = {"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "start", "endInclusive", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive-s-VKNKU", "()J", "getStart-s-VKNKU", "contains", "", "value", "contains-VKZWuLQ", "(J)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ULongRange extends ULongProgression implements ClosedRange {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final ULongRange EMPTY = new ULongRange(-1L, 0L, (DefaultConstructorMarker)null);

   private ULongRange(long var1, long var3) {
      super(var1, var3, 1L, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public ULongRange(long var1, long var3, DefaultConstructorMarker var5) {
      this(var1, var3);
   }

   public boolean contains_VKZWuLQ(long var1) {
      boolean var3;
      if (UnsignedKt.ulongCompare(this.getFirst_s_VKNKU(), var1) <= 0 && UnsignedKt.ulongCompare(var1, this.getLast_s_VKNKU()) <= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public boolean equals(Object var1) {
      boolean var4;
      label27: {
         if (var1 instanceof ULongRange) {
            if (this.isEmpty() && ((ULongRange)var1).isEmpty()) {
               break label27;
            }

            long var2 = this.getFirst_s_VKNKU();
            ULongRange var5 = (ULongRange)var1;
            if (var2 == var5.getFirst_s_VKNKU() && this.getLast_s_VKNKU() == var5.getLast_s_VKNKU()) {
               break label27;
            }
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   public long getEndInclusive_s_VKNKU() {
      return this.getLast_s_VKNKU();
   }

   public long getStart_s_VKNKU() {
      return this.getFirst_s_VKNKU();
   }

   public int hashCode() {
      int var1;
      if (this.isEmpty()) {
         var1 = -1;
      } else {
         var1 = (int)ULong.constructor_impl(this.getFirst_s_VKNKU() ^ ULong.constructor_impl(this.getFirst_s_VKNKU() >>> 32)) * 31 + (int)ULong.constructor_impl(this.getLast_s_VKNKU() ^ ULong.constructor_impl(this.getLast_s_VKNKU() >>> 32));
      }

      return var1;
   }

   public boolean isEmpty() {
      boolean var1;
      if (UnsignedKt.ulongCompare(this.getFirst_s_VKNKU(), this.getLast_s_VKNKU()) > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public String toString() {
      return ULong.toString_impl(this.getFirst_s_VKNKU()) + ".." + ULong.toString_impl(this.getLast_s_VKNKU());
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
      d2 = {"Lkotlin/ranges/ULongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "kotlin-stdlib"},
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

      public final ULongRange getEMPTY() {
         return ULongRange.EMPTY;
      }
   }
}
