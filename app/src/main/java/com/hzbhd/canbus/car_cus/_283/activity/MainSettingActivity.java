package com.hzbhd.canbus.car_cus._283.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingCombinationView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingDoorView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingPAView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingParkingView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingRearView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingRevertView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTyreView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUpKeepView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.HybridView;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.ArrayList;
import java.util.List;

public class MainSettingActivity extends AbstractBaseActivity implements View.OnClickListener {
   public static final String CARSETTING_BUNDLE_WHAT = "_283_CarSetting_what";
   public static final int CARSETTING_CONBINATION = 2;
   public static final int CARSETTING_DOOR = 3;
   public static final int CARSETTING_HYBRID = 13;
   public static final int CARSETTING_LIGHT = 4;
   public static final int CARSETTING_NOW = 1;
   public static final int CARSETTING_PA = 6;
   public static final int CARSETTING_PARKING = 5;
   public static final int CARSETTING_REAR = 7;
   public static final int CARSETTING_REVERT = 8;
   public static final int CARSETTING_TIME = 9;
   public static final int CARSETTING_TYRE = 10;
   public static final int CARSETTING_UNIT = 11;
   public static final int CARSETTING_UPKEEP = 12;
   private boolean isSecondSetting = false;
   private ImageView iv_back;
   private List lists = new ArrayList();
   private CarSettingCombinationView mCarSettingCombinationView;
   private CarSettingDoorView mCarSettingDoorView;
   private CarSettingLightView mCarSettingLightView;
   private CarSettingPAView mCarSettingPAView;
   private CarSettingParkingView mCarSettingParkingView;
   private CarSettingRearView mCarSettingRearView;
   private CarSettingRevertView mCarSettingRevertView;
   private CarSettingTimeView mCarSettingTimeView;
   private CarSettingTyreView mCarSettingTyreView;
   private CarSettingUnitView mCarSettingUnitView;
   private CarSettingUpKeepView mCarSettingUpKeepView;
   private HybridView mHybridView;
   private int mWhat;
   private NestedScrollView mainSettingScrollView;
   private SettingView reverse_setting;
   private SettingDialogView sdv_esc;
   private SettingView sv_carLight;
   private SettingView sv_combination;
   private SettingView sv_door;
   private SettingView sv_hybrid;
   private SettingView sv_pa;
   private SettingView sv_parking;
   private SettingView sv_rear;
   private SettingView sv_revert;
   private SettingView sv_time;
   private SettingView sv_tyre;
   private SettingView sv_unit;
   private SettingView sv_upkeep;
   private TextView tv_title;

   private void allViewGone() {
      this.isSecondSetting = false;
      this.mCarSettingCombinationView.setVisibility(8);
      this.mCarSettingDoorView.setVisibility(8);
      this.mCarSettingLightView.setVisibility(8);
      this.mCarSettingParkingView.setVisibility(8);
      this.mCarSettingPAView.setVisibility(8);
      this.mCarSettingRearView.setVisibility(8);
      this.mCarSettingRevertView.setVisibility(8);
      this.mCarSettingTimeView.setVisibility(8);
      this.mCarSettingTyreView.setVisibility(8);
      this.mCarSettingUnitView.setVisibility(8);
      this.mCarSettingUpKeepView.setVisibility(8);
      this.mHybridView.setVisibility(8);
      this.tv_title.setText(2131760450);
      this.mainSettingScrollView.setVisibility(0);
   }

   private void initView() {
      this.mainSettingScrollView = (NestedScrollView)this.findViewById(2131362827);
      this.iv_back = (ImageView)this.findViewById(2131362534);
      this.sdv_esc = (SettingDialogView)this.findViewById(2131363271);
      this.sv_carLight = (SettingView)this.findViewById(2131363444);
      this.sv_tyre = (SettingView)this.findViewById(2131363470);
      this.sv_pa = (SettingView)this.findViewById(2131363453);
      this.sv_parking = (SettingView)this.findViewById(2131363454);
      this.sv_rear = (SettingView)this.findViewById(2131363455);
      this.sv_door = (SettingView)this.findViewById(2131363449);
      this.sv_combination = (SettingView)this.findViewById(2131363447);
      this.sv_time = (SettingView)this.findViewById(2131363468);
      this.sv_unit = (SettingView)this.findViewById(2131363471);
      this.sv_upkeep = (SettingView)this.findViewById(2131363472);
      this.sv_revert = (SettingView)this.findViewById(2131363458);
      this.reverse_setting = (SettingView)this.findViewById(2131363140);
      this.sv_hybrid = (SettingView)this.findViewById(2131363452);
      this.tv_title = (TextView)this.findViewById(2131363710);
      this.mCarSettingCombinationView = (CarSettingCombinationView)this.findViewById(2131362098);
      this.mCarSettingDoorView = (CarSettingDoorView)this.findViewById(2131362099);
      this.mCarSettingLightView = (CarSettingLightView)this.findViewById(2131362100);
      this.mCarSettingParkingView = (CarSettingParkingView)this.findViewById(2131362102);
      this.mCarSettingPAView = (CarSettingPAView)this.findViewById(2131362101);
      this.mCarSettingRearView = (CarSettingRearView)this.findViewById(2131362103);
      this.mCarSettingRevertView = (CarSettingRevertView)this.findViewById(2131362104);
      this.mCarSettingTimeView = (CarSettingTimeView)this.findViewById(2131362105);
      this.mCarSettingTyreView = (CarSettingTyreView)this.findViewById(2131362106);
      this.mCarSettingUnitView = (CarSettingUnitView)this.findViewById(2131362107);
      this.mCarSettingUpKeepView = (CarSettingUpKeepView)this.findViewById(2131362108);
      this.mHybridView = (HybridView)this.findViewById(2131362379);
      if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
         this.sv_hybrid.setVisibility(0);
      } else {
         this.sv_hybrid.setVisibility(8);
      }

