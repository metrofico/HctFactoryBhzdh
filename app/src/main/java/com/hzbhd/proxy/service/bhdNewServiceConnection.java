package com.hzbhd.proxy.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public abstract class bhdNewServiceConnection implements IbhdServiceConnection, android.content.ServiceConnection {
   private static final String TAG = "bhdNewServiceConnection";
   private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient(this) {
      final bhdNewServiceConnection this$0;

      {
         this.this$0 = var1;
      }

      public void binderDied() {
         Log.i(bhdNewServiceConnection.TAG, "bhdNewServiceConnection,DeathRecipient,binderDied");
         this.this$0.isConnected = false;
         bhdNewServiceConnection var1 = this.this$0;
         var1.onServiceConnectionChanged(var1.mBinder, false);
         this.this$0.asyncNotifyToListenerServiceConnectionChanged(false);
      }
   };
   private boolean isConnected = false;
   private String mAction;
   private IBinder mBinder;
   private Context mContext;
   private IbhdServiceConnectionListener mListener;
   private String mPackageName;
   private String mServiceName;

   public bhdNewServiceConnection() {
      this.mServiceName = this.getServiceName();
      this.mAction = this.getServiceAction();
      this.mPackageName = this.getServicePkgName();
   }

   public bhdNewServiceConnection(Context var1) {
      this.mContext = var1;
      this.mServiceName = this.getServiceName();
      this.mAction = this.getServiceAction();
      this.mPackageName = this.getServicePkgName();
   }

   private void asyncNotifyToListenerServiceConnectionChanged(boolean var1) {
      if (this.mListener != null) {
         Log.d(TAG, "asyncNotifyToListenerServiceConnectionChanged,state=" + var1);
         (new Thread(new Runnable(this, var1) {
            final bhdNewServiceConnection this$0;
            final boolean val$state;

            {
               this.this$0 = var1;
               this.val$state = var2;
            }

            public void run() {
               Log.d(bhdNewServiceConnection.TAG, "asyncNotifyToListenerServiceConnectionChanged,thread run state=" + this.val$state);
               Log.d(bhdNewServiceConnection.TAG, "run: asyncNotifyToListenerServiceConnectionChanged mIbhdServiceConnectionListener " + this.this$0.mListener);
               this.this$0.mListener.onServiceConnectionChanged(this.val$state);
            }
         })).start();
      }

   }

   public void bindService() {
      this.bindService(this.mContext);
   }

   public void bindService(Context var1) {
      Log.i(TAG, "bindService");
      this.bindService(var1, new Intent());
   }

   public void bindService(Context var1, Intent var2) {
      String var4 = TAG;
      Log.i(var4, "bindService with intent");
      if (var1 == null) {
         Log.e(var4, "bindService with intent,mContext is null!");
      }

      if (!this.isAlive()) {
         Log.i(var4, "bindService with intent,!isAlive");
         Intent var3 = var2;
         if (var2 == null) {
            var3 = new Intent();
         }

         Log.i(var4, "bindService,mAction=" + this.mAction + " mPackageName=" + this.mPackageName);
         var3.setAction(this.mAction);
         var3.setPackage(this.mPackageName);
         var1.bindService(var3, this, 1);
      } else {
         Log.i(var4, "bindService with intent,isAlive");
      }

   }

   public void bindService(Context var1, String var2) {
      Log.i(TAG, "bindService,serviceName=" + var2);
      Intent var3 = new Intent();
      var3.putExtra("SERVICE_NAME", var2);
      this.bindService(var1, var3);
   }

   public void bindService(String var1) {
      this.bindService(this.mContext, var1);
   }

   public void connectServiceUseServiceManager() {
      Object var1 = new Object();
      if (this.mServiceName != null) {
         Exception var10000;
         label29: {
            boolean var10001;
            IBinder var2;
            try {
               var2 = (IBinder)Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(var1, this.getServiceName());
               Log.i(TAG, "connectServiceUseServiceManager");
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label29;
            }

            ComponentName var6;
            if (var2 != null) {
               try {
                  var6 = new ComponentName(this.getServicePkgName(), this.getServiceAction());
                  this.onServiceConnected(var6, var2);
                  return;
               } catch (Exception var3) {
                  var10000 = var3;
                  var10001 = false;
               }
            } else {
               try {
                  var6 = new ComponentName(this.getServicePkgName(), this.getServiceAction());
                  this.onServiceDisconnected(var6);
                  return;
               } catch (Exception var4) {
                  var10000 = var4;
                  var10001 = false;
               }
            }
         }

         Exception var7 = var10000;
         var7.printStackTrace();
      } else {
         String var8 = TAG;
         Log.e(var8, "connectServiceUseServiceManager,mServiceName is " + this.mServiceName + ",please override method getServiceName() first!");
         Log.e(var8, "connectServiceUseServiceManager,getServiceName()=" + this.getServiceName() + " getServicePkgName=" + this.getServicePkgName() + " getServiceAction=" + this.getServiceAction());
      }

   }

   public IBinder getBinder() {
      return this.mBinder;
   }

   public String getServiceName() {
      return null;
   }

   public boolean isAlive() {
      Log.i(TAG, "isAlive: isConnected=" + this.isConnected + ",mBinder=" + this.mBinder);
      if (this.isConnected) {
         IBinder var1 = this.mBinder;
         if (var1 != null && var1.isBinderAlive()) {
            return true;
         }
      }

      return false;
   }

   public boolean isConnected() {
      return this.isConnected;
   }

   public void onServiceConnected(ComponentName var1, IBinder var2) {
      String var3 = TAG;
      Log.i(var3, "onServiceConnected,ComponentName=" + var1 + " service=" + var2);
      this.isConnected = true;
      this.mBinder = var2;
      if (var2 == null) {
         Log.e(var3, "onServiceConnected, mBinder is null,Please invoke connectServiceUseServiceManager to connect Service!");
      }

      this.onServiceConnectionChanged(this.mBinder, true);
      this.asyncNotifyToListenerServiceConnectionChanged(true);
      if (var2 != null) {
         try {
            var2.linkToDeath(this.deathRecipient, 0);
         } catch (RemoteException var4) {
            var4.printStackTrace();
            Log.e(TAG, "onServiceConnected: ", var4);
         }
      } else {
         Log.i(var3, "onServiceConnected: service is null!");
      }

   }

   public abstract void onServiceConnectionChanged(IBinder var1, boolean var2);

   public void onServiceDisconnected(ComponentName var1) {
      Log.i(TAG, "onServiceDisconnected,name=" + var1);
      this.isConnected = false;
      this.mBinder = null;
      this.onServiceConnectionChanged((IBinder)null, false);
      this.asyncNotifyToListenerServiceConnectionChanged(false);
   }

   public boolean registerServiceConnectionListener(IbhdServiceConnectionListener var1) {
      this.mListener = var1;
      return true;
   }

   public void unbindService() {
      this.unbindService(this.mContext);
   }

   public void unbindService(Context var1) {
      Log.d(TAG, "unbindService,isConnected=" + this.isConnected + " mPackageName=" + this.mPackageName + " mAction=" + this.mAction + " mServiceName=" + this.mServiceName);
      if (this.isConnected) {
         var1.unbindService(this);
         this.isConnected = false;
         this.mBinder = null;
      }

   }

   public boolean unregisterServiceConnectionListener(IbhdServiceConnectionListener var1) {
      if (this.mListener == var1) {
         this.mListener = null;
         return true;
      } else {
         return false;
      }
   }
}
