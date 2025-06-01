package com.hzbhd.canbus.car._461;

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
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
   private int[] mAirData;
   private int[] mCarDoorData;
   private int[] mCarSettingInfo;
   private Context mContext;
   private int[] mFrontRadarData;
   private int[] mRearRadarData;
   private int[] mSystemConfigInfo;
   private int[] mTrackData;
   private UiMgr mUiMgr;
   private String tempUnit = "℃";

   private String getTempValue(int var1) {
      return this.tempUnit.equals("℃") ? this.formatDecimal1.format((double)var1 * 0.5) + this.getTempUnitC(this.mContext) : var1 + this.getTempUnitF(this.mContext);
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

   private boolean isCarSettingChange(int[] var1) {
      if (Arrays.equals(this.mCarSettingInfo, var1)) {
         return false;
      } else {
         this.mCarSettingInfo = Arrays.copyOf(var1, var1.length);
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

   private boolean isRearRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRearRadarData, var1)) {
         return false;
      } else {
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isSystemConfigChange(int[] var1) {
      if (Arrays.equals(this.mSystemConfigInfo, var1)) {
         return false;
      } else {
         this.mSystemConfigInfo = Arrays.copyOf(var1, var1.length);
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

   private void set0x03DriveData(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_461_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_461_drive_info_temp"), this.getTempValue(DataHandleUtils.getMsbLsbResult(var1[2], var1[3]))));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_461_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_461_drive_info_mileage"), DataHandleUtils.getMsbLsbResult(var1[4], var1[5]) + "km"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_461_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_461_drive_info_speed"), this.formatDecimal1.format((double)((float)DataHandleUtils.getMsbLsbResult(var1[6], var1[7]) / 10.0F)) + "km/h"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_461_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_461_drive_info_fuel"), this.formatDecimal1.format((double)((float)DataHandleUtils.getMsbLsbResult(var1[8], var1[9]) / 10.0F)) + "l/100km"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x04SystemConfig(int[] var1) {
      if (this.isSystemConfigChange(var1)) {
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_language"), var1[2]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_fuel_unit"), var1[3]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_temp_unit"), var1[4]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_date"), var1[5]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_time"), var1[6]));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x20Swc(int[] var1) {
      int var2 = var1[2];
      if (var2 != 70) {
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
               this.realKeyLongClick1(this.mContext, 20, var1[3]);
               break;
            case 4:
               this.realKeyLongClick1(this.mContext, 21, var1[3]);
               break;
            case 5:
               this.realKeyLongClick1(this.mContext, 14, var1[3]);
               break;
            case 6:
               this.realKeyLongClick1(this.mContext, 3, var1[3]);
               break;
            case 7:
               this.realKeyLongClick1(this.mContext, 2, var1[3]);
               break;
            case 8:
               this.realKeyLongClick1(this.mContext, 187, var1[3]);
               break;
            case 9:
               this.realKeyLongClick1(this.mContext, 128, var1[3]);
               break;
            case 10:
               this.realKeyLongClick1(this.mContext, 33, var1[3]);
               break;
            case 11:
               this.realKeyLongClick1(this.mContext, 34, var1[3]);
               break;
            case 12:
               this.realKeyLongClick1(this.mContext, 35, var1[3]);
               break;
            case 13:
               this.realKeyLongClick1(this.mContext, 36, var1[3]);
               break;
            case 14:
               this.realKeyLongClick1(this.mContext, 37, var1[3]);
               break;
            case 15:
               this.realKeyLongClick1(this.mContext, 38, var1[3]);
               break;
            case 16:
               this.realKeyLongClick1(this.mContext, 39, var1[3]);
               break;
            case 17:
               this.realKeyLongClick1(this.mContext, 40, var1[3]);
               break;
            default:
               switch (var2) {
                  case 41:
                     this.realKeyClick4(this.mContext, 21);
                     break;
                  case 42:
                     this.realKeyClick4(this.mContext, 20);
                     break;
                  case 43:
                     this.realKeyLongClick1(this.mContext, 21, var1[3]);
                     break;
                  case 44:
                     this.realKeyLongClick1(this.mContext, 20, var1[3]);
                     break;
                  case 45:
                     this.realKeyLongClick1(this.mContext, 45, var1[3]);
                     break;
                  case 46:
                     this.realKeyLongClick1(this.mContext, 46, var1[3]);
                     break;
                  case 47:
                     this.realKeyLongClick1(this.mContext, 49, var1[3]);
                     break;
                  case 48:
                     this.realKeyLongClick1(this.mContext, 59, var1[3]);
                     break;
                  case 49:
                     this.realKeyLongClick1(this.mContext, 62, var1[3]);
                     break;
                  case 50:
                     this.realKeyLongClick1(this.mContext, 52, var1[3]);
                     break;
                  case 51:
                     this.realKeyLongClick1(this.mContext, 14, var1[3]);
                     break;
                  case 52:
                     this.realKeyLongClick1(this.mContext, 128, var1[3]);
                     break;
                  case 53:
                     this.realKeyLongClick1(this.mContext, 50, var1[3]);
               }
         }
      } else {
         this.realKeyLongClick1(this.mContext, 58, var1[3]);
      }

   }

   private void set0x21AirInfo(int[] var1) {
      if (this.isAirDataChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var1[2]) ^ true;
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(var1[2]);
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         if (DataHandleUtils.getBoolBit7(var1[3])) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
         }

         if (DataHandleUtils.getBoolBit6(var1[3])) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         }

         if (DataHandleUtils.getBoolBit5(var1[3])) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }

         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4);
         int var2 = var1[5];
         if (var2 == 0) {
            GeneralAirData.front_left_temperature = "LO";
         } else if (var2 == 31) {
            GeneralAirData.front_left_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = this.formatDecimal1.format((double)((float)var1[5] / 2.0F) + 15.5) + this.getTempUnitC(this.mContext);
         }

         var2 = var1[6];
         if (var2 == 0) {
            GeneralAirData.front_right_temperature = "LO";
         } else if (var2 == 31) {
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_right_temperature = this.formatDecimal1.format((double)((float)var1[6] / 2.0F) + 15.5) + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 3);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 3);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void set0x22RearRadar(int[] var1) {
      if (this.isRearRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x23FrontRadarInfo(int[] var1) {
      if (this.isFrontRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x24BasicInfo(int[] var1) {
      var1[3] = 0;
      if (this.isBasicInfoChange(var1) && DataHandleUtils.getBoolBit0(var1[2])) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x29Esp(int[] var1) {
      if (this.isTrackInfoChange(var1)) {
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[2], (byte)var1[3], 0, 5400, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x40Setting(int[] var1) {
      if (this.isCarSettingChange(var1)) {
         ArrayList var2 = new ArrayList();
         var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_speed"), var1[2])).setProgress(var1[2]));
         var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_home_lighting"), var1[3])).setProgress(var1[3]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_three_lighting"), var1[4]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_running_lights"), var1[5]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_welcome_light"), var1[6]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_car_door_locks"), var1[7]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_relock"), var1[8]));
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_interlock"), var1[9]));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      int var3 = var4[1];
      if (var3 != 3) {
         if (var3 != 4) {
            if (var3 != 41) {
               if (var3 != 64) {
                  switch (var3) {
                     case 32:
                        this.set0x20Swc(var4);
                        break;
                     case 33:
                        this.set0x21AirInfo(var4);
                        break;
                     case 34:
                        this.set0x22RearRadar(var4);
                        break;
                     case 35:
                        this.set0x23FrontRadarInfo(var4);
                        break;
                     case 36:
                        this.set0x24BasicInfo(var4);
                  }
               } else {
                  this.set0x40Setting(var4);
               }
            } else {
               this.set0x29Esp(var4);
            }
         } else {
            this.set0x04SystemConfig(var4);
         }
      } else {
         this.set0x03DriveData(var4);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var14 = (byte)var5;
      byte var15 = (byte)var6;
      CanbusMsgSender.sendMsg(new byte[]{22, -125, var14, var15, (byte)var4, var15, (byte)(var1 - 2000)});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }
}
