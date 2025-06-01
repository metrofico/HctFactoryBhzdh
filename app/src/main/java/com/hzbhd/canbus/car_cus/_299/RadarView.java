package com.hzbhd.canbus.car_cus._299;

import android.content.Context;
import android.provider.Settings;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;

public class RadarView extends RelativeLayout {
   private static final int TIME = 3000;
   private ImageView front_radar_left;
   private int[] front_radar_leftImages;
   private ImageView front_radar_left_mid;
   private int[] front_radar_left_midImages;
   private ImageView front_radar_right;
   private int[] front_radar_rightImages;
   private ImageView front_radar_right_mid;
   private int[] front_radar_right_midImages;
   private boolean isShowing;
   private Context mContext;
   private WindowManager.LayoutParams mLayoutParams;
   private Runnable mRunnable;
   private View mView;
   private WindowManager mWindowManager;
   private ImageView rear_radar_left;
   private int[] rear_radar_leftImages;
   private ImageView rear_radar_left_mid;
   private int[] rear_radar_left_midImages;
   private ImageView rear_radar_right;
   private int[] rear_radar_rightImages;
   private ImageView rear_radar_right_mid;
   private int[] rear_radar_right_midImages;
   private RelativeLayout relativeLayout;

   public RadarView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public RadarView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public RadarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.isShowing = false;
      this.front_radar_leftImages = new int[]{2131232907, 2131232908, 2131232909, 2131232910, 2131232911, 2131232912};
      this.front_radar_left_midImages = new int[]{2131232913, 2131232914, 2131232915, 2131232916, 2131232917, 2131232918};
      this.front_radar_right_midImages = new int[]{2131232919, 2131232920, 2131232921, 2131232922, 2131232923, 2131232924};
      this.front_radar_rightImages = new int[]{2131232925, 2131232926, 2131232927, 2131232928, 2131232929, 2131232930};
      this.rear_radar_leftImages = new int[]{2131232931, 2131232932, 2131232933, 2131232934, 2131232935, 2131232936};
      this.rear_radar_left_midImages = new int[]{2131232937, 2131232938, 2131232939, 2131232940, 2131232941, 2131232942};
      this.rear_radar_right_midImages = new int[]{2131232943, 2131232944, 2131232945, 2131232946, 2131232947, 2131232948};
      this.rear_radar_rightImages = new int[]{2131232949, 2131232950, 2131232951, 2131232952, 2131232953, 2131232954};
      this.mRunnable = new Runnable(this) {
         final RadarView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.dismissView();
         }
      };
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      this.mView = LayoutInflater.from(this.mContext).inflate(2131558498, this, true);
      this.initView();
   }

   private void addViewToWindow() {
      if (this.mLayoutParams == null) {
         WindowManager.LayoutParams var3 = new WindowManager.LayoutParams();
         this.mLayoutParams = var3;
         var3.type = 2002;
         this.mLayoutParams.width = -1;
         this.mLayoutParams.height = -2;
         this.mLayoutParams.format = 1;
      }

      int var1 = 0;

      label25: {
         int var2;
         try {
            var2 = System.getInt(this.mContext.getContentResolver(), "RADAR_DISP_CHECK");
         } catch (Settings.SettingNotFoundException var4) {
            var4.printStackTrace();
            break label25;
         }

         var1 = var2;
      }

      if (!this.isShowing && var1 == 1) {
         ViewGroup var5 = (ViewGroup)this.getParent();
         if (var5 != null) {
            var5.removeAllViews();
         }

         this.mWindowManager.addView(this.mView, this.mLayoutParams);
         this.isShowing = true;
      }

      this.mView.removeCallbacks(this.mRunnable);
      this.mView.postDelayed(this.mRunnable, 3000L);
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

   private int getRadarRes(int var1, int[] var2) {
      if (var1 > 0 && var1 <= 7) {
         return var1 == 7 ? var2[var2.length - 1] : var2[var1 - 1];
      } else {
         return 0;
      }
   }

   private void initView() {
      this.front_radar_left = (ImageView)this.mView.findViewById(2131362273);
      this.front_radar_left_mid = (ImageView)this.mView.findViewById(2131362274);
      this.front_radar_right_mid = (ImageView)this.mView.findViewById(2131362282);
      this.front_radar_right = (ImageView)this.mView.findViewById(2131362281);
      this.rear_radar_left = (ImageView)this.mView.findViewById(2131363057);
      this.rear_radar_left_mid = (ImageView)this.mView.findViewById(2131363059);
      this.rear_radar_right_mid = (ImageView)this.mView.findViewById(2131363062);
      this.rear_radar_right = (ImageView)this.mView.findViewById(2131363060);
      this.relativeLayout = (RelativeLayout)this.mView.findViewById(2131363090);
   }

   public void refreshUi() {
      this.front_radar_left.setImageResource(this.getRadarRes(GeneralDzData.radar_front_l, this.front_radar_leftImages));
      this.front_radar_left_mid.setImageResource(this.getRadarRes(GeneralDzData.radar_front_ml, this.front_radar_left_midImages));
      this.front_radar_right_mid.setImageResource(this.getRadarRes(GeneralDzData.radar_front_mr, this.front_radar_right_midImages));
      this.front_radar_right.setImageResource(this.getRadarRes(GeneralDzData.radar_front_r, this.front_radar_rightImages));
      this.rear_radar_left.setImageResource(this.getRadarRes(GeneralDzData.radar_rear_l, this.rear_radar_leftImages));
      this.rear_radar_left_mid.setImageResource(this.getRadarRes(GeneralDzData.radar_rear_ml, this.rear_radar_left_midImages));
      this.rear_radar_right_mid.setImageResource(this.getRadarRes(GeneralDzData.radar_rear_mr, this.rear_radar_right_midImages));
      this.rear_radar_right.setImageResource(this.getRadarRes(GeneralDzData.radar_rear_r, this.rear_radar_rightImages));
   }

   public void refreshUiOut() {
      this.addViewToWindow();
      this.refreshUi();
   }

   public void setBackgroundLayoutColor(int var1) {
      this.relativeLayout.setBackgroundColor(var1);
   }
}
