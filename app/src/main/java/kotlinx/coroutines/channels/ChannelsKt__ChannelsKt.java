package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"},
   d2 = {"sendBlocking", "", "E", "Lkotlinx/coroutines/channels/SendChannel;", "element", "(Lkotlinx/coroutines/channels/SendChannel;Ljava/lang/Object;)V", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/channels/ChannelsKt"
)
final class ChannelsKt__ChannelsKt {
   public static final void sendBlocking(SendChannel var0, Object var1) {
      if (!var0.offer(var1)) {
         BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2(var0, var1, (Continuation)null) {
            final Object $element;
            final SendChannel $this_sendBlocking;
            Object L$0;
            int label;
            private CoroutineScope p$;

            {
               this.$this_sendBlocking = var1;
               this.$element = var2;
            }

            public final Continuation create(Object var1, Continuation var2) {
               Function2 var3 = new <anonymous constructor>(this.$this_sendBlocking, this.$element, var2);
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
                  SendChannel var6 = this.$this_sendBlocking;
                  Object var4 = this.$element;
                  this.L$0 = var5;
                  this.label = 1;
                  if (var6.send(var4, this) == var3) {
                     return var3;
                  }
               }

               return Unit.INSTANCE;
            }
         }), 1, (Object)null);
      }
   }
}
