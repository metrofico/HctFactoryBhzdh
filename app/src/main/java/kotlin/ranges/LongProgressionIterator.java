package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata(
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\b\u001a\u00020\tH\u0096\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"},
   d2 = {"Lkotlin/ranges/LongProgressionIterator;", "Lkotlin/collections/LongIterator;", "first", "", "last", "step", "(JJJ)V", "finalElement", "hasNext", "", "next", "getStep", "()J", "nextLong", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class LongProgressionIterator extends LongIterator {
   private final long finalElement;
   private boolean hasNext;
   private long next;
   private final long step;

   public LongProgressionIterator(long var1, long var3, long var5) {
      boolean var7;
      label20: {
         super();
         this.step = var5;
         this.finalElement = var3;
         var7 = true;
         if (var5 > 0L) {
            if (var1 <= var3) {
               break label20;
            }
         } else if (var1 >= var3) {
            break label20;
         }

         var7 = false;
      }

      this.hasNext = var7;
      if (!var7) {
         var1 = var3;
      }

      this.next = var1;
   }

   public final long getStep() {
      return this.step;
   }

   public boolean hasNext() {
      return this.hasNext;
   }

   public long nextLong() {
      long var1 = this.next;
      if (var1 == this.finalElement) {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         }

         this.hasNext = false;
      } else {
         this.next = this.step + var1;
      }

      return var1;
   }
}
