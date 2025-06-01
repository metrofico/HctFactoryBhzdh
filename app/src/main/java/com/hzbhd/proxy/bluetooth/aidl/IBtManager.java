package com.hzbhd.proxy.bluetooth.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface IBtManager extends IInterface {
   void addBtCallBack(IBtCallback var1) throws RemoteException;

   void answer(String var1) throws RemoteException;

   void call(String var1) throws RemoteException;

   boolean enableWechatFilter() throws RemoteException;

   List getCall() throws RemoteException;

   int getCallDevice() throws RemoteException;

   String getCallNum() throws RemoteException;

   List getConHfp() throws RemoteException;

   String getCurrA2dpAddress() throws RemoteException;

   String getCurrHfpAddress() throws RemoteException;

   String getLocalAddress() throws RemoteException;

   String getMusicAlbum() throws RemoteException;

   String getMusicArtist() throws RemoteException;

   String getMusicTitle() throws RemoteException;

   String getName() throws RemoteException;

   boolean getPairMode() throws RemoteException;

   List getPairedHfp() throws RemoteException;

   String getPinCode() throws RemoteException;

   String getState() throws RemoteException;

   String getWechatCall() throws RemoteException;

   void handup(String var1) throws RemoteException;

   boolean isAutoAnswer() throws RemoteException;

   boolean isAutoConn() throws RemoteException;

   boolean isMicMute() throws RemoteException;

   boolean isMicOut() throws RemoteException;

   boolean isWechatFilter() throws RemoteException;

   void removeBtCallBack(IBtCallback var1) throws RemoteException;

   void sendAction(String var1) throws RemoteException;

   void sendCallKey(String var1, String var2) throws RemoteException;

   void sendDeviceAction(String var1, String var2) throws RemoteException;

   void sendKey(String var1) throws RemoteException;

   void setName(String var1) throws RemoteException;

   void setPinCode(String var1) throws RemoteException;

   void setWechatFilter(boolean var1) throws RemoteException;

   public static class Default implements IBtManager {
      public void addBtCallBack(IBtCallback var1) throws RemoteException {
      }

      public void answer(String var1) throws RemoteException {
      }

      public IBinder asBinder() {
         return null;
      }

      public void call(String var1) throws RemoteException {
      }

      public boolean enableWechatFilter() throws RemoteException {
         return false;
      }

      public List getCall() throws RemoteException {
         return null;
      }

      public int getCallDevice() throws RemoteException {
         return 0;
      }

      public String getCallNum() throws RemoteException {
         return null;
      }

      public List getConHfp() throws RemoteException {
         return null;
      }

      public String getCurrA2dpAddress() throws RemoteException {
         return null;
      }

      public String getCurrHfpAddress() throws RemoteException {
         return null;
      }

      public String getLocalAddress() throws RemoteException {
         return null;
      }

      public String getMusicAlbum() throws RemoteException {
         return null;
      }

      public String getMusicArtist() throws RemoteException {
         return null;
      }

      public String getMusicTitle() throws RemoteException {
         return null;
      }

      public String getName() throws RemoteException {
         return null;
      }

      public boolean getPairMode() throws RemoteException {
         return false;
      }

      public List getPairedHfp() throws RemoteException {
         return null;
      }

      public String getPinCode() throws RemoteException {
         return null;
      }

      public String getState() throws RemoteException {
         return null;
      }

      public String getWechatCall() throws RemoteException {
         return null;
      }

      public void handup(String var1) throws RemoteException {
      }

      public boolean isAutoAnswer() throws RemoteException {
         return false;
      }

      public boolean isAutoConn() throws RemoteException {
         return false;
      }

      public boolean isMicMute() throws RemoteException {
         return false;
      }

      public boolean isMicOut() throws RemoteException {
         return false;
      }

      public boolean isWechatFilter() throws RemoteException {
         return false;
      }

      public void removeBtCallBack(IBtCallback var1) throws RemoteException {
      }

      public void sendAction(String var1) throws RemoteException {
      }

      public void sendCallKey(String var1, String var2) throws RemoteException {
      }

      public void sendDeviceAction(String var1, String var2) throws RemoteException {
      }

      public void sendKey(String var1) throws RemoteException {
      }

      public void setName(String var1) throws RemoteException {
      }

      public void setPinCode(String var1) throws RemoteException {
      }

      public void setWechatFilter(boolean var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IBtManager {
      private static final String DESCRIPTOR = "com.hzbhd.proxy.bluetooth.aidl.IBtManager";
      static final int TRANSACTION_addBtCallBack = 1;
      static final int TRANSACTION_answer = 30;
      static final int TRANSACTION_call = 5;
      static final int TRANSACTION_enableWechatFilter = 26;
      static final int TRANSACTION_getCall = 29;
      static final int TRANSACTION_getCallDevice = 22;
      static final int TRANSACTION_getCallNum = 19;
      static final int TRANSACTION_getConHfp = 12;
      static final int TRANSACTION_getCurrA2dpAddress = 15;
      static final int TRANSACTION_getCurrHfpAddress = 14;
      static final int TRANSACTION_getLocalAddress = 13;
      static final int TRANSACTION_getMusicAlbum = 17;
      static final int TRANSACTION_getMusicArtist = 18;
      static final int TRANSACTION_getMusicTitle = 16;
      static final int TRANSACTION_getName = 10;
      static final int TRANSACTION_getPairMode = 23;
      static final int TRANSACTION_getPairedHfp = 11;
      static final int TRANSACTION_getPinCode = 25;
      static final int TRANSACTION_getState = 8;
      static final int TRANSACTION_getWechatCall = 33;
      static final int TRANSACTION_handup = 31;
      static final int TRANSACTION_isAutoAnswer = 34;
      static final int TRANSACTION_isAutoConn = 9;
      static final int TRANSACTION_isMicMute = 21;
      static final int TRANSACTION_isMicOut = 20;
      static final int TRANSACTION_isWechatFilter = 27;
      static final int TRANSACTION_removeBtCallBack = 2;
      static final int TRANSACTION_sendAction = 3;
      static final int TRANSACTION_sendCallKey = 32;
      static final int TRANSACTION_sendDeviceAction = 4;
      static final int TRANSACTION_sendKey = 6;
      static final int TRANSACTION_setName = 7;
      static final int TRANSACTION_setPinCode = 24;
      static final int TRANSACTION_setWechatFilter = 28;

      public Stub() {
         this.attachInterface(this, "com.hzbhd.proxy.bluetooth.aidl.IBtManager");
      }

      public static IBtManager asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
            return (IBtManager)(var1 != null && var1 instanceof IBtManager ? (IBtManager)var1 : new Proxy(var0));
         }
      }

      public static IBtManager getDefaultImpl() {
         return Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IBtManager var0) {
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
            byte var6;
            String var7;
            List var8;
            switch (var1) {
               case 1:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.addBtCallBack(IBtCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 2:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.removeBtCallBack(IBtCallback.Stub.asInterface(var2.readStrongBinder()));
                  var3.writeNoException();
                  return true;
               case 3:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.sendAction(var2.readString());
                  var3.writeNoException();
                  return true;
               case 4:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.sendDeviceAction(var2.readString(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 5:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.call(var2.readString());
                  var3.writeNoException();
                  return true;
               case 6:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.sendKey(var2.readString());
                  var3.writeNoException();
                  return true;
               case 7:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.setName(var2.readString());
                  var3.writeNoException();
                  return true;
               case 8:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getState();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 9:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var6 = this.isAutoConn();
                  var3.writeNoException();
                  var3.writeInt(var6);
                  return true;
               case 10:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getName();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 11:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var8 = this.getPairedHfp();
                  var3.writeNoException();
                  var3.writeStringList(var8);
                  return true;
               case 12:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var8 = this.getConHfp();
                  var3.writeNoException();
                  var3.writeStringList(var8);
                  return true;
               case 13:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getLocalAddress();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 14:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getCurrHfpAddress();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 15:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getCurrA2dpAddress();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 16:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getMusicTitle();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 17:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getMusicAlbum();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 18:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getMusicArtist();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 19:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getCallNum();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 20:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var6 = this.isMicOut();
                  var3.writeNoException();
                  var3.writeInt(var6);
                  return true;
               case 21:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var6 = this.isMicMute();
                  var3.writeNoException();
                  var3.writeInt(var6);
                  return true;
               case 22:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var1 = this.getCallDevice();
                  var3.writeNoException();
                  var3.writeInt(var1);
                  return true;
               case 23:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var6 = this.getPairMode();
                  var3.writeNoException();
                  var3.writeInt(var6);
                  return true;
               case 24:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.setPinCode(var2.readString());
                  var3.writeNoException();
                  return true;
               case 25:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getPinCode();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 26:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var6 = this.enableWechatFilter();
                  var3.writeNoException();
                  var3.writeInt(var6);
                  return true;
               case 27:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var6 = this.isWechatFilter();
                  var3.writeNoException();
                  var3.writeInt(var6);
                  return true;
               case 28:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  boolean var5;
                  if (var2.readInt() != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.setWechatFilter(var5);
                  var3.writeNoException();
                  return true;
               case 29:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var8 = this.getCall();
                  var3.writeNoException();
                  var3.writeStringList(var8);
                  return true;
               case 30:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.answer(var2.readString());
                  var3.writeNoException();
                  return true;
               case 31:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.handup(var2.readString());
                  var3.writeNoException();
                  return true;
               case 32:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  this.sendCallKey(var2.readString(), var2.readString());
                  var3.writeNoException();
                  return true;
               case 33:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var7 = this.getWechatCall();
                  var3.writeNoException();
                  var3.writeString(var7);
                  return true;
               case 34:
                  var2.enforceInterface("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var6 = this.isAutoAnswer();
                  var3.writeNoException();
                  var3.writeInt(var6);
                  return true;
               default:
                  return super.onTransact(var1, var2, var3, var4);
            }
         } else {
            var3.writeString("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
            return true;
         }
      }

      private static class Proxy implements IBtManager {
         public static IBtManager sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public void addBtCallBack(IBtCallback var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
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
                     if (this.mRemote.transact(1, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label223;
                     }

                     Stub.getDefaultImpl().addBtCallBack(var1);
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

         public void answer(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               var3.writeString(var1);
               if (this.mRemote.transact(30, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().answer(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public void call(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               var3.writeString(var1);
               if (!this.mRemote.transact(5, var3, var2, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().call(var1);
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public boolean enableWechatFilter() throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(26, var4, var3, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().enableWechatFilter();
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

         public List getCall() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            ArrayList var3;
            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(29, var1, var2, 0) && Stub.getDefaultImpl() != null) {
                  List var6 = Stub.getDefaultImpl().getCall();
                  return var6;
               }

               var2.readException();
               var3 = var2.createStringArrayList();
            } finally {
               var2.recycle();
               var1.recycle();
            }

            return var3;
         }

         public int getCallDevice() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            int var1;
            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(22, var2, var4, 0) && Stub.getDefaultImpl() != null) {
                  var1 = Stub.getDefaultImpl().getCallDevice();
                  return var1;
               }

               var4.readException();
               var1 = var4.readInt();
            } finally {
               var4.recycle();
               var2.recycle();
            }

            return var1;
         }

         public String getCallNum() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            String var3;
            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(19, var1, var2, 0) && Stub.getDefaultImpl() != null) {
                  var3 = Stub.getDefaultImpl().getCallNum();
                  return var3;
               }

               var2.readException();
               var3 = var2.readString();
            } finally {
               var2.recycle();
               var1.recycle();
            }

            return var3;
         }

         public List getConHfp() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var1 = Parcel.obtain();

            ArrayList var3;
            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(12, var2, var1, 0) && Stub.getDefaultImpl() != null) {
                  List var6 = Stub.getDefaultImpl().getConHfp();
                  return var6;
               }

               var1.readException();
               var3 = var1.createStringArrayList();
            } finally {
               var1.recycle();
               var2.recycle();
            }

            return var3;
         }

         public String getCurrA2dpAddress() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            String var3;
            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (this.mRemote.transact(15, var1, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  var3 = var2.readString();
                  return var3;
               }

               var3 = Stub.getDefaultImpl().getCurrA2dpAddress();
            } finally {
               var2.recycle();
               var1.recycle();
            }

            return var3;
         }

         public String getCurrHfpAddress() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            String var3;
            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (this.mRemote.transact(14, var1, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  var3 = var2.readString();
                  return var3;
               }

               var3 = Stub.getDefaultImpl().getCurrHfpAddress();
            } finally {
               var2.recycle();
               var1.recycle();
            }

            return var3;
         }

         public String getInterfaceDescriptor() {
            return "com.hzbhd.proxy.bluetooth.aidl.IBtManager";
         }

         public String getLocalAddress() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            String var3;
            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(13, var1, var2, 0) && Stub.getDefaultImpl() != null) {
                  var3 = Stub.getDefaultImpl().getLocalAddress();
                  return var3;
               }

               var2.readException();
               var3 = var2.readString();
            } finally {
               var2.recycle();
               var1.recycle();
            }

            return var3;
         }

         public String getMusicAlbum() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            String var3;
            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (this.mRemote.transact(17, var1, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  var3 = var2.readString();
                  return var3;
               }

               var3 = Stub.getDefaultImpl().getMusicAlbum();
            } finally {
               var2.recycle();
               var1.recycle();
            }

            return var3;
         }

         public String getMusicArtist() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var1 = Parcel.obtain();

            String var3;
            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(18, var2, var1, 0) && Stub.getDefaultImpl() != null) {
                  var3 = Stub.getDefaultImpl().getMusicArtist();
                  return var3;
               }

               var1.readException();
               var3 = var1.readString();
            } finally {
               var1.recycle();
               var2.recycle();
            }

            return var3;
         }

         public String getMusicTitle() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            String var3;
            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(16, var1, var2, 0) && Stub.getDefaultImpl() != null) {
                  var3 = Stub.getDefaultImpl().getMusicTitle();
                  return var3;
               }

               var2.readException();
               var3 = var2.readString();
            } finally {
               var2.recycle();
               var1.recycle();
            }

            return var3;
         }

         public String getName() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var1 = Parcel.obtain();

            String var3;
            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (this.mRemote.transact(10, var2, var1, 0) || Stub.getDefaultImpl() == null) {
                  var1.readException();
                  var3 = var1.readString();
                  return var3;
               }

               var3 = Stub.getDefaultImpl().getName();
            } finally {
               var1.recycle();
               var2.recycle();
            }

            return var3;
         }

         public boolean getPairMode() throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(23, var4, var3, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().getPairMode();
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

         public List getPairedHfp() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            ArrayList var3;
            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(11, var1, var2, 0) && Stub.getDefaultImpl() != null) {
                  List var6 = Stub.getDefaultImpl().getPairedHfp();
                  return var6;
               }

               var2.readException();
               var3 = var2.createStringArrayList();
            } finally {
               var2.recycle();
               var1.recycle();
            }

            return var3;
         }

         public String getPinCode() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var1 = Parcel.obtain();

            String var3;
            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(25, var2, var1, 0) && Stub.getDefaultImpl() != null) {
                  var3 = Stub.getDefaultImpl().getPinCode();
                  return var3;
               }

               var1.readException();
               var3 = var1.readString();
            } finally {
               var1.recycle();
               var2.recycle();
            }

            return var3;
         }

         public String getState() throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var1 = Parcel.obtain();

            String var3;
            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(8, var2, var1, 0) && Stub.getDefaultImpl() != null) {
                  var3 = Stub.getDefaultImpl().getState();
                  return var3;
               }

               var1.readException();
               var3 = var1.readString();
            } finally {
               var1.recycle();
               var2.recycle();
            }

            return var3;
         }

         public String getWechatCall() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            String var3;
            try {
               var1.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               if (!this.mRemote.transact(33, var1, var2, 0) && Stub.getDefaultImpl() != null) {
                  var3 = Stub.getDefaultImpl().getWechatCall();
                  return var3;
               }

               var2.readException();
               var3 = var2.readString();
            } finally {
               var2.recycle();
               var1.recycle();
            }

            return var3;
         }

         public void handup(String var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               var2.writeString(var1);
               if (!this.mRemote.transact(31, var2, var3, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().handup(var1);
                  return;
               }

               var3.readException();
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public boolean isAutoAnswer() throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var4.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(34, var4, var3, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().isAutoAnswer();
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

         public boolean isAutoConn() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(9, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().isAutoConn();
                        break label172;
                     }
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label171;
                  }

                  int var1;
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

         public boolean isMicMute() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(21, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().isMicMute();
                        break label172;
                     }
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label171;
                  }

                  int var1;
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

         public boolean isMicOut() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(20, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().isMicOut();
                        break label172;
                     }
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label171;
                  }

                  int var1;
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

         public boolean isWechatFilter() throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label171: {
               boolean var10001;
               IBinder var5;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
                  var5 = this.mRemote;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label171;
               }

               boolean var2 = false;

               label172: {
                  try {
                     if (!var5.transact(27, var3, var4, 0) && Stub.getDefaultImpl() != null) {
                        var2 = Stub.getDefaultImpl().isWechatFilter();
                        break label172;
                     }
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label171;
                  }

                  int var1;
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

         public void removeBtCallBack(IBtCallback var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label229: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
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

                     Stub.getDefaultImpl().removeBtCallBack(var1);
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

         public void sendAction(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               var3.writeString(var1);
               if (this.mRemote.transact(3, var3, var2, 0) || Stub.getDefaultImpl() == null) {
                  var2.readException();
                  return;
               }

               Stub.getDefaultImpl().sendAction(var1);
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void sendCallKey(String var1, String var2) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               var3.writeString(var1);
               var3.writeString(var2);
               if (this.mRemote.transact(32, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                  var4.readException();
                  return;
               }

               Stub.getDefaultImpl().sendCallKey(var1, var2);
            } finally {
               var4.recycle();
               var3.recycle();
            }

         }

         public void sendDeviceAction(String var1, String var2) throws RemoteException {
            Parcel var4 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var4.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               var4.writeString(var1);
               var4.writeString(var2);
               if (this.mRemote.transact(4, var4, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().sendDeviceAction(var1, var2);
            } finally {
               var3.recycle();
               var4.recycle();
            }

         }

         public void sendKey(String var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               var2.writeString(var1);
               if (!this.mRemote.transact(6, var2, var3, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().sendKey(var1);
                  return;
               }

               var3.readException();
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public void setName(String var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            try {
               var2.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               var2.writeString(var1);
               if (this.mRemote.transact(7, var2, var3, 0) || Stub.getDefaultImpl() == null) {
                  var3.readException();
                  return;
               }

               Stub.getDefaultImpl().setName(var1);
            } finally {
               var3.recycle();
               var2.recycle();
            }

         }

         public void setPinCode(String var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            try {
               var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
               var3.writeString(var1);
               if (!this.mRemote.transact(24, var3, var2, 0) && Stub.getDefaultImpl() != null) {
                  Stub.getDefaultImpl().setPinCode(var1);
                  return;
               }

               var2.readException();
            } finally {
               var2.recycle();
               var3.recycle();
            }

         }

         public void setWechatFilter(boolean var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();
            Parcel var4 = Parcel.obtain();

            Throwable var10000;
            label167: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("com.hzbhd.proxy.bluetooth.aidl.IBtManager");
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
                     if (this.mRemote.transact(28, var3, var4, 0) || Stub.getDefaultImpl() == null) {
                        break label161;
                     }

                     Stub.getDefaultImpl().setWechatFilter(var1);
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
      }
   }
}
