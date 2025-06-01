package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.ValueOrClosed;
import kotlinx.coroutines.flow.internal.ChannelFlowKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a0\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n\u001a/\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a9\u0010\u0010\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a&\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"},
   d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "Lkotlinx/coroutines/channels/BroadcastChannel;", "broadcastIn", "scope", "Lkotlinx/coroutines/CoroutineScope;", "start", "Lkotlinx/coroutines/CoroutineStart;", "consumeAsFlow", "Lkotlinx/coroutines/channels/ReceiveChannel;", "emitAll", "", "Lkotlinx/coroutines/flow/FlowCollector;", "channel", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitAllImpl", "consume", "", "emitAllImpl$FlowKt__ChannelsKt", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "produceIn", "receiveAsFlow", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__ChannelsKt {
   public static final Flow asFlow(BroadcastChannel var0) {
      return (Flow)(new Flow(var0) {
         final BroadcastChannel $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = FlowKt.emitAll(var1, this.$this_asFlow$inlined.openSubscription(), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "Use shareIn operator and the resulting SharedFlow as a replacement for BroadcastChannel",
      replaceWith = @ReplaceWith(
   expression = "shareIn(scope, 0, SharingStarted.Lazily)",
   imports = {}
)
   )
   public static final BroadcastChannel broadcastIn(Flow var0, CoroutineScope var1, CoroutineStart var2) {
      return ChannelFlowKt.asChannelFlow(var0).broadcastImpl(var1, var2);
   }

   // $FF: synthetic method
   public static BroadcastChannel broadcastIn$default(Flow var0, CoroutineScope var1, CoroutineStart var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = CoroutineStart.LAZY;
      }

      return FlowKt.broadcastIn(var0, var1, var2);
   }

   public static final Flow consumeAsFlow(ReceiveChannel var0) {
      return (Flow)(new ChannelAsFlow(var0, true, (CoroutineContext)null, 0, (BufferOverflow)null, 28, (DefaultConstructorMarker)null));
   }

   public static final Object emitAll(FlowCollector var0, ReceiveChannel var1, Continuation var2) {
      Object var3 = emitAllImpl$FlowKt__ChannelsKt(var0, var1, true, var2);
      return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
   }

   // $FF: synthetic method
   static final Object emitAllImpl$FlowKt__ChannelsKt(FlowCollector var0, ReceiveChannel var1, boolean var2, Continuation var3) {
      Object var285;
      label2867: {
         if (var3 instanceof <undefinedtype>) {
            <undefinedtype> var6 = (<undefinedtype>)var3;
            if ((var6.label & Integer.MIN_VALUE) != 0) {
               var6.label += Integer.MIN_VALUE;
               var285 = var6;
               break label2867;
            }
         }

         var285 = new ContinuationImpl(var3) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            boolean Z$0;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt((FlowCollector)null, (ReceiveChannel)null, false, this);
            }
         };
      }

      Throwable var10000;
      ReceiveChannel var284;
      Throwable var286;
      label2870: {
         Object var8 = ((<undefinedtype>)var285).result;
         Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         int var4 = ((<undefinedtype>)var285).label;
         boolean var5;
         FlowCollector var7;
         boolean var10001;
         Throwable var287;
         if (var4 != 0) {
            if (var4 != 1) {
               if (var4 != 2) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Object var283 = ((<undefinedtype>)var285).L$3;
               var287 = (Throwable)((<undefinedtype>)var285).L$2;
               var5 = ((<undefinedtype>)var285).Z$0;
               var1 = (ReceiveChannel)((<undefinedtype>)var285).L$1;
               var7 = (FlowCollector)((<undefinedtype>)var285).L$0;
               var2 = var5;
               var284 = var1;

               try {
                  ResultKt.throwOnFailure(var8);
               } catch (Throwable var278) {
                  var10000 = var278;
                  var10001 = false;
                  break label2870;
               }

               var0 = var7;
               var2 = var5;

               try {
                  ((<undefinedtype>)var285).L$0 = var0;
                  ((<undefinedtype>)var285).L$1 = var1;
                  ((<undefinedtype>)var285).Z$0 = var2;
                  ((<undefinedtype>)var285).L$2 = var287;
                  ((<undefinedtype>)var285).L$3 = var0;
                  ((<undefinedtype>)var285).label = 1;
                  var8 = var1.receiveOrClosed_ZYPwvRU((Continuation)var285);
               } finally {
                  ;
               }

               if (var8 == var9) {
                  return var9;
               }

               var5 = var5;
               var7 = var7;
            } else {
               var0 = (FlowCollector)((<undefinedtype>)var285).L$3;
               var287 = (Throwable)((<undefinedtype>)var285).L$2;
               var5 = ((<undefinedtype>)var285).Z$0;
               var1 = (ReceiveChannel)((<undefinedtype>)var285).L$1;
               var7 = (FlowCollector)((<undefinedtype>)var285).L$0;
               var2 = var5;
               var284 = var1;

               try {
                  ResultKt.throwOnFailure(var8);
               } catch (Throwable var279) {
                  var10000 = var279;
                  var10001 = false;
                  break label2870;
               }
            }
         } else {
            ResultKt.throwOnFailure(var8);
            var287 = null;
            Throwable var288 = (Throwable)null;

            try {
               ((<undefinedtype>)var285).L$0 = var0;
               ((<undefinedtype>)var285).L$1 = var1;
               ((<undefinedtype>)var285).Z$0 = var2;
               ((<undefinedtype>)var285).L$2 = var287;
               ((<undefinedtype>)var285).L$3 = var0;
               ((<undefinedtype>)var285).label = 1;
               var8 = var1.receiveOrClosed_ZYPwvRU((Continuation)var285);
            } catch (Throwable var281) {
               ;
            }

            if (var8 == var9) {
               return var9;
            }

            var5 = var2;
            var7 = var0;
         }

         while(true) {
            var2 = var5;
            var284 = var1;

            try {
               if (ValueOrClosed.isClosed_impl(var8)) {
                  break;
               }
            } catch (Throwable var280) {
               var10000 = var280;
               var10001 = false;
               break label2870;
            }

            var2 = var5;
            var284 = var1;

            Object var10;
            try {
               var10 = ValueOrClosed.getValue_impl(var8);
            } catch (Throwable var277) {
               var10000 = var277;
               var10001 = false;
               break label2870;
            }

            var2 = var5;
            var284 = var1;

            try {
               ((<undefinedtype>)var285).L$0 = var7;
            } catch (Throwable var276) {
               var10000 = var276;
               var10001 = false;
               break label2870;
            }

            var2 = var5;
            var284 = var1;

            try {
               ((<undefinedtype>)var285).L$1 = var1;
            } catch (Throwable var275) {
               var10000 = var275;
               var10001 = false;
               break label2870;
            }

            var2 = var5;
            var284 = var1;

            try {
               ((<undefinedtype>)var285).Z$0 = var5;
            } catch (Throwable var274) {
               var10000 = var274;
               var10001 = false;
               break label2870;
            }

            var2 = var5;
            var284 = var1;

            try {
               ((<undefinedtype>)var285).L$2 = var287;
            } catch (Throwable var273) {
               var10000 = var273;
               var10001 = false;
               break label2870;
            }

            var2 = var5;
            var284 = var1;

            try {
               ((<undefinedtype>)var285).L$3 = var8;
            } catch (Throwable var272) {
               var10000 = var272;
               var10001 = false;
               break label2870;
            }

            var2 = var5;
            var284 = var1;

            try {
               ((<undefinedtype>)var285).label = 2;
            } catch (Throwable var271) {
               var10000 = var271;
               var10001 = false;
               break label2870;
            }

            var2 = var5;
            var284 = var1;

            try {
               var8 = var7.emit(var10, (Continuation)var285);
            } catch (Throwable var270) {
               var10000 = var270;
               var10001 = false;
               break label2870;
            }

            if (var8 == var9) {
               return var9;
            }

            var0 = var7;
            var2 = var5;

            try {
               ((<undefinedtype>)var285).L$0 = var0;
               ((<undefinedtype>)var285).L$1 = var1;
               ((<undefinedtype>)var285).Z$0 = var2;
               ((<undefinedtype>)var285).L$2 = var287;
               ((<undefinedtype>)var285).L$3 = var0;
               ((<undefinedtype>)var285).label = 1;
               var8 = var1.receiveOrClosed_ZYPwvRU((Continuation)var285);
            } catch (Throwable var282) {
               ;
            }

            if (var8 == var9) {
               return var9;
            }

            var5 = var5;
            var7 = var7;
         }

         var2 = var5;
         var284 = var1;

         try {
            var286 = ValueOrClosed.getCloseCause_impl(var8);
         } catch (Throwable var269) {
            var10000 = var269;
            var10001 = false;
            break label2870;
         }

         if (var286 == null) {
            if (var5) {
               ChannelsKt.cancelConsumed(var1, var287);
            }

            return Unit.INSTANCE;
         }

         var2 = var5;
         var284 = var1;

         label2779:
         try {
            throw var286;
         } catch (Throwable var268) {
            var10000 = var268;
            var10001 = false;
            break label2779;
         }
      }

      var286 = var10000;

      try {
         throw var286;
      } finally {
         if (var2) {
            ChannelsKt.cancelConsumed(var284, var286);
         }

      }
   }

   public static final ReceiveChannel produceIn(Flow var0, CoroutineScope var1) {
      return ChannelFlowKt.asChannelFlow(var0).produceImpl(var1);
   }

   public static final Flow receiveAsFlow(ReceiveChannel var0) {
      return (Flow)(new ChannelAsFlow(var0, false, (CoroutineContext)null, 0, (BufferOverflow)null, 28, (DefaultConstructorMarker)null));
   }
}
