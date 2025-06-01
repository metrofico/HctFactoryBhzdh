package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\n\u0010\n\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"},
   d2 = {"Lkotlinx/coroutines/Delay;", "", "delay", "", "time", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "context", "Lkotlin/coroutines/CoroutineContext;", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface Delay {
   Object delay(long var1, Continuation var3);

   DisposableHandle invokeOnTimeout(long var1, Runnable var3, CoroutineContext var4);

   void scheduleResumeAfterDelay(long var1, CancellableContinuation var3);

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      public static Object delay(Delay var0, long var1, Continuation var3) {
         if (var1 <= 0L) {
            return Unit.INSTANCE;
         } else {
            CancellableContinuationImpl var4 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var3), 1);
            var4.initCancellability();
            var0.scheduleResumeAfterDelay(var1, (CancellableContinuation)var4);
            Object var5 = var4.getResult();
            if (var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               DebugProbesKt.probeCoroutineSuspended(var3);
            }

            return var5;
         }
      }

      public static DisposableHandle invokeOnTimeout(Delay var0, long var1, Runnable var3, CoroutineContext var4) {
         return DefaultExecutorKt.getDefaultDelay().invokeOnTimeout(var1, var3, var4);
      }
   }
}
