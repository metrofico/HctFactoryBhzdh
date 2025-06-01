package com.hzbhd.canbus.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.view.AirFrontView;
import com.hzbhd.canbus.view.AirRearView;
import java.lang.ref.WeakReference;

public class AirActivity extends AbstractBaseActivity {
   public static final int AIR_FRONT_WHAT = 1001;
   public static final int AIR_FRONT_WHAT_NO_TURN = 1004;
   public static final int AIR_REAR_WHAT = 1002;
   public static final int AIR_REAR_WHAT_NO_TURN = 1003;
   public static final String BUNDLE_AIR_WHAT = "bundle_air_what";
   private static final int DISMISS_POPUP_VIEW = 100;
   public static boolean mIsAirActivityInit;
   public static boolean mIsClickOpen;
   public static boolean mIsMsgOpen;
   private static int mWhat;
   public static int page;
   private AirFrontView mAirFrontView;
   private AirRearView mAirRearView;
   private MyHandler mHandler = new MyHandler(this);
   private BroadcastReceiver mReceiver = new BroadcastReceiver(this) {
      final AirActivity this$0;

      {
         this.this$0 = var1;
      }

      public void onReceive(Context var1, Intent var2) {
         if ("intent_broadcast_finish_air_activity".equals(var2.getAction())) {
            this.this$0.finish();
         }

      }
   };
   private AirPageUiSet mSet;

   public static void clickToOpenActivity(Context var0) {
      mIsClickOpen = true;
      var0.startActivity(new Intent(var0, AirActivity.class));
   }

   private void findViews() {
      this.mAirFrontView = (AirFrontView)this.findViewById(2131361906);
      this.mAirRearView = (AirRearView)this.findViewById(2131361908);
   }

   private void initViews() {
      AirPageUiSet var1 = this.getUiMgrInterface(this).getAirUiSet(this);
      this.mSet = var1;
      if (var1 != null) {
         if (var1.getOnAirInitListener() != null) {
            this.mSet.getOnAirInitListener().onInit();
         }

         this.mAirFrontView.initViews(this, this.mSet);
         this.mAirRearView.setVisibility(4);
         if (this.mSet.isHaveRearArea()) {
            this.mAirRearView.initViews(this, this.mSet);
         }

         this.registerReceiver(this.mReceiver, new IntentFilter("intent_broadcast_finish_air_activity"));
      }
   }

   public static void msgOpenActivity(Context var0, Bundle var1) {
      mIsMsgOpen = true;
      mWhat = var1.getInt("bundle_air_what");
      if (!SystemUtil.isForeground(var0, AirActivity.class.getName())) {
         Intent var2 = new Intent(var0, AirActivity.class);
         var2.setFlags(268435456);
         var0.startActivity(var2);
      }

   }

   public boolean isNeedSwitchTemAndSeat() {
      return ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558654);
      this.findViews();
      this.initViews();
   }

   protected void onDestroy() {
      super.onDestroy();
      MyHandler var1 = this.mHandler;
      if (var1 != null) {
         var1.removeMessages(100);
      }

      BroadcastReceiver var2 = this.mReceiver;
      if (var2 != null) {
         this.unregisterReceiver(var2);
      }

   }

   protected void onResume() {
      super.onResume();
      mIsAirActivityInit = true;
      Bundle var1 = new Bundle();
      var1.putInt("bundle_air_what", mWhat);
      this.refreshUi(var1);
   }

   protected void onStop() {
      super.onStop();
      mIsClickOpen = false;
      mIsMsgOpen = false;
   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         if (var1 != null) {
            int var2 = var1.getInt("bundle_air_what");
            mWhat = var2;
            if (!mIsAirActivityInit) {
               LogUtil.showLog("activity not init");
            } else {
               AirFrontView var3;
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
                     AirRearView var4 = this.mAirRearView;
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

               MyHandler var6 = this.mHandler;
               if (var6 != null && mIsMsgOpen && !mIsClickOpen) {
                  var6.removeCallbacksAndMessages((Object)null);
                  this.mHandler.sendEmptyMessageDelayed(100, 5000L);
               }

            }
         }
      }
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

   private static class MyHandler extends Handler {
      private WeakReference activity;

      MyHandler(AirActivity var1) {
         this.activity = new WeakReference(var1);
      }

      public void handleMessage(Message var1) {
         if (var1.what == 100) {
            try {
               ((AirActivity)this.activity.get()).finish();
            } catch (Exception var2) {
               var2.printStackTrace();
            }
         }

      }
   }
}
