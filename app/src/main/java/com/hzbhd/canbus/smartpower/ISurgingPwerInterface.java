package com.hzbhd.canbus.smartpower;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISurgingPwerInterface extends IInterface {
   String registerCallBack(ISurgingPowerCallback var1) throws RemoteException;

   void sendMsg(byte[] var1) throws RemoteException;

   void unRegisterCallBack(String var1) throws RemoteException;

   public static class Default implements ISurgingPwerInterface {
      public IBinder asBinder() {
         return null;
      }

      public String registerCallBack(ISurgingPowerCallback var1) throws RemoteException {
         return null;
      }

      public void sendMsg(byte[] var1) throws RemoteException {
      }

      public void unRegisterCallBack(String var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ISurgingPwerInterface {
      private static final String DESCRIPTOR = "com.hzbhd.canbus.smartpower.ISurgingPwerInterface";
      static final int TRANSACTION_registerCallBack = 2;
      static final int TRANSACTION_sendMsg = 1;
      static final int TRANSACTION_unRegisterCallBack = 3;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.canbus.smartpower.ISurgingPwerInterface");
      }

      public static ISurgingPwerInterface asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.canbus.smartpower.ISurgingPwerInterface");
            return (ISurgingPwerInterface)(var1 != null && var1 instanceof ISurgingPwerInterface ? (ISurgingPwerInterface)var1 : new Proxy(var0));
         }
      }

      public static ISurgingPwerInterface getDefaultImpl() {
         return ISurgingPwerInterface.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ISurgingPwerInterface var0) {
         if (ISurgingPwerInterface.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               ISurgingPwerInterface.Stub.Proxy.sDefaultImpl = var0;
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
                     var3.writeString("com.hzbhd.canbus.smartpower.ISurgingPwerInterface");
                     return true;
                  }
               } else {
                  var2.enforceInterface("com.hzbhd.canbus.smartpower.ISurgingPwerInterface");
                  this.unRegisterCallBack(var2.readString());
                  var3.writeNoException();
                  return true;
               }
            } else {
               var2.enforceInterface("com.hzbhd.canbus.smartpower.ISurgingPwerInterface");
               String var5 = this.registerCallBack(ISurgingPowerCallback.Stub.asInterface(var2.readStrongBinder()));
               var3.writeNoException();
               var3.writeString(var5);
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.canbus.smartpower.ISurgingPwerInterface");
            this.sendMsg(var2.createByteArray());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements ISurgingPwerInterface {
         public static ISurgingPwerInterface sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.canbus.smartpower.ISurgingPwerInterface";
         }

         public String registerCallBack(ISurgingPowerCallback var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.canbus.smartpower.ISurgingPwerInterface");
               } catch (Throwable var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label229;
               }

               IBinder var2;
               if (var1 != null) {
                  try {
                     var2 = var1.asBinder();
                  } catch (Throwable var22) {
                     var10000 = var22;
                     var10001 = false;
                     break label229;
                  }
               } else {
                  var2 = null;
               }

               String var25;
               label223: {
                  try {
                     var3.writeStrongBinder(var2);
                     if (this.mRemote.transact(2, var3, var4, 0) || ISurgingPwerInterface.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     var25 = ISurgingPwerInterface.Stub.getDefaultImpl().registerCallBack(var1);
                  } catch (Throwable var24) {
                     var10000 = var24;
                     var10001 = false;
                     break label229;
                  }

                  var4.recycle();
                  var3.recycle();
                  return var25;
               }

               try {
                  var4.readException();
                  var25 = var4.readString();
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label229;
               }

               var4.recycle();
               var3.recycle();
               return var25;
            }

            Throwable var26 = var10000;
            var4.recycle();
            var3.recycle();
            throw var26;
         }

         public void sendMsg(byte[] var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.canbus.smartpower.ISurgingPwerInterface");
               var2.writeByteArray(var1);
               if (!this.mRemote.transact(1, var2, var3, 0) && ISurgingPwerInterface.Stub.getDefaultImpl() != null) {
                  ISurgingPwerInterface.Stub.getDefaultImpl().sendMsg(var1);
                  return;
               }

               var3.readException();
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public void unRegisterCallBack(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.canbus.smartpower.ISurgingPwerInterface");
               var3.writeString(var1);
               if (!this.mRemote.transact(3, var3, var2, 0) && ISurgingPwerInterface.Stub.getDefaultImpl() != null) {
                  ISurgingPwerInterface.Stub.getDefaultImpl().unRegisterCallBack(var1);
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }
      }
   }
}
