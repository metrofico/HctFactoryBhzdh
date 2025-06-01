package com.hzbhd.canbus.car._277;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.car_cus._277.DiagnosisEntity;
import com.hzbhd.canbus.car_cus._277.DialogUtil;
import com.hzbhd.canbus.car_cus._277._277_GeneralSettingData;
import com.hzbhd.canbus.car_cus._277.activity.VehicleDiagnosisActivity;
import com.hzbhd.canbus.car_cus._277.activity.VehicleMonitorActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private final int VEHICLE_MODE_ELECT_DRIV = 2;
   private final int VEHICLE_MODE_PARK_CHARGE = 1;
   private final int VEHICLE_MODE_REGE_BREAK = 3;
   private final int VEHICLE_MODE_SLOPE_MODE = 4;
   private final int VEHICLE_MODE_STOP = 0;
   private byte[] m0x33DataNow;
   private byte[] m0x36DataNow;
   private byte[] m0x38DataNow;
   private byte[] m0x39DataNow;
   private byte[] m0x76DataNow;
   private byte[] m0x7ADataNow;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private List mDiagnosisList;
   private boolean mIsNeedShow;
   private String[] mStrings;
   private String[] mStringsNow;
   private int mVehicleMode = 0;

   private SettingUpdateEntity batteryVoltageSupporter(int var1, int var2, int var3) {
      int var4 = var1;
      if (var1 < 1) {
         var4 = 8;
      }

      return new SettingUpdateEntity(1, var4 - 1 + 7, (float)(var2 * 256 + var3) / 100.0F);
   }

   private void checkFaultLevel(int var1, int var2) {
      if (var2 == 1) {
         String[] var3 = this.mStrings;
         if (var3[var1] == null) {
            this.mIsNeedShow = true;
         }

         var3[var1] = CommUtil.getStrByResId(this.mContext, "geely_e6_fualt_" + var1);
      } else {
         this.mStrings[var1] = null;
      }

   }

   private Bundle getBundle() {
      Bundle var1 = new Bundle();
      var1.putString("head", Integer.toHexString(this.mCanBusInfoInt[1]));
      return var1;
   }

   private String getDowntimeStatus(boolean var1) {
      return var1 ? "downtime" : "invalid";
   }

   private String getFaultLevelString(int var1, int var2, int var3) {
      var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[var2], var3, 2);
      this.checkFaultLevel(var1, var2);
      return "geely_e6_item_" + var2;
   }

   private String getFaultSign(int var1) {
      return var1 == 0 ? "geely_e6_item_0" : "_268_diagnosis_page6_item11_" + var1;
   }

   private String getStatusString(int var1, int var2) {
      StringBuilder var3 = (new StringBuilder()).append("geely_e6_item_");
      byte var4;
      if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[var1], var2)) {
         var4 = 4;
      } else {
         var4 = 0;
      }

      return var3.append(var4).toString();
   }

   private boolean is0x33DataChange() {
      if (Arrays.equals(this.m0x33DataNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x33DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x36DataChange() {
      if (Arrays.equals(this.m0x36DataNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x36DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x38DataChange() {
      if (Arrays.equals(this.m0x38DataNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x38DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x39DataChange() {
      if (Arrays.equals(this.m0x39DataNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x39DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x76DataChange() {
      if (Arrays.equals(this.m0x76DataNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x76DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x7ADataChange() {
      if (Arrays.equals(this.m0x7ADataNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x7ADataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAlertChange() {
      if (Arrays.equals(this.mStringsNow, this.mStrings)) {
         return false;
      } else {
         String[] var1 = this.mStrings;
         this.mStringsNow = (String[])Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void setBatteryVoltage0x39() {
      ArrayList var1 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(this.batteryVoltageSupporter(var2[2], var2[3], var2[4]));
      var2 = this.mCanBusInfoInt;
      var1.add(this.batteryVoltageSupporter(var2[5], var2[6], var2[7]));
      var2 = this.mCanBusInfoInt;
      var1.add(this.batteryVoltageSupporter(var2[8], var2[9], var2[10]));
      var2 = this.mCanBusInfoInt;
      var1.add(this.batteryVoltageSupporter(var2[11], var2[12], var2[13]));
      this.update277GeneralSettingData(var1);
      this.update277VehicleMonitorActivity(this.getBundle());
   }

   private void setGeneratorControllerFaultInformation0x36() {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(1, 8, this.getFaultLevelString(8, 2, 0)));
      this.showDiagnosisWindow();
      var2.add(new SettingUpdateEntity(9, 0, this.getStatusString(2, 7)));
      var2.add(new SettingUpdateEntity(9, 1, this.getStatusString(2, 6)));
      var2.add(new SettingUpdateEntity(9, 2, this.getStatusString(2, 5)));
      var2.add(new SettingUpdateEntity(9, 3, this.getStatusString(2, 4)));
      var2.add(new SettingUpdateEntity(9, 4, this.getStatusString(2, 3)));
      var2.add(new SettingUpdateEntity(9, 5, this.getStatusString(2, 2)));
      var2.add(new SettingUpdateEntity(9, 6, this.getStatusString(3, 7)));
      var2.add(new SettingUpdateEntity(9, 7, this.getStatusString(3, 6)));
      var2.add(new SettingUpdateEntity(9, 8, this.getStatusString(3, 5)));
      var2.add(new SettingUpdateEntity(9, 9, this.getStatusString(3, 4)));
      var2.add(new SettingUpdateEntity(9, 10, this.getStatusString(3, 3)));
      var2.add(new SettingUpdateEntity(9, 11, this.getStatusString(3, 2)));
      var2.add(new SettingUpdateEntity(9, 12, this.getStatusString(3, 1)));
      var2.add(new SettingUpdateEntity(9, 13, this.getStatusString(3, 0)));
      int[] var1 = this.mCanBusInfoInt;
      var2.add(new SettingUpdateEntity(9, 14, (float)(var1[4] * 256 + var1[5]) / 10.0F));
      this.updateGeneralSettingData(var2);
      this.updateVehicleDiagnosisActivity(this.getBundle());
   }

   private void setMotorStatus0x38() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(2, 0, (int)((float)(this.mCanBusInfoInt[6] - 40))));
      var1.add(new SettingUpdateEntity(2, 1, (int)((float)(this.mCanBusInfoInt[7] - 40))));
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new SettingUpdateEntity(2, 2, (float)(var2[8] * 256 + var2[9]) / 10.0F));
      var2 = this.mCanBusInfoInt;
      var1.add(new SettingUpdateEntity(2, 4, (int)((float)(var2[10] * 256 + var2[11]))));
      var2 = this.mCanBusInfoInt;
      var1.add(new SettingUpdateEntity(2, 3, (float)(var2[12] * 256 + var2[13] - 5000) / 10.0F));
      this.update277GeneralSettingData(var1);
      this.update277VehicleMonitorActivity(this.getBundle());
      var1 = new ArrayList();
      var1.clear();
      this.mVehicleMode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 3);
      Log.i("_277", "setMotorStatus0x38: mVehicleMode: " + this.mVehicleMode);
      var1.add(new SettingUpdateEntity(0, 31, this.getStatusString(15, 4)));
      var1.add(new SettingUpdateEntity(0, 38, this.getStatusString(15, 0)));
      var1.add(new SettingUpdateEntity(0, 39, this.getStatusString(15, 1)));
      var1.add(new SettingUpdateEntity(0, 40, this.getStatusString(15, 2)));
      var1.add(new SettingUpdateEntity(0, 41, this.getStatusString(15, 3)));
      var1.add(new SettingUpdateEntity(0, 42, this.getFaultLevelString(9, 15, 5)));
      this.showDiagnosisWindow();
      this.updateGeneralSettingData(var1);
      this.updateVehicleDiagnosisActivity(this.getBundle());
   }

   private void setVehicleDiagnosisInfo0x76() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[2]));
      var1.add(new SettingUpdateEntity(0, 1, this.getFaultLevelString(3, 3, 0)));
      var1.add(new SettingUpdateEntity(0, 2, this.getFaultLevelString(0, 3, 6)));
      var1.add(new SettingUpdateEntity(0, 3, this.getStatusString(6, 4)));
      var1.add(new SettingUpdateEntity(0, 4, this.getFaultLevelString(2, 3, 2)));
      var1.add(new SettingUpdateEntity(0, 5, this.getFaultLevelString(1, 3, 4)));
      var1.add(new SettingUpdateEntity(0, 6, this.getStatusString(6, 5)));
      var1.add(new SettingUpdateEntity(0, 7, this.getStatusString(6, 0)));
      var1.add(new SettingUpdateEntity(0, 8, this.getStatusString(7, 1)));
      var1.add(new SettingUpdateEntity(0, 9, this.getStatusString(6, 6)));
      var1.add(new SettingUpdateEntity(0, 10, this.getStatusString(6, 7)));
      var1.add(new SettingUpdateEntity(0, 11, this.getFaultLevelString(7, 4, 0)));
      var1.add(new SettingUpdateEntity(0, 12, this.getStatusString(7, 7)));
      var1.add(new SettingUpdateEntity(0, 13, this.getFaultLevelString(5, 4, 4)));
      var1.add(new SettingUpdateEntity(0, 14, this.getStatusString(7, 5)));
      var1.add(new SettingUpdateEntity(0, 15, this.getFaultLevelString(4, 4, 6)));
      var1.add(new SettingUpdateEntity(0, 16, this.getStatusString(7, 6)));
      var1.add(new SettingUpdateEntity(0, 17, this.getFaultLevelString(6, 4, 2)));
      var1.add(new SettingUpdateEntity(0, 18, this.getStatusString(7, 4)));
      var1.add(new SettingUpdateEntity(0, 19, this.getStatusString(6, 2)));
      var1.add(new SettingUpdateEntity(0, 20, this.getStatusString(7, 2)));
      var1.add(new SettingUpdateEntity(0, 21, this.getStatusString(6, 3)));
      var1.add(new SettingUpdateEntity(0, 22, this.getStatusString(7, 3)));
      var1.add(new SettingUpdateEntity(0, 23, this.getStatusString(5, 0)));
      var1.add(new SettingUpdateEntity(0, 24, this.getStatusString(5, 1)));
      var1.add(new SettingUpdateEntity(0, 25, this.getStatusString(5, 2)));
      var1.add(new SettingUpdateEntity(0, 26, this.getStatusString(5, 3)));
      var1.add(new SettingUpdateEntity(0, 27, this.getStatusString(5, 4)));
      var1.add(new SettingUpdateEntity(0, 28, this.getStatusString(5, 5)));
      var1.add(new SettingUpdateEntity(0, 29, this.getStatusString(5, 6)));
      var1.add(new SettingUpdateEntity(0, 30, this.getStatusString(5, 7)));
      var1.add(new SettingUpdateEntity(0, 32, this.getStatusString(7, 0)));
      var1.add(new SettingUpdateEntity(0, 43, this.getStatusString(6, 1)));
      var1.add(new SettingUpdateEntity(1, 0, this.getStatusString(8, 0)));
      var1.add(new SettingUpdateEntity(1, 1, this.getStatusString(8, 3)));
      var1.add(new SettingUpdateEntity(1, 2, this.getStatusString(8, 4)));
      var1.add(new SettingUpdateEntity(1, 3, this.getStatusString(10, 0)));
      var1.add(new SettingUpdateEntity(1, 4, this.getStatusString(8, 2)));
      var1.add(new SettingUpdateEntity(1, 5, this.getStatusString(8, 1)));
      var1.add(new SettingUpdateEntity(1, 6, this.getStatusString(8, 7)));
      var1.add(new SettingUpdateEntity(1, 7, this.getStatusString(9, 0)));
      var1.add(new SettingUpdateEntity(1, 8, this.getStatusString(9, 1)));
      var1.add(new SettingUpdateEntity(1, 9, this.getStatusString(9, 3)));
      var1.add(new SettingUpdateEntity(1, 10, this.getStatusString(9, 4)));
      var1.add(new SettingUpdateEntity(1, 11, this.getStatusString(8, 5)));
      var1.add(new SettingUpdateEntity(1, 12, this.getStatusString(8, 6)));
      var1.add(new SettingUpdateEntity(1, 13, this.getStatusString(9, 2)));
      var1.add(new SettingUpdateEntity(1, 14, this.getStatusString(10, 2)));
      var1.add(new SettingUpdateEntity(1, 15, this.getStatusString(9, 5)));
      var1.add(new SettingUpdateEntity(1, 16, this.getStatusString(9, 6)));
      var1.add(new SettingUpdateEntity(1, 17, this.getStatusString(9, 7)));
      var1.add(new SettingUpdateEntity(1, 18, this.getStatusString(10, 1)));
      var1.add(new SettingUpdateEntity(1, 19, this.getStatusString(10, 3)));
      var1.add(new SettingUpdateEntity(1, 20, this.getStatusString(10, 4)));
      var1.add(new SettingUpdateEntity(1, 21, this.getStatusString(10, 5)));
      var1.add(new SettingUpdateEntity(1, 22, this.getStatusString(10, 6)));
      var1.add(new SettingUpdateEntity(1, 23, this.getStatusString(10, 7)));
      var1.add(new SettingUpdateEntity(2, 0, this.getStatusString(14, 0)));
      var1.add(new SettingUpdateEntity(2, 1, this.getStatusString(15, 5)));
      var1.add(new SettingUpdateEntity(2, 2, this.getStatusString(14, 2)));
      var1.add(new SettingUpdateEntity(2, 3, this.getStatusString(15, 6)));
      var1.add(new SettingUpdateEntity(2, 4, this.getStatusString(14, 4)));
      var1.add(new SettingUpdateEntity(2, 5, this.getStatusString(14, 5)));
      var1.add(new SettingUpdateEntity(2, 6, this.getStatusString(14, 6)));
      var1.add(new SettingUpdateEntity(2, 7, this.getStatusString(14, 7)));
      var1.add(new SettingUpdateEntity(2, 8, this.getStatusString(14, 3)));
      var1.add(new SettingUpdateEntity(2, 9, this.getStatusString(15, 7)));
      var1.add(new SettingUpdateEntity(3, 0, this.getStatusString(12, 0)));
      var1.add(new SettingUpdateEntity(3, 1, this.getStatusString(12, 1)));
      var1.add(new SettingUpdateEntity(3, 2, this.getStatusString(12, 2)));
      var1.add(new SettingUpdateEntity(3, 3, this.getStatusString(12, 3)));
      var1.add(new SettingUpdateEntity(3, 4, this.getStatusString(12, 4)));
      var1.add(new SettingUpdateEntity(3, 5, this.getStatusString(12, 5)));
      var1.add(new SettingUpdateEntity(3, 6, this.getStatusString(12, 6)));
      var1.add(new SettingUpdateEntity(3, 7, this.getStatusString(12, 7)));
      var1.add(new SettingUpdateEntity(3, 8, this.getStatusString(13, 0)));
      var1.add(new SettingUpdateEntity(3, 9, this.getStatusString(13, 1)));
      var1.add(new SettingUpdateEntity(3, 10, this.getStatusString(13, 2)));
      var1.add(new SettingUpdateEntity(3, 11, this.getStatusString(13, 3)));
      var1.add(new SettingUpdateEntity(4, 0, this.getStatusString(16, 0)));
      var1.add(new SettingUpdateEntity(4, 1, this.getStatusString(16, 1)));
      var1.add(new SettingUpdateEntity(4, 2, this.getStatusString(16, 2)));
      var1.add(new SettingUpdateEntity(4, 3, this.getStatusString(16, 3)));
      var1.add(new SettingUpdateEntity(4, 4, this.getStatusString(16, 4)));
      var1.add(new SettingUpdateEntity(4, 5, this.getStatusString(16, 5)));
      var1.add(new SettingUpdateEntity(4, 6, this.getStatusString(16, 6)));
      var1.add(new SettingUpdateEntity(4, 7, this.getStatusString(16, 7)));
      var1.add(new SettingUpdateEntity(5, 0, this.getStatusString(11, 0)));
      var1.add(new SettingUpdateEntity(5, 1, this.getStatusString(11, 1)));
      var1.add(new SettingUpdateEntity(5, 2, this.getStatusString(11, 2)));
      var1.add(new SettingUpdateEntity(5, 3, this.getStatusString(11, 3)));
      var1.add(new SettingUpdateEntity(5, 4, this.getStatusString(11, 4)));
      var1.add(new SettingUpdateEntity(5, 5, this.getStatusString(11, 5)));
      var1.add(new SettingUpdateEntity(5, 6, this.getStatusString(11, 6)));
      var1.add(new SettingUpdateEntity(5, 7, this.getStatusString(11, 7)));
      var1.add(new SettingUpdateEntity(6, 0, this.getStatusString(15, 0)));
      var1.add(new SettingUpdateEntity(6, 1, this.getStatusString(15, 1)));
      var1.add(new SettingUpdateEntity(6, 2, this.getStatusString(15, 2)));
      var1.add(new SettingUpdateEntity(6, 3, this.getStatusString(15, 3)));
      var1.add(new SettingUpdateEntity(6, 4, this.getStatusString(15, 4)));
      this.showDiagnosisWindow();
      this.updateGeneralSettingData(var1);
      this.updateVehicleDiagnosisActivity(this.getBundle());
   }

   private void setVehicleDiagnosisInfo0x7A() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 33, this.getStatusString(2, 5)));
      var1.add(new SettingUpdateEntity(0, 34, this.getStatusString(2, 4)));
      var1.add(new SettingUpdateEntity(0, 35, this.getStatusString(2, 3)));
      var1.add(new SettingUpdateEntity(0, 36, this.getStatusString(2, 2)));
      var1.add(new SettingUpdateEntity(0, 37, this.getStatusString(2, 1)));
      var1.add(new SettingUpdateEntity(0, 44, this.getDowntimeStatus(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      var1.add(new SettingUpdateEntity(1, 24, this.getStatusString(3, 0)));
      var1.add(new SettingUpdateEntity(1, 25, this.getStatusString(3, 1)));
      var1.add(new SettingUpdateEntity(1, 26, this.getStatusString(3, 2)));
      var1.add(new SettingUpdateEntity(1, 27, this.getStatusString(3, 3)));
      var1.add(new SettingUpdateEntity(1, 28, this.getStatusString(3, 4)));
      var1.add(new SettingUpdateEntity(1, 29, this.getStatusString(3, 5)));
      var1.add(new SettingUpdateEntity(1, 30, this.getStatusString(3, 6)));
      var1.add(new SettingUpdateEntity(1, 31, this.getStatusString(3, 7)));
      var1.add(new SettingUpdateEntity(1, 32, this.getStatusString(4, 0)));
      var1.add(new SettingUpdateEntity(1, 33, this.getStatusString(4, 1)));
      var1.add(new SettingUpdateEntity(1, 34, this.getStatusString(4, 2)));
      var1.add(new SettingUpdateEntity(1, 35, this.getStatusString(4, 3)));
      var1.add(new SettingUpdateEntity(1, 36, this.getStatusString(4, 4)));
      var1.add(new SettingUpdateEntity(1, 37, this.getStatusString(4, 5)));
      var1.add(new SettingUpdateEntity(1, 38, this.getStatusString(4, 6)));
      var1.add(new SettingUpdateEntity(1, 39, this.getStatusString(4, 7)));
      var1.add(new SettingUpdateEntity(1, 40, this.getStatusString(5, 0)));
      var1.add(new SettingUpdateEntity(1, 41, this.getStatusString(5, 1)));
      var1.add(new SettingUpdateEntity(1, 42, this.getStatusString(5, 2)));
      var1.add(new SettingUpdateEntity(1, 43, this.getStatusString(5, 3)));
      var1.add(new SettingUpdateEntity(1, 44, this.getStatusString(5, 4)));
      var1.add(new SettingUpdateEntity(1, 45, this.getStatusString(5, 5)));
      var1.add(new SettingUpdateEntity(1, 46, this.getStatusString(5, 6)));
      var1.add(new SettingUpdateEntity(1, 47, this.getStatusString(5, 7)));
      var1.add(new SettingUpdateEntity(1, 48, this.getStatusString(6, 0)));
      var1.add(new SettingUpdateEntity(1, 49, this.getStatusString(6, 1)));
      var1.add(new SettingUpdateEntity(1, 50, this.getStatusString(6, 2)));
      var1.add(new SettingUpdateEntity(1, 51, this.getStatusString(6, 3)));
      var1.add(new SettingUpdateEntity(1, 52, this.getStatusString(6, 4)));
      var1.add(new SettingUpdateEntity(1, 53, this.getStatusString(6, 5)));
      var1.add(new SettingUpdateEntity(1, 54, this.getStatusString(6, 6)));
      var1.add(new SettingUpdateEntity(1, 55, this.getStatusString(6, 7)));
      var1.add(new SettingUpdateEntity(1, 56, this.getStatusString(7, 0)));
      var1.add(new SettingUpdateEntity(1, 57, this.getStatusString(7, 1)));
      var1.add(new SettingUpdateEntity(1, 58, this.getStatusString(7, 2)));
      var1.add(new SettingUpdateEntity(1, 59, this.getStatusString(7, 3)));
      var1.add(new SettingUpdateEntity(1, 60, this.getStatusString(7, 4)));
      var1.add(new SettingUpdateEntity(1, 61, this.getStatusString(7, 5)));
      var1.add(new SettingUpdateEntity(1, 62, this.getStatusString(7, 6)));
      var1.add(new SettingUpdateEntity(2, 10, this.getStatusString(8, 2)));
      var1.add(new SettingUpdateEntity(2, 11, this.getStatusString(8, 3)));
      var1.add(new SettingUpdateEntity(2, 12, this.getStatusString(8, 4)));
      var1.add(new SettingUpdateEntity(2, 13, this.getStatusString(8, 5)));
      var1.add(new SettingUpdateEntity(2, 14, this.getStatusString(8, 6)));
      var1.add(new SettingUpdateEntity(2, 15, this.getStatusString(8, 7)));
      var1.add(new SettingUpdateEntity(2, 16, this.getStatusString(9, 0)));
      var1.add(new SettingUpdateEntity(2, 17, this.getStatusString(9, 1)));
      var1.add(new SettingUpdateEntity(2, 18, this.getStatusString(9, 2)));
      var1.add(new SettingUpdateEntity(2, 19, this.getStatusString(9, 3)));
      var1.add(new SettingUpdateEntity(2, 20, this.getStatusString(9, 4)));
      var1.add(new SettingUpdateEntity(2, 21, this.getStatusString(9, 5)));
      var1.add(new SettingUpdateEntity(2, 22, this.getStatusString(10, 0)));
      var1.add(new SettingUpdateEntity(2, 23, this.getStatusString(10, 1)));
      var1.add(new SettingUpdateEntity(2, 24, this.getStatusString(10, 2)));
      var1.add(new SettingUpdateEntity(2, 25, this.getStatusString(10, 3)));
      var1.add(new SettingUpdateEntity(2, 26, this.getStatusString(10, 4)));
      var1.add(new SettingUpdateEntity(2, 27, this.getStatusString(10, 5)));
      var1.add(new SettingUpdateEntity(2, 28, this.getStatusString(10, 7)));
      var1.add(new SettingUpdateEntity(4, 8, this.getStatusString(11, 4)));
      var1.add(new SettingUpdateEntity(4, 9, this.getStatusString(11, 5)));
      var1.add(new SettingUpdateEntity(4, 10, this.getStatusString(11, 6)));
      var1.add(new SettingUpdateEntity(4, 11, this.getStatusString(11, 7)));
      var1.add(new SettingUpdateEntity(5, 8, this.getStatusString(12, 0)));
      var1.add(new SettingUpdateEntity(5, 9, this.getStatusString(12, 1)));
      var1.add(new SettingUpdateEntity(5, 10, this.getFaultSign(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 2, 4))));
      this.updateGeneralSettingData(var1);
      this.updateVehicleDiagnosisActivity(this.getBundle());
   }

   private void setVehicleStatus0x33() {
      ArrayList var3 = new ArrayList();
      int[] var4 = this.mCanBusInfoInt;
      float var1 = (float)(var4[4] * 256 + var4[5]) / 10.0F;
      Integer var6 = 0;
      var3.add(new SettingUpdateEntity(0, 0, var1));
      var3.add(new SettingUpdateEntity(1, 0, var1));
      int[] var5 = this.mCanBusInfoInt;
      float var2 = (float)(var5[6] * 256 + var5[7] - 5000) / 10.0F;
      var1 = var2;
      if (var2 > 500.0F) {
         var1 = 500.0F;
      }

      var2 = var1;
      if (var1 < -500.0F) {
         var2 = -500.0F;
      }

      var3.add(new SettingUpdateEntity(1, 1, var2));
      Log.i("", "setVehicleStatus0x33: mVehicleMode: " + this.mVehicleMode);
      if (this.mVehicleMode == 1) {
         var3.add(new SettingUpdateEntity(0, 1, var6));
         if (var2 > 0.0F) {
            var3.add(new SettingUpdateEntity(0, 3, var6));
         } else if (var2 < 0.0F) {
            var3.add(new SettingUpdateEntity(0, 3, var2));
         }
      } else {
         var3.add(new SettingUpdateEntity(0, 1, var2));
         var3.add(new SettingUpdateEntity(0, 3, var6));
      }

      var3.add(new SettingUpdateEntity(0, 2, (int)((float)this.mCanBusInfoInt[8])));
      var4 = this.mCanBusInfoInt;
      var3.add(new SettingUpdateEntity(1, 2, (float)(var4[9] * 256 + var4[10]) / 100.0F));
      var4 = this.mCanBusInfoInt;
      var3.add(new SettingUpdateEntity(1, 3, (float)(var4[11] * 256 + var4[12]) / 100.0F));
      var3.add(new SettingUpdateEntity(1, 4, (int)((float)(this.mCanBusInfoInt[13] - 40))));
      var3.add(new SettingUpdateEntity(1, 5, (int)((float)(this.mCanBusInfoInt[14] - 40))));
      this.update277GeneralSettingData(var3);
      this.update277VehicleMonitorActivity(this.getBundle());
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void showDiagnosisWindow() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.mDiagnosisList.clear();
            String[] var4 = this.this$0.mStrings;
            int var2 = var4.length;

            for(int var1 = 0; var1 < var2; ++var1) {
               String var3 = var4[var1];
               if (var3 != null) {
                  this.this$0.mDiagnosisList.add(new DiagnosisEntity(var3));
               }
            }

            if (this.this$0.mIsNeedShow || DialogUtil.mHasAdded) {
               Log.i("ljq", "callback: mIsNeedShow: " + this.this$0.mIsNeedShow + ", mHasAdded: " + DialogUtil.mHasAdded);
               DialogUtil.getInstance().showDiagnosisWindow(this.this$0.mContext, this.this$0.mDiagnosisList, this.this$0.mIsNeedShow);
               this.this$0.mIsNeedShow = false;
               Intent var5 = new Intent();
               var5.setFlags(268435456);
               var5.setComponent(Constant.VehicleDiagnosisActivity);
               this.this$0.mContext.startActivity(var5);
            }

         }
      });
   }

   private void update277GeneralSettingData(List var1) {
      if (var1 != null) {
         for(int var2 = 0; var2 < var1.size(); ++var2) {
            for(int var3 = 0; var3 < _277_GeneralSettingData.dataList2.size(); ++var3) {
               if (((SettingUpdateEntity)_277_GeneralSettingData.dataList2.get(var3)).getLeftListIndex() == ((SettingUpdateEntity)var1.get(var2)).getLeftListIndex() && ((SettingUpdateEntity)_277_GeneralSettingData.dataList2.get(var3)).getRightListIndex() == ((SettingUpdateEntity)var1.get(var2)).getRightListIndex()) {
                  _277_GeneralSettingData.dataList2.remove(var3);
               }
            }

            _277_GeneralSettingData.dataList2.add((SettingUpdateEntity)var1.get(var2));
         }

      }
   }

   private void update277VehicleMonitorActivity(Bundle var1) {
      LogUtil.showLog("updateVehicleMonitorActivity");
      if (this.getActivity() == null) {
         LogUtil.showLog("MonitorActivity handler==null");
      } else if (this.getActivity() instanceof VehicleMonitorActivity) {
         this.updateActivity(var1);
      }

   }

   private void updateVehicleDiagnosisActivity(Bundle var1) {
      LogUtil.showLog("updateVehicleDiagnosisActivity");
      if (this.getActivity() == null) {
         LogUtil.showLog("DiagnosisActivity mMgrRefreshUiInterface==null");
      } else if (this.getActivity() instanceof VehicleDiagnosisActivity) {
         this.updateActivity(var1);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;
      if (this.mDiagnosisList == null) {
         this.mDiagnosisList = new ArrayList();
      }

      if (this.mStrings == null) {
         this.mStrings = new String[10];
      }

      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 51) {
         if (var3 != 118) {
            if (var3 != 122) {
               if (var3 != 240) {
                  if (var3 != 56) {
                     if (var3 == 57) {
                        this.setBatteryVoltage0x39();
                     }
                  } else {
                     this.setMotorStatus0x38();
                  }
               } else {
                  this.setVersionInfo();
               }
            } else {
               this.setVehicleDiagnosisInfo0x7A();
            }
         } else {
            this.setVehicleDiagnosisInfo0x76();
         }
      } else {
         this.setVehicleStatus0x33();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
