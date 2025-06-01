package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LyricTextViewView extends LinearLayout {
   private TextView txt;
   private View view;

   public LyricTextViewView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public LyricTextViewView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public LyricTextViewView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558556, this, true);
      this.view = var4;
      this.txt = (TextView)var4.findViewById(2131363721);
   }

   public void setTxt(String var1) {
      this.txt.setText(var1);
   }
}
