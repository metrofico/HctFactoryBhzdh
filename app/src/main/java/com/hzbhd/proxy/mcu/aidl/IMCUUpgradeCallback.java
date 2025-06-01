package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMCUUpgradeCallback extends IInterface {
   void notifyMCUUpgradeEndCheckStatus(String var1) throws RemoteException;

   void notifyMCUUpgradeSendDataSeq(int var1) throws RemoteException;

   void notifyMCUUpgradeStartCheckStatus(boolean var1, String var2) throws RemoteException;

   public static class Default implements IMCUUpgradeCallback {
      public IBinder asBinder() {
         return null;
      }

      public void notifyMCUUpgradeEndCheckStatus(String var1) throws RemoteException {
      }

      public void notifyMCUUpgradeSendDataSeq(int var1) throws RemoteException {
      }

      public void notifyMCUUpgradeStartCheckStatus(boolean var1, String var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IMCUUpgradeCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback";
      static final int TRANSACTION_notifyMCUUpgradeEndCheckStatus = 2;
      static final int TRANSACTION_notifyMCUUpgradeSendDataSeq = 3;
      static final int TRANSACTION_notifyMCUUpgradeStartCheckStatus = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback");
      }

      public static IMCUUpgradeCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback");
            return (IMCUUpgradeCallback)(var1 != null && var1 instanceof IMCUUpgradeCallback ? (IMCUUpgradeCallback)var1 : new Proxy(var0));
         }
      }

      public static IMCUUpgradeCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IMCUUpgradeCallback var0) {
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
                  if (var1 != 1598968902) {
                     return super.onTransact(var1, var2, var3, var4);
                  } else {
                     var3.writeString("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback");
                     return true;
                  }
               } else {
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback");
                  this.notifyMCUUpgradeSendDataSeq(var2.readInt());
                  var3.writeNoException();
                  return true;
               }
            } else {
               var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback");
               this.notifyMCUUpgradeEndCheckStatus(var2.readString());
               var3.writeNoException();
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback");
            boolean var5;
            if (var2.readInt() != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            this.notifyMCUUpgradeStartCheckStatus(var5, var2.readString());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements IMCUUpgradeCallback {
         public static IMCUUpgradeCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback";
         }

         public void notifyMCUUpgradeEndCheckStatus(String var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback");
               var2.writeString(var1);
               if (this.mRemote.transact(2, var2, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().notifyMCUUpgradeEndCheckStatus(var1);
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public void notifyMCUUpgradeSendDataSeq(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback");
               var3.writeInt(var1);
               if (this.mRemote.transact(3, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().notifyMCUUpgradeSendDataSeq(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void notifyMCUUpgradeStartCheckStatus(boolean var1, String var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback");
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label167;
               }

               byte var3;
               if (var1) {
                  var3 = 1;
               } else {
                  var3 = 0;
               }

               label161: {
                  try {
                     var5.writeInt(var3);
                     var5.writeString(var2);
                     if (this.mRemote.transact(1, var5, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().notifyMCUUpgradeStartCheckStatus(var1, var2);
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label167;
                  }

                  var4.recycle();
                  var5.recycle();
                  return;
               }

               try {
                  var4.readException();
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label167;
               }

               var4.recycle();
               var5.recycle();
               return;
            }

            Throwable var18 = var10000;
            var4.recycle();
            var5.recycle();
            throw var18;
         }
      }
   }
}
