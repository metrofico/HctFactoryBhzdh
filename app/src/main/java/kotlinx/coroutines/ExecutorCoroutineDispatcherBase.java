package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ConcurrentKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u0007H\u0016J\u001c\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010H\u0016J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\r\u0010\u0016\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0017J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\b\u001a\u00020\tH\u0016J*\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001e\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070 H\u0016J\b\u0010!\u001a\u00020\"H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"},
   d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcherBase;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "()V", "removesFutureOnCancellation", "", "cancelJobOnRejection", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "Ljava/util/concurrent/RejectedExecutionException;", "close", "dispatch", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "equals", "other", "", "hashCode", "", "initFutureCancellation", "initFutureCancellation$kotlinx_coroutines_core", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "scheduleBlock", "Ljava/util/concurrent/ScheduledFuture;", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class ExecutorCoroutineDispatcherBase extends ExecutorCoroutineDispatcher implements Delay {
   private boolean removesFutureOnCancellation;

   private final void cancelJobOnRejection(CoroutineContext var1, RejectedExecutionException var2) {
      JobKt.cancel(var1, ExceptionsKt.CancellationException("The task was rejected", (Throwable)var2));
   }

   private final ScheduledFuture scheduleBlock(Runnable var1, CoroutineContext var2, long var3) {
      Object var6 = null;

      ScheduledFuture var13;
      RejectedExecutionException var10000;
      label46: {
         Executor var7;
         boolean var10001;
         try {
            var7 = this.getExecutor();
         } catch (RejectedExecutionException var11) {
            var10000 = var11;
            var10001 = false;
            break label46;
         }

         Executor var5 = var7;

         label36: {
            try {
               if (var7 instanceof ScheduledExecutorService) {
                  break label36;
               }
            } catch (RejectedExecutionException var10) {
               var10000 = var10;
               var10001 = false;
               break label46;
            }

            var5 = null;
         }

         ScheduledExecutorService var14;
         try {
            var14 = (ScheduledExecutorService)var5;
         } catch (RejectedExecutionException var9) {
            var10000 = var9;
            var10001 = false;
            break label46;
         }

         var13 = (ScheduledFuture)var6;
         if (var14 == null) {
            return var13;
         }

         try {
            var13 = var14.schedule(var1, var3, TimeUnit.MILLISECONDS);
            return var13;
         } catch (RejectedExecutionException var8) {
            var10000 = var8;
            var10001 = false;
         }
      }

      RejectedExecutionException var12 = var10000;
      this.cancelJobOnRejection(var2, var12);
      var13 = (ScheduledFuture)var6;
      return var13;
   }

   public void close() {
      Executor var2 = this.getExecutor();
      Executor var1 = var2;
      if (!(var2 instanceof ExecutorService)) {
         var1 = null;
      }

      ExecutorService var3 = (ExecutorService)var1;
      if (var3 != null) {
         var3.shutdown();
      }

   }

   public Object delay(long var1, Continuation var3) {
      return Delay.DefaultImpls.delay(this, var1, var3);
   }

   public void dispatch(CoroutineContext var1, Runnable var2) {
      RejectedExecutionException var10000;
      TimeSource var3;
      label42: {
         boolean var10001;
         Executor var4;
         try {
            var4 = this.getExecutor();
            var3 = TimeSourceKt.getTimeSource();
         } catch (RejectedExecutionException var7) {
            var10000 = var7;
            var10001 = false;
            break label42;
         }

         Runnable var8;
         label34: {
            if (var3 != null) {
               try {
                  var8 = var3.wrapTask(var2);
               } catch (RejectedExecutionException var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label42;
               }

               if (var8 != null) {
                  break label34;
               }
            }

            var8 = var2;
         }

         try {
            var4.execute(var8);
            return;
         } catch (RejectedExecutionException var5) {
            var10000 = var5;
            var10001 = false;
         }
      }

      RejectedExecutionException var9 = var10000;
      var3 = TimeSourceKt.getTimeSource();
      if (var3 != null) {
         var3.unTrackTask();
      }

      this.cancelJobOnRejection(var1, var9);
      Dispatchers.getIO().dispatch(var1, var2);
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof ExecutorCoroutineDispatcherBase && ((ExecutorCoroutineDispatcherBase)var1).getExecutor() == this.getExecutor()) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public int hashCode() {
      return System.identityHashCode(this.getExecutor());
   }

   public final void initFutureCancellation$kotlinx_coroutines_core() {
      this.removesFutureOnCancellation = ConcurrentKt.removeFutureOnCancel(this.getExecutor());
   }

   public DisposableHandle invokeOnTimeout(long var1, Runnable var3, CoroutineContext var4) {
      ScheduledFuture var5;
      if (this.removesFutureOnCancellation) {
         var5 = this.scheduleBlock(var3, var4, var1);
      } else {
         var5 = null;
      }

      DisposableHandle var6;
      if (var5 != null) {
         var6 = (DisposableHandle)(new DisposableFutureHandle((Future)var5));
      } else {
         var6 = DefaultExecutor.INSTANCE.invokeOnTimeout(var1, var3, var4);
      }

      return var6;
   }

   public void scheduleResumeAfterDelay(long var1, CancellableContinuation var3) {
      ScheduledFuture var4;
      if (this.removesFutureOnCancellation) {
         var4 = this.scheduleBlock((Runnable)(new ResumeUndispatchedRunnable((CoroutineDispatcher)this, var3)), var3.getContext(), var1);
      } else {
         var4 = null;
      }

      if (var4 != null) {
         JobKt.cancelFutureOnCancellation(var3, (Future)var4);
      } else {
         DefaultExecutor.INSTANCE.scheduleResumeAfterDelay(var1, var3);
      }
   }

   public String toString() {
      return this.getExecutor().toString();
   }
}
