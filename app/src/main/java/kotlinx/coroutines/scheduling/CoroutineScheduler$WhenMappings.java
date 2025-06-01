package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   k = 3,
   mv = {1, 4, 0}
)
public final class CoroutineScheduler$WhenMappings {
   public static final int[] $EnumSwitchMapping$0;

   static {
      int[] var0 = new int[CoroutineScheduler.WorkerState.values().length];
      $EnumSwitchMapping$0 = var0;
      var0[CoroutineScheduler.WorkerState.PARKING.ordinal()] = 1;
      var0[CoroutineScheduler.WorkerState.BLOCKING.ordinal()] = 2;
      var0[CoroutineScheduler.WorkerState.CPU_ACQUIRED.ordinal()] = 3;
      var0[CoroutineScheduler.WorkerState.DORMANT.ordinal()] = 4;
      var0[CoroutineScheduler.WorkerState.TERMINATED.ordinal()] = 5;
   }
}
