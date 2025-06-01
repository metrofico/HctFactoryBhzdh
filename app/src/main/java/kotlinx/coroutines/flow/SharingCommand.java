package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"},
   d2 = {"Lkotlinx/coroutines/flow/SharingCommand;", "", "(Ljava/lang/String;I)V", "START", "STOP", "STOP_AND_RESET_REPLAY_CACHE", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public enum SharingCommand {
   private static final SharingCommand[] $VALUES;
   START,
   STOP,
   STOP_AND_RESET_REPLAY_CACHE;

   static {
      SharingCommand var2 = new SharingCommand("START", 0);
      START = var2;
      SharingCommand var0 = new SharingCommand("STOP", 1);
      STOP = var0;
      SharingCommand var1 = new SharingCommand("STOP_AND_RESET_REPLAY_CACHE", 2);
      STOP_AND_RESET_REPLAY_CACHE = var1;
      $VALUES = new SharingCommand[]{var2, var0, var1};
   }
}
