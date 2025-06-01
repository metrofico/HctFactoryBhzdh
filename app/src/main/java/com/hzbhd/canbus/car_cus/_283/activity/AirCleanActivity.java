package com.hzbhd.canbus.car_cus._283.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;

public class AirCleanActivity extends AbstractBaseActivity implements View.OnClickListener {
   private int[] airCleanImages = new int[]{2131232868, 2131232870, 2131232871, 2131232872, 2131232873, 2131232874, 2131232875, 2131232876, 2131232877, 2131232869};
   private ImageView iv_back;
   private ImageView iv_click_isSelect;
   private ImageView iv_progress;
   private ImageView tv_isSelect;
   private TextView tv_tips;

   private void initView() {
      this.iv_click_isSelect = (ImageView)this.findViewById(2131362557);
      this.tv_isSelect = (ImageView)this.findViewById(2131363632);
      this.iv_back = (ImageView)this.findViewById(2131362534);
      this.iv_progress = (ImageView)this.findViewById(2131362608);
      this.tv_tips = (TextView)this.findViewById(2131363708);
      this.iv_back.setOnClickListener(this);
      this.iv_click_isSelect.setOnClickListener(this);
   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362534) {
         if (var2 == 2131362557) {
            if ("true".equals(this.tv_isSelect.getTag())) {
               MessageSender.sendMsg(new byte[]{22, 58, 32, 0});
            } else {
               MessageSender.sendMsg(new byte[]{22, 58, 32, 1});
            }
         }
      } else {
         this.onBackPressed();
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558428);
      this.getWindow().setFlags(1024, 1024);
      this.initView();
   }

   protected void onDestroy() {
      super.onDestroy();
      MessageSender.sendMsg(new byte[]{22, 58, 32, 0});
   }

   public void refreshUi(Bundle var1) {
      if (GeneralDzData.air_clean) {
         this.tv_isSelect.setImageResource(2131233195);
         this.tv_isSelect.setTag("true");
      } else {
         this.tv_isSelect.setImageResource(2131233194);
         this.tv_isSelect.setTag("false");
      }

      if (GeneralDzData.air_clean) {
         int var2 = GeneralDzData.air_clean_progress;
         if (var2 == 0) {
            this.iv_progress.setImageDrawable((Drawable)null);
            this.tv_tips.setText(this.getString(2131760438));
         } else if (var2 >= 1 && var2 <= 10) {
            this.iv_progress.setImageResource(this.airCleanImages[var2 - 1]);
            this.tv_tips.setText(this.getString(2131760437));
         } else if (var2 == 15) {
            this.iv_progress.setImageDrawable((Drawable)null);
            this.tv_tips.setText(this.getString(2131760439));
         }
      } else {
         this.iv_progress.setImageDrawable((Drawable)null);
         this.tv_tips.setText(this.getString(2131760441));
      }

   }
}
