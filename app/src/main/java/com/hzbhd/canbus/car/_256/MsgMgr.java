package com.hzbhd.canbus.car._256;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final int SHARE_256_AMP_BAS_TRE_OFFSET = 10;
   static final int SHARE_256_AMP_FAD_BAL_OFFSET = 16;
   private boolean ac;
   private boolean auto;
   private boolean eco;
   private boolean fornt_left_blow_foot;
   private boolean fornt_left_blow_head;
   private boolean fornt_right_blow_foot;
   private boolean fornt_right_blow_head;
   private boolean front_defog;
   private String front_left_temperature = "";
   private String front_right_temperature = "";
   private int front_wind_level;
   private boolean in_out_cycle;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private Context mContext;
   private int mDiscExsit;
   private int mDiscRadom;
   private int mDiscRepeat;
   private boolean mIsFirstTime = true;
   private int mRadioSt;
   private int mSdCardIn;
   private TimerTask mTimeTask;
   private Timer mTimer;
   private boolean rear_defog;

   private void bluetooth0x11() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 == 1) {
            this.enterAuxIn2();
         }
      } else {
         this.exitAuxIn2();
      }

   }

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

   private int getIntByBoolan(boolean var1) {
      return var1 ? 1 : 0;
   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -3, 7, (byte)GeneralAmplifierData.volume});
      byte[] var6 = new byte[]{22, -3, 1, (byte)(GeneralAmplifierData.frontRear + 16)};
      byte var2 = (byte)(GeneralAmplifierData.leftRight + 16);
      byte[] var4 = new byte[]{22, -3, 4, (byte)(GeneralAmplifierData.bandBass + 10)};
      byte[] var5 = new byte[]{22, -3, 5, (byte)(GeneralAmplifierData.bandTreble + 10)};
      TimerUtil var3 = new TimerUtil(this);
      var3.startTimer(new TimerTask(this, new byte[][]{var6, {22, -3, 2, var2}, var4, var5}, var3) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$amplifierData;
         final TimerUtil val$timerUtil;

         {
            this.this$0 = var1;
            this.val$amplifierData = var2;
            this.val$timerUtil = var3;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$amplifierData;
            if (var1 < var2.length) {
               this.i = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.val$timerUtil.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private boolean isAirMsgChange() {
      if (!this.front_left_temperature.equals(GeneralAirData.front_left_temperature)) {
         return true;
      } else if (!this.front_right_temperature.equals(GeneralAirData.front_right_temperature)) {
         return true;
      } else if (this.in_out_cycle != GeneralAirData.in_out_cycle) {
         return true;
      } else if (this.front_defog != GeneralAirData.front_defog) {
         return true;
      } else if (this.rear_defog != GeneralAirData.rear_defog) {
         return true;
      } else if (this.fornt_left_blow_foot != GeneralAirData.front_left_blow_foot) {
         return true;
      } else if (this.fornt_right_blow_foot != GeneralAirData.front_right_blow_foot) {
         return true;
      } else if (this.fornt_left_blow_head != GeneralAirData.front_left_blow_head) {
         return true;
      } else if (this.fornt_right_blow_head != GeneralAirData.front_right_blow_head) {
         return true;
      } else if (this.auto != GeneralAirData.auto) {
         return true;
      } else if (this.eco != GeneralAirData.eco) {
         return true;
      } else if (this.ac != GeneralAirData.ac) {
         return true;
      } else {
         return this.front_wind_level != GeneralAirData.front_wind_level;
      }
   }

   private boolean isDeviceMsgChange(int var1, int var2, int var3, int var4, int var5) {
      if (this.mDiscRadom != var1) {
         return true;
      } else if (this.mDiscRepeat != var2) {
         return true;
      } else if (this.mRadioSt != var3) {
         return true;
      } else if (this.mSdCardIn != var4) {
         return true;
      } else {
         return this.mDiscExsit != var5;
      }
   }

   private void keyControl0x12() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 48) {
         if (var1 != 49) {
            switch (var1) {
               case 1:
                  this.realKeyClick(75);
                  break;
               case 2:
                  this.realKeyClick(129);
                  break;
               case 3:
                  this.realKeyClick(52);
                  break;
               case 4:
                  this.realKeyClick(30);
                  break;
               case 5:
                  this.realKeyClick(4);
                  break;
               case 6:
                  this.realKeyClick(31);
                  break;
               case 7:
                  this.realKeyClick(134);
                  break;
               default:
                  switch (var1) {
                     case 17:
                        this.realKeyClick(2);
                        break;
                     case 18:
                        this.realKeyClick(48);
                        break;
                     case 19:
                        this.realKeyClick(47);
                        break;
                     case 20:
                        this.realKeyClick(7);
                        break;
                     case 21:
                        this.realKeyClick(8);
                        break;
                     case 22:
                        this.realKeyClick(3);
                        break;
                     case 23:
                        this.realKeyClick(206);
                        break;
                     case 24:
                        this.realKeyClick(207);
                  }
            }
         } else {
            this.realKeyClick(15);
         }
      } else {
         this.realKeyClick(14);
      }

   }

   private void realKeyClick(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private void reportCanbusInfo(String var1, String var2) {
      byte[] var3 = Util.makeMediaInfoCenteredInBytes(15, var1).getBytes();
      var3 = Util.byteMerger(new byte[]{22, 7}, var3);
      this.sendMediaMsg(this.mContext, var2, var3);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (254 == var1) {
         var2 = this.mContext.getString(2131769395);
      } else if (1 <= var1 && var1 <= 254) {
         var2 = var1 + "." + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int[] var6 = this.mCanBusInfoInt;
      int var5 = var6[2];
      double var1;
      if (var5 < 0) {
         var1 = (double)(var5 - 100);
         return var1 + "." + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + this.getTempUnitC(this.mContext);
      } else {
         double var3 = (double)(var5 - 100);
         var1 = (double)DataHandleUtils.getIntFromByteWithBit(var6[5], 0, 4);
         return var3 + var1 * 0.1 + this.getTempUnitC(this.mContext);
      }
   }

   private void setAirData0x10(int[] var1) {
      if (DataHandleUtils.getBoolBit7(var1[4])) {
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      } else {
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(var1[2]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(var1[2]);
      }

      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit7(var1[3]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(var1[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(var1[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(var1[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(var1[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit3(var1[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit3(var1[3]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit2(var1[3]);
      GeneralAirData.eco = DataHandleUtils.getBoolBit1(var1[3]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit0(var1[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4);
      if (this.isAirMsgChange()) {
         this.front_left_temperature = GeneralAirData.front_left_temperature;
         this.front_right_temperature = GeneralAirData.front_right_temperature;
         this.in_out_cycle = GeneralAirData.in_out_cycle;
         this.front_defog = GeneralAirData.front_defog;
         this.rear_defog = GeneralAirData.rear_defog;
         this.fornt_left_blow_foot = GeneralAirData.front_left_blow_foot;
         this.fornt_right_blow_foot = GeneralAirData.front_right_blow_foot;
         this.fornt_left_blow_head = GeneralAirData.front_left_blow_head;
         this.fornt_right_blow_head = GeneralAirData.front_right_blow_head;
         this.auto = GeneralAirData.auto;
         this.eco = GeneralAirData.eco;
         this.ac = GeneralAirData.ac;
         this.front_wind_level = GeneralAirData.front_wind_level;
         if (this.mIsFirstTime) {
            this.mIsFirstTime = false;
            return;
         }

         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setAmplifier() {
      GeneralAmplifierData.frontRear = this.mCanBusInfoInt[2] - 16;
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 16;
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[4] - 10;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[5] - 10;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[6];
      this.saveAmplifierData(this.mContext, this.mCanId);
      this.updateAmplifierActivity((Bundle)null);
   }

   private void setRadar() {
      int var1 = this.mCanBusInfoInt[2];
      int[] var2;
      if (var1 == 0) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = this.mCanBusInfoInt[3];
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(var2[3], var2[4] + 1, var2[5] + 1, var2[6] + 1, var2[7] + 1);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         this.updateParkUi((Bundle)null, this.mContext);
      } else if (var1 == 1) {
         GeneralParkData.isShowDistanceNotShowLocationUi = true;
         GeneralParkData.radar_distance_disable = new int[]{255};
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarDistanceData(var2[4], var2[5], var2[6], var2[7]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSetting0x31() {
      int var1 = this.getIntByBoolan(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]));
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrack() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 7744, 13424, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void startReportDeviceInfo() {
      (new TimerUtil(this)).startTimer(new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, 6, UiMgr.m0x06Byte});
         }
      }, 0L, 1500L);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.reportCanbusInfo("BT MUSIC", SourceConstantsDef.SOURCE_ID.BTAUDIO.name());
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 41) {
         if (var3 != 49) {
            if (var3 != 50) {
               switch (var3) {
                  case 16:
                     if (this.isAirMsgRepeat(var2)) {
                        return;
                     }

                     this.setAirData0x10(this.mCanBusInfoInt);
                     break;
                  case 17:
                     this.bluetooth0x11();
                     break;
                  case 18:
                     this.keyControl0x12();
               }
            } else {
               this.setRadar();
            }
         } else {
            this.setSetting0x31();
            this.setAmplifier();
         }
      } else {
         this.setTrack();
      }

   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super.deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      if (this.isDeviceMsgChange(var2, var3, var7, var5, var4)) {
         this.mDiscRadom = var2;
         this.mDiscRepeat = var3;
         this.mRadioSt = var7;
         this.mSdCardIn = var5;
         this.mDiscExsit = var4;
         byte var15 = UiMgr.m0x06Byte;
         boolean var14 = true;
         boolean var13;
         if (var2 == 1) {
            var13 = true;
         } else {
            var13 = false;
         }

         UiMgr.m0x06Byte = (byte)DataHandleUtils.setIntByteWithBit(var15, 4, var13);
         var15 = UiMgr.m0x06Byte;
         if (var3 == 1) {
            var13 = true;
         } else {
            var13 = false;
         }

         UiMgr.m0x06Byte = (byte)DataHandleUtils.setIntByteWithBit(var15, 3, var13);
         var15 = UiMgr.m0x06Byte;
         if (var7 == 1) {
            var13 = true;
         } else {
            var13 = false;
         }

         UiMgr.m0x06Byte = (byte)DataHandleUtils.setIntByteWithBit(var15, 2, var13);
         var15 = UiMgr.m0x06Byte;
         if (var5 == 1) {
            var13 = true;
         } else {
            var13 = false;
         }

         UiMgr.m0x06Byte = (byte)DataHandleUtils.setIntByteWithBit(var15, 1, var13);
         var15 = UiMgr.m0x06Byte;
         if (var4 == 1) {
            var13 = var14;
         } else {
            var13 = false;
         }

         UiMgr.m0x06Byte = (byte)DataHandleUtils.setIntByteWithBit(var15, 0, var13);
      }
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var10 == 240) {
         this.sendDiscEjectMsg(this.mContext);
      } else if (var10 == 32) {
         this.reportCanbusInfo("DISC", SourceConstantsDef.SOURCE_ID.MPEG.name());
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.initAmplifier(var1);
      this.startReportDeviceInfo();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.reportCanbusInfo("MUSIC", SourceConstantsDef.SOURCE_ID.MUSIC.name());
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.reportCanbusInfo(var2 + " " + var3 + this.getBandUnit(var2), SourceConstantsDef.SOURCE_ID.FM.name());
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         this.reportCanbusInfo(" ", SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name());
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
      this.reportCanbusInfo("VIDEO", SourceConstantsDef.SOURCE_ID.VIDEO.name());
   }

   private class TimerUtil {
      private Timer mTimer;
      private TimerTask mTimerTask;
      final MsgMgr this$0;

      private TimerUtil(MsgMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      TimerUtil(MsgMgr var1, Object var2) {
         this(var1);
      }

      public void startTimer(TimerTask var1, long var2, long var4) {
         Log.i("TimerUtil", "startTimer: " + this);
         if (var1 != null) {
            this.mTimerTask = var1;
            if (this.mTimer == null) {
               this.mTimer = new Timer();
            }

            this.mTimer.schedule(this.mTimerTask, var2, var4);
         }
      }

      public void stopTimer() {
         Log.i("TimerUtil", "stopTimer: " + this);
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
   }
}
