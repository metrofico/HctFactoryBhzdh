package com.hzbhd.canbus.adapter.park;

import android.os.IBinder;
import android.os.RemoteException;

public class ParkManager {
   public static final String TAG = "ParkManager";
   private static ParkClient mParkClient;
   private static ParkManager mParkManager;
   private static IParkInterface mService;
   private IBinder getServiceObj = null;
   private IParkCallBackInterface mIParkCallBackInterface = new IParkCallBackInterface(this) {
      final ParkManager this$0;

      {
         this.this$0 = var1;
      }

      public IBinder asBinder() {
         return null;
      }

      public void onParkOrbitAngleChange(int var1) throws RemoteException {
         ParkManager.mParkClient.onParkOrbitAngleChange(var1);
      }
   };

   private ParkManager() {
      if (mService == null) {
         mService = new IParkInterface(this) {
            final ParkManager this$0;

            {
               this.this$0 = var1;
            }

            public IBinder asBinder() {
               return null;
            }

            public void registerCallBack(IParkCallBackInterface var1) throws RemoteException {
            }

            public void sendPanoramicParkKey(int var1) throws RemoteException {
            }

            public void sendPanoramicParkOn(boolean var1) throws RemoteException {
            }

            public void sendPanoramicParkPos(int var1, int var2, int var3) throws RemoteException {
            }

            public void sendPanoramicParkWH(int var1, int var2) throws RemoteException {
            }

            public void unRegisterCallBack() throws RemoteException {
            }
         };
      }

   }

   public static ParkManager getAtvManager() {
      synchronized(ParkManager.class){}

      ParkManager var0;
      try {
         if (mParkManager == null) {
            var0 = new ParkManager();
            mParkManager = var0;
         }

         var0 = mParkManager;
      } finally {
         ;
      }

      return var0;
   }

   public void registerCallBack(ParkClient param1) {
      // $FF: Couldn't be decompiled
   }

   public void sendPanoramicParkKey(int param1) {
      // $FF: Couldn't be decompiled
   }

   public void sendPanoramicParkOn(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void sendPanoramicParkPos(int param1, int param2, int param3) {
      // $FF: Couldn't be decompiled
   }

   public void sendPanoramicParkWH(int param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public void unregisterCalllBack() {
      // $FF: Couldn't be decompiled
   }
}
