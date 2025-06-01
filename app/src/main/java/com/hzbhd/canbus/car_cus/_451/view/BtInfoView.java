package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BtInfoView extends LinearLayout {
   private TextView song_number;
   private TextView time;
   private View view;

   public BtInfoView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public BtInfoView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public BtInfoView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558553, this, true);
      this.view = var4;
      this.song_number = (TextView)var4.findViewById(2131363341);
      this.time = (TextView)this.view.findViewById(2131363526);
   }

   public void setData(String var1, String var2) {
      this.song_number.setText(var1);
      this.time.setText(var2);
   }
}
