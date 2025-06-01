package com.hzbhd.canbus.car._470;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;

public class RadarView extends RelativeLayout {
   TextView pdc_ecufault;
   TextView pdc_led;
   TextView radar_mode;
   ImageView rear_center_error;
   ImageView rear_centre;
   ImageView rear_left;
   ImageView rear_left_error;
   ImageView rear_right;
   ImageView rear_right_error;

   public RadarView(Context var1) {
      super(var1);
      LayoutInflater.from(var1).inflate(2131558584, this);
      this.radar_mode = (TextView)this.findViewById(2131363018);
      this.pdc_led = (TextView)this.findViewById(2131362972);
      this.pdc_ecufault = (TextView)this.findViewById(2131362971);
      this.rear_left = (ImageView)this.findViewById(2131363047);
      this.rear_centre = (ImageView)this.findViewById(2131363046);
      this.rear_right = (ImageView)this.findViewById(2131363063);
      this.rear_left_error = (ImageView)this.findViewById(2131363048);
      this.rear_center_error = (ImageView)this.findViewById(2131363045);
      this.rear_right_error = (ImageView)this.findViewById(2131363065);
   }

   public RadarView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public RadarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   // $FF: synthetic method
   Unit lambda$refreshRadar$0$com_hzbhd_canbus_car__470_RadarView() {
      if (MdRadarData.sensorFaultRl) {
         this.rear_left_error.setVisibility(8);
      } else {
         this.rear_left_error.setVisibility(0);
      }

      if (MdRadarData.sensorFaultRml) {
         this.rear_center_error.setVisibility(8);
      } else {
         this.rear_center_error.setVisibility(0);
      }

      if (MdRadarData.sensorFaultRr) {
         this.rear_right_error.setVisibility(8);
      } else {
         this.rear_right_error.setVisibility(0);
      }

      switch (MdRadarData.distanceRl) {
         case 0:
            this.rear_left.setBackgroundResource(2131234912);
            break;
         case 1:
            this.rear_left.setBackgroundResource(2131231625);
            break;
         case 2:
            this.rear_left.setBackgroundResource(2131231626);
            break;
         case 3:
            this.rear_left.setBackgroundResource(2131231627);
            break;
         case 4:
            this.rear_left.setBackgroundResource(2131231628);
            break;
         case 5:
            this.rear_left.setBackgroundResource(2131231629);
            break;
         case 6:
            this.rear_left.setBackgroundResource(2131231630);
            break;
         case 7:
            this.rear_left.setBackgroundResource(2131231631);
      }

      switch (MdRadarData.distanceRml) {
         case 0:
            this.rear_centre.setBackgroundResource(2131234912);
            break;
         case 1:
            this.rear_centre.setBackgroundResource(2131231618);
            break;
         case 2:
            this.rear_centre.setBackgroundResource(2131231619);
            break;
         case 3:
            this.rear_centre.setBackgroundResource(2131231620);
            break;
         case 4:
            this.rear_centre.setBackgroundResource(2131231621);
            break;
         case 5:
            this.rear_centre.setBackgroundResource(2131231622);
            break;
         case 6:
            this.rear_centre.setBackgroundResource(2131231623);
            break;
         case 7:
            this.rear_centre.setBackgroundResource(2131231624);
      }

      switch (MdRadarData.distanceRr) {
         case 0:
            this.rear_right.setBackgroundResource(2131234912);
            break;
         case 1:
            this.rear_right.setBackgroundResource(2131231632);
            break;
         case 2:
            this.rear_right.setBackgroundResource(2131231633);
            break;
         case 3:
            this.rear_right.setBackgroundResource(2131231634);
            break;
         case 4:
            this.rear_right.setBackgroundResource(2131231635);
            break;
         case 5:
            this.rear_right.setBackgroundResource(2131231636);
            break;
         case 6:
            this.rear_right.setBackgroundResource(2131231637);
            break;
         case 7:
            this.rear_right.setBackgroundResource(2131231638);
      }

      switch (MdRadarData.reverse_radar_working_mode) {
         case 0:
            this.radar_mode.setText("off");
            break;
         case 1:
            this.radar_mode.setText("Standby");
            break;
         case 2:
            this.radar_mode.setText("Front Rear Active");
            break;
         case 3:
            this.radar_mode.setText("Front Active");
            break;
         case 4:
            this.radar_mode.setText("Rear Active");
            break;
         case 5:
            this.radar_mode.setText("System Failure");
            break;
         case 6:
            this.radar_mode.setText("Inhibited");
            break;
         case 7:
            this.radar_mode.setText("Invalid(Reserved)");
      }

      if (MdRadarData.pdc_led) {
         this.pdc_led.setText("开启");
      } else {
         this.pdc_led.setText("关闭");
      }

      if (MdRadarData.pdc_ecufault) {
         this.pdc_ecufault.setText("正常");
      } else {
         this.pdc_ecufault.setText("故障");
      }

      return null;
   }

   public void refreshRadar() {
      BaseUtil.INSTANCE.runUi(new RadarView$$ExternalSyntheticLambda0(this));
   }
}
