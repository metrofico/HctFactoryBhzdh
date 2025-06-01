package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u000f\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00060\u0002j\u0002`\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0011J\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0010¢\u0006\u0002\b\u0014J\u001f\u0010\u0015\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0010¢\u0006\u0004\b\u0016\u0010\u0017J!\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u001bJ\u0006\u0010\u001c\u001a\u00020\fJ\u000f\u0010\u001d\u001a\u0004\u0018\u00010\u000eH ¢\u0006\u0002\b\u001eR\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX \u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"},
   d2 = {"Lkotlinx/coroutines/DispatchedTask;", "T", "Lkotlinx/coroutines/scheduling/Task;", "Lkotlinx/coroutines/SchedulerTask;", "resumeMode", "", "(I)V", "delegate", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "cancelCompletedResult", "", "takenState", "", "cause", "", "cancelCompletedResult$kotlinx_coroutines_core", "getExceptionalResult", "state", "getExceptionalResult$kotlinx_coroutines_core", "getSuccessfulResult", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "handleFatalException", "exception", "finallyException", "handleFatalException$kotlinx_coroutines_core", "run", "takeState", "takeState$kotlinx_coroutines_core", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class DispatchedTask extends Task {
   public int resumeMode;

   public DispatchedTask(int var1) {
      this.resumeMode = var1;
   }

   public void cancelCompletedResult$kotlinx_coroutines_core(Object var1, Throwable var2) {
   }

   public abstract Continuation getDelegate$kotlinx_coroutines_core();

   public Throwable getExceptionalResult$kotlinx_coroutines_core(Object var1) {
      boolean var2 = var1 instanceof CompletedExceptionally;
      Object var3 = null;
      if (!var2) {
         var1 = null;
      }

      CompletedExceptionally var4 = (CompletedExceptionally)var1;
      Throwable var5 = (Throwable)var3;
      if (var4 != null) {
         var5 = var4.cause;
      }

      return var5;
   }

   public Object getSuccessfulResult$kotlinx_coroutines_core(Object var1) {
      return var1;
   }

   public final void handleFatalException$kotlinx_coroutines_core(Throwable var1, Throwable var2) {
      if (var1 != null || var2 != null) {
         if (var1 != null && var2 != null) {
            kotlin.ExceptionsKt.addSuppressed(var1, var2);
         }

         if (var1 == null) {
            var1 = var2;
         }

         String var4 = "Fatal exception in coroutines machinery for " + this + ". " + "Please read KDoc to 'handleFatalException' method and report this incident to maintainers";
         Intrinsics.checkNotNull(var1);
         CoroutinesInternalError var3 = new CoroutinesInternalError(var4, var1);
         CoroutineExceptionHandlerKt.handleCoroutineException(this.getDelegate$kotlinx_coroutines_core().getContext(), (Throwable)var3);
      }
   }

   public final void run() {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var1;
         if (this.resumeMode != -1) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (!var1) {
            throw (Throwable)(new AssertionError());
         }
      }

      Object var3 = this.taskContext;
      Object var5 = null;
      Result.Companion var4 = null;
      Throwable var2 = (Throwable)null;

      label2296: {
         Throwable var10000;
         label2289: {
            boolean var10001;
            Continuation var283;
            try {
               var283 = this.getDelegate$kotlinx_coroutines_core();
            } catch (Throwable var282) {
               var10000 = var282;
               var10001 = false;
               break label2289;
            }

            if (var283 != null) {
               label2297: {
                  CoroutineContext var6;
                  Object var7;
                  Continuation var8;
                  Object var9;
                  try {
                     DispatchedContinuation var284 = (DispatchedContinuation)var283;
                     var8 = var284.continuation;
                     var6 = var8.getContext();
                     var9 = this.takeState$kotlinx_coroutines_core();
                     var7 = ThreadContextKt.updateThreadContext(var6, var284.countOrElement);
                  } catch (Throwable var281) {
                     var10000 = var281;
                     var10001 = false;
                     break label2297;
                  }

                  Result.Companion var288;
                  label2298: {
                     label2299: {
                        Throwable var10;
                        try {
                           var10 = this.getExceptionalResult$kotlinx_coroutines_core(var9);
                        } catch (Throwable var280) {
                           var10000 = var280;
                           var10001 = false;
                           break label2299;
                        }

                        Job var285;
                        label2276: {
                           if (var10 == null) {
                              try {
                                 if (DispatchedTaskKt.isCancellableMode(this.resumeMode)) {
                                    var285 = (Job)var6.get((CoroutineContext.Key)Job.Key);
                                    break label2276;
                                 }
                              } catch (Throwable var279) {
                                 var10000 = var279;
                                 var10001 = false;
                                 break label2299;
                              }
                           }

                           var285 = null;
                        }

                        label2303: {
                           if (var285 != null) {
                              label2302: {
                                 label2266: {
                                    CancellationException var286;
                                    label2265: {
                                       try {
                                          if (var285.isActive()) {
                                             break label2302;
                                          }

                                          var286 = var285.getCancellationException();
                                          this.cancelCompletedResult$kotlinx_coroutines_core(var9, (Throwable)var286);
                                          Result.Companion var292 = Result.Companion;
                                          if (!DebugKt.getRECOVER_STACK_TRACES() || !(var8 instanceof CoroutineStackFrame)) {
                                             break label2265;
                                          }
                                       } catch (Throwable var278) {
                                          var10000 = var278;
                                          var10001 = false;
                                          break label2299;
                                       }

                                       try {
                                          var2 = StackTraceRecoveryKt.access$recoverFromStackFrame((Throwable)var286, (CoroutineStackFrame)var8);
                                          break label2266;
                                       } catch (Throwable var277) {
                                          var10000 = var277;
                                          var10001 = false;
                                          break label2299;
                                       }
                                    }

                                    try {
                                       var2 = (Throwable)var286;
                                    } catch (Throwable var276) {
                                       var10000 = var276;
                                       var10001 = false;
                                       break label2299;
                                    }
                                 }

                                 try {
                                    var8.resumeWith(Result.constructor_impl(ResultKt.createFailure(var2)));
                                    break label2303;
                                 } catch (Throwable var275) {
                                    var10000 = var275;
                                    var10001 = false;
                                    break label2299;
                                 }
                              }
                           }

                           if (var10 != null) {
                              try {
                                 var288 = Result.Companion;
                                 var8.resumeWith(Result.constructor_impl(ResultKt.createFailure(var10)));
                              } catch (Throwable var274) {
                                 var10000 = var274;
                                 var10001 = false;
                                 break label2299;
                              }
                           } else {
                              try {
                                 var9 = this.getSuccessfulResult$kotlinx_coroutines_core(var9);
                                 var288 = Result.Companion;
                                 var8.resumeWith(Result.constructor_impl(var9));
                              } catch (Throwable var273) {
                                 var10000 = var273;
                                 var10001 = false;
                                 break label2299;
                              }
                           }
                        }

                        label2246:
                        try {
                           Unit var289 = Unit.INSTANCE;
                           break label2298;
                        } catch (Throwable var272) {
                           var10000 = var272;
                           var10001 = false;
                           break label2246;
                        }
                     }

                     var2 = var10000;

                     try {
                        ThreadContextKt.restoreThreadContext(var6, var7);
                        throw var2;
                     } catch (Throwable var269) {
                        var10000 = var269;
                        var10001 = false;
                        break label2297;
                     }
                  }

                  try {
                     ThreadContextKt.restoreThreadContext(var6, var7);
                  } catch (Throwable var271) {
                     var10000 = var271;
                     var10001 = false;
                     break label2297;
                  }

                  try {
                     var288 = Result.Companion;
                     DispatchedTask var290 = (DispatchedTask)this;
                     ((TaskContext)var3).afterTask();
                     var3 = Result.constructor_impl(Unit.INSTANCE);
                  } finally {
                     ;
                  }

                  var2 = (Throwable)var5;
                  break label2296;
               }
            } else {
               label2238:
               try {
                  NullPointerException var291 = new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T>");
                  throw var291;
               } catch (Throwable var270) {
                  var10000 = var270;
                  var10001 = false;
                  break label2238;
               }
            }
         }

         var2 = var10000;

         label2231:
         try {
            var4 = Result.Companion;
            DispatchedTask var287 = (DispatchedTask)this;
            ((TaskContext)var3).afterTask();
            var3 = Result.constructor_impl(Unit.INSTANCE);
         } finally {
            var4 = Result.Companion;
            var3 = Result.constructor_impl(ResultKt.createFailure((Throwable)var3));
            break label2231;
         }
      }

      this.handleFatalException$kotlinx_coroutines_core(var2, Result.exceptionOrNull_impl(var3));
   }

   public abstract Object takeState$kotlinx_coroutines_core();
}
