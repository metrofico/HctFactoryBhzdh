package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"},
   d2 = {"Lkotlinx/coroutines/ResumeAwaitOnCompletion;", "T", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/JobSupport;", "job", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/CancellableContinuationImpl;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class ResumeAwaitOnCompletion extends JobNode {
   private final CancellableContinuationImpl continuation;

   public ResumeAwaitOnCompletion(JobSupport var1, CancellableContinuationImpl var2) {
      super((Job)var1);
      this.continuation = var2;
   }

   public void invoke(Throwable var1) {
      Object var2 = ((JobSupport)this.job).getState$kotlinx_coroutines_core();
      if (DebugKt.getASSERTIONS_ENABLED() && !(var2 instanceof Incomplete ^ true)) {
         throw (Throwable)(new AssertionError());
      } else {
         Continuation var4;
         Result.Companion var5;
         if (var2 instanceof CompletedExceptionally) {
            var4 = (Continuation)this.continuation;
            Throwable var3 = ((CompletedExceptionally)var2).cause;
            var5 = Result.Companion;
            var4.resumeWith(Result.constructor_impl(ResultKt.createFailure(var3)));
         } else {
            var4 = (Continuation)this.continuation;
            Object var6 = JobSupportKt.unboxState(var2);
            var5 = Result.Companion;
            var4.resumeWith(Result.constructor_impl(var6));
         }

      }
   }

   public String toString() {
      return "ResumeAwaitOnCompletion[" + this.continuation + ']';
   }
}
