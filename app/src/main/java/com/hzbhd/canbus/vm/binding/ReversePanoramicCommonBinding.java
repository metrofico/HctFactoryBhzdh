package com.hzbhd.canbus.vm.binding;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import com.hzbhd.canbus.adapter.PanoramiceBtnLvAdapter;
import com.hzbhd.canbus.park.panoramic.PanoramicView;
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
   d2 = {"Lcom/hzbhd/canbus/vm/binding/ReversePanoramicCommonBinding;", "Lcom/hzbhd/ui/life/EmptyBinding;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "view_panoramic", "Lcom/hzbhd/canbus/park/panoramic/PanoramicView;", "getView_panoramic", "()Lcom/hzbhd/canbus/park/panoramic/PanoramicView;", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ReversePanoramicCommonBinding extends EmptyBinding {
   private final PanoramicView view_panoramic;

   public ReversePanoramicCommonBinding(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.view_panoramic = (PanoramicView)this.getRoot().findViewById(2131363775);
   }

   public void addObserver(LifecycleOwner var1) {
      Intrinsics.checkNotNullParameter(var1, "lifecycleObserver");
      HandlerThreadUtilKt.runUi((Function0)(new Function0(var1, this) {
         final LifecycleOwner $lifecycleObserver;
         final ReversePanoramicCommonBinding this$0;

         // $FF: synthetic method
         public static void $r8$lambda$aiXafsSPZFKlvpybyuNlZakCYxg(ReversePanoramicCommonBinding var0, Integer var1) {
            invoke$lambda_1(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$nB3osUYGmPuKC8IqI6wI_8wVEM8(ReversePanoramicCommonBinding var0, Integer var1) {
            invoke$lambda_0(var0, var1);
         }

         {
            this.$lifecycleObserver = var1;
            this.this$0 = var2;
         }

         private static final void invoke$lambda_0(ReversePanoramicCommonBinding var0, Integer var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            PanoramicView var2 = var0.getView_panoramic();
            if (var2 != null) {
               PanoramiceBtnLvAdapter var3 = var2.getAdapter();
               if (var3 != null) {
                  var3.notifyDataSetChanged();
               }
            }

         }

         private static final void invoke$lambda_1(ReversePanoramicCommonBinding var0, Integer var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            PanoramicView var2 = var0.getView_panoramic();
            if (var2 != null) {
               Intrinsics.checkNotNullExpressionValue(var1, "it");
               var2.setVisibility(var1);
            }

            if (var1 != null && var1 == 0) {
               PanoramicView var3 = var0.getView_panoramic();
               if (var3 != null) {
                  var3.setBtnList(UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getParkPageUiSet(BaseUtil.INSTANCE.getContext()).getPanoramicBtnList(), UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getParkPageUiSet(BaseUtil.INSTANCE.getContext()).getOnPanoramicItemClickListener());
               }
            }

         }

         public final void invoke() {
            Vm.Companion.getVm().getData().getReverse().getPanoramicState().observe(this.$lifecycleObserver, new ReversePanoramicCommonBinding$addObserver$1$$ExternalSyntheticLambda0(this.this$0));
            Vm.Companion.getVm().getData().getReverse().getPanoramicVisible().observe(this.$lifecycleObserver, new ReversePanoramicCommonBinding$addObserver$1$$ExternalSyntheticLambda1(this.this$0));
         }
      }));
   }

   public void bindAction() {
   }

   public int getLayoutId() {
      return 2131558709;
   }

   public final PanoramicView getView_panoramic() {
      return this.view_panoramic;
   }
}
