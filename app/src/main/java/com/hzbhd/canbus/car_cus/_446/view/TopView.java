package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TopView extends LinearLayout {
   private ImageView icon;
   private TextView name;
   private ImageView power;
   private View view;

   public TopView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public TopView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public TopView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558591, this, true);
      this.view = var4;
      this.icon = (ImageView)var4.findViewById(2131362435);
      this.power = (ImageView)this.view.findViewById(2131362989);
      this.name = (TextView)this.view.findViewById(2131362886);
   }

   public void turnOff(int var1, String var2, int var3) {
      this.icon.setBackgroundResource(var1);
      this.power.setBackgroundResource(var3);
      this.name.setText(var2);
      this.name.setTextColor(Color.parseColor("#808080"));
   }

   public void turnOn(int var1, String var2, int var3) {
      this.icon.setBackgroundResource(var1);
      this.power.setBackgroundResource(var3);
      this.name.setText(var2);
      this.name.setTextColor(Color.parseColor("#f8f8ff"));
   }
}
