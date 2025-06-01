package com.hzbhd.canbus.car_cus._291;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import java.util.HashMap;

public class RadarView extends RelativeLayout {
   public static final String SHARE_SYS_REVERSE = "sys.Reverse";
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
   int mReverseStatus;
   private Runnable mRunnable;
   private View mView;
   private WindowManager mWindowManager;
   private RelativeLayout radarLayout;
   private ImageView rear_radar_left;
   private int[] rear_radar_leftImages;
   private ImageView rear_radar_left_mid;
   private int[] rear_radar_left_midImages;
   private ImageView rear_radar_right;
   private int[] rear_radar_rightImages;
   private ImageView rear_radar_right_mid;
   private int[] rear_radar_right_midImages;

   public RadarView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public RadarView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public RadarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.isShowing = false;
      this.front_radar_leftImages = new int[]{2131231393, 2131231394, 2131231395, 2131231396, 2131231397, 2131231398};
      this.front_radar_left_midImages = new int[]{2131231387, 2131231388, 2131231389, 2131231390, 2131231391, 2131231392};
      this.rear_radar_leftImages = new int[]{2131231405, 2131231406, 2131231407, 2131231408, 2131231409, 2131231410};
      this.rear_radar_left_midImages = new int[]{2131231399, 2131231400, 2131231401, 2131231402, 2131231403, 2131231404};
      this.front_radar_right_midImages = new int[]{2131231411, 2131231412, 2131231413, 2131231414, 2131231415, 2131231416};
      this.front_radar_rightImages = new int[]{2131231417, 2131231418, 2131231419, 2131231420, 2131231421, 2131231422};
      this.rear_radar_rightImages = new int[]{2131231429, 2131231430, 2131231431, 2131231432, 2131231433, 2131231434};
      this.rear_radar_right_midImages = new int[]{2131231423, 2131231424, 2131231425, 2131231426, 2131231427, 2131231428};
      this.mReverseStatus = 0;
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
      this.mView = LayoutInflater.from(this.mContext).inflate(2131558493, this, true);
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

      if (this.isRadarShowCondition() && !this.isDistance()) {
         if (!this.isShowing) {
            this.mWindowManager.addView(this.mView, this.mLayoutParams);
            this.isShowing = true;
         }
      } else {
         this.dismissView();
      }

   }

   private void dismissView() {
      Exception var10000;
      label34: {
         boolean var10001;
         WindowManager var2;
         try {
            var2 = this.mWindowManager;
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label34;
         }

         if (var2 == null) {
            return;
         }

         View var1;
         try {
            var1 = this.mView;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label34;
         }

         if (var1 == null) {
            return;
         }

         try {
            var2.removeView(var1);
            this.isShowing = false;
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
   }

   private Drawable getRadarImageResource(int[] var1, int var2) {
      if (var2 == 255) {
         return null;
      } else {
         int var3 = 12;
         byte var4;
         if (var2 > 127) {
            var4 = (byte)var3;
         } else if (var2 > 105) {
            var4 = 6;
         } else if (var2 > 84) {
            var4 = 5;
         } else if (var2 > 63) {
            var4 = 4;
         } else if (var2 > 42) {
            var4 = 3;
         } else if (var2 > 21) {
            var4 = 2;
         } else {
            var4 = 1;
         }

         if (var4 > 6) {
            return null;
         } else {
            var3 = var4;
            if (var4 > var1.length) {
               var3 = var1.length;
            }

            return this.mContext.getDrawable(var1[var3 - 1]);
         }
      }
   }

   private void initView() {
      this.front_radar_left_mid = (ImageView)this.mView.findViewById(2131362274);
      this.front_radar_left = (ImageView)this.mView.findViewById(2131362273);
      this.rear_radar_left = (ImageView)this.mView.findViewById(2131363057);
      this.rear_radar_left_mid = (ImageView)this.mView.findViewById(2131363059);
      this.front_radar_right_mid = (ImageView)this.mView.findViewById(2131362282);
      this.front_radar_right = (ImageView)this.mView.findViewById(2131362281);
      this.rear_radar_right = (ImageView)this.mView.findViewById(2131363060);
      this.rear_radar_right_mid = (ImageView)this.mView.findViewById(2131363062);
      this.radarLayout = (RelativeLayout)this.mView.findViewById(2131363005);
   }

   private boolean isDistance() {
      return this.front_radar_left_mid.getDrawable() == null && this.front_radar_left.getDrawable() == null && this.front_radar_right_mid.getDrawable() == null && this.front_radar_right.getDrawable() == null && this.rear_radar_left_mid.getDrawable() == null && this.rear_radar_left.getDrawable() == null && this.rear_radar_right_mid.getDrawable() == null && this.rear_radar_right.getDrawable() == null;
   }

   private boolean isRadarShowCondition() {
      boolean var1;
      if (!GeneralDzData.handBrake && !GeneralDzData.gears && GeneralDzData.speed <= 20) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean getReverseState() {
      int var1 = ShareDataManager.getInstance().registerShareIntListener("sys.Reverse", new IShareIntListener(this) {
         final RadarView this$0;

         {
            this.this$0 = var1;
         }

         public void onInt(int var1) {
            if (this.this$0.mReverseStatus != var1) {
               this.this$0.mReverseStatus = var1;
            }
         }
      });
      this.mReverseStatus = var1;
      return var1 == 1;
   }

   public void goneRadar() {
      this.radarLayout.setVisibility(8);
   }

   public void refreshUi() {
      HashMap var1 = GeneralParkData.radar_distance_data;
      this.front_radar_left_mid.setImageDrawable(this.getRadarImageResource(this.front_radar_left_midImages, (Integer)var1.get(Constant.RadarLocation.FRONT_MID_LEFT)));
      this.front_radar_left.setImageDrawable(this.getRadarImageResource(this.front_radar_leftImages, (Integer)var1.get(Constant.RadarLocation.FRONT_LEFT)));
      this.front_radar_right_mid.setImageDrawable(this.getRadarImageResource(this.front_radar_right_midImages, (Integer)var1.get(Constant.RadarLocation.FRONT_MID_RIGHT)));
      this.front_radar_right.setImageDrawable(this.getRadarImageResource(this.front_radar_rightImages, (Integer)var1.get(Constant.RadarLocation.FRONT_RIGHT)));
      this.rear_radar_left_mid.setImageDrawable(this.getRadarImageResource(this.rear_radar_left_midImages, (Integer)var1.get(Constant.RadarLocation.REAR_MID_LEFT)));
      this.rear_radar_left.setImageDrawable(this.getRadarImageResource(this.rear_radar_leftImages, (Integer)var1.get(Constant.RadarLocation.REAR_LEFT)));
      this.rear_radar_right_mid.setImageDrawable(this.getRadarImageResource(this.rear_radar_right_midImages, (Integer)var1.get(Constant.RadarLocation.REAR_MID_RIGHT)));
      this.rear_radar_right.setImageDrawable(this.getRadarImageResource(this.rear_radar_rightImages, (Integer)var1.get(Constant.RadarLocation.REAR_RIGHT)));
      if (!this.getReverseState()) {
         this.addViewToWindow();
      }

   }

   public void showRadar() {
      this.radarLayout.setVisibility(0);
   }
}
