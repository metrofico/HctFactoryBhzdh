package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a[\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00072\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\tH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00010\r\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u000eH\u0000\u001a&\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"},
   d2 = {"withContextUndispatched", "T", "V", "newContext", "Lkotlin/coroutines/CoroutineContext;", "value", "countOrElement", "", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asChannelFlow", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "Lkotlinx/coroutines/flow/Flow;", "withUndispatchedContextCollector", "Lkotlinx/coroutines/flow/FlowCollector;", "emitContext", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ChannelFlowKt {
   // $FF: synthetic method
   public static final FlowCollector access$withUndispatchedContextCollector(FlowCollector var0, CoroutineContext var1) {
      return withUndispatchedContextCollector(var0, var1);
   }

   public static final ChannelFlow asChannelFlow(Flow var0) {
      Flow var1;
      if (!(var0 instanceof ChannelFlow)) {
         var1 = null;
      } else {
         var1 = var0;
      }

      ChannelFlow var3 = (ChannelFlow)var1;
      ChannelFlow var2;
      if (var3 != null) {
         var2 = var3;
      } else {
         var2 = (ChannelFlow)(new ChannelFlowOperatorImpl(var0, (CoroutineContext)null, 0, (BufferOverflow)null, 14, (DefaultConstructorMarker)null));
      }

      return var2;
   }

   public static final Object withContextUndispatched(CoroutineContext var0, Object var1, Object var2, Function2 var3, Continuation var4) {
      var2 = ThreadContextKt.updateThreadContext(var0, var2);

      Throwable var10000;
      label124: {
         boolean var10001;
         Continuation var20;
         try {
            StackFrameContinuation var5 = new StackFrameContinuation(var4, var0);
            var20 = (Continuation)var5;
         } catch (Throwable var17) {
            var10000 = var17;
            var10001 = false;
            break label124;
         }

         if (var3 != null) {
            label128: {
               try {
                  var1 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var3, 2)).invoke(var1, var20);
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label128;
               }

               ThreadContextKt.restoreThreadContext(var0, var2);
               if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                  DebugProbesKt.probeCoroutineSuspended(var4);
               }

               return var1;
            }
         } else {
            label120:
            try {
               NullPointerException var19 = new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
               throw var19;
            } catch (Throwable var16) {
               var10000 = var16;
               var10001 = false;
               break label120;
            }
         }
      }

      Throwable var18 = var10000;
      ThreadContextKt.restoreThreadContext(var0, var2);
      throw var18;
   }

   // $FF: synthetic method
   public static Object withContextUndispatched$default(CoroutineContext var0, Object var1, Object var2, Function2 var3, Continuation var4, int var5, Object var6) {
      if ((var5 & 4) != 0) {
         var2 = ThreadContextKt.threadContextElements(var0);
      }

      return withContextUndispatched(var0, var1, var2, var3, var4);
   }

   private static final FlowCollector withUndispatchedContextCollector(FlowCollector var0, CoroutineContext var1) {
      if (!(var0 instanceof SendingCollector) && !(var0 instanceof NopCollector)) {
         var0 = (FlowCollector)(new UndispatchedContextCollector(var0, var1));
      }

      return var0;
   }
}
