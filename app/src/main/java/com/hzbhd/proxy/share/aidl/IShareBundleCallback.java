package com.hzbhd.proxy.share.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IShareBundleCallback extends IInterface {
   void onBundle(Bundle var1, int var2) throws RemoteException;

   public static class Default implements IShareBundleCallback {
      public IBinder asBinder() {
         return null;
      }

      public void onBundle(Bundle var1, int var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IShareBundleCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.share.aidl.IShareBundleCallback";
      static final int TRANSACTION_onBundle = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.share.aidl.IShareBundleCallback");
      }

      public static IShareBundleCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.share.aidl.IShareBundleCallback");
            return (IShareBundleCallback)(var1 != null && var1 instanceof IShareBundleCallback ? (IShareBundleCallback)var1 : new Proxy(var0));
         }
      }

      public static IShareBundleCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IShareBundleCallback var0) {
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
               var3.writeString("com.hzbhd.proxy.share.aidl.IShareBundleCallback");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareBundleCallback");
            Bundle var5;
            if (var2.readInt() != 0) {
               var5 = (Bundle)Bundle.CREATOR.createFromParcel(var2);
            } else {
               var5 = null;
            }

            this.onBundle(var5, var2.readInt());
            var3.writeNoException();
            if (var5 != null) {
               var3.writeInt(1);
               var5.writeToParcel(var3, 1);
            } else {
               var3.writeInt(0);
            }

            return true;
         }
      }

      private static class Proxy implements IShareBundleCallback {
         public static IShareBundleCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.share.aidl.IShareBundleCallback";
         }

         public void onBundle(Bundle var1, int var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label311: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareBundleCallback");
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label311;
               }

               if (var1 != null) {
                  try {
                     var3.writeInt(1);
                     var1.writeToParcel(var3, 0);
                  } catch (Throwable var32) {
                     var10000 = var32;
                     var10001 = false;
                     break label311;
                  }
               } else {
                  try {
                     var3.writeInt(0);
                  } catch (Throwable var31) {
                     var10000 = var31;
                     var10001 = false;
                     break label311;
                  }
               }

               label305: {
                  try {
                     var3.writeInt(var2);
                     if (this.mRemote.transact(1, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label305;
                     }

                     Stub.getDefaultImpl().onBundle(var1, var2);
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label311;
                  }

                  var4.recycle();
                  var3.recycle();
                  return;
               }

               try {
                  var4.readException();
                  if (var4.readInt() != 0) {
                     var1.readFromParcel(var4);
                  }
               } catch (Throwable var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label311;
               }

               var4.recycle();
               var3.recycle();
               return;
            }

            Throwable var35 = var10000;
            var4.recycle();
            var3.recycle();
            throw var35;
         }
      }
   }
}
