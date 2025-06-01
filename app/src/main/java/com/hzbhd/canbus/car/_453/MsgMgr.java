package com.hzbhd.canbus.car._453;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
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
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   private int differentId;
   private int eachId;
   int[] mAirData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   int[] mCarDoorData;
   private Context mContext;
   int[] mDoorInfo;
   int[] mFrontRadarData;
   int[] mRearRadarData;
   int[] mSettingdInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;

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
         this.mAirData = var1;
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

   private boolean isRearRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRearRadarData, var1)) {
         return false;
      } else {
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isSettingsInfoChange(int[] var1) {
      if (Arrays.equals(this.mSettingdInfo, var1)) {
         return false;
      } else {
         this.mSettingdInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var2 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var2, var2.length);
         return true;
      }
   }

   private void set0x14(int[] var1) {
      if (this.isBasicInfoChange(var1)) {
         this.setBacklightLevel(var1[2] / 12 + 1);
      }

   }

   private void set0x20Swc(int[] var1) {
      switch (var1[2]) {
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
            this.realKeyLongClick1(this.mContext, 14, var1[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 187, var1[3]);
            break;
         case 8:
            this.realKeyLongClick1(this.mContext, 15, var1[3]);
            break;
         case 9:
            int var2 = var1[3];
            if (var2 == 2) {
               this.realKeyLongClick1(this.mContext, 1, var2);
            } else {
               this.realKeyLongClick1(this.mContext, 3, var2);
            }
            break;
         case 10:
            this.realKeyClick4(this.mContext, 7);
            break;
         case 11:
            this.realKeyClick4(this.mContext, 8);
            break;
         case 12:
            this.realKeyLongClick1(this.mContext, 52, var1[3]);
            break;
         case 13:
            this.realKeyLongClick1(this.mContext, 63, var1[3]);
            break;
         case 14:
            this.realKeyLongClick1(this.mContext, 5, var1[3]);
            break;
         case 15:
            this.realKeyLongClick1(this.mContext, 50, var1[3]);
            break;
         case 16:
            this.realKeyClick4(this.mContext, 47);
            break;
         case 17:
            this.realKeyClick4(this.mContext, 48);
            break;
         case 18:
            this.realKeyLongClick1(this.mContext, 49, var1[3]);
      }

   }

   private void set0x22(int[] var1) {
      if (this.isRearRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(6, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x23(int[] var1) {
      if (this.isFrontRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(6, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x24DoorInfo(int[] var1) {
      if (this.isDoorInfoChange(var1) && DataHandleUtils.getBoolBit0(var1[2])) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x27AirInfo(int[] var1) {
      if (this.isAirDataChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var1[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(var1[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(var1[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4);
         int var2 = var1[4];
         if (var2 == 255) {
            GeneralAirData.front_left_temperature = "HI";
         } else if (var2 == 0) {
            GeneralAirData.front_left_temperature = "LO";
         } else {
            GeneralAirData.front_left_temperature = this.df_1Decimal.format((double)((float)var1[4] / 2.0F)) + this.getTempUnitC(this.mContext);
         }

         var2 = var1[5];
         if (var2 == 255) {
            GeneralAirData.front_right_temperature = "HI";
         } else if (var2 == 0) {
            GeneralAirData.front_right_temperature = "LO";
         } else {
            GeneralAirData.front_right_temperature = this.df_1Decimal.format((double)((float)var1[5] / 2.0F)) + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.eco = DataHandleUtils.getBoolBit1(var1[6]);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void set0x29(int[] var1) {
      if (this.isTrackInfoChange(var1)) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 21632, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x30(byte[] var1) {
      this.updateVersionInfo(this.mContext, this.getVersionStr(var1));
   }

   private void set0x38() {
      if (this.isSettingsInfoChange(this.mCanBusInfoInt)) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_1", "_453_park_assist_tone"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_1", "_453_park_assist_delay_timer"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_1", "_453_park_assist"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_2", "_453_vehicle_auto_relock"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_2", "_453_door_unlocking"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_exterior_litghts_approach_lamps"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_mode"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_front"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_rear"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_color"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_lumim"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_drive_away_locking"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 32) {
            if (var3 != 39) {
               if (var3 != 41) {
                  if (var3 != 48) {
                     if (var3 != 56) {
                        switch (var3) {
                           case 34:
                              this.set0x22(var4);
                              break;
                           case 35:
                              this.set0x23(var4);
                              break;
                           case 36:
                              this.set0x24DoorInfo(var4);
                        }
                     } else {
                        this.set0x38();
                     }
                  } else {
                     this.set0x30(var2);
                  }
               } else {
                  this.set0x29(var4);
               }
            } else {
               this.set0x27AirInfo(var4);
            }
         } else {
            this.set0x20Swc(var4);
         }
      } else {
         this.set0x14(var4);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
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
}
