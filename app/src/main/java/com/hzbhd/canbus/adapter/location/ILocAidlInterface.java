package com.hzbhd.canbus.adapter.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ILocAidlInterface extends IInterface {
   void addCallBack(LocationCallback var1) throws RemoteException;

   int getSpeed(int var1) throws RemoteException;

   void removeCallBack(LocationCallback var1) throws RemoteException;

   public static class Default implements ILocAidlInterface {
      public void addCallBack(LocationCallback var1) throws RemoteException {
      }

      public IBinder asBinder() {
         return null;
      }

      public int getSpeed(int var1) throws RemoteException {
         return 0;
      }

      public void removeCallBack(LocationCallback var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ILocAidlInterface {
      private static final String DESCRIPTOR = "com.hzbhd.canbus.adapter.location.ILocAidlInterface";
      static final int TRANSACTION_addCallBack = 2;
      static final int TRANSACTION_getSpeed = 1;
      static final int TRANSACTION_removeCallBack = 3;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.canbus.adapter.location.ILocAidlInterface");
      }

      public static ILocAidlInterface asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.canbus.adapter.location.ILocAidlInterface");
            return (ILocAidlInterface)(var1 != null && var1 instanceof ILocAidlInterface ? (ILocAidlInterface)var1 : new Proxy(var0));
         }
      }

      public static ILocAidlInterface getDefaultImpl() {
         return ILocAidlInterface.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ILocAidlInterface var0) {
         if (ILocAidlInterface.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               ILocAidlInterface.Stub.Proxy.sDefaultImpl = var0;
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
                     var3.writeString("com.hzbhd.canbus.adapter.location.ILocAidlInterface");
                     return true;
                  }
               } else {
                  var2.enforceInterface("com.hzbhd.canbus.adapter.location.ILocAidlInterface");
                  this.removeCallBack(LocationCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               }
            } else {
               var2.enforceInterface("com.hzbhd.canbus.adapter.location.ILocAidlInterface");
               this.addCallBack(LocationCallback.Stub.asInterface(var2.readStrongBinder()));
               var3.writeNoException();
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.canbus.adapter.location.ILocAidlInterface");
            var1 = this.getSpeed(var2.readInt());
            var3.writeNoException();
            var3.writeInt(var1);
            return true;
         }
      }

      private static class Proxy implements ILocAidlInterface {
         public static ILocAidlInterface sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public void addCallBack(LocationCallback var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.canbus.adapter.location.ILocAidlInterface");
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
                     if (this.mRemote.transact(2, var4, var3, 0) || ILocAidlInterface.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     ILocAidlInterface.Stub.getDefaultImpl().addCallBack(var1);
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

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.canbus.adapter.location.ILocAidlInterface";
         }

         public int getSpeed(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.canbus.adapter.location.ILocAidlInterface");
               var3.writeInt(var1);
               if (this.mRemote.transact(1, var3, var2, 0) || ILocAidlInterface.Stub.getDefaultImpl() == null) {
                  var2.readException();
                  var1 = var2.readInt();
                  return var1;
               }

               var1 = ILocAidlInterface.Stub.getDefaultImpl().getSpeed(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

            return var1;
         }

         public void removeCallBack(LocationCallback var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.canbus.adapter.location.ILocAidlInterface");
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
                     if (this.mRemote.transact(3, var4, var3, 0) || ILocAidlInterface.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     ILocAidlInterface.Stub.getDefaultImpl().removeCallBack(var1);
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
      }
   }
}
