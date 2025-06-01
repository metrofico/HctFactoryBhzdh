package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0002\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"},
   d2 = {"yield", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkCompletion", "Lkotlin/coroutines/CoroutineContext;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class YieldKt {
   public static final void checkCompletion(CoroutineContext var0) {
      Job var1 = (Job)var0.get((CoroutineContext.Key)Job.Key);
      if (var1 != null && !var1.isActive()) {
         throw (Throwable)var1.getCancellationException();
      }
   }

   public static final Object yield(Continuation var0) {
      CoroutineContext var3 = var0.getContext();
      checkCompletion(var3);
      Continuation var2 = IntrinsicsKt.intercepted(var0);
      Continuation var1 = var2;
      if (!(var2 instanceof DispatchedContinuation)) {
         var1 = null;
      }

      DispatchedContinuation var4 = (DispatchedContinuation)var1;
      Object var5;
      if (var4 != null) {
         label29: {
            if (var4.dispatcher.isDispatchNeeded(var3)) {
               var4.dispatchYield$kotlinx_coroutines_core(var3, Unit.INSTANCE);
            } else {
               YieldContext var6 = new YieldContext();
               var4.dispatchYield$kotlinx_coroutines_core(var3.plus((CoroutineContext)var6), Unit.INSTANCE);
               if (var6.dispatcherWasUnconfined) {
                  if (DispatchedContinuationKt.yieldUndispatched(var4)) {
                     var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  } else {
                     var5 = Unit.INSTANCE;
                  }
                  break label29;
               }
            }

            var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         }
      } else {
         var5 = Unit.INSTANCE;
      }

      if (var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var0);
      }

      return var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var5 : Unit.INSTANCE;
   }
}
