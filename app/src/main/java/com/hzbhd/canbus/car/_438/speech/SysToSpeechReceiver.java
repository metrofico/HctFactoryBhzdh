package com.hzbhd.canbus.car._438.speech;

import android.content.Context;
import android.content.Intent;

public class SysToSpeechReceiver {
   private AcCtrl acCtrl = null;
   private CarCtrl carCtrl = null;
   private Context mContext;
   private Intent mIntent = null;
   private SpeechCtrl speechCtrl = null;

   public SysToSpeechReceiver(Context var1) {
      this.mContext = var1;
   }

   private void sendSeech(Intent var1) {
      this.mContext.sendBroadcast(var1);
   }

   public AcCtrl getAc() {
      if (this.acCtrl == null) {
         this.acCtrl = new AcCtrl(this);
      }

      return this.acCtrl;
   }

   public CarCtrl getCar() {
      if (this.carCtrl == null) {
         this.carCtrl = new CarCtrl(this);
      }

      return this.carCtrl;
   }

   public SpeechCtrl getSpeech() {
      if (this.speechCtrl == null) {
         this.speechCtrl = new SpeechCtrl(this);
      }

      return this.speechCtrl;
   }

   public class AcCtrl {
      final SysToSpeechReceiver this$0;

      public AcCtrl(SysToSpeechReceiver var1) {
         this.this$0 = var1;
      }

      public void sendAir(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "ac.air.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "air");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendAuto(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "ac.auto.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "auto");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendCold(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "ac.cold.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "cold");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendDefrost(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "ac.defrost.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "defrost");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendLoop(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "ac.loop");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "loop");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendMode(SRxData.Ac.Mode.ModeEnum var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "ac.mode");
         if (var1.ordinal() == SRxData.Ac.Mode.ModeEnum.face.ordinal()) {
            this.this$0.mIntent.putExtra("mode", "face");
         } else if (var1.ordinal() == SRxData.Ac.Mode.ModeEnum.foot.ordinal()) {
            this.this$0.mIntent.putExtra("mode", "foot");
         } else if (var1.ordinal() == SRxData.Ac.Mode.ModeEnum.facefoot.ordinal()) {
            this.this$0.mIntent.putExtra("mode", "face.foot");
         } else if (var1.ordinal() == SRxData.Ac.Mode.ModeEnum.footdefrost.ordinal()) {
            this.this$0.mIntent.putExtra("mode", "foot.defrost");
         } else {
            this.this$0.mIntent.putExtra("mode", "other");
         }

         this.this$0.mIntent.putExtra("rx_type", "mode");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendTemp(int var1) {
         try {
            SysToSpeechReceiver var3 = this.this$0;
            Intent var2 = new Intent("com.hzbhd.sys.to.speech");
            var3.mIntent = var2;
            this.this$0.mIntent.putExtra("action", "ac.temp.change");
            this.this$0.mIntent.putExtra("number", var1);
            this.this$0.mIntent.putExtra("rx_type", "temp");
            SysToSpeechReceiver var5 = this.this$0;
            var5.sendSeech(var5.mIntent);
         } catch (Exception var4) {
            var4.printStackTrace();
         }

      }

      public void sendWarm(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "ac.warm.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "warm");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendWind(int var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "ac.wind.change");
         this.this$0.mIntent.putExtra("number", var1);
         this.this$0.mIntent.putExtra("rx_type", "wind");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }
   }

   public class CarCtrl {
      final SysToSpeechReceiver this$0;

      public CarCtrl(SysToSpeechReceiver var1) {
         this.this$0 = var1;
      }

      public void sendAlarmLight(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.alarm.light.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "alarmlight");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendClearancelamps(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.clearance.lamps.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "clearancelamps");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendHeadlight(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.dipped.headlight.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "headlight");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendHighbeam(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.high.beam.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "highbeam");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendLock(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.lock.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "lock");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendReadinglamp(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.reading.lamp.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "readinglamp");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendSpeed(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.speed.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "speed");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendToplight(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.toplight.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "toplight");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendWindow(boolean var1, boolean var2) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.window.status");
         this.this$0.mIntent.putExtra("left.front", var1);
         this.this$0.mIntent.putExtra("right.front", var2);
         this.this$0.mIntent.putExtra("rx_type", "window");
         SysToSpeechReceiver var3 = this.this$0;
         var3.sendSeech(var3.mIntent);
      }

      public void sendWiper(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.wiper.status");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "wiper");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void sendWiperMode(boolean var1, boolean var2, boolean var3, boolean var4) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", "car.wiper.mode");
         this.this$0.mIntent.putExtra("intermittent", var1);
         this.this$0.mIntent.putExtra("speed.low", var2);
         this.this$0.mIntent.putExtra("speed.high", var3);
         this.this$0.mIntent.putExtra("wash", var4);
         this.this$0.mIntent.putExtra("rx_type", "wipermode");
         SysToSpeechReceiver var5 = this.this$0;
         var5.sendSeech(var5.mIntent);
      }
   }

   public class SpeechCtrl {
      final SysToSpeechReceiver this$0;

      public SpeechCtrl(SysToSpeechReceiver var1) {
         this.this$0 = var1;
      }

      public void openWindow(boolean var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("type", var1);
         this.this$0.mIntent.putExtra("rx_type", "speech.window");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }

      public void speak(String var1) {
         this.this$0.mIntent = new Intent("com.hzbhd.sys.to.speech");
         this.this$0.mIntent.putExtra("action", var1);
         this.this$0.mIntent.putExtra("rx_type", "speech.speak");
         SysToSpeechReceiver var2 = this.this$0;
         var2.sendSeech(var2.mIntent);
      }
   }
}
