package com.hzbhd.proxy.share.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IShareDataService extends IInterface {
   Bundle getBundle(String var1) throws RemoteException;

   int getInt(String var1) throws RemoteException;

   String getString(String var1) throws RemoteException;

   Bundle registerShareBundleCallback(String var1, IShareBundleCallback var2) throws RemoteException;

   int registerShareIntCallback(String var1, IShareIntCallback var2) throws RemoteException;

   String registerShareStringCallback(String var1, IShareStringCallback var2) throws RemoteException;

   void reportBundle(String var1, Bundle var2) throws RemoteException;

   void reportInt(String var1, int var2) throws RemoteException;

   void reportOtherBundle(String var1, Bundle var2, int var3) throws RemoteException;

   void reportOtherInt(String var1, int var2, int var3) throws RemoteException;

   void reportOtherString(String var1, String var2, int var3) throws RemoteException;

   void reportString(String var1, String var2) throws RemoteException;

   void unregisterShareBundleCallback(String var1, IShareBundleCallback var2) throws RemoteException;

   void unregisterShareIntCallback(String var1, IShareIntCallback var2) throws RemoteException;

   void unregisterShareStringCallback(String var1, IShareStringCallback var2) throws RemoteException;

   public static class Default implements IShareDataService {
      public IBinder asBinder() {
         return null;
      }

      public Bundle getBundle(String var1) throws RemoteException {
         return null;
      }

      public int getInt(String var1) throws RemoteException {
         return 0;
      }

      public String getString(String var1) throws RemoteException {
         return null;
      }

      public Bundle registerShareBundleCallback(String var1, IShareBundleCallback var2) throws RemoteException {
         return null;
      }

      public int registerShareIntCallback(String var1, IShareIntCallback var2) throws RemoteException {
         return 0;
      }

      public String registerShareStringCallback(String var1, IShareStringCallback var2) throws RemoteException {
         return null;
      }

      public void reportBundle(String var1, Bundle var2) throws RemoteException {
      }

      public void reportInt(String var1, int var2) throws RemoteException {
      }

      public void reportOtherBundle(String var1, Bundle var2, int var3) throws RemoteException {
      }

      public void reportOtherInt(String var1, int var2, int var3) throws RemoteException {
      }

      public void reportOtherString(String var1, String var2, int var3) throws RemoteException {
      }

      public void reportString(String var1, String var2) throws RemoteException {
      }

      public void unregisterShareBundleCallback(String var1, IShareBundleCallback var2) throws RemoteException {
      }

      public void unregisterShareIntCallback(String var1, IShareIntCallback var2) throws RemoteException {
      }

      public void unregisterShareStringCallback(String var1, IShareStringCallback var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IShareDataService {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.share.aidl.IShareDataService";
      static final int TRANSACTION_getBundle = 13;
      static final int TRANSACTION_getInt = 3;
      static final int TRANSACTION_getString = 8;
      static final int TRANSACTION_registerShareBundleCallback = 11;
      static final int TRANSACTION_registerShareIntCallback = 1;
      static final int TRANSACTION_registerShareStringCallback = 6;
      static final int TRANSACTION_reportBundle = 14;
      static final int TRANSACTION_reportInt = 4;
      static final int TRANSACTION_reportOtherBundle = 15;
      static final int TRANSACTION_reportOtherInt = 5;
      static final int TRANSACTION_reportOtherString = 10;
      static final int TRANSACTION_reportString = 9;
      static final int TRANSACTION_unregisterShareBundleCallback = 12;
      static final int TRANSACTION_unregisterShareIntCallback = 2;
      static final int TRANSACTION_unregisterShareStringCallback = 7;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.share.aidl.IShareDataService");
      }

      public static IShareDataService asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
            return (IShareDataService)(var1 != null && var1 instanceof IShareDataService ? (IShareDataService)var1 : new Proxy(var0));
         }
      }

      public static IShareDataService getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IShareDataService var0) {
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
            String var6 = null;
            Bundle var5 = null;
            Bundle var8;
            String var9;
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var1 = this.registerShareIntCallback(var2.readString(), IShareIntCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  this.unregisterShareIntCallback(var2.readString(), IShareIntCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var1 = this.getInt(var2.readString());
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  this.reportInt(var2.readString(), var2.readInt());
                  var3.writeNoException();
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  this.reportOtherInt(var2.readString(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var9 = this.registerShareStringCallback(var2.readString(), IShareStringCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  var3.writeString(var9);
                  return true;
               case 7:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  this.unregisterShareStringCallback(var2.readString(), IShareStringCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 8:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var9 = this.getString(var2.readString());
                  var3.writeNoException();
                  var3.writeString(var9);
                  return true;
               case 9:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  this.reportString(var2.readString(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 10:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  this.reportOtherString(var2.readString(), var2.readString(), var2.readInt());
                  var3.writeNoException();
                  return true;
               case 11:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var8 = this.registerShareBundleCallback(var2.readString(), IShareBundleCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  if (var8 != null) {
                     var3.writeInt(1);
                     var8.writeToParcel(var3, 1);
                  } else {
                     var3.writeInt(0);
                  }

                  return true;
               case 12:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  this.unregisterShareBundleCallback(var2.readString(), IShareBundleCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 13:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var8 = this.getBundle(var2.readString());
                  var3.writeNoException();
                  if (var8 != null) {
                     var3.writeInt(1);
                     var8.writeToParcel(var3, 1);
                  } else {
                     var3.writeInt(0);
                  }

                  return true;
               case 14:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  String var7 = var2.readString();
                  var5 = var6;
                  if (var2.readInt() != 0) {
                     var5 = (Bundle)Bundle.CREATOR.createFromParcel(var2);
                  }

                  this.reportBundle(var7, var5);
                  var3.writeNoException();
                  if (var5 != null) {
                     var3.writeInt(1);
                     var5.writeToParcel(var3, 1);
                  } else {
                     var3.writeInt(0);
                  }

                  return true;
               case 15:
                  var2.enforceInterface("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var6 = var2.readString();
                  if (var2.readInt() != 0) {
                     var5 = (Bundle)Bundle.CREATOR.createFromParcel(var2);
                  }

                  this.reportOtherBundle(var6, var5, var2.readInt());
                  var3.writeNoException();
                  if (var5 != null) {
                     var3.writeInt(1);
                     var5.writeToParcel(var3, 1);
                  } else {
                     var3.writeInt(0);
                  }

                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.proxy.share.aidl.IShareDataService");
            return true;
         }
      }

      private static class Proxy implements IShareDataService {
         public static IShareDataService sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public Bundle getBundle(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();
            boolean var5 = false;

            Bundle var7;
            label60: {
               label59: {
                  try {
                     var5 = true;
                     var3.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
                     var3.writeString(var1);
                     if (!this.mRemote.transact(13, var3, var2, 0) && Stub.getDefaultImpl() != null) {
                        var7 = Stub.getDefaultImpl().getBundle(var1);
                        var5 = false;
                        break label60;
                     }

                     var2.readException();
                     if (var2.readInt() != 0) {
                        var7 = (Bundle)Bundle.CREATOR.createFromParcel(var2);
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

                  var7 = null;
               }

               var2.recycle();
               var3.recycle();
               return var7;
            }

            var2.recycle();
            var3.recycle();
            return var7;
         }

         public int getInt(String var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            int var2;
            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
               var4.writeString(var1);
               if (this.mRemote.transact(3, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  var2 = var3.readInt();
                  return var2;
               }

               var2 = Stub.getDefaultImpl().getInt(var1);
            } finally {
               var3.recycle();
               var4.recycle();
            }

            return var2;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.share.aidl.IShareDataService";
         }

         public String getString(String var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
               var2.writeString(var1);
               if (this.mRemote.transact(8, var2, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  var1 = var3.readString();
                  return var1;
               }

               var1 = Stub.getDefaultImpl().getString(var1);
            } finally {
               var3.recycle();
               var2.recycle();
            }

            return var1;
         }

         public Bundle registerShareBundleCallback(String var1, IShareBundleCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            Throwable var10000;
            label341: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var5.writeString(var1);
               } catch (Throwable var35) {
                  var10000 = var35;
                  var10001 = false;
                  break label341;
               }

               Object var4 = null;
               IBinder var3;
               if (var2 != null) {
                  try {
                     var3 = var2.asBinder();
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label341;
                  }
               } else {
                  var3 = null;
               }

               Bundle var37;
               label335: {
                  try {
                     var5.writeStrongBinder(var3);
                     if (this.mRemote.transact(11, var5, var6, 0) || Stub.getDefaultImpl() == null) {
                        break label335;
                     }

                     var37 = Stub.getDefaultImpl().registerShareBundleCallback(var1, var2);
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label341;
                  }

                  var6.recycle();
                  var5.recycle();
                  return var37;
               }

               try {
                  var6.readException();
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label341;
               }

               var37 = (Bundle)var4;

               try {
                  if (var6.readInt() != 0) {
                     var37 = (Bundle)Bundle.CREATOR.createFromParcel(var6);
                  }
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label341;
               }

               var6.recycle();
               var5.recycle();
               return var37;
            }

            Throwable var38 = var10000;
            var6.recycle();
            var5.recycle();
            throw var38;
         }

         public int registerShareIntCallback(String var1, IShareIntCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var5.writeString(var1);
               } catch (Throwable var25) {
                  var10000 = var25;
                  var10001 = false;
                  break label229;
               }

               IBinder var4;
               if (var2 != null) {
                  try {
                     var4 = var2.asBinder();
                  } catch (Throwable var24) {
                     var10000 = var24;
                     var10001 = false;
                     break label229;
                  }
               } else {
                  var4 = null;
               }

               int var3;
               label223: {
                  try {
                     var5.writeStrongBinder(var4);
                     if (this.mRemote.transact(1, var5, var6, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     var3 = Stub.getDefaultImpl().registerShareIntCallback(var1, var2);
                  } catch (Throwable var26) {
                     var10000 = var26;
                     var10001 = false;
                     break label229;
                  }

                  var6.recycle();
                  var5.recycle();
                  return var3;
               }

               try {
                  var6.readException();
                  var3 = var6.readInt();
               } catch (Throwable var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label229;
               }

               var6.recycle();
               var5.recycle();
               return var3;
            }

            Throwable var27 = var10000;
            var6.recycle();
            var5.recycle();
            throw var27;
         }

         public String registerShareStringCallback(String var1, IShareStringCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var5.writeString(var1);
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
                     if (this.mRemote.transact(6, var5, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     var1 = Stub.getDefaultImpl().registerShareStringCallback(var1, var2);
                  } catch (Throwable var25) {
                     var10000 = var25;
                     var10001 = false;
                     break label229;
                  }

                  var4.recycle();
                  var5.recycle();
                  return var1;
               }

               try {
                  var4.readException();
                  var1 = var4.readString();
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label229;
               }

               var4.recycle();
               var5.recycle();
               return var1;
            }

            Throwable var26 = var10000;
            var4.recycle();
            var5.recycle();
            throw var26;
         }

         public void reportBundle(String var1, Bundle var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label311: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var3.writeString(var1);
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label311;
               }

               if (var2 != null) {
                  try {
                     var3.writeInt(1);
                     var2.writeToParcel(var3, 0);
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
                     if (this.mRemote.transact(14, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label305;
                     }

                     Stub.getDefaultImpl().reportBundle(var1, var2);
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
                     var2.readFromParcel(var4);
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

         public void reportInt(String var1, int var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
               var3.writeString(var1);
               var3.writeInt(var2);
               if (!this.mRemote.transact(4, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().reportInt(var1, var2);
                  return;
               }

               var4.readException();
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void reportOtherBundle(String var1, Bundle var2, int var3) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label311: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var5.writeString(var1);
               } catch (Throwable var34) {
                  var10000 = var34;
                  var10001 = false;
                  break label311;
               }

               if (var2 != null) {
                  try {
                     var5.writeInt(1);
                     var2.writeToParcel(var5, 0);
                  } catch (Throwable var33) {
                     var10000 = var33;
                     var10001 = false;
                     break label311;
                  }
               } else {
                  try {
                     var5.writeInt(0);
                  } catch (Throwable var32) {
                     var10000 = var32;
                     var10001 = false;
                     break label311;
                  }
               }

               label305: {
                  try {
                     var5.writeInt(var3);
                     if (this.mRemote.transact(15, var5, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label305;
                     }

                     Stub.getDefaultImpl().reportOtherBundle(var1, var2, var3);
                  } catch (Throwable var35) {
                     var10000 = var35;
                     var10001 = false;
                     break label311;
                  }

                  var4.recycle();
                  var5.recycle();
                  return;
               }

               try {
                  var4.readException();
                  if (var4.readInt() != 0) {
                     var2.readFromParcel(var4);
                  }
               } catch (Throwable var31) {
                  var10000 = var31;
                  var10001 = false;
                  break label311;
               }

               var4.recycle();
               var5.recycle();
               return;
            }

            Throwable var36 = var10000;
            var4.recycle();
            var5.recycle();
            throw var36;
         }

         public void reportOtherInt(String var1, int var2, int var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
               var4.writeString(var1);
               var4.writeInt(var2);
               var4.writeInt(var3);
               if (this.mRemote.transact(5, var4, var5, 0) || Stub.getDefaultImpl() == null) {
                  var5.readException();
                  return;
               }

               Stub.getDefaultImpl().reportOtherInt(var1, var2, var3);
            } finally {
               var5.recycle();
               var4.recycle();
            }

         }

         public void reportOtherString(String var1, String var2, int var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
               var4.writeString(var1);
               var4.writeString(var2);
               var4.writeInt(var3);
               if (!this.mRemote.transact(10, var4, var5, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().reportOtherString(var1, var2, var3);
                  return;
               }

               var5.readException();
            } finally {
               var5.recycle();
               var4.recycle();
            }

         }

         public void reportString(String var1, String var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
               var4.writeString(var1);
               var4.writeString(var2);
               if (!this.mRemote.transact(9, var4, var3, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().reportString(var1, var2);
                  return;
               }

               var3.readException();
            } finally {
               var3.recycle();
               var4.recycle();
            }

         }

         public void unregisterShareBundleCallback(String var1, IShareBundleCallback var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var4.writeString(var1);
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
                     if (this.mRemote.transact(12, var4, var5, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().unregisterShareBundleCallback(var1, var2);
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

         public void unregisterShareIntCallback(String var1, IShareIntCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var5.writeString(var1);
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
                     if (this.mRemote.transact(2, var5, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().unregisterShareIntCallback(var1, var2);
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

         public void unregisterShareStringCallback(String var1, IShareStringCallback var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.share.aidl.IShareDataService");
                  var4.writeString(var1);
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
                     if (this.mRemote.transact(7, var4, var5, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().unregisterShareStringCallback(var1, var2);
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
