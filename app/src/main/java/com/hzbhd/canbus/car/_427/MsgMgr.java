package com.hzbhd.canbus.car._427;

import android.content.Context;
import android.os.Bundle;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private int nowBackLight = 100;
   int nowData7 = 0;

   private void DoorInfo() {
      int var2 = this.nowData7;
      int var1 = this.mCanBusInfoInt[9];
      if (var2 != var1) {
         this.nowData7 = var1;
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var1);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
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

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getTemperature(int var1, int var2, int var3) {
      if (var1 == var2) {
         return "LO";
      } else {
         return var1 == var3 ? "HI" : var1 + this.getTempUnitC(this.mContext);
      }
   }

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isNotAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
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
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
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
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void setAirInfo0x73() {
      this.DoorInfo();
      int[] var1 = this.mCanBusInfoInt;
      var1[9] = 0;
      if (DataHandleUtils.getBoolBit7(var1[8])) {
         this.updateOutDoorTemp(this.mContext, this.df_2Decimal.format((long)(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7) - 40)));
      }

      this.mCanBusInfoInt[8] = 0;
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4], 1, 255);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5], 1, 255);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void setBackLight() {
      int var1 = this.mCanBusInfoInt[5];
      if (var1 != this.nowBackLight) {
         this.nowBackLight = var1;
         this.setBacklightLevel(var1 / 20 + 1);
      }
   }

   private void setRadar0x72() {
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var1[8], var1[9], var1[10], var1[11]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarDistanceData(var1[12], var1[13], var1[14], var1[15]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSpeed0x72() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_427_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_427_drive1"), this.mCanBusInfoInt[3] + "km/h"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSwc0x72() {
      switch (this.mCanBusInfoInt[4]) {
         case 0:
            this.realKeyClick4(this.mContext, 0);
            break;
         case 1:
            this.realKeyClick4(this.mContext, 7);
            break;
         case 2:
            this.realKeyClick4(this.mContext, 8);
            break;
         case 3:
            this.realKeyClick4(this.mContext, 3);
         case 4:
         case 7:
         default:
            break;
         case 5:
            this.realKeyClick4(this.mContext, 14);
            break;
         case 6:
            this.realKeyClick4(this.mContext, 15);
            break;
         case 8:
            this.realKeyClick4(this.mContext, 45);
            break;
         case 9:
            this.realKeyClick4(this.mContext, 46);
            break;
         case 10:
            this.realKeyClick4(this.mContext, 2);
      }

   }

   private void setTrack0x72() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[7];
      if (var1 == 0) {
         GeneralParkData.trackAngle = -var2[8] / 6;
      } else {
         GeneralParkData.trackAngle = var1 / 6;
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      String var2 = "AUX Playing";

      for(int var1 = 0; var1 < 1; ++var1) {
         var2 = var2 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0xD2(12, var2.getBytes());
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      String var10 = "BtPhone";

      for(var1 = 0; var1 < 5; ++var1) {
         var10 = var10 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0xD2(10, var10.getBytes());
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
      if (var3 != 114) {
         if (var3 == 115) {
            this.setAirInfo0x73();
         }
      } else {
         this.setSpeed0x72();
         this.setSwc0x72();
         this.setBackLight();
         this.setTrack0x72();
         this.setRadar0x72();
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      String var2 = "DTV Playing";

      for(int var1 = 0; var1 < 1; ++var1) {
         var2 = var2 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0xD2(8, var2.getBytes());
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = var5 + var12 + var13;
      var4 = var11.length();

      for(var3 = 0; var3 < 12 - var4; ++var3) {
         var11 = var11 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0xD2(13, var11.getBytes());
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      boolean var7 = var2.equals("FM1");
      byte var8 = 1;
      if (var7) {
         var2 = var3 + "MHz";
      } else if (var2.equals("FM2")) {
         var8 = 2;
         var2 = var3 + "MHz";
      } else if (var2.equals("FM3")) {
         var8 = 3;
         var2 = var3 + "MHz";
      } else if (var2.equals("AM1")) {
         var8 = 4;
         var2 = var3 + "KHz";
      } else if (var2.equals("AM2")) {
         var8 = 5;
         var2 = var3 + "KHz";
      } else {
         var2 = "nothing";
      }

      int var6 = var2.length();

      for(var5 = 0; var5 < 12 - var6; ++var5) {
         var2 = var2 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0xD2(var8, var2.getBytes());
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
      var8 = var5 + var12 + var13;
      var4 = var8.length();

      for(var3 = 0; var3 < 12 - var4; ++var3) {
         var8 = var8 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0xD2(13, var8.getBytes());
   }
}
