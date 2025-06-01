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

public class CarSettingDoorView extends RelativeLayout {
   private SettingDialogView adv_close;
   private ExecutorService executorService;
   private List listClose;
   private List listOpen;
   private Context mContext;
   private View mView;
   private SettingDialogView sdv_open;
   private SettingSelectView ssv_auto_lock;
   private SettingSelectView ssv_boot;
   private SettingSelectView ssv_intelligence;
   private SettingSelectView ssv_voice;

   public CarSettingDoorView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingDoorView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingDoorView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.listOpen = new ArrayList();
      this.listClose = new ArrayList();
      this.executorService = Executors.newSingleThreadExecutor();
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558444, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new CarSettingDoorView$$ExternalSyntheticLambda2(this), 1000L);
   }

   private void initClick() {
      CarSettingDoorView$$ExternalSyntheticLambda0 var1 = new CarSettingDoorView$$ExternalSyntheticLambda0();
      CarSettingDoorView$$ExternalSyntheticLambda1 var2 = new CarSettingDoorView$$ExternalSyntheticLambda1();
      this.sdv_open.setOnItemClickListener(var1);
      this.adv_close.setOnItemClickListener(var1);
      this.ssv_auto_lock.setOnItemClickListener(var2);
      this.ssv_intelligence.setOnItemClickListener(var2);
      this.ssv_boot.setOnItemClickListener(var2);
      this.ssv_voice.setOnItemClickListener(var2);
   }

   private void initView() {
      this.sdv_open = (SettingDialogView)this.mView.findViewById(2131363275);
      this.adv_close = (SettingDialogView)this.mView.findViewById(2131361903);
      this.ssv_auto_lock = (SettingSelectView)this.mView.findViewById(2131363385);
      this.ssv_intelligence = (SettingSelectView)this.mView.findViewById(2131363403);
      this.ssv_boot = (SettingSelectView)this.mView.findViewById(2131363390);
      this.ssv_voice = (SettingSelectView)this.mView.findViewById(2131363419);
      this.executorService.execute(new Runnable(this) {
         final CarSettingDoorView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760606)));
            this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760607)));
            this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760608)));
            this.this$0.sdv_open.setListFirst(this.this$0.listOpen);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingDoorView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listClose.add(new SettingDialogBean(this.this$0.mContext.getString(2131760609)));
            this.this$0.listClose.add(new SettingDialogBean(this.this$0.mContext.getString(2131760610)));
            this.this$0.listClose.add(new SettingDialogBean(this.this$0.mContext.getString(2131760611)));
            this.this$0.adv_close.setListFirst(this.this$0.listClose);
         }
      });
   }

   // $FF: synthetic method
   static void lambda$initClick$1(View var0, int var1) {
      byte var2;
      if (var0.getId() != 2131361903) {
         var2 = 1;
      } else {
         var2 = 2;
      }

      MessageSender.sendMsg(new byte[]{22, 111, var2, (byte)var1});
   }

   // $FF: synthetic method
   static void lambda$initClick$2(View var0, boolean var1) {
      int var4 = var0.getId();
      byte var3 = 3;
      byte var2 = var3;
      switch (var4) {
         case 2131363385:
            break;
         case 2131363390:
            var2 = 5;
            break;
         case 2131363403:
            var2 = 4;
            break;
         case 2131363419:
            var2 = 6;
            break;
         default:
            var2 = var3;
      }

      MessageSender.sendMsg((byte)111, var2, var1);
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingDoorView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.sdv_open.setSelect(GeneralDzData.door_open);
      this.adv_close.setSelect(GeneralDzData.door_unlock);
      this.ssv_auto_lock.setSelect(GeneralDzData.door_auto_lock);
      this.ssv_intelligence.setSelect(GeneralDzData.door_intelligence);
      this.ssv_boot.setSelect(GeneralDzData.door_boot);
      this.ssv_voice.setSelect(GeneralDzData.door_voice);
   }
}
