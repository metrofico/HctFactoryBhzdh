package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"},
   d2 = {"coroutineScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "Landroidx/lifecycle/Lifecycle;", "getCoroutineScope", "(Landroidx/lifecycle/Lifecycle;)Landroidx/lifecycle/LifecycleCoroutineScope;", "lifecycle-runtime-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class LifecycleKt {
   public static final LifecycleCoroutineScope getCoroutineScope(Lifecycle var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$coroutineScope");

      LifecycleCoroutineScopeImpl var1;
      do {
         var1 = (LifecycleCoroutineScopeImpl)var0.mInternalScopeRef.get();
         if (var1 != null) {
            return (LifecycleCoroutineScope)var1;
         }

         var1 = new LifecycleCoroutineScopeImpl(var0, SupervisorKt.SupervisorJob$default((Job)null, 1, (Object)null).plus((CoroutineContext)Dispatchers.getMain().getImmediate()));
      } while(!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(var0.mInternalScopeRef, (Object)null, var1));

      var1.register();
      return (LifecycleCoroutineScope)var1;
   }
}
