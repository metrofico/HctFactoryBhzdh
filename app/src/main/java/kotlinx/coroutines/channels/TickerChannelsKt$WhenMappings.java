package kotlinx.coroutines.channels;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   k = 3,
   mv = {1, 4, 0}
)
public final class TickerChannelsKt$WhenMappings {
   public static final int[] $EnumSwitchMapping$0;

   static {
      int[] var0 = new int[TickerMode.values().length];
      $EnumSwitchMapping$0 = var0;
      var0[TickerMode.FIXED_PERIOD.ordinal()] = 1;
      var0[TickerMode.FIXED_DELAY.ordinal()] = 2;
   }
}
