package com.hzbhd.canbus.car._375;

import android.content.Context;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   public String KEY_0x85_DATA0 = "KEY_0x85_DATA0";
   public String KEY_0x85_DATA1BIT7 = "KEY_0x85_DATA1BIT7";
   public String KEY_AUTO_PARKING_VIEW = "KEY_AUTO_PARKING_VIEW";
   public String KEY_CAR_SELECT = "KEY_CAR_SELECT";
   int d0b0 = 0;
   int d0b1 = 0;
   int d0b2 = 0;
   int d0b3 = 0;
   int d0b4 = 0;
   int d0b5 = 0;
   int d0b6 = 0;
   int d0b7 = 0;
   int d1b0 = 0;
   int d1b1 = 0;
   int d1b2 = 0;
   int d1b3 = 0;
   int d1b4 = 0;
   int d1b5 = 0;
   int d1b6 = 0;
   int d1b7 = 0;
   int d2b0 = 0;
   int d2b1 = 0;
   int d2b2 = 0;
   int d2b3 = 0;
   int d2b4 = 0;
   int d2b5 = 0;
   int d2b6 = 0;
   int d2b7 = 0;
   int d3b0 = 0;
   int d3b1 = 0;
   int d3b2 = 0;
   int d3b3 = 0;
   int d3b4 = 0;
   int d3b5 = 0;
   int d3b6 = 0;
   int d3b7 = 0;
   int d5b2 = 0;
   int data0_0x85 = 0;
   int data1Bit5_0x85 = 0;
   int data1Bit6_0x85 = 0;
   int data1Bit7_0x85 = 0;
   int differentId;
   int eachId;
   Context mContext;
   private String mId3SongTitle = "";
   MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.d5b2 = 1;
                  this.this$0.send0x86AirInfo();
                  this.this$0.d5b2 = 0;
                  this.this$0.send0x86AirInfo();
               }
            } else {
               this.this$0.d0b7 = 1;
               this.this$0.send0x86AirInfo();
               this.this$0.d0b7 = 0;
               this.this$0.send0x86AirInfo();
            }
         } else {
            this.this$0.d1b2 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d1b2 = 0;
            this.this$0.send0x86AirInfo();
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
            if (!GeneralAirData.in_out_cycle) {
               this.this$0.d0b3 = 1;
               this.this$0.send0x86AirInfo();
               this.this$0.d0b3 = 0;
               this.this$0.send0x86AirInfo();
            } else {
               this.this$0.d0b2 = 1;
               this.this$0.send0x86AirInfo();
               this.this$0.d0b2 = 0;
               this.this$0.send0x86AirInfo();
            }
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
            if (var1 == 1) {
               this.this$0.d0b0 = 1;
               this.this$0.send0x86AirInfo();
               this.this$0.d0b0 = 0;
               this.this$0.send0x86AirInfo();
            }
         } else {
            this.this$0.d0b1 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d0b1 = 0;
            this.this$0.send0x86AirInfo();
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
            this.this$0.d0b5 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d0b5 = 0;
            this.this$0.send0x86AirInfo();
         }

      }
   };
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestInfo(33, 0);
      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.seatState == 0) {
            this.this$0.d2b4 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d2b4 = 0;
            this.this$0.send0x86AirInfo();
            this.this$0.seatState = 1;
         } else if (this.this$0.seatState == 1) {
            this.this$0.d2b3 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d2b3 = 0;
            this.this$0.send0x86AirInfo();
            this.this$0.seatState = 2;
         } else if (this.this$0.seatState == 2) {
            this.this$0.d2b2 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d2b2 = 0;
            this.this$0.send0x86AirInfo();
            this.this$0.seatState = 3;
         } else if (this.this$0.seatState == 3) {
            this.this$0.d2b1 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d2b1 = 0;
            this.this$0.send0x86AirInfo();
            this.this$0.seatState = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.seatState == 0) {
            this.this$0.d2b4 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d2b4 = 0;
            this.this$0.send0x86AirInfo();
            this.this$0.seatState = 1;
         } else if (this.this$0.seatState == 1) {
            this.this$0.d2b3 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d2b3 = 0;
            this.this$0.send0x86AirInfo();
            this.this$0.seatState = 2;
         } else if (this.this$0.seatState == 2) {
            this.this$0.d2b2 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d2b2 = 0;
            this.this$0.send0x86AirInfo();
            this.this$0.seatState = 3;
         } else if (this.this$0.seatState == 3) {
            this.this$0.d2b1 = 1;
            this.this$0.send0x86AirInfo();
            this.this$0.d2b1 = 0;
            this.this$0.send0x86AirInfo();
            this.this$0.seatState = 0;
         }

      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.d3b0 = 1;
         this.this$0.send0x86AirInfo();
         this.this$0.d3b0 = 0;
         this.this$0.send0x86AirInfo();
      }

      public void onClickUp() {
         this.this$0.d3b1 = 1;
         this.this$0.send0x86AirInfo();
         this.this$0.d3b1 = 0;
         this.this$0.send0x86AirInfo();
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.d2b4 = 1;
         this.this$0.send0x86AirInfo();
         this.this$0.d2b4 = 0;
         this.this$0.send0x86AirInfo();
      }

      public void onClickUp() {
         this.this$0.d2b4 = 1;
         this.this$0.send0x86AirInfo();
         this.this$0.d2b4 = 0;
         this.this$0.send0x86AirInfo();
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (GeneralAirData.front_wind_level != 0) {
            this.this$0.d1b7 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level - 1, 3, 1);
            this.this$0.d1b6 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level - 1, 2, 1);
            this.this$0.d1b5 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level - 1, 1, 1);
            this.this$0.d1b4 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level - 1, 0, 1);
            this.this$0.send0x86AirInfo();
            this.this$0.d1b7 = 0;
            this.this$0.d1b6 = 0;
            this.this$0.d1b5 = 0;
            this.this$0.d1b4 = 0;
            this.this$0.send0x86AirInfo();
         }
      }

      public void onClickRight() {
         if (GeneralAirData.front_wind_level != 8) {
            this.this$0.d1b7 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level + 1, 3, 1);
            this.this$0.d1b6 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level + 1, 2, 1);
            this.this$0.d1b5 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level + 1, 1, 1);
            this.this$0.d1b4 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level + 1, 0, 1);
            this.this$0.send0x86AirInfo();
            this.this$0.d1b7 = 0;
            this.this$0.d1b6 = 0;
            this.this$0.d1b5 = 0;
            this.this$0.d1b4 = 0;
            this.this$0.send0x86AirInfo();
         }
      }
   };
   OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestInfo(58, 0);
      }
   };
   OnPanoramicItemClickListener onPanoramicItemClickListener = new OnPanoramicItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         switch (var1) {
            case 0:
               this.this$0.send0xA7PanoramicInfo(1);
               break;
            case 1:
               this.this$0.send0xA7PanoramicInfo(3);
               break;
            case 2:
               this.this$0.send0xA7PanoramicInfo(4);
               break;
            case 3:
               this.this$0.send0xA7PanoramicInfo(2);
               break;
            case 4:
               this.this$0.send0xA7PanoramicInfo(16);
               break;
            case 5:
               this.this$0.send0xA7PanoramicInfo(17);
               break;
            case 6:
               this.this$0.send0xA7PanoramicInfo(5);
               break;
            case 7:
               this.this$0.send0xA7PanoramicInfo(16);
               break;
            case 8:
               this.this$0.send0xA7PanoramicInfo(18);
               break;
            case 9:
               UiMgr var2 = this.this$0;
               var2.getMsgMgr(var2.mContext).myForceReverse(false);
         }

      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_375_reservation_charging")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_375_reservation_charging", "_375_reservation_charging_start")) {
               var3 = this.this$0;
               var3.getMsgMgr(var3.mContext).setStartTime();
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_375_reservation_charging", "_375_reservation_charging_end")) {
               var3 = this.this$0;
               var3.getMsgMgr(var3.mContext).setEndTime();
            }
         }

         var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_375_other_setting")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_375_other_setting", "_375_panoramic_trigger2")) {
               this.this$0.data1Bit6_0x85 = 1;
               this.this$0.send0x85Info();
               this.this$0.data1Bit6_0x85 = 0;
               this.this$0.send0x85Info();
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_375_other_setting", "_375_panoramic_trigger3")) {
               this.this$0.data1Bit5_0x85 = 1;
               this.this$0.send0x85Info();
               this.this$0.data1Bit5_0x85 = 0;
               this.this$0.send0x85Info();
            }
         }

         var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_375_car_setting")) {
            if (var2 == this.this$0.get0x83Right("_375_car_setting1")) {
               this.this$0.send0x83CarInfo(128, 0);
               var3 = this.this$0;
               var3.getMsgMgr(var3.mContext).toast("Reset success");
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting14")) {
               this.this$0.send0x83CarInfo(128, 12);
               var3 = this.this$0;
               var3.getMsgMgr(var3.mContext).toast("Tire pressure reset success");
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
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_375_car_setting") && var2 == this.this$0.get0x83Right("_375_car_setting374")) {
            this.this$0.send0x83CarInfo(41, var3 + 10);
            MyLog.temporaryTracking(var3 + "");
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
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_375_auto_park")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_375_auto_park", "_375_auto_park_setting")) {
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_AUTO_PARKING_VIEW, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_375_reservation_charging")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_375_reservation_charging", "_375_reservation_setting1")) {
               this.this$0.send0x84CarInfo(2, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_375_reservation_charging", "_375_reservation_setting2")) {
               this.this$0.send0x84CarInfo(3, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_375_reservation_charging", "_375_reservation_setting3")) {
               if (var3 == 0) {
                  this.this$0.send0x84CarInfo(4, 60);
               } else if (var3 == 1) {
                  this.this$0.send0x84CarInfo(4, 100);
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_375_reservation_charging", "_375_reservation_setting4")) {
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 == 3) {
                           this.this$0.send0x84CarInfo(5, 32);
                        }
                     } else {
                        this.this$0.send0x84CarInfo(5, 16);
                     }
                  } else {
                     this.this$0.send0x84CarInfo(5, 10);
                  }
               } else {
                  this.this$0.send0x84CarInfo(5, 8);
               }
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_375_car_select")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_375_car_select", "_375_car_select")) {
               this.this$0.send0xEECarSelectInfo(var3 + 1);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_CAR_SELECT, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_375_other_setting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_375_other_setting", "_375_parking_trajectory")) {
               this.this$0.data0_0x85 = var3 + 1;
               this.this$0.send0x85Info();
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_0x85_DATA0, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_375_other_setting", "_375_panoramic_trigger1")) {
               this.this$0.data1Bit7_0x85 = var3;
               this.this$0.send0x85Info();
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_0x85_DATA1BIT7, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_375_car_setting")) {
            if (var2 == this.this$0.get0x83Right("_375_car_setting2")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(128, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting3")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(1, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting4")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(2, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting5")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(3, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting6")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(4, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting7")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(5, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting8")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(6, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting9")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(7, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting10")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(8, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting11")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(9, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting12")) {
               this.this$0.send0x83CarInfo(10, var3);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting13")) {
               this.this$0.send0x83CarInfo(11, var3);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting14")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(12, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting15")) {
               this.this$0.send0x83CarInfo(13, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting16")) {
               this.this$0.send0x83CarInfo(14, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting17")) {
               this.this$0.send0x83CarInfo(15, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting18")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(16, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting180")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(17, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting19")) {
               this.this$0.send0x83CarInfo(18, var3);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting20")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(19, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting21")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(20, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting22")) {
               this.this$0.send0x83CarInfo(21, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting23")) {
               this.this$0.send0x83CarInfo(22, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting24")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(23, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting25")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(24, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting26")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(25, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting27")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(26, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting28")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(27, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting29")) {
               this.this$0.send0x83CarInfo(28, var3);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting30")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(29, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting30")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(29, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting31")) {
               this.this$0.send0x83CarInfo(30, var3);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting32")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(31, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting33")) {
               this.this$0.send0x83CarInfo(32, var3);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting34")) {
               this.this$0.send0x83CarInfo(33, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting35")) {
               this.this$0.send0x83CarInfo(34, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting36")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(35, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting37")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(36, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting370")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(37, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting371")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(38, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting372")) {
               this.this$0.send0x83CarInfo(39, var3);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting373")) {
               this.this$0.send0x83CarInfo(40, var3);
            }

            this.this$0.get0x83Right("_375_car_setting374");
            if (var2 == this.this$0.get0x83Right("_375_car_setting375")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(42, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting38")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(43, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting39")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(44, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting40")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(45, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting41")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(46, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting42")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(47, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting43")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(48, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting44")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(49, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting45")) {
               this.this$0.send0x83CarInfo(50, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting46")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(51, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting47")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(52, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting48")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(53, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting49")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(54, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting50")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(55, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting51")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(56, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting53")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(57, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting54")) {
               this.this$0.send0x83CarInfo(58, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting55")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(59, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting56")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(60, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting57")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(61, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting58")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(62, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting59")) {
               this.this$0.send0x83CarInfo(63, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting60")) {
               this.this$0.send0x83CarInfo(64, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting61")) {
               this.this$0.send0x83CarInfo(65, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting62")) {
               this.this$0.send0x83CarInfo(66, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting63")) {
               this.this$0.send0x83CarInfo(67, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting64")) {
               this.this$0.send0x83CarInfo(68, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting65")) {
               this.this$0.send0x83CarInfo(69, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting66")) {
               this.this$0.send0x83CarInfo(70, var3 + 1);
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting67")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(71, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting68")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(72, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting69")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(73, var4.get0x83SelectPos(var3));
            }

            if (var2 == this.this$0.get0x83Right("_375_car_setting70")) {
               var4 = this.this$0;
               var4.send0x83CarInfo(74, var4.get0x83SelectPos(var3));
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
         this.this$0.activeRequestInfo(83, 0);
         this.this$0.activeRequestInfo(82, 0);
      }
   };
   DecimalFormat oneDecimal = new DecimalFormat("###0.0");
   int seatState = 0;
   DecimalFormat timeFormat = new DecimalFormat("00");
   OnTirePageStatusListener tirePageStatusListener = new OnTirePageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestInfo(56, 0);
         this.this$0.activeRequestInfo(57, 0);
      }
   };
   DecimalFormat towDecimal = new DecimalFormat("###0.00");

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      var2.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
      this.getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var3.setOnSettingItemClickListener(this.onSettingItemClickListener);
      var3.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
      var3.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      this.getTireUiSet(this.mContext).setOnTirePageStatusListener(this.tirePageStatusListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int get0x83Right(String var1) {
      return this.getSettingRightIndex(this.mContext, "_375_car_setting", var1);
   }

   private int get0x83SelectPos(int var1) {
      return var1 == 0 ? 2 : 1;
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

   private void initUi() {
      MsgMgr var4 = this.getMsgMgr(this.mContext);
      Context var3 = this.mContext;
      int var1 = this.getSettingLeftIndexes(var3, "_375_auto_park");
      int var2 = this.getSettingRightIndex(this.mContext, "_375_auto_park", "_375_auto_park_setting");
      String var5 = this.KEY_AUTO_PARKING_VIEW;
      var4.updateSettings(var3, var1, var2, var5, SharePreUtil.getIntValue(this.mContext, var5, 0));
      MsgMgr var6 = this.getMsgMgr(this.mContext);
      Context var9 = this.mContext;
      var2 = this.getSettingLeftIndexes(var9, "_375_car_select");
      var1 = this.getSettingRightIndex(this.mContext, "_375_car_select", "_375_car_select");
      String var7 = this.KEY_CAR_SELECT;
      var6.updateSettings(var9, var2, var1, var7, SharePreUtil.getIntValue(this.mContext, var7, 0));
      var6 = this.getMsgMgr(this.mContext);
      var9 = this.mContext;
      var1 = this.getSettingLeftIndexes(var9, "_375_other_setting");
      var2 = this.getSettingRightIndex(this.mContext, "_375_other_setting", "_375_parking_trajectory");
      var7 = this.KEY_0x85_DATA1BIT7;
      var6.updateSettings(var9, var1, var2, var7, SharePreUtil.getIntValue(this.mContext, var7, 0));
      var6 = this.getMsgMgr(this.mContext);
      Context var8 = this.mContext;
      var2 = this.getSettingLeftIndexes(var8, "_375_other_setting");
      var1 = this.getSettingRightIndex(this.mContext, "_375_other_setting", "_375_panoramic_trigger1");
      var5 = this.KEY_0x85_DATA1BIT7;
      var6.updateSettings(var8, var2, var1, var5, SharePreUtil.getIntValue(this.mContext, var5, 0));
   }

   private void send0x83CarInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
   }

   private void send0x85Info() {
      CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)this.data0_0x85, (byte)this.getDecimalFrom8Bit(this.data1Bit7_0x85, this.data1Bit6_0x85, this.data1Bit5_0x85, 0, 0, 0, 0, 0)});
   }

   private void send0x86AirInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte)this.getDecimalFrom8Bit(this.d0b7, this.d0b6, this.d0b5, this.d0b4, this.d0b3, this.d0b2, this.d0b1, this.d0b0), (byte)this.getDecimalFrom8Bit(this.d1b7, this.d1b6, this.d1b5, this.d1b4, this.d1b3, this.d1b2, this.d1b1, this.d1b0), (byte)this.getDecimalFrom8Bit(this.d2b7, this.d2b6, this.d2b5, this.d2b4, this.d2b3, this.d2b2, this.d2b1, this.d2b0), (byte)this.getDecimalFrom8Bit(this.d3b7, this.d3b6, this.d3b5, this.d3b4, this.d3b3, this.d3b2, this.d3b1, this.d3b0), 0, (byte)this.getDecimalFrom8Bit(0, 0, 0, 0, 0, this.d5b2, 0, 0)});
   }

   private void send0xEECarSelectInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -18, 3, (byte)var1});
   }

   public void activeRequestInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1, (byte)var2});
   }

   public void getCanBoxVersion() {
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var7 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var7.size(); ++var3) {
         Iterator var5 = var7.iterator();

         while(var5.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

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

   public void send0x75LCD(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      CanbusMsgSender.sendMsg(new byte[]{22, 117, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var8});
   }

   public void send0x84CarInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   public void send0x84CarInfo(int var1, int var2, int var3, int var4, int var5) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5});
   }

   public void send0xA7PanoramicInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -89, (byte)var1});
   }

   public void send0xC0MediaInfo(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var8, (byte)var9, (byte)var10});
   }

   public void send0xC0PhoneInfo(int var1, int var2, int var3, byte[] var4) {
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -64, 5, (byte)var1, (byte)var2, (byte)var3, 1, 11}, var4));
   }

   public void send0xC0RadioInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void send0xC0Str(int var1, String var2) {
      if (!TextUtils.isEmpty(var2)) {
         if (!var2.equals(this.mId3SongTitle)) {
            this.mId3SongTitle = var2;
            byte var3 = (byte)var1;

            try {
               byte var4 = (byte)var2.length();
               byte[] var6 = var2.getBytes("utf-8");
               CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -64, var3, -1, 18, 1, var4}, var6));
            } catch (Exception var5) {
               var5.printStackTrace();
            }

         }
      }
   }

   public void sendAutoParkingModel(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)var1});
   }

   public void sendTimeInfo(int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var1, (byte)var2, (byte)var3});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initUi();
   }
}
