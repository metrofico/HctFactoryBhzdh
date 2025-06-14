package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\u0004¨\u0006\u0005"},
   d2 = {"get", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/lifecycle/ViewModelProvider;", "(Landroidx/lifecycle/ViewModelProvider;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class ViewModelProviderKt {
   // $FF: synthetic method
   public static final ViewModel get(ViewModelProvider var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$get");
      Intrinsics.reifiedOperationMarker(4, "VM");
      ViewModel var1 = var0.get(ViewModel.class);
      Intrinsics.checkNotNullExpressionValue(var1, "get(VM::class.java)");
      return var1;
   }
}
