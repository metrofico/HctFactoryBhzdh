package com.hzbhd.canbus.car_cus._446.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.hzbhd.canbus.car_cus._446.CanObserver;
import com.hzbhd.canbus.car_cus._446.view.Page1View;
import com.hzbhd.canbus.car_cus._446.view.Page2View;
import com.hzbhd.canbus.car_cus._446.view.Page3View;
import com.hzbhd.canbus.car_cus._446.view.Page4View;
import com.hzbhd.canbus.car_cus._446.view.TopView;

public class WmCarSettingActivity extends Activity {
   private TopView mode1;
   private TopView mode2;
   private TopView mode3;
   private TopView mode4;
   private Page1View p1;
   private Page2View p2;
   private Page3View p3;
   private Page4View p4;

   private void Mode1TurnOff() {
      this.mode1.turnOff(2131231552, this.getString(2131766083), 2131231564);
   }

   private void Mode1TurnOn() {
      this.mode1.turnOn(2131231553, this.getString(2131766083), 2131231565);
   }

   private void Mode2TurnOff() {
      this.mode2.turnOff(2131231557, this.getString(2131766084), 2131231564);
   }

   private void Mode2TurnOn() {
      this.mode2.turnOn(2131231558, this.getString(2131766084), 2131231565);
   }

   private void Mode3TurnOff() {
      this.mode3.turnOff(2131231550, this.getString(2131766085), 2131231564);
   }

   private void Mode3TurnOn() {
      this.mode3.turnOn(2131231551, this.getString(2131766085), 2131231565);
   }

   private void Mode4TurnOff() {
      this.mode4.turnOff(2131231560, this.getString(2131766048), 2131231564);
   }

   private void Mode4TurnOn() {
      this.mode4.turnOn(2131231561, this.getString(2131766048), 2131231565);
   }

   private void SelectMode1() {
      this.Mode1TurnOn();
      this.Mode2TurnOff();
      this.Mode3TurnOff();
      this.Mode4TurnOff();
      this.p1.setVisibility(0);
      this.p2.setVisibility(8);
      this.p3.setVisibility(8);
      this.p4.setVisibility(8);
   }

   private void SelectMode2() {
      this.Mode1TurnOff();
      this.Mode2TurnOn();
      this.Mode3TurnOff();
      this.Mode4TurnOff();
      this.p1.setVisibility(8);
      this.p2.setVisibility(0);
      this.p3.setVisibility(8);
      this.p4.setVisibility(8);
   }

   private void SelectMode3() {
      this.Mode1TurnOff();
      this.Mode2TurnOff();
      this.Mode3TurnOn();
      this.Mode4TurnOff();
      this.p1.setVisibility(8);
      this.p2.setVisibility(8);
      this.p3.setVisibility(0);
      this.p4.setVisibility(8);
   }

   private void SelectMode4() {
      this.Mode1TurnOff();
      this.Mode2TurnOff();
      this.Mode3TurnOff();
      this.Mode4TurnOn();
      this.p1.setVisibility(8);
      this.p2.setVisibility(8);
      this.p3.setVisibility(8);
      this.p4.setVisibility(0);
   }

   private void initView() {
      this.mode1 = (TopView)this.findViewById(2131362860);
      this.mode2 = (TopView)this.findViewById(2131362861);
      this.mode3 = (TopView)this.findViewById(2131362862);
      this.mode4 = (TopView)this.findViewById(2131362863);
      this.p1 = (Page1View)this.findViewById(2131362934);
      this.p2 = (Page2View)this.findViewById(2131362944);
      this.p3 = (Page3View)this.findViewById(2131362945);
      this.p4 = (Page4View)this.findViewById(2131362946);
      this.mode1.setOnClickListener(new View.OnClickListener(this) {
         final WmCarSettingActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.SelectMode1();
         }
      });
      this.mode2.setOnClickListener(new View.OnClickListener(this) {
         final WmCarSettingActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.SelectMode2();
         }
      });
      this.mode3.setOnClickListener(new View.OnClickListener(this) {
         final WmCarSettingActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.SelectMode3();
         }
      });
      this.mode4.setOnClickListener(new View.OnClickListener(this) {
         final WmCarSettingActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.SelectMode4();
         }
      });
      this.SelectMode1();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558702);
      this.initView();
   }

   protected void onDestroy() {
      super.onDestroy();
      CanObserver.getInstance().unRegister(this.p1);
      CanObserver.getInstance().unRegister(this.p2);
      CanObserver.getInstance().unRegister(this.p3);
   }

   protected void onResume() {
      super.onResume();
      CanObserver.getInstance().register(this.p1);
      CanObserver.getInstance().register(this.p2);
      CanObserver.getInstance().register(this.p3);
   }
}
