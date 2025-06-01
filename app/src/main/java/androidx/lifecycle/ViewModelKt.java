package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
   d2 = {"JOB_KEY", "", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/lifecycle/ViewModel;", "getViewModelScope", "(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;", "lifecycle-viewmodel-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class ViewModelKt {
   private static final String JOB_KEY = "androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY";

   public static final CoroutineScope getViewModelScope(ViewModel var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$viewModelScope");
      CoroutineScope var1 = (CoroutineScope)var0.getTag("androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY");
      if (var1 != null) {
         return var1;
      } else {
         Object var2 = var0.setTagIfAbsent("androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY", new CloseableCoroutineScope(SupervisorKt.SupervisorJob$default((Job)null, 1, (Object)null).plus((CoroutineContext)Dispatchers.getMain().getImmediate())));
         Intrinsics.checkNotNullExpressionValue(var2, "setTagIfAbsent(\n        …Main.immediate)\n        )");
         return (CoroutineScope)var2;
      }
   }
}
