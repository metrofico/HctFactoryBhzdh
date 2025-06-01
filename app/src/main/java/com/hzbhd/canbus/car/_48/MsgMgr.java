package com.hzbhd.canbus.car._48;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.OriginalCarDeviceActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private final String ORGINAL_DEVICE_AM = "AM";
   private final String ORGINAL_DEVICE_AUX = "AUX";
   private final String ORGINAL_DEVICE_FM = "FM";
   private int[] m0x22Data;
   private int[] m0x25Data;
   private int[] m0x29Data;
   private int[] m0x30Data;
   private int[] m0x32Data;
   private int[] m0x33DataIndexOne;
   private int[] m0x33DataIndexTwo;
   private int[] m0xD1Data;
   private int m0xD2Data0;
   private int[] m0xD3Data;
   private int[] m0xD4Data;
   private List mAmPresetFrequencyList;
   private List mAmValidFrequencyList;
   private boolean mBackStatus;
   private int mBsmData;
   boolean mCalibrationStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private DecimalFormat mDecimalFormat0P0;
   private DecimalFormat mDecimalFormat0P00;
   private List mFmPresetFrequencyList;
   private List mFmValidFrequencyList;
   private boolean mFrontStatus;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private boolean mOrginalAuxStatus;
   private OriginalCarDevicePageUiSet mOriDevUiSet;
   private int mOriginalRadioBand = -1;
   private List mPresetList;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private boolean mRightViewStatus;
   private List mValidList;

   private String getBand(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "FM";
      } else {
         var2 = "AM";
      }

      return var2;
   }

   private String getCalibrationStatus() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      this.mCalibrationStatus = var1;
      return var1 ? CommUtil.getStrByResId(this.mContext, "compass_calibrating") : CommUtil.getStrByResId(this.mContext, "compass_calibration_finish");
   }

   private String getData(int[] var1, float var2, String var3) {
      int var4 = 0;

      int var5;
      for(var5 = 0; var4 < var1.length; ++var4) {
         var5 = (int)((double)var5 + (double)var1[var4] * Math.pow(2.0, (double)(var4 * 8)));
      }

      return (double)var5 == Math.pow(2.0, (double)(var1.length * 8)) - 1.0 ? "---" : this.mDecimalFormat0P0.format((double)((float)var5 * var2)) + " " + var3;
   }

   private String getDistanceUnit(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? "" : "mile";
      } else {
         return "km";
      }
   }

   private String getFrequency(int var1, int var2, int var3) {
      float var4 = (float)(var2 | var3 << 8);
      if (var1 == 0) {
         var4 /= 10.0F;
         return this.mDecimalFormat0P00.format((double)var4) + " MHz";
      } else {
         return this.mDecimalFormat0P00.format((double)var4) + " KHz";
      }
   }

   private String getFuelUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : "l/100km";
         } else {
            return "km/l";
         }
      } else {
         return "mpg";
      }
   }

   private int getRange(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? (var1 - 1) * 10 : 12;
         } else {
            return 10;
         }
      } else {
         return 60;
      }
   }

   private void initOriginalDeviceList() {
      ArrayList var1 = new ArrayList();
      this.mFmPresetFrequencyList = var1;
      var1.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_preset_station")));
      this.mFmPresetFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
      var1 = new ArrayList();
      this.mFmValidFrequencyList = var1;
      var1.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_valid_station")));
      this.mFmValidFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
      var1 = new ArrayList();
      this.mAmPresetFrequencyList = var1;
      var1.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_preset_station")));
      this.mAmPresetFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
      var1 = new ArrayList();
      this.mAmValidFrequencyList = var1;
      var1.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_valid_station")));
      this.mAmValidFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
      this.mPresetList = this.mFmPresetFrequencyList;
      this.mValidList = this.mFmValidFrequencyList;
   }

   private boolean is0x22DataChange() {
      if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x25DataChange() {
      if (Arrays.equals(this.m0x25Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x25Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x29DataChange() {
      if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x29Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x30DataChange() {
      if (Arrays.equals(this.m0x30Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x30Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x32DataChange() {
      if (Arrays.equals(this.m0x32Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x32Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x33IndexOneDataChange() {
      if (Arrays.equals(this.m0x33DataIndexOne, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33DataIndexOne = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x33IndexTwoDataChange() {
      if (Arrays.equals(this.m0x33DataIndexTwo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33DataIndexTwo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xD1DataChange() {
      if (Arrays.equals(this.m0xD1Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xD1Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xD2Data0Change() {
      int var2 = this.m0xD2Data0;
      int var1 = this.mCanBusInfoInt[2];
      if (var2 == var1) {
         return false;
      } else {
         this.m0xD2Data0 = var1;
         return true;
      }
   }

   private boolean is0xD3DataChange() {
      if (Arrays.equals(this.m0xD3Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xD3Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xD4DataChange() {
      if (Arrays.equals(this.m0xD4Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xD4Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isBsmDataChange() {
      if (this.mBsmData == DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)) {
         return false;
      } else {
         this.mBsmData = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen && GeneralDoorData.skyWindowOpenLevel == 0) {
            return true;
         }
      }

      return false;
   }

   private void realKeyLongClick1(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void refreshStationList(List var1) {
      StringBuilder var2;
      if (this.mCanBusInfoInt[7] + 1 < var1.size()) {
         SongListEntity var3 = (SongListEntity)var1.get(this.mCanBusInfoInt[7] + 1);
         var2 = (new StringBuilder()).append("\t\t");
         int[] var4 = this.mCanBusInfoInt;
         var3.setTitle(var2.append(this.getFrequency(var4[3], var4[4], var4[5])).toString());
      } else {
         var2 = (new StringBuilder()).append("\t\t");
         int[] var5 = this.mCanBusInfoInt;
         var1.add(new SongListEntity(var2.append(this.getFrequency(var5[3], var5[4], var5[5])).toString()));
      }

   }

   private String resolveAirTemp(int var1) {
      String var4;
      if (var1 == 0) {
         var4 = "LO";
      } else if (var1 == 255) {
         var4 = "HI";
      } else {
         StringBuilder var6 = new StringBuilder();
         float var3 = (float)var1;
         float var2;
         if (GeneralAirData.fahrenheit_celsius) {
            var2 = 1.0F;
         } else {
            var2 = 0.5F;
         }

         StringBuilder var5 = var6.append(var3 * var2);
         if (GeneralAirData.fahrenheit_celsius) {
            var4 = this.getTempUnitF(this.mContext);
         } else {
            var4 = this.getTempUnitC(this.mContext);
         }

         var4 = var5.append(var4).toString();
      }

      return var4;
   }

   private void setAirData0x21() {
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralAirData.manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[5]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCameraStatusData0xD2() {
      boolean var2;
      ArrayList var4;
      if (this.is0xD2Data0Change()) {
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
         var4 = new ArrayList();
         boolean var3 = true;
         if (var1 == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(0, var2));
         if (var1 == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(1, var2));
         if (var1 == 2) {
            var2 = var3;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(2, var2));
         GeneralParkData.dataList = var4;
         this.updateParkUi((Bundle)null, this.mContext);
      }

      if (this.mRightViewStatus ^ DataHandleUtils.getBoolBit7(this.mCanBusInfoByte[3])) {
         var2 = DataHandleUtils.getBoolBit7(this.mCanBusInfoByte[3]);
         this.mRightViewStatus = var2;
         this.switchFCamera(this.mContext, var2);
      }

      if (this.isBsmDataChange()) {
         var4 = new ArrayList();
         var4.add(new SettingUpdateEntity(6, 0, this.mBsmData));
         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setCompassData0xD4() {
      if (this.is0xD4DataChange()) {
         ArrayList var2 = new ArrayList();
         var2.add((new SettingUpdateEntity(5, 0, this.getCalibrationStatus())).setValueStr(true));
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
         var2.add((new SettingUpdateEntity(5, 2, var1)).setProgress(var1 - 1));
         var2.add((new SettingUpdateEntity(5, 3, this.mCanBusInfoInt[3] * 3 / 2 + CommUtil.getStrByResId(this.mContext, "unit_degree"))).setValueStr(true));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setDoorData0x24() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = var1;
      this.mRightFrontRec = var1;
      var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = var1;
      this.mLeftFrontRec = var1;
      var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = var1;
      this.mRightRearRec = var1;
      var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = var1;
      this.mLeftRearRec = var1;
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

      if (this.mOrginalAuxStatus != DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         var1 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         this.mOrginalAuxStatus = var1;
         if (var1) {
            this.enterAuxIn2();
         } else {
            if (SystemUtil.isForeground(this.mContext, OriginalCarDeviceActivity.class.getName())) {
               this.realKeyClick(this.mContext, 52);
            }

            this.exitAuxIn2();
         }
      }

   }

   private void setFuelData0x33() {
      int var2 = this.mCanBusInfoInt[2];
      String var6;
      int[] var7;
      int[] var12;
      if (var2 != 1) {
         if (var2 == 2 && this.is0x33IndexTwoDataChange()) {
            String var5 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 4, 2));
            var6 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 6, 1));
            ArrayList var4 = new ArrayList();
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 1, this.getData(new int[]{var7[5], var7[4], var7[3]}, 0.1F, var6)));
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 2, this.getData(new int[]{var7[7], var7[6]}, 0.1F, var5)));
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 4, this.getData(new int[]{var7[10], var7[9], var7[8]}, 0.1F, var6)));
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 5, this.getData(new int[]{var7[12], var7[11]}, 0.1F, var5)));
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 7, this.getData(new int[]{var7[15], var7[14], var7[13]}, 0.1F, var6)));
            var12 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 8, this.getData(new int[]{var12[17], var12[16]}, 0.1F, var5)));
            this.updateGeneralDriveData(var4);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else if (this.is0x33IndexOneDataChange()) {
         String var9 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 2));
         String var8 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 2, 2));
         String var14 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 4, 2));
         var6 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1));
         String var10 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1));
         int var3 = this.getRange(this.mCanBusInfoInt[16]);
         ArrayList var11 = new ArrayList();
         var2 = this.mCanBusInfoInt[3];
         float var1 = (float)var3;
         var11.add(new DriverUpdateEntity(0, 0, this.getData(new int[]{var2}, var1 * 0.04761905F, var9)));
         int[] var15 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 1, this.getData(new int[]{var15[5], var15[4]}, 0.1F, var8)));
         var15 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 2, this.getData(new int[]{var15[7], var15[6]}, 0.1F, var8)));
         int[] var13 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 3, this.getData(new int[]{var13[9], var13[8]}, 0.1F, var14)));
         var7 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 4, this.getData(new int[]{var7[12], var7[11], var7[10]}, 0.1F, var6)));
         var12 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 5, this.getData(new int[]{var12[14], var12[13]}, 1.0F, var10)));
         this.updateGeneralDriveData(var11);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setOriginalRadioData0xD1() {
      if (this.is0xD1DataChange()) {
         boolean var2;
         if (this.mOriginalRadioBand != this.mCanBusInfoInt[3]) {
            var2 = true;
         } else {
            var2 = false;
         }

         GeneralOriginalCarDeviceData.isSongListChange = var2;
         int var1 = this.mCanBusInfoInt[3];
         this.mOriginalRadioBand = var1;
         if (var1 > 3) {
            this.mPresetList = this.mAmPresetFrequencyList;
            this.mValidList = this.mAmValidFrequencyList;
            GeneralOriginalCarDeviceData.cdStatus = "AM";
         } else {
            this.mPresetList = this.mFmPresetFrequencyList;
            this.mValidList = this.mFmValidFrequencyList;
            GeneralOriginalCarDeviceData.cdStatus = "FM";
         }

         var1 = this.mCanBusInfoInt[2];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.refreshStationList(this.mPresetList);
                  GeneralOriginalCarDeviceData.isSongListChange = true;
               }
            } else {
               this.refreshStationList(this.mValidList);
               GeneralOriginalCarDeviceData.isSongListChange = true;
            }
         } else {
            ArrayList var3 = new ArrayList();
            var3.add(new OriginalCarDeviceUpdateEntity(0, this.getBand(this.mCanBusInfoInt[3])));
            var3.add(new OriginalCarDeviceUpdateEntity(1, "P" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)));
            int[] var4 = this.mCanBusInfoInt;
            var3.add(new OriginalCarDeviceUpdateEntity(2, this.getFrequency(var4[3], var4[4], var4[5])));
            GeneralOriginalCarDeviceData.mList = var3;
         }

         GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralOriginalCarDeviceData.refresh = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralOriginalCarDeviceData.songList.clear();
         GeneralOriginalCarDeviceData.songList.addAll(this.mPresetList);
         GeneralOriginalCarDeviceData.songList.addAll(this.mValidList);
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setParkAssistData0x25() {
      if (this.is0x25DataChange()) {
         ArrayList var2 = new ArrayList();
         Context var3 = this.mContext;
         String var1;
         if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            var1 = "open";
         } else {
            var1 = "close";
         }

         var2.add(new DriverUpdateEntity(2, 0, CommUtil.getStrByResId(var3, var1)));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setRearRadarData0x22() {
      if (this.is0x22DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setScreenData0xD3() {
      if (this.is0xD3DataChange()) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(4, 0, this.mCanBusInfoInt[2]));
         var1.add((new SettingUpdateEntity(4, 1, this.mCanBusInfoInt[3] - 5)).setProgress(this.mCanBusInfoInt[3]));
         var1.add((new SettingUpdateEntity(4, 2, this.mCanBusInfoInt[4] - 5)).setProgress(this.mCanBusInfoInt[4]));
         var1.add((new SettingUpdateEntity(4, 3, this.mCanBusInfoInt[5] - 5)).setProgress(this.mCanBusInfoInt[5]));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setTrackData0x29() {
      if (this.is0x29DataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVehicleSetupData0x32() {
      if (this.is0x32DataChange()) {
         ArrayList var2;
         label24: {
            var2 = new ArrayList();
            int var1 = this.mCanBusInfoInt.length;
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        break label24;
                     }

                     var2.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
                     var2.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
                     var2.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
                     var2.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
                     var2.add(new SettingUpdateEntity(3, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
                     var2.add(new SettingUpdateEntity(3, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)));
                     var2.add(new SettingUpdateEntity(3, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)));
                  }

                  var2.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
                  var2.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
                  var2.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
                  var2.add(new SettingUpdateEntity(2, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)));
                  var2.add(new SettingUpdateEntity(2, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2)));
               }

               var2.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
               var2.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3)));
               var2.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2)));
               var2.add(new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
            }

            var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
            var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)));
            var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)));
            var2.add((new SettingUpdateEntity(0, 2, var1 - 5)).setProgress(var1));
         }

         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setVersionInfo0x30() {
      if (this.is0x30DataChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWheelKey0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 23) {
                     switch (var1) {
                        case 7:
                           this.realKeyLongClick1(2);
                           break;
                        case 8:
                           this.realKeyLongClick1(187);
                           break;
                        case 9:
                           this.realKeyLongClick1(14);
                           break;
                        case 10:
                           this.realKeyLongClick1(189);
                     }
                  } else {
                     this.realKeyLongClick1(152);
                  }
               } else {
                  this.realKeyLongClick1(45);
               }
            } else {
               this.realKeyLongClick1(46);
            }
         } else {
            this.realKeyLongClick1(8);
         }
      } else {
         this.realKeyLongClick1(7);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 36) {
         if (var3 != 37) {
            if (var3 != 41) {
               if (var3 != 48) {
                  if (var3 != 50) {
                     if (var3 != 51) {
                        switch (var3) {
                           case 32:
                              this.setWheelKey0x20();
                              break;
                           case 33:
                              this.setAirData0x21();
                              break;
                           case 34:
                              this.setRearRadarData0x22();
                              break;
                           default:
                              switch (var3) {
                                 case 209:
                                    this.setOriginalRadioData0xD1();
                                    break;
                                 case 210:
                                    this.setCameraStatusData0xD2();
                                    break;
                                 case 211:
                                    this.setScreenData0xD3();
                                    break;
                                 case 212:
                                    this.setCompassData0xD4();
                              }
                        }
                     } else {
                        this.setFuelData0x33();
                     }
                  } else {
                     this.setVehicleSetupData0x32();
                  }
               } else {
                  this.setVersionInfo0x30();
               }
            } else {
               this.setTrackData0x29();
            }
         } else {
            this.setParkAssistData0x25();
         }
      } else {
         this.setDoorData0x24();
      }

   }

   int getPresetListSize() {
      return this.mPresetList.size();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      GeneralOriginalCarDeviceData.songList = new ArrayList();
      this.initOriginalDeviceList();
      this.mDecimalFormat0P0 = new DecimalFormat("0.0");
      this.mDecimalFormat0P00 = new DecimalFormat("0.00");
      GeneralParkData.radar_location_data = null;
      RadarInfoUtil.mMinIsClose = true;
      GeneralOriginalCarDeviceData.isBottomBtnChange = false;
      this.mOriDevUiSet = UiMgrFactory.getCanUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
   }

   void onAuxClick() {
      GeneralOriginalCarDeviceData.cdStatus = "AUX";
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   void updateSetttings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
