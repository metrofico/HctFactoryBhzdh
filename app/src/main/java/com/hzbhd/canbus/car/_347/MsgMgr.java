package com.hzbhd.canbus.car._347;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._333.AlertView;
import com.hzbhd.canbus.car._341.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int[] OutGoingPhoneNumber;
   int alarmInfo1;
   int alarmInfo2;
   int carDoorInt;
   int[] comingPhoneNumber;
   int differentId;
   private int eachId;
   DecimalFormat integerFormat = new DecimalFormat("0");
   int[] mAirData;
   int[] mBackLightInt;
   private boolean mBackStatus;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   private byte mFreqHi;
   private byte mFreqLo;
   int[] mFrontRadarData;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   int[] mTireInfo;
   byte[] mTrackData;
   private int[] talkingPhoneNumber;
   private UiMgr uiMgr;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      int var4 = var1.length + var2.length;
      byte[] var6 = new byte[var4];
      int var5 = var1.length;
      int var3 = 0;
      System.arraycopy(var1, 0, var6, 0, var5);
      System.arraycopy(var2, 0, var6, var1.length, var2.length);
      MyLog.e("长度", var4 + "");

      while(var3 < var4) {
         MyLog.e("内容" + var3, var6[var3] + "");
         ++var3;
      }

      return var6;
   }

   private void adjustBrightness() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 <= 50) {
         this.setBacklightLevel(5);
      } else if (var1 > 50 && var1 <= 100) {
         this.setBacklightLevel(4);
      } else if (var1 > 100 && var1 <= 150) {
         this.setBacklightLevel(3);
      } else if (var1 > 150 && var1 <= 200) {
         this.setBacklightLevel(2);
      } else if (var1 > 200) {
         this.setBacklightLevel(1);
      }

   }

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

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
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

   private String getNullHaveState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131762844) : this.mContext.getString(2131762873);
   }

   private String getP_GearState(boolean var1) {
      return var1 ? this.mContext.getString(2131759703) : this.mContext.getString(2131759702);
   }

   private String getReversingState(boolean var1) {
      return var1 ? this.mContext.getString(2131759718) : this.mContext.getString(2131759719);
   }

   private int getSurroundVolFRprogress(int var1) {
      int var2 = var1;
      switch (var1) {
         default:
            switch (var1) {
               case 247:
                  var2 = -9;
                  break;
               case 248:
                  return -8;
               case 249:
                  return -7;
               case 250:
                  return -6;
               case 251:
                  return -5;
               case 252:
                  return -4;
               case 253:
                  return -3;
               case 254:
                  return -2;
               case 255:
                  return -1;
               default:
                  return 0;
            }
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
            return var2;
      }
   }

   private String getSwitchState(boolean var1) {
      return var1 ? this.mContext.getString(2131759717) : this.mContext.getString(2131759716);
   }

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isAlarm1InfoChange() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
      if (this.alarmInfo1 == var1) {
         return false;
      } else {
         this.alarmInfo1 = var1;
         return true;
      }
   }

   private boolean isAlarm2InfoChange() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
      if (this.alarmInfo2 == var1) {
         return false;
      } else {
         this.alarmInfo2 = var1;
         return true;
      }
   }

   private boolean isBackLightIntChange() {
      if (Arrays.equals(this.mBackLightInt, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mBackLightInt = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isCarDoorInfoChange() {
      int var2 = this.carDoorInt;
      int var1 = this.mCanBusInfoInt[3];
      if (var2 == var1) {
         return false;
      } else {
         this.carDoorInt = var1;
         return true;
      }
   }

   private boolean isDoorChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mHandBrake = GeneralDoorData.isHandBrakeUp;
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return true;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void setAirData0x21() {
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4], 0.5, 0.0, "C", 0, 254);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5], 0.5, 0.0, "C", 0, 254);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setAmplifierInfo0x27() {
      ArrayList var1 = new ArrayList();
      GeneralAmplifierData.volume = this.mCanBusInfoInt[3];
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6];
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[8];
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7];
      GeneralAmplifierData.frontRear = this.getSurroundVolFRprogress(this.mCanBusInfoInt[4]);
      GeneralAmplifierData.leftRight = this.getSurroundVolFRprogress(this.mCanBusInfoInt[5]);
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_Vehicle_volume_follows_ASL"), this.mCanBusInfoInt[9])).setProgress(this.mCanBusInfoInt[9]));
      this.updateAmplifierActivity(new Bundle());
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setBacklightInfo0x14() {
      if (!this.isBackLightIntChange()) {
         ArrayList var1 = new ArrayList();
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info1"), this.mCanBusInfoInt[2] + ""));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
         this.adjustBrightness();
      }
   }

   private void setDoorData0x24() {
      if (!this.isBasicInfoChange()) {
         ArrayList var1 = new ArrayList();
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info3"), this.getSwitchState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info4"), this.getSwitchState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info5"), this.getReversingState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info10"), this.getP_GearState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
         this.adjustBrightness();
      }
   }

   private void setDoorData0x41() {
      int[] var4 = this.mCanBusInfoInt;
      int var1 = var4[2];
      if (var1 != 1) {
         int var2;
         ArrayList var10;
         if (var1 != 2) {
            var2 = 0;
            if (var1 != 3) {
               if (var1 != 64) {
                  if (var1 == 128) {
                     var10 = new ArrayList();
                     if (this.mCanBusInfoInt[4] > 0) {
                        var10.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_347_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_347_settings", "_347_settings_0"), this.mCanBusInfoInt[4] - 1));
                     }

                     if (this.mCanBusInfoInt[5] > 0) {
                        var10.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_347_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_347_settings", "_347_settings_1"), this.mCanBusInfoInt[5] - 1));
                     }

                     if (this.mCanBusInfoInt[6] > 5) {
                        var10.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_347_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_347_settings", "_347_settings_2"), this.mCanBusInfoInt[6] - 6));
                     }

                     this.updateGeneralSettingData(var10);
                     this.updateActivity((Bundle)null);
                  }
               } else {
                  var10 = new ArrayList();
                  var10.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_347_setting_0_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 1));
                  var10.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_347_setting_0_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 1));
                  var10.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_347_setting_0_6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) - 1));
                  var10.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_347_setting_0_7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 1));
                  var10.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_347_setting_0"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_347_setting_0_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)));
                  this.updateGeneralSettingData(var10);
                  this.updateActivity((Bundle)null);
               }
            } else {
               String var5 = "";
               String var11 = var5;
               byte var9 = (byte)var2;
               if (this.isAlarm1InfoChange()) {
                  var11 = var5;
                  var9 = (byte)var2;
                  if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
                     var11 = "" + "【" + this.mContext.getString(2131759712) + 1 + "：" + this.mContext.getString(2131759709) + "】";
                     var9 = 1;
                  }
               }

               var5 = var11;
               byte var3 = var9;
               if (this.isAlarm2InfoChange()) {
                  var5 = var11;
                  var3 = var9;
                  if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
                     var5 = var11 + "【" + this.mContext.getString(2131759712) + (var9 + 1) + "：" + this.mContext.getString(2131759710) + "】";
                     var3 = 1;
                  }
               }

               if (var3 == 1) {
                  this.showDialog(var5);
               }
            }
         } else {
            var10 = new ArrayList();
            var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
            var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13");
            StringBuilder var12 = new StringBuilder();
            int[] var6 = this.mCanBusInfoInt;
            var10.add(new DriverUpdateEntity(var1, var2, var12.append(var6[3] * 256 + var6[4]).append(" RPM").toString()));
            var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
            var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14");
            var12 = new StringBuilder();
            var6 = this.mCanBusInfoInt;
            var10.add(new DriverUpdateEntity(var2, var1, var12.append((double)(var6[5] * 256 + var6[6]) * 0.01).append(" Km/h").toString()));
            var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
            var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info15");
            var12 = new StringBuilder();
            var6 = this.mCanBusInfoInt;
            var10.add(new DriverUpdateEntity(var2, var1, var12.append((double)(var6[7] * 256 + var6[8]) * 0.01).append(" V").toString()));
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])) {
               Context var13 = this.mContext;
               var12 = new StringBuilder();
               DecimalFormat var7 = this.integerFormat;
               int[] var8 = this.mCanBusInfoInt;
               this.updateOutDoorTemp(var13, var12.append(var7.format((double)(var8[9] * 256 + var8[10]) * 0.1 - 6553.6)).append(this.getTempUnitC(this.mContext)).toString());
            } else {
               Context var16 = this.mContext;
               StringBuilder var14 = new StringBuilder();
               DecimalFormat var15 = this.integerFormat;
               var6 = this.mCanBusInfoInt;
               this.updateOutDoorTemp(var16, var14.append(var15.format((double)(var6[9] * 256 + var6[10]) * 0.1)).append(this.getTempUnitC(this.mContext)).toString());
            }

            var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
            var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16");
            var12 = new StringBuilder();
            var6 = this.mCanBusInfoInt;
            var10.add(new DriverUpdateEntity(var1, var2, var12.append(var6[11] * 65536 + var6[12] * 256 + var6[13]).append(" Km").toString()));
            var10.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info17"), this.mCanBusInfoInt[14] + " L"));
            this.updateGeneralDriveData(var10);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else {
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit7(var4[3]);
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      }

      GeneralDoorData.isShowHandBrake = true;
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontRadarInfo0x23() {
      if (!this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setPanelKey0x28(int[] var1) {
      int var2 = var1[2];
      if (var2 != 24) {
         if (var2 != 56) {
            if (var2 != 64) {
               if (var2 != 65) {
                  if (var2 != 80) {
                     if (var2 != 81) {
                        switch (var2) {
                           case 0:
                              this.realKeyLongClick1(this.mContext, 0, var1[3]);
                              break;
                           case 1:
                              this.realKeyLongClick1(this.mContext, 129, var1[3]);
                              break;
                           case 2:
                              this.realKeyLongClick1(this.mContext, 2, var1[3]);
                              break;
                           case 3:
                              this.realKeyLongClick1(this.mContext, 39, var1[3]);
                              break;
                           case 4:
                              this.realKeyLongClick1(this.mContext, 188, var1[3]);
                              break;
                           case 5:
                              this.realKeyLongClick1(this.mContext, 128, var1[3]);
                              break;
                           case 6:
                              this.realKeyLongClick1(this.mContext, 40, var1[3]);
                              break;
                           case 7:
                              this.realKeyLongClick1(this.mContext, 41, var1[3]);
                              break;
                           case 8:
                              this.realKeyLongClick1(this.mContext, 58, var1[3]);
                              break;
                           case 9:
                              this.realKeyLongClick1(this.mContext, 42, var1[3]);
                              break;
                           case 10:
                              this.realKeyLongClick1(this.mContext, 47, var1[3]);
                              break;
                           case 11:
                              this.realKeyLongClick1(this.mContext, 47, var1[3]);
                              break;
                           case 12:
                              this.realKeyLongClick1(this.mContext, 47, var1[3]);
                              break;
                           case 13:
                              this.realKeyLongClick1(this.mContext, 48, var1[3]);
                              break;
                           case 14:
                              this.realKeyLongClick1(this.mContext, 48, var1[3]);
                              break;
                           case 15:
                              this.realKeyLongClick1(this.mContext, 50, var1[3]);
                              break;
                           case 16:
                              this.realKeyLongClick1(this.mContext, 48, var1[3]);
                        }
                     } else {
                        this.realKeyClick4(this.mContext, 46);
                     }
                  } else {
                     this.realKeyClick4(this.mContext, 45);
                  }
               } else {
                  this.realKeyClick4(this.mContext, 8);
               }
            } else {
               this.realKeyClick4(this.mContext, 7);
            }
         } else {
            this.realKeyClick4(this.mContext, 33);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 4112, var1[3]);
      }

   }

   private void setPanoramicData0x25() {
      ArrayList var2 = new ArrayList();
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_paring_info1"), this.getSwitchState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_paring_info2"), this.getSwitchState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_paring_info", "_246_paring_info2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRearRadarInfo0x22() {
      if (!this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setSpeedInfo0x16() {
      ArrayList var5 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info2");
      StringBuilder var4 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var4.append(MsgMgrKt.getMsbLsbResult(var3[3], var3[2]) / 16).append("km/h").toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
      var3 = this.mCanBusInfoInt;
      this.updateSpeedInfo((int)((float)MsgMgrKt.getMsbLsbResult(var3[3], var3[2]) / 16.0F));
   }

   private void setTrackData0x26() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKeyInfo0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 22) {
         switch (var1) {
            case 0:
               this.buttonKey(0);
               break;
            case 1:
               this.buttonKey(7);
               break;
            case 2:
               this.buttonKey(8);
               break;
            case 3:
               this.buttonKey(20);
               break;
            case 4:
               this.buttonKey(21);
               break;
            case 5:
               this.buttonKey(188);
               break;
            case 6:
               this.buttonKey(3);
               break;
            case 7:
               this.buttonKey(2);
               break;
            case 8:
               this.buttonKey(136);
               break;
            case 9:
               this.buttonKey(14);
               break;
            case 10:
               this.buttonKey(15);
               break;
            case 11:
               this.buttonKey(135);
         }
      } else {
         this.buttonKey(49);
      }

   }

   private void showDialog(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$showContent;

         {
            this.this$0 = var1;
            this.val$showContent = var2;
         }

         public void callback() {
            (new AlertView()).showDialog(this.this$0.getActivity(), this.val$showContent);
         }
      });
   }

   private void tokingNowTime(int var1) {
      int var2 = var1 % 3600;
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)(var2 % 60), (byte)(var2 / 60), (byte)(var1 / 3600), 0});
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      int var1 = this.eachId;
      if (var1 == 1 || var1 == 2 || var1 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(7, 48);
      }

      this.getUiMgr(this.mContext).sendIconInfo(0, 0);
      var1 = this.eachId;
      if (var1 == 1 || var1 == 2) {
         this.getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
      }

   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      int var1 = this.eachId;
      if (var1 == 1 || var1 == 2 || var1 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(11, 34);
      }

      this.getUiMgr(this.mContext).sendIconInfo(0, 0);
      var1 = this.eachId;
      if (var1 == 1 || var1 == 2) {
         this.getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
      }

   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      int var1 = this.eachId;
      if (var1 == 1 || var1 == 2 || var1 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(0, 0);
      }

      this.getUiMgr(this.mContext).sendIconInfo(0, 0);
      var1 = this.eachId;
      if (var1 == 1 || var1 == 2) {
         this.getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
      }

   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      MyLog.temporaryTracking("挂断");
      this.comingPhoneNumber = null;
      this.OutGoingPhoneNumber = null;
      this.talkingPhoneNumber = null;
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            (new CountDownTimer(this, 2000L, 500L) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onFinish() {
                  MyLog.temporaryTracking("挂断完成");
                  if (this.this$1.this$0.eachId == 1 || this.this$1.this$0.eachId == 2) {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPhoneNumber();
                  }

               }

               public void onTick(long var1) {
                  MyLog.temporaryTracking("挂断中");
                  if (this.this$1.this$0.eachId == 1 || this.this$1.this$0.eachId == 2) {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPhoneNumber();
                  }

               }
            }).start();
         }
      });
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      MyLog.temporaryTracking("来电");
      int[] var5 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var5, this.comingPhoneNumber)) {
         MyLog.temporaryTracking("传 来电");
         this.comingPhoneNumber = var5;
         int var4 = this.eachId;
         if (var4 == 1 || var4 == 2) {
            this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 1, 1}, var1));
         }
      }

   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      MyLog.temporaryTracking("拨号");

      int var4;
      int var5;
      for(var4 = 0; var4 < var1.length; var4 = var5) {
         StringBuilder var6 = (new StringBuilder()).append("号码");
         var5 = var4 + 1;
         MyLog.e(var6.append(var5).toString(), var1[var4] + "");
      }

      int[] var7 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var7, this.OutGoingPhoneNumber)) {
         MyLog.temporaryTracking("传 拨号");
         this.OutGoingPhoneNumber = var7;
         var4 = this.eachId;
         if (var4 == 1 || var4 == 2) {
            this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 2, 1}, var1));
         }
      }

   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      var4 = this.eachId;
      if (var4 == 1 || var4 == 2 || var4 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(5, 64);
      }

      this.getUiMgr(this.mContext).sendIconInfo(0, 0);
      MyLog.temporaryTracking("通话中");
      int[] var5 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var5, this.talkingPhoneNumber)) {
         MyLog.temporaryTracking("传递 通话中");
         this.talkingPhoneNumber = var5;
         var4 = this.eachId;
         if (var4 == 1 || var4 == 2) {
            this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 4, 1}, var1));
         }
      }

   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 22) {
            if (var3 != 48) {
               if (var3 != 65) {
                  switch (var3) {
                     case 32:
                        this.setWheelKeyInfo0x20();
                        break;
                     case 33:
                        if (this.eachId != 5) {
                           return;
                        }

                        this.setAirData0x21();
                        break;
                     case 34:
                        var3 = this.eachId;
                        if (var3 != 5 && var3 != 6) {
                           return;
                        }

                        this.setRearRadarInfo0x22();
                        break;
                     case 35:
                        var3 = this.eachId;
                        if (var3 != 5 && var3 != 6) {
                           return;
                        }

                        this.setFrontRadarInfo0x23();
                        break;
                     case 36:
                        this.setDoorData0x24();
                        break;
                     case 37:
                        if (this.eachId != 5) {
                           return;
                        }

                        this.setPanoramicData0x25();
                        break;
                     case 38:
                        this.setTrackData0x26();
                        break;
                     case 39:
                        this.setAmplifierInfo0x27();
                        break;
                     case 40:
                        this.setPanelKey0x28(var4);
                  }
               } else {
                  this.setDoorData0x41();
               }
            } else {
               this.setVersionInfo0x30();
            }
         } else {
            this.setSpeedInfo0x16();
         }
      } else {
         this.setBacklightInfo0x14();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      if (var2) {
         var1 = this.getDecimalFrom8Bit(1, DataHandleUtils.getIntFromByteWithBit(var1, 6, 1), DataHandleUtils.getIntFromByteWithBit(var1, 5, 1), DataHandleUtils.getIntFromByteWithBit(var1, 4, 1), DataHandleUtils.getIntFromByteWithBit(var1, 3, 1), DataHandleUtils.getIntFromByteWithBit(var1, 2, 1), DataHandleUtils.getIntFromByteWithBit(var1, 1, 1), DataHandleUtils.getIntFromByteWithBit(var1, 0, 1));
         this.getUiMgr(this.mContext).sendVol(var1);
      } else {
         this.getUiMgr(this.mContext).sendVol(var1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).sendCarType();
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var3 = this.eachId;
      if (var3 == 1 || var3 == 2 || var3 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(2, 33);
      }

      this.getUiMgr(this.mContext).sendIconInfo(0, 0);
      var3 = this.eachId;
      if (var3 == 1 || var3 == 2) {
         this.getUiMgr(this.mContext).sendMediaPalyInfo(var5, var6, 0, 0, var2 / 60, var2 % 60);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(this.mContext).sendCarType();
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void musicDestroy() {
      super.musicDestroy();
      int var1 = this.eachId;
      if (var1 == 1 || var1 == 2 || var1 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(0, 0);
      }

      this.getUiMgr(this.mContext).sendIconInfo(0, 0);
      var1 = this.eachId;
      if (var1 == 1 || var1 == 2) {
         this.getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
      }

   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      int var25 = this.eachId;
      if (var25 == 1 || var25 == 2 || var25 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(8, 17);
      }

      this.getUiMgr(this.mContext).sendIconInfo(0, 0);
      MyLog.e("stoagePath", var1 + "");
      MyLog.e("playRatio", var2 + "");
      MyLog.e("currentPlayingIndexLow", var3 + "");
      MyLog.e("totalCount", var4 + "");
      MyLog.e("currentPlayingIndexHigh", var9 + "");
      MyLog.e("currentPos", var14 + "");
      MyLog.e("playIndex", var17 + "");
      var17 = this.eachId;
      if (var17 == 1 || var17 == 2) {
         this.getUiMgr(this.mContext).sendMediaPalyInfo(this.getLsb(var4), this.getMsb(var4), this.getLsb(var3), this.getMsb(var3), var6, var7);
      }

   }

   public void radioDestroy() {
      super.radioDestroy();
      int var1 = this.eachId;
      if (var1 == 1 || var1 == 2 || var1 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(0, 0);
      }

      this.getUiMgr(this.mContext).sendIconInfo(0, 0);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = this.eachId;
      if (var5 == 1 || var5 == 2 || var5 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(1, 1);
      }

      this.getUiMgr(this.mContext).sendIconInfo(0, 1);
      var5 = this.eachId;
      if (var5 == 1 || var5 == 2) {
         this.getUiMgr(this.mContext).sendRadioInfo(this.getBandAmFM(var2), this.getFreqLsb(var2, var3), this.getFreqMsb(var2, var3), var1);
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void updateAmplifier() {
      this.updateAmplifierActivity((Bundle)null);
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5) {
      SharePreUtil.setIntValue(var1, var2, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var3, var4, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettingsUi(Context var1, int var2, int var3, String var4, int var5) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var2, var3, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }
}
