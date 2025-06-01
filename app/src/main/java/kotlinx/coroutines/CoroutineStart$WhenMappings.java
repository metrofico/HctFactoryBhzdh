package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   k = 3,
   mv = {1, 4, 0}
)
public final class CoroutineStart$WhenMappings {
   public static final int[] $EnumSwitchMapping$0;
   public static final int[] $EnumSwitchMapping$1;

   static {
      int[] var0 = new int[CoroutineStart.values().length];
      $EnumSwitchMapping$0 = var0;
      var0[CoroutineStart.DEFAULT.ordinal()] = 1;
      var0[CoroutineStart.ATOMIC.ordinal()] = 2;
      var0[CoroutineStart.UNDISPATCHED.ordinal()] = 3;
      var0[CoroutineStart.LAZY.ordinal()] = 4;
      var0 = new int[CoroutineStart.values().length];
      $EnumSwitchMapping$1 = var0;
      var0[CoroutineStart.DEFAULT.ordinal()] = 1;
      var0[CoroutineStart.ATOMIC.ordinal()] = 2;
      var0[CoroutineStart.UNDISPATCHED.ordinal()] = 3;
      var0[CoroutineStart.LAZY.ordinal()] = 4;
   }
}
