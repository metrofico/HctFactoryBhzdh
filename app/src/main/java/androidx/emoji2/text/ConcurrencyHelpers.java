package androidx.emoji2.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ConcurrencyHelpers {
   private static final int FONT_LOAD_TIMEOUT_SECONDS = 15;

   // $FF: synthetic method
   public static boolean $r8$lambda$xMC8M6LLEeW997bBmO6BCC6GGAM(Handler var0, Runnable var1) {
      return var0.post(var1);
   }

   private ConcurrencyHelpers() {
   }

   @Deprecated
   static Executor convertHandlerToExecutor(Handler var0) {
      Objects.requireNonNull(var0);
      return new ConcurrencyHelpers$$ExternalSyntheticLambda1(var0);
   }

   static ThreadPoolExecutor createBackgroundPriorityExecutor(String var0) {
      ConcurrencyHelpers$$ExternalSyntheticLambda0 var1 = new ConcurrencyHelpers$$ExternalSyntheticLambda0(var0);
      ThreadPoolExecutor var2 = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), var1);
      var2.allowCoreThreadTimeOut(true);
      return var2;
   }

   // $FF: synthetic method
   static Thread lambda$createBackgroundPriorityExecutor$0(String var0, Runnable var1) {
      Thread var2 = new Thread(var1, var0);
      var2.setPriority(10);
      return var2;
   }

   static Handler mainHandlerAsync() {
      return VERSION.SDK_INT >= 28 ? Handler.createAsync(Looper.getMainLooper()) : new Handler(Looper.getMainLooper());
   }

   static class Handler28Impl {
      private Handler28Impl() {
      }

      public static Handler createAsync(Looper var0) {
         return Handler.createAsync(var0);
      }
   }
}
