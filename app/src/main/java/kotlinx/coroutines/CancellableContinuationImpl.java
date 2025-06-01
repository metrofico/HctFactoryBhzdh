package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\t\u0012\u0004\u0012\u00028\u00000\u008a\u00012\t\u0012\u0004\u0012\u00028\u00000\u008b\u00012\u00060rj\u0002`sB\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0012\u0010\u0013JB\u0010\u0012\u001a\u00020\u00112'\u0010\u000e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u0014j\u0002`\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0018J\u001e\u0010\u001b\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u0019H\u0082\b¢\u0006\u0004\b\u001b\u0010\u001cJ8\u0010\u001e\u001a\u00020\u00112!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u00142\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u001e\u0010\u0018J\u0019\u0010 \u001a\u00020\u001f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0004\b \u0010!J!\u0010%\u001a\u00020\u00112\b\u0010\"\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0010¢\u0006\u0004\b#\u0010$J\u0017\u0010&\u001a\u00020\u001f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b&\u0010!J\u000f\u0010'\u001a\u00020\u001fH\u0002¢\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\bH\u0016¢\u0006\u0004\b*\u0010+J\u000f\u0010.\u001a\u00020\u0011H\u0000¢\u0006\u0004\b,\u0010-J\u000f\u0010/\u001a\u00020\u0011H\u0002¢\u0006\u0004\b/\u0010-J\u0017\u00101\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0004H\u0002¢\u0006\u0004\b1\u00102J\u0017\u00105\u001a\u00020\u000f2\u0006\u00104\u001a\u000203H\u0016¢\u0006\u0004\b5\u00106J\u001b\u0010:\u001a\u0004\u0018\u00010\u000f2\b\u00107\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0004\b8\u00109J\u0011\u0010;\u001a\u0004\u0018\u00010\bH\u0001¢\u0006\u0004\b;\u0010<J\u0017\u0010?\u001a\n\u0018\u00010=j\u0004\u0018\u0001`>H\u0016¢\u0006\u0004\b?\u0010@J\u001f\u0010C\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00012\b\u00107\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0004\bA\u0010BJ\u000f\u0010D\u001a\u00020\u0011H\u0016¢\u0006\u0004\bD\u0010-J8\u0010E\u001a\u00020\u00112'\u0010\u000e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u0014j\u0002`\u0017H\u0016¢\u0006\u0004\bE\u0010FJ\u000f\u0010G\u001a\u00020\u001fH\u0002¢\u0006\u0004\bG\u0010(J8\u0010H\u001a\u00020\r2'\u0010\u000e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u0014j\u0002`\u0017H\u0002¢\u0006\u0004\bH\u0010IJB\u0010J\u001a\u00020\u00112'\u0010\u000e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u0014j\u0002`\u00172\b\u00107\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\bJ\u0010KJ\u000f\u0010M\u001a\u00020LH\u0014¢\u0006\u0004\bM\u0010NJ\u0017\u0010Q\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\bO\u0010PJ\u000f\u0010R\u001a\u00020\u001fH\u0001¢\u0006\u0004\bR\u0010(J<\u0010T\u001a\u00020\u00112\u0006\u0010S\u001a\u00028\u00002#\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0014H\u0016¢\u0006\u0004\bT\u0010UJH\u0010V\u001a\u00020\u00112\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042%\b\u0002\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0014H\u0002¢\u0006\u0004\bV\u0010WJ \u0010Z\u001a\u00020\u00112\f\u0010Y\u001a\b\u0012\u0004\u0012\u00028\u00000XH\u0016ø\u0001\u0000¢\u0006\u0004\bZ\u0010+JZ\u0010]\u001a\u0004\u0018\u00010\b2\u0006\u00107\u001a\u00020[2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042#\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00142\b\u0010\\\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b]\u0010^J\u000f\u0010_\u001a\u00020\u0011H\u0002¢\u0006\u0004\b_\u0010-J\u0011\u0010a\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0004\b`\u0010<J\u000f\u0010b\u001a\u00020LH\u0016¢\u0006\u0004\bb\u0010NJ\u000f\u0010c\u001a\u00020\u001fH\u0002¢\u0006\u0004\bc\u0010(J#\u0010c\u001a\u0004\u0018\u00010\b2\u0006\u0010S\u001a\u00028\u00002\b\u0010\\\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\bc\u0010dJH\u0010c\u001a\u0004\u0018\u00010\b2\u0006\u0010S\u001a\u00028\u00002\b\u0010\\\u001a\u0004\u0018\u00010\b2#\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0014H\u0016¢\u0006\u0004\bc\u0010eJJ\u0010g\u001a\u0004\u0018\u00010f2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\\\u001a\u0004\u0018\u00010\b2#\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0014H\u0002¢\u0006\u0004\bg\u0010hJ\u0019\u0010j\u001a\u0004\u0018\u00010\b2\u0006\u0010i\u001a\u00020\u000fH\u0016¢\u0006\u0004\bj\u0010kJ\u000f\u0010l\u001a\u00020\u001fH\u0002¢\u0006\u0004\bl\u0010(J\u001b\u0010n\u001a\u00020\u0011*\u00020m2\u0006\u0010S\u001a\u00028\u0000H\u0016¢\u0006\u0004\bn\u0010oJ\u001b\u0010p\u001a\u00020\u0011*\u00020m2\u0006\u0010i\u001a\u00020\u000fH\u0016¢\u0006\u0004\bp\u0010qR\u001e\u0010v\u001a\n\u0018\u00010rj\u0004\u0018\u0001`s8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bt\u0010uR\u001c\u0010x\u001a\u00020w8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\bx\u0010y\u001a\u0004\bz\u0010{R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010|\u001a\u0004\b}\u0010~R\u0016\u0010\u007f\u001a\u00020\u001f8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u007f\u0010(R\u0018\u0010\u0080\u0001\u001a\u00020\u001f8V@\u0016X\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0080\u0001\u0010(R\u0018\u0010\u0081\u0001\u001a\u00020\u001f8V@\u0016X\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0081\u0001\u0010(R/\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0082\u00012\t\u0010S\u001a\u0005\u0018\u00010\u0082\u00018B@BX\u0082\u000e¢\u0006\u0010\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001\"\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0019\u00107\u001a\u0004\u0018\u00010\b8@@\u0000X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0088\u0001\u0010<\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0089\u0001"},
   d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "Lkotlin/coroutines/Continuation;", "delegate", "", "resumeMode", "<init>", "(Lkotlin/coroutines/Continuation;I)V", "", "proposedUpdate", "", "alreadyResumedError", "(Ljava/lang/Object;)Ljava/lang/Void;", "Lkotlinx/coroutines/CancelHandler;", "handler", "", "cause", "", "callCancelHandler", "(Lkotlinx/coroutines/CancelHandler;Ljava/lang/Throwable;)V", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Throwable;)V", "Lkotlin/Function0;", "block", "callCancelHandlerSafely", "(Lkotlin/jvm/functions/Function0;)V", "onCancellation", "callOnCancellation", "", "cancel", "(Ljava/lang/Throwable;)Z", "takenState", "cancelCompletedResult$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelCompletedResult", "cancelLater", "checkCompleted", "()Z", "token", "completeResume", "(Ljava/lang/Object;)V", "detachChild$kotlinx_coroutines_core", "()V", "detachChild", "detachChildIfNonResuable", "mode", "dispatchResume", "(I)V", "Lkotlinx/coroutines/Job;", "parent", "getContinuationCancellationCause", "(Lkotlinx/coroutines/Job;)Ljava/lang/Throwable;", "state", "getExceptionalResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getExceptionalResult", "getResult", "()Ljava/lang/Object;", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "getSuccessfulResult", "initCancellability", "invokeOnCancellation", "(Lkotlin/jvm/functions/Function1;)V", "isReusable", "makeCancelHandler", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/CancelHandler;", "multipleHandlersError", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)V", "", "nameString", "()Ljava/lang/String;", "parentCancelled$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)V", "parentCancelled", "resetStateReusable", "value", "resume", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "resumeImpl", "(Ljava/lang/Object;ILkotlin/jvm/functions/Function1;)V", "Lkotlin/Result;", "result", "resumeWith", "Lkotlinx/coroutines/NotCompleted;", "idempotent", "resumedState", "(Lkotlinx/coroutines/NotCompleted;Ljava/lang/Object;ILkotlin/jvm/functions/Function1;Ljava/lang/Object;)Ljava/lang/Object;", "setupCancellation", "takeState$kotlinx_coroutines_core", "takeState", "toString", "tryResume", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeImpl", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/Symbol;", "exception", "tryResumeWithException", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "trySuspend", "Lkotlinx/coroutines/CoroutineDispatcher;", "resumeUndispatched", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Throwable;)V", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "isActive", "isCancelled", "isCompleted", "Lkotlinx/coroutines/DisposableHandle;", "getParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", "setParentHandle", "(Lkotlinx/coroutines/DisposableHandle;)V", "parentHandle", "getState$kotlinx_coroutines_core", "kotlinx-coroutines-core", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlinx/coroutines/CancellableContinuation;"},
   k = 1,
   mv = {1, 4, 0}
)
public class CancellableContinuationImpl extends DispatchedTask implements CancellableContinuation, CoroutineStackFrame {
   private static final AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decision");
   private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");
   private volatile int _decision;
   private volatile Object _parentHandle;
   private volatile Object _state;
   private final CoroutineContext context;
   private final Continuation delegate;

