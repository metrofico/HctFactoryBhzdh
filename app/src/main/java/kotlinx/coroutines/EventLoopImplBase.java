package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b \u0018\u00002\u0002092\u00020::\u00044567B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0002J\u0017\u0010\u0007\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\t2\n\u0010\u000b\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000f\u001a\u00020\u00032\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u00112\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0017\u0010\u0002J\u000f\u0010\u0018\u001a\u00020\u0003H\u0004¢\u0006\u0004\b\u0018\u0010\u0002J\u001d\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001f\u0010 J#\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\u00142\n\u0010\u000b\u001a\u00060\u0005j\u0002`\u0006H\u0004¢\u0006\u0004\b#\u0010$J%\u0010'\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00142\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030%H\u0016¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u001aH\u0002¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u0003H\u0014¢\u0006\u0004\b+\u0010\u0002R$\u0010-\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00118B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u00118T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b1\u0010.R\u0016\u00103\u001a\u00020\u00148T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u0016¨\u00068"},
   d2 = {"Lkotlinx/coroutines/EventLoopImplBase;", "<init>", "()V", "", "closeQueue", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dequeue", "()Ljava/lang/Runnable;", "Lkotlin/coroutines/CoroutineContext;", "context", "block", "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "task", "enqueue", "(Ljava/lang/Runnable;)V", "", "enqueueImpl", "(Ljava/lang/Runnable;)Z", "", "processNextEvent", "()J", "rescheduleAllDelayed", "resetAll", "now", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "delayedTask", "schedule", "(JLkotlinx/coroutines/EventLoopImplBase$DelayedTask;)V", "", "scheduleImpl", "(JLkotlinx/coroutines/EventLoopImplBase$DelayedTask;)I", "timeMillis", "Lkotlinx/coroutines/DisposableHandle;", "scheduleInvokeOnTimeout", "(JLjava/lang/Runnable;)Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "scheduleResumeAfterDelay", "(JLkotlinx/coroutines/CancellableContinuation;)V", "shouldUnpark", "(Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;)Z", "shutdown", "value", "isCompleted", "()Z", "setCompleted", "(Z)V", "isEmpty", "getNextTime", "nextTime", "DelayedResumeTask", "DelayedRunnableTask", "DelayedTask", "DelayedTaskQueue", "kotlinx-coroutines-core", "Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/Delay;"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
   private static final AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");
   private static final AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
   private volatile Object _delayed = null;
   private volatile int _isCompleted = 0;
   private volatile Object _queue = null;

   // $FF: synthetic method
   public static final boolean access$isCompleted$p(EventLoopImplBase var0) {
      return var0.isCompleted();
   }

   // $FF: synthetic method
   public static final void access$setCompleted$p(EventLoopImplBase var0, boolean var1) {
      var0.setCompleted(var1);
   }

   private final void closeQueue() {
      if (DebugKt.getASSERTIONS_ENABLED() && !this.isCompleted()) {
         throw (Throwable)(new AssertionError());
      } else {
         label34:
         do {
            LockFreeTaskQueueCore var1;
            Object var2;
            do {
               var2 = this._queue;
               if (var2 == null) {
                  continue label34;
               }

               if (var2 instanceof LockFreeTaskQueueCore) {
                  ((LockFreeTaskQueueCore)var2).close();
                  return;
               }

               if (var2 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
                  return;
               }

               var1 = new LockFreeTaskQueueCore(8, true);
               if (var2 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
               }

               var1.addLast((Runnable)var2);
            } while(!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, var2, var1));

            return;
         } while(!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, (Object)null, EventLoop_commonKt.access$getCLOSED_EMPTY$p()));

      }
   }

   private final Runnable dequeue() {
      while(true) {
         Object var2 = this._queue;
         if (var2 == null) {
            return null;
         }

         if (var2 instanceof LockFreeTaskQueueCore) {
            if (var2 == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.Queue<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> /* = kotlinx.coroutines.internal.LockFreeTaskQueueCore<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> */");
            }

            LockFreeTaskQueueCore var3 = (LockFreeTaskQueueCore)var2;
            Object var1 = var3.removeFirstOrNull();
            if (var1 != LockFreeTaskQueueCore.REMOVE_FROZEN) {
               return (Runnable)var1;
            }

            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, var2, var3.next());
         } else {
            if (var2 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
               return null;
            }

            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, var2, (Object)null)) {
               if (var2 != null) {
                  return (Runnable)var2;
               }

               throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
            }
         }
      }
   }

   private final boolean enqueueImpl(Runnable var1) {
      while(true) {
         Object var3 = this._queue;
         if (this.isCompleted()) {
            return false;
         }

         if (var3 == null) {
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, (Object)null, var1)) {
               return true;
            }
         } else {
            LockFreeTaskQueueCore var4;
            if (var3 instanceof LockFreeTaskQueueCore) {
               if (var3 != null) {
                  var4 = (LockFreeTaskQueueCore)var3;
                  int var2 = var4.addLast(var1);
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           continue;
                        }

                        return false;
                     }

                     AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, var3, var4.next());
                     continue;
                  }

                  return true;
               }

               throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.Queue<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> /* = kotlinx.coroutines.internal.LockFreeTaskQueueCore<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> */");
            } else {
               if (var3 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
                  return false;
               }

               var4 = new LockFreeTaskQueueCore(8, true);
               if (var3 != null) {
                  var4.addLast((Runnable)var3);
                  var4.addLast(var1);
                  if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, var3, var4)) {
                     continue;
                  }

                  return true;
               }

               throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
            }
         }
      }
   }

   private final boolean isCompleted() {
      return (boolean)this._isCompleted;
   }

   private final void rescheduleAllDelayed() {
      TimeSource var3 = TimeSourceKt.getTimeSource();
      long var1;
      if (var3 != null) {
         var1 = var3.nanoTime();
      } else {
         var1 = System.nanoTime();
      }

      while(true) {
         DelayedTaskQueue var4 = (DelayedTaskQueue)this._delayed;
         if (var4 == null) {
            break;
         }

         DelayedTask var5 = (DelayedTask)var4.removeFirstOrNull();
         if (var5 == null) {
            break;
         }

         this.reschedule(var1, var5);
      }

   }

   private final int scheduleImpl(long var1, DelayedTask var3) {
      if (this.isCompleted()) {
         return 1;
      } else {
         DelayedTaskQueue var4 = (DelayedTaskQueue)this._delayed;
         if (var4 == null) {
            EventLoopImplBase var5 = (EventLoopImplBase)this;
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_delayed$FU, this, (Object)null, new DelayedTaskQueue(var1));
            Object var6 = this._delayed;
            Intrinsics.checkNotNull(var6);
            var4 = (DelayedTaskQueue)var6;
         }

         return var3.scheduleTask(var1, var4, this);
      }
   }

   private final void setCompleted(boolean var1) {
      this._isCompleted = var1;
   }

   private final boolean shouldUnpark(DelayedTask var1) {
      DelayedTaskQueue var3 = (DelayedTaskQueue)this._delayed;
      DelayedTask var4;
      if (var3 != null) {
         var4 = (DelayedTask)var3.peek();
      } else {
         var4 = null;
      }

      boolean var2;
      if (var4 == var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public Object delay(long var1, Continuation var3) {
      return Delay.DefaultImpls.delay(this, var1, var3);
   }

   public final void dispatch(CoroutineContext var1, Runnable var2) {
      this.enqueue(var2);
   }

   public final void enqueue(Runnable var1) {
      if (this.enqueueImpl(var1)) {
         this.unpark();
      } else {
         DefaultExecutor.INSTANCE.enqueue(var1);
      }

   }

   protected long getNextTime() {
      if (super.getNextTime() == 0L) {
         return 0L;
      } else {
         Object var5 = this._queue;
         if (var5 != null) {
            if (!(var5 instanceof LockFreeTaskQueueCore)) {
               if (var5 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
                  return Long.MAX_VALUE;
               }

               return 0L;
            }

            if (!((LockFreeTaskQueueCore)var5).isEmpty()) {
               return 0L;
            }
         }

         DelayedTaskQueue var6 = (DelayedTaskQueue)this._delayed;
         if (var6 != null) {
            DelayedTask var7 = (DelayedTask)var6.peek();
            if (var7 != null) {
               long var3 = var7.nanoTime;
               TimeSource var8 = TimeSourceKt.getTimeSource();
               long var1;
               if (var8 != null) {
                  var1 = var8.nanoTime();
               } else {
                  var1 = System.nanoTime();
               }

               return RangesKt.coerceAtLeast(var3 - var1, 0L);
            }
         }

         return Long.MAX_VALUE;
      }
   }

   public DisposableHandle invokeOnTimeout(long var1, Runnable var3, CoroutineContext var4) {
      return Delay.DefaultImpls.invokeOnTimeout(this, var1, var3, var4);
   }

   protected boolean isEmpty() {
      boolean var2 = this.isUnconfinedQueueEmpty();
      boolean var1 = false;
      if (!var2) {
         return false;
      } else {
         DelayedTaskQueue var3 = (DelayedTaskQueue)this._delayed;
         if (var3 != null && !var3.isEmpty()) {
            return false;
         } else {
            Object var4 = this._queue;
            if (var4 != null) {
               if (var4 instanceof LockFreeTaskQueueCore) {
                  var1 = ((LockFreeTaskQueueCore)var4).isEmpty();
                  return var1;
               }

               if (var4 != EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
                  return var1;
               }
            }

            var1 = true;
            return var1;
         }
      }
   }

   public long processNextEvent() {
      if (this.processUnconfinedEvent()) {
         return 0L;
      } else {
         DelayedTaskQueue var6 = (DelayedTaskQueue)this._delayed;
         if (var6 != null && !var6.isEmpty()) {
            TimeSource var4 = TimeSourceKt.getTimeSource();
            long var2;
            if (var4 != null) {
               var2 = var4.nanoTime();
            } else {
               var2 = System.nanoTime();
            }

            ThreadSafeHeapNode var21;
            do {
               ThreadSafeHeap var7 = (ThreadSafeHeap)var6;
               synchronized(var7){}

               Throwable var10000;
               label282: {
                  ThreadSafeHeapNode var8;
                  boolean var10001;
                  try {
                     var8 = var7.firstImpl();
                  } catch (Throwable var20) {
                     var10000 = var20;
                     var10001 = false;
                     break label282;
                  }

                  DelayedTask var5 = null;
                  var21 = null;
                  if (var8 == null) {
                     var21 = var5;
                     continue;
                  }

                  boolean var1;
                  label265: {
                     try {
                        var5 = (DelayedTask)var8;
                        if (var5.timeToExecute(var2)) {
                           var1 = this.enqueueImpl((Runnable)var5);
                           break label265;
                        }
                     } catch (Throwable var19) {
                        var10000 = var19;
                        var10001 = false;
                        break label282;
                     }

                     var1 = false;
                  }

                  if (!var1) {
                     continue;
                  }

                  label257:
                  try {
                     var21 = var7.removeAtImpl(0);
                     continue;
                  } catch (Throwable var18) {
                     var10000 = var18;
                     var10001 = false;
                     break label257;
                  }
               }

               Throwable var22 = var10000;
               throw var22;
            } while((DelayedTask)var21 != null);
         }

         Runnable var23 = this.dequeue();
         if (var23 != null) {
            var23.run();
            return 0L;
         } else {
            return this.getNextTime();
         }
      }
   }

   protected final void resetAll() {
      this._queue = null;
      this._delayed = null;
   }

   public final void schedule(long var1, DelayedTask var3) {
      int var4 = this.scheduleImpl(var1, var3);
      if (var4 != 0) {
         if (var4 != 1) {
            if (var4 != 2) {
               throw (Throwable)(new IllegalStateException("unexpected result".toString()));
            }
         } else {
            this.reschedule(var1, var3);
         }
      } else if (this.shouldUnpark(var3)) {
         this.unpark();
      }

   }

   protected final DisposableHandle scheduleInvokeOnTimeout(long var1, Runnable var3) {
      long var4 = EventLoop_commonKt.delayToNanos(var1);
      DisposableHandle var8;
      if (var4 < 4611686018427387903L) {
         TimeSource var6 = TimeSourceKt.getTimeSource();
         if (var6 != null) {
            var1 = var6.nanoTime();
         } else {
            var1 = System.nanoTime();
         }

         DelayedRunnableTask var7 = new DelayedRunnableTask(var4 + var1, var3);
         this.schedule(var1, (DelayedTask)var7);
         var8 = (DisposableHandle)var7;
      } else {
         var8 = (DisposableHandle)NonDisposableHandle.INSTANCE;
      }

      return var8;
   }

   public void scheduleResumeAfterDelay(long var1, CancellableContinuation var3) {
      long var4 = EventLoop_commonKt.delayToNanos(var1);
      if (var4 < 4611686018427387903L) {
         TimeSource var6 = TimeSourceKt.getTimeSource();
         if (var6 != null) {
            var1 = var6.nanoTime();
         } else {
            var1 = System.nanoTime();
         }

         DelayedResumeTask var7 = new DelayedResumeTask(this, var4 + var1, var3);
         CancellableContinuationKt.disposeOnCancellation(var3, (DisposableHandle)var7);
         this.schedule(var1, (DelayedTask)var7);
      }

   }

   protected void shutdown() {
      ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
      this.setCompleted(true);
      this.closeQueue();

      while(this.processNextEvent() <= 0L) {
      }

      this.rescheduleAllDelayed();
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
      d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedResumeTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/EventLoopImplBase;JLkotlinx/coroutines/CancellableContinuation;)V", "run", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class DelayedResumeTask extends DelayedTask {
      private final CancellableContinuation cont;
      final EventLoopImplBase this$0;

      public DelayedResumeTask(EventLoopImplBase var1, long var2, CancellableContinuation var4) {
         super(var2);
         this.this$0 = var1;
         this.cont = var4;
      }

      public void run() {
         this.cont.resumeUndispatched(this.this$0, Unit.INSTANCE);
      }

      public String toString() {
         return super.toString() + this.cont.toString();
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"},
      d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedRunnableTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(JLjava/lang/Runnable;)V", "run", "", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class DelayedRunnableTask extends DelayedTask {
      private final Runnable block;

      public DelayedRunnableTask(long var1, Runnable var3) {
         super(var1);
         this.block = var3;
      }

      public void run() {
         this.block.run();
      }

      public String toString() {
         return super.toString() + this.block.toString();
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\u00020\u00042\u00020\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0000H\u0096\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u0007J\b\u0010$\u001a\u00020%H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006&"},
      d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "nanoTime", "", "(J)V", "_heap", "", "value", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "compareTo", "other", "dispose", "", "scheduleTask", "now", "delayed", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "eventLoop", "Lkotlinx/coroutines/EventLoopImplBase;", "timeToExecute", "", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public abstract static class DelayedTask implements Runnable, Comparable, DisposableHandle, ThreadSafeHeapNode {
      private Object _heap;
      private int index;
      public long nanoTime;

      public DelayedTask(long var1) {
         this.nanoTime = var1;
         this.index = -1;
      }

      public int compareTo(DelayedTask var1) {
         long var4;
         int var2 = (var4 = this.nanoTime - var1.nanoTime - 0L) == 0L ? 0 : (var4 < 0L ? -1 : 1);
         byte var3;
         if (var2 > 0) {
            var3 = 1;
         } else if (var2 < 0) {
            var3 = -1;
         } else {
            var3 = 0;
         }

         return var3;
      }

      public final void dispose() {
         synchronized(this){}

         Throwable var10000;
         label294: {
            Symbol var1;
            Object var2;
            boolean var10001;
            try {
               var2 = this._heap;
               var1 = EventLoop_commonKt.access$getDISPOSED_TASK$p();
            } catch (Throwable var32) {
               var10000 = var32;
               var10001 = false;
               break label294;
            }

            if (var2 == var1) {
               return;
            }

            Object var33 = var2;

            label283: {
               try {
                  if (var2 instanceof DelayedTaskQueue) {
                     break label283;
                  }
               } catch (Throwable var31) {
                  var10000 = var31;
                  var10001 = false;
                  break label294;
               }

               var33 = null;
            }

            DelayedTaskQueue var34;
            try {
               var34 = (DelayedTaskQueue)var33;
            } catch (Throwable var30) {
               var10000 = var30;
               var10001 = false;
               break label294;
            }

            if (var34 != null) {
               try {
                  var34.remove((ThreadSafeHeapNode)this);
               } catch (Throwable var29) {
                  var10000 = var29;
                  var10001 = false;
                  break label294;
               }
            }

            try {
               this._heap = EventLoop_commonKt.access$getDISPOSED_TASK$p();
            } catch (Throwable var28) {
               var10000 = var28;
               var10001 = false;
               break label294;
            }

            return;
         }

         Throwable var35 = var10000;
         throw var35;
      }

      public ThreadSafeHeap getHeap() {
         Object var2 = this._heap;
         Object var1 = var2;
         if (!(var2 instanceof ThreadSafeHeap)) {
            var1 = null;
         }

         return (ThreadSafeHeap)var1;
      }

      public int getIndex() {
         return this.index;
      }

      public final int scheduleTask(long param1, DelayedTaskQueue param3, EventLoopImplBase param4) {
         // $FF: Couldn't be decompiled
      }

      public void setHeap(ThreadSafeHeap var1) {
         boolean var2;
         if (this._heap != EventLoop_commonKt.access$getDISPOSED_TASK$p()) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            this._heap = var1;
         } else {
            throw (Throwable)(new IllegalArgumentException("Failed requirement.".toString()));
         }
      }

      public void setIndex(int var1) {
         this.index = var1;
      }

      public final boolean timeToExecute(long var1) {
         boolean var3;
         if (var1 - this.nanoTime >= 0L) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      public String toString() {
         return "Delayed[nanos=" + this.nanoTime + ']';
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"},
      d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "timeNow", "", "(J)V", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class DelayedTaskQueue extends ThreadSafeHeap {
      public long timeNow;

      public DelayedTaskQueue(long var1) {
         this.timeNow = var1;
      }
   }
}
