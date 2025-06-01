package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;

public class CarSettingRearView extends RelativeLayout {
   private Context mContext;
   private View mView;
   private SettingSelectView ssv_1;
   private SettingSelectView ssv_2;
   private SettingSelectView ssv_3;
   private SettingSelectView ssv_4;
   private SettingSelectView ssv_5;

   public CarSettingRearView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingRearView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingRearView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558447, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new CarSettingRearView$$ExternalSyntheticLambda0(this), 1000L);
   }

   private void initClick() {
      SettingSelectView.OnItemClickListener var1 = new SettingSelectView.OnItemClickListener(this) {
         final CarSettingRearView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1, boolean var2) {
            int var5 = var1.getId();
            byte var4 = 1;
            byte var3 = var4;
            switch (var5) {
               case 2131363375:
                  break;
               case 2131363376:
                  var3 = 2;
                  break;
               case 2131363377:
                  var3 = 3;
                  break;
               case 2131363378:
                  var3 = 4;
                  break;
               case 2131363379:
                  var3 = 5;
                  break;
               default:
                  var3 = var4;
            }

            MessageSender.sendMsg((byte)110, var3, var2);
         }
      };
      this.ssv_1.setOnItemClickListener(var1);
      this.ssv_2.setOnItemClickListener(var1);
      this.ssv_3.setOnItemClickListener(var1);
      this.ssv_4.setOnItemClickListener(var1);
      this.ssv_5.setOnItemClickListener(var1);
   }

   private void initView() {
      this.ssv_1 = (SettingSelectView)this.mView.findViewById(2131363375);
      this.ssv_2 = (SettingSelectView)this.mView.findViewById(2131363376);
      this.ssv_3 = (SettingSelectView)this.mView.findViewById(2131363377);
      this.ssv_4 = (SettingSelectView)this.mView.findViewById(2131363378);
      this.ssv_5 = (SettingSelectView)this.mView.findViewById(2131363379);
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingRearView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.ssv_1.setSelect(GeneralDzData.rear_sync);
      this.ssv_2.setSelect(GeneralDzData.rear_down);
      this.ssv_3.setSelect(GeneralDzData.rear_in);
      this.ssv_4.setSelect(GeneralDzData.rear_auto_wiper);
      this.ssv_5.setSelect(GeneralDzData.rear_wiper);
   }
}
