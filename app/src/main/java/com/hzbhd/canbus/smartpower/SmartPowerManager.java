package com.hzbhd.canbus.smartpower;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import java.util.HashMap;

public class SmartPowerManager {
   private static final int NOTIFYSWCSETTINGS = 0;
   private static final String TAG = "SmartPowerManager";
   private static SmartPowerManager mSmartPowerManager;
   private static ISurgingPwerInterface mSmartPowerService;
   private static HashMap mSwcListenerMap = new HashMap();

   private SmartPowerManager() {
      HzbhdLog.e(TAG, "SmartPowerService SmartPowerManager");
      if (mSmartPowerService == null) {
         mSmartPowerService = new ISurgingPwerInterface(this) {
            final SmartPowerManager this$0;

            {
               this.this$0 = var1;
            }

            public IBinder asBinder() {
               return null;
            }

            public String registerCallBack(ISurgingPowerCallback var1) throws RemoteException {
               return null;
            }

            public void sendMsg(byte[] var1) throws RemoteException {
            }

            public void unRegisterCallBack(String var1) throws RemoteException {
            }
         };
      }

   }

   public static SmartPowerManager getInstance() {
      synchronized(SmartPowerManager.class){}

      SmartPowerManager var0;
      try {
         String var1 = TAG;
         HzbhdLog.e(var1, "SmartPowerService SmartPowerManager getInstance");
         if (mSmartPowerManager == null) {
            var0 = new SmartPowerManager();
            mSmartPowerManager = var0;
            HzbhdLog.e(var1, "SmartPowerService SmartPowerManager getInstance 22");
         }

         var0 = mSmartPowerManager;
      } finally {
         ;
      }

      return var0;
   }

   public void addOnSmartPowerChangeListener(OnSmartPowerChangeListener var1) {
      synchronized(this){}
      if (var1 != null) {
         try {
            if (!mSwcListenerMap.containsKey(var1)) {
               HashMap var2 = mSwcListenerMap;
               SwcCallback var3 = new SwcCallback(var1);
               var2.put(var1, var3);
            }
         } finally {
            ;
         }
      }

   }

   public void sendMsg(byte[] var1) throws RemoteException {
      ISurgingPwerInterface var2 = mSmartPowerService;
      if (var2 != null) {
         var2.sendMsg(var1);
      }

   }

   public interface OnSmartPowerChangeListener {
      void onDataChange(byte[] var1);
   }

   private static final class SwcCallback extends ISurgingPowerCallback.Stub {
      private Handler mSwcHandler;
      private String mToken;

      SwcCallback(OnSmartPowerChangeListener var1) {
         this.mSwcHandler = new PowerChangeHandler(var1);

         try {
            if (SmartPowerManager.mSmartPowerService != null) {
               this.mToken = SmartPowerManager.mSmartPowerService.registerCallBack(this);
            }
         } catch (RemoteException var2) {
            var2.printStackTrace();
         }

      }

      public void notifySurgingPower(byte[] var1) throws RemoteException {
         this.mSwcHandler.obtainMessage(0, var1).sendToTarget();
      }

      private static final class PowerChangeHandler extends Handler {
         private OnSmartPowerChangeListener mListener;

         PowerChangeHandler(OnSmartPowerChangeListener var1) {
            this.mListener = var1;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 0) {
               HzbhdLog.i("SettingManager", "handleMessage-->NOTIFYSWCSETTINGS");
               byte[] var2 = (byte[])var1.obj;
               this.mListener.onDataChange(var2);
            }

         }
      }
   }
}
