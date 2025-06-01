package com.hzbhd.canbus.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.util.CommUtil;

public class ParkAssistFloatView {
   private static final int MAX_CMD_RANGE = 39;
   public static final String REFRESH_UI_BUNDLE_KEY = "PARK_ASSIST_REFRESH_UI_BUNDLE_KEY";
   private static final int TYPE_CAR_IMG_IN_LEFT = 1;
   private static final int TYPE_CAR_IMG_IN_RIGHT = 2;
   private static final int TYPE_NO_CAR_IMG = 0;
   private static final int TYPE_PARKASSIT_DISABLE = 3;
   private int[] carInLeftIds = new int[]{2, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 35, 37};
   private int[] carInRightIds = new int[]{4, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 27, 36, 38};
   private int[] iconBackwardIds = new int[]{15, 16, 17, 18, 25, 26, 37, 38};
   private int[] iconForwardIds = new int[]{9, 10, 21, 22, 35, 36};
   private int[] iconStopIds = new int[]{11, 12, 13, 14, 19, 20, 23, 24};
   private ImageView img_ford_pa_tips_icon;
   private ImageView img_left_ford_pa;
   private ImageView img_right_ford_pa;
   private Context mContext;
   private LinearLayout mFloatLayout;
   private boolean mHaveAdded = false;
   private Runnable mRunnable = new Runnable(this) {
      final ParkAssistFloatView this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.dismissView();
      }
   };
   private WindowManager mWindowManager;
   private int[] noCarIds = new int[]{6, 29, 30, 31, 32, 33, 34, 39};
   private TextView text_ford_pa_tips;
   private WindowManager.LayoutParams wmParams;

   public ParkAssistFloatView(Context var1) {
      this.mContext = var1;
      this.initFloatView();
      this.initWidegets();
   }

   private String cmdHexString(byte var1) {
      return "0x" + Util.byte2HexString(var1);
   }

   private void dismissCanbusAirInfoPanel() {
      WindowManager var2 = this.mWindowManager;
      if (var2 != null && this.mHaveAdded) {
         LinearLayout var1 = this.mFloatLayout;
         if (var1 != null) {
            var2.removeView(var1);
            this.mHaveAdded = false;
         }
      }

   }

   private void dismissView() {
      WindowManager var1 = this.mWindowManager;
      if (var1 != null) {
         LinearLayout var2 = this.mFloatLayout;
         if (var2 != null) {
            var1.removeView(var2);
            this.mHaveAdded = false;
         }
      }

   }

   private int[] getCarInLeftIds() {
      return this.carInLeftIds;
   }

   private int[] getCarInRightIds() {
      return this.carInRightIds;
   }

   private int getCmdType(byte var1) {
      int var2;
      for(var2 = 0; var2 < this.getCarInLeftIds().length; ++var2) {
         if (var1 == this.getCarInLeftIds()[var2]) {
            return 1;
         }
      }

      for(var2 = 0; var2 < this.getCarInRightIds().length; ++var2) {
         if (var1 == this.getCarInRightIds()[var2]) {
            return 2;
         }
      }

      for(var2 = 0; var2 < this.getNoCarIds().length; ++var2) {
         if (var1 == this.getNoCarIds()[var2]) {
            return 0;
         }
      }

      return 3;
   }

   private int[] getIconBackwardIds() {
      return this.iconBackwardIds;
   }

   private int[] getIconForwardIds() {
      return this.iconForwardIds;
   }

   private int[] getIconStopIds() {
      return this.iconStopIds;
   }

   private int[] getNoCarIds() {
      return this.noCarIds;
   }

   private int getTipsImageResId(byte var1) {
      byte var4 = 0;

      int var2;
      for(var2 = 0; var2 < this.getIconStopIds().length; ++var2) {
         if (var1 == this.getIconStopIds()[var2]) {
            return 2131233451;
         }
      }

      var2 = 0;

      while(true) {
         int var3 = var4;
         if (var2 >= this.getIconForwardIds().length) {
            while(var3 < this.getIconBackwardIds().length) {
               if (var1 == this.getIconBackwardIds()[var3]) {
                  return 2131233445;
               }

               ++var3;
            }

            return 2131233621;
         }

         if (var1 == this.getIconForwardIds()[var2]) {
            return 2131233449;
         }

         ++var2;
      }
   }

   private void initFloatView() {
      this.wmParams = new WindowManager.LayoutParams();
      this.mWindowManager = (WindowManager)this.mContext.getSystemService("window");
      this.wmParams.type = 2010;
      this.wmParams.format = 1;
      this.wmParams.windowAnimations = 16973828;
      this.wmParams.gravity = 17;
      LayoutInflater var1 = LayoutInflater.from(this.mContext);
      this.wmParams.width = -2;
      this.wmParams.height = -2;
      this.mFloatLayout = (LinearLayout)var1.inflate(2131558730, (ViewGroup)null);
   }

   private void initWidegets() {
      this.img_left_ford_pa = (ImageView)this.mFloatLayout.findViewById(2131362494);
      this.img_right_ford_pa = (ImageView)this.mFloatLayout.findViewById(2131362499);
      this.text_ford_pa_tips = (TextView)this.mFloatLayout.findViewById(2131363520);
      this.img_ford_pa_tips_icon = (ImageView)this.mFloatLayout.findViewById(2131362489);
   }

   public void refreshUi(Bundle var1) {
      byte var2 = var1.getByte("PARK_ASSIST_REFRESH_UI_BUNDLE_KEY");
      int var3 = this.getCmdType(var2);
      int var4 = this.getTipsImageResId(var2);
      if (var3 != 0) {
         if (var3 != 1) {
            if (var3 != 2) {
               this.dismissCanbusAirInfoPanel();
            } else {
               this.img_left_ford_pa.setVisibility(4);
               this.img_right_ford_pa.setVisibility(0);
               this.img_ford_pa_tips_icon.setImageResource(var4);
               this.img_right_ford_pa.setImageResource(CommUtil.getImgIdByResId(this.mContext, "ford_pa_" + this.cmdHexString(var2)));
               this.text_ford_pa_tips.setText(CommUtil.getStrIdByResId(this.mContext, "ford_park_assist_" + this.cmdHexString(var2)));
            }
         } else {
            this.img_left_ford_pa.setVisibility(0);
            this.img_right_ford_pa.setVisibility(4);
            this.img_ford_pa_tips_icon.setImageResource(var4);
            this.img_left_ford_pa.setImageResource(CommUtil.getImgIdByResId(this.mContext, "ford_pa_" + this.cmdHexString(var2)));
            this.text_ford_pa_tips.setText(CommUtil.getStrIdByResId(this.mContext, "ford_park_assist_" + this.cmdHexString(var2)));
         }
      } else {
         this.img_left_ford_pa.setVisibility(4);
         this.img_right_ford_pa.setVisibility(4);
         this.img_ford_pa_tips_icon.setImageResource(var4);
         this.text_ford_pa_tips.setText(CommUtil.getStrIdByResId(this.mContext, "ford_park_assist_" + this.cmdHexString(var2)));
      }

      if (!this.mHaveAdded) {
         this.mWindowManager.addView(this.mFloatLayout, this.wmParams);
         this.mHaveAdded = true;
      }

      this.mFloatLayout.removeCallbacks(this.mRunnable);
      this.mFloatLayout.postDelayed(this.mRunnable, 5000L);
   }
}
