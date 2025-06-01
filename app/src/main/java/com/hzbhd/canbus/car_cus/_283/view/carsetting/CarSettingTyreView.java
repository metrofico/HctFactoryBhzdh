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

public class CarSettingTyreView extends RelativeLayout {
   private ExecutorService executorService;
   private List listAlarm;
   private List listLoad;
   private Context mContext;
   private View mView;
   private SettingDialogView sdv_alarm;
   private SettingDialogView sdv_load;
   private SettingSelectView ssv_alarm;

   public CarSettingTyreView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingTyreView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingTyreView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.listLoad = new ArrayList();
      this.listAlarm = new ArrayList();
      this.executorService = Executors.newSingleThreadExecutor();
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558450, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new CarSettingTyreView$$ExternalSyntheticLambda0(this), 1000L);
   }

   private void initClick() {
      this.ssv_alarm.setOnItemClickListener(new SettingSelectView.OnItemClickListener(this) {
         final CarSettingTyreView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1, boolean var2) {
            if (var2) {
               MessageSender.sendMsg(new byte[]{22, 75, 2, 0});
            } else {
               MessageSender.sendMsg(new byte[]{22, 75, 2, 1});
            }

         }
      });
      CarSettingTyreView$$ExternalSyntheticLambda1 var1 = new CarSettingTyreView$$ExternalSyntheticLambda1(this);
      this.sdv_load.setOnItemClickListener(var1);
      this.sdv_alarm.setOnItemClickListener(var1);
   }

   private void initView() {
      this.sdv_load = (SettingDialogView)this.mView.findViewById(2131363274);
      this.sdv_alarm = (SettingDialogView)this.mView.findViewById(2131363261);
      this.ssv_alarm = (SettingSelectView)this.mView.findViewById(2131363380);
      this.executorService.execute(new Runnable(this) {
         final CarSettingTyreView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listLoad.add(new SettingDialogBean(this.this$0.mContext.getString(2131760778)));
            this.this$0.listLoad.add(new SettingDialogBean(this.this$0.mContext.getString(2131760779)));
            this.this$0.listLoad.add(new SettingDialogBean(this.this$0.mContext.getString(2131760780)));
            this.this$0.sdv_load.setListFirst(this.this$0.listLoad);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingTyreView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            for(int var1 = 30; var1 <= 240; var1 += 10) {
               this.this$0.listAlarm.add(new SettingDialogBean(var1 + "", this.this$0.mContext.getString(2131760786)));
            }

            this.this$0.sdv_alarm.setListFirst(this.this$0.listAlarm);
         }
      });
   }

   // $FF: synthetic method
   void lambda$initClick$1$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingTyreView(View var1, int var2) {
      int var3 = var1.getId();
      if (var3 != 2131363261) {
         if (var3 == 2131363274) {
            MessageSender.sendMsg(new byte[]{22, 75, 4, (byte)var2});
         }
      } else {
         MessageSender.sendMsg(new byte[]{22, 75, 3, (byte)Integer.parseInt(((SettingDialogBean)this.listAlarm.get(var2)).getValue())});
      }

   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingTyreView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.sdv_load.setSelect(GeneralDzData.tmpsChoose);
      this.ssv_alarm.setSelect(GeneralDzData.tmpsAlarm);
      this.sdv_alarm.setEnable(GeneralDzData.tmpsAlarm);
      this.sdv_alarm.setSelectOnValue(String.valueOf(GeneralDzData.tmpsAlarmSpeed));
   }
}