      this.iv_back.setOnClickListener(this);
      this.sv_carLight.setOnClickListener(this);
      this.sv_revert.setOnClickListener(this);
      this.reverse_setting.setOnClickListener(this);
      this.sv_tyre.setOnClickListener(this);
      this.sv_pa.setOnClickListener(this);
      this.sv_parking.setOnClickListener(this);
      this.sv_rear.setOnClickListener(this);
      this.sv_door.setOnClickListener(this);
      this.sv_combination.setOnClickListener(this);
      this.sv_time.setOnClickListener(this);
      this.sv_unit.setOnClickListener(this);
      this.sv_upkeep.setOnClickListener(this);
      this.sv_hybrid.setOnClickListener(this);
      this.lists.add(new SettingDialogBean(this.getString(2131760625)));
      this.lists.add(new SettingDialogBean(this.getString(2131760626)));
      this.lists.add(new SettingDialogBean(this.getString(2131760627)));
      ((SettingDialogBean)this.lists.get(0)).setSelect(true);
      this.sdv_esc.setList(this.lists);
      this.sdv_esc.setOnItemClickListener(new MainSettingActivity$$ExternalSyntheticLambda0());
      this.sdv_esc.setSelect(GeneralDzData.esc);
   }

   // $FF: synthetic method
   static void lambda$initView$0(View var0, int var1) {
      MessageSender.sendMsg(new byte[]{22, -118, 3, (byte)var1});
   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362534:
            if (this.isSecondSetting) {
               this.allViewGone();
            } else {
               this.onBackPressed();
            }
            break;
         case 2131363140:
            this.startActivity(new Intent(this, DyReverseSettingActivity.class));
            break;
         case 2131363444:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760745);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingLightView.setVisibility(0);
            break;
         case 2131363447:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760750);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingCombinationView.setVisibility(0);
            break;
         case 2131363449:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760749);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingDoorView.setVisibility(0);
            break;
         case 2131363452:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760752);
            this.mainSettingScrollView.setVisibility(8);
            this.mHybridView.setVisibility(0);
            break;
         case 2131363453:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760746);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingPAView.setVisibility(0);
            break;
         case 2131363454:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760747);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingParkingView.setVisibility(0);
            break;
         case 2131363455:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760748);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingRearView.setVisibility(0);
            break;
         case 2131363458:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760738);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingRevertView.setVisibility(0);
            break;
         case 2131363468:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760751);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingTimeView.setVisibility(0);
            break;
         case 2131363470:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760744);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingTyreView.setVisibility(0);
            break;
         case 2131363471:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760736);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingUnitView.setVisibility(0);
            break;
         case 2131363472:
            this.isSecondSetting = true;
            this.tv_title.setText(2131760737);
            this.mainSettingScrollView.setVisibility(8);
            this.mCarSettingUpKeepView.setVisibility(0);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558464);
      this.initView();
      this.getWindow().setFlags(1024, 1024);
   }

   public boolean onKeyUp(int var1, KeyEvent var2) {
      if (var1 == 4 && this.isSecondSetting) {
         this.allViewGone();
         return false;
      } else {
         return super.onKeyUp(var1, var2);
      }
   }

   public void refreshUi(Bundle var1) {
      if (var1 != null) {
         int var2 = var1.getInt("_283_CarSetting_what");
         this.mWhat = var2;
         switch (var2) {
            case 1:
               this.sdv_esc.setSelect(GeneralDzData.esc);
               break;
            case 2:
               this.mCarSettingCombinationView.refreshUi();
               break;
            case 3:
               this.mCarSettingDoorView.refreshUi();
               break;
            case 4:
               this.mCarSettingLightView.refreshUi();
               break;
            case 5:
               this.mCarSettingParkingView.refreshUi();
               break;
            case 6:
               this.mCarSettingPAView.refreshUi();
               break;
            case 7:
               this.mCarSettingRearView.refreshUi();
               break;
            case 8:
               this.mCarSettingRevertView.refreshUi();
               break;
            case 9:
               this.mCarSettingTimeView.refreshUi();
               break;
            case 10:
               this.mCarSettingTyreView.refreshUi();
               break;
            case 11:
               this.mCarSettingUnitView.refreshUi();
               break;
            case 12:
               this.mCarSettingUpKeepView.refreshUi();
               break;
            case 13:
               this.mHybridView.refreshUi();
         }

      }
   }
}
