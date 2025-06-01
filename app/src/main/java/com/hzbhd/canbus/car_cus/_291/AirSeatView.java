package com.hzbhd.canbus.car_cus._291;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.hzbhd.canbus.adapter.util.PA6Utils;
import com.hzbhd.canbus.car_cus._283.view.BtnView;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.SystemUtil;

public class AirSeatView {
   private BtnView btn_left_front_hot_1;
   private BtnView btn_left_front_hot_2;
   private BtnView btn_left_front_hot_3;
   private BtnView btn_right_front_hot_1;
   private BtnView btn_right_front_hot_2;
   private BtnView btn_right_front_hot_3;
   private boolean isShowing = false;
   private Context mContext;
   private WindowManager.LayoutParams mLayoutParams;
   private Runnable mRunnable = new Runnable(this) {
      final AirSeatView this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.dismissView();
      }
   };
   private View mView;
   private WindowManager mWindowManager;
   private TextView tv_left_front_hot;
   private TextView tv_right_front_hot;

   public AirSeatView(Context var1) {
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558490, (ViewGroup)null);
      this.mWindowManager = (WindowManager)this.mContext.getSystemService("window");
      this.initView();
   }

   private void addViewToWindow() {
      if (this.mLayoutParams == null) {
         WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
         this.mLayoutParams = var1;
         var1.type = 2002;
         this.mLayoutParams.gravity = 80;
         this.mLayoutParams.width = -1;
         this.mLayoutParams.height = -2;
         this.mLayoutParams.format = 1;
         this.mLayoutParams.flags = 8;
      }

      if (!this.isShowing) {
         this.mWindowManager.addView(this.mView, this.mLayoutParams);
         this.isShowing = true;
      }

      this.mView.removeCallbacks(this.mRunnable);
      this.mView.postDelayed(this.mRunnable, 5000L);
   }

   private void dismissView() {
      WindowManager var1 = this.mWindowManager;
      if (var1 != null) {
         View var2 = this.mView;
         if (var2 != null) {
            var1.removeView(var2);
            this.isShowing = false;
         }
      }

   }

   private void initView() {
      this.tv_left_front_hot = (TextView)this.mView.findViewById(2131363636);
      this.tv_right_front_hot = (TextView)this.mView.findViewById(2131363678);
      this.btn_left_front_hot_1 = (BtnView)this.mView.findViewById(2131362025);
      this.btn_left_front_hot_2 = (BtnView)this.mView.findViewById(2131362026);
      this.btn_left_front_hot_3 = (BtnView)this.mView.findViewById(2131362027);
      this.btn_right_front_hot_1 = (BtnView)this.mView.findViewById(2131362046);
      this.btn_right_front_hot_2 = (BtnView)this.mView.findViewById(2131362047);
      this.btn_right_front_hot_3 = (BtnView)this.mView.findViewById(2131362048);
   }

   private void setGrade(int var1, TextView var2, BtnView var3, BtnView var4, BtnView var5) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  var2.setText(2131760436);
                  var3.setSelect(true);
                  var4.setSelect(true);
                  var5.setSelect(true);
               }
            } else {
               var2.setText(2131760436);
               var3.setSelect(true);
               var4.setSelect(true);
               var5.setSelect(false);
            }
         } else {
            var2.setText(2131760436);
            var3.setSelect(true);
            var4.setSelect(false);
            var5.setSelect(false);
         }
      } else {
         var2.setText(2131760435);
         var3.setSelect(false);
         var4.setSelect(false);
         var5.setSelect(false);
      }

   }

   public void refreshUi() {
      this.setGrade(GeneralAirData.front_left_seat_heat_level, this.tv_left_front_hot, this.btn_left_front_hot_1, this.btn_left_front_hot_2, this.btn_left_front_hot_3);
      this.setGrade(GeneralAirData.front_right_seat_heat_level, this.tv_right_front_hot, this.btn_right_front_hot_1, this.btn_right_front_hot_2, this.btn_right_front_hot_3);
      if (!SystemUtil.isForeground(this.mContext, AirActivity.class.getName()) && PA6Utils.getAirSeatShow()) {
         this.addViewToWindow();
      }

   }
}
