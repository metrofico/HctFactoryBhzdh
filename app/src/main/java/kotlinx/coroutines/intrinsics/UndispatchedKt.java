package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a9\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u001a\u0010\u0005\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0082\b\u001a>\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\t\u001aR\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u0002*\u001e\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b2\u0006\u0010\f\u001a\u0002H\n2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a>\u0010\u000e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\t\u001aR\u0010\u000e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u0002*\u001e\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b2\u0006\u0010\f\u001a\u0002H\n2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\r\u001aY\u0010\u000f\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u00102\u0006\u0010\f\u001a\u0002H\n2'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b¢\u0006\u0002\b\u0011H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aY\u0010\u0013\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u00102\u0006\u0010\f\u001a\u0002H\n2'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b¢\u0006\u0002\b\u0011H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a?\u0010\u0014\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00102\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00062\u000e\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0019H\u0082\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"},
   d2 = {"startDirect", "", "T", "completion", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function1;", "", "startCoroutineUndispatched", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "startCoroutineUnintercepted", "startUndispatchedOrReturn", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/internal/ScopeCoroutine;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "startUndispatchedOrReturnIgnoreTimeout", "undispatchedResult", "shouldThrow", "", "", "startBlock", "Lkotlin/Function0;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class UndispatchedKt {
   public static final void startCoroutineUndispatched(Function1 var0, Continuation var1) {
      Continuation var2 = DebugProbesKt.probeCoroutineCreated(var1);

      Throwable var10000;
      label281: {
         CoroutineContext var3;
         Object var37;
         boolean var10001;
         try {
            var3 = var1.getContext();
            var37 = ThreadContextKt.updateThreadContext(var3, (Object)null);
         } catch (Throwable var33) {
            var10000 = var33;
            var10001 = false;
            break label281;
         }

         if (var0 != null) {
            label287: {
               Object var34;
               try {
                  var34 = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var0, 1)).invoke(var2);
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label287;
               }

               try {
                  ThreadContextKt.restoreThreadContext(var3, var37);
               } catch (Throwable var31) {
                  var10000 = var31;
                  var10001 = false;
                  break label281;
               }

               if (var34 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                  Result.Companion var39 = Result.Companion;
                  var2.resumeWith(Result.constructor_impl(var34));
               }

               return;
            }
         } else {
            label263:
            try {
               NullPointerException var38 = new NullPointerException("null cannot be cast to non-null type (kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
               throw var38;
            } catch (Throwable var30) {
               var10000 = var30;
               var10001 = false;
               break label263;
            }
         }

         Throwable var35 = var10000;

         label260:
         try {
            ThreadContextKt.restoreThreadContext(var3, var37);
            throw var35;
         } catch (Throwable var29) {
            var10000 = var29;
            var10001 = false;
            break label260;
         }
      }

      Throwable var40 = var10000;
      Result.Companion var36 = Result.Companion;
      var2.resumeWith(Result.constructor_impl(ResultKt.createFailure(var40)));
   }

   public static final void startCoroutineUndispatched(Function2 var0, Object var1, Continuation var2) {
      Continuation var3 = DebugProbesKt.probeCoroutineCreated(var2);

      Throwable var10000;
      label281: {
         Object var4;
         CoroutineContext var41;
         boolean var10001;
         try {
            var41 = var2.getContext();
            var4 = ThreadContextKt.updateThreadContext(var41, (Object)null);
         } catch (Throwable var34) {
            var10000 = var34;
            var10001 = false;
            break label281;
         }

         if (var0 != null) {
            label287: {
               Object var35;
               try {
                  var35 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var0, 2)).invoke(var1, var3);
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label287;
               }

               try {
                  ThreadContextKt.restoreThreadContext(var41, var4);
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label281;
               }

               if (var35 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                  Result.Companion var39 = Result.Companion;
                  var3.resumeWith(Result.constructor_impl(var35));
               }

               return;
            }
         } else {
            label263:
            try {
               NullPointerException var38 = new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
               throw var38;
            } catch (Throwable var31) {
               var10000 = var31;
               var10001 = false;
               break label263;
            }
         }

         Throwable var36 = var10000;

         label260:
         try {
            ThreadContextKt.restoreThreadContext(var41, var4);
            throw var36;
         } catch (Throwable var30) {
            var10000 = var30;
            var10001 = false;
            break label260;
         }
      }

      Throwable var40 = var10000;
      Result.Companion var37 = Result.Companion;
      var3.resumeWith(Result.constructor_impl(ResultKt.createFailure(var40)));
   }

   public static final void startCoroutineUnintercepted(Function1 var0, Continuation var1) {
      var1 = DebugProbesKt.probeCoroutineCreated(var1);
      Throwable var10000;
      boolean var10001;
      if (var0 != null) {
         label75: {
            Object var9;
            try {
               var9 = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var0, 1)).invoke(var1);
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label75;
            }

            if (var9 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               Result.Companion var2 = Result.Companion;
               var1.resumeWith(Result.constructor_impl(var9));
            }

            return;
         }
      } else {
         label63:
         try {
            NullPointerException var11 = new NullPointerException("null cannot be cast to non-null type (kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
            throw var11;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label63;
         }
      }

      Throwable var12 = var10000;
      Result.Companion var10 = Result.Companion;
      var1.resumeWith(Result.constructor_impl(ResultKt.createFailure(var12)));
   }

   public static final void startCoroutineUnintercepted(Function2 var0, Object var1, Continuation var2) {
      var2 = DebugProbesKt.probeCoroutineCreated(var2);
      Throwable var10000;
      boolean var10001;
      Result.Companion var12;
      if (var0 != null) {
         label75: {
            Object var9;
            try {
               var9 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var0, 2)).invoke(var1, var2);
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label75;
            }

            if (var9 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               var12 = Result.Companion;
               var2.resumeWith(Result.constructor_impl(var9));
            }

            return;
         }
      } else {
         label63:
         try {
            NullPointerException var11 = new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
            throw var11;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label63;
         }
      }

      Throwable var10 = var10000;
      var12 = Result.Companion;
      var2.resumeWith(Result.constructor_impl(ResultKt.createFailure(var10)));
   }

   private static final void startDirect(Continuation var0, Function1 var1) {
      var0 = DebugProbesKt.probeCoroutineCreated(var0);

      Result.Companion var2;
      Object var5;
      try {
         var5 = var1.invoke(var0);
      } catch (Throwable var4) {
         var2 = Result.Companion;
         var0.resumeWith(Result.constructor_impl(ResultKt.createFailure(var4)));
         return;
      }

      if (var5 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         var2 = Result.Companion;
         var0.resumeWith(Result.constructor_impl(var5));
      }

   }

   public static final Object startUndispatchedOrReturn(ScopeCoroutine var0, Object var1, Function2 var2) {
      var0.initParentJob$kotlinx_coroutines_core();

      Throwable var17;
      label186: {
         Throwable var10000;
         label185: {
            Continuation var3;
            boolean var10001;
            try {
               var3 = (Continuation)var0;
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label185;
            }

            if (var2 != null) {
               label181:
               try {
                  var1 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var2, 2)).invoke(var1, var3);
                  break label186;
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label181;
               }
            } else {
               label178:
               try {
                  NullPointerException var21 = new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                  throw var21;
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label178;
               }
            }
         }

         var17 = var10000;
         var1 = new CompletedExceptionally(var17, false, 2, (DefaultConstructorMarker)null);
      }

      Object var16;
      if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         var16 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      } else {
         var1 = var0.makeCompletingOnce$kotlinx_coroutines_core(var1);
         if (var1 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            var16 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         } else {
            if (var1 instanceof CompletedExceptionally) {
               CompletedExceptionally var19 = (CompletedExceptionally)var1;
               var17 = var19.cause;
               var17 = var19.cause;
               Continuation var20 = var0.uCont;
               Throwable var18 = var17;
               if (DebugKt.getRECOVER_STACK_TRACES()) {
                  if (!(var20 instanceof CoroutineStackFrame)) {
                     var18 = var17;
                  } else {
                     var18 = StackTraceRecoveryKt.access$recoverFromStackFrame(var17, (CoroutineStackFrame)var20);
                  }
               }

               throw var18;
            }

            var16 = JobSupportKt.unboxState(var1);
         }
      }

      return var16;
   }

   public static final Object startUndispatchedOrReturnIgnoreTimeout(ScopeCoroutine var0, Object var1, Function2 var2) {
      var0.initParentJob$kotlinx_coroutines_core();
      boolean var3 = false;

      Throwable var18;
      Object var19;
      label269: {
         Throwable var10000;
         label268: {
            Continuation var4;
            boolean var10001;
            try {
               var4 = (Continuation)var0;
            } catch (Throwable var16) {
               var10000 = var16;
               var10001 = false;
               break label268;
            }

            if (var2 != null) {
               label264:
               try {
                  var19 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var2, 2)).invoke(var1, var4);
                  break label269;
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label264;
               }
            } else {
               label261:
               try {
                  NullPointerException var22 = new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                  throw var22;
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label261;
               }
            }
         }

         var18 = var10000;
         var19 = new CompletedExceptionally(var18, false, 2, (DefaultConstructorMarker)null);
      }

      if (var19 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         var1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      } else {
         var1 = var0.makeCompletingOnce$kotlinx_coroutines_core(var19);
         if (var1 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            var1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         } else if (var1 instanceof CompletedExceptionally) {
            CompletedExceptionally var21 = (CompletedExceptionally)var1;
            var18 = var21.cause;
            if (!(var18 instanceof TimeoutCancellationException) || ((TimeoutCancellationException)var18).coroutine != var0) {
               var3 = true;
            }

            Throwable var17;
            Continuation var20;
            if (var3) {
               var18 = var21.cause;
               var20 = var0.uCont;
               var17 = var18;
               if (DebugKt.getRECOVER_STACK_TRACES()) {
                  if (!(var20 instanceof CoroutineStackFrame)) {
                     var17 = var18;
                  } else {
                     var17 = StackTraceRecoveryKt.access$recoverFromStackFrame(var18, (CoroutineStackFrame)var20);
                  }
               }

               throw var17;
            }

            var1 = var19;
            if (var19 instanceof CompletedExceptionally) {
               var18 = ((CompletedExceptionally)var19).cause;
               var20 = var0.uCont;
               var17 = var18;
               if (DebugKt.getRECOVER_STACK_TRACES()) {
                  if (!(var20 instanceof CoroutineStackFrame)) {
                     var17 = var18;
                  } else {
                     var17 = StackTraceRecoveryKt.access$recoverFromStackFrame(var18, (CoroutineStackFrame)var20);
                  }
               }

               throw var17;
            }
         } else {
            var1 = JobSupportKt.unboxState(var1);
         }
      }

      return var1;
   }

   private static final Object undispatchedResult(ScopeCoroutine var0, Function1 var1, Function0 var2) {
      Object var9;
      label87:
      try {
         var9 = var2.invoke();
      } catch (Throwable var5) {
         var9 = new CompletedExceptionally(var5, false, 2, (DefaultConstructorMarker)null);
         break label87;
      }

      if (var9 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         return IntrinsicsKt.getCOROUTINE_SUSPENDED();
      } else {
         Object var3 = var0.makeCompletingOnce$kotlinx_coroutines_core(var9);
         if (var3 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
         } else {
            Object var7;
            if (var3 instanceof CompletedExceptionally) {
               CompletedExceptionally var10 = (CompletedExceptionally)var3;
               Throwable var6;
               Throwable var8;
               Continuation var11;
               if ((Boolean)var1.invoke(var10.cause)) {
                  var8 = var10.cause;
                  var11 = var0.uCont;
                  var6 = var8;
                  if (DebugKt.getRECOVER_STACK_TRACES()) {
                     if (!(var11 instanceof CoroutineStackFrame)) {
                        var6 = var8;
                     } else {
                        var6 = StackTraceRecoveryKt.access$recoverFromStackFrame(var8, (CoroutineStackFrame)var11);
                     }
                  }

                  throw var6;
               }

               var7 = var9;
               if (var9 instanceof CompletedExceptionally) {
                  var8 = ((CompletedExceptionally)var9).cause;
                  var11 = var0.uCont;
                  var6 = var8;
                  if (DebugKt.getRECOVER_STACK_TRACES()) {
                     if (!(var11 instanceof CoroutineStackFrame)) {
                        var6 = var8;
                     } else {
                        var6 = StackTraceRecoveryKt.access$recoverFromStackFrame(var8, (CoroutineStackFrame)var11);
                     }
                  }

                  throw var6;
               }
            } else {
               var7 = JobSupportKt.unboxState(var3);
            }

            return var7;
         }
      }
   }
}
