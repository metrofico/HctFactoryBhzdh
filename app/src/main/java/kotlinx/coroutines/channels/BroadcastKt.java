package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u009c\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2-\b\u0002\u0010\n\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000bj\u0004\u0018\u0001`\u00112/\b\u0001\u0010\u0012\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0013¢\u0006\u0002\b\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a0\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00192\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"},
   d2 = {"broadcast", "Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "start", "Lkotlinx/coroutines/CoroutineStart;", "onCompletion", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/BroadcastChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class BroadcastKt {
   public static final BroadcastChannel broadcast(CoroutineScope var0, CoroutineContext var1, int var2, CoroutineStart var3, Function1 var4, Function2 var5) {
      var1 = CoroutineContextKt.newCoroutineContext(var0, var1);
      BroadcastChannel var6 = BroadcastChannelKt.BroadcastChannel(var2);
      BroadcastCoroutine var7;
      if (var3.isLazy()) {
         var7 = (BroadcastCoroutine)(new LazyBroadcastCoroutine(var1, var6, var5));
      } else {
         var7 = new BroadcastCoroutine(var1, var6, true);
      }

      if (var4 != null) {
         var7.invokeOnCompletion(var4);
      }

      var7.start(var3, var7, var5);
      return (BroadcastChannel)var7;
   }

   public static final BroadcastChannel broadcast(ReceiveChannel var0, int var1, CoroutineStart var2) {
      return broadcast$default(CoroutineScopeKt.plus(CoroutineScopeKt.plus((CoroutineScope)GlobalScope.INSTANCE, (CoroutineContext)Dispatchers.getUnconfined()), (CoroutineContext)((CoroutineExceptionHandler)(new CoroutineExceptionHandler((CoroutineContext.Key)CoroutineExceptionHandler.Key) {
         public void handleException(CoroutineContext var1, Throwable var2) {
         }
      }))), (CoroutineContext)null, var1, var2, (Function1)(new Function1(var0) {
         final ReceiveChannel $this_broadcast;

         {
            this.$this_broadcast = var1;
         }

         public final void invoke(Throwable var1) {
            ChannelsKt.cancelConsumed(this.$this_broadcast, var1);
         }
      }), (Function2)(new Function2(var0, (Continuation)null) {
         final ReceiveChannel $this_broadcast;
         Object L$0;
         Object L$1;
         Object L$2;
         int label;
         private ProducerScope p$;

         {
            this.$this_broadcast = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$this_broadcast, var2);
            var3.p$ = (ProducerScope)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            ProducerScope var3;
            ChannelIterator var4;
            ChannelIterator var5;
            <undefinedtype> var8;
            ProducerScope var9;
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var4 = (ChannelIterator)this.L$2;
                  var3 = (ProducerScope)this.L$0;
                  ResultKt.throwOnFailure(var1);
                  var8 = this;
                  var5 = var4;
               } else {
                  var5 = (ChannelIterator)this.L$1;
                  var9 = (ProducerScope)this.L$0;
                  ResultKt.throwOnFailure(var1);
                  if (!(Boolean)var1) {
                     return Unit.INSTANCE;
                  }

                  var1 = var5.next();
                  this.L$0 = var9;
                  this.L$1 = var1;
                  this.L$2 = var5;
                  this.label = 2;
                  if (var9.send(var1, this) == var7) {
                     return var7;
                  }

                  var8 = this;
                  var3 = var9;
               }
            } else {
               ResultKt.throwOnFailure(var1);
               var3 = this.p$;
               var4 = this.$this_broadcast.iterator();
               var8 = this;
               var5 = var4;
            }

            while(true) {
               var8.L$0 = var3;
               var8.L$1 = var5;
               var8.label = 1;
               Object var6 = var5.hasNext(var8);
               if (var6 == var7) {
                  return var7;
               }

               var9 = var3;
               Object var10 = var6;
               <undefinedtype> var11 = var8;
               if (!(Boolean)var10) {
                  return Unit.INSTANCE;
               }

               var1 = var5.next();
               var11.L$0 = var9;
               var11.L$1 = var1;
               var11.L$2 = var5;
               var11.label = 2;
               if (var9.send(var1, var11) == var7) {
                  return var7;
               }

               var8 = var11;
               var3 = var9;
            }
         }
      }), 1, (Object)null);
   }

   // $FF: synthetic method
   public static BroadcastChannel broadcast$default(CoroutineScope var0, CoroutineContext var1, int var2, CoroutineStart var3, Function1 var4, Function2 var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var1 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var6 & 2) != 0) {
         var2 = 1;
      }

      if ((var6 & 4) != 0) {
         var3 = CoroutineStart.LAZY;
      }

      if ((var6 & 8) != 0) {
         var4 = null;
         Function1 var8 = (Function1)null;
      }

      return broadcast(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static BroadcastChannel broadcast$default(ReceiveChannel var0, int var1, CoroutineStart var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = 1;
      }

      if ((var3 & 2) != 0) {
         var2 = CoroutineStart.LAZY;
      }

      return broadcast(var0, var1, var2);
   }
}
