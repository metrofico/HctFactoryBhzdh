package com.hzbhd.canbus.car._296;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Base64;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private int FreqInt;
   private byte bandType;
   private byte freqHi;
   private byte freqLo;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mFrontStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private int outDoorTemp;

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private boolean getIsUseFunit() {
      int var1 = Integer.parseInt(SharePreUtil.getStringValue(this.mContext, "share_pre_is_use_f_unit", "0"));
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   private String getResString(int var1) {
      return this.mContext.getResources().getString(var1);
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

   private boolean isOutDoorTempChange() {
      return SharePreUtil.getIntValue(this.mContext, "_296_out_door_temp", 0) != this.outDoorTemp;
   }

   private void realKeyClick(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick2(var3, var1, var2[2], var2[3]);
   }

   private String resolveLeftRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 127) {
         var2 = "HI";
      } else if (var1 >= 30 && var1 <= 64) {
         if (this.mCanBusInfoInt[10] == 0) {
            var2 = (float)var1 * 0.5F + "℃";
         } else {
            var2 = (float)((double)var1 * 0.5 * 1.8 + 32.0) + "F";
         }
      } else {
         var2 = "--";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      byte[] var4 = this.mCanBusInfoByte;
      byte var3 = var4[8];
      double var1 = (double)(var4[9] | var3 << 8);
      String var5;
      if (var1 < 0.0) {
         var5 = var1 * 0.5 + "";
      } else {
         var5 = "" + var1 * 0.5;
      }

      return var5 + "℃";
   }

   private String returnData(boolean var1) {
      int var2;
      if (var1) {
         var2 = 2131769504;
      } else {
         var2 = 2131768042;
      }

      return this.getResString(var2);
   }

   private void setAir() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
      GeneralAirData.front_left_temperature = this.resolveLeftRightTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_right_temperature = this.resolveLeftRightTemp(this.mCanBusInfoInt[6]);
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarDoor(Context var1) {
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void setCarSetData0x03() {
      int[] var5 = this.mCanBusInfoInt;
      int var3 = var5[2];
      int var4 = var5[3];
      int var2 = var5[6];
      int var1 = var5[7];
      ArrayList var6 = new ArrayList();
      StringBuilder var8 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var6.add(new DriverUpdateEntity(0, 4, var8.append(var7[8] * 256 * 256 + var7[9] * 256 + var7[10]).append("km").toString()));
      var6.add(new DriverUpdateEntity(0, 5, this.returnData(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]))));
      var6.add(new DriverUpdateEntity(0, 6, this.returnData(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]))));
      var6.add(new DriverUpdateEntity(0, 7, this.returnData(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[12]))));
      var6.add(new DriverUpdateEntity(0, 8, this.returnData(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12]))));
      var6.add(new DriverUpdateEntity(0, 9, this.returnData(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12]))));
      var6.add(new DriverUpdateEntity(0, 10, this.returnData(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12]))));
      var6.add(new DriverUpdateEntity(0, 11, this.returnData(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12]))));
      var6.add(new DriverUpdateEntity(1, 0, (var3 << 8 | var4) + " rpm"));
      var6.add(new DriverUpdateEntity(1, 1, (new DecimalFormat("0.0")).format((double)(var1 | var2 << 8) * 0.1) + " km/h"));
      this.updateGeneralDriveData(var6);
      this.updateDriveDataActivity((Bundle)null);
      var5 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var5[6], var5[7]));
   }

   private void setCarSetData0x0c() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      String var4 = "";
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = "";
            } else {
               var2 = this.getResString(2131767734) + this.getResString(2131769504);
            }
         } else {
            var2 = this.getResString(2131767734);
         }
      } else {
         var2 = this.getResString(2131767734) + this.getResString(2131768042);
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            var3 = "";
         } else {
            var3 = this.getResString(2131769504);
         }
      } else {
         var3 = this.getResString(2131768042);
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
      if (var1 != 0) {
         if (var1 == 1) {
            var4 = this.getResString(2131769504);
            this.enterAuxIn2();
         }
      } else {
         var4 = this.getResString(2131768042);
         this.exitAuxIn2();
      }

      ArrayList var5 = new ArrayList();
      var5.add(new DriverUpdateEntity(0, 1, var2));
      var5.add(new DriverUpdateEntity(0, 2, var3));
      var5.add(new DriverUpdateEntity(0, 3, var4));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRadar() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[3], var1[4], var1[5], var1[6]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[7], var1[8], var1[9], var1[10]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSwc() {
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
            this.realKeyClick(2);
            break;
         case 4:
            this.realKeyClick(3);
            break;
         case 5:
            this.realKeyClick(46);
            break;
         case 6:
            this.realKeyClick(45);
            break;
         case 7:
            this.realKeyClick(187);
            break;
         case 8:
            this.realKeyClick(14);
      }

   }

   private void setTrack() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 0, 4608, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVwRadioInfo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW")) {
         if (!var1.equals("AM1") && !var1.equals("LW")) {
            if (var1.equals("FM1")) {
               this.bandType = 1;
            } else if (var1.equals("FM2")) {
               this.bandType = 2;
            } else if (var1.equals("FM3") || var1.equals("OIRT")) {
               this.bandType = 3;
            }
         } else {
            int var3 = Integer.parseInt(var2);
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
            this.bandType = 17;
         }
      } else {
         this.bandType = 18;
      }

      this.getFreqByteHiLo(var1, var2);
   }

   private void sndDiscMidea(Context var1, byte[] var2) {
      if (System.getInt(var1.getContentResolver(), "btState", 1) == 1 && (FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BACKCAMERA || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.MPEG || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE)) {
         try {
            CanbusMsgSender.sendMsg(var2);
            System.putString(var1.getContentResolver(), "currentReport", Base64.encodeToString(var2, 0));
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   private void sndMusicMidea(Context var1, byte[] var2) {
      if (System.getInt(var1.getContentResolver(), "btState", 1) == 1 && (FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.MUSIC || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE)) {
         try {
            CanbusMsgSender.sendMsg(var2);
            System.putString(var1.getContentResolver(), "currentReport", Base64.encodeToString(var2, 0));
            System.putString(var1.getContentResolver(), "currentReport_disc", Base64.encodeToString(var2, 0));
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   private void sndVideoMidea(Context var1, byte[] var2) {
      if (System.getInt(var1.getContentResolver(), "btState", 1) == 1 && (FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.VIDEO || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE)) {
         try {
            CanbusMsgSender.sendMsg(var2);
            System.putString(var1.getContentResolver(), "currentReport", Base64.encodeToString(var2, 0));
            System.putString(var1.getContentResolver(), "currentReport_disc", Base64.encodeToString(var2, 0));
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   private double tempCToTempF(double var1) {
      LogUtil.showLog("tempCToTempF:" + var1);

      try {
         DecimalFormat var3 = new DecimalFormat("0.0");
         var1 = Double.valueOf(var3.format(var1 * 1.8 + 32.0));
         return var1;
      } catch (Exception var4) {
         LogUtil.showLog("Exception:" + var4);
         return 0.0;
      }
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 48});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 64});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 3) {
         if (var3 != 6) {
            if (var3 != 8) {
               if (var3 != 12) {
                  if (var3 != 113) {
                     switch (var3) {
                        case 98:
                           this.setAir();
                           break;
                        case 99:
                           this.setRadar();
                           break;
                        case 100:
                           this.setTrack();
                     }
                  } else {
                     this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
                  }
               } else {
                  this.setCarSetData0x0c();
               }
            } else {
               this.setCarDoor(var1);
            }
         } else {
            this.setSwc();
         }
      } else {
         this.setCarSetData0x03();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)DataHandleUtils.rangeNumber(var1, 40)});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1) {
         Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.DISC, 33, false, false);
         this.sndDiscMidea(this.mContext, new byte[]{22, -61, (byte)var5, (byte)var6, 0, 0, (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
      } else {
         Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.DISC, 16, false, false);
         this.sndDiscMidea(this.mContext, new byte[]{22, -61, 1, (byte)var4, 0, 0, (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 == 9) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 9, 17});
      } else {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17});
      }

      this.sndMusicMidea(this.mContext, new byte[]{22, -61, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, var6, var7});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.setVwRadioInfo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -62, this.bandType, this.freqLo, this.freqHi, (byte)var1});
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -64, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 == 9) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 9, 32});
      } else {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 32});
      }

      this.sndVideoMidea(this.mContext, new byte[]{22, -61, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, 0, 0});
   }
}
