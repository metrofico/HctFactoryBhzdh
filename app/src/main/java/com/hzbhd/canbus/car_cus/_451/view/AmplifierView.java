package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class AmplifierView extends LinearLayout {
   private AmplifierItemSwitchView asl_item;
   private AmplifierItemSevenView bal_item;
   private AmplifierItemView bas_item;
   private AmplifierItemSevenView fad_item;
   private AmplifierItemView mid_item;
   private AmplifierItemView tre_item;
   private View view;

   public AmplifierView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AmplifierView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public AmplifierView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558546, this, true);
      this.view = var4;
      this.bas_item = (AmplifierItemView)var4.findViewById(2131361967);
      this.mid_item = (AmplifierItemView)this.view.findViewById(2131362853);
      this.tre_item = (AmplifierItemView)this.view.findViewById(2131363565);
      this.fad_item = (AmplifierItemSevenView)this.view.findViewById(2131362227);
      this.bal_item = (AmplifierItemSevenView)this.view.findViewById(2131361961);
      this.asl_item = (AmplifierItemSwitchView)this.view.findViewById(2131361924);
      this.bas_item.setTitle("BAS");
      this.mid_item.setTitle("MID");
      this.tre_item.setTitle("TRE");
      this.fad_item.setTitle("FAD");
      this.fad_item.setUnit("F", "R");
      this.bal_item.setTitle("BAL");
      this.bal_item.setUnit("L", "R");
      this.asl_item.setTitle("ASL");
      this.bas_item.setData(-5);
      this.mid_item.setData(-2);
      this.tre_item.setData(-3);
      this.fad_item.setData(-5);
      this.bal_item.setData(7);
      this.asl_item.setData(false);
   }

   public void setData(int var1, int var2, int var3, int var4, int var5, boolean var6) {
      this.bas_item.setData(var1);
      this.mid_item.setData(var2);
      this.tre_item.setData(var3);
      this.fad_item.setData(var4);
      this.bal_item.setData(var5);
      this.asl_item.setData(var6);
   }
}
