package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0003R\u0014\u0010\u0006\u001a\u00020\u0007X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\u0010"},
   d2 = {"Lkotlinx/coroutines/JobImpl;", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/CompletableJob;", "parent", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "handlesException", "", "getHandlesException$kotlinx_coroutines_core", "()Z", "onCancelComplete", "getOnCancelComplete$kotlinx_coroutines_core", "complete", "completeExceptionally", "exception", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class JobImpl extends JobSupport implements CompletableJob {
   private final boolean handlesException;

   public JobImpl(Job var1) {
      super(true);
      this.initParentJobInternal$kotlinx_coroutines_core(var1);
      this.handlesException = this.handlesException();
   }

   private final boolean handlesException() {
      ChildHandle var2 = this.getParentHandle$kotlinx_coroutines_core();
      ChildHandle var1 = var2;
      if (!(var2 instanceof ChildHandleNode)) {
         var1 = null;
      }

      ChildHandleNode var3 = (ChildHandleNode)var1;
      if (var3 != null) {
         JobSupport var4 = (JobSupport)var3.job;
         if (var4 != null) {
            do {
               if (var4.getHandlesException$kotlinx_coroutines_core()) {
                  return true;
               }

               var2 = var4.getParentHandle$kotlinx_coroutines_core();
               var1 = var2;
               if (!(var2 instanceof ChildHandleNode)) {
                  var1 = null;
               }

               var3 = (ChildHandleNode)var1;
               if (var3 == null) {
                  break;
               }

               var4 = (JobSupport)var3.job;
            } while(var4 != null);
         }
      }

      return false;
   }

   public boolean complete() {
      return this.makeCompleting$kotlinx_coroutines_core(Unit.INSTANCE);
   }

   public boolean completeExceptionally(Throwable var1) {
      return this.makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(var1, false, 2, (DefaultConstructorMarker)null));
   }

   public boolean getHandlesException$kotlinx_coroutines_core() {
      return this.handlesException;
   }

   public boolean getOnCancelComplete$kotlinx_coroutines_core() {
      return true;
   }
}