   public CancellableContinuationImpl(Continuation var1, int var2) {
      super(var2);
      this.delegate = var1;
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var3;
         if (var2 != -1) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (!var3) {
            throw (Throwable)(new AssertionError());
         }
      }

      this.context = var1.getContext();
      this._decision = 0;
      this._state = Active.INSTANCE;
      this._parentHandle = null;
   }

   private final Void alreadyResumedError(Object var1) {
      throw (Throwable)(new IllegalStateException(("Already resumed, but proposed with update " + var1).toString()));
   }

   private final void callCancelHandler(Function1 var1, Throwable var2) {
      try {
         var1.invoke(var2);
      } catch (Throwable var4) {
         CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), (Throwable)(new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, var4)));
         return;
      }

   }

   private final void callCancelHandlerSafely(Function0 var1) {
      try {
         var1.invoke();
      } catch (Throwable var3) {
         CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), (Throwable)(new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, var3)));
         return;
      }

   }

   private final boolean cancelLater(Throwable var1) {
      if (!DispatchedTaskKt.isReusableMode(this.resumeMode)) {
         return false;
      } else {
         Continuation var3 = this.delegate;
         Continuation var2 = var3;
         if (!(var3 instanceof DispatchedContinuation)) {
            var2 = null;
         }

         DispatchedContinuation var4 = (DispatchedContinuation)var2;
         return var4 != null ? var4.postponeCancellation(var1) : false;
      }
   }

   private final boolean checkCompleted() {
      boolean var2 = this.isCompleted();
      if (!DispatchedTaskKt.isReusableMode(this.resumeMode)) {
         return var2;
      } else {
         Continuation var4 = this.delegate;
         Continuation var3 = var4;
         if (!(var4 instanceof DispatchedContinuation)) {
            var3 = null;
         }

         DispatchedContinuation var5 = (DispatchedContinuation)var3;
         boolean var1 = var2;
         if (var5 != null) {
            Throwable var6 = var5.checkPostponedCancellation((CancellableContinuation)this);
            var1 = var2;
            if (var6 != null) {
               if (!var2) {
                  this.cancel(var6);
               }

               var1 = true;
            }
         }

         return var1;
      }
   }

   private final void detachChildIfNonResuable() {
      if (!this.isReusable()) {
         this.detachChild$kotlinx_coroutines_core();
      }

   }

   private final void dispatchResume(int var1) {
      if (!this.tryResume()) {
         DispatchedTaskKt.dispatch(this, var1);
      }
   }

   private final DisposableHandle getParentHandle() {
      return (DisposableHandle)this._parentHandle;
   }

   private final boolean isReusable() {
      Continuation var2 = this.delegate;
      boolean var1;
      if (var2 instanceof DispatchedContinuation && ((DispatchedContinuation)var2).isReusable(this)) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private final CancelHandler makeCancelHandler(Function1 var1) {
      CancelHandler var2;
      if (var1 instanceof CancelHandler) {
         var2 = (CancelHandler)var1;
      } else {
         var2 = (CancelHandler)(new InvokeOnCancel(var1));
      }

      return var2;
   }

   private final void multipleHandlersError(Function1 var1, Object var2) {
      throw (Throwable)(new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + var1 + ", already has " + var2).toString()));
   }

   private final void resumeImpl(Object var1, int var2, Function1 var3) {
      while(true) {
         Object var4 = this._state;
         if (var4 instanceof NotCompleted) {
            Object var5 = this.resumedState((NotCompleted)var4, var1, var2, var3, (Object)null);
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var4, var5)) {
               continue;
            }

            this.detachChildIfNonResuable();
            this.dispatchResume(var2);
            return;
         }

         if (var4 instanceof CancelledContinuation) {
            CancelledContinuation var6 = (CancelledContinuation)var4;
            if (var6.makeResumed()) {
               if (var3 != null) {
                  this.callOnCancellation(var3, var6.cause);
               }

               return;
            }
         }

         this.alreadyResumedError(var1);
         throw new KotlinNothingValueException();
      }
   }

   // $FF: synthetic method
   static void resumeImpl$default(CancellableContinuationImpl var0, Object var1, int var2, Function1 var3, int var4, Object var5) {
      if (var5 == null) {
         if ((var4 & 4) != 0) {
            var3 = null;
            Function1 var6 = (Function1)null;
         }

         var0.resumeImpl(var1, var2, var3);
      } else {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
      }
   }

   private final Object resumedState(NotCompleted var1, Object var2, int var3, Function1 var4, Object var5) {
      Object var8;
      if (var2 instanceof CompletedExceptionally) {
         boolean var7 = DebugKt.getASSERTIONS_ENABLED();
         boolean var6 = true;
         boolean var9;
         if (var7) {
            if (var5 == null) {
               var9 = true;
            } else {
               var9 = false;
            }

            if (!var9) {
               throw (Throwable)(new AssertionError());
            }
         }

         var8 = var2;
         if (DebugKt.getASSERTIONS_ENABLED()) {
            if (var4 == null) {
               var9 = var6;
            } else {
               var9 = false;
            }

            if (!var9) {
               throw (Throwable)(new AssertionError());
            }

            var8 = var2;
         }
      } else if (!DispatchedTaskKt.isCancellableMode(var3) && var5 == null) {
         var8 = var2;
      } else {
         if (var4 == null && (!(var1 instanceof CancelHandler) || var1 instanceof BeforeResumeCancelHandler)) {
            var8 = var2;
            if (var5 == null) {
               return var8;
            }
         }

         NotCompleted var10 = var1;
         if (!(var1 instanceof CancelHandler)) {
            var10 = null;
         }

         var8 = new CompletedContinuation(var2, (CancelHandler)var10, var4, var5, (Throwable)null, 16, (DefaultConstructorMarker)null);
      }

      return var8;
   }

   private final void setParentHandle(DisposableHandle var1) {
      this._parentHandle = var1;
   }

   private final void setupCancellation() {
      if (!this.checkCompleted()) {
         if (this.getParentHandle() == null) {
            Job var1 = (Job)this.delegate.getContext().get((CoroutineContext.Key)Job.Key);
            if (var1 != null) {
               DisposableHandle var2 = Job.DefaultImpls.invokeOnCompletion$default(var1, true, false, (Function1)((CompletionHandlerBase)(new ChildContinuation(var1, this))), 2, (Object)null);
               this.setParentHandle(var2);
               if (this.isCompleted() && !this.isReusable()) {
                  var2.dispose();
                  this.setParentHandle((DisposableHandle)NonDisposableHandle.INSTANCE);
               }
            }

         }
      }
   }

   private final boolean tryResume() {
      do {
         int var1 = this._decision;
         if (var1 != 0) {
            if (var1 == 1) {
               return false;
            }

            throw (Throwable)(new IllegalStateException("Already resumed".toString()));
         }
      } while(!_decision$FU.compareAndSet(this, 0, 2));

      return true;
   }

   private final Symbol tryResumeImpl(Object var1, Object var2, Function1 var3) {
      while(true) {
         Object var6 = this._state;
         Object var5;
         if (var6 instanceof NotCompleted) {
            var5 = this.resumedState((NotCompleted)var6, var1, this.resumeMode, var3, var2);
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var6, var5)) {
               continue;
            }

            this.detachChildIfNonResuable();
            return CancellableContinuationImplKt.RESUME_TOKEN;
         }

         boolean var4 = var6 instanceof CompletedContinuation;
         var5 = null;
         Symbol var7 = (Symbol)var5;
         if (var4) {
            var7 = (Symbol)var5;
            if (var2 != null) {
               CompletedContinuation var8 = (CompletedContinuation)var6;
               var7 = (Symbol)var5;
               if (var8.idempotentResume == var2) {
                  if (DebugKt.getASSERTIONS_ENABLED() && !Intrinsics.areEqual(var8.result, var1)) {
                     throw (Throwable)(new AssertionError());
                  }

                  var7 = CancellableContinuationImplKt.RESUME_TOKEN;
               }
            }
         }

         return var7;
      }
   }

   private final boolean trySuspend() {
      do {
         int var1 = this._decision;
         if (var1 != 0) {
            if (var1 == 2) {
               return false;
            }

            throw (Throwable)(new IllegalStateException("Already suspended".toString()));
         }
      } while(!_decision$FU.compareAndSet(this, 0, 1));

      return true;
   }

   public final void callCancelHandler(CancelHandler var1, Throwable var2) {
      try {
         var1.invoke(var2);
      } catch (Throwable var4) {
         CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), (Throwable)(new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, var4)));
         return;
      }

   }

   public final void callOnCancellation(Function1 var1, Throwable var2) {
      try {
         var1.invoke(var2);
      } catch (Throwable var4) {
         CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), (Throwable)(new CompletionHandlerException("Exception in resume onCancellation handler for " + this, var4)));
         return;
      }

   }

   public boolean cancel(Throwable var1) {
      boolean var2;
      Object var3;
      CancelledContinuation var6;
      do {
         var3 = this._state;
         if (!(var3 instanceof NotCompleted)) {
            return false;
         }

         Continuation var4 = (Continuation)this;
         var2 = var3 instanceof CancelHandler;
         var6 = new CancelledContinuation(var4, var1, var2);
      } while(!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var3, var6));

      if (!var2) {
         var3 = null;
      }

      CancelHandler var5 = (CancelHandler)var3;
      if (var5 != null) {
         this.callCancelHandler(var5, var1);
      }

      this.detachChildIfNonResuable();
      this.dispatchResume(this.resumeMode);
      return true;
   }

   public void cancelCompletedResult$kotlinx_coroutines_core(Object var1, Throwable var2) {
      while(true) {
         Object var3 = this._state;
         if (!(var3 instanceof NotCompleted)) {
            if (var3 instanceof CompletedExceptionally) {
               return;
            }

            if (var3 instanceof CompletedContinuation) {
               CompletedContinuation var4 = (CompletedContinuation)var3;
               if (var4.getCancelled() ^ true) {
                  CompletedContinuation var5 = CompletedContinuation.copy$default(var4, (Object)null, (CancelHandler)null, (Function1)null, (Object)null, var2, 15, (Object)null);
                  if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var3, var5)) {
                     continue;
                  }

                  var4.invokeHandlers(this, var2);
                  return;
               }

               throw (Throwable)(new IllegalStateException("Must be called at most once".toString()));
            }

            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var3, new CompletedContinuation(var3, (CancelHandler)null, (Function1)null, (Object)null, var2, 14, (DefaultConstructorMarker)null))) {
               continue;
            }

            return;
         }

         throw (Throwable)(new IllegalStateException("Not completed".toString()));
      }
   }

   public void completeResume(Object var1) {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var2;
         if (var1 == CancellableContinuationImplKt.RESUME_TOKEN) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            throw (Throwable)(new AssertionError());
         }
      }

      this.dispatchResume(this.resumeMode);
   }

   public final void detachChild$kotlinx_coroutines_core() {
      DisposableHandle var1 = this.getParentHandle();
      if (var1 != null) {
         var1.dispose();
      }

      this.setParentHandle((DisposableHandle)NonDisposableHandle.INSTANCE);
   }

   public CoroutineStackFrame getCallerFrame() {
      Continuation var2 = this.delegate;
      Continuation var1 = var2;
      if (!(var2 instanceof CoroutineStackFrame)) {
         var1 = null;
      }

      return (CoroutineStackFrame)var1;
   }

   public CoroutineContext getContext() {
      return this.context;
   }

   public Throwable getContinuationCancellationCause(Job var1) {
      return (Throwable)var1.getCancellationException();
   }

   public final Continuation getDelegate$kotlinx_coroutines_core() {
      return this.delegate;
   }

   public Throwable getExceptionalResult$kotlinx_coroutines_core(Object var1) {
      Throwable var2 = super.getExceptionalResult$kotlinx_coroutines_core(var1);
      Throwable var4;
      if (var2 != null) {
         Continuation var3 = this.delegate;
         var4 = var2;
         if (DebugKt.getRECOVER_STACK_TRACES()) {
            if (!(var3 instanceof CoroutineStackFrame)) {
               var4 = var2;
            } else {
               var4 = StackTraceRecoveryKt.access$recoverFromStackFrame(var2, (CoroutineStackFrame)var3);
            }
         }
      } else {
         var4 = null;
      }

      return var4;
   }

   public final Object getResult() {
      this.setupCancellation();
      if (this.trySuspend()) {
         return IntrinsicsKt.getCOROUTINE_SUSPENDED();
      } else {
         Object var1 = this.getState$kotlinx_coroutines_core();
         Continuation var3;
         Throwable var4;
         Throwable var5;
         if (var1 instanceof CompletedExceptionally) {
            var5 = ((CompletedExceptionally)var1).cause;
            var3 = (Continuation)this;
            var4 = var5;
            if (DebugKt.getRECOVER_STACK_TRACES()) {
               if (!(var3 instanceof CoroutineStackFrame)) {
                  var4 = var5;
               } else {
                  var4 = StackTraceRecoveryKt.access$recoverFromStackFrame(var5, (CoroutineStackFrame)var3);
               }
            }

            throw var4;
         } else {
            if (DispatchedTaskKt.isCancellableMode(this.resumeMode)) {
               Job var2 = (Job)this.getContext().get((CoroutineContext.Key)Job.Key);
               if (var2 != null && !var2.isActive()) {
                  var5 = (Throwable)var2.getCancellationException();
                  this.cancelCompletedResult$kotlinx_coroutines_core(var1, var5);
                  var3 = (Continuation)this;
                  var4 = var5;
                  if (DebugKt.getRECOVER_STACK_TRACES()) {
                     if (!(var3 instanceof CoroutineStackFrame)) {
                        var4 = var5;
                     } else {
                        var4 = StackTraceRecoveryKt.access$recoverFromStackFrame(var5, (CoroutineStackFrame)var3);
                     }
                  }

                  throw var4;
               }
            }

            return this.getSuccessfulResult$kotlinx_coroutines_core(var1);
         }
      }
   }

   public StackTraceElement getStackTraceElement() {
      return null;
   }

   public final Object getState$kotlinx_coroutines_core() {
      return this._state;
   }

   public Object getSuccessfulResult$kotlinx_coroutines_core(Object var1) {
      Object var2 = var1;
      if (var1 instanceof CompletedContinuation) {
         var2 = ((CompletedContinuation)var1).result;
      }

      return var2;
   }

   public void initCancellability() {
      this.setupCancellation();
   }

   public void invokeOnCancellation(Function1 var1) {
      CancelHandler var4 = this.makeCancelHandler(var1);

      Object var3;
      do {
         while(true) {
            var3 = this._state;
            if (var3 instanceof Active) {
               break;
            }

            if (var3 instanceof CancelHandler) {
               this.multipleHandlersError(var1, var3);
            } else {
               boolean var2 = var3 instanceof CompletedExceptionally;
               if (var2) {
                  if (!((CompletedExceptionally)var3).makeHandled()) {
                     this.multipleHandlersError(var1, var3);
                  }

                  if (var3 instanceof CancelledContinuation) {
                     var4 = null;
                     if (!var2) {
                        var3 = null;
                     }

                     CompletedExceptionally var7 = (CompletedExceptionally)var3;
                     Throwable var6 = var4;
                     if (var7 != null) {
                        var6 = var7.cause;
                     }

                     this.callCancelHandler(var1, var6);
                  }

                  return;
               }

               CompletedContinuation var5;
               if (var3 instanceof CompletedContinuation) {
                  var5 = (CompletedContinuation)var3;
                  if (var5.cancelHandler != null) {
                     this.multipleHandlersError(var1, var3);
                  }

                  if (var4 instanceof BeforeResumeCancelHandler) {
                     return;
                  }

                  if (var5.getCancelled()) {
                     this.callCancelHandler(var1, var5.cancelCause);
                     return;
                  }

                  var5 = CompletedContinuation.copy$default(var5, (Object)null, var4, (Function1)null, (Object)null, (Throwable)null, 29, (Object)null);
                  if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var3, var5)) {
                     return;
                  }
               } else {
                  if (var4 instanceof BeforeResumeCancelHandler) {
                     return;
                  }

                  var5 = new CompletedContinuation(var3, var4, (Function1)null, (Object)null, (Throwable)null, 28, (DefaultConstructorMarker)null);
                  if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var3, var5)) {
                     return;
                  }
               }
            }
         }
      } while(!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var3, var4));

   }

   public boolean isActive() {
      return this.getState$kotlinx_coroutines_core() instanceof NotCompleted;
   }

   public boolean isCancelled() {
      return this.getState$kotlinx_coroutines_core() instanceof CancelledContinuation;
   }

   public boolean isCompleted() {
      return this.getState$kotlinx_coroutines_core() instanceof NotCompleted ^ true;
   }

   protected String nameString() {
      return "CancellableContinuation";
   }

   public final void parentCancelled$kotlinx_coroutines_core(Throwable var1) {
      if (!this.cancelLater(var1)) {
         this.cancel(var1);
         this.detachChildIfNonResuable();
      }
   }

   public final boolean resetStateReusable() {
      boolean var1;
      if (DebugKt.getASSERTIONS_ENABLED()) {
         if (this.resumeMode == 2) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (!var1) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (DebugKt.getASSERTIONS_ENABLED()) {
         if (this.getParentHandle() != NonDisposableHandle.INSTANCE) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (!var1) {
            throw (Throwable)(new AssertionError());
         }
      }

      Object var2 = this._state;
      if (DebugKt.getASSERTIONS_ENABLED() && !(var2 instanceof NotCompleted ^ true)) {
         throw (Throwable)(new AssertionError());
      } else if (var2 instanceof CompletedContinuation && ((CompletedContinuation)var2).idempotentResume != null) {
         this.detachChild$kotlinx_coroutines_core();
         return false;
      } else {
         this._decision = 0;
         this._state = Active.INSTANCE;
         return true;
      }
   }

   public void resume(Object var1, Function1 var2) {
      this.resumeImpl(var1, this.resumeMode, var2);
   }

   public void resumeUndispatched(CoroutineDispatcher var1, Object var2) {
      Continuation var5 = this.delegate;
      boolean var4 = var5 instanceof DispatchedContinuation;
      Object var6 = null;
      if (!var4) {
         var5 = null;
      }

      DispatchedContinuation var7 = (DispatchedContinuation)var5;
      CoroutineDispatcher var8 = (CoroutineDispatcher)var6;
      if (var7 != null) {
         var8 = var7.dispatcher;
      }

      int var3;
      if (var8 == var1) {
         var3 = 4;
      } else {
         var3 = this.resumeMode;
      }

      resumeImpl$default(this, var2, var3, (Function1)null, 4, (Object)null);
   }

   public void resumeUndispatchedWithException(CoroutineDispatcher var1, Throwable var2) {
      Continuation var5 = this.delegate;
      boolean var4 = var5 instanceof DispatchedContinuation;
      Object var6 = null;
      if (!var4) {
         var5 = null;
      }

      DispatchedContinuation var9 = (DispatchedContinuation)var5;
      CompletedExceptionally var7 = new CompletedExceptionally(var2, false, 2, (DefaultConstructorMarker)null);
      CoroutineDispatcher var8 = (CoroutineDispatcher)var6;
      if (var9 != null) {
         var8 = var9.dispatcher;
      }

      int var3;
      if (var8 == var1) {
         var3 = 4;
      } else {
         var3 = this.resumeMode;
      }

      resumeImpl$default(this, var7, var3, (Function1)null, 4, (Object)null);
   }

   public void resumeWith(Object var1) {
      resumeImpl$default(this, CompletionStateKt.toState(var1, (CancellableContinuation)this), this.resumeMode, (Function1)null, 4, (Object)null);
   }

   public Object takeState$kotlinx_coroutines_core() {
      return this.getState$kotlinx_coroutines_core();
   }

   public String toString() {
      return this.nameString() + '(' + DebugStringsKt.toDebugString(this.delegate) + "){" + this.getState$kotlinx_coroutines_core() + "}@" + DebugStringsKt.getHexAddress(this);
   }

   public Object tryResume(Object var1, Object var2) {
      return this.tryResumeImpl(var1, var2, (Function1)null);
   }

   public Object tryResume(Object var1, Object var2, Function1 var3) {
      return this.tryResumeImpl(var1, var2, var3);
   }

   public Object tryResumeWithException(Throwable var1) {
      return this.tryResumeImpl(new CompletedExceptionally(var1, false, 2, (DefaultConstructorMarker)null), (Object)null, (Function1)null);
   }
}
