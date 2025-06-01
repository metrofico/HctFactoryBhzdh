package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMCUMainService extends IInterface {
   void clearMessage(int var1) throws RemoteException;

   void closeDevice() throws RemoteException;

   int getCarBackState() throws RemoteException;

   int getHardwareReset() throws RemoteException;

   int getMcuHardware() throws RemoteException;

   int getPowerOnType() throws RemoteException;

   int getPowerStatus() throws RemoteException;

   byte getProcotolVersion() throws RemoteException;

   void getVersion() throws RemoteException;

   Bundle initMCU() throws RemoteException;

   void initSystemReady() throws RemoteException;

   boolean isInitMCU() throws RemoteException;

   void registerMCUCanboxCallback(IMCUCanBoxControlCallback var1) throws RemoteException;

   void registerMCUMainCallback(IMCUMainCallback var1) throws RemoteException;

   void registerMcuMsgListener(IMCUMsgCallback var1) throws RemoteException;

   void requestPowerStatus(int var1) throws RemoteException;

   void sendMCUCanboxData(int var1, byte[] var2) throws RemoteException;

   void sendMCUDebugData(byte[] var1) throws RemoteException;

   void sendTestCmd(byte[] var1) throws RemoteException;

   void setSendSleepStatus(boolean var1) throws RemoteException;

   void setUpgradeStatus(boolean var1) throws RemoteException;

   void startSendHeartBeatMsg() throws RemoteException;

   void stopSendHeartBeatMsg() throws RemoteException;

   void unregisterMCUCanboxCallback(IMCUCanBoxControlCallback var1) throws RemoteException;

   void unregisterMCUMainCallback(IMCUMainCallback var1) throws RemoteException;

   void unregisterMcuMsgListener(IMCUMsgCallback var1) throws RemoteException;

   byte[] updateFlashData(byte[] var1) throws RemoteException;

   public static class Default implements IMCUMainService {
      public IBinder asBinder() {
         return null;
      }

      public void clearMessage(int var1) throws RemoteException {
      }

      public void closeDevice() throws RemoteException {
      }

      public int getCarBackState() throws RemoteException {
         return 0;
      }

      public int getHardwareReset() throws RemoteException {
         return 0;
      }

      public int getMcuHardware() throws RemoteException {
         return 0;
      }

      public int getPowerOnType() throws RemoteException {
         return 0;
      }

      public int getPowerStatus() throws RemoteException {
         return 0;
      }

      public byte getProcotolVersion() throws RemoteException {
         return 0;
      }

      public void getVersion() throws RemoteException {
      }

      public Bundle initMCU() throws RemoteException {
         return null;
      }

      public void initSystemReady() throws RemoteException {
      }

      public boolean isInitMCU() throws RemoteException {
         return false;
      }

      public void registerMCUCanboxCallback(IMCUCanBoxControlCallback var1) throws RemoteException {
      }

      public void registerMCUMainCallback(IMCUMainCallback var1) throws RemoteException {
      }

      public void registerMcuMsgListener(IMCUMsgCallback var1) throws RemoteException {
      }

      public void requestPowerStatus(int var1) throws RemoteException {
      }

      public void sendMCUCanboxData(int var1, byte[] var2) throws RemoteException {
      }

      public void sendMCUDebugData(byte[] var1) throws RemoteException {
      }

      public void sendTestCmd(byte[] var1) throws RemoteException {
      }

      public void setSendSleepStatus(boolean var1) throws RemoteException {
      }

      public void setUpgradeStatus(boolean var1) throws RemoteException {
      }

      public void startSendHeartBeatMsg() throws RemoteException {
      }

      public void stopSendHeartBeatMsg() throws RemoteException {
      }

      public void unregisterMCUCanboxCallback(IMCUCanBoxControlCallback var1) throws RemoteException {
      }

      public void unregisterMCUMainCallback(IMCUMainCallback var1) throws RemoteException {
      }

      public void unregisterMcuMsgListener(IMCUMsgCallback var1) throws RemoteException {
      }

      public byte[] updateFlashData(byte[] var1) throws RemoteException {
         return null;
      }
   }

   public abstract static class Stub extends Binder implements IMCUMainService {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUMainService";
      static final int TRANSACTION_clearMessage = 4;
      static final int TRANSACTION_closeDevice = 6;
      static final int TRANSACTION_getCarBackState = 19;
      static final int TRANSACTION_getHardwareReset = 16;
      static final int TRANSACTION_getMcuHardware = 15;
      static final int TRANSACTION_getPowerOnType = 17;
      static final int TRANSACTION_getPowerStatus = 23;
      static final int TRANSACTION_getProcotolVersion = 18;
      static final int TRANSACTION_getVersion = 20;
      static final int TRANSACTION_initMCU = 3;
      static final int TRANSACTION_initSystemReady = 5;
      static final int TRANSACTION_isInitMCU = 21;
      static final int TRANSACTION_registerMCUCanboxCallback = 12;
      static final int TRANSACTION_registerMCUMainCallback = 1;
      static final int TRANSACTION_registerMcuMsgListener = 25;
      static final int TRANSACTION_requestPowerStatus = 22;
      static final int TRANSACTION_sendMCUCanboxData = 11;
      static final int TRANSACTION_sendMCUDebugData = 14;
      static final int TRANSACTION_sendTestCmd = 24;
      static final int TRANSACTION_setSendSleepStatus = 9;
      static final int TRANSACTION_setUpgradeStatus = 10;
      static final int TRANSACTION_startSendHeartBeatMsg = 7;
      static final int TRANSACTION_stopSendHeartBeatMsg = 8;
      static final int TRANSACTION_unregisterMCUCanboxCallback = 13;
      static final int TRANSACTION_unregisterMCUMainCallback = 2;
      static final int TRANSACTION_unregisterMcuMsgListener = 26;
      static final int TRANSACTION_updateFlashData = 27;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.mcu.aidl.IMCUMainService");
      }

      public static IMCUMainService asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
            return (IMCUMainService)(var1 != null && var1 instanceof IMCUMainService ? (IMCUMainService)var1 : new Proxy(var0));
         }
      }

      public static IMCUMainService getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IMCUMainService var0) {
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
            boolean var7 = false;
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.registerMCUMainCallback(IMCUMainCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.unregisterMCUMainCallback(IMCUMainCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  Bundle var10 = this.initMCU();
                  var3.writeNoException();
                  if (var10 != null) {
                     var3.writeInt(1);
                     var10.writeToParcel(var3, 1);
                  } else {
                     var3.writeInt(0);
                  }

                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.clearMessage(var2.readInt());
                  var3.writeNoException();
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.initSystemReady();
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.closeDevice();
                  var3.writeNoException();
                  return true;
               case 7:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.startSendHeartBeatMsg();
                  var3.writeNoException();
                  return true;
               case 8:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.stopSendHeartBeatMsg();
                  var3.writeNoException();
                  return true;
               case 9:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  if (var2.readInt() != 0) {
                     var6 = true;
                  }

                  this.setSendSleepStatus(var6);
                  var3.writeNoException();
                  return true;
               case 10:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  var6 = var7;
                  if (var2.readInt() != 0) {
                     var6 = true;
                  }

                  this.setUpgradeStatus(var6);
                  var3.writeNoException();
                  return true;
               case 11:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.sendMCUCanboxData(var2.readInt(), var2.createByteArray());
                  var3.writeNoException();
                  return true;
               case 12:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.registerMCUCanboxCallback(IMCUCanBoxControlCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 13:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.unregisterMCUCanboxCallback(IMCUCanBoxControlCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 14:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.sendMCUDebugData(var2.createByteArray());
                  var3.writeNoException();
                  return true;
               case 15:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  var1 = this.getMcuHardware();
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 16:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  var1 = this.getHardwareReset();
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 17:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  var1 = this.getPowerOnType();
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 18:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  byte var5 = this.getProcotolVersion();
                  var3.writeNoException();
                  var3.writeByte(var5);
                  return true;
               case 19:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  var1 = this.getCarBackState();
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 20:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.getVersion();
                  var3.writeNoException();
                  return true;
               case 21:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  byte var8 = this.isInitMCU();
                  var3.writeNoException();
                  var3.writeInt(var8);
                  return true;
               case 22:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.requestPowerStatus(var2.readInt());
                  var3.writeNoException();
                  return true;
               case 23:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  var1 = this.getPowerStatus();
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 24:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.sendTestCmd(var2.createByteArray());
                  var3.writeNoException();
                  return true;
               case 25:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.registerMcuMsgListener(IMCUMsgCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 26:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  this.unregisterMcuMsgListener(IMCUMsgCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 27:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  byte[] var9 = this.updateFlashData(var2.createByteArray());
                  var3.writeNoException();
                  var3.writeByteArray(var9);
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
            return true;
         }
      }

      private static class Proxy implements IMCUMainService {
         public static IMCUMainService sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public void clearMessage(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               var3.writeInt(var1);
               if (!this.mRemote.transact(4, var3, var2, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().clearMessage(var1);
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void closeDevice() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var1 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (!this.mRemote.transact(6, var2, var1, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().closeDevice();
                  return;
               }

               var1.readException();
            } finally {
               var1.recycle();
               var2.recycle();
            }

         }

         public int getCarBackState() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            int var1;
            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (this.mRemote.transact(19, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  var1 = var2.readInt();
                  return var1;
               }

               var1 = Stub.getDefaultImpl().getCarBackState();
            } finally {
               var2.recycle();
               var3.recycle();
            }

            return var1;
         }

         public int getHardwareReset() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            int var1;
            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (!this.mRemote.transact(16, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                  var1 = Stub.getDefaultImpl().getHardwareReset();
                  return var1;
               }

               var4.readException();
               var1 = var4.readInt();
            } finally {
               var4.recycle();
               var3.recycle();
            }

            return var1;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.mcu.aidl.IMCUMainService";
         }

         public int getMcuHardware() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            int var1;
            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (!this.mRemote.transact(15, var2, var3, 0) && Stub.getDefaultImpl() != null) {
                  var1 = Stub.getDefaultImpl().getMcuHardware();
                  return var1;
               }

               var3.readException();
               var1 = var3.readInt();
            } finally {
               var3.recycle();
               var2.recycle();
            }

            return var1;
         }

         public int getPowerOnType() throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            int var1;
            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (this.mRemote.transact(17, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  var1 = var3.readInt();
                  return var1;
               }

               var1 = Stub.getDefaultImpl().getPowerOnType();
            } finally {
               var3.recycle();
               var4.recycle();
            }

            return var1;
         }

         public int getPowerStatus() throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            int var1;
            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (!this.mRemote.transact(23, var4, var3, 0) && Stub.getDefaultImpl() != null) {
                  var1 = Stub.getDefaultImpl().getPowerStatus();
                  return var1;
               }

               var3.readException();
               var1 = var3.readInt();
            } finally {
               var3.recycle();
               var4.recycle();
            }

            return var1;
         }

         public byte getProcotolVersion() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            byte var1;
            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (!this.mRemote.transact(18, var2, var4, 0) && Stub.getDefaultImpl() != null) {
                  var1 = Stub.getDefaultImpl().getProcotolVersion();
                  return var1;
               }

               var4.readException();
               var1 = var4.readByte();
            } finally {
               var4.recycle();
               var2.recycle();
            }

            return var1;
         }

         public void getVersion() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (this.mRemote.transact(20, var1, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().getVersion();
            } finally {
               var3.recycle();
               var1.recycle();
            }

         }

         public Bundle initMCU() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();
            boolean var5 = false;

            Bundle var1;
            label60: {
               label59: {
                  try {
                     var5 = true;
                     var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                     if (!this.mRemote.transact(3, var3, var2, 0) && Stub.getDefaultImpl() != null) {
                        var1 = Stub.getDefaultImpl().initMCU();
                        var5 = false;
                        break label60;
                     }

                     var2.readException();
                     if (var2.readInt() != 0) {
                        var1 = (Bundle)Bundle.CREATOR.createFromParcel(var2);
                        var5 = false;
                        break label59;
                     }

                     var5 = false;
                  } finally {
                     if (var5) {
                        var2.recycle();
                        var3.recycle();
                     }
                  }

                  var1 = null;
               }

               var2.recycle();
               var3.recycle();
               return var1;
            }

            var2.recycle();
            var3.recycle();
            return var1;
         }

         public void initSystemReady() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (this.mRemote.transact(5, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().initSystemReady();
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public boolean isInitMCU() throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(21, var4, var3, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().isInitMCU();
                        break label172;
                     }
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label171;
                  }

                  int var1;
                  try {
                     var3.readException();
                     var1 = var3.readInt();
                  } catch (Throwable var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label171;
                  }

                  if (var1 != 0) {
                     var2 = true;
                  }

                  var3.recycle();
                  var4.recycle();
                  return var2;
               }

               var3.recycle();
               var4.recycle();
               return var2;
            }

            Throwable var18 = var10000;
            var3.recycle();
            var4.recycle();
            throw var18;
         }

         public void registerMCUCanboxCallback(IMCUCanBoxControlCallback var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
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
                     if (this.mRemote.transact(12, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().registerMCUCanboxCallback(var1);
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

         public void registerMCUMainCallback(IMCUMainCallback var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
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

                     Stub.getDefaultImpl().registerMCUMainCallback(var1);
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

         public void registerMcuMsgListener(IMCUMsgCallback var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
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
                     if (this.mRemote.transact(25, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().registerMcuMsgListener(var1);
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

         public void requestPowerStatus(int var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               var4.writeInt(var1);
               if (!this.mRemote.transact(22, var4, var2, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().requestPowerStatus(var1);
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var4.recycle();
            }

         }

         public void sendMCUCanboxData(int var1, byte[] var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               var4.writeInt(var1);
               var4.writeByteArray(var2);
               if (this.mRemote.transact(11, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().sendMCUCanboxData(var1, var2);
            } finally {
               var3.recycle();
               var4.recycle();
            }

         }

         public void sendMCUDebugData(byte[] var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               var2.writeByteArray(var1);
               if (this.mRemote.transact(14, var2, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().sendMCUDebugData(var1);
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public void sendTestCmd(byte[] var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               var3.writeByteArray(var1);
               if (this.mRemote.transact(24, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().sendTestCmd(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void setSendSleepStatus(boolean var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
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
                     if (this.mRemote.transact(9, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().setSendSleepStatus(var1);
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

         public void setUpgradeStatus(boolean var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
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
                     if (this.mRemote.transact(10, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().setUpgradeStatus(var1);
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

         public void startSendHeartBeatMsg() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (this.mRemote.transact(7, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().startSendHeartBeatMsg();
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void stopSendHeartBeatMsg() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               if (this.mRemote.transact(8, var1, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().stopSendHeartBeatMsg();
            } finally {
               var3.recycle();
               var1.recycle();
            }

         }

         public void unregisterMCUCanboxCallback(IMCUCanBoxControlCallback var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
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
                     if (this.mRemote.transact(13, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().unregisterMCUCanboxCallback(var1);
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

         public void unregisterMCUMainCallback(IMCUMainCallback var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
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

                     Stub.getDefaultImpl().unregisterMCUMainCallback(var1);
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

         public void unregisterMcuMsgListener(IMCUMsgCallback var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
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
                     if (this.mRemote.transact(26, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().unregisterMcuMsgListener(var1);
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

         public byte[] updateFlashData(byte[] var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUMainService");
               var3.writeByteArray(var1);
               if (this.mRemote.transact(27, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  var1 = var2.createByteArray();
                  return var1;
               }

               var1 = Stub.getDefaultImpl().updateFlashData(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

            return var1;
         }
      }
   }
}
