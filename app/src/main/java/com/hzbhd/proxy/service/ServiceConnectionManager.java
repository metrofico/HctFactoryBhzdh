package com.hzbhd.proxy.service;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceConnectionCanbusMsgServiceManager {
   private static final String TAG = "ServiceConnectionManager";
   static ServiceConnectionManager mServiceManager;
   private ReentrantLock mLock = new ReentrantLock();
   private Vector mServiceNeedConnected = new Vector();
   private HashMap mServices = new HashMap();
   private Thread mWorkThread = new Thread((ThreadGroup)null, new MonitorRunnable(this), "ServiceConnectionManager thread");

   private ServiceConnectionManager() {
      this.mWorkThread.start();
   }

   public static ServiceConnectionManager getServiceConnectionManager() {
      if (mServiceManager == null) {
         mServiceManager = new ServiceConnectionManager();
      }

      return mServiceManager;
   }

   public void checkService() {
      this.mLock.lock();
      Vector var1 = new Vector();
      Iterator var2 = this.mServiceNeedConnected.iterator();

      while(var2.hasNext()) {
         ServiceConnection var3 = (ServiceConnection)var2.next();
         if (!var3.isServiceConnected()) {
            var1.add(var3);
            Log.i(TAG, "checkService in back thread add: " + var3.getServiceName());
         } else {
            Log.i(TAG, "checkService in back thread del: " + var3.getServiceName());
         }
      }

      this.mServiceNeedConnected = var1;
      this.mLock.unlock();
   }

   public boolean connectService() {
      this.mLock.lock();
      Iterator var1 = this.mServiceNeedConnected.iterator();

      while(var1.hasNext()) {
         ServiceConnection var2 = (ServiceConnection)var1.next();
         if (!var2.getServiceConnection()) {
            Log.w(TAG, "doBindService in back thread fail: " + var2.getServiceName());
         } else {
            Log.i(TAG, "doBindService in back thread success: " + var2.getServiceName());
            var2.serviceReConnected();
         }
      }

      this.mLock.unlock();
      return false;
   }

   public void onServiceDisconnected(String var1) {
      this.mLock.lock();
      ServiceConnection var2 = (ServiceConnection)this.mServices.get(var1);
      if (var2 != null && !this.mServiceNeedConnected.contains(var2)) {
         this.mServiceNeedConnected.add(var2);
      }

      this.mLock.unlock();
   }

   public void registerConnectionListener(String var1, ServiceConnection var2) {
      this.mLock.lock();
      if (!this.mServices.containsKey(var1)) {
         this.mServices.put(var1, var2);
      }

      this.mLock.unlock();
   }

   private class MonitorRunnable implements Runnable {
      final ServiceConnectionManager this$0;

      private MonitorRunnable(ServiceConnectionManager var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      MonitorRunnable(ServiceConnectionManager var1, Object var2) {
         this(var1);
      }

      public void run() {
         while(true) {
            while(true) {
               try {
                  if (this.this$0.mServiceNeedConnected.isEmpty()) {
                     Thread.sleep(2000L);
                  } else {
                     this.this$0.connectService();
                     Thread.sleep(1000L);
                     this.this$0.checkService();
                  }
               } catch (Exception var2) {
                  var2.printStackTrace();
               }
            }
         }
      }
   }
}
