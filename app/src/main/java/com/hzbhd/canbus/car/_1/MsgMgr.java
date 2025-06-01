package com.hzbhd.canbus.car._1;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car._333.AlertView;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   public static int bandBassTag;
   public static int bandMiddleTag;
   public static int bandTrebleTag;
   public static int frontRearTag;
   public static int leftRightTag;
   String MediaInfo0 = "NULL INFO";
   String MediaInfo1 = "NULL INFO";
   String MediaInfo2 = "NULL INFO";
   String MediaInfo3 = "NULL INFO";
   String MediaInfo4 = "NULL INFO";
   String MediaInfo5 = "NULL INFO";
   int[] OutGoingPhoneNumber;
   int alarmInfo1;
   int alarmInfo2;
   private String[] arr0 = new String[10];
   private String[] arr1 = new String[10];
   private String[] arr2 = new String[10];
   private String[] arr3 = new String[10];
   int carDoorInt;
   int[] comingPhoneNumber;
   DecimalFormat df = new DecimalFormat("#0");
   int differentId;
   int eachId;
   int front_radar_key;
   int[] mAirData;
   int[] mBackLightInt;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private int[] talkingPhoneNumber;
   private List tyreInfoList = new ArrayList();

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void adjustBrightness() {
      int var1 = FutureUtil.instance.getBrightness();
      if (var1 == 5) {
         this.setBacklightLevel(1);
      } else {
         this.setBacklightLevel(var1 + 1);
      }

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

   private int getBandAmFM(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 2092:
            if (var1.equals("AM")) {
               var2 = 0;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 1;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 2;
            }
            break;
         case 64903:
            if (var1.equals("AM3")) {
               var2 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 6;
            }
      }

      switch (var2) {
         case 0:
            return 16;
         case 1:
            return 17;
         case 2:
            return 18;
         case 3:
            return 19;
         case 4:
            return 1;
         case 5:
            return 2;
         case 6:
            return 3;
         default:
            return 0;
      }
   }

   private String getBtState1() {
      return DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131756551) : this.mContext.getString(2131756550);
   }

   private String getBtState2() {
      return DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131756554) : this.mContext.getString(2131756553);
   }

   private String getBtState3() {
      return DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131756557) : this.mContext.getString(2131756556);
   }

   private String getBtState4() {
      return DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131756560) : this.mContext.getString(2131756559);
   }

   private String getBtState5() {
      return DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131756563) : this.mContext.getString(2131756562);
   }

   private String getBtState6() {
      return DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131756566) : this.mContext.getString(2131756565);
   }

   private String getBtState7() {
      return DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131756569) : this.mContext.getString(2131756568);
   }

   private String getBtState8() {
      return DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131756572) : this.mContext.getString(2131756571);
   }

   private String getCdStatus() {
      return DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131756584) : this.mContext.getString(2131756574);
   }

   private int getFreqLsb(String var1, String var2) {
      return this.getBandAmFM(var1) != 0 && this.getBandAmFM(var1) != 1 && this.getBandAmFM(var1) != 2 && this.getBandAmFM(var1) != 3 ? this.getLsb((int)Double.parseDouble(var2)) : this.getLsb((int)(Double.parseDouble(var2) * 100.0));
   }

   private int getFreqMsb(String var1, String var2) {
      return this.getBandAmFM(var1) != 0 && this.getBandAmFM(var1) != 1 && this.getBandAmFM(var1) != 2 && this.getBandAmFM(var1) != 3 ? this.getMsb((int)Double.parseDouble(var2)) : this.getMsb((int)(Double.parseDouble(var2) * 100.0));
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private String getModelState(int[] var1) {
      boolean var2 = DataHandleUtils.getBoolBit3(var1[2]);
      String var4 = "";
      if (var2) {
         var4 = "" + this.mContext.getString(2131756577) + "/";
      }

      String var3 = var4;
      if (DataHandleUtils.getBoolBit2(var1[2])) {
         var3 = var4 + this.mContext.getString(2131756578) + "/";
      }

      var4 = var3;
      if (DataHandleUtils.getBoolBit1(var1[2])) {
         var4 = var3 + this.mContext.getString(2131756579) + "/";
      }

      var3 = var4;
      if (DataHandleUtils.getBoolBit0(var1[2])) {
         var3 = var4 + this.mContext.getString(2131756580) + "/";
      }

      return var3;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUigMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private List getOriginalDeviceInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDeviceUpdateEntity(0, this.MediaInfo0));
      var1.add(new OriginalCarDeviceUpdateEntity(1, this.MediaInfo1));
      var1.add(new OriginalCarDeviceUpdateEntity(2, this.MediaInfo2));
      var1.add(new OriginalCarDeviceUpdateEntity(3, this.MediaInfo3));
      var1.add(new OriginalCarDeviceUpdateEntity(4, this.MediaInfo4));
      var1.add(new OriginalCarDeviceUpdateEntity(5, this.MediaInfo5));
      return var1;
   }

   private String getP_GearState(boolean var1) {
      return var1 ? this.mContext.getString(2131759703) : this.mContext.getString(2131759702);
   }

   private String getReversingState(boolean var1) {
      return var1 ? this.mContext.getString(2131759718) : this.mContext.getString(2131759719);
   }

   private String getRunningState() {
      int var1 = this.mCanBusInfoInt[3];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 3) {
               if (var1 != 4) {
                  return var1 != 5 ? this.mContext.getString(2131756589) : this.mContext.getString(2131756588);
               } else {
                  return this.mContext.getString(2131756587);
               }
            } else {
               return this.mContext.getString(2131756586);
            }
         } else {
            return this.mContext.getString(2131756585);
         }
      } else {
         return this.mContext.getString(2131756575);
      }
   }

   private String getSwitchState(boolean var1) {
      return var1 ? this.mContext.getString(2131759717) : this.mContext.getString(2131759716);
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

   private UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initOriginalCarDevice() {
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();
            this.this$0.initOriginalDeviceDataArray();
         }
      }).start();
   }

   private void initOriginalDeviceDataArray() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_8", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_9", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_11", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_16", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_17", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_18", (String)null));
      SparseArray var2 = new SparseArray();
      this.mOriginalDeviceDataArray = var2;
      var2.put(42, new OriginalDeviceData(var1, new String[]{"prev_disc", "left", "random", "play_pause", "cycle", "right", "next_disc"}));
   }

   private boolean is404(String var1, String var2) {
      return this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isAlarm1InfoChange() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
      if (this.alarmInfo1 == var1) {
         return false;
      } else {
         this.alarmInfo1 = var1;
         return true;
      }
   }

   private boolean isAlarm2InfoChange() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
      if (this.alarmInfo2 == var1) {
         return false;
      } else {
         this.alarmInfo2 = var1;
         return true;
      }
   }

   private boolean isBackLightIntChange() {
      if (Arrays.equals(this.mBackLightInt, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mBackLightInt = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isCarDoorInfoChange() {
      int var1 = this.carDoorInt;
      int var2 = this.mCanBusInfoInt[3];
      if (var1 == var2) {
         return false;
      } else {
         this.carDoorInt = var2;
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
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
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
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
         return true;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void set0x14BackLightInfo() {
      if (!this.isBackLightIntChange()) {
         this.adjustBrightness();
      }
   }

   private void set0x16SpeedInfo() {
      ArrayList var5 = new ArrayList();
      int var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      int var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info2");
      StringBuilder var3 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var3.append(this.getMsbLsbResult(var4[3], var4[2]) / 16).append("km/h").toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var6[3], var6[2]) / 16);
   }

   private void set0x20WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 19) {
         if (var1 != 20) {
            switch (var1) {
               case 0:
                  this.buttonKey(0);
                  break;
               case 1:
                  this.buttonKey(7);
                  break;
               case 2:
                  this.buttonKey(8);
                  break;
               case 3:
                  this.buttonKey(45);
                  break;
               case 4:
                  this.buttonKey(46);
                  break;
               case 5:
                  this.buttonKey(14);
                  break;
               case 6:
                  this.buttonKey(3);
                  break;
               case 7:
                  this.buttonKey(2);
                  break;
               case 8:
                  this.buttonKey(136);
            }
         } else {
            this.buttonKey(21);
         }
      } else {
         this.buttonKey(20);
      }

   }

   private void set0x21AirInfo() {
      int[] var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 4);
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4], 0.5, 17.5, "C", 0, 31);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5], 0.5, 17.5, "C", 0, 31);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void set0x22RearRadarInfo() {
      if (!this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x23FrontRadarInfo() {
      if (!this.isFrontRadarDataChange()) {
         label38: {
            RadarInfoUtil.mMinIsClose = true;
            int[] var2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(10, var2[2], var2[3], var2[4], var2[5]);
            this.front_radar_key = SharePreUtil.getIntValue(this.mContext, "FRONT_RADAR_KEY", 0);
            Log.d("front_radar_key", this.front_radar_key + "");
            int var1;
            if (this.front_radar_key == 1) {
               var1 = this.mCanBusInfoInt[2];
               if (var1 <= 5 && var1 != 0) {
                  break label38;
               }
            }

            var2 = this.mCanBusInfoInt;
            var1 = var2[3];
            if (var1 > 5 || var1 == 0) {
               var1 = var2[4];
               if (var1 > 5 || var1 == 0) {
                  var1 = var2[5];
                  if (var1 > 5 || var1 == 0) {
                     this.forceReverse(this.mContext, false);
                     return;
                  }
               }
            }
         }

         this.forceReverse(this.mContext, true);
      }
   }

   private void set0x24BaseInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info4"), this.getSwitchState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info5"), this.getReversingState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info10"), this.getP_GearState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      this.mCanBusInfoInt[2] = 0;
      if (!this.isBasicInfoChange()) {
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }
   }

   private void set0x25ParkingInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), 0, this.getSwitchState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      GeneralParkData.pKeyRadarState = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      this.updatePKeyRadar();
   }

   private void set0x26EspInfo() {
      if (!this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 10000, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x27AmplifierInfo() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[3];
      if (leftRightTag == -1) {
         GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] - 256;
      } else {
         GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4];
      }

      if (frontRearTag == -1) {
         GeneralAmplifierData.frontRear = this.mCanBusInfoInt[5] - 256;
      } else {
         GeneralAmplifierData.frontRear = this.mCanBusInfoInt[5];
      }

      if (bandBassTag == -1) {
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6] - 247;
      } else {
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6] + 9;
      }

      if (bandMiddleTag == -1) {
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7] - 247;
      } else {
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7] + 9;
      }

      if (bandTrebleTag == -1) {
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[8] - 247;
      } else {
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[8] + 9;
      }

      this.updateAmplifierActivity(new Bundle());
      AmpUtil.saveAmpUIValue(this.mContext);
   }

   private void set0x28BtInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_1"), this.getBtState1()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_2"), this.getBtState2()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_3"), this.getBtState3()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_4"), this.getBtState4()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_5"), this.getBtState5()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_6"), this.getBtState6()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_7"), this.getBtState7()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_8"), this.getBtState8()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      var1 = new ArrayList();
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1001_bt2"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1001_bt2", "_1001_bt_7"), 1));
      } else {
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1001_bt2"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1001_bt2", "_1001_bt_7"), 0));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x29MediaInfo() {
      GeneralOriginalCarDeviceData.cdStatus = this.getCdStatus();
      GeneralOriginalCarDeviceData.runningState = this.getRunningState();
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var5 = var5.append(this.getMsbLsbResult(var4[4], var4[5])).append("/");
      var4 = this.mCanBusInfoInt;
      this.MediaInfo0 = var5.append(this.getMsbLsbResult(var4[6], var4[7])).toString();
      var4 = this.mCanBusInfoInt;
      int var2 = this.getMsbLsbResult(var4[8], var4[9]);
      var4 = this.mCanBusInfoInt;
      int var1 = this.getMsbLsbResult(var4[10], var4[11]);
      StringBuilder var6 = new StringBuilder();
      int var3 = var2 / 60;
      var6 = var6.append(var3 / 60).append(":").append(var3 % 60).append(":").append(var2 % 60).append("/");
      var2 = var1 / 60;
      this.MediaInfo1 = var6.append(var2 / 60).append(":").append(var2 % 60).append(":").append(var1 % 60).toString();
      this.MediaInfo2 = this.getModelState(this.mCanBusInfoInt);
      this.setDataToOriginalCar();
   }

   private void set0x2AMediaStrInfo() {
      // $FF: Couldn't be decompiled
   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x37TireInfo() {
      GeneralTireData.isHaveSpareTire = false;
      ArrayList var1 = new ArrayList();
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2) == 0) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1001_tire_3"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1001_tire_3", "_1001_tire_3"), this.mContext.getString(2131756595))).setValueStr(true));
      } else {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1001_tire_3"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1001_tire_3", "_1001_tire_3"), this.mContext.getString(2131756596))).setValueStr(true));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      List var2 = this.tyreInfoList;
      if (var2 != null) {
         var2.clear();
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         this.arr0[0] = this.mContext.getString(2131756592);
         this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
      } else {
         this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
         this.arr0[0] = this.mContext.getString(2131756593);
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         this.arr1[0] = this.mContext.getString(2131756592);
         this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
      } else {
         this.arr1[0] = this.mContext.getString(2131756593);
         this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
         this.arr2[0] = this.mContext.getString(2131756592);
      } else {
         this.arr2[0] = this.mContext.getString(2131756593);
         this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
         this.arr3[0] = this.mContext.getString(2131756592);
      } else {
         this.arr3[0] = this.mContext.getString(2131756593);
         this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
         this.arr0[1] = this.mContext.getString(2131756599);
         this.arr1[1] = this.mContext.getString(2131756599);
         this.arr2[1] = this.mContext.getString(2131756599);
         this.arr3[1] = this.mContext.getString(2131756599);
      } else {
         this.arr0[1] = this.mContext.getString(2131756598);
         this.arr1[1] = this.mContext.getString(2131756598);
         this.arr2[1] = this.mContext.getString(2131756598);
         this.arr3[1] = this.mContext.getString(2131756598);
      }

      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void set0x41CarInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               this.set0x41CarInfo0x03Data();
            }
         } else {
            this.set0x41CarInfo0x02Data();
         }
      } else {
         this.set0x41CarInfo0x01Data();
      }

   }

   private void set0x41CarInfo0x01Data() {
      if (this.isCarDoorInfoChange()) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ^ true;
         GeneralDoorData.isShowWashingFluidWarning = true;
         GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isShowHandBrake = true;
         GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x41CarInfo0x02Data() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      int var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(var4[3] * 256 + var4[4]).append(" RPM").toString()));
      var2 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      var1 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14");
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append((double)(var4[5] * 256 + var4[6]) * 0.01).append(" Km/h").toString()));
      var2 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      var1 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info15");
      StringBuilder var8 = new StringBuilder();
      int[] var10 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var8.append((double)(var10[7] * 256 + var10[8]) * 0.01).append(" V").toString()));
      var4 = this.mCanBusInfoInt;
      Context var7;
      if ((double)(var4[9] * 256 + var4[10]) * 0.1 > 6000.0) {
         var7 = this.mContext;
         StringBuilder var6 = new StringBuilder();
         DecimalFormat var9 = this.df;
         var10 = this.mCanBusInfoInt;
         this.updateOutDoorTemp(var7, var6.append(var9.format((double)(var10[9] * 256 + var10[10]) * 0.1 - 6554.0)).append(this.getTempUnitC(this.mContext)).toString());
      } else {
         var7 = this.mContext;
         var8 = new StringBuilder();
         DecimalFormat var11 = this.df;
         int[] var12 = this.mCanBusInfoInt;
         this.updateOutDoorTemp(var7, var8.append(var11.format((double)(var12[9] * 256 + var12[10]) * 0.1)).append(this.getTempUnitC(this.mContext)).toString());
      }

      var2 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      var1 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16");
      var8 = new StringBuilder();
      var10 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var8.append(var10[11] * 65536 + var10[12] * 256 + var10[13]).append(" Km").toString()));
      var3.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info17"), this.mCanBusInfoInt[14] + " L"));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x41CarInfo0x03Data() {
      boolean var4 = this.isAlarm1InfoChange();
      byte var2 = 0;
      String var5 = "";
      boolean var1;
      if (var4 && DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var5 = "" + "【" + this.mContext.getString(2131759712) + 1 + "：" + this.mContext.getString(2131759709) + "】";
         var1 = true;
         var2 = 1;
      } else {
         var1 = false;
      }

      boolean var3 = var1;
      String var6 = var5;
      if (this.isAlarm2InfoChange()) {
         var3 = var1;
         var6 = var5;
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            var6 = var5 + "【" + this.mContext.getString(2131759712) + (var2 + 1) + "：" + this.mContext.getString(2131759710) + "】";
            var3 = true;
         }
      }

      if (var3) {
         this.showDialog(var6);
      }

   }

   private void set0x95data() {
      ArrayList var1 = new ArrayList();
      if (this.mCanBusInfoInt[2] == 0) {
         var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_driver_1"), "OFF"));
      } else {
         var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_driver_1"), "ON"));
      }

      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDataToOriginalCar() {
      OriginalDeviceData var1 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(42);
      GeneralOriginalCarDeviceData.mList = null;
      GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceInfo();
      OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var2.setItems(var1.getItemList());
      var2.setRowBottomBtnAction(var1.getBottomBtns());
      Bundle var3 = new Bundle();
      var3.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var3);
   }

   private void showDialog(String var1) {
      this.runOnUi(new CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$showContent;

         {
            this.this$0 = var1;
            this.val$showContent = var2;
         }

         public void callback() {
            (new AlertView()).showDialog(this.this$0.getActivity(), this.val$showContent);
         }
      });
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
      this.front_radar_key = SharePreUtil.getIntValue(var1, "FRONT_RADAR_KEY", 0);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUigMgr(this.mContext).sendSourceInfo(7, 48);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getUigMgr(this.mContext).sendSourceInfo(11, 34);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      this.getUigMgr(this.mContext).sendSourceInfo(0, 0);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.comingPhoneNumber = null;
      this.OutGoingPhoneNumber = null;
      this.talkingPhoneNumber = null;
      this.runOnUi(new CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            (new CountDownTimer(this, 2000L, 500L) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onFinish() {
                  this.this$1.this$0.getUigMgr(this.this$1.this$0.mContext).sendPhoneNumber();
               }

               public void onTick(long var1) {
                  this.this$1.this$0.getUigMgr(this.this$1.this$0.mContext).sendPhoneNumber();
               }
            }).start();
         }
      });
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      int[] var4 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var4, this.comingPhoneNumber)) {
         this.comingPhoneNumber = var4;
         this.getUigMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 1, 1}, var1));
      }

   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      int[] var4 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var4, this.OutGoingPhoneNumber)) {
         this.OutGoingPhoneNumber = var4;
         this.getUigMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 2, 1}, var1));
      }

   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      this.getUigMgr(this.mContext).sendSourceInfo(5, 64);
      int[] var5 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var5, this.talkingPhoneNumber)) {
         this.talkingPhoneNumber = var5;
         this.getUigMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 4, 1}, var1));
      }

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
      if (var3 != 20) {
         if (var3 != 22) {
            if (var3 != 48) {
               if (var3 != 55) {
                  if (var3 != 65) {
                     if (var3 != 149) {
                        switch (var3) {
                           case 32:
                              this.set0x20WheelKeyInfo();
                              break;
                           case 33:
                              this.set0x21AirInfo();
                              break;
                           case 34:
                              this.set0x22RearRadarInfo();
                              break;
                           case 35:
                              this.set0x23FrontRadarInfo();
                              break;
                           case 36:
                              this.set0x24BaseInfo();
                              break;
                           case 37:
                              this.set0x25ParkingInfo();
                              break;
                           case 38:
                              this.set0x26EspInfo();
                              break;
                           case 39:
                              this.set0x27AmplifierInfo();
                              break;
                           case 40:
                              this.set0x28BtInfo();
                              break;
                           case 41:
                              this.set0x29MediaInfo();
                              break;
                           case 42:
                              this.set0x2AMediaStrInfo();
                        }
                     } else {
                        this.set0x95data();
                     }
                  } else {
                     this.set0x41CarInfo();
                  }
               } else {
                  this.set0x37TireInfo();
               }
            } else {
               this.set0x30VersionInfo();
            }
         } else {
            this.set0x16SpeedInfo();
         }
      } else {
         this.set0x14BackLightInfo();
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUigMgr(this.mContext).sendSourceInfo(2, 33);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(var5, var6, 0, 0, var2 / 60, var2 % 60);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUigMgr(var1).makeConnection();
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initOriginalCarDevice();
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.getUigMgr(this.mContext).sendSourceInfo(0, 0);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.getUigMgr(this.mContext).sendSourceInfo(8, 17);
      this.getUigMgr(this.mContext).sendMediaPalyInfo(this.getLsb(var4), this.getMsb(var4), this.getLsb(var3), this.getMsb(var3), var6, var7);
   }

   public void radioDestroy() {
      super.radioDestroy();
      this.getUigMgr(this.mContext).sendSourceInfo(0, 0);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.getUigMgr(this.mContext).sendSourceInfo(1, 1);
      this.getUigMgr(this.mContext).sendRadioInfo(this.getBandAmFM(var2), this.getFreqLsb(var2, var3), this.getFreqMsb(var2, var3), var1);
   }

   public void reverseStateChange(boolean var1) {
      super.reverseStateChange(var1);
   }

   public void updateAmp() {
      this.updateAmplifierActivity((Bundle)null);
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5) {
      SharePreUtil.setIntValue(var1, var2, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var3, var4, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   private static class OriginalDeviceData {
      private final String[] bottomBtns;
      private final List list;

      public OriginalDeviceData(List var1) {
         this(var1, (String[])null);
      }

      public OriginalDeviceData(List var1, String[] var2) {
         this.list = var1;
         this.bottomBtns = var2;
      }

      public String[] getBottomBtns() {
         return this.bottomBtns;
      }

      public List getItemList() {
         return this.list;
      }
   }
}
