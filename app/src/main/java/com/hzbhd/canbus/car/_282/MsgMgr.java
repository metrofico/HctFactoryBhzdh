package com.hzbhd.canbus.car._282;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.DriveDataActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MsgMgr extends AbstractMsgMgr {
   private static final String SHARE_281_RADAR_DISPLAY = "share_281_radar_display";
   private static final String SHARE_281_RADAR_DISTANCE = "share_281_radar_distance";
   private static final String SHARE_281_RADAR_VOLUME = "share_281_radar_volume";
   private static final String SHARE_281_RADAR_VOLUME_SWITCH = "share_281_radar_volume_switch";
   static final String SHARE_281_SHOW_RADAR = "share_281_show_radar";
   private boolean isShowing = false;
   private byte[] m0x07Data;
   private int[] m0x14Data;
   private int[] m0x15Data;
   private int[] m0x17Data;
   private byte[] m0x19Data;
   private int[] m0x26Data;
   private int[] m0x27Data;
   private int[] m0x29Data;
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private Context mContext;
   private int mDay;
   private int mDifferent;
   private RelativeLayout mFloatView;
   private int[] mFrontRadarDataNow;
   private int mHour;
   private boolean mIsAirFirst = true;
   private boolean mIsDoorFirst = true;
   private WindowManager.LayoutParams mLayoutParams;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private int mMinute;
   private int mMonth;
   private boolean mPanoramicStatus;
   private boolean mPanoramicStatus2;
   private boolean mPm2p5InsideCheck;
   private boolean mPm2p5OutsideCheck;
   private int mPm2p5Warning;
   private boolean mRearCameraStatus;
   private int[] mRearRadarDataNow;
   private boolean mRightCameraStatus;
   private boolean mRightCameraStatus2;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private Runnable mRunnable;
   private int mSecond;
   private int[] mVersionInfoNow;
   private WindowManager mWindowManager;
   private int mYear;

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

   private void dismissView() {
      WindowManager var2 = this.mWindowManager;
      if (var2 != null) {
         RelativeLayout var1 = this.mFloatView;
         if (var1 != null) {
            var2.removeView(var1);
            this.isShowing = false;
         }
      }

   }

   private int getAirWhat() {
      int[] var4 = new int[5];
      int[] var2 = this.mCanBusInfoInt;
      var4[0] = var2[2];
      var4[1] = var2[3];
      var4[2] = var2[4];
      var4[3] = var2[5];
      var4[4] = var2[6] & 249;
      int[] var3 = new int[]{var2[8], var2[9]};
      short var1;
      if (!Arrays.equals(this.mAirFrontDataNow, var4)) {
         var1 = 1001;
      } else if (!Arrays.equals(this.mAirRearDataNow, var3)) {
         var1 = 1002;
      } else {
         var1 = -1;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var4, 5);
      this.mAirRearDataNow = Arrays.copyOf(var3, 2);
      return var1;
   }

   private String getMainDisplay(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "null_value" : "_282_14_0_10_2";
         } else {
            return "_282_14_0_10_1";
         }
      } else {
         return "invalid";
      }
   }

   private String getOpenClose(boolean var1) {
      String var2;
      if (var1) {
         var2 = "open";
      } else {
         var2 = "close";
      }

      return var2;
   }

   private String getPm2p5Level(int var1) {
      switch (var1) {
         case 1:
            return CommUtil.getStrByResId(this.mContext, "pm_excellent");
         case 2:
            return CommUtil.getStrByResId(this.mContext, "pm_good");
         case 3:
            return CommUtil.getStrByResId(this.mContext, "pm_mild_pollution");
         case 4:
            return CommUtil.getStrByResId(this.mContext, "pm_moderately_polluted");
         case 5:
            return CommUtil.getStrByResId(this.mContext, "pm_heavy_pollution");
         case 6:
            return CommUtil.getStrByResId(this.mContext, "pm_severe_pollution");
         default:
            return "";
      }
   }

   private String getWarning(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : CommUtil.getStrByResId(this.mContext, "_282_14_5_2");
         } else {
            return CommUtil.getStrByResId(this.mContext, "_282_14_5_1");
         }
      } else {
         return CommUtil.getStrByResId(this.mContext, "_253_normal");
      }
   }

   private void initWindow() {
      if (this.mWindowManager == null) {
         this.mWindowManager = (WindowManager)this.mContext.getSystemService("window");
      }

      if (this.mLayoutParams == null) {
         WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
         this.mLayoutParams = var1;
         var1.type = 2002;
         this.mLayoutParams.gravity = 17;
         this.mLayoutParams.width = -2;
         this.mLayoutParams.height = -2;
      }

      if (this.mFloatView == null) {
         RelativeLayout var2 = (RelativeLayout)LayoutInflater.from(this.mContext).inflate(2131558742, (ViewGroup)null);
         this.mFloatView = var2;
         var2.setOnClickListener(new View.OnClickListener(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.mFloatView.removeCallbacks(this.this$0.mRunnable);
               this.this$0.mFloatView.post(this.this$0.mRunnable);
               this.this$0.dismissView();
            }
         });
      }

      if (this.mRunnable == null) {
         this.mRunnable = new Runnable(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               this.this$0.dismissView();
            }
         };
      }

   }

   private boolean is0x07DataChange() {
      if (Arrays.equals(this.m0x07Data, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x07Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x14DataChange() {
      if (Arrays.equals(this.m0x14Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x14Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x15DataChange() {
      if (Arrays.equals(this.m0x15Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x15Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x17DataChange() {
      if (Arrays.equals(this.m0x17Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x17Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x19DataChange() {
      if (Arrays.equals(this.m0x19Data, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x19Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x26DataChange() {
      if (Arrays.equals(this.m0x26Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x26Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x27DataChange() {
      if (Arrays.equals(this.m0x27Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x27Data = Arrays.copyOf(var1, var1.length);
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

   private boolean isDoorDataChange() {
      if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
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

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return GeneralAirData.power ^ true;
      } else {
         return false;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void openDriveData() {
      if (!SystemUtil.isForeground(this.mContext, DriveDataActivity.class.getName())) {
         this.startDrivingDataActivity(this.mContext, 0);
      }

   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private String resolveAirTemp(int var1) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 31) {
         return "HI";
      } else if (var1 >= 1 && var1 <= 29) {
         float var2 = (float)(var1 + 35) / 2.0F;
         return GeneralAirData.fahrenheit_celsius ? (new DecimalFormat("0.0")).format((double)(var2 * 9.0F / 5.0F + 32.0F)) + this.getTempUnitF(this.mContext) : var2 + this.getTempUnitC(this.mContext);
      } else {
         return "";
      }
   }

   private String resolveOutDoorTem() {
      float var1 = (float)this.mCanBusInfoInt[7] / 2.0F - 40.0F;
      return GeneralAirData.fahrenheit_celsius ? (new DecimalFormat("0.0")).format((double)(var1 * 9.0F / 5.0F + 32.0F)) + this.getTempUnitF(this.mContext) : var1 + this.getTempUnitC(this.mContext);
   }

   private void setAirData0x28() {
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      this.setOutDoorTem();
      int var1 = this.getAirWhat();
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(this.bytesExpectOneByte(this.mCanBusInfoByte, 7))) {
         if (!this.isFirst()) {
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[5]);
            GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_lock = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) ^ true;
            GeneralAirData.rear_temperature = this.resolveAirTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
            this.updateAirActivity(this.mContext, var1);
         }
      }
   }

   private void setCameraData0x17() {
      if (this.is0x17DataChange()) {
         boolean var3 = this.mRightCameraStatus2;
         boolean var4 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         byte var2 = 0;
         if (var3 != var4) {
            var3 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            this.mRightCameraStatus2 = var3;
            this.switchFCamera(this.mContext, var3);
            short var1;
            if (this.mRightCameraStatus2) {
               var1 = 128;
            } else {
               var1 = 0;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 9, (byte)var1});
         }

         if (this.mRearCameraStatus != DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            var3 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            this.mRearCameraStatus = var3;
            this.forceReverse(this.mContext, var3);
            byte var5 = var2;
            if (this.mRearCameraStatus) {
               var5 = 64;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 9, (byte)var5});
         }
      }

   }

   private void setDoorData0x24() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      this.mRightFrontRec = var1;
      GeneralDoorData.isRightFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      this.mLeftFrontRec = var1;
      GeneralDoorData.isLeftFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      this.mRightRearRec = var1;
      GeneralDoorData.isRightRearDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      this.mLeftRearRec = var1;
      GeneralDoorData.isLeftRearDoorOpen = var1;
      if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var1 = false;
      } else {
         var1 = true;
      }

      GeneralDoorData.isBackOpen = var1;
      if (this.isDoorDataChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setDrivceSetup0x19() {
      if (this.is0x19DataChange()) {
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)));
         var2.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
         var2.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
         int var1 = DataHandleUtils.rangeNumber(this.mCanBusInfoInt[3], 15, 70);
         var2.add((new SettingUpdateEntity(2, 3, var1)).setProgress(var1 - 15));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setFrontRadarData0x1D() {
      if (this.isFrontRadarDataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setPanoramicData0x15() {
      if (this.is0x15DataChange()) {
         boolean var4 = this.mPanoramicStatus2;
         boolean var2 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         boolean var3 = true;
         int var1;
         if (var4 != var2) {
            var2 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            this.mPanoramicStatus2 = var2;
            this.forceReverse(this.mContext, var2);
            if (this.mPanoramicStatus2) {
               var1 = 64;
            } else {
               var1 = 0;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 9, (byte)var1});
         }

         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
         ArrayList var5 = new ArrayList();
         if (var1 == 2) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5.add(new PanoramicBtnUpdateEntity(0, var2));
         if (var1 == 3) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5.add(new PanoramicBtnUpdateEntity(1, var2));
         if (var1 == 4) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5.add(new PanoramicBtnUpdateEntity(2, var2));
         if (var1 == 5) {
            var2 = var3;
         } else {
            var2 = false;
         }

         var5.add(new PanoramicBtnUpdateEntity(3, var2));
         GeneralParkData.dataList = var5;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setPm2p5Data0x14() {
      if (this.is0x14DataChange()) {
         boolean var3 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         boolean var4 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         ArrayList var6 = new ArrayList();
         var6.add(new SettingUpdateEntity(1, 0, this.getOpenClose(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
         var6.add(new SettingUpdateEntity(1, 1, this.getOpenClose(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]))));
         var6.add(new SettingUpdateEntity(1, 2, this.getOpenClose(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
         var6.add(new SettingUpdateEntity(1, 3, this.getOpenClose(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
         String var5;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2) == 0) {
            var5 = "_253_normal";
         } else {
            var5 = "_282_14_0_32";
         }

         var6.add(new SettingUpdateEntity(1, 4, var5));
         var6.add(new SettingUpdateEntity(1, 5, this.getMainDisplay(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
         var6.add(new SettingUpdateEntity(1, 6, this.getOpenClose(var3)));
         var6.add(new SettingUpdateEntity(1, 7, this.getOpenClose(var4)));
         this.updateGeneralSettingData(var6);
         this.updateSettingActivity((Bundle)null);
         if (this.mPm2p5InsideCheck != var3) {
            this.mPm2p5InsideCheck = var3;
            if (var3) {
               this.openDriveData();
            }
         }

         if (this.mPm2p5OutsideCheck != var4) {
            this.mPm2p5OutsideCheck = var4;
            if (var4) {
               this.openDriveData();
            }
         }

         ArrayList var7 = new ArrayList();
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
         int[] var8 = this.mCanBusInfoInt;
         var1 = var1 * 256 + var8[3];
         int var2 = var8[5] * 16 + DataHandleUtils.getIntFromByteWithBit(var8[4], 4, 4);
         String var9 = "";
         if (var1 == 4095) {
            var5 = "";
         } else {
            var5 = Integer.toString(var1);
         }

         var7.add(new DriverUpdateEntity(0, 0, var5));
         var7.add(new DriverUpdateEntity(0, 1, this.getPm2p5Level(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3))));
         if (var2 == 4095) {
            var5 = var9;
         } else {
            var5 = Integer.toString(var2);
         }

         var7.add(new DriverUpdateEntity(0, 2, var5));
         var7.add(new DriverUpdateEntity(0, 3, this.getPm2p5Level(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 3))));
         this.updateGeneralDriveData(var7);
         this.updateDriveDataActivity((Bundle)null);
         var2 = this.mPm2p5Warning;
         var1 = this.mCanBusInfoInt[7];
         if (var2 != var1) {
            this.mPm2p5Warning = var1;
            if (var1 == 1) {
               this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
                  final MsgMgr this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void callback() {
                     this.this$0.showWindow();
                  }
               });
            }
         }
      }

   }

   private void setRearRadarData0x1E() {
      if (this.isRearRadarDataChange()) {
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
         SharePreUtil.setBoolValue(this.mContext, "share_281_show_radar", DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]));
         String var5 = Integer.toString(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3));
         boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         String var4 = "open";
         String var7;
         if (var1) {
            var7 = "open";
         } else {
            var7 = "close";
         }

         String var3;
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
            var3 = "far";
         } else {
            var3 = "near";
         }

         if (!DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
            var4 = "close";
         }

         SharePreUtil.setStringValue(this.mContext, "share_281_radar_volume", var5);
         SharePreUtil.setStringValue(this.mContext, "share_281_radar_display", var7);
         SharePreUtil.setStringValue(this.mContext, "share_281_radar_distance", var3);
         SharePreUtil.setStringValue(this.mContext, "share_281_radar_volume_switch", var4);
         ArrayList var6 = new ArrayList();
         var6.add(new SettingUpdateEntity(4, 1, var7));
         var6.add(new SettingUpdateEntity(4, 2, var3));
         var6.add(new SettingUpdateEntity(4, 3, var4));
         var6.add((new SettingUpdateEntity(4, 0, var5)).setValueStr(true));
         this.updateGeneralSettingData(var6);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setTrackData0x29() {
      if (this.is0x29DataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 7799, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setTtsData0x06() {
   }

   private void setVehicleInfo0x26() {
      if (this.is0x26DataChange()) {
         ArrayList var1 = new ArrayList();
         StringBuilder var2 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(1, 0, var2.append(var3[2] * 256 + var3[3]).append(" km/h").toString()));
         var2 = new StringBuilder();
         var3 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(1, 1, var2.append(var3[4] * 256 + var3[5]).append(" rpm").toString()));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
         int[] var4 = this.mCanBusInfoInt;
         this.updateSpeedInfo(var4[2] * 256 + var4[3]);
      }

   }

   private void setVehicleSetupData0x27() {
      if (this.is0x27DataChange()) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(3, 0, this.mCanBusInfoInt[2]));
         var1.add(new SettingUpdateEntity(3, 1, this.mCanBusInfoInt[3]));
         var1.add(new SettingUpdateEntity(3, 2, this.mCanBusInfoInt[4]));
         var1.add(new SettingUpdateEntity(3, 3, this.mCanBusInfoInt[5]));
         var1.add(new SettingUpdateEntity(3, 4, this.mCanBusInfoInt[6]));
         var1.add(new SettingUpdateEntity(3, 5, this.mCanBusInfoInt[7]));
         var1.add(new SettingUpdateEntity(3, 6, this.mCanBusInfoInt[8]));
         var1.add(new SettingUpdateEntity(3, 7, this.mCanBusInfoInt[9]));
         var1.add(new SettingUpdateEntity(3, 8, this.mCanBusInfoInt[10]));
         var1.add(new SettingUpdateEntity(3, 9, this.mCanBusInfoInt[11]));
         var1.add((new SettingUpdateEntity(3, 10, this.mCanBusInfoInt[12])).setProgress(this.mCanBusInfoInt[12]));
         var1.add(new SettingUpdateEntity(3, 11, this.mCanBusInfoInt[13]));
         var1.add(new SettingUpdateEntity(3, 12, this.mCanBusInfoInt[14]));
         var1.add((new SettingUpdateEntity(3, 13, this.mCanBusInfoInt[15])).setProgress(this.mCanBusInfoInt[15]));
         var1.add((new SettingUpdateEntity(3, 14, this.mCanBusInfoInt[16])).setProgress(this.mCanBusInfoInt[16]));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setVersionInfo0x30() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWheelKey0x20() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick1(0);
            break;
         case 1:
            this.realKeyClick1(7);
         case 2:
         case 3:
         case 5:
         case 6:
         case 9:
         case 18:
         case 28:
         default:
            break;
         case 4:
            this.realKeyClick1(8);
            break;
         case 7:
            this.realKeyClick1(47);
            break;
         case 8:
            this.realKeyClick1(21);
            break;
         case 10:
            this.realKeyClick1(48);
            break;
         case 11:
            this.realKeyClick1(20);
            break;
         case 12:
            this.realKeyClick1(14);
            break;
         case 13:
            this.realKeyClick1(14);
            break;
         case 14:
            this.realKeyClick1(4);
            break;
         case 15:
            this.realKeyClick1(4);
            break;
         case 16:
            this.realKeyClick1(2);
            break;
         case 17:
            this.realKeyClick1(2);
            break;
         case 19:
            this.realKeyClick1(187);
            break;
         case 20:
            this.realKeyClick1(187);
            break;
         case 21:
            this.realKeyClick1(52);
            break;
         case 22:
            this.realKeyClick1(52);
            break;
         case 23:
            this.realKeyClick1(50);
            break;
         case 24:
            this.realKeyClick1(50);
            break;
         case 25:
            this.realKeyClick1(3);
            break;
         case 26:
            this.realKeyClick1(134);
            break;
         case 27:
            this.realKeyClick1(152);
            break;
         case 29:
            this.realKeyClick1(15);
            break;
         case 30:
            this.realKeyClick1(205);
            break;
         case 31:
            if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
               this.finishActivity();
            } else {
               AirActivity.mIsClickOpen = true;
               Intent var1 = new Intent(this.mContext, AirActivity.class);
               var1.setFlags(268435456);
               this.mContext.startActivity(var1);
            }
      }

   }

   private void setWindowSetup0x07() {
      if (this.is0x07DataChange()) {
         ArrayList var4 = new ArrayList();
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
         byte var2 = 0;
         var4.add(new SettingUpdateEntity(0, 0, var1));
         var4.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
         var4.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
         var4.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
         boolean var3;
         if (this.mRightCameraStatus != DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            var3 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            this.mRightCameraStatus = var3;
            this.switchFCamera(this.mContext, var3);
            short var5;
            if (this.mRightCameraStatus) {
               var5 = 128;
            } else {
               var5 = 0;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 9, (byte)var5});
         }

         if (this.mPanoramicStatus != DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            var3 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            this.mPanoramicStatus = var3;
            this.forceReverse(this.mContext, var3);
            byte var6 = var2;
            if (this.mPanoramicStatus) {
               var6 = 64;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 9, (byte)var6});
         }
      }

   }

   private void showWindow() {
      this.initWindow();
      if (!this.isShowing) {
         this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
         this.isShowing = true;
      }

      this.mFloatView.removeCallbacks(this.mRunnable);
      this.mFloatView.postDelayed(this.mRunnable, 3000L);
   }

   private void updateRadarSetup() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(4, 0, SharePreUtil.getStringValue(this.mContext, "share_281_radar_volume", "1"))).setValueStr(true));
      var1.add(new SettingUpdateEntity(4, 1, SharePreUtil.getStringValue(this.mContext, "share_281_radar_display", "open")));
      var1.add(new SettingUpdateEntity(4, 2, SharePreUtil.getStringValue(this.mContext, "share_281_radar_distance", "near")));
      var1.add(new SettingUpdateEntity(4, 3, SharePreUtil.getStringValue(this.mContext, "share_281_radar_volume_switch", "open")));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   public void cameraDestroy() {
      super.cameraDestroy();
      CanbusMsgSender.sendMsg(new byte[]{22, 9, 0});
   }

   public void cameraInfoChange() {
      super.cameraInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, 9, -128});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 6) {
         if (var3 != 7) {
            if (var3 != 20) {
               if (var3 != 21) {
                  if (var3 != 23) {
                     if (var3 != 25) {
                        if (var3 != 32) {
                           if (var3 != 36) {
                              if (var3 != 29) {
                                 if (var3 != 30) {
                                    switch (var3) {
                                       case 38:
                                          this.setVehicleInfo0x26();
                                          break;
                                       case 39:
                                          this.setVehicleSetupData0x27();
                                          break;
                                       case 40:
                                          this.setAirData0x28();
                                          break;
                                       case 41:
                                          this.setTrackData0x29();
                                    }
                                 } else {
                                    this.setRearRadarData0x1E();
                                 }
                              } else {
                                 this.setFrontRadarData0x1D();
                              }
                           } else {
                              this.setDoorData0x24();
                           }
                        } else {
                           this.setWheelKey0x20();
                        }
                     } else {
                        this.setDrivceSetup0x19();
                     }
                  } else {
                     this.setCameraData0x17();
                  }
               } else {
                  this.setPanoramicData0x15();
               }
            } else {
               this.setPm2p5Data0x14();
            }
         } else {
            this.setWindowSetup0x07();
         }
      } else {
         this.setTtsData0x06();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var9 = Calendar.getInstance().get(7);
      short var14;
      if (this.mYear == var2 && this.mMonth == var3 && this.mDay == var4) {
         if (this.mHour == var5 && this.mMinute == var6) {
            var14 = 0;
         } else {
            this.mHour = var5;
            this.mMinute = var6;
            this.mSecond = var7;
            var14 = 64;
         }
      } else {
         this.mYear = var2;
         this.mMonth = var3;
         this.mDay = var4;
         var14 = 128;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 118, (byte)var2, (byte)var3, (byte)var4, (byte)var8, (byte)var6, (byte)var7, (byte)(var9 - 1 | var14)});
   }

   byte[] get0x07Data() {
      if (ArrayUtils.isEmpty(this.m0x07Data)) {
         this.m0x07Data = new byte[4];
      }

      byte[] var1 = this.m0x07Data;
      return Arrays.copyOf(var1, var1.length - 1);
   }

   byte[] get0x19Data() {
      if (ArrayUtils.isEmpty(this.m0x19Data)) {
         this.m0x19Data = new byte[4];
      }

      return this.m0x19Data;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      this.updateRadarSetup();
   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
