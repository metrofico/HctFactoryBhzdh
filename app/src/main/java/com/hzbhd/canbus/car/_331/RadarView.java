package com.hzbhd.canbus.car._331;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class RadarView extends RelativeLayout {
   public static final int[] frontRadar = new int[]{0, 0, 0, 0};
   private static final int[] imgFrontLeft = new int[]{2131231446, 2131231447};
   private static final int[] imgFrontLeftMid = new int[]{2131231448, 2131231449, 2131231450};
   private static final int[] imgFrontRight = new int[]{2131231454, 2131231455};
   private static final int[] imgFrontRightMid = new int[]{2131231451, 2131231452, 2131231453};
   private static final int[] imgRearLeft = new int[]{2131231456, 2131231457, 2131231458};
   private static final int[] imgRearLeftMid = new int[]{2131231459, 2131231460, 2131231461, 2131231462, 2131231463};
   private static final int[] imgRearRight = new int[]{2131231469, 2131231470, 2131231471};
   private static final int[] imgRearRightMid = new int[]{2131231464, 2131231465, 2131231466, 2131231467, 2131231468};
   public static final int[] rearRadar = new int[]{0, 0, 0, 0};
   private ImageView img_car;
   private ImageView img_front_left;
   private ImageView img_front_left_mid;
   private ImageView img_front_right;
   private ImageView img_front_right_mid;
   private ImageView img_rear_left;
   private ImageView img_rear_left_mid;
   private ImageView img_rear_right;
   private ImageView img_rear_right_mid;
   private boolean isShowing;
   private final Context mContext;
   private WindowManager.LayoutParams mLayoutParams;
   private final Runnable mRunnable;
   private final View mView;
   private final WindowManager mWindowManager;

   // $FF: synthetic method
   public static void $r8$lambda$J4_W_55JxavEAO4L7iE9JkQTrWo(RadarView var0) {
      var0.dismissView();
   }

   public RadarView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public RadarView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public RadarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.isShowing = false;
      this.mRunnable = new RadarView$$ExternalSyntheticLambda0(this);
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      this.mView = LayoutInflater.from(var1).inflate(2131558512, this);
      this.initView();
   }

   private void addViewToWindow() {
      if (this.mLayoutParams == null) {
         WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
         this.mLayoutParams = var1;
         var1.type = 2038;
         this.mLayoutParams.gravity = 51;
         this.mLayoutParams.width = -2;
         this.mLayoutParams.height = -2;
         this.mLayoutParams.format = 1;
         this.mLayoutParams.flags = 8;
      }

      if (!this.isShowing && !this.isDistance()) {
         this.mWindowManager.addView(this.mView, this.mLayoutParams);
         this.isShowing = true;
      }

      this.mView.removeCallbacks(this.mRunnable);
      this.mView.postDelayed(this.mRunnable, 5000L);
   }

   private void dismissView() {
      try {
         this.isShowing = false;
         this.mWindowManager.removeView(this.mView);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   private int getImage(int[] var1, int var2) {
      int var3 = var2;
      if (var2 == 6) {
         var3 = var2 - 1;
      }

      return var3 > 0 && var3 <= var1.length ? var1[var3 - 1] : 0;
   }

   private int getImageRear(int[] var1, int var2) {
      if (var2 != 1) {
         if (var2 != 2) {
            return var2 != 3 && var2 != 4 ? 0 : var1[2];
         } else {
            return var1[1];
         }
      } else {
         return var1[0];
      }
   }

   private void initView() {
      this.img_car = (ImageView)this.findViewById(2131362488);
      this.img_front_left = (ImageView)this.findViewById(2131362490);
      this.img_front_left_mid = (ImageView)this.findViewById(2131362491);
      this.img_front_right_mid = (ImageView)this.findViewById(2131362493);
      this.img_front_right = (ImageView)this.findViewById(2131362492);
      this.img_rear_left = (ImageView)this.findViewById(2131362495);
      this.img_rear_left_mid = (ImageView)this.findViewById(2131362496);
      this.img_rear_right_mid = (ImageView)this.findViewById(2131362498);
      this.img_rear_right = (ImageView)this.findViewById(2131362497);
   }

   private boolean isDistance() {
      int[] var3 = rearRadar;
      boolean var2 = false;
      boolean var1 = var2;
      if (var3[0] == 0) {
         var1 = var2;
         if (var3[1] == 0) {
            var1 = var2;
            if (var3[2] == 0) {
               var1 = var2;
               if (var3[3] == 0) {
                  var3 = frontRadar;
                  var1 = var2;
                  if (var3[0] == 0) {
                     var1 = var2;
                     if (var3[1] == 0) {
                        var1 = var2;
                        if (var3[2] == 0) {
                           var1 = var2;
                           if (var3[3] == 0) {
                              var1 = true;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return var1;
   }

   private void removeAndDismiss() {
      View var1 = this.mView;
      if (var1 != null) {
         var1.removeCallbacks(this.mRunnable);
         this.dismissView();
      }

   }

   public void refreshUi() {
      this.updateUi();
      ContentResolver var2 = this.mContext.getContentResolver();
      boolean var1 = false;
      if (System.getInt(var2, "isBackCamera", 0) == 1) {
         var1 = true;
      }

      if (!var1) {
         this.addViewToWindow();
      } else {
         this.removeAndDismiss();
      }

   }

   public void updateUi() {
      ImageView var2 = this.img_front_left;
      int[] var3 = imgFrontLeft;
      int[] var1 = frontRadar;
      var2.setImageResource(this.getImage(var3, var1[0]));
      this.img_front_left_mid.setImageResource(this.getImage(imgFrontLeftMid, var1[1]));
      this.img_front_right_mid.setImageResource(this.getImage(imgFrontRightMid, var1[2]));
      this.img_front_right.setImageResource(this.getImage(imgFrontRight, var1[3]));
      var2 = this.img_rear_left;
      int[] var4 = imgRearLeft;
      var3 = rearRadar;
      var2.setImageResource(this.getImageRear(var4, var3[0]));
      this.img_rear_left_mid.setImageResource(this.getImage(imgRearLeftMid, var3[1]));
      this.img_rear_right_mid.setImageResource(this.getImage(imgRearRightMid, var3[2]));
      this.img_rear_right.setImageResource(this.getImageRear(imgRearRight, var3[3]));
      if (var3[0] != 1 && var3[1] != 1 && var3[2] != 1 && var3[3] != 1 && var1[0] != 1 && var1[1] != 1 && var1[2] != 1 && var1[3] != 1) {
         this.img_car.setImageResource(2131231472);
      } else {
         this.img_car.setImageResource(2131231473);
      }

   }
}
