package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0012\u0004\u0012\u00028\u00000\u001bB!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\u00020\tH\u0000¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\tH\u0000¢\u0006\u0004\b\u0010\u0010\u000bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0017\u001a\u00020\u00058&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0019\u001a\u00020\t8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000b¨\u0006\u001a"},
   d2 = {"Lkotlinx/coroutines/internal/Segment;", "S", "", "id", "prev", "", "pointers", "<init>", "(JLkotlinx/coroutines/internal/Segment;I)V", "", "decPointers$kotlinx_coroutines_core", "()Z", "decPointers", "", "onSlotCleaned", "()V", "tryIncPointers$kotlinx_coroutines_core", "tryIncPointers", "J", "getId", "()J", "getMaxSlots", "()I", "maxSlots", "getRemoved", "removed", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class Segment extends ConcurrentLinkedListNode {
   private static final AtomicIntegerFieldUpdater cleanedAndPointers$FU = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers");
   private volatile int cleanedAndPointers;
   private final long id;

   public Segment(long var1, Segment var3, int var4) {
      super((ConcurrentLinkedListNode)var3);
      this.id = var1;
      this.cleanedAndPointers = var4 << 16;
   }

   public final boolean decPointers$kotlinx_coroutines_core() {
      boolean var1;
      if (cleanedAndPointers$FU.addAndGet(this, -65536) == this.getMaxSlots() && !this.isTail()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final long getId() {
      return this.id;
   }

   public abstract int getMaxSlots();

   public boolean getRemoved() {
      boolean var1;
      if (this.cleanedAndPointers == this.getMaxSlots() && !this.isTail()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final void onSlotCleaned() {
      if (cleanedAndPointers$FU.incrementAndGet(this) == this.getMaxSlots() && !this.isTail()) {
         this.remove();
      }

   }

   public final boolean tryIncPointers$kotlinx_coroutines_core() {
      while(true) {
         int var2 = this.cleanedAndPointers;
         int var1 = this.getMaxSlots();
         boolean var3 = false;
         boolean var4;
         if (var2 == var1 && !this.isTail()) {
            var4 = false;
         } else {
            var4 = true;
         }

         if (var4) {
            if (!cleanedAndPointers$FU.compareAndSet(this, var2, 65536 + var2)) {
               continue;
            }

            var3 = true;
         }

         return var3;
      }
   }
}
