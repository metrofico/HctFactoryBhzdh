package com.hzbhd.canbus.car._346;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_KEY_253_FRONT_RADAR_ENABLE = "share_key_253_front_radar_enable";
   static final String SHARE_KEY_253_REAR_RADAR_ENABLE = "share_key_253_rear_radar_enable";
   int differentId;
   private int eachId;
   int[] m0x60Data;
   int[] mAirData;
   private boolean mBackStatus;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   MsgMgr msgMgr;
   int nowData4 = 0;
   private UiMgr uiMgr;

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
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

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 31) {
         var2 = "HI";
      } else if (var1 >= 1 && var1 <= 29) {
         var2 = (float)(var1 + 35) / 2.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "---";
      }

      return var2;
   }

   private void setAirData0x26() {
      int var2 = this.nowData4;
      int var1 = this.mCanBusInfoInt[6];
      if (var2 != var1) {
         this.nowData4 = var1;
         GeneralAirData.rear_ac = DataHandleUtils.getBoolBit7(var1);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 3);
         this.updateAirActivity(this.mContext, 1003);
      } else {
         this.nowData4 = 0;
         if (!this.isAirDataChange()) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
            this.updateAirActivity(this.mContext, 1004);
         }
      }
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowHandBrake = true;
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontRadarInfo0x23() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_front_radar_enable", true)) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      } else {
         RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
      }

      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setPanelKeyInfo0x21() {
      switch (this.mCanBusInfoInt[2]) {
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
            this.buttonKey(3);
            break;
         case 6:
            this.buttonKey(17);
            break;
         case 7:
            this.buttonKey(151);
            break;
         case 8:
            this.buttonKey(2);
            break;
         case 9:
            this.buttonKey(77);
            break;
         case 10:
            this.buttonKey(140);
            break;
         case 11:
            this.buttonKey(467);
            break;
         case 12:
            this.buttonKey(468);
            break;
         case 13:
            this.buttonKey(50);
            break;
         case 14:
            this.buttonKey(1);
            break;
         case 15:
            this.buttonKey(4);
            break;
         case 16:
            this.buttonKey(220);
            break;
         case 17:
            this.buttonKey(128);
      }

   }

   private void setRadarWarningInfo0x25() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRearRadarInfo0x22() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_rear_radar_enable", true)) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      } else {
         RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
      }

      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTrackData0x29() {
      MyLog.temporaryTracking("高位：" + this.getMsb(59698) + "低位：" + this.getLsb(59698));
      StringBuilder var2 = (new StringBuilder()).append("数据结果：");
      byte[] var1 = this.mCanBusInfoByte;
      MyLog.temporaryTracking(var2.append(this.getMsbLsbResult(var1[2], var1[3])).toString());
      var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 0, 5969, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0x7F() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKeyInfo0x20() {
      switch (this.mCanBusInfoInt[2]) {
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
            this.buttonKey(20);
            break;
         case 4:
            this.buttonKey(21);
            break;
         case 5:
            this.buttonKey(3);
            break;
         case 6:
            this.buttonKey(2);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
      this.getUiMgr(var1).selectCarModel();
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
      if (var3 != 41) {
         if (var3 != 127) {
            switch (var3) {
               case 32:
                  this.setWheelKeyInfo0x20();
                  break;
               case 33:
                  this.setPanelKeyInfo0x21();
                  break;
               case 34:
                  this.setRearRadarInfo0x22();
                  break;
               case 35:
                  this.setFrontRadarInfo0x23();
                  break;
               case 36:
                  this.setDoorData0x24();
                  break;
               case 37:
                  this.setRadarWarningInfo0x25();
                  break;
               case 38:
                  this.setAirData0x26();
            }
         } else {
            this.setVersionInfo0x7F();
         }
      } else {
         this.setTrackData0x29();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(this.mContext).selectCarModel();
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }
}
