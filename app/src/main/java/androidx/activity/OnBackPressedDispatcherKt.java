package androidx.activity;

import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a9\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n¨\u0006\u000b"},
   d2 = {"addCallback", "Landroidx/activity/OnBackPressedCallback;", "Landroidx/activity/OnBackPressedDispatcher;", "owner", "Landroidx/lifecycle/LifecycleOwner;", "enabled", "", "onBackPressed", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "activity-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class OnBackPressedDispatcherKt {
   public static final OnBackPressedCallback addCallback(OnBackPressedDispatcher var0, LifecycleOwner var1, boolean var2, Function1 var3) {
      Intrinsics.checkNotNullParameter(var0, "$this$addCallback");
      Intrinsics.checkNotNullParameter(var3, "onBackPressed");
      OnBackPressedCallback var4 = new OnBackPressedCallback(var3, var2, var2) {
         final boolean $enabled;
         final Function1 $onBackPressed;

         {
            this.$onBackPressed = var1;
            this.$enabled = var2;
         }

         public void handleOnBackPressed() {
            this.$onBackPressed.invoke(this);
         }
      };
      if (var1 != null) {
         var0.addCallback(var1, (OnBackPressedCallback)var4);
      } else {
         var0.addCallback((OnBackPressedCallback)var4);
      }

      return (OnBackPressedCallback)var4;
   }

   // $FF: synthetic method
   public static OnBackPressedCallback addCallback$default(OnBackPressedDispatcher var0, LifecycleOwner var1, boolean var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = null;
         LifecycleOwner var6 = (LifecycleOwner)null;
      }

      if ((var4 & 2) != 0) {
         var2 = true;
      }

      return addCallback(var0, var1, var2, var3);
   }
}
