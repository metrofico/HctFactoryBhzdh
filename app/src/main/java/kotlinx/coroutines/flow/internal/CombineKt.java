package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001an\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00012(\u0010\u0007\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u0090\u0001\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u00010\u00112\u0016\u0010\u0012\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u0001H\u000e\u0018\u00010\u00110\u001329\u0010\u0007\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\u0002\b\u0014H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u0015*\u001c\b\u0002\u0010\u0016\"\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00172\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"},
   d2 = {"zipImpl", "Lkotlinx/coroutines/flow/Flow;", "R", "T1", "T2", "flow", "flow2", "transform", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "combineInternal", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "flows", "", "arrayFactory", "Lkotlin/Function0;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/FlowCollector;[Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Update", "Lkotlin/collections/IndexedValue;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CombineKt {
   public static final Object combineInternal(FlowCollector var0, Flow[] var1, Function0 var2, Function3 var3, Continuation var4) {
      Object var5 = FlowCoroutineKt.flowScope((Function2)(new Function2(var0, var1, var2, var3, (Continuation)null) {
         final Function0 $arrayFactory;
         final Flow[] $flows;
         final FlowCollector $this_combineInternal;
         final Function3 $transform;
         byte B$0;
         int I$0;
         int I$1;
         int I$2;
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
         Object L$4;
         Object L$5;
         Object L$6;
         int label;
         private CoroutineScope p$;

         {
            this.$this_combineInternal = var1;
            this.$flows = var2;
            this.$arrayFactory = var3;
            this.$transform = var4;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$this_combineInternal, this.$flows, this.$arrayFactory, this.$transform, var2);
            var3.p$ = (CoroutineScope)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = this.label;
            int var5 = 0;
            byte var2;
            int var4;
            int var6;
            AtomicInteger var9;
            Object[] var10;
            Object[] var12;
            byte[] var13;
            CoroutineScope var18;
            <undefinedtype> var20;
            Channel var23;
            byte[] var24;
            byte[] var26;
            Object var27;
            Object var28;
            ReceiveChannel var31;
            Object var32;
            IndexedValue var33;
            if (var3 != 0) {
               if (var3 != 1) {
                  Object[] var8;
                  IndexedValue var22;
                  if (var3 != 2) {
                     if (var3 != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     var8 = (Object[])this.L$6;
                     var22 = (IndexedValue)this.L$5;
                     var5 = this.I$2;
                     var13 = (byte[])this.L$4;
                     var4 = this.I$1;
                     var9 = (AtomicInteger)this.L$3;
                     var23 = (Channel)this.L$2;
                     var12 = (Object[])this.L$1;
                     var3 = this.I$0;
                     CoroutineScope var11 = (CoroutineScope)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var18 = var11;
                     var27 = var7;
                     var6 = var3;
                     var26 = var13;
                     var4 = var4;
                     var20 = this;
                     var10 = var12;
                  } else {
                     var8 = (Object[])this.L$6;
                     var22 = (IndexedValue)this.L$5;
                     var4 = this.I$2;
                     var24 = (byte[])this.L$4;
                     var3 = this.I$1;
                     var9 = (AtomicInteger)this.L$3;
                     var23 = (Channel)this.L$2;
                     Object[] var30 = (Object[])this.L$1;
                     var5 = this.I$0;
                     CoroutineScope var14 = (CoroutineScope)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var18 = var14;
                     var6 = var5;
                     var27 = var7;
                     var5 = var4;
                     var26 = var24;
                     var4 = var3;
                     var20 = this;
                     var10 = var30;
                  }

                  var2 = (byte)(var5 + 1);
                  var31 = (ReceiveChannel)var23;
                  var20.L$0 = var18;
                  var20.I$0 = var6;
                  var20.L$1 = var10;
                  var20.L$2 = var23;
                  var20.L$3 = var9;
                  var20.I$1 = var4;
                  var20.L$4 = var26;
                  var20.B$0 = var2;
                  var20.label = 1;
                  var32 = ChannelsKt.receiveOrNull(var31, var20);
                  if (var32 == var27) {
                     return var27;
                  }

                  var24 = var26;
                  var3 = var6;
                  var28 = var27;
                  var12 = var10;
                  var33 = (IndexedValue)var32;
                  if (var33 == null) {
                     return Unit.INSTANCE;
                  }

                  var5 = var4;
                  var6 = var33.getIndex();
                  var32 = var10[var6];
                  var10[var6] = var33.getValue();
                  var4 = var4;
                  if (var32 == NullSurrogateKt.UNINITIALIZED) {
                     var4 = var5 - 1;
                  }
               } else {
                  var2 = this.B$0;
                  var24 = (byte[])this.L$4;
                  var4 = this.I$1;
                  var9 = (AtomicInteger)this.L$3;
                  Channel var34 = (Channel)this.L$2;
                  var12 = (Object[])this.L$1;
                  var3 = this.I$0;
                  CoroutineScope var35 = (CoroutineScope)this.L$0;
                  ResultKt.throwOnFailure(var1);
                  var28 = var7;
                  var20 = this;
                  var23 = var34;
                  var18 = var35;
                  var33 = (IndexedValue)var1;
                  if (var33 == null) {
                     return Unit.INSTANCE;
                  }

                  var5 = var4;
                  var6 = var33.getIndex();
                  var32 = var12[var6];
                  var12[var6] = var33.getValue();
                  var4 = var4;
                  if (var32 == NullSurrogateKt.UNINITIALIZED) {
                     var4 = var5 - 1;
                  }
               }
            } else {
               ResultKt.throwOnFailure(var1);
               var18 = this.p$;
               var4 = this.$flows.length;
               if (var4 == 0) {
                  return Unit.INSTANCE;
               }

               var10 = new Object[var4];
               ArraysKt.fill$default(var10, NullSurrogateKt.UNINITIALIZED, 0, 0, 6, (Object)null);
               var23 = ChannelKt.Channel$default(var4, (BufferOverflow)null, (Function1)null, 6, (Object)null);
               var9 = new AtomicInteger(var4);

               for(var3 = 0; var3 < var4; ++var3) {
                  BuildersKt.launch$default(var18, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(this, var3, var23, var9, (Continuation)null) {
                     final int $i;
                     final AtomicInteger $nonClosed;
                     final Channel $resultChannel;
                     Object L$0;
                     Object L$1;
                     int label;
                     private CoroutineScope p$;
                     final <undefinedtype> this$0;

                     {
                        this.this$0 = var1;
                        this.$i = var2;
                        this.$resultChannel = var3;
                        this.$nonClosed = var4;
                     }

                     public final Continuation create(Object var1, Continuation var2) {
                        Function2 var3 = new <anonymous constructor>(this.this$0, this.$i, this.$resultChannel, this.$nonClosed, var2);
                        var3.p$ = (CoroutineScope)var1;
                        return var3;
                     }

                     public final Object invoke(Object var1, Object var2) {
                        return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
                     }

                     public final Object invokeSuspend(Object var1) {
                        Throwable var10000;
                        label130: {
                           Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                           int var2 = this.label;
                           boolean var10001;
                           if (var2 != 0) {
                              if (var2 != 1) {
                                 throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                              }

                              Flow var14 = (Flow)this.L$1;
                              CoroutineScope var15 = (CoroutineScope)this.L$0;

                              try {
                                 ResultKt.throwOnFailure(var1);
                              } catch (Throwable var11) {
                                 var10000 = var11;
                                 var10001 = false;
                                 break label130;
                              }
                           } else {
                              ResultKt.throwOnFailure(var1);
                              CoroutineScope var12 = this.p$;

                              try {
                                 Flow var4 = this.this$0.$flows[this.$i];
                                 FlowCollector var5 = new FlowCollector(this) {
                                    final <undefinedtype> this$0;

                                    public {
                                       this.this$0 = var1;
                                    }

                                    public Object emit(Object var1, Continuation var2) {
                                       Object var10;
                                       label34: {
                                          if (var2 instanceof <undefinedtype>) {
                                             <undefinedtype> var4 = (<undefinedtype>)var2;
                                             if ((var4.label & Integer.MIN_VALUE) != 0) {
                                                var4.label += Integer.MIN_VALUE;
                                                var10 = var4;
                                                break label34;
                                             }
                                          }

                                          var10 = new ContinuationImpl(this, var2) {
                                             Object L$0;
                                             Object L$1;
                                             Object L$2;
                                             Object L$3;
                                             int label;
                                             Object result;
                                             final <undefinedtype> this$0;

                                             public {
                                                this.this$0 = var1;
                                             }

                                             public final Object invokeSuspend(Object var1) {
                                                this.result = var1;
                                                this.label |= Integer.MIN_VALUE;
                                                return this.this$0.emit((Object)null, this);
                                             }
                                          };
                                       }

                                       Object var6 = ((<undefinedtype>)var10).result;
                                       Object var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                       int var3 = ((<undefinedtype>)var10).label;
                                       Object var5;
                                       Continuation var7;
                                       <undefinedtype> var12;
                                       if (var3 != 0) {
                                          if (var3 != 1) {
                                             if (var3 != 2) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                             }

                                             var1 = ((<undefinedtype>)var10).L$3;
                                             Continuation var9 = (Continuation)((<undefinedtype>)var10).L$2;
                                             var1 = ((<undefinedtype>)var10).L$1;
                                             <undefinedtype> var11 = (<undefinedtype>)((<undefinedtype>)var10).L$0;
                                             ResultKt.throwOnFailure(var6);
                                             return Unit.INSTANCE;
                                          }

                                          var1 = ((<undefinedtype>)var10).L$3;
                                          var7 = (Continuation)((<undefinedtype>)var10).L$2;
                                          var5 = ((<undefinedtype>)var10).L$1;
                                          var12 = (<undefinedtype>)((<undefinedtype>)var10).L$0;
                                          ResultKt.throwOnFailure(var6);
                                          var6 = var1;
                                       } else {
                                          ResultKt.throwOnFailure(var6);
                                          var7 = (Continuation)var10;
                                          Channel var13 = this.this$0.$resultChannel;
                                          IndexedValue var14 = new IndexedValue(this.this$0.$i, var1);
                                          ((<undefinedtype>)var10).L$0 = this;
                                          ((<undefinedtype>)var10).L$1 = var1;
                                          ((<undefinedtype>)var10).L$2 = var7;
                                          ((<undefinedtype>)var10).L$3 = var1;
                                          ((<undefinedtype>)var10).label = 1;
                                          if (var13.send(var14, (Continuation)var10) == var8) {
                                             return var8;
                                          }

                                          var12 = this;
                                          var6 = var1;
                                          var5 = var1;
                                       }

                                       ((<undefinedtype>)var10).L$0 = var12;
                                       ((<undefinedtype>)var10).L$1 = var5;
                                       ((<undefinedtype>)var10).L$2 = var7;
                                       ((<undefinedtype>)var10).L$3 = var6;
                                       ((<undefinedtype>)var10).label = 2;
                                       if (YieldKt.yield((Continuation)var10) == var8) {
                                          return var8;
                                       } else {
                                          return Unit.INSTANCE;
                                       }
                                    }
                                 };
                                 var5 = (FlowCollector)var5;
                                 this.L$0 = var12;
                                 this.L$1 = var4;
                                 this.label = 1;
                                 var1 = var4.collect(var5, this);
                              } catch (Throwable var10) {
                                 var10000 = var10;
                                 var10001 = false;
                                 break label130;
                              }

                              if (var1 == var3) {
                                 return var3;
                              }
                           }

                           if (this.$nonClosed.decrementAndGet() == 0) {
                              SendChannel.DefaultImpls.close$default((SendChannel)this.$resultChannel, (Throwable)null, 1, (Object)null);
                           }

                           return Unit.INSTANCE;
                        }

                        Throwable var13 = var10000;
                        if (this.$nonClosed.decrementAndGet() == 0) {
                           SendChannel.DefaultImpls.close$default((SendChannel)this.$resultChannel, (Throwable)null, 1, (Object)null);
                        }

                        throw var13;
                     }
                  }), 3, (Object)null);
               }

               var13 = new byte[var4];
               var20 = this;
               var2 = (byte)(var5 + 1);
               var31 = (ReceiveChannel)var23;
               this.L$0 = var18;
               this.I$0 = var4;
               this.L$1 = var10;
               this.L$2 = var23;
               this.L$3 = var9;
               this.I$1 = var4;
               this.L$4 = var13;
               this.B$0 = var2;
               this.label = 1;
               var32 = ChannelsKt.receiveOrNull(var31, this);
               if (var32 == var7) {
                  return var7;
               }

               var24 = var13;
               var3 = var4;
               var28 = var7;
               var12 = var10;
               var33 = (IndexedValue)var32;
               if (var33 == null) {
                  return Unit.INSTANCE;
               }

               var5 = var4;
               var6 = var33.getIndex();
               var32 = var10[var6];
               var10[var6] = var33.getValue();
               var4 = var4;
               if (var32 == NullSurrogateKt.UNINITIALIZED) {
                  var4 = var5 - 1;
               }
            }

            while(true) {
               while(true) {
                  if (var24[var6] != var2) {
                     var24[var6] = var2;
                     IndexedValue var38 = (IndexedValue)var23.poll();
                     if (var38 != null) {
                        var33 = var38;
                        var5 = var4;
                        var6 = var38.getIndex();
                        var32 = var12[var6];
                        var12[var6] = var38.getValue();
                        var4 = var4;
                        if (var32 == NullSurrogateKt.UNINITIALIZED) {
                           var4 = var5 - 1;
                        }
                        continue;
                     }
                  }

                  byte var21;
                  if (var4 == 0) {
                     Object[] var15 = (Object[])var20.$arrayFactory.invoke();
                     if (var15 == null) {
                        Function3 var16 = var20.$transform;
                        FlowCollector var39 = var20.$this_combineInternal;
                        if (var12 == null) {
                           throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                        }

                        var20.L$0 = var18;
                        var20.I$0 = var3;
                        var20.L$1 = var12;
                        var20.L$2 = var23;
                        var20.L$3 = var9;
                        var20.I$1 = var4;
                        var20.L$4 = var24;
                        var20.I$2 = var2;
                        var20.L$5 = var33;
                        var20.L$6 = var15;
                        var20.label = 2;
                        if (var16.invoke(var39, var12, var20) == var28) {
                           return var28;
                        }

                        var6 = var3;
                        var27 = var28;
                        var21 = var2;
                        var26 = var24;
                        var4 = var4;
                        var20 = var20;
                        var10 = var12;
                     } else {
                        if (var12 == null) {
                           throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T?>");
                        }

                        ArraysKt.copyInto$default(var12, var15, 0, 0, 0, 14, (Object)null);
                        Function3 var17 = var20.$transform;
                        FlowCollector var37 = var20.$this_combineInternal;
                        var20.L$0 = var18;
                        var20.I$0 = var3;
                        var20.L$1 = var12;
                        var20.L$2 = var23;
                        var20.L$3 = var9;
                        var20.I$1 = var4;
                        var20.L$4 = var24;
                        var20.I$2 = var2;
                        var20.L$5 = var33;
                        var20.L$6 = var15;
                        var20.label = 3;
                        var21 = var2;
                        if (var17.invoke(var37, var15, var20) == var28) {
                           return var28;
                        }

                        var27 = var28;
                        var6 = var3;
                        var26 = var24;
                        var4 = var4;
                        var20 = var20;
                        var10 = var12;
                     }
                  } else {
                     var21 = var2;
                     var27 = var28;
                     var6 = var3;
                     var26 = var24;
                     var4 = var4;
                     var20 = var20;
                     var10 = var12;
                  }

                  var2 = (byte)(var21 + 1);
                  var31 = (ReceiveChannel)var23;
                  var20.L$0 = var18;
                  var20.I$0 = var6;
                  var20.L$1 = var10;
                  var20.L$2 = var23;
                  var20.L$3 = var9;
                  var20.I$1 = var4;
                  var20.L$4 = var26;
                  var20.B$0 = var2;
                  var20.label = 1;
                  var32 = ChannelsKt.receiveOrNull(var31, var20);
                  if (var32 == var27) {
                     return var27;
                  }

                  var24 = var26;
                  var3 = var6;
                  var28 = var27;
                  var12 = var10;
                  var33 = (IndexedValue)var32;
                  if (var33 == null) {
                     return Unit.INSTANCE;
                  }

                  var5 = var4;
                  var6 = var33.getIndex();
                  var32 = var10[var6];
                  var10[var6] = var33.getValue();
                  var4 = var4;
                  if (var32 == NullSurrogateKt.UNINITIALIZED) {
                     var4 = var5 - 1;
                  }
               }
            }
         }
      }), var4);
      return var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var5 : Unit.INSTANCE;
   }

   public static final Flow zipImpl(Flow var0, Flow var1, Function3 var2) {
      return (Flow)(new Flow(var1, var0, var2) {
         final Flow $flow$inlined;
         final Flow $flow2$inlined;
         final Function3 $transform$inlined;

         public {
            this.$flow2$inlined = var1;
            this.$flow$inlined = var2;
            this.$transform$inlined = var3;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = CoroutineScopeKt.coroutineScope((Function2)(new Function2(var1, (Continuation)null, this) {
               final FlowCollector $this_unsafeFlow;
               Object L$0;
               Object L$1;
               Object L$2;
               Object L$3;
               Object L$4;
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
                  Throwable var32;
                  ReceiveChannel var37;
                  label321: {
                     Throwable var10000;
                     label327: {
                        ReceiveChannel var31;
                        label315: {
                           ReceiveChannel var4;
                           label316: {
                              Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                              int var2 = this.label;
                              AbortFlowException var5;
                              boolean var10001;
                              if (var2 != 0) {
                                 if (var2 != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                 }

                                 CoroutineContext var33 = (CoroutineContext)this.L$3;
                                 CompletableJob var34 = (CompletableJob)this.L$2;
                                 var4 = (ReceiveChannel)this.L$1;
                                 CoroutineScope var35 = (CoroutineScope)this.L$0;
                                 var37 = var4;

                                 try {
                                    try {
                                       ResultKt.throwOnFailure(var1);
                                       break label316;
                                    } catch (AbortFlowException var27) {
                                       var5 = var27;
                                    }
                                 } catch (Throwable var28) {
                                    var10000 = var28;
                                    var10001 = false;
                                    break label327;
                                 }

                                 var31 = var4;
                              } else {
                                 label318: {
                                    ResultKt.throwOnFailure(var1);
                                    CoroutineScope var36 = this.p$;
                                    var31 = ProduceKt.produce$default(var36, (CoroutineContext)null, 0, (Function2)(new Function2(this, (Continuation)null) {
                                       Object L$0;
                                       Object L$1;
                                       int label;
                                       private ProducerScope p$;
                                       final <undefinedtype> this$0;

                                       {
                                          this.this$0 = var1;
                                       }

                                       public final Continuation create(Object var1, Continuation var2) {
                                          Function2 var3 = new <anonymous constructor>(this.this$0, var2);
                                          var3.p$ = (ProducerScope)var1;
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

                                             Flow var7 = (Flow)this.L$1;
                                             ProducerScope var8 = (ProducerScope)this.L$0;
                                             ResultKt.throwOnFailure(var1);
                                          } else {
                                             ResultKt.throwOnFailure(var1);
                                             ProducerScope var4 = this.p$;
                                             Flow var6 = this.this$0.this$0.$flow2$inlined;
                                             FlowCollector var5 = (FlowCollector)(new FlowCollector(var4) {
                                                final ProducerScope $this_produce$inlined;

                                                public {
                                                   this.$this_produce$inlined = var1;
                                                }

                                                public Object emit(Object var1, Continuation var2) {
                                                   SendChannel var3 = this.$this_produce$inlined.getChannel();
                                                   if (var1 == null) {
                                                      var1 = NullSurrogateKt.NULL;
                                                   }

                                                   var1 = var3.send(var1, var2);
                                                   return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
                                                }
                                             });
                                             this.L$0 = var4;
                                             this.L$1 = var6;
                                             this.label = 1;
                                             if (var6.collect(var5, this) == var3) {
                                                return var3;
                                             }
                                          }

                                          return Unit.INSTANCE;
                                       }
                                    }), 3, (Object)null);
                                    CompletableJob var9 = JobKt.Job$default((Job)null, 1, (Object)null);
                                    if (var31 == null) {
                                       throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.SendChannel<*>");
                                    }

                                    ((SendChannel)var31).invokeOnClose((Function1)(new Function1(this, var9) {
                                       final CompletableJob $collectJob;
                                       final <undefinedtype> this$0;

                                       {
                                          this.this$0 = var1;
                                          this.$collectJob = var2;
                                       }

                                       public final void invoke(Throwable var1) {
                                          if (this.$collectJob.isActive()) {
                                             this.$collectJob.cancel((CancellationException)(new AbortFlowException(this.this$0.$this_unsafeFlow)));
                                          }

                                       }
                                    }));

                                    Object var38;
                                    try {
                                       try {
                                          CoroutineContext var39 = var36.getCoroutineContext();
                                          Object var8 = ThreadContextKt.threadContextElements(var39);
                                          CoroutineContext var7 = var36.getCoroutineContext().plus((CoroutineContext)var9);
                                          Unit var6 = Unit.INSTANCE;
                                          Function2 var10 = new Function2(this, var39, var8, var31, (Continuation)null) {
                                             final Object $cnt;
                                             final CoroutineContext $scopeContext;
                                             final ReceiveChannel $second;
                                             Object L$0;
                                             Object L$1;
                                             int label;
                                             private Unit p$0;
                                             final <undefinedtype> this$0;

                                             {
                                                this.this$0 = var1;
                                                this.$scopeContext = var2;
                                                this.$cnt = var3;
                                                this.$second = var4;
                                             }

                                             public final Continuation create(Object var1, Continuation var2) {
                                                Function2 var3 = new <anonymous constructor>(this.this$0, this.$scopeContext, this.$cnt, this.$second, var2);
                                                var3.p$0 = (Unit)var1;
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

                                                   Flow var7 = (Flow)this.L$1;
                                                   Unit var8 = (Unit)this.L$0;
                                                   ResultKt.throwOnFailure(var1);
                                                } else {
                                                   ResultKt.throwOnFailure(var1);
                                                   Unit var5 = this.p$0;
                                                   Flow var4 = this.this$0.this$0.$flow$inlined;
                                                   FlowCollector var6 = (FlowCollector)(new FlowCollector(this) {
                                                      final <undefinedtype> this$0;

                                                      public {
                                                         this.this$0 = var1;
                                                      }

                                                      public Object emit(Object var1, Continuation var2) {
                                                         var1 = ChannelFlowKt.withContextUndispatched(this.this$0.$scopeContext, Unit.INSTANCE, this.this$0.$cnt, (Function2)(new Function2(var1, (Continuation)null, this) {
                                                            final Object $value;
                                                            Object L$0;
                                                            Object L$1;
                                                            Object L$2;
                                                            int label;
                                                            private Unit p$0;
                                                            final <undefinedtype> this$0;

                                                            {
                                                               this.$value = var1;
                                                               this.this$0 = var3;
                                                            }

                                                            public final Continuation create(Object var1, Continuation var2) {
                                                               Function2 var3 = new <anonymous constructor>(this.$value, var2, this.this$0);
                                                               var3.p$0 = (Unit)var1;
                                                               return var3;
                                                            }

                                                            public final Object invoke(Object var1, Object var2) {
                                                               return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
                                                            }

                                                            public final Object invokeSuspend(Object var1) {
                                                               Object var4;
                                                               Unit var5;
                                                               Object var6;
                                                               Object var8;
                                                               FlowCollector var10;
                                                               label45: {
                                                                  var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                  int var2 = this.label;
                                                                  Unit var3;
                                                                  if (var2 != 0) {
                                                                     if (var2 != 1) {
                                                                        if (var2 != 2) {
                                                                           if (var2 != 3) {
                                                                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                           }

                                                                           var3 = (Unit)this.L$0;
                                                                           ResultKt.throwOnFailure(var1);
                                                                           return Unit.INSTANCE;
                                                                        }

                                                                        var10 = (FlowCollector)this.L$2;
                                                                        var4 = this.L$1;
                                                                        var5 = (Unit)this.L$0;
                                                                        ResultKt.throwOnFailure(var1);
                                                                        var6 = var1;
                                                                        break label45;
                                                                     }

                                                                     var3 = (Unit)this.L$0;
                                                                     ResultKt.throwOnFailure(var1);
                                                                  } else {
                                                                     ResultKt.throwOnFailure(var1);
                                                                     var3 = this.p$0;
                                                                     ReceiveChannel var9 = this.this$0.this$0.$second;
                                                                     this.L$0 = var3;
                                                                     this.label = 1;
                                                                     var1 = ChannelsKt.receiveOrNull(var9, this);
                                                                     if (var1 == var8) {
                                                                        return var8;
                                                                     }
                                                                  }

                                                                  if (var1 == null) {
                                                                     throw (Throwable)(new AbortFlowException(this.this$0.this$0.this$0.$this_unsafeFlow));
                                                                  }

                                                                  FlowCollector var7 = this.this$0.this$0.this$0.$this_unsafeFlow;
                                                                  Function3 var11 = this.this$0.this$0.this$0.this$0.$transform$inlined;
                                                                  var6 = this.$value;
                                                                  if (var1 == NullSurrogateKt.NULL) {
                                                                     var4 = null;
                                                                  } else {
                                                                     var4 = var1;
                                                                  }

                                                                  this.L$0 = var3;
                                                                  this.L$1 = var1;
                                                                  this.L$2 = var7;
                                                                  this.label = 2;
                                                                  InlineMarker.mark(6);
                                                                  InlineMarker.mark(6);
                                                                  var6 = var11.invoke(var6, var4, this);
                                                                  InlineMarker.mark(7);
                                                                  InlineMarker.mark(7);
                                                                  if (var6 == var8) {
                                                                     return var8;
                                                                  }

                                                                  var5 = var3;
                                                                  var4 = var1;
                                                                  var10 = var7;
                                                               }

                                                               this.L$0 = var5;
                                                               this.L$1 = var4;
                                                               this.label = 3;
                                                               if (var10.emit(var6, this) == var8) {
                                                                  return var8;
                                                               } else {
                                                                  return Unit.INSTANCE;
                                                               }
                                                            }
                                                         }), var2);
                                                         return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
                                                      }
                                                   });
                                                   this.L$0 = var5;
                                                   this.L$1 = var4;
                                                   this.label = 1;
                                                   if (var4.collect(var6, this) == var3) {
                                                      return var3;
                                                   }
                                                }

                                                return Unit.INSTANCE;
                                             }
                                          };
                                          var10 = (Function2)var10;
                                          this.L$0 = var36;
                                          this.L$1 = var31;
                                          this.L$2 = var9;
                                          this.L$3 = var39;
                                          this.L$4 = var8;
                                          this.label = 1;
                                          var38 = ChannelFlowKt.withContextUndispatched$default(var7, var6, (Object)null, var10, this, 4, (Object)null);
                                       } catch (AbortFlowException var29) {
                                          var5 = var29;
                                          break label318;
                                       }
                                    } catch (Throwable var30) {
                                       var37 = var31;
                                       var32 = var30;
                                       break label321;
                                    }

                                    if (var38 == var3) {
                                       return var3;
                                    }

                                    var4 = var31;
                                    break label316;
                                 }
                              }

                              var37 = var31;

                              try {
                                 FlowExceptions_commonKt.checkOwnership(var5, this.$this_unsafeFlow);
                              } catch (Throwable var26) {
                                 var10000 = var26;
                                 var10001 = false;
                                 break label327;
                              }

                              if (var31.isClosedForReceive()) {
                                 return Unit.INSTANCE;
                              }
                              break label315;
                           }

                           if (var4.isClosedForReceive()) {
                              return Unit.INSTANCE;
                           }

                           var31 = var4;
                        }

                        ReceiveChannel.DefaultImpls.cancel$default(var31, (CancellationException)null, 1, (Object)null);
                        return Unit.INSTANCE;
                     }

                     var32 = var10000;
                  }

                  if (!var37.isClosedForReceive()) {
                     ReceiveChannel.DefaultImpls.cancel$default(var37, (CancellationException)null, 1, (Object)null);
                  }

                  throw var32;
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }
}
