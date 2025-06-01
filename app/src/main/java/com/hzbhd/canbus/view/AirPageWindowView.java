package com.hzbhd.canbus.view;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;

public class AirPageWindowView extends AbstractBaseActivity {
   public static final int AIR_FRONT_WHAT = 1001;
   public static final int AIR_FRONT_WHAT_NO_TURN = 1004;
   public static final int AIR_REAR_WHAT = 1002;
   public static final int AIR_REAR_WHAT_NO_TURN = 1003;
   public static final String BUNDLE_AIR_WHAT = "bundle_air_what";
   private static AirPageWindowView airPageWindowView;
   private static CountDownTimer countDownTimer;
   private static IShareIntListener iShareIntListener;
   private static Context mContext;
   public static boolean mIsAirActivityInit;
   public static boolean mIsClickOpen;
   public static boolean mIsMsgOpen;
   private static int mWhat;
   public static int page;
   public boolean addTag = false;
   private ImageButton hide;
   private WindowManager.LayoutParams layoutParams;
   private AirFrontWindowView mAirFrontView;
   private AirRearWindowView mAirRearView;
   private AirPageUiSet mSet;
   private WindowManager mWindowManager;
   private View view;
   private LinearLayout window_lay;

   private AirPageWindowView(Context var1) {
      this.initWindow(var1);
   }

