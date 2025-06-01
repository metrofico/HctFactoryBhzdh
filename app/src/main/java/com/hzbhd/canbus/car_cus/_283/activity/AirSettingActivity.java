package com.hzbhd.canbus.car_cus._283.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AirSettingActivity extends AbstractBaseActivity {
   private ExecutorService executorService = Executors.newSingleThreadExecutor();
   private SettingDialogView sdv_temp_interval;
   private SettingSelectView ssv_auto_cycle;
   private SettingSelectView ssv_auto_defogging;
   SettingSelectView.OnItemClickListener ssv_oicl = new SettingSelectView.OnItemClickListener(this) {
      final AirSettingActivity this$0;

      {
         this.this$0 = var1;
      }

      public void onClick(View var1, boolean var2) {
         switch (var1.getId()) {
            case 2131363383:
               MessageSender.sendMsg((byte)58, (byte)14, GeneralDzData.auto_cycle);
               break;
            case 2131363384:
               MessageSender.sendMsg((byte)58, (byte)10, GeneralDzData.auto_defogging);
               break;
            case 2131363483:
               MessageSender.sendMsg((byte)58, (byte)36, GeneralDzData.wheel_seat_hot_sync);
         }

      }
   };
   private SettingSelectView sync_hot;

   private void initIntervalList() {
      ArrayList var1 = new ArrayList();
      this.executorService.execute(new AirSettingActivity$$ExternalSyntheticLambda0(this, var1));
      this.sdv_temp_interval.setOnItemClickListener(new AirSettingActivity$$ExternalSyntheticLambda1(this, var1));
   }

   private void initView() {
      this.sdv_temp_interval = (SettingDialogView)this.findViewById(2131363280);
      this.sync_hot = (SettingSelectView)this.findViewById(2131363483);
      this.ssv_auto_defogging = (SettingSelectView)this.findViewById(2131363384);
      this.ssv_auto_cycle = (SettingSelectView)this.findViewById(2131363383);
      this.findViewById(2131362534).setOnClickListener(new AirSettingActivity$$ExternalSyntheticLambda2(this));
      this.sync_hot.setOnItemClickListener(this.ssv_oicl);
      this.ssv_auto_defogging.setOnItemClickListener(this.ssv_oicl);
      this.ssv_auto_cycle.setOnItemClickListener(this.ssv_oicl);
      this.initIntervalList();
      this.refreshUi((Bundle)null);
   }

   // $FF: synthetic method
   void lambda$initIntervalList$1$com_hzbhd_canbus_car_cus__283_activity_AirSettingActivity(List var1) {
      this.sdv_temp_interval.setList(var1);
   }

   // $FF: synthetic method
   void lambda$initIntervalList$2$com_hzbhd_canbus_car_cus__283_activity_AirSettingActivity(List var1) {
      int var2;
      for(var2 = 0; var2 < 6; ++var2) {
         var1.add(new SettingDialogBean((new String[]{"0.5", "1", "1.5", "2", "2.5", "3"})[var2]));
      }

      String var3 = SharePreUtil.getStringValue(this, "_283_temp_interval", "0.5");
      if (TextUtils.isEmpty(var3)) {
         ((SettingDialogBean)var1.get(0)).setSelect(true);
      } else {
         for(var2 = 0; var2 < var1.size(); ++var2) {
            if (var3.equals(((SettingDialogBean)var1.get(var2)).getValue())) {
               ((SettingDialogBean)var1.get(var2)).setSelect(true);
            } else {
               ((SettingDialogBean)var1.get(var2)).setSelect(false);
            }
         }
      }

      this.runOnUiThread(new AirSettingActivity$$ExternalSyntheticLambda3(this, var1));
   }

   // $FF: synthetic method
   void lambda$initIntervalList$3$com_hzbhd_canbus_car_cus__283_activity_AirSettingActivity(List var1, View var2, int var3) {
      this.sdv_temp_interval.setSelect(var3);
      SharePreUtil.setStringValue(this, "_283_temp_interval", ((SettingDialogBean)var1.get(var3)).getValue());
   }

   // $FF: synthetic method
   void lambda$initView$0$com_hzbhd_canbus_car_cus__283_activity_AirSettingActivity(View var1) {
      this.onBackPressed();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558429);
      this.getWindow().setFlags(1024, 1024);
      this.initView();
   }

   public void refreshUi(Bundle var1) {
      this.sync_hot.setSelect(GeneralDzData.wheel_seat_hot_sync);
      this.ssv_auto_defogging.setSelect(GeneralDzData.auto_defogging);
      this.ssv_auto_cycle.setSelect(GeneralDzData.auto_cycle);
   }
}
