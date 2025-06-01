package com.hzbhd.canbus.car._310;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UiMgr extends AbstractUiMgr {
   private final String TAG = "_310_UiMgr";
   private Handler mHandler = new Handler(Looper.getMainLooper());
   private MsgMgr mMsgMgr;
   private MyPanoramicView myPanoramicView;

   public UiMgr(Context var1) {
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var2), new UiMgr$$ExternalSyntheticLambda12(this, var2), new UiMgr$$ExternalSyntheticLambda13(this, var2), new UiMgr$$ExternalSyntheticLambda14(this, var2)});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(2);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(3);
         }
      }, null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(4);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(5);
         }
      }});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(9);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(10);
         }
      });
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new UiMgr$$ExternalSyntheticLambda15(this, var2)});
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(38);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(39);
         }
      }, null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(46);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(47);
         }
      }});
      var2.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(40);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(41);
         }
      });
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      var4.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda1(this, var4, var1));
      var4.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda2(var4));
      var4.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda3(var4));
      var4.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda4(var4));
      var4.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda5(var4));
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
      var5.setOnAmplifierPositionListener(new UiMgr$$ExternalSyntheticLambda7());
      var5.setOnAmplifierSeekBarListener(new UiMgr$$ExternalSyntheticLambda8());
      this.getParkPageUiSet(var1).setOnBackCameraStatusListener(new UiMgr$$ExternalSyntheticLambda9(this, var1));
      PanelKeyPageUiSet var6 = this.getPanelKeyPageUiSet(var1);
      var6.setOnPanelKeyPositionListener(new UiMgr$$ExternalSyntheticLambda10(var6));
      OriginalCarDevicePageUiSet var3 = this.getOriginalCarDevicePageUiSet(var1);
      var3.setOnOriginalBottomBtnClickListeners(new UiMgr$$ExternalSyntheticLambda11(var3));
   }

   private void addAirBtn(Context var1, int var2, int var3, String var4) {
      String[][] var10 = this.getAirUiSet(var1).getFrontArea().getLineBtnAction();
      int var7 = var10.length;

      for(int var5 = 0; var5 < var7; ++var5) {
         String[] var9 = var10[var5];
         int var8 = var9.length;

         for(int var6 = 0; var6 < var8; ++var6) {
            if (var9[var6].equals(var4)) {
               return;
            }
         }
      }

      ArrayList var11 = new ArrayList(Arrays.asList(var10[var2]));
      var11.add(var3, var4);
      var10[var2] = (String[])var11.toArray(new String[0]);
   }

   private int[] getAirBtnPosition(Context var1, String var2) {
      int[] var5 = new int[]{-1, -1};
      String[][] var6 = this.getAirUiSet(var1).getFrontArea().getLineBtnAction();

      for(int var3 = 0; var3 < var6.length; ++var3) {
         String[] var7 = var6[var3];

         for(int var4 = 0; var4 < var7.length; ++var4) {
            if (var7[var4].equals(var2)) {
               var5[0] = var3;
               var5[1] = var4;
            }
         }
      }

      return var5;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private OnPanoramicBtnCllick getOnPanoramicBtnCllick(Context var1) {
      boolean var2 = CommUtil.isBackCamera(var1);
      boolean var3 = CommUtil.isPanoramic(var1);
      if (var2) {
         return new OnPanoramicBtnCllick(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onEndBottomClick() {
               super.onEndBottomClick();
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 3});
            }

            public void onEndTopClick() {
               super.onEndTopClick();
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 6});
            }

            public void onStartBottomClick() {
               super.onStartBottomClick();
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 4});
            }
         };
      } else {
         return var3 ? new OnPanoramicBtnCllick(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onStartBottomClick() {
               super.onStartBottomClick();
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 2});
            }

            public void onStartTopClick() {
               super.onStartTopClick();
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 1});
            }
         } : null;
      }
   }

   // $FF: synthetic method
   static void lambda$new$10(AmplifierActivity.AmplifierPosition var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var0.ordinal()];
      if (var2 != 1) {
         if (var2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var1 + 7)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(7 - var1)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$11(AmplifierActivity.AmplifierBand var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var0.ordinal()];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var1 + 2)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var1 + 2)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var1 + 2)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$13(PanelKeyPageUiSet var0, int var1) {
      String var3 = (String)((List)Objects.requireNonNull(var0.getList())).get(var1);
      var3.hashCode();
      int var2 = var3.hashCode();
      byte var4 = -1;
      switch (var2) {
         case -1629801595:
            if (var3.equals("_309_panel_19")) {
               var4 = 0;
            }
            break;
         case -1629801573:
            if (var3.equals("_309_panel_20")) {
               var4 = 1;
            }
            break;
         case -1502244073:
            if (var3.equals("_310_min_0")) {
               var4 = 2;
            }
      }

      switch (var4) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, 0});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, 1});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, 2});
      }

   }

   // $FF: synthetic method
   static void lambda$new$14(OriginalCarDevicePageUiSet var0, int var1) {
      String var2 = var0.getRowBottomBtnAction()[var1];
      var2.hashCode();
      if (var2.equals("power")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, 1});
      }

   }

   // $FF: synthetic method
   static void lambda$new$6(SettingPageUiSet var0, int var1, int var2, int var3) {
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var4.hashCode();
      var2 = var4.hashCode();
      byte var5 = -1;
      switch (var2) {
         case -1082386052:
            if (var4.equals("hiworld_jeep_123_0x60_data1_65")) {
               var5 = 0;
            }
            break;
         case 633503347:
            if (var4.equals("radar_volume")) {
               var5 = 1;
            }
            break;
         case 1797283203:
            if (var4.equals("_310_auto_circulation_sensitivity")) {
               var5 = 2;
            }
      }

      switch (var5) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)var3});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)var3});
      }

   }

   // $FF: synthetic method
   static void lambda$new$7(SettingPageUiSet var0, int var1, int var2) {
      String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var3.hashCode();
      if (!var3.equals("_165_clear_data")) {
         if (var3.equals("clear_data")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
      }

   }

   // $FF: synthetic method
   static void lambda$new$8(SettingPageUiSet var0, int var1, int var2) {
      String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var3.hashCode();
      if (var3.equals("update_data")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
      }

   }

   // $FF: synthetic method
   static void lambda$new$9(SettingPageUiSet var0, int var1, int var2, int var3) {
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var5.hashCode();
      int var4 = var5.hashCode();
      byte var7 = 8;
      byte var6 = -1;
      switch (var4) {
         case -902681199:
            if (var5.equals("_18_vehicle_setting_item_1_0")) {
               var6 = 0;
            }
            break;
         case -902681198:
            if (var5.equals("_18_vehicle_setting_item_1_1")) {
               var6 = 1;
            }
            break;
         case -902681195:
            if (var5.equals("_18_vehicle_setting_item_1_4")) {
               var6 = 2;
            }
            break;
         case -902680234:
            if (var5.equals("_18_vehicle_setting_item_2_4")) {
               var6 = 3;
            }
            break;
         case -902680233:
            if (var5.equals("_18_vehicle_setting_item_2_5")) {
               var6 = 4;
            }
            break;
         case -902680232:
            if (var5.equals("_18_vehicle_setting_item_2_6")) {
               var6 = 5;
            }
            break;
         case -902680231:
            if (var5.equals("_18_vehicle_setting_item_2_7")) {
               var6 = 6;
            }
            break;
         case -404707241:
            if (var5.equals("_310_speed_auto_lock")) {
               var6 = 7;
            }
            break;
         case -386185518:
            if (var5.equals("_310_shift_auto_lock")) {
               var6 = 8;
            }
            break;
         case 158712797:
            if (var5.equals("_310_position_p_unlock")) {
               var6 = 9;
            }
            break;
         case 257294315:
            if (var5.equals("_186_asl")) {
               var6 = 10;
            }
            break;
         case 957932200:
            if (var5.equals("light_ctrl_3")) {
               var6 = 11;
            }
            break;
         case 1214765374:
            if (var5.equals("_283_radar_switch")) {
               var6 = 12;
            }
            break;
         case 2015341731:
            if (var5.equals("_16_title_23")) {
               var6 = 13;
            }
      }

      switch (var6) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var3});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)var3});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var3});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var3});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var3});
            break;
         case 10:
            var6 = var7;
            if (var3 == 0) {
               var6 = 1;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)var6});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)var3});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var3});
      }

   }

   // $FF: synthetic method
   static void lambda$sendAirCommand$15(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var0, 0});
   }

   private void sendAirBtnCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -2052939637:
            if (var1.equals("rear_blow_positive")) {
               var2 = 0;
            }
            break;
         case -1761278488:
            if (var1.equals("pollrn_removal")) {
               var2 = 1;
            }
            break;
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 2;
            }
            break;
         case -1274277292:
            if (var1.equals("clean_air")) {
               var2 = 3;
            }
            break;
         case -713186454:
            if (var1.equals("rear_auto")) {
               var2 = 4;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 5;
            }
            break;
         case -620266838:
            if (var1.equals("rear_power")) {
               var2 = 6;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 7;
            }
            break;
         case -424438238:
            if (var1.equals("blow_negative")) {
               var2 = 8;
            }
            break;
         case -92674103:
            if (var1.equals("front_window_heat")) {
               var2 = 9;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 10;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 11;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 12;
            }
            break;
         case 3496356:
            if (var1.equals("rear")) {
               var2 = 13;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 14;
            }
            break;
         case 109854462:
            if (var1.equals("swing")) {
               var2 = 15;
            }
            break;
         case 395882750:
            if (var1.equals("negative_ion")) {
               var2 = 16;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 17;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(43);
            break;
         case 1:
            this.sendAirCommand(32);
            break;
         case 2:
            this.sendAirCommand(18);
            break;
         case 3:
            this.sendAirCommand(27);
            break;
         case 4:
            this.sendAirCommand(45);
            break;
         case 5:
            this.sendAirCommand(20);
            break;
         case 6:
            this.sendAirCommand(44);
            break;
         case 7:
            this.sendAirCommand(36);
            break;
         case 8:
            this.sendAirCommand(37);
            break;
         case 9:
            this.sendAirCommand(26);
            break;
         case 10:
            this.sendAirCommand(23);
            break;
         case 11:
            this.sendAirCommand(21);
            break;
         case 12:
            this.sendAirCommand(16);
            break;
         case 13:
            this.sendAirCommand(42);
            break;
         case 14:
            this.sendAirCommand(1);
            break;
         case 15:
            this.sendAirCommand(29);
            break;
         case 16:
            this.sendAirCommand(28);
            break;
         case 17:
            this.sendAirCommand(25);
      }

   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1, 1});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda6(var1), 100L);
   }

   public View getCusPanoramicView(Context var1) {
      if (this.myPanoramicView == null) {
         this.myPanoramicView = new MyPanoramicView(var1);
      }

      return this.myPanoramicView;
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__310_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirBtnCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__310_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirBtnCommand(var1.getFrontArea().getLineBtnAction()[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$12$com_hzbhd_canbus_car__310_UiMgr(Context var1) {
      ((MyPanoramicView)this.getCusPanoramicView(var1)).setmOnBtnClickListener(this.getOnPanoramicBtnCllick(var1));
   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__310_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirBtnCommand(var1.getFrontArea().getLineBtnAction()[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__310_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirBtnCommand(var1.getFrontArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__310_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirBtnCommand(var1.getRearArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$5$com_hzbhd_canbus_car__310_UiMgr(SettingPageUiSet var1, Context var2, int var3, int var4, int var5) {
      String var7;
      byte var9;
      label79: {
         var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var3)).getItemList().get(var4)).getTitleSrn();
         var7.hashCode();
         switch (var7) {
            case "_55_0x65_data1_bit21":
               var9 = 0;
               break label79;
            case "_18_vehicle_setting_item_1_5":
               var9 = 1;
               break label79;
            case "_310_passenger_wind_mode":
               var9 = 2;
               break label79;
            case "_11_0x26_data2_bit20":
               var9 = 3;
               break label79;
            case "_11_0x26_data3_bit32":
               var9 = 4;
               break label79;
            case "light_ctrl_4":
               var9 = 5;
               break label79;
            case "radar_distance":
               var9 = 6;
               break label79;
            case "_55_0x67_data1_bit32":
               var9 = 7;
               break label79;
            case "_157_seat_auto_move":
               var9 = 8;
               break label79;
            case "_18_vehicle_setting_item_3_43":
               var9 = 9;
               break label79;
         }

         var9 = -1;
      }

      switch (var9) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var5});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var5});
            break;
         case 2:
            boolean var6;
            if (var5 == 1) {
               var6 = true;
            } else {
               var6 = false;
            }

            SharePreUtil.setBoolValue(var2, "share_is_support_passenger_wind_mode", var6);
            this.getMsgMgr(var2).msgMgrUpdateSettingActivity(var7, var5);
            if (var6) {
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_right_blow_foot = false;
               int[] var8 = this.getAirBtnPosition(var2, "blow_positive");
               var4 = var8[0];
               if (var4 != -1) {
                  var3 = var8[1];
                  if (var3 != -1) {
                     this.addAirBtn(var2, var4, var3 + 1, "blow_negative");
                  }
               }
            } else {
               GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
               GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
               GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
               this.removeFrontAirButtonByName(var2, "blow_negative");
            }
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)(var5 + 1)});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)var5});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var5});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var5});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var5});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte)var5});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var5});
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }

   private class OnPanoramicBtnCllick implements MyPanoramicView.OnBtnClickListener {
      final UiMgr this$0;

      private OnPanoramicBtnCllick(UiMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      OnPanoramicBtnCllick(UiMgr var1, Object var2) {
         this(var1);
      }

      public void onEndBottomClick() {
         Log.i("_310_UiMgr", "onEndBottomClick: ");
      }

      public void onEndTopClick() {
         Log.i("_310_UiMgr", "onEndTopClick: ");
      }

      public void onStartBottomClick() {
         Log.i("_310_UiMgr", "onStartBottomClick: ");
      }

      public void onStartTopClick() {
         Log.i("_310_UiMgr", "onStartTopClick: ");
      }
   }
}
