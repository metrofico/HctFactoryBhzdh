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
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarSettingUnitView extends RelativeLayout {
   private static final int Interval = 1;
   private static final String UNIT_LANGUAGE = "_283_language";
   private ExecutorService executorService;
   private List listConsumption;
   private List listDistance;
   private List listElectric;
   private List listLanguage;
   private List listPressure;
   private List listSpeed;
   private List listTemperature;
   private List listVolume;
   private Context mContext;
   private View mView;
   private SettingDialogView sdv_consumption;
   private SettingDialogView sdv_distance;
   private SettingDialogView sdv_electric;
   private SettingDialogView sdv_language;
   private SettingDialogView sdv_pressure;
   private SettingDialogView sdv_speed;
   private SettingDialogView sdv_temperature;
   private SettingDialogView sdv_volume;

   public CarSettingUnitView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingUnitView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingUnitView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.executorService = Executors.newSingleThreadExecutor();
      this.listDistance = new ArrayList();
      this.listSpeed = new ArrayList();
      this.listTemperature = new ArrayList();
      this.listVolume = new ArrayList();
      this.listConsumption = new ArrayList();
      this.listPressure = new ArrayList();
      this.listElectric = new ArrayList();
      this.listLanguage = new ArrayList();
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558451, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new CarSettingUnitView$$ExternalSyntheticLambda0(this), 1000L);
   }

   private void initClick() {
      this.sdv_language.setOnItemClickListener(new SettingDialogView.OnItemClickListener(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1, int var2) {
            MessageSender.sendMsg(new byte[]{22, -102, 1, (byte)(var2 + 1)});
            SharePreUtil.setStringValue(this.this$0.mContext, "_283_language", ((SettingDialogBean)this.this$0.listLanguage.get(var2)).getValue());
            this.this$0.sdv_language.setSelect(var2);
         }
      });
      SettingDialogView.OnItemClickListener var1 = new SettingDialogView.OnItemClickListener(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1, int var2) {
            byte var3;
            switch (var1.getId()) {
               case 2131363266:
                  var3 = 5;
                  break;
               case 2131363268:
               default:
                  var3 = 1;
                  break;
               case 2131363270:
                  var3 = 7;
                  break;
               case 2131363278:
                  var3 = 6;
                  break;
               case 2131363279:
                  var3 = 2;
                  break;
               case 2131363281:
                  var3 = 3;
                  break;
               case 2131363285:
                  var3 = 4;
            }

            MessageSender.sendMsg(new byte[]{22, -54, var3, (byte)(var2 + 1)});
         }
      };
      this.sdv_distance.setOnItemClickListener(var1);
      this.sdv_speed.setOnItemClickListener(var1);
      this.sdv_temperature.setOnItemClickListener(var1);
      this.sdv_volume.setOnItemClickListener(var1);
      this.sdv_consumption.setOnItemClickListener(var1);
      this.sdv_pressure.setOnItemClickListener(var1);
      this.sdv_electric.setOnItemClickListener(var1);
   }

   private void initView() {
      this.sdv_distance = (SettingDialogView)this.mView.findViewById(2131363268);
      this.sdv_speed = (SettingDialogView)this.mView.findViewById(2131363279);
      this.sdv_temperature = (SettingDialogView)this.mView.findViewById(2131363281);
      this.sdv_volume = (SettingDialogView)this.mView.findViewById(2131363285);
      this.sdv_consumption = (SettingDialogView)this.mView.findViewById(2131363266);
      this.sdv_pressure = (SettingDialogView)this.mView.findViewById(2131363278);
      this.sdv_electric = (SettingDialogView)this.mView.findViewById(2131363270);
      this.sdv_language = (SettingDialogView)this.mView.findViewById(2131363272);
      this.executorService.execute(new Runnable(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listDistance.add(new SettingDialogBean(this.this$0.mContext.getString(2131760791)));
            this.this$0.listDistance.add(new SettingDialogBean(this.this$0.mContext.getString(2131760792)));
            this.this$0.sdv_distance.setListFirst(this.this$0.listDistance);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listSpeed.add(new SettingDialogBean(this.this$0.mContext.getString(2131760786)));
            this.this$0.listSpeed.add(new SettingDialogBean(this.this$0.mContext.getString(2131760793)));
            this.this$0.sdv_speed.setListFirst(this.this$0.listSpeed);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listTemperature.add(new SettingDialogBean(this.this$0.mContext.getString(2131760753)));
            this.this$0.listTemperature.add(new SettingDialogBean(this.this$0.mContext.getString(2131760629)));
            this.this$0.sdv_temperature.setListFirst(this.this$0.listTemperature);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listVolume.add(new SettingDialogBean(this.this$0.mContext.getString(2131760794)));
            this.this$0.listVolume.add(new SettingDialogBean(this.this$0.mContext.getString(2131760795)));
            this.this$0.listVolume.add(new SettingDialogBean(this.this$0.mContext.getString(2131760796)));
            this.this$0.sdv_volume.setListFirst(this.this$0.listVolume);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listConsumption.add(new SettingDialogBean(this.this$0.mContext.getString(2131760797)));
            this.this$0.listConsumption.add(new SettingDialogBean(this.this$0.mContext.getString(2131760798)));
            this.this$0.listConsumption.add(new SettingDialogBean(this.this$0.mContext.getString(2131760787)));
            this.this$0.listConsumption.add(new SettingDialogBean(this.this$0.mContext.getString(2131760788)));
            this.this$0.sdv_consumption.setListFirst(this.this$0.listConsumption);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listPressure.add(new SettingDialogBean(this.this$0.mContext.getString(2131760775)));
            this.this$0.listPressure.add(new SettingDialogBean(this.this$0.mContext.getString(2131760776)));
            this.this$0.listPressure.add(new SettingDialogBean(this.this$0.mContext.getString(2131760777)));
            this.this$0.sdv_pressure.setListFirst(this.this$0.listPressure);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listElectric.add(new SettingDialogBean(this.this$0.mContext.getString(2131760789)));
            this.this$0.listElectric.add(new SettingDialogBean(this.this$0.mContext.getString(2131760790)));
            this.this$0.sdv_electric.setListFirst(this.this$0.listElectric);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingUnitView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listLanguage.add(new SettingDialogBean(this.this$0.mContext.getString(2131760663)));
            this.this$0.listLanguage.add(new SettingDialogBean(this.this$0.mContext.getString(2131760662)));
            String var1 = SharePreUtil.getStringValue(this.this$0.mContext, "_283_language", this.this$0.mContext.getString(2131760663));
            this.this$0.sdv_language.setList(this.this$0.listLanguage);
            this.this$0.sdv_language.setSelectOnValue(var1);
         }
      });
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingUnitView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.sdv_distance.setSelect(1 - GeneralDzData.unit_distance);
      this.sdv_speed.setSelect(1 - GeneralDzData.unit_speed);
      this.sdv_temperature.setSelect(1 - GeneralDzData.unit_temperature);
      this.sdv_pressure.setSelect(GeneralDzData.unit_pressure);
      this.sdv_electric.setSelect(GeneralDzData.unit_electric);
      this.sdv_volume.setSelect(GeneralDzData.unit_volume);
      this.sdv_consumption.setSelect(GeneralDzData.unit_consumption);
   }
}
