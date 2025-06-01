package com.hzbhd.canbus.car._194;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   protected static final int CAR_1 = 1;
   protected static final int CAR_10 = 10;
   protected static final int CAR_11 = 11;
   protected static final int CAR_12 = 12;
   protected static final int CAR_13 = 13;
   protected static final int CAR_14 = 14;
   protected static final int CAR_15 = 15;
   protected static final int CAR_16 = 16;
   protected static final int CAR_17 = 17;
   protected static final int CAR_18 = 18;
   protected static final int CAR_19 = 19;
   protected static final int CAR_2 = 2;
   protected static final int CAR_20 = 20;
   protected static final int CAR_21 = 21;
   protected static final int CAR_22 = 22;
   protected static final int CAR_3 = 3;
   protected static final int CAR_4 = 4;
   protected static final int CAR_5 = 5;
   protected static final int CAR_6 = 6;
   protected static final int CAR_7 = 7;
   protected static final int CAR_8 = 8;
   protected static final int CAR_9 = 9;
   private static int mFrontWindMode;
   private int WindStatus = 1;
   private Context mContext;
   private int mDifferent;
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
                  this.this$0.sendAcCmd(AIR_CMD.REAR_DEFOG);
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 1});
            }
         } else {
            this.this$0.sendAcCmd(AIR_CMD.FRONT_DEFOG);
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
                  this.this$0.sendAcCmd(AIR_CMD.AUTO);
               }
            } else {
               this.this$0.sendAcCmd(AIR_CMD.AC);
            }
         } else {
            this.this$0.sendAcCmd(AIR_CMD.POWER);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 27, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 26, 1});
         }

      }
   };
   OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_194_driving_maintenance")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_194_driving_maintenance", "_194_tire_pressure_reset")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 1, 1});
            }
         }

         var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "panorama_setting")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "panorama_setting", "str_250_0_4")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
            }
         }

         var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_194_airconditioning_settings")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_194_airconditioning_settings", "_194_pm_25")) {
               var3 = this.this$0;
               var3.getMsgMgr(var3.mContext).showPmInfo();
            }
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_TEMP_DN);
      }

      public void onClickUp() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_TEMP_UP);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_TEMP_DN);
      }

      public void onClickUp() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_TEMP_UP);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 24, 2});
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 24, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 24, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 24, 0});
      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         this.this$0.sendAcCmd(AIR_CMD.MODE);
      }

      public void onRightSeatClick() {
         this.this$0.sendAcCmd(AIR_CMD.MODE);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAcCmd(AIR_CMD.WIND_DN);
      }

      public void onClickRight() {
         this.this$0.sendAcCmd(AIR_CMD.WIND_UP);
      }
   };
   OnConfirmDialogListener onConfirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         if (var1 == 0 && var2 == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -119, 12, 0});
         }

      }
   };
   OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_SEAT_HEAT);
      }

      public void onClickPlus() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_SEAT_HEAT);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendAcCmd(AIR_CMD.RIGHT_SEAT_HEAT);
      }

      public void onClickPlus() {
         this.this$0.sendAcCmd(AIR_CMD.RIGHT_SEAT_HEAT);
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
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 6});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 7});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 8});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 9});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 10});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 11});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
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
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_window")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_window", "_194_skylights_state")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 1, (byte)var3});
            }
         }

      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      int bit0;
      int bit1;
      int bit2;
      int bit3;
      int bit4;
      int bit5;
      int bit6;
      int bit7;
      final UiMgr this$0;

      {
         this.this$0 = var1;
         this.bit0 = 0;
         this.bit1 = 0;
         this.bit2 = 0;
         this.bit3 = 0;
         this.bit4 = 0;
         this.bit5 = 0;
         this.bit6 = 0;
         this.bit7 = 0;
      }

      private void Left1(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_lock_control")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_lock_control", "_194_driving_luosuo")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 1, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_lock_control")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_lock_control", "_194_unlock")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 2, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_lock_control")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_lock_control", "_194_unlock_mode")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_lock_control")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_lock_control", "_194_smart_unlock_the_car_near")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte)var3});
            }
         }

      }

      private void Left10(int var1, int var2, int var3) {
      }

      private void Left11(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "panorama_setting")) {
            var4 = this.this$0;
            var4.getSettingRightIndex(var4.mContext, "panorama_setting", "str_250_0_4");
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "panorama_setting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "panorama_setting", "_194_360panoramic_10")) {
               if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 16});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 17});
               }
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "panorama_setting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "panorama_setting", "_194_360panoramic_11")) {
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 == 3) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -126, 15});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 14});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 13});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 12});
               }
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "panorama_setting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "panorama_setting", "_194_360panoramic_12")) {
               if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
               }
            }
         }

      }

      private void Left12(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "language_setup")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "language_setup", "language_setup")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, 1, (byte)(var3 + 1)});
            }
         }

      }

      private void Left13(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_drive_model1")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_drive_model1", "_194_drive_model1")) {
               if (var3 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte)(var3 + 1), 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte)(var3 + 1)});
               }
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_drive_model1")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_drive_model1", "_194_drive_model2")) {
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 == 2) {
                        this.bit6 = 0;
                        this.bit7 = 1;
                     }
                  } else {
                     this.bit6 = 1;
                     this.bit7 = 0;
                  }
               } else {
                  this.bit6 = 0;
                  this.bit7 = 0;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 4, (byte)this.this$0.getDecimalFrom8Bit(this.bit7, this.bit6, this.bit5, this.bit4, this.bit3, this.bit2, this.bit1, this.bit0)});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_drive_model1")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_drive_model1", "_194_drive_model3")) {
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 == 2) {
                        this.bit4 = 0;
                        this.bit5 = 1;
                     }
                  } else {
                     this.bit4 = 1;
                     this.bit5 = 0;
                  }
               } else {
                  this.bit4 = 0;
                  this.bit5 = 0;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 4, (byte)this.this$0.getDecimalFrom8Bit(this.bit7, this.bit6, this.bit5, this.bit4, this.bit3, this.bit2, this.bit1, this.bit0)});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_drive_model1")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_drive_model1", "_194_drive_model4")) {
               if (var3 != 0) {
                  if (var3 == 1) {
                     this.bit3 = 1;
                  }
               } else {
                  this.bit3 = 0;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 4, (byte)this.this$0.getDecimalFrom8Bit(this.bit7, this.bit6, this.bit5, this.bit4, this.bit3, this.bit2, this.bit1, this.bit0)});
            }
         }

      }

      private void Left2(int var1, int var2, int var3) {
         UiMgr var6 = this.this$0;
         int var4;
         boolean var5;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_194_lighting_control")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_194_lighting_control", "_194_i_came_home_with__reversing_lights")) {
               var4 = MsgMgr.mHomeWithMeLight;
               if (var3 == 1) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 1, (byte)DataHandleUtils.setIntByteWithBit(var4, 7, var5)});
            }
         }

         var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_194_lighting_control")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_194_lighting_control", "_194_i_came_home_with__beam_lights")) {
               var4 = MsgMgr.mHomeWithMeLight;
               if (var3 == 1) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 1, (byte)DataHandleUtils.setIntByteWithBit(var4, 6, var5)});
            }
         }

         var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_194_lighting_control")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_194_lighting_control", "_194_i_came_home_with__rear_fog")) {
               var4 = MsgMgr.mHomeWithMeLight;
               if (var3 == 1) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 1, (byte)DataHandleUtils.setIntByteWithBit(var4, 5, var5)});
            }
         }

         var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_194_lighting_control")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_194_lighting_control", "_194_i_came_home_with_a_duration")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 2, (byte)DataHandleUtils.setIntFromByteWithBit(MsgMgr.mHomeWithMeTime, var3, 0, 4)});
            }
         }

         var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_194_lighting_control")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_194_lighting_control", "_194_searching_cars_indication__reversing_lights")) {
               var4 = MsgMgr.mFindCarLight;
               if (var3 == 1) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 3, (byte)DataHandleUtils.setIntByteWithBit(var4, 7, var5)});
            }
         }

         var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_194_lighting_control")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_194_lighting_control", "_194_searching_cars_indication__near_light")) {
               var4 = MsgMgr.mFindCarLight;
               if (var3 == 1) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 3, (byte)DataHandleUtils.setIntByteWithBit(var4, 6, var5)});
            }
         }

         var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_194_lighting_control")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_194_lighting_control", "_194_searching_cars_indication__rear_fog")) {
               var4 = MsgMgr.mFindCarLight;
               if (var3 == 1) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 3, (byte)DataHandleUtils.setIntByteWithBit(var4, 5, var5)});
            }
         }

         var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_194_lighting_control")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_194_lighting_control", "_194_searching_cars_indicate_the_duration")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 4, (byte)DataHandleUtils.setIntFromByteWithBit(MsgMgr.mFindCarTime, var3, 0, 4)});
            }
         }

      }

      private void Left3(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_convenient_and_comfortable")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_convenient_and_comfortable", "_194_i_went_home_with")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_convenient_and_comfortable")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_convenient_and_comfortable", "_194_searching_cars_indication")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_convenient_and_comfortable")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_convenient_and_comfortable", "_194_steering_wheel_feels")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 3, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_convenient_and_comfortable")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_convenient_and_comfortable", "_194_unlock_mode")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_convenient_and_comfortable")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_convenient_and_comfortable", "_194_smart_unlock_the_car_near")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_convenient_and_comfortable")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_convenient_and_comfortable", "_194_a_foldable_outside_mirror_automatically")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, 1, (byte)var3});
            }
         }

      }

      private void Left4(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_airconditioning_settings")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_airconditioning_settings", "_194_rear_window_demisting_demisting_linkage")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 1, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_airconditioning_settings")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_airconditioning_settings", "_194_automatic_mode_wind")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 2, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_airconditioning_settings")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_airconditioning_settings", "_194_partition_temperature_settings")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 3, (byte)var3});
            }
         }

      }

      private void Left5(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_driving_maintenance")) {
            var4 = this.this$0;
            var4.getSettingRightIndex(var4.mContext, "_194_driving_maintenance", "_194_tire_pressure_reset");
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_driving_maintenance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_driving_maintenance", "_194_vehicle_stability_control")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 2, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_driving_maintenance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_driving_maintenance", "_194_ecodriving")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 3, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_driving_maintenance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_driving_maintenance", "_194_add_action1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 4, (byte)var3});
            }
         }

      }

      private void Left6(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_reset")) {
            var4 = this.this$0;
            var4.getSettingRightIndex(var4.mContext, "_194_reset", "_194_add_action2");
         }

      }

      private void Left7(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_instrument_brightness")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_instrument_brightness", "_194_meter_brightness_level")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, 1, (byte)(var3 + 1)});
            }
         }

      }

      private void Left9(int var1, int var2, int var3) {
      }

      private void left8(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_pedestrian_warning_beep")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 1, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_speed_auxiliary")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 2, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_assist_mode")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 3, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_lane_departure_warning")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 4, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_alarm_sensitivity")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 5, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_alarm_sound")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 6, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_auxiliary_systems")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 7, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_assist__assist_mode")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 8, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_aid__alert_sensitivity")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 9, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 10, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function2")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 11, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function3")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 12, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function4")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 13, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function5")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 14, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function6")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 15, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function7")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 16, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function8")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 17, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function9")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 18, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function10")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 19, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_advanced_driver_assistance")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_advanced_driver_assistance", "_194_add_function11")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 20, (byte)var3});
            }
         }

      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_194_car_select")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_car_select", "_194_car_select0")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, (byte)var3});
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_194_car_select", "_194_car_select1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, (byte)var3});
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }
         }

         this.Left1(var1, var2, var3);
         this.Left2(var1, var2, var3);
         this.Left3(var1, var2, var3);
         this.Left4(var1, var2, var3);
         this.Left5(var1, var2, var3);
         this.Left6(var1, var2, var3);
         this.Left7(var1, var2, var3);
         this.left8(var1, var2, var3);
         this.Left9(var1, var2, var3);
         this.Left10(var1, var2, var3);
         this.Left11(var1, var2, var3);
         this.Left12(var1, var2, var3);
         this.Left13(var1, var2, var3);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      FrontArea var3 = var2.getFrontArea();
      var3.setOnAirSeatClickListener(this.onAirSeatClickListener);
      var3.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, null, null, this.mOnAirBtnClickListenerFrontBottom});
      var3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight, null, null});
      var3.setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
      var2.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 20, 2});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 20, 1});
         }
      });
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRearCenter, null});
      var2.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            if (this.this$0.WindStatus == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 25, 1});
               this.this$0.WindStatus = 2;
            } else if (this.this$0.WindStatus == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 25, 2});
               this.this$0.WindStatus = 3;
            } else if (this.this$0.WindStatus == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 25, 3});
               this.this$0.WindStatus = 1;
            }

         }

         public void onRightSeatClick() {
         }
      });
      var2.setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
         }
      });
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      var4.setOnSettingConfirmDialogListener(this.onConfirmDialogListener);
      var4.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
      var4.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var4.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var4.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 57});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 67});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 83});
         }
      });
      this.getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 36});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 84});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 96});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 97});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 98});
         }
      });
      this.getTireUiSet(this.mContext).setOnTirePageStatusListener(new OnTirePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 82});
         }
      });
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

   private void intiUi() {
      int var1 = this.mDifferent;
      if (var1 != 13 && var1 != 17) {
         this.getAirUiSet(this.mContext).setHaveRearArea(false);
      }

      var1 = this.mDifferent;
      if (var1 != 6 && var1 != 14) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_lock_control", "_194_lighting_control"});
      }

      var1 = this.mDifferent;
      if (var1 != 1 && var1 != 2 && var1 != 7 && var1 != 8 && var1 != 9 && var1 != 10 && var1 != 11 && var1 != 12 && var1 != 13 && var1 != 14 && var1 != 15) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_convenient_and_comfortable", "_194_airconditioning_settings", "_194_driving_maintenance", "_194_reset", "_194_instrument_brightness", "_194_advanced_driver_assistance", "_194_window", "language_setup"});
      }

      var1 = this.mDifferent;
      if (var1 != 11 && var1 != 12 && var1 != 17) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_194_pm_25"});
      }

      var1 = this.mDifferent;
      if (var1 != 6 && var1 != 7) {
         this.removeMainPrjBtnByName(this.mContext, "warning");
      }

      var1 = this.mDifferent;
      if (var1 != 9 && var1 != 10) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"panorama_setting"});
         this.getParkPageUiSet(this.mContext).setShowPanoramic(false);
      }

      if (this.mDifferent != 16) {
         this.removeMainPrjBtnByName(this.mContext, "drive_data");
      }

   }

   private void sendAcCmd(AIR_CMD var1) {
      switch (null.$SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[var1.ordinal()]) {
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 0});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, 0});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 0});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 0});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 17, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 17, 0});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 18, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 18, 0});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 0});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 0});
            break;
         case 13:
            sendAirCommandFrontWindMode();
      }

   }

   protected static void sendAirCommandFrontWindMode() {
      int var0 = mFrontWindMode;
      CanbusMsgSender.sendMsg(new byte[]{22, -118, 16, (new byte[]{1, 2, 3, 4})[var0]});
      CanbusMsgSender.sendMsg(new byte[]{22, -118, 16, 0});
      var0 = mFrontWindMode + 1;
      mFrontWindMode = var0;
      if (var0 >= 4) {
         mFrontWindMode = 0;
      }

   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var6.next()).getItemList();

            for(int var4 = 0; var4 < var7.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var7.get(var4)).getTitleSrn().equals(var2)) {
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
      this.intiUi();
   }

   static enum AIR_CMD {
      private static final AIR_CMD[] $VALUES;
      AC,
      AUTO,
      FRONT_DEFOG,
      LEFT_SEAT_HEAT,
      LEFT_TEMP_DN,
      LEFT_TEMP_UP,
      LOOP,
      MODE,
      POWER,
      REAR_DEFOG,
      RIGHT_SEAT_HEAT,
      WIND_DN,
      WIND_UP;

      static {
         AIR_CMD var1 = new AIR_CMD("POWER", 0);
         POWER = var1;
         AIR_CMD var5 = new AIR_CMD("AC", 1);
         AC = var5;
         AIR_CMD var8 = new AIR_CMD("LOOP", 2);
         LOOP = var8;
         AIR_CMD var3 = new AIR_CMD("AUTO", 3);
         AUTO = var3;
         AIR_CMD var10 = new AIR_CMD("LEFT_SEAT_HEAT", 4);
         LEFT_SEAT_HEAT = var10;
         AIR_CMD var2 = new AIR_CMD("RIGHT_SEAT_HEAT", 5);
         RIGHT_SEAT_HEAT = var2;
         AIR_CMD var4 = new AIR_CMD("LEFT_TEMP_UP", 6);
         LEFT_TEMP_UP = var4;
         AIR_CMD var0 = new AIR_CMD("LEFT_TEMP_DN", 7);
         LEFT_TEMP_DN = var0;
         AIR_CMD var11 = new AIR_CMD("WIND_DN", 8);
         WIND_DN = var11;
         AIR_CMD var12 = new AIR_CMD("WIND_UP", 9);
         WIND_UP = var12;
         AIR_CMD var6 = new AIR_CMD("FRONT_DEFOG", 10);
         FRONT_DEFOG = var6;
         AIR_CMD var7 = new AIR_CMD("REAR_DEFOG", 11);
         REAR_DEFOG = var7;
         AIR_CMD var9 = new AIR_CMD("MODE", 12);
         MODE = var9;
         $VALUES = new AIR_CMD[]{var1, var5, var8, var3, var10, var2, var4, var0, var11, var12, var6, var7, var9};
      }
   }
}
