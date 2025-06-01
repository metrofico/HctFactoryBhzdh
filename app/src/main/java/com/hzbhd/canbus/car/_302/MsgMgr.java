package com.hzbhd.canbus.car._302;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private boolean mFrontStatus;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private byte[] mMediaInfo = new byte[9];
   private int mMediaInfoData6;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;

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

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

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

   private String resolveAirTemp(Context var1, int var2) {
      String var3;
      if (var2 == 0) {
         var3 = "LO";
      } else if (var2 == 255) {
         var3 = "HI";
      } else {
         var3 = (float)var2 * 0.5F + this.getTempUnitC(var1);
      }

      return var3;
   }

   private void setAirData0x21(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      var2[3] = (byte)(var2[3] & 239);
      if (!this.isAirMsgRepeat(var2)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
         GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemp(var1, this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemp(var1, this.mCanBusInfoInt[5]);
         GeneralAirData.rest = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         this.updateAirActivity(var1, 1001);
      }
   }

   private void setBaseInfo0x22(Context var1) {
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(var1);
      }

   }

   private void setFrontRadarData0x23(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private void setRearRadarData0x25(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private void setSpeedData0x26() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var2.append(var3[2] * 256 + var3[3]).append(" km/h").toString()));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var2.append(var3[4] * 256 + var3[5]).append(" rpm").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(var4[2] * 256 + var4[3]);
   }

   private void setTrackData0x24(Context var1) {
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(this.mCanBusInfoByte[2], (byte)0, 0, 50, 8);
      this.updateParkUi((Bundle)null, var1);
   }

   private void setWheelKey0x01(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         switch (var2) {
            case 18:
               this.realKeyLongClick2(var1, 46);
               break;
            case 19:
               this.realKeyLongClick2(var1, 45);
               break;
            case 20:
               this.realKeyLongClick2(var1, 7);
               break;
            case 21:
               this.realKeyLongClick2(var1, 8);
               break;
            case 22:
               this.realKeyLongClick2(var1, 3);
               break;
            case 23:
               this.realKeyLongClick2(var1, 187);
               break;
            default:
               switch (var2) {
                  case 80:
                     this.realKeyLongClick2(var1, 14);
                     break;
                  case 81:
                     this.realKeyLongClick2(var1, 15);
                     break;
                  case 82:
                     this.realKeyLongClick2(var1, 2);
                     break;
                  case 83:
                     this.realKeyLongClick2(var1, 52);
               }
         }
      } else {
         this.realKeyLongClick2(var1, 0);
      }

   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = this.mMediaInfo;
      var1[0] = 10;
      var1[1] = -1;
      var1[2] = -1;
      var1[3] = 0;
      var1[4] = 0;
      var1[5] = -1;
      var1[6] = (byte)this.mMediaInfoData6;
      var1[7] = -1;
      var1[8] = -1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var1));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = this.mMediaInfo;
      var1[0] = 7;
      var1[1] = -1;
      var1[2] = -1;
      var1[3] = -1;
      var1[4] = -1;
      var1[5] = -1;
      var1[6] = (byte)this.mMediaInfoData6;
      var1[7] = -1;
      var1[8] = -1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var1));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = this.mMediaInfo;
      var1[0] = 9;
      var1[1] = -1;
      var1[2] = -1;
      var1[3] = -1;
      var1[4] = -1;
      var1[5] = -1;
      var1[6] = (byte)this.mMediaInfoData6;
      var1[7] = -1;
      var1[8] = -1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var1));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      byte[] var4 = this.mMediaInfo;
      var4[0] = 9;
      var4[1] = -1;
      var4[2] = -1;
      var4[3] = -1;
      var4[4] = -1;
      var4[5] = -1;
      var4[6] = (byte)this.mMediaInfoData6;
      var4[7] = -1;
      var4[8] = -1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var4));
      var1 = DataHandleUtils.makeBytesFixedLength(var1, 12, 32);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125, 28}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      byte[] var4 = this.mMediaInfo;
      var4[0] = 9;
      var4[1] = -1;
      var4[2] = -1;
      var4[3] = -1;
      var4[4] = -1;
      var4[5] = -1;
      var4[6] = (byte)this.mMediaInfoData6;
      var4[7] = -1;
      var4[8] = -1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var4));
      var1 = DataHandleUtils.makeBytesFixedLength(var1, 12, 32);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125, 28}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      var1 = DataHandleUtils.setIntByteWithBit(this.mMediaInfoData6, 2, var3);
      this.mMediaInfoData6 = var1;
      var2 = this.mMediaInfo;
      var2[6] = (byte)var1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var2));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 1) {
         switch (var3) {
            case 33:
               this.setAirData0x21(var1);
               break;
            case 34:
               this.setBaseInfo0x22(var1);
               break;
            case 35:
               this.setFrontRadarData0x23(var1);
               break;
            case 36:
               this.setTrackData0x24(var1);
               break;
            case 37:
               this.setRearRadarData0x25(var1);
               break;
            case 38:
               this.setSpeedData0x26();
         }
      } else {
         this.setWheelKey0x01(var1);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      var1 = DataHandleUtils.setIntByteWithBit(this.mMediaInfoData6, 3, var2);
      this.mMediaInfoData6 = var1;
      byte[] var3 = this.mMediaInfo;
      var3[6] = (byte)var1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var3));
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      int[] var14 = this.getTime(var2);
      byte[] var15 = this.mMediaInfo;
      var15[0] = 2;
      var15[1] = -1;
      var15[2] = -1;
      var15[3] = (byte)(var5 >> 8);
      var15[4] = (byte)(var5 & 255);
      var15[5] = -1;
      var15[6] = (byte)this.mMediaInfoData6;
      var15[7] = (byte)var14[1];
      var15[8] = (byte)var14[2];
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var15));
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = this.mMediaInfo;
      var1[0] = 10;
      var1[1] = -1;
      var1[2] = -1;
      var1[3] = 0;
      var1[4] = 0;
      var1[5] = -1;
      var1[6] = (byte)this.mMediaInfoData6;
      var1[7] = -1;
      var1[8] = -1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var1));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 3;
      } else {
         var25 = 4;
      }

      byte[] var26 = this.mMediaInfo;
      var26[0] = (byte)var25;
      var26[1] = -1;
      var26[2] = -1;
      var26[3] = var9;
      var26[4] = (byte)var3;
      var26[5] = -1;
      var26[6] = (byte)this.mMediaInfoData6;
      var26[7] = var6;
      var26[8] = var7;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var26));
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var8;
      label40: {
         super.radioInfoChange(var1, var2, var3, var4, var5);
         var2.hashCode();
         switch (var2) {
            case "AM1":
               var8 = 0;
               break label40;
            case "AM2":
               var8 = 1;
               break label40;
            case "FM1":
               var8 = 2;
               break label40;
            case "FM2":
               var8 = 3;
               break label40;
            case "FM3":
               var8 = 4;
               break label40;
         }

         var8 = -1;
      }

      switch (var8) {
         case 0:
            var8 = 33;
            break;
         case 1:
            var8 = 34;
            break;
         case 2:
            var8 = 17;
            break;
         case 3:
            var8 = 18;
            break;
         case 4:
            var8 = 19;
            break;
         default:
            var8 = 0;
      }

      int[] var7 = this.getFreqByteHiLo(var2, var3);
      byte[] var6 = this.mMediaInfo;
      var6[0] = 1;
      var6[1] = -1;
      var6[2] = (byte)var8;
      var6[3] = (byte)var7[0];
      var6[4] = (byte)var7[1];
      var6[5] = (byte)var1;
      var6[6] = (byte)this.mMediaInfoData6;
      var6[7] = -1;
      var6[8] = -1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var6));
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      byte[] var2 = this.mMediaInfo;
      var2[0] = 0;
      var2[1] = -1;
      var2[2] = -1;
      var2[3] = -1;
      var2[4] = -1;
      var2[5] = -1;
      var2[6] = (byte)this.mMediaInfoData6;
      var2[7] = -1;
      var2[8] = -1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var2));
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 3;
      } else {
         var18 = 4;
      }

      byte[] var19 = this.mMediaInfo;
      var19[0] = (byte)var18;
      var19[1] = -1;
      var19[2] = -1;
      var19[3] = var9;
      var19[4] = (byte)var3;
      var19[5] = -1;
      var19[6] = (byte)this.mMediaInfoData6;
      var19[7] = var6;
      var19[8] = var7;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, var19));
   }
}
