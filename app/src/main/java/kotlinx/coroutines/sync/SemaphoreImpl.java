package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancelHandlerBase;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.debug.internal.ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00020\u001eB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\t\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0013\u0010\u0012J\u0019\u0010\u0014\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u00060\nH\u0002¢\u0006\u0004\b\u0014\u0010\u000eR\u0016\u0010\u0017\u001a\u00020\u00018V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\"\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00060\u00188\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0002\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"},
   d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "", "permits", "acquiredPermits", "<init>", "(II)V", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireSlowPath", "Lkotlinx/coroutines/CancellableContinuation;", "cont", "", "addAcquireToQueue", "(Lkotlinx/coroutines/CancellableContinuation;)Z", "release", "()V", "tryAcquire", "()Z", "tryResumeNextFromQueue", "tryResumeAcquire", "getAvailablePermits", "()I", "availablePermits", "Lkotlin/Function1;", "", "onCancellationRelease", "Lkotlin/jvm/functions/Function1;", "I", "kotlinx-coroutines-core", "Lkotlinx/coroutines/sync/Semaphore;"},
   k = 1,
   mv = {1, 4, 0}
)
final class SemaphoreImpl implements Semaphore {
   static final AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");
   private static final AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
   private static final AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
   private static final AtomicReferenceFieldUpdater head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "head");
   private static final AtomicReferenceFieldUpdater tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
   volatile int _availablePermits;
   private volatile long deqIdx;
   private volatile long enqIdx;
   private volatile Object head;
   private final Function1 onCancellationRelease;
   private final int permits;
   private volatile Object tail;

   public SemaphoreImpl(int var1, int var2) {
      this.permits = var1;
      this.deqIdx = 0L;
      this.enqIdx = 0L;
      boolean var4 = true;
      boolean var3;
      if (var1 > 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (!var3) {
         throw (Throwable)(new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + var1).toString()));
      } else {
         if (var2 >= 0 && var1 >= var2) {
            var3 = var4;
         } else {
            var3 = false;
         }

         if (var3) {
            SemaphoreSegment var5 = new SemaphoreSegment(0L, (SemaphoreSegment)null, 2);
            this.head = var5;
            this.tail = var5;
            this._availablePermits = var1 - var2;
            this.onCancellationRelease = (Function1)(new Function1(this) {
               final SemaphoreImpl this$0;

               {
                  this.this$0 = var1;
               }

               public final void invoke(Throwable var1) {
                  this.this$0.release();
               }
            });
         } else {
            throw (Throwable)(new IllegalArgumentException(("The number of acquired permits should be in 0.." + var1).toString()));
         }
      }
   }

   // $FF: synthetic method
   public static final boolean access$addAcquireToQueue(SemaphoreImpl var0, CancellableContinuation var1) {
      return var0.addAcquireToQueue(var1);
   }

   private final boolean addAcquireToQueue(CancellableContinuation var1) {
      SemaphoreSegment var12 = (SemaphoreSegment)this.tail;
      long var4 = enqIdx$FU.getAndIncrement(this);
      long var6 = var4 / (long)SemaphoreKt.access$getSEGMENT_SIZE$p();

      boolean var2;
      boolean var3;
      Object var9;
      do {
         Segment var10 = (Segment)var12;

         Segment var11;
         label69:
         while(true) {
            Segment var15;
            while(true) {
               if (var10.getId() >= var6 && !var10.getRemoved()) {
                  var9 = SegmentOrClosed.constructor_impl(var10);
                  break label69;
               }

               var9 = ConcurrentLinkedListNode.access$getNextOrClosed$p((ConcurrentLinkedListNode)var10);
               if (var9 == ConcurrentLinkedListKt.access$getCLOSED$p()) {
                  var9 = SegmentOrClosed.constructor_impl(ConcurrentLinkedListKt.access$getCLOSED$p());
                  break label69;
               }

               var15 = (Segment)((ConcurrentLinkedListNode)var9);
               if (var15 != null) {
                  break;
               }

               var11 = (Segment)SemaphoreKt.access$createSegment(var10.getId() + 1L, (SemaphoreSegment)var10);
               if (var10.trySetNext((ConcurrentLinkedListNode)var11)) {
                  var15 = var11;
                  if (var10.getRemoved()) {
                     var10.remove();
                     var15 = var11;
                  }
                  break;
               }
            }

            var10 = var15;
         }

         boolean var8 = SegmentOrClosed.isClosed_impl(var9);
         var3 = true;
         if (var8) {
            break;
         }

         var11 = SegmentOrClosed.getSegment_impl(var9);

         while(true) {
            var10 = (Segment)this.tail;
            if (var10.getId() < var11.getId()) {
               if (!var11.tryIncPointers$kotlinx_coroutines_core()) {
                  var2 = false;
                  break;
               }

               if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(tail$FU, this, var10, var11)) {
                  if (var11.decPointers$kotlinx_coroutines_core()) {
                     var11.remove();
                  }
                  continue;
               }

               if (var10.decPointers$kotlinx_coroutines_core()) {
                  var10.remove();
               }
            }

            var2 = true;
            break;
         }
      } while(!var2);

      SemaphoreSegment var18 = (SemaphoreSegment)SegmentOrClosed.getSegment_impl(var9);
      int var14 = (int)(var4 % (long)SemaphoreKt.access$getSEGMENT_SIZE$p());
      if (ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(var18.acquirers, var14, (Object)null, var1)) {
         var1.invokeOnCancellation((Function1)((CancelHandlerBase)(new CancelSemaphoreAcquisitionHandler(var18, var14))));
         return true;
      } else {
         Symbol var16 = SemaphoreKt.access$getPERMIT$p();
         Symbol var19 = SemaphoreKt.access$getTAKEN$p();
         if (ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(var18.acquirers, var14, var16, var19)) {
            Continuation var13 = (Continuation)var1;
            Unit var17 = Unit.INSTANCE;
            Result.Companion var20 = Result.Companion;
            var13.resumeWith(Result.constructor_impl(var17));
            return true;
         } else {
            if (DebugKt.getASSERTIONS_ENABLED()) {
               if (var18.acquirers.get(var14) == SemaphoreKt.access$getBROKEN$p()) {
                  var2 = var3;
               } else {
                  var2 = false;
               }

               if (!var2) {
                  throw (Throwable)(new AssertionError());
               }
            }

            return false;
         }
      }
   }

   private final boolean tryResumeAcquire(CancellableContinuation var1) {
      Object var2 = var1.tryResume(Unit.INSTANCE, (Object)null, this.onCancellationRelease);
      if (var2 != null) {
         var1.completeResume(var2);
         return true;
      } else {
         return false;
      }
   }

   private final boolean tryResumeNextFromQueue() {
      SemaphoreSegment var13 = (SemaphoreSegment)this.head;
      long var5 = deqIdx$FU.getAndIncrement(this);
      long var7 = var5 / (long)SemaphoreKt.access$getSEGMENT_SIZE$p();

      boolean var1;
      byte var2;
      Object var10;
      do {
         Segment var11 = (Segment)var13;

         Segment var12;
         label72:
         while(true) {
            Segment var15;
            while(true) {
               if (var11.getId() >= var7 && !var11.getRemoved()) {
                  var10 = SegmentOrClosed.constructor_impl(var11);
                  break label72;
               }

               var10 = ConcurrentLinkedListNode.access$getNextOrClosed$p((ConcurrentLinkedListNode)var11);
               if (var10 == ConcurrentLinkedListKt.access$getCLOSED$p()) {
                  var10 = SegmentOrClosed.constructor_impl(ConcurrentLinkedListKt.access$getCLOSED$p());
                  break label72;
               }

               var15 = (Segment)((ConcurrentLinkedListNode)var10);
               if (var15 != null) {
                  break;
               }

               var12 = (Segment)SemaphoreKt.access$createSegment(var11.getId() + 1L, (SemaphoreSegment)var11);
               if (var11.trySetNext((ConcurrentLinkedListNode)var12)) {
                  var15 = var12;
                  if (var11.getRemoved()) {
                     var11.remove();
                     var15 = var12;
                  }
                  break;
               }
            }

            var11 = var15;
         }

         boolean var9 = SegmentOrClosed.isClosed_impl(var10);
         var2 = 0;
         if (var9) {
            break;
         }

         var12 = SegmentOrClosed.getSegment_impl(var10);

         while(true) {
            var11 = (Segment)this.head;
            if (var11.getId() < var12.getId()) {
               if (!var12.tryIncPointers$kotlinx_coroutines_core()) {
                  var1 = false;
                  break;
               }

               if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(head$FU, this, var11, var12)) {
                  if (var12.decPointers$kotlinx_coroutines_core()) {
                     var12.remove();
                  }
                  continue;
               }

               if (var11.decPointers$kotlinx_coroutines_core()) {
                  var11.remove();
               }
            }

            var1 = true;
            break;
         }
      } while(!var1);

      SemaphoreSegment var18 = (SemaphoreSegment)SegmentOrClosed.getSegment_impl(var10);
      var18.cleanPrev();
      if (var18.getId() > var7) {
         return false;
      } else {
         int var4 = (int)(var5 % (long)SemaphoreKt.access$getSEGMENT_SIZE$p());
         Symbol var16 = SemaphoreKt.access$getPERMIT$p();
         Object var17 = var18.acquirers.getAndSet(var4, var16);
         if (var17 == null) {
            int var3 = SemaphoreKt.access$getMAX_SPIN_CYCLES$p();

            for(int var14 = var2; var14 < var3; ++var14) {
               if (var18.acquirers.get(var4) == SemaphoreKt.access$getTAKEN$p()) {
                  return true;
               }
            }

            var16 = SemaphoreKt.access$getPERMIT$p();
            Symbol var19 = SemaphoreKt.access$getBROKEN$p();
            return ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(var18.acquirers, var4, var16, var19) ^ true;
         } else {
            return var17 == SemaphoreKt.access$getCANCELLED$p() ? false : this.tryResumeAcquire((CancellableContinuation)var17);
         }
      }
   }

   public Object acquire(Continuation var1) {
      if (_availablePermits$FU.getAndDecrement(this) > 0) {
         return Unit.INSTANCE;
      } else {
         Object var2 = this.acquireSlowPath(var1);
         return var2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var2 : Unit.INSTANCE;
      }
   }

   // $FF: synthetic method
   final Object acquireSlowPath(Continuation var1) {
      CancellableContinuationImpl var2 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(var1));
      CancellableContinuation var3 = (CancellableContinuation)var2;

      while(!access$addAcquireToQueue(this, var3)) {
         if (_availablePermits$FU.getAndDecrement(this) > 0) {
            Continuation var7 = (Continuation)var3;
            Unit var5 = Unit.INSTANCE;
            Result.Companion var4 = Result.Companion;
            var7.resumeWith(Result.constructor_impl(var5));
            break;
         }
      }

      Object var6 = var2.getResult();
      if (var6 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var6;
   }

   public int getAvailablePermits() {
      return Math.max(this._availablePermits, 0);
   }

   public void release() {
      while(true) {
         int var2 = this._availablePermits;
         boolean var1;
         if (var2 < this.permits) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            if (!_availablePermits$FU.compareAndSet(this, var2, var2 + 1)) {
               continue;
            }

            if (var2 >= 0) {
               return;
            }

            if (!this.tryResumeNextFromQueue()) {
               continue;
            }

            return;
         }

         throw (Throwable)(new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString()));
      }
   }

   public boolean tryAcquire() {
      int var1;
      do {
         var1 = this._availablePermits;
         if (var1 <= 0) {
            return false;
         }
      } while(!_availablePermits$FU.compareAndSet(this, var1, var1 - 1));

      return true;
   }
}
