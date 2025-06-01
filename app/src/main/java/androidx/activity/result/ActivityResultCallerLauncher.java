package androidx.activity.result;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B/\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0002\u0010\tJ\u0014\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00010\u0007H\u0016J!\u0010\u0016\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0004H\u0016R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R'\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00010\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0012\u0010\u000b¨\u0006\u001c"},
   d2 = {"Landroidx/activity/result/ActivityResultCallerLauncher;", "I", "O", "Landroidx/activity/result/ActivityResultLauncher;", "", "launcher", "callerContract", "Landroidx/activity/result/contract/ActivityResultContract;", "input", "(Landroidx/activity/result/ActivityResultLauncher;Landroidx/activity/result/contract/ActivityResultContract;Ljava/lang/Object;)V", "getCallerContract", "()Landroidx/activity/result/contract/ActivityResultContract;", "getInput", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "resultContract", "getResultContract", "resultContract$delegate", "Lkotlin/Lazy;", "getContract", "launch", "void", "options", "Landroidx/core/app/ActivityOptionsCompat;", "(Lkotlin/Unit;Landroidx/core/app/ActivityOptionsCompat;)V", "unregister", "activity-ktx_release"},
   k = 1,
   mv = {1, 4, 1}
)
public final class ActivityResultCallerLauncher extends ActivityResultLauncher {
   private final ActivityResultContract callerContract;
   private final Object input;
   private final ActivityResultLauncher launcher;
   private final Lazy resultContract$delegate;

   public ActivityResultCallerLauncher(ActivityResultLauncher var1, ActivityResultContract var2, Object var3) {
      Intrinsics.checkNotNullParameter(var1, "launcher");
      Intrinsics.checkNotNullParameter(var2, "callerContract");
      super();
      this.launcher = var1;
      this.callerContract = var2;
      this.input = var3;
      this.resultContract$delegate = LazyKt.lazy((Function0)(new Function0(this) {
         final ActivityResultCallerLauncher this$0;

         {
            this.this$0 = var1;
         }

         public final <undefinedtype> invoke() {
            return new ActivityResultContract(this) {
               final <undefinedtype> this$0;

               {
                  this.this$0 = var1;
               }

               public Intent createIntent(Context var1, Unit var2) {
                  Intrinsics.checkNotNullParameter(var1, "context");
                  Intent var3 = this.this$0.this$0.getCallerContract().createIntent(var1, this.this$0.this$0.getInput());
                  Intrinsics.checkNotNullExpressionValue(var3, "callerContract.createIntent(context, input)");
                  return var3;
               }

               public Object parseResult(int var1, Intent var2) {
                  return this.this$0.this$0.getCallerContract().parseResult(var1, var2);
               }
            };
         }
      }));
   }

   public final ActivityResultContract getCallerContract() {
      return this.callerContract;
   }

   public ActivityResultContract getContract() {
      return this.getResultContract();
   }

   public final Object getInput() {
      return this.input;
   }

   public final ActivityResultLauncher getLauncher() {
      return this.launcher;
   }

   public final ActivityResultContract getResultContract() {
      return (ActivityResultContract)this.resultContract$delegate.getValue();
   }

   public void launch(Unit var1, ActivityOptionsCompat var2) {
      this.launcher.launch(this.input, var2);
   }

   public void unregister() {
      this.launcher.unregister();
   }
}
