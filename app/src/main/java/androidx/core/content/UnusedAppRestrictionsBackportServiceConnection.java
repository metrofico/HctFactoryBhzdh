package androidx.core.content;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService;

class UnusedAppRestrictionsBackportServiceConnection implements ServiceConnection {
   private final Context mContext;
   private boolean mHasBoundService = false;
   ResolvableFuture mResultFuture;
   IUnusedAppRestrictionsBackportService mUnusedAppRestrictionsService = null;

   UnusedAppRestrictionsBackportServiceConnection(Context var1) {
      this.mContext = var1;
   }

   private IUnusedAppRestrictionsBackportCallback getBackportCallback() {
      return new IUnusedAppRestrictionsBackportCallback.Stub(this) {
         final UnusedAppRestrictionsBackportServiceConnection this$0;

         {
            this.this$0 = var1;
         }

         public void onIsPermissionRevocationEnabledForAppResult(boolean var1, boolean var2) throws RemoteException {
            if (var1) {
               if (var2) {
                  this.this$0.mResultFuture.set(3);
               } else {
                  this.this$0.mResultFuture.set(2);
               }
            } else {
               this.this$0.mResultFuture.set(0);
               Log.e("PackageManagerCompat", "Unable to retrieve the permission revocation setting from the backport");
            }

         }
      };
   }

   public void connectAndFetchResult(ResolvableFuture var1) {
      if (!this.mHasBoundService) {
         this.mHasBoundService = true;
         this.mResultFuture = var1;
         Intent var2 = (new Intent("android.support.unusedapprestrictions.action.CustomUnusedAppRestrictionsBackportService")).setPackage(PackageManagerCompat.getPermissionRevocationVerifierApp(this.mContext.getPackageManager()));
         this.mContext.bindService(var2, this, 1);
      } else {
         throw new IllegalStateException("Each UnusedAppRestrictionsBackportServiceConnection can only be bound once.");
      }
   }

   public void disconnectFromService() {
      if (this.mHasBoundService) {
         this.mHasBoundService = false;
         this.mContext.unbindService(this);
      } else {
         throw new IllegalStateException("bindService must be called before unbind");
      }
   }

   public void onServiceConnected(ComponentName var1, IBinder var2) {
      IUnusedAppRestrictionsBackportService var4 = IUnusedAppRestrictionsBackportService.Stub.asInterface(var2);
      this.mUnusedAppRestrictionsService = var4;

      try {
         var4.isPermissionRevocationEnabledForApp(this.getBackportCallback());
      } catch (RemoteException var3) {
         this.mResultFuture.set(0);
      }

   }

   public void onServiceDisconnected(ComponentName var1) {
      this.mUnusedAppRestrictionsService = null;
   }
}
