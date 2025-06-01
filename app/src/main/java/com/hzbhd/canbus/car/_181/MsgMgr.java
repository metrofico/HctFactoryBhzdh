package com.hzbhd.canbus.car._181;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   String ResultTemp;
   int differentId;
   private int eachId;
   int i;
   private boolean mBackStatus;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private DecimalFormat mDecimalFormat0p0;
   int[] mFrontRadarData;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   int[] mRearRadarData;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private UiMgr uiMgr;

   private void VersionInfo0x7F() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private String getAmplifierTemperature() {
      return this.mCanBusInfoInt[11] == 255 ? this.mContext.getString(2131763828) : this.mCanBusInfoInt[11] - 40 + this.getTempUnitC(this.mContext);
   }

   private Object getAmplifierVoltage() {
      return this.mCanBusInfoInt[10] == 255 ? this.mContext.getString(2131763828) : (double)this.mCanBusInfoInt[10] * 0.25 + "V";
   }

   private boolean getBose(int var1) {
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   private byte getFreqByteHi(String var1, String var2) {
      byte var3;
      int var4;
      if (this.isBandAm(var1)) {
         var4 = Integer.parseInt(var2);
      } else {
         if (!this.isBandFm(var1)) {
            var3 = 0;
            return var3;
         }

         var4 = (int)(Double.parseDouble(var2) * 100.0);
      }

      var3 = (byte)(var4 >> 8);
      return var3;
   }

   private byte getFreqByteHi2(String var1, String var2) {
      byte var3;
      int var4;
      if (this.isBandAm(var1)) {
         var4 = Integer.parseInt(var2);
      } else {
         if (!this.isBandFm(var1)) {
            var3 = 0;
            return var3;
         }

         var4 = (int)Double.parseDouble(var2);
      }

      var3 = (byte)(var4 >> 8);
      return var3;
   }

   private byte getFreqByteLo(String var1, String var2) {
      byte var3;
      int var4;
      if (this.isBandAm(var1)) {
         var4 = Integer.parseInt(var2);
      } else {
         if (!this.isBandFm(var1)) {
            var3 = 0;
            return var3;
         }

         var4 = (int)(Double.parseDouble(var2) * 100.0);
      }

      var3 = (byte)(var4 & 255);
      return var3;
   }

   private int getLeftRight(int var1) {
      int var3 = 0;
      int var2;
      if (var1 >= 0 && var1 <= 10) {
         var2 = var1;
      } else {
         var2 = 0;
      }

      if (var1 == 10) {
         var2 = var3;
      }

      var3 = var2;
      if (var1 >= 10) {
         var3 = var2;
         if (var1 <= 20) {
            var3 = var1 - 10;
         }
      }

      return var3;
   }

   private int getSurroundVolFRprogress(int var1) {
      int var2 = var1;
      switch (var1) {
         default:
            switch (var1) {
               case 247:
                  var2 = -9;
                  break;
               case 248:
                  return -8;
               case 249:
                  return -7;
               case 250:
                  return -6;
               case 251:
                  return -5;
               case 252:
                  return -4;
               case 253:
                  return -3;
               case 254:
                  return -2;
               case 255:
                  return -1;
               default:
                  return 0;
            }
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
            return var2;
      }
   }

   private int getTenData(int var1) {
      if (var1 == 0) {
         return -10;
      } else if (var1 == 1) {
         return -9;
      } else if (var1 == 2) {
         return -8;
      } else if (var1 == 3) {
         return -7;
      } else if (var1 == 4) {
         return -6;
      } else if (var1 == 5) {
         return -5;
      } else if (var1 == 6) {
         return -4;
      } else if (var1 == 7) {
         return -3;
      } else if (var1 == 8) {
         return -2;
      } else if (var1 == 9) {
         return -1;
      } else {
         return var1 == 10 ? 0 : var1 - 10;
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private boolean isDoorChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mHandBrake = GeneralDoorData.isHandBrakeUp;
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

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   // $FF: synthetic method
   static void lambda$musicInfoChange$0(byte[] var0, byte[] var1) {
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64}, var0));

      try {
         Thread.sleep(500L);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64}, var1));
   }

   private String resolveAirPressure(int var1) {
      DecimalFormat var2 = new DecimalFormat("0.0");
      this.mDecimalFormat0p0 = var2;
      return var2.format((double)var1 * 0.59 + "Kpa");
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         this.ResultTemp = "LOW";
      } else if (var1 == 255) {
         this.ResultTemp = "HIGH";
      } else {
         this.ResultTemp = (float)(var1 - 116) / 2.0F + 16.0F + this.getTempUnitC(this.mContext);
      }

      return this.ResultTemp;
   }

   private String resolveAltitude(boolean var1, int var2, int var3) {
      String var4;
      if (!var1) {
         var4 = this.mContext.getResources().getString(2131758564);
      } else {
         var4 = this.mContext.getResources().getString(2131758565);
      }

      this.i = (var2 & 255) << 8 | var3 & 255;
      return var4 + this.i;
   }

   private String resolveCoolantTemperature(int var1) {
      DecimalFormat var2 = new DecimalFormat("0.0");
      this.mDecimalFormat0p0 = var2;
      return var2.format((double)var1 * 0.75 - 48.0 + this.getTempUnitC(this.mContext));
   }

   private String resolveDipAngle(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getResources().getString(2131763829);
      } else {
         var3 = this.mContext.getResources().getString(2131763830);
      }

      return var3 + var2 + this.mContext.getResources().getString(2131770206);
   }

   private String resolveOutDoorTem(int var1) {
      return (float)var1 / 2.0F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private String resolvePanoramic(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131758571);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131758572);
      } else if (var1 == 3) {
         var2 = this.mContext.getResources().getString(2131758573);
      } else if (var1 == 4) {
         var2 = this.mContext.getResources().getString(2131758574);
      } else if (var1 == 5) {
         var2 = this.mContext.getResources().getString(2131758575);
      } else {
         var2 = null;
      }

      return var2;
   }

   private String resolveSlope(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getResources().getString(2131763834);
      } else {
         var3 = this.mContext.getResources().getString(2131763833);
      }

      return var3 + var2 + this.mContext.getResources().getString(2131770206);
   }

   private String resolveTrailer(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131763831);
      } else {
         var2 = this.mContext.getResources().getString(2131763832);
      }

      return var2;
   }

   private String resolveVoltage(int var1) {
      DecimalFormat var2 = new DecimalFormat("0.0");
      this.mDecimalFormat0p0 = var2;
      return var2.format((double)var1 * 0.25 + "V");
   }

   private void setAmplifierInfo0x37() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[3];
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[4];
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
      GeneralAmplifierData.leftRight = this.getTenData(this.mCanBusInfoInt[6]);
      GeneralAmplifierData.frontRear = this.getTenData(this.mCanBusInfoInt[7]);
      GeneralAmplifierData.bose_center_b = this.getBose(this.mCanBusInfoInt[8]);
      this.updateAmplifierActivity(new Bundle());
   }

   private void setBaseInfo0x28() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontAirInfo0x23() {
      int[] var11 = this.mCanBusInfoInt;
      int var5 = var11[2];
      int var10 = var11[3];
      int var9 = var11[4];
      int var4 = var11[5];
      int var6 = var11[6];
      int var1 = var11[7];
      int var2 = var11[8];
      int var7 = var11[9];
      int var3 = var11[10];
      int var8 = var11[11];
      GeneralAirData.power = DataHandleUtils.getBoolBit7(var5);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(var5);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var5) ^ true;
      GeneralAirData.threeZone = DataHandleUtils.getBoolBit4(var5);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(var5);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(var5);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(var5);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(var5);
      switch (var10) {
         case 1:
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_defog = false;
            break;
         case 2:
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_defog = false;
            break;
         case 3:
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_defog = false;
            break;
         case 4:
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_defog = true;
            break;
         case 5:
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_defog = true;
            break;
         case 6:
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_defog = true;
            break;
         case 7:
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_defog = true;
      }

      GeneralAirData.front_wind_level = var9;
      GeneralAirData.front_left_temperature = this.resolveAirTemperature(var4);
      GeneralAirData.front_right_temperature = this.resolveAirTemperature(var6);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var2, 4, 4);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var2, 0, 4);
      GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var7, 4, 4);
      GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var7, 0, 4);
      ArrayList var12 = new ArrayList();
      var12.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_1", "_181_set_1_0"), DataHandleUtils.getIntFromByteWithBit(var3, 4, 4)));
      var12.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_1", "_181_set_1_1"), DataHandleUtils.getIntFromByteWithBit(var3, 0, 4)));
      var12.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_1", "_181_set_1_2"), DataHandleUtils.getIntFromByteWithBit(var8, 4, 4)));
      var12.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_1", "_181_set_1_3"), DataHandleUtils.getIntFromByteWithBit(var8, 0, 4)));
      this.resolveOutDoorTem(var1);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setFrontRadarInfo0x27() {
      if (!this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setOriginalVehicleInfo0x29() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_driver_2"), this.resolveCoolantTemperature(this.mCanBusInfoInt[2])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_driver_3"), this.mCanBusInfoInt[3] - 40 + this.getTempUnitC(this.mContext)));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_driver_1"), this.resolveVoltage(this.mCanBusInfoInt[4])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_driver_4"), this.resolveAirPressure(this.mCanBusInfoInt[5])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setPanelKeyInfo0x22() {
      switch (this.mCanBusInfoInt[2]) {
         case 16:
            this.buttonKey(62);
            break;
         case 17:
            this.buttonKey(1);
            break;
         case 18:
            this.buttonKey(7);
            break;
         case 19:
            this.buttonKey(8);
            break;
         case 20:
            this.buttonKey(188);
            break;
         case 21:
            this.buttonKey(128);
            break;
         case 22:
            this.buttonKey(129);
            break;
         case 23:
            this.buttonKey(50);
            break;
         case 24:
            this.buttonKey(151);
            break;
         case 25:
            this.buttonKey(31);
            break;
         case 26:
            this.buttonKey(47);
            break;
         case 27:
            this.buttonKey(48);
      }

   }

   private void setPanoramicstate0x41() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_181_panoramic"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_panoramic"), this.resolvePanoramic(this.mCanBusInfoInt[2])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRearAirInfo0x24() {
      int[] var6 = this.mCanBusInfoInt;
      int var3 = var6[2];
      int var1 = var6[3];
      int var4 = var6[4];
      int var5 = var6[5];
      int var2 = var6[6];
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(var3);
      GeneralAirData.rear_auto = DataHandleUtils.getBoolBit3(var3);
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               GeneralAirData.rear_left_blow_head = false;
               GeneralAirData.rear_left_blow_foot = true;
               GeneralAirData.rear_left_blow_window = false;
               GeneralAirData.rear_right_blow_head = false;
               GeneralAirData.rear_right_blow_foot = true;
               GeneralAirData.rear_right_blow_window = false;
               GeneralAirData.rear_defog = false;
            }
         } else {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_defog = false;
         }
      } else {
         GeneralAirData.rear_left_blow_head = true;
         GeneralAirData.rear_left_blow_foot = false;
         GeneralAirData.rear_left_blow_window = false;
         GeneralAirData.rear_right_blow_head = true;
         GeneralAirData.rear_right_blow_foot = false;
         GeneralAirData.rear_right_blow_window = false;
         GeneralAirData.rear_defog = false;
      }

      GeneralAirData.rear_wind_level = var4;
      GeneralAirData.rear_temperature = this.resolveAirTemperature(var5);
      GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var2, 0, 4);
      GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var2, 0, 4);
      this.updateAirActivity(this.mContext, 1002);
   }

   private void setRearRadarInfo0x26() {
      if (!this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setScreenBrightness0x32() {
      ArrayList var3 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 26) {
         var2 = "最暗";
      } else if (var1 == 255) {
         var2 = "最亮";
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_setting_16"), var2));
      this.setBacklightLevel(this.mCanBusInfoInt[2]);
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSettingInfo0x31() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_4"), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_6"), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_7"), DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_8"), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_9"), DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_10"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_11"), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_12"), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_13"), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_14"), DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_19"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_15"), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_16"), DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingInfo0x36() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info1"), this.resolveSlope(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info2"), this.mCanBusInfoInt[3] + "%"));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info3"), this.resolveDipAngle(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info4"), this.resolveTrailer(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info5"), this.resolveAltitude(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), this.mCanBusInfoInt[7])));
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_drive_car_info5"), this.resolveAltitude(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), this.mCanBusInfoInt[7]))).setProgress(this.i));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTrackData0x30() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 10000, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setWheelKeyInfo0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 7) {
                  switch (var1) {
                     case 9:
                        this.buttonKey(467);
                        break;
                     case 10:
                        this.buttonKey(468);
                        break;
                     case 11:
                        this.buttonKey(45);
                        break;
                     case 12:
                        this.buttonKey(46);
                        break;
                     case 13:
                        this.buttonKey(187);
                        break;
                     case 14:
                        this.buttonKey(3);
                        break;
                     default:
                        switch (var1) {
                           case 16:
                              this.buttonKey(62);
                              break;
                           case 17:
                              this.buttonKey(1);
                              break;
                           case 18:
                              this.buttonKey(7);
                              break;
                           case 19:
                              this.buttonKey(8);
                              break;
                           case 20:
                              this.buttonKey(188);
                              break;
                           case 21:
                              this.buttonKey(128);
                              break;
                           case 22:
                              this.buttonKey(129);
                              break;
                           case 23:
                              this.buttonKey(50);
                              break;
                           case 24:
                              this.buttonKey(151);
                              break;
                           case 25:
                              this.buttonKey(31);
                              break;
                           case 26:
                              this.buttonKey(47);
                              break;
                           case 27:
                              this.buttonKey(48);
                        }
                  }
               } else {
                  this.buttonKey(2);
               }
            } else {
               this.buttonKey(8);
            }
         } else {
            this.buttonKey(7);
         }
      } else {
         this.buttonKey(0);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
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
      if (var3 != 54) {
         if (var3 != 55) {
            if (var3 != 65) {
               if (var3 != 127) {
                  switch (var3) {
                     case 33:
                        this.setWheelKeyInfo0x21();
                        break;
                     case 34:
                        this.setPanelKeyInfo0x22();
                        break;
                     case 35:
                        this.setFrontAirInfo0x23();
                        break;
                     case 36:
                        this.setRearAirInfo0x24();
                        break;
                     default:
                        switch (var3) {
                           case 38:
                              this.setRearRadarInfo0x26();
                              break;
                           case 39:
                              this.setFrontRadarInfo0x27();
                              break;
                           case 40:
                              this.setBaseInfo0x28();
                              break;
                           case 41:
                              this.setOriginalVehicleInfo0x29();
                              break;
                           default:
                              switch (var3) {
                                 case 48:
                                    this.setTrackData0x30();
                                    break;
                                 case 49:
                                    this.setSettingInfo0x31();
                                    break;
                                 case 50:
                                    this.setScreenBrightness0x32();
                              }
                        }
                  }
               } else {
                  this.VersionInfo0x7F();
               }
            } else {
               this.setPanoramicstate0x41();
            }
         } else {
            this.setAmplifierInfo0x37();
         }
      } else {
         this.setSettingInfo0x36();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (this.eachId == 7) {
         (new Thread(new MsgMgr$$ExternalSyntheticLambda0(var21.getBytes(StandardCharsets.UTF_8), var23.getBytes(StandardCharsets.UTF_8)))).start();
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var8;
      if (!this.isBandFm(var2) && this.isBandAm(var2)) {
         var8 = 1;
      } else {
         var8 = 0;
      }

      var2.hashCode();
      switch (var2) {
         case "AM1":
            var6 = 3;
            break;
         case "AM2":
            var6 = 4;
            break;
         case "FM1":
         default:
            var6 = 0;
            break;
         case "FM2":
            var6 = 1;
            break;
         case "FM3":
            var6 = 2;
      }

      String[] var7 = FutureUtil.instance.getValidFreqList(var6);
      if (FutureUtil.instance.getAutoSearchStatus()) {
         var6 = 4;
      } else {
         var6 = 0;
      }

      if (FutureUtil.instance.getPSSwitchStatus()) {
         var6 = 3;
      }

      if (FutureUtil.instance.getSeekDownStatus()) {
         var6 = 2;
      }

      if (FutureUtil.instance.getSeekUpStatus()) {
         var6 = 1;
      }

      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -58, (byte)var8, this.getFreqByteHi2(var2, var7[0]), this.getFreqByteLo(var2, var7[0]), this.getFreqByteHi2(var2, var7[1]), this.getFreqByteLo(var2, var7[1]), this.getFreqByteHi2(var2, var7[2]), this.getFreqByteLo(var2, var7[2]), this.getFreqByteHi2(var2, var7[3]), this.getFreqByteLo(var2, var7[3]), 0, 0, this.getFreqByteHi(var2, var3), this.getFreqByteLo(var2, var3), (byte)var6});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 0, 0, 0, 0, 0, 0, 0});
      }
   }
}
