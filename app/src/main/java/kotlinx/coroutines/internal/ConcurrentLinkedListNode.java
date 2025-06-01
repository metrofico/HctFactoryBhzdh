package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\t\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00020\u001aB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ \u0010\u000e\u001a\u0004\u0018\u00018\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0086\b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0007J\u0015\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u00020\b8F@\u0006¢\u0006\u0006\u001a\u0004\b\u0014\u0010\nR\u0018\u0010\u0017\u001a\u0004\u0018\u00018\u00008B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0019\u001a\u0004\u0018\u00018\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001a8B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\u0002\u001a\u0004\u0018\u00018\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0016R\u0016\u0010 \u001a\u00020\b8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\nR\u0016\u0010\"\u001a\u00028\u00008B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0016¨\u0006#"},
   d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", "prev", "<init>", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)V", "", "cleanPrev", "()V", "", "markAsClosed", "()Z", "Lkotlin/Function0;", "", "onClosedAction", "nextOrIfClosed", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "remove", "value", "trySetNext", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Z", "isTail", "getLeftmostAliveNode", "()Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "leftmostAliveNode", "getNext", "next", "", "getNextOrClosed", "()Ljava/lang/Object;", "nextOrClosed", "getPrev", "getRemoved", "removed", "getRightmostAliveNode", "rightmostAliveNode", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class ConcurrentLinkedListNode {
   private static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");
   private static final AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");
   private volatile Object _next = null;
   private volatile Object _prev;

   public ConcurrentLinkedListNode(ConcurrentLinkedListNode var1) {
      this._prev = var1;
   }

   // $FF: synthetic method
   public static final Object access$getNextOrClosed$p(ConcurrentLinkedListNode var0) {
      return var0.getNextOrClosed();
   }

   private final ConcurrentLinkedListNode getLeftmostAliveNode() {
      ConcurrentLinkedListNode var1;
      for(var1 = this.getPrev(); var1 != null && var1.getRemoved(); var1 = (ConcurrentLinkedListNode)var1._prev) {
      }

      return var1;
   }

   private final Object getNextOrClosed() {
      return this._next;
   }

   private final ConcurrentLinkedListNode getRightmostAliveNode() {
      if (DebugKt.getASSERTIONS_ENABLED() && !(this.isTail() ^ true)) {
         throw (Throwable)(new AssertionError());
      } else {
         ConcurrentLinkedListNode var1 = this.getNext();
         Intrinsics.checkNotNull(var1);

         while(var1.getRemoved()) {
            var1 = var1.getNext();
            Intrinsics.checkNotNull(var1);
         }

         return var1;
      }
   }

   public final void cleanPrev() {
      _prev$FU.lazySet(this, (Object)null);
   }

   public final ConcurrentLinkedListNode getNext() {
      Object var1 = access$getNextOrClosed$p(this);
      return var1 == ConcurrentLinkedListKt.access$getCLOSED$p() ? null : (ConcurrentLinkedListNode)var1;
   }

   public final ConcurrentLinkedListNode getPrev() {
      return (ConcurrentLinkedListNode)this._prev;
   }

   public abstract boolean getRemoved();

   public final boolean isTail() {
      boolean var1;
      if (this.getNext() == null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean markAsClosed() {
      return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, (Object)null, ConcurrentLinkedListKt.access$getCLOSED$p());
   }

   public final ConcurrentLinkedListNode nextOrIfClosed(Function0 var1) {
      Object var2 = access$getNextOrClosed$p(this);
      if (var2 != ConcurrentLinkedListKt.access$getCLOSED$p()) {
         return (ConcurrentLinkedListNode)var2;
      } else {
         var1.invoke();
         throw new KotlinNothingValueException();
      }
   }

   public final void remove() {
      if (DebugKt.getASSERTIONS_ENABLED() && !this.getRemoved()) {
         throw (Throwable)(new AssertionError());
      } else if (DebugKt.getASSERTIONS_ENABLED() && !(this.isTail() ^ true)) {
         throw (Throwable)(new AssertionError());
      } else {
         ConcurrentLinkedListNode var1;
         ConcurrentLinkedListNode var2;
         do {
            do {
               var1 = this.getLeftmostAliveNode();
               var2 = this.getRightmostAliveNode();
               var2._prev = var1;
               if (var1 != null) {
                  var1._next = var2;
               }
            } while(var2.getRemoved());
         } while(var1 != null && var1.getRemoved());

      }
   }

   public final boolean trySetNext(ConcurrentLinkedListNode var1) {
      return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, (Object)null, var1);
   }
}
