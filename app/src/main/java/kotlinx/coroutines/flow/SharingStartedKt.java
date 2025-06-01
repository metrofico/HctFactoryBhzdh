package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.time.Duration;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"},
   d2 = {"WhileSubscribed", "Lkotlinx/coroutines/flow/SharingStarted;", "Lkotlinx/coroutines/flow/SharingStarted$Companion;", "stopTimeout", "Lkotlin/time/Duration;", "replayExpiration", "WhileSubscribed-9tZugJw", "(Lkotlinx/coroutines/flow/SharingStarted$Companion;DD)Lkotlinx/coroutines/flow/SharingStarted;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class SharingStartedKt {
   public static final SharingStarted WhileSubscribed_9tZugJw(SharingStarted.Companion var0, double var1, double var3) {
      return (SharingStarted)(new StartedWhileSubscribed(Duration.toLongMilliseconds_impl(var1), Duration.toLongMilliseconds_impl(var3)));
   }

   // $FF: synthetic method
   public static SharingStarted WhileSubscribed_9tZugJw$default(SharingStarted.Companion var0, double var1, double var3, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = Duration.Companion.getZERO_UwyO8pc();
      }

      if ((var5 & 2) != 0) {
         var3 = Duration.Companion.getINFINITE_UwyO8pc();
      }

      return WhileSubscribed_9tZugJw(var0, var1, var3);
   }
}
