package com.hzbhd.canbus.car_cus._299.air;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.SystemUtil;

public class AirPopupView {
   private static final int WHAT_HIDE_VIEW = 1;
   private static final int WHAT_SHOW_VIEW = 0;
   private boolean isShowing = false;
   private ImageView iv_ac;
   private ImageView iv_wind;
   private Context mContext;
   private WindowManager.LayoutParams mLayoutParams;
   private Runnable mRunnable = new AirPopupView$$ExternalSyntheticLambda0(this);
   private View mView;
   private WindowManager mWindowManager;
   private TextView tv_left_temp;
   private TextView tv_right_temp;
   private TextView tv_wind;

   // $FF: synthetic method
   public static void $r8$lambda$xBawITu4P_VnRRx_mt4GxtFZJrI(AirPopupView var0) {
      var0.dismissView();
   }

   public AirPopupView(Context var1) {
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558497, (ViewGroup)null);
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
         this.mLayoutParams.height = (int)this.mContext.getResources().getDimension(2131170366);
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
      if (this.isShowing) {
         WindowManager var1 = this.mWindowManager;
         if (var1 != null) {
            View var2 = this.mView;
            if (var2 != null) {
               var1.removeView(var2);
               this.isShowing = false;
            }
         }
      }

   }

   private int getWindRes() {
      boolean var3 = GeneralAirData.front_left_blow_window;
      boolean var1 = GeneralAirData.front_left_blow_head;
      boolean var2 = GeneralAirData.front_left_blow_foot;
      if (var3 && var1 && var2) {
         return 2131232059;
      } else if (var3 && var1) {
         return 2131232060;
      } else if (var3 && var2) {
         return 2131232058;
      } else if (var1 && var2) {
         return 2131232061;
      } else if (var3) {
         return 2131232057;
      } else if (var1) {
         return 2131232062;
      } else {
         return var2 ? 2131232056 : 2131232063;
      }
   }

   private void initView() {
      this.tv_left_temp = (TextView)this.mView.findViewById(2131363644);
      this.tv_right_temp = (TextView)this.mView.findViewById(2131363684);
      this.tv_wind = (TextView)this.mView.findViewById(2131363719);
      this.iv_ac = (ImageView)this.mView.findViewById(2131362530);
      this.iv_wind = (ImageView)this.mView.findViewById(2131362671);
   }

   public void refreshUi() {
      this.tv_left_temp.setText(GeneralAirData.front_left_temperature);
      this.tv_right_temp.setText(GeneralAirData.front_right_temperature);
      this.tv_wind.setText(String.valueOf(GeneralAirData.front_wind_level));
      ImageView var2 = this.iv_ac;
      int var1;
      if (GeneralAirData.ac) {
         var1 = 2131232055;
      } else {
         var1 = 2131232054;
      }

      var2.setImageResource(var1);
      this.iv_wind.setImageResource(this.getWindRes());
      if (!SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
         this.addViewToWindow();
      }

   }
}
