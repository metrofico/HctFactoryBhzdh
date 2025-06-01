package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AmplifierItemSwitchView extends LinearLayout {
   private TextView name;
   private TextView off_txt;
   private TextView on_txt;
   private View view;

   public AmplifierItemSwitchView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AmplifierItemSwitchView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public AmplifierItemSwitchView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558549, this, true);
      this.view = var4;
      this.name = (TextView)var4.findViewById(2131362886);
      this.off_txt = (TextView)this.view.findViewById(2131362916);
      this.on_txt = (TextView)this.view.findViewById(2131362924);
   }

   public void setData(boolean var1) {
      if (var1) {
         this.off_txt.setBackgroundResource(2131100048);
         this.on_txt.setBackgroundResource(2131099974);
      } else {
         this.off_txt.setBackgroundResource(2131099974);
         this.on_txt.setBackgroundResource(2131100048);
      }

   }

   public void setTitle(String var1) {
      this.name.setText(var1);
   }
}
