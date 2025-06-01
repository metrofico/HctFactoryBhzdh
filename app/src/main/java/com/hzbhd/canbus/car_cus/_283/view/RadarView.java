package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.utils._283_SharedPreferencesUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;

public class RadarView extends RelativeLayout implements View.OnClickListener {
   public static final String SHARE_SYS_REVERSE = "sys.Reverse";
   private static final int TIME = 3000;
   private RelativeLayout LeftRelative;
   private BtnView btn_margin;
   private BtnView btn_overlook;
   private BtnView btn_radar_bg;
   private BtnView btn_standard;
   private BtnView btn_wide;
   private ImageView front_radar_left;
   private int[] front_radar_leftImages;
   private ImageView front_radar_left_mid;
   private int[] front_radar_left_midImages;
   private ImageView front_radar_right;
   private int[] front_radar_rightImages;
   private ImageView front_radar_right_mid;
   private int[] front_radar_right_midImages;
   private ImageView hideCarRadar;
   private boolean isBackCar;
   private boolean isHideLeftRadar;
   private boolean isShowing;
   private boolean isTransparency;
   private ImageView iv_radar_stop;
   private ImageView iv_radar_voice;
   private Runnable justShowRadarRunnable;
   private ImageView leftClose;
   private RelativeLayout leftRadarView;
   private ImageView left_radar_front;
   private int[] left_radar_frontImages;
   private ImageView left_radar_mid_front;
   private int[] left_radar_mid_frontImages;
   private ImageView left_radar_mid_rear;
   private int[] left_radar_mid_rearImages;
   private ImageView left_radar_rear;
   private int[] left_radar_rearImages;
   private LinearLayout linearBottomRadar;
   private Context mContext;
   private SharedPreferences.Editor mEditor;
   private Handler mHandler;
   private WindowManager.LayoutParams mLayoutParams;
   private RadarRegulationView mRadarRegulationView;
   int mReverseStatus;
   private Runnable mRunnable;
   private SharedPreferences mSharedPreferences;
   private View mView;
   private WindowManager mWindowManager;
   private ImageView rear_radar_left;
   private int[] rear_radar_leftImages;
   private ImageView rear_radar_left_auxiliary;
   private int[] rear_radar_left_auxiliaryImages;
   private ImageView rear_radar_left_mid;
   private int[] rear_radar_left_midImages;
   private ImageView rear_radar_right;
   private int[] rear_radar_rightImages;
   private ImageView rear_radar_right_auxiliary;
   private int[] rear_radar_right_auxiliaryImages;
   private ImageView rear_radar_right_mid;
   private int[] rear_radar_right_midImages;
   private RelativeLayout relativeLayout;
   private BtnView rightClose;
   private LinearLayout rightLinear;
   private ImageView right_radar_front;
   private int[] right_radar_frontImages;
   private ImageView right_radar_mid_front;
   private int[] right_radar_mid_frontImages;
   private ImageView right_radar_mid_rear;
   private int[] right_radar_mid_rearImages;
   private ImageView right_radar_rear;
   private int[] right_radar_rearImages;
   private ImageView showCarRadar;
   private ImageView showRightBar;

   public RadarView(Context var1) {
      this(var1, (AttributeSet)null);
      this.isBackCar = false;
      this.rightLinear.setVisibility(8);
      this.showRightBar.setVisibility(8);
   }

