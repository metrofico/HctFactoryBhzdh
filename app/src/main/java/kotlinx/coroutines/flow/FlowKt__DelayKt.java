package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.time.Duration;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004H\u0007\u001a:\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\b\b\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0005H\u0007\u001a0\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a7\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004H\u0002¢\u0006\u0002\b\r\u001a$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u0005H\u0000\u001a&\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0005H\u0007\u001a0\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"},
   d2 = {"debounce", "Lkotlinx/coroutines/flow/Flow;", "T", "timeoutMillis", "Lkotlin/Function1;", "", "timeout", "Lkotlin/time/Duration;", "debounceDuration", "debounce-8GFy2Ro", "(Lkotlinx/coroutines/flow/Flow;D)Lkotlinx/coroutines/flow/Flow;", "debounceInternal", "timeoutMillisSelector", "debounceInternal$FlowKt__DelayKt", "fixedPeriodTicker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", "Lkotlinx/coroutines/CoroutineScope;", "delayMillis", "initialDelayMillis", "sample", "periodMillis", "period", "sample-8GFy2Ro", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__DelayKt {
   public static final Flow debounce(Flow var0, long var1) {
      long var5;
      int var4 = (var5 = var1 - 0L) == 0L ? 0 : (var5 < 0L ? -1 : 1);
      boolean var3;
      if (var4 >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         return var4 == 0 ? var0 : debounceInternal$FlowKt__DelayKt(var0, (Function1)(new Function1(var1) {
            final long $timeoutMillis;

            {
               this.$timeoutMillis = var1;
            }

            public final long invoke(Object var1) {
               return this.$timeoutMillis;
            }
         }));
      } else {
         throw (Throwable)(new IllegalArgumentException("Debounce timeout should not be negative".toString()));
      }
   }

   public static final Flow debounce(Flow var0, Function1 var1) {
      return debounceInternal$FlowKt__DelayKt(var0, var1);
   }

   public static final Flow debounce_8GFy2Ro(Flow var0, double var1) {
      return FlowKt.debounce(var0, DelayKt.toDelayMillis_LRDsOJo(var1));
   }

   public static final Flow debounceDuration(Flow var0, Function1 var1) {
      return debounceInternal$FlowKt__DelayKt(var0, (Function1)(new Function1(var1) {
         final Function1 $timeout;

         {
            this.$timeout = var1;
         }

         public final long invoke(Object var1) {
            return DelayKt.toDelayMillis_LRDsOJo(((Duration)this.$timeout.invoke(var1)).unbox_impl());
         }
      }));
   }

   private static final Flow debounceInternal$FlowKt__DelayKt(Flow var0, Function1 var1) {
      return FlowCoroutineKt.scopedFlow((Function3)(new Function3(var0, var1, (Continuation)null) {
         final Flow $this_debounceInternal;
         final Function1 $timeoutMillisSelector;
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
         Object L$4;
         Object L$5;
         int label;
         private CoroutineScope p$;
         private FlowCollector p$0;

         {
            this.$this_debounceInternal = var1;
            this.$timeoutMillisSelector = var2;
         }

         public final Continuation create(CoroutineScope var1, FlowCollector var2, Continuation var3) {
            Function3 var4 = new <anonymous constructor>(this.$this_debounceInternal, this.$timeoutMillisSelector, var3);
            var4.p$ = var1;
            var4.p$0 = var2;
            return var4;
         }

         public final Object invoke(Object var1, Object var2, Object var3) {
            return ((<undefinedtype>)this.create((CoroutineScope)var1, (FlowCollector)var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object param1) {
            // $FF: Couldn't be decompiled
         }
      }));
   }

   public static final ReceiveChannel fixedPeriodTicker(CoroutineScope var0, long var1, long var3) {
      boolean var6 = true;
      boolean var5;
      if (var1 >= 0L) {
         var5 = true;
      } else {
         var5 = false;
      }

      if (var5) {
         if (var3 >= 0L) {
            var5 = var6;
         } else {
            var5 = false;
         }

         if (var5) {
            return ProduceKt.produce$default(var0, (CoroutineContext)null, 0, (Function2)(new Function2(var3, var1, (Continuation)null) {
               final long $delayMillis;
               final long $initialDelayMillis;
               Object L$0;
               int label;
               private ProducerScope p$;

               {
                  this.$initialDelayMillis = var1;
                  this.$delayMillis = var3;
               }

               public final Continuation create(Object var1, Continuation var2) {
                  Function2 var3 = new <anonymous constructor>(this.$initialDelayMillis, this.$delayMillis, var2);
                  var3.p$ = (ProducerScope)var1;
                  return var3;
               }

               public final Object invoke(Object var1, Object var2) {
                  return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  long var3;
                  ProducerScope var6;
                  ProducerScope var10;
                  <undefinedtype> var12;
                  if (var2 != 0) {
                     ProducerScope var5;
                     if (var2 != 1) {
                        if (var2 != 2) {
                           if (var2 != 3) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           var5 = (ProducerScope)this.L$0;
                           ResultKt.throwOnFailure(var1);
                           var10 = var5;
                           var12 = this;
                           var6 = var10;
                        } else {
                           var6 = (ProducerScope)this.L$0;
                           ResultKt.throwOnFailure(var1);
                           var3 = this.$delayMillis;
                           this.L$0 = var6;
                           this.label = 3;
                           var12 = this;
                           if (DelayKt.delay(var3, this) == var7) {
                              return var7;
                           }
                        }
                     } else {
                        var5 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        var10 = var5;
                        var12 = this;
                        var6 = var10;
                     }
                  } else {
                     ResultKt.throwOnFailure(var1);
                     var10 = this.p$;
                     var3 = this.$initialDelayMillis;
                     this.L$0 = var10;
                     this.label = 1;
                     if (DelayKt.delay(var3, this) == var7) {
                        return var7;
                     }

                     var12 = this;
                     var6 = var10;
                  }

                  <undefinedtype> var11;
                  do {
                     SendChannel var8 = var6.getChannel();
                     Unit var9 = Unit.INSTANCE;
                     var12.L$0 = var6;
                     var12.label = 2;
                     var11 = var12;
                     if (var8.send(var9, var12) == var7) {
                        return var7;
                     }

                     var3 = var12.$delayMillis;
                     var12.L$0 = var6;
                     var12.label = 3;
                     var12 = var12;
                  } while(DelayKt.delay(var3, var11) != var7);

                  return var7;
               }
            }), 1, (Object)null);
         } else {
            throw (Throwable)(new IllegalArgumentException(("Expected non-negative initial delay, but has " + var3 + " ms").toString()));
         }
      } else {
         throw (Throwable)(new IllegalArgumentException(("Expected non-negative delay, but has " + var1 + " ms").toString()));
      }
   }

   // $FF: synthetic method
   public static ReceiveChannel fixedPeriodTicker$default(CoroutineScope var0, long var1, long var3, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var3 = var1;
      }

      return FlowKt.fixedPeriodTicker(var0, var1, var3);
   }

   public static final Flow sample(Flow var0, long var1) {
      boolean var3;
      if (var1 > 0L) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         return FlowCoroutineKt.scopedFlow((Function3)(new Function3(var0, var1, (Continuation)null) {
            final long $periodMillis;
            final Flow $this_sample;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            int label;
            private CoroutineScope p$;
            private FlowCollector p$0;

            {
               this.$this_sample = var1;
               this.$periodMillis = var2;
            }

            public final Continuation create(CoroutineScope var1, FlowCollector var2, Continuation var3) {
               Function3 var4 = new <anonymous constructor>(this.$this_sample, this.$periodMillis, var3);
               var4.p$ = var1;
               var4.p$0 = var2;
               return var4;
            }

            public final Object invoke(Object var1, Object var2, Object var3) {
               return ((<undefinedtype>)this.create((CoroutineScope)var1, (FlowCollector)var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object param1) {
               // $FF: Couldn't be decompiled
            }
         }));
      } else {
         throw (Throwable)(new IllegalArgumentException("Sample period should be positive".toString()));
      }
   }

   public static final Flow sample_8GFy2Ro(Flow var0, double var1) {
      return FlowKt.sample(var0, DelayKt.toDelayMillis_LRDsOJo(var1));
   }
}
