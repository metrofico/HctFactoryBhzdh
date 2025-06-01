package com.hzbhd.canbus.park;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import com.hzbhd.canbus.interfaces.AnalogColorSettingInterface;
import com.hzbhd.canbus.interfaces.VideoTypeUiChangeInterface;
import com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase;
import com.hzbhd.canbus.park.parkui.BackCameraUiServiceVm;

public class BackCameraUiService extends Service implements AnalogColorSettingInterface, VideoTypeUiChangeInterface {
   public static final int MSG_CHECK_BACKCAMERA_STATE = 0;
   public static final int MSG_REFRESH_UI = 1;
   public static final String SHARE_IS_SHOW_RADAR = "is_show_radar";
   private BackCameraUiServiceBase backCameraUiServiceBase;

   private BackCameraUiServiceBase getBackCameraUiServiceBase() {
      if (this.backCameraUiServiceBase == null) {
         this.backCameraUiServiceBase = new BackCameraUiServiceVm(this);
      }

      return this.backCameraUiServiceBase;
   }

   public void changeVideoType() {
      this.getBackCameraUiServiceBase().changeVideoType();
   }

   public Handler getHandler() {
      return this.getBackCameraUiServiceBase().getmHandler();
   }

   public IBinder onBind(Intent var1) {
      return new MyBinder(this);
   }

   public void onConfigurationChanged(Configuration var1) {
      this.getBackCameraUiServiceBase().onConfigurationChanged(var1);
   }

   public void onCreate() {
      super.onCreate();
      this.getBackCameraUiServiceBase().onCreate();
   }

   public void onDestroy() {
      super.onDestroy();
      this.getBackCameraUiServiceBase().onDestroy();
   }

   public void setAnalogColorUiChange(int var1, int var2, int var3) {
      this.getBackCameraUiServiceBase().setAnalogColorUiChange(var1, var2, var3);
   }

   public class MyBinder extends Binder {
      final BackCameraUiService this$0;

      public MyBinder(BackCameraUiService var1) {
         this.this$0 = var1;
      }

      public BackCameraUiService getService() {
         return this.this$0;
      }
   }
}
