package kotlinx.coroutines.selects;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletionHandlerBase;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020Y2\b\u0012\u0004\u0012\u00028\u00000Z2\b\u0012\u0004\u0012\u00028\u00000[2\b\u0012\u0004\u0012\u00028\u00000\u00022\u00060Bj\u0002`C:\u0004TUVWB\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ.\u0010\u0011\u001a\u00020\b2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0082\b¢\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\n\u0018\u00010\u0015j\u0004\u0018\u0001`\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001d\u0010\fJ8\u0010!\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001e2\u001c\u0010\u0010\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000e0 H\u0016ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0019\u0010%\u001a\u0004\u0018\u00010\u000e2\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u0019H\u0016¢\u0006\u0004\b(\u0010\u001cJ \u0010+\u001a\u00020\b2\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000)H\u0016ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u000f\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/J\u000f\u00101\u001a\u000200H\u0016¢\u0006\u0004\b1\u00102J\u001b\u00105\u001a\u0004\u0018\u00010\u000e2\b\u00104\u001a\u0004\u0018\u000103H\u0016¢\u0006\u0004\b5\u00106J5\u00108\u001a\u00020\b*\u0002072\u001c\u0010\u0010\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000e0 H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b8\u00109JG\u00108\u001a\u00020\b\"\u0004\b\u0001\u0010:*\b\u0012\u0004\u0012\u00028\u00010;2\"\u0010\u0010\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000e0<H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b8\u0010=J[\u00108\u001a\u00020\b\"\u0004\b\u0001\u0010>\"\u0004\b\u0002\u0010:*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020?2\u0006\u0010@\u001a\u00028\u00012\"\u0010\u0010\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000e0<H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b8\u0010AR\u001e\u0010F\u001a\n\u0018\u00010Bj\u0004\u0018\u0001`C8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001c\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0016\u0010M\u001a\u00020J8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0016\u0010N\u001a\u0002008V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u00102R(\u0010R\u001a\u0004\u0018\u00010\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u00068B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010\nR\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010S\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006X"},
   d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl;", "R", "Lkotlin/coroutines/Continuation;", "uCont", "<init>", "(Lkotlin/coroutines/Continuation;)V", "Lkotlinx/coroutines/DisposableHandle;", "handle", "", "disposeOnSelect", "(Lkotlinx/coroutines/DisposableHandle;)V", "doAfterSelect", "()V", "Lkotlin/Function0;", "", "value", "block", "doResume", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getResult", "()Ljava/lang/Object;", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "", "e", "handleBuilderException", "(Ljava/lang/Throwable;)V", "initCancellability", "", "timeMillis", "Lkotlin/Function1;", "onTimeout", "(JLkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/internal/AtomicDesc;", "desc", "performAtomicTrySelect", "(Lkotlinx/coroutines/internal/AtomicDesc;)Ljava/lang/Object;", "exception", "resumeSelectWithException", "Lkotlin/Result;", "result", "resumeWith", "(Ljava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "", "trySelect", "()Z", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "trySelectOther", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectClause0;", "invoke", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "getCompletion", "()Lkotlin/coroutines/Continuation;", "completion", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "context", "isSelected", "getParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", "setParentHandle", "parentHandle", "Lkotlin/coroutines/Continuation;", "AtomicSelectOp", "DisposeNode", "PairSelectOp", "SelectOnCancelling", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlinx/coroutines/selects/SelectInstance;"},
   k = 1,
   mv = {1, 4, 0}
)
public final class SelectBuilderImpl extends LockFreeLinkedListHead implements SelectBuilder, SelectInstance, Continuation, CoroutineStackFrame {
   static final AtomicReferenceFieldUpdater _result$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");
   static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");
   private volatile Object _parentHandle;
   volatile Object _result;
   volatile Object _state;
   private final Continuation uCont;

   public SelectBuilderImpl(Continuation var1) {
      this.uCont = var1;
      this._state = SelectKt.getNOT_SELECTED();
      this._result = SelectKt.access$getUNDECIDED$p();
      this._parentHandle = null;
   }

