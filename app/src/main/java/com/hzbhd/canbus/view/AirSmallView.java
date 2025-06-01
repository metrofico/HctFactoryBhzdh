package com.hzbhd.canbus.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AirSmallView {
   private boolean isInit = false;
   private boolean isShowing = false;
   private AirPageUiSet mAirPageUiSet;
   private Context mContext;
   private RelativeLayout mFloatView;
   private WindowManager.LayoutParams mLayoutParams;
   private ImageView[] mLeftBlow;
   private ImageView mLeftBlowAuto;
   private ImageView mLeftBlowFoot;
   private ImageView mLeftBlowHead;
   private ImageView mLeftBlowWindow;
   private ImageView mLeftHeat;
   private TextView mLeftTemperature;
   private ImageView[] mRightBlow;
   private ImageView mRightBlowAuto;
   private ImageView mRightBlowFoot;
   private ImageView mRightBlowHead;
   private ImageView mRightBlowWindow;
   private ImageView mRightHeat;
   private TextView mRightTemperature;
   private Runnable mRunnable = new Runnable(this) {
      final AirSmallView this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.dismissView();
      }
   };
   private int mSwitchStatus;
   private LinearLayout mTopStatusLayout;
   private List mTopStatusList;
   private SetWindSpeedView mWindSpeedWsv;
   private WindowManager mWindowManager;

   public AirSmallView(Context var1) {
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      this.findView();
      this.initView(var1);
   }

   private void addViewToWindow() {
      if (this.mLayoutParams == null) {
         WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
         this.mLayoutParams = var1;
         var1.type = 2002;
         this.mLayoutParams.gravity = 80;
         this.mLayoutParams.width = -1;
         this.mLayoutParams.height = -2;
      }

      if (!this.isShowing) {
         this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
         this.isShowing = true;
      }

      this.mFloatView.removeCallbacks(this.mRunnable);
      this.mFloatView.postDelayed(this.mRunnable, 5000L);
   }

   private void dismissView() {
      WindowManager var2 = this.mWindowManager;
      if (var2 != null) {
         RelativeLayout var1 = this.mFloatView;
         if (var1 != null) {
            var2.removeView(var1);
            this.isShowing = false;
         }
      }

   }

   private void findView() {
      this.mAirPageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getAirUiSet(this.mContext);
      RelativeLayout var1 = (RelativeLayout)LayoutInflater.from(this.mContext).inflate(2131558753, (ViewGroup)null);
      this.mFloatView = var1;
      this.mTopStatusLayout = (LinearLayout)var1.findViewById(2131362807);
      this.mWindSpeedWsv = (SetWindSpeedView)this.mFloatView.findViewById(2131363793);
      this.mLeftTemperature = (TextView)this.mFloatView.findViewById(2131363645);
      this.mRightTemperature = (TextView)this.mFloatView.findViewById(2131363685);
      this.mLeftBlowAuto = (ImageView)this.mFloatView.findViewById(2131362584);
      this.mLeftBlowWindow = (ImageView)this.mFloatView.findViewById(2131362587);
      this.mLeftBlowHead = (ImageView)this.mFloatView.findViewById(2131362586);
      this.mLeftBlowFoot = (ImageView)this.mFloatView.findViewById(2131362585);
      this.mRightBlowAuto = (ImageView)this.mFloatView.findViewById(2131362616);
      this.mRightBlowWindow = (ImageView)this.mFloatView.findViewById(2131362619);
      this.mRightBlowHead = (ImageView)this.mFloatView.findViewById(2131362618);
      this.mRightBlowFoot = (ImageView)this.mFloatView.findViewById(2131362617);
      this.mLeftHeat = (ImageView)this.mFloatView.findViewById(2131362594);
   }

   private AirSmallStatusItemView getItem(Context var1, String var2) {
      var2.hashCode();
      int var4 = var2.hashCode();
      byte var3 = -1;
      switch (var4) {
         case -1470045433:
            if (var2.equals("front_defog")) {
               var3 = 0;
            }
            break;
         case -631663038:
            if (var2.equals("rear_defog")) {
               var3 = 1;
            }
            break;
         case 3106:
            if (var2.equals("ac")) {
               var3 = 2;
            }
            break;
         case 96835:
            if (var2.equals("aqs")) {
               var3 = 3;
            }
            break;
         case 3005871:
            if (var2.equals("auto")) {
               var3 = 4;
            }
            break;
         case 3094652:
            if (var2.equals("dual")) {
               var3 = 5;
            }
            break;
         case 3545755:
            if (var2.equals("sync")) {
               var3 = 6;
            }
            break;
         case 106858757:
            if (var2.equals("power")) {
               var3 = 7;
            }
            break;
         case 756225563:
            if (var2.equals("in_out_cycle")) {
               var3 = 8;
            }
      }

      AirSmallStatusItemView var5;
      switch (var3) {
         case 0:
            var5 = new AirSmallStatusItemView(var1, var2, 2131234085, 2131234086, new OnAirInfoChangeListener(this) {
               final AirSmallView this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onAirInfoChange() {
                  boolean var1;
                  if (!GeneralAirData.front_defog && !GeneralAirData.max_front) {
                     var1 = false;
                  } else {
                     var1 = true;
                  }

                  return var1;
               }
            });
            break;
         case 1:
            var5 = new AirSmallStatusItemView(var1, var2, 2131234090, 2131234091, new OnAirInfoChangeListener(this) {
               final AirSmallView this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onAirInfoChange() {
                  return GeneralAirData.rear_defog;
               }
            });
            break;
         case 2:
            var5 = new AirSmallStatusItemView(var1, var2, 2131234069, 2131234070, new OnAirInfoChangeListener(this) {
               final AirSmallView this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onAirInfoChange() {
                  return GeneralAirData.ac;
               }
            });
            break;
         case 3:
            var5 = new AirSmallStatusItemView(var1, var2, 2131234071, 2131234072, new OnAirInfoChangeListener(this) {
               final AirSmallView this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onAirInfoChange() {
                  return GeneralAirData.aqs;
               }
            });
            break;
         case 4:
            var5 = new AirSmallStatusItemView(var1, var2, 2131234073, 2131234074, new OnAirInfoChangeListener(this) {
               final AirSmallView this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onAirInfoChange() {
                  boolean var1;
                  if (!GeneralAirData.auto && !GeneralAirData.ac_auto) {
                     var1 = false;
                  } else {
                     var1 = true;
                  }

                  return var1;
               }
            });
            break;
         case 5:
            var5 = new AirSmallStatusItemView(var1, var2, 2131234083, 2131234084, new OnAirInfoChangeListener(this) {
               final AirSmallView this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onAirInfoChange() {
                  return GeneralAirData.dual;
               }
            });
            break;
         case 6:
            var5 = new AirSmallStatusItemView(var1, var2, 2131234096, 2131234097, new OnAirInfoChangeListener(this) {
               final AirSmallView this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onAirInfoChange() {
                  return GeneralAirData.sync;
               }
            });
            break;
         case 7:
            var5 = new AirSmallStatusItemView(var1, var2, 2131234088, 2131234089, new OnAirInfoChangeListener(this) {
               final AirSmallView this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onAirInfoChange() {
                  return GeneralAirData.power;
               }
            });
            break;
         case 8:
            var5 = new AirSmallStatusItemView(var1, var2, 2131234081, 2131234082, new OnAirInfoChangeListener(this) {
               final AirSmallView this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onAirInfoChange() {
                  return GeneralAirData.in_out_cycle;
               }
            });
            break;
         default:
            var5 = null;
      }

      return var5;
   }

   private void initView(Context var1) {
      if (this.mAirPageUiSet != null) {
         this.mFloatView.setOnClickListener(new View.OnClickListener(this) {
            final AirSmallView this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.mFloatView.removeCallbacks(this.this$0.mRunnable);
               this.this$0.mFloatView.post(this.this$0.mRunnable);
            }
         });
         this.mFloatView.findViewById(2131361911).setOnClickListener(new View.OnClickListener(this, var1) {
            final AirSmallView this$0;
            final Context val$context;

            {
               this.this$0 = var1;
               this.val$context = var2;
            }

            public void onClick(View var1) {
               Intent var2 = new Intent(this.val$context, AirActivity.class);
               var2.setFlags(268435456);
               this.val$context.startActivity(var2);
               this.this$0.mFloatView.removeCallbacks(this.this$0.mRunnable);
               this.this$0.mFloatView.post(this.this$0.mRunnable);
            }
         });
         this.mWindSpeedWsv.initViews(this.mContext, this.mAirPageUiSet.getFrontArea().isCanSetWindSpeed(), DataHandleUtils.rangeNumber(this.mAirPageUiSet.getFrontArea().getWindMaxLevel(), 0, 8), (OnAirWindSpeedUpDownClickListener)null);
         this.mWindSpeedWsv.findViewById(2131361909).setBackground((Drawable)null);
         this.mWindSpeedWsv.findViewById(2131362387).setVisibility(4);
         this.mWindSpeedWsv.findViewById(2131362425).setVisibility(4);
         this.setVisible(this.mLeftHeat, this.mAirPageUiSet.getFrontArea().isShowSeatHeat());
         this.mTopStatusList = new ArrayList();
         String[] var5 = this.mAirPageUiSet.getFrontArea().getSmallWindowStatusArray();
         if (!ArrayUtils.isEmpty(var5)) {
            for(int var2 = 0; var2 < var5.length && var2 < 4; ++var2) {
               AirSmallStatusItemView var3 = this.getItem(this.mContext, var5[var2]);
               if (var3 == null) {
                  Log.i("AirSmallView", "initView: The action[" + var5[var2] + "] is undefined in smallWindow");
               } else {
                  LinearLayout.LayoutParams var4 = new LinearLayout.LayoutParams(0, -1, 1.0F);
                  this.mTopStatusLayout.addView(var3, var4);
                  this.mTopStatusList.add(var3);
               }
            }
         }

         byte var6 = ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature();
         this.mSwitchStatus = var6;
         if (var6 == 1) {
            this.switchLeftRight();
         }

         this.isInit = true;
      }
   }

   private void setVisible(View var1, boolean var2) {
      if (var1 != null) {
         if (var2) {
            var1.setVisibility(0);
         } else {
            var1.setVisibility(4);
         }

      }
   }

   public void refreshUi() {
      if (this.isInit) {
         if (this.mSwitchStatus != ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
            this.mSwitchStatus = ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature();
            this.switchLeftRight();
         }

         this.mWindSpeedWsv.setCurWindSpeed(GeneralAirData.front_wind_level);
         this.mWindSpeedWsv.setAuto(GeneralAirData.front_auto_wind_speed);
         this.mLeftTemperature.setText(CommUtil.temperatureUnitSwitch(GeneralAirData.front_left_temperature));
         this.mRightTemperature.setText(CommUtil.temperatureUnitSwitch(GeneralAirData.front_right_temperature));
         this.setVisible(this.mLeftBlowAuto, GeneralAirData.front_left_auto_wind);
         this.setVisible(this.mLeftBlowWindow, GeneralAirData.front_left_blow_window);
         this.setVisible(this.mLeftBlowHead, GeneralAirData.front_left_blow_head);
         this.setVisible(this.mLeftBlowFoot, GeneralAirData.front_left_blow_foot);
         this.setVisible(this.mRightBlowAuto, GeneralAirData.front_right_auto_wind);
         this.setVisible(this.mRightBlowWindow, GeneralAirData.front_right_blow_window);
         this.setVisible(this.mRightBlowHead, GeneralAirData.front_right_blow_head);
         this.setVisible(this.mRightBlowFoot, GeneralAirData.front_right_blow_foot);
         if (this.mSwitchStatus == 0) {
            this.mLeftHeat.setImageResource(CommUtil.getImgIdByResId(this.mContext, "ic_air_s_seat_heat_" + DataHandleUtils.rangeNumber(GeneralAirData.front_left_seat_heat_level, 0, 3)));
         } else {
            this.mLeftHeat.setImageResource(CommUtil.getImgIdByResId(this.mContext, "ic_air_s_seat_heat_" + DataHandleUtils.rangeNumber(GeneralAirData.front_right_seat_heat_level, 0, 3)));
         }

         Iterator var1 = this.mTopStatusList.iterator();

         while(var1.hasNext()) {
            ((AirSmallStatusItemView)var1.next()).turn();
         }

         this.addViewToWindow();
      }
   }

   public void switchLeftRight() {
      Log.i("AirSmallView", "switchLeftRight: " + this.mSwitchStatus);
      TextView var1 = this.mLeftTemperature;
      this.mLeftTemperature = this.mRightTemperature;
      this.mRightTemperature = var1;
   }
}
