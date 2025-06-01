package com.hzbhd.canbus.vm.binding;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import androidx.lifecycle.LifecycleOwner;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.ui.life.EmptyBinding;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.HandlerThreadUtilKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"},
   d2 = {"Lcom/hzbhd/canbus/vm/binding/ReversePanoramicCustomBinding;", "Lcom/hzbhd/ui/life/EmptyBinding;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "view_cus_panoramic", "Landroid/widget/RelativeLayout;", "getView_cus_panoramic", "()Landroid/widget/RelativeLayout;", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ReversePanoramicCustomBinding extends EmptyBinding {
   private final RelativeLayout view_cus_panoramic;

   public ReversePanoramicCustomBinding(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.view_cus_panoramic = (RelativeLayout)this.getRoot().findViewById(2131363772);
   }

   public void addObserver(LifecycleOwner var1) {
      Intrinsics.checkNotNullParameter(var1, "lifecycleObserver");
      HandlerThreadUtilKt.runUi((Function0)(new Function0(var1, this) {
         final LifecycleOwner $lifecycleObserver;
         final ReversePanoramicCustomBinding this$0;

         // $FF: synthetic method
         public static void $r8$lambda$_t0EpYntPvJPimxHpWhzxOYLEok(ReversePanoramicCustomBinding var0, Integer var1) {
            invoke$lambda_0(var0, var1);
         }

         {
            this.$lifecycleObserver = var1;
            this.this$0 = var2;
         }

         private static final void invoke$lambda_0(ReversePanoramicCustomBinding var0, Integer var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RelativeLayout var2 = var0.getView_cus_panoramic();
            if (var2 != null) {
               Intrinsics.checkNotNullExpressionValue(var1, "it");
               var2.setVisibility(var1);
            }

            if (var1 != null && var1 == 0) {
               View var5 = UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getCusPanoramicView(BaseUtil.INSTANCE.getContext());
               if (var5 != null) {
                  RelativeLayout var4 = var0.getView_cus_panoramic();
                  if (var4 != null) {
                     var1 = var4.getChildCount();
                  } else {
                     var1 = null;
                  }

                  if (var1 == 0) {
                     if (var5.getParent() != null) {
                        ViewParent var6 = var5.getParent();
                        Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type android.view.ViewGroup");
                        ((ViewGroup)var6).removeAllViews();
                     }

                     RelativeLayout var3 = var0.getView_cus_panoramic();
                     if (var3 != null) {
                        var3.addView(var5);
                     }
                  }
               }
            }

         }

         public final void invoke() {
            Vm.Companion.getVm().getData().getReverse().getCusPanoramicVisible().observe(this.$lifecycleObserver, new ReversePanoramicCustomBinding$addObserver$1$$ExternalSyntheticLambda0(this.this$0));
         }
      }));
   }

   public void bindAction() {
   }

   public int getLayoutId() {
      return 2131558708;
   }

   public final RelativeLayout getView_cus_panoramic() {
      return this.view_cus_panoramic;
   }
}
