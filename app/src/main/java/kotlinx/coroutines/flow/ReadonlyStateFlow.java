package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0006J\u001f\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0097Aø\u0001\u0000¢\u0006\u0002\u0010\u0012J&\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00028\u0000X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"},
   d2 = {"Lkotlinx/coroutines/flow/ReadonlyStateFlow;", "T", "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "flow", "(Lkotlinx/coroutines/flow/StateFlow;)V", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "value", "getValue", "()Ljava/lang/Object;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fuse", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class ReadonlyStateFlow implements StateFlow, CancellableFlow, FusibleFlow {
   private final StateFlow $$delegate_0;

   public ReadonlyStateFlow(StateFlow var1) {
      this.$$delegate_0 = var1;
   }

   public Object collect(FlowCollector var1, Continuation var2) {
      return this.$$delegate_0.collect(var1, var2);
   }

   public Flow fuse(CoroutineContext var1, int var2, BufferOverflow var3) {
      return StateFlowKt.fuseStateFlow(this, var1, var2, var3);
   }

   public List getReplayCache() {
      return this.$$delegate_0.getReplayCache();
   }

   public Object getValue() {
      return this.$$delegate_0.getValue();
   }
}
