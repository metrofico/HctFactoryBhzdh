package com.hzbhd.canbus.car._295;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static int carSpeedWarning;
   static int frontWindLv;
   static float leftFrontTemp;
   static float rearTemp;
   static float rightFrontTemp;
   private int[] arrConvConsumers = new int[]{2131770297, 2131770307, 2131770308, 2131770309, 2131770310, 2131770311, 2131770312, 2131770313, 2131770314, 2131770298, 2131770299, 2131770300, 2131770301, 2131770302, 2131770303, 2131770304, 2131770305, 2131770306};
   private int[] arrReportsHw = $d2j$hex$0325f41d$decode_I("c13c107f303d107f4a3d107f553d107f603d107f6b3d107f763d107fee3c107ff93c107f043d107f0f3d107f1a3d107f253d107f313d107f3c3d107f423d107f433d107f703d107f713d107f723d107f733d107f743d107f753d107f773d107f783d107f793d107f7a3d107f7b3d107f7c3d107f7d3d107f7e3d107f7f3d107f803d107f823d107f833d107f843d107f853d107f863d107f873d107f883d107f893d107f8a3d107f8b3d107f8d3d107f8e3d107f8f3d107f903d107f913d107f923d107f933d107f943d107f953d107f963d107fc33c107fc43c107fc53c107fc63c107fc73c107fc83c107fc93c107fca3c107fcb3c107fcc3c107fce3c107fcf3c107fd03c107fd13c107fd23c107fd33c107fd43c107fd53c107fd63c107fd73c107fd93c107fda3c107fdb3c107fdc3c107fdd3c107fde3c107fdf3c107fe03c107fe13c107fff3c107f003d107f013d107f023d107f033d107f053d107f063d107f073d107f083d107f093d107f0a3d107f0b3d107f0c3d107f0d3d107f0e3d107f103d107f113d107f123d107f133d107f143d107f153d107f163d107f173d107f443d107f453d107f463d107f473d107f483d107f493d107f4b3d107f4c3d107f4d3d107f4e3d107f4f3d107f503d107f513d107f523d107f533d107f543d107f563d107f573d107f583d107f593d107f5a3d107f5b3d107f5c3d107f5d3d107f5e3d107f5f3d107f613d107f623d107f633d107f643d107f653d107f663d107f673d107f683d107f693d107f6a3d107f6c3d107f6d3d107f6e3d107f6f3d107fe23c107fe43c107fe53c107fe63c107fe73c107fe83c107fe93c107fea3c107feb3c107fec3c107fed3c107fef3c107ff03c107ff13c107ff23c107ff33c107ff43c107ff53c107ff63c107ff73c107ff83c107f183d107f193d107f1b3d107f1c3d107f1d3d107f1e3d107f1f3d107f203d107f213d107f223d107f233d107f243d107f263d107f273d107f283d107f293d107f2a3d107f2b3d107f2c3d107f2d3d107f2e3d107f2f3d107f323d107f333d107f343d107f353d107f363d107f373d107f383d107f393d107f3a3d107f3b3d107f3d3d107f3e3d107f3f3d107f403d107f413d107f");
   private int[] arrStartStopHw = new int[]{2131770786, 2131770797, 2131770808, 2131770819, 2131770820, 2131770821, 2131770822, 2131770823, 2131770824, 2131770787, 2131770788, 2131770789, 2131770812, 2131770790, 2131770808, 2131770794, 2131770795, 2131770813, 2131770814, 2131770815, 2131770791, 2131770792, 2131770793, 2131770794, 2131770795, 2131770796, 2131770798, 2131770799, 2131770800, 2131770801, 2131770802, 2131770803, 2131770804, 2131770805, 2131770806, 2131770807, 2131770809, 2131770810, 2131770811, 2131770794, 2131770817, 2131770818, 2131770776, 2131770777, 2131770778, 2131770784, 2131770779, 2131770780, 2131770781, 2131770782, 2131770783};
   private boolean isFirst = true;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private List mList1 = new ArrayList();
   private List mList2 = new ArrayList();
   private List mList3 = new ArrayList();

   private int distanceToLocation0(int var1) {
      if (var1 >= 0 && var1 <= 5) {
         return 1;
      } else if (var1 >= 6 && var1 <= 10) {
         return 2;
      } else if (var1 >= 11 && var1 <= 15) {
         return 3;
      } else if (var1 >= 16 && var1 <= 20) {
         return 4;
      } else if (var1 >= 21 && var1 <= 25) {
         return 5;
      } else if (var1 >= 26 && var1 <= 30) {
         return 6;
      } else if (var1 >= 31 && var1 <= 35) {
         return 7;
      } else if (var1 >= 36 && var1 <= 40) {
         return 8;
      } else if (var1 >= 41 && var1 <= 45) {
         return 9;
      } else if (var1 >= 46 && var1 <= 50) {
         return 10;
      } else if (var1 >= 51 && var1 <= 55) {
         return 11;
      } else {
         return var1 >= 60 ? 12 : 0;
      }
   }

   private int distanceToLocation1(int var1) {
      if (var1 >= 0 && var1 <= 15) {
         return 1;
      } else if (var1 >= 16 && var1 <= 30) {
         return 2;
      } else if (var1 >= 31 && var1 <= 45) {
         return 3;
      } else if (var1 >= 46 && var1 <= 60) {
         return 4;
      } else if (var1 >= 61 && var1 <= 75) {
         return 5;
      } else if (var1 >= 76 && var1 <= 90) {
         return 6;
      } else if (var1 >= 91 && var1 <= 105) {
         return 7;
      } else if (var1 >= 106 && var1 <= 120) {
         return 8;
      } else if (var1 >= 121 && var1 <= 135) {
         return 9;
      } else if (var1 >= 136 && var1 <= 150) {
         return 10;
      } else if (var1 >= 151 && var1 <= 165) {
         return 11;
      } else {
         return var1 >= 166 ? 12 : 0;
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

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIndexBy3Bit(int var1) {
      byte var2 = 2;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private int getIndexBy4Bit(int var1) {
      byte var2 = 3;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            var2 = 2;
            return var2;
         }

         if (var1 == 3) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private int getIndexBy5Bit(int var1) {
      byte var2 = 4;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            var2 = 2;
            return var2;
         }

         if (var1 == 3) {
            var2 = 3;
            return var2;
         }

         if (var1 == 4) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private String getResString(int var1) {
      return this.mContext.getResources().getString(var1);
   }

   private TireUpdateEntity getTireEntity(int var1, String var2, String var3, String var4) {
      return new TireUpdateEntity(var1, var2.equals(this.mContext.getResources().getString(2131767766)) ^ 1, new String[]{var2, var3, var4});
   }

   private String getTirePressure(int var1, int var2) {
      StringBuilder var4 = (new StringBuilder()).append((float)((double)(var1 * 256 + var2) * 0.1));
      String var3 = "";
      String var5 = var4.append("").toString();
      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               var3 = "psi";
            }
         } else {
            var3 = "bar";
         }
      } else {
         var3 = "kPa";
      }

      return var5 + var3;
   }

   private String getTisWarmMsg(int var1) {
      return var1 == 0 ? this.mContext.getResources().getString(2131767766) : this.mContext.getResources().getString(2131767772);
   }

   private boolean isOnlyData6Change() {
      return SharePreUtil.getIntValue(this.mContext, "_295_0x21_data6", 0) != this.mCanBusInfoInt[8];
   }

   private boolean isOnlyData9Change() {
      return SharePreUtil.getIntValue(this.mContext, "_295_0x21_data9", 0) != this.mCanBusInfoInt[11];
   }

   private boolean isOnlyRearAirDataChange() {
      return SharePreUtil.getIntValue(this.mContext, "_295_0x21_data7", 0) != this.mCanBusInfoInt[9];
   }

   private static List mergeLists(List... var0) {
      int var1 = 0;
      Class var3 = var0[0].getClass();

      List var5;
      try {
         var5 = (List)var3.newInstance();
      } catch (Exception var4) {
         var4.printStackTrace();
         var5 = null;
      }

      for(int var2 = var0.length; var1 < var2; ++var1) {
         var5.addAll(var0[var1]);
      }

      return var5;
   }

   private void realKeyClick(int var1) {
      this.realKeyClick2(this.mContext, var1, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
   }

   private void realKeyClick2(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (31 == var1) {
         var2 = "HI";
      } else if (GeneralAirData.fahrenheit_celsius) {
         var2 = (double)((float)var1 * 0.5F) + 15.5 + this.getTempUnitC(this.mContext);
      } else {
         var2 = (int)(((double)((float)var1 * 0.5F) + 15.5) * 1.8 + 32.0) + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      byte[] var2 = this.mCanBusInfoByte;
      int var1 = var2[3] + var2[4] * 256;
      String var3;
      if (!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         var3 = (float)(var1 / 10) + this.getTempUnitC(this.mContext);
      } else {
         var3 = (float)(var1 / 10) + this.getTempUnitF(this.mContext);
      }

      return var3;
   }

   private void sendSourceMsg1(String var1) {
      byte[] var2 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -111, 0, 0}, var2), 28));
   }

   private void sendSourceMsg2(String var1, int var2) {
      byte[] var3 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, (byte)var2, 0}, var3), 27));
   }

   private void setAir0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_lock = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
      GeneralAirData.eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]) ^ true;
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 3);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3);
      boolean var2 = this.isOnlyRearAirDataChange();
      boolean var1 = this.isOnlyData6Change();
      boolean var3 = this.isOnlyData9Change();
      if (!var2 && !var1 && !var3) {
         this.updateAirActivity(this.mContext, 1001);
      } else if (var2 && !this.isOnlyData6Change() && !var3) {
         SharePreUtil.setIntValue(this.mContext, "_295_0x21_data7", this.mCanBusInfoInt[9]);
         GeneralAirData.rear_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
         this.updateAirActivity(this.mContext, 1002);
      }

      if (var1) {
         SharePreUtil.setIntValue(this.mContext, "_295_0x21_data6", this.mCanBusInfoInt[8]);
         this.startSettingActivity(this.mContext, 0, 0);
      }

      if (var3) {
         SharePreUtil.setIntValue(this.mContext, "_295_0x21_data9", this.mCanBusInfoInt[11]);
         this.updateGeneralSettingData(new ArrayList());
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setAllow0x7f() {
   }

   private void setCarReports() {
   }

   private void setCarService() {
   }

   private void setCarStatus0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
      ArrayList var2 = new ArrayList();
      int var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         var1 = 2131768423;
      } else {
         var1 = 2131768424;
      }

      var2.add(new DriverUpdateEntity(0, 1, this.getResString(var1)));
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var1 = 2131769504;
      } else {
         var1 = 2131768042;
      }

      var2.add(new DriverUpdateEntity(0, 2, this.getResString(var1)));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarStatus0x40() {
   }

   private void setCarTrack0x64() {
   }

   private void setCarWarningInfo() {
      ArrayList var5 = new ArrayList();
      int[] var6 = this.mCanBusInfoInt;
      int var2 = var6[2];
      int var1 = var2;
      if (var2 > 16) {
         var1 = 16;
      }

      String[] var4 = new String[var1];

      for(var2 = 0; var2 < var1; ++var2) {
         int var3 = var2 + 3;
         if (var6[var3] >= this.arrReportsHw.length) {
            var4[var2] = " NULL ";
         } else {
            var4[var2] = this.mContext.getResources().getString(this.arrReportsHw[var6[var3]]);
            var5.add(new WarningEntity(var4[var2]));
         }
      }

      this.mList1.clear();
      this.mList1.addAll(var5);
      GeneralWarningDataData.dataList = mergeLists(this.mList1, this.mList2, this.mList3);
      this.updateWarningActivity((Bundle)null);
   }

   private void setCarWarningInfo0x75() {
      ArrayList var4 = new ArrayList();
      int[] var6 = this.mCanBusInfoInt;
      int var2 = var6[2];
      int var1 = var2;
      if (var2 > 7) {
         var1 = 7;
      }

      String[] var5 = new String[var1];

      for(var2 = 0; var2 < var1; ++var2) {
         int var3 = var2 + 3;
         if (var6[var3] >= this.arrStartStopHw.length) {
            var5[var2] = " NULL ";
         } else {
            var5[var2] = this.mContext.getResources().getString(this.arrStartStopHw[var6[var3]]);
            var4.add(new WarningEntity(var5[var2]));
         }
      }

      this.mList2.clear();
      this.mList2.addAll(var4);
      GeneralWarningDataData.dataList = mergeLists(this.mList1, this.mList2, this.mList3);
      this.updateWarningActivity((Bundle)null);
   }

   private void setCarWarningInfo0x77() {
      ArrayList var4 = new ArrayList();
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[2];
      int var1 = var2;
      if (var2 > 7) {
         var1 = 7;
      }

      String[] var3 = new String[var1];

      for(var2 = 0; var2 < var1; ++var2) {
         if (var5[var2 + 2] >= this.arrConvConsumers.length) {
            var3[var2] = " NULL ";
         } else {
            var3[var2] = this.mContext.getResources().getString(this.arrConvConsumers[var5[var2 + 3]]);
            var4.add(new WarningEntity(var3[var2]));
         }
      }

      this.mList3.clear();
      this.mList3.addAll(var4);
      GeneralWarningDataData.dataList = mergeLists(this.mList1, this.mList2, this.mList3);
      this.updateWarningActivity((Bundle)null);
   }

   private void setConvenienceConsumers0x62() {
   }

   private void setDrive0x14() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769222);
      } else if (var1 == 255) {
         var2 = this.mContext.getResources().getString(2131769223);
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      var1 = this.mCanBusInfoInt[3];
      String var3;
      if (var1 == 0) {
         var3 = this.mContext.getResources().getString(2131769222);
      } else if (var1 == 255) {
         var3 = this.mContext.getResources().getString(2131769223);
      } else {
         var3 = this.mCanBusInfoInt[3] + "";
      }

      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(0, 0, var2));
      var4.add(new DriverUpdateEntity(0, 1, var3));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrivingData0x50() {
   }

   private void setEnvironmentTemp0x27() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setEsp0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 19918, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setFrontRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 12;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationDataType2(11, var1[2], 11, this.distanceToLocation1(var1[3]), 11, this.distanceToLocation1(this.mCanBusInfoInt[4]), 60, this.mCanBusInfoInt[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setParkingAssistStatus0x25() {
      ArrayList var2 = new ArrayList();
      int var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var1 = 2131769504;
      } else {
         var1 = 2131768042;
      }

      var2.add(new DriverUpdateEntity(0, 3, this.getResString(var1)));
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var1 = 2131770832;
      } else {
         var1 = 2131770831;
      }

      var2.add(new DriverUpdateEntity(0, 4, this.getResString(var1)));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRearRadar() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationDataType2(60, var1[2], 165, var1[3], 165, var1[4], 60, var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRemind0x60() {
   }

   private void setSwc0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 6) {
                        this.realKeyClick(3);
                     }
                  } else {
                     this.realKeyClick(21);
                  }
               } else {
                  this.realKeyClick(20);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void setSwc0x2f() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 17) {
               if (var1 == 18) {
                  this.realKeyClick2(14);
               }
            } else {
               this.realKeyClick2(14);
            }
         } else {
            this.realKeyClick2(46);
         }
      } else {
         this.realKeyClick2(45);
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendSourceMsg1("AUX");
      this.sendSourceMsg2(" ", 146);
      this.sendSourceMsg2(" ", 147);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendSourceMsg1("BT MUSIC");
      this.sendSourceMsg2(" ", 146);
      this.sendSourceMsg2(" ", 147);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -107}, 27));
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -106}, 27));
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      String var5 = new String(var1);
      var1 = new byte[0];

      byte[] var8;
      label23: {
         try {
            var8 = var5.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            break label23;
         }

         var1 = var8;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -107, 0}, var1), 27));
      var5 = var4 / 60 + ":" + var4 % 60;
      var1 = new byte[0];

      label18: {
         try {
            var8 = var5.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
            break label18;
         }

         var1 = var8;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -106, 0}, var1), 27));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 22) {
            if (var3 != 39) {
               if (var3 != 41) {
                  if (var3 != 64) {
                     if (var3 != 80) {
                        if (var3 != 127) {
                           if (var3 != 47) {
                              if (var3 != 48) {
                                 switch (var3) {
                                    case 32:
                                       this.setSwc0x20();
                                       break;
                                    case 33:
                                       this.setAir0x21();
                                       break;
                                    case 34:
                                       this.setRearRadar();
                                       break;
                                    case 35:
                                       this.setFrontRadar();
                                       break;
                                    case 36:
                                       this.setCarStatus0x24();
                                       break;
                                    case 37:
                                       this.setParkingAssistStatus0x25();
                                       break;
                                    default:
                                       switch (var3) {
                                          case 96:
                                             this.setRemind0x60();
                                             break;
                                          case 97:
                                             this.setCarReports();
                                             break;
                                          case 98:
                                             this.setConvenienceConsumers0x62();
                                             break;
                                          case 99:
                                             this.setCarService();
                                             break;
                                          case 100:
                                             this.setCarTrack0x64();
                                       }
                                 }
                              } else {
                                 this.setVersionInfo();
                              }
                           } else {
                              this.setSwc0x2f();
                           }
                        } else {
                           this.setAllow0x7f();
                        }
                     } else {
                        this.setDrivingData0x50();
                     }
                  } else {
                     this.setCarStatus0x40();
                  }
               } else {
                  this.setEsp0x29();
               }
            } else {
               this.setEnvironmentTemp0x27();
            }
         } else {
            this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[3], var4[2]));
         }
      } else {
         this.setDrive0x14();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, (boolean)var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte)(var12 ^ 1), (byte)var5, (byte)var6, 0, 0, (byte)var10, (byte)var2, (byte)var3, (byte)var4, (byte)var9});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      boolean var15 = true;
      if (var7 != 1) {
         var15 = false;
      }

      byte var16 = (byte)(var2 / 3600);
      byte var17 = (byte)(var2 / 60 % 60);
      byte var14 = (byte)(var2 % 60);
      StringBuilder var18 = new StringBuilder();
      if (var15) {
         var11 = "  DVD  ";
      } else {
         var11 = "  CD  ";
      }

      byte[] var19 = var18.append(var11).append(Util.numCompensateZero(var16)).append(":").append(Util.numCompensateZero(var17)).append(":").append(Util.numCompensateZero(var14)).toString().getBytes();
      CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -111}, var19), 28));
      if (this.isFirst) {
         this.isFirst = false;
         CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(new byte[]{22, -110}, 27));
         CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(new byte[]{22, -109}, 27));
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendSourceMsg1("DTV");
      this.sendSourceMsg2(" ", 146);
      this.sendSourceMsg2(" ", 147);
   }

   public void initCommand(Context var1) {
      GeneralTireData.isHaveSpareTire = false;
      super.initCommand(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var28 = new byte[0];

      byte[] var29;
      label33: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var21.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var27) {
            var27.printStackTrace();
            break label33;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, var28), 28);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
      var28 = new byte[0];

      label28: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var23.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var26) {
            var26.printStackTrace();
            break label28;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -110, 0}, var28), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
      var28 = new byte[0];

      label23: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var22.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var25) {
            var25.printStackTrace();
            break label23;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -109, 0}, var28), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte[] var7 = (var3 + " " + this.getBandUnit(var2)).getBytes();
      var7 = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, var7), 28);
      byte[] var6 = var2.getBytes();
      var6 = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -110, 0}, var6), 27);
      CanbusMsgSender.sendMsg(var7);
      CanbusMsgSender.sendMsg(var6);
      this.sendSourceMsg2(" ", 147);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendSourceMsg1("OFF");
      this.sendSourceMsg2(" ", 146);
      this.sendSourceMsg2(" ", 147);
   }

   void updateAirSet(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   void updateLanguageSet(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = (var6 + ":" + var7 + "   ").getBytes();
      var18 = DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -111, 0, 0}, var18), 28);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      var18 = ((var9 & 255) * 256 + (var3 & 255) + "/" + (var4 & 255)).getBytes();
      var18 = DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -110, 0}, var18), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      this.sendSourceMsg2(" ", 147);
   }

   private static long[] $d2j$hex$0325f41d$decode_J(String src) {
      byte[] d = $d2j$hex$0325f41d$decode_B(src);
      ByteBuffer b = ByteBuffer.wrap(d);
      b.order(ByteOrder.LITTLE_ENDIAN);
      LongBuffer s = b.asLongBuffer();
      long[] data = new long[d.length / 8];
      s.get(data);
      return data;
   }

   private static int[] $d2j$hex$0325f41d$decode_I(String src) {
      byte[] d = $d2j$hex$0325f41d$decode_B(src);
      ByteBuffer b = ByteBuffer.wrap(d);
      b.order(ByteOrder.LITTLE_ENDIAN);
      IntBuffer s = b.asIntBuffer();
      int[] data = new int[d.length / 4];
      s.get(data);
      return data;
   }

   private static short[] $d2j$hex$0325f41d$decode_S(String src) {
      byte[] d = $d2j$hex$0325f41d$decode_B(src);
      ByteBuffer b = ByteBuffer.wrap(d);
      b.order(ByteOrder.LITTLE_ENDIAN);
      ShortBuffer s = b.asShortBuffer();
      short[] data = new short[d.length / 2];
      s.get(data);
      return data;
   }

   private static byte[] $d2j$hex$0325f41d$decode_B(String src) {
      char[] d = src.toCharArray();
      byte[] ret = new byte[src.length() / 2];

      for(int i = 0; i < ret.length; ++i) {
         char h = d[2 * i];
         char l = d[2 * i + 1];
         int hh;
         if (h >= '0' && h <= '9') {
            hh = h - 48;
         } else if (h >= 'a' && h <= 'f') {
            hh = h - 97 + 10;
         } else {
            if (h < 'A' || h > 'F') {
               throw new RuntimeException();
            }

            hh = h - 65 + 10;
         }

         int ll;
         if (l >= '0' && l <= '9') {
            ll = l - 48;
         } else if (l >= 'a' && l <= 'f') {
            ll = l - 97 + 10;
         } else {
            if (l < 'A' || l > 'F') {
               throw new RuntimeException();
            }

            ll = l - 65 + 10;
         }

         ret[i] = (byte)(hh << 4 | ll);
      }

      return ret;
   }
}
