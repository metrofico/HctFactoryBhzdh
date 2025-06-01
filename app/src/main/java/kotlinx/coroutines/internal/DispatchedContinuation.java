package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000O2\u00060>j\u0002`?2\b\u0012\u0004\u0012\u00028\u00000\u0004B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\u000f\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\nH\u0010¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0014\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001e\u001a\n\u0018\u00010\u001cj\u0004\u0018\u0001`\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0019\u0010\"\u001a\u00020!2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\u0013¢\u0006\u0004\b\"\u0010#J\u0015\u0010$\u001a\u00020!2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b$\u0010%JH\u0010,\u001a\u00020\f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000&2%\b\b\u0010+\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010(H\u0086\bø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u001a\u0010/\u001a\u00020!2\b\u0010.\u001a\u0004\u0018\u00010\bH\u0086\b¢\u0006\u0004\b/\u00100J!\u00101\u001a\u00020\f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0086\bø\u0001\u0000¢\u0006\u0004\b1\u00102J \u00103\u001a\u00020\f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0016ø\u0001\u0000¢\u0006\u0004\b3\u00102J\u0011\u00106\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0004\b4\u00105J\u000f\u00108\u001a\u000207H\u0016¢\u0006\u0004\b8\u00109R\u001e\u0010:\u001a\u0004\u0018\u00010\b8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b:\u0010;\u0012\u0004\b<\u0010=R$\u0010@\u001a\n\u0018\u00010>j\u0004\u0018\u0001`?8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u0016\u0010\u0017\u001a\u00020\u00168\u0016@\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010FR\u0016\u0010G\u001a\u00020\b8\u0000@\u0001X\u0081\u0004¢\u0006\u0006\n\u0004\bG\u0010;R\u001c\u0010J\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048P@\u0010X\u0090\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010KR\u0019\u0010M\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00138F@\u0006¢\u0006\u0006\u001a\u0004\bL\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006N"},
   d2 = {"Lkotlinx/coroutines/internal/DispatchedContinuation;", "T", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "Lkotlin/coroutines/Continuation;", "continuation", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "", "takenState", "", "cause", "", "cancelCompletedResult$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelCompletedResult", "Lkotlinx/coroutines/CancellableContinuation;", "checkPostponedCancellation", "(Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Throwable;", "Lkotlinx/coroutines/CancellableContinuationImpl;", "claimReusableCancellableContinuation", "()Lkotlinx/coroutines/CancellableContinuationImpl;", "Lkotlin/coroutines/CoroutineContext;", "context", "value", "dispatchYield$kotlinx_coroutines_core", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "dispatchYield", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "requester", "", "isReusable", "(Lkotlinx/coroutines/CancellableContinuationImpl;)Z", "postponeCancellation", "(Ljava/lang/Throwable;)Z", "Lkotlin/Result;", "result", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onCancellation", "resumeCancellableWith", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "state", "resumeCancelled", "(Ljava/lang/Object;)Z", "resumeUndispatchedWith", "(Ljava/lang/Object;)V", "resumeWith", "takeState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "takeState", "", "toString", "()Ljava/lang/String;", "_state", "Ljava/lang/Object;", "get_state$kotlinx_coroutines_core$annotations", "()V", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "callerFrame", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/Continuation;", "countOrElement", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "delegate", "Lkotlinx/coroutines/CoroutineDispatcher;", "getReusableCancellableContinuation", "reusableCancellableContinuation", "kotlinx-coroutines-core", "Lkotlinx/coroutines/DispatchedTask;"},
   k = 1,
   mv = {1, 4, 0}
)
public final class DispatchedContinuation extends DispatchedTask implements CoroutineStackFrame, Continuation {
   private static final AtomicReferenceFieldUpdater _reusableCancellableContinuation$FU = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation.class, Object.class, "_reusableCancellableContinuation");
   private volatile Object _reusableCancellableContinuation;
   public Object _state;
   private final CoroutineStackFrame callerFrame;
   public final Continuation continuation;
   public final Object countOrElement;
   public final CoroutineDispatcher dispatcher;

   public DispatchedContinuation(CoroutineDispatcher var1, Continuation var2) {
      super(-1);
      this.dispatcher = var1;
      this.continuation = var2;
      this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
      Continuation var3 = var2;
      if (!(var2 instanceof CoroutineStackFrame)) {
         var3 = null;
      }

      this.callerFrame = (CoroutineStackFrame)var3;
      this.countOrElement = ThreadContextKt.threadContextElements(this.getContext());
      this._reusableCancellableContinuation = null;
   }

   // $FF: synthetic method
   public static void get_state$kotlinx_coroutines_core$annotations() {
   }

   public void cancelCompletedResult$kotlinx_coroutines_core(Object var1, Throwable var2) {
      if (var1 instanceof CompletedWithCancellation) {
         ((CompletedWithCancellation)var1).onCancellation.invoke(var2);
      }

   }

   public final Throwable checkPostponedCancellation(CancellableContinuation var1) {
      while(true) {
         Object var2 = this._reusableCancellableContinuation;
         if (var2 == DispatchedContinuationKt.REUSABLE_CLAIMED) {
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, DispatchedContinuationKt.REUSABLE_CLAIMED, var1)) {
               continue;
            }

            return null;
         }

         if (var2 == null) {
            return null;
         }

         if (var2 instanceof Throwable) {
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, var2, (Object)null)) {
               return (Throwable)var2;
            }

            throw (Throwable)(new IllegalArgumentException("Failed requirement.".toString()));
         }

         throw (Throwable)(new IllegalStateException(("Inconsistent state " + var2).toString()));
      }
   }

   public final CancellableContinuationImpl claimReusableCancellableContinuation() {
      while(true) {
         Object var1 = this._reusableCancellableContinuation;
         if (var1 == null) {
            this._reusableCancellableContinuation = DispatchedContinuationKt.REUSABLE_CLAIMED;
            return null;
         }

         if (var1 instanceof CancellableContinuationImpl) {
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, var1, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
               continue;
            }

            return (CancellableContinuationImpl)var1;
         }

         throw (Throwable)(new IllegalStateException(("Inconsistent state " + var1).toString()));
      }
   }

   public final void dispatchYield$kotlinx_coroutines_core(CoroutineContext var1, Object var2) {
      this._state = var2;
      this.resumeMode = 1;
      this.dispatcher.dispatchYield(var1, (Runnable)this);
   }

   public CoroutineStackFrame getCallerFrame() {
      return this.callerFrame;
   }

   public CoroutineContext getContext() {
      return this.continuation.getContext();
   }

   public Continuation getDelegate$kotlinx_coroutines_core() {
      return (Continuation)this;
   }

   public final CancellableContinuationImpl getReusableCancellableContinuation() {
      Object var2 = this._reusableCancellableContinuation;
      Object var1 = var2;
      if (!(var2 instanceof CancellableContinuationImpl)) {
         var1 = null;
      }

      return (CancellableContinuationImpl)var1;
   }

   public StackTraceElement getStackTraceElement() {
      return null;
   }

   public final boolean isReusable(CancellableContinuationImpl var1) {
      Object var3 = this._reusableCancellableContinuation;
      boolean var2 = false;
      if (var3 != null) {
         if (var3 instanceof CancellableContinuationImpl) {
            if (var3 == var1) {
               var2 = true;
            }

            return var2;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   public final boolean postponeCancellation(Throwable var1) {
      while(true) {
         Object var2 = this._reusableCancellableContinuation;
         if (Intrinsics.areEqual((Object)var2, (Object)DispatchedContinuationKt.REUSABLE_CLAIMED)) {
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, DispatchedContinuationKt.REUSABLE_CLAIMED, var1)) {
               return true;
            }
         } else {
            if (var2 instanceof Throwable) {
               return true;
            }

            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, var2, (Object)null)) {
               return false;
            }
         }
      }
   }

   public final void resumeCancellableWith(Object var1, Function1 var2) {
      Object var6 = CompletionStateKt.toState(var1, var2);
      if (this.dispatcher.isDispatchNeeded(this.getContext())) {
         this._state = var6;
         this.resumeMode = 1;
         this.dispatcher.dispatch(this.getContext(), (Runnable)this);
      } else {
         DebugKt.getASSERTIONS_ENABLED();
         EventLoop var79 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
         if (var79.isUnconfinedLoopActive()) {
            this._state = var6;
            this.resumeMode = 1;
            var79.dispatchUnconfined((DispatchedTask)this);
         } else {
            DispatchedTask var5 = (DispatchedTask)this;
            var79.incrementUseCount(true);

            label719: {
               Throwable var10000;
               label720: {
                  Job var7;
                  boolean var10001;
                  try {
                     var7 = (Job)this.getContext().get((CoroutineContext.Key)Job.Key);
                  } catch (Throwable var76) {
                     var10000 = var76;
                     var10001 = false;
                     break label720;
                  }

                  boolean var3;
                  label706: {
                     label705: {
                        if (var7 != null) {
                           try {
                              if (!var7.isActive()) {
                                 CancellationException var82 = var7.getCancellationException();
                                 this.cancelCompletedResult$kotlinx_coroutines_core(var6, (Throwable)var82);
                                 Result.Companion var80 = Result.Companion;
                                 this.resumeWith(Result.constructor_impl(ResultKt.createFailure((Throwable)var82)));
                                 break label705;
                              }
                           } catch (Throwable var75) {
                              var10000 = var75;
                              var10001 = false;
                              break label720;
                           }
                        }

                        var3 = false;
                        break label706;
                     }

                     var3 = true;
                  }

                  if (!var3) {
                     CoroutineContext var81;
                     Object var83;
                     try {
                        var81 = this.getContext();
                        var83 = ThreadContextKt.updateThreadContext(var81, this.countOrElement);
                     } catch (Throwable var74) {
                        var10000 = var74;
                        var10001 = false;
                        break label720;
                     }

                     try {
                        this.continuation.resumeWith(var1);
                        Unit var77 = Unit.INSTANCE;
                     } finally {
                        try {
                           InlineMarker.finallyStart(1);
                           ThreadContextKt.restoreThreadContext(var81, var83);
                           InlineMarker.finallyEnd(1);
                        } catch (Throwable var71) {
                           var10000 = var71;
                           var10001 = false;
                           break label720;
                        }
                     }
                  }

                  while(true) {
                     boolean var4;
                     try {
                        var4 = var79.processUnconfinedEvent();
                     } catch (Throwable var72) {
                        var10000 = var72;
                        var10001 = false;
                        break;
                     }

                     if (!var4) {
                        InlineMarker.finallyStart(1);
                        break label719;
                     }
                  }
               }

               Throwable var78 = var10000;
               boolean var16 = false;

               try {
                  var16 = true;
                  var5.handleFatalException$kotlinx_coroutines_core(var78, (Throwable)null);
                  var16 = false;
               } finally {
                  if (var16) {
                     InlineMarker.finallyStart(1);
                     var79.decrementUseCount(true);
                     InlineMarker.finallyEnd(1);
                  }
               }

               InlineMarker.finallyStart(1);
            }

            var79.decrementUseCount(true);
            InlineMarker.finallyEnd(1);
         }
      }

   }

   public final boolean resumeCancelled(Object var1) {
      Job var2 = (Job)this.getContext().get((CoroutineContext.Key)Job.Key);
      if (var2 != null && !var2.isActive()) {
         Throwable var4 = (Throwable)var2.getCancellationException();
         this.cancelCompletedResult$kotlinx_coroutines_core(var1, var4);
         Result.Companion var3 = Result.Companion;
         this.resumeWith(Result.constructor_impl(ResultKt.createFailure(var4)));
         return true;
      } else {
         return false;
      }
   }

   public final void resumeUndispatchedWith(Object var1) {
      CoroutineContext var2 = this.getContext();
      Object var3 = ThreadContextKt.updateThreadContext(var2, this.countOrElement);

      try {
         this.continuation.resumeWith(var1);
         Unit var6 = Unit.INSTANCE;
      } finally {
         InlineMarker.finallyStart(1);
         ThreadContextKt.restoreThreadContext(var2, var3);
         InlineMarker.finallyEnd(1);
      }

   }

   public void resumeWith(Object var1) {
      CoroutineContext var2 = this.continuation.getContext();
      Object var3 = CompletionStateKt.toState$default(var1, (Function1)null, 1, (Object)null);
      if (this.dispatcher.isDispatchNeeded(var2)) {
         this._state = var3;
         this.resumeMode = 0;
         this.dispatcher.dispatch(var2, (Runnable)this);
      } else {
         DebugKt.getASSERTIONS_ENABLED();
         EventLoop var48 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
         if (var48.isUnconfinedLoopActive()) {
            this._state = var3;
            this.resumeMode = 0;
            var48.dispatchUnconfined((DispatchedTask)this);
         } else {
            DispatchedTask var49 = (DispatchedTask)this;
            var48.incrementUseCount(true);

            Throwable var10000;
            label359: {
               CoroutineContext var4;
               Object var5;
               boolean var10001;
               try {
                  var4 = this.getContext();
                  var5 = ThreadContextKt.updateThreadContext(var4, this.countOrElement);
               } catch (Throwable var45) {
                  var10000 = var45;
                  var10001 = false;
                  break label359;
               }

               try {
                  this.continuation.resumeWith(var1);
                  Unit var46 = Unit.INSTANCE;
               } finally {
                  try {
                     ThreadContextKt.restoreThreadContext(var4, var5);
                  } catch (Throwable var42) {
                     var10000 = var42;
                     var10001 = false;
                     break label359;
                  }
               }

               label345:
               try {
                  while(var48.processUnconfinedEvent()) {
                  }

                  return;
               } catch (Throwable var43) {
                  var10000 = var43;
                  var10001 = false;
                  break label345;
               }
            }

            Throwable var47 = var10000;

            try {
               var49.handleFatalException$kotlinx_coroutines_core(var47, (Throwable)null);
            } finally {
               var48.decrementUseCount(true);
            }
         }
      }

   }

   public Object takeState$kotlinx_coroutines_core() {
      Object var2 = this._state;
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var1;
         if (var2 != DispatchedContinuationKt.access$getUNDEFINED$p()) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (!var1) {
            throw (Throwable)(new AssertionError());
         }
      }

      this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
      return var2;
   }

   public String toString() {
      return "DispatchedContinuation[" + this.dispatcher + ", " + DebugStringsKt.toDebugString(this.continuation) + ']';
   }
}
