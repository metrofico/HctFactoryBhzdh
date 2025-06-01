package com.hzbhd.canbus.car._382;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
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

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
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

   private boolean isAirDataChange() {
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

   private void set0x11DoorInfo() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[11]);
      this.updateDoorView(this.mContext);
   }

   private void set0x11LightingInfo() {
      this.setBacklightLevel(this.mCanBusInfoInt[7] / 20 + 1);
   }

   private void set0x11SWC() {
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

   private void set0x11SpeedInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_382_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_382_car_speed"), this.mCanBusInfoInt[3] + "KM/H"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x11TrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 480, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x19Info() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor");
      int var1 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor9");
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var3.add((new SettingUpdateEntity(var2, var1, var4.append((var5[2] << 16) + (var5[3] << 8) + var5[4]).append("km").toString())).setValueStr(true));
      var1 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor");
      var2 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor10");
      var4 = new StringBuilder();
      var5 = this.mCanBusInfoInt;
      var3.add((new SettingUpdateEntity(var1, var2, var4.append((var5[10] << 8) + var5[11]).append("RPM").toString())).setValueStr(true));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x31AirInfo() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
      GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]) ^ true;
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 3) {
               if (var1 != 5) {
                  if (var1 != 6) {
                     switch (var1) {
                        case 11:
                           GeneralAirData.front_left_blow_window = false;
                           GeneralAirData.front_left_blow_foot = false;
                           GeneralAirData.front_left_blow_head = false;
                           GeneralAirData.front_left_auto_wind = true;
                           GeneralAirData.front_right_blow_window = false;
                           GeneralAirData.front_right_blow_foot = false;
                           GeneralAirData.front_right_blow_head = false;
                           GeneralAirData.front_right_auto_wind = true;
                           break;
                        case 12:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_foot = true;
                           GeneralAirData.front_left_blow_head = false;
                           GeneralAirData.front_left_auto_wind = false;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_foot = true;
                           GeneralAirData.front_right_blow_head = false;
                           GeneralAirData.front_right_auto_wind = false;
                           break;
                        case 13:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_foot = false;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_left_auto_wind = false;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_foot = false;
                           GeneralAirData.front_right_blow_head = true;
                           GeneralAirData.front_right_auto_wind = false;
                           break;
                        case 14:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_foot = true;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_left_auto_wind = false;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_foot = true;
                           GeneralAirData.front_right_blow_head = true;
                           GeneralAirData.front_right_auto_wind = false;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_left_blow_foot = false;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_left_auto_wind = false;
                     GeneralAirData.front_right_blow_window = false;
                     GeneralAirData.front_right_blow_foot = false;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_right_auto_wind = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_auto_wind = false;
                  GeneralAirData.front_right_blow_window = false;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_auto_wind = false;
               }
            } else {
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_left_auto_wind = false;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_right_auto_wind = false;
            }
         } else {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_auto_wind = true;
         }
      } else {
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_auto_wind = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_auto_wind = false;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      var1 = this.mCanBusInfoInt[8];
      if (var1 == 254) {
         GeneralAirData.front_left_temperature = "Low";
      } else if (var1 == 255) {
         GeneralAirData.front_left_temperature = "Hi";
      } else {
         GeneralAirData.front_left_temperature = (double)this.mCanBusInfoInt[8] * 0.5 + this.getTempUnitC(this.mContext);
      }

      var1 = this.mCanBusInfoInt[9];
      if (var1 == 254) {
         GeneralAirData.front_right_temperature = "Low";
      } else if (var1 == 255) {
         GeneralAirData.front_right_temperature = "Hi";
      } else {
         GeneralAirData.front_right_temperature = (double)this.mCanBusInfoInt[9] * 0.5 + this.getTempUnitC(this.mContext);
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   private void set0x41RadarInfo() {
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var1[2], var1[3], var1[4], var1[5]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarDistanceData(var1[6], var1[7], var1[8], var1[9]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x46TyresInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_tyres"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_tyres", "_382_car_tyres1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_tyres"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_tyres", "_382_car_tyres2"), this.mCanBusInfoInt[4] + "")).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])).setProgress(this.mCanBusInfoInt[4]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x48SystemInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_auxiliary"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_auxiliary", "_382_car_auxiliary1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x64WindowInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x68LightInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2) - 1)).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light6"), this.mCanBusInfoInt[6])).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])).setProgress(this.mCanBusInfoInt[6]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light7"), this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x71ParkingInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking2"), this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking3"), this.mCanBusInfoInt[5])).setProgress(this.mCanBusInfoInt[5]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking4"), this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking5"), this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xC1UnitInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xC3Info() {
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor1"), this.mCanBusInfoInt[2]));
      if (this.mCanBusInfoInt[4] == 1) {
         var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor7"), "Mile")).setValueStr(true));
      } else {
         var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor7"), "Km")).setValueStr(true));
      }

      int var1 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor");
      int var2 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor2");
      int[] var4 = this.mCanBusInfoInt;
      var3.add((new SettingUpdateEntity(var1, var2, (var4[5] * 256 + var4[6]) * 100)).setValueStr(true));
      var1 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor");
      var2 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor3");
      var4 = this.mCanBusInfoInt;
      var3.add((new SettingUpdateEntity(var1, var2, var4[7] * 256 + var4[8])).setValueStr(true));
      var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor4"), this.mCanBusInfoInt[9] * 100));
      var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor5"), this.mCanBusInfoInt[10]));
      var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1)));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void Toast(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$content;

         {
            this.this$0 = var1;
            this.val$content = var2;
         }

         public void callback() {
            Toast.makeText(this.this$0.mContext, this.val$content, 0).show();
         }
      });
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
      var1 = this.SplicingByte(new byte[]{22, -31, 8}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      System.arraycopy("AUX".getBytes(), 0, var1, 0, "AUX".getBytes().length);
      var1 = this.SplicingByte(new byte[]{22, -31, 12}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), var1);
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      byte[] var4 = new byte[12];
      Arrays.fill(var4, (byte)32);
      System.arraycopy("BtMusic".getBytes(), 0, var4, 0, "BtMusic".getBytes().length);
      var4 = this.SplicingByte(new byte[]{22, -31, 11}, var4);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), var4);
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      switch (var3[1]) {
         case 17:
            this.set0x11SpeedInfo();
            this.set0x11SWC();
            this.set0x11LightingInfo();
            this.set0x11TrackInfo();
            this.set0x11DoorInfo();
            this.updateSpeedInfo(this.mCanBusInfoInt[3]);
            break;
         case 25:
            this.set0x19Info();
            break;
         case 49:
            this.set0x31AirInfo();
            break;
         case 65:
            this.set0x41RadarInfo();
            break;
         case 70:
            this.set0x46TyresInfo();
            break;
         case 72:
            this.set0x48SystemInfo();
            break;
         case 100:
            this.set0x64WindowInfo();
            break;
         case 104:
            this.set0x68LightInfo();
            break;
         case 113:
            this.set0x71ParkingInfo();
            break;
         case 193:
            this.set0xC1UnitInfo();
            break;
         case 195:
            this.set0xC3Info();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      short var14 = 128;
      if (var12) {
         var14 = 0;
      }

      MyLog.temporaryTracking("bYearTotal:" + var1);
      this.getUiMgr(this.mContext).send0xCBTimeInfo(var14, var5, var6, 128, 1, var10, var1 - 2000 + 208, var3, var4, 2);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte[] var14 = (String.format((new DecimalFormat("000")).format((long)var4)) + "         ").getBytes();
      var14 = this.SplicingByte(new byte[]{22, -31, 6}, var14);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var14);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = (new DecimalFormat("00")).format((long)var6);
      var12 = (new DecimalFormat("00")).format((long)var7);
      var11 = String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " " + var11 + ":" + var12 + "   ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      byte[] var25 = var11.getBytes();
      var25 = this.SplicingByte(new byte[]{22, -31, var1}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var25);
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
      var8 = this.SplicingByte(new byte[]{22, -31, var6}, var8);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var8);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
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
      var18 = this.SplicingByte(new byte[]{22, -31, var1}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var18);
   }
}
