package com.hzbhd.canbus.car_cus._304.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._304.bean.MainPageEntity;
import com.hzbhd.canbus.car_cus._304.view.MainView;
import com.hzbhd.canbus.car_cus._304.view.VehicleAssistanceView;
import com.hzbhd.canbus.car_cus._304.view.VehicleDiagnosisView;
import com.hzbhd.canbus.car_cus._304.view.VehicleEnergyView;
import com.hzbhd.canbus.car_cus._304.view.VehicleInfoView;
import com.hzbhd.canbus.car_cus._304.view.VehiclePanoramicView;
import com.hzbhd.canbus.car_cus._304.view.VehicleSettingsView;
import java.util.ArrayList;

public class MainActivity extends AbstractBaseActivity {
   private final String TAG = "_304_MainActivity";
   private MainView mMainView;

   private void findViews() {
      this.mMainView = (MainView)this.findViewById(2131362830);
   }

   private void initViews() {
      RelativeLayout.LayoutParams var4 = new RelativeLayout.LayoutParams(-1, -1);
      VehicleDiagnosisView var1 = new VehicleDiagnosisView(this);
      VehicleInfoView var7 = new VehicleInfoView(this);
      VehicleEnergyView var5 = new VehicleEnergyView(this);
      VehicleAssistanceView var2 = new VehicleAssistanceView(this);
      VehicleSettingsView var6 = new VehicleSettingsView(this, this);
      VehiclePanoramicView var3 = new VehiclePanoramicView(this);
      var1.setLayoutParams(var4);
      var7.setLayoutParams(var4);
      var5.setLayoutParams(var4);
      var2.setLayoutParams(var4);
      var6.setLayoutParams(var4);
      ArrayList var8 = new ArrayList();
      var8.add(new MainPageEntity(this.getString(2131761979), var1));
      var8.add(new MainPageEntity(this.getString(2131761978), var2));
      var8.add(new MainPageEntity(this.getString(2131761982), var3));
      this.mMainView.initView(this, var8);
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558501);
      this.findViews();
      this.initViews();
   }

   protected void onResume() {
      super.onResume();
      this.mMainView.reset();
   }

   public void refreshUi(Bundle var1) {
   }
}
