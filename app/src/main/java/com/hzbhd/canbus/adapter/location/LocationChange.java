package com.hzbhd.canbus.adapter.location;

import android.os.IBinder;
import android.os.RemoteException;

public class LocationChange extends LocationCallback.Stub {
   public IBinder asBinder() {
      return this;
   }

   public void getAltitude(double var1) throws RemoteException {
   }

   public void getBear(float var1) throws RemoteException {
   }

   public void getBearAndAltitude(float var1, double var2) throws RemoteException {
   }

   public void getLoc(double var1, double var3) throws RemoteException {
   }

   public void getMaxSatelliteCount(int var1) throws RemoteException {
   }

   public void getSpeed(int var1) throws RemoteException {
   }
}
