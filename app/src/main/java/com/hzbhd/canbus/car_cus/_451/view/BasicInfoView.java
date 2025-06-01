package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BasicInfoView extends LinearLayout {
   public TextView asl_txt;
   public TextView band_txt;
   public TextView band_value_txt;
   public TextView bt_txt;
   public DiscView disc1;
   public DiscView disc2;
   public DiscView disc3;
   public DiscView disc4;
   public DiscView disc5;
   public DiscView disc6;
   public DiscView disc_in;
   public TextView phone_txt;
   public TextView preset_txt;
   public TextView rand_txt;
   public TextView rpt_txt;
   public TextView scan_txt;
   public TextView signal_txt;
   public TextView st_txt;
   private View view;

   public BasicInfoView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public BasicInfoView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public BasicInfoView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558552, this, true);
      this.view = var4;
      this.disc_in = (DiscView)var4.findViewById(2131362203);
      this.disc1 = (DiscView)this.view.findViewById(2131362197);
      this.disc2 = (DiscView)this.view.findViewById(2131362198);
      this.disc3 = (DiscView)this.view.findViewById(2131362199);
      this.disc4 = (DiscView)this.view.findViewById(2131362200);
      this.disc5 = (DiscView)this.view.findViewById(2131362201);
      this.disc6 = (DiscView)this.view.findViewById(2131362202);
      this.disc_in.setText("DISC IN");
      this.disc1.setText("DISC 1");
      this.disc2.setText("DISC 2");
      this.disc3.setText("DISC 3");
      this.disc4.setText("DISC 4");
      this.disc5.setText("DISC 5");
      this.disc6.setText("DISC 6");
      this.band_txt = (TextView)this.view.findViewById(2131361963);
      this.asl_txt = (TextView)this.view.findViewById(2131361925);
      this.bt_txt = (TextView)this.view.findViewById(2131361992);
      this.phone_txt = (TextView)this.view.findViewById(2131362976);
      this.scan_txt = (TextView)this.view.findViewById(2131363251);
      this.rpt_txt = (TextView)this.view.findViewById(2131363206);
      this.rand_txt = (TextView)this.view.findViewById(2131363040);
      this.signal_txt = (TextView)this.view.findViewById(2131363333);
      this.band_value_txt = (TextView)this.view.findViewById(2131361964);
      this.st_txt = (TextView)this.view.findViewById(2131363420);
      this.preset_txt = (TextView)this.view.findViewById(2131362991);
   }
}
