package com.hzbhd.canbus.car._56;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String _55_IS_RADAR_OPEN = "_55_is_radar_open";
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private int[] mAmplifierDataNow;
   private boolean mBackStatus;
   private int mBtOn;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mData;
   private int mDifferent;
   private int[] mDisplayDataNow;
   private int[] mDoorLockDataNow;
   private int[] mDriveDatax16Now;
   private int[] mDriveDatax17Now;
   private int[] mDrivingAssistanceDataNow;
   private DecimalFormat mFormat00;
   private DecimalFormat mFormat000;
   private int mFrontCameraStatusNow;
   private boolean mFrontStatus;
   private String mFuelUnit;
   private String mInvalid = " - -";
   private boolean mIsAirFirst = true;
   private boolean mIsDoorFirst = true;
   private boolean mIsMute;
   private boolean mIsRightCameraFirst = true;
   private boolean mIsShowRadar;
   private boolean mLeftFrontStatus;
   private int mLeftHeat;
   private boolean mLeftRearStatus;
   private int[] mLightDataNow;
   private int mMediaStatusNow;
   private String mMileageUnit;
   private int mPanoramicBtnNow;
   private int mPanoramicStatusNow;
   private int[] mRadarDataNow;
   private int[] mRemoteDataNow;
   private boolean mRightFrontStatus;
   private int mRightHeat;
   private boolean mRightRearStatus;
   private TimerTask mTimeTask;
   private Timer mTimer;
   private int[] mTireDataNow;
   private byte[] mTrackDataNow;
   private UiMgr mUiMgr;
   private String mUsbSonTimeNow;
   private byte[] mUsbSonTimeNowByte;
   private String mUsbSongAlbumNow;
   private String mUsbSongArtistNow;
   private String mUsbSongTitleNow;
   private int[] mVehicleInfoDataNow;
   private byte[] mVersionInfoNow;
   private int mWheelKeyNow;
   private byte[] m_0xe0;
   private byte[] mediaDataNow;
   private int vol;

   private byte[] bytesExpectOneByte(byte[] var1, int var2) {
      int var4 = var1.length - 1;
      byte[] var6 = new byte[var4];
      byte[] var5;
      if (var2 == var4) {
         var5 = Arrays.copyOf(var1, var4);
      } else {
         int var3 = 0;

         while(true) {
            var5 = var6;
            if (var3 >= var4) {
               break;
            }

            if (var3 < var2) {
               var6[var3] = var1[var3];
            } else {
               var6[var3] = var1[var3 + 1];
            }

            ++var3;
         }
      }

      return var5;
   }

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
   }

   private int getAirWhat() {
      int[] var4 = Arrays.copyOfRange(this.mCanBusInfoInt, 2, 9);
      int[] var3 = new int[1];
      int var2 = this.mCanBusInfoInt[10];
      short var1 = 0;
      var3[0] = var2;
      if (!Arrays.equals(this.mAirFrontDataNow, var4)) {
         var1 = 1001;
      } else if (!Arrays.equals(this.mAirRearDataNow, var3)) {
         var1 = 1002;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var4, var4.length);
      this.mAirRearDataNow = Arrays.copyOf(var3, 1);
      return var1;
   }

   private byte getAllBandTypeData(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 2;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 3;
            }
      }

      switch (var2) {
         case 0:
            return 4;
         case 1:
            return 5;
         case 2:
            return 2;
         case 3:
            return 3;
         default:
            return 1;
      }
   }

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private String getBandUnit(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
         case 1:
            return "KHz";
         case 2:
         case 3:
         case 4:
            return "MHz";
         default:
            return "";
      }
   }

   private int getDriveData(int var1, int var2) {
      return var1 * 256 + var2;
   }

   private int getDriveData(int var1, int var2, int var3) {
      return var1 * 256 * 256 + var2 * 256 + var3;
   }

   private String getDriveTime() {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = this.getDriveData(var3[8], var3[9]);
      int var1 = var2 / 60;
      return (new DecimalFormat("00")).format((long)var1) + ":" + (new DecimalFormat("00")).format((long)(var2 % 60));
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

   private int getHour(int var1) {
      return var1 / 60 / 60;
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

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private boolean is0xe0Repeat(byte[] var1) {
      if (Arrays.equals(var1, this.m_0xe0)) {
         return true;
      } else {
         this.m_0xe0 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isAmplifierDataChange(int[] var1) {
      if (Arrays.equals(var1, this.mAmplifierDataNow)) {
         return false;
      } else {
         this.mAmplifierDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDisplayChange() {
      if (Arrays.equals(this.mDisplayDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mDisplayDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (GeneralDoorData.isLeftFrontDoorOpen == this.mLeftFrontStatus && GeneralDoorData.isRightFrontDoorOpen == this.mRightFrontStatus && GeneralDoorData.isLeftRearDoorOpen == this.mLeftRearStatus && GeneralDoorData.isRightRearDoorOpen == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
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

   private boolean isDriveDatax17Change() {
      if (Arrays.equals(this.mDriveDatax17Now, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mDriveDatax17Now = Arrays.copyOf(var1, var1.length);
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
         if (this.mIsRightCameraFirst) {
            this.mIsRightCameraFirst = false;
         }

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

   private boolean isMediaStatusChange() {
      int var1 = this.mCanBusInfoInt[2];
      if (this.mMediaStatusNow == var1) {
         return false;
      } else {
         this.mMediaStatusNow = var1;
         return true;
      }
   }

   private boolean isPanoramicBtnChange() {
      int var2 = this.mPanoramicBtnNow;
      int var1 = this.mCanBusInfoInt[3];
      if (var2 == var1) {
         return false;
      } else {
         this.mPanoramicBtnNow = var1;
         return true;
      }
   }

   private boolean isPanoramicStatusChange() {
      int var1 = this.mCanBusInfoInt[6];
      if (this.mPanoramicStatusNow == var1) {
         return false;
      } else {
         this.mPanoramicStatusNow = var1;
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

   private boolean isRadarOpen() {
      int var1 = this.mCanBusInfoInt[12];
      boolean var3 = false;
      boolean var2;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      UiMgr var4 = this.mUiMgr;
      if (var1 == 1) {
         var3 = true;
      }

      var4.setShowRadar(var3);
      return var2;
   }

   private boolean isRemoteDataChange() {
      if (Arrays.equals(this.mRemoteDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRemoteDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isSeatHeatChange() {
      if (GeneralAirData.front_left_seat_heat_level == this.mLeftHeat && GeneralAirData.front_right_seat_heat_level == this.mRightHeat) {
         return false;
      } else {
         this.mLeftHeat = GeneralAirData.front_left_seat_heat_level;
         this.mRightHeat = GeneralAirData.front_right_seat_heat_level;
         return true;
      }
   }

   private boolean isSupportAmplifier() {
      int var1 = this.mDifferent;
      boolean var3 = true;
      boolean var2 = var3;
      if (var1 != 1) {
         if (var1 == 2) {
            var2 = var3;
         } else {
            var2 = false;
         }
      }

      return var2;
   }

   private boolean isSupportDriveData0x16() {
      return true;
   }

   private boolean isSupportDriveData0x17() {
      return true;
   }

   private boolean isSupportPanoramic() {
      return true;
   }

   private boolean isTireDataChange() {
      if (Arrays.equals(this.mTireDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireDataNow = Arrays.copyOf(var1, var1.length);
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

   private boolean isVehicleInfoChange() {
      if (Arrays.equals(this.mVehicleInfoDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mVehicleInfoDataNow = Arrays.copyOf(var1, var1.length);
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
      int var2 = this.mWheelKeyNow;
      int var1 = this.mCanBusInfoInt[4];
      if (var2 == var1) {
         return var1 == 1 || var1 == 2 || var1 == 8 || var1 == 9 || var1 == 13 || var1 == 14;
      } else {
         this.mWheelKeyNow = var1;
         return true;
      }
   }

   private void panelKeyClick(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick5(var3, var1, var2[2], var2[3]);
   }

   private void reportID3Info(String var1, String var2, String var3, boolean var4, String var5) {
      (new Thread(this, var4, var1, var2, var3, var5) {
         final MsgMgr this$0;
         final String val$album;
         final String val$artist;
         final String val$charsetName;
         final boolean val$isClean;
         final String val$title;

         {
            this.this$0 = var1;
            this.val$isClean = var2;
            this.val$title = var3;
            this.val$album = var4;
            this.val$artist = var5;
            this.val$charsetName = var6;
         }

         public void run() {
            super.run();

            try {
               if (this.val$isClean) {
                  sleep(1000L);
               }

               if (!this.val$title.equals(this.this$0.mUsbSongTitleNow) || !this.val$album.equals(this.this$0.mUsbSongAlbumNow) || !this.val$artist.equals(this.this$0.mUsbSongArtistNow)) {
                  this.this$0.mUsbSongTitleNow = this.val$title;
                  this.this$0.mUsbSongAlbumNow = this.val$album;
                  this.this$0.mUsbSongArtistNow = this.val$artist;
                  sleep(100L);
                  this.this$0.reportID3InfoFinal((byte)-28, this.val$title, this.val$charsetName);
                  sleep(100L);
                  this.this$0.reportID3InfoFinal((byte)-29, this.val$album, this.val$charsetName);
                  sleep(100L);
                  this.this$0.reportID3InfoFinal((byte)-30, this.val$artist, this.val$charsetName);
               }
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private void reportID3InfoFinal(byte var1, String var2, String var3) {
      try {
         byte[] var5 = DataHandleUtils.exceptBOMHead(var2.getBytes(var3));
         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, var1}, var5), 34));
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private String resolveAirTemp(int var1) {
      String var2;
      if (var1 == 254) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      return (float)this.mCanBusInfoInt[13] * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private void sendMediaMsg1(Context var1, String var2, byte[] var3) {
      Log.i("AbstractMsgMgr", "sendMediaMsg: context: " + var1 + ", media: " + var2);
      if (var1 != null) {
         String var4 = FutureUtil.instance.getCurrentValidSource().toString();
         Log.i("AbstractMsgMgr", "sendMediaMsg: frontSeat:" + var4);
         if (System.getInt(var1.getContentResolver(), "btState", 1) == 1 && (var2.equals(var4) || SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name().equals(var2)) && !Arrays.equals(var3, this.mediaDataNow)) {
            CanbusMsgSender.sendMsg(var3);
            this.mediaDataNow = Arrays.copyOf(var3, var3.length);
            if (!SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(var2)) {
               System.putString(var1.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(var3, 0));
               if (!SourceConstantsDef.SOURCE_ID.MPEG.toString().equals(var4)) {
                  System.putString(var1.getContentResolver(), "reportForDiscEject", Base64.encodeToString(var3, 0));
               }
            }
         }

      }
   }

   private void setAirData0x31() {
      int var1 = this.getAirWhat();
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         if (!this.isFirst()) {
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
            this.cleanAllBlow();
            int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
            if (var2 != 3) {
               if (var2 != 4) {
                  if (var2 != 5) {
                     if (var2 != 6) {
                        if (var2 != 7) {
                           if (var2 == 10) {
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_left_blow_head = true;
                              GeneralAirData.front_left_blow_foot = true;
                           }
                        } else {
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_head = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_left_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_window = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
            }

            var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
            if (var2 != 3) {
               if (var2 != 4) {
                  if (var2 != 5) {
                     if (var2 != 6) {
                        if (var2 != 7) {
                           if (var2 == 10) {
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_right_blow_head = true;
                              GeneralAirData.front_right_blow_foot = true;
                           }
                        } else {
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_head = true;
                        }
                     } else {
                        GeneralAirData.front_right_blow_head = true;
                     }
                  } else {
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_right_blow_window = true;
               }
            } else {
               GeneralAirData.front_right_blow_foot = true;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_wind_level = this.mCanBusInfoInt[10];
            if (!this.isSeatHeatChange()) {
               this.updateAirActivity(this.mContext, var1);
            }

         }
      }
   }

   private void setAmplifierData0xA6() {
      if (this.isSupportAmplifier()) {
         int[] var2 = new int[5];
         byte[] var1 = this.mCanBusInfoByte;
         var2[0] = var1[3];
         var2[1] = var1[4];
         var2[2] = var1[5];
         var2[4] = var1[6];
         var2[3] = var1[7];
         if (this.isAmplifierDataChange(var2)) {
            Bundle var3 = new Bundle();
            var3.putIntArray("Amplifier", var2);
            Intent var5 = new Intent();
            var5.setAction("com.CAMRY_POWER_AMPLIFIER_DATA_CHANGED");
            var5.putExtras(var3);
            this.mContext.sendBroadcast(var5);
         }

         ArrayList var4 = new ArrayList();
         var4.add(new SettingUpdateEntity(9, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4)));
         var4.add(new SettingUpdateEntity(9, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2)));
         var4.add(new SettingUpdateEntity(9, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)));
         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setDisplay0x69() {
      if (this.isDisplayChange()) {
         SharePreUtil.setStringValue(this.mContext, "55_0x69", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(6, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3)));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2) != 0) {
            var1.add(new SettingUpdateEntity(6, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2) - 1));
         }

         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2) != 0) {
            var1.add(new SettingUpdateEntity(6, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2) - 1));
         }

         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) != 0) {
            var1.add(new SettingUpdateEntity(6, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) - 1));
         }

         var1.add(new SettingUpdateEntity(6, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
         var1.add(new SettingUpdateEntity(6, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
         var1.add(new SettingUpdateEntity(6, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
         var1.add(new SettingUpdateEntity(6, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setDoorData0x12() {
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
         SharePreUtil.setStringValue(this.mContext, "55_0x65", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2) != 0) {
            var1.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2) - 1));
         }

         var1.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setDriveData0x16() {
      if (this.isSupportDriveData0x16() && this.isDriveData0x16Change()) {
         int var2 = this.getFuelRange(this.mCanBusInfoInt[15]);
         ArrayList var5 = new ArrayList();
         int[] var3 = this.mCanBusInfoInt;
         int var1 = var3[2];
         String var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(var3[14], 0, 2));
         var5.add(new DriverUpdateEntity(1, 0, (new DecimalFormat("0.0")).format((double)((float)var2 / 21.0F * (float)var1)) + var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[3], var3[4]);
         var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
         var6 = (float)var1 / 10.0F + var6;
         String var4 = "--";
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(1, 1, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[5], var3[6]);
         var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
         var6 = (float)var1 / 10.0F + var6;
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(1, 2, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[9], var3[10], var3[11]);
         var6 = this.getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 6, 1));
         this.mMileageUnit = var6;
         var6 = (float)var1 / 10.0F + var6;
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(1, 3, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[7], var3[8]);
         var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 4, 2));
         this.mFuelUnit = var6;
         var6 = (float)var1 / 10.0F + var6;
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(1, 4, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[12], var3[13]);
         var6 = this.getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 7, 1));
         var6 = var1 + var6;
         if (var1 == 65535) {
            var6 = var4;
         }

         var5.add(new DriverUpdateEntity(1, 5, var6));
         var1 = this.mCanBusInfoInt[16];
         var5.add(new DriverUpdateEntity(1, 6, var1 + "km/h"));
         this.updateSpeedInfo(var1);
         var5.add(new DriverUpdateEntity(1, 7, this.mCanBusInfoInt[17] + " h " + this.mCanBusInfoInt[18] + " m"));
         this.updateGeneralDriveData(var5);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setDriveData0x17() {
      if (this.isSupportDriveData0x17() && this.isDriveDatax17Change()) {
         int[] var2 = this.mCanBusInfoInt;
         this.getFuelUnit(var2[var2.length - 1]);
         ArrayList var3 = new ArrayList();
         var2 = this.mCanBusInfoInt;
         float var1 = (float)this.getDriveData(var2[2], var2[3], var2[4]);
         String var4;
         if (var1 == 1.6777215E7F) {
            var4 = this.mInvalid;
         } else {
            var4 = var1 / 10.0F + this.mMileageUnit;
         }

         var3.add(new DriverUpdateEntity(2, 1, var4));
         var2 = this.mCanBusInfoInt;
         var1 = (float)this.getDriveData(var2[5], var2[6]);
         if (var1 == 65535.0F) {
            var4 = this.mInvalid;
         } else {
            var4 = var1 / 10.0F + this.mFuelUnit;
         }

         var3.add(new DriverUpdateEntity(2, 2, var4));
         var2 = this.mCanBusInfoInt;
         var1 = (float)this.getDriveData(var2[7], var2[8], var2[9]);
         if (var1 == 1.6777215E7F) {
            var4 = this.mInvalid;
         } else {
            var4 = var1 / 10.0F + this.mMileageUnit;
         }

         var3.add(new DriverUpdateEntity(2, 4, var4));
         var2 = this.mCanBusInfoInt;
         var1 = (float)this.getDriveData(var2[10], var2[11]);
         if (var1 == 65535.0F) {
            var4 = this.mInvalid;
         } else {
            var4 = var1 / 10.0F + this.mFuelUnit;
         }

         var3.add(new DriverUpdateEntity(2, 5, var4));
         var2 = this.mCanBusInfoInt;
         var1 = (float)this.getDriveData(var2[12], var2[13], var2[14]);
         if (var1 == 1.6777215E7F) {
            var4 = this.mInvalid;
         } else {
            var4 = var1 / 10.0F + this.mMileageUnit;
         }

         var3.add(new DriverUpdateEntity(2, 7, var4));
         var2 = this.mCanBusInfoInt;
         var1 = (float)this.getDriveData(var2[15], var2[16]);
         if (var1 == 65535.0F) {
            var4 = this.mInvalid;
         } else {
            var4 = var1 / 10.0F + this.mFuelUnit;
         }

         var3.add(new DriverUpdateEntity(2, 8, var4));
         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setDrivingAssistance0x68() {
      if (this.isDrivingAssistanceChange()) {
         SharePreUtil.setStringValue(this.mContext, "55_0x68", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2)));
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
         if (var1 != 0) {
            var2.add(new SettingUpdateEntity(5, 1, var1 - 1));
         }

         var2.add(new SettingUpdateEntity(5, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
         var2.add(new SettingUpdateEntity(5, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
         if (var1 != 0) {
            var2.add(new SettingUpdateEntity(5, 4, var1 - 1));
         }

         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setKeyTrack0x11() {
      this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      if (this.isWheelKeyDataChange()) {
         switch (this.mCanBusInfoInt[4]) {
            case 0:
               this.wheelKeyLongClick(0);
               break;
            case 1:
               this.wheelKeyLongClick(7);
               break;
            case 2:
               this.wheelKeyLongClick(8);
            case 3:
            case 7:
            default:
               break;
            case 4:
               this.wheelKeyLongClick(187);
               break;
            case 5:
               this.wheelKeyLongClick(14);
               break;
            case 6:
               this.wheelKeyLongClick(189);
               break;
            case 8:
               this.wheelKeyLongClick(47);
               break;
            case 9:
               this.wheelKeyLongClick(48);
               break;
            case 10:
               this.wheelKeyLongClick(3);
               break;
            case 11:
               this.wheelKeyLongClick(2);
               break;
            case 12:
               this.wheelKeyLongClick(185);
               break;
            case 13:
               this.wheelKeyLongClick(45);
               break;
            case 14:
               this.wheelKeyLongClick(46);
               break;
            case 15:
               this.wheelKeyLongClick(49);
         }
      }

      if (this.isTrackDataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(var1[9], var1[8], 0, 5200, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setLightStatus0x67() {
      if (this.isLightDataChange()) {
         SharePreUtil.setStringValue(this.mContext, "55_0x67", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
         var1.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3)));
         var1.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3)));
         var1.add(new SettingUpdateEntity(2, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2)));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) != 0) {
            var1.add(new SettingUpdateEntity(2, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) - 1));
         }

         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setMediaSwitch0xE0() {
      if (this.isMediaStatusChange()) {
         int var1 = this.mCanBusInfoInt[2];
         if (var1 != 0) {
            switch (var1) {
               case 32:
                  this.sourceKeyClick(77);
                  break;
               case 33:
                  this.sourceKeyClick(76);
                  break;
               case 34:
                  this.sourceKeyClick(139);
                  break;
               case 35:
                  this.sourceKeyClick(140);
                  break;
               case 36:
                  this.sourceKeyClick(141);
            }
         } else {
            this.sourceKeyClick(52);
            this.enterNoSource();
         }
      }

   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setPanelKey0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 69) {
            if (var1 != 70) {
               if (var1 != 87) {
                  if (var1 != 88) {
                     switch (var1) {
                        case 91:
                           this.panelKeyClick(20);
                           break;
                        case 92:
                           this.panelKeyClick(21);
                           break;
                        case 93:
                           this.panelKeyClick(46);
                           break;
                        case 94:
                           this.panelKeyClick(45);
                     }
                  } else {
                     this.panelKeyClick(47);
                  }
               } else {
                  this.panelKeyClick(48);
               }
            } else {
               this.panelKeyClick(8);
            }
         } else {
            this.panelKeyClick(7);
         }
      } else {
         this.panelKeyClick(0);
      }

   }

   private void setPanoramic0xE8() {
      boolean var1 = CommUtil.isMiscReverse();
      boolean var2 = false;
      Context var3;
      if (!var1 && this.isFrontCameraStatusChange()) {
         var3 = this.mContext;
         if (this.mCanBusInfoInt[4] == 1) {
            var1 = true;
         } else {
            var1 = false;
         }

         this.switchFCamera(var3, var1);
      }

      if (this.isSupportPanoramic() && this.isPanoramicStatusChange()) {
         var3 = this.mContext;
         if (this.mCanBusInfoInt[6] == 1) {
            var1 = true;
         } else {
            var1 = false;
         }

         this.forceReverse(var3, var1);
      }

      if (this.isPanoramicBtnChange()) {
         ArrayList var4 = new ArrayList();
         if (this.mCanBusInfoInt[3] == 1) {
            var1 = true;
         } else {
            var1 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(0, var1));
         if (this.mCanBusInfoInt[3] == 2) {
            var1 = true;
         } else {
            var1 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(1, var1));
         var1 = var2;
         if (this.mCanBusInfoInt[3] == 3) {
            var1 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(2, var1));
         GeneralParkData.dataList = var4;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRadarData0x41() {
      if (this.isRadarDataChange()) {
         UiMgr var3 = this.mUiMgr;
         int var1 = this.mCanBusInfoInt[12];
         boolean var2 = true;
         if (var1 != 1) {
            var2 = false;
         }

         var3.setShowRadar(var2);
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mDisableData = 255;
         int[] var4 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var4[2], var4[3], var4[4], var4[5]);
         var4 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var4[6], 255, 255, var4[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRemoteControl0x66() {
      if (this.isRemoteDataChange()) {
         SharePreUtil.setStringValue(this.mContext, "55_0x66", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
         var1.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
         var1.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
         var1.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setTirePressure0x48() {
      if (this.isTireDataChange()) {
         int var1;
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
         } else {
            var1 = 0;
         }

         ArrayList var4 = new ArrayList();

         for(int var2 = 0; var2 < 5; ++var2) {
            StringBuilder var3 = new StringBuilder();
            int[] var5 = this.mCanBusInfoInt;
            var4.add(new TireUpdateEntity(var2, var1, new String[]{var3.append(var5[var2 + 4] + var5[var2 + 9]).append("kPa").toString()}));
         }

         GeneralTireData.dataList = var4;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void setVehicleInfo0x32() {
      if (this.isVehicleInfoChange()) {
         ArrayList var4 = new ArrayList();
         int[] var2 = this.mCanBusInfoInt;
         int var1 = this.getDriveData(var2[4], var2[5]);
         String var5 = var1 + " rpm";
         String var3 = " -- ";
         if (var1 == 65535) {
            var5 = " -- ";
         }

         var4.add(new DriverUpdateEntity(0, 0, var5));
         var2 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var2[6], var2[7]);
         var5 = var1 + " km/h";
         if (var1 == 65535) {
            var5 = var3;
         }

         var4.add(new DriverUpdateEntity(0, 1, var5));
         this.updateGeneralDriveData(var4);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setVersionInfo0xF0() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void sourceKeyClick(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private void wheelKeyLongClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void wheelKeyShortClick(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("TV".getBytes(), 0, var1, 0, "TV".getBytes().length);
      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, var1));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("AUX".getBytes(), 0, var1, 0, "AUX".getBytes().length);
      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, 12}, var1));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = new byte[9];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("A2DP".getBytes(), 0, var1, 0, "A2DP".getBytes().length);
      var1 = DataHandleUtils.byteMerger("000".getBytes(), var1);
      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, var1));
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      byte[] var4 = new byte[30];
      Arrays.fill(var4, (byte)32);
      var4[var1.length] = 0;
      java.lang.System.arraycopy(var1, 0, var4, 0, var1.length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 4}, var4), new byte[]{0}));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      byte[] var4 = new byte[30];
      Arrays.fill(var4, (byte)32);
      var4[var1.length] = 0;
      java.lang.System.arraycopy(var1, 0, var4, 0, var1.length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 1}, var4), new byte[]{0}));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      byte[] var4 = new byte[30];
      Arrays.fill(var4, (byte)32);
      var4[var1.length] = 0;
      java.lang.System.arraycopy(var1, 0, var4, 0, var1.length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 2}, var4), new byte[]{0}));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (var3 && var1 == 0) {
         var2 = new byte[30];
         Arrays.fill(var2, (byte)0);
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 0}, var2), new byte[]{0}));
      }

      if (var3) {
         this.mBtOn = 1;
      } else {
         this.mBtOn = 0;
      }

      FutureUtil.instance.DevicesStutasRepCanbus(this.mContext);
      Log.d("cwh", "mBtOn = " + this.mBtOn);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      byte[] var4 = new byte[30];
      Arrays.fill(var4, (byte)32);
      var4[var1.length] = 0;
      java.lang.System.arraycopy(var1, 0, var4, 0, var1.length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 3}, var4), new byte[]{0}));
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      var1 = ("    " + (new DecimalFormat("00")).format((long)(var4 / 60 % 60)) + (new DecimalFormat("00")).format((long)(var4 % 60)) + "    ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, var1));
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
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 22) {
               if (var3 != 23) {
                  if (var3 != 49) {
                     if (var3 != 50) {
                        if (var3 != 65) {
                           if (var3 != 72) {
                              if (var3 != 224) {
                                 if (var3 != 232) {
                                    if (var3 != 240) {
                                       switch (var3) {
                                          case 101:
                                             this.setDoorLock0x65();
                                             break;
                                          case 102:
                                             this.setRemoteControl0x66();
                                             break;
                                          case 103:
                                             this.setLightStatus0x67();
                                             break;
                                          case 104:
                                             this.setDrivingAssistance0x68();
                                             break;
                                          case 105:
                                             this.setDisplay0x69();
                                       }
                                    } else {
                                       this.setVersionInfo0xF0();
                                    }
                                 } else {
                                    this.setPanoramic0xE8();
                                 }
                              } else {
                                 if (this.is0xe0Repeat(var2)) {
                                    return;
                                 }

                                 this.setMediaSwitch0xE0();
                              }
                           } else {
                              this.setTirePressure0x48();
                           }
                        } else {
                           this.setRadarData0x41();
                        }
                     } else {
                        this.setVehicleInfo0x32();
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
            this.setDoorData0x12();
         }
      } else {
         this.setKeyTrack0x11();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      StringBuilder var5 = (new StringBuilder()).append("VOL ");
      DecimalFormat var6 = new DecimalFormat("00");
      long var3 = (long)var1;
      byte[] var7 = var5.append(String.format(var6.format(var3))).append("  ").append(String.format((new DecimalFormat("00")).format(var3))).append("  ").toString().getBytes();
      this.vol = var1;
      this.mIsMute = var2;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 32}, var7));
      FutureUtil.instance.DevicesStutasRepCanbus(this.mContext);
      Log.d("cwh", "当前音量改变");
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -75, (byte)var5, (byte)var6, (byte)var7});
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super.deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      var1 = var2 << 3 | var3 << 4 | var6 << 7 | this.mBtOn << 6 | this.mIsMute << 5 | var11 << 2 | var12 << 1;
      this.mData = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -26, (byte)var1, 0, 0, 0, (byte)this.vol, 0, 0, 0, 0, 0});
      Log.d("cwh", "设备状态改变，蓝牙图标是 : " + this.mBtOn);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("TV".getBytes(), 0, var1, 0, "TV".getBytes().length);
      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, var1));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      int var2 = this.getCurrentCanDifferentId();
      this.mDifferent = var2;
      if (var2 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 33, 2});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 32, 2});
      }

      if (var1.getResources().getConfiguration().orientation == 1 && this.mDifferent == 104) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 32});
      }

   }

   public void musicDestroy() {
      super.musicDestroy();
      this.reportID3Info("", "", "", true, "ascii");
      byte[] var1 = new byte[]{22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      if (!var1.equals(this.mUsbSonTimeNowByte)) {
         CanbusMsgSender.sendMsg(var1);
         this.mUsbSonTimeNowByte = var1;
      }

   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = (new DecimalFormat("00")).format((long)var7);
      byte[] var25 = (String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " 00      ").getBytes();
      var25 = DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var25);
      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var25);
      if (!var11.equals(this.mUsbSonTimeNow)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, var6, var7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         this.mUsbSonTimeNow = var11;
      }

      this.reportID3Info(var21, "", "", false, "gbk");
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = var1;
      if (var1 == 256) {
         var5 = 0;
      }

      byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         if (var3.length() == 4) {
            var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3 + "     ";
         } else {
            var2 = (new DecimalFormat("00")).format((long)var5) + " 0" + var3 + "     ";
         }
      } else if (var3.length() == 5) {
         var2 = (new DecimalFormat("00")).format((long)var5) + "  " + var3.substring(0, var3.length() - 1) + "    ";
      } else {
         var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3.substring(0, var3.length() - 1) + "    ";
      }

      byte[] var7 = var2.getBytes();
      var7 = DataHandleUtils.byteMerger(new byte[]{22, -31, var6}, var7);
      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), var7);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      byte[] var2 = new byte[]{22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      if (!var2.equals(this.mUsbSonTimeNowByte)) {
         CanbusMsgSender.sendMsg(var2);
         this.mUsbSonTimeNowByte = var2;
      }

      this.reportID3Info("", "", "", true, "ascii");
      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.toString(), new byte[]{22, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   void updateLanguage(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(7, 0, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   void updateSettingData(int var1) {
      LogUtil.showLog("updateSettingData:" + var1);
      ArrayList var2 = new ArrayList();
      String var3;
      byte[] var4;
      switch (var1) {
         case 2:
            var3 = SharePreUtil.getStringValue(this.mContext, "55_0x67", (String)null);
            if (!TextUtils.isEmpty(var3)) {
               var4 = Base64.decode(var3, 0);
               var2.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(var4[2], 3, 1)));
               var2.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(var4[2], 0, 3)));
               var2.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(var4[3], 4, 3)));
               var2.add(new SettingUpdateEntity(2, 3, DataHandleUtils.getIntFromByteWithBit(var4[3], 2, 2)));
               var1 = DataHandleUtils.getIntFromByteWithBit(var4[3], 0, 2);
               if (var1 != 0) {
                  var2.add(new SettingUpdateEntity(2, 4, var1 - 1));
               }
            }
            break;
         case 3:
            var3 = SharePreUtil.getStringValue(this.mContext, "55_0x66", (String)null);
            if (!TextUtils.isEmpty(var3)) {
               var4 = Base64.decode(var3, 0);
               var2.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(var4[3], 3, 1)));
               var2.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(var4[3], 2, 1)));
               var2.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(var4[3], 1, 1)));
               var2.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(var4[3], 0, 1)));
            }
            break;
         case 4:
            var3 = SharePreUtil.getStringValue(this.mContext, "55_0x65", (String)null);
            if (!TextUtils.isEmpty(var3)) {
               var4 = Base64.decode(var3, 0);
               var2.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(var4[3], 3, 1)));
               var1 = DataHandleUtils.getIntFromByteWithBit(var4[3], 1, 2);
               if (var1 != 0) {
                  var2.add(new SettingUpdateEntity(4, 1, var1 - 1));
               }

               var2.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(var4[3], 0, 1)));
            }
            break;
         case 5:
            var3 = SharePreUtil.getStringValue(this.mContext, "55_0x68", (String)null);
            if (!TextUtils.isEmpty(var3)) {
               var4 = Base64.decode(var3, 0);
               var2.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(var4[3], 6, 2)));
               var2.add(new SettingUpdateEntity(5, 1, DataHandleUtils.getIntFromByteWithBit(var4[3], 4, 2)));
               var2.add(new SettingUpdateEntity(5, 2, DataHandleUtils.getIntFromByteWithBit(var4[3], 3, 1)));
               var2.add(new SettingUpdateEntity(5, 3, DataHandleUtils.getIntFromByteWithBit(var4[3], 2, 1)));
               var2.add(new SettingUpdateEntity(5, 4, DataHandleUtils.getIntFromByteWithBit(var4[3], 0, 2)));
            }
            break;
         case 6:
            var3 = SharePreUtil.getStringValue(this.mContext, "55_0x69", (String)null);
            if (!TextUtils.isEmpty(var3)) {
               var4 = Base64.decode(var3, 0);
               var2.add(new SettingUpdateEntity(6, 7, DataHandleUtils.getIntFromByteWithBit(var4[3], 0, 3)));
               var1 = DataHandleUtils.getIntFromByteWithBit(var4[3], 3, 2);
               if (var1 != 0) {
                  var2.add(new SettingUpdateEntity(6, 6, var1 - 1));
               }

               var1 = DataHandleUtils.getIntFromByteWithBit(var4[3], 5, 2);
               if (var1 != 0) {
                  var2.add(new SettingUpdateEntity(6, 5, var1 - 1));
               }

               var1 = DataHandleUtils.getIntFromByteWithBit(var4[2], 0, 2);
               if (var1 != 0) {
                  var2.add(new SettingUpdateEntity(6, 4, var1 - 1));
               }

               var2.add(new SettingUpdateEntity(6, 3, DataHandleUtils.getIntFromByteWithBit(var4[2], 2, 1)));
               var2.add(new SettingUpdateEntity(6, 2, DataHandleUtils.getIntFromByteWithBit(var4[2], 3, 1)));
               var2.add(new SettingUpdateEntity(6, 1, DataHandleUtils.getIntFromByteWithBit(var4[2], 4, 1)));
               var2.add(new SettingUpdateEntity(6, 0, DataHandleUtils.getIntFromByteWithBit(var4[2], 5, 1)));
            }
            break;
         case 7:
            var2.add(new SettingUpdateEntity(7, 0, SharePreUtil.getIntValue(this.mContext, "55_language", 0)));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoDestroy() {
      super.videoDestroy();
      byte[] var1 = new byte[]{22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      if (!var1.equals(this.mUsbSonTimeNowByte)) {
         CanbusMsgSender.sendMsg(var1);
         this.mUsbSonTimeNowByte = var1;
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (this.mFormat00 == null) {
         this.mFormat00 = new DecimalFormat("00");
      }

      if (this.mFormat000 == null) {
         this.mFormat000 = new DecimalFormat("000");
      }

      var8 = this.mFormat00.format((long)var7);
      var11 = String.format(this.mFormat000.format((long)((var9 & 255) * 256 + (var3 & 255)))) + " 00      ";
      if (!var8.equals(this.mUsbSonTimeNow)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, var6, var7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         this.mUsbSonTimeNow = var8;
      }

      Context var18 = this.mContext;
      var12 = SourceConstantsDef.SOURCE_ID.VIDEO.toString();
      byte[] var19 = var11.getBytes();
      this.sendMediaMsg1(var18, var12, DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var19));
   }
}
