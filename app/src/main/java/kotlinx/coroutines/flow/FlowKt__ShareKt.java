package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.internal.ChannelFlow;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a+\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\b\f\u001aM\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\t2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u0002H\u0002H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001aA\u0010\u0019\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\t2\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u001bH\u0002¢\u0006\u0002\b\u001c\u001aS\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012-\u0010\u001e\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020 \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0!\u0012\u0006\u0012\u0004\u0018\u00010\"0\u001f¢\u0006\u0002\b#ø\u0001\u0000¢\u0006\u0002\u0010$\u001a6\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a/\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010&\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010(\u001a9\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u0002H\u0002¢\u0006\u0002\u0010)\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"},
   d2 = {"asSharedFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "T", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "asStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "configureSharing", "Lkotlinx/coroutines/flow/SharingConfig;", "Lkotlinx/coroutines/flow/Flow;", "replay", "", "configureSharing$FlowKt__ShareKt", "launchSharing", "", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "upstream", "shared", "started", "Lkotlinx/coroutines/flow/SharingStarted;", "initialValue", "launchSharing$FlowKt__ShareKt", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlinx/coroutines/flow/SharingStarted;Ljava/lang/Object;)V", "launchSharingDeferred", "result", "Lkotlinx/coroutines/CompletableDeferred;", "launchSharingDeferred$FlowKt__ShareKt", "onSubscription", "action", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/SharedFlow;", "shareIn", "scope", "stateIn", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/flow/SharingStarted;Ljava/lang/Object;)Lkotlinx/coroutines/flow/StateFlow;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__ShareKt {
   public static final SharedFlow asSharedFlow(MutableSharedFlow var0) {
      return (SharedFlow)(new ReadonlySharedFlow((SharedFlow)var0));
   }

   public static final StateFlow asStateFlow(MutableStateFlow var0) {
      return (StateFlow)(new ReadonlyStateFlow((StateFlow)var0));
   }

   private static final SharingConfig configureSharing$FlowKt__ShareKt(Flow var0, int var1) {
      boolean var5 = DebugKt.getASSERTIONS_ENABLED();
      byte var3 = 1;
      if (var5) {
         boolean var2;
         if (var1 >= 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            throw (Throwable)(new AssertionError());
         }
      }

      int var8 = RangesKt.coerceAtLeast(var1, Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core()) - var1;
      if (var0 instanceof ChannelFlow) {
         ChannelFlow var6 = (ChannelFlow)var0;
         Flow var7 = var6.dropChannelOperators();
         if (var7 != null) {
            int var4 = var6.capacity;
            if (var4 != -3 && var4 != -2 && var4 != 0) {
               var1 = var6.capacity;
            } else {
               if (var6.onBufferOverflow == BufferOverflow.SUSPEND) {
                  if (var6.capacity != 0) {
                     var1 = var8;
                     return new SharingConfig(var7, var1, var6.onBufferOverflow, var6.context);
                  }
               } else if (var1 == 0) {
                  var1 = var3;
                  return new SharingConfig(var7, var1, var6.onBufferOverflow, var6.context);
               }

               var1 = 0;
            }

            return new SharingConfig(var7, var1, var6.onBufferOverflow, var6.context);
         }
      }

      return new SharingConfig(var0, var8, BufferOverflow.SUSPEND, (CoroutineContext)EmptyCoroutineContext.INSTANCE);
   }

   private static final void launchSharing$FlowKt__ShareKt(CoroutineScope var0, CoroutineContext var1, Flow var2, MutableSharedFlow var3, SharingStarted var4, Object var5) {
      BuildersKt.launch$default(var0, var1, (CoroutineStart)null, (Function2)(new Function2(var4, var2, var3, var5, (Continuation)null) {
         final Object $initialValue;
         final MutableSharedFlow $shared;
         final SharingStarted $started;
         final Flow $upstream;
         Object L$0;
         int label;
         private CoroutineScope p$;

         {
            this.$started = var1;
            this.$upstream = var2;
            this.$shared = var3;
            this.$initialValue = var4;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$started, this.$upstream, this.$shared, this.$initialValue, var2);
            var3.p$ = (CoroutineScope)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            Flow var5;
            CoroutineScope var6;
            if (var2 != 0) {
               label57: {
                  CoroutineScope var8;
                  if (var2 != 1) {
                     if (var2 == 2) {
                        var8 = (CoroutineScope)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        var6 = var8;
                        break label57;
                     }

                     if (var2 != 3 && var2 != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }
                  }

                  var8 = (CoroutineScope)this.L$0;
                  ResultKt.throwOnFailure(var1);
                  return Unit.INSTANCE;
               }
            } else {
               ResultKt.throwOnFailure(var1);
               var6 = this.p$;
               if (this.$started == SharingStarted.Companion.getEagerly()) {
                  Flow var7 = this.$upstream;
                  FlowCollector var10 = (FlowCollector)this.$shared;
                  this.L$0 = var6;
                  this.label = 1;
                  if (var7.collect(var10, this) == var4) {
                     return var4;
                  }

                  return Unit.INSTANCE;
               }

               Function2 var3;
               if (this.$started != SharingStarted.Companion.getLazily()) {
                  var5 = FlowKt.distinctUntilChanged(this.$started.command(this.$shared.getSubscriptionCount()));
                  var3 = (Function2)(new Function2(this, (Continuation)null) {
                     Object L$0;
                     int label;
                     private SharingCommand p$0;
                     final <undefinedtype> this$0;

                     {
                        this.this$0 = var1;
                     }

                     public final Continuation create(Object var1, Continuation var2) {
                        Function2 var3 = new <anonymous constructor>(this.this$0, var2);
                        var3.p$0 = (SharingCommand)var1;
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

                           SharingCommand var7 = (SharingCommand)this.L$0;
                           ResultKt.throwOnFailure(var1);
                        } else {
                           ResultKt.throwOnFailure(var1);
                           SharingCommand var4 = this.p$0;
                           var2 = FlowKt$WhenMappings.$EnumSwitchMapping$0[var4.ordinal()];
                           if (var2 != 1) {
                              if (var2 == 3) {
                                 if (this.this$0.$initialValue == SharedFlowKt.NO_VALUE) {
                                    this.this$0.$shared.resetReplayCache();
                                 } else {
                                    this.this$0.$shared.tryEmit(this.this$0.$initialValue);
                                 }
                              }
                           } else {
                              Flow var5 = this.this$0.$upstream;
                              FlowCollector var6 = (FlowCollector)this.this$0.$shared;
                              this.L$0 = var4;
                              this.label = 1;
                              if (var5.collect(var6, this) == var3) {
                                 return var3;
                              }
                           }
                        }

                        return Unit.INSTANCE;
                     }
                  });
                  this.L$0 = var6;
                  this.label = 4;
                  if (FlowKt.collectLatest(var5, var3, this) == var4) {
                     return var4;
                  }

                  return Unit.INSTANCE;
               }

               var5 = (Flow)this.$shared.getSubscriptionCount();
               var3 = (Function2)(new Function2((Continuation)null) {
                  int label;
                  private int p$0;

                  public final Continuation create(Object var1, Continuation var2) {
                     Function2 var4 = new <anonymous constructor>(var2);
                     Number var3 = (Number)var1;
                     var3.intValue();
                     var4.p$0 = var3.intValue();
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2) {
                     return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     if (this.label == 0) {
                        ResultKt.throwOnFailure(var1);
                        boolean var2;
                        if (this.p$0 > 0) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        return Boxing.boxBoolean(var2);
                     } else {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }
                  }
               });
               this.L$0 = var6;
               this.label = 2;
               if (FlowKt.first(var5, var3, this) == var4) {
                  return var4;
               }
            }

            var5 = this.$upstream;
            FlowCollector var9 = (FlowCollector)this.$shared;
            this.L$0 = var6;
            this.label = 3;
            if (var5.collect(var9, this) == var4) {
               return var4;
            } else {
               return Unit.INSTANCE;
            }
         }
      }), 2, (Object)null);
   }

   private static final void launchSharingDeferred$FlowKt__ShareKt(CoroutineScope var0, CoroutineContext var1, Flow var2, CompletableDeferred var3) {
      BuildersKt.launch$default(var0, var1, (CoroutineStart)null, (Function2)(new Function2(var2, var3, (Continuation)null) {
         final CompletableDeferred $result;
         final Flow $upstream;
         Object L$0;
         Object L$1;
         Object L$2;
         int label;
         private CoroutineScope p$;

         {
            this.$upstream = var1;
            this.$result = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$upstream, this.$result, var2);
            var3.p$ = (CoroutineScope)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            Throwable var10000;
            boolean var10001;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Flow var15 = (Flow)this.L$2;
               Ref.ObjectRef var16 = (Ref.ObjectRef)this.L$1;
               CoroutineScope var17 = (CoroutineScope)this.L$0;

               label95:
               try {
                  ResultKt.throwOnFailure(var1);
                  return Unit.INSTANCE;
               } catch (Throwable var11) {
                  var10000 = var11;
                  var10001 = false;
                  break label95;
               }
            } else {
               label115: {
                  ResultKt.throwOnFailure(var1);
                  CoroutineScope var13 = this.p$;

                  try {
                     Ref.ObjectRef var4 = new Ref.ObjectRef();
                     MutableStateFlow var5 = (MutableStateFlow)null;
                     var4.element = null;
                     Flow var18 = this.$upstream;
                     FlowCollector var6 = new FlowCollector(this, var13, var4) {
                        final Ref.ObjectRef $state$inlined;
                        final CoroutineScope $this_launch$inlined;
                        final <undefinedtype> this$0;

                        public {
                           this.this$0 = var1;
                           this.$this_launch$inlined = var2;
                           this.$state$inlined = var3;
                        }

                        public Object emit(Object var1, Continuation var2) {
                           MutableStateFlow var6 = (MutableStateFlow)this.$state$inlined.element;
                           Unit var4;
                           if (var6 != null) {
                              var6.setValue(var1);
                              var4 = Unit.INSTANCE;
                           } else {
                              Ref.ObjectRef var7 = this.$state$inlined;
                              MutableStateFlow var5 = StateFlowKt.MutableStateFlow(var1);
                              this.this$0.$result.complete(FlowKt.asStateFlow(var5));
                              Unit var3 = Unit.INSTANCE;
                              var7.element = var5;
                              var4 = Unit.INSTANCE;
                           }

                           return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
                        }
                     };
                     var6 = (FlowCollector)var6;
                     this.L$0 = var13;
                     this.L$1 = var4;
                     this.L$2 = var18;
                     this.label = 1;
                     var1 = var18.collect(var6, this);
                  } catch (Throwable var12) {
                     var10000 = var12;
                     var10001 = false;
                     break label115;
                  }

                  if (var1 == var3) {
                     return var3;
                  }

                  return Unit.INSTANCE;
               }
            }

            Throwable var14 = var10000;
            this.$result.completeExceptionally(var14);
            throw var14;
         }
      }), 2, (Object)null);
   }

   public static final SharedFlow onSubscription(SharedFlow var0, Function2 var1) {
      return (SharedFlow)(new SubscribedSharedFlow(var0, var1));
   }

   public static final SharedFlow shareIn(Flow var0, CoroutineScope var1, SharingStarted var2, int var3) {
      SharingConfig var5 = configureSharing$FlowKt__ShareKt(var0, var3);
      MutableSharedFlow var4 = SharedFlowKt.MutableSharedFlow(var3, var5.extraBufferCapacity, var5.onBufferOverflow);
      launchSharing$FlowKt__ShareKt(var1, var5.context, var5.upstream, var4, var2, (Object)SharedFlowKt.NO_VALUE);
      return FlowKt.asSharedFlow(var4);
   }

   // $FF: synthetic method
   public static SharedFlow shareIn$default(Flow var0, CoroutineScope var1, SharingStarted var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return FlowKt.shareIn(var0, var1, var2, var3);
   }

   public static final Object stateIn(Flow var0, CoroutineScope var1, Continuation var2) {
      SharingConfig var4 = configureSharing$FlowKt__ShareKt(var0, 1);
      CompletableDeferred var3 = CompletableDeferredKt.CompletableDeferred$default((Job)null, 1, (Object)null);
      launchSharingDeferred$FlowKt__ShareKt(var1, var4.context, var4.upstream, var3);
      return var3.await(var2);
   }

   public static final StateFlow stateIn(Flow var0, CoroutineScope var1, SharingStarted var2, Object var3) {
      SharingConfig var5 = configureSharing$FlowKt__ShareKt(var0, 1);
      MutableStateFlow var4 = StateFlowKt.MutableStateFlow(var3);
      launchSharing$FlowKt__ShareKt(var1, var5.context, var5.upstream, (MutableSharedFlow)var4, var2, var3);
      return FlowKt.asStateFlow(var4);
   }
}
