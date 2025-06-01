package com.hzbhd.proxy.camera.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

public interface ICameraManager extends IInterface {
   void addCallBack(int var1, ICameraCallback var2) throws RemoteException;

   String getCameraInfo(int var1, int var2) throws RemoteException;

   int getFlagAttr(int var1, int var2) throws RemoteException;

   void removeCallBack(int var1, ICameraCallback var2) throws RemoteException;

   void setCameraInfo(int var1, int var2, String var3) throws RemoteException;

   void setFlagAttr(int var1, int var2, int var3) throws RemoteException;

   void startPreview(int var1, Surface var2) throws RemoteException;

   void startPreviewWithCallBack(int var1, Surface var2, ICameraCallback var3) throws RemoteException;

   void startRecord(int var1, Surface var2) throws RemoteException;

   void stopPreview(int var1) throws RemoteException;

   void stopRecord(int var1) throws RemoteException;

   public static class Default implements ICameraManager {
      public void addCallBack(int var1, ICameraCallback var2) throws RemoteException {
      }

      public IBinder asBinder() {
         return null;
      }

      public String getCameraInfo(int var1, int var2) throws RemoteException {
         return null;
      }

      public int getFlagAttr(int var1, int var2) throws RemoteException {
         return 0;
      }

      public void removeCallBack(int var1, ICameraCallback var2) throws RemoteException {
      }

      public void setCameraInfo(int var1, int var2, String var3) throws RemoteException {
      }

      public void setFlagAttr(int var1, int var2, int var3) throws RemoteException {
      }

      public void startPreview(int var1, Surface var2) throws RemoteException {
      }

      public void startPreviewWithCallBack(int var1, Surface var2, ICameraCallback var3) throws RemoteException {
      }

      public void startRecord(int var1, Surface var2) throws RemoteException {
      }

      public void stopPreview(int var1) throws RemoteException {
      }

      public void stopRecord(int var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements ICameraManager {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.camera.aidl.ICameraManager";
      static final int TRANSACTION_addCallBack = 5;
      static final int TRANSACTION_getCameraInfo = 10;
      static final int TRANSACTION_getFlagAttr = 4;
      static final int TRANSACTION_removeCallBack = 6;
      static final int TRANSACTION_setCameraInfo = 9;
      static final int TRANSACTION_setFlagAttr = 3;
      static final int TRANSACTION_startPreview = 1;
      static final int TRANSACTION_startPreviewWithCallBack = 11;
      static final int TRANSACTION_startRecord = 7;
      static final int TRANSACTION_stopPreview = 2;
      static final int TRANSACTION_stopRecord = 8;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.camera.aidl.ICameraManager");
      }

      public static ICameraManager asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
            return (ICameraManager)(var1 != null && var1 instanceof ICameraManager ? (ICameraManager)var1 : new Proxy(var0));
         }
      }

