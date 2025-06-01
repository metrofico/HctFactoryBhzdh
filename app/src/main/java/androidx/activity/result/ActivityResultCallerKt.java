package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aQ\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00072\u0006\u0010\b\u001a\u0002H\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0002\u0010\u000b\u001aY\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00072\u0006\u0010\b\u001a\u0002H\u00032\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0002\u0010\u000e¨\u0006\u000f"},
   d2 = {"registerForActivityResult", "Landroidx/activity/result/ActivityResultLauncher;", "", "I", "O", "Landroidx/activity/result/ActivityResultCaller;", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "input", "callback", "Lkotlin/Function1;", "(Landroidx/activity/result/ActivityResultCaller;Landroidx/activity/result/contract/ActivityResultContract;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Landroidx/activity/result/ActivityResultLauncher;", "registry", "Landroidx/activity/result/ActivityResultRegistry;", "(Landroidx/activity/result/ActivityResultCaller;Landroidx/activity/result/contract/ActivityResultContract;Ljava/lang/Object;Landroidx/activity/result/ActivityResultRegistry;Lkotlin/jvm/functions/Function1;)Landroidx/activity/result/ActivityResultLauncher;", "activity-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class ActivityResultCallerKt {
   public static final ActivityResultLauncher registerForActivityResult(ActivityResultCaller var0, ActivityResultContract var1, Object var2, ActivityResultRegistry var3, Function1 var4) {
      Intrinsics.checkNotNullParameter(var0, "$this$registerForActivityResult");
      Intrinsics.checkNotNullParameter(var1, "contract");
      Intrinsics.checkNotNullParameter(var3, "registry");
      Intrinsics.checkNotNullParameter(var4, "callback");
      ActivityResultLauncher var5 = var0.registerForActivityResult(var1, var3, (ActivityResultCallback)(new ActivityResultCallback(var4) {
         final Function1 $callback;

         {
            this.$callback = var1;
         }

         public final void onActivityResult(Object var1) {
            this.$callback.invoke(var1);
         }
      }));
      Intrinsics.checkNotNullExpressionValue(var5, "registerForActivityResul…egistry) { callback(it) }");
      return (ActivityResultLauncher)(new ActivityResultCallerLauncher(var5, var1, var2));
   }

   public static final ActivityResultLauncher registerForActivityResult(ActivityResultCaller var0, ActivityResultContract var1, Object var2, Function1 var3) {
      Intrinsics.checkNotNullParameter(var0, "$this$registerForActivityResult");
      Intrinsics.checkNotNullParameter(var1, "contract");
      Intrinsics.checkNotNullParameter(var3, "callback");
      ActivityResultLauncher var4 = var0.registerForActivityResult(var1, (ActivityResultCallback)(new ActivityResultCallback(var3) {
         final Function1 $callback;

         {
            this.$callback = var1;
         }

         public final void onActivityResult(Object var1) {
            this.$callback.invoke(var1);
         }
      }));
      Intrinsics.checkNotNullExpressionValue(var4, "registerForActivityResul…ontract) { callback(it) }");
      return (ActivityResultLauncher)(new ActivityResultCallerLauncher(var4, var1, var2));
   }
}
