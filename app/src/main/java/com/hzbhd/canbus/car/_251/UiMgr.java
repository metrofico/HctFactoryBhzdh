package com.hzbhd.canbus.car._251;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\"\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"},
   d2 = {"Lcom/hzbhd/canbus/car/_251/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_251/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "selectAirConditionerBtn", "", "btn", "", "sendAirConditionerCmd", "dIndex", "", "bIndex", "status", "", "SettingHandleListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet mAirPageUiSet;
   private final ParkPageUiSet mParkPageUiSet;
   private final SettingHandleListener mSettingHandleListener;
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$_pg_iTiD52bSmhAWWEGjvZcpdd4(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$9Ug9q3ZHCVIAffwJ5ffjdvTnxNQ(int var0) {
      lambda_10$lambda_9(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$D42_BFrZxb2ui7QXPmHJaUJHflg(int var0) {
      lambda_8$lambda_7(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Iov3ZUe4VeszGGWDTjP2X_w2sgo(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ijRWqwV72yjmzBgfR68d6tl0AHU(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_4(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$rRAETxgXzf1r409iod2w4tjrVzs(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$zB7Pi6usvebqeqHgSn_bfRfyy6w(int var0) {
      lambda_6$lambda_0(var0);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var2;
      AirPageUiSet var3 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getAirUiSet(context)");
      this.mAirPageUiSet = var3;
      ParkPageUiSet var4 = this.getParkPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var4, "getParkPageUiSet(context)");
      this.mParkPageUiSet = var4;
      SettingHandleListener var5 = new SettingHandleListener(var2);
      this.mSettingHandleListener = var5;
      var3.setOnAirPageStatusListener(new UiMgr$$ExternalSyntheticLambda0());
      FrontArea var6 = var3.getFrontArea();
      var6.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda1(this, var6), new UiMgr$$ExternalSyntheticLambda2(this, var6), new UiMgr$$ExternalSyntheticLambda3(this, var6), new UiMgr$$ExternalSyntheticLambda4(this, var6)});
      var6.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            UiMgr.sendAirConditionerCmd$default(this.this$0, 1, 0, false, 4, (Object)null);
         }

         public void onClickRight() {
            UiMgr.sendAirConditionerCmd$default(this.this$0, 1, 1, false, 4, (Object)null);
         }
      }));
      var6.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            UiMgr.sendAirConditionerCmd$default(this.this$0, 3, 0, false, 4, (Object)null);
         }

         public void onClickUp() {
            UiMgr.sendAirConditionerCmd$default(this.this$0, 3, 1, false, 4, (Object)null);
         }
      }, null, null}));
      var6.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            UiMgr.sendAirConditionerCmd$default(this.this$0, 0, 6, false, 4, (Object)null);
         }

         public void onRightSeatClick() {
         }
      }));
      var2.setOnSettingItemSelectListener((OnSettingItemSelectListener)var5);
      var2.setOnSettingItemSwitchListener((OnSettingItemSwitchListener)var5);
      var2.setOnSettingItemSeekbarSelectListener((OnSettingItemSeekbarSelectListener)var5);
      var2.setOnSettingPageStatusListener(new UiMgr$$ExternalSyntheticLambda5());
      var4.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda6());
   }

   private static final void lambda_10$lambda_9(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 2) {
               if (var0 == 3) {
                  lambda_10$sendPanoramaCmd(8);
               }
            } else {
               lambda_10$sendPanoramaCmd(7);
            }
         } else {
            lambda_10$sendPanoramaCmd(6);
         }
      } else {
         lambda_10$sendPanoramaCmd(5);
      }

   }

   private static final void lambda_10$sendPanoramaCmd(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)var0});
   }

   private static final void lambda_6$lambda_0(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 35});
   }

   private static final void lambda_6$lambda_5$lambda_1(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_6$lambda_5$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_6$lambda_5$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_6$lambda_5$lambda_4(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_8$lambda_7(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 41});
   }

   private final void selectAirConditionerBtn(String var1) {
      switch (var1) {
         case "front_defog":
            this.sendAirConditionerCmd(0, 4, GeneralAirData.front_defog);
            break;
         case "ac_max":
            this.sendAirConditionerCmd(0, 0, GeneralAirData.ac_max);
            break;
         case "rear_defog":
            this.sendAirConditionerCmd(1, 2, GeneralAirData.rear_defog);
            break;
         case "ac":
            this.sendAirConditionerCmd(0, 1, GeneralAirData.ac);
            break;
         case "auto":
            this.sendAirConditionerCmd(0, 5, GeneralAirData.auto);
            break;
         case "rear":
            this.sendAirConditionerCmd(5, 6, GeneralAirData.rear);
            break;
         case "power":
            this.sendAirConditionerCmd(0, 7, GeneralAirData.power);
            break;
         case "in_out_cycle":
            this.sendAirConditionerCmd(2, 0, GeneralAirData.in_out_cycle);
      }

   }

   private final void sendAirConditionerCmd(int var1, int var2, boolean var3) {
      byte[] var4 = new byte[]{0, 0, 0, 0, 0, 0};
      if (var3) {
         var4[var1] = (byte)DataHandleUtils.setIntByteWithBit(var4[var1], var2, true);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -57}, var4));
      } else {
         var4[var1] = (byte)DataHandleUtils.setIntByteWithBit(var4[var1], var2, true);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -57}, var4));
      }

   }

   // $FF: synthetic method
   static void sendAirConditionerCmd$default(UiMgr var0, int var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      var0.sendAirConditionerCmd(var1, var2, var3);
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0016J \u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002J\u0018\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"},
      d2 = {"Lcom/hzbhd/canbus/car/_251/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "progressORvalue", "onSwitch", "value", "selectSettingsBtn", "param", "sendSettingsCmd", "d0", "d1", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
      private final SettingPageUiSet mSettingPageUiSet;

      public SettingHandleListener(SettingPageUiSet var1) {
         Intrinsics.checkNotNullParameter(var1, "mSettingPageUiSet");
         super();
         this.mSettingPageUiSet = var1;
      }

      private final void selectSettingsBtn(int var1, int var2, int var3) {
         if (var4 != null) {
            switch (var4) {
               case "S251_Other_1":
                  this.sendSettingsCmd(1, var3);
                  break;
               case "S251_Other_2":
                  this.sendSettingsCmd(2, var3);
                  break;
               case "S251_Other_3":
                  this.sendSettingsCmd(3, var3);
                  break;
               case "S251_Other_4":
                  this.sendSettingsCmd(4, var3);
                  break;
               case "S251_Other_5":
                  this.sendSettingsCmd(5, var3);
                  break;
               case "S251_Other_6":
                  this.sendSettingsCmd(7, var3);
                  break;
               case "S251_Other_7":
                  this.sendSettingsCmd(8, var3);
                  break;
               case "S251_Other_8":
                  this.sendSettingsCmd(9, var3);
            }
         }

      }

      private final void sendSettingsCmd(int var1, int var2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -105, (byte)var1, (byte)var2});
      }

      public final SettingPageUiSet getMSettingPageUiSet() {
         return this.mSettingPageUiSet;
      }

      public void onClickItem(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }

      public void onSwitch(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }
   }
}
