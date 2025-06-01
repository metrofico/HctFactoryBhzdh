package com.hzbhd.canbus.car._470;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Unit;

public class MsgMgr extends AbstractMsgMgr {
   private String airJsonStr;
   DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
   DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   int[] mAirData;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   int[] mTrackData;
   private UiMgr mUiMgr;

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String intArrayToJsonStr(int[] var1) {
      this.airJsonStr = "{";

      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var2 == var1.length - 1) {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + "}";
         } else {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + ",";
         }
      }

      return this.airJsonStr;
   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarData, var1)) {
         return false;
      } else {
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicInfoChange(int[] var1) {
      if (Arrays.equals(this.mPanoramicInfo, var1)) {
         return false;
      } else {
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRearRadarData, var1)) {
         return false;
      } else {
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange(int[] var1) {
      if (Arrays.equals(this.mTireInfo, var1)) {
         return false;
      } else {
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, var1)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   public void PDCDistanceRl(int var1) {
      switch (var1) {
         case 0:
            MdRadarData.distanceRl = 0;
            break;
         case 1:
            MdRadarData.distanceRl = 1;
            break;
         case 2:
            MdRadarData.distanceRl = 2;
            break;
         case 3:
            MdRadarData.distanceRl = 3;
            break;
         case 4:
            MdRadarData.distanceRl = 4;
            break;
         case 5:
            MdRadarData.distanceRl = 5;
            break;
         case 6:
            MdRadarData.distanceRl = 6;
            break;
         case 7:
            MdRadarData.distanceRl = 7;
      }

   }

   public void PDCDistanceRml(int var1) {
      switch (var1) {
         case 0:
            MdRadarData.distanceRml = 0;
            break;
         case 1:
            MdRadarData.distanceRml = 1;
            break;
         case 2:
            MdRadarData.distanceRml = 2;
            break;
         case 3:
            MdRadarData.distanceRml = 3;
            break;
         case 4:
            MdRadarData.distanceRml = 4;
            break;
         case 5:
            MdRadarData.distanceRml = 5;
            break;
         case 6:
            MdRadarData.distanceRml = 6;
            break;
         case 7:
            MdRadarData.distanceRml = 7;
      }

   }

   public void PDCDistanceRr(int var1) {
      switch (var1) {
         case 0:
            MdRadarData.distanceRr = 0;
            break;
         case 1:
            MdRadarData.distanceRr = 1;
            break;
         case 2:
            MdRadarData.distanceRr = 2;
            break;
         case 3:
            MdRadarData.distanceRr = 3;
            break;
         case 4:
            MdRadarData.distanceRr = 4;
            break;
         case 5:
            MdRadarData.distanceRr = 5;
            break;
         case 6:
            MdRadarData.distanceRr = 6;
            break;
         case 7:
            MdRadarData.distanceRr = 7;
      }

   }

   public void PDCECUFault(int var1) {
      if (var1 == 0) {
         MdRadarData.pdc_ecufault = true;
      } else {
         MdRadarData.pdc_ecufault = false;
      }

   }

   public void PDCLed(int var1) {
      if (var1 == 0) {
         MdRadarData.pdc_led = false;
      } else {
         MdRadarData.pdc_led = true;
      }

   }

   public void PDCModeStatus(int var1) {
      switch (var1) {
         case 0:
            MdRadarData.reverse_radar_working_mode = 0;
            break;
         case 1:
            MdRadarData.reverse_radar_working_mode = 1;
            break;
         case 2:
            MdRadarData.reverse_radar_working_mode = 2;
            break;
         case 3:
            MdRadarData.reverse_radar_working_mode = 3;
            break;
         case 4:
            MdRadarData.reverse_radar_working_mode = 4;
            break;
         case 5:
            MdRadarData.reverse_radar_working_mode = 5;
            break;
         case 6:
            MdRadarData.reverse_radar_working_mode = 6;
            break;
         case 7:
            MdRadarData.reverse_radar_working_mode = 7;
      }

   }

   public void SensorFaultRl(int var1) {
      boolean var2;
      if (var1 == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      MdRadarData.sensorFaultRl = var2;
   }

   public void SensorFaultRml(int var1) {
      boolean var2;
      if (var1 == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      MdRadarData.sensorFaultRml = var2;
   }

   public void SensorFaultRr(int var1) {
      boolean var2;
      if (var1 == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      MdRadarData.sensorFaultRr = var2;
   }

   protected void ShareBasicInfo(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportAllCanBusData(this.intArrayToJsonStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      BaseUtil.INSTANCE.runUi(new MsgMgr$$ExternalSyntheticLambda1(this, var2));
   }

   protected int getMsDataType(int[] var1) {
      int var3 = var1[2];
      int var2 = var1[3];
      int var4 = var1[4];
      return var1[5] & 255 | (var3 & 255) << 24 | (var2 & 255) << 16 | (var4 & 255) << 8;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   // $FF: synthetic method
   Unit lambda$canbusInfoChange$0$com_hzbhd_canbus_car__470_MsgMgr(byte[] var1) {
      int[] var3 = this.getByteArrayToIntArray(var1);
      if (LogUtil.log5()) {
         LogUtil.d("canbusInfoChange(): " + this.getMsDataType(var3));
      }

      int var2 = this.getMsDataType(var3);
      if (var2 != 888) {
         if (var2 == 928) {
            this.set0x3A0ParkingSensorInfo(var3);
         }
      } else {
         this.ShareBasicInfo(this.getByteArrayToIntArray(var1));
      }

      return null;
   }

   // $FF: synthetic method
   Unit lambda$set0x3A0ParkingSensorInfo$1$com_hzbhd_canbus_car__470_MsgMgr(int[] var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[7], 3, 3);
      if (LogUtil.log5()) {
         LogUtil.d("set0x3A0ParkingSensorInfo(): " + var2);
      }

      this.PDCModeStatus(var2);
      var2 = DataHandleUtils.getIntFromByteWithBit(var1[7], 6, 1);
      if (LogUtil.log5()) {
         LogUtil.d("set0x3A0ParkingSensorInfo(): " + var2);
      }

      this.PDCLed(var2);
      var2 = DataHandleUtils.getIntFromByteWithBit(var1[13], 0, 1);
      if (LogUtil.log5()) {
         LogUtil.d("set0x3A0ParkingSensorInfo(): " + var2);
      }

      this.PDCECUFault(var2);
      this.PDCDistanceRl(DataHandleUtils.getIntFromByteWithBit(var1[10], 0, 3));
      this.PDCDistanceRml(DataHandleUtils.getIntFromByteWithBit(var1[10], 5, 3));
      this.PDCDistanceRr(DataHandleUtils.getIntFromByteWithBit(var1[11], 5, 3));
      this.SensorFaultRl(DataHandleUtils.getIntFromByteWithBit(var1[12], 4, 1));
      this.SensorFaultRml(DataHandleUtils.getIntFromByteWithBit(var1[12], 5, 1));
      this.SensorFaultRr(DataHandleUtils.getIntFromByteWithBit(var1[12], 7, 1));
      this.getUiMgr(this.mContext).refreshRadar();
      return null;
   }

   public void onAccOff() {
      super.onAccOff();
   }

   public void onAccOn() {
      super.onAccOn();
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
   }

   public void set0x3A0ParkingSensorInfo(int[] var1) {
      BaseUtil.INSTANCE.runUi(new MsgMgr$$ExternalSyntheticLambda0(this, var1));
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }
}
