package com.hzbhd.proxy.sourcemanager.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAudioCallback extends IInterface {
   void requestAudio(int var1, int var2, boolean var3) throws RemoteException;

   void setNavi(int var1, int var2) throws RemoteException;

   public static class Default implements IAudioCallback {
      public IBinder asBinder() {
         return null;
      }

      public void requestAudio(int var1, int var2, boolean var3) throws RemoteException {
      }

      public void setNavi(int var1, int var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IAudioCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback";
      static final int TRANSACTION_requestAudio = 1;
      static final int TRANSACTION_setNavi = 2;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback");
      }

      public static IAudioCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback");
            return (IAudioCallback)(var1 != null && var1 instanceof IAudioCallback ? (IAudioCallback)var1 : new Proxy(var0));
         }
      }

      public static IAudioCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IAudioCallback var0) {
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
               if (var1 != 1598968902) {
                  return super.onTransact(var1, var2, var3, var4);
               } else {
                  var3.writeString("com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback");
                  return true;
               }
            } else {
               var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback");
               this.setNavi(var2.readInt(), var2.readInt());
               var3.writeNoException();
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback");
            var1 = var2.readInt();
            var4 = var2.readInt();
            boolean var5;
            if (var2.readInt() != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            this.requestAudio(var1, var4, var5);
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements IAudioCallback {
         public static IAudioCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback";
         }

         public void requestAudio(int var1, int var2, boolean var3) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var7 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var6.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback");
                  var6.writeInt(var1);
                  var6.writeInt(var2);
               } catch (Throwable var18) {
                  var10000 = var18;
                  var10001 = false;
                  break label167;
               }

               byte var4;
               if (var3) {
                  var4 = 1;
               } else {
                  var4 = 0;
               }

               label161: {
                  try {
                     var6.writeInt(var4);
                     if (this.mRemote.transact(1, var6, var7, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().requestAudio(var1, var2, var3);
                  } catch (Throwable var19) {
                     var10000 = var19;
                     var10001 = false;
                     break label167;
                  }

                  var7.recycle();
                  var6.recycle();
                  return;
               }

               try {
                  var7.readException();
               } catch (Throwable var17) {
                  var10000 = var17;
                  var10001 = false;
                  break label167;
               }

               var7.recycle();
               var6.recycle();
               return;
            }

            Throwable var5 = var10000;
            var7.recycle();
            var6.recycle();
            throw var5;
         }

         public void setNavi(int var1, int var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback");
               var4.writeInt(var1);
               var4.writeInt(var2);
               if (this.mRemote.transact(2, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().setNavi(var1, var2);
            } finally {
               var3.recycle();
               var4.recycle();
            }

         }
      }
   }
}
