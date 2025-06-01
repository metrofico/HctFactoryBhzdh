package com.hzbhd.proxy.camera.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICameraCallback extends IInterface {
   void onAttrChange(int var1, int var2) throws RemoteException;

   void onInfoChange(int var1, String var2) throws RemoteException;

   void onPreviewState(int var1) throws RemoteException;

   void onSignalChange(int var1) throws RemoteException;

   public static class Default implements ICameraCallback {
      public IBinder asBinder() {
         return null;
      }

      public void onAttrChange(int var1, int var2) throws RemoteException {
      }

      public void onInfoChange(int var1, String var2) throws RemoteException {
      }

      public void onPreviewState(int var1) throws RemoteException {
      }

      public void onSignalChange(int var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ICameraCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.camera.aidl.ICameraCallback";
      static final int TRANSACTION_onAttrChange = 3;
      static final int TRANSACTION_onInfoChange = 4;
      static final int TRANSACTION_onPreviewState = 2;
      static final int TRANSACTION_onSignalChange = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.camera.aidl.ICameraCallback");
      }

      public static ICameraCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.camera.aidl.ICameraCallback");
            return (ICameraCallback)(var1 != null && var1 instanceof ICameraCallback ? (ICameraCallback)var1 : new Proxy(var0));
         }
      }

      public static ICameraCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ICameraCallback var0) {
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
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 1598968902) {
                        return super.onTransact(var1, var2, var3, var4);
                     } else {
                        var3.writeString("com.hzbhd.proxy.camera.aidl.ICameraCallback");
                        return true;
                     }
                  } else {
                     var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraCallback");
                     this.onInfoChange(var2.readInt(), var2.readString());
                     var3.writeNoException();
                     return true;
                  }
               } else {
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraCallback");
                  this.onAttrChange(var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  return true;
               }
            } else {
               var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraCallback");
               this.onPreviewState(var2.readInt());
               var3.writeNoException();
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraCallback");
            this.onSignalChange(var2.readInt());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements ICameraCallback {
         public static ICameraCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.camera.aidl.ICameraCallback";
         }

         public void onAttrChange(int var1, int var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraCallback");
               var3.writeInt(var1);
               var3.writeInt(var2);
               if (this.mRemote.transact(3, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                  var4.readException();
                  return;
               }

               Stub.getDefaultImpl().onAttrChange(var1, var2);
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void onInfoChange(int var1, String var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraCallback");
               var3.writeInt(var1);
               var3.writeString(var2);
               if (!this.mRemote.transact(4, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().onInfoChange(var1, var2);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void onPreviewState(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraCallback");
               var3.writeInt(var1);
               if (this.mRemote.transact(2, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                  var4.readException();
                  return;
               }

               Stub.getDefaultImpl().onPreviewState(var1);
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void onSignalChange(int var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraCallback");
               var2.writeInt(var1);
               if (!this.mRemote.transact(1, var2, var4, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().onSignalChange(var1);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var2.recycle();
            }

         }
      }
   }
}
