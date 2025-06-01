package com.hzbhd.canbus.car._300;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mDifferent;
   private int mEachId;
   private boolean mFrontStatus;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private int[] mRadarDataNow;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
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

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isWindModeMatch(int var1, int... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var1 == var2[var3]) {
            return true;
         }
      }

      return false;
   }

   private void realKeyClick3_1(Context var1, int var2) {
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick3_1(var1, var2, var3[2], var3[3]);
   }

   private void realKeyClick3_2(Context var1, int var2) {
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick3_2(var1, var2, var3[2], var3[3]);
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private String resolveAirTemp(Context var1, int var2) {
      if (var2 == 0) {
         return "";
      } else if (var2 == 1) {
         return "LO";
      } else {
         return var2 == 63 ? "HI" : (float)var2 / 2.0F + this.getTempUnitC(var1);
      }
   }

   private String resolveOutDoorTemperature(Context var1) {
      return this.mCanBusInfoByte[7] - 40 + this.getTempUnitC(var1);
   }

   private void setAirData0x02(Context var1) {
      String var6;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
         var6 = "open";
      } else {
         var6 = "close";
      }

      ArrayList var7 = new ArrayList();
      boolean var5 = true;
      var7.add(new SettingUpdateEntity(1, 0, var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
      byte[] var8 = this.mCanBusInfoByte;
      var8[6] = (byte)(var8[6] & 1);
      this.updateOutDoorTemp(var1, this.mCanBusInfoByte[7] + this.getTempUnitC(var1));
      var8 = this.mCanBusInfoByte;
      var8[7] = 0;
      if (!this.isAirMsgRepeat(var8)) {
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
         boolean var4;
         if (var2 == 8) {
            var4 = true;
         } else {
            var4 = false;
         }

         GeneralAirData.front_auto_wind_speed = var4;
         GeneralAirData.front_wind_level = var2;
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
         GeneralAirData.front_left_blow_window = this.isWindModeMatch(var3, 4, 5);
         GeneralAirData.front_left_blow_head = this.isWindModeMatch(var3, 1, 2);
         GeneralAirData.front_left_blow_foot = this.isWindModeMatch(var3, 2, 3, 4);
         GeneralAirData.front_left_auto_wind = this.isWindModeMatch(var3, 6);
         GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
         GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
         GeneralAirData.front_left_temperature = this.resolveAirTemp(var1, this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_temperature = this.resolveAirTemp(var1, this.mCanBusInfoInt[4]);
         var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
         if (var3 == 2) {
            var4 = true;
         } else {
            var4 = false;
         }

         GeneralAirData.ac = var4;
         if (var3 == 3) {
            var4 = true;
         } else {
            var4 = false;
         }

         GeneralAirData.ac_auto = var4;
         var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
         GeneralAirData.in_out_auto_cycle = var3;
         if (var3 == 2) {
            GeneralAirData.in_out_auto_cycle = 0;
         }

         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
         GeneralAirData.max_cool = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         if (var2 != 0) {
            var4 = var5;
         } else {
            var4 = false;
         }

         GeneralAirData.power = var4;
         this.updateAirActivity(var1, 1001);
      }
   }

   private void setEnginSpeedData0x65() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var3.append(var2[3] + var2[2] * 256).append(" rpm").toString()));
      StringBuilder var5 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var5.append(var6[4] * 256 + var6[5]).append(" km/h").toString()));
      var2 = this.mCanBusInfoInt;
      this.updateSpeedInfo(var2[4] * 256 + var2[5]);
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[4], var4[5]));
   }

   private void setMultimediaKeyData0x06(Context var1) {
      switch (this.mCanBusInfoInt[2]) {
         case 1:
            this.realKeyClick3_1(var1, 7);
            break;
         case 2:
            this.realKeyClick3_1(var1, 8);
            break;
         case 3:
            this.realKeyClick3_2(var1, 48);
            break;
         case 4:
            this.realKeyClick3_2(var1, 47);
            break;
         case 5:
            this.realKeyLongClick1(var1, 134);
            break;
         case 6:
            this.realKeyLongClick1(var1, 49);
            break;
         case 7:
            this.realKeyLongClick1(var1, 3);
            break;
         case 8:
            this.realKeyLongClick1(var1, 128);
            break;
         case 9:
            this.realKeyLongClick1(var1, 129);
      }

   }

   private void setRadarData0x03(Context var1) {
      if (this.isRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(3, var2[2], var2[3], var2[4], var2[5]);
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(3, var2[6], var2[7], var2[8], var2[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void setVehicleData0x04(Context var1) {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[4]));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVersionInfo0x30(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 2) {
         if (var3 != 3) {
            if (var3 != 4) {
               if (var3 != 6) {
                  if (var3 != 48) {
                     if (var3 == 101) {
                        this.setEnginSpeedData0x65();
                     }
                  } else {
                     this.setVersionInfo0x30(var1);
                  }
               } else {
                  this.setMultimediaKeyData0x06(var1);
               }
            } else {
               this.setVehicleData0x04(var1);
            }
         } else {
            this.setRadarData0x03(var1);
         }
      } else {
         this.setAirData0x02(var1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var8 = var13 - 1;
      var1 = var8;
      if (var8 == 0) {
         var1 = 7;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var1});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }
}
