package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.activity.MainSettingPersonalActivity;
import com.hzbhd.canbus.util.CommUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DrivingPatternView extends RelativeLayout {
   private boolean isClick = true;
   private Context mContext;
   private ImageView mPersonSetting;
   private View mView;
   private RadioButton radioButtonComfort;
   private RadioButton radioButtonCrossCountry;
   private RadioButton radioButtonCustom;
   private RadioButton radioButtonEconomics;
   private RadioButton radioButtonSnowfield;
   private RadioButton radioButtonSport;
   private RadioButton radioButtonStandard;
   private List radioButtons = new ArrayList();
   private RadioGroup radioGroup;
   private ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

   public DrivingPatternView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558462, this);
      this.initView();
      this.initData();
   }

   private void initData() {
      this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) {
         final DrivingPatternView this$0;

         {
            this.this$0 = var1;
         }

         public void onCheckedChanged(RadioGroup var1, int var2) {
            byte var3;
            switch (var2) {
               case 2131363029:
               case 2131363033:
               default:
                  var3 = 1;
                  break;
               case 2131363030:
                  var3 = 8;
                  break;
               case 2131363031:
                  var3 = 5;
                  break;
               case 2131363032:
                  var3 = 4;
                  break;
               case 2131363034:
                  var3 = 6;
                  break;
               case 2131363035:
                  var3 = 3;
                  break;
               case 2131363036:
                  var3 = 2;
            }

            if (this.this$0.isClick) {
               byte[] var4 = new byte[]{22, -117, var3, 0, 0};
               CanbusMsgSender.sendMsg(var4);
               CommUtil.printHexString("scyscyscy:Mode DCC 单击 发 ", var4);
            }

         }
      });
   }

   private void initView() {
      this.radioGroup = (RadioGroup)this.mView.findViewById(2131363038);
      this.radioButtonComfort = (RadioButton)this.mView.findViewById(2131363029);
      this.radioButtonStandard = (RadioButton)this.mView.findViewById(2131363036);
      this.radioButtonSport = (RadioButton)this.mView.findViewById(2131363035);
      this.radioButtonEconomics = (RadioButton)this.mView.findViewById(2131363032);
      this.radioButtonCustom = (RadioButton)this.mView.findViewById(2131363031);
      this.radioButtonCrossCountry = (RadioButton)this.mView.findViewById(2131363030);
      this.radioButtonSnowfield = (RadioButton)this.mView.findViewById(2131363034);
      ImageView var1 = (ImageView)this.mView.findViewById(2131362974);
      this.mPersonSetting = var1;
      var1.setOnClickListener(new DrivingPatternView$$ExternalSyntheticLambda0(this));
      this.threadExecutor.execute(new Runnable(this) {
         final DrivingPatternView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.radioButtons.add(this.this$0.radioButtonComfort);
            this.this$0.radioButtons.add(this.this$0.radioButtonStandard);
            this.this$0.radioButtons.add(this.this$0.radioButtonSport);
            this.this$0.radioButtons.add(this.this$0.radioButtonEconomics);
            this.this$0.radioButtons.add(this.this$0.radioButtonCustom);
            this.this$0.radioButtons.add(this.this$0.radioButtonCrossCountry);
            this.this$0.radioButtons.add(this.this$0.radioButtonSnowfield);
         }
      });
   }

   private void scaleRadioButton(RadioButton var1) {
      for(int var2 = 0; var2 < this.radioButtons.size(); ++var2) {
         if (var1 == this.radioButtons.get(var2)) {
            var1.setScaleX(1.1F);
            var1.setScaleY(1.1F);
         } else {
            ((RadioButton)this.radioButtons.get(var2)).setScaleX(1.0F);
            ((RadioButton)this.radioButtons.get(var2)).setScaleY(1.0F);
         }
      }

   }

   // $FF: synthetic method
   void lambda$initView$0$com_hzbhd_canbus_car_cus__283_view_DrivingPatternView(View var1) {
      this.mContext.startActivity(new Intent(this.mContext, MainSettingPersonalActivity.class));
   }

   public void refreshUi() {
      this.isClick = false;
      RadioButton var2 = this.radioButtonComfort;
      int var1;
      if (GeneralDzData.drivingMode_comfort) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var2.setVisibility(var1);
      var2 = this.radioButtonSport;
      if (GeneralDzData.drivingMode_sport) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var2.setVisibility(var1);
      var2 = this.radioButtonEconomics;
      if (GeneralDzData.drivingMode_eco) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var2.setVisibility(var1);
      var2 = this.radioButtonCustom;
      if (GeneralDzData.drivingMode_indivdual) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var2.setVisibility(var1);
      var2 = this.radioButtonSnowfield;
      if (GeneralDzData.drivingMode_snowfield) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var2.setVisibility(var1);
      var2 = this.radioButtonCrossCountry;
      if (GeneralDzData.drivingMode_cross_country) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var2.setVisibility(var1);
      var1 = GeneralDzData.drivingMode;
      switch (var1) {
         case 1:
            this.radioGroup.check(2131363030);
            this.scaleRadioButton(this.radioButtonCrossCountry);
            break;
         case 2:
            this.radioGroup.check(2131363034);
            this.scaleRadioButton(this.radioButtonSnowfield);
            break;
         case 3:
            this.radioGroup.check(2131363031);
            this.scaleRadioButton(this.radioButtonCustom);
            break;
         case 4:
            this.radioGroup.check(2131363032);
            this.scaleRadioButton(this.radioButtonEconomics);
            break;
         case 5:
            this.radioGroup.check(2131363035);
            this.scaleRadioButton(this.radioButtonSport);
            break;
         case 6:
            this.radioGroup.check(2131363036);
            this.scaleRadioButton(this.radioButtonStandard);
            break;
         case 7:
            this.radioGroup.check(2131363029);
            this.scaleRadioButton(this.radioButtonComfort);
      }

      this.isClick = true;
      if (var1 == 3) {
         this.mPersonSetting.setVisibility(0);
      } else {
         this.mPersonSetting.setVisibility(8);
      }

   }
}
