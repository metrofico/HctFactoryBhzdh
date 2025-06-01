package kotlinx.coroutines.scheduling;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00020+2\u00020,2\u00020\u001fB)\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\fJ#\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0013\u001a\u00020\n2\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u00112\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0013\u0010\u0017J#\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u0011H\u0016¢\u0006\u0004\b\u0018\u0010\u0014J\u001b\u0010\u001a\u001a\u00020\n2\n\u0010\u0019\u001a\u00060\u0010j\u0002`\u0011H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0002\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u001eR\u0016\u0010\"\u001a\u00020\u001f8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010#R\u0016\u0010\u0004\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010$R \u0010&\u001a\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110%8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u001c\u0010\u0007\u001a\u00020\u00038\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b(\u0010)¨\u0006*"},
   d2 = {"Lkotlinx/coroutines/scheduling/LimitingDispatcher;", "Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "dispatcher", "", "parallelism", "", "name", "taskMode", "<init>", "(Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;ILjava/lang/String;I)V", "", "afterTask", "()V", "close", "Lkotlin/coroutines/CoroutineContext;", "context", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "", "tailDispatch", "(Ljava/lang/Runnable;Z)V", "dispatchYield", "command", "execute", "(Ljava/lang/Runnable;)V", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "executor", "Ljava/lang/String;", "I", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "queue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "getTaskMode", "()I", "kotlinx-coroutines-core", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/scheduling/TaskContext;"},
   k = 1,
   mv = {1, 4, 0}
)
final class LimitingDispatcher extends ExecutorCoroutineDispatcher implements TaskContext, Executor {
   private static final AtomicIntegerFieldUpdater inFlightTasks$FU = AtomicIntegerFieldUpdater.newUpdater(LimitingDispatcher.class, "inFlightTasks");
   private final ExperimentalCoroutineDispatcher dispatcher;
   private volatile int inFlightTasks;
   private final String name;
   private final int parallelism;
   private final ConcurrentLinkedQueue queue;
   private final int taskMode;

   public LimitingDispatcher(ExperimentalCoroutineDispatcher var1, int var2, String var3, int var4) {
      this.dispatcher = var1;
      this.parallelism = var2;
      this.name = var3;
      this.taskMode = var4;
      this.queue = new ConcurrentLinkedQueue();
      this.inFlightTasks = 0;
   }

   private final void dispatch(Runnable var1, boolean var2) {
      do {
         AtomicIntegerFieldUpdater var3 = inFlightTasks$FU;
         if (var3.incrementAndGet(this) <= this.parallelism) {
            this.dispatcher.dispatchWithContext$kotlinx_coroutines_core(var1, (TaskContext)this, var2);
            return;
         }

         this.queue.add(var1);
         if (var3.decrementAndGet(this) >= this.parallelism) {
            return;
         }

         var1 = (Runnable)this.queue.poll();
      } while(var1 != null);

   }

   public void afterTask() {
      Runnable var1 = (Runnable)this.queue.poll();
      if (var1 != null) {
         this.dispatcher.dispatchWithContext$kotlinx_coroutines_core(var1, (TaskContext)this, true);
      } else {
         inFlightTasks$FU.decrementAndGet(this);
         var1 = (Runnable)this.queue.poll();
         if (var1 != null) {
            this.dispatch(var1, true);
         }

      }
   }

   public void close() {
      throw (Throwable)(new IllegalStateException("Close cannot be invoked on LimitingBlockingDispatcher".toString()));
   }

   public void dispatch(CoroutineContext var1, Runnable var2) {
      this.dispatch(var2, false);
   }

   public void dispatchYield(CoroutineContext var1, Runnable var2) {
      this.dispatch(var2, true);
   }

   public void execute(Runnable var1) {
      this.dispatch(var1, false);
   }

   public Executor getExecutor() {
      return (Executor)this;
   }

   public int getTaskMode() {
      return this.taskMode;
   }

   public String toString() {
      String var1 = this.name;
      if (var1 == null) {
         var1 = super.toString() + "[dispatcher = " + this.dispatcher + ']';
      }

      return var1;
   }
}
