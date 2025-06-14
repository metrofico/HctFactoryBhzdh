package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"},
   d2 = {"lifecycleScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "Landroidx/lifecycle/LifecycleOwner;", "getLifecycleScope", "(Landroidx/lifecycle/LifecycleOwner;)Landroidx/lifecycle/LifecycleCoroutineScope;", "lifecycle-runtime-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class LifecycleOwnerKt {
   public static final LifecycleCoroutineScope getLifecycleScope(LifecycleOwner var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$lifecycleScope");
      Lifecycle var1 = var0.getLifecycle();
      Intrinsics.checkNotNullExpressionValue(var1, "lifecycle");
      return LifecycleKt.getCoroutineScope(var1);
   }
}
