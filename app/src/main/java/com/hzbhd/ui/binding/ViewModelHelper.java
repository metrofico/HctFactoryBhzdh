package com.hzbhd.ui.binding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JS\u0010\u0003\u001a\u00020\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H\u00052\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00050\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0011H\u0017¢\u0006\u0002\u0010\u0012¨\u0006\u0013"},
   d2 = {"Lcom/hzbhd/ui/binding/ViewModelHelper;", "", "()V", "initView", "Landroid/view/View;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "inflater", "Landroid/view/LayoutInflater;", "layout", "", "baseViewModel", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "baseActionHelper", "Lcom/hzbhd/ui/binding/BaseActionHelper;", "modelClass", "Ljava/lang/Class;", "(Landroid/view/LayoutInflater;ILcom/hzbhd/ui/binding/BaseViewModel;Landroidx/lifecycle/LifecycleOwner;Lcom/hzbhd/ui/binding/BaseActionHelper;Ljava/lang/Class;)Landroid/view/View;", "java-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ViewModelHelper {
   public static final ViewModelHelper INSTANCE = new ViewModelHelper();

   private ViewModelHelper() {
   }

   @Deprecated(
      message = "改成了在Repository实现"
   )
   public View initView(LayoutInflater var1, int var2, BaseViewModel var3, LifecycleOwner var4, BaseActionHelper var5, Class var6) {
      Intrinsics.checkNotNullParameter(var1, "inflater");
      Intrinsics.checkNotNullParameter(var3, "baseViewModel");
      Intrinsics.checkNotNullParameter(var4, "lifecycleOwner");
      Intrinsics.checkNotNullParameter(var5, "baseActionHelper");
      Intrinsics.checkNotNullParameter(var6, "modelClass");
      BaseUtil.logTime("init binding start: layout = " + var2 + " class - " + var6.getName());
      BaseBinding var7 = var5.getBindIngFromLayout(var1, var2, (ViewGroup)null, false, var6);
      var7.addObserver(var3, var4);
      var5.bindingAddAction(var7, var3);
      var7.bindAction(var3);
      BaseUtil.logTime("init binding " + var7.getClass().getSimpleName() + " finish " + var7.getRoot());
      return var7.getRoot();
   }
}
