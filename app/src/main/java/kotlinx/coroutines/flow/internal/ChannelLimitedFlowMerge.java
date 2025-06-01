package kotlinx.coroutines.flow.internal;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B7\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J&\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010\u000f\u001a\u00020\u0015H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"},
   d2 = {"Lkotlinx/coroutines/flow/internal/ChannelLimitedFlowMerge;", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flows", "", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Ljava/lang/Iterable;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "collectTo", "", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "produceImpl", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ChannelLimitedFlowMerge extends ChannelFlow {
   private final Iterable flows;

   public ChannelLimitedFlowMerge(Iterable var1, CoroutineContext var2, int var3, BufferOverflow var4) {
      super(var2, var3, var4);
      this.flows = var1;
   }

   // $FF: synthetic method
   public ChannelLimitedFlowMerge(Iterable var1, CoroutineContext var2, int var3, BufferOverflow var4, int var5, DefaultConstructorMarker var6) {
      if ((var5 & 2) != 0) {
         var2 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var5 & 4) != 0) {
         var3 = -2;
      }

      if ((var5 & 8) != 0) {
         var4 = BufferOverflow.SUSPEND;
      }

      this(var1, var2, var3, var4);
   }

   protected Object collectTo(ProducerScope var1, Continuation var2) {
      SendingCollector var5 = new SendingCollector((SendChannel)var1);
      Iterator var3 = this.flows.iterator();

      while(var3.hasNext()) {
         Flow var4 = (Flow)var3.next();
         BuildersKt.launch$default((CoroutineScope)var1, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(var4, (Continuation)null, var1, var5) {
            final SendingCollector $collector$inlined;
            final Flow $flow;
            final ProducerScope $scope$inlined;
            Object L$0;
            int label;
            private CoroutineScope p$;

            {
               this.$flow = var1;
               this.$scope$inlined = var3;
               this.$collector$inlined = var4;
            }

            public final Continuation create(Object var1, Continuation var2) {
               Function2 var3 = new <anonymous constructor>(this.$flow, var2, this.$scope$inlined, this.$collector$inlined);
               var3.p$ = (CoroutineScope)var1;
               return var3;
            }

            public final Object invoke(Object var1, Object var2) {
               return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object var1) {
               Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               int var2 = this.label;
               if (var2 != 0) {
                  if (var2 != 1) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  CoroutineScope var7 = (CoroutineScope)this.L$0;
                  ResultKt.throwOnFailure(var1);
               } else {
                  ResultKt.throwOnFailure(var1);
                  CoroutineScope var4 = this.p$;
                  Flow var5 = this.$flow;
                  FlowCollector var6 = (FlowCollector)this.$collector$inlined;
                  this.L$0 = var4;
                  this.label = 1;
                  if (var5.collect(var6, this) == var3) {
                     return var3;
                  }
               }

               return Unit.INSTANCE;
            }
         }), 3, (Object)null);
      }

      return Unit.INSTANCE;
   }

   protected ChannelFlow create(CoroutineContext var1, int var2, BufferOverflow var3) {
      return (ChannelFlow)(new ChannelLimitedFlowMerge(this.flows, var1, var2, var3));
   }

   public ReceiveChannel produceImpl(CoroutineScope var1) {
      return FlowCoroutineKt.flowProduce(var1, this.context, this.capacity, this.getCollectToFun$kotlinx_coroutines_core());
   }
}
