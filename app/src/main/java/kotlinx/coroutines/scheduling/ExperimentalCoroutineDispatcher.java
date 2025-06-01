package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DefaultExecutor;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0017\u0018\u00002\u00020\u0001B%\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001b\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\bB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0003J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\rH\u0002J\u001c\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u001cj\u0002`\u001dH\u0016J)\u0010\u001e\u001a\u00020\u00162\n\u0010\u001b\u001a\u00060\u001cj\u0002`\u001d2\u0006\u0010\u0019\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"J\u001c\u0010#\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u001cj\u0002`\u001dH\u0016J\u000e\u0010$\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003J\r\u0010%\u001a\u00020\u0016H\u0000¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\nH\u0000¢\u0006\u0002\b)J\b\u0010*\u001a\u00020\u0006H\u0016J\r\u0010+\u001a\u00020\u0016H\u0000¢\u0006\u0002\b,R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"},
   d2 = {"Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "corePoolSize", "", "maxPoolSize", "schedulerName", "", "(IILjava/lang/String;)V", "(II)V", "idleWorkerKeepAliveNs", "", "(IIJLjava/lang/String;)V", "coroutineScheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "blocking", "Lkotlinx/coroutines/CoroutineDispatcher;", "parallelism", "close", "", "createScheduler", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchWithContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "tailDispatch", "", "dispatchWithContext$kotlinx_coroutines_core", "dispatchYield", "limited", "restore", "restore$kotlinx_coroutines_core", "shutdown", "timeout", "shutdown$kotlinx_coroutines_core", "toString", "usePrivateScheduler", "usePrivateScheduler$kotlinx_coroutines_core", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class ExperimentalCoroutineDispatcher extends ExecutorCoroutineDispatcher {
   private final int corePoolSize;
   private CoroutineScheduler coroutineScheduler;
   private final long idleWorkerKeepAliveNs;
   private final int maxPoolSize;
   private final String schedulerName;

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Binary compatibility for Ktor 1.0-beta"
   )
   public ExperimentalCoroutineDispatcher(int var1, int var2) {
      this(var1, var2, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, (String)null, 8, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public ExperimentalCoroutineDispatcher(int var1, int var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 1) != 0) {
         var1 = TasksKt.CORE_POOL_SIZE;
      }

      if ((var3 & 2) != 0) {
         var2 = TasksKt.MAX_POOL_SIZE;
      }

      this(var1, var2);
   }

   public ExperimentalCoroutineDispatcher(int var1, int var2, long var3, String var5) {
      super();
      this.corePoolSize = var1;
      this.maxPoolSize = var2;
      this.idleWorkerKeepAliveNs = var3;
      this.schedulerName = var5;
      this.coroutineScheduler = this.createScheduler();
   }

   // $FF: synthetic method
   public ExperimentalCoroutineDispatcher(int var1, int var2, long var3, String var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 8) != 0) {
         var5 = "CoroutineScheduler";
      }

      this(var1, var2, var3, var5);
   }

   public ExperimentalCoroutineDispatcher(int var1, int var2, String var3) {
      this(var1, var2, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, var3);
   }

   // $FF: synthetic method
   public ExperimentalCoroutineDispatcher(int var1, int var2, String var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 1) != 0) {
         var1 = TasksKt.CORE_POOL_SIZE;
      }

      if ((var4 & 2) != 0) {
         var2 = TasksKt.MAX_POOL_SIZE;
      }

      if ((var4 & 4) != 0) {
         var3 = "DefaultDispatcher";
      }

      this(var1, var2, var3);
   }

   // $FF: synthetic method
   public static CoroutineDispatcher blocking$default(ExperimentalCoroutineDispatcher var0, int var1, int var2, Object var3) {
      if (var3 == null) {
         if ((var2 & 1) != 0) {
            var1 = TasksKt.BLOCKING_DEFAULT_PARALLELISM;
         }

         return var0.blocking(var1);
      } else {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: blocking");
      }
   }

   private final CoroutineScheduler createScheduler() {
      return new CoroutineScheduler(this.corePoolSize, this.maxPoolSize, this.idleWorkerKeepAliveNs, this.schedulerName);
   }

   public final CoroutineDispatcher blocking(int var1) {
      boolean var2;
      if (var1 > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2) {
         return (CoroutineDispatcher)(new LimitingDispatcher(this, var1, (String)null, 1));
      } else {
         throw (Throwable)(new IllegalArgumentException(("Expected positive parallelism level, but have " + var1).toString()));
      }
   }

   public void close() {
      this.coroutineScheduler.close();
   }

   public void dispatch(CoroutineContext var1, Runnable var2) {
      try {
         CoroutineScheduler.dispatch$default(this.coroutineScheduler, var2, (TaskContext)null, false, 6, (Object)null);
      } catch (RejectedExecutionException var4) {
         DefaultExecutor.INSTANCE.dispatch(var1, var2);
      }

   }

   public final void dispatchWithContext$kotlinx_coroutines_core(Runnable var1, TaskContext var2, boolean var3) {
      try {
         this.coroutineScheduler.dispatch(var1, var2, var3);
      } catch (RejectedExecutionException var5) {
         DefaultExecutor.INSTANCE.enqueue((Runnable)this.coroutineScheduler.createTask$kotlinx_coroutines_core(var1, var2));
      }

   }

   public void dispatchYield(CoroutineContext var1, Runnable var2) {
      try {
         CoroutineScheduler.dispatch$default(this.coroutineScheduler, var2, (TaskContext)null, true, 2, (Object)null);
      } catch (RejectedExecutionException var4) {
         DefaultExecutor.INSTANCE.dispatchYield(var1, var2);
      }

   }

   public Executor getExecutor() {
      return (Executor)this.coroutineScheduler;
   }

   public final CoroutineDispatcher limited(int var1) {
      boolean var3 = true;
      boolean var2;
      if (var1 > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2) {
         if (var1 <= this.corePoolSize) {
            var2 = var3;
         } else {
            var2 = false;
         }

         if (var2) {
            return (CoroutineDispatcher)(new LimitingDispatcher(this, var1, (String)null, 0));
         } else {
            throw (Throwable)(new IllegalArgumentException(("Expected parallelism level lesser than core pool size (" + this.corePoolSize + "), but have " + var1).toString()));
         }
      } else {
         throw (Throwable)(new IllegalArgumentException(("Expected positive parallelism level, but have " + var1).toString()));
      }
   }

   public final void restore$kotlinx_coroutines_core() {
      this.usePrivateScheduler$kotlinx_coroutines_core();
   }

   public final void shutdown$kotlinx_coroutines_core(long var1) {
      synchronized(this){}

      try {
         this.coroutineScheduler.shutdown(var1);
      } finally {
         ;
      }

   }

   public String toString() {
      return super.toString() + "[scheduler = " + this.coroutineScheduler + ']';
   }

   public final void usePrivateScheduler$kotlinx_coroutines_core() {
      synchronized(this){}

      try {
         this.coroutineScheduler.shutdown(1000L);
         this.coroutineScheduler = this.createScheduler();
      } finally {
         ;
      }

   }
}
