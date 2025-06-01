package com.hzbhd.canbus.car._372;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   public static int accState;
   public static int cruiseSpeedLimit_Data0Bit0;
   public static int cruiseSpeedLimit_Data0Bit1;
   public static int cruiseSpeedLimit_Data0Bit2;
   public static int cruiseSpeedLimit_Data0Bit3;
   public static int cruiseSpeedLimit_Data0Bit4;
   public static int cruiseSpeedLimit_Data0Bit5;
   public static int cruiseSpeedLimit_Data0Bit6;
   public static int cruiseSpeedLimit_Data0Bit7;
   public static int cruiseSpeed_Data1;
   public static int cruiseSpeed_Data2;
   public static int cruiseSpeed_Data3;
   public static int cruiseSpeed_Data4;
   public static int cruiseSpeed_Data5;
   public static int cruiseSpeed_Data6;
   public static int delayTime;
   public static int phoneStateData0Bit0;
   public static int phoneStateData0Bit1;
   public static int phoneStateData0Bit2;
   public static int phoneStateData0Bit4;
   public static int phoneStateData0Bit5;
   public static int phoneStateData0Bit6;
   public static int phoneStateData1Bit0;
   public static int phoneStateData1Bit1;
   public static int phoneStateData1Bit2;
   public static int phoneStateData1Bit4;
   public static int phoneStateData2;
   public static int phoneStateData3;
   public static int phoneStateData4;
   public static int speedLimit_Data1;
   public static int speedLimit_Data2;
   public static int speedLimit_Data3;
   public static int speedLimit_Data4;
   public static int speedLimit_Data5;
   public static int speedLimit_Data6;
   public static int speedMemory_data0Bit0;
   public static int speedMemory_data0Bit1;
   public static int speedMemory_data0Bit2;
   public static int speedMemory_data0Bit3;
   public static int speedMemory_data0Bit4;
   public static int speedMemory_data0Bit5;
   public static int speedMemory_data0Bit6;
   public static int speedMemory_data0Bit7;
   public static int speedMemory_data1;
   public static int speedMemory_data2;
   public static int speedMemory_data3;
   public static int speedMemory_data4;
   public static int speedMemory_data5;
   int ComputerInfoData3Bit0 = 0;
   int ComputerInfoData3Bit1 = 0;
   int ComputerInfoData3Bit2 = 0;
   int ComputerInfoData3Bit3 = 0;
   int ComputerInfoData3Bit5 = 0;
   int ComputerInfoData3Bit6 = 0;
   int ComputerInfoData3Bit7 = 0;
   int ComputerInfoData4 = 0;
   int ComputerInfoData5 = 0;
   int eachId = this.getEachId();
   Context mContext;
   MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 2) {
            if (var1 == 3) {
               if (GeneralAirData.auto_wind_lv == 0) {
                  this.this$0.send0xA8AirInfo(9, 1);
               } else if (GeneralAirData.auto_wind_lv == 1) {
                  this.this$0.send0xA8AirInfo(9, 2);
               } else if (GeneralAirData.auto_wind_lv == 2) {
                  this.this$0.send0xA8AirInfo(9, 0);
               }
            }
         } else if (GeneralAirData.ac_max) {
            this.this$0.send0xA8AirInfo(3, 0);
         } else {
            this.this$0.send0xA8AirInfo(3, 1);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 1) {
            if (var1 == 3) {
               if (GeneralAirData.auto) {
                  this.this$0.send0xA8AirInfo(1, 0);
               } else {
                  this.this$0.send0xA8AirInfo(1, 1);
               }
            }
         } else if (GeneralAirData.ac) {
            this.this$0.send0xA8AirInfo(2, 0);
         } else {
            this.this$0.send0xA8AirInfo(2, 1);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            if (GeneralAirData.dual) {
               this.this$0.send0xA8AirInfo(11, 0);
            } else {
               this.this$0.send0xA8AirInfo(11, 1);
            }
         }

      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.windTag == 0) {
            this.this$0.windTag = 1;
            this.this$0.send0xA8AirInfo(6, 1);
            this.this$0.send0xA8AirInfo(7, 1);
            this.this$0.send0xA8AirInfo(8, 1);
         } else {
            this.this$0.windTag = 0;
            this.this$0.send0xA8AirInfo(6, 0);
            this.this$0.send0xA8AirInfo(7, 0);
            this.this$0.send0xA8AirInfo(8, 0);
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.windTag == 0) {
            this.this$0.windTag = 1;
            this.this$0.send0xA8AirInfo(6, 1);
            this.this$0.send0xA8AirInfo(7, 1);
            this.this$0.send0xA8AirInfo(8, 1);
         } else {
            this.this$0.windTag = 0;
            this.this$0.send0xA8AirInfo(6, 0);
            this.this$0.send0xA8AirInfo(7, 0);
            this.this$0.send0xA8AirInfo(8, 0);
         }

      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.send0xA8AirInfo(4, 2);
      }

      public void onClickUp() {
         this.this$0.send0xA8AirInfo(4, 1);
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.send0xA8AirInfo(5, 2);
      }

      public void onClickUp() {
         this.this$0.send0xA8AirInfo(5, 1);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.send0xA8AirInfo(10, 2);
      }

      public void onClickRight() {
         this.this$0.send0xA8AirInfo(10, 1);
      }
   };
   OnConfirmDialogListener onConfirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_setting_speed_memory") && var2 == 12) {
            UiMgr.speedMemory_data0Bit7 = 1;
            UiMgr.speedMemory_data0Bit6 = 0;
            this.this$0.sendMemorySpeedInfo();
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_setting_cruise_speed") && var2 == 6) {
            UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
            UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
            UiMgr.cruiseSpeedLimit_Data0Bit5 = 1;
            UiMgr.cruiseSpeedLimit_Data0Bit4 = 0;
            this.this$0.sendCruiseSpeedInfo();
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_setting_speed_limit") && var2 == 6) {
            UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
            UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
            UiMgr.cruiseSpeedLimit_Data0Bit5 = 1;
            UiMgr.cruiseSpeedLimit_Data0Bit4 = 0;
            this.this$0.sendSpeedLimitInfo();
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_drive_DrivingPage_1") && var2 == 3) {
            this.this$0.ComputerInfoData3Bit6 = 1;
            this.this$0.sendComputerInfo();
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_drive_DrivingPage_2") && var2 == 3) {
            this.this$0.ComputerInfoData3Bit5 = 1;
            this.this$0.sendComputerInfo();
         }

      }
   };
   OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestInfo(57);
         this.this$0.activeRequestInfo(62);
         this.this$0.activeRequestInfo(60);
      }
   };
   OnPanelKeyPositionListener onPanelKeyPositionListener = new OnPanelKeyPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void click(int var1) {
         switch (var1) {
            case 0:
               this.this$0.sendPanelKeyInfo(8);
               break;
            case 1:
               this.this$0.sendPanelKeyInfo(6);
               break;
            case 2:
               this.this$0.sendPanelKeyInfo(9);
               break;
            case 3:
               this.this$0.sendPanelKeyInfo(5);
               break;
            case 4:
               this.this$0.sendPanelKeyInfo(7);
               break;
            case 5:
               this.this$0.sendPanelKeyInfo(2);
               break;
            case 6:
               this.this$0.sendPanelKeyInfo(3);
               break;
            case 7:
               this.this$0.sendPanelKeyInfo(4);
               break;
            case 8:
               this.this$0.sendPanelKeyInfo(1);
               break;
            case 9:
               this.this$0.sendPanelKeyInfo(0);
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
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_carState")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_7")) {
               this.this$0.sendCarParamInfo(4, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_speed_memory")) {
            switch (var2) {
               case 7:
                  UiMgr.speedMemory_data1 = var3;
                  this.this$0.sendMemorySpeedInfo();
                  break;
               case 8:
                  UiMgr.speedMemory_data2 = var3;
                  this.this$0.sendMemorySpeedInfo();
                  break;
               case 9:
                  UiMgr.speedMemory_data3 = var3;
                  this.this$0.sendMemorySpeedInfo();
                  break;
               case 10:
                  UiMgr.speedMemory_data4 = var3;
                  this.this$0.sendMemorySpeedInfo();
                  break;
               case 11:
                  UiMgr.speedMemory_data5 = var3;
                  this.this$0.sendMemorySpeedInfo();
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_cruise_speed")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 != 4) {
                           if (var2 == 5) {
                              UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                              UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                              UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                              UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                              UiMgr.cruiseSpeed_Data6 = var3;
                              this.this$0.sendCruiseSpeedInfo();
                           }
                        } else {
                           UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                           UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                           UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                           UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                           UiMgr.cruiseSpeed_Data5 = var3;
                           this.this$0.sendCruiseSpeedInfo();
                        }
                     } else {
                        UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                        UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                        UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                        UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                        UiMgr.cruiseSpeed_Data4 = var3;
                        this.this$0.sendCruiseSpeedInfo();
                     }
                  } else {
                     UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                     UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                     UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                     UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                     UiMgr.cruiseSpeed_Data3 = var3;
                     this.this$0.sendCruiseSpeedInfo();
                  }
               } else {
                  UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                  UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                  UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                  UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                  UiMgr.cruiseSpeed_Data2 = var3;
                  this.this$0.sendCruiseSpeedInfo();
               }
            } else {
               UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
               UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
               UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
               UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
               UiMgr.cruiseSpeed_Data1 = var3;
               this.this$0.sendCruiseSpeedInfo();
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_speed_limit")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 != 4) {
                           if (var2 == 5) {
                              UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                              UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                              UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                              UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                              UiMgr.speedLimit_Data6 = var3;
                              this.this$0.sendSpeedLimitInfo();
                           }
                        } else {
                           UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                           UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                           UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                           UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                           UiMgr.speedLimit_Data5 = var3;
                           this.this$0.sendSpeedLimitInfo();
                        }
                     } else {
                        UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                        UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                        UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                        UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                        UiMgr.speedLimit_Data4 = var3;
                        this.this$0.sendSpeedLimitInfo();
                     }
                  } else {
                     UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                     UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                     UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                     UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                     UiMgr.speedLimit_Data3 = var3;
                     this.this$0.sendSpeedLimitInfo();
                  }
               } else {
                  UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                  UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                  UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                  UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                  UiMgr.speedLimit_Data2 = var3;
                  this.this$0.sendSpeedLimitInfo();
               }
            } else {
               UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
               UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
               UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
               UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
               UiMgr.speedLimit_Data1 = var3;
               this.this$0.sendSpeedLimitInfo();
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_acc") && var2 == 2) {
            UiMgr.delayTime = var3;
            this.this$0.sendAccInfo();
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_Scheduled_mileage") && var2 == 1) {
            var4 = this.this$0;
            var4.ComputerInfoData4 = var4.getMsb(var3);
            var4 = this.this$0;
            var4.ComputerInfoData5 = var4.getLsb(var3);
            this.this$0.sendComputerInfo();
            var4 = this.this$0;
            MsgMgr var5 = var4.getMsgMgr(var4.mContext);
            Context var6 = this.this$0.mContext;
            var4 = this.this$0;
            var5.updateSettings(var6, var4.getLeftIndexes(var4.mContext, "_333_setting_Scheduled_mileage"), 1, var3);
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
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_carState")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_1")) {
               this.this$0.sendCarParamInfo(2, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_8")) {
               this.this$0.sendCarParamInfo(1, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_9")) {
               this.this$0.sendCarParamInfo(5, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_13")) {
               this.this$0.sendCarParamInfo(6, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_14")) {
               this.this$0.sendCarParamInfo(7, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_15")) {
               this.this$0.sendCarParamInfo(8, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_16")) {
               this.this$0.sendCarParamInfo(9, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_18")) {
               this.this$0.sendCarParamInfo(12, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_19")) {
               this.this$0.sendCarParamInfo(13, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_20")) {
               this.this$0.sendCarParamInfo(14, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_21")) {
               this.this$0.sendCarParamInfo(15, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_22")) {
               this.this$0.sendCarParamInfo(11, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_333_setting_carState", "_333_setting_carState_23")) {
               this.this$0.sendCarParamInfo(10, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_speed_memory")) {
            switch (var2) {
               case 1:
                  this.this$0.sendSettingsSpeedSwitchInfo(var3);
                  break;
               case 2:
                  this.this$0.sendSettingsSpeedSelectionInfo(1, var3);
                  break;
               case 3:
                  this.this$0.sendSettingsSpeedSelectionInfo(2, var3);
                  break;
               case 4:
                  this.this$0.sendSettingsSpeedSelectionInfo(3, var3);
                  break;
               case 5:
                  this.this$0.sendSettingsSpeedSelectionInfo(4, var3);
                  break;
               case 6:
                  this.this$0.sendSettingsSpeedSelectionInfo(5, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_acc") && var2 == 1) {
            if (var3 == 0) {
               UiMgr.accState = 0;
            } else {
               UiMgr.accState = 1;
            }

            this.this$0.sendAccInfo();
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_333_setting_Scheduled_mileage") && var2 == 0) {
            this.this$0.ComputerInfoData3Bit7 = var3;
            this.this$0.sendComputerInfo();
            var4 = this.this$0;
            MsgMgr var6 = var4.getMsgMgr(var4.mContext);
            Context var5 = this.this$0.mContext;
            var4 = this.this$0;
            var6.updateSettings(var5, var4.getLeftIndexes(var4.mContext, "_333_setting_Scheduled_mileage"), 0, var3);
         }

      }
   };
   OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_setting_speed_memory")) {
            this.this$0.activeRequestInfo(59);
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_setting_cruise_speed")) {
            this.this$0.activeRequestInfo(61);
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_setting_speed_limit")) {
            this.this$0.activeRequestInfo(61);
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_setting_acc")) {
            this.this$0.activeRequestInfo(68);
            this.this$0.activeRequestInfo(234);
         }

         var3 = this.this$0;
         int var2 = var3.getLeftIndexes(var3.mContext, "_333_setting_Scheduled_mileage");
         if (var1 == var2) {
            var3 = this.this$0;
            var3.getMsgMgr(var3.mContext).updateSettings(this.this$0.mContext, var2, 0, SharePreUtil.getIntValue(this.this$0.mContext, "EachCanId" + this.this$0.getEachId() + "L" + var2 + "R" + 0, 0));
            var3 = this.this$0;
            var3.getMsgMgr(var3.mContext).updateSettings(this.this$0.mContext, var2, 1, SharePreUtil.getIntValue(this.this$0.mContext, "EachCanId" + this.this$0.getEachId() + "L" + var2 + "R" + 1, 0));
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_drive_DrivingPage_0")) {
            this.this$0.activeRequestInfo(51);
            this.this$0.ComputerInfoData3Bit0 = 0;
            this.this$0.ComputerInfoData3Bit1 = 0;
            this.this$0.ComputerInfoData3Bit2 = 0;
            this.this$0.sendComputerInfo();
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_drive_DrivingPage_1")) {
            this.this$0.activeRequestInfo(52);
            this.this$0.ComputerInfoData3Bit0 = 1;
            this.this$0.ComputerInfoData3Bit1 = 0;
            this.this$0.ComputerInfoData3Bit2 = 0;
            this.this$0.sendComputerInfo();
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_drive_DrivingPage_2")) {
            this.this$0.activeRequestInfo(53);
            this.this$0.ComputerInfoData3Bit0 = 0;
            this.this$0.ComputerInfoData3Bit1 = 1;
            this.this$0.ComputerInfoData3Bit2 = 0;
            this.this$0.sendComputerInfo();
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_333_drive_page_1")) {
            this.this$0.activeRequestInfo(113);
         }

      }
   };
   int windTag = 0;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var2.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var2.setOnSettingConfirmDialogListener(this.onConfirmDialogListener);
      var2.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            UiMgr var3 = this.this$0;
            if (var1 == var3.getLeftIndexes(var3.mContext, "_333_setting_carState")) {
               var3 = this.this$0;
               if (var2 == var3.getSettingRightIndex(var3.mContext, "_333_setting_carState", "_333_setting_carState_25")) {
                  this.this$0.sendCarParamInfo(16, 1);
                  var3 = this.this$0;
                  var3.getMsgMgr(var3.mContext).tireSetting();
               }
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(this.mContext);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      var3.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      this.getPanelKeyPageUiSet(this.mContext).setOnPanelKeyPositionListener(this.onPanelKeyPositionListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void intiUi() {
      if (this.eachId == 14) {
         this.removeDriveDataPagesByHeadTitles(this.mContext, new String[]{"_333_drivePage_front_radar_info"});
      }

      if (this.eachId != 3) {
         Context var2 = this.mContext;
         this.removeSettingRightItem(var2, this.getLeftIndexes(var2, "_333_drive_DrivingPage_0"), 3, 4);
      }

      if (this.eachId != 5) {
         this.getAirUiSet(this.mContext).setHaveRearArea(false);
         this.removeFrontAirButtonByName(this.mContext, "rear");
      }

      if (this.eachId != 4) {
         this.removeMainPrjBtnByName(this.mContext, "panel_key");
      }

      if (this.eachId == 12) {
         CanbusMsgSender.sendMsg(new byte[]{22, -54, 1});
      }

      if (this.eachId == 17) {
         CanbusMsgSender.sendMsg(new byte[]{22, -54, 2});
      }

      int var1 = this.eachId;
      if (var1 != 16 && var1 != 8) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_333_drive_page_1"});
      }

   }

   public static void makeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   private void send0xA8AirInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)var1, (byte)var2});
   }

   private void sendAccInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -21, (byte)accState, (byte)delayTime});
   }

   private void sendAirInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)var1, (byte)var2});
   }

   private void sendAmplifierInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   private void sendCarParamInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -128, (byte)var1, (byte)var2});
   }

   private void sendComputerInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 0, 0, 0, (byte)this.getDecimalFrom8Bit(this.ComputerInfoData3Bit7, this.ComputerInfoData3Bit6, this.ComputerInfoData3Bit5, 0, this.ComputerInfoData3Bit3, this.ComputerInfoData3Bit2, this.ComputerInfoData3Bit1, this.ComputerInfoData3Bit0), (byte)this.ComputerInfoData4, (byte)this.ComputerInfoData5});
   }

   private void sendCruiseSpeedInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)this.getDecimalFrom8Bit(cruiseSpeedLimit_Data0Bit7, cruiseSpeedLimit_Data0Bit6, cruiseSpeedLimit_Data0Bit5, cruiseSpeedLimit_Data0Bit4, cruiseSpeedLimit_Data0Bit3, cruiseSpeedLimit_Data0Bit2, cruiseSpeedLimit_Data0Bit1, cruiseSpeedLimit_Data0Bit0), (byte)cruiseSpeed_Data1, (byte)cruiseSpeed_Data2, (byte)cruiseSpeed_Data3, (byte)cruiseSpeed_Data4, (byte)cruiseSpeed_Data5, (byte)cruiseSpeed_Data6});
   }

   private void sendMemorySpeedInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte)this.getDecimalFrom8Bit(speedMemory_data0Bit7, speedMemory_data0Bit6, speedMemory_data0Bit5, speedMemory_data0Bit4, speedMemory_data0Bit3, speedMemory_data0Bit2, speedMemory_data0Bit1, speedMemory_data0Bit0), (byte)speedMemory_data1, (byte)speedMemory_data2, (byte)speedMemory_data3, (byte)speedMemory_data4, (byte)speedMemory_data5});
   }

   private void sendPanelKeyInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte)var1});
   }

   private void sendParkingInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -113, (byte)var1});
   }

   private void sendSettingsSpeedSelectionInfo(int var1, int var2) {
      if (var2 == 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        speedMemory_data0Bit1 = 0;
                        this.sendMemorySpeedInfo();
                     }
                  } else {
                     speedMemory_data0Bit2 = 0;
                     this.sendMemorySpeedInfo();
                  }
               } else {
                  speedMemory_data0Bit3 = 0;
                  this.sendMemorySpeedInfo();
               }
            } else {
               speedMemory_data0Bit4 = 0;
               this.sendMemorySpeedInfo();
            }
         } else {
            speedMemory_data0Bit5 = 0;
            this.sendMemorySpeedInfo();
         }
      } else if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 == 5) {
                     speedMemory_data0Bit1 = 1;
                     this.sendMemorySpeedInfo();
                  }
               } else {
                  speedMemory_data0Bit2 = 1;
                  this.sendMemorySpeedInfo();
               }
            } else {
               speedMemory_data0Bit3 = 1;
               this.sendMemorySpeedInfo();
            }
         } else {
            speedMemory_data0Bit4 = 1;
            this.sendMemorySpeedInfo();
         }
      } else {
         speedMemory_data0Bit5 = 1;
         this.sendMemorySpeedInfo();
      }

   }

   private void sendSettingsSpeedSwitchInfo(int var1) {
      if (var1 == 0) {
         speedMemory_data0Bit6 = 0;
         this.sendMemorySpeedInfo();
      } else {
         speedMemory_data0Bit6 = 1;
         this.sendMemorySpeedInfo();
      }

   }

   private void sendSpeedLimitInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)this.getDecimalFrom8Bit(cruiseSpeedLimit_Data0Bit7, cruiseSpeedLimit_Data0Bit6, cruiseSpeedLimit_Data0Bit5, cruiseSpeedLimit_Data0Bit4, cruiseSpeedLimit_Data0Bit3, cruiseSpeedLimit_Data0Bit2, cruiseSpeedLimit_Data0Bit1, cruiseSpeedLimit_Data0Bit0), (byte)speedLimit_Data1, (byte)speedLimit_Data2, (byte)speedLimit_Data3, (byte)speedLimit_Data4, (byte)speedLimit_Data5, (byte)speedLimit_Data6});
   }

   private void sendWarningInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)var1, -1});
   }

   public void activeRequestInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1});
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

   protected int getLeftIndexes(Context var1, String var2) {
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
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
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

   public void sendID3Info(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15, int var16, int var17, int var18, int var19, int var20, int var21) {
      CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var8, (byte)var9, (byte)var10, (byte)var11, (byte)var12, (byte)var13, (byte)var14, (byte)var15, (byte)var16, (byte)var17, (byte)var18, (byte)var19, (byte)var20, (byte)var21});
   }

   public void sendID3Info(byte[] var1) {
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -53, 1}, var1));
   }

   public void sendPhoneStateInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -59, (byte)this.getDecimalFrom8Bit(0, phoneStateData0Bit6, phoneStateData0Bit5, phoneStateData0Bit4, 0, phoneStateData0Bit2, phoneStateData0Bit1, phoneStateData0Bit0), (byte)this.getDecimalFrom8Bit(0, 0, 0, phoneStateData1Bit4, 0, phoneStateData1Bit2, phoneStateData1Bit1, phoneStateData1Bit0), (byte)phoneStateData2, (byte)phoneStateData3, (byte)phoneStateData4});
   }

   public void sendRadioInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void sendSourceInfo(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var8});
   }

   public void sendVolInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var1});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.intiUi();
   }
}
