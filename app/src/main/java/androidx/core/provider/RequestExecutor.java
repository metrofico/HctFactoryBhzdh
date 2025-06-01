package androidx.core.provider;

import android.os.Handler;
import android.os.Process;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class RequestExecutor {
   private RequestExecutor() {
   }

   static ThreadPoolExecutor createDefaultExecutor(String var0, int var1, int var2) {
      DefaultThreadFactory var3 = new DefaultThreadFactory(var0, var1);
      ThreadPoolExecutor var4 = new ThreadPoolExecutor(0, 1, (long)var2, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), var3);
      var4.allowCoreThreadTimeOut(true);
      return var4;
   }

   static Executor createHandlerExecutor(Handler var0) {
      return new HandlerExecutor(var0);
   }

   static void execute(Executor var0, Callable var1, Consumer var2) {
      var0.execute(new ReplyRunnable(CalleeHandler.create(), var1, var2));
   }

   static Object submit(ExecutorService var0, Callable var1, int var2) throws InterruptedException {
      Future var8 = var0.submit(var1);
      long var3 = (long)var2;

      try {
         Object var9 = var8.get(var3, TimeUnit.MILLISECONDS);
         return var9;
      } catch (ExecutionException var5) {
         throw new RuntimeException(var5);
      } catch (InterruptedException var6) {
         throw var6;
      } catch (TimeoutException var7) {
         throw new InterruptedException("timeout");
      }
   }

   private static class DefaultThreadFactory implements ThreadFactory {
      private int mPriority;
      private String mThreadName;

      DefaultThreadFactory(String var1, int var2) {
         this.mThreadName = var1;
         this.mPriority = var2;
      }

      public Thread newThread(Runnable var1) {
         return new ProcessPriorityThread(var1, this.mThreadName, this.mPriority);
      }

      private static class ProcessPriorityThread extends Thread {
         private final int mPriority;

         ProcessPriorityThread(Runnable var1, String var2, int var3) {
            super(var1, var2);
            this.mPriority = var3;
         }

         public void run() {
            Process.setThreadPriority(this.mPriority);
            super.run();
         }
      }
   }

   private static class HandlerExecutor implements Executor {
      private final Handler mHandler;

      HandlerExecutor(Handler var1) {
         this.mHandler = (Handler)Preconditions.checkNotNull(var1);
      }

      public void execute(Runnable var1) {
         if (!this.mHandler.post((Runnable)Preconditions.checkNotNull(var1))) {
            throw new RejectedExecutionException(this.mHandler + " is shutting down");
         }
      }
   }

   private static class ReplyRunnable implements Runnable {
      private Callable mCallable;
      private Consumer mConsumer;
      private Handler mHandler;

      ReplyRunnable(Handler var1, Callable var2, Consumer var3) {
         this.mCallable = var2;
         this.mConsumer = var3;
         this.mHandler = var1;
      }

      public void run() {
         Object var1;
         try {
            var1 = this.mCallable.call();
         } catch (Exception var3) {
            var1 = null;
         }

         Consumer var2 = this.mConsumer;
         this.mHandler.post(new Runnable(this, var2, var1) {
            final ReplyRunnable this$0;
            final Consumer val$consumer;
            final Object val$result;

            {
               this.this$0 = var1;
               this.val$consumer = var2;
               this.val$result = var3;
            }

            public void run() {
               this.val$consumer.accept(this.val$result);
            }
         });
      }
   }
}
