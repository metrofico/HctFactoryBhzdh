package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingProgressView;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarSettingParkingView extends RelativeLayout {
   private ExecutorService executorService;
   private boolean isSeekbar;
   private List listParking;
   private Context mContext;
   private Handler mHandler;
   private View mView;
   private SettingDialogView sdv_parking;
   private SettingProgressView spv_front_tone;
   private SettingProgressView spv_front_volume;
   private SettingProgressView spv_rear_tone;
   private SettingProgressView spv_rear_volume;
   private SettingSelectView ssv_auto;
   private SettingSelectView ssv_off_road;
   private SettingSelectView ssv_out;
   private SettingSelectView ssv_parking_function;
   private SettingSelectView ssv_parking_radar_voice;
   private SettingSelectView ssv_parking_switch;

   public CarSettingParkingView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingParkingView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingParkingView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.listParking = new ArrayList();
      this.executorService = Executors.newSingleThreadExecutor();
      this.isSeekbar = false;
      this.mHandler = new Handler(this, Looper.getMainLooper()) {
         final CarSettingParkingView this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 0) {
               this.this$0.isSeekbar = false;
            }

         }
      };
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558446, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new CarSettingParkingView$$ExternalSyntheticLambda0(this), 1000L);
   }

   private void initClick() {
      CarSettingParkingView$$ExternalSyntheticLambda1 var1 = new CarSettingParkingView$$ExternalSyntheticLambda1(this);
      CarSettingParkingView$$ExternalSyntheticLambda2 var2 = new CarSettingParkingView$$ExternalSyntheticLambda2(this);
      this.spv_front_volume.setOnItemClickListener(var1);
      this.spv_front_tone.setOnItemClickListener(var1);
      this.spv_rear_volume.setOnItemClickListener(var1);
      this.spv_rear_tone.setOnItemClickListener(var1);
      this.ssv_parking_function.setOnItemClickListener(var2);
      this.ssv_parking_switch.setOnItemClickListener(var2);
      this.ssv_auto.setOnItemClickListener(var2);
      this.ssv_out.setOnItemClickListener(var2);
      this.ssv_parking_radar_voice.setOnItemClickListener(var2);
      this.ssv_off_road.setOnItemClickListener(var2);
      this.sdv_parking.setOnItemClickListener(new SettingDialogView.OnItemClickListener(this) {
         final CarSettingParkingView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1, int var2) {
            MessageSender.sendMsg(new byte[]{22, 74, 7, (byte)(2 - var2)});
         }
      });
   }

   private void initView() {
      this.sdv_parking = (SettingDialogView)this.mView.findViewById(2131363277);
      this.spv_front_volume = (SettingProgressView)this.mView.findViewById(2131363355);
      this.spv_front_tone = (SettingProgressView)this.mView.findViewById(2131363354);
      this.spv_rear_volume = (SettingProgressView)this.mView.findViewById(2131363359);
      this.spv_rear_tone = (SettingProgressView)this.mView.findViewById(2131363358);
      this.ssv_parking_function = (SettingSelectView)this.mView.findViewById(2131363411);
      this.ssv_parking_switch = (SettingSelectView)this.mView.findViewById(2131363413);
      this.ssv_parking_radar_voice = (SettingSelectView)this.mView.findViewById(2131363412);
      this.ssv_out = (SettingSelectView)this.mView.findViewById(2131363410);
      this.ssv_auto = (SettingSelectView)this.mView.findViewById(2131363382);
      this.ssv_off_road = (SettingSelectView)this.mView.findViewById(2131363408);
      this.executorService.execute(new Runnable(this) {
         final CarSettingParkingView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listParking.add(new SettingDialogBean(this.this$0.mContext.getString(2131760720)));
            this.this$0.listParking.add(new SettingDialogBean(this.this$0.mContext.getString(2131760721)));
            this.this$0.listParking.add(new SettingDialogBean(this.this$0.mContext.getString(2131760722)));
            this.this$0.sdv_parking.setListFirst(this.this$0.listParking);
         }
      });
      this.setProgress(this.spv_front_volume);
      this.setProgress(this.spv_front_tone);
      this.setProgress(this.spv_rear_volume);
      this.setProgress(this.spv_rear_tone);
      this.spv_front_volume.setBigAndSmallValueText("1.0", "");
      this.spv_front_tone.setBigAndSmallValueText("1.0", "");
      this.spv_rear_volume.setBigAndSmallValueText("1.0", "");
      this.spv_rear_tone.setBigAndSmallValueText("1.0", "");
   }

   private void sendMsg(byte var1, boolean var2) {
      MessageSender.sendMsg((byte)74, var1, var2);
   }

   private void setProgress(SettingProgressView var1) {
      var1.setMaxAndMinProgress(1, 9);
   }

   // $FF: synthetic method
   void lambda$initClick$1$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingParkingView(View var1, int var2) {
      this.isSeekbar = true;
      byte var3;
      switch (var1.getId()) {
         case 2131363354:
            var3 = 3;
            break;
         case 2131363355:
         case 2131363356:
         case 2131363357:
         default:
            var3 = 2;
            break;
         case 2131363358:
            var3 = 5;
            break;
         case 2131363359:
            var3 = 4;
      }

      MessageSender.sendMsg(new byte[]{22, 74, var3, (byte)var2});
   }

   // $FF: synthetic method
   void lambda$initClick$2$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingParkingView(View var1, boolean var2) {
      int var5 = var1.getId();
      byte var4 = 1;
      byte var3 = var4;
      if (var5 != 2131363382) {
         if (var5 != 2131363408) {
            switch (var5) {
               case 2131363410:
                  var3 = 9;
                  break;
               case 2131363411:
                  var3 = 11;
                  break;
               case 2131363412:
                  var3 = 8;
                  break;
               case 2131363413:
                  var3 = 12;
                  break;
               default:
                  var3 = var4;
            }
         } else {
            var3 = 10;
         }
      }

      this.sendMsg(var3, var2);
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingParkingView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.sdv_parking.setSelect(GeneralDzData.parking_mode);
      this.ssv_parking_function.setSelect(GeneralDzData.parking_function);
      this.ssv_parking_switch.setSelect(GeneralDzData.parking_switch);
      this.ssv_auto.setSelect(GeneralDzData.parking_auto);
      this.ssv_out.setSelect(GeneralDzData.parking_out);
      this.ssv_parking_radar_voice.setSelect(GeneralDzData.parking_radar_voice);
      this.ssv_off_road.setSelect(GeneralDzData.parking_off_road);
      if (!this.isSeekbar) {
         this.spv_front_volume.setValueProgress(GeneralDzData.parking_front_volume);
         this.spv_front_tone.setValueProgress(GeneralDzData.parking_front_tone);
         this.spv_rear_volume.setValueProgress(GeneralDzData.parking_rear_volume);
         this.spv_rear_tone.setValueProgress(GeneralDzData.parking_rear_tone);
      } else {
         this.mHandler.removeMessages(0);
         this.mHandler.sendEmptyMessageDelayed(0, 200L);
      }

   }
}
