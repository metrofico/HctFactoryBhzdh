package com.hzbhd.canbus.car._473;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import kotlin.Unit;

public class RadarView extends RelativeLayout {
   FrameLayout left_rear;
   ImageView left_rear_point;
   FrameLayout right_rear;
   ImageView right_rear_point;

   public RadarView(Context var1) {
      super(var1);
      LayoutInflater.from(var1).inflate(2131558585, this);
      this.left_rear_point = (ImageView)this.findViewById(2131362745);
      this.right_rear_point = (ImageView)this.findViewById(2131362745);
      this.left_rear = (FrameLayout)this.findViewById(2131362744);
      this.right_rear = (FrameLayout)this.findViewById(2131363174);
   }

   public RadarView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public RadarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   // $FF: synthetic method
   Unit lambda$refreshRadar$0$com_hzbhd_canbus_car__473_RadarView() {
      if (LogUtil.log5()) {
         LogUtil.d("refreshRadar(): --------------" + this.left_rear.getMeasuredWidth() + "----" + this.left_rear_point.getMeasuredWidth());
      }

      this.left_rear.setPadding(MdRadarData.reverse_right_rear_horizontal * (this.left_rear.getMeasuredWidth() - this.left_rear_point.getMeasuredWidth()) / 255, 0, 0, MdRadarData.reverse_left_rear_vertical * (this.left_rear.getMeasuredHeight() - this.left_rear_point.getMeasuredHeight()) / 255);
      this.right_rear.setPadding(0, 0, MdRadarData.reverse_right_rear_horizontal * (this.right_rear.getMeasuredWidth() - this.right_rear_point.getMeasuredWidth()) / 255, MdRadarData.reverse_right_rear_vertical * (this.right_rear.getMeasuredHeight() - this.right_rear_point.getMeasuredHeight()) / 255);
      return null;
   }

   public void refreshRadar() {
      BaseUtil.INSTANCE.runUi(new RadarView$$ExternalSyntheticLambda0(this));
   }
}
