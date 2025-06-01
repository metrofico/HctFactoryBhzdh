package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMCUMsgCallback extends IInterface {
   void onMsg(boolean var1, byte[] var2) throws RemoteException;

   public static class Default implements IMCUMsgCallback {
      public IBinder asBinder() {
         return null;
      }

      public void onMsg(boolean var1, byte[] var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IMCUMsgCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback";
      static final int TRANSACTION_onMsg = 1;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback");
      }

      public static IMCUMsgCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback");
            return (IMCUMsgCallback)(var1 != null && var1 instanceof IMCUMsgCallback ? (IMCUMsgCallback)var1 : new Proxy(var0));
         }
      }

      public static IMCUMsgCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IMCUMsgCallback var0) {
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
               var3.writeString("com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback");
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback");
            boolean var5;
            if (var2.readInt() != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            this.onMsg(var5, var2.createByteArray());
            var3.writeNoException();
            return true;
         }
      }

      private static class Proxy implements IMCUMsgCallback {
         public static IMCUMsgCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback";
         }

         public void onMsg(boolean var1, byte[] var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback");
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
                     var4.writeInt(var3);
                     var4.writeByteArray(var2);
                     if (this.mRemote.transact(1, var4, var5, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().onMsg(var1, var2);
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label167;
                  }

                  var5.recycle();
                  var4.recycle();
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
               var4.recycle();
               return;
            }

            Throwable var18 = var10000;
            var5.recycle();
            var4.recycle();
            throw var18;
         }
      }
   }
}
