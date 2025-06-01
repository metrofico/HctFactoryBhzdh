package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.ranges.RangesKt;
import kotlin.time.Duration;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0011\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0019\u0010\u0000\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u001e\u0010\u0000\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0087@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0016\u0010\u0010\u001a\u00020\n*\u00020\rH\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"},
   d2 = {"delay", "Lkotlinx/coroutines/Delay;", "Lkotlin/coroutines/CoroutineContext;", "getDelay", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Delay;", "awaitCancellation", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "timeMillis", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "duration", "Lkotlin/time/Duration;", "delay-p9JZ4hM", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDelayMillis", "toDelayMillis-LRDsOJo", "(D)J", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class DelayKt {
   public static final Object awaitCancellation(Continuation var0) {
      CancellableContinuationImpl var2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var0), 1);
      var2.initCancellability();
      CancellableContinuation var1 = (CancellableContinuation)var2;
      Object var3 = var2.getResult();
      if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var0);
      }

      return var3;
   }

   public static final Object delay(long var0, Continuation var2) {
      if (var0 <= 0L) {
         return Unit.INSTANCE;
      } else {
         CancellableContinuationImpl var3 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var2), 1);
         var3.initCancellability();
         CancellableContinuation var4 = (CancellableContinuation)var3;
         if (var0 < Long.MAX_VALUE) {
            getDelay(var4.getContext()).scheduleResumeAfterDelay(var0, var4);
         }

         Object var5 = var3.getResult();
         if (var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var2);
         }

         return var5;
      }
   }

   public static final Object delay_p9JZ4hM(double var0, Continuation var2) {
      Object var3 = delay(toDelayMillis_LRDsOJo(var0), var2);
      return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
   }

   public static final Delay getDelay(CoroutineContext var0) {
      CoroutineContext.Element var1 = var0.get((CoroutineContext.Key)ContinuationInterceptor.Key);
      CoroutineContext.Element var2 = var1;
      if (!(var1 instanceof Delay)) {
         var2 = null;
      }

      Delay var3 = (Delay)var2;
      if (var3 == null) {
         var3 = DefaultExecutorKt.getDefaultDelay();
      }

      return var3;
   }

   public static final long toDelayMillis_LRDsOJo(double var0) {
      long var2;
      if (Duration.compareTo_LRDsOJo(var0, Duration.Companion.getZERO_UwyO8pc()) > 0) {
         var2 = RangesKt.coerceAtLeast(Duration.toLongMilliseconds_impl(var0), 1L);
      } else {
         var2 = 0L;
      }

      return var2;
   }
}