      public static ICameraManager getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(ICameraManager var0) {
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
            Object var6 = null;
            Surface var5 = null;
            Object var7 = null;
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  var1 = var2.readInt();
                  if (var2.readInt() != 0) {
                     var5 = (Surface)Surface.CREATOR.createFromParcel(var2);
                  }

                  this.startPreview(var1, var5);
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  this.stopPreview(var2.readInt());
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  this.setFlagAttr(var2.readInt(), var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  var1 = this.getFlagAttr(var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  this.addCallBack(var2.readInt(), ICameraCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  this.removeCallBack(var2.readInt(), ICameraCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 7:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  var1 = var2.readInt();
                  var5 = (Surface)var6;
                  if (var2.readInt() != 0) {
                     var5 = (Surface)Surface.CREATOR.createFromParcel(var2);
                  }

                  this.startRecord(var1, var5);
                  var3.writeNoException();
                  return true;
               case 8:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  this.stopRecord(var2.readInt());
                  var3.writeNoException();
                  return true;
               case 9:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  this.setCameraInfo(var2.readInt(), var2.readInt(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 10:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  String var8 = this.getCameraInfo(var2.readInt(), var2.readInt());
                  var3.writeNoException();
                  var3.writeString(var8);
                  return true;
               case 11:
                  var2.enforceInterface("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  var1 = var2.readInt();
                  var5 = (Surface)var7;
                  if (var2.readInt() != 0) {
                     var5 = (Surface)Surface.CREATOR.createFromParcel(var2);
                  }

                  this.startPreviewWithCallBack(var1, var5, ICameraCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.proxy.camera.aidl.ICameraManager");
            return true;
         }
      }

      private static class Proxy implements ICameraManager {
         public static ICameraManager sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public void addCallBack(int var1, ICameraCallback var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
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
                     if (this.mRemote.transact(5, var4, var5, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().addCallBack(var1, var2);
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

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getCameraInfo(int var1, int var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            String var5;
            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
               var3.writeInt(var1);
               var3.writeInt(var2);
               if (!this.mRemote.transact(10, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                  var5 = Stub.getDefaultImpl().getCameraInfo(var1, var2);
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

         public int getFlagAttr(int var1, int var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
               var4.writeInt(var1);
               var4.writeInt(var2);
               if (this.mRemote.transact(4, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  var1 = var3.readInt();
                  return var1;
               }

               var1 = Stub.getDefaultImpl().getFlagAttr(var1, var2);
            } finally {
               var3.recycle();
               var4.recycle();
            }

            return var1;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.camera.aidl.ICameraManager";
         }

         public void removeCallBack(int var1, ICameraCallback var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
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
                     if (this.mRemote.transact(6, var5, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().removeCallBack(var1, var2);
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

         public void setCameraInfo(int var1, int var2, String var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
               var4.writeInt(var1);
               var4.writeInt(var2);
               var4.writeString(var3);
               if (!this.mRemote.transact(9, var4, var5, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().setCameraInfo(var1, var2, var3);
                  return;
               }

               var5.readException();
            } finally {
               var5.recycle();
               var4.recycle();
            }

         }

         public void setFlagAttr(int var1, int var2, int var3) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var6 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
               var4.writeInt(var1);
               var4.writeInt(var2);
               var4.writeInt(var3);
               if (this.mRemote.transact(3, var4, var6, 0) || Stub.getDefaultImpl() == null) {
                  var6.readException();
                  return;
               }

               Stub.getDefaultImpl().setFlagAttr(var1, var2, var3);
            } finally {
               var6.recycle();
               var4.recycle();
            }

         }

         public void startPreview(int var1, Surface var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label287: {
               boolean var10001;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  var4.writeInt(var1);
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label287;
               }

               if (var2 != null) {
                  try {
                     var4.writeInt(1);
                     var2.writeToParcel(var4, 0);
                  } catch (Throwable var32) {
                     var10000 = var32;
                     var10001 = false;
                     break label287;
                  }
               } else {
                  try {
                     var4.writeInt(0);
                  } catch (Throwable var31) {
                     var10000 = var31;
                     var10001 = false;
                     break label287;
                  }
               }

               label281: {
                  try {
                     if (this.mRemote.transact(1, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                        break label281;
                     }

                     Stub.getDefaultImpl().startPreview(var1, var2);
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label287;
                  }

                  var3.recycle();
                  var4.recycle();
                  return;
               }

               try {
                  var3.readException();
               } catch (Throwable var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label287;
               }

               var3.recycle();
               var4.recycle();
               return;
            }

            Throwable var35 = var10000;
            var3.recycle();
            var4.recycle();
            throw var35;
         }

         public void startPreviewWithCallBack(int var1, Surface var2, ICameraCallback var3) throws RemoteException {
            Parcel var6 = Parcel.obtain();
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label385: {
               boolean var10001;
               try {
                  var6.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  var6.writeInt(var1);
               } catch (Throwable var47) {
                  var10000 = var47;
                  var10001 = false;
                  break label385;
               }

               if (var2 != null) {
                  try {
                     var6.writeInt(1);
                     var2.writeToParcel(var6, 0);
                  } catch (Throwable var46) {
                     var10000 = var46;
                     var10001 = false;
                     break label385;
                  }
               } else {
                  try {
                     var6.writeInt(0);
                  } catch (Throwable var45) {
                     var10000 = var45;
                     var10001 = false;
                     break label385;
                  }
               }

               IBinder var4;
               if (var3 != null) {
                  try {
                     var4 = var3.asBinder();
                  } catch (Throwable var44) {
                     var10000 = var44;
                     var10001 = false;
                     break label385;
                  }
               } else {
                  var4 = null;
               }

               label379: {
                  try {
                     var6.writeStrongBinder(var4);
                     if (this.mRemote.transact(11, var6, var5, 0) || Stub.getDefaultImpl() == null) {
                        break label379;
                     }

                     Stub.getDefaultImpl().startPreviewWithCallBack(var1, var2, var3);
                  } catch (Throwable var48) {
                     var10000 = var48;
                     var10001 = false;
                     break label385;
                  }

                  var5.recycle();
                  var6.recycle();
                  return;
               }

               try {
                  var5.readException();
               } catch (Throwable var43) {
                  var10000 = var43;
                  var10001 = false;
                  break label385;
               }

               var5.recycle();
               var6.recycle();
               return;
            }

            Throwable var49 = var10000;
            var5.recycle();
            var6.recycle();
            throw var49;
         }

         public void startRecord(int var1, Surface var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label287: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
                  var3.writeInt(var1);
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label287;
               }

               if (var2 != null) {
                  try {
                     var3.writeInt(1);
                     var2.writeToParcel(var3, 0);
                  } catch (Throwable var32) {
                     var10000 = var32;
                     var10001 = false;
                     break label287;
                  }
               } else {
                  try {
                     var3.writeInt(0);
                  } catch (Throwable var31) {
                     var10000 = var31;
                     var10001 = false;
                     break label287;
                  }
               }

               label281: {
                  try {
                     if (this.mRemote.transact(7, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label281;
                     }

                     Stub.getDefaultImpl().startRecord(var1, var2);
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label287;
                  }

                  var4.recycle();
                  var3.recycle();
                  return;
               }

               try {
                  var4.readException();
               } catch (Throwable var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label287;
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

         public void stopPreview(int var1) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
               var4.writeInt(var1);
               if (this.mRemote.transact(2, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().stopPreview(var1);
            } finally {
               var3.recycle();
               var4.recycle();
            }

         }

         public void stopRecord(int var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.camera.aidl.ICameraManager");
               var2.writeInt(var1);
               if (!this.mRemote.transact(8, var2, var3, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().stopRecord(var1);
                  return;
               }

               var3.readException();
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }
      }
   }
}
