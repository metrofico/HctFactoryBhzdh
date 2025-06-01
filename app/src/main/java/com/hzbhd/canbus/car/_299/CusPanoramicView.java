package com.hzbhd.canbus.car._299;

import android.content.Context;
import android.provider.Settings;
import android.provider.Settings.System;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._299.RadarView;

public class CusPanoramicView extends RelativeLayout {
   private RadarView mRadarView;

   public CusPanoramicView(Context var1) {
      super(var1);
      RadarView var2 = (RadarView)LayoutInflater.from(var1).inflate(2131558802, this).findViewById(2131363007);
      this.mRadarView = var2;
      var2.setBackgroundLayoutColor(0);
   }

   public void refreshRadarUi() {
      this.mRadarView.refreshUi();
   }

   public void setVisible(Context var1) {
      try {
         if (System.getInt(var1.getContentResolver(), "RADAR_DISP_CHECK") == 1) {
            this.mRadarView.setVisibility(0);
         } else {
            this.mRadarView.setVisibility(8);
         }
      } catch (Settings.SettingNotFoundException var2) {
         var2.printStackTrace();
      }

   }
}
