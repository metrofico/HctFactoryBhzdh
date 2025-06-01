package com.hzbhd.canbus.car._202;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int doorData = 0;
   int eachId;
   int[] lastAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private UiMgr mUiMgr;
   int[] thisAirData;

   private int assignRadarProgress(int var1) {
      return var1 == 255 ? 0 : var1 / 25 + 1;
   }

   private String get0x73Data0Satate(boolean var1) {
      return !var1 ? this.mContext.getString(2131759095) : this.mContext.getString(2131759096);
   }

   private boolean getInOutCycleState(int var1) {
      return var1 != 0;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void set0x72VehicleStatus() {
      this.setData0_Data1();
      this.setData2WheelKeyInfo();
      this.setData4_Data5_SWA();
      this.setData6_Data13RadarInfo();
   }

   private void set0x73HVACStatus() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
         MyLog.temporaryTracking("更新环境温度");
         this.updateOutDoorTemp(this.mContext, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7) + this.getTempUnitC(this.mContext));
      }

      this.mCanBusInfoInt[8] = 0;
      MyLog.temporaryTracking("0x73进来");
      if (this.thisAirData == null) {
         this.thisAirData = new int[6];
      }

      int[] var3 = this.thisAirData;
      int[] var4 = this.mCanBusInfoInt;
      var3[0] = var4[0];
      var3[1] = var4[1];
      var3[2] = var4[2];
      var3[3] = var4[3];
      var3[4] = var4[4];
      var3[5] = var4[5];
      if (!Arrays.equals(this.lastAirData, var3)) {
         MyLog.temporaryTracking("空调变化");
         if (this.lastAirData == null) {
            this.lastAirData = new int[6];
         }

         var3 = this.lastAirData;
         var4 = this.mCanBusInfoInt;
         var3[0] = var4[0];
         var3[1] = var4[1];
         var3[2] = var4[2];
         var3[3] = var4[3];
         var3[4] = var4[4];
         var3[5] = var4[5];
         this.setAirInfo();
      }

      int var2 = this.doorData;
      int var1 = this.mCanBusInfoInt[9];
      if (var2 != var1) {
         this.doorData = var1;
         this.setDoorInfo();
      }

   }

   private void setAirInfo() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = this.getInOutCycleState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      int var1 = this.mCanBusInfoInt[4];
      if (var1 == 0) {
         GeneralAirData.front_left_temperature = "LO";
      } else if (var1 == 255) {
         GeneralAirData.front_left_temperature = "HI";
      } else {
         GeneralAirData.front_left_temperature = this.mCanBusInfoInt[4] + this.getTempUnitC(this.mContext);
      }

      var1 = this.mCanBusInfoInt[5];
      if (var1 == 0) {
         GeneralAirData.front_right_temperature = "LO";
      } else if (var1 == 255) {
         GeneralAirData.front_right_temperature = "HI";
      } else {
         GeneralAirData.front_right_temperature = this.mCanBusInfoInt[5] + this.getTempUnitC(this.mContext);
      }

      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
      this.updateAirActivity(this.mContext, 1001);
      MyLog.temporaryTracking("更新空调");
   }

   private void setData0_Data1() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit5"), this.get0x73Data0Satate(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit4"), this.get0x73Data0Satate(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit3"), this.get0x73Data0Satate(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit2"), this.get0x73Data0Satate(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit1"), this.get0x73Data0Satate(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit0"), this.get0x73Data0Satate(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_D1"), this.mCanBusInfoInt[3] + "km/h"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(this.mCanBusInfoInt[3]);
   }

   private void setData2WheelKeyInfo() {
      switch (this.mCanBusInfoInt[4]) {
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
            this.buttonKey(3);
         case 4:
         case 7:
         default:
            break;
         case 5:
            this.buttonKey(14);
            break;
         case 6:
            this.buttonKey(15);
            break;
         case 8:
            this.buttonKey(45);
            break;
         case 9:
            this.buttonKey(46);
            break;
         case 10:
            this.buttonKey(2);
      }

   }

   private void setData4_Data5_SWA() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[6];
      if (var1 != 0) {
         GeneralParkData.trackAngle = -(var1 / 10);
         this.updateParkUi((Bundle)null, this.mContext);
         MyLog.temporaryTracking("ID202:左转——转角数据:" + GeneralParkData.trackAngle);
      } else {
         GeneralParkData.trackAngle = var2[7] / 10;
         this.updateParkUi((Bundle)null, this.mContext);
         MyLog.temporaryTracking("ID202:右转——转角数据:" + GeneralParkData.trackAngle);
      }

   }

   private void setData6_Data13RadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(10, this.assignRadarProgress(this.mCanBusInfoInt[8]), this.assignRadarProgress(this.mCanBusInfoInt[9]), this.assignRadarProgress(this.mCanBusInfoInt[10]), this.assignRadarProgress(this.mCanBusInfoInt[11]));
      RadarInfoUtil.setFrontRadarLocationData(10, this.assignRadarProgress(this.mCanBusInfoInt[12]), this.assignRadarProgress(this.mCanBusInfoInt[13]), this.assignRadarProgress(this.mCanBusInfoInt[14]), this.assignRadarProgress(this.mCanBusInfoInt[15]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setDoorInfo() {
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9])) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
         this.updateDoorView(this.mContext);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = "ATV PLAYING".getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, 8}, var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = "AUX PLAYING".getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, 12}, var1);
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      var1 = "Talking".getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, 10}, var1);
   }

   public void buttonKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 114) {
         if (var3 == 115) {
            this.set0x73HVACStatus();
         }
      } else {
         this.set0x72VehicleStatus();
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = "DTV PLAYING".getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, 8}, var1);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 6;
      } else {
         var25 = 13;
      }

      var1 = (byte)var25;
      byte[] var26 = var21.getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, var1}, var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      MyLog.temporaryTracking("RADIO INFO CHANGE");
      if (!var2.equals("FM1") && !var2.equals("FM2") && !var2.equals("FM3") && !var2.equals("AM1")) {
         var2.equals("AM2");
      }

      byte var6 = (byte)0;
      byte[] var7 = var3.getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, var6}, var7);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      byte[] var2 = "MEDIA OFF".getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, 0}, var2);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 6;
      } else {
         var18 = 13;
      }

      var1 = (byte)var18;
      byte[] var19 = var8.getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, var1}, var19);
   }
}
