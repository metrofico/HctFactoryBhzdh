package androidx.activity;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0010\b\n\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"},
   d2 = {"viewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/activity/ComponentActivity;", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "activity-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class ActivityViewModelLazyKt {
   // $FF: synthetic method
   public static final Lazy viewModels(ComponentActivity var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$viewModels");
      if (var1 == null) {
         var1 = (Function0)(new Function0(var0) {
            final ComponentActivity $this_viewModels;

            public {
               this.$this_viewModels = var1;
            }

            public final ViewModelProvider.Factory invoke() {
               return this.$this_viewModels.getDefaultViewModelProviderFactory();
            }
         });
      }

      Intrinsics.reifiedOperationMarker(4, "VM");
      return (Lazy)(new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), (Function0)(new Function0(var0) {
         final ComponentActivity $this_viewModels;

         public {
            this.$this_viewModels = var1;
         }

         public final ViewModelStore invoke() {
            ViewModelStore var1 = this.$this_viewModels.getViewModelStore();
            Intrinsics.checkNotNullExpressionValue(var1, "viewModelStore");
            return var1;
         }
      }), var1));
   }

   // $FF: synthetic method
   public static Lazy viewModels$default(ComponentActivity var0, Function0 var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         Function0 var4 = (Function0)null;
      }

      Intrinsics.checkNotNullParameter(var0, "$this$viewModels");
      if (var1 == null) {
         var1 = (Function0)(new Function0(var0) {
            final ComponentActivity $this_viewModels;

            public {
               this.$this_viewModels = var1;
            }

            public final ViewModelProvider.Factory invoke() {
               return this.$this_viewModels.getDefaultViewModelProviderFactory();
            }
         });
      }

      Intrinsics.reifiedOperationMarker(4, "VM");
      return (Lazy)(new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), (Function0)(new Function0(var0) {
         final ComponentActivity $this_viewModels;

         public {
            this.$this_viewModels = var1;
         }

         public final ViewModelStore invoke() {
            ViewModelStore var1 = this.$this_viewModels.getViewModelStore();
            Intrinsics.checkNotNullExpressionValue(var1, "viewModelStore");
            return var1;
         }
      }), var1));
   }
}
