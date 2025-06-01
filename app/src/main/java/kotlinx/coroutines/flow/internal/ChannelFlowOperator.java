package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b \u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0014H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J'\u0010\u0016\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u001f\u0010\u0019\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0010H¤@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0004X\u0085\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"},
   d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "S", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectWithContextUndispatched", "newContext", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flowCollect", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class ChannelFlowOperator extends ChannelFlow {
   protected final Flow flow;

   public ChannelFlowOperator(Flow var1, CoroutineContext var2, int var3, BufferOverflow var4) {
      super(var2, var3, var4);
      this.flow = var1;
   }

   // $FF: synthetic method
   static Object collect$suspendImpl(ChannelFlowOperator var0, FlowCollector var1, Continuation var2) {
      Object var5;
      if (var0.capacity == -3) {
         CoroutineContext var4 = var2.getContext();
         CoroutineContext var3 = var4.plus(var0.context);
         if (Intrinsics.areEqual((Object)var3, (Object)var4)) {
            var5 = var0.flowCollect(var1, var2);
            if (var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               return var5;
            }

            return Unit.INSTANCE;
         }

         if (Intrinsics.areEqual((Object)((ContinuationInterceptor)var3.get((CoroutineContext.Key)ContinuationInterceptor.Key)), (Object)((ContinuationInterceptor)var4.get((CoroutineContext.Key)ContinuationInterceptor.Key)))) {
            var5 = var0.collectWithContextUndispatched(var1, var3, var2);
            if (var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               return var5;
            }

            return Unit.INSTANCE;
         }
      }

      var5 = var0.collect(var1, var2);
      return var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var5 : Unit.INSTANCE;
   }

   // $FF: synthetic method
   static Object collectTo$suspendImpl(ChannelFlowOperator var0, ProducerScope var1, Continuation var2) {
      Object var3 = var0.flowCollect((FlowCollector)(new SendingCollector((SendChannel)var1)), var2);
      return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
   }

   public Object collect(FlowCollector var1, Continuation var2) {
      return collect$suspendImpl(this, var1, var2);
   }

   protected Object collectTo(ProducerScope var1, Continuation var2) {
      return collectTo$suspendImpl(this, var1, var2);
   }

   // $FF: synthetic method
   final Object collectWithContextUndispatched(FlowCollector var1, CoroutineContext var2, Continuation var3) {
      Object var4 = ChannelFlowKt.withContextUndispatched$default(var2, ChannelFlowKt.access$withUndispatchedContextCollector(var1, var3.getContext()), (Object)null, (Function2)(new Function2(this, (Continuation)null) {
         Object L$0;
         int label;
         private FlowCollector p$0;
         final ChannelFlowOperator this$0;

         {
            this.this$0 = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.this$0, var2);
            var3.p$0 = (FlowCollector)var1;
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

               FlowCollector var6 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var4 = this.p$0;
               ChannelFlowOperator var5 = this.this$0;
               this.L$0 = var4;
               this.label = 1;
               if (var5.flowCollect(var4, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }), var3, 4, (Object)null);
      return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
   }

   protected abstract Object flowCollect(FlowCollector var1, Continuation var2);

   public String toString() {
      return this.flow + " -> " + super.toString();
   }
}
