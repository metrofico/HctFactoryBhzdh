package com.hzbhd.canbus.car._85;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyPanoramicView extends RelativeLayout {
   static final String LEFT_BACKWARD = "left_backward";
   static final String LEFT_FORWARD = "left_forward";
   static final String LEFT_RADAR = "left_radar";
   static final String LEFT_REVERSE = "left_REVERSE";
   static final String LEFT_STOP = "left_stop";
   static final String RIGHT_RADAR = "right_radar";
   private ImageView mLeftBackWard;
   private ImageView mLeftForward;
   private ImageView mLeftRadar;
   private TextView mLeftReverse;
   private ImageView mLeftStop;
   private LinearLayout mLlAlert;
   private ImageView mRightRadar;
   private TextView mTvAlertMessage;
   private TextView mTvMessage;

   public MyPanoramicView(Context var1) {
      super(var1);
      LayoutInflater.from(var1).inflate(2131558808, this);
      this.findView();
   }

   private void findView() {
      this.mTvMessage = (TextView)this.findViewById(2131363650);
      this.mTvAlertMessage = (TextView)this.findViewById(2131363589);
      this.mLeftRadar = (ImageView)this.findViewById(2131362596);
      this.mRightRadar = (ImageView)this.findViewById(2131362626);
      this.mLeftStop = (ImageView)this.findViewById(2131362600);
      this.mLeftForward = (ImageView)this.findViewById(2131362591);
      this.mLeftBackWard = (ImageView)this.findViewById(2131362583);
      this.mLeftReverse = (TextView)this.findViewById(2131363642);
      this.mLlAlert = (LinearLayout)this.findViewById(2131362770);
   }

   private void setVisible(View var1, boolean var2) {
      if (var2) {
         var1.setVisibility(0);
      } else {
         var1.setVisibility(8);
      }

   }

   public void hideAlertWindow() {
      this.mLlAlert.setVisibility(8);
   }

   public void refreshIcon(String var1) {
      this.setVisible(this.mLeftRadar, "left_radar".equals(var1));
      this.setVisible(this.mRightRadar, "right_radar".equals(var1));
      this.setVisible(this.mLeftForward, "left_forward".equals(var1));
      this.setVisible(this.mLeftBackWard, "left_backward".equals(var1));
      this.setVisible(this.mLeftStop, "left_stop".equals(var1));
      this.setVisible(this.mLeftReverse, "left_REVERSE".equals(var1));
   }

   public void setAlertMessage(int var1) {
      this.mTvAlertMessage.setText(var1);
   }

   public void setAlertMessage(String var1) {
      this.mTvAlertMessage.setText(var1);
   }

   public void setMessage(int var1) {
      this.mTvMessage.setText(var1);
   }

   public void showAlertWindow() {
      this.mLlAlert.setVisibility(0);
   }
}
