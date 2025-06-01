package com.hzbhd.canbus.adapter.park;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IParkInterface extends IInterface {
   void registerCallBack(IParkCallBackInterface var1) throws RemoteException;

   void sendPanoramicParkKey(int var1) throws RemoteException;

   void sendPanoramicParkOn(boolean var1) throws RemoteException;

   void sendPanoramicParkPos(int var1, int var2, int var3) throws RemoteException;

   void sendPanoramicParkWH(int var1, int var2) throws RemoteException;

   void unRegisterCallBack() throws RemoteException;

   public static class Default implements IParkInterface {
      public IBinder asBinder() {
         return null;
      }

      public void registerCallBack(IParkCallBackInterface var1) throws RemoteException {
      }

      public void sendPanoramicParkKey(int var1) throws RemoteException {
      }

      public void sendPanoramicParkOn(boolean var1) throws RemoteException {
      }

      public void sendPanoramicParkPos(int var1, int var2, int var3) throws RemoteException {
      }

      public void sendPanoramicParkWH(int var1, int var2) throws RemoteException {
      }

      public void unRegisterCallBack() throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IParkInterface {
      private static final String DESCRIPTOR = "com.hzbhd.canbus.adapter.park.IParkInterface";
      static final int TRANSACTION_registerCallBack = 1;
      static final int TRANSACTION_sendPanoramicParkKey = 4;
      static final int TRANSACTION_sendPanoramicParkOn = 3;
      static final int TRANSACTION_sendPanoramicParkPos = 6;
      static final int TRANSACTION_sendPanoramicParkWH = 5;
      static final int TRANSACTION_unRegisterCallBack = 2;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.canbus.adapter.park.IParkInterface");
      }

      public static IParkInterface asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.canbus.adapter.park.IParkInterface");
            return (IParkInterface)(var1 != null && var1 instanceof IParkInterface ? (IParkInterface)var1 : new Proxy(var0));
         }
      }

      public static IParkInterface getDefaultImpl() {
         return IParkInterface.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IParkInterface var0) {
         if (IParkInterface.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               IParkInterface.Stub.Proxy.sDefaultImpl = var0;
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
                  var2.enforceInterface("com.hzbhd.canbus.adapter.park.IParkInterface");
                  this.registerCallBack(IParkCallBackInterface.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.park.IParkInterface");
                  this.unRegisterCallBack();
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.park.IParkInterface");
                  boolean var5;
                  if (var2.readInt() != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.sendPanoramicParkOn(var5);
                  var3.writeNoException();
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.park.IParkInterface");
                  this.sendPanoramicParkKey(var2.readInt());
                  var3.writeNoException();
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.park.IParkInterface");
                  this.sendPanoramicParkWH(var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.canbus.adapter.park.IParkInterface");
                  this.sendPanoramicParkPos(var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.canbus.adapter.park.IParkInterface");
            return true;
         }
      }

      private static class Proxy implements IParkInterface {
         public static IParkInterface sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.canbus.adapter.park.IParkInterface";
         }

         public void registerCallBack(IParkCallBackInterface var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.canbus.adapter.park.IParkInterface");
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

               label223: {
                  try {
                     var4.writeStrongBinder(var2);
                     if (this.mRemote.transact(1, var4, var3, 0) || IParkInterface.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     IParkInterface.Stub.getDefaultImpl().registerCallBack(var1);
                  } catch (Throwable var24) {
                     var10000 = var24;
                     var10001 = false;
                     break label229;
                  }

                  var3.recycle();
                  var4.recycle();
                  return;
               }

               try {
                  var3.readException();
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label229;
               }

               var3.recycle();
               var4.recycle();
               return;
            }

            Throwable var25 = var10000;
            var3.recycle();
            var4.recycle();
            throw var25;
         }

         public void sendPanoramicParkKey(int var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.canbus.adapter.park.IParkInterface");
               var4.writeInt(var1);
               if (!this.mRemote.transact(4, var4, var2, 0) && IParkInterface.Stub.getDefaultImpl() != null) {
                  IParkInterface.Stub.getDefaultImpl().sendPanoramicParkKey(var1);
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var4.recycle();
            }

         }

         public void sendPanoramicParkOn(boolean var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.canbus.adapter.park.IParkInterface");
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label167;
               }

               byte var2;
               if (var1) {
                  var2 = 1;
               } else {
                  var2 = 0;
               }

               label161: {
                  try {
                     var3.writeInt(var2);
                     if (this.mRemote.transact(3, var3, var5, 0) || IParkInterface.Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     IParkInterface.Stub.getDefaultImpl().sendPanoramicParkOn(var1);
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label167;
                  }

                  var5.recycle();
                  var3.recycle();
                  return;
               }

               try {
                  var5.readException();
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label167;
               }

               var5.recycle();
               var3.recycle();
               return;
            }

            Throwable var4 = var10000;
            var5.recycle();
            var3.recycle();
            throw var4;
         }

         public void sendPanoramicParkPos(int var1, int var2, int var3) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var5.writeInterfaceToken("com.hzbhd.canbus.adapter.park.IParkInterface");
               var5.writeInt(var1);
               var5.writeInt(var2);
               var5.writeInt(var3);
               if (this.mRemote.transact(6, var5, var4, 0) || IParkInterface.Stub.getDefaultImpl() == null) {
                  var4.readException();
                  return;
               }

               IParkInterface.Stub.getDefaultImpl().sendPanoramicParkPos(var1, var2, var3);
            } finally {
               var4.recycle();
               var5.recycle();
            }

         }

         public void sendPanoramicParkWH(int var1, int var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.canbus.adapter.park.IParkInterface");
               var3.writeInt(var1);
               var3.writeInt(var2);
               if (this.mRemote.transact(5, var3, var5, 0) || IParkInterface.Stub.getDefaultImpl() == null) {
                  var5.readException();
                  return;
               }

               IParkInterface.Stub.getDefaultImpl().sendPanoramicParkWH(var1, var2);
            } finally {
               var5.recycle();
               var3.recycle();
            }

         }

         public void unRegisterCallBack() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var1.writeInterfaceToken("com.hzbhd.canbus.adapter.park.IParkInterface");
               if (this.mRemote.transact(2, var1, var2, 0) || IParkInterface.Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               IParkInterface.Stub.getDefaultImpl().unRegisterCallBack();
            } finally {
               var2.recycle();
               var1.recycle();
            }

         }
      }
   }
}
