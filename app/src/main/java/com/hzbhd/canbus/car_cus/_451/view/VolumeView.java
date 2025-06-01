package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class VolumeView extends LinearLayout {
   private ImageView mute_img;
   private SeekBar my_seek;
   private View view;

   public VolumeView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public VolumeView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public VolumeView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558559, this, true);
      this.view = var4;
      SeekBar var5 = (SeekBar)var4.findViewById(2131362885);
      this.my_seek = var5;
      var5.setEnabled(false);
      this.mute_img = (ImageView)this.view.findViewById(2131362881);
      this.setMax(100);
      this.setProgress(45);
   }

   public void setMax(int var1) {
      this.my_seek.setMax(var1);
   }

   public void setMute(boolean var1) {
      ImageView var3 = this.mute_img;
      int var2;
      if (var1) {
         var2 = 2131231330;
      } else {
         var2 = 2131231329;
      }

      var3.setBackgroundResource(var2);
   }

   public void setProgress(int var1) {
      this.my_seek.setProgress(var1);
   }
}
