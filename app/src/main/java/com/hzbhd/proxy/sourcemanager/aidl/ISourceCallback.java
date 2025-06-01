package com.hzbhd.proxy.sourcemanager.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISourceCallback extends IInterface {
   void onAction(int var1, Bundle var2) throws RemoteException;

   public static class Default implements ISourceCallback {
      public IBinder asBinder() {
         return null;
      }

      public void onAction(int var1, Bundle var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ISourceCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback";
      static final int TRANSACTION_onAction = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback");
      }

      public static ISourceCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback");
            return (ISourceCallback)(var1 != null && var1 instanceof ISourceCallback ? (ISourceCallback)var1 : new Proxy(var0));
         }
      }

      public static ISourceCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ISourceCallback var0) {
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
               var3.writeString("com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback");
            var1 = var2.readInt();
            Bundle var5;
            if (var2.readInt() != 0) {
               var5 = (Bundle)Bundle.CREATOR.createFromParcel(var2);
            } else {
               var5 = null;
            }

            this.onAction(var1, var5);
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements ISourceCallback {
         public static ISourceCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback";
         }

         public void onAction(int var1, Bundle var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label287: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback");
                  var4.writeInt(var1);
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label287;
               }

               if (var2 != null) {
                  try {
                     var4.writeInt(1);
                     var2.writeToParcel(var4, 0);
                  } catch (Throwable var32) {
                     var10000 = var32;
                     var10001 = false;
                     break label287;
                  }
               } else {
                  try {
                     var4.writeInt(0);
                  } catch (Throwable var31) {
                     var10000 = var31;
                     var10001 = false;
                     break label287;
                  }
               }

               label281: {
                  try {
                     if (this.mRemote.transact(1, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                        break label281;
                     }

                     Stub.getDefaultImpl().onAction(var1, var2);
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label287;
                  }

                  var3.recycle();
                  var4.recycle();
                  return;
               }

               try {
                  var3.readException();
               } catch (Throwable var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label287;
               }

               var3.recycle();
               var4.recycle();
               return;
            }

            Throwable var35 = var10000;
            var3.recycle();
            var4.recycle();
            throw var35;
         }
      }
   }
}
