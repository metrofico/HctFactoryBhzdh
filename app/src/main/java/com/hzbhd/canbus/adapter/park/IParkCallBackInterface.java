package com.hzbhd.canbus.adapter.park;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IParkCallBackInterface extends IInterface {
   void onParkOrbitAngleChange(int var1) throws RemoteException;

   public static class Default implements IParkCallBackInterface {
      public IBinder asBinder() {
         return null;
      }

      public void onParkOrbitAngleChange(int var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IParkCallBackInterface {
      private static final String DESCRIPTOR = "com.hzbhd.canbus.adapter.park.IParkCallBackInterface";
      static final int TRANSACTION_onParkOrbitAngleChange = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.canbus.adapter.park.IParkCallBackInterface");
      }

      public static IParkCallBackInterface asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.canbus.adapter.park.IParkCallBackInterface");
            return (IParkCallBackInterface)(var1 != null && var1 instanceof IParkCallBackInterface ? (IParkCallBackInterface)var1 : new Proxy(var0));
         }
      }

      public static IParkCallBackInterface getDefaultImpl() {
         return IParkCallBackInterface.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IParkCallBackInterface var0) {
         if (IParkCallBackInterface.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               IParkCallBackInterface.Stub.Proxy.sDefaultImpl = var0;
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
         if (var1 != 1) {
            if (var1 != 1598968902) {
               return super.onTransact(var1, var2, var3, var4);
            } else {
               var3.writeString("com.hzbhd.canbus.adapter.park.IParkCallBackInterface");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.canbus.adapter.park.IParkCallBackInterface");
            this.onParkOrbitAngleChange(var2.readInt());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements IParkCallBackInterface {
         public static IParkCallBackInterface sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.canbus.adapter.park.IParkCallBackInterface";
         }

         public void onParkOrbitAngleChange(int var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.canbus.adapter.park.IParkCallBackInterface");
               var2.writeInt(var1);
               if (!this.mRemote.transact(1, var2, var3, 0) && IParkCallBackInterface.Stub.getDefaultImpl() != null) {
                  IParkCallBackInterface.Stub.getDefaultImpl().onParkOrbitAngleChange(var1);
                  return;
               }

               var3.readException();
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }
      }
   }
}
