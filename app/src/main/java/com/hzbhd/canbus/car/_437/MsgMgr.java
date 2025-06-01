package com.hzbhd.canbus.car._437;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
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
   int[] mDoorData;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;

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

   private String getOutTemperature() {
      int[] var1 = this.mCanBusInfoInt;
      if (this.getMsbLsbResult(var1[2], var1[3]) > 200) {
         StringBuilder var4 = new StringBuilder();
         var1 = this.mCanBusInfoInt;
         return var4.append((double)this.getMsbLsbResult(var1[2], var1[3]) * 0.5 - 32768.0).append(this.getTempUnitC(this.mContext)).toString();
      } else {
         StringBuilder var3 = new StringBuilder();
         int[] var2 = this.mCanBusInfoInt;
         return var3.append((double)this.getMsbLsbResult(var2[2], var2[3]) * 0.5).append(this.getTempUnitC(this.mContext)).toString();
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

   private boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
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

   private boolean isDoorDataNoChange() {
      if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mDoorData = this.mCanBusInfoInt;
         return false;
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

   private void set0x03CarInfo() {
      if (this.isBasicInfoChange()) {
         this.updateOutDoorTemp(this.mContext, this.getOutTemperature());
         DecimalFormat var4 = this.df_2Integer;
         int[] var3 = this.mCanBusInfoInt;
         this.updateSpeedInfo(Integer.parseInt(var4.format((double)this.getMsbLsbResult(var3[6], var3[7]) * 0.1)));
         ArrayList var7 = new ArrayList();
         var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_437_function_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_437_function_1"), this.getOutTemperature()));
         int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_437_function_0");
         int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_437_function_2");
         StringBuilder var8 = new StringBuilder();
         int[] var5 = this.mCanBusInfoInt;
         var7.add(new DriverUpdateEntity(var2, var1, var8.append(this.getMsbLsbResult(var5[4], var5[5])).append("KM").toString()));
         var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_437_function_0");
         var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_437_function_3");
         StringBuilder var9 = new StringBuilder();
         var4 = this.df_2Decimal;
         int[] var6 = this.mCanBusInfoInt;
         var7.add(new DriverUpdateEntity(var1, var2, var9.append(var4.format((double)this.getMsbLsbResult(var6[6], var6[7]) * 0.1)).append("KM/H").toString()));
         var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_437_function_0");
         var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_437_function_4");
         StringBuilder var10 = new StringBuilder();
         var4 = this.df_2Decimal;
         var5 = this.mCanBusInfoInt;
         var7.add(new DriverUpdateEntity(var2, var1, var10.append(var4.format((double)this.getMsbLsbResult(var5[8], var5[9]) * 0.1)).append("L").toString()));
         this.updateGeneralDriveData(var7);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void set0x12AuxState() {
      if (this.mCanBusInfoInt[2] == 1) {
         CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.ORIGAUX, SourceConstantsDef.DISP_ID.disp_main, (Bundle)null);
         this.getUiMgr(this.mContext).issueAux(true);
      } else {
         CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.ORIGAUX, SourceConstantsDef.DISP_ID.disp_main);
         this.getUiMgr(this.mContext).issueAux(false);
      }

   }

   private void set0x20SWC() {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[3];
      if (var2 != 2) {
         int var1 = var3[2];
         if (var1 != 70) {
            switch (var1) {
               case 0:
                  this.realKeyLongClick1(this.mContext, 0, var2);
                  break;
               case 1:
                  this.realKeyLongClick1(this.mContext, 7, var2);
                  break;
               case 2:
                  this.realKeyLongClick1(this.mContext, 8, var2);
                  break;
               case 3:
                  this.realKeyLongClick1(this.mContext, 46, var2);
                  break;
               case 4:
                  this.realKeyLongClick1(this.mContext, 45, var2);
                  break;
               case 5:
                  this.realKeyLongClick1(this.mContext, 188, var2);
                  break;
               case 6:
                  this.realKeyLongClick1(this.mContext, 3, var2);
                  break;
               case 7:
                  this.realKeyLongClick1(this.mContext, 2, var2);
                  break;
               case 8:
                  this.realKeyLongClick1(this.mContext, 187, var2);
                  break;
               case 9:
                  this.realKeyLongClick1(this.mContext, 128, var2);
                  break;
               case 10:
                  this.realKeyLongClick1(this.mContext, 33, var2);
                  break;
               case 11:
                  this.realKeyLongClick1(this.mContext, 34, var2);
                  break;
               case 12:
                  this.realKeyLongClick1(this.mContext, 35, var2);
                  break;
               case 13:
                  this.realKeyLongClick1(this.mContext, 36, var2);
                  break;
               case 14:
                  this.realKeyLongClick1(this.mContext, 37, var2);
                  break;
               case 15:
                  this.realKeyLongClick1(this.mContext, 38, var2);
                  break;
               case 16:
                  this.realKeyLongClick1(this.mContext, 39, var2);
                  break;
               case 17:
                  this.realKeyLongClick1(this.mContext, 40, var2);
                  break;
               default:
                  switch (var1) {
                     case 41:
                        if (var2 == 0) {
                           return;
                        }

                        this.realKeyClick4(this.mContext, 47);
                        break;
                     case 42:
                        if (var2 == 0) {
                           return;
                        }

                        this.realKeyClick4(this.mContext, 48);
                        break;
                     case 43:
                        this.realKeyLongClick1(this.mContext, 21, var2);
                        break;
                     case 44:
                        this.realKeyLongClick1(this.mContext, 20, var2);
                        break;
                     case 45:
                        this.realKeyLongClick1(this.mContext, 45, var2);
                        break;
                     case 46:
                        this.realKeyLongClick1(this.mContext, 46, var2);
                        break;
                     case 47:
                        this.realKeyLongClick1(this.mContext, 49, var2);
                        break;
                     case 48:
                        this.realKeyLongClick1(this.mContext, 59, var2);
                        break;
                     case 49:
                        this.realKeyLongClick1(this.mContext, 62, var2);
                        break;
                     case 50:
                        this.realKeyLongClick1(this.mContext, 52, var2);
                        break;
                     case 51:
                        this.realKeyLongClick1(this.mContext, 14, var2);
                        break;
                     case 52:
                        this.realKeyLongClick1(this.mContext, 128, var2);
                        break;
                     case 53:
                        this.realKeyLongClick1(this.mContext, 50, var2);
                  }
            }
         } else {
            this.realKeyLongClick1(this.mContext, 58, var2);
         }

      }
   }

   private void set0x21AirInfo() {
      if (!this.isAirDataNoChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         int var1 = this.mCanBusInfoInt[5];
         if (var1 == 0) {
            GeneralAirData.front_left_temperature = "LOW";
         } else if (var1 == 31) {
            GeneralAirData.front_left_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = (double)this.mCanBusInfoInt[5] * 0.5 + 15.5 + this.getTempUnitC(this.mContext);
         }

         var1 = this.mCanBusInfoInt[6];
         if (var1 == 0) {
            GeneralAirData.front_right_temperature = "LOW";
         } else if (var1 == 31) {
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_right_temperature = (double)this.mCanBusInfoInt[6] * 0.5 + 15.5 + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 3);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void set0x22RearRadar() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x23FrontRadar() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x24BasicInfo() {
      int[] var1 = this.mCanBusInfoInt;
      var1[3] = 0;
      if (DataHandleUtils.getBoolBit0(var1[2])) {
         if (!this.isDoorDataNoChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            this.updateDoorView(this.mContext);
         }
      }
   }

   private void set0x29EspInfo() {
      if (this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 5400, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
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

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 3) {
         if (var3 != 18) {
            if (var3 != 41) {
               if (var3 != 48) {
                  switch (var3) {
                     case 32:
                        this.set0x20SWC();
                        break;
                     case 33:
                        this.set0x21AirInfo();
                        break;
                     case 34:
                        this.set0x22RearRadar();
                        break;
                     case 35:
                        this.set0x23FrontRadar();
                        break;
                     case 36:
                        this.set0x24BasicInfo();
                  }
               } else {
                  this.set0x30VersionInfo();
               }
            } else {
               this.set0x29EspInfo();
            }
         } else {
            this.set0x12AuxState();
         }
      } else {
         this.set0x03CarInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(this.mContext).makeConnect();
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
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
