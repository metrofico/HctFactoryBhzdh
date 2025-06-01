package com.hzbhd.setting.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISettingServiceManager extends IInterface {
   byte[] getBytes(int var1, int var2, int var3) throws RemoteException;

   String getConfig(int var1) throws RemoteException;

   int getInt(int var1, int var2, int var3) throws RemoteException;

   String getString(int var1, int var2, int var3) throws RemoteException;

   void notifyBytes(int var1, int var2, int var3, byte[] var4) throws RemoteException;

   void notifyConfig(int var1, String var2) throws RemoteException;

   void notifyInt(int var1, int var2, int var3, int var4) throws RemoteException;

   void notifyString(int var1, int var2, int var3, String var4) throws RemoteException;

   void registerModuleListener(int var1, IModuleCallBack var2) throws RemoteException;

   void registerSettingsListener(int var1, ISettingsCallBack var2) throws RemoteException;

   void setBytes(int var1, int var2, int var3, byte[] var4) throws RemoteException;

   void setInt(int var1, int var2, int var3, int var4) throws RemoteException;

   void setString(int var1, int var2, int var3, String var4) throws RemoteException;

   void unregisterModuleListener(int var1, IModuleCallBack var2) throws RemoteException;

   void unregisterSettingsListener(int var1, ISettingsCallBack var2) throws RemoteException;

   public static class Default implements ISettingServiceManager {
      public IBinder asBinder() {
         return null;
      }

      public byte[] getBytes(int var1, int var2, int var3) throws RemoteException {
         return null;
      }

      public String getConfig(int var1) throws RemoteException {
         return null;
      }

      public int getInt(int var1, int var2, int var3) throws RemoteException {
         return 0;
      }

      public String getString(int var1, int var2, int var3) throws RemoteException {
         return null;
      }

      public void notifyBytes(int var1, int var2, int var3, byte[] var4) throws RemoteException {
      }

      public void notifyConfig(int var1, String var2) throws RemoteException {
      }

      public void notifyInt(int var1, int var2, int var3, int var4) throws RemoteException {
      }

      public void notifyString(int var1, int var2, int var3, String var4) throws RemoteException {
      }

      public void registerModuleListener(int var1, IModuleCallBack var2) throws RemoteException {
      }

      public void registerSettingsListener(int var1, ISettingsCallBack var2) throws RemoteException {
      }

      public void setBytes(int var1, int var2, int var3, byte[] var4) throws RemoteException {
      }

      public void setInt(int var1, int var2, int var3, int var4) throws RemoteException {
      }

      public void setString(int var1, int var2, int var3, String var4) throws RemoteException {
      }

      public void unregisterModuleListener(int var1, IModuleCallBack var2) throws RemoteException {
      }

      public void unregisterSettingsListener(int var1, ISettingsCallBack var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ISettingServiceManager {
      private static final String DESCRIPTOR = "com.hzbhd.setting.proxy.aidl.ISettingServiceManager";
      static final int TRANSACTION_getBytes = 7;
      static final int TRANSACTION_getConfig = 14;
      static final int TRANSACTION_getInt = 6;
      static final int TRANSACTION_getString = 8;
      static final int TRANSACTION_notifyBytes = 12;
      static final int TRANSACTION_notifyConfig = 15;
      static final int TRANSACTION_notifyInt = 11;
      static final int TRANSACTION_notifyString = 13;
      static final int TRANSACTION_registerModuleListener = 9;
      static final int TRANSACTION_registerSettingsListener = 1;
      static final int TRANSACTION_setBytes = 4;
      static final int TRANSACTION_setInt = 3;
      static final int TRANSACTION_setString = 5;
      static final int TRANSACTION_unregisterModuleListener = 10;
      static final int TRANSACTION_unregisterSettingsListener = 2;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
      }

      public static ISettingServiceManager asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
            return (ISettingServiceManager)(var1 != null && var1 instanceof ISettingServiceManager ? (ISettingServiceManager)var1 : new Proxy(var0));
         }
      }

      public static ISettingServiceManager getDefaultImpl() {
         return ISettingServiceManager.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ISettingServiceManager var0) {
         if (ISettingServiceManager.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               ISettingServiceManager.Stub.Proxy.sDefaultImpl = var0;
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
            String var5;
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.registerSettingsListener(var2.readInt(), ISettingsCallBack.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.unregisterSettingsListener(var2.readInt(), ISettingsCallBack.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.setInt(var2.readInt(), var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.setBytes(var2.readInt(), var2.readInt(), var2.readInt(), var2.createByteArray());
                  var3.writeNoException();
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.setString(var2.readInt(), var2.readInt(), var2.readInt(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  var1 = this.getInt(var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 7:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  byte[] var6 = this.getBytes(var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeByteArray(var6);
                  return true;
               case 8:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  var5 = this.getString(var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeString(var5);
                  return true;
               case 9:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.registerModuleListener(var2.readInt(), IModuleCallBack.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 10:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.unregisterModuleListener(var2.readInt(), IModuleCallBack.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 11:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.notifyInt(var2.readInt(), var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  return true;
               case 12:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.notifyBytes(var2.readInt(), var2.readInt(), var2.readInt(), var2.createByteArray());
                  var3.writeNoException();
                  return true;
               case 13:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.notifyString(var2.readInt(), var2.readInt(), var2.readInt(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 14:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  var5 = this.getConfig(var2.readInt());
                  var3.writeNoException();
                  var3.writeString(var5);
                  return true;
               case 15:
                  var2.enforceInterface("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  this.notifyConfig(var2.readInt(), var2.readString());
                  var3.writeNoException();
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
            return true;
         }
      }

      private static class Proxy implements ISettingServiceManager {
         public static ISettingServiceManager sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public byte[] getBytes(int var1, int var2, int var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            byte[] var6;
            try {
               var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var4.writeInt(var1);
               var4.writeInt(var2);
               var4.writeInt(var3);
               if (!this.mRemote.transact(7, var4, var5, 0) && ISettingServiceManager.Stub.getDefaultImpl() != null) {
                  var6 = ISettingServiceManager.Stub.getDefaultImpl().getBytes(var1, var2, var3);
                  return var6;
               }

               var5.readException();
               var6 = var5.createByteArray();
            } finally {
               var5.recycle();
               var4.recycle();
            }

            return var6;
         }

         public String getConfig(int var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            String var4;
            try {
               var2.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var2.writeInt(var1);
               if (!this.mRemote.transact(14, var2, var3, 0) && ISettingServiceManager.Stub.getDefaultImpl() != null) {
                  var4 = ISettingServiceManager.Stub.getDefaultImpl().getConfig(var1);
                  return var4;
               }

               var3.readException();
               var4 = var3.readString();
            } finally {
               var3.recycle();
               var2.recycle();
            }

            return var4;
         }

         public int getInt(int var1, int var2, int var3) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            try {
               var5.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var5.writeInt(var1);
               var5.writeInt(var2);
               var5.writeInt(var3);
               if (!this.mRemote.transact(6, var5, var6, 0) && ISettingServiceManager.Stub.getDefaultImpl() != null) {
                  var1 = ISettingServiceManager.Stub.getDefaultImpl().getInt(var1, var2, var3);
                  return var1;
               }

               var6.readException();
               var1 = var6.readInt();
            } finally {
               var6.recycle();
               var5.recycle();
            }

            return var1;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.setting.proxy.aidl.ISettingServiceManager";
         }

         public String getString(int var1, int var2, int var3) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            String var6;
            try {
               var5.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var5.writeInt(var1);
               var5.writeInt(var2);
               var5.writeInt(var3);
               if (!this.mRemote.transact(8, var5, var4, 0) && ISettingServiceManager.Stub.getDefaultImpl() != null) {
                  var6 = ISettingServiceManager.Stub.getDefaultImpl().getString(var1, var2, var3);
                  return var6;
               }

               var4.readException();
               var6 = var4.readString();
            } finally {
               var4.recycle();
               var5.recycle();
            }

            return var6;
         }

         public void notifyBytes(int var1, int var2, int var3, byte[] var4) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var6.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var6.writeInt(var1);
               var6.writeInt(var2);
               var6.writeInt(var3);
               var6.writeByteArray(var4);
               if (this.mRemote.transact(12, var6, var5, 0) || ISettingServiceManager.Stub.getDefaultImpl() == null) {
                  var5.readException();
                  return;
               }

               ISettingServiceManager.Stub.getDefaultImpl().notifyBytes(var1, var2, var3, var4);
            } finally {
               var5.recycle();
               var6.recycle();
            }

         }

         public void notifyConfig(int var1, String var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var3.writeInt(var1);
               var3.writeString(var2);
               if (!this.mRemote.transact(15, var3, var4, 0) && ISettingServiceManager.Stub.getDefaultImpl() != null) {
                  ISettingServiceManager.Stub.getDefaultImpl().notifyConfig(var1, var2);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void notifyInt(int var1, int var2, int var3, int var4) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var6.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var6.writeInt(var1);
               var6.writeInt(var2);
               var6.writeInt(var3);
               var6.writeInt(var4);
               if (this.mRemote.transact(11, var6, var5, 0) || ISettingServiceManager.Stub.getDefaultImpl() == null) {
                  var5.readException();
                  return;
               }

               ISettingServiceManager.Stub.getDefaultImpl().notifyInt(var1, var2, var3, var4);
            } finally {
               var5.recycle();
               var6.recycle();
            }

         }

         public void notifyString(int var1, int var2, int var3, String var4) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var6.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var6.writeInt(var1);
               var6.writeInt(var2);
               var6.writeInt(var3);
               var6.writeString(var4);
               if (this.mRemote.transact(13, var6, var5, 0) || ISettingServiceManager.Stub.getDefaultImpl() == null) {
                  var5.readException();
                  return;
               }

               ISettingServiceManager.Stub.getDefaultImpl().notifyString(var1, var2, var3, var4);
            } finally {
               var5.recycle();
               var6.recycle();
            }

         }

         public void registerModuleListener(int var1, IModuleCallBack var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  var4.writeInt(var1);
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
                     var4.writeStrongBinder(var3);
                     if (this.mRemote.transact(9, var4, var5, 0) || ISettingServiceManager.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     ISettingServiceManager.Stub.getDefaultImpl().registerModuleListener(var1, var2);
                  } catch (Throwable var25) {
                     var10000 = var25;
                     var10001 = false;
                     break label229;
                  }

                  var5.recycle();
                  var4.recycle();
                  return;
               }

               try {
                  var5.readException();
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label229;
               }

               var5.recycle();
               var4.recycle();
               return;
            }

            Throwable var26 = var10000;
            var5.recycle();
            var4.recycle();
            throw var26;
         }

         public void registerSettingsListener(int var1, ISettingsCallBack var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  var4.writeInt(var1);
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
                     var4.writeStrongBinder(var3);
                     if (this.mRemote.transact(1, var4, var5, 0) || ISettingServiceManager.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     ISettingServiceManager.Stub.getDefaultImpl().registerSettingsListener(var1, var2);
                  } catch (Throwable var25) {
                     var10000 = var25;
                     var10001 = false;
                     break label229;
                  }

                  var5.recycle();
                  var4.recycle();
                  return;
               }

               try {
                  var5.readException();
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label229;
               }

               var5.recycle();
               var4.recycle();
               return;
            }

            Throwable var26 = var10000;
            var5.recycle();
            var4.recycle();
            throw var26;
         }

         public void setBytes(int var1, int var2, int var3, byte[] var4) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            try {
               var5.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var5.writeInt(var1);
               var5.writeInt(var2);
               var5.writeInt(var3);
               var5.writeByteArray(var4);
               if (this.mRemote.transact(4, var5, var6, 0) || ISettingServiceManager.Stub.getDefaultImpl() == null) {
                  var6.readException();
                  return;
               }

               ISettingServiceManager.Stub.getDefaultImpl().setBytes(var1, var2, var3, var4);
            } finally {
               var6.recycle();
               var5.recycle();
            }

         }

         public void setInt(int var1, int var2, int var3, int var4) throws RemoteException {
            Parcel var7 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            try {
               var7.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var7.writeInt(var1);
               var7.writeInt(var2);
               var7.writeInt(var3);
               var7.writeInt(var4);
               if (!this.mRemote.transact(3, var7, var6, 0) && ISettingServiceManager.Stub.getDefaultImpl() != null) {
                  ISettingServiceManager.Stub.getDefaultImpl().setInt(var1, var2, var3, var4);
                  return;
               }

               var6.readException();
            } finally {
               var6.recycle();
               var7.recycle();
            }

         }

         public void setString(int var1, int var2, int var3, String var4) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var6.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
               var6.writeInt(var1);
               var6.writeInt(var2);
               var6.writeInt(var3);
               var6.writeString(var4);
               if (this.mRemote.transact(5, var6, var5, 0) || ISettingServiceManager.Stub.getDefaultImpl() == null) {
                  var5.readException();
                  return;
               }

               ISettingServiceManager.Stub.getDefaultImpl().setString(var1, var2, var3, var4);
            } finally {
               var5.recycle();
               var6.recycle();
            }

         }

         public void unregisterModuleListener(int var1, IModuleCallBack var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
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
                     if (this.mRemote.transact(10, var5, var4, 0) || ISettingServiceManager.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     ISettingServiceManager.Stub.getDefaultImpl().unregisterModuleListener(var1, var2);
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

         public void unregisterSettingsListener(int var1, ISettingsCallBack var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.setting.proxy.aidl.ISettingServiceManager");
                  var4.writeInt(var1);
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
                     var4.writeStrongBinder(var3);
                     if (this.mRemote.transact(2, var4, var5, 0) || ISettingServiceManager.Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     ISettingServiceManager.Stub.getDefaultImpl().unregisterSettingsListener(var1, var2);
                  } catch (Throwable var25) {
                     var10000 = var25;
                     var10001 = false;
                     break label229;
                  }

                  var5.recycle();
                  var4.recycle();
                  return;
               }

               try {
                  var5.readException();
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label229;
               }

               var5.recycle();
               var4.recycle();
               return;
            }

            Throwable var26 = var10000;
            var5.recycle();
            var4.recycle();
            throw var26;
         }
      }
   }
}
