package kotlin.ranges;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B \u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\u0016\u0010\f\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\b\u001a\u00020\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u00020\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0005\u001a\u00020\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u000f"},
   d2 = {"Lkotlin/ranges/ULongProgressionIterator;", "", "Lkotlin/ULong;", "first", "last", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "J", "hasNext", "", "next", "next-s-VKNKU", "()J", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class ULongProgressionIterator implements Iterator, KMappedMarker {
   private final long finalElement;
   private boolean hasNext;
   private long next;
   private final long step;

   private ULongProgressionIterator(long var1, long var3, long var5) {
      boolean var7;
      label20: {
         super();
         this.finalElement = var3;
         var7 = true;
         if (var5 > 0L) {
            if (UnsignedKt.ulongCompare(var1, var3) <= 0) {
               break label20;
            }
         } else if (UnsignedKt.ulongCompare(var1, var3) >= 0) {
            break label20;
         }

         var7 = false;
      }

      this.hasNext = var7;
      this.step = ULong.constructor_impl(var5);
      if (!this.hasNext) {
         var1 = var3;
      }

      this.next = var1;
   }

   // $FF: synthetic method
   public ULongProgressionIterator(long var1, long var3, long var5, DefaultConstructorMarker var7) {
      this(var1, var3, var5);
   }

   public boolean hasNext() {
      return this.hasNext;
   }

   public long next_s_VKNKU() {
      long var1 = this.next;
      if (var1 == this.finalElement) {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         }

         this.hasNext = false;
      } else {
         this.next = ULong.constructor_impl(this.step + var1);
      }

      return var1;
   }

   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
