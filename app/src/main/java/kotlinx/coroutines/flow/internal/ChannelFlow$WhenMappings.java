package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlinx.coroutines.channels.BufferOverflow;

@Metadata(
   bv = {1, 0, 3},
   k = 3,
   mv = {1, 4, 0}
)
public final class ChannelFlow$WhenMappings {
   public static final int[] $EnumSwitchMapping$0;

   static {
      int[] var0 = new int[BufferOverflow.values().length];
      $EnumSwitchMapping$0 = var0;
      var0[BufferOverflow.SUSPEND.ordinal()] = 1;
      var0[BufferOverflow.DROP_OLDEST.ordinal()] = 2;
      var0[BufferOverflow.DROP_LATEST.ordinal()] = 3;
   }
}
