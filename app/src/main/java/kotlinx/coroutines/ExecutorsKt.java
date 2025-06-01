package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¢\u0006\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0004*\u00020\u0005H\u0007¢\u0006\u0002\b\u0003\u001a\n\u0010\u0006\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0007"},
   d2 = {"asCoroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "Ljava/util/concurrent/Executor;", "from", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Ljava/util/concurrent/ExecutorService;", "asExecutor", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ExecutorsKt {
   public static final Executor asExecutor(CoroutineDispatcher var0) {
      CoroutineDispatcher var1;
      if (!(var0 instanceof ExecutorCoroutineDispatcher)) {
         var1 = null;
      } else {
         var1 = var0;
      }

      ExecutorCoroutineDispatcher var3 = (ExecutorCoroutineDispatcher)var1;
      Executor var2;
      if (var3 != null) {
         Executor var4 = var3.getExecutor();
         if (var4 != null) {
            var2 = var4;
            return var2;
         }
      }

      var2 = (Executor)(new DispatcherExecutor(var0));
      return var2;
   }

   public static final CoroutineDispatcher from(Executor var0) {
      Executor var1;
      if (!(var0 instanceof DispatcherExecutor)) {
         var1 = null;
      } else {
         var1 = var0;
      }

      DispatcherExecutor var3 = (DispatcherExecutor)var1;
      CoroutineDispatcher var2;
      if (var3 != null) {
         CoroutineDispatcher var4 = var3.dispatcher;
         if (var4 != null) {
            var2 = var4;
            return var2;
         }
      }

      var2 = (CoroutineDispatcher)(new ExecutorCoroutineDispatcherImpl(var0));
      return var2;
   }

   public static final ExecutorCoroutineDispatcher from(ExecutorService var0) {
      return (ExecutorCoroutineDispatcher)(new ExecutorCoroutineDispatcherImpl((Executor)var0));
   }
}
