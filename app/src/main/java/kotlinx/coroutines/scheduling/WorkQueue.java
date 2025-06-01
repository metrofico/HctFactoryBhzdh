package kotlinx.coroutines.scheduling;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.debug.internal.ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\b\u0000\u0018\u00002\u00020*B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J!\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0004\b\u0012\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u001a\u0010\u0019J\u001f\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001e\u001a\u00020\r*\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u001e\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030 8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010&\u001a\u00020#8@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0016\u0010(\u001a\u00020#8@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b'\u0010%¨\u0006)"},
   d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", "<init>", "()V", "Lkotlinx/coroutines/scheduling/Task;", "task", "", "fair", "add", "(Lkotlinx/coroutines/scheduling/Task;Z)Lkotlinx/coroutines/scheduling/Task;", "addLast", "(Lkotlinx/coroutines/scheduling/Task;)Lkotlinx/coroutines/scheduling/Task;", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalQueue", "", "offloadAllWorkTo", "(Lkotlinx/coroutines/scheduling/GlobalQueue;)V", "poll", "()Lkotlinx/coroutines/scheduling/Task;", "pollBuffer", "queue", "pollTo", "(Lkotlinx/coroutines/scheduling/GlobalQueue;)Z", "victim", "", "tryStealBlockingFrom", "(Lkotlinx/coroutines/scheduling/WorkQueue;)J", "tryStealFrom", "blockingOnly", "tryStealLastScheduled", "(Lkotlinx/coroutines/scheduling/WorkQueue;Z)J", "decrementIfBlocking", "(Lkotlinx/coroutines/scheduling/Task;)V", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "", "getBufferSize$kotlinx_coroutines_core", "()I", "bufferSize", "getSize$kotlinx_coroutines_core", "size", "kotlinx-coroutines-core", ""},
   k = 1,
   mv = {1, 4, 0}
)
public final class WorkQueue {
   private static final AtomicIntegerFieldUpdater blockingTasksInBuffer$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "blockingTasksInBuffer");
   private static final AtomicIntegerFieldUpdater consumerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");
   private static final AtomicReferenceFieldUpdater lastScheduledTask$FU = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");
   private static final AtomicIntegerFieldUpdater producerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");
   private volatile int blockingTasksInBuffer = 0;
   private final AtomicReferenceArray buffer = new AtomicReferenceArray(128);
   private volatile int consumerIndex = 0;
   private volatile Object lastScheduledTask = null;
   private volatile int producerIndex = 0;

   // $FF: synthetic method
   public static Task add$default(WorkQueue var0, Task var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return var0.add(var1, var2);
   }

   private final Task addLast(Task var1) {
      int var3 = var1.taskContext.getTaskMode();
      boolean var2 = true;
      if (var3 != 1) {
         var2 = false;
      }

      if (var2) {
         blockingTasksInBuffer$FU.incrementAndGet(this);
      }

      if (this.getBufferSize$kotlinx_coroutines_core() == 127) {
         return var1;
      } else {
         int var4 = this.producerIndex & 127;

         while(this.buffer.get(var4) != null) {
            Thread.yield();
         }

         this.buffer.lazySet(var4, var1);
         producerIndex$FU.incrementAndGet(this);
         return null;
      }
   }

   private final void decrementIfBlocking(Task var1) {
      if (var1 != null) {
         int var2 = var1.taskContext.getTaskMode();
         boolean var3 = false;
         boolean var5;
         if (var2 == 1) {
            var5 = true;
         } else {
            var5 = false;
         }

         if (var5) {
            int var4 = blockingTasksInBuffer$FU.decrementAndGet(this);
            if (DebugKt.getASSERTIONS_ENABLED()) {
               var5 = var3;
               if (var4 >= 0) {
                  var5 = true;
               }

               if (!var5) {
                  throw (Throwable)(new AssertionError());
               }
            }
         }
      }

   }

   private final Task pollBuffer() {
      while(true) {
         int var1 = this.consumerIndex;
         if (var1 - this.producerIndex == 0) {
            return null;
         }

         if (consumerIndex$FU.compareAndSet(this, var1, var1 + 1)) {
            Task var2 = (Task)this.buffer.getAndSet(var1 & 127, (Object)null);
            if (var2 != null) {
               this.decrementIfBlocking(var2);
               return var2;
            }
         }
      }
   }

   private final boolean pollTo(GlobalQueue var1) {
      Task var2 = this.pollBuffer();
      if (var2 != null) {
         var1.addLast(var2);
         return true;
      } else {
         return false;
      }
   }

   private final long tryStealLastScheduled(WorkQueue var1, boolean var2) {
      while(true) {
         Task var7 = (Task)var1.lastScheduledTask;
         if (var7 != null) {
            if (var2) {
               int var4 = var7.taskContext.getTaskMode();
               boolean var3 = true;
               if (var4 != 1) {
                  var3 = false;
               }

               if (!var3) {
                  return -2L;
               }
            }

            long var5 = TasksKt.schedulerTimeSource.nanoTime() - var7.submissionTime;
            if (var5 < TasksKt.WORK_STEALING_TIME_RESOLUTION_NS) {
               return TasksKt.WORK_STEALING_TIME_RESOLUTION_NS - var5;
            }

            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(lastScheduledTask$FU, var1, var7, (Object)null)) {
               continue;
            }

            add$default(this, var7, false, 2, (Object)null);
            return -1L;
         }

         return -2L;
      }
   }

   public final Task add(Task var1, boolean var2) {
      if (var2) {
         return this.addLast(var1);
      } else {
         var1 = (Task)lastScheduledTask$FU.getAndSet(this, var1);
         return var1 != null ? this.addLast(var1) : null;
      }
   }

   public final int getBufferSize$kotlinx_coroutines_core() {
      return this.producerIndex - this.consumerIndex;
   }

   public final int getSize$kotlinx_coroutines_core() {
      int var1;
      if (this.lastScheduledTask != null) {
         var1 = this.getBufferSize$kotlinx_coroutines_core() + 1;
      } else {
         var1 = this.getBufferSize$kotlinx_coroutines_core();
      }

      return var1;
   }

   public final void offloadAllWorkTo(GlobalQueue var1) {
      Task var2 = (Task)lastScheduledTask$FU.getAndSet(this, (Object)null);
      if (var2 != null) {
         var1.addLast(var2);
      }

      while(this.pollTo(var1)) {
      }

   }

   public final Task poll() {
      Task var1 = (Task)lastScheduledTask$FU.getAndSet(this, (Object)null);
      if (var1 == null) {
         var1 = this.pollBuffer();
      }

      return var1;
   }

   public final long tryStealBlockingFrom(WorkQueue var1) {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var2;
         if (this.getBufferSize$kotlinx_coroutines_core() == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            throw (Throwable)(new AssertionError());
         }
      }

      int var8 = var1.consumerIndex;
      int var4 = var1.producerIndex;

      for(AtomicReferenceArray var7 = var1.buffer; var8 != var4; ++var8) {
         int var5 = var8 & 127;
         if (var1.blockingTasksInBuffer == 0) {
            break;
         }

         Task var6 = (Task)var7.get(var5);
         if (var6 != null) {
            boolean var3;
            if (var6.taskContext.getTaskMode() == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (var3 && ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(var7, var5, var6, (Object)null)) {
               blockingTasksInBuffer$FU.decrementAndGet(var1);
               add$default(this, var6, false, 2, (Object)null);
               return -1L;
            }
         }
      }

      return this.tryStealLastScheduled(var1, true);
   }

   public final long tryStealFrom(WorkQueue var1) {
      boolean var4 = DebugKt.getASSERTIONS_ENABLED();
      boolean var3 = true;
      boolean var2;
      if (var4) {
         if (this.getBufferSize$kotlinx_coroutines_core() == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            throw (Throwable)(new AssertionError());
         }
      }

      Task var5 = var1.pollBuffer();
      if (var5 != null) {
         Task var6 = add$default(this, var5, false, 2, (Object)null);
         if (DebugKt.getASSERTIONS_ENABLED()) {
            if (var6 == null) {
               var2 = var3;
            } else {
               var2 = false;
            }

            if (!var2) {
               throw (Throwable)(new AssertionError());
            }
         }

         return -1L;
      } else {
         return this.tryStealLastScheduled(var1, false);
      }
   }
}
