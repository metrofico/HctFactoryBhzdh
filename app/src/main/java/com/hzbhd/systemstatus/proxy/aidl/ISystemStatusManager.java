package com.hzbhd.systemstatus.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISystemStatusManager extends IInterface {
   String getServiceData(int var1, int var2) throws RemoteException;

   void regitsterServiceConnectCallback(int var1, IServiceConnectCallback var2) throws RemoteException;

   void setServiceData(int var1, int var2, String var3) throws RemoteException;

   void unregisterServiceConnectCallback(int var1, IServiceConnectCallback var2) throws RemoteException;

   public static class Default implements ISystemStatusManager {
      public IBinder asBinder() {
         return null;
      }

      public String getServiceData(int var1, int var2) throws RemoteException {
         return null;
      }

      public void regitsterServiceConnectCallback(int var1, IServiceConnectCallback var2) throws RemoteException {
      }

      public void setServiceData(int var1, int var2, String var3) throws RemoteException {
      }

      public void unregisterServiceConnectCallback(int var1, IServiceConnectCallback var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ISystemStatusManager {
      private static final String DESCRIPTOR = "com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager";
      static final int TRANSACTION_getServiceData = 4;
      static final int TRANSACTION_regitsterServiceConnectCallback = 1;
      static final int TRANSACTION_setServiceData = 3;
      static final int TRANSACTION_unregisterServiceConnectCallback = 2;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
      }

      public static ISystemStatusManager asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
            return (ISystemStatusManager)(var1 != null && var1 instanceof ISystemStatusManager ? (ISystemStatusManager)var1 : new Proxy(var0));
         }
      }

      public static ISystemStatusManager getDefaultImpl() {
         return ISystemStatusManager.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ISystemStatusManager var0) {
         if (ISystemStatusManager.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               ISystemStatusManager.Stub.Proxy.sDefaultImpl = var0;
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
                  if (var1 != 4) {
                     if (var1 != 1598968902) {
                        return super.onTransact(var1, var2, var3, var4);
                     } else {
                        var3.writeString("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
                        return true;
                     }
                  } else {
                     var2.enforceInterface("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
                     String var5 = this.getServiceData(var2.readInt(), var2.readInt());
                     var3.writeNoException();
                     var3.writeString(var5);
                     return true;
                  }
               } else {
                  var2.enforceInterface("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
                  this.setServiceData(var2.readInt(), var2.readInt(), var2.readString());
                  var3.writeNoException();
                  return true;
               }
            } else {
               var2.enforceInterface("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
               this.unregisterServiceConnectCallback(var2.readInt(), IServiceConnectCallback.Stub.asInterface(var2.readStrongBinder()));
               var3.writeNoException();
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
            this.regitsterServiceConnectCallback(var2.readInt(), IServiceConnectCallback.Stub.asInterface(var2.readStrongBinder()));
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements ISystemStatusManager {
         public static ISystemStatusManager sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager";
         }

         public String getServiceData(int var1, int var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            String var5;
            try {
               var3.writeInterfaceToken("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
               var3.writeInt(var1);
               var3.writeInt(var2);
               if (!this.mRemote.transact(4, var3, var4, 0) && ISystemStatusManager.Stub.getDefaultImpl() != null) {
                  var5 = ISystemStatusManager.Stub.getDefaultImpl().getServiceData(var1, var2);
                  return var5;
               }

               var4.readException();
               var5 = var4.readString();
            } finally {
               var4.recycle();
               var3.recycle();
            }

            return var5;
         }

         public void regitsterServiceConnectCallback(int var1, IServiceConnectCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
                  var5.writeInt(var1);
               } catch (Throwable var24) {
                  var10000 = var24;
                  var10001 = false;
                  break label229;
               }

               IBinder var3;
               if (var2 != null) {
                  try {
                     var3 = var2.asBinder();
                  } catch (Throwable var23) {
                     var10000 = var23;
                     var10001 = false;
                     break label229;
                  }
               } else {
                  var3 = null;
               }

               label223: {
                  try {
                     var5.writeStrongBinder(var3);
                     if (this.mRemote.transact(1, var5, var4, 0) || ISystemStatusManager.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     ISystemStatusManager.Stub.getDefaultImpl().regitsterServiceConnectCallback(var1, var2);
                  } catch (Throwable var25) {
                     var10000 = var25;
                     var10001 = false;
                     break label229;
                  }

                  var4.recycle();
                  var5.recycle();
                  return;
               }

               try {
                  var4.readException();
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label229;
               }

               var4.recycle();
               var5.recycle();
               return;
            }

            Throwable var26 = var10000;
            var4.recycle();
            var5.recycle();
            throw var26;
         }

         public void setServiceData(int var1, int var2, String var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
               var4.writeInt(var1);
               var4.writeInt(var2);
               var4.writeString(var3);
               if (!this.mRemote.transact(3, var4, var5, 0) && ISystemStatusManager.Stub.getDefaultImpl() != null) {
                  ISystemStatusManager.Stub.getDefaultImpl().setServiceData(var1, var2, var3);
                  return;
               }

               var5.readException();
            } finally {
               var5.recycle();
               var4.recycle();
            }

         }

         public void unregisterServiceConnectCallback(int var1, IServiceConnectCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager");
                  var5.writeInt(var1);
               } catch (Throwable var24) {
                  var10000 = var24;
                  var10001 = false;
                  break label229;
               }

               IBinder var3;
               if (var2 != null) {
                  try {
                     var3 = var2.asBinder();
                  } catch (Throwable var23) {
                     var10000 = var23;
                     var10001 = false;
                     break label229;
                  }
               } else {
                  var3 = null;
               }

               label223: {
                  try {
                     var5.writeStrongBinder(var3);
                     if (this.mRemote.transact(2, var5, var4, 0) || ISystemStatusManager.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     ISystemStatusManager.Stub.getDefaultImpl().unregisterServiceConnectCallback(var1, var2);
                  } catch (Throwable var25) {
                     var10000 = var25;
                     var10001 = false;
                     break label229;
                  }

                  var4.recycle();
                  var5.recycle();
                  return;
               }

               try {
                  var4.readException();
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label229;
               }

               var4.recycle();
               var5.recycle();
               return;
            }

            Throwable var26 = var10000;
            var4.recycle();
            var5.recycle();
            throw var26;
         }
      }
   }
}
