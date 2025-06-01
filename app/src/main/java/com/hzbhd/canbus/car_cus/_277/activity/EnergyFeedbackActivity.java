package com.hzbhd.canbus.car_cus._277.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.util.CommUtil;

public class EnergyFeedbackActivity extends AbstractBaseActivity implements View.OnClickListener {
   private static boolean mIsDowntime;
   private static int mStrength;
   private final int STRENGTH_STEP = 10;
   private ImageView mEnergyBar;
   private ImageButton mEnergyDecrease;
   private Button mEnergyDowntime;
   private ImageButton mEnergyIncrease;
   private TextView mEnergyText;

   private void dataProcessing() {
      if (mStrength < 0) {
         mStrength = 0;
      }

      if (mStrength > 100) {
         mStrength = 100;
      }

      byte var1 = mIsDowntime;
      CanbusMsgSender.sendMsg(new byte[]{22, 60, 0, (byte)mStrength, (byte)var1, 0, 0});
   }

   private void findViews() {
      this.mEnergyBar = (ImageView)this.findViewById(2131362564);
      this.mEnergyText = (TextView)this.findViewById(2131363619);
      this.mEnergyDecrease = (ImageButton)this.findViewById(2131362390);
      this.mEnergyIncrease = (ImageButton)this.findViewById(2131362391);
      this.mEnergyDowntime = (Button)this.findViewById(2131362021);
   }

   private void initViews() {
      this.mEnergyDecrease.setOnClickListener(this);
      this.mEnergyIncrease.setOnClickListener(this);
      this.mEnergyDowntime.setOnClickListener(this);
      this.refreshUi((Bundle)null);
   }

   private void refreshENergyDowntime() {
      Button var2 = this.mEnergyDowntime;
      int var1;
      if (mIsDowntime) {
         var1 = 2131768600;
      } else {
         var1 = 2131768599;
      }

      var2.setText(var1);
   }

   private void refreshEnergyBar() {
      if (mStrength - 1 >= 0) {
         this.mEnergyBar.setVisibility(0);
         int var1 = CommUtil.getImgIdByResId(this, "energy_feedback_strength_" + ((mStrength - 1) / 10 + 1));
         this.mEnergyBar.setImageResource(var1);
      } else {
         this.mEnergyBar.setVisibility(4);
      }

   }

   private void refreshEnergyText() {
      this.mEnergyText.setText(mStrength + "%");
   }

   public void onClick(View var1) {
      int var2;
      switch (var1.getId()) {
         case 2131362021:
            mIsDowntime ^= true;
            break;
         case 2131362390:
            var2 = mStrength - 10;
            mStrength = var2;
            if (var2 < 0) {
               mStrength = 0;
            }
            break;
         case 2131362391:
            var2 = mStrength + 10;
            mStrength = var2;
            if (var2 > 100) {
               mStrength = 100;
            }
      }

      this.refreshUi((Bundle)null);
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558417);
      this.findViews();
      this.initViews();
   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         this.dataProcessing();
         this.refreshEnergyText();
         this.refreshEnergyBar();
         this.refreshENergyDowntime();
      }
   }
}
