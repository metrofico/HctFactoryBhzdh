package androidx.core.app.unusedapprestrictions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUnusedAppRestrictionsBackportCallback extends IInterface {
   void onIsPermissionRevocationEnabledForAppResult(boolean var1, boolean var2) throws RemoteException;

   public static class Default implements IUnusedAppRestrictionsBackportCallback {
      public IBinder asBinder() {
         return null;
      }

      public void onIsPermissionRevocationEnabledForAppResult(boolean var1, boolean var2) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IUnusedAppRestrictionsBackportCallback {
      private static final String DESCRIPTOR = "androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback";
      static final int TRANSACTION_onIsPermissionRevocationEnabledForAppResult = 1;

      public Stub() {
         this.attachInterface(this, "androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback");
      }

      public static IUnusedAppRestrictionsBackportCallback asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback");
            return (IUnusedAppRestrictionsBackportCallback)(var1 != null && var1 instanceof IUnusedAppRestrictionsBackportCallback ? (IUnusedAppRestrictionsBackportCallback)var1 : new Proxy(var0));
         }
      }

      public static IUnusedAppRestrictionsBackportCallback getDefaultImpl() {
         return IUnusedAppRestrictionsBackportCallback.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IUnusedAppRestrictionsBackportCallback var0) {
         if (IUnusedAppRestrictionsBackportCallback.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               IUnusedAppRestrictionsBackportCallback.Stub.Proxy.sDefaultImpl = var0;
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
               var3.writeString("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback");
               return true;
            }
         } else {
            var2.enforceInterface("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback");
            var1 = var2.readInt();
            boolean var6 = false;
            boolean var5;
            if (var1 != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            if (var2.readInt() != 0) {
               var6 = true;
            }

            this.onIsPermissionRevocationEnabledForAppResult(var5, var6);
            return true;
         }
      }

      private static class Proxy implements IUnusedAppRestrictionsBackportCallback {
         public static IUnusedAppRestrictionsBackportCallback sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback";
         }

         public void onIsPermissionRevocationEnabledForAppResult(boolean var1, boolean var2) throws RemoteException {
            Parcel var5 = Parcel.obtain();

            Throwable var10000;
            label180: {
               boolean var10001;
               try {
                  var5.writeInterfaceToken("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback");
               } catch (Throwable var18) {
                  var10000 = var18;
                  var10001 = false;
                  break label180;
               }

               byte var4 = 0;
               byte var3;
               if (var1) {
                  var3 = 1;
               } else {
                  var3 = 0;
               }

               try {
                  var5.writeInt(var3);
               } catch (Throwable var17) {
                  var10000 = var17;
                  var10001 = false;
                  break label180;
               }

               var3 = var4;
               if (var2) {
                  var3 = 1;
               }

               label167: {
                  try {
                     var5.writeInt(var3);
                     if (!this.mRemote.transact(1, var5, (Parcel)null, 1) && IUnusedAppRestrictionsBackportCallback.Stub.getDefaultImpl() != null) {
                        IUnusedAppRestrictionsBackportCallback.Stub.getDefaultImpl().onIsPermissionRevocationEnabledForAppResult(var1, var2);
                        break label167;
                     }
                  } catch (Throwable var16) {
                     var10000 = var16;
                     var10001 = false;
                     break label180;
                  }

                  var5.recycle();
                  return;
               }

               var5.recycle();
               return;
            }

            Throwable var6 = var10000;
            var5.recycle();
            throw var6;
         }
      }
   }
}
