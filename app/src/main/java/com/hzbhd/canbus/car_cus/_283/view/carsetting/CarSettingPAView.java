package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarSettingPAView extends RelativeLayout {
   private ExecutorService executorService;
   private List listDistance;
   private List listDriving;
   private Context mContext;
   private View mView;
   private SettingDialogView sdv_distance;
   private SettingDialogView sdv_driving;
   private SettingSelectView ssv_dead_zone;
   private SettingSelectView ssv_driverAlertSystem;
   private SettingSelectView ssv_front_assist;
   private SettingSelectView ssv_front_distance;
   private SettingSelectView ssv_front_warning;
   private SettingSelectView ssv_lane;
   private SettingSelectView ssv_lane_keeping;
   private SettingSelectView ssv_last;
   private SettingSelectView ssv_proactive;
   private SettingSelectView ssv_traffice;

   public CarSettingPAView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingPAView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingPAView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.executorService = Executors.newSingleThreadExecutor();
      this.listDriving = new ArrayList();
      this.listDistance = new ArrayList();
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558445, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new CarSettingPAView$$ExternalSyntheticLambda0(this), 1000L);
   }

   private void initClick() {
      CarSettingPAView$$ExternalSyntheticLambda1 var1 = new CarSettingPAView$$ExternalSyntheticLambda1();
      CarSettingPAView$$ExternalSyntheticLambda2 var2 = new CarSettingPAView$$ExternalSyntheticLambda2(this);
      this.sdv_driving.setOnItemClickListener(var1);
      this.sdv_distance.setOnItemClickListener(var1);
      this.ssv_last.setOnItemClickListener(var2);
      this.ssv_front_assist.setOnItemClickListener(var2);
      this.ssv_front_warning.setOnItemClickListener(var2);
      this.ssv_front_distance.setOnItemClickListener(var2);
      this.ssv_lane.setOnItemClickListener(var2);
      this.ssv_traffice.setOnItemClickListener(var2);
      this.ssv_driverAlertSystem.setOnItemClickListener(var2);
      this.ssv_dead_zone.setOnItemClickListener(var2);
      this.ssv_proactive.setOnItemClickListener(var2);
      this.ssv_lane_keeping.setOnItemClickListener(var2);
   }

   private void initView() {
      this.sdv_driving = (SettingDialogView)this.mView.findViewById(2131363269);
      this.sdv_distance = (SettingDialogView)this.mView.findViewById(2131363268);
      this.ssv_last = (SettingSelectView)this.mView.findViewById(2131363407);
      this.ssv_front_assist = (SettingSelectView)this.mView.findViewById(2131363399);
      this.ssv_front_warning = (SettingSelectView)this.mView.findViewById(2131363401);
      this.ssv_front_distance = (SettingSelectView)this.mView.findViewById(2131363400);
      this.ssv_lane = (SettingSelectView)this.mView.findViewById(2131363404);
      this.ssv_traffice = (SettingSelectView)this.mView.findViewById(2131363417);
      this.ssv_driverAlertSystem = (SettingSelectView)this.mView.findViewById(2131363397);
      this.ssv_dead_zone = (SettingSelectView)this.mView.findViewById(2131363394);
      this.ssv_proactive = (SettingSelectView)this.mView.findViewById(2131363414);
      this.ssv_lane_keeping = (SettingSelectView)this.mView.findViewById(2131363406);
      this.executorService.execute(new Runnable(this) {
         final CarSettingPAView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listDriving.add(new SettingDialogBean(this.this$0.mContext.getString(2131760618)));
            this.this$0.listDriving.add(new SettingDialogBean(this.this$0.mContext.getString(2131760616)));
            this.this$0.listDriving.add(new SettingDialogBean(this.this$0.mContext.getString(2131760617)));
            this.this$0.sdv_driving.setListFirst(this.this$0.listDriving);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingPAView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listDistance.add(new SettingDialogBean(this.this$0.mContext.getString(2131760715)));
            this.this$0.listDistance.add(new SettingDialogBean(this.this$0.mContext.getString(2131760716)));
            this.this$0.listDistance.add(new SettingDialogBean(this.this$0.mContext.getString(2131760717)));
            this.this$0.listDistance.add(new SettingDialogBean(this.this$0.mContext.getString(2131760718)));
            this.this$0.listDistance.add(new SettingDialogBean(this.this$0.mContext.getString(2131760719)));
            this.this$0.sdv_distance.setListFirst(this.this$0.listDistance);
         }
      });
   }

   // $FF: synthetic method
   static void lambda$initClick$1(View var0, int var1) {
      switch (var0.getId()) {
         case 2131363268:
            MessageSender.sendMsg(new byte[]{22, 76, 8, (byte)(var1 + 1)});
            break;
         case 2131363269:
            MessageSender.sendMsg(new byte[]{22, 76, 9, (byte)(var1 + 1)});
      }

   }

   private void sendMsg(byte var1, boolean var2) {
      MessageSender.sendMsg((byte)76, var1, var2);
   }

   // $FF: synthetic method
   void lambda$initClick$2$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingPAView(View var1, boolean var2) {
      switch (var1.getId()) {
         case 2131363394:
            this.sendMsg((byte)13, var2);
            break;
         case 2131363397:
            this.sendMsg((byte)6, var2);
            break;
         case 2131363399:
            this.sendMsg((byte)1, var2);
            break;
         case 2131363400:
            this.sendMsg((byte)3, var2);
            break;
         case 2131363401:
            this.sendMsg((byte)2, var2);
            break;
         case 2131363404:
            this.sendMsg((byte)4, var2);
            break;
         case 2131363406:
            this.sendMsg((byte)10, var2);
            break;
         case 2131363407:
            this.sendMsg((byte)11, var2);
            break;
         case 2131363414:
            this.sendMsg((byte)7, var2);
            break;
         case 2131363417:
            this.sendMsg((byte)5, var2);
      }

   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingPAView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.sdv_driving.setSelect(GeneralDzData.pa_driving);
      this.sdv_distance.setSelect(GeneralDzData.pa_distance);
      this.ssv_last.setSelect(GeneralDzData.pa_last);
      this.ssv_front_assist.setSelect(GeneralDzData.pa_front_assist);
      this.ssv_front_warning.setSelect(GeneralDzData.pa_front_warning);
      this.ssv_front_distance.setSelect(GeneralDzData.pa_front_distance);
      this.ssv_lane.setSelect(GeneralDzData.pa_lane);
      this.ssv_traffice.setSelect(GeneralDzData.pa_traffice);
      this.ssv_driverAlertSystem.setSelect(GeneralDzData.pa_driverAlertSystem);
      this.ssv_dead_zone.setSelect(GeneralDzData.pa_dead_zone);
      this.ssv_proactive.setSelect(GeneralDzData.pa_proactive);
      this.ssv_lane_keeping.setSelect(GeneralDzData.pa_lane_keeping);
   }
}
