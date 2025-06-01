package kotlinx.coroutines.flow;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import kotlinx.coroutines.flow.internal.SendingCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000 B;\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0014¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J!\u0010\u001b\u001a\u00020\u001a2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ!\u0010\u001e\u001a\u00020\u001a2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0094@ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ-\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0014¢\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010#H\u0016¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u001aH\u0002¢\u0006\u0004\b&\u0010'J\u001d\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b(\u0010)R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010*R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010+\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"},
   d2 = {"Lkotlinx/coroutines/flow/ChannelAsFlow;", "T", "Lkotlinx/coroutines/channels/ReceiveChannel;", "channel", "", "consume", "Lkotlin/coroutines/CoroutineContext;", "context", "", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "<init>", "(Lkotlinx/coroutines/channels/ReceiveChannel;ZLkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "", "additionalToStringProps", "()Ljava/lang/String;", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlinx/coroutines/CoroutineStart;", "start", "Lkotlinx/coroutines/channels/BroadcastChannel;", "broadcastImpl", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineStart;)Lkotlinx/coroutines/channels/BroadcastChannel;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ProducerScope;", "collectTo", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "create", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)Lkotlinx/coroutines/flow/internal/ChannelFlow;", "Lkotlinx/coroutines/flow/Flow;", "dropChannelOperators", "()Lkotlinx/coroutines/flow/Flow;", "markConsumed", "()V", "produceImpl", "(Lkotlinx/coroutines/CoroutineScope;)Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Z", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class ChannelAsFlow extends ChannelFlow {
   private static final AtomicIntegerFieldUpdater consumed$FU = AtomicIntegerFieldUpdater.newUpdater(ChannelAsFlow.class, "consumed");
   private final ReceiveChannel channel;
   private final boolean consume;
   private volatile int consumed;

   public ChannelAsFlow(ReceiveChannel var1, boolean var2, CoroutineContext var3, int var4, BufferOverflow var5) {
      super(var3, var4, var5);
      this.channel = var1;
      this.consume = var2;
      this.consumed = 0;
   }

   // $FF: synthetic method
   public ChannelAsFlow(ReceiveChannel var1, boolean var2, CoroutineContext var3, int var4, BufferOverflow var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 4) != 0) {
         var3 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var6 & 8) != 0) {
         var4 = -3;
      }

      if ((var6 & 16) != 0) {
         var5 = BufferOverflow.SUSPEND;
      }

      this(var1, var2, var3, var4, var5);
   }

   private final void markConsumed() {
      if (this.consume) {
         AtomicIntegerFieldUpdater var2 = consumed$FU;
         boolean var1 = true;
         if (var2.getAndSet(this, 1) != 0) {
            var1 = false;
         }

         if (!var1) {
            throw (Throwable)(new IllegalStateException("ReceiveChannel.consumeAsFlow can be collected just once".toString()));
         }
      }

   }

   protected String additionalToStringProps() {
      return "channel=" + this.channel;
   }

   public BroadcastChannel broadcastImpl(CoroutineScope var1, CoroutineStart var2) {
      this.markConsumed();
      return super.broadcastImpl(var1, var2);
   }

   public Object collect(FlowCollector var1, Continuation var2) {
      Object var3;
      if (this.capacity == -3) {
         this.markConsumed();
         var3 = FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(var1, this.channel, this.consume, var2);
         if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return var3;
         }
      } else {
         var3 = super.collect(var1, var2);
         if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return var3;
         }
      }

      return Unit.INSTANCE;
   }

   protected Object collectTo(ProducerScope var1, Continuation var2) {
      Object var3 = FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt((FlowCollector)(new SendingCollector((SendChannel)var1)), this.channel, this.consume, var2);
      return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
   }

   protected ChannelFlow create(CoroutineContext var1, int var2, BufferOverflow var3) {
      return (ChannelFlow)(new ChannelAsFlow(this.channel, this.consume, var1, var2, var3));
   }

   public Flow dropChannelOperators() {
      return (Flow)(new ChannelAsFlow(this.channel, this.consume, (CoroutineContext)null, 0, (BufferOverflow)null, 28, (DefaultConstructorMarker)null));
   }

   public ReceiveChannel produceImpl(CoroutineScope var1) {
      this.markConsumed();
      ReceiveChannel var2;
      if (this.capacity == -3) {
         var2 = this.channel;
      } else {
         var2 = super.produceImpl(var1);
      }

      return var2;
   }
}
