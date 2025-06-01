package com.hzbhd.proxy.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDvrDataCallBack extends IInterface {
   void onDvrDataRead(byte[] var1) throws RemoteException;

   public static class Default implements IDvrDataCallBack {
      public IBinder asBinder() {
         return null;
      }

      public void onDvrDataRead(byte[] var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IDvrDataCallBack {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.callback.IDvrDataCallBack";
      static final int TRANSACTION_onDvrDataRead = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.callback.IDvrDataCallBack");
      }

      public static IDvrDataCallBack asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.callback.IDvrDataCallBack");
            return (IDvrDataCallBack)(var1 != null && var1 instanceof IDvrDataCallBack ? (IDvrDataCallBack)var1 : new Proxy(var0));
         }
      }

      public static IDvrDataCallBack getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IDvrDataCallBack var0) {
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
               var3.writeString("com.hzbhd.proxy.callback.IDvrDataCallBack");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.callback.IDvrDataCallBack");
            this.onDvrDataRead(var2.createByteArray());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements IDvrDataCallBack {
         public static IDvrDataCallBack sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.callback.IDvrDataCallBack";
         }

         public void onDvrDataRead(byte[] var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.callback.IDvrDataCallBack");
               var2.writeByteArray(var1);
               if (this.mRemote.transact(1, var2, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().onDvrDataRead(var1);
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }
      }
   }
}
