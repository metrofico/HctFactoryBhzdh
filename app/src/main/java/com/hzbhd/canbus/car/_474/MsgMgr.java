package com.hzbhd.canbus.car._474;

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
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private String consumptionUnit = "l/100km";
   private String distanceUnit = "km";
   DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
   DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   int[] mAirData;
   int[] mCarDoorData;
   private Context mContext;
   int[] mDoorInfo;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   int[] mTrackData;
   private UiMgr mUiMgr;
   private String tempUnit = "℃";

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

   private String getP_GearState(boolean var1) {
      return var1 ? this.mContext.getString(2131759703) : this.mContext.getString(2131759702);
   }

   private int getRadarRange(int var1) {
      return var1;
   }

   private String getReversingState(boolean var1) {
      return var1 ? this.mContext.getString(2131759718) : this.mContext.getString(2131759719);
   }

   private String getSwitchState(boolean var1) {
      return var1 ? this.mContext.getString(2131759717) : this.mContext.getString(2131759716);
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

   private boolean isDoorInfoChange(int[] var1) {
      if (Arrays.equals(this.mDoorInfo, var1)) {
         return false;
      } else {
         this.mDoorInfo = Arrays.copyOf(var1, var1.length);
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

   private void set0x01BaseInfo(int[] var1) {
      if (this.isBasicInfoChange(var1)) {
         this.setBacklightLevel(var1[3] / 20 + 1);
      }

   }

   private void set0x02WheelKey(int[] var1) {
      int var2 = var1[2];
      if (var2 != 13) {
         if (var2 != 25) {
            switch (var2) {
               case 0:
                  this.realKeyLongClick1(this.mContext, 0, var1[3]);
                  break;
               case 1:
                  this.realKeyLongClick1(this.mContext, 7, var1[3]);
                  break;
               case 2:
                  this.realKeyLongClick1(this.mContext, 8, var1[3]);
                  break;
               case 3:
                  this.realKeyLongClick1(this.mContext, 46, var1[3]);
                  break;
               case 4:
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
               case 10:
                  this.realKeyLongClick1(this.mContext, 45, var1[3]);
                  break;
               case 11:
                  this.realKeyLongClick1(this.mContext, 46, var1[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 187, var1[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 49, var1[3]);
      }

   }

   private void set0x03AirInfo(int[] var1) {
      if (this.isAirDataChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(var1[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(var1[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(var1[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4);
         int var2 = var1[4];
         if (var2 == 255) {
            GeneralAirData.front_left_temperature = "HI";
         } else if (var2 == 0) {
            GeneralAirData.front_left_temperature = "LO";
         } else {
            GeneralAirData.front_left_temperature = this.formatDecimal1.format((double)((float)var1[4] / 2.0F)) + this.getTempUnitC(this.mContext);
         }

         var2 = var1[5];
         if (var2 == 255) {
            GeneralAirData.front_right_temperature = "HI";
         } else if (var2 == 0) {
            GeneralAirData.front_right_temperature = "LO";
         } else {
            GeneralAirData.front_right_temperature = this.formatDecimal1.format((double)((float)var1[5] / 2.0F)) + "℃";
         }

         GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit5(var1[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(var1[6]);
         GeneralAirData.mono = DataHandleUtils.getBoolBit1(var1[6]);
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(var1[6]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(var1[8]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(var1[8]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var1[8]);
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(var1[8]);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void set0x35DriveData(int[] var1) {
      ArrayList var9 = new ArrayList();
      int var5 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      int var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_front_fog_lamp");
      boolean var6 = DataHandleUtils.getBoolBit0(var1[2]);
      String var8 = "ON";
      String var7;
      if (var6) {
         var7 = "ON";
      } else {
         var7 = "OFF";
      }

      var9.add(new DriverUpdateEntity(var5, var4, var7));
      var5 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_low_beam");
      if (DataHandleUtils.getBoolBit1(var1[2])) {
         var7 = "ON";
      } else {
         var7 = "OFF";
      }

      var9.add(new DriverUpdateEntity(var5, var4, var7));
      var4 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var5 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_high_beam");
      if (DataHandleUtils.getBoolBit2(var1[2])) {
         var7 = "ON";
      } else {
         var7 = "OFF";
      }

      var9.add(new DriverUpdateEntity(var4, var5, var7));
      var5 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_rear_fog_lamp");
      if (DataHandleUtils.getBoolBit3(var1[2])) {
         var7 = "ON";
      } else {
         var7 = "OFF";
      }

      var9.add(new DriverUpdateEntity(var5, var4, var7));
      var5 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_left_trun_lamp");
      if (DataHandleUtils.getBoolBit4(var1[2])) {
         var7 = "ON";
      } else {
         var7 = "OFF";
      }

      var9.add(new DriverUpdateEntity(var5, var4, var7));
      this.turnLeftLamp(DataHandleUtils.getBoolBit4(var1[2]));
      var4 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var5 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_right_turn_lamp");
      if (DataHandleUtils.getBoolBit5(var1[2])) {
         var7 = var8;
      } else {
         var7 = "OFF";
      }

      var9.add(new DriverUpdateEntity(var4, var5, var7));
      this.turnRightLamp(DataHandleUtils.getBoolBit5(var1[2]));
      var9.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14"), var1[3] + "km/h"));
      this.updateSpeedInfo(var1[3]);
      var9.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_112_available_mileage"), var1[4] * 256 + var1[5] + this.distanceUnit));
      var9.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13"), DataHandleUtils.getMsbLsbResult(var1[6], var1[7]) + "rpm"));
      var4 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var5 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "show_outdoor_temperature");
      double var2;
      StringBuilder var10;
      if (this.tempUnit.equals("℃")) {
         var10 = new StringBuilder();
         var2 = (double)var1[8] * 0.5 - 40.0;
      } else {
         var10 = new StringBuilder();
         var2 = ((double)var1[8] * 0.5 - 40.0) * 1.8 + 32.0;
      }

      var9.add(new DriverUpdateEntity(var4, var5, var10.append(var2).append(this.tempUnit).toString()));
      var9.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "total_mileage"), (var1[9] << 24) + (var1[10] << 16) + (var1[11] << 8) + var1[12] + this.distanceUnit));
      this.updateGeneralDriveData(var9);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarState0x38(int[] var1) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_0"), Integer.valueOf(DataHandleUtils.getBoolBit7(var1[3]))));
      var4.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_1"), var1[2])).setProgress(var1[2] - 16));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_2"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[3]))));
      String var3;
      if (DataHandleUtils.getBoolBit6(var1[3])) {
         var3 = "℉";
      } else {
         var3 = "℃";
      }

      this.tempUnit = var3;
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_3"), DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 2)));
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 2);
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 == 3) {
                  this.consumptionUnit = "mpg us";
               }
            } else {
               this.consumptionUnit = "km/l";
            }
         } else {
            this.consumptionUnit = "mpg uk";
         }
      } else {
         this.consumptionUnit = "l/100km";
      }

      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_4"), Integer.valueOf(DataHandleUtils.getBoolBit0(var1[4]))));
      if (DataHandleUtils.getBoolBit0(var1[4])) {
         var3 = "mil";
      } else {
         var3 = "km";
      }

      this.distanceUnit = var3;
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_buzzer_volume"), DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4)));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_automatic_door_lock"), Integer.valueOf(DataHandleUtils.getBoolBit7(var1[4]))));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_independent_unlocking_of_driver_door"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[4]))));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_independent_trunk_lid_unlocking"), Integer.valueOf(DataHandleUtils.getBoolBit5(var1[4]))));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_61_radar_switch"), Integer.valueOf(DataHandleUtils.getBoolBit4(var1[4]))));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_143_setting_5"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[4]))));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "light_ctrl_3"), Integer.valueOf(DataHandleUtils.getBoolBit2(var1[4]))));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_photosensitivity"), DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 4)));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_5"), var1[6]));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorInfo0x06(int[] var1) {
      if (this.isDoorInfoChange(var1)) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit0(var1[2]);
         GeneralDoorData.isShowSeatBelt = DataHandleUtils.getBoolBit0(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setDrive0x37(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_a_driving_time"), this.formatInteger2.format((long)var1[2]) + ":" + this.formatInteger2.format((long)var1[3])));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_a_average"), var1[4] + "km/h"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_a_distance_traveled"), this.formatDecimal2.format((double)((float)DataHandleUtils.getMsbLsbResult(var1[5], var1[6]) / 10.0F)) + this.distanceUnit));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_a_average_fuel_consumption"), this.formatDecimal2.format((double)((float)DataHandleUtils.getMsbLsbResult(var1[7], var1[8]) / 10.0F)) + this.consumptionUnit));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_b_driving_time"), this.formatInteger2.format((long)var1[9]) + ":" + this.formatInteger2.format((long)var1[10])));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_b_average"), var1[11] + "km/h"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_b_distance_traveled"), this.formatDecimal2.format((double)((float)DataHandleUtils.getMsbLsbResult(var1[12], var1[13]) / 10.0F)) + this.distanceUnit));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_b_average_fuel_consumption"), this.formatDecimal2.format((double)((float)DataHandleUtils.getMsbLsbResult(var1[14], var1[15]) / 10.0F)) + this.consumptionUnit));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setMaintainInfo0x39(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_189_maintenance"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_94_maintenance_mileage"), DataHandleUtils.getMsbLsbResult(var1[2], var1[3]) + this.distanceUnit));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_189_maintenance"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_maintenance_days"), var1[4] + "day"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRearRadar0x04(int[] var1) {
      if (this.isRearRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(3, this.getRadarRange(var1[2]), this.getRadarRange(var1[3]), this.getRadarRange(var1[4]), this.getRadarRange(var1[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRearRadar0x05(int[] var1) {
      if (this.isFrontRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(3, this.getRadarRange(var1[2]), this.getRadarRange(var1[3]), this.getRadarRange(var1[4]), this.getRadarRange(var1[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
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
      if (var3 != 10) {
         if (var3 != 53) {
            if (var3 != 127) {
               switch (var3) {
                  case 1:
                     this.set0x01BaseInfo(var4);
                     break;
                  case 2:
                     this.set0x02WheelKey(var4);
                     break;
                  case 3:
                     this.set0x03AirInfo(var4);
                     break;
                  case 4:
                     this.setRearRadar0x04(var4);
                     break;
                  case 5:
                     this.setRearRadar0x05(var4);
                     break;
                  case 6:
                     this.setDoorInfo0x06(var4);
                     break;
                  default:
                     switch (var3) {
                        case 55:
                           this.setDrive0x37(var4);
                           break;
                        case 56:
                           this.setCarState0x38(var4);
                           break;
                        case 57:
                           this.setMaintainInfo0x39(var4);
                     }
               }
            } else {
               this.updateVersionInfo(this.mContext, this.getVersionStr(var2));
            }
         } else {
            this.set0x35DriveData(var4);
         }
      } else {
         this.setTrack0x0A(var4);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      byte var15 = (byte)var2;
      byte var16 = (byte)var3;
      byte var18 = (byte)var4;
      byte var14 = (byte)(var10 ^ 1);
      byte var17 = (byte)var8;
      byte var19 = (byte)var6;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58}, new byte[]{var15, var16, var18, var14, var17, var19}));
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
