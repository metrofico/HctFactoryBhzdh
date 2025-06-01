package kotlinx.coroutines.flow.internal;

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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B?\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0014J\u001f\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J&\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\u0011\u001a\u00020\u0017H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"},
   d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowMerge;", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flow", "Lkotlinx/coroutines/flow/Flow;", "concurrency", "", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlinx/coroutines/flow/Flow;ILkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "additionalToStringProps", "", "collectTo", "", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "produceImpl", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ChannelFlowMerge extends ChannelFlow {
   private final int concurrency;
   private final Flow flow;

   public ChannelFlowMerge(Flow var1, int var2, CoroutineContext var3, int var4, BufferOverflow var5) {
      super(var3, var4, var5);
      this.flow = var1;
      this.concurrency = var2;
   }

   // $FF: synthetic method
   public ChannelFlowMerge(Flow var1, int var2, CoroutineContext var3, int var4, BufferOverflow var5, int var6, DefaultConstructorMarker var7) {
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

   protected String additionalToStringProps() {
      return "concurrency=" + this.concurrency;
   }

   protected Object collectTo(ProducerScope var1, Continuation var2) {
      Semaphore var3 = SemaphoreKt.Semaphore$default(this.concurrency, 0, 2, (Object)null);
      SendingCollector var4 = new SendingCollector((SendChannel)var1);
      Job var5 = (Job)var2.getContext().get((CoroutineContext.Key)Job.Key);
      Object var6 = this.flow.collect((FlowCollector)(new FlowCollector(var5, var3, var1, var4) {
         final SendingCollector $collector$inlined;
         final Job $job$inlined;
         final ProducerScope $scope$inlined;
         final Semaphore $semaphore$inlined;

         public {
            this.$job$inlined = var1;
            this.$semaphore$inlined = var2;
            this.$scope$inlined = var3;
            this.$collector$inlined = var4;
         }

         public Object emit(Object var1, Continuation var2) {
            Object var10;
            label29: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var10 = var4;
                     break label29;
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

            Object var5 = ((<undefinedtype>)var10).result;
            Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var10).label;
            <undefinedtype> var9;
            Flow var11;
            Flow var12;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var12 = (Flow)((<undefinedtype>)var10).L$3;
               Continuation var8 = (Continuation)((<undefinedtype>)var10).L$2;
               var1 = ((<undefinedtype>)var10).L$1;
               var9 = (<undefinedtype>)((<undefinedtype>)var10).L$0;
               ResultKt.throwOnFailure(var5);
               var11 = var12;
            } else {
               ResultKt.throwOnFailure(var5);
               Continuation var13 = (Continuation)var10;
               var12 = (Flow)var1;
               Job var7 = this.$job$inlined;
               if (var7 != null) {
                  JobKt.ensureActive(var7);
               }

               Semaphore var14 = this.$semaphore$inlined;
               ((<undefinedtype>)var10).L$0 = this;
               ((<undefinedtype>)var10).L$1 = var1;
               ((<undefinedtype>)var10).L$2 = var13;
               ((<undefinedtype>)var10).L$3 = var12;
               ((<undefinedtype>)var10).label = 1;
               if (var14.acquire((Continuation)var10) == var6) {
                  return var6;
               }

               var9 = this;
               var11 = var12;
            }

            BuildersKt.launch$default((CoroutineScope)var9.$scope$inlined, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(var11, (Continuation)null, var9) {
               final Flow $inner;
               Object L$0;
               int label;
               private CoroutineScope p$;
               final <undefinedtype> this$0;

               {
                  this.$inner = var1;
                  this.this$0 = var3;
               }

               public final Continuation create(Object var1, Continuation var2) {
                  Function2 var3 = new <anonymous constructor>(this.$inner, var2, this.this$0);
                  var3.p$ = (CoroutineScope)var1;
                  return var3;
               }

               public final Object invoke(Object var1, Object var2) {
                  return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  label102: {
                     Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     Throwable var10000;
                     boolean var10001;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        CoroutineScope var14 = (CoroutineScope)this.L$0;

                        label95:
                        try {
                           ResultKt.throwOnFailure(var1);
                           break label102;
                        } catch (Throwable var10) {
                           var10000 = var10;
                           var10001 = false;
                           break label95;
                        }
                     } else {
                        label106: {
                           ResultKt.throwOnFailure(var1);
                           CoroutineScope var4 = this.p$;

                           try {
                              Flow var13 = this.$inner;
                              FlowCollector var5 = (FlowCollector)this.this$0.$collector$inlined;
                              this.L$0 = var4;
                              this.label = 1;
                              var1 = var13.collect(var5, this);
                           } catch (Throwable var11) {
                              var10000 = var11;
                              var10001 = false;
                              break label106;
                           }

                           if (var1 == var3) {
                              return var3;
                           }
                           break label102;
                        }
                     }

                     Throwable var12 = var10000;
                     this.this$0.$semaphore$inlined.release();
                     throw var12;
                  }

                  this.this$0.$semaphore$inlined.release();
                  return Unit.INSTANCE;
               }
            }), 3, (Object)null);
            return Unit.INSTANCE;
         }
      }), var2);
      return var6 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var6 : Unit.INSTANCE;
   }

   protected ChannelFlow create(CoroutineContext var1, int var2, BufferOverflow var3) {
      return (ChannelFlow)(new ChannelFlowMerge(this.flow, this.concurrency, var1, var2, var3));
   }

   public ReceiveChannel produceImpl(CoroutineScope var1) {
      return FlowCoroutineKt.flowProduce(var1, this.context, this.capacity, this.getCollectToFun$kotlinx_coroutines_core());
   }
}
