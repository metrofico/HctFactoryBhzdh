package com.hzbhd.canbus.vm.binding;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import com.hzbhd.canbus.park.radar.RadarView;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.ui.life.EmptyBinding;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"},
   d2 = {"Lcom/hzbhd/canbus/vm/binding/ReverseRadarBinding;", "Lcom/hzbhd/ui/life/EmptyBinding;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "view_radar", "Lcom/hzbhd/canbus/park/radar/RadarView;", "getView_radar", "()Lcom/hzbhd/canbus/park/radar/RadarView;", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ReverseRadarBinding extends EmptyBinding {
   private final RadarView view_radar;

   public ReverseRadarBinding(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.view_radar = (RadarView)this.getRoot().findViewById(2131363776);
   }

   public void addObserver(LifecycleOwner var1) {
      Intrinsics.checkNotNullParameter(var1, "lifecycleObserver");
      HandlerThreadUtilKt.runUi((Function0)(new Function0(var1, this) {
         final LifecycleOwner $lifecycleObserver;
         final ReverseRadarBinding this$0;

         // $FF: synthetic method
         public static void $r8$lambda$RgP1_XpfjIJubkG2LZ9WYJrIhBk(ReverseRadarBinding var0, Boolean var1) {
            invoke$lambda_2(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$ZQymLxvMZfkUDG9DXqZFt8qhr88(ReverseRadarBinding var0, Boolean var1) {
            invoke$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$j1yFlrvOd9Z27L0DNcc4gE6_B2E(ReverseRadarBinding var0, Boolean var1) {
            invoke$lambda_5(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$jTMYX3WU5B2SdRChSm3qO_Hb0So(ReverseRadarBinding var0, LifecycleOwner var1, Integer var2) {
            invoke$lambda_1(var0, var1, var2);
         }

         // $FF: synthetic method
         public static void $r8$lambda$mHcK_Lha10vbr2LfmMigFRPEzdE(ReverseRadarBinding var0, Boolean var1) {
            invoke$lambda_0(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$y01ptGyddtpeiIDbvYyZKH8US_0(ReverseRadarBinding var0, Boolean var1) {
            invoke$lambda_4(var0, var1);
         }

         {
            this.$lifecycleObserver = var1;
            this.this$0 = var2;
         }

         private static final void invoke$lambda_0(ReverseRadarBinding var0, Boolean var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullExpressionValue(var1, "it");
            if (var1) {
               RadarView var2 = var0.getView_radar();
               if (var2 != null) {
                  var2.refreshText();
               }
            }

         }

         private static final void invoke$lambda_1(ReverseRadarBinding var0, LifecycleOwner var1, Integer var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$lifecycleObserver");
            if (LogUtil.log5()) {
               LogUtil.d("radarVisible: " + var0 + "   " + var0.getView_radar() + "  " + var1 + "  " + var2);
            }

            RadarView var3 = var0.getView_radar();
            if (var3 != null) {
               Intrinsics.checkNotNullExpressionValue(var2, "it");
               var3.setVisibility(var2);
            }

         }

         private static final void invoke$lambda_2(ReverseRadarBinding var0, Boolean var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullExpressionValue(var1, "it");
            RadarView var2;
            if (var1) {
               var2 = var0.getView_radar();
               if (var2 != null) {
                  var2.showRadarView();
               }
            } else {
               var2 = var0.getView_radar();
               if (var2 != null) {
                  var2.hideRadarView();
               }
            }

         }

         private static final void invoke$lambda_3(ReverseRadarBinding var0, Boolean var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullExpressionValue(var1, "it");
            RadarView var2;
            if (var1) {
               var2 = var0.getView_radar();
               if (var2 != null) {
                  var2.refreshDistance(GeneralParkData.radar_distance_data);
               }
            } else {
               var2 = var0.getView_radar();
               if (var2 != null) {
                  var2.refreshLocation(GeneralParkData.radar_location_data);
               }
            }

         }

         private static final void invoke$lambda_4(ReverseRadarBinding var0, Boolean var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullExpressionValue(var1, "it");
            RadarView var2;
            if (var1) {
               var2 = var0.getView_radar();
               if (var2 != null) {
                  var2.setOneRadarDitance(GeneralParkData.strOnlyOneDistance);
               }
            } else {
               var2 = var0.getView_radar();
               if (var2 != null) {
                  var2.hideOneRadarDistance();
               }
            }

         }

         private static final void invoke$lambda_5(ReverseRadarBinding var0, Boolean var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullExpressionValue(var1, "it");
            if (var1) {
               RadarView var2 = var0.getView_radar();
               if (var2 != null) {
                  var2.setSmallRadarViewWidth();
               }
            }

         }

         public final void invoke() {
            Vm.Companion.getVm().getData().getReverse().isReverse().observe(this.$lifecycleObserver, new ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda0(this.this$0));
            MutableLiveData var1 = Vm.Companion.getVm().getData().getRadar().getRadarVisible();
            LifecycleOwner var2 = this.$lifecycleObserver;
            var1.observe(var2, new ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda1(this.this$0, var2));
            Vm.Companion.getVm().getData().getRadar().getRadarScale().observe(this.$lifecycleObserver, new ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda2(this.this$0));
            Vm.Companion.getVm().getData().getRadar().isShowDistanceNotShowLocationUi().observe(this.$lifecycleObserver, new ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda3(this.this$0));
            Vm.Companion.getVm().getData().getRadar().isShowLeftTopOneDistanceUi().observe(this.$lifecycleObserver, new ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda4(this.this$0));
            Vm.Companion.getVm().getData().getRadar().getSmallRadar().observe(this.$lifecycleObserver, new ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda5(this.this$0));
         }
      }));
   }

   public void bindAction() {
   }

   public int getLayoutId() {
      return 2131558710;
   }

   public final RadarView getView_radar() {
      return this.view_radar;
   }
}
