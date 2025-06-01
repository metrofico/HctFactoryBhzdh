package com.hzbhd.canbus.car._122;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_122_AMPLIFIER_BALANCE = "share_122_amplifier_balance";
   static final String SHARE_122_AMPLIFIER_BASS = "share_122_amplifier_bass";
   static final String SHARE_122_AMPLIFIER_FADE = "share_122_amplifier_fade";
   static final String SHARE_122_AMPLIFIER_MIDDLE = "share_122_amplifier_middle";
   static final String SHARE_122_AMPLIFIER_TREBLE = "share_122_amplifier_treble";
   static final String SHARE_122_AMPLIFIER_VOLUME = "share_122_amplifier_volume";
   static final int _122_AMPLIFIER_OFFSET = 1;
   static final int _122_AMPLIFIER_RANGE = 9;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCarSpeedData;
   private Context mContext;
   private Timer mTimer;
   private TimerTask mTimerTask;

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

   private int getRadioBandData(String var1) {
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
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 2;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 3;
            }
      }

      switch (var2) {
         case 0:
            return 4;
         case 1:
            return 5;
         case 2:
            return 2;
         case 3:
            return 3;
         default:
            return 1;
      }
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "share_122_amplifier_volume", 0);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_122_amplifier_balance", 0);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_122_amplifier_fade", 0);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_122_amplifier_bass", 0);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "share_122_amplifier_middle", 0);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_122_amplifier_treble", 0);
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume});
      this.mTimerTask = new TimerTask(this, new byte[][]{{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 1 + 9)}, {22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 1 + 9)}, {22, -83, 4, (byte)(GeneralAmplifierData.bandBass + 1)}, {22, -83, 5, (byte)(GeneralAmplifierData.bandMiddle + 1)}, {22, -83, 6, (byte)(GeneralAmplifierData.bandTreble + 1)}}) {
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

   private void realKeyControl0x72() {
      switch (this.mCanBusInfoInt[4]) {
         case 0:
            this.realKeyLongClick2(0);
            break;
         case 1:
            this.realKeyLongClick2(7);
            break;
         case 2:
            this.realKeyLongClick2(8);
            break;
         case 3:
            this.realKeyLongClick2(3);
         case 4:
         case 10:
         default:
            break;
         case 5:
            this.realKeyLongClick2(14);
            break;
         case 6:
            this.realKeyLongClick2(15);
            break;
         case 7:
            this.realKeyLongClick2(187);
            break;
         case 8:
            this.realKeyLongClick2(46);
            break;
         case 9:
            this.realKeyLongClick2(45);
            break;
         case 11:
            this.realKeyLongClick2(2);
      }

      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[7], var1[6], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void realKeyLongClick2(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   private void saveAmplifierData() {
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_volume", GeneralAmplifierData.volume);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_treble", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_middle", GeneralAmplifierData.bandMiddle);
   }

   private void setAmplifierData0xA6() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.leftRight = this.mCanBusInfoByte[3] - 1 - 9;
      GeneralAmplifierData.frontRear = this.mCanBusInfoByte[4] - 1 - 9;
      GeneralAmplifierData.bandBass = this.mCanBusInfoByte[5] - 1;
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoByte[6] - 1;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[7] - 1;
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData();
   }

   private void setDoorData0x12() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setEnineSpeed() {
      ArrayList var3 = new ArrayList();
      StringBuilder var1 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 0, var1.append(var2[4] * 256 + var2[5]).append(" Rpm").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
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
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mContext = var1;
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte var1 = (byte)8;
      byte[] var2 = "            ".getBytes();
      var2 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var2);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), var2);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte var1 = (byte)12;
      byte[] var2 = "            ".getBytes();
      var2 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var2);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), var2);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      byte var4 = (byte)10;
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, var4}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      byte var4 = (byte)10;
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, var4}, var1));
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      byte var4 = (byte)10;
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, var4}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 18) {
         if (var3 != 50) {
            if (var3 != 114) {
               if (var3 != 166) {
                  if (var3 == 240) {
                     this.setVersionInfo0xF0();
                  }
               } else {
                  this.setAmplifierData0xA6();
               }
            } else {
               this.realKeyControl0x72();
               var3 = this.mCanBusInfoInt[3];
               if (var3 == 0) {
                  this.updateSpeedInfo(var3);
               }
            }
         } else {
            this.setEnineSpeed();
         }
      } else {
         this.setDoorData0x12();
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var14;
      if (var7 == 1) {
         var14 = 7;
      } else {
         var14 = 6;
      }

      if (var7 == 1) {
         var4 = var5;
      }

      var3 = DataHandleUtils.rangeNumber(var4, 0, 999);
      var11 = (new DecimalFormat("000")).format((long)var3) + "     ";
      var11 = var11 + "    ";
      var1 = (byte)var14;
      byte[] var15 = var11.getBytes();
      var15 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var15);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var15);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte var1 = (byte)8;
      byte[] var2 = "            ".getBytes();
      var2 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var2);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), var2);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifierData(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 14;
      } else {
         var25 = 13;
      }

      var3 = DataHandleUtils.rangeNumber(var9 * 256 + var3, 0, 999);
      var11 = (new DecimalFormat("000")).format((long)var3) + "     ";
      var11 = var11 + "    ";
      var1 = (byte)var25;
      byte[] var26 = var11.getBytes();
      var26 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var26);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = this.getRadioBandData(var2);
      if (var2.contains("FM")) {
         var2 = var3.substring(0, var3.length() - 1);
      } else {
         if (!var2.contains("AM")) {
            return;
         }

         var2 = var3 + " ";
      }

      for(var1 = var2.length(); var1 < 8; ++var1) {
         var2 = " " + var2;
      }

      var2 = var2 + "    ";
      byte var6 = (byte)var5;
      byte[] var7 = var2.getBytes();
      var7 = DataHandleUtils.byteMerger(new byte[]{22, -46, var6}, var7);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var7);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 14;
      } else {
         var18 = 13;
      }

      var3 = DataHandleUtils.rangeNumber(var9 * 256 + var3, 0, 999);
      var8 = (new DecimalFormat("000")).format((long)var3) + "     ";
      var8 = var8 + "    ";
      var1 = (byte)var18;
      byte[] var19 = var8.getBytes();
      var19 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var19);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var19);
   }
}
