package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001aI\u0010\b\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012%\b\u0002\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a.\u0010\b\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"},
   d2 = {"recoverResult", "Lkotlin/Result;", "T", "state", "", "uCont", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toState", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "caller", "Lkotlinx/coroutines/CancellableContinuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CompletionStateKt {
   public static final Object recoverResult(Object var0, Continuation var1) {
      if (var0 instanceof CompletedExceptionally) {
         Result.Companion var2 = Result.Companion;
         Throwable var5 = ((CompletedExceptionally)var0).cause;
         Throwable var3 = var5;
         if (DebugKt.getRECOVER_STACK_TRACES()) {
            if (!(var1 instanceof CoroutineStackFrame)) {
               var3 = var5;
            } else {
               var3 = StackTraceRecoveryKt.access$recoverFromStackFrame(var5, (CoroutineStackFrame)var1);
            }
         }

         var0 = Result.constructor_impl(ResultKt.createFailure(var3));
      } else {
         Result.Companion var4 = Result.Companion;
         var0 = Result.constructor_impl(var0);
      }

      return var0;
   }

   public static final Object toState(Object var0, Function1 var1) {
      Throwable var2 = Result.exceptionOrNull_impl(var0);
      Object var3;
      if (var2 == null) {
         var3 = var0;
         if (var1 != null) {
            var3 = new CompletedWithCancellation(var0, var1);
         }
      } else {
         var3 = new CompletedExceptionally(var2, false, 2, (DefaultConstructorMarker)null);
      }

      return var3;
   }

   public static final Object toState(Object var0, CancellableContinuation var1) {
      Throwable var2 = Result.exceptionOrNull_impl(var0);
      if (var2 != null) {
         Continuation var4 = (Continuation)var1;
         Throwable var3 = var2;
         if (DebugKt.getRECOVER_STACK_TRACES()) {
            if (!(var4 instanceof CoroutineStackFrame)) {
               var3 = var2;
            } else {
               var3 = StackTraceRecoveryKt.access$recoverFromStackFrame(var2, (CoroutineStackFrame)var4);
            }
         }

         var0 = new CompletedExceptionally(var3, false, 2, (DefaultConstructorMarker)null);
      }

      return var0;
   }

   // $FF: synthetic method
   public static Object toState$default(Object var0, Function1 var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         Function1 var4 = (Function1)null;
      }

      return toState(var0, var1);
   }
}
