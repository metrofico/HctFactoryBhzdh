package androidx.lifecycle;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0007J\u0011\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0082\bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"},
   d2 = {"Landroidx/lifecycle/LifecycleController;", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "minState", "Landroidx/lifecycle/Lifecycle$State;", "dispatchQueue", "Landroidx/lifecycle/DispatchQueue;", "parentJob", "Lkotlinx/coroutines/Job;", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;Landroidx/lifecycle/DispatchQueue;Lkotlinx/coroutines/Job;)V", "observer", "Landroidx/lifecycle/LifecycleEventObserver;", "finish", "", "handleDestroy", "lifecycle-runtime-ktx_release"},
   k = 1,
   mv = {1, 4, 1}
)
public final class LifecycleController {
   private final DispatchQueue dispatchQueue;
   private final Lifecycle lifecycle;
   private final Lifecycle.State minState;
   private final LifecycleEventObserver observer;

   public LifecycleController(Lifecycle var1, Lifecycle.State var2, DispatchQueue var3, Job var4) {
      Intrinsics.checkNotNullParameter(var1, "lifecycle");
      Intrinsics.checkNotNullParameter(var2, "minState");
      Intrinsics.checkNotNullParameter(var3, "dispatchQueue");
      Intrinsics.checkNotNullParameter(var4, "parentJob");
      super();
      this.lifecycle = var1;
      this.minState = var2;
      this.dispatchQueue = var3;
      LifecycleEventObserver var5 = (LifecycleEventObserver)(new LifecycleEventObserver(this, var4) {
         final Job $parentJob;
         final LifecycleController this$0;

         {
            this.this$0 = var1;
            this.$parentJob = var2;
         }

         public final void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
            Intrinsics.checkNotNullParameter(var1, "source");
            Intrinsics.checkNotNullParameter(var2, "<anonymous parameter 1>");
            Lifecycle var5 = var1.getLifecycle();
            Intrinsics.checkNotNullExpressionValue(var5, "source.lifecycle");
            if (var5.getCurrentState() == Lifecycle.State.DESTROYED) {
               LifecycleController var3 = this.this$0;
               Job.DefaultImpls.cancel$default(this.$parentJob, (CancellationException)null, 1, (Object)null);
               var3.finish();
            } else {
               Lifecycle var4 = var1.getLifecycle();
               Intrinsics.checkNotNullExpressionValue(var4, "source.lifecycle");
               if (var4.getCurrentState().compareTo((Enum)this.this$0.minState) < 0) {
                  this.this$0.dispatchQueue.pause();
               } else {
                  this.this$0.dispatchQueue.resume();
               }
            }

         }
      });
      this.observer = var5;
      if (var1.getCurrentState() == Lifecycle.State.DESTROYED) {
         Job.DefaultImpls.cancel$default(var4, (CancellationException)null, 1, (Object)null);
         this.finish();
      } else {
         var1.addObserver((LifecycleObserver)var5);
      }

   }

   // $FF: synthetic method
   public static final void access$handleDestroy(LifecycleController var0, Job var1) {
      var0.handleDestroy(var1);
   }

   private final void handleDestroy(Job var1) {
      Job.DefaultImpls.cancel$default(var1, (CancellationException)null, 1, (Object)null);
      this.finish();
   }

   public final void finish() {
      this.lifecycle.removeObserver((LifecycleObserver)this.observer);
      this.dispatchQueue.finish();
   }
}
