package com.hzbhd.ui.binding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J2\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0006H\u0016J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\"\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J\b\u0010$\u001a\u00020\u001fH&J\u0016\u0010%\u001a\u00020\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006&"},
   d2 = {"Lcom/hzbhd/ui/binding/BaseRepository;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "", "()V", "isInit", "", "()Z", "setInit", "(Z)V", "vm", "getVm", "()Lcom/hzbhd/ui/binding/BaseViewModel;", "setVm", "(Lcom/hzbhd/ui/binding/BaseViewModel;)V", "Lcom/hzbhd/ui/binding/BaseViewModel;", "getBindIngFromLayout", "Lcom/hzbhd/ui/binding/BaseBinding;", "inflater", "Landroid/view/LayoutInflater;", "layoutId", "", "parent", "Landroid/view/ViewGroup;", "attachToRoot", "initView", "Landroid/view/View;", "layout", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "initVm", "", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "modelClass", "Ljava/lang/Class;", "onInit", "resetVm", "java-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class BaseRepository {
   private boolean isInit;
   public BaseViewModel vm;

   // $FF: synthetic method
   public static void initVm$default(BaseRepository var0, ViewModelStoreOwner var1, Class var2, int var3, Object var4) {
      if (var4 == null) {
         if ((var3 & 1) != 0) {
            var1 = null;
         }

         var0.initVm(var1, var2);
      } else {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initVm");
      }
   }

   public BaseBinding getBindIngFromLayout(LayoutInflater var1, int var2, ViewGroup var3, boolean var4) {
      Intrinsics.checkNotNullParameter(var1, "inflater");
      return null;
   }

   public final BaseViewModel getVm() {
      BaseViewModel var1 = this.vm;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("vm");
         return null;
      }
   }

   public View initView(LayoutInflater var1, int var2, LifecycleOwner var3) {
      Intrinsics.checkNotNullParameter(var1, "inflater");
      Intrinsics.checkNotNullParameter(var3, "lifecycleOwner");
      BaseUtil.logTime("init binding start: layout = " + var2 + " class - " + this.getVm().getClass().getName());
      BaseBinding var4 = this.getBindIngFromLayout(var1, var2, (ViewGroup)null, false);
      Intrinsics.checkNotNull(var4);
      var4.addObserver(this.getVm(), var3);
      var4.bindAction(this.getVm());
      BaseUtil.logTime("init binding " + var4.getClass().getSimpleName() + " finish " + var4.getRoot());
      return var4.getRoot();
   }

   public void initVm(ViewModelStoreOwner var1, Class var2) {
      Intrinsics.checkNotNullParameter(var2, "modelClass");
      if (!this.isInit) {
         this.isInit = true;
         Object var3 = var2.newInstance();
         Intrinsics.checkNotNullExpressionValue(var3, "modelClass.newInstance()");
         this.setVm((BaseViewModel)var3);
         this.onInit();
      }

   }

   public final boolean isInit() {
      return this.isInit;
   }

   public abstract void onInit();

   public void resetVm(Class var1) {
      Intrinsics.checkNotNullParameter(var1, "modelClass");
      Object var2 = var1.newInstance();
      Intrinsics.checkNotNullExpressionValue(var2, "modelClass.newInstance()");
      this.setVm((BaseViewModel)var2);
   }

   public final void setInit(boolean var1) {
      this.isInit = var1;
   }

   public final void setVm(BaseViewModel var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.vm = var1;
   }
}
