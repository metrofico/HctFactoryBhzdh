package com.hzbhd.canbus.vm.binding;

import android.content.Context;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.ui.life.EmptyBinding;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b¨\u0006\u0018"},
   d2 = {"Lcom/hzbhd/canbus/vm/binding/ReverseMainBinding;", "Lcom/hzbhd/ui/life/EmptyBinding;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "__01__reverse_main_panoramic", "Landroid/view/View;", "get__01__reverse_main_panoramic", "()Landroid/view/View;", "__01__reverse_main_panoramic1", "get__01__reverse_main_panoramic1", "__01__reverse_main_radar", "get__01__reverse_main_radar", "back_view_back", "getBack_view_back", "tv_camera_tis", "getTv_camera_tis", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ReverseMainBinding extends EmptyBinding {
   private final View __01__reverse_main_panoramic;
   private final View __01__reverse_main_panoramic1;
   private final View __01__reverse_main_radar;
   private final View back_view_back;
   private final View tv_camera_tis;

   // $FF: synthetic method
   public static void $r8$lambda$q_YLDqE_QWu2_2Jd87VBBBwwB_w(View var0) {
      bindAction$lambda_0(var0);
   }

   public ReverseMainBinding(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      View var2 = this.getRoot().findViewById(2131361833);
      Intrinsics.checkNotNullExpressionValue(var2, "root.findViewById(R.id._…__reverse_main_panoramic)");
      this.__01__reverse_main_panoramic = var2;
      var2 = this.getRoot().findViewById(2131361835);
      Intrinsics.checkNotNullExpressionValue(var2, "root.findViewById(R.id.__01__reverse_main_radar)");
      this.__01__reverse_main_radar = var2;
      var2 = this.getRoot().findViewById(2131361834);
      Intrinsics.checkNotNullExpressionValue(var2, "root.findViewById(R.id._…_reverse_main_panoramic1)");
      this.__01__reverse_main_panoramic1 = var2;
      var2 = this.getRoot().findViewById(2131363595);
      Intrinsics.checkNotNullExpressionValue(var2, "root.findViewById(R.id.tv_camera_tis)");
      this.tv_camera_tis = var2;
      var2 = this.getRoot().findViewById(2131361960);
      Intrinsics.checkNotNullExpressionValue(var2, "root.findViewById(R.id.back_view_back)");
      this.back_view_back = var2;
   }

   private static final void bindAction$lambda_0(View var0) {
      if (LogUtil.log5()) {
         LogUtil.d("bindAction: back_view_back");
      }

      ShareDataManager.getInstance().reportInt("user.Reverse", 0);
   }

   public void addObserver(LifecycleOwner var1) {
      Intrinsics.checkNotNullParameter(var1, "lifecycleObserver");
      HandlerThreadUtilKt.runUi((Function0)(new Function0(var1, this) {
         final LifecycleOwner $lifecycleObserver;
         final ReverseMainBinding this$0;

         // $FF: synthetic method
         public static void $r8$lambda$B2VPk28LVx1phJKlSV2FP3e_oM0(ReverseMainBinding var0, Boolean var1) {
            invoke$lambda_0(var0, var1);
         }

         {
            this.$lifecycleObserver = var1;
            this.this$0 = var2;
         }

         private static final void invoke$lambda_0(ReverseMainBinding var0, Boolean var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            if (LogUtil.log5()) {
               LogUtil.d("ReverseMainBinding: " + var1);
            }

            View var3 = var0.getBack_view_back();
            Intrinsics.checkNotNullExpressionValue(var1, "it");
            byte var2;
            if (var1) {
               var2 = 0;
            } else {
               var2 = 8;
            }

            var3.setVisibility(var2);
         }

         public final void invoke() {
            Vm.Companion.getVm().getData().getReverse().getBackBtn().observe(this.$lifecycleObserver, new ReverseMainBinding$addObserver$1$$ExternalSyntheticLambda0(this.this$0));
         }
      }));
   }

   public void bindAction() {
      this.back_view_back.setOnClickListener(new ReverseMainBinding$$ExternalSyntheticLambda0());
   }

   public final View getBack_view_back() {
      return this.back_view_back;
   }

   public int getLayoutId() {
      return 2131558707;
   }

   public final View getTv_camera_tis() {
      return this.tv_camera_tis;
   }

   public final View get__01__reverse_main_panoramic() {
      return this.__01__reverse_main_panoramic;
   }

   public final View get__01__reverse_main_panoramic1() {
      return this.__01__reverse_main_panoramic1;
   }

   public final View get__01__reverse_main_radar() {
      return this.__01__reverse_main_radar;
   }
}
