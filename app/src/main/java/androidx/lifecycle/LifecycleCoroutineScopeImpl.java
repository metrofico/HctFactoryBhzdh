package androidx.lifecycle;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0006\u0010\u0012\u001a\u00020\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"},
   d2 = {"Landroidx/lifecycle/LifecycleCoroutineScopeImpl;", "Landroidx/lifecycle/LifecycleCoroutineScope;", "Landroidx/lifecycle/LifecycleEventObserver;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/lifecycle/Lifecycle;Lkotlin/coroutines/CoroutineContext;)V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getLifecycle$lifecycle_runtime_ktx_release", "()Landroidx/lifecycle/Lifecycle;", "onStateChanged", "", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "register", "lifecycle-runtime-ktx_release"},
   k = 1,
   mv = {1, 4, 1}
)
public final class LifecycleCoroutineScopeImpl extends LifecycleCoroutineScope implements LifecycleEventObserver {
   private final CoroutineContext coroutineContext;
   private final Lifecycle lifecycle;

   public LifecycleCoroutineScopeImpl(Lifecycle var1, CoroutineContext var2) {
      Intrinsics.checkNotNullParameter(var1, "lifecycle");
      Intrinsics.checkNotNullParameter(var2, "coroutineContext");
      super();
      this.lifecycle = var1;
      this.coroutineContext = var2;
      if (this.getLifecycle$lifecycle_runtime_ktx_release().getCurrentState() == Lifecycle.State.DESTROYED) {
         JobKt.cancel$default(this.getCoroutineContext(), (CancellationException)null, 1, (Object)null);
      }

   }

   public CoroutineContext getCoroutineContext() {
      return this.coroutineContext;
   }

   public Lifecycle getLifecycle$lifecycle_runtime_ktx_release() {
      return this.lifecycle;
   }

   public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
      Intrinsics.checkNotNullParameter(var1, "source");
      Intrinsics.checkNotNullParameter(var2, "event");
      if (this.getLifecycle$lifecycle_runtime_ktx_release().getCurrentState().compareTo((Enum)Lifecycle.State.DESTROYED) <= 0) {
         this.getLifecycle$lifecycle_runtime_ktx_release().removeObserver((LifecycleObserver)this);
         JobKt.cancel$default(this.getCoroutineContext(), (CancellationException)null, 1, (Object)null);
      }

   }

   public final void register() {
      BuildersKt.launch$default(this, (CoroutineContext)Dispatchers.getMain().getImmediate(), (CoroutineStart)null, (Function2)(new Function2(this, (Continuation)null) {
         private Object L$0;
         int label;
         final LifecycleCoroutineScopeImpl this$0;

         {
            this.this$0 = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Intrinsics.checkNotNullParameter(var2, "completion");
            Function2 var3 = new <anonymous constructor>(this.this$0, var2);
            var3.L$0 = var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
               ResultKt.throwOnFailure(var1);
               CoroutineScope var2 = (CoroutineScope)this.L$0;
               if (this.this$0.getLifecycle$lifecycle_runtime_ktx_release().getCurrentState().compareTo((Enum)Lifecycle.State.INITIALIZED) >= 0) {
                  this.this$0.getLifecycle$lifecycle_runtime_ktx_release().addObserver((LifecycleObserver)this.this$0);
               } else {
                  JobKt.cancel$default(var2.getCoroutineContext(), (CancellationException)null, 1, (Object)null);
               }

               return Unit.INSTANCE;
            } else {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
         }
      }), 2, (Object)null);
   }
}
