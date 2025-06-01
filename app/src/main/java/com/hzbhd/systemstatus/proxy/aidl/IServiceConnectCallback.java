package com.hzbhd.systemstatus.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IServiceConnectCallback extends IInterface {
   void onConnected() throws RemoteException;

   public static class Default implements IServiceConnectCallback {
      public IBinder asBinder() {
         return null;
      }

      public void onConnected() throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IServiceConnectCallback {
      private static final String DESCRIPTOR = "com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback";
      static final int TRANSACTION_onConnected = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback");
      }

      public static IServiceConnectCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback");
            return (IServiceConnectCallback)(var1 != null && var1 instanceof IServiceConnectCallback ? (IServiceConnectCallback)var1 : new Proxy(var0));
         }
      }

      public static IServiceConnectCallback getDefaultImpl() {
         return IServiceConnectCallback.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IServiceConnectCallback var0) {
         if (IServiceConnectCallback.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               IServiceConnectCallback.Stub.Proxy.sDefaultImpl = var0;
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
               var3.writeString("com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback");
            this.onConnected();
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements IServiceConnectCallback {
         public static IServiceConnectCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback";
         }

         public void onConnected() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback");
               if (this.mRemote.transact(1, var2, var3, 0) || IServiceConnectCallback.Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               IServiceConnectCallback.Stub.getDefaultImpl().onConnected();
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }
      }
   }
}
