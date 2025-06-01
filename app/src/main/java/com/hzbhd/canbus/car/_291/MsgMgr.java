package com.hzbhd.canbus.car._291;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.PA6Utils;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.smart.SmartPowerActivity;
import com.hzbhd.canbus.car_cus._291.AirActivity;
import com.hzbhd.canbus.car_cus._291.AirSeatView;
import com.hzbhd.canbus.car_cus._291.GeneralDzPQData;
import com.hzbhd.canbus.car_cus._291.MainActivity;
import com.hzbhd.canbus.car_cus._291.MainSettingActivity;
import com.hzbhd.canbus.car_cus._291.RadarView;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
   private static final int SEND_NORMAL_MESSAGE = 2;
   private static final String SHARE_291_SMARTPOWER_DATA3 = "_291_smart_data3";
   private static final String SHARE_291_SMARTPOWER_DATA4 = "_291_smart_data4";
   private static final String SHARE_291_SMARTPOWER_DATA5 = "_291_smart_data5";
   private static final String SHARE_291_SMARTPOWER_DATA6 = "_291_smart_data6";
   private static final String SHARE_291_SMARTPOWER_MODE = "_291_smart_mode";
   private int data3;
   private int data4;
   private int data5;
   private int data6;
   private boolean isFirstAirSeat = true;
   private int left_front_hot;
   private int[] m0x12Data;
   private int[] m0x13Data;
   private int[] m0x42Data;
   private int[] m0x47Data;
   private boolean mAirFirst = true;
   private AirSeatView mAirSeatView;
   private boolean mBackStatus;
   private boolean mBatteryStatus;
   private boolean mBeltStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   DecimalFormat mDecimalFormat = new DecimalFormat("#####00");
   private boolean mDoorFirst = true;
   private boolean mFrontStatus;
   private boolean mFuelStaus;
   private Handler mHandler;
   private HandlerThread mHandlerThread;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private int mLeftWindLevelNow;
   private int mLeftWindLevelRec;
   private int mOutDoorTemperature;
   private CusPanoramicView mPanoramicView;
   private int[] mParkData;
   private RadarView mRadarView;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private int mRightWindLevelNow;
   private int mRightWindLevelRec;
   private int mVehicleSpeedData;
   private boolean mWashingFluidStatus;
   private int mWheelKeyData;
   private int mode;
   private int mode_int;
   int result = 0;
   private int right_front_hot;
   private int version;

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

   private void canBusInfo0x55(byte[] var1) {
      int var2 = this.mCanBusInfoInt[1];
      if (var2 != 18) {
         if (var2 != 19) {
            if (var2 != 66) {
               if (var2 != 71) {
                  if (var2 != 240) {
                     if (var2 != 114) {
                        if (var2 == 115) {
                           if (this.isAirMsgRepeat(var1)) {
                              return;
                           }

                           this.setAirDoor0x73();
                        }
                     } else {
                        this.setKeySpeedTrackRadar0x72();
                     }
                  } else {
                     this.setCanVersion0xF0();
                  }
               } else {
                  this.setParkingSystem0x47();
               }
            } else {
               this.setRadarInfo0x42();
            }
         } else {
            this.setVehicleInfoTwo0x13();
         }
      } else {
         this.setVehicleInfoOne0x12();
      }

   }

   private void canBusInfo0x57(byte[] var1) {
      GeneralDzSmartData.mode = this.mCanBusInfoInt[1];
      GeneralDzSmartData.mode_int = this.getModeInt(this.mCanBusInfoInt);
      GeneralDzSmartData.data3 = this.mCanBusInfoInt[2];
      GeneralDzSmartData.data4 = this.mCanBusInfoInt[3];
      GeneralDzSmartData.data5 = this.mCanBusInfoInt[4];
      GeneralDzSmartData.data6 = this.mCanBusInfoInt[5];
      GeneralDzSmartData.version = this.mCanBusInfoInt[7];
      if (this.mode != GeneralDzSmartData.mode || this.mode_int != GeneralDzSmartData.mode_int || this.data3 != GeneralDzSmartData.data3 || this.data4 != GeneralDzSmartData.data4 || this.data5 != GeneralDzSmartData.data5 || this.data6 != GeneralDzSmartData.data6 || this.version != GeneralDzSmartData.version) {
         GeneralDzSmartData.show = true;
         this.mode = GeneralDzSmartData.mode;
         this.mode_int = GeneralDzSmartData.mode_int;
         this.data3 = GeneralDzSmartData.data3;
         this.data4 = GeneralDzSmartData.data4;
         this.data5 = GeneralDzSmartData.data5;
         this.data6 = GeneralDzSmartData.data6;
         this.version = GeneralDzSmartData.version;
         this.updateSmartActivity();
         this.updateSystemUIDrivingPattern(GeneralDzSmartData.mode);
         SharePreUtil.setIntValue(this.mContext, "_291_smart_mode", GeneralDzSmartData.mode);
         SharePreUtil.setIntValue(this.mContext, "_291_smart_data3", GeneralDzSmartData.data3);
         SharePreUtil.setIntValue(this.mContext, "_291_smart_data4", GeneralDzSmartData.data4);
         SharePreUtil.setIntValue(this.mContext, "_291_smart_data5", GeneralDzSmartData.data5);
         SharePreUtil.setIntValue(this.mContext, "_291_smart_data6", GeneralDzSmartData.data6);
      }

   }

   private int distanceToLocation(int var1) {
      if (var1 >= 0 && var1 < 13) {
         return 10;
      } else if (var1 >= 13 && var1 < 26) {
         return 9;
      } else if (var1 >= 26 && var1 < 39) {
         return 8;
      } else if (var1 >= 39 && var1 < 52) {
         return 7;
      } else if (var1 >= 52 && var1 < 65) {
         return 6;
      } else if (var1 >= 65 && var1 < 78) {
         return 5;
      } else if (var1 >= 78 && var1 < 91) {
         return 4;
      } else if (var1 >= 91 && var1 < 104) {
         return 3;
      } else if (var1 >= 104 && var1 < 117) {
         return 2;
      } else if (var1 >= 117 && var1 < 128) {
         return 1;
      } else {
         return var1 >= 128 ? 255 : 0;
      }
   }

   private int distanceToLocationLeftRight(int var1) {
      return var1 > 127 ? 255 : var1 / 12 + 1;
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

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private String getLightStr(int var1) {
      if (var1 == 0) {
         return this.mContext.getString(2131761264);
      } else {
         return var1 == 100 ? this.mContext.getString(2131761263) : var1 + "";
      }
   }

   public static int getLsb(int var0) {
      return (var0 & -1) >> 0 & 255;
   }

   public static int getMidLsb(int var0) {
      return (var0 & -1) >> 8 & 255;
   }

   public static int getMidMsb(int var0) {
      return (var0 & -1) >> 16 & 255;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private int getModeInt(int[] var1) {
      if (var1.length <= 4) {
         return GeneralDzSmartData.mode;
      } else {
         int var4 = var1[1];
         int var2 = var1[2];
         byte var3 = 0;
         if (var4 == 3 || var4 == 4) {
            var2 = var1[3];
         }

         if (var4 == 3 || var4 == 0) {
            var3 = 4;
         }

         return DataHandleUtils.getIntFromByteWithBit(var2, var3, 4) - 1;
      }
   }

   public static int getMsb(int var0) {
      return (var0 & -1) >> 24 & 255;
   }

   private CusPanoramicView getMyPanoramicView() {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (CusPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      }

      return this.mPanoramicView;
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private void initHandler(Context var1) {
      HandlerThread var2 = new HandlerThread("MyApplication");
      this.mHandlerThread = var2;
      var2.start();
      this.mHandler = new Handler(this, this.mHandlerThread.getLooper(), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 1) {
               this.this$0.sendMediaMsg(this.val$context, SourceConstantsDef.SOURCE_ID.values()[var1.arg1].name(), (byte[])var1.obj);
            } else if (var1.what == 2) {
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };
   }

   private void initPower(Context var1) {
      (new Thread(new MsgMgr$$ExternalSyntheticLambda0(var1))).start();
   }

   private boolean is0x12DataChange() {
      int[] var1 = new int[4];
      int[] var2 = this.mCanBusInfoInt;
      var1[0] = var2[5];
      var1[1] = var2[6];
      var1[2] = var2[9];
      var1[3] = var2[10];
      if (Arrays.equals(this.m0x12Data, var1)) {
         return false;
      } else {
         this.m0x12Data = Arrays.copyOf(var1, 4);
         return true;
      }
   }

   private boolean is0x13DataChange() {
      if (Arrays.equals(this.m0x13Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x13Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x42DataChange() {
      if (Arrays.equals(this.m0x42Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x42Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x47DataChange() {
      if (Arrays.equals(this.m0x47Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x47Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataRepeat() {
      if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus && GeneralDoorData.isFuelWarning == this.mFuelStaus && GeneralDoorData.isBatteryWarning == this.mBatteryStatus && GeneralDoorData.isSeatBeltTie == this.mBeltStatus && GeneralDoorData.isWashingFluidWarning == this.mWashingFluidStatus) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mFuelStaus = GeneralDoorData.isFuelWarning;
         this.mBatteryStatus = GeneralDoorData.isBatteryWarning;
         this.mBeltStatus = GeneralDoorData.isSeatBeltTie;
         this.mWashingFluidStatus = GeneralDoorData.isWashingFluidWarning;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mDoorFirst) {
         this.mDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen && !GeneralDoorData.isFuelWarning && !GeneralDoorData.isBatteryWarning && !GeneralDoorData.isWashingFluidWarning) {
            return false;
         }
      }

      return true;
   }

   private boolean isFirst() {
      if (this.mAirFirst) {
         this.mAirFirst = false;
         if (!GeneralAirData.power) {
            return true;
         }
      }

      return false;
   }

   private boolean isOutDoorTemperatureChange() {
      int var1 = this.mOutDoorTemperature;
      int var2 = this.mCanBusInfoInt[8];
      if (var1 == var2) {
         return false;
      } else {
         this.mOutDoorTemperature = var2;
         return true;
      }
   }

   private boolean isParkDataChange() {
      int[] var1 = this.mCanBusInfoInt;
      var1 = Arrays.copyOfRange(var1, 6, var1.length);
      if (Arrays.equals(this.mParkData, var1)) {
         return false;
      } else {
         this.mParkData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isVehicleSpeedDataChange() {
      int var2 = this.mVehicleSpeedData;
      int var1 = this.mCanBusInfoInt[3];
      if (var2 == var1) {
         return false;
      } else {
         this.mVehicleSpeedData = var1;
         return true;
      }
   }

   private boolean isWheelKeyDataChange() {
      int var1 = this.mWheelKeyData;
      int var2 = this.mCanBusInfoInt[4];
      if (var1 == var2) {
         return false;
      } else {
         this.mWheelKeyData = var2;
         return true;
      }
   }

   // $FF: synthetic method
   static void lambda$initPower$0(Context var0) {
      try {
         GeneralDzSmartData.mode = SharePreUtil.getIntValue(var0, "_291_smart_mode", 1);
         GeneralDzSmartData.data3 = SharePreUtil.getIntValue(var0, "_291_smart_data3", 0);
         GeneralDzSmartData.data4 = SharePreUtil.getIntValue(var0, "_291_smart_data4", 0);
         GeneralDzSmartData.data5 = SharePreUtil.getIntValue(var0, "_291_smart_data5", 0);
         GeneralDzSmartData.data6 = SharePreUtil.getIntValue(var0, "_291_smart_data6", 0);
         MessageSender.sendDzMsg(GeneralDzSmartData.mode, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
      } catch (Exception var1) {
         MessageSender.sendDzMsg(1, 0, 0, 0, 0);
         var1.printStackTrace();
      }

   }

   private void realKeyLongClick2(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else if (var1 >= 18 && var1 <= 26) {
         var2 = var1 + this.getTempUnitC(this.mContext);
      } else {
         var2 = " ";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[8];
      String var2;
      if (var1 >= 1 && var1 <= 255) {
         var2 = (float)var1 * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = " ";
      }

      return var2;
   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var5 = Message.obtain();
         var5.what = 1;
         var5.arg1 = var1;
         var5.obj = var2;
         this.mHandler.sendMessageDelayed(var5, var3);
      }
   }

   private void sendMediaMessage(Object var1) {
      this.sendMediaMessage(var1, 0L);
   }

   private void sendMediaMessage(Object var1, long var2) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var4 = Message.obtain();
         var4.what = 2;
         var4.obj = var1;
         this.mHandler.sendMessageDelayed(var4, var2);
      }
   }

   private void setAirDoor0x73() {
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9])) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[9]);
         this.mRightFrontRec = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         this.mLeftFrontRec = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         this.mLeftRearRec = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         this.mRightRearRec = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
         if (this.isDoorDataRepeat() && this.isDoorFirst()) {
            this.updateDoorView(this.mContext);
         }
      }

      byte[] var2 = this.bytesExpectOneByte(this.bytesExpectOneByte(this.mCanBusInfoByte, 9), 8);
      if (this.isOutDoorTemperatureChange()) {
         this.setOutDoorTem();
      }

      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(var2)) {
         CommUtil.printHexString("scyscyscy：", var2);
         if (!this.isFirst()) {
            GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.auto_2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
            GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
            byte var1 = ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature();
            this.mLeftWindLevelRec = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[var1 + 6], 0, 3);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
            this.mRightWindLevelRec = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7 - var1], 0, 3);
            GeneralAirData.front_wind_level = this.mLeftWindLevelRec;
            GeneralAirData.front_right_wind_level = this.mRightWindLevelRec;
            this.updateAirActivity("");
            if (PA6Utils.getAirShow(this.mContext) && !SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
               Intent var3 = new Intent();
               var3.addFlags(268435456);
               var3.setComponent(new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._291.AirActivity"));
               var3.putExtra("type", "SETUP");
               this.mContext.startActivity(var3);
            }

            if (this.right_front_hot != GeneralAirData.front_right_seat_heat_level || this.left_front_hot != GeneralAirData.front_left_seat_heat_level) {
               this.right_front_hot = GeneralAirData.front_right_seat_heat_level;
               this.left_front_hot = GeneralAirData.front_left_seat_heat_level;
               if (!this.isFirstAirSeat) {
                  this.updateDZAirSeatView(this.mContext);
               } else {
                  this.isFirstAirSeat = false;
               }
            }

         }
      }
   }

   private void setCanVersion0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setKeySpeedTrackRadar0x72() {
      GeneralDzData.speed = this.mCanBusInfoInt[3];
      GeneralDzData.gears = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDzData.handBrake = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      if (this.isVehicleSpeedDataChange()) {
         ArrayList var3 = new ArrayList();
         var3.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + "KM/H"));
         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

      int var1;
      if (this.isWheelKeyDataChange()) {
         var1 = this.mCanBusInfoInt[4];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 5) {
                        if (var1 != 6) {
                           if (var1 != 15) {
                              switch (var1) {
                                 case 8:
                                    this.realKeyLongClick2(45);
                                    break;
                                 case 9:
                                    this.realKeyLongClick2(46);
                                    break;
                                 case 10:
                                    this.realKeyLongClick2(2);
                              }
                           } else {
                              this.realKeyLongClick2(201);
                           }
                        } else {
                           this.realKeyLongClick2(15);
                        }
                     } else {
                        this.realKeyLongClick2(14);
                     }
                  } else {
                     this.realKeyLongClick2(3);
                  }
               } else {
                  this.realKeyLongClick2(8);
               }
            } else {
               this.realKeyLongClick2(7);
            }
         } else {
            this.realKeyLongClick2(0);
         }
      }

      GeneralDzPQData.vehicleLight = this.getLightStr(this.mCanBusInfoInt[9]);
      int[] var5;
      if (this.isParkDataChange()) {
         var5 = this.mCanBusInfoInt;
         var1 = var5[7];
         int var2 = var5[6];
         short var4;
         if (var2 > 0 && var1 == 0) {
            var1 = var2;
            var4 = 128;
         } else {
            var4 = 0;
         }

         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1((byte)var1, (byte)var4, 0, 140, 16);
         this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void callback() {
               this.this$0.getMyPanoramicView().updateParkUi();
            }
         });
      }

      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      var5 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var5[8], var5[9], var5[10], var5[11]);
      var5 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarDistanceData(var5[12], var5[13], var5[14], var5[15]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      if (this.getReverseState()) {
         GeneralDzData.show_radar = false;
         this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void callback() {
               this.this$0.getMyPanoramicView().refreRadarUi();
            }
         });
      } else {
         this.updateDZRadarView(this.mContext);
      }

   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setParkingSystem0x47() {
      if (this.is0x47DataChange()) {
         GeneralDzPQData.vehicleOut = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15]);
         GeneralDzPQData.vehicleIn = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[15]);
         GeneralDzPQData.vehicleRadarMute = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[15]);
         this.updateMainSettingActivity();
      }

   }

   private void setRadarInfo0x42() {
      if (this.is0x42DataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         RadarInfoUtil.setRightRadarLocationData(10, this.distanceToLocationLeftRight(this.mCanBusInfoInt[2]), this.distanceToLocationLeftRight(this.mCanBusInfoInt[3]), this.distanceToLocationLeftRight(this.mCanBusInfoInt[4]), this.distanceToLocationLeftRight(this.mCanBusInfoInt[5]));
         RadarInfoUtil.setLeftRadarLocationData(10, this.distanceToLocationLeftRight(this.mCanBusInfoInt[6]), this.distanceToLocationLeftRight(this.mCanBusInfoInt[7]), this.distanceToLocationLeftRight(this.mCanBusInfoInt[8]), this.distanceToLocationLeftRight(this.mCanBusInfoInt[9]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVehicleInfoOne0x12() {
      if (this.is0x12DataChange()) {
         GeneralDzPQData.vehicleFuel = this.mCanBusInfoInt[5] + "." + this.mCanBusInfoInt[6] + " L/100KM";
         GeneralDzPQData.vehicleOil = this.mCanBusInfoInt[9] + " L";
         GeneralDzPQData.vehicleV = this.mCanBusInfoInt[10] + "." + this.mCanBusInfoInt[11] + " V";
         this.updateMainActivity(1);
      }

      GeneralDoorData.isShowFuelWarning = true;
      GeneralDoorData.isShowBatteryWarning = true;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isShowWashingFluidWarning = true;
      GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
      GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
      this.updateMainActivity(2);
   }

   private void setVehicleInfoTwo0x13() {
      if (this.is0x13DataChange()) {
         StringBuilder var1 = new StringBuilder();
         int[] var2 = this.mCanBusInfoInt;
         GeneralDzPQData.vehicleDistance = var1.append(var2[2] * 256 * 256 + var2[3] * 256 + var2[4]).append(" KM").toString();
         StringBuilder var4 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         GeneralDzPQData.vehicleRev = var4.append(var3[10] * 256 + var3[11]).append(" RPM").toString();
         this.updateMainActivity(1);
      }

   }

   private void updateAirActivity(String var1) {
      Bundle var2 = new Bundle();
      var2.putString("update_wind_power", var1);
      if (this.getActivity() != null && this.getActivity() instanceof AirActivity) {
         this.updateActivity(var2);
      }

   }

   private void updateMainActivity(int var1) {
      Bundle var2 = new Bundle();
      var2.putInt("bundle_dezhong_what", var1);
      if (this.getActivity() != null && this.getActivity() instanceof MainActivity) {
         this.updateActivity(var2);
      }

   }

   private void updateMainSettingActivity() {
      Bundle var1 = new Bundle();
      if (this.getActivity() != null && this.getActivity() instanceof MainSettingActivity) {
         this.updateActivity(var1);
      }

   }

   private void updateSmartActivity() {
      Bundle var1 = new Bundle();
      if (this.getActivity() != null && this.getActivity() instanceof SmartPowerActivity) {
         this.updateActivity(var1);
      }

   }

   private void updateSystemUIDrivingPattern(int var1) {
      if (var1 >= 0 && var1 <= 4) {
         Intent var2 = new Intent("com.android.systemui.statusbar.action.CARMODECHANGE");
         var2.putExtra("_283_car_mode", String.valueOf(var1));
         this.mContext.sendBroadcast(var2);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      AMapUtils.getInstance().startAMap(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -46, 8}, var1);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), var1);
      var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -30}, var1);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), var1);
      var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -29}, var1);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -46, 12}, var1);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), var1);
      var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -30}, var1);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), var1);
      var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -29}, var1);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), var1);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      byte[] var4 = DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes();
      this.sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, var4));
      var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, new String(var1)).getBytes();
      this.sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -30}, var1));
      var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes();
      this.sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -29}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[0];
      if (var3 == 85) {
         this.canBusInfo0x55(var2);
      } else if (var3 == 87) {
         this.canBusInfo0x57(var2);
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte[] var18;
      if (var10 == 240) {
         var11 = System.getString(this.mContext.getContentResolver(), "reportForDiscEject");
         if (!TextUtils.isEmpty(var11)) {
            var18 = Base64.decode(var11, 0);
            this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), var18);
         }
      } else if (var10 == 32) {
         int var14;
         int var15;
         byte var16;
         label20: {
            var10 = this.getHour(var2);
            var14 = this.getMinute(var2);
            var15 = this.getSecond(var2);
            byte var17 = 6;
            if (var7 == 1) {
               var16 = 7;
            } else {
               var16 = var17;
               if (var7 != 5) {
                  break label20;
               }

               var16 = var17;
            }

            var4 = var5;
         }

         var3 = DataHandleUtils.rangeNumber(var4, 0, 99);
         var1 = (byte)var16;
         var18 = DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes();
         var18 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var18);
         this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), var18);
         var18 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, "T" + (new DecimalFormat("000")).format((long)var3) + "/" + (new DecimalFormat("000")).format((long)var6)).getBytes();
         var18 = DataHandleUtils.byteMerger(new byte[]{22, -30}, var18);
         this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), var18);
         var18 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, (new DecimalFormat("00")).format((long)var10) + ":" + (new DecimalFormat("00")).format((long)var14) + ":" + (new DecimalFormat("00")).format((long)var15)).getBytes();
         var18 = DataHandleUtils.byteMerger(new byte[]{22, -29}, var18);
         this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), var18);
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -46, 20}, var1);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), var1);
      var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -30}, var1);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), var1);
      var1 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -29}, var1);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), var1);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.initHandler(var1);
      this.initPower(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var27;
      if (var1 == 9) {
         var27 = 14;
      } else {
         var27 = 13;
      }

      var1 = (byte)var27;
      byte[] var25 = DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes();
      var25 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var25);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), var25);
      var25 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, var9 * 256 + var3 + "/" + var4).getBytes();
      var25 = DataHandleUtils.byteMerger(new byte[]{22, -30}, var25);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), var25);
      byte[] var26 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, var11 + ":" + var12 + ":" + var13).getBytes();
      var26 = DataHandleUtils.byteMerger(new byte[]{22, -29}, var26);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), var26);
   }

   public void onAMapCallBack(AMapEntity var1) {
      super.onAMapCallBack(var1);
      if (var1.getDestinationDistance() != 0) {
         byte var2;
         if (var1.getCarDirection() == 7) {
            var2 = 7;
         } else if (var1.getCarDirection() == 8) {
            var2 = 8;
         } else if (var1.getCarDirection() == 1) {
            var2 = 1;
         } else if (var1.getCarDirection() == 2) {
            var2 = 2;
         } else if (var1.getCarDirection() == 3) {
            var2 = 3;
         } else if (var1.getCarDirection() == 4) {
            var2 = 4;
         } else if (var1.getCarDirection() == 5) {
            var2 = 5;
         } else if (var1.getCarDirection() == 6) {
            var2 = 6;
         } else {
            var2 = 0;
         }

         byte var3;
         if (var1.getIcon() == 9) {
            var3 = 1;
         } else if (var1.getIcon() == 5) {
            var3 = 2;
         } else if (var1.getIcon() == 3) {
            var3 = 3;
         } else if (var1.getIcon() == 7) {
            var3 = 4;
         } else if (var1.getIcon() == 6) {
            var3 = 6;
         } else if (var1.getIcon() == 2) {
            var3 = 7;
         } else if (var1.getIcon() == 4) {
            var3 = 8;
         } else {
            var3 = 0;
         }

         if (var1.getDestinationDistance() != 0) {
            this.result = Integer.parseInt(this.mDecimalFormat.format((long)(var1.getNextDistance() * 100 / var1.getDestinationDistance())));
         } else {
            this.result = 0;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -28, -128, -128, (byte)getMsb(var1.getNextDistance() * 10), (byte)getMidMsb(var1.getNextDistance() * 10), (byte)getMidLsb(var1.getNextDistance() * 10), (byte)getLsb(var1.getNextDistance() * 10), (byte)this.result, (byte)getMsb(var1.getDestinationDistance() * 10), (byte)getMidMsb(var1.getDestinationDistance() * 10), (byte)getMidLsb(var1.getDestinationDistance() * 10), (byte)getLsb(var1.getDestinationDistance() * 10), (byte)var2, (byte)Integer.parseInt(var1.getPlanTime().substring(0, var1.getPlanTime().indexOf(":"))), (byte)Integer.parseInt(var1.getPlanTime().substring(var1.getPlanTime().indexOf(":") + 1)), (byte)Integer.parseInt(var1.getSurplusAllTimeStr().substring(0, var1.getSurplusAllTimeStr().indexOf(":"))), (byte)Integer.parseInt(var1.getSurplusAllTimeStr().substring(var1.getSurplusAllTimeStr().indexOf(":") + 1)), (byte)var3, 0, 0});
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = this.getAllBandTypeData(var2);
      byte[] var8 = DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes();
      var8 = DataHandleUtils.byteMerger(new byte[]{22, -46, var6}, var8);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), var8);
      var8 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, "P" + var1).getBytes();
      var8 = DataHandleUtils.byteMerger(new byte[]{22, -30}, var8);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), var8);
      byte[] var7 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, var3 + this.getBandUnit(var2)).getBytes();
      var7 = DataHandleUtils.byteMerger(new byte[]{22, -29}, var7);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), var7);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      byte[] var2 = DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes();
      this.sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -46, 0}, var2));
      var2 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes();
      this.sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -30}, var2));
      var2 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes();
      this.sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -29}, var2));
   }

   protected void updateDZAirSeatView(Context var1) {
      if (this.mAirSeatView == null) {
         this.mAirSeatView = new AirSeatView(var1);
      }

      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.mAirSeatView.refreshUi();
         }
      });
   }

   protected void updateDZRadarView(Context var1) {
      if (this.mRadarView == null) {
         this.mRadarView = new RadarView(var1);
      }

      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.mRadarView.refreshUi();
         }
      });
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var19;
      if (var1 == 9) {
         var19 = 14;
      } else {
         var19 = 13;
      }

      var1 = (byte)var19;
      byte[] var18 = DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes();
      var18 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var18);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), var18);
      var18 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, var9 * 256 + var3 + "/" + var4).getBytes();
      var18 = DataHandleUtils.byteMerger(new byte[]{22, -30}, var18);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), var18);
      var18 = DataHandleUtils.makeMediaInfoCenteredInBytes(13, var11 + ":" + var12 + ":" + var13).getBytes();
      var18 = DataHandleUtils.byteMerger(new byte[]{22, -29}, var18);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), var18);
   }

   public void voiceControlInfo(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -2039274493:
            if (var1.equals("rear.windows.close")) {
               var2 = 0;
            }
            break;
         case -1871342186:
            if (var1.equals("rear.right.window.close")) {
               var2 = 1;
            }
            break;
         case -1789699888:
            if (var1.equals("mirror.fold")) {
               var2 = 2;
            }
            break;
         case -1486324951:
            if (var1.equals("mirror.unfold")) {
               var2 = 3;
            }
            break;
         case -1391366141:
            if (var1.equals("skylight.open")) {
               var2 = 4;
            }
            break;
         case -1386876687:
            if (var1.equals("front.right.window.close")) {
               var2 = 5;
            }
            break;
         case -954737720:
            if (var1.equals("front.windows.close")) {
               var2 = 6;
            }
            break;
         case -910491843:
            if (var1.equals("tailgate.open")) {
               var2 = 7;
            }
            break;
         case -891288852:
            if (var1.equals("rear.right.window.open")) {
               var2 = 8;
            }
            break;
         case -861720966:
            if (var1.equals("front.windows.open")) {
               var2 = 9;
            }
            break;
         case -193868961:
            if (var1.equals("skylight.close")) {
               var2 = 10;
            }
            break;
         case 292772663:
            if (var1.equals("rear.left.window.close")) {
               var2 = 11;
            }
            break;
         case 345504582:
            if (var1.equals("front.left.window.open")) {
               var2 = 12;
            }
            break;
         case 925454385:
            if (var1.equals("front.right.window.open")) {
               var2 = 13;
            }
            break;
         case 1358234944:
            if (var1.equals("all.windows.close")) {
               var2 = 14;
            }
            break;
         case 1458598623:
            if (var1.equals("rear.windows.open")) {
               var2 = 15;
            }
            break;
         case 1828332389:
            if (var1.equals("tailgate.close")) {
               var2 = 16;
            }
            break;
         case 1949467947:
            if (var1.equals("rear.left.window.open")) {
               var2 = 17;
            }
            break;
         case 1983837698:
            if (var1.equals("all.windows.open")) {
               var2 = 18;
            }
            break;
         case 2109515900:
            if (var1.equals("front.left.window.close")) {
               var2 = 19;
            }
      }

      switch (var2) {
         case 0:
            MessageSender.sendVoiceMsg(20);
            break;
         case 1:
            MessageSender.sendVoiceMsg(7);
            break;
         case 2:
            MessageSender.sendVoiceMsg(11);
            break;
         case 3:
            MessageSender.sendVoiceMsg(12);
            break;
         case 4:
            MessageSender.sendVoiceMsg(10);
            break;
         case 5:
            MessageSender.sendVoiceMsg(3);
            break;
         case 6:
            MessageSender.sendVoiceMsg(18);
            break;
         case 7:
            MessageSender.sendVoiceMsg(13);
            break;
         case 8:
            MessageSender.sendVoiceMsg(8);
            break;
         case 9:
            MessageSender.sendVoiceMsg(17);
            break;
         case 10:
            MessageSender.sendVoiceMsg(9);
            break;
         case 11:
            MessageSender.sendVoiceMsg(5);
            break;
         case 12:
            MessageSender.sendVoiceMsg(2);
            break;
         case 13:
            MessageSender.sendVoiceMsg(4);
            break;
         case 14:
            MessageSender.sendVoiceMsg(16);
            break;
         case 15:
            MessageSender.sendVoiceMsg(19);
            break;
         case 16:
            MessageSender.sendVoiceMsg(14);
            break;
         case 17:
            MessageSender.sendVoiceMsg(6);
            break;
         case 18:
            MessageSender.sendVoiceMsg(15);
            break;
         case 19:
            MessageSender.sendVoiceMsg(1);
      }

   }
}
