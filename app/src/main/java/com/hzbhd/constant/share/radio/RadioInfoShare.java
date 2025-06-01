package com.hzbhd.constant.share.radio;

public class RadioInfoShare {
   private String current_band;
   private String current_freq;
   private int preset_index;
   private String ps_name;
   private int stereo;

   public String getCurrent_band() {
      return this.current_band;
   }

   public String getCurrent_freq() {
      return this.current_freq;
   }

   public int getPreset_index() {
      return this.preset_index;
   }

   public String getPs_name() {
      return this.ps_name;
   }

   public int getStereo() {
      return this.stereo;
   }

   public void setCurrent_band(String var1) {
      this.current_band = var1;
   }

   public void setCurrent_freq(String var1) {
      this.current_freq = var1;
   }

   public void setPreset_index(int var1) {
      this.preset_index = var1;
   }

   public void setPs_name(String var1) {
      this.ps_name = var1;
   }

   public void setStereo(int var1) {
      this.stereo = var1;
   }
}
