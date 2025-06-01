package com.hzbhd.canbus.car._4;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._333.AlertView;
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
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.ui.util.BaseUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class MsgMgr extends AbstractMsgMgr {
   public static int bandBassTag;
   public static int bandMiddleTag;
   public static int bandTrebleTag;
   public static int frontRearTag;
   public static int leftRightTag;
   int[] OutGoingPhoneNumber;
   int alarmInfo1;
   int alarmInfo2;
   int carDoorInt;
   int[] comingPhoneNumber;
   DecimalFormat df = new DecimalFormat("#0");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int[] mAirData;
   int[] mBackLightInt;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mLeftRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mRightRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   int[] now0x41;
   private int[] talkingPhoneNumber;

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

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
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

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getP_GearState(boolean var1) {
      return var1 ? this.mContext.getString(2131759703) : this.mContext.getString(2131759702);
   }

   private String getReversingState(boolean var1) {
      return var1 ? this.mContext.getString(2131759718) : this.mContext.getString(2131759719);
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

   private UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), this.getUigMgr(var1).getAmplifierPageUiSet(var1));
      }

      byte var5 = (byte)GeneralAmplifierData.leftRight;
      byte var3 = (byte)GeneralAmplifierData.frontRear;
      byte[] var7 = new byte[]{22, -96, 3, (byte)(GeneralAmplifierData.bandBass - 9)};
      byte var4 = (byte)(GeneralAmplifierData.bandMiddle - 9);
      byte var2 = (byte)(GeneralAmplifierData.bandTreble - 9);
      TimerUtil var6 = new TimerUtil();
      var6.startTimer(new TimerTask(this, new byte[][]{{22, -96, 1, var5}, {22, -96, 2, var3}, var7, {22, -96, 3, var4}, {22, -96, 3, var2}}, var6) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$commands;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$commands = var2;
            this.val$util = var3;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$commands;
            if (var1 < var2.length) {
               this.i = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.val$util.stopTimer();
            }

         }
      }, 0L, 100L);
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

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isLeftRadarDataChange() {
      if (Arrays.equals(this.mLeftRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mLeftRadarData = Arrays.copyOf(var1, var1.length);
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

   private boolean isRightRadarDataChange() {
      if (Arrays.equals(this.mRightRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRightRadarData = Arrays.copyOf(var1, var1.length);
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

   private void resetImplVolume() {
      BaseUtil.INSTANCE.runBack(new Function0(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public Unit invoke() {
            int var2 = GeneralAmplifierData.volume;
            int var1 = var2;
            if (var2 == 0) {
               var1 = 15;
            }

            for(var2 = 0; var2 <= var1; ++var2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 0, (byte)var2});

               try {
                  Thread.sleep(100L);
               } catch (InterruptedException var4) {
                  var4.printStackTrace();
               }
            }

            return null;
         }
      });
   }

   private void set0x14BackLightInfo() {
      if (!this.isBackLightIntChange()) {
         this.adjustBrightness();
      }
   }

   private void set0x16SpeedInfo() {
      ArrayList var5 = new ArrayList();
      int var2 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      int var1 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info2");
      StringBuilder var4 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var2, var1, var4.append(this.getMsbLsbResult(var3[3], var3[2]) / 16).append("km/h").toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
      DecimalFormat var6 = this.df_2Integer;
      var3 = this.mCanBusInfoInt;
      this.updateSpeedInfo(Integer.parseInt(var6.format((double)(var3[5] * 256 + var3[6]) * 0.01)));
   }

   private void set0x20WheelKeyInfo() {
      switch (this.mCanBusInfoInt[2]) {
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
            if (this.getCurrentEachCanId() == 1) {
               this.buttonKey(45);
            } else {
               this.buttonKey(46);
            }
            break;
         case 4:
            if (this.getCurrentEachCanId() == 1) {
               this.buttonKey(46);
            } else {
               this.buttonKey(45);
            }
            break;
         case 5:
            this.buttonKey(14);
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

   }

   private void set0x21AirInfo() {
      int[] var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 4);
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4], 0.5, 17.5, "C", 0, 31);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5], 0.5, 17.5, "C", 0, 31);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void set0x22RearRadarInfo() {
      if (!this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x23FrontRadarInfo() {
      if (!this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x24BaseInfo() {
      if (!this.isBasicInfoChange()) {
         ArrayList var1 = new ArrayList();
         var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info4"), this.getSwitchState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info5"), this.getReversingState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info10"), this.getP_GearState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
         this.adjustBrightness();
      }
   }

   private void set0x25ParkingInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), 0, this.getSwitchState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      GeneralParkData.pKeyRadarState = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      this.updatePKeyRadar();
   }

   private void set0x26EspInfo() {
      if (!this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 540, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x27AmplifierInfo(Context var1) {
      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1004_amp"), 0, this.mCanBusInfoInt[9])).setProgress(this.mCanBusInfoInt[9]));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      GeneralAmplifierData.volume = this.mCanBusInfoInt[3];
      if (leftRightTag == -1) {
         GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] - 256;
      } else {
         GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4];
      }

      if (frontRearTag == -1) {
         GeneralAmplifierData.frontRear = this.mCanBusInfoInt[5] - 256;
      } else {
         GeneralAmplifierData.frontRear = this.mCanBusInfoInt[5];
      }

      if (bandBassTag == -1) {
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6] - 247;
      } else {
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6] + 9;
      }

      if (bandMiddleTag == -1) {
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7] - 247;
      } else {
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7] + 9;
      }

      if (bandTrebleTag == -1) {
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[8] - 247;
      } else {
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[8] + 9;
      }

      this.saveAmplifierData(var1, this.getCanId());
      this.updateAmplifierActivity(new Bundle());
   }

   private void set0x28CherInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1004_chair_info"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1004_chair_info", "_1004_chair_info1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 3)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1004_chair_info"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1004_chair_info", "_1004_chair_info2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x32LeftRadarInfo() {
      if (!this.isLeftRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setLeftRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x33RightRadarInfo() {
      if (!this.isRightRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRightRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x41CarInfo() {
      if (!Arrays.equals(this.now0x41, this.mCanBusInfoInt)) {
         int[] var2 = this.mCanBusInfoInt;
         this.now0x41 = var2;
         int var1 = var2[2];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.set0x41CarInfo0x03Data();
               }
            } else {
               this.set0x41CarInfo0x02Data();
            }
         } else {
            this.set0x41CarInfo0x01Data();
         }

      }
   }

   private void set0x41CarInfo0x01Data() {
      if (this.isCarDoorInfoChange()) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ^ true;
         GeneralDoorData.isShowWashingFluidWarning = true;
         GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isShowHandBrake = true;
         GeneralDoorData.isHandBrakeUp = true ^ DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x41CarInfo0x02Data() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      int var1 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13");
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var4.append(var5[3] * 256 + var5[4]).append(" RPM").toString()));
      var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14");
      var4 = new StringBuilder();
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var4.append((double)(var5[5] * 256 + var5[6]) * 0.01).append(" Km/h").toString()));
      var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info15");
      StringBuilder var9 = new StringBuilder();
      int[] var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var9.append((double)(var8[7] * 256 + var8[8]) * 0.01).append(" V").toString()));
      var8 = this.mCanBusInfoInt;
      if ((double)(var8[9] * 256 + var8[10]) * 0.1 > 6000.0) {
         Context var10 = this.mContext;
         var4 = new StringBuilder();
         DecimalFormat var7 = this.df;
         int[] var6 = this.mCanBusInfoInt;
         this.updateOutDoorTemp(var10, var4.append(var7.format((double)(var6[9] * 256 + var6[10]) * 0.1 - 6554.0)).append(this.getTempUnitC(this.mContext)).toString());
      } else {
         Context var11 = this.mContext;
         var4 = new StringBuilder();
         DecimalFormat var12 = this.df;
         int[] var13 = this.mCanBusInfoInt;
         this.updateOutDoorTemp(var11, var4.append(var12.format((double)(var13[9] * 256 + var13[10]) * 0.1)).append(this.getTempUnitC(this.mContext)).toString());
      }

      var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16");
      var4 = new StringBuilder();
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var4.append(var5[11] * 65536 + var5[12] * 256 + var5[13]).append(" Km").toString()));
      var3.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info17"), this.mCanBusInfoInt[14] + " L"));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x41CarInfo0x03Data() {
      boolean var4 = this.isAlarm1InfoChange();
      byte var2 = 0;
      String var5 = "";
      boolean var1;
      if (var4 && DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var5 = "" + "【" + this.mContext.getString(2131759712) + 1 + "：" + this.mContext.getString(2131759709) + "】";
         var1 = true;
         var2 = 1;
      } else {
         var1 = false;
      }

      boolean var3 = var1;
      String var6 = var5;
      if (this.isAlarm2InfoChange()) {
         var3 = var1;
         var6 = var5;
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            var6 = var5 + "【" + this.mContext.getString(2131759712) + (var2 + 1) + "：" + this.mContext.getString(2131759710) + "】";
            var3 = true;
         }
      }

      if (var3) {
         this.showDialog(var6);
      }

   }

   private void showDialog(String var1) {
      this.runOnUi(new CallBackInterface(this, var1) {
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

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUigMgr(this.mContext).sendSourceInfo(7, 48);
      this.getUigMgr(this.mContext).sendIconInfo(0, 0);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getUigMgr(this.mContext).sendSourceInfo(11, 34);
      this.getUigMgr(this.mContext).sendIconInfo(0, 0);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      this.getUigMgr(this.mContext).sendSourceInfo(0, 0);
      this.getUigMgr(this.mContext).sendIconInfo(0, 0);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      MyLog.temporaryTracking("挂断");
      this.comingPhoneNumber = null;
      this.OutGoingPhoneNumber = null;
      this.talkingPhoneNumber = null;
      this.runOnUi(new CallBackInterface(this) {
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
                  this.this$1.this$0.getUigMgr(this.this$1.this$0.mContext).sendPhoneNumber();
               }

               public void onTick(long var1) {
                  MyLog.temporaryTracking("挂断中");
                  this.this$1.this$0.getUigMgr(this.this$1.this$0.mContext).sendPhoneNumber();
               }
            }).start();
         }
      });
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      MyLog.temporaryTracking("来电");
      int[] var4 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var4, this.comingPhoneNumber)) {
         MyLog.temporaryTracking("传 来电");
         this.comingPhoneNumber = var4;
         this.getUigMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 1, 1}, var1));
      }

   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      MyLog.temporaryTracking("拨号");

      int var5;
      for(int var4 = 0; var4 < var1.length; var4 = var5) {
         StringBuilder var6 = (new StringBuilder()).append("号码");
         var5 = var4 + 1;
         MyLog.e(var6.append(var5).toString(), var1[var4] + "");
      }

      int[] var7 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var7, this.OutGoingPhoneNumber)) {
         MyLog.temporaryTracking("传 拨号");
         this.OutGoingPhoneNumber = var7;
         this.getUigMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 2, 1}, var1));
      }

   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      this.getUigMgr(this.mContext).sendSourceInfo(5, 64);
      this.getUigMgr(this.mContext).sendIconInfo(0, 0);
      MyLog.temporaryTracking("通话中");
      int[] var5 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var5, this.talkingPhoneNumber)) {
         MyLog.temporaryTracking("传递 通话中");
         this.talkingPhoneNumber = var5;
         this.getUigMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 4, 1}, var1));
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
                  if (var3 != 50) {
                     if (var3 != 51) {
                        switch (var3) {
                           case 32:
                              this.set0x20WheelKeyInfo();
                              break;
                           case 33:
                              this.set0x21AirInfo();
                              break;
                           case 34:
                              this.set0x22RearRadarInfo();
                              break;
                           case 35:
                              this.set0x23FrontRadarInfo();
                              break;
                           case 36:
                              this.set0x24BaseInfo();
                              break;
                           case 37:
                              this.set0x25ParkingInfo();
                              break;
                           case 38:
                              this.set0x26EspInfo();
                              break;
                           case 39:
                              this.set0x27AmplifierInfo(var1);
                              break;
                           case 40:
                              this.set0x28CherInfo();
                        }
                     } else {
                        this.set0x33RightRadarInfo();
                     }
                  } else {
                     this.set0x32LeftRadarInfo();
                  }
               } else {
                  this.set0x41CarInfo();
               }
            } else {
               this.set0x30VersionInfo();
            }
         } else {
            this.set0x16SpeedInfo();
         }
      } else {
         this.set0x14BackLightInfo();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.getUigMgr(this.mContext).sendVolInfo(var1, var2);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUigMgr(this.mContext).sendSourceInfo(2, 33);
      this.getUigMgr(this.mContext).sendIconInfo(0, 0);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(var5, var6, 0, 0, var2 / 60, var2 % 60);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUigMgr(this.mContext).makeConnection();
      this.initAmplifier(var1);
      this.resetImplVolume();
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.getUigMgr(this.mContext).sendSourceInfo(0, 0);
      this.getUigMgr(this.mContext).sendIconInfo(0, 0);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.getUigMgr(this.mContext).sendSourceInfo(8, 17);
      this.getUigMgr(this.mContext).sendIconInfo(0, 0);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(this.getLsb(var4), this.getMsb(var4), this.getLsb(var3), this.getMsb(var3), var6, var7);
   }

   public void onAccOn() {
      super.onAccOn();
      this.initAmplifier(BaseUtil.INSTANCE.getContext());
      this.resetImplVolume();
   }

   public void radioDestroy() {
      super.radioDestroy();
      this.getUigMgr(this.mContext).sendSourceInfo(0, 0);
      this.getUigMgr(this.mContext).sendIconInfo(0, 0);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.getUigMgr(this.mContext).sendSourceInfo(1, 1);
      this.getUigMgr(this.mContext).sendIconInfo(0, 1);
      this.getUigMgr(this.mContext).sendRadioInfo(this.getBandAmFM(var2), this.getFreqLsb(var2, var3), this.getFreqMsb(var2, var3), var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

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

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      this.getUigMgr(this.mContext).sendSourceInfo(8, 17);
      this.getUigMgr(this.mContext).sendIconInfo(0, 0);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(this.getLsb(var4), this.getMsb(var4), this.getLsb(var3), this.getMsb(var3), var6, var7);
   }
}
