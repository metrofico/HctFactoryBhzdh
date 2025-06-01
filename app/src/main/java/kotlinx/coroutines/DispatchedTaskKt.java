package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000<\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u000f2\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u001a.\u0010\u0011\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0000\u001a\u0010\u0010\u0015\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u000fH\u0002\u001a\u0019\u0010\u0016\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0080\b\u001a'\u0010\u0019\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u001dH\u0080\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0018\u0010\b\u001a\u00020\t*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n\"\u0018\u0010\u000b\u001a\u00020\t*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\n¨\u0006\u001e"},
   d2 = {"MODE_ATOMIC", "", "MODE_CANCELLABLE", "getMODE_CANCELLABLE$annotations", "()V", "MODE_CANCELLABLE_REUSABLE", "MODE_UNDISPATCHED", "MODE_UNINITIALIZED", "isCancellableMode", "", "(I)Z", "isReusableMode", "dispatch", "", "T", "Lkotlinx/coroutines/DispatchedTask;", "mode", "resume", "delegate", "Lkotlin/coroutines/Continuation;", "undispatched", "resumeUnconfined", "resumeWithStackTrace", "exception", "", "runUnconfinedEventLoop", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "block", "Lkotlin/Function0;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class DispatchedTaskKt {
   public static final int MODE_ATOMIC = 0;
   public static final int MODE_CANCELLABLE = 1;
   public static final int MODE_CANCELLABLE_REUSABLE = 2;
   public static final int MODE_UNDISPATCHED = 4;
   public static final int MODE_UNINITIALIZED = -1;

   public static final void dispatch(DispatchedTask var0, int var1) {
      boolean var4 = DebugKt.getASSERTIONS_ENABLED();
      boolean var3 = true;
      if (var4) {
         boolean var2;
         if (var1 != -1) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            throw (Throwable)(new AssertionError());
         }
      }

      Continuation var6 = var0.getDelegate$kotlinx_coroutines_core();
      if (var1 != 4) {
         var3 = false;
      }

      if (!var3 && var6 instanceof DispatchedContinuation && isCancellableMode(var1) == isCancellableMode(var0.resumeMode)) {
         CoroutineDispatcher var5 = ((DispatchedContinuation)var6).dispatcher;
         CoroutineContext var7 = var6.getContext();
         if (var5.isDispatchNeeded(var7)) {
            var5.dispatch(var7, (Runnable)var0);
         } else {
            resumeUnconfined(var0);
         }
      } else {
         resume(var0, var6, var3);
      }

   }

   // $FF: synthetic method
   public static void getMODE_CANCELLABLE$annotations() {
   }

   public static final boolean isCancellableMode(int var0) {
      boolean var2 = true;
      boolean var1 = var2;
      if (var0 != 1) {
         if (var0 == 2) {
            var1 = var2;
         } else {
            var1 = false;
         }
      }

      return var1;
   }

   public static final boolean isReusableMode(int var0) {
      boolean var1;
      if (var0 == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final void resume(DispatchedTask var0, Continuation var1, boolean var2) {
      Object var3 = var0.takeState$kotlinx_coroutines_core();
      Throwable var4 = var0.getExceptionalResult$kotlinx_coroutines_core(var3);
      Object var8;
      if (var4 != null) {
         Result.Companion var7 = Result.Companion;
         var8 = ResultKt.createFailure(var4);
      } else {
         Result.Companion var12 = Result.Companion;
         var8 = var0.getSuccessfulResult$kotlinx_coroutines_core(var3);
      }

      var3 = Result.constructor_impl(var8);
      if (var2) {
         if (var1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T>");
         }

         DispatchedContinuation var13 = (DispatchedContinuation)var1;
         CoroutineContext var10 = var13.getContext();
         Object var9 = ThreadContextKt.updateThreadContext(var10, var13.countOrElement);

         try {
            var13.continuation.resumeWith(var3);
            Unit var11 = Unit.INSTANCE;
         } finally {
            ThreadContextKt.restoreThreadContext(var10, var9);
         }
      } else {
         var1.resumeWith(var3);
      }

   }

   private static final void resumeUnconfined(DispatchedTask var0) {
      EventLoop var2 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
      if (var2.isUnconfinedLoopActive()) {
         var2.dispatchUnconfined(var0);
      } else {
         var2.incrementUseCount(true);

         Throwable var10000;
         label127: {
            boolean var10001;
            try {
               resume(var0, var0.getDelegate$kotlinx_coroutines_core(), true);
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label127;
            }

            while(true) {
               boolean var1;
               try {
                  var1 = var2.processUnconfinedEvent();
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break;
               }

               if (!var1) {
                  return;
               }
            }
         }

         Throwable var3 = var10000;

         try {
            var0.handleFatalException$kotlinx_coroutines_core(var3, (Throwable)null);
         } finally {
            var2.decrementUseCount(true);
         }
      }

   }

   public static final void resumeWithStackTrace(Continuation var0, Throwable var1) {
      Result.Companion var2 = Result.Companion;
      Throwable var3 = var1;
      if (DebugKt.getRECOVER_STACK_TRACES()) {
         if (!(var0 instanceof CoroutineStackFrame)) {
            var3 = var1;
         } else {
            var3 = StackTraceRecoveryKt.access$recoverFromStackFrame(var1, (CoroutineStackFrame)var0);
         }
      }

      var0.resumeWith(Result.constructor_impl(ResultKt.createFailure(var3)));
   }

   public static final void runUnconfinedEventLoop(DispatchedTask var0, EventLoop var1, Function0 var2) {
      var1.incrementUseCount(true);

      label132: {
         Throwable var10000;
         label127: {
            boolean var10001;
            try {
               var2.invoke();
            } catch (Throwable var16) {
               var10000 = var16;
               var10001 = false;
               break label127;
            }

            while(true) {
               boolean var3;
               try {
                  var3 = var1.processUnconfinedEvent();
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break;
               }

               if (!var3) {
                  InlineMarker.finallyStart(1);
                  break label132;
               }
            }
         }

         Throwable var17 = var10000;
         boolean var7 = false;

         try {
            var7 = true;
            var0.handleFatalException$kotlinx_coroutines_core(var17, (Throwable)null);
            var7 = false;
         } finally {
            if (var7) {
               InlineMarker.finallyStart(1);
               var1.decrementUseCount(true);
               InlineMarker.finallyEnd(1);
            }
         }

         InlineMarker.finallyStart(1);
      }

      var1.decrementUseCount(true);
      InlineMarker.finallyEnd(1);
   }
}
