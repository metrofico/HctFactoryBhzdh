package com.hzbhd.canbus.car_cus._291.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._291.GeneralDzPQData;

public class DataView extends RelativeLayout {
   private View mView;
   private TextView tv_distance;
   private TextView tv_fuel;
   private TextView tv_light;
   private TextView tv_oil;
   private TextView tv_rev;
   private TextView tv_v;

   public DataView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mView = LayoutInflater.from(var1).inflate(2131558492, this);
      this.initView();
   }

   private void initView() {
      this.tv_rev = (TextView)this.mView.findViewById(2131363677);
      this.tv_fuel = (TextView)this.mView.findViewById(2131363624);
      this.tv_distance = (TextView)this.mView.findViewById(2131363615);
      this.tv_oil = (TextView)this.mView.findViewById(2131363656);
      this.tv_v = (TextView)this.mView.findViewById(2131363713);
      this.tv_light = (TextView)this.mView.findViewById(2131363647);
   }

   public void refreshUi() {
      this.tv_rev.setText(GeneralDzPQData.vehicleRev);
      this.tv_fuel.setText(GeneralDzPQData.vehicleFuel);
      this.tv_distance.setText(GeneralDzPQData.vehicleDistance);
      this.tv_oil.setText(GeneralDzPQData.vehicleOil);
      this.tv_v.setText(GeneralDzPQData.vehicleV);
      this.tv_light.setText(GeneralDzPQData.vehicleLight);
   }
}
