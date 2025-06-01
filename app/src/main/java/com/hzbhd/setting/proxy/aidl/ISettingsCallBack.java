package com.hzbhd.setting.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISettingsCallBack extends IInterface {
   void onSettingsBytes(int var1, int var2, byte[] var3) throws RemoteException;

   void onSettingsInt(int var1, int var2, int var3) throws RemoteException;

   void onSettingsString(int var1, int var2, String var3) throws RemoteException;

   public static class Default implements ISettingsCallBack {
      public IBinder asBinder() {
         return null;
      }

      public void onSettingsBytes(int var1, int var2, byte[] var3) throws RemoteException {
      }

      public void onSettingsInt(int var1, int var2, int var3) throws RemoteException {
      }

      public void onSettingsString(int var1, int var2, String var3) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ISettingsCallBack {
      private static final String DESCRIPTOR = "com.hzbhd.setting.proxy.aidl.ISettingsCallBack";
      static final int TRANSACTION_onSettingsBytes = 2;
      static final int TRANSACTION_onSettingsInt = 1;
      static final int TRANSACTION_onSettingsString = 3;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.setting.proxy.aidl.ISettingsCallBack");
      }

      public static ISettingsCallBack asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.setting.proxy.aidl.ISettingsCallBack");
            return (ISettingsCallBack)(var1 != null && var1 instanceof ISettingsCallBack ? (ISettingsCallBack)var1 : new Proxy(var0));
         }
      }

      public static ISettingsCallBack getDefaultImpl() {
         return ISettingsCallBack.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ISettingsCallBack var0) {
         if (ISettingsCallBack.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               ISettingsCallBack.Stub.Proxy.sDefaultImpl = var0;
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
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 1598968902) {
                     return super.onTransact(var1, var2, var3, var4);
                  } else {
                     var3.writeString("com.hzbhd.setting.proxy.aidl.ISettingsCallBack");
                     return true;
                  }
               } else {
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingsCallBack");
                  this.onSettingsString(var2.readInt(), var2.readInt(), var2.readString());
                  var3.writeNoException();
                  return true;
               }
            } else {
               var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingsCallBack");
               this.onSettingsBytes(var2.readInt(), var2.readInt(), var2.createByteArray());
               var3.writeNoException();
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingsCallBack");
            this.onSettingsInt(var2.readInt(), var2.readInt(), var2.readInt());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements ISettingsCallBack {
         public static ISettingsCallBack sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.setting.proxy.aidl.ISettingsCallBack";
         }

         public void onSettingsBytes(int var1, int var2, byte[] var3) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var5.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingsCallBack");
               var5.writeInt(var1);
               var5.writeInt(var2);
               var5.writeByteArray(var3);
               if (this.mRemote.transact(2, var5, var4, 0) || ISettingsCallBack.Stub.getDefaultImpl() == null) {
                  var4.readException();
                  return;
               }

               ISettingsCallBack.Stub.getDefaultImpl().onSettingsBytes(var1, var2, var3);
            } finally {
               var4.recycle();
               var5.recycle();
            }

         }

         public void onSettingsInt(int var1, int var2, int var3) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var5.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingsCallBack");
               var5.writeInt(var1);
               var5.writeInt(var2);
               var5.writeInt(var3);
               if (this.mRemote.transact(1, var5, var4, 0) || ISettingsCallBack.Stub.getDefaultImpl() == null) {
                  var4.readException();
                  return;
               }

               ISettingsCallBack.Stub.getDefaultImpl().onSettingsInt(var1, var2, var3);
            } finally {
               var4.recycle();
               var5.recycle();
            }

         }

         public void onSettingsString(int var1, int var2, String var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingsCallBack");
               var4.writeInt(var1);
               var4.writeInt(var2);
               var4.writeString(var3);
               if (!this.mRemote.transact(3, var4, var5, 0) && ISettingsCallBack.Stub.getDefaultImpl() != null) {
                  ISettingsCallBack.Stub.getDefaultImpl().onSettingsString(var1, var2, var3);
                  return;
               }

               var5.readException();
            } finally {
               var5.recycle();
               var4.recycle();
            }

         }
      }
   }
}
