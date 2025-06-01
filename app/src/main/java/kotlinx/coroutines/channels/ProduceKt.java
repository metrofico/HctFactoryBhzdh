package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u009e\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2-\b\u0002\u0010\u0010\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011j\u0004\u0018\u0001`\u00162/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a¨\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2-\b\u0002\u0010\u0010\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011j\u0004\u0018\u0001`\u00162/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017¢\u0006\u0002\b\u001aH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001ae\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "},
   d2 = {"awaitClose", "", "Lkotlinx/coroutines/channels/ProducerScope;", "block", "Lkotlin/Function0;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "produce", "Lkotlinx/coroutines/channels/ReceiveChannel;", "E", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "start", "Lkotlinx/coroutines/CoroutineStart;", "onCompletion", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlinx/coroutines/CompletionHandler;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ProduceKt {
   public static final Object awaitClose(ProducerScope var0, Function0 var1, Continuation var2) {
      Object var4;
      label2189: {
         if (var2 instanceof <undefinedtype>) {
            var4 = (<undefinedtype>)var2;
            if ((((<undefinedtype>)var4).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)var4).label += Integer.MIN_VALUE;
               break label2189;
            }
         }

         var4 = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return ProduceKt.awaitClose((ProducerScope)null, (Function0)null, this);
            }
         };
      }

      Throwable var10000;
      Function0 var252;
      label2196: {
         Object var6 = ((<undefinedtype>)var4).result;
         Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         int var3 = ((<undefinedtype>)var4).label;
         boolean var10001;
         Function0 var249;
         if (var3 != 0) {
            if (var3 != 1) {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            var249 = (Function0)((<undefinedtype>)var4).L$1;
            ProducerScope var251 = (ProducerScope)((<undefinedtype>)var4).L$0;
            var252 = var249;

            try {
               ResultKt.throwOnFailure(var6);
            } catch (Throwable var247) {
               var10000 = var247;
               var10001 = false;
               break label2196;
            }
         } else {
            ResultKt.throwOnFailure(var6);
            boolean var253;
            if ((Job)((Continuation)var4).getContext().get((CoroutineContext.Key)Job.Key) == var0) {
               var253 = true;
            } else {
               var253 = false;
            }

            if (!var253) {
               throw (Throwable)(new IllegalStateException("awaitClose() can only be invoked from the producer context".toString()));
            }

            var252 = var1;

            try {
               ((<undefinedtype>)var4).L$0 = var0;
            } catch (Throwable var246) {
               var10000 = var246;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            try {
               ((<undefinedtype>)var4).L$1 = var1;
            } catch (Throwable var245) {
               var10000 = var245;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            try {
               ((<undefinedtype>)var4).label = 1;
            } catch (Throwable var244) {
               var10000 = var244;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            Continuation var7;
            try {
               var7 = (Continuation)var4;
            } catch (Throwable var243) {
               var10000 = var243;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            CancellableContinuationImpl var254;
            try {
               var254 = new CancellableContinuationImpl;
            } catch (Throwable var242) {
               var10000 = var242;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            try {
               var254.<init>(IntrinsicsKt.intercepted(var7), 1);
            } catch (Throwable var241) {
               var10000 = var241;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            try {
               var254.initCancellability();
            } catch (Throwable var240) {
               var10000 = var240;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            CancellableContinuation var255;
            try {
               var255 = (CancellableContinuation)var254;
            } catch (Throwable var239) {
               var10000 = var239;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            Function1 var8;
            try {
               var8 = new Function1() {
                  final CancellableContinuation $cont;

                  {
                     this.$cont = var1;
                  }

                  public final void invoke(Throwable var1) {
                     Continuation var4 = (Continuation)this.$cont;
                     Unit var3 = Unit.INSTANCE;
                     Result.Companion var2 = Result.Companion;
                     var4.resumeWith(Result.constructor_impl(var3));
                  }
               };
            } catch (Throwable var238) {
               var10000 = var238;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            try {
               var8.<init>(var255);
            } catch (Throwable var237) {
               var10000 = var237;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            try {
               var0.invokeOnClose((Function1)var8);
            } catch (Throwable var236) {
               var10000 = var236;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            try {
               var6 = var254.getResult();
            } catch (Throwable var235) {
               var10000 = var235;
               var10001 = false;
               break label2196;
            }

            var252 = var1;

            label2194: {
               try {
                  if (var6 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                     break label2194;
                  }
               } catch (Throwable var248) {
                  var10000 = var248;
                  var10001 = false;
                  break label2196;
               }

               var252 = var1;

               try {
                  DebugProbesKt.probeCoroutineSuspended((Continuation)var4);
               } catch (Throwable var234) {
                  var10000 = var234;
                  var10001 = false;
                  break label2196;
               }
            }

            var249 = var1;
            if (var6 == var5) {
               return var5;
            }
         }

         var249.invoke();
         return Unit.INSTANCE;
      }

      Throwable var250 = var10000;
      var252.invoke();
      throw var250;
   }

   // $FF: synthetic method
   public static Object awaitClose$default(ProducerScope var0, Function0 var1, Continuation var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = (Function0)null.INSTANCE;
      }

      return awaitClose(var0, var1, var2);
   }

   public static final ReceiveChannel produce(CoroutineScope var0, CoroutineContext var1, int var2, Function2 var3) {
      return produce(var0, var1, var2, BufferOverflow.SUSPEND, CoroutineStart.DEFAULT, (Function1)null, var3);
   }

   public static final ReceiveChannel produce(CoroutineScope var0, CoroutineContext var1, int var2, CoroutineStart var3, Function1 var4, Function2 var5) {
      return produce(var0, var1, var2, BufferOverflow.SUSPEND, var3, var4, var5);
   }

   public static final ReceiveChannel produce(CoroutineScope var0, CoroutineContext var1, int var2, BufferOverflow var3, CoroutineStart var4, Function1 var5, Function2 var6) {
      Channel var8 = ChannelKt.Channel$default(var2, var3, (Function1)null, 4, (Object)null);
      ProducerCoroutine var7 = new ProducerCoroutine(CoroutineContextKt.newCoroutineContext(var0, var1), var8);
      if (var5 != null) {
         var7.invokeOnCompletion(var5);
      }

      var7.start(var4, var7, var6);
      return (ReceiveChannel)var7;
   }

   // $FF: synthetic method
   public static ReceiveChannel produce$default(CoroutineScope var0, CoroutineContext var1, int var2, Function2 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      return produce(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static ReceiveChannel produce$default(CoroutineScope var0, CoroutineContext var1, int var2, CoroutineStart var3, Function1 var4, Function2 var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var1 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var6 & 2) != 0) {
         var2 = 0;
      }

      if ((var6 & 4) != 0) {
         var3 = CoroutineStart.DEFAULT;
      }

      if ((var6 & 8) != 0) {
         var4 = null;
         Function1 var8 = (Function1)null;
      }

      return produce(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static ReceiveChannel produce$default(CoroutineScope var0, CoroutineContext var1, int var2, BufferOverflow var3, CoroutineStart var4, Function1 var5, Function2 var6, int var7, Object var8) {
      if ((var7 & 1) != 0) {
         var1 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var7 & 2) != 0) {
         var2 = 0;
      }

      if ((var7 & 4) != 0) {
         var3 = BufferOverflow.SUSPEND;
      }

      if ((var7 & 8) != 0) {
         var4 = CoroutineStart.DEFAULT;
      }

      if ((var7 & 16) != 0) {
         var5 = null;
         Function1 var9 = (Function1)null;
      }

      return produce(var0, var1, var2, var3, var4, var5, var6);
   }
}
