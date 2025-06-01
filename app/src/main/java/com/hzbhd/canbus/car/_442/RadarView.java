package com.hzbhd.canbus.car._442;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class RadarView extends RelativeLayout {
   ImageView rear_left_line;
   ImageView rear_mid_left_line;
   ImageView rear_mid_right_line;
   ImageView rear_right_line;

   public RadarView(Context var1) {
      super(var1);
      LayoutInflater.from(var1).inflate(2131558545, this);
      this.rear_left_line = (ImageView)this.findViewById(2131363049);
      this.rear_mid_left_line = (ImageView)this.findViewById(2131363053);
      this.rear_mid_right_line = (ImageView)this.findViewById(2131363055);
      this.rear_right_line = (ImageView)this.findViewById(2131363066);
   }

   public RadarView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public RadarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public void refreshRadar() {
      if (MdRadarData.left_rear == 0) {
         this.rear_left_line.setBackgroundResource(2131231291);
      } else if (MdRadarData.left_rear == 1) {
         this.rear_left_line.setBackgroundResource(2131231313);
      } else if (MdRadarData.left_rear == 2) {
         this.rear_left_line.setBackgroundResource(2131231312);
      } else if (MdRadarData.left_rear == 3) {
         this.rear_left_line.setBackgroundResource(2131231311);
      }

      if (MdRadarData.left_mid_rear == 0) {
         this.rear_mid_left_line.setBackgroundResource(2131231292);
      } else if (MdRadarData.left_mid_rear == 1) {
         this.rear_mid_left_line.setBackgroundResource(2131231293);
      } else if (MdRadarData.left_mid_rear == 2) {
         this.rear_mid_left_line.setBackgroundResource(2131231294);
      } else if (MdRadarData.left_mid_rear == 3) {
         this.rear_mid_left_line.setBackgroundResource(2131231295);
      }

      if (MdRadarData.right_mid_rear == 0) {
         this.rear_mid_right_line.setBackgroundResource(2131231292);
      } else if (MdRadarData.right_mid_rear == 1) {
         this.rear_mid_right_line.setBackgroundResource(2131231296);
      } else if (MdRadarData.right_mid_rear == 2) {
         this.rear_mid_right_line.setBackgroundResource(2131231297);
      } else if (MdRadarData.right_mid_rear == 3) {
         this.rear_mid_right_line.setBackgroundResource(2131231298);
      }

      if (MdRadarData.right_rear == 0) {
         this.rear_right_line.setBackgroundResource(2131231291);
      } else if (MdRadarData.right_rear == 1) {
         this.rear_right_line.setBackgroundResource(2131231307);
      } else if (MdRadarData.right_rear == 2) {
         this.rear_right_line.setBackgroundResource(2131231306);
      } else if (MdRadarData.right_rear == 3) {
         this.rear_right_line.setBackgroundResource(2131231305);
      }

   }
}
