package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0002\b\u0004\u001a(\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a0\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0006\u001a\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0006\u001a$\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\u0006\u0010\u0002\u001a\u00020\u0003\u001a[\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0006\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00070\u00062\u0006\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\t2#\u0010\u0013\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\u00060\u0014¢\u0006\u0002\b\u0015H\u0007¨\u0006\u0016"},
   d2 = {"checkFlowContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "checkFlowContext$FlowKt__ContextKt", "buffer", "Lkotlinx/coroutines/flow/Flow;", "T", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "cancellable", "conflate", "flowOn", "flowWith", "R", "flowContext", "bufferSize", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__ContextKt {
   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.4.0, binary compatibility with earlier versions"
   )
   public static final Flow buffer(Flow var0, int var1) {
      return FlowKt.buffer$default(var0, var1, (BufferOverflow)null, 2, (Object)null);
   }

   public static final Flow buffer(Flow var0, int var1, BufferOverflow var2) {
      boolean var4 = true;
      boolean var3;
      if (var1 < 0 && var1 != -2 && var1 != -1) {
         var3 = false;
      } else {
         var3 = true;
      }

      if (var3) {
         var3 = var4;
         if (var1 == -1) {
            if (var2 == BufferOverflow.SUSPEND) {
               var3 = var4;
            } else {
               var3 = false;
            }
         }

         if (var3) {
            if (var1 == -1) {
               var2 = BufferOverflow.DROP_OLDEST;
               var1 = 0;
            }

            if (var0 instanceof FusibleFlow) {
               var0 = FusibleFlow.DefaultImpls.fuse$default((FusibleFlow)var0, (CoroutineContext)null, var1, var2, 1, (Object)null);
            } else {
               var0 = (Flow)(new ChannelFlowOperatorImpl(var0, (CoroutineContext)null, var1, var2, 2, (DefaultConstructorMarker)null));
            }

            return var0;
         } else {
            throw (Throwable)(new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString()));
         }
      } else {
         throw (Throwable)(new IllegalArgumentException(("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was " + var1).toString()));
      }
   }

   // $FF: synthetic method
   public static Flow buffer$default(Flow var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = -2;
      }

      return FlowKt.buffer(var0, var1);
   }

   // $FF: synthetic method
   public static Flow buffer$default(Flow var0, int var1, BufferOverflow var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = -2;
      }

      if ((var3 & 2) != 0) {
         var2 = BufferOverflow.SUSPEND;
      }

      return FlowKt.buffer(var0, var1, var2);
   }

   public static final Flow cancellable(Flow var0) {
      if (!(var0 instanceof CancellableFlow)) {
         var0 = (Flow)(new CancellableFlowImpl(var0));
      }

      return var0;
   }

   private static final void checkFlowContext$FlowKt__ContextKt(CoroutineContext var0) {
      boolean var1;
      if (var0.get((CoroutineContext.Key)Job.Key) == null) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (!var1) {
         throw (Throwable)(new IllegalArgumentException(("Flow context cannot contain job in it. Had " + var0).toString()));
      }
   }

   public static final Flow conflate(Flow var0) {
      return FlowKt.buffer$default(var0, -1, (BufferOverflow)null, 2, (Object)null);
   }

   public static final Flow flowOn(Flow var0, CoroutineContext var1) {
      checkFlowContext$FlowKt__ContextKt(var1);
      if (!Intrinsics.areEqual((Object)var1, (Object)EmptyCoroutineContext.INSTANCE)) {
         if (var0 instanceof FusibleFlow) {
            var0 = FusibleFlow.DefaultImpls.fuse$default((FusibleFlow)var0, var1, 0, (BufferOverflow)null, 6, (Object)null);
         } else {
            var0 = (Flow)(new ChannelFlowOperatorImpl(var0, var1, 0, (BufferOverflow)null, 12, (DefaultConstructorMarker)null));
         }
      }

      return var0;
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "flowWith is deprecated without replacement, please refer to its KDoc for an explanation"
   )
   public static final Flow flowWith(Flow var0, CoroutineContext var1, int var2, Function1 var3) {
      checkFlowContext$FlowKt__ContextKt(var1);
      return (Flow)(new Flow(var0, var2, var3, var1) {
         final int $bufferSize$inlined;
         final Function1 $builder$inlined;
         final CoroutineContext $flowContext$inlined;
         final Flow $source$inlined;

         public {
            this.$source$inlined = var1;
            this.$bufferSize$inlined = var2;
            this.$builder$inlined = var3;
            this.$flowContext$inlined = var4;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            CoroutineContext var3 = var2.getContext().minusKey((CoroutineContext.Key)Job.Key);
            Flow var5 = FlowKt.buffer$default(FlowKt.flowOn(this.$source$inlined, var3), this.$bufferSize$inlined, (BufferOverflow)null, 2, (Object)null);
            Object var4 = FlowKt.buffer$default(FlowKt.flowOn((Flow)this.$builder$inlined.invoke(var5), this.$flowContext$inlined), this.$bufferSize$inlined, (BufferOverflow)null, 2, (Object)null).collect((FlowCollector)(new FlowCollector(var1) {
               final FlowCollector $this_unsafeFlow$inlined;

               public {
                  this.$this_unsafeFlow$inlined = var1;
               }

               public Object emit(Object var1, Continuation var2) {
                  var1 = this.$this_unsafeFlow$inlined.emit(var1, var2);
                  return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
               }
            }), var2);
            return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
         }
      });
   }

   // $FF: synthetic method
   public static Flow flowWith$default(Flow var0, CoroutineContext var1, int var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = -2;
      }

      return FlowKt.flowWith(var0, var1, var2, var3);
   }
}
