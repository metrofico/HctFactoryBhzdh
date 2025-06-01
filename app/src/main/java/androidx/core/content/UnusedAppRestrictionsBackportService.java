package androidx.core.content;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService;

public abstract class UnusedAppRestrictionsBackportService extends Service {
   public static final String ACTION_UNUSED_APP_RESTRICTIONS_BACKPORT_CONNECTION = "android.support.unusedapprestrictions.action.CustomUnusedAppRestrictionsBackportService";
   private IUnusedAppRestrictionsBackportService.Stub mBinder = new IUnusedAppRestrictionsBackportService.Stub(this) {
      final UnusedAppRestrictionsBackportService this$0;

      {
         this.this$0 = var1;
      }

      public void isPermissionRevocationEnabledForApp(IUnusedAppRestrictionsBackportCallback var1) throws RemoteException {
         if (var1 != null) {
            UnusedAppRestrictionsBackportCallback var2 = new UnusedAppRestrictionsBackportCallback(var1);
            this.this$0.isPermissionRevocationEnabled(var2);
         }
      }
   };

   protected abstract void isPermissionRevocationEnabled(UnusedAppRestrictionsBackportCallback var1);

   public IBinder onBind(Intent var1) {
      return this.mBinder;
   }
}
