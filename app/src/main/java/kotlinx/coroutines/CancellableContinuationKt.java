package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000\u001a3\u0010\u0005\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a3\u0010\u000b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0080Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u0018\u0010\f\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\r\u001a\u00020\u000eH\u0007\u001a\u0018\u0010\u000f\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"},
   d2 = {"getOrCreateCancellableContinuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "delegate", "Lkotlin/coroutines/Continuation;", "suspendCancellableCoroutine", "block", "Lkotlin/Function1;", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suspendCancellableCoroutineReusable", "disposeOnCancellation", "handle", "Lkotlinx/coroutines/DisposableHandle;", "removeOnCancellation", "node", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CancellableContinuationKt {
   public static final void disposeOnCancellation(CancellableContinuation var0, DisposableHandle var1) {
      var0.invokeOnCancellation((Function1)((CancelHandlerBase)(new DisposeOnCancel(var1))));
   }

   public static final CancellableContinuationImpl getOrCreateCancellableContinuation(Continuation var0) {
      if (!(var0 instanceof DispatchedContinuation)) {
         return new CancellableContinuationImpl(var0, 2);
      } else {
         CancellableContinuationImpl var1 = ((DispatchedContinuation)var0).claimReusableCancellableContinuation();
         if (var1 != null) {
            if (!var1.resetStateReusable()) {
               var1 = null;
            }

            if (var1 != null) {
               return var1;
            }
         }

         return new CancellableContinuationImpl(var0, 2);
      }
   }

   public static final void removeOnCancellation(CancellableContinuation var0, LockFreeLinkedListNode var1) {
      var0.invokeOnCancellation((Function1)((CancelHandlerBase)(new RemoveOnCancel(var1))));
   }

   public static final Object suspendCancellableCoroutine(Function1 var0, Continuation var1) {
      CancellableContinuationImpl var2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var1), 1);
      var2.initCancellability();
      var0.invoke(var2);
      Object var3 = var2.getResult();
      if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var3;
   }

   private static final Object suspendCancellableCoroutine$$forInline(Function1 var0, Continuation var1) {
      InlineMarker.mark(0);
      CancellableContinuationImpl var2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var1), 1);
      var2.initCancellability();
      var0.invoke(var2);
      Object var3 = var2.getResult();
      if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      InlineMarker.mark(1);
      return var3;
   }

   public static final Object suspendCancellableCoroutineReusable(Function1 var0, Continuation var1) {
      CancellableContinuationImpl var2 = getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(var1));
      var0.invoke(var2);
      Object var3 = var2.getResult();
      if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var3;
   }

   private static final Object suspendCancellableCoroutineReusable$$forInline(Function1 var0, Continuation var1) {
      InlineMarker.mark(0);
      CancellableContinuationImpl var2 = getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(var1));
      var0.invoke(var2);
      Object var3 = var2.getResult();
      if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      InlineMarker.mark(1);
      return var3;
   }
}
