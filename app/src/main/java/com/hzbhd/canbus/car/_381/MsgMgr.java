package com.hzbhd.canbus.car._381;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   int firstEnable = 0;
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

   private void Air_0x73() {
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void Lighting_0x72() {
      int var1 = this.mCanBusInfoInt[5];
      if (var1 != 0 || this.firstEnable == 0) {
         this.firstEnable = 1;
         this.setBacklightLevel(var1 / 20 + 1);
      }
   }

   private void Radar_0x72() {
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var1[8], var1[9], var1[10], var1[11]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarDistanceData(var1[12], var1[13], var1[14], var1[15]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void SMC_0x72() {
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

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void Track_0x72() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[6];
      if (var1 != 0) {
         GeneralParkData.trackAngle = -var1 / 10;
      } else {
         GeneralParkData.trackAngle = var2[7] / 10;
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void Version_0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
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

   private String getTemperature(int var1) {
      if (var1 == 0) {
         return "LOW";
      } else {
         return var1 == 0 ? "HI" : var1 + this.getTempUnitC(this.mContext);
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

   private void sendAsciiInfo(int var1, int var2, String var3) {
      byte[] var6 = (" " + var3 + "             ").getBytes(StandardCharsets.UTF_8);
      byte var5 = 0;
      byte var4 = (byte)var2;
      byte[] var7 = new byte[var1];

      for(var2 = var5; var2 < var1; ++var2) {
         var7[var2] = var6[var2];
      }

      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, var4}, var7));
   }

   private int sixteenToTen(String var1) {
      this.updateParkUi((Bundle)null, this.mContext);
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

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      System.arraycopy("TV".getBytes(), 0, var1, 0, "TV".getBytes().length);
      var1 = this.SplicingByte(new byte[]{22, -46, 8}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), var1);
      this.sendAsciiInfo(13, 226, "Atv Playing");
      this.sendAsciiInfo(13, 227, "Device:TV");
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      System.arraycopy("AUX".getBytes(), 0, var1, 0, "AUX".getBytes().length);
      var1 = this.SplicingByte(new byte[]{22, -46, 12}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), var1);
      this.sendAsciiInfo(13, 226, "Aux Playing");
      this.sendAsciiInfo(13, 227, "Device:Aux");
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      byte[] var4 = new byte[12];
      Arrays.fill(var4, (byte)32);
      System.arraycopy("BtMusic".getBytes(), 0, var4, 0, "BtMusic".getBytes().length);
      var4 = this.SplicingByte(new byte[]{22, -46, 11}, var4);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), var4);
      this.sendAsciiInfo(13, 226, "IPOD Playing");
      this.sendAsciiInfo(13, 227, "Device:IPOD");
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
         if (var3 != 115) {
            if (var3 == 240) {
               this.Version_0xF0();
            }
         } else {
            this.Air_0x73();
         }
      } else {
         this.SMC_0x72();
         this.Lighting_0x72();
         this.Track_0x72();
         this.Radar_0x72();
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte[] var14 = (String.format((new DecimalFormat("000")).format((long)var4)) + "         ").getBytes();
      var14 = this.SplicingByte(new byte[]{22, -46, 6}, var14);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var14);
      this.sendAsciiInfo(13, 226, "Disc Playing");
      this.sendAsciiInfo(13, 227, "Device:CD");
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var12 = (new DecimalFormat("00")).format((long)var6);
      var11 = (new DecimalFormat("00")).format((long)var7);
      var11 = String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " " + var12 + ":" + var11 + "   ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      byte[] var25 = var11.getBytes();
      var25 = this.SplicingByte(new byte[]{22, -46, var1}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var25);
      this.sendAsciiInfo(13, 226, "MusicPlaying");
      this.sendAsciiInfo(13, 227, "Device:USB");
   }

   public void panoramicSwitch(boolean var1) {
      this.forceReverse(this.mContext, var1);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = var1;
      if (var1 > 6) {
         var5 = 0;
      }

      byte var7 = CommUtil.getRadioCurrentBand(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         if (var3.length() == 4) {
            var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3 + "  Khz";
         } else {
            var2 = (new DecimalFormat("00")).format((long)var5) + "  " + var3 + "  Khz";
         }
      } else if (var3.length() == 5) {
         var2 = (new DecimalFormat("00")).format((long)var5) + "  " + var3 + "Mhz";
      } else {
         var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3 + "Mhz";
      }

      byte var6 = (byte)var7;
      byte[] var8 = var2.getBytes();
      var8 = this.SplicingByte(new byte[]{22, -46, var6}, var8);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var8);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         this.sendAsciiInfo(13, 226, " ");
         this.sendAsciiInfo(13, 227, " ");
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
      var8 = String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + "         ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      byte[] var18 = var8.getBytes();
      var18 = this.SplicingByte(new byte[]{22, -46, var1}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var18);
      this.sendAsciiInfo(13, 226, "VideoPlaying");
      this.sendAsciiInfo(13, 227, "Device:USB");
   }
}
