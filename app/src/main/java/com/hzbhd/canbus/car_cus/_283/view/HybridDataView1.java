package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;

public class HybridDataView1 extends RelativeLayout {
   private View mView;
   private TipsTextView tipsTextView1;
   private TipsTextView tipsTextView2;
   private TipsTextView tipsTextView3;
   private TipsTextView tipsTextView4;
   private TipsTextView tipsTextView5;
   private TipsTextView tipsTextView6;

   public HybridDataView1(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public HybridDataView1(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public HybridDataView1(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mView = LayoutInflater.from(var1).inflate(2131558465, this, true);
      this.initView();
   }

   private void initView() {
      this.tipsTextView1 = (TipsTextView)this.mView.findViewById(2131363534);
      this.tipsTextView2 = (TipsTextView)this.mView.findViewById(2131363535);
      this.tipsTextView3 = (TipsTextView)this.mView.findViewById(2131363536);
      this.tipsTextView4 = (TipsTextView)this.mView.findViewById(2131363537);
      this.tipsTextView5 = (TipsTextView)this.mView.findViewById(2131363538);
      this.tipsTextView6 = (TipsTextView)this.mView.findViewById(2131363539);
   }

   public void refreshUi() {
      this.tipsTextView1.setContent(GeneralDzData.hybrid_endurance);
      this.tipsTextView2.setContent(GeneralDzData.hybrid_energy_flow);
      this.tipsTextView3.setContent(GeneralDzData.hybrid_travelled);
      this.tipsTextView4.setContent(GeneralDzData.hybrid_zero_travelled);
      this.tipsTextView5.setContent(GeneralDzData.hybrid_zero_persents);
      this.tipsTextView6.setContent(GeneralDzData.hybrid_electricity);
   }
}
