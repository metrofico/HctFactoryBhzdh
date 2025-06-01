package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;

public class CarSettingUpKeepView extends RelativeLayout {
   private Context mContext;
   private View mView;
   private TextView tv_Imei;
   private TextView tv_checkCar;
   private TextView tv_checkOil;
   private int[] units;

   public CarSettingUpKeepView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingUpKeepView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingUpKeepView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.units = new int[]{2131760791, 2131760792};
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558452, this, true);
      this.initView();
      (new Handler()).postDelayed(new CarSettingUpKeepView$$ExternalSyntheticLambda0(this), 1000L);
   }

   private void initView() {
      this.tv_Imei = (TextView)this.mView.findViewById(2131363583);
      this.tv_checkCar = (TextView)this.mView.findViewById(2131363603);
      this.tv_checkOil = (TextView)this.mView.findViewById(2131363604);
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingUpKeepView() {
      this.refreshUi();
   }

   public void refreshUi() {
      String var6 = this.mContext.getString(this.units[GeneralDzData.upkeep_unit]);
      int var1 = GeneralDzData.upkeep_car_day_mark;
      String var5 = "-";
      String var2;
      if (var1 == 0) {
         var2 = "-";
      } else if (GeneralDzData.upkeep_car_day_mark == 1) {
         var2 = GeneralDzData.upkeep_car_day + this.mContext.getString(2131760605);
      } else if (GeneralDzData.upkeep_car_day_mark == 2) {
         var2 = this.mContext.getString(2131760723) + GeneralDzData.upkeep_car_day + this.mContext.getString(2131760605);
      } else {
         var2 = "";
      }

      String var3;
      if (GeneralDzData.upkeep_car_distance_mark == 0) {
         var3 = "-";
      } else if (GeneralDzData.upkeep_car_distance_mark == 1) {
         var3 = this.mContext.getString(2131760714) + GeneralDzData.upkeep_car_distance + var6;
      } else if (GeneralDzData.upkeep_car_distance_mark == 2) {
         var3 = this.mContext.getString(2131760714) + this.mContext.getString(2131760723) + GeneralDzData.upkeep_car_distance + var6;
      } else {
         var3 = "";
      }

      String var4;
      if (GeneralDzData.upkeep_oil_day_mark == 0) {
         var4 = "-";
      } else if (GeneralDzData.upkeep_oil_day_mark == 1) {
         var4 = GeneralDzData.upkeep_oil_day + this.mContext.getString(2131760605);
      } else if (GeneralDzData.upkeep_oil_day_mark == 2) {
         var4 = this.mContext.getString(2131760723) + GeneralDzData.upkeep_oil_day + this.mContext.getString(2131760605);
      } else {
         var4 = "";
      }

      if (GeneralDzData.upkeep_oil_distance_mark != 0) {
         if (GeneralDzData.upkeep_oil_distance_mark == 1) {
            var5 = this.mContext.getString(2131760714) + GeneralDzData.upkeep_oil_distance + var6;
         } else if (GeneralDzData.upkeep_oil_distance_mark == 2) {
            var5 = this.mContext.getString(2131760714) + this.mContext.getString(2131760723) + GeneralDzData.upkeep_oil_distance + var6;
         } else {
            var5 = "";
         }
      }

      this.tv_Imei.setText(GeneralDzData.car_imei);
      this.tv_checkCar.setText(var2 + var3);
      this.tv_checkOil.setText(var4 + var5);
   }
}
