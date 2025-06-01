package com.hzbhd.canbus.car._438;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car._438.impl.MsAbstractMsgMgr;
import com.hzbhd.canbus.car._438.speech.ISysRxListener;
import com.hzbhd.canbus.car._438.speech.SRxData;
import com.hzbhd.canbus.car._438.speech.STxData;
import com.hzbhd.canbus.car._438.speech.SysRxSpeechReceiver;
import com.hzbhd.canbus.car._438.speech.SysToSpeechReceiver;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Arrays;

public class MsgMgr extends MsAbstractMsgMgr {
   private static SysToSpeechReceiver.AcCtrl sa;
   private static SysToSpeechReceiver.CarCtrl sc;
   private static SysToSpeechReceiver sysToSpeechReceiver;
   private final String PATH_VOICE = "voice/";
   private SparseArray beepVoice;
   private boolean handsTag = false;
   int[] mAirData;
   int[] mCanBusInfoInt;
   Context mContext;
   int[] mLightData;
   private UiMgr mUiMgr;
   private int nowRadarData11 = 0;
   private int nowRadarData12 = 0;
   private SparseArray speechVoice;
   private VoiceManger voiceManger;

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initRadarVoice() {
      this.voiceManger = new VoiceManger();
      SparseArray var1 = new SparseArray();
      this.speechVoice = var1;
      var1.put(1, "_434_speech_0x01_stop.mp3");
      this.speechVoice.put(2, "_434_speech_0x02_0x03_0x04_0x07.mp3");
      this.speechVoice.put(3, "_434_speech_0x02_0x03_0x04_0x07.mp3");
      this.speechVoice.put(4, "_434_speech_0x02_0x03_0x04_0x07.mp3");
      this.speechVoice.put(5, "_434_speech_0x05_3second.mp3");
      this.speechVoice.put(6, "_434_speech_0x06_1second.mp3");
      this.speechVoice.put(7, "_434_speech_0x02_0x03_0x04_0x07.mp3");
      var1 = new SparseArray();
      this.beepVoice = var1;
      var1.put(1, "_434_beep_0x01_long.mp3");
      this.beepVoice.put(2, "_434_beep_0x02_6hz.mp3");
      this.beepVoice.put(3, "_434_beep_0x03_4hz.mp3");
      this.beepVoice.put(4, "_434_beep_0x04_2hz.mp3");
      this.beepVoice.put(5, "_434_speech_0x05_3second.mp3");
      this.beepVoice.put(6, "_434_speech_0x06_1second.mp3");
      this.beepVoice.put(7, "_434_beep_0x07_6beep.mp3");
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return false;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return true;
      }
   }

   private boolean isLightDataChange() {
      if (Arrays.equals(this.mLightData, this.mCanBusInfoInt)) {
         return false;
      } else {
         this.mLightData = this.mCanBusInfoInt;
         return true;
      }
   }

   private void registerSanYiVoiceReceiver(Context var1) {
      SanYiVoiceControl var2 = new SanYiVoiceControl(this);
      SysRxSpeechReceiver var4 = new SysRxSpeechReceiver(this.mContext, var2);
      IntentFilter var3 = new IntentFilter();
      var3.addAction("com.hzbhd.speech.to.sys");
      var1.registerReceiver(var4, var3);
   }

   private void sendAirInfoToSpeech(byte[] var1) {
      sa.sendTemp(var1[11] / 2);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[8], 3, 3);
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
                  sa.sendMode(SRxData.Ac.Mode.ModeEnum.footdefrost);
               }
            } else {
               sa.sendMode(SRxData.Ac.Mode.ModeEnum.foot);
            }
         } else {
            sa.sendMode(SRxData.Ac.Mode.ModeEnum.facefoot);
         }
      } else {
         sa.sendMode(SRxData.Ac.Mode.ModeEnum.face);
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1[8], 3, 3) == 5) {
         sa.sendDefrost(true);
      } else {
         sa.sendDefrost(false);
      }

      sa.sendWind(DataHandleUtils.getIntFromByteWithBit(var1[9], 4, 4));
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 1, 2) == 0) {
         sa.sendCold(false);
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[7], 1, 2) == 1) {
         sa.sendCold(true);
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 1) == 0) {
         sa.sendAir(false);
      } else {
         sa.sendAir(true);
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1[8], 7, 1) == 0) {
         sa.sendLoop(true);
      } else {
         sa.sendLoop(false);
      }

   }

   private void sendLightInfoToSheech(int[] var1) {
      int var2 = this.getMsDataType(this.mCanBusInfoInt);
      if (var2 != 419315745) {
         if (var2 != 419316001) {
            if (var2 == 419367713) {
               if (DataHandleUtils.getBoolBit2(var1[10])) {
                  sc.sendWiper(true);
               } else {
                  sc.sendWiper(true);
               }
            }
         } else {
            if (DataHandleUtils.getBoolBit4(var1[7])) {
               sc.sendHeadlight(true);
            } else {
               sc.sendHeadlight(false);
            }

            if (DataHandleUtils.getBoolBit6(var1[7])) {
               sc.sendHighbeam(true);
            } else {
               sc.sendHighbeam(false);
            }
         }
      } else if (DataHandleUtils.getBoolBit2(var1[7])) {
         sc.sendAlarmLight(true);
      } else {
         sc.sendAlarmLight(false);
      }

   }

   private void setRadarVoice(Context var1, int var2) {
      synchronized(this){}
      if (var2 != 0) {
         try {
            if (TextUtils.isEmpty((CharSequence)this.speechVoice.get(var2))) {
               return;
            }

            this.requestVoiceSource();
            this.voiceManger.setVolume(Integer.parseInt(System.getString(this.mContext.getContentResolver(), "SHARE_MS_RADAR_VOLUME")));
            VoiceManger var3;
            StringBuilder var4;
            if (System.getString(this.mContext.getContentResolver(), "SHARE_MS_BEEP_SPEECH").equals("BEEP")) {
               var3 = this.voiceManger;
               var4 = new StringBuilder();
               var3.play(var1, var4.append("voice/").append((String)this.beepVoice.get(var2)).toString());
            } else {
               var3 = this.voiceManger;
               var4 = new StringBuilder();
               var3.play(var1, var4.append("voice/").append((String)this.speechVoice.get(var2)).toString());
            }
         } finally {
            ;
         }

      }
   }

   private void setRadarVoiceData(Context var1) {
      int var2 = this.nowRadarData11;
      int var3 = this.mCanBusInfoInt[13];
      if (var2 != var3) {
         this.nowRadarData11 = var3;
         this.setRadarVoice(var1, DataHandleUtils.getIntFromByteWithBit(var3, 5, 3));
      }

      var2 = this.nowRadarData12;
      var3 = this.mCanBusInfoInt[14];
      if (var2 != var3) {
         this.nowRadarData12 = var3;
         this.setRadarVoice(var1, DataHandleUtils.getIntFromByteWithBit(var3, 0, 3));
      }

   }

   private void toMute(int var1) {
      if (var1 == 1) {
         if (!this.isMute()) {
            this.realKeyLongClick1(this.mContext, 3, 1);
            this.realKeyLongClick1(this.mContext, 3, 0);
         }
      } else if (var1 == 0 && this.isMute()) {
         this.realKeyLongClick1(this.mContext, 3, 1);
         this.realKeyLongClick1(this.mContext, 3, 0);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.getUiMgr(var1).registerCanBusAirControlListener();
      this.getUiMgr(var1).registerCanBusBasicControlListener();
      this.initRadarVoice();
      this.registerSanYiVoiceReceiver(var1);
      SysToSpeechReceiver var2 = new SysToSpeechReceiver(var1);
      sysToSpeechReceiver = var2;
      sa = var2.getAc();
      sc = sysToSpeechReceiver.getCar();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mContext = var1;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      if (this.getMsDataType(var3) == 419404313) {
         this.SharedAirData(this.mCanBusInfoInt);
         if (this.handsTag && this.isAirDataChange()) {
            this.sendAirInfoToSpeech(var2);
         }
      } else if (this.getMsDataType(this.mCanBusInfoInt) == 418322406) {
         this.showRadarView();
         this.setRadarVoiceData(var1);
         this.ShareBasicInfo(this.mCanBusInfoInt);
      } else if (this.getMsDataType(this.mCanBusInfoInt) == 419366352) {
         this.toMute(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2));
      } else {
         if (this.getMsDataType(this.mCanBusInfoInt) == 419427075) {
            this.updateSpeedInfo(this.mCanBusInfoInt[10]);
         }

         this.ShareBasicInfo(this.mCanBusInfoInt);
         if (this.isLightDataChange()) {
            this.sendLightInfoToSheech(this.mCanBusInfoInt);
         }
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.updateVersionInfo(var1, "CANBOX-V1.0.0");
      System.putInt(var1.getContentResolver(), "share.out.of.misc.turn.left.or.right", 0);
   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
   }

   public void onHandshake(Context var1) {
      this.getUiMgr(var1).hand("MysMgr");
   }

   public void showRadarView() {
      try {
         if (System.getString(this.mContext.getContentResolver(), "SHARE_MS_RADAR_SETTING_SHOW_RADAR_SWITCH") != null && System.getString(this.mContext.getContentResolver(), "SHARE_MS_RADAR_SETTING_SHOW_RADAR_SWITCH").equals("ON")) {
            ComponentName var1 = ((ActivityManager.RunningTaskInfo)((ActivityManager)this.mContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity;
            if (!var1.getClassName().trim().equals("com.hzbhd.ui.config.ui_l9.activity.MsRadarActivity") && !var1.getClassName().equals("com.hzbhd.ui.activity.VideoActivity")) {
               this.startOtherActivity(this.mContext, HzbhdComponentName.MsRadarActivity);
            }
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   private class SanYiVoiceControl implements ISysRxListener {
      final MsgMgr this$0;

      private SanYiVoiceControl(MsgMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      SanYiVoiceControl(MsgMgr var1, Object var2) {
         this(var1);
      }

      public void AcAir(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, -128, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, -128, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, -128, 0, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 64, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 64, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 64, 0, 0, 0, 1});
         }

      }

      public void AcAuto(boolean var1) {
      }

      public void AcCold(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 2, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 2, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 2, 0, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 1, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 1, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 1, 0, 0, 0, 1});
         }

      }

      public void AcDefrost(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 32, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 32, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 32, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 16, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 16, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 16, 0, 0, 1});
         }

      }

      public void AcLoop(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, -128, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, -128, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, -128, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 64, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 64, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 64, 0, 0, 1});
         }

      }

      public void AcMode(STxData.AcCtrl.Mode.ModeEnum var1) {
         if (var1.equals(STxData.AcCtrl.Mode.ModeEnum.face)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 8, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 8, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 8, 0, 0, 0, 1});
         }

         if (var1.equals(STxData.AcCtrl.Mode.ModeEnum.foot)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 24, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 24, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 24, 0, 0, 0, 1});
         }

         if (var1.equals(STxData.AcCtrl.Mode.ModeEnum.footdefrost)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 32, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 32, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 32, 0, 0, 0, 1});
         }

         if (var1.equals(STxData.AcCtrl.Mode.ModeEnum.facefoot)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 16, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 16, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 16, 0, 0, 0, 1});
         }

      }

      public void AcTempDec(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 64, 0, 0, 0, 0, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 64, 0, 0, 0, 0, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 64, 0, 0, 0, 0, 1});
      }

      public void AcTempInc(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, -128, 0, 0, 0, 0, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, -128, 0, 0, 0, 0, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, -128, 0, 0, 0, 0, 1});
      }

      public void AcTempTo(int var1) {
         byte var2 = (byte)(var1 * 2);
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, 0, var2, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, 0, var2, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, 0, var2, 1});
      }

      public void AcWarm(boolean var1) {
      }

      public void AcWind(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 32, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 32, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 32, 0, 0, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 16, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 16, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 16, 0, 0, 0, 0, 1});
         }

      }

      public void AcWindTo(int var1) {
         byte var2 = (byte)var1;
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, var2, 0, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, var2, 0, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, var2, 0, 1});
      }

      public void CarAlarmlight(boolean var1) {
         if (!var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 127, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 127, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 127, -1, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarClearancelamps(boolean var1) {
      }

      public void CarHeadlight(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarHighBeam(boolean var1) {
         if (!var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarLock(boolean var1) {
         if (!var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, 127, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, 127, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, 127, -1, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -65, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -65, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -65, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarReadinglamp(boolean var1) {
      }

      public void CarToplight(boolean var1) {
      }

      public void CarWindowClose() {
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
      }

      public void CarWindowOpen(STxData.CarCtrl.WindowOpen.TypeEnum var1) {
         if (var1.equals(STxData.CarCtrl.WindowOpen.TypeEnum.leftfront)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
         }

         if (var1.equals(STxData.CarCtrl.WindowOpen.TypeEnum.rightfront)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarWiper(boolean var1) {
         if (!var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -8, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -8, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -8, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -6, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -6, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -6, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum var1) {
      }

      public void CarWiperMove(int var1) {
      }

      public void SystemSync() {
         this.this$0.handsTag = true;
      }
   }
}
