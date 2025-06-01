package com.hzbhd.canbus.park.parkui;

import android.app.Service;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.adapter.externel360camera.VZ360Constance;
import com.hzbhd.canbus.adapter.externel360camera.view.IrCam360VZLayout;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.adapter.util.ScreenLogic;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.AnalogColorSettingInterface;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.VideoTypeUiChangeInterface;
import com.hzbhd.canbus.park.external360cam.External360CamCmds;
import com.hzbhd.canbus.park.external360cam.IrCam360MaylasiaView;
import com.hzbhd.canbus.park.panoramic.PanoramicView;
import com.hzbhd.canbus.park.panoramic.ParkPanoramic;
import com.hzbhd.canbus.park.radar.RadarView;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.config.use.CanBusDefault;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import java.util.Iterator;
import java.util.List;

public class BackCameraUiServiceOld implements AnalogColorSettingInterface, VideoTypeUiChangeInterface, BackCameraUiServiceBase {
   private static final String TAG = "fang";
   private TextView mCameraTisTv;
   private RelativeLayout mCusPanoramicContainerView;
   private int mExternal360CamType = 0;
   private final Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final BackCameraUiServiceOld this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         int var2 = var1.what;
         if (var2 != 0) {
            if (var2 == 1) {
               this.this$0.refreshUi(var1.getData());
            }
         } else {
            boolean var3;
            if (var1.arg1 == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            Log.d("fang", "MSG_CHECK_BACKCAMERA_STATE " + var3);
            if (var3 && !this.this$0.mHasAddViewToWindow) {
               this.this$0.addViewToWindow(false);
               this.this$0.mHasAddViewToWindow = true;
            } else if (!var3 && this.this$0.mHasAddViewToWindow) {
               this.this$0.removeViewFromWindow();
               this.this$0.mHasAddViewToWindow = false;
            }
         }

      }
   };
   private boolean mHasAddViewToWindow = false;
   private IrCam360MaylasiaView mIrCam360MaylasiaView;
   private boolean mIsShowRadarLayout;
   private PanoramicView mPanoramicView;
   private ParkPageUiSet mParkPageUiSet;
   private ParkPanoramic mParkPanoramic;
   private RadarView mRadarView;
   private final IShareIntListener mReverseStatusListener = new IShareIntListener(this) {
      final BackCameraUiServiceOld this$0;

      {
         this.this$0 = var1;
      }

      public void onInt(int var1) {
         Log.d("fang", "REVERSE <onChanged> " + var1);
         this.this$0.setReverseState(var1);
      }
   };
   private Service mService;
   private View mTouchListenerView;
   private View mView;
   private WindowManager.LayoutParams mViewParams;
   private WindowManager mWindowManager;
   private RelativeLayout radar_all_page;

   public BackCameraUiServiceOld(Service var1) {
      this.mService = var1;
   }

   private void addViewToWindow(boolean var1) {
      this.initWindowParam();
      HzbhdLog.d("fang", "addViewToWindow");
      this.mRadarView = (RadarView)this.mView.findViewById(2131363776);
      this.mPanoramicView = (PanoramicView)this.mView.findViewById(2131363775);
      this.mCusPanoramicContainerView = (RelativeLayout)this.mView.findViewById(2131363772);
      this.mTouchListenerView = this.mView.findViewById(2131362083);
      this.mIrCam360MaylasiaView = (IrCam360MaylasiaView)this.mView.findViewById(2131363773);
      this.mCameraTisTv = (TextView)this.mView.findViewById(2131363595);
      this.mRadarView.refreshText();
      if (FutureUtil.instance.isShowBackCameraTips()) {
         this.mCameraTisTv.setVisibility(0);
      }

      SystemProperties.set("BackCameraUI", "true");
      if (UiMgrFactory.getCanUiMgr(this.mService) != null) {
         this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(this.mService).getParkPageUiSet(this.mService);
      }

      ParkPageUiSet var2 = this.mParkPageUiSet;
      if (var2 != null) {
         OnBackCameraStatusListener var3 = var2.getOnBackCameraStatusListener();
         if (var3 != null) {
            var3.addViewToWindows();
         }

         var1 = ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getRadarDispCheck();
         LogUtil.showLog("fang", "IsShowRadar:" + this.mParkPageUiSet.isIsShowRadar() + ", showRadarFromSetting:" + var1);
         if ((!var1 || !this.mParkPageUiSet.isIsShowRadar()) && this.mService.getResources().getConfiguration().orientation != 1) {
            this.mRadarView.setVisibility(8);
         } else {
            this.mRadarView.setVisibility(0);
            if (!SharePreUtil.getBoolValue(this.mService, "is_show_radar", true) && this.mService.getResources().getConfiguration().orientation != 1) {
               this.mRadarView.hideRadarView();
            } else {
               this.mRadarView.showRadarView();
            }

            if (this.mParkPageUiSet.isHaveLeftRightRadar()) {
               this.mRadarView.setSmallRadarViewWidth();
            }
         }

         LogUtil.showLog("fang", "IsShowPanoramic:" + this.mParkPageUiSet.isShowPanoramic());
         if (this.mParkPageUiSet.isShowPanoramic()) {
            this.mPanoramicView.setBtnList(this.mParkPageUiSet.getPanoramicBtnList(), this.mParkPageUiSet.getOnPanoramicItemClickListener());
            this.mPanoramicView.setVisibility(0);
         } else {
            this.mPanoramicView.setVisibility(8);
         }

         LogUtil.showLog("fang", "isShowCusPanoramic:" + this.mParkPageUiSet.isShowCusPanoramicView());
         if (this.mParkPageUiSet.isShowCusPanoramicView()) {
            this.mCusPanoramicContainerView.setVisibility(0);
            View var4 = UiMgrFactory.getCanUiMgr(this.mService).getCusPanoramicView(this.mService);
            if (var4 != null && this.mCusPanoramicContainerView.getChildCount() == 0) {
               if (var4.getParent() != null) {
                  ((ViewGroup)var4.getParent()).removeAllViews();
               }

               this.mCusPanoramicContainerView.addView(var4);
            }
         } else {
            this.mCusPanoramicContainerView.setVisibility(8);
         }
      } else {
         LogUtil.showLog("fang", "mParkPageUiSet==null");
         if (this.mService.getResources().getConfiguration().orientation == 2) {
            this.mRadarView.setVisibility(8);
         }
      }

      if (ParkPanoramic.isEnableParkPanoramic()) {
         LogUtil.showLog("fang", "isEnableParkPanoramic");
         if (this.mParkPanoramic == null) {
            this.mParkPanoramic = new ParkPanoramic();
         }

         this.mParkPanoramic.constructParkPanoramic();
      }

      if (this.mExternal360CamType == 2) {
         this.mIrCam360MaylasiaView.setVisibility(0);
      }

      this.intIrCam360VZView();
      this.mTouchListenerView.setOnTouchListener(new View.OnTouchListener(this) {
         private float downX;
         private float downY;
         private final Runnable runnable;
         final BackCameraUiServiceOld this$0;

         {
            this.this$0 = var1;
            this.downX = 0.0F;
            this.downY = 0.0F;
            this.runnable = new BackCameraUiServiceOld$3$$ExternalSyntheticLambda0();
         }

         // $FF: synthetic method
         static void lambda$$0() {
            if (CanBusDefault.INSTANCE.getLongClick()) {
               LogUtil.showLog("fang", "onLongClick");
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
               LogUtil.showLog("fang", var5.toString());
            }

            return true;
         }
      });
      this.radar_all_page = (RelativeLayout)this.mView.findViewById(2131363008);
      if (CanIdSpecialConfig.hideRadarLayoutCanID(CanbusConfig.INSTANCE.getCanType())) {
         this.radar_all_page.setVisibility(8);
      }

      this.mWindowManager.addView(this.mView, this.mViewParams);
   }

   private void initWindowParam() {
      LogUtil.showLog("fang", "initWindowParam");
      this.mWindowManager = (WindowManager)this.mService.getSystemService("window");
      WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
      this.mViewParams = var1;
      var1.type = 2024;
      this.mViewParams.format = 1;
      this.mViewParams.flags = 16777512;
      this.mViewParams.x = 0;
      this.mViewParams.y = 0;
      this.mViewParams.width = -1;
      this.mViewParams.height = -1;
      this.mViewParams.format = 1;
      this.mViewParams.setTitle("ReverseWindow");
   }

   private void intIrCam360VZView() {
      if (VZ360Constance.isVZ360Camera()) {
         IrCam360VZLayout.addVIew(this.mService.getBaseContext(), this.mView);
      }

   }

   private void refreshUi(Bundle var1) {
      LogUtil.showLog("fang", "BackCameraUiService refreshUi");
      if (this.mParkPageUiSet == null) {
         LogUtil.showLog("fang", "mParkPageUiSet==null");
      } else {
         LogUtil.showLog("fang", "BackCameraUiService mParkPageUiSet.isIsShowRadar():" + this.mParkPageUiSet.isIsShowRadar());
         if (this.mIsShowRadarLayout != this.mParkPageUiSet.isIsShowRadar()) {
            this.mIsShowRadarLayout = this.mParkPageUiSet.isIsShowRadar();
            if (this.mParkPageUiSet.isIsShowRadar()) {
               this.mRadarView.setVisibility(0);
               if (SharePreUtil.getBoolValue(this.mService, "is_show_radar", true)) {
                  this.mRadarView.showRadarView();
               } else {
                  this.mRadarView.hideRadarView();
               }
            } else {
               this.mRadarView.setVisibility(8);
            }
         }

         if (this.mParkPageUiSet.isIsShowRadar()) {
            if (GeneralParkData.isShowDistanceNotShowLocationUi) {
               this.mRadarView.refreshDistance(GeneralParkData.radar_distance_data);
            } else {
               this.mRadarView.refreshLocation(GeneralParkData.radar_location_data);
            }

            if (GeneralParkData.isShowLeftTopOneDistanceUi) {
               this.mRadarView.setOneRadarDitance(GeneralParkData.strOnlyOneDistance);
            } else {
               this.mRadarView.hideOneRadarDistance();
            }
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

            this.mPanoramicView.getAdapter().notifyDataSetChanged();
         }

         if (this.mParkPageUiSet.isShowCusPanoramicView()) {
            this.mCusPanoramicContainerView.setVisibility(0);
            View var5 = UiMgrFactory.getCanUiMgr(this.mService).getCusPanoramicView(this.mService);
            if (var5 != null && this.mCusPanoramicContainerView.getChildCount() == 0) {
               this.mCusPanoramicContainerView.addView(var5);
            }
         } else {
            this.mCusPanoramicContainerView.setVisibility(8);
         }

      }
   }

   private void removeViewFromWindow() {
      HzbhdLog.d("fang", "removeViewFromWindow");

      try {
         this.mWindowManager.removeView(this.mView);
      } catch (Exception var2) {
         LogUtil.showLog("fang", "e:" + var2.toString());
      }

   }

   private void setReverseState(int var1) {
      Message var2 = new Message();
      var2.what = 0;
      var2.arg1 = var1;
      this.mHandler.sendMessage(var2);
   }

   public void changeVideoType() {
   }

   public Handler getmHandler() {
      return this.mHandler;
   }

   // $FF: synthetic method
   void lambda$onCreate$0$com_hzbhd_canbus_park_parkui_BackCameraUiServiceOld(int var1) {
      Log.d("fang", "REVERSE <onChanged> " + var1);
      this.setReverseState(var1);
   }

   public void onConfigurationChanged(Configuration var1) {
      LogUtil.showLog("fang", "onConfigurationChanged");

      try {
         this.mWindowManager.removeView(this.mView);
      } catch (Exception var3) {
      }

      this.mView = LayoutInflater.from(this.mService).inflate(2131558809, (ViewGroup)null);
      if (ScreenLogic.isScreenOreitaionChanged(var1) && this.mHasAddViewToWindow) {
         LogUtil.showLog("fang", "onConfigurationChanged 22");
         this.addViewToWindow(false);
      }

   }

   public void onCreate() {
      ScreenLogic.setOrientation(this.mService.getApplicationContext().getResources().getConfiguration().orientation);
      this.mService.setTheme(16974064);
      this.mView = LayoutInflater.from(this.mService).inflate(2131558809, (ViewGroup)null);
      this.mExternal360CamType = FutureUtil.instance.is360External();
      HzbhdLog.d("fang", "BackCameraUiService onCreate");
      int var1 = ShareDataManager.getInstance().registerShareIntListener("misc.Reverse", new BackCameraUiServiceOld$$ExternalSyntheticLambda0(this));

      try {
         this.setReverseState(var1);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public void onDestroy() {
   }

   public void setAnalogColorUiChange(int var1, int var2, int var3) {
      Log.d("fang", "setAnalogColorUiChange ");
   }
}
