package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\t"},
   d2 = {"Lkotlinx/coroutines/flow/SharingStarted;", "", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "Companion", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface SharingStarted {
   Companion Companion = SharingStarted.Companion.$$INSTANCE;

   Flow command(StateFlow var1);

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\r"},
      d2 = {"Lkotlinx/coroutines/flow/SharingStarted$Companion;", "", "()V", "Eagerly", "Lkotlinx/coroutines/flow/SharingStarted;", "getEagerly", "()Lkotlinx/coroutines/flow/SharingStarted;", "Lazily", "getLazily", "WhileSubscribed", "stopTimeoutMillis", "", "replayExpirationMillis", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Companion {
      static final Companion $$INSTANCE = new Companion();
      private static final SharingStarted Eagerly = (SharingStarted)(new StartedEagerly());
      private static final SharingStarted Lazily = (SharingStarted)(new StartedLazily());

      private Companion() {
      }

      // $FF: synthetic method
      public static SharingStarted WhileSubscribed$default(Companion var0, long var1, long var3, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = 0L;
         }

         if ((var5 & 2) != 0) {
            var3 = Long.MAX_VALUE;
         }

         return var0.WhileSubscribed(var1, var3);
      }

      public final SharingStarted WhileSubscribed(long var1, long var3) {
         return (SharingStarted)(new StartedWhileSubscribed(var1, var3));
      }

      public final SharingStarted getEagerly() {
         return Eagerly;
      }

      public final SharingStarted getLazily() {
         return Lazily;
      }
   }
}
