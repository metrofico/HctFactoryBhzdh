package com.hzbhd.canbus.car._337;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   public static int seatState;
   public static int seatStatePortrait;
   OnAirWindSpeedUpDownClickListener OnAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendPortraitAirInfo(9);
         } else {
            this.this$0.sendAirInfo(29);
         }
      }

      public void onClickRight() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendPortraitAirInfo(10);
         } else {
            this.this$0.sendAirInfo(28);
         }
      }
   };
   String PARK_PAGE_360_VISIBILITY_TAG = "PARK_PAGE_360_VISIBILITY_TAG";
   private int cycleModelTag = 0;
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr mMsgMgr;
   OnAirBtnClickListener onAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 1) {
            if (var1 == 2) {
               if (this.this$0.isPortrait()) {
                  this.this$0.sendPortraitAirInfo(21);
                  return;
               }

               this.this$0.sendAirInfo(34);
            }
         } else {
            if (this.this$0.isPortrait()) {
               this.this$0.sendPortraitAirInfo(1);
               return;
            }

            this.this$0.sendAirInfo(16);
         }

      }
   };
   OnAirBtnClickListener onAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0 && this.this$0.isPortrait()) {
            this.this$0.sendPortraitAirInfo(19);
         }

      }
   };
   OnAirBtnClickListener onAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirInfo(129);
            }
         } else {
            this.this$0.sendAirInfo(130);
         }

      }
   };
   OnAirBtnClickListener onAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0 && this.this$0.isPortrait()) {
            this.this$0.sendPortraitAirInfo(20);
         }

      }
   };
   OnAirBtnClickListener onAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               if (this.this$0.isPortrait()) {
                  this.this$0.sendPortraitAirInfo(25);
                  return;
               }

               this.this$0.sendAirInfo(19);
            }
         } else {
            if (this.this$0.isPortrait()) {
               this.this$0.sendPortraitAirInfo(23);
               return;
            }

            this.this$0.sendAirInfo(17);
         }

      }
   };
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequest(35, 0);
         this.this$0.activeRequest(52, 0);
      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.isPortrait()) {
            if (UiMgr.seatStatePortrait == 0) {
               this.this$0.sendPortraitAirInfo(7);
               UiMgr.seatStatePortrait = 1;
            } else if (UiMgr.seatStatePortrait == 1) {
               this.this$0.sendPortraitAirInfo(8);
               UiMgr.seatStatePortrait = 2;
            } else if (UiMgr.seatStatePortrait == 2) {
               this.this$0.sendPortraitAirInfo(32);
               UiMgr.seatStatePortrait = 3;
            } else if (UiMgr.seatStatePortrait == 3) {
               this.this$0.sendPortraitAirInfo(33);
               UiMgr.seatStatePortrait = 4;
            }

         } else {
            if (UiMgr.seatState == 0) {
               this.this$0.sendAirInfo(21);
               UiMgr.seatState = 1;
            } else if (UiMgr.seatState == 1) {
               this.this$0.sendAirInfo(24);
               UiMgr.seatState = 2;
            } else if (UiMgr.seatState == 2) {
               this.this$0.sendAirInfo(25);
               UiMgr.seatState = 3;
            } else if (UiMgr.seatState == 3) {
               this.this$0.sendAirInfo(26);
               UiMgr.seatState = 4;
            } else if (UiMgr.seatState == 4) {
               this.this$0.sendAirInfo(27);
               UiMgr.seatState = 0;
            }

         }
      }

      public void onRightSeatClick() {
         if (UiMgr.seatState == 0) {
            this.this$0.sendAirInfo(21);
            UiMgr.seatState = 1;
         } else if (UiMgr.seatState == 1) {
            this.this$0.sendAirInfo(24);
            UiMgr.seatState = 2;
         } else if (UiMgr.seatState == 2) {
            this.this$0.sendAirInfo(25);
            UiMgr.seatState = 3;
         } else if (UiMgr.seatState == 3) {
            this.this$0.sendAirInfo(26);
            UiMgr.seatState = 4;
         } else if (UiMgr.seatState == 4) {
            this.this$0.sendAirInfo(27);
            UiMgr.seatState = 0;
         }

      }
   };
   OnAirSeatClickListener onAirSeatClickListenerRear = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.rearSeatTag == 0) {
            this.this$0.sendAirInfo(135);
            this.this$0.rearSeatTag = 1;
         } else if (this.this$0.rearSeatTag == 1) {
            this.this$0.sendAirInfo(136);
            this.this$0.rearSeatTag = 2;
         } else if (this.this$0.rearSeatTag == 2) {
            this.this$0.sendAirInfo(137);
            this.this$0.rearSeatTag = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.rearSeatTag == 0) {
            this.this$0.sendAirInfo(135);
            this.this$0.rearSeatTag = 1;
         } else if (this.this$0.rearSeatTag == 1) {
            this.this$0.sendAirInfo(136);
            this.this$0.rearSeatTag = 2;
         } else if (this.this$0.rearSeatTag == 2) {
            this.this$0.sendAirInfo(137);
            this.this$0.rearSeatTag = 0;
         }

      }
   };
   OnAirTemperatureUpDownClickListener onAirTempLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendPortraitAirInfo(2);
         } else {
            this.this$0.sendAirInfo(31);
         }
      }

      public void onClickUp() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendPortraitAirInfo(3);
         } else {
            this.this$0.sendAirInfo(30);
         }
      }
   };
   OnAirTemperatureUpDownClickListener onAirTempRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendPortraitAirInfo(4);
         } else {
            this.this$0.sendAirInfo(33);
         }
      }

      public void onClickUp() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendPortraitAirInfo(5);
         } else {
            this.this$0.sendAirInfo(32);
         }
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRarCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirInfo(134);
      }

      public void onClickUp() {
         this.this$0.sendAirInfo(133);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirInfo(132);
      }

      public void onClickRight() {
         this.this$0.sendAirInfo(131);
      }
   };
   OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void create() {
         this.this$0.activeRequest(55, 0);
      }

      public void destroy() {
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
               MyLog.e("LEFT_RIGHT", var2 + "");
               this.this$0.sendAmplifierInfo(5, var2 + 10);
            }
         } else {
            MyLog.e("FRONT_REAR", var2 + "");
            this.this$0.sendAmplifierInfo(6, -var2 + 10);
         }

      }
   };
   OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         String var4 = "0x" + var2;
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     this.this$0.sendAmplifierInfo(4, Integer.parseInt(var4.replaceAll("^0[x|X]", ""), 16));
                  }
               } else {
                  this.this$0.sendAmplifierInfo(3, Integer.parseInt(var4.replaceAll("^0[x|X]", ""), 16));
               }
            } else {
               this.this$0.sendAmplifierInfo(2, Integer.parseInt(var4.replaceAll("^0[x|X]", ""), 16));
            }
         } else {
            this.this$0.sendAmplifierInfo(1, var2);
         }

      }
   };
   OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         switch (var1) {
            case 0:
               this.this$0.sendOriginalCarInfo(2, 0, 0);
               break;
            case 1:
               this.this$0.sendOriginalCarInfo(3, 0, 0);
               break;
            case 2:
               if (this.this$0.cycleModelTag == 0) {
                  this.this$0.sendOriginalCarInfo(8, 0, 0);
                  this.this$0.cycleModelTag = 1;
               } else {
                  this.this$0.sendOriginalCarInfo(17, 0, 0);
                  this.this$0.cycleModelTag = 0;
               }
               break;
            case 3:
               if (this.this$0.playTag == 0) {
                  this.this$0.sendOriginalCarInfo(19, 0, 0);
                  this.this$0.playTag = 1;
               } else {
                  this.this$0.sendOriginalCarInfo(20, 0, 0);
                  this.this$0.playTag = 0;
               }
               break;
            case 4:
               this.this$0.sendOriginalCarInfo(0, 0, 0);
               break;
            case 5:
               this.this$0.sendOriginalCarInfo(4, 0, 0);
               break;
            case 6:
               this.this$0.sendOriginalCarInfo(1, 0, 0);
         }

      }
   };
   OnOriginalCarDevicePageStatusListener onOriginalCarDevicePageStatusListener = new OnOriginalCarDevicePageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequest(56, 0);
      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_337_air")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_337_air", "_337_air_2")) {
               this.this$0.sendAirInfo(36);
               return;
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_337_air", "_337_air_3")) {
               this.this$0.sendAirInfo(37);
               return;
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_337_air", "_337_air_4")) {
               this.this$0.sendAirInfo(38);
               return;
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_337_air", "_337_air_5")) {
               this.this$0.sendAirInfo(39);
               return;
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_337_air", "_337_air_6")) {
               this.this$0.sendAirInfo(40);
               return;
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_337_air", "_337_air_7")) {
               this.this$0.sendAirInfo(41);
               return;
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_337_air", "_337_air_8")) {
               this.this$0.sendAirInfo(42);
               return;
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_337_air", "_337_air_9")) {
               this.this$0.sendAirInfo(43);
            }
         }

      }
   };
   OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_337_car_setting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_36")) {
               this.this$0.send0x83CarSettingInfo(64, var3 + 10, 0);
            }
         }

      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_337_car_setting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_0")) {
               this.this$0.send0x83CarSettingInfo(4, var3 + 1, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_1")) {
               this.this$0.send0x83CarSettingInfo(5, var3 + 1, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_2")) {
               this.this$0.send0x83CarSettingInfo(6, var3 + 1, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_3")) {
               this.this$0.send0x83CarSettingInfo(6, var3 + 4, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_4")) {
               this.this$0.send0x83CarSettingInfo(7, var3 + 1, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_5")) {
               this.this$0.send0x83CarSettingInfo(8, var3 + 1, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_6")) {
               this.this$0.send0x83CarSettingInfo(9, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_7")) {
               this.this$0.send0x83CarSettingInfo(12, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_8")) {
               this.this$0.send0x83CarSettingInfo(13, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_9")) {
               this.this$0.send0x83CarSettingInfo(15, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_10")) {
               this.this$0.send0x83CarSettingInfo(14, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_11")) {
               this.this$0.send0x83CarSettingInfo(16, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_12")) {
               this.this$0.send0x83CarSettingInfo(17, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_13")) {
               this.this$0.send0x83CarSettingInfo(18, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_14")) {
               this.this$0.send0x83CarSettingInfo(19, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_15")) {
               this.this$0.send0x83CarSettingInfo(23, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_16")) {
               this.this$0.send0x83CarSettingInfo(24, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_17")) {
               this.this$0.send0x83CarSettingInfo(11, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_18")) {
               this.this$0.send0x83CarSettingInfo(25, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_19")) {
               this.this$0.send0x83CarSettingInfo(27, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_20")) {
               this.this$0.send0x83CarSettingInfo(28, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_21")) {
               this.this$0.send0x83CarSettingInfo(48, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_22")) {
               this.this$0.send0x83CarSettingInfo(49, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_23")) {
               this.this$0.send0x83CarSettingInfo(50, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_24")) {
               this.this$0.send0x83CarSettingInfo(52, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_25")) {
               this.this$0.send0x83CarSettingInfo(53, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_26")) {
               this.this$0.send0x83CarSettingInfo(54, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_26_on_27")) {
               this.this$0.send0x83CarSettingInfo(55, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_27")) {
               this.this$0.send0x83CarSettingInfo(57, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_28")) {
               this.this$0.send0x83CarSettingInfo(58, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_29")) {
               this.this$0.send0x83CarSettingInfo(59, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_30")) {
               this.this$0.send0x83CarSettingInfo(60, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_31")) {
               this.this$0.send0x83CarSettingInfo(61, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_32")) {
               this.this$0.send0x83CarSettingInfo(51, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_33")) {
               this.this$0.send0x83CarSettingInfo(62, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_34")) {
               this.this$0.send0x83CarSettingInfo(68, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_35")) {
               this.this$0.send0x83CarSettingInfo(56, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_37")) {
               this.this$0.send0x83CarSettingInfo(65, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_38")) {
               this.this$0.send0x83CarSettingInfo(63, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_39")) {
               this.this$0.send0x83CarSettingInfo(69, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_40")) {
               this.this$0.send0x83CarSettingInfo(70, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_41")) {
               this.this$0.send0x83CarSettingInfo(66, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_42")) {
               this.this$0.send0x83CarSettingInfo(67, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_43")) {
               this.this$0.send0x83CarSettingInfo(72, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_44")) {
               this.this$0.send0x83CarSettingInfo(71, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_45")) {
               this.this$0.send0x83CarSettingInfo(73, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_46")) {
               this.this$0.send0x83CarSettingInfo(74, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_47")) {
               this.this$0.send0x83CarSettingInfo(76, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_48")) {
               this.this$0.send0x83CarSettingInfo(77, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_49")) {
               this.this$0.send0x83CarSettingInfo(78, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_50")) {
               this.this$0.send0x83CarSettingInfo(79, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_51")) {
               this.this$0.send0x83CarSettingInfo(75, var3, 0);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_setting_52")) {
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.PARK_PAGE_360_VISIBILITY_TAG, var3);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).showDialogAndRestartApp(this.this$0.mContext.getString(2131763943));
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_park")) {
               this.this$0.send0x83CarSettingInfo(2, var3 + 1, 0);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_car_setting", "_337_rear_camera")) {
               this.this$0.send0x83CarSettingInfo(1, var3, 0);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_337_amplifier_info")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_amplifier_info", "_337_amplifier_info1")) {
               if (var3 != 0) {
                  this.this$0.sendAmplifierInfo(9, var3 - 1);
               } else {
                  this.this$0.sendAmplifierInfo(9, 5);
               }

               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_amplifier_info", "_337_amplifier_info2")) {
               this.this$0.sendAmplifierInfo(7, var3 + 1);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_amplifier_info", "_337_amplifier_info3")) {
               this.this$0.sendAmplifierInfo(10, var3);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_337_amplifier_info", "_337_amplifier_info4")) {
               this.this$0.sendAmplifierInfo(8, var3);
            }
         }

      }
   };
   OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         UiMgr var2 = this.this$0;
         if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_337_car_setting")) {
            this.this$0.activeRequest(49, 0);
         }

         var2 = this.this$0;
         if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_337_air")) {
            this.this$0.activeRequest(53, 0);
         }

         var2 = this.this$0;
         if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_337_car_config")) {
            this.this$0.activeRequest(63, 0);
         }

      }
   };
   DecimalFormat oneDecimal = new DecimalFormat("###0.0");
   private int playTag = 0;
   private int rearSeatTag = 0;
   DecimalFormat timeFormat = new DecimalFormat("00");
   DecimalFormat towDecimal = new DecimalFormat("###0.00");

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.PARK_PAGE_360_VISIBILITY_TAG = this.PARK_PAGE_360_VISIBILITY_TAG + this.differentId + "" + this.eachId;
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerTop, this.onAirBtnClickListenerLeft, this.onAirBtnClickListenerRight, this.onAirBtnClickListenerBottom});
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.OnAirWindSpeedUpDownClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTempLeft, null, this.onAirTempRight});
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.onAirBtnClickListenerRearBottom});
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.onAirTemperatureUpDownClickListenerRarCenter, null});
      var2.getRearArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerRear);
      var2.getRearArea().setOnAirSeatClickListener(this.onAirSeatClickListenerRear);
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var3.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var3.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
      var3.setOnSettingItemClickListener(this.onSettingItemClickListener);
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(this.mContext);
      var4.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
      var4.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var4.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      OriginalCarDevicePageUiSet var5 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var5.setOnOriginalCarDevicePageStatusListener(this.onOriginalCarDevicePageStatusListener);
      var5.setOnOriginalBottomBtnClickListeners(this.onOriginalBottomBtnClickListener);
   }

   private void activeRequest(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1, (byte)var2});
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initAirUI() {
      AirPageUiSet var1 = this.getAirUiSet(this.mContext);
      if (this.isH9()) {
         var1.setHaveRearArea(true);
      } else {
         var1.setHaveRearArea(false);
      }

      if (this.isPortrait()) {
         this.getAirUiSet(this.mContext).getFrontArea().setDisableBtnArray(new String[]{"dual"});
      } else {
         this.getAirUiSet(this.mContext).getFrontArea().setDisableBtnArray(new String[]{"dual", "rear_defog", "front_defog"});
      }

   }

   private int initAmplifierUi() {
      if (!this.isH9AndH6Couple() && !this.isH7()) {
         this.removeMainPrjBtnByName(this.mContext, "amplifier");
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_337_amplifier_info"});
         return 0;
      } else {
         return 1;
      }
   }

   private int initCArConfigInfoUi() {
      if (!this.isH7()) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_337_car_config"});
         return 0;
      } else {
         return 1;
      }
   }

   private void initDriveDataUi() {
      boolean var4 = this.isCarModel7();
      boolean var3 = true;
      boolean var1;
      if (!var4) {
         this.removeDriveDataPagesByHeadTitles(this.mContext, new String[]{"_337_drive_car_info"});
         var1 = false;
      } else {
         var1 = true;
      }

      if (!this.isH9()) {
         this.removeDriveDataPagesByHeadTitles(this.mContext, new String[]{"_337_drive_car_info_tow"});
      } else {
         var1 = true;
      }

      boolean var2 = var3;
      if (!this.isCarModel6()) {
         var2 = var3;
         if (!this.isH7()) {
            this.removeDriveDataPagesByHeadTitles(this.mContext, new String[]{"_337_panorama_info"});
            var2 = var1;
         }
      }

      if (!var2) {
         this.removeMainPrjBtnByName(this.mContext, "drive_data");
      }

   }

   private void initOriginalCarUi() {
      if (!this.isH7()) {
         this.removeMainPrjBtnByName(this.mContext, "original_car_device");
      }

   }

   private void initParkPageUi() {
      MsgMgr var1;
      Context var2;
      if (SharePreUtil.getIntValue(this.mContext, this.PARK_PAGE_360_VISIBILITY_TAG, 0) == 0) {
         if (!this.getMsgMgr(this.mContext).is404("_337_car_setting", "_337_setting_52")) {
            var1 = this.getMsgMgr(this.mContext);
            var2 = this.mContext;
            var1.updateSettings(var2, this.getSettingLeftIndexes(var2, "_337_car_setting"), this.getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_52"), this.PARK_PAGE_360_VISIBILITY_TAG, 0);
         }

         this.getParkPageUiSet(this.mContext).setShowPanoramic(true);
      } else {
         if (!this.getMsgMgr(this.mContext).is404("_337_car_setting", "_337_setting_52")) {
            var1 = this.getMsgMgr(this.mContext);
            var2 = this.mContext;
            var1.updateSettings(var2, this.getSettingLeftIndexes(var2, "_337_car_setting"), this.getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_52"), this.PARK_PAGE_360_VISIBILITY_TAG, 1);
         }

         this.getParkPageUiSet(this.mContext).setShowPanoramic(false);
      }

   }

   private int initSeatSettingUi() {
      boolean var1;
      if (!this.isH9AndH6Couple()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_air_2", "_337_air_3", "_337_air_4", "_337_air_5"});
         var1 = false;
      } else {
         var1 = true;
      }

      if (!this.isH9()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_air_6", "_337_air_7", "_337_air_8", "_337_air_9"});
      } else {
         var1 = true;
      }

      if (!var1) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_337_air"});
         return 0;
      } else {
         return 1;
      }
   }

   private void initUi() {
      int var1 = this.intiCarSeetingUi();
      int var3 = this.initSeatSettingUi();
      this.initAirUI();
      this.initDriveDataUi();
      int var2 = this.initAmplifierUi();
      this.initOriginalCarUi();
      int var4 = this.initCArConfigInfoUi();
      this.initParkPageUi();
      if (var1 == 0 && var3 == 0 && var2 == 0 && var4 == 0) {
         this.removeMainPrjBtnByName(this.mContext, "setting");
      }

   }

   private int intiCarSeetingUi() {
      boolean var1;
      if (this.isCarModel1()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_0", "_337_setting_1", "_337_setting_2", "_337_setting_3"});
         var1 = false;
      } else {
         if (this.isGreatWall()) {
            this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_2"});
         } else {
            this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_3"});
         }

         var1 = true;
      }

      if (!this.isCarModel2()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_4"});
      } else {
         var1 = true;
      }

      if (!this.isCarModel3()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_5"});
      } else {
         var1 = true;
      }

      if (!this.isCarModel4()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_6"});
      } else {
         var1 = true;
      }

      if (!this.isH9()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_7", "_337_setting_9", "_337_setting_10", "_337_setting_11", "_337_setting_12", "_337_setting_13", "_337_setting_14", "_337_setting_15", "_337_setting_16", "_337_setting_18", "_337_setting_33", "_337_setting_34", "_337_setting_35", "_337_setting_36", "_337_setting_37", "_337_setting_38", "_337_setting_39", "_337_setting_40", "_337_setting_41", "_337_setting_42", "_337_setting_43", "_337_setting_44", "_337_setting_45", "_337_setting_46"});
      } else {
         var1 = true;
      }

      if (!this.isH9AndH7()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_8", "_337_setting_19", "_337_setting_30"});
      } else {
         var1 = true;
      }

      if (!this.isH9AndF7()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_8", "_337_setting_19", "_337_setting_21", "_337_setting_22", "_337_setting_23", "_337_setting_24", "_337_setting_25", "_337_setting_26", "_337_setting_26_on_27", "_337_setting_27", "_337_setting_28", "_337_setting_29", "_337_setting_30", "_337_setting_32", "_337_setting_51"});
      } else {
         var1 = true;
      }

      if (!this.isH7()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_31"});
      } else {
         var1 = true;
      }

      if (!this.isCarModel5()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_17"});
      } else {
         var1 = true;
      }

      if (!this.isCarModel6()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_20"});
      } else {
         var1 = true;
      }

      if (!this.isGreatWall()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_47", "_337_setting_48", "_337_setting_49", "_337_setting_50"});
      } else {
         var1 = true;
      }

      if (!this.isH2()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_337_park", "_337_rear_camera"});
      } else {
         var1 = true;
      }

      if (!var1) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_337_car_setting"});
         return 0;
      } else {
         return 1;
      }
   }

   private void intiData() {
      this.selectCarModel();
   }

   private boolean isH2() {
      int var1 = this.eachId;
      return var1 == 2 || var1 == 3;
   }

   private void sendAmplifierInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte)var1, (byte)var2});
   }

   private void sendCarModelInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)var1});
   }

   private void sendOriginalCarInfo(int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte)var1, (byte)var2, (byte)var3});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var7 = var5.iterator();

         while(var7.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

            for(int var4 = 0; var4 < var6.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var6.get(var4)).getTitleSrn().equals(var2)) {
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
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var10 = var7.getItemList();
            Iterator var8 = var10.iterator();

            for(int var5 = 0; var5 < var10.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var8.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public boolean isCarModel1() {
      int var1 = this.eachId;
      return var1 == 7 || var1 == 2 || var1 == 3 || var1 == 4 || var1 == 15 || var1 == 16;
   }

   public boolean isCarModel2() {
      int var1 = this.eachId;
      return var1 == 7 || var1 == 8 || var1 == 9 || var1 == 10 || var1 == 11 || var1 == 12;
   }

   public boolean isCarModel3() {
      int var1 = this.eachId;
      return var1 == 7 || var1 == 8 || var1 == 9 || var1 == 10;
   }

   public boolean isCarModel4() {
      int var1 = this.eachId;
      return var1 == 1 || var1 == 2 || var1 == 3 || var1 == 9 || var1 == 10 || var1 == 11 || var1 == 12 || var1 == 13 || var1 == 14 || var1 == 15;
   }

   public boolean isCarModel5() {
      int var1 = this.eachId;
      return var1 == 2 || var1 == 7 || var1 == 9 || var1 == 13 || var1 == 14;
   }

   public boolean isCarModel6() {
      int var1 = this.eachId;
      return var1 == 9 || var1 == 11 || var1 == 12;
   }

   public boolean isCarModel7() {
      int var1 = this.eachId;
      return var1 == 4 || var1 == 7 || var1 == 9 || var1 == 10 || var1 == 11 || var1 == 12 || var1 == 13 || var1 == 14 || var1 == 16;
   }

   public boolean isCarModel8() {
      int var1 = this.eachId;
      return var1 == 9 || var1 == 12 || var1 == 14;
   }

   public boolean isGreatWall() {
      return this.differentId == 0;
   }

   public boolean isH7() {
      return this.eachId == 12;
   }

   public boolean isH9() {
      return this.eachId == 11;
   }

   public boolean isH9AndF7() {
      int var1 = this.eachId;
      return var1 == 11 || var1 == 14;
   }

   public boolean isH9AndH6Couple() {
      int var1 = this.eachId;
      return var1 == 10 || var1 == 11;
   }

   public boolean isH9AndH7() {
      int var1 = this.eachId;
      return var1 == 11 || var1 == 12;
   }

   public boolean isLandscape() {
      boolean var1;
      if (this.mContext.getResources().getConfiguration().orientation == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isPortrait() {
      int var1 = this.mContext.getResources().getConfiguration().orientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void makeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void selectCarModel() {
      switch (this.eachId) {
         case 1:
            this.sendCarModelInfo(32);
            break;
         case 2:
            this.sendCarModelInfo(1);
            break;
         case 3:
            this.sendCarModelInfo(2);
            break;
         case 4:
            this.sendCarModelInfo(4);
            break;
         case 5:
            this.sendCarModelInfo(5);
            break;
         case 6:
            this.sendCarModelInfo(5);
            break;
         case 7:
            this.sendCarModelInfo(8);
         case 8:
         default:
            break;
         case 9:
            this.sendCarModelInfo(12);
            break;
         case 10:
            this.sendCarModelInfo(6);
            break;
         case 11:
            this.sendCarModelInfo(9);
            break;
         case 12:
            this.sendCarModelInfo(13);
            break;
         case 13:
            this.sendCarModelInfo(158);
            break;
         case 14:
            this.sendCarModelInfo(15);
            break;
         case 15:
            this.sendCarModelInfo(1);
            break;
         case 16:
            this.sendCarModelInfo(4);
            break;
         case 17:
            this.sendCarModelInfo(17);
      }

   }

   public void send0x83CarSettingInfo(int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2, (byte)var3});
   }

   public void sendAirInfo(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -124, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -124, var2, 0});
   }

   public void sendPhoneInfo(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void sendPortraitAirInfo(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 0});
   }

   public void sendRadioInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void sendSourceCmd(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2});
   }

   public void sendSourceInfo1Line(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void sendSourceInfo2Line(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void sendSourceInfo3Line(int var1, int var2, int var3, int var4, int var5, int var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initUi();
      this.intiData();
   }
}
