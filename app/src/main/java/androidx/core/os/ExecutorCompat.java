package androidx.core.os;

import android.os.Handler;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class ExecutorCompat {
   private ExecutorCompat() {
   }

   public static Executor create(Handler var0) {
      return new HandlerExecutor(var0);
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
}
