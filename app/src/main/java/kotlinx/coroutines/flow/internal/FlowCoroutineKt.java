package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000J\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aB\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012)\b\u0001\u0010\u0002\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001aS\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\n\"\u0004\b\u0000\u0010\u000125\b\u0001\u0010\u0002\u001a/\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000b¢\u0006\u0002\b\u0007H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001ac\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0011*\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152/\b\u0001\u0010\u0002\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"},
   d2 = {"flowScope", "R", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scopedFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "(Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "flowProduce", "Lkotlinx/coroutines/channels/ReceiveChannel;", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class FlowCoroutineKt {
   public static final ReceiveChannel flowProduce(CoroutineScope var0, CoroutineContext var1, int var2, Function2 var3) {
      Channel var4 = ChannelKt.Channel$default(var2, (BufferOverflow)null, (Function1)null, 6, (Object)null);
      FlowProduceCoroutine var5 = new FlowProduceCoroutine(CoroutineContextKt.newCoroutineContext(var0, var1), var4);
      var5.start(CoroutineStart.ATOMIC, var5, var3);
      return (ReceiveChannel)var5;
   }

   // $FF: synthetic method
   public static ReceiveChannel flowProduce$default(CoroutineScope var0, CoroutineContext var1, int var2, Function2 var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      return flowProduce(var0, var1, var2, var3);
   }

   public static final Object flowScope(Function2 var0, Continuation var1) {
      FlowCoroutine var2 = new FlowCoroutine(var1.getContext(), var1);
      Object var3 = UndispatchedKt.startUndispatchedOrReturn((ScopeCoroutine)var2, var2, var0);
      if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var3;
   }

   public static final Flow scopedFlow(Function3 var0) {
      return (Flow)(new Flow(var0) {
         final Function3 $block$inlined;

         public {
            this.$block$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = FlowCoroutineKt.flowScope((Function2)(new Function2(var1, (Continuation)null, this) {
               final FlowCollector $this_unsafeFlow;
               Object L$0;
               int label;
               private CoroutineScope p$;
               final <undefinedtype> this$0;

               {
                  this.$this_unsafeFlow = var1;
                  this.this$0 = var3;
               }

               public final Continuation create(Object var1, Continuation var2) {
                  Function2 var3 = new <anonymous constructor>(this.$this_unsafeFlow, var2, this.this$0);
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
                     CoroutineScope var5 = this.p$;
                     Function3 var4 = this.this$0.$block$inlined;
                     FlowCollector var6 = this.$this_unsafeFlow;
                     this.L$0 = var5;
                     this.label = 1;
                     InlineMarker.mark(6);
                     var1 = var4.invoke(var5, var6, this);
                     InlineMarker.mark(7);
                     if (var1 == var3) {
                        return var3;
                     }
                  }

                  return Unit.INSTANCE;
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }
}
