package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003Bx\u0012B\u0010\u0004\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005¢\u0006\u0002\b\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0002\u0010\u0016J&\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u00192\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\u001f\u0010\u001a\u001a\u00020\u000b2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u001cRO\u0010\u0004\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005¢\u0006\u0002\b\rX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"},
   d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowTransformLatest;", "T", "R", "Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "transform", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlin/jvm/functions/Function3;Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "Lkotlin/jvm/functions/Function3;", "create", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flowCollect", "collector", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ChannelFlowTransformLatest extends ChannelFlowOperator {
   private final Function3 transform;

   public ChannelFlowTransformLatest(Function3 var1, Flow var2, CoroutineContext var3, int var4, BufferOverflow var5) {
      super(var2, var3, var4, var5);
      this.transform = var1;
   }

   // $FF: synthetic method
   public ChannelFlowTransformLatest(Function3 var1, Flow var2, CoroutineContext var3, int var4, BufferOverflow var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 4) != 0) {
         var3 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var6 & 8) != 0) {
         var4 = -2;
      }

      if ((var6 & 16) != 0) {
         var5 = BufferOverflow.SUSPEND;
      }

      this(var1, var2, var3, var4, var5);
   }

   protected ChannelFlow create(CoroutineContext var1, int var2, BufferOverflow var3) {
      return (ChannelFlow)(new ChannelFlowTransformLatest(this.transform, this.flow, var1, var2, var3));
   }

   protected Object flowCollect(FlowCollector var1, Continuation var2) {
      if (DebugKt.getASSERTIONS_ENABLED() && !Boxing.boxBoolean(var1 instanceof SendingCollector)) {
         throw (Throwable)(new AssertionError());
      } else {
         Object var3 = FlowCoroutineKt.flowScope((Function2)(new Function2(this, var1, (Continuation)null) {
            final FlowCollector $collector;
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            private CoroutineScope p$;
            final ChannelFlowTransformLatest this$0;

            {
               this.this$0 = var1;
               this.$collector = var2;
            }

            public final Continuation create(Object var1, Continuation var2) {
               Function2 var3 = new <anonymous constructor>(this.this$0, this.$collector, var2);
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

                  Flow var8 = (Flow)this.L$2;
                  Ref.ObjectRef var9 = (Ref.ObjectRef)this.L$1;
                  CoroutineScope var10 = (CoroutineScope)this.L$0;
                  ResultKt.throwOnFailure(var1);
               } else {
                  ResultKt.throwOnFailure(var1);
                  CoroutineScope var4 = this.p$;
                  Ref.ObjectRef var7 = new Ref.ObjectRef();
                  Job var5 = (Job)null;
                  var7.element = null;
                  Flow var11 = this.this$0.flow;
                  FlowCollector var6 = (FlowCollector)(new FlowCollector(this, var4, var7) {
                     final Ref.ObjectRef $previousFlow$inlined;
                     final CoroutineScope $this_flowScope$inlined;
                     final <undefinedtype> this$0;

                     public {
                        this.this$0 = var1;
                        this.$this_flowScope$inlined = var2;
                        this.$previousFlow$inlined = var3;
                     }

                     public Object emit(Object var1, Continuation var2) {
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
                              Object L$2;
                              Object L$3;
                              Object L$4;
                              Object L$5;
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

                        Object var10 = ((<undefinedtype>)var8).result;
                        Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int var3 = ((<undefinedtype>)var8).label;
                        <undefinedtype> var9;
                        if (var3 != 0) {
                           if (var3 != 1) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           Job var7 = (Job)((<undefinedtype>)var8).L$5;
                           var7 = (Job)((<undefinedtype>)var8).L$4;
                           var1 = ((<undefinedtype>)var8).L$3;
                           Continuation var12 = (Continuation)((<undefinedtype>)var8).L$2;
                           var5 = ((<undefinedtype>)var8).L$1;
                           var9 = (<undefinedtype>)((<undefinedtype>)var8).L$0;
                           ResultKt.throwOnFailure(var10);
                        } else {
                           ResultKt.throwOnFailure(var10);
                           Continuation var6 = (Continuation)var8;
                           Job var11 = (Job)this.$previousFlow$inlined.element;
                           if (var11 != null) {
                              var11.cancel((CancellationException)(new ChildCancelledException()));
                              ((<undefinedtype>)var8).L$0 = this;
                              ((<undefinedtype>)var8).L$1 = var1;
                              ((<undefinedtype>)var8).L$2 = var6;
                              ((<undefinedtype>)var8).L$3 = var1;
                              ((<undefinedtype>)var8).L$4 = var11;
                              ((<undefinedtype>)var8).L$5 = var11;
                              ((<undefinedtype>)var8).label = 1;
                              if (var11.join((Continuation)var8) == var5) {
                                 return var5;
                              }
                           }

                           var9 = this;
                        }

                        var9.$previousFlow$inlined.element = BuildersKt.launch$default(var9.$this_flowScope$inlined, (CoroutineContext)null, CoroutineStart.UNDISPATCHED, (Function2)(new Function2(var1, (Continuation)null, var9) {
                           final Object $value;
                           Object L$0;
                           int label;
                           private CoroutineScope p$;
                           final <undefinedtype> this$0;

                           {
                              this.$value = var1;
                              this.this$0 = var3;
                           }

                           public final Continuation create(Object var1, Continuation var2) {
                              Function2 var3 = new <anonymous constructor>(this.$value, var2, this.this$0);
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
                                 Function3 var6 = this.this$0.this$0.this$0.transform;
                                 FlowCollector var4 = this.this$0.this$0.$collector;
                                 var1 = this.$value;
                                 this.L$0 = var5;
                                 this.label = 1;
                                 if (var6.invoke(var4, var1, this) == var3) {
                                    return var3;
                                 }
                              }

                              return Unit.INSTANCE;
                           }
                        }), 1, (Object)null);
                        return Unit.INSTANCE;
                     }
                  });
                  this.L$0 = var4;
                  this.L$1 = var7;
                  this.L$2 = var11;
                  this.label = 1;
                  if (var11.collect(var6, this) == var3) {
                     return var3;
                  }
               }

               return Unit.INSTANCE;
            }
         }), var2);
         return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
      }
   }
}
