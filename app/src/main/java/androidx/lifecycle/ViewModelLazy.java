package androidx.lifecycle;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B/\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0002\u0010\u000bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0012\u0010\f\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"},
   d2 = {"Landroidx/lifecycle/ViewModelLazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Lkotlin/Lazy;", "viewModelClass", "Lkotlin/reflect/KClass;", "storeProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelStore;", "factoryProducer", "Landroidx/lifecycle/ViewModelProvider$Factory;", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "cached", "Landroidx/lifecycle/ViewModel;", "value", "getValue", "()Landroidx/lifecycle/ViewModel;", "isInitialized", "", "lifecycle-viewmodel-ktx_release"},
   k = 1,
   mv = {1, 4, 1}
)
public final class ViewModelLazy implements Lazy {
   private ViewModel cached;
   private final Function0 factoryProducer;
   private final Function0 storeProducer;
   private final KClass viewModelClass;

   public ViewModelLazy(KClass var1, Function0 var2, Function0 var3) {
      Intrinsics.checkNotNullParameter(var1, "viewModelClass");
      Intrinsics.checkNotNullParameter(var2, "storeProducer");
      Intrinsics.checkNotNullParameter(var3, "factoryProducer");
      super();
      this.viewModelClass = var1;
      this.storeProducer = var2;
      this.factoryProducer = var3;
   }

   public ViewModel getValue() {
      ViewModel var2 = this.cached;
      ViewModel var1 = var2;
      if (var2 == null) {
         ViewModelProvider.Factory var3 = (ViewModelProvider.Factory)this.factoryProducer.invoke();
         var1 = (new ViewModelProvider((ViewModelStore)this.storeProducer.invoke(), var3)).get(JvmClassMappingKt.getJavaClass(this.viewModelClass));
         this.cached = var1;
         Intrinsics.checkNotNullExpressionValue(var1, "ViewModelProvider(store,…ed = it\n                }");
      }

      return var1;
   }

   public boolean isInitialized() {
      boolean var1;
      if (this.cached != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }
}
