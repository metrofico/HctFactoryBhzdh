package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"},
   d2 = {"Lkotlinx/coroutines/ThreadPoolDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcherBase;", "nThreads", "", "name", "", "(ILjava/lang/String;)V", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "threadNo", "Ljava/util/concurrent/atomic/AtomicInteger;", "close", "", "toString", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ThreadPoolDispatcher extends ExecutorCoroutineDispatcherBase {
   private final Executor executor;
   private final int nThreads;
   private final String name;
   private final AtomicInteger threadNo;

   public ThreadPoolDispatcher(int var1, String var2) {
      this.nThreads = var1;
      this.name = var2;
      this.threadNo = new AtomicInteger();
      this.executor = (Executor)Executors.newScheduledThreadPool(var1, (ThreadFactory)(new ThreadFactory(this) {
         final ThreadPoolDispatcher this$0;

         {
            this.this$0 = var1;
         }

         public final Thread newThread(Runnable var1) {
            ThreadPoolDispatcher var3 = this.this$0;
            String var2;
            if (var3.nThreads == 1) {
               var2 = this.this$0.name;
            } else {
               var2 = this.this$0.name + "-" + this.this$0.threadNo.incrementAndGet();
            }

            return (Thread)(new PoolThread(var3, var1, var2));
         }
      }));
      this.initFutureCancellation$kotlinx_coroutines_core();
   }

   public void close() {
      Executor var1 = this.getExecutor();
      if (var1 != null) {
         ((ExecutorService)var1).shutdown();
      } else {
         throw new NullPointerException("null cannot be cast to non-null type java.util.concurrent.ExecutorService");
      }
   }

   public Executor getExecutor() {
      return this.executor;
   }

   public String toString() {
      return "ThreadPoolDispatcher[" + this.nThreads + ", " + this.name + ']';
   }
}
