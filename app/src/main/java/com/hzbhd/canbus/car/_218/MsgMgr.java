package com.hzbhd.canbus.car._218;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private boolean mBackStatus;
   private int mButtonNow;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferent;
   private int[] mDriveData0x32Now;
   private int[] mDriveData0x34Now;
   private int mEachId;
   private boolean mIsAirFirst = true;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private UiMgr mUiMgr;
   private int[] mVersionInfoNow;
   private int[] mWarningDataNow;

   private String getDriveData(int[] var1) {
      if (var1 != null && var1.length != 0) {
         int var4 = 0;
         int var2 = var1[0];

         int var3;
         for(var3 = 1; var3 < var1.length; ++var3) {
            var2 = (int)((double)var2 + (double)var1[var3] * Math.pow(256.0, (double)var3));
         }

         for(var3 = 1; var4 < var1.length * 8 - 1; ++var4) {
            var3 = (var3 << 1) + 1;
         }

         return var2 == var3 ? "" : Integer.toString(var2);
      } else {
         return "";
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isButtonDataChange() {
      int var1 = this.mButtonNow;
      int var2 = this.mCanBusInfoInt[4];
      if (var1 == var2) {
         return false;
      } else {
         this.mButtonNow = var2;
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen) {
            return true;
         }
      }

      return false;
   }

   private boolean isDriveData0x32Change() {
      if (Arrays.equals(this.mDriveData0x32Now, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mDriveData0x32Now = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDriveData0x34Change() {
      if (Arrays.equals(this.mDriveData0x34Now, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mDriveData0x34Now = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return !GeneralAirData.power;
      } else {
         return false;
      }
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isWarningDataChange() {
      if (Arrays.equals(this.mWarningDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mWarningDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void openWarningActivity() {
      Intent var1 = new Intent(this.mContext, WarningActivity.class);
      var1.setFlags(268435456);
      this.mContext.startActivity(var1);
   }

   private String resolveAirTemp(int var1) {
      if (var1 == 254) {
         return "LO";
      } else {
         return var1 == 255 ? "HI" : (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }
   }

   private void setAirData0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         if (!this.isFirst()) {
            GeneralAirData.max_heat = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            int var1 = this.mCanBusInfoInt[6];
            boolean var3 = true;
            boolean var2;
            if (var1 == 3) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_foot = var2;
            if (this.mCanBusInfoInt[6] == 3) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_right_blow_foot = var2;
            if (this.mCanBusInfoInt[6] == 6) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_head = var2;
            if (this.mCanBusInfoInt[6] == 6) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_right_blow_head = var2;
            if (this.mCanBusInfoInt[6] == 11) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_window = var2;
            if (this.mCanBusInfoInt[6] == 11) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_right_blow_window = var2;
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = GeneralAirData.front_left_temperature;
            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setButtonData0x72() {
      if (this.isButtonDataChange()) {
         switch (this.mCanBusInfoInt[4]) {
            case 0:
               this.wheelKeyClick(0);
               break;
            case 1:
               this.wheelKeyClick(130);
               break;
            case 2:
               this.wheelKeyClick(76);
               break;
            case 3:
               this.wheelKeyClick(141);
               break;
            case 4:
               this.wheelKeyClick(3);
               break;
            case 5:
               this.wheelKeyClick(52);
               break;
            case 6:
               this.wheelKeyClick(31);
               break;
            case 7:
               this.startMainActivity(this.mContext);
               this.playBeep();
               break;
            case 8:
               this.wheelKeyClick(45);
               break;
            case 9:
               this.wheelKeyClick(46);
               break;
            case 10:
               this.wheelKeyClick(47);
               break;
            case 11:
               this.wheelKeyClick(48);
               break;
            case 12:
               this.wheelKeyClick(49);
               break;
            case 13:
               this.wheelKeyClick(21);
               break;
            case 14:
               this.wheelKeyClick(20);
               break;
            case 15:
               this.wheelKeyClick(134);
               break;
            case 16:
               this.wheelKeyClick(94);
               break;
            case 17:
               this.wheelKeyClick(59);
               break;
            case 18:
               this.wheelKeyClick(33);
               break;
            case 19:
               this.wheelKeyClick(34);
               break;
            case 20:
               this.wheelKeyClick(35);
               break;
            case 21:
               this.wheelKeyClick(36);
               break;
            case 22:
               this.wheelKeyClick(37);
               break;
            case 23:
               this.wheelKeyClick(38);
               break;
            case 24:
               this.wheelKeyClick(39);
               break;
            case 25:
               this.wheelKeyClick(40);
               break;
            case 26:
               this.wheelKeyClick(41);
               break;
            case 27:
               this.wheelKeyClick(32);
               break;
            case 28:
               this.wheelKeyClick(143);
               break;
            case 29:
               this.wheelKeyClick(6);
               break;
            case 30:
               this.wheelKeyClick(44);
               break;
            case 31:
               this.wheelKeyClick(94);
               break;
            case 32:
               this.wheelKeyClick(219);
               break;
            case 33:
               this.wheelKeyClick(11);
               break;
            case 34:
               this.wheelKeyClick(7);
               break;
            case 35:
               this.wheelKeyClick(8);
         }
      }

   }

   private void setDoorData0x73() {
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9])) {
         boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         this.mLeftFrontRec = var1;
         GeneralDoorData.isLeftFrontDoorOpen = var1;
         var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         this.mRightFrontRec = var1;
         GeneralDoorData.isRightFrontDoorOpen = var1;
         var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         this.mLeftRearRec = var1;
         GeneralDoorData.isLeftRearDoorOpen = var1;
         var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
         this.mRightRearRec = var1;
         GeneralDoorData.isRightRearDoorOpen = var1;
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
      }

      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setVehicleInfo0x32() {
      if (this.isDriveData0x32Change()) {
         ArrayList var1 = new ArrayList();
         StringBuilder var3 = new StringBuilder();
         int[] var2 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 1, var3.append(this.getDriveData(new int[]{var2[5], var2[4]})).append(" rpm").toString()));
         StringBuilder var5 = new StringBuilder();
         int[] var6 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 2, var5.append(this.getDriveData(new int[]{var6[7], var6[6]})).append(" km/h").toString()));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
         int[] var4 = this.mCanBusInfoInt;
         this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[6], var4[7]));
      }

   }

   private void setVehicleInfo0x34() {
      if (this.isDriveData0x34Change()) {
         ArrayList var1 = new ArrayList();
         StringBuilder var2 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 0, var2.append(this.getDriveData(new int[]{var3[8], var3[7], var3[6]})).append(" km").toString()));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setVersionInfo0xF0() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWarningData0x74() {
      if (this.isWarningDataChange()) {
         ArrayList var3 = new ArrayList();

         for(int var1 = 1; var1 <= 6; ++var1) {
            for(int var2 = 0; var2 <= 7; ++var2) {
               if ((this.mCanBusInfoInt[var1 + 2] & 1 << var2) != 0 && !"null".equals(CommUtil.getStrByResId(this.mContext, "_218_warning_" + var1 + "_" + var2))) {
                  var3.add(new WarningEntity(CommUtil.getStrByResId(this.mContext, "_218_warning_" + var1 + "_" + var2)));
               }
            }
         }

         GeneralWarningDataData.dataList = var3;
         this.updateWarningActivity((Bundle)null);
         if (GeneralWarningDataData.dataList.size() > 0) {
            this.openWarningActivity();
         }
      }

   }

   private void wheelKeyClick(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 49) {
         if (var3 != 50) {
            if (var3 != 52) {
               if (var3 != 240) {
                  switch (var3) {
                     case 114:
                        this.setButtonData0x72();
                        break;
                     case 115:
                        this.setDoorData0x73();
                        break;
                     case 116:
                        this.setWarningData0x74();
                  }
               } else {
                  this.setVersionInfo0xF0();
               }
            } else {
               this.setVehicleInfo0x34();
            }
         } else {
            this.setVehicleInfo0x32();
         }
      } else {
         this.setAirData0x31();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      byte var3 = 1;
      byte var2 = (byte)this.getUiMgr(this.mContext).getData0();
      if (!FutureUtil.instance.isDiscExist() && var1) {
         var3 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -94, var2, (byte)var3, 0, 0});
   }

   void updateSetting(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(var1, 4, 1)));
      var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(var1, 3, 1)));
      var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(var1, 2, 1)));
      var2.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(var1, 1, 1)));
      var2.add(new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(var1, 0, 1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
