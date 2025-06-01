package com.hzbhd.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.proxy.callback.IDvrDataCallBack;

public interface IDvrAidlInterface extends IInterface {
   void registerOnDataReadCallback(IDvrDataCallBack var1) throws RemoteException;

   void sendData(byte[] var1) throws RemoteException;

   void unregisterOnDataReadCallback(IDvrDataCallBack var1) throws RemoteException;

   public static class Default implements IDvrAidlInterface {
      public IBinder asBinder() {
         return null;
      }

      public void registerOnDataReadCallback(IDvrDataCallBack var1) throws RemoteException {
      }

      public void sendData(byte[] var1) throws RemoteException {
      }

      public void unregisterOnDataReadCallback(IDvrDataCallBack var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IDvrAidlInterface {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.aidl.IDvrAidlInterface";
      static final int TRANSACTION_registerOnDataReadCallback = 1;
      static final int TRANSACTION_sendData = 3;
      static final int TRANSACTION_unregisterOnDataReadCallback = 2;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.aidl.IDvrAidlInterface");
      }

      public static IDvrAidlInterface asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.aidl.IDvrAidlInterface");
            return (IDvrAidlInterface)(var1 != null && var1 instanceof IDvrAidlInterface ? (IDvrAidlInterface)var1 : new Proxy(var0));
         }
      }

      public static IDvrAidlInterface getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IDvrAidlInterface var0) {
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
                     var3.writeString("com.hzbhd.proxy.aidl.IDvrAidlInterface");
                     return true;
                  }
               } else {
                  var2.enforceInterface("com.hzbhd.proxy.aidl.IDvrAidlInterface");
                  this.sendData(var2.createByteArray());
                  var3.writeNoException();
                  return true;
               }
            } else {
               var2.enforceInterface("com.hzbhd.proxy.aidl.IDvrAidlInterface");
               this.unregisterOnDataReadCallback(IDvrDataCallBack.Stub.asInterface(var2.readStrongBinder()));
               var3.writeNoException();
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.aidl.IDvrAidlInterface");
            this.registerOnDataReadCallback(IDvrDataCallBack.Stub.asInterface(var2.readStrongBinder()));
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements IDvrAidlInterface {
         public static IDvrAidlInterface sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.aidl.IDvrAidlInterface";
         }

         public void registerOnDataReadCallback(IDvrDataCallBack var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.aidl.IDvrAidlInterface");
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
                     if (this.mRemote.transact(1, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().registerOnDataReadCallback(var1);
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

         public void sendData(byte[] var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.aidl.IDvrAidlInterface");
               var2.writeByteArray(var1);
               if (this.mRemote.transact(3, var2, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().sendData(var1);
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public void unregisterOnDataReadCallback(IDvrDataCallBack var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.aidl.IDvrAidlInterface");
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
                     var3.writeStrongBinder(var2);
                     if (this.mRemote.transact(2, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().unregisterOnDataReadCallback(var1);
                  } catch (Throwable var24) {
                     var10000 = var24;
                     var10001 = false;
                     break label229;
                  }

                  var4.recycle();
                  var3.recycle();
                  return;
               }

               try {
                  var4.readException();
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label229;
               }

               var4.recycle();
               var3.recycle();
               return;
            }

            Throwable var25 = var10000;
            var4.recycle();
            var3.recycle();
            throw var25;
         }
      }
   }
}
