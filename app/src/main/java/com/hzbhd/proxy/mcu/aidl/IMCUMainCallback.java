package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMCUMainCallback extends IInterface {
   void mcuInit(byte var1, boolean var2, boolean var3) throws RemoteException;

   void notifyCanboxVersion(String var1) throws RemoteException;

   void notifyHardwareVersion(String var1, String var2, String var3, String var4) throws RemoteException;

   void notifyMCUVersion(String var1, String var2, String var3) throws RemoteException;

   void notifyPowerStatus(int var1) throws RemoteException;

   void notifyScreenVersion(String var1) throws RemoteException;

   public static class Default implements IMCUMainCallback {
      public IBinder asBinder() {
         return null;
      }

      public void mcuInit(byte var1, boolean var2, boolean var3) throws RemoteException {
      }

      public void notifyCanboxVersion(String var1) throws RemoteException {
      }

      public void notifyHardwareVersion(String var1, String var2, String var3, String var4) throws RemoteException {
      }

      public void notifyMCUVersion(String var1, String var2, String var3) throws RemoteException {
      }

      public void notifyPowerStatus(int var1) throws RemoteException {
      }

      public void notifyScreenVersion(String var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IMCUMainCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUMainCallback";
      static final int TRANSACTION_mcuInit = 1;
      static final int TRANSACTION_notifyCanboxVersion = 4;
      static final int TRANSACTION_notifyHardwareVersion = 3;
      static final int TRANSACTION_notifyMCUVersion = 2;
      static final int TRANSACTION_notifyPowerStatus = 6;
      static final int TRANSACTION_notifyScreenVersion = 5;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
      }

      public static IMCUMainCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
            return (IMCUMainCallback)(var1 != null && var1 instanceof IMCUMainCallback ? (IMCUMainCallback)var1 : new Proxy(var0));
         }
      }

      public static IMCUMainCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IMCUMainCallback var0) {
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
         if (var1 != 1598968902) {
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
                  byte var5 = var2.readByte();
                  var1 = var2.readInt();
                  boolean var7 = false;
                  boolean var6;
                  if (var1 != 0) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  if (var2.readInt() != 0) {
                     var7 = true;
                  }

                  this.mcuInit(var5, var6, var7);
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
                  this.notifyMCUVersion(var2.readString(), var2.readString(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
                  this.notifyHardwareVersion(var2.readString(), var2.readString(), var2.readString(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
                  this.notifyCanboxVersion(var2.readString());
                  var3.writeNoException();
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
                  this.notifyScreenVersion(var2.readString());
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
                  this.notifyPowerStatus(var2.readInt());
                  var3.writeNoException();
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
            return true;
         }
      }

      private static class Proxy implements IMCUMainCallback {
         public static IMCUMainCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.mcu.aidl.IMCUMainCallback";
         }

         public void mcuInit(byte var1, boolean var2, boolean var3) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label254: {
               boolean var10001;
               try {
                  var6.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
                  var6.writeByte(var1);
               } catch (Throwable var26) {
                  var10000 = var26;
                  var10001 = false;
                  break label254;
               }

               byte var4;
               if (var2) {
                  var4 = 1;
               } else {
                  var4 = 0;
               }

               try {
                  var6.writeInt(var4);
               } catch (Throwable var25) {
                  var10000 = var25;
                  var10001 = false;
                  break label254;
               }

               if (var3) {
                  var4 = 1;
               } else {
                  var4 = 0;
               }

               label248: {
                  try {
                     var6.writeInt(var4);
                     if (this.mRemote.transact(1, var6, var5, 0) || Stub.getDefaultImpl() == null) {
                        break label248;
                     }

                     Stub.getDefaultImpl().mcuInit(var1, var2, var3);
                  } catch (Throwable var27) {
                     var10000 = var27;
                     var10001 = false;
                     break label254;
                  }

                  var5.recycle();
                  var6.recycle();
                  return;
               }

               try {
                  var5.readException();
               } catch (Throwable var24) {
                  var10000 = var24;
                  var10001 = false;
                  break label254;
               }

               var5.recycle();
               var6.recycle();
               return;
            }

            Throwable var7 = var10000;
            var5.recycle();
            var6.recycle();
            throw var7;
         }

         public void notifyCanboxVersion(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
               var3.writeString(var1);
               if (this.mRemote.transact(4, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().notifyCanboxVersion(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void notifyHardwareVersion(String var1, String var2, String var3, String var4) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var6.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
               var6.writeString(var1);
               var6.writeString(var2);
               var6.writeString(var3);
               var6.writeString(var4);
               if (this.mRemote.transact(3, var6, var5, 0) || Stub.getDefaultImpl() == null) {
                  var5.readException();
                  return;
               }

               Stub.getDefaultImpl().notifyHardwareVersion(var1, var2, var3, var4);
            } finally {
               var5.recycle();
               var6.recycle();
            }

         }

         public void notifyMCUVersion(String var1, String var2, String var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
               var4.writeString(var1);
               var4.writeString(var2);
               var4.writeString(var3);
               if (!this.mRemote.transact(2, var4, var5, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().notifyMCUVersion(var1, var2, var3);
                  return;
               }

               var5.readException();
            } finally {
               var5.recycle();
               var4.recycle();
            }

         }

         public void notifyPowerStatus(int var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
               var4.writeInt(var1);
               if (!this.mRemote.transact(6, var4, var3, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().notifyPowerStatus(var1);
                  return;
               }

               var3.readException();
            } finally {
               var3.recycle();
               var4.recycle();
            }

         }

         public void notifyScreenVersion(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainCallback");
               var3.writeString(var1);
               if (this.mRemote.transact(5, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().notifyScreenVersion(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }
      }
   }
}
