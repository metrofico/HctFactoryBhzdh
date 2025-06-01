package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VideoStateView extends LinearLayout {
   private ImageView state_icon;
   private TextView state_txt;
   private TextView time_txt;
   private View view;

   public VideoStateView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public VideoStateView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public VideoStateView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558613, this, true);
      this.view = var4;
      this.state_icon = (ImageView)var4.findViewById(2131363424);
      this.state_txt = (TextView)this.view.findViewById(2131363425);
      this.time_txt = (TextView)this.view.findViewById(2131363533);
   }

   public void setIcon(int var1) {
      this.state_icon.setBackgroundResource(var1);
   }

   public void setState(String var1) {
      this.state_txt.setText(var1);
   }

   public void setTime(String var1) {
      this.time_txt.setText(var1);
   }
}
