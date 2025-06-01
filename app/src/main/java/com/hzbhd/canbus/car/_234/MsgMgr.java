package com.hzbhd.canbus.car._234;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private boolean isMute = false;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   int[] mDoorData;

   private void MyRealKeyClick4(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private boolean isNotDo0rDataChange() {
      if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mDoorData = this.mCanBusInfoInt;
         return false;
      }
   }

   private String resolveLeftRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else if (var1 >= 30 && var1 <= 64) {
         var2 = (float)var1 * 0.5F + "℃";
      } else {
         var2 = "--";
      }

      return var2;
   }

   private void setAir() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.rest = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveLeftRightTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftRightTemp(this.mCanBusInfoInt[5]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarDoor() {
      if (!this.isNotDo0rDataChange()) {
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setCarStatus() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var2.append(var3[2] * 256 + var3[3]).append("km/h").toString()));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var5.append(var4[4] * 256 + var4[5]).append("rpm").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSwc() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 0) {
            if (var1 != 80) {
               if (var1 != 81) {
                  switch (var1) {
                     case 18:
                        this.MyRealKeyClick4(46);
                        break;
                     case 19:
                        this.MyRealKeyClick4(45);
                        break;
                     case 20:
                        this.MyRealKeyClick4(7);
                        break;
                     case 21:
                        this.MyRealKeyClick4(8);
                        break;
                     case 22:
                        this.MyRealKeyClick4(3);
                        break;
                     case 23:
                        this.MyRealKeyClick4(187);
                  }
               } else {
                  this.MyRealKeyClick4(15);
               }
            } else {
               this.MyRealKeyClick4(14);
            }
         } else {
            this.MyRealKeyClick4(0);
         }

      }
   }

   private void setTrack() {
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(this.mCanBusInfoByte[2], (byte)0, 0, 50, 8);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte var3;
      if (this.isMute) {
         var3 = 8;
      } else {
         var3 = 0;
      }

      byte var1 = (byte)7;
      byte var2 = (byte)255;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, var1, var2, var2, var2, var2, var2, (byte)var3, var2, var2});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte var3;
      if (this.isMute) {
         var3 = 12;
      } else {
         var3 = 4;
      }

      byte var1 = (byte)9;
      byte var2 = (byte)255;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, var1, var2, var2, var2, var2, var2, (byte)var3, var2, var2});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.sendPhoneNumber(var1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.sendPhoneNumber(var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.sendPhoneNumber(var1);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.sendPhoneNumber(var2);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      this.sendPhoneNumber(var1);
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      this.sendPhoneNumber(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 36) {
            if (var3 != 38) {
               if (var3 != 48) {
                  if (var3 != 33) {
                     if (var3 == 34) {
                        this.setCarDoor();
                     }
                  } else {
                     this.setAir();
                  }
               } else {
                  this.setVersionInfo();
               }
            } else {
               this.setCarStatus();
            }
         } else {
            this.setTrack();
         }
      } else {
         this.setSwc();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.isMute = var2;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var4 = DataHandleUtils.getMsb(var17);
      var17 = DataHandleUtils.getLsb(var17);
      byte var28;
      if (this.isMute) {
         var28 = 8;
      } else {
         var28 = 0;
      }

      int var26 = Integer.parseInt(var11);
      int var25 = Integer.parseInt(var12);
      int var27 = Integer.parseInt(var13);
      var1 = (byte)4;
      var2 = (byte)255;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, var1, var2, var2, (byte)var4, (byte)var17, var2, (byte)var28, (byte)(var26 * 60 + var25), (byte)var27});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      int var8;
      int var9;
      byte var11;
      if (!var2.equals("FM") && !var2.equals("FM1") && !var2.equals("FM2") && !var2.equals("FM3")) {
         if (!var2.equals("AM") && !var2.equals("AM1") && !var2.equals("AM2")) {
            var11 = 0;
            var8 = 0;
            var9 = var8;
         } else {
            var11 = 32;
            var8 = Integer.parseInt(var3);
            var9 = DataHandleUtils.getMsb(var8);
            var8 = DataHandleUtils.getLsb(var8);
         }
      } else {
         var11 = 16;
         var8 = (int)(Float.parseFloat(var3) * 100.0F);
         var9 = DataHandleUtils.getMsb(var8);
         var8 = DataHandleUtils.getLsb(var8);
      }

      byte var10;
      if (this.isMute) {
         var10 = 8;
      } else {
         var10 = 0;
      }

      byte var6 = (byte)1;
      byte var7 = (byte)255;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, var6, var7, (byte)var11, (byte)var9, (byte)var8, (byte)var1, (byte)var10, var7, var7});
   }

   public void sendPhoneNumber(byte[] var1) {
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -125, (byte)DataHandleUtils.getMsbLsbResult_4bit(1, var1.length)}, var1));
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var19;
      if (this.isMute) {
         var19 = 8;
      } else {
         var19 = 0;
      }

      var14 = Integer.parseInt(var11);
      int var18 = Integer.parseInt(var12);
      var17 = Integer.parseInt(var13);
      var1 = (byte)4;
      var2 = (byte)255;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, var1, var2, var2, var9, (byte)var3, var2, (byte)var19, (byte)(var14 * 60 + var18), (byte)var17});
   }
}
