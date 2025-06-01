package com.hzbhd.proxy.service;

import android.os.IBinder;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServiceConnection {
   private static final String TAG = "ServiceOperation";
   private ServiceConnectionManager mConnectionManager = null;
   protected Boolean mIsConnected = true;
   private List mServiceConnectListeners = new ArrayList();
   protected ServiceConnection mServiceConnection = null;

   public ServiceConnection() {
      ServiceConnectionManager var1 = ServiceConnectionManager.getServiceConnectionManager();
      this.mConnectionManager = var1;
      var1.registerConnectionListener(this.getServiceName(), this);
   }

   public IBinder connectService() {
      Object var1 = new Object();

      IBinder var6;
      label36: {
         Exception var2;
         label40: {
            try {
               var6 = (IBinder)Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(var1, this.getServiceName());
            } catch (Exception var5) {
               var2 = var5;
               var6 = null;
               break label40;
            }

            Exception var10000;
            boolean var10001;
            if (var6 != null) {
               try {
                  this.mIsConnected = true;
                  BinderDeathRecipient var7 = new BinderDeathRecipient(this);
                  var6.linkToDeath(var7, 0);
                  this.serviceConnected(true);
                  break label36;
               } catch (Exception var3) {
                  var10000 = var3;
                  var10001 = false;
               }
            } else {
               try {
                  this.mIsConnected = false;
                  this.mConnectionManager.onServiceDisconnected(this.getServiceName());
                  break label36;
               } catch (Exception var4) {
                  var10000 = var4;
                  var10001 = false;
               }
            }

            var2 = var10000;
         }

         var2.printStackTrace();
      }

      if (var6 != null) {
         this.mIsConnected = true;
      } else {
         this.mIsConnected = false;
      }

      return var6;
   }

   public boolean getServiceConnection() {
      return false;
   }

   public String getServiceName() {
      return null;
   }

   public boolean isServiceConnected() {
      return this.mIsConnected;
   }

   public void registerServiceConnectListener(ServiceConnectListener var1) {
      List var2 = this.mServiceConnectListeners;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label122: {
         try {
            if (!this.mServiceConnectListeners.contains(var1)) {
               this.mServiceConnectListeners.add(var1);
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label122;
         }

         label119:
         try {
            return;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label119;
         }
      }

      while(true) {
         Throwable var15 = var10000;

         try {
            throw var15;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public void serviceConnected(boolean var1) {
      Log.d("ServiceOperation", "serviceConnected,flag=" + var1);
      if (this.mServiceConnectListeners != null) {
         Log.d("ServiceOperation", "serviceReConnected,mServiceConnectListeners size=" + this.mServiceConnectListeners.size());
         Iterator var2 = this.mServiceConnectListeners.iterator();

         while(var2.hasNext()) {
            ((ServiceConnectListener)var2.next()).onServiceConnectStateChanged(var1);
         }
      }

   }

   public void serviceDied() {
      try {
         Log.d("ServiceOperation", "serviceDied");
         this.serviceConnected(false);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void serviceReConnected() {
      Log.d("ServiceOperation", "serviceReConnected");
      this.serviceConnected(true);
   }

   public void unRegisterServiceConnectListener(ServiceConnectListener var1) {
      List var2 = this.mServiceConnectListeners;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label122: {
         try {
            if (this.mServiceConnectListeners.contains(var1)) {
               this.mServiceConnectListeners.remove(var1);
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label122;
         }

         label119:
         try {
            return;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label119;
         }
      }

      while(true) {
         Throwable var15 = var10000;

         try {
            throw var15;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public class BinderDeathRecipient implements IBinder.DeathRecipient {
      final ServiceConnection this$0;

      public BinderDeathRecipient(ServiceConnection var1) {
         this.this$0 = var1;
      }

      public void binderDied() {
         Log.e("ServiceOperation", "ServiceConnection BinderDeathRecipient: binderDied");
         this.this$0.mIsConnected = false;
         this.this$0.serviceDied();
         this.this$0.mConnectionManager.onServiceDisconnected(this.this$0.getServiceName());
      }
   }
}
