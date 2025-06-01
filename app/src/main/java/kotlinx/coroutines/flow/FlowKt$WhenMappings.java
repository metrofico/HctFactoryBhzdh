package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   k = 3,
   mv = {1, 4, 0}
)
public final class FlowKt$WhenMappings {
   public static final int[] $EnumSwitchMapping$0;

   static {
      int[] var0 = new int[SharingCommand.values().length];
      $EnumSwitchMapping$0 = var0;
      var0[SharingCommand.START.ordinal()] = 1;
      var0[SharingCommand.STOP.ordinal()] = 2;
      var0[SharingCommand.STOP_AND_RESET_REPLAY_CACHE.ordinal()] = 3;
   }
}