   public RadarView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
      this.isBackCar = true;
   }

   public RadarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.isShowing = false;
      this.isTransparency = false;
      this.isBackCar = false;
      this.isHideLeftRadar = false;
      this.front_radar_left_midImages = new int[]{2131231726, 2131231727, 2131231728, 2131231729, 2131231730, 2131231731, 2131231732, 2131231733};
      this.front_radar_leftImages = new int[]{2131231734, 2131231735, 2131231736, 2131231737, 2131231738, 2131231739};
      this.left_radar_frontImages = new int[]{2131231740, 2131231741, 2131231742, 2131231743, 2131231744, 2131231745};
      this.left_radar_mid_frontImages = new int[]{2131231746, 2131231747, 2131231748, 2131231749, 2131231750, 2131231751};
      this.left_radar_mid_rearImages = new int[]{2131231752, 2131231753, 2131231754, 2131231755, 2131231756, 2131231757};
      this.left_radar_rearImages = new int[]{2131231758, 2131231759, 2131231760, 2131231761, 2131231762, 2131231763};
      this.rear_radar_leftImages = new int[]{2131231764, 2131231765, 2131231766, 2131231767, 2131231768, 2131231769};
      this.rear_radar_left_midImages = new int[]{2131231770, 2131231773, 2131231774, 2131231775, 2131231776, 2131231777, 2131231778, 2131231779, 2131231780, 2131231771, 2131231772};
      this.rear_radar_left_auxiliaryImages = new int[]{2131233106, 2131233107, 2131233108};
      this.front_radar_right_midImages = new int[]{2131231781, 2131231782, 2131231783, 2131231784, 2131231785, 2131231786, 2131231787, 2131231788};
      this.front_radar_rightImages = new int[]{2131231789, 2131231790, 2131231791, 2131231792, 2131231793, 2131231794};
      this.right_radar_frontImages = new int[]{2131231795, 2131231796, 2131231797, 2131231798, 2131231799, 2131231800};
      this.right_radar_mid_frontImages = new int[]{2131231801, 2131231802, 2131231803, 2131231804, 2131231805, 2131231806};
      this.right_radar_mid_rearImages = new int[]{2131231807, 2131231808, 2131231809, 2131231810, 2131231811, 2131231812};
      this.right_radar_rearImages = new int[]{2131231813, 2131231814, 2131231815, 2131231816, 2131231817, 2131231818};
      this.rear_radar_rightImages = new int[]{2131231819, 2131231820, 2131231821, 2131231822, 2131231823, 2131231824};
      this.rear_radar_right_midImages = new int[]{2131231825, 2131231828, 2131231829, 2131231830, 2131231831, 2131231832, 2131231833, 2131231834, 2131231835, 2131231826, 2131231827};
      this.rear_radar_right_auxiliaryImages = new int[]{2131233186, 2131233187, 2131233188};
      this.mHandler = new Handler(Looper.getMainLooper());
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
      this.justShowRadarRunnable = new Runnable(this) {
         final RadarView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.justShowRadar();
         }
      };
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      this.mView = LayoutInflater.from(this.mContext).inflate(2131558473, this, true);
      SharedPreferences var4 = this.mContext.getSharedPreferences("283_main_name", 0);
      this.mSharedPreferences = var4;
      this.mEditor = var4.edit();
      this.initView();
   }

   private void addViewToWindow() {
      if (this.mLayoutParams == null) {
         WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
         this.mLayoutParams = var1;
         var1.type = 2038;
         this.mLayoutParams.gravity = 80;
         this.mLayoutParams.width = -1;
         this.mLayoutParams.height = -2;
         this.mLayoutParams.format = 1;
         this.mLayoutParams.flags = 8;
      }

      if (this.isRadarShowCondition() && !this.isRadarDistance()) {
         if (!this.isShowing) {
            Context var2 = this.mContext;
            if (var2 == null) {
               return;
            }

            if (SharePreUtil.getBoolValue(var2, _283_SharedPreferencesUtils.KEY_DIALOG_RADAR_SWITCH, false)) {
               this.mWindowManager.addView(this.mView, this.mLayoutParams);
               this.isShowing = true;
            }
         }
      } else {
         this.dismissView();
      }

   }

   private void dismissView() {
      WindowManager var1 = this.mWindowManager;
      if (var1 != null) {
         View var2 = this.mView;
         if (var2 != null && this.isShowing) {
            try {
               this.isShowing = false;
               var1.removeView(var2);
            } catch (Exception var3) {
               var3.printStackTrace();
            }
         }
      }

   }

   private Drawable getRadarImageResource(int[] var1, int var2) {
      byte var4;
      if (var2 >= 0 && var2 <= 15) {
         var4 = 1;
      } else if (var2 >= 16 && var2 <= 30) {
         var4 = 2;
      } else if (var2 >= 31 && var2 <= 45) {
         var4 = 3;
      } else if (var2 >= 46 && var2 <= 60) {
         var4 = 4;
      } else if (var2 >= 61 && var2 <= 75) {
         var4 = 5;
      } else if (var2 >= 76 && var2 <= 90) {
         var4 = 6;
      } else if (var2 >= 91 && var2 <= 105) {
         var4 = 7;
      } else if (var2 >= 106 && var2 <= 120) {
         var4 = 8;
      } else if (var2 >= 121 && var2 <= 135) {
         var4 = 9;
      } else if (var2 >= 136 && var2 <= 150) {
         var4 = 10;
      } else if (var2 >= 151 && var2 <= 165) {
         var4 = 11;
      } else {
         var4 = 12;
      }

      if (var4 > 11) {
         return null;
      } else {
         int var3 = var4;
         if (var4 > var1.length) {
            var3 = var1.length;
         }

         return this.mContext.getDrawable(var1[var3 - 1]);
      }
   }

   private void initView() {
      this.showRightBar = (ImageView)this.mView.findViewById(2131363330);
      this.hideCarRadar = (ImageView)this.mView.findViewById(2131362369);
      this.showCarRadar = (ImageView)this.mView.findViewById(2131363327);
      this.front_radar_left_mid = (ImageView)this.mView.findViewById(2131362274);
      this.front_radar_left = (ImageView)this.mView.findViewById(2131362273);
      this.left_radar_front = (ImageView)this.mView.findViewById(2131362730);
      this.left_radar_mid_front = (ImageView)this.mView.findViewById(2131362736);
      this.left_radar_mid_rear = (ImageView)this.mView.findViewById(2131362737);
      this.left_radar_rear = (ImageView)this.mView.findViewById(2131362738);
      this.rear_radar_left = (ImageView)this.mView.findViewById(2131363057);
      this.rear_radar_left_auxiliary = (ImageView)this.mView.findViewById(2131363058);
      this.rear_radar_left_mid = (ImageView)this.mView.findViewById(2131363059);
      this.front_radar_right_mid = (ImageView)this.mView.findViewById(2131362282);
      this.front_radar_right = (ImageView)this.mView.findViewById(2131362281);
      this.right_radar_front = (ImageView)this.mView.findViewById(2131363160);
      this.right_radar_mid_front = (ImageView)this.mView.findViewById(2131363166);
      this.right_radar_mid_rear = (ImageView)this.mView.findViewById(2131363167);
      this.right_radar_rear = (ImageView)this.mView.findViewById(2131363168);
      this.rear_radar_right = (ImageView)this.mView.findViewById(2131363060);
      this.rear_radar_right_auxiliary = (ImageView)this.mView.findViewById(2131363061);
      this.rear_radar_right_mid = (ImageView)this.mView.findViewById(2131363062);
      this.iv_radar_stop = (ImageView)this.mView.findViewById(2131362609);
      this.iv_radar_voice = (ImageView)this.mView.findViewById(2131362610);
      this.relativeLayout = (RelativeLayout)this.mView.findViewById(2131363090);
      this.linearBottomRadar = (LinearLayout)this.mView.findViewById(2131362758);
      this.LeftRelative = (RelativeLayout)this.mView.findViewById(2131361825);
      this.leftRadarView = (RelativeLayout)this.mView.findViewById(2131362723);
      this.rightLinear = (LinearLayout)this.mView.findViewById(2131363151);
      this.leftClose = (ImageView)this.mView.findViewById(2131362720);
      this.rightClose = (BtnView)this.mView.findViewById(2131363148);
      this.btn_radar_bg = (BtnView)this.mView.findViewById(2131362043);
      this.btn_overlook = (BtnView)this.mView.findViewById(2131362037);
      this.btn_margin = (BtnView)this.mView.findViewById(2131362031);
      this.btn_standard = (BtnView)this.mView.findViewById(2131362055);
      this.btn_wide = (BtnView)this.mView.findViewById(2131362066);
      this.mRadarRegulationView = (RadarRegulationView)this.mView.findViewById(2131363006);
      this.btn_radar_bg.setOnClickListener(this);
      this.btn_overlook.setOnClickListener(this);
      this.btn_margin.setOnClickListener(this);
      this.btn_standard.setOnClickListener(this);
      this.btn_wide.setOnClickListener(this);
      this.leftClose.setOnClickListener(this);
      this.rightClose.setOnClickListener(this);
      this.iv_radar_stop.setOnClickListener(this);
      this.iv_radar_voice.setOnClickListener(this);
      this.showCarRadar.setOnClickListener(this);
      this.hideCarRadar.setOnClickListener(this);
      this.showRightBar.setOnClickListener(this);
      this.showAllView();
      boolean var3 = this.mSharedPreferences.getBoolean("283_radar_left", true);
      RelativeLayout var4 = this.LeftRelative;
      byte var2 = 0;
      byte var1;
      if (var3) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var4.setVisibility(var1);
      var4 = this.leftRadarView;
      if (var3) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var4.setVisibility(var1);
      ImageView var5 = this.showCarRadar;
      if (var3) {
         var1 = 8;
      } else {
         var1 = 0;
      }

      var5.setVisibility(var1);
      var3 = this.mSharedPreferences.getBoolean("283_radar_right", true);
      LinearLayout var6 = this.rightLinear;
      if (var3) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var6.setVisibility(var1);
      var5 = this.showRightBar;
      var1 = var2;
      if (var3) {
         var1 = 8;
      }

      var5.setVisibility(var1);
   }

   private boolean isRadarDistance() {
      return GeneralDzData.radar_left_r >= 165 && GeneralDzData.radar_left_mf >= 165 && GeneralDzData.radar_left_mr >= 165 && GeneralDzData.radar_left_f >= 165 && GeneralDzData.radar_right_f >= 165 && GeneralDzData.radar_right_mf >= 165 && GeneralDzData.radar_right_mr >= 165 && GeneralDzData.radar_right_r >= 165 && GeneralDzData.radar_rear_ml >= 165 && GeneralDzData.radar_rear_l >= 165 && GeneralDzData.radar_rear_mr >= 165 && GeneralDzData.radar_rear_r >= 165 && GeneralDzData.radar_front_ml >= 165 && GeneralDzData.radar_front_l >= 165 && GeneralDzData.radar_front_mr >= 165 && GeneralDzData.radar_front_r >= 165;
   }

   private boolean isRadarShowCondition() {
      return !GeneralDzData.handBrake && !GeneralDzData.gears && GeneralDzData.speed <= 20;
   }

   private void justShowRadar() {
      this.LeftRelative.setVisibility(8);
      this.rightLinear.setVisibility(8);
   }

   // $FF: synthetic method
   static void lambda$sendRadarStop$1(boolean var0) {
      MessageSender.sendMsg((byte)74, (byte)11, var0);

      try {
         Thread.sleep(200L);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      MessageSender.sendMsg((byte)74, (byte)12, var0);
   }

   private void sendRadarStop(boolean var1) {
      if (GeneralDzData.parking_function) {
         MessageSender.sendMsg((byte)74, (byte)12, var1);
      } else {
         (new Thread(new RadarView$$ExternalSyntheticLambda1(var1))).start();
      }

   }

   private void showAllView() {
      if (!this.isHideLeftRadar) {
         this.LeftRelative.setVisibility(0);
      }

      if (this.isBackCar) {
         this.rightLinear.setVisibility(0);
      }

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
      this.LeftRelative.setVisibility(8);
      this.leftRadarView.setVisibility(8);
      this.showCarRadar.setVisibility(8);
   }

   // $FF: synthetic method
   void lambda$updateUi$0$com_hzbhd_canbus_car_cus__283_view_RadarView() {
      boolean var1 = GeneralDzData.handBrake;
      boolean var2 = false;
      if (var1) {
         this.iv_radar_stop.setEnabled(false);
         this.iv_radar_stop.setSelected(false);
         this.iv_radar_voice.setEnabled(false);
         this.iv_radar_voice.setSelected(false);
      } else {
         this.iv_radar_stop.setEnabled(true);
         ImageView var3 = this.iv_radar_stop;
         var1 = var2;
         if (GeneralDzData.parking_function) {
            var1 = var2;
            if (GeneralDzData.parking_switch) {
               var1 = true;
            }
         }

         var3.setSelected(var1);
         this.iv_radar_voice.setEnabled(true);
         this.iv_radar_voice.setSelected(GeneralDzData.parking_radar_voice ^ true);
      }

   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362031:
            MessageSender.sendMsg(new byte[]{22, -14, 9, -1});
            break;
         case 2131362037:
            MessageSender.sendMsg(new byte[]{22, -14, 3, -1});
            break;
         case 2131362043:
            if (this.isTransparency) {
               this.mRadarRegulationView.setVisibility(8);
            } else {
               this.mRadarRegulationView.setVisibility(0);
            }

            this.isTransparency ^= true;
            break;
         case 2131362055:
            MessageSender.sendMsg(new byte[]{22, -14, 2, -1});
            break;
         case 2131362066:
            MessageSender.sendMsg(new byte[]{22, -14, 1, -1});
            break;
         case 2131362369:
            this.isHideLeftRadar = true;
            this.LeftRelative.setVisibility(8);
            this.showCarRadar.setVisibility(0);
            this.leftRadarView.setVisibility(8);
            this.mEditor.putBoolean("283_radar_left", false);
            this.mEditor.apply();
            break;
         case 2131362609:
            if (GeneralDzData.parking_switch) {
               this.sendRadarStop(true);
            } else {
               this.sendRadarStop(false);
            }
            break;
         case 2131362610:
            MessageSender.sendMsg((byte)74, (byte)8, GeneralDzData.parking_radar_voice ^ true);
            break;
         case 2131362720:
            this.LeftRelative.setVisibility(8);
            break;
         case 2131363148:
            this.rightLinear.setVisibility(8);
            this.showRightBar.setVisibility(0);
            this.mEditor.putBoolean("283_radar_right", false);
            this.mEditor.apply();
            break;
         case 2131363327:
            this.isHideLeftRadar = false;
            this.showCarRadar.setVisibility(8);
            this.leftRadarView.setVisibility(0);
            this.LeftRelative.setVisibility(0);
            this.mEditor.putBoolean("283_radar_left", true);
            this.mEditor.apply();
            break;
         case 2131363330:
            this.rightLinear.setVisibility(0);
            this.showRightBar.setVisibility(8);
            this.mEditor.putBoolean("283_radar_right", true);
            this.mEditor.apply();
      }

   }

   public void refreshUi() {
      this.updateUi();
      if (!this.getReverseState()) {
         this.addViewToWindow();
      }

   }

   public void setRadarBackground(Drawable var1) {
      this.relativeLayout.setBackground(var1);
   }

   public void setRadarBackgroundColor(int var1) {
      this.relativeLayout.setBackgroundColor(var1);
   }

   public void showRadar() {
      boolean var3 = this.mSharedPreferences.getBoolean("283_radar_left", true);
      RelativeLayout var4 = this.LeftRelative;
      byte var2 = 0;
      byte var1;
      if (var3) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var4.setVisibility(var1);
      var4 = this.leftRadarView;
      if (var3) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var4.setVisibility(var1);
      ImageView var5 = this.showCarRadar;
      var1 = var2;
      if (var3) {
         var1 = 8;
      }

      var5.setVisibility(var1);
   }

   public void updateUi() {
      this.front_radar_left_mid.setImageDrawable(this.getRadarImageResource(this.front_radar_left_midImages, GeneralDzData.radar_front_ml));
      this.front_radar_left.setImageDrawable(this.getRadarImageResource(this.front_radar_leftImages, GeneralDzData.radar_front_l));
      this.front_radar_right_mid.setImageDrawable(this.getRadarImageResource(this.front_radar_right_midImages, GeneralDzData.radar_front_mr));
      this.front_radar_right.setImageDrawable(this.getRadarImageResource(this.front_radar_rightImages, GeneralDzData.radar_front_r));
      this.rear_radar_left_mid.setImageDrawable(this.getRadarImageResource(this.rear_radar_left_midImages, GeneralDzData.radar_rear_ml));
      this.rear_radar_left.setImageDrawable(this.getRadarImageResource(this.rear_radar_leftImages, GeneralDzData.radar_rear_l));
      this.rear_radar_right_mid.setImageDrawable(this.getRadarImageResource(this.rear_radar_right_midImages, GeneralDzData.radar_rear_mr));
      this.rear_radar_right.setImageDrawable(this.getRadarImageResource(this.rear_radar_rightImages, GeneralDzData.radar_rear_r));
      this.right_radar_front.setImageDrawable(this.getRadarImageResource(this.right_radar_frontImages, GeneralDzData.radar_right_f));
      this.right_radar_mid_front.setImageDrawable(this.getRadarImageResource(this.right_radar_mid_frontImages, GeneralDzData.radar_right_mf));
      this.right_radar_mid_rear.setImageDrawable(this.getRadarImageResource(this.right_radar_mid_rearImages, GeneralDzData.radar_right_mr));
      this.right_radar_rear.setImageDrawable(this.getRadarImageResource(this.right_radar_rearImages, GeneralDzData.radar_right_r));
      this.left_radar_front.setImageDrawable(this.getRadarImageResource(this.left_radar_frontImages, GeneralDzData.radar_left_f));
      this.left_radar_mid_front.setImageDrawable(this.getRadarImageResource(this.left_radar_mid_frontImages, GeneralDzData.radar_left_mf));
      this.left_radar_mid_rear.setImageDrawable(this.getRadarImageResource(this.left_radar_mid_rearImages, GeneralDzData.radar_left_mr));
      this.left_radar_rear.setImageDrawable(this.getRadarImageResource(this.left_radar_rearImages, GeneralDzData.radar_left_r));
      this.btn_overlook.setSelect(GeneralDzData.camera_overlook);
      this.btn_standard.setSelect(GeneralDzData.camera_standard);
      this.btn_wide.setSelect(GeneralDzData.camera_wide);
      this.btn_margin.setSelect(GeneralDzData.camera_margin);
      (new Handler(Looper.getMainLooper())).post(new RadarView$$ExternalSyntheticLambda0(this));
      MyLog.temporaryTracking("雷达数据初始化完成");
   }
}
