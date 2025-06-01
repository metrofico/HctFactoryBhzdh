package com.hzbhd.canbus.car._5;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
   private static final int SEND_NORMAL_MESSAGE = 2;
   private int[] m0x12Data;
   private int[] m0x13Data;
   private int[] m0x42Data;
   private int[] m0x47Data;
   private boolean mAirFirst = true;
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
   private int[] mParkData;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private int mRightWindLevelNow;
   private int mRightWindLevelRec;
   private int mVehicleSpeedData;
   private boolean mWashingFluidStatus;
   private int mWheelKeyData;
   int result = 0;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

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

   private int distanceToLocation(int var1) {
      if (var1 >= 0 && var1 < 13) {
         return 1;
      } else if (var1 >= 13 && var1 < 26) {
         return 2;
      } else if (var1 >= 26 && var1 < 39) {
         return 3;
      } else if (var1 >= 39 && var1 < 52) {
         return 4;
      } else if (var1 >= 52 && var1 < 65) {
         return 5;
      } else if (var1 >= 65 && var1 < 78) {
         return 6;
      } else if (var1 >= 78 && var1 < 91) {
         return 7;
      } else if (var1 >= 91 && var1 < 104) {
         return 8;
      } else if (var1 >= 104 && var1 < 117) {
         return 9;
      } else if (var1 >= 117 && var1 < 128) {
         return 10;
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

   public static int getMsb(int var0) {
      return (var0 & -1) >> 24 & 255;
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

   private boolean is0x12DataChange() {
      int[] var2 = new int[5];
      int[] var1 = this.mCanBusInfoInt;
      var2[0] = var1[5];
      var2[1] = var1[6];
      var2[2] = var1[9];
      var2[3] = var1[10];
      var2[4] = var1[11];
      if (Arrays.equals(this.m0x12Data, var2)) {
         return false;
      } else {
         this.m0x12Data = Arrays.copyOf(var2, 5);
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
      int var2 = this.mWheelKeyData;
      int var1 = this.mCanBusInfoInt[4];
      if (var2 == var1) {
         return false;
      } else {
         this.mWheelKeyData = var1;
         return true;
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

      byte[] var3 = this.bytesExpectOneByte(this.bytesExpectOneByte(this.mCanBusInfoByte, 9), 8);
      if (this.isOutDoorTemperatureChange()) {
         this.setOutDoorTem();
      }

      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(var3)) {
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
            int var1 = ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature();
            this.mLeftWindLevelRec = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[var1 + 6], 0, 3);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
            this.mRightWindLevelRec = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7 - var1], 0, 3);
            int var2 = this.mLeftWindLevelNow;
            var1 = this.mLeftWindLevelRec;
            if (var2 != var1) {
               this.mLeftWindLevelNow = var1;
               GeneralAirData.front_wind_level = var1;
               GeneralAirData.center_wheel = "Left";
            }

            var1 = this.mRightWindLevelNow;
            var2 = this.mRightWindLevelRec;
            if (var1 != var2) {
               this.mRightWindLevelNow = var2;
               GeneralAirData.front_wind_level = var2;
               GeneralAirData.center_wheel = "Right";
            }

            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setCanVersion0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setKeySpeedTrackRadar0x72() {
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
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
                              this.realKeyLongClick2(187);
                           }
                        } else {
                           this.realKeyLongClick2(15);
                        }
                     } else {
                        this.realKeyLongClick2(188);
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

      if (this.isParkDataChange()) {
         int[] var5 = this.mCanBusInfoInt;
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
         GeneralParkData.radar_distance_disable = new int[]{0, 255};
         GeneralParkData.isShowDistanceNotShowLocationUi = true;
         var5 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarDistanceData(var5[8], var5[9], var5[10], var5[11]);
         var5 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarDistanceData(var5[12], var5[13], var5[14], var5[15]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mDisableData = 255;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(11, this.distanceToLocation(this.mCanBusInfoInt[8]), this.distanceToLocation(this.mCanBusInfoInt[9]), this.distanceToLocation(this.mCanBusInfoInt[10]), this.distanceToLocation(this.mCanBusInfoInt[11]));
         RadarInfoUtil.setFrontRadarLocationData(11, this.distanceToLocation(this.mCanBusInfoInt[12]), this.distanceToLocation(this.mCanBusInfoInt[13]), this.distanceToLocation(this.mCanBusInfoInt[14]), this.distanceToLocation(this.mCanBusInfoInt[15]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setParkingSystem0x47() {
      if (this.is0x47DataChange()) {
         SharePreUtil.setStringValue(this.mContext, "5_0x47", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1)));
         var1.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1)));
         var1.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 5, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
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
         ArrayList var1 = new ArrayList();
         var1.add(new DriverUpdateEntity(0, 1, this.mCanBusInfoInt[5] + "." + this.mCanBusInfoInt[6] + " L/100KM"));
         var1.add(new DriverUpdateEntity(0, 2, this.mCanBusInfoInt[9] + " L"));
         var1.add(new DriverUpdateEntity(0, 3, this.mCanBusInfoInt[10] + "." + this.mCanBusInfoInt[11] + " V"));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
      }

      GeneralDoorData.isShowFuelWarning = true;
      GeneralDoorData.isShowBatteryWarning = true;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isShowWashingFluidWarning = true;
      GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]) ^ true;
      GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
      if (this.isDoorDataRepeat() && this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setVehicleInfoTwo0x13() {
      if (this.is0x13DataChange()) {
         ArrayList var1 = new ArrayList();
         StringBuilder var3 = new StringBuilder();
         int[] var2 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 4, var3.append(var2[2] * 256 * 256 + var2[3] * 256 + var2[4]).append(".").append(Math.round((float)(this.mCanBusInfoInt[7] * 100)) / 100).append(" KM").toString()));
         var3 = new StringBuilder();
         var2 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 5, var3.append(var2[10] * 256 + var2[11]).append(" RPM").toString()));
         var3 = new StringBuilder();
         var2 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 6, var3.append((double)DataHandleUtils.getMsbLsbResult(var2[5], var2[6]) / 10.0).append(" KM/H").toString()));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
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
      int var3 = var4[1];
      if (var3 != 18) {
         if (var3 != 19) {
            if (var3 != 66) {
               if (var3 != 71) {
                  if (var3 != 240) {
                     if (var3 != 114) {
                        if (var3 == 115) {
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

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte[] var14 = (var5 + ":" + var6).getBytes(StandardCharsets.UTF_8);
      if (var14.length == 20) {
         CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -27}, var14));
      } else {
         var2 = var14.length;
         var1 = 0;
         byte[] var15;
         if (var2 > 20) {
            for(var15 = new byte[20]; var1 < 20; ++var1) {
               var15[var1] = var14[var1];
            }

            CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -27}, var15));
         } else if (var14.length < 20) {
            var15 = new byte[20];

            for(var1 = 0; var1 < 20; ++var1) {
               if (var1 < var14.length) {
                  var15[var1] = var14[var1];
               } else {
                  var15[var1] = 0;
               }
            }

            CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -27}, var15));
         }
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte[] var18;
      if (var10 == 240) {
         var11 = android.provider.Settings.System.getString(this.mContext.getContentResolver(), "reportForDiscEject");
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

   void updateSettingData(int var1) {
      LogUtil.showLog("updateSettingData:" + var1);
      ArrayList var2 = new ArrayList();
      if (var1 == 0) {
         String var3 = SharePreUtil.getStringValue(this.mContext, "5_0x47", (String)null);
         if (!TextUtils.isEmpty(var3)) {
            byte[] var4 = Base64.decode(var3, 0);
            var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(var4[15], 7, 1)));
            var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(var4[15], 6, 1)));
            var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(var4[15], 5, 1)));
         }
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
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
}
