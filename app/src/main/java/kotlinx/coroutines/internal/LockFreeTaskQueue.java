package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0012\"\u0004\b\u0001\u0010\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u00020\u00038F@\u0006¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000eR\u0013\u0010\u001b\u001a\u00020\u00188F@\u0006¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"},
   d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueue;", "", "E", "", "singleConsumer", "<init>", "(Z)V", "element", "addLast", "(Ljava/lang/Object;)Z", "", "close", "()V", "isClosed", "()Z", "R", "Lkotlin/Function1;", "transform", "", "map", "(Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "removeFirstOrNull", "()Ljava/lang/Object;", "isEmpty", "", "getSize", "()I", "size", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class LockFreeTaskQueue {
   private static final AtomicReferenceFieldUpdater _cur$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueue.class, Object.class, "_cur");
   private volatile Object _cur;

   public LockFreeTaskQueue(boolean var1) {
      this._cur = new LockFreeTaskQueueCore(8, var1);
   }

   public final boolean addLast(Object var1) {
      while(true) {
         LockFreeTaskQueueCore var3 = (LockFreeTaskQueueCore)this._cur;
         int var2 = var3.addLast(var1);
         if (var2 == 0) {
            return true;
         }

         if (var2 != 1) {
            if (var2 == 2) {
               return false;
            }
         } else {
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_cur$FU, this, var3, var3.next());
         }
      }
   }

   public final void close() {
      while(true) {
         LockFreeTaskQueueCore var1 = (LockFreeTaskQueueCore)this._cur;
         if (var1.close()) {
            return;
         }

         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_cur$FU, this, var1, var1.next());
      }
   }

   public final int getSize() {
      return ((LockFreeTaskQueueCore)this._cur).getSize();
   }

   public final boolean isClosed() {
      return ((LockFreeTaskQueueCore)this._cur).isClosed();
   }

   public final boolean isEmpty() {
      return ((LockFreeTaskQueueCore)this._cur).isEmpty();
   }

   public final List map(Function1 var1) {
      return ((LockFreeTaskQueueCore)this._cur).map(var1);
   }

   public final Object removeFirstOrNull() {
      while(true) {
         LockFreeTaskQueueCore var1 = (LockFreeTaskQueueCore)this._cur;
         Object var2 = var1.removeFirstOrNull();
         if (var2 != LockFreeTaskQueueCore.REMOVE_FROZEN) {
            return var2;
         }

         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_cur$FU, this, var1, var1.next());
      }
   }
}
