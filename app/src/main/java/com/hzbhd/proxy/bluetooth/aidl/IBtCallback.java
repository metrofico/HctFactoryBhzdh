package com.hzbhd.proxy.bluetooth.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBtCallback extends IInterface {
   void callingMessage(String var1, String var2) throws RemoteException;

   void onCallNumChange(String var1) throws RemoteException;

   void onCloseA2dp() throws RemoteException;

   void onCloseHfp() throws RemoteException;

   void onCurrA2dpAddressChange(String var1) throws RemoteException;

   void onCurrHfpAddressChange(String var1) throws RemoteException;

   void onFoundDeviceChange(List var1) throws RemoteException;

   void onHfpConnChange(List var1) throws RemoteException;

   void onMicOutChange(boolean var1) throws RemoteException;

   void onPairedChange(List var1) throws RemoteException;

   void updateBtStatus(int var1, String var2) throws RemoteException;

   void updateCall(List var1, String var2) throws RemoteException;

   void updateCallDevice(int var1) throws RemoteException;

   void updateCallName(String var1) throws RemoteException;

   void updateCallTime(long var1) throws RemoteException;

   void updateId3(String var1, String var2, String var3) throws RemoteException;

   void updateIsAutoAnswer(boolean var1) throws RemoteException;

   void updateIsAutoConn(boolean var1) throws RemoteException;

   void updateMicMute(boolean var1) throws RemoteException;

   void updateName(String var1) throws RemoteException;

   void updateWechatCall(String var1) throws RemoteException;

   public static class Default implements IBtCallback {
      public IBinder asBinder() {
         return null;
      }

      public void callingMessage(String var1, String var2) throws RemoteException {
      }

      public void onCallNumChange(String var1) throws RemoteException {
      }

      public void onCloseA2dp() throws RemoteException {
      }

      public void onCloseHfp() throws RemoteException {
      }

      public void onCurrA2dpAddressChange(String var1) throws RemoteException {
      }

      public void onCurrHfpAddressChange(String var1) throws RemoteException {
      }

      public void onFoundDeviceChange(List var1) throws RemoteException {
      }

      public void onHfpConnChange(List var1) throws RemoteException {
      }

      public void onMicOutChange(boolean var1) throws RemoteException {
      }

      public void onPairedChange(List var1) throws RemoteException {
      }

      public void updateBtStatus(int var1, String var2) throws RemoteException {
      }

      public void updateCall(List var1, String var2) throws RemoteException {
      }

      public void updateCallDevice(int var1) throws RemoteException {
      }

      public void updateCallName(String var1) throws RemoteException {
      }

      public void updateCallTime(long var1) throws RemoteException {
      }

      public void updateId3(String var1, String var2, String var3) throws RemoteException {
      }

      public void updateIsAutoAnswer(boolean var1) throws RemoteException {
      }

      public void updateIsAutoConn(boolean var1) throws RemoteException {
      }

      public void updateMicMute(boolean var1) throws RemoteException {
      }

      public void updateName(String var1) throws RemoteException {
      }

      public void updateWechatCall(String var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IBtCallback {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.bluetooth.aidl.IBtCallback";
      static final int TRANSACTION_callingMessage = 18;
      static final int TRANSACTION_onCallNumChange = 13;
      static final int TRANSACTION_onCloseA2dp = 1;
      static final int TRANSACTION_onCloseHfp = 2;
      static final int TRANSACTION_onCurrA2dpAddressChange = 9;
      static final int TRANSACTION_onCurrHfpAddressChange = 8;
      static final int TRANSACTION_onFoundDeviceChange = 6;
      static final int TRANSACTION_onHfpConnChange = 7;
      static final int TRANSACTION_onMicOutChange = 5;
      static final int TRANSACTION_onPairedChange = 10;
      static final int TRANSACTION_updateBtStatus = 3;
      static final int TRANSACTION_updateCall = 19;
      static final int TRANSACTION_updateCallDevice = 17;
      static final int TRANSACTION_updateCallName = 12;
      static final int TRANSACTION_updateCallTime = 15;
      static final int TRANSACTION_updateId3 = 14;
      static final int TRANSACTION_updateIsAutoAnswer = 21;
      static final int TRANSACTION_updateIsAutoConn = 4;
      static final int TRANSACTION_updateMicMute = 16;
      static final int TRANSACTION_updateName = 11;
      static final int TRANSACTION_updateWechatCall = 20;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
      }

      public static IBtCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
            return (IBtCallback)(var1 != null && var1 instanceof IBtCallback ? (IBtCallback)var1 : new Proxy(var0));
         }
      }

      public static IBtCallback getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IBtCallback var0) {
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
            boolean var6 = false;
            boolean var5 = false;
            boolean var8 = false;
            boolean var7 = false;
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.onCloseA2dp();
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.onCloseHfp();
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.updateBtStatus(var2.readInt(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  var5 = var8;
                  if (var2.readInt() != 0) {
                     var5 = true;
                  }

                  this.updateIsAutoConn(var5);
                  var3.writeNoException();
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  if (var2.readInt() != 0) {
                     var5 = true;
                  }

                  this.onMicOutChange(var5);
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.onFoundDeviceChange(var2.createStringArrayList());
                  var3.writeNoException();
                  return true;
               case 7:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.onHfpConnChange(var2.createStringArrayList());
                  var3.writeNoException();
                  return true;
               case 8:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.onCurrHfpAddressChange(var2.readString());
                  var3.writeNoException();
                  return true;
               case 9:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.onCurrA2dpAddressChange(var2.readString());
                  var3.writeNoException();
                  return true;
               case 10:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.onPairedChange(var2.createStringArrayList());
                  var3.writeNoException();
                  return true;
               case 11:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.updateName(var2.readString());
                  var3.writeNoException();
                  return true;
               case 12:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.updateCallName(var2.readString());
                  var3.writeNoException();
                  return true;
               case 13:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.onCallNumChange(var2.readString());
                  var3.writeNoException();
                  return true;
               case 14:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.updateId3(var2.readString(), var2.readString(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 15:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.updateCallTime(var2.readLong());
                  var3.writeNoException();
                  return true;
               case 16:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  var5 = var6;
                  if (var2.readInt() != 0) {
                     var5 = true;
                  }

                  this.updateMicMute(var5);
                  var3.writeNoException();
                  return true;
               case 17:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.updateCallDevice(var2.readInt());
                  var3.writeNoException();
                  return true;
               case 18:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.callingMessage(var2.readString(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 19:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.updateCall(var2.createStringArrayList(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 20:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  this.updateWechatCall(var2.readString());
                  var3.writeNoException();
                  return true;
               case 21:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
                  var5 = var7;
                  if (var2.readInt() != 0) {
                     var5 = true;
                  }

                  this.updateIsAutoAnswer(var5);
                  var3.writeNoException();
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
            return true;
         }
      }

      private static class Proxy implements IBtCallback {
         public static IBtCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public void callingMessage(String var1, String var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var3.writeString(var1);
               var3.writeString(var2);
               if (this.mRemote.transact(18, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                  var4.readException();
                  return;
               }

               Stub.getDefaultImpl().callingMessage(var1, var2);
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.bluetooth.aidl.IBtCallback";
         }

         public void onCallNumChange(String var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var2.writeString(var1);
               if (this.mRemote.transact(13, var2, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().onCallNumChange(var1);
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public void onCloseA2dp() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var1 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               if (!this.mRemote.transact(1, var3, var1, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().onCloseA2dp();
                  return;
               }

               var1.readException();
            } finally {
               var1.recycle();
               var3.recycle();
            }

         }

         public void onCloseHfp() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               if (!this.mRemote.transact(2, var3, var2, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().onCloseHfp();
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void onCurrA2dpAddressChange(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var3.writeString(var1);
               if (this.mRemote.transact(9, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().onCurrA2dpAddressChange(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void onCurrHfpAddressChange(String var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var2.writeString(var1);
               if (!this.mRemote.transact(8, var2, var3, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().onCurrHfpAddressChange(var1);
                  return;
               }

               var3.readException();
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public void onFoundDeviceChange(List var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var3.writeStringList(var1);
               if (this.mRemote.transact(6, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().onFoundDeviceChange(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void onHfpConnChange(List var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var3.writeStringList(var1);
               if (this.mRemote.transact(7, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().onHfpConnChange(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void onMicOutChange(boolean var1) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
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
                     var5.writeInt(var2);
                     if (this.mRemote.transact(5, var5, var3, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().onMicOutChange(var1);
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label167;
                  }

                  var3.recycle();
                  var5.recycle();
                  return;
               }

               try {
                  var3.readException();
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label167;
               }

               var3.recycle();
               var5.recycle();
               return;
            }

            Throwable var4 = var10000;
            var3.recycle();
            var5.recycle();
            throw var4;
         }

         public void onPairedChange(List var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var2.writeStringList(var1);
               if (!this.mRemote.transact(10, var2, var3, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().onPairedChange(var1);
                  return;
               }

               var3.readException();
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public void updateBtStatus(int var1, String var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var3.writeInt(var1);
               var3.writeString(var2);
               if (!this.mRemote.transact(3, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().updateBtStatus(var1, var2);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void updateCall(List var1, String var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var4.writeStringList(var1);
               var4.writeString(var2);
               if (this.mRemote.transact(19, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().updateCall(var1, var2);
            } finally {
               var3.recycle();
               var4.recycle();
            }

         }

         public void updateCallDevice(int var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var4.writeInt(var1);
               if (this.mRemote.transact(17, var4, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().updateCallDevice(var1);
            } finally {
               var2.recycle();
               var4.recycle();
            }

         }

         public void updateCallName(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var3.writeString(var1);
               if (this.mRemote.transact(12, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().updateCallName(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void updateCallTime(long var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var3.writeLong(var1);
               if (!this.mRemote.transact(15, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().updateCallTime(var1);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void updateId3(String var1, String var2, String var3) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var5.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var5.writeString(var1);
               var5.writeString(var2);
               var5.writeString(var3);
               if (!this.mRemote.transact(14, var5, var4, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().updateId3(var1, var2, var3);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var5.recycle();
            }

         }

         public void updateIsAutoAnswer(boolean var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
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
                     if (this.mRemote.transact(21, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().updateIsAutoAnswer(var1);
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label167;
                  }

                  var4.recycle();
                  var3.recycle();
                  return;
               }

               try {
                  var4.readException();
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label167;
               }

               var4.recycle();
               var3.recycle();
               return;
            }

            Throwable var5 = var10000;
            var4.recycle();
            var3.recycle();
            throw var5;
         }

         public void updateIsAutoConn(boolean var1) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
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
                     var5.writeInt(var2);
                     if (this.mRemote.transact(4, var5, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().updateIsAutoConn(var1);
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label167;
                  }

                  var4.recycle();
                  var5.recycle();
                  return;
               }

               try {
                  var4.readException();
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label167;
               }

               var4.recycle();
               var5.recycle();
               return;
            }

            Throwable var3 = var10000;
            var4.recycle();
            var5.recycle();
            throw var3;
         }

         public void updateMicMute(boolean var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
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
                     var4.writeInt(var2);
                     if (this.mRemote.transact(16, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().updateMicMute(var1);
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label167;
                  }

                  var3.recycle();
                  var4.recycle();
                  return;
               }

               try {
                  var3.readException();
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label167;
               }

               var3.recycle();
               var4.recycle();
               return;
            }

            Throwable var5 = var10000;
            var3.recycle();
            var4.recycle();
            throw var5;
         }

         public void updateName(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var3.writeString(var1);
               if (!this.mRemote.transact(11, var3, var2, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().updateName(var1);
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void updateWechatCall(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtCallback");
               var3.writeString(var1);
               if (this.mRemote.transact(20, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().updateWechatCall(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }
      }
   }
}
