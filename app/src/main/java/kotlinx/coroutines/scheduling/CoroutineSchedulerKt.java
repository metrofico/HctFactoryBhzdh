package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¨\u0006\u0005"},
   d2 = {"isSchedulerWorker", "", "thread", "Ljava/lang/Thread;", "mayNotBlock", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CoroutineSchedulerKt {
   public static final boolean isSchedulerWorker(Thread var0) {
      return var0 instanceof CoroutineScheduler.Worker;
   }

   public static final boolean mayNotBlock(Thread var0) {
      boolean var1;
      if (var0 instanceof CoroutineScheduler.Worker && ((CoroutineScheduler.Worker)var0).state == CoroutineScheduler.WorkerState.CPU_ACQUIRED) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }
}