   private void findViews(View var1) {
      this.window_lay = (LinearLayout)var1.findViewById(2131363794);
      this.mAirFrontView = (AirFrontWindowView)var1.findViewById(2131361906);
      this.mAirRearView = (AirRearWindowView)var1.findViewById(2131361908);
      ImageButton var2 = (ImageButton)var1.findViewById(2131362368);
      this.hide = var2;
      var2.setOnClickListener(new View.OnClickListener(this) {
         final AirPageWindowView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.hide();
         }
      });
      this.window_lay.setOnTouchListener(new View.OnTouchListener(this) {
         final AirPageWindowView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            this.this$0.initCountDownTimer();
            return false;
         }
      });
   }

   public static AirPageWindowView getInstance(Context var0) {
      mContext = var0;
      if (airPageWindowView == null) {
         airPageWindowView = new AirPageWindowView(var0);
      }

      return airPageWindowView;
   }

   private void initView(Context var1) {
      View var2 = LayoutInflater.from(var1).inflate(2131558619, (ViewGroup)null);
      this.view = var2;
      this.findViews(var2);
      this.initViews(this.view);
   }

   private void initViews(View var1) {
      AirPageUiSet var2 = this.getUiMgrInterface(mContext).getAirUiSet(mContext);
      this.mSet = var2;
      if (var2 != null) {
         if (var2.getOnAirInitListener() != null) {
            this.mSet.getOnAirInitListener().onInit();
         }

         this.mAirFrontView.initViews(this, this.mSet);
         this.mAirRearView.setVisibility(4);
         if (this.mSet.isHaveRearArea()) {
            this.mAirRearView.initViews(this, this.mSet);
         }

      }
   }

   private void selectPagaFrontRear(Bundle var1) {
      int var2 = var1.getInt("bundle_air_what");
      mWhat = var2;
      switch (var2) {
         case 1001:
            if (this.mAirFrontView != null) {
               this.switchViewPager(0);
            }
            break;
         case 1002:
            if (this.mAirRearView != null && this.mSet.isHaveRearArea()) {
               this.switchViewPager(1);
            }
            break;
         case 1003:
            AirRearWindowView var4 = this.mAirRearView;
            if (var4 != null) {
               var4.refreshUi();
            }
            break;
         case 1004:
            AirFrontWindowView var3 = this.mAirFrontView;
            if (var3 != null) {
               var3.refreshUi();
            }
      }

   }

   public void hide() {
      MyLog.temporaryTracking("hide");

      label39: {
         Exception var10000;
         label43: {
            boolean var10001;
            WindowManager var2;
            try {
               var2 = this.mWindowManager;
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label43;
            }

            if (var2 == null) {
               break label39;
            }

            View var1;
            try {
               var1 = this.view;
            } catch (Exception var4) {
               var10000 = var4;
               var10001 = false;
               break label43;
            }

            if (var1 == null) {
               break label39;
            }

            try {
               if (this.addTag) {
                  var2.removeView(var1);
                  this.addTag = false;
               }
               break label39;
            } catch (Exception var3) {
               var10000 = var3;
               var10001 = false;
            }
         }

         Exception var6 = var10000;
         Log.e("Exception", var6.toString());
      }

      CountDownTimer var7 = countDownTimer;
      if (var7 != null) {
         var7.cancel();
      }

      mIsClickOpen = false;
      mIsMsgOpen = false;
   }

   public void initCountDownTimer() {
      CountDownTimer var1 = countDownTimer;
      if (var1 != null) {
         var1.cancel();
      }

      var1 = new CountDownTimer(this, 5000L, 1000L) {
         final AirPageWindowView this$0;

         {
            this.this$0 = var1;
         }

         public void onFinish() {
            this.this$0.hide();
         }

         public void onTick(long var1) {
         }
      };
      countDownTimer = var1;
      var1.start();
   }

   public void initWindow(Context var1) {
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var2 = new WindowManager.LayoutParams();
      this.layoutParams = var2;
      var2.gravity = 80;
      this.layoutParams.type = 2010;
      this.layoutParams.flags = 16777512;
      this.layoutParams.x = 0;
      this.layoutParams.y = 0;
      this.layoutParams.width = -1;
      this.layoutParams.height = -1;
      this.initView(var1);
   }

   public boolean isNeedSwitchTemAndSeat() {
      return ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature();
   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         if (var1 != null) {
            int var2 = var1.getInt("bundle_air_what");
            mWhat = var2;
            if (mIsAirActivityInit) {
               AirFrontWindowView var3;
               switch (var2) {
                  case 1001:
                     if (this.mAirFrontView != null) {
                        this.switchViewPager(0);
                     }
                     break;
                  case 1002:
                     if (this.mAirRearView != null && this.mSet.isHaveRearArea()) {
                        this.switchViewPager(1);
                     }
                     break;
                  case 1003:
                     AirRearWindowView var4 = this.mAirRearView;
                     if (var4 != null) {
                        var4.refreshUi();
                     }
                     break;
                  case 1004:
                     var3 = this.mAirFrontView;
                     if (var3 != null) {
                        var3.refreshUi();
                     }
               }

               var3 = this.mAirFrontView;
               if (var3 != null) {
                  var3.refreshUi();
               }

               AirPageUiSet var5 = this.mSet;
               if (var5 != null && this.mAirRearView != null && var5.isHaveRearArea()) {
                  this.mAirRearView.refreshUi();
               }

            }
         }
      }
   }

   public void show(Bundle var1) {
      this.initCountDownTimer();
      if (!this.addTag) {
         this.mWindowManager.addView(this.view, this.layoutParams);
         this.addTag = true;
      }

      this.mAirFrontView.refreshUi();
      if (this.mSet.isHaveRearArea()) {
         this.mAirRearView.refreshUi();
      }

      if (iShareIntListener == null) {
         iShareIntListener = new ReversState(this);
      }

      ShareDataManager.getInstance().registerShareIntListener("sys.Reverse", iShareIntListener);
      this.selectPagaFrontRear(var1);
   }

   public void switchViewPager(int var1) {
      if (var1 != 0) {
         if (var1 == 1) {
            this.mAirFrontView.setVisibility(4);
            this.mAirRearView.setVisibility(0);
            page = var1;
         }
      } else {
         this.mAirFrontView.setVisibility(0);
         this.mAirRearView.setVisibility(4);
         page = var1;
      }

   }

   private class ReversState implements IShareIntListener {
      final AirPageWindowView this$0;

      private ReversState(AirPageWindowView var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      ReversState(AirPageWindowView var1, Object var2) {
         this(var1);
      }

      public void onInt(int var1) {
         this.this$0.hide();
      }
   }
}
