package com.hzbhd.canbus.car_cus._291;

import android.os.Bundle;
import android.view.View;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;

public class MainSettingActivity extends AbstractBaseActivity {
   private SettingSelectView ssv_in;
   private SettingSelectView ssv_out;
   private SettingSelectView ssv_radar;

   private void initView() {
      this.findViewById(2131362534).setOnClickListener(new MainSettingActivity$$ExternalSyntheticLambda0(this));
      this.ssv_out = (SettingSelectView)this.findViewById(2131363410);
      this.ssv_in = (SettingSelectView)this.findViewById(2131363402);
      this.ssv_radar = (SettingSelectView)this.findViewById(2131363415);
      SettingSelectView.OnItemClickListener var1 = new SettingSelectView.OnItemClickListener(this) {
         final MainSettingActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1, boolean var2) {
            int var5 = var1.getId();
            byte var4 = 9;
            byte var3;
            if (var5 != 2131363402) {
               var3 = var4;
               if (var5 != 2131363410) {
                  if (var5 != 2131363415) {
                     var3 = var4;
                  } else {
                     var3 = 11;
                  }
               }
            } else {
               var3 = 10;
            }

            MessageSender.sendMsg(new byte[]{22, 76, var3, (byte)(var2 ^ 1)});
         }
      };
      this.ssv_out.setOnItemClickListener(var1);
      this.ssv_in.setOnItemClickListener(var1);
      this.ssv_radar.setOnItemClickListener(var1);
   }

   // $FF: synthetic method
   void lambda$initView$0$com_hzbhd_canbus_car_cus__291_MainSettingActivity(View var1) {
      this.finish();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558489);
      this.initView();
      this.getWindow().setFlags(1024, 1024);
      this.refreshUi((Bundle)null);
   }

   public void refreshUi(Bundle var1) {
      if (var1 != null) {
         this.ssv_out.setSelect(GeneralDzPQData.vehicleOut);
         this.ssv_in.setSelect(GeneralDzPQData.vehicleIn);
         this.ssv_radar.setSelect(GeneralDzPQData.vehicleRadarMute);
      }
   }
}
