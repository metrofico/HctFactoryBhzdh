package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ProducerCoroutine;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"},
   d2 = {"Lkotlinx/coroutines/flow/internal/FlowProduceCoroutine;", "T", "Lkotlinx/coroutines/channels/ProducerCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "channel", "Lkotlinx/coroutines/channels/Channel;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/Channel;)V", "childCancelled", "", "cause", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class FlowProduceCoroutine extends ProducerCoroutine {
   public FlowProduceCoroutine(CoroutineContext var1, Channel var2) {
      super(var1, var2);
   }

   public boolean childCancelled(Throwable var1) {
      return var1 instanceof ChildCancelledException ? true : this.cancelImpl$kotlinx_coroutines_core(var1);
   }
}
