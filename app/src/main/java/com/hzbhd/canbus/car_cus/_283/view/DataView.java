package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.utils.Utils;
import com.hzbhd.canbus.util.SharePreUtil;

public class DataView extends RelativeLayout {
   private CarImageView carImageView;
   private Context mContext;
   private View mView;
   private ImageView rediduImage;
   private TextView tv_mileage;
   private TextView tv_oil;
   private TextView tv_residu;
   private TextView tv_speed;
   private TextView tv_time;

   public DataView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558455, this);
      this.initView();
   }

   private void initView() {
      this.tv_time = (TextView)this.mView.findViewById(2131363707);
      this.tv_speed = (TextView)this.mView.findViewById(2131363703);
      this.tv_oil = (TextView)this.mView.findViewById(2131363656);
      this.tv_residu = (TextView)this.mView.findViewById(2131363676);
      this.tv_mileage = (TextView)this.mView.findViewById(2131363653);
      this.carImageView = (CarImageView)this.mView.findViewById(2131362097);
      this.rediduImage = (ImageView)this.mView.findViewById(2131363079);
   }

   private void setTextRed(String var1) {
      try {
         if (Integer.parseInt(var1) <= 40) {
            this.tv_residu.setTextColor(-65536);
            this.rediduImage.setImageResource(2131233318);
         } else {
            this.tv_residu.setTextColor(-1);
            this.rediduImage.setImageResource(2131233317);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public String getDataString(String var1, String var2) {
      if (!var2.equals("0")) {
         SharePreUtil.setStringValue(this.mContext, var1, var2);
         return var2;
      } else {
         return SharePreUtil.getStringValue(this.mContext, var1, var2);
      }
   }

   public void refreshUi(int var1) {
      String var3;
      String var4;
      String var5;
      String var6;
      String var7;
      label18: {
         var6 = "";
         String var2;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var5 = "";
                  var3 = "";
                  var4 = var3;
                  var7 = var3;
                  break label18;
               }

               var4 = this.getDataString("refuel_oil", GeneralDzData.refuel_oil);
               var6 = this.getDataString("refuel_residu", GeneralDzData.refuel_residu);
               var2 = this.getDataString("refuel_mileage", GeneralDzData.refuel_mileage);
               var5 = this.getDataString("refuel_time", GeneralDzData.refuel_time);
               var3 = this.getDataString("refuel_speed", GeneralDzData.refuel_speed);
            } else {
               var4 = this.getDataString("longtime_oil", GeneralDzData.longtime_oil);
               var6 = this.getDataString("longtime_residu", GeneralDzData.longtime_residu);
               var2 = this.getDataString("longtime_mileage", GeneralDzData.longtime_mileage);
               var5 = this.getDataString("longtime_time", GeneralDzData.longtime_time);
               var3 = this.getDataString("longtime_speed", GeneralDzData.longtime_speed);
            }
         } else {
            var4 = this.getDataString("launch_oil", GeneralDzData.launch_oil);
            var6 = this.getDataString("launch_residu", GeneralDzData.launch_residu);
            var2 = this.getDataString("launch_mileage", GeneralDzData.launch_mileage);
            var5 = this.getDataString("launch_time", GeneralDzData.launch_time);
            var3 = this.getDataString("launch_speed", GeneralDzData.launch_speed);
         }

         String var8 = var5;
         var5 = var4;
         var4 = var3;
         var7 = var6;
         var6 = var8;
         var3 = var2;
      }

      this.tv_time.setText(var6 + "  min");
      this.tv_speed.setText(var4 + "  km/h");
      this.tv_oil.setText(var5 + "  L/100km");
      this.tv_mileage.setText(var3 + "  km");
      this.tv_residu.setText(var7 + "  km");
      Utils.setCarResidu(this.carImageView, var7);
      this.setTextRed(var7);
   }
}
