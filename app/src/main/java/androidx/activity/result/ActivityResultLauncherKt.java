package androidx.activity.result;

import androidx.core.app.ActivityOptionsCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u001a#\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\b\u0006¨\u0006\u0007"},
   d2 = {"launch", "", "Landroidx/activity/result/ActivityResultLauncher;", "Ljava/lang/Void;", "options", "Landroidx/core/app/ActivityOptionsCompat;", "launchUnit", "activity-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class ActivityResultLauncherKt {
   public static final void launch(ActivityResultLauncher var0, ActivityOptionsCompat var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$launch");
      var0.launch((Object)null, var1);
   }

   // $FF: synthetic method
   public static void launch$default(ActivityResultLauncher var0, ActivityOptionsCompat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         ActivityOptionsCompat var4 = (ActivityOptionsCompat)null;
      }

      launch(var0, var1);
   }

   public static final void launchUnit(ActivityResultLauncher var0, ActivityOptionsCompat var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$launch");
      var0.launch(Unit.INSTANCE, var1);
   }

   // $FF: synthetic method
   public static void launchUnit$default(ActivityResultLauncher var0, ActivityOptionsCompat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         ActivityOptionsCompat var4 = (ActivityOptionsCompat)null;
      }

      launchUnit(var0, var1);
   }
}
