package com.hzbhd.canbus.car_cus._291;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._291.view.BtnView;
import com.hzbhd.canbus.car_cus._291.view.WindSpeedView;
import com.hzbhd.canbus.ui_datas.GeneralAirData;

public class AirActivity extends AbstractBaseActivity {
   public static final String UPDATE_LEFT_WIND_POWER = "update_left_wind_power";
   public static final String UPDATE_RIGHT_WIND_POWER = "update_right_wind_power";
   public static final String UPDATE_WIND_POWER = "update_wind_power";
   public static final int WHAT_FINISH_THIS = 0;
   private BtnView btn_ac;
   private BtnView btn_auto;
   private BtnView btn_auto2;
   private BtnView btn_cycle;
   private BtnView btn_dual;
   private BtnView btn_max;
   private BtnView btn_max_ac;
   private BtnView btn_off_on;
   private BtnView btn_rear;
   private TextView front_left_airTextView;
   private int[] front_left_hot = new int[]{2131233266, 2131233267, 2131233268};
   private ImageView front_left_seat;
   private TextView front_right_airTextView;
   private int[] front_right_hot = new int[]{2131233269, 2131233270, 2131233271};
   private ImageView front_right_seat;
   private ImageView iv_left_foot;
   private ImageView iv_left_head;
   private ImageView iv_left_windows;
   private ImageView iv_right_foot;
   private ImageView iv_right_head;
   private ImageView iv_right_windows;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final AirActivity this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         if (var1.what == 0) {
            this.this$0.finish();
         }

      }
   };
   private WindSpeedView wsv_left_Wind;
   private WindSpeedView wsv_right_Wind;

   private int getIvVisible(boolean var1) {
      return var1 ? 0 : 8;
   }

   private void initView() {
      this.front_left_seat = (ImageView)this.findViewById(2131362269);
      this.front_right_seat = (ImageView)this.findViewById(2131362290);
      this.iv_left_foot = (ImageView)this.findViewById(2131362590);
      this.iv_left_head = (ImageView)this.findViewById(2131362593);
      this.iv_left_windows = (ImageView)this.findViewById(2131362601);
      this.iv_right_foot = (ImageView)this.findViewById(2131362622);
      this.iv_right_head = (ImageView)this.findViewById(2131362624);
      this.iv_right_windows = (ImageView)this.findViewById(2131362630);
      this.front_right_airTextView = (TextView)this.findViewById(2131362289);
      this.front_left_airTextView = (TextView)this.findViewById(2131362268);
      this.btn_off_on = (BtnView)this.findViewById(2131362035);
      this.btn_auto = (BtnView)this.findViewById(2131362006);
      this.btn_max_ac = (BtnView)this.findViewById(2131362033);
      this.btn_max = (BtnView)this.findViewById(2131362032);
      this.btn_rear = (BtnView)this.findViewById(2131362044);
      this.btn_auto2 = (BtnView)this.findViewById(2131362007);
      this.btn_dual = (BtnView)this.findViewById(2131362020);
      this.btn_ac = (BtnView)this.findViewById(2131362004);
      this.btn_cycle = (BtnView)this.findViewById(2131362017);
      this.wsv_left_Wind = (WindSpeedView)this.findViewById(2131363798);
      this.wsv_right_Wind = (WindSpeedView)this.findViewById(2131363799);
      this.btn_off_on.setEnabled(false);
      this.btn_auto.setEnabled(false);
      this.btn_max_ac.setEnabled(false);
      this.btn_max.setEnabled(false);
      this.btn_rear.setEnabled(false);
      this.btn_auto2.setEnabled(false);
      this.btn_dual.setEnabled(false);
      this.btn_ac.setEnabled(false);
      this.btn_cycle.setEnabled(false);
   }

   private void setSeat(int var1, ImageView var2, int[] var3) {
      if (var1 == 0) {
         var2.setImageDrawable((Drawable)null);
      } else if (var1 >= 1 && var1 <= 3) {
         var2.setImageDrawable(this.getDrawable(var3[var1 - 1]));
      }

   }

   private void setTimeOut() {
      String var1 = this.getIntent().getStringExtra("type");
      if (!TextUtils.isEmpty(var1) && var1.equals("SETUP")) {
         this.mHandler.removeMessages(0);
         this.mHandler.sendEmptyMessageDelayed(0, 5000L);
      }

   }

   // $FF: synthetic method
   void lambda$onCreate$0$com_hzbhd_canbus_car_cus__291_AirActivity() {
      this.refreshUi((Bundle)null);
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558487);
      this.initView();
      this.setTimeOut();
      this.mHandler.postDelayed(new AirActivity$$ExternalSyntheticLambda0(this), 500L);
   }

   public void refreshUi(Bundle var1) {
      this.setTimeOut();
      this.btn_auto.setSelect(GeneralAirData.auto);
      this.btn_max_ac.setSelect(GeneralAirData.ac_max);
      this.btn_max.setSelect(GeneralAirData.ac_max);
      this.btn_rear.setSelect(GeneralAirData.rear_defog);
      this.btn_auto2.setSelect(GeneralAirData.auto_2);
      this.btn_dual.setSelect(GeneralAirData.dual);
      this.btn_ac.setSelect(GeneralAirData.ac);
      if (GeneralAirData.in_out_auto_cycle == 1) {
         this.btn_cycle.setIcon(2131232854, 2131232854);
      } else if (GeneralAirData.in_out_auto_cycle == 3) {
         this.btn_cycle.setIcon(2131232816, 2131232816);
      } else {
         this.btn_cycle.setIcon(2131232863, 2131232863);
      }

      if (GeneralAirData.power) {
         this.btn_off_on.setIcon(2131230868, 2131230867);
      } else {
         this.btn_off_on.setIcon(2131230866, 2131230865);
      }

      this.front_right_airTextView.setText(GeneralAirData.front_right_temperature);
      this.front_left_airTextView.setText(GeneralAirData.front_left_temperature);
      this.iv_left_foot.setVisibility(this.getIvVisible(GeneralAirData.front_left_blow_foot));
      this.iv_left_head.setVisibility(this.getIvVisible(GeneralAirData.front_left_blow_head));
      this.iv_left_windows.setVisibility(this.getIvVisible(GeneralAirData.front_left_blow_window));
      this.iv_right_foot.setVisibility(this.getIvVisible(GeneralAirData.front_right_blow_foot));
      this.iv_right_head.setVisibility(this.getIvVisible(GeneralAirData.front_right_blow_head));
      this.iv_right_windows.setVisibility(this.getIvVisible(GeneralAirData.front_right_blow_window));
      if (var1 != null) {
         String var2 = var1.getString("update_wind_power");
         if (var2 != null) {
            var2.hashCode();
            if (!var2.equals("update_left_wind_power")) {
               if (var2.equals("update_right_wind_power") && GeneralAirData.center_wheel.equals("Right")) {
                  this.wsv_right_Wind.setWindPower(GeneralAirData.front_right_wind_level);
               }
            } else if (GeneralAirData.center_wheel.equals("Left")) {
               this.wsv_left_Wind.setWindPower(GeneralAirData.front_wind_level);
            }
         }
      }

      this.wsv_left_Wind.setWindPower(GeneralAirData.front_wind_level);
      this.wsv_right_Wind.setWindPower(GeneralAirData.front_right_wind_level);
      this.setSeat(GeneralAirData.front_left_seat_heat_level, this.front_left_seat, this.front_left_hot);
      this.setSeat(GeneralAirData.front_right_seat_heat_level, this.front_right_seat, this.front_right_hot);
   }
}
