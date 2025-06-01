package com.hzbhd.canbus.car._59;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] m0xe9Bytes;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int[] mDoorLockDataNow;
   private int[] mDriveDatax16Now;
   private int[] mDrivingAssistanceDataNow;
   private int mFrontCameraStatusNow;
   private boolean mFrontStatus;
   private String mFuelUnit;
   private String mInvalid = " - -";
   private boolean mIs0x12First = true;
   private boolean mIs0xe9First = true;
   private boolean mIsAirFirst = true;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private int[] mLightDataNow;
   private String mMileageUnit;
   private int[] mPanoramicDataNow;
   private int[] mRadarDataNow;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private boolean mSeatBeltTie;
   private int[] mSetting0x75DataNow;
   private boolean mSubSeatBeltTie;
   private byte[] mTrackDataNow;
   private UiMgr mUiMgr;
   private byte[] mVersionInfoNow;
   private int mWheelData;
   private int mWheelKeyNow;

   private void cleanAllBlow() {
      GeneralAirData.front_auto_wind_model = false;
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.rear_left_blow_window = false;
      GeneralAirData.rear_left_blow_head = false;
      GeneralAirData.rear_left_blow_foot = false;
      GeneralAirData.rear_right_blow_window = false;
      GeneralAirData.rear_right_blow_head = false;
      GeneralAirData.rear_right_blow_foot = false;
      GeneralAirData.rear_defog = false;
   }

   private int getDriveData(int var1, int var2) {
      return var1 * 256 + var2;
   }

   private int getDriveData(int var1, int var2, int var3) {
      return var1 * 256 * 256 + var2 * 256 + var3;
   }

   private int getFuelRange(int var1) {
      switch (var1) {
         case 1:
            return 10;
         case 2:
            return 12;
         case 3:
            return 20;
         case 4:
            return 30;
         case 5:
            return 40;
         case 6:
            return 50;
         case 7:
            return 70;
         case 8:
            return 80;
         case 9:
            return 90;
         case 10:
            return 100;
         default:
            return 60;
      }
   }

   private String getFuelUnit(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = " MPG";
      } else if (var1 == 1) {
         var2 = " km/L";
      } else if (var1 == 2) {
         var2 = " L/100km";
      } else {
         var2 = "";
      }

      return var2;
   }

   private String getMileageUnit(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = " km";
      } else if (var1 == 1) {
         var2 = " mile";
      } else {
         var2 = "";
      }

      return var2;
   }

   private boolean is0x12First() {
      if (this.mIs0x12First) {
         this.mIs0x12First = false;
         return true;
      } else {
         return false;
      }
   }

   private boolean is0xe9Repeat(byte[] var1) {
      if (Arrays.equals(var1, this.m0xe9Bytes)) {
         return true;
      } else {
         this.m0xe9Bytes = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDoorDataChange() {
      if (GeneralDoorData.isLeftFrontDoorOpen == this.mLeftFrontStatus && GeneralDoorData.isRightFrontDoorOpen == this.mRightFrontStatus && GeneralDoorData.isLeftRearDoorOpen == this.mLeftRearStatus && GeneralDoorData.isRightRearDoorOpen == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus && GeneralDoorData.isSeatBeltTie == this.mSeatBeltTie && GeneralDoorData.isSubSeatBeltTie == this.mSubSeatBeltTie) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mSeatBeltTie = GeneralDoorData.isSeatBeltTie;
         this.mSubSeatBeltTie = GeneralDoorData.isSubSeatBeltTie;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
            return true;
         }
      }

      return false;
   }

   private boolean isDoorLockDataChange() {
      if (Arrays.equals(this.mDoorLockDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mDoorLockDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDriveData0x16Change() {
      if (Arrays.equals(this.mDriveDatax16Now, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mDriveDatax16Now = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDrivingAssistanceChange() {
      if (Arrays.equals(this.mDrivingAssistanceDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mDrivingAssistanceDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return true;
      } else {
         return false;
      }
   }

   private boolean isFrontCameraStatusChange() {
      int var1 = this.mCanBusInfoInt[4];
      if (this.mFrontCameraStatusNow == var1) {
         return false;
      } else {
         this.mFrontCameraStatusNow = var1;
         return true;
      }
   }

   private boolean isLightDataChange() {
      if (Arrays.equals(this.mLightDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mLightDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramic0xE8Change() {
      if (Arrays.equals(this.mPanoramicDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isSetting0x75Change() {
      if (Arrays.equals(this.mSetting0x75DataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mSetting0x75DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackDataChange() {
      byte[] var1 = new byte[2];
      byte[] var2 = this.mCanBusInfoByte;
      var1[0] = var2[8];
      var1[1] = var2[9];
      if (Arrays.equals(this.mTrackDataNow, var1)) {
         return false;
      } else {
         this.mTrackDataNow = Arrays.copyOf(var1, 2);
         return true;
      }
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isWheelKeyDataChange() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[4];
      var1 = var2[5] | var1 << 8;
      if (this.mWheelKeyNow == var1) {
         return false;
      } else {
         this.mWheelKeyNow = var1;
         return true;
      }
   }

   private void openRightCamera() {
      if (!CommUtil.isMiscReverse()) {
         Context var3 = this.mContext;
         int var1 = this.mCanBusInfoInt[5];
         boolean var2 = true;
         if (var1 != 1) {
            var2 = false;
         }

         this.switchFCamera(var3, var2);
      }

   }

   private String resolveAirTemp(int var1) {
      String var2;
      if (var1 == 254) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else {
         var2 = var1 + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void setAirData0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         if (!this.isFirst()) {
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            int var1 = this.mCanBusInfoInt[2];
            boolean var2 = false;
            if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 2) == 1) {
               var2 = true;
            }

            GeneralAirData.ac = var2;
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
            this.cleanAllBlow();
            switch (this.mCanBusInfoInt[6]) {
               case 2:
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  break;
               case 3:
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  break;
               case 4:
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  break;
               case 5:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_foot = true;
                  break;
               case 6:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  break;
               case 7:
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_right_blow_head = true;
                  break;
               case 8:
                  GeneralAirData.rear_defog = true;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[9]);
            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setCameraStatus() {
      ArrayList var4 = new ArrayList();
      int var1 = this.mCanBusInfoInt[3];
      boolean var3 = false;
      boolean var2;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(0, var2));
      if (this.mCanBusInfoInt[3] == 2) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(1, var2));
      if (this.mCanBusInfoInt[3] == 3) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(2, var2));
      GeneralParkData.dataList = var4;
      this.updateParkUi((Bundle)null, this.mContext);
      if (!CommUtil.isMiscReverse() && this.isFrontCameraStatusChange()) {
         Context var5 = this.mContext;
         var2 = var3;
         if (this.mCanBusInfoInt[4] == 1) {
            var2 = true;
         }

         this.forceReverse(var5, var2);
         Log.d("cwh", "在右视");
      }

   }

   private void setDoorData0x12() {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setDoorLock0x65() {
      if (this.isDoorLockDataChange()) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]))));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) != 0) {
            var1.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) - 1));
         }

         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setDriveData0x11() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[3] + "Km/h"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveData0x16() {
      if (this.isDriveData0x16Change()) {
         this.mMileageUnit = this.getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 6, 1));
         this.mFuelUnit = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 4, 2));
         int var2 = this.getFuelRange(this.mCanBusInfoInt[15]);
         ArrayList var5 = new ArrayList();
         int[] var3 = this.mCanBusInfoInt;
         int var1 = var3[2];
         String var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(var3[14], 0, 2));
         var5.add(new DriverUpdateEntity(0, 0, (new DecimalFormat("0.0")).format((double)((float)var2 / 21.0F * (float)var1)) + var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[3], var3[4]);
         var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
         var6 = (float)var1 / 10.0F + var6;
         String var4 = "--";
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(0, 1, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[5], var3[6]);
         var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
         var6 = (float)var1 / 10.0F + var6;
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(0, 2, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[9], var3[10], var3[11]);
         var6 = this.mMileageUnit;
         var6 = (float)var1 / 10.0F + var6;
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(0, 3, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[7], var3[8]);
         var6 = this.mFuelUnit;
         var6 = (float)var1 / 10.0F + var6;
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(0, 4, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[12], var3[13]);
         var6 = this.getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 7, 1));
         var6 = var1 + var6;
         if (var1 == 65535) {
            var6 = var4;
         }

         var5.add(new DriverUpdateEntity(0, 5, var6));
         this.updateGeneralDriveData(var5);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setDriveData0x17() {
      this.mMileageUnit = this.getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 6, 1));
      this.mFuelUnit = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 4, 2));
      ArrayList var3 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      float var1 = (float)this.getDriveData(var2[2], var2[3], var2[4]);
      String var4;
      if (var1 == 1.6777215E7F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mMileageUnit;
      }

      var3.add(new DriverUpdateEntity(1, 1, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[5], var2[6]);
      if (var1 == 65535.0F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mFuelUnit;
      }

      var3.add(new DriverUpdateEntity(1, 2, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[7], var2[8], var2[9]);
      if (var1 == 1.6777215E7F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mMileageUnit;
      }

      var3.add(new DriverUpdateEntity(1, 4, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[10], var2[11]);
      if (var1 == 65535.0F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mFuelUnit;
      }

      var3.add(new DriverUpdateEntity(1, 5, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[12], var2[13], var2[14]);
      if (var1 == 1.6777215E7F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mMileageUnit;
      }

      var3.add(new DriverUpdateEntity(1, 7, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[15], var2[16]);
      if (var1 == 65535.0F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mFuelUnit;
      }

      var3.add(new DriverUpdateEntity(1, 8, var4));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrivingAssistance0x68() {
      if (this.isDrivingAssistanceChange()) {
         ArrayList var1 = new ArrayList();
         var1.add((new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 0, 4) - 5)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 0, 4)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setKeyTrack0x11() {
      if (this.isWheelKeyDataChange()) {
         int var1 = this.mCanBusInfoInt[4];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 12) {
                     switch (var1) {
                        case 8:
                           this.wheelKeyLongClick(45);
                           break;
                        case 9:
                           this.wheelKeyLongClick(46);
                           break;
                        case 10:
                           this.wheelKeyLongClick(2);
                     }
                  } else {
                     this.wheelKeyLongClick(185);
                  }
               } else {
                  this.wheelKeyLongClick(8);
               }
            } else {
               this.wheelKeyLongClick(7);
            }
         } else {
            this.wheelKeyLongClick(0);
         }
      }

      if (this.isTrackDataChange()) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(var2[9], var2[8], 0, 5200, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setLightStatus0x67() {
      if (this.isLightDataChange()) {
         SharePreUtil.setStringValue(this.mContext, "55_0x67", Base64.encodeToString(this.mCanBusInfoByte, 0));
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
         ArrayList var3 = new ArrayList();
         var3.add(new SettingUpdateEntity(0, 2, var1));
         if (var2 != 0) {
            var3.add(new SettingUpdateEntity(0, 3, var2 - 1));
         }

         this.updateGeneralSettingData(var3);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setPanoramic0xE8() {
      if (this.isPanoramic0xE8Change()) {
         ArrayList var1 = new ArrayList();
         var1.add((new SettingUpdateEntity(1, 0, this.mCanBusInfoInt[2])).setProgress(this.mCanBusInfoInt[2]));
         var1.add((new SettingUpdateEntity(1, 1, this.mCanBusInfoByte[3])).setProgress(this.mCanBusInfoByte[3] + 5));
         var1.add((new SettingUpdateEntity(1, 2, this.mCanBusInfoByte[4])).setProgress(this.mCanBusInfoByte[4] + 5));
         var1.add(new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setRadarData0x41() {
      if (this.isRadarDataChange()) {
         Log.d("cwh", "0x41");
         UiMgr var2 = this.mUiMgr;
         boolean var1;
         if (this.mCanBusInfoInt[12] == 1) {
            var1 = true;
         } else {
            var1 = false;
         }

         var2.setShowRadar(var1);
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         int[] var3 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var3[2], var3[3], var3[4], var3[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSetting0x75() {
      if (this.isSetting0x75Change()) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(0, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) != 0) {
            var1.add(new SettingUpdateEntity(0, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) - 1));
         }

         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) != 0) {
            var1.add(new SettingUpdateEntity(0, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) - 1));
         }

         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setVersionInfo0xF0() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void wheelKeyLongClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void wheelKeyShortClick(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void cameraDestroy() {
      super.cameraDestroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 0});
   }

   public void cameraInfoChange() {
      super.cameraInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 22) {
               if (var3 != 23) {
                  if (var3 != 49) {
                     if (var3 != 65) {
                        if (var3 != 101) {
                           if (var3 != 117) {
                              if (var3 != 240) {
                                 if (var3 != 103) {
                                    if (var3 != 104) {
                                       if (var3 != 232) {
                                          if (var3 == 233) {
                                             if (this.is0xe9Repeat(var2)) {
                                                return;
                                             }

                                             this.setCameraStatus();
                                          }
                                       } else {
                                          this.setPanoramic0xE8();
                                       }
                                    } else {
                                       this.setDrivingAssistance0x68();
                                    }
                                 } else {
                                    this.setLightStatus0x67();
                                 }
                              } else {
                                 this.setVersionInfo0xF0();
                              }
                           } else {
                              this.setSetting0x75();
                           }
                        } else {
                           this.setDoorLock0x65();
                        }
                     } else {
                        this.setRadarData0x41();
                     }
                  } else {
                     this.setAirData0x31();
                  }
               } else {
                  this.setDriveData0x17();
               }
            } else {
               this.setDriveData0x16();
            }
         } else {
            if (this.isDoorMsgRepeat(this.mCanBusInfoByte)) {
               return;
            }

            if (this.is0x12First()) {
               return;
            }

            this.setDoorData0x12();
            this.openRightCamera();
         }
      } else {
         this.setKeyTrack0x11();
         this.setDriveData0x11();
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var8, (byte)var6, 0, 0, (byte)var10, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentEachCanId(), 2});
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
      }

   }
}
