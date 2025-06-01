package com.hzbhd.canbus.car_cus._439.air.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WindModeView extends LinearLayout {
   private ImageView body_wind;
   private ImageView foot_wind;
   private ImageView head_wind;
   private View view;

   public WindModeView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public WindModeView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public WindModeView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558542, this, true);
      this.view = var4;
      this.head_wind = (ImageView)var4.findViewById(2131362366);
      this.body_wind = (ImageView)this.view.findViewById(2131361983);
      this.foot_wind = (ImageView)this.view.findViewById(2131362254);
   }

   public void setBodyWind(boolean var1) {
      if (var1) {
         this.body_wind.setBackgroundResource(2131231272);
      } else {
         this.body_wind.setBackgroundResource(2131231271);
      }

   }

   public void setFootWind(boolean var1) {
      if (var1) {
         this.foot_wind.setBackgroundResource(2131231275);
      } else {
         this.foot_wind.setBackgroundResource(2131231274);
      }

   }

   public void setWindowWind(boolean var1) {
      if (var1) {
         this.head_wind.setBackgroundResource(2131231287);
      } else {
         this.head_wind.setBackgroundResource(2131231286);
      }

   }
}
