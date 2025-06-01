package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import com.hzbhd.canbus.car_cus._283.view.SettingView;

public class CarSettingCombinationView extends RelativeLayout {
   private Context mContext;
   private View mView;
   private SettingSelectView ssv_average_consumption;
   private SettingSelectView ssv_average_speed;
   private SettingSelectView ssv_convenience_consumer;
   private SettingSelectView ssv_current_consumption;
   private SettingSelectView ssv_digital_speed_display;
   private SettingSelectView ssv_distance_travelled;
   private SettingSelectView ssv_eco;
   private SettingSelectView ssv_oil_temperature;
   private SettingSelectView ssv_speed_warning;
   private SettingSelectView ssv_travelling_time;
   private SettingView sv_reset_long_term;
   private SettingView sv_reset_since_start;

   public CarSettingCombinationView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingCombinationView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingCombinationView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558443, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new CarSettingCombinationView$$ExternalSyntheticLambda0(this), 1000L);
   }

   private void initClick() {
      CarSettingCombinationView$$ExternalSyntheticLambda2 var1 = new CarSettingCombinationView$$ExternalSyntheticLambda2();
      this.ssv_current_consumption.setOnItemClickListener(var1);
      this.ssv_average_consumption.setOnItemClickListener(var1);
      this.ssv_convenience_consumer.setOnItemClickListener(var1);
      this.ssv_eco.setOnItemClickListener(var1);
      this.ssv_travelling_time.setOnItemClickListener(var1);
      this.ssv_distance_travelled.setOnItemClickListener(var1);
      this.ssv_average_speed.setOnItemClickListener(var1);
      this.ssv_digital_speed_display.setOnItemClickListener(var1);
      this.ssv_speed_warning.setOnItemClickListener(var1);
      this.ssv_oil_temperature.setOnItemClickListener(var1);
      CarSettingCombinationView$$ExternalSyntheticLambda3 var2 = new CarSettingCombinationView$$ExternalSyntheticLambda3(this);
      this.sv_reset_since_start.setOnClickListener(var2);
      this.sv_reset_long_term.setOnClickListener(var2);
   }

   private void initView() {
      this.ssv_current_consumption = (SettingSelectView)this.mView.findViewById(2131363392);
      this.ssv_average_consumption = (SettingSelectView)this.mView.findViewById(2131363387);
      this.ssv_convenience_consumer = (SettingSelectView)this.mView.findViewById(2131363391);
      this.ssv_eco = (SettingSelectView)this.mView.findViewById(2131363398);
      this.ssv_travelling_time = (SettingSelectView)this.mView.findViewById(2131363418);
      this.ssv_distance_travelled = (SettingSelectView)this.mView.findViewById(2131363396);
      this.ssv_average_speed = (SettingSelectView)this.mView.findViewById(2131363388);
      this.ssv_digital_speed_display = (SettingSelectView)this.mView.findViewById(2131363395);
      this.ssv_speed_warning = (SettingSelectView)this.mView.findViewById(2131363416);
      this.ssv_oil_temperature = (SettingSelectView)this.mView.findViewById(2131363409);
      this.sv_reset_since_start = (SettingView)this.mView.findViewById(2131363457);
      this.sv_reset_long_term = (SettingView)this.mView.findViewById(2131363456);
   }

   // $FF: synthetic method
   static void lambda$initClick$1(View var0, boolean var1) {
      int var4 = var0.getId();
      byte var3 = 1;
      byte var2 = var3;
      switch (var4) {
         case 2131363387:
            var2 = 2;
            break;
         case 2131363388:
            var2 = 7;
            break;
         case 2131363391:
            var2 = 3;
         case 2131363392:
            break;
         case 2131363395:
            var2 = 8;
            break;
         case 2131363396:
            var2 = 6;
            break;
         case 2131363398:
            var2 = 4;
            break;
         case 2131363409:
            var2 = 10;
            break;
         case 2131363416:
            var2 = 9;
            break;
         case 2131363418:
            var2 = 5;
            break;
         default:
            var2 = var3;
      }

      MessageSender.sendMsg((byte)123, var2, var1);
   }

   // $FF: synthetic method
   static void lambda$initClick$2(byte var0, View var1) {
      MessageSender.sendMsg(new byte[]{22, 123, var0, 0});
   }

   // $FF: synthetic method
   void lambda$initClick$3$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingCombinationView(View var1) {
      int var6 = var1.getId();
      int var5 = 2131760599;
      byte var3 = 11;
      int var4 = var5;
      byte var2 = var3;
      switch (var6) {
         case 2131363456:
            var2 = 12;
            var4 = 2131760600;
         case 2131363457:
            break;
         default:
            var4 = var5;
            var2 = var3;
      }

      Context var7 = this.mContext;
      DialogUtils.showTipsDialog(var7, var7.getString(var4), new CarSettingCombinationView$$ExternalSyntheticLambda1(var2));
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingCombinationView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.ssv_current_consumption.setSelect(GeneralDzData.combination_current_consumption);
      this.ssv_average_consumption.setSelect(GeneralDzData.combination_average_consumption);
      this.ssv_convenience_consumer.setSelect(GeneralDzData.combination_convenience_consumer);
      this.ssv_eco.setSelect(GeneralDzData.combination_eco);
      this.ssv_travelling_time.setSelect(GeneralDzData.combination_travelling_time);
      this.ssv_distance_travelled.setSelect(GeneralDzData.combination_distance_travelled);
      this.ssv_average_speed.setSelect(GeneralDzData.combination_average_speed);
      this.ssv_digital_speed_display.setSelect(GeneralDzData.combination_digital_speed_display);
      this.ssv_speed_warning.setSelect(GeneralDzData.combination_speed_warning);
      this.ssv_oil_temperature.setSelect(GeneralDzData.combination_oil_temperature);
   }
}
