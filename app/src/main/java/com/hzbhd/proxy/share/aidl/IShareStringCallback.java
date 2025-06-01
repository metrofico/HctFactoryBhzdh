package com.hzbhd.proxy.share.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IShareStringCallback extends IInterface {
   void onString(String var1, int var2) throws RemoteException;

   public static class Default implements IShareStringCallback {
      public IBinder asBinder() {
         return null;
      }

      public void onString(String var1, int var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IShareStringCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.share.aidl.IShareStringCallback";
      static final int TRANSACTION_onString = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.share.aidl.IShareStringCallback");
      }

      public static IShareStringCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.share.aidl.IShareStringCallback");
            return (IShareStringCallback)(var1 != null && var1 instanceof IShareStringCallback ? (IShareStringCallback)var1 : new Proxy(var0));
         }
      }

      public static IShareStringCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IShareStringCallback var0) {
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
               var3.writeString("com.hzbhd.proxy.share.aidl.IShareStringCallback");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareStringCallback");
            this.onString(var2.readString(), var2.readInt());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements IShareStringCallback {
         public static IShareStringCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.share.aidl.IShareStringCallback";
         }

         public void onString(String var1, int var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareStringCallback");
               var3.writeString(var1);
               var3.writeInt(var2);
               if (!this.mRemote.transact(1, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().onString(var1, var2);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }
      }
   }
}
