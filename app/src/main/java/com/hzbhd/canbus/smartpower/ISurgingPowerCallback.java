package com.hzbhd.canbus.smartpower;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISurgingPowerCallback extends IInterface {
   void notifySurgingPower(byte[] var1) throws RemoteException;

   public static class Default implements ISurgingPowerCallback {
      public IBinder asBinder() {
         return null;
      }

      public void notifySurgingPower(byte[] var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ISurgingPowerCallback {
      private static final String DESCRIPTOR = "com.hzbhd.canbus.smartpower.ISurgingPowerCallback";
      static final int TRANSACTION_notifySurgingPower = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.canbus.smartpower.ISurgingPowerCallback");
      }

      public static ISurgingPowerCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.canbus.smartpower.ISurgingPowerCallback");
            return (ISurgingPowerCallback)(var1 != null && var1 instanceof ISurgingPowerCallback ? (ISurgingPowerCallback)var1 : new Proxy(var0));
         }
      }

      public static ISurgingPowerCallback getDefaultImpl() {
         return ISurgingPowerCallback.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ISurgingPowerCallback var0) {
         if (ISurgingPowerCallback.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               ISurgingPowerCallback.Stub.Proxy.sDefaultImpl = var0;
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
               var3.writeString("com.hzbhd.canbus.smartpower.ISurgingPowerCallback");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.canbus.smartpower.ISurgingPowerCallback");
            this.notifySurgingPower(var2.createByteArray());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements ISurgingPowerCallback {
         public static ISurgingPowerCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.canbus.smartpower.ISurgingPowerCallback";
         }

         public void notifySurgingPower(byte[] var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.canbus.smartpower.ISurgingPowerCallback");
               var2.writeByteArray(var1);
               if (this.mRemote.transact(1, var2, var3, 0) || ISurgingPowerCallback.Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               ISurgingPowerCallback.Stub.getDefaultImpl().notifySurgingPower(var1);
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }
      }
   }
}
