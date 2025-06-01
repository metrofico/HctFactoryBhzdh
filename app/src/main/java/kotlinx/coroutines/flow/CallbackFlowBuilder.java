package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.internal.ChannelFlow;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002BU\u0012-\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J&\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014R:\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"},
   d2 = {"Lkotlinx/coroutines/flow/CallbackFlowBuilder;", "T", "Lkotlinx/coroutines/flow/ChannelFlowBuilder;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "Lkotlin/jvm/functions/Function2;", "collectTo", "scope", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class CallbackFlowBuilder extends ChannelFlowBuilder {
   private final Function2 block;

   public CallbackFlowBuilder(Function2 var1, CoroutineContext var2, int var3, BufferOverflow var4) {
      super(var1, var2, var3, var4);
      this.block = var1;
   }

   // $FF: synthetic method
   public CallbackFlowBuilder(Function2 var1, CoroutineContext var2, int var3, BufferOverflow var4, int var5, DefaultConstructorMarker var6) {
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
      Object var8;
      label27: {
         if (var2 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var2;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var8 = var4;
               break label27;
            }
         }

         var8 = new ContinuationImpl(this, var2) {
            Object L$0;
            Object L$1;
            int label;
            Object result;
            final CallbackFlowBuilder this$0;

            {
               this.this$0 = var1;
            }

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.collectTo((ProducerScope)null, this);
            }
         };
      }

      Object var5 = ((<undefinedtype>)var8).result;
      Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var3 = ((<undefinedtype>)var8).label;
      ProducerScope var9;
      if (var3 != 0) {
         if (var3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var9 = (ProducerScope)((<undefinedtype>)var8).L$1;
         CallbackFlowBuilder var7 = (CallbackFlowBuilder)((<undefinedtype>)var8).L$0;
         ResultKt.throwOnFailure(var5);
      } else {
         ResultKt.throwOnFailure(var5);
         ((<undefinedtype>)var8).L$0 = this;
         ((<undefinedtype>)var8).L$1 = var1;
         ((<undefinedtype>)var8).label = 1;
         var9 = var1;
         if (super.collectTo(var1, (Continuation)var8) == var6) {
            return var6;
         }
      }

      if (var9.isClosedForSend()) {
         return Unit.INSTANCE;
      } else {
         throw (Throwable)(new IllegalStateException("'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.\nOtherwise, a callback/listener may leak in case of external cancellation.\nSee callbackFlow API documentation for the details."));
      }
   }

   protected ChannelFlow create(CoroutineContext var1, int var2, BufferOverflow var3) {
      return (ChannelFlow)(new CallbackFlowBuilder(this.block, var1, var2, var3));
   }
}