   private final void doAfterSelect() {
      DisposableHandle var1 = this.getParentHandle();
      if (var1 != null) {
         var1.dispose();
      }

      LockFreeLinkedListHead var2 = (LockFreeLinkedListHead)this;
      Object var3 = var2.getNext();
      if (var3 != null) {
         for(LockFreeLinkedListNode var4 = (LockFreeLinkedListNode)var3; Intrinsics.areEqual((Object)var4, (Object)var2) ^ true; var4 = var4.getNextNode()) {
            if (var4 instanceof DisposeNode) {
               ((DisposeNode)var4).handle.dispose();
            }
         }

      } else {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      }
   }

   private final void doResume(Function0 var1, Function0 var2) {
      if (DebugKt.getASSERTIONS_ENABLED() && !this.isSelected()) {
         throw (Throwable)(new AssertionError());
      } else {
         Object var3;
         label26:
         do {
            do {
               var3 = this._result;
               if (var3 == SelectKt.access$getUNDECIDED$p()) {
                  var3 = var1.invoke();
                  continue label26;
               }

               if (var3 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                  throw (Throwable)(new IllegalStateException("Already resumed"));
               }
            } while(!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p()));

            var2.invoke();
            return;
         } while(!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, SelectKt.access$getUNDECIDED$p(), var3));

      }
   }

   private final DisposableHandle getParentHandle() {
      return (DisposableHandle)this._parentHandle;
   }

   private final void initCancellability() {
      Job var1 = (Job)this.getContext().get((CoroutineContext.Key)Job.Key);
      if (var1 != null) {
         DisposableHandle var2 = Job.DefaultImpls.invokeOnCompletion$default(var1, true, false, (Function1)((CompletionHandlerBase)(new SelectOnCancelling(this, var1))), 2, (Object)null);
         this.setParentHandle(var2);
         if (this.isSelected()) {
            var2.dispose();
         }
      }

   }

   private final void setParentHandle(DisposableHandle var1) {
      this._parentHandle = var1;
   }

   public void disposeOnSelect(DisposableHandle var1) {
      DisposeNode var2 = new DisposeNode(var1);
      if (!this.isSelected()) {
         this.addLast((LockFreeLinkedListNode)var2);
         if (!this.isSelected()) {
            return;
         }
      }

      var1.dispose();
   }

   public CoroutineStackFrame getCallerFrame() {
      Continuation var2 = this.uCont;
      Continuation var1 = var2;
      if (!(var2 instanceof CoroutineStackFrame)) {
         var1 = null;
      }

      return (CoroutineStackFrame)var1;
   }

   public Continuation getCompletion() {
      return (Continuation)this;
   }

   public CoroutineContext getContext() {
      return this.uCont.getContext();
   }

   public final Object getResult() {
      if (!this.isSelected()) {
         this.initCancellability();
      }

      Object var2 = this._result;
      Object var1 = var2;
      if (var2 == SelectKt.access$getUNDECIDED$p()) {
         if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, SelectKt.access$getUNDECIDED$p(), IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
         }

         var1 = this._result;
      }

      if (var1 != SelectKt.access$getRESUMED$p()) {
         if (!(var1 instanceof CompletedExceptionally)) {
            return var1;
         } else {
            throw ((CompletedExceptionally)var1).cause;
         }
      } else {
         throw (Throwable)(new IllegalStateException("Already resumed"));
      }
   }

   public StackTraceElement getStackTraceElement() {
      return null;
   }

   public final void handleBuilderException(Throwable var1) {
      if (this.trySelect()) {
         Result.Companion var2 = Result.Companion;
         this.resumeWith(Result.constructor_impl(ResultKt.createFailure(var1)));
      } else if (!(var1 instanceof CancellationException)) {
         Object var4 = this.getResult();
         if (var4 instanceof CompletedExceptionally) {
            Throwable var5 = ((CompletedExceptionally)var4).cause;
            if (DebugKt.getRECOVER_STACK_TRACES()) {
               var5 = StackTraceRecoveryKt.unwrapImpl(var5);
            }

            Throwable var3;
            if (!DebugKt.getRECOVER_STACK_TRACES()) {
               var3 = var1;
            } else {
               var3 = StackTraceRecoveryKt.unwrapImpl(var1);
            }

            if (var5 == var3) {
               return;
            }
         }

         CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), var1);
      }

   }

   public void invoke(SelectClause0 var1, Function1 var2) {
      var1.registerSelectClause0((SelectInstance)this, var2);
   }

   public void invoke(SelectClause1 var1, Function2 var2) {
      var1.registerSelectClause1((SelectInstance)this, var2);
   }

   public void invoke(SelectClause2 var1, Object var2, Function2 var3) {
      var1.registerSelectClause2((SelectInstance)this, var2, var3);
   }

   public void invoke(SelectClause2 var1, Function2 var2) {
      DefaultImpls.invoke(this, var1, var2);
   }

   public boolean isSelected() {
      while(true) {
         Object var1 = this._state;
         if (var1 == SelectKt.getNOT_SELECTED()) {
            return false;
         }

         if (!(var1 instanceof OpDescriptor)) {
            return true;
         }

         ((OpDescriptor)var1).perform(this);
      }
   }

   public void onTimeout(long var1, Function1 var3) {
      if (var1 <= 0L) {
         if (this.trySelect()) {
            UndispatchedKt.startCoroutineUnintercepted(var3, this.getCompletion());
         }

      } else {
         Runnable var4 = (Runnable)(new Runnable(this, var3) {
            final Function1 $block$inlined;
            final SelectBuilderImpl this$0;

            public {
               this.this$0 = var1;
               this.$block$inlined = var2;
            }

            public final void run() {
               if (this.this$0.trySelect()) {
                  CancellableKt.startCoroutineCancellable(this.$block$inlined, this.this$0.getCompletion());
               }

            }
         });
         this.disposeOnSelect(DelayKt.getDelay(this.getContext()).invokeOnTimeout(var1, var4, this.getContext()));
      }
   }

   public Object performAtomicTrySelect(AtomicDesc var1) {
      return (new AtomicSelectOp(this, var1)).perform((Object)null);
   }

   public void resumeSelectWithException(Throwable var1) {
      if (DebugKt.getASSERTIONS_ENABLED() && !this.isSelected()) {
         throw (Throwable)(new AssertionError());
      } else {
         while(true) {
            Object var2 = this._result;
            Continuation var4;
            if (var2 != SelectKt.access$getUNDECIDED$p()) {
               if (var2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                  throw (Throwable)(new IllegalStateException("Already resumed"));
               }

               if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p())) {
                  var4 = IntrinsicsKt.intercepted(this.uCont);
                  Result.Companion var3 = Result.Companion;
                  var4.resumeWith(Result.constructor_impl(ResultKt.createFailure(var1)));
                  break;
               }
            } else {
               var4 = this.uCont;
               Throwable var5;
               if (DebugKt.getRECOVER_STACK_TRACES() && var4 instanceof CoroutineStackFrame) {
                  var5 = StackTraceRecoveryKt.access$recoverFromStackFrame(var1, (CoroutineStackFrame)var4);
               } else {
                  var5 = var1;
               }

               CompletedExceptionally var6 = new CompletedExceptionally(var5, false, 2, (DefaultConstructorMarker)null);
               if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, SelectKt.access$getUNDECIDED$p(), var6)) {
                  break;
               }
            }
         }

      }
   }

   public void resumeWith(Object var1) {
      if (DebugKt.getASSERTIONS_ENABLED() && !this.isSelected()) {
         throw (Throwable)(new AssertionError());
      } else {
         while(true) {
            Object var2 = this._result;
            if (var2 == SelectKt.access$getUNDECIDED$p()) {
               var2 = CompletionStateKt.toState$default(var1, (Function1)null, 1, (Object)null);
               if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, SelectKt.access$getUNDECIDED$p(), var2)) {
                  break;
               }
            } else {
               if (var2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                  throw (Throwable)(new IllegalStateException("Already resumed"));
               }

               if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p())) {
                  if (Result.isFailure_impl(var1)) {
                     Continuation var3 = this.uCont;
                     Throwable var6 = Result.exceptionOrNull_impl(var1);
                     Intrinsics.checkNotNull(var6);
                     Result.Companion var4 = Result.Companion;
                     Throwable var5 = var6;
                     if (DebugKt.getRECOVER_STACK_TRACES()) {
                        if (!(var3 instanceof CoroutineStackFrame)) {
                           var5 = var6;
                        } else {
                           var5 = StackTraceRecoveryKt.access$recoverFromStackFrame(var6, (CoroutineStackFrame)var3);
                        }
                     }

                     var3.resumeWith(Result.constructor_impl(ResultKt.createFailure(var5)));
                  } else {
                     this.uCont.resumeWith(var1);
                  }
                  break;
               }
            }
         }

      }
   }

   public String toString() {
      return "SelectInstance(state=" + this._state + ", result=" + this._result + ')';
   }

   public boolean trySelect() {
      Object var2 = this.trySelectOther((PrepareOp)null);
      boolean var1;
      if (var2 == CancellableContinuationImplKt.RESUME_TOKEN) {
         var1 = true;
      } else {
         if (var2 != null) {
            throw (Throwable)(new IllegalStateException(("Unexpected trySelectIdempotent result " + var2).toString()));
         }

         var1 = false;
      }

      return var1;
   }

   public Object trySelectOther(PrepareOp var1) {
      while(true) {
         Object var5 = this._state;
         if (var5 == SelectKt.getNOT_SELECTED()) {
            if (var1 == null) {
               if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, SelectKt.getNOT_SELECTED(), (Object)null)) {
                  continue;
               }
            } else {
               PairSelectOp var7 = new PairSelectOp(var1);
               if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, SelectKt.getNOT_SELECTED(), var7)) {
                  continue;
               }

               Object var6 = var7.perform(this);
               if (var6 != null) {
                  return var6;
               }
            }

            this.doAfterSelect();
            return CancellableContinuationImplKt.RESUME_TOKEN;
         } else {
            if (!(var5 instanceof OpDescriptor)) {
               if (var1 == null) {
                  return null;
               }

               if (var5 == var1.desc) {
                  return CancellableContinuationImplKt.RESUME_TOKEN;
               }

               return null;
            }

            if (var1 != null) {
               AtomicOp var2 = var1.getAtomicOp();
               if (var2 instanceof AtomicSelectOp) {
                  SelectBuilderImpl var4 = ((AtomicSelectOp)var2).impl;
                  SelectBuilderImpl var3 = (SelectBuilderImpl)this;
                  if (var4 == this) {
                     throw (Throwable)(new IllegalStateException("Cannot use matching select clauses on the same object".toString()));
                  }
               }

               if (var2.isEarlierThan((OpDescriptor)var5)) {
                  return AtomicKt.RETRY_ATOMIC;
               }
            }

            ((OpDescriptor)var5).perform(this);
         }
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0019\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001c\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0002J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0002R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"},
      d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$AtomicSelectOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "", "impl", "Lkotlinx/coroutines/selects/SelectBuilderImpl;", "desc", "Lkotlinx/coroutines/internal/AtomicDesc;", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;Lkotlinx/coroutines/internal/AtomicDesc;)V", "opSequence", "", "getOpSequence", "()J", "complete", "", "affected", "failure", "completeSelect", "prepare", "prepareSelectOp", "toString", "", "undoPrepare", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class AtomicSelectOp extends AtomicOp {
      public final AtomicDesc desc;
      public final SelectBuilderImpl impl;
      private final long opSequence;

      public AtomicSelectOp(SelectBuilderImpl var1, AtomicDesc var2) {
         this.impl = var1;
         this.desc = var2;
         this.opSequence = SelectKt.access$getSelectOpSequenceNumber$p().next();
         var2.setAtomicOp((AtomicOp)this);
      }

      private final void completeSelect(Object var1) {
         boolean var2;
         if (var1 == null) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            var1 = null;
         } else {
            var1 = SelectKt.getNOT_SELECTED();
         }

         SelectBuilderImpl var3 = this.impl;
         if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(SelectBuilderImpl._state$FU, var3, this, var1) && var2) {
            this.impl.doAfterSelect();
         }

      }

      private final Object prepareSelectOp() {
         SelectBuilderImpl var1 = this.impl;

         while(true) {
            Object var2 = var1._state;
            AtomicSelectOp var3 = (AtomicSelectOp)this;
            if (var2 == this) {
               return null;
            }

            if (var2 instanceof OpDescriptor) {
               ((OpDescriptor)var2).perform(this.impl);
            } else {
               if (var2 != SelectKt.getNOT_SELECTED()) {
                  return SelectKt.getALREADY_SELECTED();
               }

               SelectBuilderImpl var4 = this.impl;
               if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(SelectBuilderImpl._state$FU, var4, SelectKt.getNOT_SELECTED(), this)) {
                  return null;
               }
            }
         }
      }

      private final void undoPrepare() {
         SelectBuilderImpl var1 = this.impl;
         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(SelectBuilderImpl._state$FU, var1, this, SelectKt.getNOT_SELECTED());
      }

      public void complete(Object var1, Object var2) {
         this.completeSelect(var2);
         this.desc.complete((AtomicOp)this, var2);
      }

      public long getOpSequence() {
         return this.opSequence;
      }

      public Object prepare(Object var1) {
         Object var2;
         if (var1 == null) {
            var2 = this.prepareSelectOp();
            if (var2 != null) {
               return var2;
            }
         }

         try {
            var2 = this.desc.prepare((AtomicOp)this);
            return var2;
         } finally {
            if (var1 == null) {
               this.undoPrepare();
            }

         }
      }

      public String toString() {
         return "AtomicSelectOp(sequence=" + this.getOpSequence() + ')';
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$DisposeNode;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "handle", "Lkotlinx/coroutines/DisposableHandle;", "(Lkotlinx/coroutines/DisposableHandle;)V", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class DisposeNode extends LockFreeLinkedListNode {
      public final DisposableHandle handle;

      public DisposeNode(DisposableHandle var1) {
         this.handle = var1;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"},
      d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$PairSelectOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)V", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "perform", "", "affected", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class PairSelectOp extends OpDescriptor {
      public final PrepareOp otherOp;

      public PairSelectOp(PrepareOp var1) {
         this.otherOp = var1;
      }

      public AtomicOp getAtomicOp() {
         return this.otherOp.getAtomicOp();
      }

      public Object perform(Object var1) {
         if (var1 != null) {
            SelectBuilderImpl var2 = (SelectBuilderImpl)var1;
            this.otherOp.finishPrepare();
            Object var3 = this.otherOp.getAtomicOp().decide((Object)null);
            if (var3 == null) {
               var1 = this.otherOp.desc;
            } else {
               var1 = SelectKt.getNOT_SELECTED();
            }

            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(SelectBuilderImpl._state$FU, var2, this, var1);
            return var3;
         } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.selects.SelectBuilderImpl<*>");
         }
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"},
      d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$SelectOnCancelling;", "Lkotlinx/coroutines/JobCancellingNode;", "Lkotlinx/coroutines/Job;", "job", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;Lkotlinx/coroutines/Job;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class SelectOnCancelling extends JobCancellingNode {
      final SelectBuilderImpl this$0;

      public SelectOnCancelling(SelectBuilderImpl var1, Job var2) {
         super(var2);
         this.this$0 = var1;
      }

      public void invoke(Throwable var1) {
         if (this.this$0.trySelect()) {
            this.this$0.resumeSelectWithException((Throwable)this.job.getCancellationException());
         }

      }

      public String toString() {
         return "SelectOnCancelling[" + this.this$0 + ']';
      }
   }
}
