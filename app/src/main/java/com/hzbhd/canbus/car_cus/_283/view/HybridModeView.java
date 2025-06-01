package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;

public class HybridModeView extends RelativeLayout implements View.OnClickListener {
   private View mView;
   private RelativeLayout mode1;
   private RelativeLayout mode2;
   private RelativeLayout mode3;
   private RelativeLayout mode4;

   public HybridModeView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public HybridModeView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public HybridModeView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mView = LayoutInflater.from(var1).inflate(2131558467, this, true);
      this.initView();
   }

   private void initView() {
      this.mode1 = (RelativeLayout)this.mView.findViewById(2131362860);
      this.mode2 = (RelativeLayout)this.mView.findViewById(2131362861);
      this.mode3 = (RelativeLayout)this.mView.findViewById(2131362862);
      this.mode4 = (RelativeLayout)this.mView.findViewById(2131362863);
      this.mode1.setOnClickListener(this);
      this.mode2.setOnClickListener(this);
      this.mode3.setOnClickListener(this);
      this.mode4.setOnClickListener(this);
   }

   private void recoverRelativeLayout(RelativeLayout var1) {
      var1.setSelected(false);
      var1.setScaleX(1.0F);
      var1.setScaleY(1.0F);
   }

   private void scaleRelativeLayout(RelativeLayout var1) {
      var1.setSelected(true);
      var1.setScaleX(1.1F);
      var1.setScaleY(1.1F);
   }

   private void setModel(boolean var1, RelativeLayout var2) {
      if (var1) {
         this.scaleRelativeLayout(var2);
      } else {
         this.recoverRelativeLayout(var2);
      }

   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362860:
            MessageSender.sendMsg(new byte[]{22, -117, 32, 0});
            break;
         case 2131362861:
            MessageSender.sendMsg(new byte[]{22, -117, 33, 0});
            break;
         case 2131362862:
            MessageSender.sendMsg(new byte[]{22, -117, 34, 0});
            break;
         case 2131362863:
            MessageSender.sendMsg(new byte[]{22, -117, 35, 0});
      }

   }

   public void refreshUi() {
      this.setModel(GeneralDzData.hybrid_mode_e, this.mode1);
      this.setModel(GeneralDzData.hybrid_mode_power, this.mode2);
      this.setModel(GeneralDzData.hybrid_mode_keep, this.mode3);
      this.setModel(GeneralDzData.hybrid_mode_charge, this.mode4);
   }
}
