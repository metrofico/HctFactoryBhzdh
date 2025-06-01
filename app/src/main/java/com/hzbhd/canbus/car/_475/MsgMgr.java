package com.hzbhd.canbus.car._475;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
   DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   int[] mAirData;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   int[] mTrackData;
   private UiMgr mUiMgr;

   private int getBandAmFM(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 2092:
            if (var1.equals("AM")) {
               var2 = 0;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 1;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 2;
            }
            break;
         case 64903:
            if (var1.equals("AM3")) {
               var2 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 6;
            }
      }

      switch (var2) {
         case 0:
            return 16;
         case 1:
            return 17;
         case 2:
            return 18;
         case 3:
            return 19;
         case 4:
            return 1;
         case 5:
            return 2;
         case 6:
            return 3;
         default:
            return 0;
      }
   }

   private int getFreqLsb(String var1, String var2) {
      return this.getBandAmFM(var1) != 0 && this.getBandAmFM(var1) != 1 && this.getBandAmFM(var1) != 2 && this.getBandAmFM(var1) != 3 ? this.getLsb((int)Double.parseDouble(var2)) : this.getLsb((int)(Double.parseDouble(var2) * 100.0));
   }

   private int getFreqMsb(String var1, String var2) {
      return this.getBandAmFM(var1) != 0 && this.getBandAmFM(var1) != 1 && this.getBandAmFM(var1) != 2 && this.getBandAmFM(var1) != 3 ? this.getMsb((int)Double.parseDouble(var2)) : this.getMsb((int)(Double.parseDouble(var2) * 100.0));
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarData, var1)) {
         return false;
      } else {
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicInfoChange(int[] var1) {
      if (Arrays.equals(this.mPanoramicInfo, var1)) {
         return false;
      } else {
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRearRadarData, var1)) {
         return false;
      } else {
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange(int[] var1) {
      if (Arrays.equals(this.mTireInfo, var1)) {
         return false;
      } else {
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, var1)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void set0x03AirInfo(int[] var1) {
      int[] var7 = this.mAirData;
      boolean var2;
      if (var7 == null || var7.length == 0 || var1[8] == var7[8] && var1[9] == var7[9] && var1[10] == var7[10]) {
         var2 = false;
      } else {
         var2 = true;
      }

      if (this.isAirDataChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(var1[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(var1[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4);
         int var3 = var1[4];
         if (var3 == 255) {
            GeneralAirData.front_left_temperature = "HI";
         } else if (var3 == 0) {
            GeneralAirData.front_left_temperature = "LO";
         } else {
            GeneralAirData.front_left_temperature = this.formatDecimal1.format((double)((float)var1[4] / 2.0F)) + this.getTempUnitC(this.mContext);
         }

         var3 = var1[5];
         if (var3 == 255) {
            GeneralAirData.front_right_temperature = "HI";
         } else if (var3 == 0) {
            GeneralAirData.front_right_temperature = "LO";
         } else {
            GeneralAirData.front_right_temperature = this.formatDecimal1.format((double)((float)var1[5] / 2.0F)) + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[6], 4, 4);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(var1[6]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit2(var1[6]);
         GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit1(var1[6]);
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(var1[6]);
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1[7], 6, 2);
         var3 = DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 2);
         int var5 = DataHandleUtils.getIntFromByteWithBit(var1[7], 2, 2);
         int var6 = DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 2);
         if (var4 == 0) {
            GeneralAirData.left_set_seat_cold = false;
            GeneralAirData.front_left_seat_cold_level = 0;
         } else {
            GeneralAirData.left_set_seat_cold = true;
            GeneralAirData.front_left_seat_cold_level = var4;
         }

         if (var3 == 0) {
            GeneralAirData.left_set_seat_heat = false;
            GeneralAirData.front_left_seat_heat_level = 0;
         } else {
            GeneralAirData.left_set_seat_heat = true;
            GeneralAirData.front_left_seat_heat_level = var3;
         }

         if (var5 == 0) {
            GeneralAirData.right_set_seat_cold = false;
            GeneralAirData.front_right_seat_cold_level = 0;
         } else {
            GeneralAirData.right_set_seat_cold = true;
            GeneralAirData.front_right_seat_cold_level = var5;
         }

         if (var6 == 0) {
            GeneralAirData.right_set_seat_heat = false;
            GeneralAirData.front_right_seat_heat_level = 0;
         } else {
            GeneralAirData.right_set_seat_heat = true;
            GeneralAirData.front_right_seat_heat_level = var6;
         }

         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(var1[8]);
         GeneralAirData.rear_ac = DataHandleUtils.getBoolBit6(var1[8]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit5(var1[8]);
         GeneralAirData.rear_sync = DataHandleUtils.getBoolBit4(var1[8]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit3(var1[8]);
         GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit2(var1[8]);
         GeneralAirData.rear_right_blow_window = DataHandleUtils.getBoolBit2(var1[8]);
         GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit1(var1[8]);
         GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit1(var1[8]);
         GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit0(var1[8]);
         GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit0(var1[8]);
         var3 = var1[9];
         if (var3 == 255) {
            GeneralAirData.rear_left_temperature = "HI";
         } else if (var3 == 0) {
            GeneralAirData.rear_left_temperature = "LO";
         } else {
            GeneralAirData.rear_left_temperature = this.formatDecimal1.format((double)((float)var1[9] / 2.0F)) + this.getTempUnitC(this.mContext);
         }

         var3 = var1[10];
         if (var3 == 255) {
            GeneralAirData.rear_right_temperature = "HI";
         } else if (var3 == 0) {
            GeneralAirData.rear_right_temperature = "LO";
         } else {
            GeneralAirData.rear_right_temperature = this.formatDecimal1.format((double)((float)var1[10] / 2.0F)) + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit7(var1[11]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(var1[11]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var1[11]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit4(var1[11]);
         if (var2) {
            this.updateAirActivity(this.mContext, 1002);
         } else {
            this.updateAirActivity(this.mContext, 1001);
         }
      }

   }

   private void set0x35DriveData(int[] var1) {
      ArrayList var7 = new ArrayList();
      int var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Left_turn");
      boolean var4 = DataHandleUtils.getBoolBit4(var1[2]);
      String var6 = "ON";
      String var5;
      if (var4) {
         var5 = "ON";
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var3, var2, var5));
      this.turnLeftLamp(DataHandleUtils.getBoolBit4(var1[2]));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Right_turn");
      if (DataHandleUtils.getBoolBit5(var1[2])) {
         var5 = var6;
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var3, var2, var5));
      this.turnRightLamp(DataHandleUtils.getBoolBit5(var1[2]));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14"), var1[3] + "km/h"));
      this.updateSpeedInfo(var1[3]);
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13"), DataHandleUtils.getMsbLsbResult(var1[6], var1[7]) + "rpm"));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "show_outdoor_temperature"), (double)var1[8] * 0.5 - 40.0 + "℃"));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setBasicInfo0x01(int[] var1) {
      if (this.isBasicInfoChange(var1)) {
         this.setBacklightLevel(var1[3] / 20 + 1);
      }

   }

   private void setCarState0x38(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_display_fuel_efficient_mode"), Integer.valueOf(DataHandleUtils.getBoolBit7(var1[2]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "parkingAssist"), DataHandleUtils.getIntFromByteWithBit(var1[2], 5, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_rearview_mirror_reverse_assist_adjustment"), Integer.valueOf(DataHandleUtils.getBoolBit4(var1[2]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_6"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[2]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "ramp_start_assist"), Integer.valueOf(DataHandleUtils.getBoolBit2(var1[2]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_9"), DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_auto_start_headlights_when_unlocking"), DataHandleUtils.getIntFromByteWithBit(var1[3], 6, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_10"), Integer.valueOf(DataHandleUtils.getBoolBit5(var1[3]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_high_beam_assist"), Integer.valueOf(DataHandleUtils.getBoolBit4(var1[3]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_220_daytime_running_lights"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[3]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_bending_mode"), Integer.valueOf(DataHandleUtils.getBoolBit2(var1[3]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_lights_flashing_when_locked"), Integer.valueOf(DataHandleUtils.getBoolBit1(var1[3]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "auto_locking"), Integer.valueOf(DataHandleUtils.getBoolBit0(var1[3]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "hiworld_jeep_123_0x60_data1_4"), Integer.valueOf(DataHandleUtils.getBoolBit7(var1[4]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_auto_unlocking_get_off"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[4]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_94_key_free"), Integer.valueOf(DataHandleUtils.getBoolBit5(var1[4]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_18"), Integer.valueOf(DataHandleUtils.getBoolBit4(var1[4]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_press_key_control_unlock"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[4]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_auto_activate"), Integer.valueOf(DataHandleUtils.getBoolBit2(var1[4]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "distance_unit"), Integer.valueOf(DataHandleUtils.getBoolBit1(var1[4]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_19"), Integer.valueOf(DataHandleUtils.getBoolBit0(var1[4]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_373_setting_car_control9"), DataHandleUtils.getIntFromByteWithBit(var1[5], 6, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_370_Engine_Off_Light_delay"), DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_sound_the_horn_lock"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[5]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_sound_the_horn_remote"), Integer.valueOf(DataHandleUtils.getBoolBit2(var1[5]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_110"), Integer.valueOf(DataHandleUtils.getBoolBit1(var1[5]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_guidance_line"), Integer.valueOf(DataHandleUtils.getBoolBit0(var1[5]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "language_setup"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "language_setup", "language_setup"), var1[6]));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_123_rear_mirror"), Integer.valueOf(DataHandleUtils.getBoolBit7(var1[7]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "hiworld_jeep_123_0x73_data3_1"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[7]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "hiworld_jeep_123_0x73_data3_0"), Integer.valueOf(DataHandleUtils.getBoolBit5(var1[7]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_5"), Integer.valueOf(DataHandleUtils.getBoolBit4(var1[7]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_2_setting_12_3_2"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[7]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_1193_setting_1_7"), Integer.valueOf(DataHandleUtils.getBoolBit2(var1[7]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_123_auxiliary_1"), Integer.valueOf(DataHandleUtils.getBoolBit1(var1[7]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_775_p_key"), Integer.valueOf(DataHandleUtils.getBoolBit0(var1[7]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_2"), DataHandleUtils.getIntFromByteWithBit(var1[8], 6, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_3"), DataHandleUtils.getIntFromByteWithBit(var1[8], 4, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_lanesense_warning"), DataHandleUtils.getIntFromByteWithBit(var1[8], 2, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_lanesense_strength"), DataHandleUtils.getIntFromByteWithBit(var1[8], 0, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_29"), DataHandleUtils.getIntFromByteWithBit(var1[9], 6, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_blind_spot_alarm"), DataHandleUtils.getIntFromByteWithBit(var1[9], 4, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_start_stop_system"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[9]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_250_automatic_folding_mirror"), Integer.valueOf(DataHandleUtils.getBoolBit2(var1[9]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_front_sensor_driving"), Integer.valueOf(DataHandleUtils.getBoolBit1(var1[9]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_intelligent_electric_tailgate"), Integer.valueOf(DataHandleUtils.getBoolBit0(var1[9]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_24"), Integer.valueOf(DataHandleUtils.getBoolBit7(var1[10]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_25"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[10]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_26"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[10]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_27"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[10]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_28"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[10]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "_118_setting_title_81"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[10]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "_118_setting_title_80"), DataHandleUtils.getIntFromByteWithBit(var1[10], 0, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "_475_capacity_unit"), DataHandleUtils.getIntFromByteWithBit(var1[11], 6, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "hiworld_jeep_123_0xCA_0X08"), DataHandleUtils.getIntFromByteWithBit(var1[11], 4, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "temperature_unit"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[11]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "_475_power_unit"), DataHandleUtils.getIntFromByteWithBit(var1[11], 1, 2)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorInfo0x06(int[] var1) {
      if (this.isBasicInfoChange(var1)) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setSwc0x02(int[] var1) {
      switch (var1[2]) {
         case 0:
            this.realKeyLongClick1(this.mContext, 0, var1[3]);
            break;
         case 1:
         case 17:
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
            break;
         case 2:
         case 18:
            this.realKeyLongClick1(this.mContext, 8, var1[3]);
            break;
         case 3:
         case 11:
         case 20:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 4:
         case 10:
         case 19:
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
            break;
         case 5:
            this.realKeyLongClick1(this.mContext, 2, var1[3]);
            break;
         case 6:
            this.realKeyLongClick1(this.mContext, 3, var1[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 68, var1[3]);
            break;
         case 8:
            this.realKeyLongClick1(this.mContext, 14, var1[3]);
            break;
         case 9:
            this.realKeyLongClick1(this.mContext, 15, var1[3]);
            break;
         case 12:
            this.realKeyLongClick1(this.mContext, 151, var1[3]);
            break;
         case 13:
            this.realKeyLongClick1(this.mContext, 49, var1[3]);
         case 14:
         case 15:
         case 21:
         case 22:
         case 23:
         case 26:
         case 27:
         default:
            break;
         case 16:
            this.realKeyLongClick1(this.mContext, 50, var1[3]);
            break;
         case 24:
            this.realKeyLongClick1(this.mContext, 1, var1[3]);
            break;
         case 25:
            this.realKeyLongClick1(this.mContext, 187, var1[3]);
            break;
         case 28:
            this.realKeyLongClick1(this.mContext, 31, var1[3]);
      }

   }

   private void setTrack0x0A(int[] var1) {
      if (this.isTrackInfoChange(var1)) {
         if (DataHandleUtils.getBoolBit0(var1[2])) {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)var1[3], (byte)var1[4], 0, 480, 16);
            this.updateParkUi((Bundle)null, this.mContext);
         } else {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[3], (byte)var1[4], 0, 480, 16);
            this.updateParkUi((Bundle)null, this.mContext);
         }
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = "AUX".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, 7}, var1));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = "BT MUSIC".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, 12}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 != 6) {
                  if (var3 != 10) {
                     if (var3 != 53) {
                        if (var3 != 56) {
                           if (var3 == 127) {
                              this.updateVersionInfo(this.mContext, this.getVersionStr(var2));
                           }
                        } else {
                           this.setCarState0x38(var4);
                        }
                     } else {
                        this.set0x35DriveData(var4);
                     }
                  } else {
                     this.setTrack0x0A(var4);
                  }
               } else {
                  this.setDoorInfo0x06(var4);
               }
            } else {
               this.set0x03AirInfo(var4);
            }
         } else {
            this.setSwc0x02(var4);
         }
      } else {
         this.setBasicInfo0x01(var4);
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte[] var14 = var11.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, 2}, var14));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var25 = "MUSIC".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, 12}, var25));
   }

   public void onAccOff() {
      super.onAccOff();
   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte)this.getBandAmFM(var2), (byte)this.getFreqMsb(var2, var3), (byte)this.getFreqLsb(var2, var3), (byte)var1});
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if ("NULL".equals(var1)) {
         byte[] var2 = "MEDIA OFF".getBytes();
         CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, 0}, var2));
      }

   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = "VIDEO".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, 12}, var18));
   }
}
