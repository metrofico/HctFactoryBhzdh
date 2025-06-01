package com.hzbhd.canbus.adapter.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface LocationCallback extends IInterface {
   void getAltitude(double var1) throws RemoteException;

   void getBear(float var1) throws RemoteException;

   void getBearAndAltitude(float var1, double var2) throws RemoteException;

   void getLoc(double var1, double var3) throws RemoteException;

   void getMaxSatelliteCount(int var1) throws RemoteException;

   void getSpeed(int var1) throws RemoteException;

   public static class Default implements LocationCallback {
      public IBinder asBinder() {
         return null;
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

   public abstract static class Stub extends Binder implements LocationCallback {
      private static final String DESCRIPTOR = "com.hzbhd.canbus.adapter.location.LocationCallback";
      static final int TRANSACTION_getAltitude = 6;
      static final int TRANSACTION_getBear = 4;
      static final int TRANSACTION_getBearAndAltitude = 5;
      static final int TRANSACTION_getLoc = 3;
      static final int TRANSACTION_getMaxSatelliteCount = 2;
      static final int TRANSACTION_getSpeed = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.canbus.adapter.location.LocationCallback");
      }

      public static LocationCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.canbus.adapter.location.LocationCallback");
            return (LocationCallback)(var1 != null && var1 instanceof LocationCallback ? (LocationCallback)var1 : new Proxy(var0));
         }
      }

      public static LocationCallback getDefaultImpl() {
         return LocationCallback.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(LocationCallback var0) {
         if (LocationCallback.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               LocationCallback.Stub.Proxy.sDefaultImpl = var0;
               return true;
            } else {
               return false;
            }
         } else {
            throw new IllegalStateException("setDefaultImpl() called twice");
         }
      }

      public IBinder asBinder() {
         return this;
      }

      public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
         if (var1 != 1598968902) {
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.location.LocationCallback");
                  this.getSpeed(var2.readInt());
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.location.LocationCallback");
                  this.getMaxSatelliteCount(var2.readInt());
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.location.LocationCallback");
                  this.getLoc(var2.readDouble(), var2.readDouble());
                  var3.writeNoException();
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.location.LocationCallback");
                  this.getBear(var2.readFloat());
                  var3.writeNoException();
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.location.LocationCallback");
                  this.getBearAndAltitude(var2.readFloat(), var2.readDouble());
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.location.LocationCallback");
                  this.getAltitude(var2.readDouble());
                  var3.writeNoException();
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.canbus.adapter.location.LocationCallback");
            return true;
         }
      }

      private static class Proxy implements LocationCallback {
         public static LocationCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public void getAltitude(double var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.canbus.adapter.location.LocationCallback");
               var3.writeDouble(var1);
               if (!this.mRemote.transact(6, var3, var4, 0) && LocationCallback.Stub.getDefaultImpl() != null) {
                  LocationCallback.Stub.getDefaultImpl().getAltitude(var1);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void getBear(float var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.canbus.adapter.location.LocationCallback");
               var4.writeFloat(var1);
               if (!this.mRemote.transact(4, var4, var2, 0) && LocationCallback.Stub.getDefaultImpl() != null) {
                  LocationCallback.Stub.getDefaultImpl().getBear(var1);
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var4.recycle();
            }

         }

         public void getBearAndAltitude(float var1, double var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            try {
               var5.writeInterfaceToken("com.hzbhd.canbus.adapter.location.LocationCallback");
               var5.writeFloat(var1);
               var5.writeDouble(var2);
               if (!this.mRemote.transact(5, var5, var6, 0) && LocationCallback.Stub.getDefaultImpl() != null) {
                  LocationCallback.Stub.getDefaultImpl().getBearAndAltitude(var1, var2);
                  return;
               }

               var6.readException();
            } finally {
               var6.recycle();
               var5.recycle();
            }

         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.canbus.adapter.location.LocationCallback";
         }

         public void getLoc(double var1, double var3) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var7 = Parcel.obtain();

            try {
               var6.writeInterfaceToken("com.hzbhd.canbus.adapter.location.LocationCallback");
               var6.writeDouble(var1);
               var6.writeDouble(var3);
               if (this.mRemote.transact(3, var6, var7, 0) || LocationCallback.Stub.getDefaultImpl() == null) {
                  var7.readException();
                  return;
               }

               LocationCallback.Stub.getDefaultImpl().getLoc(var1, var3);
            } finally {
               var7.recycle();
               var6.recycle();
            }

         }

         public void getMaxSatelliteCount(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.canbus.adapter.location.LocationCallback");
               var3.writeInt(var1);
               if (!this.mRemote.transact(2, var3, var4, 0) && LocationCallback.Stub.getDefaultImpl() != null) {
                  LocationCallback.Stub.getDefaultImpl().getMaxSatelliteCount(var1);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void getSpeed(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.canbus.adapter.location.LocationCallback");
               var3.writeInt(var1);
               if (!this.mRemote.transact(1, var3, var2, 0) && LocationCallback.Stub.getDefaultImpl() != null) {
                  LocationCallback.Stub.getDefaultImpl().getSpeed(var1);
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }
      }
   }
}
