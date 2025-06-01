package com.hzbhd.canbus.car._303;

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
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   public static int mLeftTemp;
   public static int mRightTemp;
   private boolean isBackOpen;
   private boolean isLeftFrontDoorOpen;
   private boolean isLeftRearDoorOpen;
   private boolean isRightFrontDoorOpen;
   private boolean isRightRearDoorOpen;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int[] mFrontRadarDataNow;

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

   private String getDriveData(boolean var1) {
      return var1 ? this.mContext.getString(2131769504) : this.mContext.getString(2131768042);
   }

   private String getLightNum(int var1) {
      if (var1 == 0) {
         return this.mContext.getString(2131768215);
      } else {
         return var1 == 100 ? this.mContext.getString(2131768216) : String.valueOf(var1);
      }
   }

   private int getLocationData(int var1) {
      if (var1 >= 0 && var1 < 25) {
         return 1;
      } else if (var1 >= 25 && var1 < 50) {
         return 2;
      } else if (var1 >= 50 && var1 < 75) {
         return 3;
      } else if (var1 >= 75 && var1 < 100) {
         return 4;
      } else if (var1 >= 100 && var1 < 125) {
         return 5;
      } else if (var1 >= 125 && var1 < 150) {
         return 6;
      } else if (var1 >= 150 && var1 < 175) {
         return 7;
      } else if (var1 >= 175 && var1 < 200) {
         return 8;
      } else if (var1 >= 200 && var1 < 225) {
         return 9;
      } else {
         return var1 >= 225 && var1 < 255 ? 10 : 0;
      }
   }

   private boolean isFrontRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarDataNow, var1)) {
         return false;
      } else {
         this.mFrontRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void setAirInto0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
      GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      this.setFrontBlowSwitch(this.mCanBusInfoInt[6]);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[9]);
      int[] var1 = this.mCanBusInfoInt;
      mLeftTemp = var1[8];
      mRightTemp = var1[9];
      if (!GeneralAirData.power) {
         GeneralAirData.front_wind_level = 0;
         GeneralAirData.auto_cycle = false;
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarInfo0x11() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, this.getDriveData(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(0, 1, this.getDriveData(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(0, 2, this.getDriveData(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(0, 3, this.getDriveData(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(0, 4, this.getDriveData(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(0, 5, this.getDriveData(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[3] + this.mContext.getString(2131770213)));
      var1.add(new DriverUpdateEntity(0, 7, this.getLightNum(this.mCanBusInfoInt[6])));
      var1.add(new DriverUpdateEntity(0, 8, this.getLightNum(this.mCanBusInfoInt[7])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
         this.forceReverse(this.mContext, true);
      } else {
         this.forceReverse(this.mContext, false);
      }

   }

   private void setDoorData0x11() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[11]);
      if (this.isLeftFrontDoorOpen != GeneralDoorData.isLeftFrontDoorOpen || this.isRightFrontDoorOpen != GeneralDoorData.isRightFrontDoorOpen || this.isLeftRearDoorOpen != GeneralDoorData.isLeftRearDoorOpen || this.isRightRearDoorOpen != GeneralDoorData.isRightRearDoorOpen || this.isBackOpen != GeneralDoorData.isBackOpen) {
         this.isLeftFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
         this.isRightFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
         this.isLeftRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
         this.isRightRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
         this.isBackOpen = GeneralDoorData.isBackOpen;
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontBlow(boolean var1, boolean var2, boolean var3, boolean var4) {
      GeneralAirData.front_left_blow_window = var1;
      GeneralAirData.front_right_blow_window = var1;
      GeneralAirData.front_left_blow_head = var2;
      GeneralAirData.front_right_blow_head = var2;
      GeneralAirData.front_left_blow_foot = var3;
      GeneralAirData.front_right_blow_foot = var3;
      GeneralAirData.rear_left_auto_wind = var4;
      GeneralAirData.rear_right_auto_wind = var4;
   }

   private void setFrontBlowSwitch(int var1) {
      this.setFrontBlow(false, false, false, false);
      if (var1 != 1) {
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 6) {
                  switch (var1) {
                     case 11:
                        this.setFrontBlow(true, false, false, false);
                        break;
                     case 12:
                        this.setFrontBlow(true, false, true, false);
                        break;
                     case 13:
                        this.setFrontBlow(true, true, false, false);
                        break;
                     case 14:
                        this.setFrontBlow(true, true, true, false);
                  }
               } else {
                  this.setFrontBlow(false, true, false, false);
               }
            } else {
               this.setFrontBlow(false, true, true, false);
            }
         } else {
            this.setFrontBlow(false, false, true, false);
         }
      } else {
         this.setFrontBlow(false, false, false, true);
      }

   }

   private void setKeyEvent0x11() {
      switch (this.mCanBusInfoInt[4]) {
         case 0:
            this.realKeyClick(0);
            break;
         case 1:
            this.realKeyClick(7);
            break;
         case 2:
            this.realKeyClick(8);
            break;
         case 3:
            this.realKeyClick(3);
         case 4:
         case 7:
         default:
            break;
         case 5:
            this.realKeyClick(14);
            break;
         case 6:
            this.realKeyClick(15);
            break;
         case 8:
            this.realKeyClick(45);
            break;
         case 9:
            this.realKeyClick(46);
            break;
         case 10:
            this.realKeyClick(2);
      }

   }

   private void setKeyNum0x7D() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 9, String.valueOf(this.mCanBusInfoInt[3])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setLightInfo0x68() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
      var1.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
      var1.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
      var1.add((new SettingUpdateEntity(3, 3, this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6]));
      var1.add(new SettingUpdateEntity(3, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2)));
      var1.add(new SettingUpdateEntity(3, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)));
      var1.add((new SettingUpdateEntity(3, 6, this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setMileageInfo0x19() {
      int[] var6 = this.mCanBusInfoInt;
      int var5 = var6[2];
      int var1 = var6[3];
      int var3 = var6[4];
      int var4 = var6[10];
      int var2 = var6[11];
      ArrayList var7 = new ArrayList();
      var7.add(new DriverUpdateEntity(1, 6, String.valueOf((var5 << 16) + (var1 << 8) + var3)));
      var7.add(new DriverUpdateEntity(1, 7, String.valueOf((var4 << 8) + var2)));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setParkingInfo0x71() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add((new SettingUpdateEntity(5, 1, this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4] - 1));
      var1.add((new SettingUpdateEntity(5, 2, this.mCanBusInfoInt[5])).setProgress(this.mCanBusInfoInt[5] - 1));
      var1.add((new SettingUpdateEntity(5, 3, this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6] - 1));
      var1.add((new SettingUpdateEntity(5, 4, this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7] - 1));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRadarInfo0x41() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setFrontRadarLocationData(10, this.getLocationData(this.mCanBusInfoInt[6]), this.getLocationData(this.mCanBusInfoInt[7]), this.getLocationData(this.mCanBusInfoInt[8]), this.getLocationData(this.mCanBusInfoInt[9]));
      RadarInfoUtil.setRearRadarLocationData(10, this.getLocationData(this.mCanBusInfoInt[2]), this.getLocationData(this.mCanBusInfoInt[3]), this.getLocationData(this.mCanBusInfoInt[4]), this.getLocationData(this.mCanBusInfoInt[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSubInfo0x48() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrackInfo0x11() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 480, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTyresInfo0x46() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add((new SettingUpdateEntity(1, 1, this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTyresInfo0xC4() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(1, 2, this.mCanBusInfoInt[3] - 1));
      var1.add((new SettingUpdateEntity(1, 3, this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setUnitInfo0xC1() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(6, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(6, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      var1.add(new SettingUpdateEntity(6, 2, Math.abs(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1) - 1)));
      var1.add(new SettingUpdateEntity(6, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2)));
      var1.add(new SettingUpdateEntity(6, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2)));
      var1.add(new SettingUpdateEntity(6, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setUpKeepInfo0xC3() {
      int var1;
      Context var2;
      if (this.mCanBusInfoInt[4] == 1) {
         var2 = this.mContext;
         var1 = 2131770540;
      } else {
         var2 = this.mContext;
         var1 = 2131770538;
      }

      String var3 = var2.getString(var1);
      String var7 = this.mContext.getString(2131770118);
      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(1, 0, String.valueOf(this.mCanBusInfoInt[2])));
      var4.add(new DriverUpdateEntity(1, 1, var3));
      StringBuilder var5 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 2, var5.append((var6[5] * 256 + var6[6]) * 100).append(var3).toString()));
      var5 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 3, var5.append(var6[7] * 256 + var6[8]).append(var7).toString()));
      var4.add(new DriverUpdateEntity(1, 4, this.mCanBusInfoInt[9] * 100 + var3));
      var4.add(new DriverUpdateEntity(1, 5, this.mCanBusInfoInt[10] + var7));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      ArrayList var8 = new ArrayList();
      var8.add(new SettingUpdateEntity(7, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1)));
      this.updateGeneralSettingData(var8);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVersionInfo0x3F() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWindowsAndCenterInfo0x64() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var1.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var1.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      var1.add(new SettingUpdateEntity(4, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
      var1.add(new SettingUpdateEntity(4, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
      var1.add(new SettingUpdateEntity(4, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
      var1.add(new SettingUpdateEntity(4, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 12, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = "BT MUSIC    ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var1));
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      byte[] var10 = var2;
      if (var2.length < 12) {
         var10 = new byte[12 - var2.length];
         Arrays.fill(var10, (byte)32);
         var10 = DataHandleUtils.byteMerger(var10, var2);
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, var10));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      this.mContext = var1;
      switch (var3[1]) {
         case 17:
            this.setCarInfo0x11();
            this.setKeyEvent0x11();
            this.setTrackInfo0x11();
            this.setDoorData0x11();
            break;
         case 25:
            this.setMileageInfo0x19();
            break;
         case 49:
            if (this.isAirMsgRepeat(var2)) {
               return;
            }

            this.setAirInto0x31();
            break;
         case 63:
            this.setVersionInfo0x3F();
            break;
         case 65:
            this.setRadarInfo0x41();
            break;
         case 70:
            this.setTyresInfo0x46();
            break;
         case 72:
            this.setSubInfo0x48();
            break;
         case 100:
            this.setWindowsAndCenterInfo0x64();
            break;
         case 104:
            this.setLightInfo0x68();
            break;
         case 113:
            this.setParkingInfo0x71();
            break;
         case 125:
            this.setKeyNum0x7D();
            break;
         case 193:
            this.setUnitInfo0xC1();
            break;
         case 195:
            this.setUpKeepInfo0xC3();
            break;
         case 196:
            this.setTyresInfo0xC4();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      byte var17 = (byte)DataHandleUtils.setIntByteWithBit(0, 7, var12);
      byte var15 = (byte)var5;
      byte var14 = (byte)var6;
      byte var16 = (byte)0;
      CanbusMsgSender.sendMsg(new byte[]{22, -53, var17, var15, var14, var16, var16, (byte)var10, (byte)(var2 + 208), (byte)var3, (byte)var4, (byte)var9});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 7, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 8, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var25 = ((var9 & 255) * 256 + var3 + "/" + var4 + " " + var12 + ":" + var13 + "     ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var25));
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var2.hashCode();
      var5 = var2.hashCode();
      byte var9 = -1;
      switch (var5) {
         case 64901:
            if (var2.equals("AM1")) {
               var9 = 0;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var9 = 1;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var9 = 2;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var9 = 3;
            }
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var9 = 4;
            }
      }

      var4 = "01";
      switch (var9) {
         case 0:
            var9 = 4;
            break;
         case 1:
            var9 = 5;
            var4 = "03";
            break;
         case 2:
         default:
            var9 = 1;
            break;
         case 3:
            var4 = "10";
            var9 = 2;
            break;
         case 4:
            var4 = "10";
            var9 = 3;
      }

      String var8;
      if (var2.contains("FM") && var2.length() == 4) {
         var8 = "  ";
      } else {
         var8 = " ";
      }

      String var7;
      if (var2.contains("FM") && var2.length() == 4) {
         var7 = " " + var3;
      } else {
         var7 = var3;
         if (var2.contains("AM")) {
            if (var2.length() == 3) {
               var7 = var3 + "  ";
            } else {
               var7 = var3 + " ";
            }
         }
      }

      var2 = var4 + var8 + var7 + this.getBandUnit(var2) + " ";
      byte var6 = (byte)var9;
      byte[] var10 = var2.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, var6}, var10));
   }

   protected void realKeyClick(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = ((var9 & 255) * 256 + var3 + "/" + var4 + " " + var12 + ":" + var13 + "     ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var18));
   }
}
