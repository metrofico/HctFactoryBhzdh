package androidx.activity.result;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\u0002\u001a\u000f\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0002H\u0086\u0002¨\u0006\u0005"},
   d2 = {"component1", "", "Landroidx/activity/result/ActivityResult;", "component2", "Landroid/content/Intent;", "activity-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class ActivityResultKt {
   public static final int component1(ActivityResult var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$component1");
      return var0.getResultCode();
   }

   public static final Intent component2(ActivityResult var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$component2");
      return var0.getData();
   }
}
