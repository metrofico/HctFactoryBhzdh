package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMCUCanBoxControlCallback extends IInterface {
   void notifyCanboxData(int var1, byte[] var2) throws RemoteException;

   public static class Default implements IMCUCanBoxControlCallback {
      public IBinder asBinder() {
         return null;
      }

      public void notifyCanboxData(int var1, byte[] var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IMCUCanBoxControlCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback";
      static final int TRANSACTION_notifyCanboxData = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback");
      }

      public static IMCUCanBoxControlCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback");
            return (IMCUCanBoxControlCallback)(var1 != null && var1 instanceof IMCUCanBoxControlCallback ? (IMCUCanBoxControlCallback)var1 : new Proxy(var0));
         }
      }

      public static IMCUCanBoxControlCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IMCUCanBoxControlCallback var0) {
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
               var3.writeString("com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback");
            this.notifyCanboxData(var2.readInt(), var2.createByteArray());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements IMCUCanBoxControlCallback {
         public static IMCUCanBoxControlCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback";
         }

         public void notifyCanboxData(int var1, byte[] var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback");
               var3.writeInt(var1);
               var3.writeByteArray(var2);
               if (this.mRemote.transact(1, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                  var4.readException();
                  return;
               }

               Stub.getDefaultImpl().notifyCanboxData(var1, var2);
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }
      }
   }
}
