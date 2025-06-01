package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000\u0015B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0014¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0014¢\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0012¨\u0006\u0014"},
   d2 = {"Lkotlinx/coroutines/DispatchedCoroutine;", "T", "Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlin/coroutines/Continuation;", "uCont", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "", "state", "", "afterCompletion", "(Ljava/lang/Object;)V", "afterResume", "getResult", "()Ljava/lang/Object;", "", "tryResume", "()Z", "trySuspend", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/ScopeCoroutine;"},
   k = 1,
   mv = {1, 4, 0}
)
final class DispatchedCoroutine extends ScopeCoroutine {
   private static final AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(DispatchedCoroutine.class, "_decision");
   private volatile int _decision = 0;

   public DispatchedCoroutine(CoroutineContext var1, Continuation var2) {
      super(var1, var2);
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

   protected void afterCompletion(Object var1) {
      this.afterResume(var1);
   }

   protected void afterResume(Object var1) {
      if (!this.tryResume()) {
         DispatchedContinuationKt.resumeCancellableWith$default(IntrinsicsKt.intercepted(this.uCont), CompletionStateKt.recoverResult(var1, this.uCont), (Function1)null, 2, (Object)null);
      }
   }

   public final Object getResult() {
      if (this.trySuspend()) {
         return IntrinsicsKt.getCOROUTINE_SUSPENDED();
      } else {
         Object var1 = JobSupportKt.unboxState(this.getState$kotlinx_coroutines_core());
         if (!(var1 instanceof CompletedExceptionally)) {
            return var1;
         } else {
            throw ((CompletedExceptionally)var1).cause;
         }
      }
   }
}
