package androidx.core.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Build.VERSION;
import android.support.v4.app.INotificationSideChannel;

public abstract class NotificationCompatSideChannelService extends Service {
   public abstract void cancel(String var1, int var2, String var3);

   public abstract void cancelAll(String var1);

   void checkPermission(int var1, String var2) {
      String[] var5 = this.getPackageManager().getPackagesForUid(var1);
      int var4 = var5.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var5[var3].equals(var2)) {
            return;
         }
      }

      throw new SecurityException("NotificationSideChannelService: Uid " + var1 + " is not authorized for package " + var2);
   }

   public abstract void notify(String var1, int var2, String var3, Notification var4);

   public IBinder onBind(Intent var1) {
      if (var1.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL")) {
         return VERSION.SDK_INT > 19 ? null : new NotificationSideChannelStub(this);
      } else {
         return null;
      }
   }

   private class NotificationSideChannelStub extends INotificationSideChannel.Stub {
      final NotificationCompatSideChannelService this$0;

      NotificationSideChannelStub(NotificationCompatSideChannelService var1) {
         this.this$0 = var1;
      }

      public void cancel(String var1, int var2, String var3) throws RemoteException {
         this.this$0.checkPermission(getCallingUid(), var1);
         long var4 = clearCallingIdentity();

         try {
            this.this$0.cancel(var1, var2, var3);
         } finally {
            restoreCallingIdentity(var4);
         }

      }

      public void cancelAll(String var1) {
         this.this$0.checkPermission(getCallingUid(), var1);
         long var2 = clearCallingIdentity();

         try {
            this.this$0.cancelAll(var1);
         } finally {
            restoreCallingIdentity(var2);
         }

      }

      public void notify(String var1, int var2, String var3, Notification var4) throws RemoteException {
         this.this$0.checkPermission(getCallingUid(), var1);
         long var5 = clearCallingIdentity();

         try {
            this.this$0.notify(var1, var2, var3, var4);
         } finally {
            restoreCallingIdentity(var5);
         }

      }
   }
}
