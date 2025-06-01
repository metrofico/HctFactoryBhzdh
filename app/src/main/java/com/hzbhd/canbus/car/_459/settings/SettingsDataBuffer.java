package com.hzbhd.canbus.car._459.settings;

import android.os.CountDownTimer;
import com.hzbhd.proxy.share.ShareDataManager;

public class SettingsDataBuffer {
   private boolean bufferTag;
   private boolean cw;
   private boolean ld;
   private boolean sa;
   private boolean sps;
   private boolean swa;
   private CountDownTimer timer;
   private String vdm;

   private SettingsDataBuffer() {
      this.bufferTag = false;
      this.sps = false;
      this.sa = true;
      this.ld = true;
      this.cw = true;
      this.swa = false;
      this.vdm = "MID";
   }

   // $FF: synthetic method
   SettingsDataBuffer(Object var1) {
      this();
   }

   public static SettingsDataBuffer getInstance() {
      return SettingsDataBuffer.Holder.INSTANCE;
   }

   public void notifyOtherModule(String var1, Object var2) {
      ShareDataManager.getInstance().reportString("can.bus.all.data.share", "{\"TAG\":\"" + var1 + "\",\"VALUE\":\"" + var2 + "\"}");
   }

   public void resetTimer() {
      this.bufferTag = true;
      CountDownTimer var1 = this.timer;
      if (var1 != null) {
         var1.cancel();
         this.timer = null;
      }

      var1 = new CountDownTimer(this, 3000L, 1000L) {
         final SettingsDataBuffer this$0;

         {
            this.this$0 = var1;
         }

         public void onFinish() {
            this.this$0.bufferTag = false;
            SettingsDataBuffer var1 = this.this$0;
            var1.notifyOtherModule("SPS", var1.sps);
            var1 = this.this$0;
            var1.notifyOtherModule("SA", var1.sa);
            var1 = this.this$0;
            var1.notifyOtherModule("LD", var1.ld);
            var1 = this.this$0;
            var1.notifyOtherModule("CW", var1.cw);
            var1 = this.this$0;
            var1.notifyOtherModule("VDM", var1.vdm);
            var1 = this.this$0;
            var1.notifyOtherModule("SWA", var1.swa);
            OptionSettingsCmd459.getInstance().spsState = this.this$0.sps;
            OptionSettingsCmd459.getInstance().setSA(this.this$0.sa);
            OptionSettingsCmd459.getInstance().setLD(this.this$0.ld);
            OptionSettingsCmd459.getInstance().setCW(this.this$0.cw);
            OptionSettingsCmd459.getInstance().setVDM(this.this$0.vdm);
         }

         public void onTick(long var1) {
         }
      };
      this.timer = var1;
      var1.start();
   }

   public void setAwsBuffer(boolean var1) {
      if (!this.bufferTag && OptionSettingsCmd459.getInstance().awsState != var1) {
         this.notifyOtherModule("AWS", var1);
      }

      this.swa = var1;
      OptionSettingsCmd459.getInstance().awsState = var1;
      OptionSettingsCmd459.getInstance().booleanSaver(OptionSettingsCmd459.getInstance().TAG_AWS, var1);
   }

   public void setCwBuffer(boolean var1) {
      if (!this.bufferTag && OptionSettingsCmd459.getInstance().cwState != var1) {
         this.notifyOtherModule("CW", var1);
      }

      this.cw = var1;
      OptionSettingsCmd459.getInstance().cwState = var1;
   }

   public void setLdBuffer(boolean var1) {
      if (!this.bufferTag && OptionSettingsCmd459.getInstance().ldState != var1) {
         this.notifyOtherModule("LD", var1);
      }

      this.ld = var1;
      OptionSettingsCmd459.getInstance().ldState = var1;
   }

   public void setSaBuffer(boolean var1) {
      if (!this.bufferTag && OptionSettingsCmd459.getInstance().saState != var1) {
         this.notifyOtherModule("SA", var1);
      }

      this.sa = var1;
      OptionSettingsCmd459.getInstance().saState = var1;
      OptionSettingsCmd459.getInstance().booleanSaver(OptionSettingsCmd459.getInstance().TAG_SA, var1);
   }

   public void setSpsBuffer(boolean var1) {
      if (!this.bufferTag && OptionSettingsCmd459.getInstance().spsState != var1) {
         this.notifyOtherModule("SPS", var1);
      }

      this.sps = var1;
      OptionSettingsCmd459.getInstance().spsState = var1;
      OptionSettingsCmd459.getInstance().booleanSaver(OptionSettingsCmd459.getInstance().TAG_SPS, var1);
   }

   public void setVdmBuffer(String var1) {
      if (!this.bufferTag && OptionSettingsCmd459.getInstance().vdmState != var1) {
         this.notifyOtherModule("VDM", var1);
      }

      this.vdm = var1;
      OptionSettingsCmd459.getInstance().vdmState = var1;
      OptionSettingsCmd459.getInstance().stringSaver(OptionSettingsCmd459.getInstance().TAG_VDM, var1);
   }

   private static class Holder {
      private static final SettingsDataBuffer INSTANCE = new SettingsDataBuffer();
   }
}
