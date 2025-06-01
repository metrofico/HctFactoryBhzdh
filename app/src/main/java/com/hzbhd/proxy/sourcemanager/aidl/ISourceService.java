package com.hzbhd.proxy.sourcemanager.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISourceService extends IInterface {
   int getCurrentAudioSource(int var1) throws RemoteException;

   void notifyAppAudio(int var1, String var2, int var3, boolean var4) throws RemoteException;

   int registerAudioCallback(int var1, IAudioCallback var2) throws RemoteException;

   boolean registerSourceCallback(int var1, ISourceCallback var2) throws RemoteException;

   int releaseAudioChannel(int var1, int var2) throws RemoteException;

   boolean releaseBluetooth(int var1) throws RemoteException;

   int requestAudioChannel(int var1, int var2, Bundle var3) throws RemoteException;

   boolean requestBluetooth(int var1, ISourceBluetoothCallback var2) throws RemoteException;

   int unregisterAudioCallback(int var1, IAudioCallback var2) throws RemoteException;

   boolean unregisterSourceCallback(int var1) throws RemoteException;

   public static class Default implements ISourceService {
      public IBinder asBinder() {
         return null;
      }

      public int getCurrentAudioSource(int var1) throws RemoteException {
         return 0;
      }

      public void notifyAppAudio(int var1, String var2, int var3, boolean var4) throws RemoteException {
      }

      public int registerAudioCallback(int var1, IAudioCallback var2) throws RemoteException {
         return 0;
      }

      public boolean registerSourceCallback(int var1, ISourceCallback var2) throws RemoteException {
         return false;
      }

      public int releaseAudioChannel(int var1, int var2) throws RemoteException {
         return 0;
      }

      public boolean releaseBluetooth(int var1) throws RemoteException {
         return false;
      }

      public int requestAudioChannel(int var1, int var2, Bundle var3) throws RemoteException {
         return 0;
      }

      public boolean requestBluetooth(int var1, ISourceBluetoothCallback var2) throws RemoteException {
         return false;
      }

      public int unregisterAudioCallback(int var1, IAudioCallback var2) throws RemoteException {
         return 0;
      }

      public boolean unregisterSourceCallback(int var1) throws RemoteException {
         return false;
      }
   }

   public abstract static class Stub extends Binder implements ISourceService {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.sourcemanager.aidl.ISourceService";
      static final int TRANSACTION_getCurrentAudioSource = 6;
      static final int TRANSACTION_notifyAppAudio = 5;
      static final int TRANSACTION_registerAudioCallback = 9;
      static final int TRANSACTION_registerSourceCallback = 1;
      static final int TRANSACTION_releaseAudioChannel = 4;
      static final int TRANSACTION_releaseBluetooth = 8;
      static final int TRANSACTION_requestAudioChannel = 3;
      static final int TRANSACTION_requestBluetooth = 7;
      static final int TRANSACTION_unregisterAudioCallback = 10;
      static final int TRANSACTION_unregisterSourceCallback = 2;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
      }

      public static ISourceService asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
            return (ISourceService)(var1 != null && var1 instanceof ISourceService ? (ISourceService)var1 : new Proxy(var0));
         }
      }

      public static ISourceService getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ISourceService var0) {
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
            byte var7;
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var7 = this.registerSourceCallback(var2.readInt(), ISourceCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  var3.writeInt(var7);
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var7 = this.unregisterSourceCallback(var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var7);
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var4 = var2.readInt();
                  var1 = var2.readInt();
                  Bundle var8;
                  if (var2.readInt() != 0) {
                     var8 = (Bundle)Bundle.CREATOR.createFromParcel(var2);
                  } else {
                     var8 = null;
                  }

                  var1 = this.requestAudioChannel(var4, var1, var8);
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var1 = this.releaseAudioChannel(var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var4 = var2.readInt();
                  String var6 = var2.readString();
                  var1 = var2.readInt();
                  boolean var5;
                  if (var2.readInt() != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.notifyAppAudio(var4, var6, var1, var5);
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var1 = this.getCurrentAudioSource(var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 7:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var7 = this.requestBluetooth(var2.readInt(), ISourceBluetoothCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  var3.writeInt(var7);
                  return true;
               case 8:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var7 = this.releaseBluetooth(var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var7);
                  return true;
               case 9:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var1 = this.registerAudioCallback(var2.readInt(), IAudioCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 10:
                  var2.enforceInterface("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var1 = this.unregisterAudioCallback(var2.readInt(), IAudioCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
            return true;
         }
      }

      private static class Proxy implements ISourceService {
         public static ISourceService sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public int getCurrentAudioSource(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
               var3.writeInt(var1);
               if (this.mRemote.transact(6, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  var1 = var2.readInt();
                  return var1;
               }

               var1 = Stub.getDefaultImpl().getCurrentAudioSource(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

            return var1;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.sourcemanager.aidl.ISourceService";
         }

         public void notifyAppAudio(int var1, String var2, int var3, boolean var4) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var7 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var6.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var6.writeInt(var1);
                  var6.writeString(var2);
                  var6.writeInt(var3);
               } catch (Throwable var18) {
                  var10000 = var18;
                  var10001 = false;
                  break label167;
               }

               byte var5;
               if (var4) {
                  var5 = 1;
               } else {
                  var5 = 0;
               }

               label161: {
                  try {
                     var6.writeInt(var5);
                     if (this.mRemote.transact(5, var6, var7, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().notifyAppAudio(var1, var2, var3, var4);
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

            Throwable var20 = var10000;
            var7.recycle();
            var6.recycle();
            throw var20;
         }

         public int registerAudioCallback(int var1, IAudioCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
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
                     if (this.mRemote.transact(9, var5, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     var1 = Stub.getDefaultImpl().registerAudioCallback(var1, var2);
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
                  var1 = var4.readInt();
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

         public boolean registerSourceCallback(int var1, ISourceCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            Throwable var10000;
            label335: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
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

                     var3 = Stub.getDefaultImpl().registerSourceCallback(var1, var2);
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

         public int releaseAudioChannel(int var1, int var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var5.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
               var5.writeInt(var1);
               var5.writeInt(var2);
               if (this.mRemote.transact(4, var5, var4, 0) || Stub.getDefaultImpl() == null) {
                  var4.readException();
                  var1 = var4.readInt();
                  return var1;
               }

               var1 = Stub.getDefaultImpl().releaseAudioChannel(var1, var2);
            } finally {
               var4.recycle();
               var5.recycle();
            }

            return var1;
         }

         public boolean releaseBluetooth(int var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
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
                     if (!var5.transact(8, var4, var3, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().releaseBluetooth(var1);
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

         public int requestAudioChannel(int var1, int var2, Bundle var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label287: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var4.writeInt(var1);
                  var4.writeInt(var2);
               } catch (Throwable var34) {
                  var10000 = var34;
                  var10001 = false;
                  break label287;
               }

               if (var3 != null) {
                  try {
                     var4.writeInt(1);
                     var3.writeToParcel(var4, 0);
                  } catch (Throwable var33) {
                     var10000 = var33;
                     var10001 = false;
                     break label287;
                  }
               } else {
                  try {
                     var4.writeInt(0);
                  } catch (Throwable var32) {
                     var10000 = var32;
                     var10001 = false;
                     break label287;
                  }
               }

               label281: {
                  try {
                     if (this.mRemote.transact(3, var4, var5, 0) || Stub.getDefaultImpl() == null) {
                        break label281;
                     }

                     var1 = Stub.getDefaultImpl().requestAudioChannel(var1, var2, var3);
                  } catch (Throwable var35) {
                     var10000 = var35;
                     var10001 = false;
                     break label287;
                  }

                  var5.recycle();
                  var4.recycle();
                  return var1;
               }

               try {
                  var5.readException();
                  var1 = var5.readInt();
               } catch (Throwable var31) {
                  var10000 = var31;
                  var10001 = false;
                  break label287;
               }

               var5.recycle();
               var4.recycle();
               return var1;
            }

            Throwable var36 = var10000;
            var5.recycle();
            var4.recycle();
            throw var36;
         }

         public boolean requestBluetooth(int var1, ISourceBluetoothCallback var2) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label335: {
               boolean var10001;
               try {
                  var6.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
                  var6.writeInt(var1);
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
                  var6.writeStrongBinder(var4);
                  var4 = this.mRemote;
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label335;
               }

               boolean var3 = false;

               label329: {
                  try {
                     if (var4.transact(7, var6, var5, 0) || Stub.getDefaultImpl() == null) {
                        break label329;
                     }

                     var3 = Stub.getDefaultImpl().requestBluetooth(var1, var2);
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label335;
                  }

                  var5.recycle();
                  var6.recycle();
                  return var3;
               }

               try {
                  var5.readException();
                  var1 = var5.readInt();
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label335;
               }

               if (var1 != 0) {
                  var3 = true;
               }

               var5.recycle();
               var6.recycle();
               return var3;
            }

            Throwable var37 = var10000;
            var5.recycle();
            var6.recycle();
            throw var37;
         }

         public int unregisterAudioCallback(int var1, IAudioCallback var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
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
                     if (this.mRemote.transact(10, var4, var5, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     var1 = Stub.getDefaultImpl().unregisterAudioCallback(var1, var2);
                  } catch (Throwable var25) {
                     var10000 = var25;
                     var10001 = false;
                     break label229;
                  }

                  var5.recycle();
                  var4.recycle();
                  return var1;
               }

               try {
                  var5.readException();
                  var1 = var5.readInt();
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label229;
               }

               var5.recycle();
               var4.recycle();
               return var1;
            }

            Throwable var26 = var10000;
            var5.recycle();
            var4.recycle();
            throw var26;
         }

         public boolean unregisterSourceCallback(int var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.sourcemanager.aidl.ISourceService");
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
                     if (!var5.transact(2, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().unregisterSourceCallback(var1);
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
      }
   }
}
