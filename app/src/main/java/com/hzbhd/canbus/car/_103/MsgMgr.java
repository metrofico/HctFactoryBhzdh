package com.hzbhd.canbus.car._103;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static final String SHARE_103_AMPLIFIER_BALANCE = "share_103_amplifier_balance";
   private static final String SHARE_103_AMPLIFIER_BASS = "share_103_amplifier_bass";
   private static final String SHARE_103_AMPLIFIER_FADE = "share_103_amplifier_fade";
   private static final String SHARE_103_AMPLIFIER_MIDDLE = "share_103_amplifier_middle";
   private static final String SHARE_103_AMPLIFIER_TREBLE = "share_103_amplifier_treble";
   private static final String SHARE_103_AMPLIFIER_VOLUME = "share_103_amplifier_volume";
   static final int _103_AMPLIFIER_BAND_MAX = 2;
   static final int _103_AMPLIFIER_HALF_MAX = 11;
   static final int _103_AMPLIFIER_PUNCH_MAX = 5;
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_FRONT_OPEN = "is_front_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private int[] mAmplifierDataNow;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private byte[] saveAirData;

   private int againstInt(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               return var1 != 3 ? var1 : 1;
            } else {
               return 3;
            }
         } else {
            return 4;
         }
      } else {
         return 0;
      }
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_103_amplifier_balance", GeneralAmplifierData.leftRight);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_103_amplifier_fade", GeneralAmplifierData.frontRear);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_103_amplifier_bass", GeneralAmplifierData.bandBass);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "share_103_amplifier_middle", GeneralAmplifierData.bandMiddle);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_103_amplifier_treble", GeneralAmplifierData.bandTreble);
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "share_103_amplifier_volume", GeneralAmplifierData.volume);
      }

      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 11)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 11)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)(GeneralAmplifierData.bandBass + 2)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)(GeneralAmplifierData.bandMiddle + 2)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)(GeneralAmplifierData.bandTreble + 2)});
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private boolean isAmplifierDataChange(int[] var1) {
      if (Arrays.equals(var1, this.mAmplifierDataNow)) {
         return false;
      } else {
         this.mAmplifierDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) != GeneralDoorData.isBackOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) != GeneralDoorData.isFrontOpen;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyClick2(this.mContext, var1, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LOW";
      } else if (255 == var1) {
         var2 = "HIGH";
      } else if (var1 > 0 && var1 < 255) {
         var2 = (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void setAirData0x21() {
      this.saveAirData = this.mCanBusInfoByte;
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         GeneralAirData.front_left_blow_window = true;
         GeneralAirData.front_right_blow_window = true;
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         GeneralAirData.front_left_blow_head = true;
         GeneralAirData.front_right_blow_head = true;
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         GeneralAirData.front_left_blow_foot = true;
         GeneralAirData.front_right_blow_foot = true;
      }

      DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralAirData.auto_manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]) ^ true;
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarSetting0x40() {
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3);
      int var15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
      int var16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3);
      int var22 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 3);
      int var20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
      int var23 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
      int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1);
      int var25 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 2);
      int var21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1);
      int var26 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1);
      int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1);
      int var17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2);
      int var19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 1);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2);
      int var24 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 2);
      ArrayList var27 = new ArrayList();
      var27.add(new SettingUpdateEntity(2, 1, var6));
      var27.add(new SettingUpdateEntity(2, 2, var15));
      var27.add(new SettingUpdateEntity(2, 3, var9));
      var27.add(new SettingUpdateEntity(2, 4, var10));
      var27.add(new SettingUpdateEntity(2, 5, var16));
      var27.add(new SettingUpdateEntity(2, 6, var7));
      var27.add(new SettingUpdateEntity(2, 7, var3));
      var27.add(new SettingUpdateEntity(2, 8, var8));
      var27.add(new SettingUpdateEntity(2, 9, var12));
      var27.add(new SettingUpdateEntity(2, 10, var22));
      var27.add(new SettingUpdateEntity(2, 11, var20));
      var27.add(new SettingUpdateEntity(2, 12, var23));
      var27.add(new SettingUpdateEntity(2, 13, var14));
      var27.add(new SettingUpdateEntity(2, 14, var11));
      var27.add(new SettingUpdateEntity(2, 15, var2));
      var27.add(new SettingUpdateEntity(2, 16, var25));
      var27.add(new SettingUpdateEntity(2, 17, var4));
      var27.add(new SettingUpdateEntity(2, 18, var21));
      var27.add(new SettingUpdateEntity(2, 19, var26));
      var27.add(new SettingUpdateEntity(2, 20, var1));
      var27.add(new SettingUpdateEntity(2, 21, var18));
      var27.add(new SettingUpdateEntity(2, 22, var17));
      var27.add(new SettingUpdateEntity(2, 23, var5));
      var27.add(new SettingUpdateEntity(2, 24, var19));
      var27.add(new SettingUpdateEntity(2, 25, var13));
      var27.add(new SettingUpdateEntity(2, 26, var24));
      this.updateGeneralSettingData(var27);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarState0xd0() {
      int var1 = this.mCanBusInfoInt[3];
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(2, 26, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarTypeState0xCB() {
      int var1 = this.mCanBusInfoInt[2];
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(1, 0, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         if (this.isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
            this.updateDoorView(this.mContext);
         }

         ArrayList var2 = new ArrayList();
         String var1;
         if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            var1 = this.mContext.getResources().getString(2131769841);
         } else {
            var1 = this.mContext.getResources().getString(2131769410);
         }

         var2.add(new DriverUpdateEntity(0, 1, var1));
         if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            var1 = this.mContext.getResources().getString(2131768423);
         } else {
            var1 = this.mContext.getResources().getString(2131768424);
         }

         var2.add(new DriverUpdateEntity(0, 2, var1));
         if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            var1 = this.mContext.getResources().getString(2131769504);
         } else {
            var1 = this.mContext.getResources().getString(2131768042);
         }

         var2.add(new DriverUpdateEntity(0, 3, var1));
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            var1 = this.mContext.getResources().getString(2131769504);
         } else {
            var1 = this.mContext.getResources().getString(2131768042);
         }

         var2.add(new DriverUpdateEntity(0, 4, var1));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
      }
   }

   private void setDriveData0x16() {
      StringBuilder var2 = new StringBuilder();
      int[] var1 = this.mCanBusInfoInt;
      String var3 = var2.append((var1[3] * 256 + var1[2]) / 100).append("Km/h").toString();
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo((var4[3] * 256 + var4[2]) / 100);
      ArrayList var5 = new ArrayList();
      var5.add(new DriverUpdateEntity(0, 0, var3));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontInfo0x23() {
      RadarInfoUtil.mMinIsClose = false;
      RadarInfoUtil.setFrontRadarLocationData(4, this.againstInt(this.mCanBusInfoInt[2]), this.againstInt(this.mCanBusInfoInt[3]), this.againstInt(this.mCanBusInfoInt[4]), this.againstInt(this.mCanBusInfoInt[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setKeyControl0x20() {
      switch (this.mCanBusInfoInt[2]) {
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
            this.realKeyClick(46);
            break;
         case 4:
            this.realKeyClick(45);
         case 5:
         case 6:
         default:
            break;
         case 7:
            this.realKeyClick(2);
            break;
         case 8:
            this.realKeyClick(187);
            break;
         case 9:
            this.realKeyClick(14);
            break;
         case 10:
            this.realKeyClick(15);
      }

   }

   private void setRadarInfo0x22() {
      RadarInfoUtil.mMinIsClose = false;
      RadarInfoUtil.setRearRadarLocationData(4, this.againstInt(this.mCanBusInfoInt[2]), this.againstInt(this.mCanBusInfoInt[3]), this.againstInt(this.mCanBusInfoInt[4]), this.againstInt(this.mCanBusInfoInt[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSettingData0x17() {
      int[] var9 = this.mCanBusInfoInt;
      int var7 = var9[4];
      int var6 = var9[10];
      int var3 = var9[11];
      int var2 = var9[12];
      int var4 = var9[13];
      int var1 = var9[14];
      int var8 = var9[15];
      int var5 = var9[8];
      ArrayList var10 = new ArrayList();
      var10.add(new SettingUpdateEntity(0, 0, var6));
      var10.add(new SettingUpdateEntity(0, 1, var3));
      var10.add(new SettingUpdateEntity(0, 2, var2));
      var10.add(new SettingUpdateEntity(0, 3, var4));
      var10.add(new SettingUpdateEntity(0, 4, var1));
      var10.add(new SettingUpdateEntity(0, 5, var8));
      var10.add(new SettingUpdateEntity(0, 6, var7));
      var10.add((new SettingUpdateEntity(0, 7, var5 - 5)).setProgress(var5 - 2));
      this.updateGeneralSettingData(var10);
      this.updateSettingActivity((Bundle)null);
      GeneralAmplifierData.frontRear = 11 - this.mCanBusInfoInt[2];
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 11;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[9];
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 2;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[6] - 2;
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7] - 2;
      if (this.isAmplifierDataChange(new int[]{GeneralAmplifierData.leftRight, GeneralAmplifierData.frontRear, GeneralAmplifierData.bandBass, GeneralAmplifierData.bandMiddle, GeneralAmplifierData.bandTreble, GeneralAmplifierData.volume})) {
         this.updateAmplifierActivity((Bundle)null);
      }

      SharePreUtil.setIntValue(this.mContext, "share_103_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_103_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_103_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_103_amplifier_middle", GeneralAmplifierData.bandMiddle);
      SharePreUtil.setIntValue(this.mContext, "share_103_amplifier_treble", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "share_103_amplifier_volume", GeneralAmplifierData.volume);
   }

   private void setTrackInfo0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4944, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 22) {
         if (var3 != 23) {
            if (var3 != 41) {
               if (var3 != 48) {
                  if (var3 != 64) {
                     if (var3 != 203) {
                        switch (var3) {
                           case 32:
                              this.setKeyControl0x20();
                              break;
                           case 33:
                              if (this.isAirMsgRepeat(var2)) {
                                 return;
                              }

                              this.setAirData0x21();
                              break;
                           case 34:
                              this.setRadarInfo0x22();
                              break;
                           case 35:
                              this.setFrontInfo0x23();
                              break;
                           case 36:
                              if (this.isDoorMsgRepeat(var2)) {
                                 return;
                              }

                              this.setDoorData0x24();
                        }
                     } else {
                        this.setCarTypeState0xCB();
                     }
                  } else {
                     this.setCarSetting0x40();
                  }
               } else {
                  this.setVersionInfo();
               }
            } else {
               this.setTrackInfo0x29();
            }
         } else {
            this.setSettingData0x17();
         }
      } else {
         this.setDriveData0x16();
      }

   }

   public byte[] getSaveAirData() {
      return this.saveAirData;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 23, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
      this.initAmplifierData(var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }
}
