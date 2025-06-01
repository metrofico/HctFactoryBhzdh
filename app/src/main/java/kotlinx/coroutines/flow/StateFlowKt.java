package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u0002H\b¢\u0006\u0002\u0010\n\u001a6\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\f\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u001a\u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00110\u00072\u0006\u0010\u0016\u001a\u00020\u0011H\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003¨\u0006\u0017"},
   d2 = {"NONE", "Lkotlinx/coroutines/internal/Symbol;", "getNONE$annotations", "()V", "PENDING", "getPENDING$annotations", "MutableStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "T", "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;", "fuseStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/StateFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "increment", "", "delta", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class StateFlowKt {
   private static final Symbol NONE = new Symbol("NONE");
   private static final Symbol PENDING = new Symbol("PENDING");

   public static final MutableStateFlow MutableStateFlow(Object var0) {
      if (var0 == null) {
         var0 = NullSurrogateKt.NULL;
      }

      return (MutableStateFlow)(new StateFlowImpl(var0));
   }

   // $FF: synthetic method
   public static final Symbol access$getNONE$p() {
      return NONE;
   }

   // $FF: synthetic method
   public static final Symbol access$getPENDING$p() {
      return PENDING;
   }

   public static final Flow fuseStateFlow(StateFlow var0, CoroutineContext var1, int var2, BufferOverflow var3) {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var4;
         if (var2 != -1) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (!var4) {
            throw (Throwable)(new AssertionError());
         }
      }

      return (var2 >= 0 && 1 >= var2 || var2 == -2) && var3 == BufferOverflow.DROP_OLDEST ? (Flow)var0 : SharedFlowKt.fuseSharedFlow((SharedFlow)var0, var1, var2, var3);
   }

   // $FF: synthetic method
   private static void getNONE$annotations() {
   }

   // $FF: synthetic method
   private static void getPENDING$annotations() {
   }

   public static final void increment(MutableStateFlow var0, int var1) {
      int var2;
      do {
         var2 = ((Number)var0.getValue()).intValue();
      } while(!var0.compareAndSet(var2, var2 + var1));

   }
}
