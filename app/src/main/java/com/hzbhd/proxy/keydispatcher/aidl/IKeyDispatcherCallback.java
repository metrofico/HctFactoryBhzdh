package com.hzbhd.proxy.keydispatcher.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IKeyDispatcherCallback extends IInterface {
   boolean onKeyEvent(int var1, int var2, int var3, Bundle var4) throws RemoteException;

   public static class Default implements IKeyDispatcherCallback {
      public IBinder asBinder() {
         return null;
      }

      public boolean onKeyEvent(int var1, int var2, int var3, Bundle var4) throws RemoteException {
         return false;
      }
   }

   public abstract static class Stub extends Binder implements IKeyDispatcherCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback";
      static final int TRANSACTION_onKeyEvent = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback");
      }

      public static IKeyDispatcherCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback");
            return (IKeyDispatcherCallback)(var1 != null && var1 instanceof IKeyDispatcherCallback ? (IKeyDispatcherCallback)var1 : new Proxy(var0));
         }
      }

      public static IKeyDispatcherCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IKeyDispatcherCallback var0) {
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
               var3.writeString("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback");
            int var5 = var2.readInt();
            var4 = var2.readInt();
            var1 = var2.readInt();
            Bundle var7;
            if (var2.readInt() != 0) {
               var7 = (Bundle)Bundle.CREATOR.createFromParcel(var2);
            } else {
               var7 = null;
            }

            byte var6 = this.onKeyEvent(var5, var4, var1, var7);
            var3.writeNoException();
            var3.writeInt(var6);
            return true;
         }
      }

      private static class Proxy implements IKeyDispatcherCallback {
         public static IKeyDispatcherCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback";
         }

         public boolean onKeyEvent(int var1, int var2, int var3, Bundle var4) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var7 = Parcel.obtain();

            Throwable var10000;
            label312: {
               boolean var10001;
               try {
                  var6.writeInterfaceToken("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback");
                  var6.writeInt(var1);
                  var6.writeInt(var2);
                  var6.writeInt(var3);
               } catch (Throwable var36) {
                  var10000 = var36;
                  var10001 = false;
                  break label312;
               }

               boolean var5 = true;
               if (var4 != null) {
                  try {
                     var6.writeInt(1);
                     var4.writeToParcel(var6, 0);
                  } catch (Throwable var35) {
                     var10000 = var35;
                     var10001 = false;
                     break label312;
                  }
               } else {
                  try {
                     var6.writeInt(0);
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label312;
                  }
               }

               label306: {
                  try {
                     if (this.mRemote.transact(1, var6, var7, 0) || Stub.getDefaultImpl() == null) {
                        break label306;
                     }

                     var5 = Stub.getDefaultImpl().onKeyEvent(var1, var2, var3, var4);
                  } catch (Throwable var37) {
                     var10000 = var37;
                     var10001 = false;
                     break label312;
                  }

                  var7.recycle();
                  var6.recycle();
                  return var5;
               }

               try {
                  var7.readException();
                  var1 = var7.readInt();
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label312;
               }

               if (var1 == 0) {
                  var5 = false;
               }

               var7.recycle();
               var6.recycle();
               return var5;
            }

            Throwable var38 = var10000;
            var7.recycle();
            var6.recycle();
            throw var38;
         }
      }
   }
}
