package kotlinx.coroutines.channels;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"},
   d2 = {"Lkotlinx/coroutines/channels/BufferOverflow;", "", "(Ljava/lang/String;I)V", "SUSPEND", "DROP_OLDEST", "DROP_LATEST", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public enum BufferOverflow {
   private static final BufferOverflow[] $VALUES;
   DROP_LATEST,
   DROP_OLDEST,
   SUSPEND;

   static {
      BufferOverflow var0 = new BufferOverflow("SUSPEND", 0);
      SUSPEND = var0;
      BufferOverflow var1 = new BufferOverflow("DROP_OLDEST", 1);
      DROP_OLDEST = var1;
      BufferOverflow var2 = new BufferOverflow("DROP_LATEST", 2);
      DROP_LATEST = var2;
      $VALUES = new BufferOverflow[]{var0, var1, var2};
   }
}
