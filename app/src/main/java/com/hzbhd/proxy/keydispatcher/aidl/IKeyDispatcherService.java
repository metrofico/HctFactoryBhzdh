package com.hzbhd.proxy.keydispatcher.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IKeyDispatcherService extends IInterface {
   boolean registerKeyDispatcherCallback(int var1, IKeyDispatcherCallback var2) throws RemoteException;

   boolean registerThirdApp(int var1) throws RemoteException;

   void setKeyEvent(int var1, int var2, int var3, Bundle var4) throws RemoteException;

   boolean unregisterKeyDispatcherCallback(int var1, IKeyDispatcherCallback var2) throws RemoteException;

   boolean unregisterThirdApp(int var1) throws RemoteException;

   public static class Default implements IKeyDispatcherService {
      public IBinder asBinder() {
         return null;
      }

      public boolean registerKeyDispatcherCallback(int var1, IKeyDispatcherCallback var2) throws RemoteException {
         return false;
      }

      public boolean registerThirdApp(int var1) throws RemoteException {
         return false;
      }

      public void setKeyEvent(int var1, int var2, int var3, Bundle var4) throws RemoteException {
      }

      public boolean unregisterKeyDispatcherCallback(int var1, IKeyDispatcherCallback var2) throws RemoteException {
         return false;
      }

      public boolean unregisterThirdApp(int var1) throws RemoteException {
         return false;
      }
   }

   public abstract static class Stub extends Binder implements IKeyDispatcherService {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService";
      static final int TRANSACTION_registerKeyDispatcherCallback = 1;
      static final int TRANSACTION_registerThirdApp = 3;
      static final int TRANSACTION_setKeyEvent = 5;
      static final int TRANSACTION_unregisterKeyDispatcherCallback = 2;
      static final int TRANSACTION_unregisterThirdApp = 4;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
      }

      public static IKeyDispatcherService asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
            return (IKeyDispatcherService)(var1 != null && var1 instanceof IKeyDispatcherService ? (IKeyDispatcherService)var1 : new Proxy(var0));
         }
      }

      public static IKeyDispatcherService getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IKeyDispatcherService var0) {
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
         byte var6;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 1598968902) {
                           return super.onTransact(var1, var2, var3, var4);
                        } else {
                           var3.writeString("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
                           return true;
                        }
                     } else {
                        var2.enforceInterface("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
                        var1 = var2.readInt();
                        var4 = var2.readInt();
                        int var5 = var2.readInt();
                        Bundle var7;
                        if (var2.readInt() != 0) {
                           var7 = (Bundle)Bundle.CREATOR.createFromParcel(var2);
                        } else {
                           var7 = null;
                        }

                        this.setKeyEvent(var1, var4, var5, var7);
                        var3.writeNoException();
                        if (var7 != null) {
                           var3.writeInt(1);
                           var7.writeToParcel(var3, 1);
                        } else {
                           var3.writeInt(0);
                        }

                        return true;
                     }
                  } else {
                     var2.enforceInterface("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
                     var6 = this.unregisterThirdApp(var2.readInt());
                     var3.writeNoException();
                     var3.writeInt(var6);
                     return true;
                  }
               } else {
                  var2.enforceInterface("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
                  var6 = this.registerThirdApp(var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var6);
                  return true;
               }
            } else {
               var2.enforceInterface("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
               var6 = this.unregisterKeyDispatcherCallback(var2.readInt(), IKeyDispatcherCallback.Stub.asInterface(var2.readStrongBinder()));
               var3.writeNoException();
               var3.writeInt(var6);
               return true;
            }
         } else {
            var2.enforceInterface("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
            var6 = this.registerKeyDispatcherCallback(var2.readInt(), IKeyDispatcherCallback.Stub.asInterface(var2.readStrongBinder()));
            var3.writeNoException();
            var3.writeInt(var6);
            return true;
         }
      }

      private static class Proxy implements IKeyDispatcherService {
         public static IKeyDispatcherService sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService";
         }

         public boolean registerKeyDispatcherCallback(int var1, IKeyDispatcherCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            Throwable var10000;
            label335: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
                  var5.writeInt(var1);
               } catch (Throwable var35) {
                  var10000 = var35;
                  var10001 = false;
                  break label335;
               }

               IBinder var4;
               if (var2 != null) {
                  try {
                     var4 = var2.asBinder();
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label335;
                  }
               } else {
                  var4 = null;
               }

               try {
                  var5.writeStrongBinder(var4);
                  var4 = this.mRemote;
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label335;
               }

               boolean var3 = false;

               label329: {
                  try {
                     if (var4.transact(1, var5, var6, 0) || Stub.getDefaultImpl() == null) {
                        break label329;
                     }

                     var3 = Stub.getDefaultImpl().registerKeyDispatcherCallback(var1, var2);
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label335;
                  }

                  var6.recycle();
                  var5.recycle();
                  return var3;
               }

               try {
                  var6.readException();
                  var1 = var6.readInt();
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label335;
               }

               if (var1 != 0) {
                  var3 = true;
               }

               var6.recycle();
               var5.recycle();
               return var3;
            }

            Throwable var37 = var10000;
            var6.recycle();
            var5.recycle();
            throw var37;
         }

         public boolean registerThirdApp(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
                  var3.writeInt(var1);
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(3, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().registerThirdApp(var1);
                        break label172;
                     }
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label171;
                  }

                  try {
                     var4.readException();
                     var1 = var4.readInt();
                  } catch (Throwable var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label171;
                  }

                  if (var1 != 0) {
                     var2 = true;
                  }

                  var4.recycle();
                  var3.recycle();
                  return var2;
               }

               var4.recycle();
               var3.recycle();
               return var2;
            }

            Throwable var18 = var10000;
            var4.recycle();
            var3.recycle();
            throw var18;
         }

         public void setKeyEvent(int var1, int var2, int var3, Bundle var4) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            Throwable var10000;
            label311: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
                  var5.writeInt(var1);
                  var5.writeInt(var2);
                  var5.writeInt(var3);
               } catch (Throwable var35) {
                  var10000 = var35;
                  var10001 = false;
                  break label311;
               }

               if (var4 != null) {
                  try {
                     var5.writeInt(1);
                     var4.writeToParcel(var5, 0);
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label311;
                  }
               } else {
                  try {
                     var5.writeInt(0);
                  } catch (Throwable var33) {
                     var10000 = var33;
                     var10001 = false;
                     break label311;
                  }
               }

               label305: {
                  try {
                     if (this.mRemote.transact(5, var5, var6, 0) || Stub.getDefaultImpl() == null) {
                        break label305;
                     }

                     Stub.getDefaultImpl().setKeyEvent(var1, var2, var3, var4);
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label311;
                  }

                  var6.recycle();
                  var5.recycle();
                  return;
               }

               try {
                  var6.readException();
                  if (var6.readInt() != 0) {
                     var4.readFromParcel(var6);
                  }
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label311;
               }

               var6.recycle();
               var5.recycle();
               return;
            }

            Throwable var37 = var10000;
            var6.recycle();
            var5.recycle();
            throw var37;
         }

         public boolean unregisterKeyDispatcherCallback(int var1, IKeyDispatcherCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            Throwable var10000;
            label335: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
                  var5.writeInt(var1);
               } catch (Throwable var35) {
                  var10000 = var35;
                  var10001 = false;
                  break label335;
               }

               IBinder var4;
               if (var2 != null) {
                  try {
                     var4 = var2.asBinder();
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label335;
                  }
               } else {
                  var4 = null;
               }

               try {
                  var5.writeStrongBinder(var4);
                  var4 = this.mRemote;
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label335;
               }

               boolean var3 = false;

               label329: {
                  try {
                     if (var4.transact(2, var5, var6, 0) || Stub.getDefaultImpl() == null) {
                        break label329;
                     }

                     var3 = Stub.getDefaultImpl().unregisterKeyDispatcherCallback(var1, var2);
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label335;
                  }

                  var6.recycle();
                  var5.recycle();
                  return var3;
               }

               try {
                  var6.readException();
                  var1 = var6.readInt();
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label335;
               }

               if (var1 != 0) {
                  var3 = true;
               }

               var6.recycle();
               var5.recycle();
               return var3;
            }

            Throwable var37 = var10000;
            var6.recycle();
            var5.recycle();
            throw var37;
         }

         public boolean unregisterThirdApp(int var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService");
                  var4.writeInt(var1);
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(4, var4, var3, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().unregisterThirdApp(var1);
                        break label172;
                     }
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label171;
                  }

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
      }
   }
}
