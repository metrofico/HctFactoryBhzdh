package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"},
   d2 = {"Lkotlinx/coroutines/ChildContinuation;", "Lkotlinx/coroutines/JobCancellingNode;", "Lkotlinx/coroutines/Job;", "parent", "child", "Lkotlinx/coroutines/CancellableContinuationImpl;", "(Lkotlinx/coroutines/Job;Lkotlinx/coroutines/CancellableContinuationImpl;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ChildContinuation extends JobCancellingNode {
   public final CancellableContinuationImpl child;

   public ChildContinuation(Job var1, CancellableContinuationImpl var2) {
      super(var1);
      this.child = var2;
   }

   public void invoke(Throwable var1) {
      CancellableContinuationImpl var2 = this.child;
      var2.parentCancelled$kotlinx_coroutines_core(var2.getContinuationCancellationCause(this.job));
   }

   public String toString() {
      return "ChildContinuation[" + this.child + ']';
   }
}
