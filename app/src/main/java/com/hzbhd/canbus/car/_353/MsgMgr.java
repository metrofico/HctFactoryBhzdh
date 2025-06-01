package com.hzbhd.canbus.car._353;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final boolean $assertionsDisabled = false;
   static final String SHARE_353_AMPLIFIER_BALANCE = "share_122_amplifier_balance";
   static final String SHARE_353_AMPLIFIER_BASS = "share_122_amplifier_bass";
   static final String SHARE_353_AMPLIFIER_FADE = "share_122_amplifier_fade";
   static final String SHARE_353_AMPLIFIER_MIDDLE = "share_122_amplifier_middle";
   static final String SHARE_353_AMPLIFIER_TREBLE = "share_122_amplifier_treble";
   static final String SHARE_353_AMPLIFIER_VOLUME = "share_122_amplifier_volume";
   static final int _353_AMPLIFIER_OFFSET = 0;
   static final int _353_AMPLIFIER_RANGE = 0;
   private final int INVAILE_VALUE = -1;
   int[] OutGoingPhoneNumber;
   int RoundVolume;
   int bandBass;
   int bandTreble;
   int[] comingPhoneNumber;
   private int eachId;
   int frontRear;
   int leftRight;
   private boolean mBackStatus;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private DecimalFormat mDecimalFormat00 = new DecimalFormat("00");
   private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
   private DecimalFormat mDecimalFormat0p00 = new DecimalFormat("0.00");
   int[] mFrontRadarData;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   int[] mRearRadarData;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private Timer mTimer;
   private TimerTask mTimerTask;
   private List mTire0 = new ArrayList();
   private List mTire1 = new ArrayList();
   private List mTire2 = new ArrayList();
   private List mTire3 = new ArrayList();
   String resulttemp;
   int speedVolume;
   String str = null;
   private int[] talkingPhoneNumber;
   private UiMgr uiMgr;
   int volume;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private boolean compare(Object var1, Object... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var2[var3].equals(var1)) {
            return true;
         }
      }

      return false;
   }

   private void finishTimer() {
      TimerTask var1 = this.mTimerTask;
      if (var1 != null) {
         var1.cancel();
         this.mTimerTask = null;
      }

      Timer var2 = this.mTimer;
      if (var2 != null) {
         var2.cancel();
         this.mTimer = null;
      }

   }

   private int getBandAmFM(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 2092:
            if (var1.equals("AM")) {
               var2 = 0;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 1;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 2;
            }
            break;
         case 64903:
            if (var1.equals("AM3")) {
               var2 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 6;
            }
      }

      switch (var2) {
         case 0:
            return 16;
         case 1:
            return 17;
         case 2:
            return 18;
         case 3:
            return 19;
         case 4:
            return 1;
         case 5:
            return 2;
         case 6:
            return 3;
         default:
            return 0;
      }
   }

   private int getData(int... var1) {
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 < var1.length; ++var2) {
         var3 += var1[var2] << var2 * 8;
      }

      return (double)var3 == Math.pow(2.0, (double)(var1.length * 8)) - 1.0 ? -1 : var3;
   }

   private int getFreqLsb(String var1, String var2) {
      return this.getBandAmFM(var1) != 0 && this.getBandAmFM(var1) != 1 && this.getBandAmFM(var1) != 2 && this.getBandAmFM(var1) != 3 ? this.getLsb((int)Double.parseDouble(var2)) : this.getLsb((int)(Double.parseDouble(var2) * 100.0));
   }

   private int getFreqMsb(String var1, String var2) {
      return this.getBandAmFM(var1) != 0 && this.getBandAmFM(var1) != 1 && this.getBandAmFM(var1) != 2 && this.getBandAmFM(var1) != 3 ? this.getMsb((int)Double.parseDouble(var2)) : this.getMsb((int)(Double.parseDouble(var2) * 100.0));
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private TireUpdateEntity getTireEntity(int var1, String var2, String var3, String var4, String var5) {
      boolean var7 = TextUtils.isEmpty(var2);
      byte var6 = 0;
      String[] var8;
      if (var7 && TextUtils.isEmpty(var3)) {
         var8 = new String[]{this.mContext.getResources().getString(2131767766), var4, var5};
      } else {
         if (TextUtils.isEmpty(var2)) {
            var8 = new String[]{var3, var4, var5};
         } else {
            var8 = new String[]{var2, var4, var5};
         }

         var6 = 1;
      }

      return new TireUpdateEntity(var1, var6, var8);
   }

   private String getTpmsCheckWarningStr(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131768195);
      } else {
         var2 = this.mContext.getResources().getString(2131768909);
      }

      return var2;
   }

   private String getTpmsHighWarningStr(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131768720);
      } else {
         var2 = null;
      }

      return var2;
   }

   private String getTpmsLowWarningStr(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131769160);
      } else {
         var2 = null;
      }

      return var2;
   }

   private String getTpmsPressureNumStr(int var1, int var2) {
      return this.getMsbLsbResult(var1, var2) + "  KPA";
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "share_122_amplifier_volume", 0);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_122_amplifier_balance", 0);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_122_amplifier_fade", 0);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_122_amplifier_bass", 0);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_122_amplifier_treble", 0);
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume});
      byte[] var6 = new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 0 + 0)};
      byte[] var5 = new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 0 + 0)};
      byte var4 = (byte)(GeneralAmplifierData.bandBass + 0);
      byte var3 = (byte)(GeneralAmplifierData.bandMiddle + 0);
      byte var2 = (byte)(GeneralAmplifierData.bandTreble + 0);
      this.mTimerTask = new TimerTask(this, new byte[][]{var6, var5, {22, -83, 4, var4}, {22, -83, 5, var3}, {22, -83, 6, var2}}) {
         int index;
         final MsgMgr this$0;
         final byte[][] val$ampCmdArrays;

         {
            this.this$0 = var1;
            this.val$ampCmdArrays = var2;
            this.index = 0;
         }

         public void run() {
            int var1 = this.index;
            byte[][] var2 = this.val$ampCmdArrays;
            if (var1 < var2.length) {
               this.index = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.this$0.finishTimer();
            }

         }
      };
      this.startTimer(100L, 100);
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

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   protected static List mergeList(List... var0) {
      int var1 = 0;
      Class var3 = var0[0].getClass();

      List var5;
      try {
         var5 = (List)var3.newInstance();
      } catch (Exception var4) {
         var4.printStackTrace();
         var5 = null;
      }

      for(int var2 = var0.length; var1 < var2; ++var1) {
         var5.addAll(var0[var1]);
      }

      return var5;
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         this.resulttemp = "LOW";
      } else if (var1 == 127) {
         this.resulttemp = "HIGH";
      } else if (var1 >= 31 && var1 <= 64) {
         String var2 = (float)var1 / 2.0F + this.getTempUnitC(this.mContext);
         this.resulttemp = var2;
         return var2;
      }

      return this.resulttemp;
   }

   private int resolveAmplifier(int var1) {
      switch (var1) {
         case 251:
            return 0;
         case 252:
            return 1;
         case 253:
            return 2;
         case 254:
            return 3;
         case 255:
            return 4;
         default:
            return var1 + 5;
      }
   }

   private int resolveAmplifier2(int var1) {
      switch (var1) {
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
            return var1;
      }
   }

   private int resolveAmplifier3(int var1) {
      switch (var1) {
         case 251:
            return 5;
         case 252:
            return 4;
         case 253:
            return 3;
         case 254:
            return 2;
         case 255:
            return 1;
         default:
            return var1 * -1;
      }
   }

   private String resolveOutDoorTem() {
      int var2 = this.mCanBusInfoInt[7];
      int var1 = var2;
      if (var2 > 86) {
         var1 = var2 - 256;
      }

      return var1 + this.getTempUnitC(this.mContext);
   }

   private void saveAmplifierData() {
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_volume", GeneralAmplifierData.volume);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_treble", GeneralAmplifierData.bandTreble);
   }

   private void serTireInfo0x70() {
      List var1 = this.mTire0;
      String var4 = this.getTpmsHighWarningStr(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
      String var2 = this.getTpmsLowWarningStr(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
      int[] var3 = this.mCanBusInfoInt;
      var1.add(this.getTireEntity(0, var4, var2, this.getTpmsPressureNumStr(var3[6], var3[5]), this.getTpmsCheckWarningStr(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]))));
      List var9 = this.mTire1;
      var2 = this.getTpmsHighWarningStr(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      String var5 = this.getTpmsLowWarningStr(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
      var3 = this.mCanBusInfoInt;
      var9.add(this.getTireEntity(1, var2, var5, this.getTpmsPressureNumStr(var3[4], var3[3]), this.getTpmsCheckWarningStr(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]))));
      List var7 = this.mTire2;
      var5 = this.getTpmsHighWarningStr(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
      var4 = this.getTpmsLowWarningStr(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
      var3 = this.mCanBusInfoInt;
      var7.add(this.getTireEntity(2, var5, var4, this.getTpmsPressureNumStr(var3[10], var3[9]), this.getTpmsCheckWarningStr(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]))));
      var7 = this.mTire3;
      var4 = this.getTpmsHighWarningStr(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
      String var8 = this.getTpmsLowWarningStr(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
      int[] var6 = this.mCanBusInfoInt;
      var7.add(this.getTireEntity(3, var4, var8, this.getTpmsPressureNumStr(var6[8], var6[7]), this.getTpmsCheckWarningStr(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]))));
      GeneralTireData.dataList = mergeList(this.mTire0, this.mTire1, this.mTire2, this.mTire3);
      this.updateTirePressureActivity((Bundle)null);
   }

   private void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarSettingInfo0x93() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      int[] var1 = this.mCanBusInfoInt;
      this.volume = var1[2];
      GeneralAmplifierData.bandBass = this.resolveAmplifier(var1[3]);
      this.bandBass = this.resolveAmplifier(this.mCanBusInfoInt[3]);
      GeneralAmplifierData.bandTreble = this.resolveAmplifier(this.mCanBusInfoInt[4]);
      this.bandTreble = this.resolveAmplifier(this.mCanBusInfoInt[4]);
      GeneralAmplifierData.leftRight = this.resolveAmplifier2(this.mCanBusInfoInt[5]);
      this.leftRight = this.resolveAmplifier2(this.mCanBusInfoInt[5]);
      GeneralAmplifierData.frontRear = this.resolveAmplifier3(this.mCanBusInfoInt[6]);
      this.frontRear = this.resolveAmplifier3(this.mCanBusInfoInt[6]);
      var1 = this.mCanBusInfoInt;
      this.speedVolume = var1[7];
      this.RoundVolume = this.resolveAmplifier(var1[9]);
      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_3", "_353_setting_2_6"), this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_3", "_353_setting_2_7"), this.mCanBusInfoInt[8]));
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_3", "_353_setting_2_8"), this.resolveAmplifier(this.mCanBusInfoInt[9]))).setProgress(this.resolveAmplifier(this.mCanBusInfoInt[9])));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_3", "_353_setting_2_9"), this.mCanBusInfoInt[10]));
      this.updateAmplifierActivity((Bundle)null);
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      this.saveAmplifierData();
   }

   private void setCartypeInfo0x60() {
      ArrayList var2 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 == 0) {
         this.str = "日产2014款新奇骏高配（自动空调）";
      }

      if (var1 == 1) {
         this.str = "日产2014款新奇骏低配（手动空调）";
      }

      if (var1 == 2) {
         this.str = "日产2016款新逍客高配（自动空调）";
      }

      if (var1 == 3) {
         this.str = "日产2016款新逍客高配（手动空调）";
      }

      if (var1 == 4) {
         this.str = "日产2013款新天籁高配（自动空调）";
      }

      if (var1 == 5) {
         this.str = "日产2013款新天籁高配（手动空调）";
      }

      if (var1 == 6) {
         this.str = "日产2008-2012款天籁高配（自动空调）";
      }

      if (var1 == 7) {
         this.str = "日产2008-2012款天籁高配（手动空调）";
      }

      if (var1 == 8) {
         this.str = "日产2011-2016款新骐达高配（自动空调）";
      }

      if (var1 == 9) {
         this.str = "日产2011-2016款新骐达高配（手动空调）";
      }

      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_1_0"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_1_0", "_353_setting_1_0"), this.str)).setValue(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setEmergencysignal0x91() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_2", "_353_setting_2_0"), this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setFrontRadarInfo0x23() {
      if (!this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = false;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setFuelConsumptionMileageInfo0x71() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "nissan_raise_mileage_title");
      StringBuilder var5 = new StringBuilder();
      DecimalFormat var6 = this.mDecimalFormat00;
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(var6.format((long)this.getMsbLsbResult(var4[3], var4[2]))).append(" KM").toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "Average_fuel_consumption");
      StringBuilder var7 = new StringBuilder();
      var6 = this.mDecimalFormat0p0;
      int[] var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var7.append(var6.format((double)this.getMsbLsbResult(var8[5], var8[4]) * 0.1)).append(" L/100KM").toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "total_mileage");
      var7 = new StringBuilder();
      DecimalFormat var9 = this.mDecimalFormat00;
      int[] var10 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var7.append(var9.format((long)this.getData(var10[6], var10[7], var10[8]))).append(" KM").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setPanelKeyInfo0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 7) {
                        if (var1 != 135) {
                           if (var1 != 9) {
                              if (var1 != 10) {
                                 if (var1 != 21) {
                                    if (var1 != 22) {
                                       switch (var1) {
                                          case 96:
                                             this.buttonKey(45);
                                             break;
                                          case 97:
                                             this.buttonKey(33);
                                             break;
                                          case 98:
                                             this.buttonKey(48);
                                             break;
                                          case 99:
                                             this.buttonKey(34);
                                             break;
                                          case 100:
                                             this.buttonKey(46);
                                             break;
                                          case 101:
                                             this.buttonKey(35);
                                             break;
                                          case 102:
                                             this.buttonKey(47);
                                             break;
                                          case 103:
                                             this.buttonKey(36);
                                             break;
                                          default:
                                             switch (var1) {
                                                case 112:
                                                   this.buttonKey(49);
                                                   break;
                                                case 113:
                                                   this.buttonKey(50);
                                                   break;
                                                case 114:
                                                   this.buttonKey(128);
                                                   break;
                                                case 115:
                                                   this.buttonKey(151);
                                                   break;
                                                case 116:
                                                   this.buttonKey(205);
                                                   break;
                                                case 117:
                                                   this.buttonKey(205);
                                             }
                                       }
                                    } else {
                                       this.buttonKey(49);
                                    }
                                 } else {
                                    this.buttonKey(50);
                                 }
                              } else {
                                 this.buttonKey(15);
                              }
                           } else {
                              this.buttonKey(14);
                           }
                        } else {
                           this.buttonKey(1);
                        }
                     } else {
                        this.buttonKey(2);
                     }
                  } else {
                     this.buttonKey(46);
                  }
               } else {
                  this.buttonKey(45);
               }
            } else {
               this.buttonKey(8);
            }
         } else {
            this.buttonKey(7);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void setRearRadarInfo0x22() {
      if (!this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = false;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setRotatingSpeedInfo0x6B() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
      StringBuilder var5 = new StringBuilder();
      DecimalFormat var4 = this.mDecimalFormat00;
      int[] var6 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append(var4.format((long)this.getData(var6[2], var6[3]))).append("rpm").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSettingFeedback0xD0() {
   }

   private void setSpeedInfo0x6A() {
      ArrayList var4 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
      StringBuilder var5 = new StringBuilder();
      DecimalFormat var6 = this.mDecimalFormat00;
      int[] var3 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var2, var1, var5.append(var6.format((long)this.getData(var3[2], var3[3]))).append(" km/h").toString()));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      var3 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var3[3], var3[2]));
   }

   private void setTrackData0x29() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4768, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKeyInfo0x22() {
      switch (this.mCanBusInfoInt[2]) {
         case 1:
            this.buttonKey0x2F(77);
            break;
         case 2:
            this.buttonKey0x2F(76);
            break;
         case 3:
            this.buttonKey0x2F(76);
            break;
         case 4:
            this.buttonKey0x2F(130);
            break;
         case 5:
            this.buttonKey0x2F(139);
            break;
         case 6:
            this.buttonKey0x2F(139);
            break;
         case 7:
            this.buttonKey0x2F(140);
            break;
         case 8:
            this.buttonKey0x2F(141);
            break;
         case 9:
            this.buttonKey0x2F(76);
            break;
         case 10:
            this.buttonKey0x2F(139);
            break;
         case 11:
            this.buttonKey0x2F(143);
            break;
         case 12:
            this.buttonKey0x2F(143);
            break;
         case 13:
            this.buttonKey0x2F(144);
            break;
         case 14:
            this.buttonKey0x2F(142);
            break;
         case 15:
            this.buttonKey0x2F(145);
            break;
         case 16:
            this.buttonKey0x2F(146);
            break;
         case 17:
            this.buttonKey0x2F(467);
            break;
         case 18:
            this.buttonKey0x2F(468);
      }

   }

   private void startTimer(long var1, int var3) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1, (long)var3);
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).sendSourceInfo(7, 48);
      this.getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getUiMgr(this.mContext).sendSourceInfo(11, 34);
      this.getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      this.getUiMgr(this.mContext).sendSourceInfo(0, 0);
      this.getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.comingPhoneNumber = null;
      this.OutGoingPhoneNumber = null;
      this.talkingPhoneNumber = null;
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            (new CountDownTimer(this, 2000L, 500L) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onFinish() {
                  this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPhoneNumber();
               }

               public void onTick(long var1) {
                  this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPhoneNumber();
               }
            }).start();
         }
      });
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      int[] var4 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var4, this.comingPhoneNumber)) {
         this.comingPhoneNumber = var4;
         this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 1, 1}, var1));
      }

   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);

      for(int var4 = 0; var4 < var1.length; ++var4) {
      }

      int[] var5 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var5, this.OutGoingPhoneNumber)) {
         this.OutGoingPhoneNumber = var5;
         this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 2, 1}, var1));
      }

   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      var4 = this.eachId;
      if (var4 == 1 || var4 == 2 || var4 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(5, 64);
      }

      int[] var5 = this.getByteArrayToIntArray(var1);
      if (!Arrays.equals(var5, this.talkingPhoneNumber)) {
         this.talkingPhoneNumber = var5;
         this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -59, 4, 1}, var1));
      }

   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void buttonKey0x2F(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 41) {
         if (var3 != 96) {
            if (var3 != 104) {
               if (var3 != 106) {
                  if (var3 != 145) {
                     if (var3 != 147) {
                        if (var3 != 208) {
                           if (var3 != 47) {
                              if (var3 != 48) {
                                 if (var3 != 112) {
                                    if (var3 != 113) {
                                       switch (var3) {
                                          case 32:
                                             this.setPanelKeyInfo0x20();
                                             break;
                                          case 33:
                                             this.setAirData0x21();
                                             break;
                                          case 34:
                                             this.setRearRadarInfo0x22();
                                             break;
                                          case 35:
                                             this.setFrontRadarInfo0x23();
                                             break;
                                          case 36:
                                             this.setDoorData0x24();
                                       }
                                    } else {
                                       this.setFuelConsumptionMileageInfo0x71();
                                    }
                                 } else {
                                    this.serTireInfo0x70();
                                 }
                              } else {
                                 this.setVersionInfo0x30();
                              }
                           } else {
                              this.setWheelKeyInfo0x22();
                           }
                        } else {
                           this.setSettingFeedback0xD0();
                        }
                     } else {
                        this.setCarSettingInfo0x93();
                     }
                  } else {
                     this.setEmergencysignal0x91();
                  }
               } else {
                  this.setSpeedInfo0x6A();
               }
            } else {
               this.setRotatingSpeedInfo0x6B();
            }
         } else {
            this.setCartypeInfo0x60();
         }
      } else {
         this.setTrackData0x29();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
      if (var11) {
         var1 = 128;
      } else {
         var1 = 0;
      }

      if (var10) {
         var5 = var8;
      } else {
         var1 |= 64;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var5, (byte)var6, (byte)var1});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).sendSourceInfo(2, 33);
      this.getUiMgr(this.mContext).sendMediaPalyInfo(var5, var6, 0, 0, var2 / 60, var2 % 60);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initAmplifierData(this.mContext);
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.getUiMgr(this.mContext).sendSourceInfo(0, 0);
      this.getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.getUiMgr(this.mContext).sendSourceInfo(8, 17);
      this.getUiMgr(this.mContext).sendMediaPalyInfo(this.getLsb(var4), this.getMsb(var4), this.getLsb(var3), this.getMsb(var3), var6, var7);
   }

   public void radioDestroy() {
      super.radioDestroy();
      this.getUiMgr(this.mContext).sendSourceInfo(0, 0);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = this.eachId;
      if (var5 == 1 || var5 == 2 || var5 == 9) {
         this.getUiMgr(this.mContext).sendSourceInfo(1, 1);
      }

      var5 = this.eachId;
      if (var5 == 1 || var5 == 2) {
         this.getUiMgr(this.mContext).sendRadioInfo(this.getBandAmFM(var2), this.getFreqLsb(var2, var3), this.getFreqMsb(var2, var3), var1);
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
