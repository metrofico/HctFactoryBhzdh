package com.hzbhd.canbus.park.parkui;

import android.content.res.Configuration;
import android.os.Handler;

public interface BackCameraUiServiceBase {
   void changeVideoType();

   Handler getmHandler();

   void onConfigurationChanged(Configuration var1);

   void onCreate();

   void onDestroy();

   void setAnalogColorUiChange(int var1, int var2, int var3);
}
