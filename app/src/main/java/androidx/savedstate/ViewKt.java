package androidx.savedstate;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"},
   d2 = {"findViewTreeSavedStateRegistryOwner", "Landroidx/savedstate/SavedStateRegistryOwner;", "Landroid/view/View;", "savedstate-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class ViewKt {
   public static final SavedStateRegistryOwner findViewTreeSavedStateRegistryOwner(View var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$findViewTreeSavedStateRegistryOwner");
      return ViewTreeSavedStateRegistryOwner.get(var0);
   }
}
