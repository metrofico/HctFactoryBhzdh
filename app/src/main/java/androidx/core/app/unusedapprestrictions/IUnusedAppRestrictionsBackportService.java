package androidx.core.app.unusedapprestrictions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUnusedAppRestrictionsBackportService extends IInterface {
   void isPermissionRevocationEnabledForApp(IUnusedAppRestrictionsBackportCallback var1) throws RemoteException;

   public static class Default implements IUnusedAppRestrictionsBackportService {
      public IBinder asBinder() {
         return null;
      }

      public void isPermissionRevocationEnabledForApp(IUnusedAppRestrictionsBackportCallback var1) throws RemoteException {
      }
   }

   public abstract static class Stub extends Binder implements IUnusedAppRestrictionsBackportService {
      private static final String DESCRIPTOR = "androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService";
      static final int TRANSACTION_isPermissionRevocationEnabledForApp = 1;

      public Stub() {
         this.attachInterface(this, "androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService");
      }

      public static IUnusedAppRestrictionsBackportService asInterface(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1 = var0.queryLocalInterface("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService");
            return (IUnusedAppRestrictionsBackportService)(var1 != null && var1 instanceof IUnusedAppRestrictionsBackportService ? (IUnusedAppRestrictionsBackportService)var1 : new Proxy(var0));
         }
      }

      public static IUnusedAppRestrictionsBackportService getDefaultImpl() {
         return IUnusedAppRestrictionsBackportService.Stub.Proxy.sDefaultImpl;
      }

      public static boolean setDefaultImpl(IUnusedAppRestrictionsBackportService var0) {
         if (IUnusedAppRestrictionsBackportService.Stub.Proxy.sDefaultImpl == null) {
            if (var0 != null) {
               IUnusedAppRestrictionsBackportService.Stub.Proxy.sDefaultImpl = var0;
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
               var3.writeString("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService");
               return true;
            }
         } else {
            var2.enforceInterface("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService");
            this.isPermissionRevocationEnabledForApp(IUnusedAppRestrictionsBackportCallback.Stub.asInterface(var2.readStrongBinder()));
            return true;
         }
      }

      private static class Proxy implements IUnusedAppRestrictionsBackportService {
         public static IUnusedAppRestrictionsBackportService sDefaultImpl;
         private IBinder mRemote;

         Proxy(IBinder var1) {
            this.mRemote = var1;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService";
         }

         public void isPermissionRevocationEnabledForApp(IUnusedAppRestrictionsBackportCallback var1) throws RemoteException {
            Parcel var3 = Parcel.obtain();

            Throwable var10000;
            label164: {
               boolean var10001;
               try {
                  var3.writeInterfaceToken("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService");
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label164;
               }

               IBinder var2;
               if (var1 != null) {
                  try {
                     var2 = var1.asBinder();
                  } catch (Throwable var14) {
                     var10000 = var14;
                     var10001 = false;
                     break label164;
                  }
               } else {
                  var2 = null;
               }

               label151: {
                  try {
                     var3.writeStrongBinder(var2);
                     if (!this.mRemote.transact(1, var3, (Parcel)null, 1) && IUnusedAppRestrictionsBackportService.Stub.getDefaultImpl() != null) {
                        IUnusedAppRestrictionsBackportService.Stub.getDefaultImpl().isPermissionRevocationEnabledForApp(var1);
                        break label151;
                     }
                  } catch (Throwable var13) {
                     var10000 = var13;
                     var10001 = false;
                     break label164;
                  }

                  var3.recycle();
                  return;
               }

               var3.recycle();
               return;
            }

            Throwable var16 = var10000;
            var3.recycle();
            throw var16;
         }
      }
   }
}
