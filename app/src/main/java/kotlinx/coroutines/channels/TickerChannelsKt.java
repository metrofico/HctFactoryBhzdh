package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.EventLoop_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.TimeSource;
import kotlinx.coroutines.TimeSourceKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a4\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"},
   d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedPeriodTicker", "ticker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "mode", "Lkotlinx/coroutines/channels/TickerMode;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class TickerChannelsKt {
   // $FF: synthetic method
   static final Object fixedDelayTicker(long var0, long var2, SendChannel var4, Continuation var5) {
      Object var11;
      label31: {
         if (var5 instanceof <undefinedtype>) {
            <undefinedtype> var9 = (<undefinedtype>)var5;
            if ((var9.label & Integer.MIN_VALUE) != 0) {
               var9.label += Integer.MIN_VALUE;
               var11 = var9;
               break label31;
            }
         }

         var11 = new ContinuationImpl(var5) {
            long J$0;
            long J$1;
            Object L$0;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return TickerChannelsKt.fixedDelayTicker(0L, 0L, (SendChannel)null, this);
            }
         };
      }

      Object var13 = ((<undefinedtype>)var11).result;
      Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var6 = ((<undefinedtype>)var11).label;
      long var7;
      if (var6 != 0) {
         if (var6 != 1) {
            if (var6 != 2) {
               if (var6 != 3) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var4 = (SendChannel)((<undefinedtype>)var11).L$0;
               var7 = ((<undefinedtype>)var11).J$1;
               var2 = ((<undefinedtype>)var11).J$0;
               ResultKt.throwOnFailure(var13);
               var0 = var2;
               var13 = var11;
            } else {
               var4 = (SendChannel)((<undefinedtype>)var11).L$0;
               var7 = ((<undefinedtype>)var11).J$1;
               var0 = ((<undefinedtype>)var11).J$0;
               ResultKt.throwOnFailure(var13);
               ((<undefinedtype>)var11).J$0 = var0;
               ((<undefinedtype>)var11).J$1 = var7;
               ((<undefinedtype>)var11).L$0 = var4;
               ((<undefinedtype>)var11).label = 3;
               if (DelayKt.delay(var0, (Continuation)var11) == var10) {
                  return var10;
               }

               var0 = var0;
               var13 = var11;
            }
         } else {
            var4 = (SendChannel)((<undefinedtype>)var11).L$0;
            var7 = ((<undefinedtype>)var11).J$1;
            var0 = ((<undefinedtype>)var11).J$0;
            ResultKt.throwOnFailure(var13);
            var13 = var11;
         }
      } else {
         ResultKt.throwOnFailure(var13);
         ((<undefinedtype>)var11).J$0 = var0;
         ((<undefinedtype>)var11).J$1 = var2;
         ((<undefinedtype>)var11).L$0 = var4;
         ((<undefinedtype>)var11).label = 1;
         var13 = var11;
         var7 = var2;
         if (DelayKt.delay(var2, (Continuation)var11) == var10) {
            return var10;
         }
      }

      while(true) {
         Unit var12 = Unit.INSTANCE;
         ((<undefinedtype>)var13).J$0 = var0;
         ((<undefinedtype>)var13).J$1 = var7;
         ((<undefinedtype>)var13).L$0 = var4;
         ((<undefinedtype>)var13).label = 2;
         if (var4.send(var12, (Continuation)var13) == var10) {
            return var10;
         }

         ((<undefinedtype>)var13).J$0 = var0;
         ((<undefinedtype>)var13).J$1 = var7;
         ((<undefinedtype>)var13).L$0 = var4;
         ((<undefinedtype>)var13).label = 3;
         if (DelayKt.delay(var0, (Continuation)var13) == var10) {
            return var10;
         }

         var0 = var0;
         var13 = var13;
      }
   }

   // $FF: synthetic method
   static final Object fixedPeriodTicker(long var0, long var2, SendChannel var4, Continuation var5) {
      long var7;
      Object var21;
      label56: {
         var7 = var2;
         if (var5 instanceof <undefinedtype>) {
            <undefinedtype> var19 = (<undefinedtype>)var5;
            if ((var19.label & Integer.MIN_VALUE) != 0) {
               var19.label += Integer.MIN_VALUE;
               var21 = var19;
               break label56;
            }
         }

         var21 = new ContinuationImpl(var5) {
            long J$0;
            long J$1;
            long J$2;
            long J$3;
            long J$4;
            long J$5;
            long J$6;
            Object L$0;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return TickerChannelsKt.fixedPeriodTicker(0L, 0L, (SendChannel)null, this);
            }
         };
      }

      Object var20 = ((<undefinedtype>)var21).result;
      Object var22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var6 = ((<undefinedtype>)var21).label;
      long var9;
      long var11;
      Unit var23;
      TimeSource var24;
      Long var25;
      if (var6 != 0) {
         if (var6 != 1) {
            if (var6 != 2) {
               if (var6 != 3) {
                  if (var6 != 4) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var0 = ((<undefinedtype>)var21).J$5;
                  var0 = ((<undefinedtype>)var21).J$4;
                  var7 = ((<undefinedtype>)var21).J$3;
                  var2 = ((<undefinedtype>)var21).J$2;
                  var4 = (SendChannel)((<undefinedtype>)var21).L$0;
                  var9 = ((<undefinedtype>)var21).J$1;
                  var0 = ((<undefinedtype>)var21).J$0;
                  ResultKt.throwOnFailure(var20);
               } else {
                  var0 = ((<undefinedtype>)var21).J$6;
                  var0 = ((<undefinedtype>)var21).J$5;
                  var0 = ((<undefinedtype>)var21).J$4;
                  var7 = ((<undefinedtype>)var21).J$3;
                  var11 = ((<undefinedtype>)var21).J$2;
                  var4 = (SendChannel)((<undefinedtype>)var21).L$0;
                  var9 = ((<undefinedtype>)var21).J$1;
                  var0 = ((<undefinedtype>)var21).J$0;
                  ResultKt.throwOnFailure(var20);
                  var2 = var11;
               }

               var11 = var0;
               var0 = var9;
               var9 = var2;
               var2 = var11;
               var9 += var7;
               var23 = Unit.INSTANCE;
               ((<undefinedtype>)var21).J$0 = var11;
               ((<undefinedtype>)var21).J$1 = var0;
               ((<undefinedtype>)var21).L$0 = var4;
               ((<undefinedtype>)var21).J$2 = var9;
               ((<undefinedtype>)var21).J$3 = var7;
               ((<undefinedtype>)var21).label = 2;
               if (var4.send(var23, (Continuation)var21) == var22) {
                  return var22;
               }

               var11 = var7;
               var7 = var0;
               var0 = var2;
               var2 = var11;
               var24 = TimeSourceKt.getTimeSource();
               if (var24 != null) {
                  var25 = Boxing.boxLong(var24.nanoTime());
                  if (var25 != null) {
                     var11 = var25;
                  } else {
                     var11 = System.nanoTime();
                  }
               } else {
                  var11 = System.nanoTime();
               }
            } else {
               var2 = ((<undefinedtype>)var21).J$3;
               var9 = ((<undefinedtype>)var21).J$2;
               var4 = (SendChannel)((<undefinedtype>)var21).L$0;
               var7 = ((<undefinedtype>)var21).J$1;
               var0 = ((<undefinedtype>)var21).J$0;
               ResultKt.throwOnFailure(var20);
               var24 = TimeSourceKt.getTimeSource();
               if (var24 != null) {
                  var25 = Boxing.boxLong(var24.nanoTime());
                  if (var25 != null) {
                     var11 = var25;
                  } else {
                     var11 = System.nanoTime();
                  }
               } else {
                  var11 = System.nanoTime();
               }
            }
         } else {
            var9 = ((<undefinedtype>)var21).J$2;
            var4 = (SendChannel)((<undefinedtype>)var21).L$0;
            var0 = ((<undefinedtype>)var21).J$1;
            var2 = ((<undefinedtype>)var21).J$0;
            ResultKt.throwOnFailure(var20);
            var7 = EventLoop_commonKt.delayToNanos(var2);
            var9 += var7;
            var23 = Unit.INSTANCE;
            ((<undefinedtype>)var21).J$0 = var2;
            ((<undefinedtype>)var21).J$1 = var0;
            ((<undefinedtype>)var21).L$0 = var4;
            ((<undefinedtype>)var21).J$2 = var9;
            ((<undefinedtype>)var21).J$3 = var7;
            ((<undefinedtype>)var21).label = 2;
            if (var4.send(var23, (Continuation)var21) == var22) {
               return var22;
            }

            var11 = var7;
            var7 = var0;
            var0 = var2;
            var2 = var11;
            var24 = TimeSourceKt.getTimeSource();
            if (var24 != null) {
               var25 = Boxing.boxLong(var24.nanoTime());
               if (var25 != null) {
                  var11 = var25;
               } else {
                  var11 = System.nanoTime();
               }
            } else {
               var11 = System.nanoTime();
            }
         }
      } else {
         ResultKt.throwOnFailure(var20);
         var24 = TimeSourceKt.getTimeSource();
         if (var24 != null) {
            var25 = Boxing.boxLong(var24.nanoTime());
            if (var25 != null) {
               var9 = var25;
            } else {
               var9 = System.nanoTime();
            }
         } else {
            var9 = System.nanoTime();
         }

         var9 += EventLoop_commonKt.delayToNanos(var2);
         ((<undefinedtype>)var21).J$0 = var0;
         ((<undefinedtype>)var21).J$1 = var2;
         ((<undefinedtype>)var21).L$0 = var4;
         ((<undefinedtype>)var21).J$2 = var9;
         ((<undefinedtype>)var21).label = 1;
         if (DelayKt.delay(var2, (Continuation)var21) == var22) {
            return var22;
         }

         var2 = var0;
         var0 = var7;
         var7 = EventLoop_commonKt.delayToNanos(var2);
         var9 += var7;
         var23 = Unit.INSTANCE;
         ((<undefinedtype>)var21).J$0 = var2;
         ((<undefinedtype>)var21).J$1 = var0;
         ((<undefinedtype>)var21).L$0 = var4;
         ((<undefinedtype>)var21).J$2 = var9;
         ((<undefinedtype>)var21).J$3 = var7;
         ((<undefinedtype>)var21).label = 2;
         if (var4.send(var23, (Continuation)var21) == var22) {
            return var22;
         }

         var11 = var7;
         var7 = var0;
         var0 = var2;
         var2 = var11;
         var24 = TimeSourceKt.getTimeSource();
         if (var24 != null) {
            var25 = Boxing.boxLong(var24.nanoTime());
            if (var25 != null) {
               var11 = var25;
            } else {
               var11 = System.nanoTime();
            }
         } else {
            var11 = System.nanoTime();
         }
      }

      while(true) {
         long var13 = RangesKt.coerceAtLeast(var9 - var11, 0L);
         long var15;
         if (var13 == 0L) {
            if (var2 != 0L) {
               var15 = var2 - (var11 - var9) % var2;
               var9 = var11 + var15;
               long var17 = EventLoop_commonKt.delayNanosToMillis(var15);
               ((<undefinedtype>)var21).J$0 = var0;
               ((<undefinedtype>)var21).J$1 = var7;
               ((<undefinedtype>)var21).L$0 = var4;
               ((<undefinedtype>)var21).J$2 = var9;
               ((<undefinedtype>)var21).J$3 = var2;
               ((<undefinedtype>)var21).J$4 = var11;
               ((<undefinedtype>)var21).J$5 = var13;
               ((<undefinedtype>)var21).J$6 = var15;
               ((<undefinedtype>)var21).label = 3;
               if (DelayKt.delay(var17, (Continuation)var21) == var22) {
                  return var22;
               }

               var11 = var9;
               var9 = var7;
               var7 = var2;
               var2 = var11;
               var11 = var0;
               var0 = var9;
               var9 = var2;
               var2 = var11;
            } else {
               var15 = EventLoop_commonKt.delayNanosToMillis(var13);
               ((<undefinedtype>)var21).J$0 = var0;
               ((<undefinedtype>)var21).J$1 = var7;
               ((<undefinedtype>)var21).L$0 = var4;
               ((<undefinedtype>)var21).J$2 = var9;
               ((<undefinedtype>)var21).J$3 = var2;
               ((<undefinedtype>)var21).J$4 = var11;
               ((<undefinedtype>)var21).J$5 = var13;
               ((<undefinedtype>)var21).label = 4;
               if (DelayKt.delay(var15, (Continuation)var21) == var22) {
                  return var22;
               }

               var11 = var7;
               var7 = var2;
               var2 = var9;
               var9 = var11;
               var11 = var0;
               var0 = var9;
               var9 = var2;
               var2 = var11;
            }
         } else {
            var15 = EventLoop_commonKt.delayNanosToMillis(var13);
            ((<undefinedtype>)var21).J$0 = var0;
            ((<undefinedtype>)var21).J$1 = var7;
            ((<undefinedtype>)var21).L$0 = var4;
            ((<undefinedtype>)var21).J$2 = var9;
            ((<undefinedtype>)var21).J$3 = var2;
            ((<undefinedtype>)var21).J$4 = var11;
            ((<undefinedtype>)var21).J$5 = var13;
            ((<undefinedtype>)var21).label = 4;
            if (DelayKt.delay(var15, (Continuation)var21) == var22) {
               return var22;
            }

            var11 = var7;
            var7 = var2;
            var2 = var9;
            var9 = var11;
            var11 = var0;
            var0 = var9;
            var9 = var2;
            var2 = var11;
         }

         var9 += var7;
         var23 = Unit.INSTANCE;
         ((<undefinedtype>)var21).J$0 = var2;
         ((<undefinedtype>)var21).J$1 = var0;
         ((<undefinedtype>)var21).L$0 = var4;
         ((<undefinedtype>)var21).J$2 = var9;
         ((<undefinedtype>)var21).J$3 = var7;
         ((<undefinedtype>)var21).label = 2;
         if (var4.send(var23, (Continuation)var21) == var22) {
            return var22;
         }

         var11 = var7;
         var7 = var0;
         var0 = var2;
         var2 = var11;
         var24 = TimeSourceKt.getTimeSource();
         if (var24 != null) {
            var25 = Boxing.boxLong(var24.nanoTime());
            if (var25 != null) {
               var11 = var25;
            } else {
               var11 = System.nanoTime();
            }
         } else {
            var11 = System.nanoTime();
         }
      }
   }

   public static final ReceiveChannel ticker(long var0, long var2, CoroutineContext var4, TickerMode var5) {
      boolean var7 = true;
      boolean var6;
      if (var0 >= 0L) {
         var6 = true;
      } else {
         var6 = false;
      }

      if (var6) {
         if (var2 >= 0L) {
            var6 = var7;
         } else {
            var6 = false;
         }

         if (var6) {
            return ProduceKt.produce((CoroutineScope)GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(var4), 0, (Function2)(new Function2(var5, var0, var2, (Continuation)null) {
               final long $delayMillis;
               final long $initialDelayMillis;
               final TickerMode $mode;
               Object L$0;
               int label;
               private ProducerScope p$;

               {
                  this.$mode = var1;
                  this.$delayMillis = var2;
                  this.$initialDelayMillis = var4;
               }

               public final Continuation create(Object var1, Continuation var2) {
                  Function2 var3 = new <anonymous constructor>(this.$mode, this.$delayMillis, this.$initialDelayMillis, var2);
                  var3.p$ = (ProducerScope)var1;
                  return var3;
               }

               public final Object invoke(Object var1, Object var2) {
                  return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  if (var2 != 0) {
                     if (var2 != 1 && var2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     ProducerScope var10 = (ProducerScope)this.L$0;
                     ResultKt.throwOnFailure(var1);
                  } else {
                     ResultKt.throwOnFailure(var1);
                     ProducerScope var9 = this.p$;
                     TickerMode var8 = this.$mode;
                     var2 = TickerChannelsKt$WhenMappings.$EnumSwitchMapping$0[var8.ordinal()];
                     long var3;
                     long var5;
                     SendChannel var11;
                     if (var2 != 1) {
                        if (var2 == 2) {
                           var5 = this.$delayMillis;
                           var3 = this.$initialDelayMillis;
                           var11 = var9.getChannel();
                           this.L$0 = var9;
                           this.label = 2;
                           if (TickerChannelsKt.fixedDelayTicker(var5, var3, var11, this) == var7) {
                              return var7;
                           }
                        }
                     } else {
                        var5 = this.$delayMillis;
                        var3 = this.$initialDelayMillis;
                        var11 = var9.getChannel();
                        this.L$0 = var9;
                        this.label = 1;
                        if (TickerChannelsKt.fixedPeriodTicker(var5, var3, var11, this) == var7) {
                           return var7;
                        }
                     }
                  }

                  return Unit.INSTANCE;
               }
            }));
         } else {
            throw (Throwable)(new IllegalArgumentException(("Expected non-negative initial delay, but has " + var2 + " ms").toString()));
         }
      } else {
         throw (Throwable)(new IllegalArgumentException(("Expected non-negative delay, but has " + var0 + " ms").toString()));
      }
   }

   // $FF: synthetic method
   public static ReceiveChannel ticker$default(long var0, long var2, CoroutineContext var4, TickerMode var5, int var6, Object var7) {
      if ((var6 & 2) != 0) {
         var2 = var0;
      }

      if ((var6 & 4) != 0) {
         var4 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var6 & 8) != 0) {
         var5 = TickerMode.FIXED_PERIOD;
      }

      return ticker(var0, var2, var4, var5);
   }
}
