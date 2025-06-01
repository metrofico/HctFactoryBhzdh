package com.hzbhd.setting.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IModuleCallBack extends IInterface {
   byte[] getModuleBytes(int var1, int var2) throws RemoteException;

   int getModuleInt(int var1, int var2) throws RemoteException;

   String getModuleString(int var1, int var2) throws RemoteException;

   void onModuleBytes(int var1, int var2, byte[] var3) throws RemoteException;

   void onModuleInt(int var1, int var2, int var3) throws RemoteException;

   void onModuleString(int var1, int var2, String var3) throws RemoteException;

   public static class Default implements IModuleCallBack {
      public IBinder asBinder() {
         return null;
      }

      public byte[] getModuleBytes(int var1, int var2) throws RemoteException {
         return null;
      }

      public int getModuleInt(int var1, int var2) throws RemoteException {
         return 0;
      }

      public String getModuleString(int var1, int var2) throws RemoteException {
         return null;
      }

      public void onModuleBytes(int var1, int var2, byte[] var3) throws RemoteException {
      }

      public void onModuleInt(int var1, int var2, int var3) throws RemoteException {
      }

      public void onModuleString(int var1, int var2, String var3) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IModuleCallBack {
      private static final String DESCRIPTOR = "com.hzbhd.setting.proxy.aidl.IModuleCallBack";
      static final int TRANSACTION_getModuleBytes = 5;
      static final int TRANSACTION_getModuleInt = 4;
      static final int TRANSACTION_getModuleString = 6;
      static final int TRANSACTION_onModuleBytes = 2;
      static final int TRANSACTION_onModuleInt = 1;
      static final int TRANSACTION_onModuleString = 3;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.setting.proxy.aidl.IModuleCallBack");
      }

      public static IModuleCallBack asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
            return (IModuleCallBack)(var1 != null && var1 instanceof IModuleCallBack ? (IModuleCallBack)var1 : new Proxy(var0));
         }
      }

      public static IModuleCallBack getDefaultImpl() {
         return IModuleCallBack.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IModuleCallBack var0) {
         if (IModuleCallBack.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               IModuleCallBack.Stub.Proxy.sDefaultImpl = var0;
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
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
                  this.onModuleInt(var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
                  this.onModuleBytes(var2.readInt(), var2.readInt(), var2.createByteArray());
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
                  this.onModuleString(var2.readInt(), var2.readInt(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
                  var1 = this.getModuleInt(var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
                  byte[] var6 = this.getModuleBytes(var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeByteArray(var6);
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
                  String var5 = this.getModuleString(var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeString(var5);
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
            return true;
         }
      }

      private static class Proxy implements IModuleCallBack {
         public static IModuleCallBack sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.setting.proxy.aidl.IModuleCallBack";
         }

         public byte[] getModuleBytes(int var1, int var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            byte[] var5;
            try {
               var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
               var4.writeInt(var1);
               var4.writeInt(var2);
               if (!this.mRemote.transact(5, var4, var3, 0) && IModuleCallBack.Stub.getDefaultImpl() != null) {
                  var5 = IModuleCallBack.Stub.getDefaultImpl().getModuleBytes(var1, var2);
                  return var5;
               }

               var3.readException();
               var5 = var3.createByteArray();
            } finally {
               var3.recycle();
               var4.recycle();
            }

            return var5;
         }

         public int getModuleInt(int var1, int var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
               var3.writeInt(var1);
               var3.writeInt(var2);
               if (this.mRemote.transact(4, var3, var4, 0) || IModuleCallBack.Stub.getDefaultImpl() == null) {
                  var4.readException();
                  var1 = var4.readInt();
                  return var1;
               }

               var1 = IModuleCallBack.Stub.getDefaultImpl().getModuleInt(var1, var2);
            } finally {
               var4.recycle();
               var3.recycle();
            }

            return var1;
         }

         public String getModuleString(int var1, int var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            String var5;
            try {
               var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
               var4.writeInt(var1);
               var4.writeInt(var2);
               if (this.mRemote.transact(6, var4, var3, 0) || IModuleCallBack.Stub.getDefaultImpl() == null) {
                  var3.readException();
                  var5 = var3.readString();
                  return var5;
               }

               var5 = IModuleCallBack.Stub.getDefaultImpl().getModuleString(var1, var2);
            } finally {
               var3.recycle();
               var4.recycle();
            }

            return var5;
         }

         public void onModuleBytes(int var1, int var2, byte[] var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
               var4.writeInt(var1);
               var4.writeInt(var2);
               var4.writeByteArray(var3);
               if (!this.mRemote.transact(2, var4, var5, 0) && IModuleCallBack.Stub.getDefaultImpl() != null) {
                  IModuleCallBack.Stub.getDefaultImpl().onModuleBytes(var1, var2, var3);
                  return;
               }

               var5.readException();
            } finally {
               var5.recycle();
               var4.recycle();
            }

         }

         public void onModuleInt(int var1, int var2, int var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
               var4.writeInt(var1);
               var4.writeInt(var2);
               var4.writeInt(var3);
               if (!this.mRemote.transact(1, var4, var5, 0) && IModuleCallBack.Stub.getDefaultImpl() != null) {
                  IModuleCallBack.Stub.getDefaultImpl().onModuleInt(var1, var2, var3);
                  return;
               }

               var5.readException();
            } finally {
               var5.recycle();
               var4.recycle();
            }

         }

         public void onModuleString(int var1, int var2, String var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.IModuleCallBack");
               var4.writeInt(var1);
               var4.writeInt(var2);
               var4.writeString(var3);
               if (this.mRemote.transact(3, var4, var5, 0) || IModuleCallBack.Stub.getDefaultImpl() == null) {
                  var5.readException();
                  return;
               }

               IModuleCallBack.Stub.getDefaultImpl().onModuleString(var1, var2, var3);
            } finally {
               var5.recycle();
               var4.recycle();
            }

         }
      }
   }
}
