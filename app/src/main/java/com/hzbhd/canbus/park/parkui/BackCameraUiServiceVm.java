package com.hzbhd.canbus.park.parkui;

import android.app.Service;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.adapter.util.ScreenLogic;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.park.external360cam.External360CamCmds;
import com.hzbhd.canbus.park.panoramic.ParkPanoramic;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.config.use.CanBusDefault;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;

public class BackCameraUiServiceVm implements BackCameraUiServiceBase {
   private static final String TAG = "fang";
   private int mExternal360CamType = 0;
   private final Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final BackCameraUiServiceVm this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 1) {
            this.this$0.refreshUi(var1.getData());
         }

      }
   };
   private boolean mIsShowRadarLayout;
   private ParkPageUiSet mParkPageUiSet;
   private ParkPanoramic mParkPanoramic;
   private Service mService;

   public BackCameraUiServiceVm(Service var1) {
      this.mService = var1;
   }

   private void addViewToWindow() {
      if (LogUtil.log5()) {
         LogUtil.d("addViewToWindow: ");
      }

      if (UiMgrFactory.getCanUiMgr(this.mService) != null) {
         this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(this.mService).getParkPageUiSet(this.mService);
      }

      ParkPageUiSet var2 = this.mParkPageUiSet;
      if (var2 != null) {
         OnBackCameraStatusListener var3 = var2.getOnBackCameraStatusListener();
         if (var3 != null) {
            var3.addViewToWindows();
         }

         boolean var1 = ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getRadarDispCheck();
         com.hzbhd.canbus.util.LogUtil.showLog("fang", "IsShowRadar:" + this.mParkPageUiSet.isIsShowRadar() + ", showRadarFromSetting:" + var1);
         if ((!var1 || !this.mParkPageUiSet.isIsShowRadar()) && this.mService.getResources().getConfiguration().orientation != 1) {
            Vm.Companion.getVm().getAction().getRadar().setRadarVisible(8);
         } else {
            Vm.Companion.getVm().getAction().getRadar().setRadarVisible(0);
            if (!SharePreUtil.getBoolValue(this.mService, "is_show_radar", true) && this.mService.getResources().getConfiguration().orientation != 1) {
               Vm.Companion.getVm().getAction().getRadar().setRadarScale(false);
            } else {
               Vm.Companion.getVm().getAction().getRadar().setRadarScale(true);
            }

            Vm.Companion.getVm().getAction().getRadar().setSmallRadar(this.mParkPageUiSet.isHaveLeftRightRadar());
         }

         com.hzbhd.canbus.util.LogUtil.showLog("fang", "IsShowPanoramic:" + this.mParkPageUiSet.isShowPanoramic());
         if (this.mParkPageUiSet.isShowPanoramic()) {
            Vm.Companion.getVm().getAction().getReverse().setPanoramicVisible(0);
         } else {
            Vm.Companion.getVm().getAction().getReverse().setPanoramicVisible(8);
         }

         com.hzbhd.canbus.util.LogUtil.showLog("fang", "isShowCusPanoramic:" + this.mParkPageUiSet.isShowCusPanoramicView());
         if (this.mParkPageUiSet.isShowCusPanoramicView()) {
            Vm.Companion.getVm().getAction().getReverse().setCustomPanoramicVisible(true);
         } else {
            Vm.Companion.getVm().getAction().getReverse().setCustomPanoramicVisible(false);
         }
      } else {
         com.hzbhd.canbus.util.LogUtil.showLog("fang", "mParkPageUiSet==null");
         if (this.mService.getResources().getConfiguration().orientation == 2) {
            Vm.Companion.getVm().getAction().getRadar().setRadarVisible(8);
         }
      }

      if (ParkPanoramic.isEnableParkPanoramic()) {
         com.hzbhd.canbus.util.LogUtil.showLog("fang", "isEnableParkPanoramic");
         if (this.mParkPanoramic == null) {
            this.mParkPanoramic = new ParkPanoramic();
         }

         this.mParkPanoramic.constructParkPanoramic();
      }

      Vm.Companion.getVm().getReverseMainView().setOnTouchListener(new View.OnTouchListener(this) {
         private float downX;
         private float downY;
         private final Runnable runnable;
         final BackCameraUiServiceVm this$0;

         {
            this.this$0 = var1;
            this.downX = 0.0F;
            this.downY = 0.0F;
            this.runnable = new BackCameraUiServiceVm$2$$ExternalSyntheticLambda0();
         }

         // $FF: synthetic method
         static void lambda$$0() {
            if (CanBusDefault.INSTANCE.getLongClick()) {
               com.hzbhd.canbus.util.LogUtil.showLog("fang", "onLongClick");
               SendKeyManager.getInstance().setKeyEvent(4108, 0, 0);
            }

         }

         public boolean onTouch(View var1, MotionEvent var2) {
            try {
               int var3 = (int)var2.getX();
               int var4 = (int)var2.getY();
               if (this.this$0.mParkPageUiSet != null) {
                  StringBuilder var6 = new StringBuilder();
                  Log.i("BackCameraUiService", var6.append("onTouch: touchListener: ").append(this.this$0.mParkPageUiSet.getOnPanoramicItemTouchListener()).toString());
                  if (this.this$0.mParkPageUiSet.getOnPanoramicItemTouchListener() != null) {
                     this.this$0.mParkPageUiSet.getOnPanoramicItemTouchListener().onTouchItem(var2);
                  }
               }

               if (var2.getAction() == 1) {
                  External360CamCmds.getInstance().getCmds().sndCoord(var3, var4);
               }

               if (var2.getAction() == 0) {
                  this.downX = var2.getX();
                  this.downY = var2.getY();
                  this.this$0.mHandler.postDelayed(this.runnable, 500L);
               } else {
                  if (var2.getAction() == 2 && Math.sqrt(Math.pow((double)(var2.getX() - this.downX), 2.0) + Math.pow((double)(var2.getY() - this.downY), 2.0)) < 20.0) {
                     return true;
                  }

                  this.this$0.mHandler.removeCallbacks(this.runnable);
               }
            } catch (Exception var5) {
               com.hzbhd.canbus.util.LogUtil.showLog("fang", var5.toString());
            }

            return true;
         }
      });
      if (CanIdSpecialConfig.hideRadarLayoutCanID(CanbusConfig.INSTANCE.getCanType())) {
         Vm.Companion.getVm().getAction().getRadar().setRadarVisible(8);
      }

   }

   private void refreshUi(Bundle var1) {
      com.hzbhd.canbus.util.LogUtil.showLog("fang", "BackCameraUiService refreshUi");
      if (this.mParkPageUiSet == null) {
         com.hzbhd.canbus.util.LogUtil.showLog("fang", "mParkPageUiSet==null");
      } else {
         com.hzbhd.canbus.util.LogUtil.showLog("fang", "BackCameraUiService mParkPageUiSet.isIsShowRadar():" + this.mParkPageUiSet.isIsShowRadar());
         if (this.mIsShowRadarLayout != this.mParkPageUiSet.isIsShowRadar()) {
            this.mIsShowRadarLayout = this.mParkPageUiSet.isIsShowRadar();
            if (this.mParkPageUiSet.isIsShowRadar()) {
               Vm.Companion.getVm().getAction().getRadar().setRadarVisible(0);
               if (SharePreUtil.getBoolValue(this.mService, "is_show_radar", true)) {
                  Vm.Companion.getVm().getAction().getRadar().setRadarScale(true);
               } else {
                  Vm.Companion.getVm().getAction().getRadar().setRadarScale(false);
               }
            } else {
               Vm.Companion.getVm().getAction().getRadar().setRadarVisible(8);
            }
         }

         if (LogUtil.log5()) {
            LogUtil.d("refreshUi: " + this.mParkPageUiSet.isIsShowRadar());
         }

         if (this.mParkPageUiSet.isIsShowRadar()) {
            Vm.Companion.getVm().getAction().getRadar().setShowDistanceNotShowLocationUi(GeneralParkData.isShowDistanceNotShowLocationUi);
            Vm.Companion.getVm().getAction().getRadar().setShowLeftTopOneDistanceUi(GeneralParkData.isShowLeftTopOneDistanceUi);
         }

         List var3 = GeneralParkData.dataList;
         if (var3 != null) {
            Iterator var2 = var3.iterator();

            while(var2.hasNext()) {
               PanoramicBtnUpdateEntity var4 = (PanoramicBtnUpdateEntity)var2.next();
               if (this.mParkPageUiSet.getPanoramicBtnList() != null) {
                  ((ParkPageUiSet.Bean)this.mParkPageUiSet.getPanoramicBtnList().get(var4.getIndex())).setSelect(var4.isSelect());
               }
            }

            Vm.Companion.getVm().getAction().getReverse().updatePanoramic();
         }

         Vm.Companion.getVm().getAction().getReverse().setCustomPanoramicVisible(this.mParkPageUiSet.isShowCusPanoramicView());
      }
   }

   public void changeVideoType() {
   }

   public Handler getmHandler() {
      return this.mHandler;
   }

   // $FF: synthetic method
   Unit lambda$onCreate$0$com_hzbhd_canbus_park_parkui_BackCameraUiServiceVm() {
      this.addViewToWindow();
      return null;
   }

   public void onConfigurationChanged(Configuration var1) {
      com.hzbhd.canbus.util.LogUtil.showLog("fang", "onConfigurationChanged");
   }

   public void onCreate() {
      Vm.Companion.getVm().getAction().getMain().init();
      ScreenLogic.setOrientation(this.mService.getApplicationContext().getResources().getConfiguration().orientation);
      this.mService.setTheme(16974064);
      this.mExternal360CamType = FutureUtil.instance.is360External();
      HzbhdLog.d("fang", "BackCameraUiService onCreate" + Vm.Companion.getVm().getReverseListener().isReversing());
      if (Vm.Companion.getVm().getReverseListener().isReversing()) {
         this.addViewToWindow();
      }

      Vm.Companion.getVm().getReverseListener().setActionBefortViewInit(new BackCameraUiServiceVm$$ExternalSyntheticLambda0(this));
   }

   public void onDestroy() {
   }

   public void setAnalogColorUiChange(int var1, int var2, int var3) {
      Log.d("fang", "setAnalogColorUiChange ");
   }
}
