package com.hzbhd.proxy.sourcemanager.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISourceBluetoothCallback extends IInterface {
   void onState(int var1) throws RemoteException;

   public static class Default implements ISourceBluetoothCallback {
      public IBinder asBinder() {
         return null;
      }

      public void onState(int var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ISourceBluetoothCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback";
      static final int TRANSACTION_onState = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback");
      }

      public static ISourceBluetoothCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback");
            return (ISourceBluetoothCallback)(var1 != null && var1 instanceof ISourceBluetoothCallback ? (ISourceBluetoothCallback)var1 : new Proxy(var0));
         }
      }

      public static ISourceBluetoothCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ISourceBluetoothCallback var0) {
         if (Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               Proxy.sDefaultImpl = var0;
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
               var3.writeString("com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback");
            this.onState(var2.readInt());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements ISourceBluetoothCallback {
         public static ISourceBluetoothCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback";
         }

         public void onState(int var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback");
               var2.writeInt(var1);
               if (this.mRemote.transact(1, var2, var4, 0) || Stub.getDefaultImpl() == null) {
                  var4.readException();
                  return;
               }

               Stub.getDefaultImpl().onState(var1);
            } finally {
               var4.recycle();
               var2.recycle();
            }

         }
      }
   }
}
