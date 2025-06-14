package kotlinx.coroutines.channels;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"},
   d2 = {"Lkotlinx/coroutines/channels/TickerMode;", "", "(Ljava/lang/String;I)V", "FIXED_PERIOD", "FIXED_DELAY", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public enum TickerMode {
   private static final TickerMode[] $VALUES;
   FIXED_DELAY,
   FIXED_PERIOD;

   static {
      TickerMode var1 = new TickerMode("FIXED_PERIOD", 0);
      FIXED_PERIOD = var1;
      TickerMode var0 = new TickerMode("FIXED_DELAY", 1);
      FIXED_DELAY = var0;
      $VALUES = new TickerMode[]{var1, var0};
   }
}
