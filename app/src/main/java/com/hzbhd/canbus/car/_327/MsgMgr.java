package com.hzbhd.canbus.car._327;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.comm.Constant;
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
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private int FreqInt;
   private String[] arr0 = new String[10];
   private String[] arr1 = new String[10];
   private String[] arr2 = new String[10];
   private String[] arr3 = new String[10];
   int currentCanDifferentId;
   int currentEachCanId;
   private DecimalFormat df = new DecimalFormat("0.00");
   private byte freqHi;
   private byte freqLo;
   private byte[] m0x21AirData;
   private int[] m0x22Data;
   private int[] m0x23Data;
   private int[] m0x24Data;
   private int[] m0x25Data;
   private int[] m0x26Data;
   private int[] m0x42Data;
   private int mAirRearMode = 0;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private boolean mPanoramicStatus2;
   private byte[] mTrackData;
   private UiMgr mUiMgr;
   int nowDay = 32;
   int nowHours = 24;
   int nowMins = 61;
   int nowMonth = 13;
   int nowSecond = 61;
   int nowYear = 3000;
   private List tyreInfoList = new ArrayList();

   private int get0x22RadarValue(int var1) {
      if (var1 != 0) {
         var1 = (int)((float)var1 / 32.0F * 10.0F) + 1;
      } else {
         var1 = 0;
      }

      return var1;
   }

   private int get0x23RadarValue(int var1) {
      if (var1 != 0) {
         var1 = (int)((float)var1 / 15.0F * 10.0F) + 1;
      } else {
         var1 = 0;
      }

      return var1;
   }

   private int get0x25BothSidesRadarGridNumber(int var1) {
      if (var1 >= 0 && var1 <= 10) {
         return 1;
      } else if (var1 > 10 && var1 <= 20) {
         return 2;
      } else if (var1 > 20 && var1 <= 30) {
         return 3;
      } else if (var1 > 30 && var1 <= 40) {
         return 4;
      } else if (var1 > 40 && var1 <= 50) {
         return 5;
      } else if (var1 > 50 && var1 <= 60) {
         return 6;
      } else if (var1 > 60 && var1 <= 70) {
         return 7;
      } else if (var1 > 70 && var1 <= 97) {
         return 8;
      } else if (var1 > 97 && var1 <= 124) {
         return 9;
      } else {
         return var1 > 124 && var1 <= 150 ? 10 : 1;
      }
   }

   private int get0x25RadarValue(int var1) {
      if (var1 != 0) {
         var1 = (int)((float)var1 / 32.0F * 10.0F) + 1;
      } else {
         var1 = 0;
      }

      return var1;
   }

   private int get0x25middleRadarGridNumber(int var1) {
      if (var1 >= 0 && var1 <= 15) {
         return 1;
      } else if (var1 > 15 && var1 <= 30) {
         return 2;
      } else if (var1 > 30 && var1 <= 45) {
         return 3;
      } else if (var1 > 45 && var1 <= 60) {
         return 4;
      } else if (var1 > 60 && var1 <= 90) {
         return 5;
      } else if (var1 > 90 && var1 <= 120) {
         return 6;
      } else if (var1 > 120 && var1 <= 149) {
         return 7;
      } else {
         return var1 > 149 ? 10 : 1;
      }
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
            return 17;
         case 1:
            return 18;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
            return 3;
         default:
            return 0;
      }
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.FreqInt = Integer.parseInt(var2);
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private String getOutdoorTemperature() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[2];
      int var2 = var3[3];
      String var4;
      if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 1) == 0) {
         var4 = (double)var2 * 0.5 - 35.0 + this.getTempUnitC(this.mContext);
      } else if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 1) == 1) {
         var4 = (double)var2 * 0.5 - 35.0 + this.getTempUnitF(this.mContext);
      } else {
         var4 = "";
      }

      return var4;
   }

   private int getTemperatureLevel(int var1) {
      byte var2;
      switch (var1) {
         case 1:
            var2 = 1;
            break;
         case 2:
            var2 = 2;
            break;
         case 3:
            var2 = 3;
            break;
         case 4:
            var2 = 4;
            break;
         case 5:
            var2 = 5;
            break;
         case 6:
            var2 = 6;
            break;
         case 7:
            var2 = 7;
            break;
         case 8:
            var2 = 8;
            break;
         case 9:
            var2 = 9;
            break;
         case 10:
            var2 = 10;
            break;
         case 11:
            var2 = 11;
            break;
         case 12:
            var2 = 12;
            break;
         case 13:
            var2 = 13;
            break;
         case 14:
            var2 = 14;
            break;
         case 15:
            var2 = 15;
            break;
         default:
            var2 = 0;
      }

      return var2;
   }

   private UiMgr getmUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
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

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x24DataChange() {
      if (Arrays.equals(this.m0x24Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x24Data = Arrays.copyOf(var1, var1.length);
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

   private boolean is0x26DataChange() {
      if (Arrays.equals(this.m0x26Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x26Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.m0x21AirData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x21AirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicDataChange() {
      if (Arrays.equals(this.m0x42Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x42Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackDataChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private String resolverAirTemperature(Context var1, int var2) {
      if (var2 == 128) {
         return "LO";
      } else if (var2 == 159) {
         return "HI";
      } else {
         return 128 < var2 && var2 < 159 ? (double)((float)(var2 + 36) * 0.5F) - 64.5 + this.getTempUnitC(var1) : "";
      }
   }

   private void sendMusic(String var1, String var2) {
      try {
         byte[] var4 = DataHandleUtils.exceptBOMHead(var1.getBytes("utf-8"));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 112, 18}, var4));
         var4 = DataHandleUtils.exceptBOMHead(var2.getBytes("utf-8"));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 113, 18}, var4));
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
      }

   }

   private void sendVoiceMsg(int var1, boolean var2) {
      if (var2) {
         CanbusMsgSender.sendMsg(new byte[]{24, -88, (byte)var1, 1});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{24, -88, (byte)var1, 0});
      }

   }

   private void set0x20WheelKey(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      switch (var2) {
         case 0:
            this.realKeyLongClick1(var1, 0, var3[3]);
            break;
         case 1:
            this.realKeyLongClick1(var1, 7, var3[3]);
            break;
         case 2:
            this.realKeyLongClick1(var1, 8, var3[3]);
            break;
         case 3:
            this.buttonKey(45);
            break;
         case 4:
            this.buttonKey(46);
            break;
         case 5:
            this.realKeyClick2(var1, 14, var2, var3[3]);
            break;
         case 6:
            this.realKeyClick2(var1, 3, var2, var3[3]);
            break;
         case 7:
            this.realKeyClick2(var1, 2, var2, var3[3]);
            break;
         case 8:
            this.realKeyClick2(var1, 15, var2, var3[3]);
            break;
         case 9:
            this.realKeyClick2(var1, 134, var2, var3[3]);
            break;
         default:
            switch (var2) {
               case 16:
                  this.realKeyClick1(var1, 128, var2, var3[3]);
                  break;
               case 17:
                  this.realKeyClick1(var1, 52, var2, var3[3]);
                  break;
               case 18:
                  this.realKeyClick1(var1, 61, var2, var3[3]);
                  break;
               case 19:
                  this.realKeyClick1(var1, 58, var2, var3[3]);
                  break;
               case 20:
                  this.realKeyClick1(var1, 59, var2, var3[3]);
                  break;
               case 21:
                  this.realKeyClick1(var1, 187, var2, var3[3]);
                  break;
               case 22:
                  this.realKeyClick1(var1, 467, var2, var3[3]);
                  break;
               case 23:
                  this.realKeyClick1(var1, 50, var2, var3[3]);
            }
      }

   }

   private void set0x21AirInfo(Context var1) {
      short var2;
      if (this.getCurrentCanDifferentId() == 3 && this.mAirRearMode != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3)) {
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3);
         this.mAirRearMode = var3;
         var2 = 1002;
         if (var3 != 0) {
            if (var3 != 1) {
               if (var3 == 2) {
                  GeneralAirData.rear_power = true;
                  GeneralAirData.rear_auto = false;
               }
            } else {
               GeneralAirData.rear_power = true;
               GeneralAirData.rear_auto = true;
            }
         } else {
            GeneralAirData.rear_power = false;
            GeneralAirData.rear_auto = false;
         }
      } else {
         var2 = 0;
      }

      byte[] var4 = this.mCanBusInfoByte;
      var4[6] = (byte)(var4[6] & 248);
      if (this.isAirDataChange()) {
         var2 = 1001;
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 0) {
            GeneralAirData.front_left_temperature = this.getTemperatureLevel(this.mCanBusInfoInt[4]) + "Level";
         } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 1) {
            GeneralAirData.front_left_temperature = this.resolverAirTemperature(var1, this.mCanBusInfoInt[4]);
         }

         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1) == 0) {
            GeneralAirData.front_right_temperature = this.getTemperatureLevel(this.mCanBusInfoInt[5]) + "Level";
         } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1) == 1) {
            GeneralAirData.front_right_temperature = this.resolverAirTemperature(var1, this.mCanBusInfoInt[5]);
         }

         GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
         GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
         GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
      }

      if (var2 != 0) {
         this.updateAirActivity(var1, var2);
      }

   }

   private void set0x22RearRadarInfo() {
      if (this.is0x22DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(10, this.get0x22RadarValue(this.mCanBusInfoInt[2]), this.get0x22RadarValue(this.mCanBusInfoInt[3]), this.get0x22RadarValue(this.mCanBusInfoInt[4]), this.get0x22RadarValue(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x23FrontRadarInfo() {
      if (this.is0x23DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(10, this.get0x23RadarValue(this.mCanBusInfoInt[2]), this.get0x23RadarValue(this.mCanBusInfoInt[3]), this.get0x23RadarValue(this.mCanBusInfoInt[4]), this.get0x23RadarValue(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x24BaseInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_327_source_setting"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      this.mCanBusInfoInt[5] = 0;
      if (this.is0x24DataChange()) {
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1) == 1) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            if (this.currentEachCanId != 1) {
               GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            }
         }

         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isSubShowSeatBelt = true;
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         if (this.currentEachCanId == 5) {
            if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
               if ((this.mCanBusInfoInt[3] >> 3 & 1) == 0) {
                  this.finishActivity();
               }
            } else if ((this.mCanBusInfoInt[3] >> 3 & 1) == 1) {
               Intent var2 = new Intent();
               var2.setComponent(Constant.AirActivity);
               var2.setFlags(268435456);
               this.mContext.startActivity(var2);
            }
         }

         this.updateDoorView(this.mContext);
      }

   }

   private void set0x25RearRadarInfo() {
      if (this.is0x25DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(10, this.get0x25BothSidesRadarGridNumber(this.mCanBusInfoInt[2]), this.get0x25middleRadarGridNumber(this.mCanBusInfoInt[3]), this.get0x25middleRadarGridNumber(this.mCanBusInfoInt[4]), this.get0x25BothSidesRadarGridNumber(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x26FrontRadarInfo() {
      if (this.is0x26DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(10, this.get0x25BothSidesRadarGridNumber(this.mCanBusInfoInt[2]), this.get0x25middleRadarGridNumber(this.mCanBusInfoInt[3]), this.get0x25middleRadarGridNumber(this.mCanBusInfoInt[4]), this.get0x25BothSidesRadarGridNumber(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x27OutDoorTemperature(Context var1) {
      this.updateOutDoorTemp(var1, this.getOutdoorTemperature());
   }

   private void set0x28TrackData() {
      if (this.isTrackDataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 7840, 2180, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x29TrackData() {
      if (this.isTrackDataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 32768, 42496, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x38TireInfo() {
      GeneralTireData.isHaveSpareTire = false;
      this.arr0[0] = "温度：" + (this.mCanBusInfoInt[2] - 40) + this.getTempUnitC(this.mContext);
      this.arr0[1] = "胎压：" + this.df.format((double)this.mCanBusInfoInt[6] * 1.373) + "KPa";
      this.arr1[0] = "温度：" + (this.mCanBusInfoInt[3] - 40) + this.getTempUnitC(this.mContext);
      this.arr1[1] = "胎压：" + this.df.format((double)this.mCanBusInfoInt[7] * 1.373) + "KPa";
      this.arr2[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + this.getTempUnitC(this.mContext);
      this.arr2[1] = "胎压：" + this.df.format((double)this.mCanBusInfoInt[8] * 1.373) + "KPa";
      this.arr3[0] = "温度：" + (this.mCanBusInfoInt[5] - 40) + this.getTempUnitC(this.mContext);
      this.arr3[1] = "胎压：" + this.df.format((double)this.mCanBusInfoInt[9] * 1.373) + "KPa";
      this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
      this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
      this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
      this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void set0x39TireAlertInfo() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         this.arr0[2] = "传感器：失效";
      } else {
         this.arr0[2] = "传感器：正常";
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
         this.arr0[3] = "胎压预警：低压";
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
         this.arr0[3] = "胎压预警：高压";
      }

      if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
         this.arr0[3] = "胎压预警：正常";
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
         this.arr1[2] = "传感器：失效";
      } else {
         this.arr1[2] = "传感器：正常";
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         this.arr1[3] = "胎压预警：低压";
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         this.arr1[3] = "胎压预警：高压";
      }

      if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         this.arr1[3] = "胎压预警：正常";
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         this.arr3[2] = "传感器：失效";
      } else {
         this.arr3[2] = "传感器：正常";
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         this.arr3[3] = "胎压预警：低压";
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         this.arr3[3] = "胎压预警：高压";
      }

      if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         this.arr3[3] = "胎压预警：正常";
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         this.arr2[2] = "传感器：失效";
      } else {
         this.arr2[2] = "传感器：正常";
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         this.arr2[3] = "胎压预警：低压";
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         this.arr2[3] = "胎压预警：高压";
      }

      if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         this.arr2[3] = "胎压预警：正常";
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         this.arr2[5] = "胎温预警：高温";
      } else {
         this.arr2[5] = "胎温预警：正常";
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         this.arr2[4] = "系统：异常";
         this.arr3[4] = "系统：异常";
      } else {
         this.arr2[4] = "系统：正常";
         this.arr3[4] = "系统：正常";
      }

      if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
      } else {
         this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
      }

      if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
      } else {
         this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
      }

      if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
      } else {
         this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
      }

      if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
      } else {
         this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
      }

      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void set0x40TireInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      boolean var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  if (var2[4] == 255) {
                     this.arr3[0] = "温度：--" + this.getTempUnitC(this.mContext);
                  } else {
                     this.arr3[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + this.getTempUnitC(this.mContext);
                  }

                  if (this.mCanBusInfoInt[3] == 255) {
                     this.arr3[1] = "胎压：--KPa";
                  } else {
                     this.arr3[1] = "胎压：" + this.mCanBusInfoInt[3] + "KPa";
                  }

                  if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
                     this.arr3[2] = "系统：故障";
                     var3 = true;
                  } else {
                     this.arr3[2] = "系统：正常";
                     var3 = false;
                  }

                  if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
                     this.arr3[3] = "信号：丢失";
                     var3 = true;
                  } else {
                     this.arr3[3] = "信号：正常";
                  }

                  if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
                     this.arr3[4] = "系统自检：异常";
                     var3 = true;
                  } else {
                     this.arr3[4] = "系统自检：正常";
                  }

                  if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
                     this.arr3[5] = "胎压故障灯：开";
                     var3 = true;
                  } else {
                     this.arr3[5] = "胎压故障灯：关";
                  }

                  if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
                     this.arr3[6] = "电池：电量低";
                     var3 = true;
                  } else {
                     this.arr3[6] = "电池：正常";
                  }

                  if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
                     this.arr3[7] = "胎温：高温";
                     var3 = true;
                  }

                  if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                     this.arr3[7] = "胎温：超高温";
                     var3 = true;
                  }

                  if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                     this.arr3[7] = "胎温：正常";
                  }

                  if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
                     this.arr3[8] = "气压：低";
                     var3 = true;
                  }

                  if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                     this.arr3[8] = "气压：高";
                     var3 = true;
                  }

                  if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                     this.arr3[8] = "气压：正常";
                  }

                  if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
                     this.arr3[9] = "漏气：慢速";
                     var3 = true;
                  }

                  if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                     this.arr3[9] = "漏气：快速";
                     var3 = true;
                  }

                  if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                     this.arr3[9] = "漏气：无";
                  }

                  if (!var3) {
                     this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
                  } else {
                     this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
                  }
               }
            } else {
               if (var2[4] == 255) {
                  this.arr2[0] = "温度：--" + this.getTempUnitC(this.mContext);
               } else {
                  this.arr2[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + this.getTempUnitC(this.mContext);
               }

               if (this.mCanBusInfoInt[3] == 255) {
                  this.arr2[1] = "胎压：--KPa";
               } else {
                  this.arr2[1] = "胎压：" + this.mCanBusInfoInt[3] + "KPa";
               }

               if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
                  this.arr2[2] = "系统：故障";
                  var3 = true;
               } else {
                  this.arr2[2] = "系统：正常";
                  var3 = false;
               }

               if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
                  this.arr2[3] = "信号：丢失";
                  var3 = true;
               } else {
                  this.arr2[3] = "信号：正常";
               }

               if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
                  this.arr2[4] = "系统自检：异常";
                  var3 = true;
               } else {
                  this.arr2[4] = "系统自检：正常";
               }

               if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
                  this.arr2[5] = "胎压故障灯：开";
                  var3 = true;
               } else {
                  this.arr2[5] = "胎压故障灯：关";
               }

               if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
                  this.arr2[6] = "电池：电量低";
                  var3 = true;
               } else {
                  this.arr2[6] = "电池：正常";
               }

               if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
                  this.arr2[7] = "胎温：高温";
                  var3 = true;
               }

               if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                  this.arr2[7] = "胎温：超高温";
                  var3 = true;
               }

               if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                  this.arr2[7] = "胎温：正常";
               }

               if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
                  this.arr2[8] = "气压：低";
                  var3 = true;
               }

               if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                  this.arr2[8] = "气压：高";
                  var3 = true;
               }

               if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                  this.arr2[8] = "气压：正常";
               }

               if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
                  this.arr2[9] = "漏气：慢速";
                  var3 = true;
               }

               if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                  this.arr2[9] = "漏气：快速";
                  var3 = true;
               }

               if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                  this.arr2[9] = "漏气：无";
               }

               if (!var3) {
                  this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
               } else {
                  this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
               }
            }
         } else {
            if (var2[4] == 255) {
               this.arr1[0] = "温度：--" + this.getTempUnitC(this.mContext);
            } else {
               this.arr1[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + this.getTempUnitC(this.mContext);
            }

            if (this.mCanBusInfoInt[3] == 255) {
               this.arr1[1] = "胎压：--KPa";
            } else {
               this.arr1[1] = "胎压：" + this.mCanBusInfoInt[3] + "KPa";
            }

            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
               this.arr1[2] = "系统：故障";
               var3 = true;
            } else {
               this.arr1[2] = "系统：正常";
               var3 = false;
            }

            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
               this.arr1[3] = "信号：丢失";
               var3 = true;
            } else {
               this.arr1[3] = "信号：正常";
            }

            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
               this.arr1[4] = "系统自检：异常";
               var3 = true;
            } else {
               this.arr1[4] = "系统自检：正常";
            }

            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
               this.arr1[5] = "胎压故障灯：开";
               var3 = true;
            } else {
               this.arr1[5] = "胎压故障灯：关";
            }

            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
               this.arr1[6] = "电池：电量低";
               var3 = true;
            } else {
               this.arr1[6] = "电池：正常";
            }

            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
               this.arr1[7] = "胎温：高温";
               var3 = true;
            }

            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
               this.arr1[7] = "胎温：超高温";
               var3 = true;
            }

            if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
               this.arr1[7] = "胎温：正常";
            }

            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
               this.arr1[8] = "气压：低";
               var3 = true;
            }

            if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
               this.arr1[8] = "气压：高";
               var3 = true;
            }

            if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
               this.arr1[8] = "气压：正常";
            }

            if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
               this.arr1[9] = "漏气：慢速";
               var3 = true;
            }

            if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
               this.arr1[9] = "漏气：快速";
               var3 = true;
            }

            if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
               this.arr1[9] = "漏气：无";
            }

            if (!var3) {
               this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
            } else {
               this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
            }
         }
      } else {
         if (var2[4] == 255) {
            this.arr0[0] = "温度：--" + this.getTempUnitC(this.mContext);
         } else {
            this.arr0[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + this.getTempUnitC(this.mContext);
         }

         if (this.mCanBusInfoInt[3] == 255) {
            this.arr0[1] = "胎压：--KPa";
         } else {
            this.arr0[1] = "胎压：" + this.mCanBusInfoInt[3] + "KPa";
         }

         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            this.arr0[2] = "系统：故障";
            var3 = true;
         } else {
            this.arr0[2] = "系统：正常";
            var3 = false;
         }

         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
            this.arr0[3] = "信号：丢失";
            var3 = true;
         } else {
            this.arr0[3] = "信号：正常";
         }

         if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
            this.arr0[4] = "系统自检：异常";
            var3 = true;
         } else {
            this.arr0[4] = "系统自检：正常";
         }

         if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
            this.arr0[5] = "胎压故障灯：开";
            var3 = true;
         } else {
            this.arr0[5] = "胎压故障灯：关";
         }

         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            this.arr0[6] = "电池：电量低";
            var3 = true;
         } else {
            this.arr0[6] = "电池：正常";
         }

         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
            this.arr0[7] = "胎温：高温";
            var3 = true;
         }

         if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
            this.arr0[7] = "胎温：超高温";
            var3 = true;
         }

         if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
            this.arr0[7] = "胎温：正常";
         }

         if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
            this.arr0[8] = "气压：低";
            var3 = true;
         }

         if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
            this.arr0[8] = "气压：高";
            var3 = true;
         }

         if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
            this.arr0[8] = "气压：正常";
         }

         if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
            this.arr0[9] = "漏气：慢速";
            var3 = true;
         }

         if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
            this.arr0[9] = "漏气：快速";
            var3 = true;
         }

         if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
            this.arr0[9] = "漏气：无";
         }

         if (!var3) {
            this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
         }
      }

      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void set0x41SettingsInfo(Context var1) {
      ArrayList var2 = new ArrayList();
      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_atmosphere_lamp") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_atmosphere_lamp"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_atmosphere_lamp"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
      }

      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_chair") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_chair"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
      }

      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_the_light_that_accompanies_me_home") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_the_light_that_accompanies_me_home"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
      }

      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_lane_departure") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_lane_departure"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
      }

      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_the_headlamps") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_the_headlamps"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
      }

      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_environmental_lighting") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_environmental_lighting"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_environmental_lighting"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_setting_environmental_lighting"), 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) - 1));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x42PanoramicData() {
      if (this.isPanoramicDataChange()) {
         ArrayList var4 = new ArrayList();
         int var1 = this.mCanBusInfoInt[2];
         boolean var3 = true;
         if (var1 == 0) {
            var4.add(new PanoramicBtnUpdateEntity(0, false));
            var4.add(new PanoramicBtnUpdateEntity(1, false));
            var4.add(new PanoramicBtnUpdateEntity(2, false));
            var4.add(new PanoramicBtnUpdateEntity(3, false));
            var4.add(new PanoramicBtnUpdateEntity(4, false));
         } else if (var1 == 1) {
            var4.add(new PanoramicBtnUpdateEntity(0, true));
            var4.add(new PanoramicBtnUpdateEntity(1, false));
            var4.add(new PanoramicBtnUpdateEntity(2, false));
            var4.add(new PanoramicBtnUpdateEntity(3, false));
            var4.add(new PanoramicBtnUpdateEntity(4, false));
         } else {
            boolean var2;
            if (this.mCanBusInfoInt[2] == 4) {
               var2 = true;
            } else {
               var2 = false;
            }

            var4.add(new PanoramicBtnUpdateEntity(0, var2));
            if (this.mCanBusInfoInt[2] == 5) {
               var2 = true;
            } else {
               var2 = false;
            }

            var4.add(new PanoramicBtnUpdateEntity(1, var2));
            if (this.mCanBusInfoInt[2] == 6) {
               var2 = true;
            } else {
               var2 = false;
            }

            var4.add(new PanoramicBtnUpdateEntity(2, var2));
            if (this.mCanBusInfoInt[2] == 7) {
               var2 = true;
            } else {
               var2 = false;
            }

            var4.add(new PanoramicBtnUpdateEntity(3, var2));
            if (this.mCanBusInfoInt[2] == 8) {
               var2 = var3;
            } else {
               var2 = false;
            }

            var4.add(new PanoramicBtnUpdateEntity(4, var2));
         }

         GeneralParkData.dataList = var4;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x43SettingsInfo(Context var1) {
      ArrayList var2 = new ArrayList();
      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 12, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 13, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 14, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 15, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 16, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 17, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 18, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 19, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 20, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 21, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 22, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 23, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 24, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 25, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 26, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_switch"), 27, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1)));
      }

      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_level_setting") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_level_setting"), 0, this.mCanBusInfoInt[2]));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_level_setting"), 1, this.mCanBusInfoInt[10]));
      }

      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_mode_selection") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_mode_selection"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_mode_selection"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_mode_selection"), 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 2)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_mode_selection"), 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_mode_selection"), 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_mode_selection"), 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 3)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_function_mode_selection"), 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3)));
      }

      if (this.getmUigMgr(var1).getLeftIndexes(var1, "_327_Face_recognition") != -1) {
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_Face_recognition"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1)));
         var2.add(new SettingUpdateEntity(this.getmUigMgr(var1).getLeftIndexes(var1, "_327_Face_recognition"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 2)));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x47OperationInfo() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var2.append((var3[2] * 256 + var3[3]) / 10).append("V").toString()));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var2.append((var3[4] * 256 + var3[5] - 6000) / 10).append("A").toString()));
      var1.add(new DriverUpdateEntity(0, 2, this.mCanBusInfoInt[6] + "%"));
      var1.add(new DriverUpdateEntity(0, 3, this.mCanBusInfoInt[7] - 50 + this.getTempUnitC(this.mContext)));
      var1.add(new DriverUpdateEntity(0, 4, this.mCanBusInfoInt[8] - 50 + this.getTempUnitC(this.mContext)));
      var1.add(new DriverUpdateEntity(0, 5, this.mCanBusInfoInt[9] - 50 + this.getTempUnitC(this.mContext)));
      var1.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[10] - 50 + this.getTempUnitC(this.mContext)));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 7, var2.append(var3[11] * 256 + var3[12] - 200).append("KW").toString()));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 8, var5.append(var4[13] * 256 + var4[14] - 32000).append("RPM").toString()));
      var2 = (new StringBuilder()).append("上部分值").append(this.mCanBusInfoInt[15]).append("V / 下部分值");
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 9, var2.append((var3[16] * 256 + var3[17]) / 100).append("V").toString()));
      var5 = (new StringBuilder()).append("上部分值").append(this.mCanBusInfoInt[18]).append("V / 下部分值");
      var4 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 10, var5.append((var4[19] * 256 + var4[20]) / 100).append("V").toString()));
      var1.add(new DriverUpdateEntity(0, 11, this.mCanBusInfoInt[21] + this.getTempUnitC(this.mContext)));
      var1.add(new DriverUpdateEntity(0, 12, this.mCanBusInfoInt[22] + this.getTempUnitC(this.mContext)));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x48CarInfo(Context var1) {
      ArrayList var3 = new ArrayList();
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 0, var5.append((var4[2] * 256 + var4[3]) / 10).append("Kwh").toString()));
      StringBuilder var8 = new StringBuilder();
      int[] var9 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 1, var8.append((var9[4] * 256 + var9[5]) / 10).append("Kwh").toString()));
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 2, var5.append((var4[6] * 256 + var4[7]) / 2).append("KM").toString()));
      var3.add(new DriverUpdateEntity(0, 3, this.mCanBusInfoInt[8] - 40 + this.getTempUnitC(var1)));
      StringBuilder var6 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 4, var6.append((var4[9] * 256 + var4[10]) / 10).append("A").toString()));
      var8 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 5, var8.append((var7[11] * 256 + var7[12]) / 10).append("V").toString()));
      var3.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[13] + "分"));
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 0, 1) == 0) {
         var3.add(new DriverUpdateEntity(0, 7, (this.mCanBusInfoInt[14] >> 1) * 10 + "分/保温关闭"));
      } else {
         var3.add(new DriverUpdateEntity(0, 7, "保温开启/" + (this.mCanBusInfoInt[14] >> 1) * 10 + "分"));
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 3, 1) == 0) {
         var3.add(new DriverUpdateEntity(0, 8, "False"));
      } else {
         var3.add(new DriverUpdateEntity(0, 8, "True"));
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 3) == 0) {
         var3.add(new DriverUpdateEntity(0, 9, "No Error 无错误"));
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 3) == 1) {
         var3.add(new DriverUpdateEntity(0, 9, " Warning 警告运行（一级）"));
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 3) == 2) {
         var3.add(new DriverUpdateEntity(0, 9, "Derating 降功率运行（二级）"));
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 3) == 3) {
         var3.add(new DriverUpdateEntity(0, 9, "Disable 禁用 （三级）"));
      }

      int var2 = this.mCanBusInfoInt[16];
      if (var2 == 0) {
         var3.add(new DriverUpdateEntity(0, 10, "Normal"));
      } else if (var2 == 1) {
         var3.add(new DriverUpdateEntity(0, 10, "Grade 1"));
      } else if (var2 == 2) {
         var3.add(new DriverUpdateEntity(0, 10, "Grade 2"));
      } else if (var2 == 3) {
         var3.add(new DriverUpdateEntity(0, 10, "Grade 3"));
      }

      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x49BackWarning(Context var1) {
   }

   private void tokingNowTime(int var1) {
      int var2 = var1 % 3600;
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)(var2 % 60), (byte)(var2 / 60), (byte)(var1 / 3600), 0});
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 0, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneCallLogInfoChange(int var1, int var2, String var3) {
      super.btPhoneCallLogInfoChange(var1, var2, var3);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1});
      this.tokingNowTime(0);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTPHONE.name(), new byte[]{22, -64, 5, -1, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTPHONE.name(), new byte[]{22, -64, 3, -1, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 1});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      this.tokingNowTime(var4);
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
      if (var3 != 48) {
         if (var3 != 56) {
            if (var3 != 57) {
               switch (var3) {
                  case 32:
                     this.set0x20WheelKey(var1);
                     return;
                  case 33:
                     this.set0x21AirInfo(var1);
                     return;
                  case 34:
                     this.set0x22RearRadarInfo();
                     return;
                  case 35:
                     this.set0x23FrontRadarInfo();
                     return;
                  case 36:
                     this.set0x24BaseInfo();
                     return;
                  case 37:
                     this.set0x25RearRadarInfo();
                     return;
                  case 38:
                     this.set0x26FrontRadarInfo();
                     return;
                  case 39:
                     this.set0x27OutDoorTemperature(var1);
                     return;
                  case 40:
                     this.set0x28TrackData();
                     return;
                  case 41:
                     this.set0x29TrackData();
                     return;
                  default:
                     switch (var3) {
                        case 64:
                           this.set0x40TireInfo();
                           return;
                        case 65:
                           this.set0x41SettingsInfo(var1);
                           return;
                        case 66:
                           this.set0x42PanoramicData();
                           return;
                        case 67:
                           this.set0x43SettingsInfo(var1);
                           return;
                        default:
                           switch (var3) {
                              case 71:
                                 this.set0x47OperationInfo();
                                 return;
                              case 72:
                                 this.set0x48CarInfo(var1);
                                 return;
                              case 73:
                                 this.set0x49BackWarning(var1);
                           }
                     }
               }
            } else {
               this.set0x39TireAlertInfo();
            }

            return;
         }
      } else {
         this.set0x30VersionInfo();
      }

      this.set0x38TireInfo();
   }

   public void countDownTimeUpdateSettings(int var1, int var2, Long var3) {
      ArrayList var4 = new ArrayList();
      if (var3 == 1L) {
         var4.add(new SettingUpdateEntity(var1, var2 - 1, 0));
         var4.add(new SettingUpdateEntity(var1, var2, "OFF"));
      } else {
         var4.add(new SettingUpdateEntity(var1, var2, var3 + "秒"));
      }

      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)(var1 - 2010), (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7});
      this.nowYear = var1;
      this.nowMonth = var3;
      this.nowDay = var4;
      this.nowHours = var5;
      this.nowMins = var6;
      this.nowSecond = var7;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.currentCanDifferentId = this.getCurrentCanDifferentId();
      this.currentEachCanId = this.getCurrentEachCanId();
      this.mContext = var1;
      GeneralTireData.isHaveSpareTire = false;
      this.getmUigMgr(this.mContext).CartypeSend();
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.sendMusic("", "");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         var1 = (byte)(var4 & 255);
         var5 = (byte)(var4 >> 8 & 255);
         var2 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, var1, var5, var2, var9, var6, var7});
         this.sendMusic(var21, var23);
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      MyLog.e("ceshi", SourceConstantsDef.SOURCE_ID.FM.name());
      byte var10 = this.getAllBandTypeData(var2);
      this.getFreqByteHiLo(var2, var3);
      byte var9 = (byte)var10;
      byte var6 = this.freqLo;
      byte var7 = this.freqHi;
      byte var8 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, var9, var6, var7, var8, 0, 0});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void updateAppointmentSettings(int var1, int var2, int var3) {
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

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var2, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var3, var4, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         var5 = (byte)(var4 & 255);
         var1 = (byte)(var4 >> 8 & 255);
         var2 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 17, var5, var1, var2, var9, var6, var7});
      }
   }

   public void voiceControlInfo(String var1) {
      super.voiceControlInfo(var1);
      var1.hashCode();
      if (!var1.equals("skylight.open")) {
         if (var1.equals("skylight.close")) {
            this.sendVoiceMsg(0, false);
         }
      } else {
         this.sendVoiceMsg(0, true);
      }

   }
}
