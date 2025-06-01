package androidx.core.content;

import android.os.RemoteException;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;

public class UnusedAppRestrictionsBackportCallback {
   private IUnusedAppRestrictionsBackportCallback mCallback;

   public UnusedAppRestrictionsBackportCallback(IUnusedAppRestrictionsBackportCallback var1) {
      this.mCallback = var1;
   }

   public void onResult(boolean var1, boolean var2) throws RemoteException {
      this.mCallback.onIsPermissionRevocationEnabledForAppResult(var1, var2);
   }
}
