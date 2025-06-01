package com.hzbhd.canbus.car._293;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings.System;
import android.util.Base64;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private int FreqInt;
   private byte bandType;
   private byte freqHi;
   private byte freqLo;
   boolean isNeedCanCtrlAmplifier;
   private byte[] mCanInfoByte;
   private int[] mCanInfoInt;
   private Context mContext;
   private Timer mTimer;
   private UiMgr mUiMgr;

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

   private String getResString(int var1) {
      return this.mContext.getResources().getString(var1);
   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(this.mContext, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         final Iterator iterator;
         final MsgMgr this$0;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$util = var2;
            byte var7 = (byte)GeneralAmplifierData.frontRear;
            byte var4 = (byte)GeneralAmplifierData.leftRight;
            byte[] var8 = new byte[]{22, -96, 3, (byte)(GeneralAmplifierData.bandBass - 9)};
            byte var6 = (byte)(GeneralAmplifierData.bandMiddle - 9);
            byte var5 = (byte)(GeneralAmplifierData.bandTreble - 9);
            byte var3 = (byte)GeneralAmplifierData.volume;
            this.iterator = Arrays.stream(new byte[][]{{22, -127, 1, 1}, {22, -96, 2, var7}, {22, -96, 1, var4}, var8, {22, -96, 5, var6}, {22, -96, 4, var5}, {22, -96, 0, var3}}).iterator();
         }

         public void run() {
            if (this.iterator.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.iterator.next());
            } else {
               this.val$util.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private boolean isScreenRed() {
      return SharePreUtil.getBoolValue(this.mContext, "_293_0x7f_is_red", false);
   }

   private void realKeyClick(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanInfoInt;
      this.realKeyClick1(var3, var1, var2[2], var2[3]);
   }

   private String resolveLeftAndRightManualTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 31) {
         var2 = "HI";
      } else {
         var2 = var1 + "";
      }

      return var2;
   }

   private void sendMediaMsg(byte[] var1, byte[] var2, byte[] var3, byte[] var4) {
      if (this.isScreenRed()) {
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 0, 0}, var1));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 0, 1}, var2));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 0, 2}, var3));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 0, 4}, var4));
      } else {
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 2, 0}, var1));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 2, 1}, var2));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 2, 2}, var3));
      }

   }

   private void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanInfoInt[2]) ^ true;
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 0, 4);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanInfoInt[6]);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[6], 4, 2);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanInfoInt[6]);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[6], 0, 2);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightManualTemp(this.mCanInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightManualTemp(this.mCanInfoInt[5]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAmplifierInfo() {
      int[] var3 = this.mCanInfoInt;
      int var1 = var3[2];
      boolean var2;
      if (var1 != 2 && var1 != 3) {
         var2 = false;
      } else {
         var2 = true;
      }

      this.isNeedCanCtrlAmplifier = var2;
      if (var2) {
         GeneralAmplifierData.volume = var3[3];
         GeneralAmplifierData.leftRight = this.mCanInfoByte[4];
         GeneralAmplifierData.frontRear = this.mCanInfoByte[5];
         GeneralAmplifierData.bandBass = this.mCanInfoByte[6] + 9;
         GeneralAmplifierData.bandMiddle = this.mCanInfoByte[8] + 9;
         GeneralAmplifierData.bandTreble = this.mCanInfoByte[7] + 9;
         this.updateAmplifierActivity((Bundle)null);
      }

      this.saveAmplifierData(this.mContext, 293);
      ArrayList var4 = new ArrayList();
      var4.add((new SettingUpdateEntity(0, 0, this.mCanInfoInt[9])).setProgress(this.mCanInfoInt[9]));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarBodyInfo() {
      int var1 = this.mCanInfoInt[2];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               GeneralDoorData.isShowFuelWarning = true;
               GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit7(this.mCanInfoInt[3]);
               GeneralDoorData.isShowBatteryWarning = true;
               GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit6(this.mCanInfoInt[3]);
               this.updateDoorView(this.mContext);
            }
         } else {
            ArrayList var2 = new ArrayList();
            StringBuilder var4 = new StringBuilder();
            int[] var3 = this.mCanInfoInt;
            var2.add(new DriverUpdateEntity(1, 2, var4.append((var3[7] * 256 + var3[8]) / 100).append("V").toString()));
            var2.add(new DriverUpdateEntity(1, 3, (this.mCanInfoByte[9] * 256 + this.mCanInfoInt[10]) / 10 + "℃"));
            StringBuilder var5 = new StringBuilder();
            int[] var6 = this.mCanInfoInt;
            var2.add(new DriverUpdateEntity(1, 4, var5.append(var6[11] * 65536 + var6[12] * 256 + var6[13]).append("Km").toString()));
            var2.add(new DriverUpdateEntity(1, 5, this.mCanInfoInt[14] + "L"));
            this.updateGeneralDriveData(var2);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else {
         GeneralDoorData.isShowMasterDriverSeatBelt = true;
         GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit7(this.mCanInfoInt[3]) ^ true;
         GeneralDoorData.isShowWashingFluidWarning = true;
         GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit6(this.mCanInfoInt[3]);
         GeneralDoorData.isShowHandBrake = true;
         GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit5(this.mCanInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanInfoInt[3]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setCarSetData0x14() {
      int var1 = this.mCanInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769222);
      } else if (var1 == 255) {
         var2 = this.mContext.getResources().getString(2131769223);
      } else {
         var2 = this.mCanInfoInt[2] + "";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanInfoInt[3]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanInfoInt[3]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanInfoInt[3]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanInfoInt[3]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanInfoInt[3]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanInfoInt[3]);
      this.updateDoorView(this.mContext);
      ArrayList var2 = new ArrayList();
      String var1;
      if (DataHandleUtils.getBoolBit2(this.mCanInfoInt[2])) {
         var1 = this.mContext.getResources().getString(2131769504);
      } else {
         var1 = this.mContext.getResources().getString(2131768042);
      }

      var2.add(new DriverUpdateEntity(0, 3, var1));
      if (DataHandleUtils.getBoolBit1(this.mCanInfoInt[2])) {
         var1 = "Not P";
      } else {
         var1 = "P";
      }

      var2.add(new DriverUpdateEntity(0, 2, var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setEsp() {
      byte[] var1 = this.mCanInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 11016, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setFrontRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setLeftRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanInfoInt;
      RadarInfoUtil.setLeftRadarLocationData(5, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setParkingState() {
      ArrayList var2 = new ArrayList();
      int var1;
      if (DataHandleUtils.getBoolBit0(this.mCanInfoInt[2])) {
         var1 = 2131770832;
      } else {
         var1 = 2131770831;
      }

      var2.add(new DriverUpdateEntity(1, 0, this.getResString(var1)));
      if (DataHandleUtils.getBoolBit1(this.mCanInfoInt[2])) {
         var1 = 2131769504;
      } else {
         var1 = 2131768042;
      }

      var2.add(new DriverUpdateEntity(1, 1, this.getResString(var1)));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRearRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanInfoInt;
      RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRightRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanInfoInt;
      RadarInfoUtil.setRightRadarLocationData(5, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setScreenType() {
      int var1 = this.mCanInfoInt[2];
      boolean var2 = true;
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            var3 = "";
         } else {
            var3 = this.getResString(2131761287);
         }
      } else {
         var3 = this.getResString(2131761286);
      }

      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(0, 3, var3));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      Context var5 = this.mContext;
      if (this.mCanInfoInt[2] != 1) {
         var2 = false;
      }

      SharePreUtil.setBoolValue(var5, "_293_0x7f_is_red", var2);
   }

   private void setSwc() {
      switch (this.mCanInfoInt[2]) {
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
            this.realKeyClick(45);
            break;
         case 4:
            this.realKeyClick(46);
            break;
         case 5:
            this.realKeyClick(14);
            break;
         case 6:
            this.realKeyClick(3);
            break;
         case 7:
            this.realKeyClick(2);
            break;
         case 8:
            this.realKeyClick(187);
      }

   }

   private void setTurnLight() {
      int var1 = this.mCanInfoInt[2];
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var2 = "";
               } else {
                  var2 = this.getResString(2131761284);
               }
            } else {
               var2 = this.getResString(2131769290);
            }
         } else {
            var2 = this.getResString(2131769289);
         }
      } else {
         var2 = this.getResString(2131769288);
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 1, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setUpdateAble() {
      int var1 = this.mCanInfoInt[2];
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = "";
            } else {
               var2 = this.getResString(2131761283);
            }
         } else {
            var2 = this.getResString(2131761282);
         }
      } else {
         var2 = this.getResString(2131761281);
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 2, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
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

   private void sndSimpleVol(byte var1, byte var2, boolean var3) {
      byte var4 = var1;
      if (var1 >= 30) {
         var4 = 30;
      }

      var1 = var2;
      if (var2 >= var4) {
         var1 = var4;
      }

      if (var3) {
         var2 = -128;
      } else {
         var2 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)(var1 & 127 | var2 << 7)});
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

   private void startTask() {
      byte[] var1 = new byte[]{22, -112, 34};
      byte[] var4 = new byte[]{22, -112, 35};
      byte[] var3 = new byte[]{22, -112, 35};
      byte[] var2 = new byte[]{22, -112, 127};
      Timer var5 = this.mTimer;
      if (var5 == null) {
         var5 = new Timer();
         this.mTimer = var5;
         var5.schedule(new TimerTask(this, new byte[][]{var1, var4, {22, -112, 37}, {22, -112, 38}}, new byte[][]{var3, var2}) {
            int i;
            final MsgMgr this$0;
            final byte[][] val$backRequestArray;
            final byte[][] val$notBackRequestArray;

            {
               this.this$0 = var1;
               this.val$backRequestArray = var2;
               this.val$notBackRequestArray = var3;
               this.i = 0;
            }

            public void run() {
               byte[][] var1;
               if ("true".equals(SystemProperties.get("vendor.backCamera_needshow"))) {
                  var1 = this.val$backRequestArray;
                  CanbusMsgSender.sendMsg(var1[this.i % var1.length]);
               } else {
                  var1 = this.val$notBackRequestArray;
                  CanbusMsgSender.sendMsg(var1[this.i % var1.length]);
               }

               ++this.i;
            }
         }, 0L, 100L);
      } else {
         var5.cancel();
         this.mTimer = null;
         this.startTask();
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      this.sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 64});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      this.sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      String var10;
      if (var3) {
         var10 = "is Connected";
      } else {
         var10 = "Not Connected";
      }

      this.sendMediaMsg(" ".getBytes(), " ".getBytes(), var2, var10.getBytes());
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanInfoByte = var2;
      this.mCanInfoInt = this.getByteArrayToIntArray(var2);
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
      }

      int[] var4 = this.mCanInfoInt;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 22) {
            if (var3 != 48) {
               if (var3 != 65) {
                  if (var3 != 112) {
                     if (var3 != 50) {
                        if (var3 != 51) {
                           if (var3 != 126) {
                              if (var3 != 127) {
                                 switch (var3) {
                                    case 32:
                                       this.setSwc();
                                       break;
                                    case 33:
                                       this.setAirData0x21();
                                       break;
                                    case 34:
                                       this.setRearRadar();
                                       break;
                                    case 35:
                                       this.setFrontRadar();
                                       break;
                                    case 36:
                                       this.setDoorData0x24();
                                       break;
                                    case 37:
                                       this.setParkingState();
                                       break;
                                    case 38:
                                       this.setEsp();
                                       break;
                                    case 39:
                                       this.setAmplifierInfo();
                                 }
                              } else {
                                 this.setScreenType();
                              }
                           } else {
                              this.setUpdateAble();
                           }
                        } else {
                           this.setRightRadar();
                        }
                     } else {
                        this.setLeftRadar();
                     }
                  } else {
                     this.setTurnLight();
                  }
               } else {
                  this.setCarBodyInfo();
               }
            } else {
               this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanInfoByte));
            }
         } else {
            this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[3], var4[2]));
         }
      } else {
         this.setCarSetData0x14();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      byte var3 = (byte)var1;
      if (var1 == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.sndSimpleVol((byte)30, var3, var2);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
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
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -64, 3, 48});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      this.sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 16, -64});
      CanbusMsgSender.sendMsg(new byte[]{22, 16, -62});
      CanbusMsgSender.sendMsg(new byte[]{22, 16, -61});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
      this.startTask();
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 == 9) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 9, 17});
      } else {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17});
      }

      this.sendMediaMsg(var21.getBytes(), var22.getBytes(), var23.getBytes(), " ".getBytes());
      this.sndMusicMidea(this.mContext, new byte[]{22, -61, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, var6, var7});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1});
      this.setVwRadioInfo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -62, this.bandType, this.freqLo, this.freqHi, (byte)var1});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      this.sendMediaMsg(var2.getBytes(), var3.getBytes(), ("P" + var1).getBytes(), " ".getBytes());
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -64, 0, 0});
         CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
         this.sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 == 9) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 9, 32});
      } else {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 32});
      }

      this.sndVideoMidea(this.mContext, new byte[]{22, -61, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, 0, 0});
      var8 = (new DecimalFormat("00")).format((long)var5) + ":" + (new DecimalFormat("00")).format((long)var6) + ":" + (new DecimalFormat("00")).format((long)var7);
      var11 = var9 * 256 + var3 + "/" + var4;
      this.sendMediaMsg(" ".getBytes(), var8.getBytes(), var11.getBytes(), " ".getBytes());
   }
}
