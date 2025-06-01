package com.hzbhd.canbus.car._339;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"},
   d2 = {"Lcom/hzbhd/canbus/car/_339/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "parkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "getParkPageUiSet", "()Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "sendRadarInfo", "", "data0", "", "sendReversingVideoInfo", "sendWarningSoundInfo", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final ParkPageUiSet parkPageUiSet;
   private final SettingPageUiSet settingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$VV2KMWm_Q4r5hQ7VPt34VkYHYfc(UiMgr var0, int var1) {
      _init_$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$c1GxRG_BjT6VVLfZ1np0wsQVuAk(UiMgr var0, int var1, int var2, int var3) {
      _init_$lambda_0(var0, var1, var2, var3);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getSettingUiSet(context)");
      this.settingPageUiSet = var2;
      ParkPageUiSet var3 = this.getParkPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getParkPageUiSet(context)");
      this.parkPageUiSet = var3;
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda0(this));
      var3.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda1(this));
   }

   private static final void _init_$lambda_0(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2 && var2 == 0) {
               var0.sendWarningSoundInfo(var3);
            }
         } else if (var2 == 0) {
            var0.sendReversingVideoInfo(var3);
         }
      } else if (var2 == 0) {
         var0.sendRadarInfo(var3);
      }

   }

   private static final void _init_$lambda_1(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 == 1) {
            var0.sendReversingVideoInfo(1);
         }
      } else {
         var0.sendReversingVideoInfo(0);
      }

   }

   private final void sendRadarInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1});
   }

   private final void sendReversingVideoInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)var1});
   }

   private final void sendWarningSoundInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte)var1});
   }

   public final ParkPageUiSet getParkPageUiSet() {
      return this.parkPageUiSet;
   }

   public final SettingPageUiSet getSettingPageUiSet() {
      return this.settingPageUiSet;
   }
}
