package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0007"},
   d2 = {"newFixedThreadPoolContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "nThreads", "", "name", "", "newSingleThreadContext", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ThreadPoolDispatcherKt {
   public static final ExecutorCoroutineDispatcher newFixedThreadPoolContext(int var0, String var1) {
      boolean var2 = true;
      if (var0 < 1) {
         var2 = false;
      }

      if (var2) {
         return (ExecutorCoroutineDispatcher)(new ThreadPoolDispatcher(var0, var1));
      } else {
         throw (Throwable)(new IllegalArgumentException(("Expected at least one thread, but " + var0 + " specified").toString()));
      }
   }

   public static final ExecutorCoroutineDispatcher newSingleThreadContext(String var0) {
      return newFixedThreadPoolContext(1, var0);
   }
}
