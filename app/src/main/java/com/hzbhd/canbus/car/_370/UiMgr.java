package com.hzbhd.canbus.car._370;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private int DifferentID;
   private int EachID;
   int a = 0;
   private AirPageUiSet airPageUiSet;
   private Context mContext;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.this$0.sendAirData(13);
               }
            } else {
               this.this$0.sendAirData(3);
            }
         } else {
            this.this$0.sendAirData(15);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirData(14);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirData(2);
               }
            } else {
               this.this$0.sendAirData(1);
            }
         } else {
            this.this$0.sendAirData(16);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirData(12);
         }

      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirData(17);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirData(18);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData(5);
      }

      public void onClickUp() {
         this.this$0.sendAirData(4);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData(21);
      }

      public void onClickUp() {
         this.this$0.sendAirData(20);
      }
   };
   private MsgMgr msgMgr;
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.a == 0) {
            this.this$0.sendAirData(8);
            this.this$0.a = 1;
         } else if (this.this$0.a == 1) {
            this.this$0.sendAirData(9);
            this.this$0.a = 2;
         } else if (this.this$0.a == 2) {
            this.this$0.sendAirData(10);
            this.this$0.a = 3;
         } else if (this.this$0.a == 3) {
            this.this$0.sendAirData(11);
            this.this$0.a = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.a == 0) {
            this.this$0.sendAirData(8);
            this.this$0.a = 1;
         } else if (this.this$0.a == 1) {
            this.this$0.sendAirData(9);
            this.this$0.a = 2;
         } else if (this.this$0.a == 2) {
            this.this$0.sendAirData(10);
            this.this$0.a = 3;
         } else if (this.this$0.a == 3) {
            this.this$0.sendAirData(11);
            this.this$0.a = 0;
         }

      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirData(7);
      }

      public void onClickRight() {
         this.this$0.sendAirData(6);
      }
   };
   OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 10)});
               Log.d("lai", var2 + "l2");
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)(-var2 + 10)});
            Log.d("lai", var2 + "l1");
         }

      }
   };
   OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)(var2 + 1)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 1)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 1)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)var2});
         }

      }
   };
   OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var4.hashCode();
         if (!var4.equals("hiworld_jeep_123_0x62_data3_765")) {
            if (var4.equals("compass_deviation")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, (byte)var3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte)var3});
         }

      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var4.hashCode();
         var2 = var4.hashCode();
         byte var5 = -1;
         switch (var2) {
            case -2023122657:
               if (var4.equals("_370_Engine_Off_Light_delay")) {
                  var5 = 0;
               }
               break;
            case -1802151965:
               if (var4.equals("_112_rear_mirror_dimmer")) {
                  var5 = 1;
               }
               break;
            case -1791231156:
               if (var4.equals("_370_Air_Pressure")) {
                  var5 = 2;
               }
               break;
            case -1710161959:
               if (var4.equals("hiworld_jeep_123_0x62_data1_4")) {
                  var5 = 3;
               }
               break;
            case -1710160041:
               if (var4.equals("hiworld_jeep_123_0x62_data3_0")) {
                  var5 = 4;
               }
               break;
            case -1710160040:
               if (var4.equals("hiworld_jeep_123_0x62_data3_1")) {
                  var5 = 5;
               }
               break;
            case -1710160039:
               if (var4.equals("hiworld_jeep_123_0x62_data3_2")) {
                  var5 = 6;
               }
               break;
            case -1710160038:
               if (var4.equals("hiworld_jeep_123_0x62_data3_3")) {
                  var5 = 7;
               }
               break;
            case -1710160037:
               if (var4.equals("hiworld_jeep_123_0x62_data3_4")) {
                  var5 = 8;
               }
               break;
            case -1475383431:
               if (var4.equals("hiworld_jeep_123_0x62_data2_10")) {
                  var5 = 9;
               }
               break;
            case -1475383367:
               if (var4.equals("hiworld_jeep_123_0x62_data2_32")) {
                  var5 = 10;
               }
               break;
            case -1475383239:
               if (var4.equals("hiworld_jeep_123_0x62_data2_76")) {
                  var5 = 11;
               }
               break;
            case -1319415810:
               if (var4.equals("_370_Automatic_parking_brake")) {
                  var5 = 12;
               }
               break;
            case -1082386052:
               if (var4.equals("hiworld_jeep_123_0x60_data1_65")) {
                  var5 = 13;
               }
               break;
            case -984370334:
               if (var4.equals("_370_Speed_adjustment_volume")) {
                  var5 = 14;
               }
               break;
            case -280006876:
               if (var4.equals("_370_Fuel_consumption")) {
                  var5 = 15;
               }
               break;
            case -187827060:
               if (var4.equals("_370_Measure")) {
                  var5 = 16;
               }
               break;
            case -63588360:
               if (var4.equals("_370_Mileage")) {
                  var5 = 17;
               }
               break;
            case 102584022:
               if (var4.equals("language_setup")) {
                  var5 = 18;
               }
               break;
            case 234755008:
               if (var4.equals("_370_Surround_Sound")) {
                  var5 = 19;
               }
               break;
            case 591812763:
               if (var4.equals("_370_Remote_Start")) {
                  var5 = 20;
               }
               break;
            case 931921358:
               if (var4.equals("_370_Engine_Off_Power_delay")) {
                  var5 = 21;
               }
               break;
            case 941738070:
               if (var4.equals("_370_Start_Open_Heat")) {
                  var5 = 22;
               }
               break;
            case 947163282:
               if (var4.equals("compass_direction")) {
                  var5 = 23;
               }
               break;
            case 1026194358:
               if (var4.equals("_370_Remote_Start_Reminder")) {
                  var5 = 24;
               }
               break;
            case 1170379802:
               if (var4.equals("hiworld_jeep_123_0x43_data3_0")) {
                  var5 = 25;
               }
               break;
            case 1170379803:
               if (var4.equals("hiworld_jeep_123_0x43_data3_1")) {
                  var5 = 26;
               }
               break;
            case 1170380765:
               if (var4.equals("hiworld_jeep_123_0x43_data4_2")) {
                  var5 = 27;
               }
               break;
            case 1170380766:
               if (var4.equals("hiworld_jeep_123_0x43_data4_3")) {
                  var5 = 28;
               }
               break;
            case 1170380768:
               if (var4.equals("hiworld_jeep_123_0x43_data4_5")) {
                  var5 = 29;
               }
               break;
            case 1170380769:
               if (var4.equals("hiworld_jeep_123_0x43_data4_6")) {
                  var5 = 30;
               }
               break;
            case 1170381728:
               if (var4.equals("hiworld_jeep_123_0x43_data5_4")) {
                  var5 = 31;
               }
               break;
            case 1508681806:
               if (var4.equals("hiworld_jeep_123_0x62_data3_4_2")) {
                  var5 = 32;
               }
               break;
            case 1904746963:
               if (var4.equals("hiworld_jeep_123_0x60_data1_0")) {
                  var5 = 33;
               }
               break;
            case 1904746964:
               if (var4.equals("hiworld_jeep_123_0x60_data1_1")) {
                  var5 = 34;
               }
               break;
            case 1904746965:
               if (var4.equals("hiworld_jeep_123_0x60_data1_2")) {
                  var5 = 35;
               }
               break;
            case 1904746966:
               if (var4.equals("hiworld_jeep_123_0x60_data1_3")) {
                  var5 = 36;
               }
               break;
            case 1904746967:
               if (var4.equals("hiworld_jeep_123_0x60_data1_4")) {
                  var5 = 37;
               }
               break;
            case 1918511028:
               if (var4.equals("_370_Driver_Seat_Convenient")) {
                  var5 = 38;
               }
               break;
            case 1922035637:
               if (var4.equals("hiworld_jeep_123_0x43_data3_32")) {
                  var5 = 39;
               }
               break;
            case 1922035701:
               if (var4.equals("hiworld_jeep_123_0x43_data3_54")) {
                  var5 = 40;
               }
               break;
            case 1922035765:
               if (var4.equals("hiworld_jeep_123_0x43_data3_76")) {
                  var5 = 41;
               }
               break;
            case 1922065364:
               if (var4.equals("hiworld_jeep_123_0x43_data4_10")) {
                  var5 = 42;
               }
               break;
            case 1922095155:
               if (var4.equals("hiworld_jeep_123_0x43_data5_10")) {
                  var5 = 43;
               }
               break;
            case 1922095219:
               if (var4.equals("hiworld_jeep_123_0x43_data5_32")) {
                  var5 = 44;
               }
               break;
            case 2072595165:
               if (var4.equals("_370_Temp_Unit")) {
                  var5 = 45;
               }
               break;
            case 2134500875:
               if (var4.equals("_370_Remote_unlock")) {
                  var5 = 46;
               }
         }

         switch (var5) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte)this.this$0.ResolveDelay(var3)});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte)var3});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte)var3});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte)var3});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte)var3});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte)var3});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte)var3});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte)var3});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var3});
               break;
            case 9:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)this.this$0.ResolveDelay(var3)});
               break;
            case 10:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)this.this$0.ResolveDelay(var3)});
               break;
            case 11:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte)(var3 + 1)});
               break;
            case 12:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte)var3});
               break;
            case 13:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte)var3});
               break;
            case 14:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -48, (byte)var3});
               break;
            case 15:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
               break;
            case 16:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
               break;
            case 17:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
               break;
            case 18:
               if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 10});
               }
               break;
            case 19:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -47, (byte)var3});
               break;
            case 20:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 59, (byte)var3});
               break;
            case 21:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte)this.this$0.ResolveDelay2(var3)});
               break;
            case 22:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -112, (byte)var3});
               break;
            case 23:
               CanbusMsgSender.sendMsg(new byte[]{22, -55, (byte)this.this$0.Resolvecompass(var3)});
               break;
            case 24:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -111, (byte)var3});
               break;
            case 25:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte)var3});
               break;
            case 26:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte)var3});
               break;
            case 27:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte)var3});
               break;
            case 28:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte)var3});
               break;
            case 29:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte)var3});
               break;
            case 30:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte)var3});
               break;
            case 31:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)var3});
               break;
            case 32:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte)var3});
               break;
            case 33:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte)var3});
               break;
            case 34:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte)var3});
               break;
            case 35:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte)var3});
               break;
            case 36:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte)var3});
               break;
            case 37:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte)var3});
               break;
            case 38:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)var3});
               break;
            case 39:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, (byte)var3});
               break;
            case 40:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, (byte)var3});
               break;
            case 41:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte)var3});
               break;
            case 42:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte)var3});
               break;
            case 43:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var3});
               break;
            case 44:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte)var3});
               break;
            case 45:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
               break;
            case 46:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 60, (byte)var3});
         }

      }
   };
   private OriginalCarDevicePageUiSet originalCarDevicePageUiSet;
   private SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.EachID = this.getEachId();
      this.DifferentID = this.getCurrentCarId();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var2;
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      this.settingPageUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (!var3.equals("compass_run_calibration")) {
               if (var3.equals("hiworld_jeep_123_0x43_data5_5")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, 1});
            }

         }
      });
      GeneralDoorData.isShowHandBrake = true;
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      var4.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var4.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      AirPageUiSet var5 = this.getAirUiSet(var1);
      var5.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var5.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var5.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerFront);
      var5.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var5.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight});
      OriginalCarDevicePageUiSet var3 = this.getOriginalCarDevicePageUiSet(var1);
      this.originalCarDevicePageUiSet = var3;
      var3.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
         }
      });
      this.originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickTopBtnItem(int var1) {
            UiMgr var2;
            if (var1 != 0) {
               if (var1 == 1) {
                  var2 = this.this$0;
                  if (var2.getMsgMgr(var2.mContext).rdm == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -56, 10});
                  } else {
                     var2 = this.this$0;
                     if (var2.getMsgMgr(var2.mContext).rdm == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 9});
                     }
                  }
               }
            } else {
               var2 = this.this$0;
               if (var2.getMsgMgr(var2.mContext).rpt == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 15});
               } else {
                  var2 = this.this$0;
                  if (var2.getMsgMgr(var2.mContext).rpt == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -56, 14});
                  }
               }
            }

         }
      });
      this.originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickBottomBtnItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -56, 1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 4});
            }

         }
      });
   }

   private int ResolveDelay(int var1) {
      if (var1 == 0) {
         return 0;
      } else if (var1 == 1) {
         return 30;
      } else {
         return var1 == 2 ? 60 : 90;
      }
   }

   private int ResolveDelay2(int var1) {
      if (var1 == 0) {
         return 0;
      } else if (var1 == 1) {
         return 3;
      } else {
         return var1 == 2 ? 20 : 40;
      }
   }

   private int Resolvecompass(int var1) {
      if (var1 == 0) {
         return 0;
      } else if (var1 == 1) {
         return 8;
      } else if (var1 == 2) {
         return 16;
      } else if (var1 == 3) {
         return 24;
      } else if (var1 == 4) {
         return 32;
      } else if (var1 == 5) {
         return 40;
      } else {
         return var1 == 6 ? 48 : 56;
      }
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private void sendAirData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte)var1, 1});
   }

   private void sendAmplifierData(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -124, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -124, var2, 0});
   }

   private int swapVal(int var1) {
      byte var2;
      if (var1 == 0) {
         var2 = 2;
      } else {
         var2 = 1;
      }

      return var2;
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            List var5 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

            for(int var4 = 0; var4 < var5.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var5.get(var4)).getTitleSrn().equals(var2)) {
                  return var4;
               }
            }
         }
      }

      return -1;
   }

   protected int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var8 = var7.getItemList();
            Iterator var10 = var8.iterator();

            for(int var5 = 0; var5 < var8.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var10.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.DifferentID == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 1});
      }

      if (this.DifferentID == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0});
      }

      if (this.EachID == 2) {
         this.removeMainPrjBtnByName(this.mContext, "air");
         this.removeMainPrjBtnByName(this.mContext, "amplifier");
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_370_Sound_Settings"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x60_data1_1"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x60_data1_2"});
      }

      if (this.EachID != 3) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data3_765"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Remote_Start_Reminder"});
      }

      if (this.EachID != 2) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data3_2"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Remote_Start"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Remote_unlock"});
      }

      if (this.EachID != 1) {
         this.removeMainPrjBtnByName(this.mContext, "original_car_device");
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"engine_speed"});
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"a_current_speed"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data2_32"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data3_0"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data3_1"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_112_rear_mirror_dimmer"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x60_data1_4"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Engine_Off_Light_delay"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Engine_Off_Power_delay"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Driver_Seat_Convenient"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Start_Open_Heat"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data5_10"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data5_4"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data4_2"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data4_6"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_1"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_0"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_32"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_54"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data4_10"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data4_5"});
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"compass"});
      }

      if (this.EachID == 1) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Fuel_consumption"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Mileage"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Temp_Unit"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Air_Pressure"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data1_4"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data2_76"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x60_data1_65"});
      }

   }
}
