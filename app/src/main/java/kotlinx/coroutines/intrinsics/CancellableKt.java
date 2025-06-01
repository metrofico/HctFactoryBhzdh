package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0082\b\u001a\u001e\u0010\u0006\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00032\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0000\u001a>\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\b*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0003\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001ay\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\b*\u001e\b\u0001\u0012\u0004\u0012\u0002H\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0003\u0012\u0006\u0012\u0004\u0018\u00010\n0\r2\u0006\u0010\u000e\u001a\u0002H\f2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u00032%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0001\u0018\u00010\tH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"},
   d2 = {"runSafely", "", "completion", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function0;", "startCoroutineCancellable", "fatalCompletion", "T", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", "receiver", "onCancellation", "", "Lkotlin/ParameterName;", "name", "cause", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CancellableKt {
   private static final void runSafely(Continuation var0, Function0 var1) {
      try {
         var1.invoke();
      } catch (Throwable var4) {
         Result.Companion var5 = Result.Companion;
         var0.resumeWith(Result.constructor_impl(ResultKt.createFailure(var4)));
         return;
      }

   }

   public static final void startCoroutineCancellable(Continuation var0, Continuation var1) {
      Result.Companion var2;
      try {
         var0 = IntrinsicsKt.intercepted(var0);
         var2 = Result.Companion;
         DispatchedContinuationKt.resumeCancellableWith$default(var0, Result.constructor_impl(Unit.INSTANCE), (Function1)null, 2, (Object)null);
      } catch (Throwable var4) {
         var2 = Result.Companion;
         var1.resumeWith(Result.constructor_impl(ResultKt.createFailure(var4)));
         return;
      }

   }

   public static final void startCoroutineCancellable(Function1 var0, Continuation var1) {
      Result.Companion var2;
      try {
         Continuation var5 = IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(var0, var1));
         var2 = Result.Companion;
         DispatchedContinuationKt.resumeCancellableWith$default(var5, Result.constructor_impl(Unit.INSTANCE), (Function1)null, 2, (Object)null);
      } catch (Throwable var4) {
         var2 = Result.Companion;
         var1.resumeWith(Result.constructor_impl(ResultKt.createFailure(var4)));
         return;
      }

   }

   public static final void startCoroutineCancellable(Function2 var0, Object var1, Continuation var2, Function1 var3) {
      Result.Companion var6;
      try {
         Continuation var7 = IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(var0, var1, var2));
         var6 = Result.Companion;
         DispatchedContinuationKt.resumeCancellableWith(var7, Result.constructor_impl(Unit.INSTANCE), var3);
      } catch (Throwable var5) {
         var6 = Result.Companion;
         var2.resumeWith(Result.constructor_impl(ResultKt.createFailure(var5)));
         return;
      }

   }

   // $FF: synthetic method
   public static void startCoroutineCancellable$default(Function2 var0, Object var1, Continuation var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = null;
         Function1 var6 = (Function1)null;
      }

      startCoroutineCancellable(var0, var1, var2, var3);
   }
}
