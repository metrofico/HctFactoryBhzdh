package com.hzbhd.proxy.service;

import android.content.Context;
import android.os.IBinder;

public interface IbhdServiceConnection {
   void bindService();

   void connectServiceUseServiceManager();

   IBinder getBinder();

   String getServiceAction();

   String getServiceName();

   String getServicePkgName();

   boolean isAlive();

   boolean isConnected();

   boolean registerServiceConnectionListener(IbhdServiceConnectionListener var1);

   void unbindService();

   void unbindService(Context var1);

   boolean unregisterServiceConnectionListener(IbhdServiceConnectionListener var1);
}
