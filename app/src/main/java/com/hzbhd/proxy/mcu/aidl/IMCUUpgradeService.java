package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMCUUpgradeService extends IInterface {
   void registerMCUUpgradeCallback(IMCUUpgradeCallback var1) throws RemoteException;

   int reqUpgrade(String var1) throws RemoteException;

   int sendUpgradeData(byte[] var1, int var2, int var3, int var4) throws RemoteException;

   void unRegisterMCUUpgradeCallback(IMCUUpgradeCallback var1) throws RemoteException;

   byte[] updateFlashData(byte[] var1) throws RemoteException;

   int upgradeEnd(int var1) throws RemoteException;

   boolean upgradeStart(boolean var1, boolean var2, int var3, int var4, int var5, int var6) throws RemoteException;

   public static class Default implements IMCUUpgradeService {
      public IBinder asBinder() {
         return null;
      }

      public void registerMCUUpgradeCallback(IMCUUpgradeCallback var1) throws RemoteException {
      }

      public int reqUpgrade(String var1) throws RemoteException {
         return 0;
      }

      public int sendUpgradeData(byte[] var1, int var2, int var3, int var4) throws RemoteException {
         return 0;
      }

      public void unRegisterMCUUpgradeCallback(IMCUUpgradeCallback var1) throws RemoteException {
      }

      public byte[] updateFlashData(byte[] var1) throws RemoteException {
         return null;
      }

      public int upgradeEnd(int var1) throws RemoteException {
         return 0;
      }

      public boolean upgradeStart(boolean var1, boolean var2, int var3, int var4, int var5, int var6) throws RemoteException {
         return false;
      }
   }

   public abstract static class Stub extends Binder implements IMCUUpgradeService {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService";
      static final int TRANSACTION_registerMCUUpgradeCallback = 1;
      static final int TRANSACTION_reqUpgrade = 5;
      static final int TRANSACTION_sendUpgradeData = 4;
      static final int TRANSACTION_unRegisterMCUUpgradeCallback = 2;
      static final int TRANSACTION_updateFlashData = 7;
      static final int TRANSACTION_upgradeEnd = 6;
      static final int TRANSACTION_upgradeStart = 3;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
      }

      public static IMCUUpgradeService asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
            return (IMCUUpgradeService)(var1 != null && var1 instanceof IMCUUpgradeService ? (IMCUUpgradeService)var1 : new Proxy(var0));
         }
      }

      public static IMCUUpgradeService getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IMCUUpgradeService var0) {
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
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
                  this.registerMCUUpgradeCallback(IMCUUpgradeCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
                  this.unRegisterMCUUpgradeCallback(IMCUUpgradeCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
                  boolean var5;
                  if (var2.readInt() != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  boolean var6;
                  if (var2.readInt() != 0) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  byte var8 = this.upgradeStart(var5, var6, var2.readInt(), var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var8);
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
                  var1 = this.sendUpgradeData(var2.createByteArray(), var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
                  var1 = this.reqUpgrade(var2.readString());
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
                  var1 = this.upgradeEnd(var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 7:
                  var2.enforceInterface("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
                  byte[] var7 = this.updateFlashData(var2.createByteArray());
                  var3.writeNoException();
                  var3.writeByteArray(var7);
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
            return true;
         }
      }

      private static class Proxy implements IMCUUpgradeService {
         public static IMCUUpgradeService sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService";
         }

         public void registerMCUUpgradeCallback(IMCUUpgradeCallback var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
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

                     Stub.getDefaultImpl().registerMCUUpgradeCallback(var1);
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

         public int reqUpgrade(String var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            int var2;
            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
               var4.writeString(var1);
               if (this.mRemote.transact(5, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  var2 = var3.readInt();
                  return var2;
               }

               var2 = Stub.getDefaultImpl().reqUpgrade(var1);
            } finally {
               var3.recycle();
               var4.recycle();
            }

            return var2;
         }

         public int sendUpgradeData(byte[] var1, int var2, int var3, int var4) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var6.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
               var6.writeByteArray(var1);
               var6.writeInt(var2);
               var6.writeInt(var3);
               var6.writeInt(var4);
               if (!this.mRemote.transact(4, var6, var5, 0) && Stub.getDefaultImpl() != null) {
                  var2 = Stub.getDefaultImpl().sendUpgradeData(var1, var2, var3, var4);
                  return var2;
               }

               var5.readException();
               var2 = var5.readInt();
            } finally {
               var5.recycle();
               var6.recycle();
            }

            return var2;
         }

         public void unRegisterMCUUpgradeCallback(IMCUUpgradeCallback var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
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

                     Stub.getDefaultImpl().unRegisterMCUUpgradeCallback(var1);
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
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
               var3.writeByteArray(var1);
               if (!this.mRemote.transact(7, var3, var2, 0) && Stub.getDefaultImpl() != null) {
                  var1 = Stub.getDefaultImpl().updateFlashData(var1);
                  return var1;
               }

               var2.readException();
               var1 = var2.createByteArray();
            } finally {
               var2.recycle();
               var3.recycle();
            }

            return var1;
         }

         public int upgradeEnd(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
               var3.writeInt(var1);
               if (this.mRemote.transact(6, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  var1 = var2.readInt();
                  return var1;
               }

               var1 = Stub.getDefaultImpl().upgradeEnd(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

            return var1;
         }

         public boolean upgradeStart(boolean var1, boolean var2, int var3, int var4, int var5, int var6) throws RemoteException {
            Parcel var11 = Parcel.obtain();
            Parcel var10 = Parcel.obtain();

            Throwable var9;
            label422: {
               boolean var8;
               Throwable var10000;
               boolean var10001;
               label417: {
                  label423: {
                     try {
                        var11.writeInterfaceToken("com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService");
                     } catch (Throwable var41) {
                        var10000 = var41;
                        var10001 = false;
                        break label423;
                     }

                     var8 = true;
                     byte var7;
                     if (var1) {
                        var7 = 1;
                     } else {
                        var7 = 0;
                     }

                     try {
                        var11.writeInt(var7);
                     } catch (Throwable var40) {
                        var10000 = var40;
                        var10001 = false;
                        break label423;
                     }

                     if (var2) {
                        var7 = 1;
                     } else {
                        var7 = 0;
                     }

                     label408:
                     try {
                        var11.writeInt(var7);
                        var11.writeInt(var3);
                        var11.writeInt(var4);
                        var11.writeInt(var5);
                        var11.writeInt(var6);
                        break label417;
                     } catch (Throwable var39) {
                        var10000 = var39;
                        var10001 = false;
                        break label408;
                     }
                  }

                  var9 = var10000;
                  break label422;
               }

               label424: {
                  label403: {
                     try {
                        if (this.mRemote.transact(3, var11, var10, 0) || Stub.getDefaultImpl() == null) {
                           break label403;
                        }

                        var1 = Stub.getDefaultImpl().upgradeStart(var1, var2, var3, var4, var5, var6);
                     } catch (Throwable var38) {
                        var10000 = var38;
                        var10001 = false;
                        break label424;
                     }

                     var10.recycle();
                     var11.recycle();
                     return var1;
                  }

                  try {
                     var10.readException();
                     var3 = var10.readInt();
                  } catch (Throwable var37) {
                     var10000 = var37;
                     var10001 = false;
                     break label424;
                  }

                  if (var3 != 0) {
                     var1 = var8;
                  } else {
                     var1 = false;
                  }

                  var10.recycle();
                  var11.recycle();
                  return var1;
               }

               var9 = var10000;
            }

            var10.recycle();
            var11.recycle();
            throw var9;
         }
      }
   }
}
