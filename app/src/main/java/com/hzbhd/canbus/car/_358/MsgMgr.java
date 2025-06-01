package com.hzbhd.canbus.car._358;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private static int mDifferentId;
   private int eachId;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private UiMgr uiMgr;

   private String ResolveLight(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131759513);
      } else {
         var2 = this.mContext.getResources().getString(2131759512);
      }

      return var2;
   }

   private String ResolveRev(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131763199);
      } else {
         var2 = this.mContext.getResources().getString(2131763198);
      }

      return var2;
   }

   private void VersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private int[] getFreqByteHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            var4[0] = (byte)(var3 >> 8);
            var4[1] = (byte)(var3 & 255);
         }
      } else {
         var4[0] = (byte)(Integer.parseInt(var2) >> 8);
         var4[1] = (byte)(Integer.parseInt(var2) & 255);
      }

      return var4;
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

   private void setAmplifier0x17() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_amplifier_switch"), DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      GeneralAmplifierData.volume = this.mCanBusInfoInt[3];
      GeneralAmplifierData.frontRear = this.getSurroundVolFRprogress(this.mCanBusInfoInt[4]);
      GeneralAmplifierData.leftRight = this.getSurroundVolFRprogress(this.mCanBusInfoInt[5]);
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6];
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[8];
      this.updateAmplifierActivity((Bundle)null);
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorInfo0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setDoorInfo0x2402() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_333_setting_carState_10"), this.ResolveRev(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "light_info"), this.ResolveLight(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTrackData0x29() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 10000, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setWheelKey0x20() {
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
         case 6:
         default:
            break;
         case 7:
            this.buttonKey(2);
            break;
         case 8:
            this.buttonKey(187);
            break;
         case 9:
            this.buttonKey(467);
      }

   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 0});
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 23) {
         if (var3 != 32) {
            if (var3 != 36) {
               if (var3 != 41) {
                  if (var3 == 48) {
                     this.VersionInfo0x30();
                  }
               } else {
                  this.setTrackData0x29();
               }
            } else {
               this.setDoorInfo0x24();
               this.setDoorInfo0x2402();
            }
         } else {
            this.setWheelKey0x20();
         }
      } else {
         this.setAmplifier0x17();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      if (var7 == 6 || var7 == 7) {
         if (var7 == 1 || var7 == 5) {
            var4 = var5;
         }

         byte var18 = (byte)(var6 & 255);
         byte var15 = (byte)(var6 >> 8 & 255);
         byte var17 = (byte)(var4 & 255);
         byte var14 = (byte)(var2 & 255);
         byte var16 = (byte)(var2 >> 8 & 255);
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 16, 0, var15, var18, var17, var15, 0, 0, var16, var14});
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = var1;
      if (var1 > 6) {
         var5 = 0;
      }

      byte var10 = CommUtil.getRadioCurrentBand(var2, (byte)1, (byte)2, (byte)3, (byte)17, (byte)18);
      int[] var11 = this.getFreqByteHiLo(var2, var3);
      byte var8 = (byte)var10;
      byte var9 = (byte)var11[1];
      byte var6 = (byte)var11[0];
      byte var7 = (byte)var5;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, var8, var9, var6, var7});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
