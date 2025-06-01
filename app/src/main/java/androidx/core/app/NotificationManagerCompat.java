package androidx.core.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class NotificationManagerCompat {
   public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
   private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
   public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
   public static final int IMPORTANCE_DEFAULT = 3;
   public static final int IMPORTANCE_HIGH = 4;
   public static final int IMPORTANCE_LOW = 2;
   public static final int IMPORTANCE_MAX = 5;
   public static final int IMPORTANCE_MIN = 1;
   public static final int IMPORTANCE_NONE = 0;
   public static final int IMPORTANCE_UNSPECIFIED = -1000;
   static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
   private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
   private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
   private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
   private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
   private static final String TAG = "NotifManCompat";
   private static Set sEnabledNotificationListenerPackages = new HashSet();
   private static String sEnabledNotificationListeners;
   private static final Object sEnabledNotificationListenersLock = new Object();
   private static final Object sLock = new Object();
   private static SideChannelManager sSideChannelManager;
   private final Context mContext;
   private final NotificationManager mNotificationManager;

   private NotificationManagerCompat(Context var1) {
      this.mContext = var1;
      this.mNotificationManager = (NotificationManager)var1.getSystemService("notification");
   }

   public static NotificationManagerCompat from(Context var0) {
      return new NotificationManagerCompat(var0);
   }

   public static Set getEnabledListenerPackages(Context var0) {
      Throwable var10000;
      boolean var10001;
      label388: {
         String var5 = Secure.getString(var0.getContentResolver(), "enabled_notification_listeners");
         Object var49 = sEnabledNotificationListenersLock;
         synchronized(var49){}
         if (var5 != null) {
            label387: {
               int var2;
               HashSet var3;
               String[] var6;
               try {
                  if (var5.equals(sEnabledNotificationListeners)) {
                     break label387;
                  }

                  var6 = var5.split(":", -1);
                  var3 = new HashSet(var6.length);
                  var2 = var6.length;
               } catch (Throwable var48) {
                  var10000 = var48;
                  var10001 = false;
                  break label388;
               }

               int var1 = 0;

               while(true) {
                  if (var1 >= var2) {
                     try {
                        sEnabledNotificationListenerPackages = var3;
                        sEnabledNotificationListeners = var5;
                        break;
                     } catch (Throwable var45) {
                        var10000 = var45;
                        var10001 = false;
                        break label388;
                     }
                  }

                  ComponentName var4;
                  try {
                     var4 = ComponentName.unflattenFromString(var6[var1]);
                  } catch (Throwable var47) {
                     var10000 = var47;
                     var10001 = false;
                     break label388;
                  }

                  if (var4 != null) {
                     try {
                        var3.add(var4.getPackageName());
                     } catch (Throwable var46) {
                        var10000 = var46;
                        var10001 = false;
                        break label388;
                     }
                  }

                  ++var1;
               }
            }
         }

         label362:
         try {
            Set var51 = sEnabledNotificationListenerPackages;
            return var51;
         } catch (Throwable var44) {
            var10000 = var44;
            var10001 = false;
            break label362;
         }
      }

      while(true) {
         Throwable var50 = var10000;

         try {
            throw var50;
         } catch (Throwable var43) {
            var10000 = var43;
            var10001 = false;
            continue;
         }
      }
   }

   private void pushSideChannelQueue(Task var1) {
      Object var2 = sLock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label122: {
         try {
            if (sSideChannelManager == null) {
               SideChannelManager var3 = new SideChannelManager(this.mContext.getApplicationContext());
               sSideChannelManager = var3;
            }
         } catch (Throwable var15) {
            var10000 = var15;
            var10001 = false;
            break label122;
         }

         label119:
         try {
            sSideChannelManager.queueTask(var1);
            return;
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label119;
         }
      }

      while(true) {
         Throwable var16 = var10000;

         try {
            throw var16;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            continue;
         }
      }
   }

   private static boolean useSideChannelForNotification(Notification var0) {
      Bundle var2 = NotificationCompat.getExtras(var0);
      boolean var1;
      if (var2 != null && var2.getBoolean("android.support.useSideChannel")) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean areNotificationsEnabled() {
      if (VERSION.SDK_INT >= 24) {
         return this.mNotificationManager.areNotificationsEnabled();
      } else {
         int var1 = VERSION.SDK_INT;
         boolean var3 = true;
         boolean var2 = var3;
         if (var1 >= 19) {
            AppOpsManager var4 = (AppOpsManager)this.mContext.getSystemService("appops");
            ApplicationInfo var6 = this.mContext.getApplicationInfo();
            String var5 = this.mContext.getApplicationContext().getPackageName();
            var1 = var6.uid;

            try {
               Class var8 = Class.forName(AppOpsManager.class.getName());
               var1 = (Integer)var8.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke(var4, (Integer)var8.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class), var1, var5);
            } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | RuntimeException | ClassNotFoundException var7) {
               var2 = var3;
               return var2;
            }

            if (var1 == 0) {
               var2 = var3;
            } else {
               var2 = false;
            }
         }

         return var2;
      }
   }

   public void cancel(int var1) {
      this.cancel((String)null, var1);
   }

   public void cancel(String var1, int var2) {
      this.mNotificationManager.cancel(var1, var2);
      if (VERSION.SDK_INT <= 19) {
         this.pushSideChannelQueue(new CancelTask(this.mContext.getPackageName(), var2, var1));
      }

   }

   public void cancelAll() {
      this.mNotificationManager.cancelAll();
      if (VERSION.SDK_INT <= 19) {
         this.pushSideChannelQueue(new CancelTask(this.mContext.getPackageName()));
      }

   }

   public void createNotificationChannel(NotificationChannel var1) {
      if (VERSION.SDK_INT >= 26) {
         this.mNotificationManager.createNotificationChannel(var1);
      }

   }

   public void createNotificationChannel(NotificationChannelCompat var1) {
      this.createNotificationChannel(var1.getNotificationChannel());
   }

   public void createNotificationChannelGroup(NotificationChannelGroup var1) {
      if (VERSION.SDK_INT >= 26) {
         this.mNotificationManager.createNotificationChannelGroup(var1);
      }

   }

   public void createNotificationChannelGroup(NotificationChannelGroupCompat var1) {
      this.createNotificationChannelGroup(var1.getNotificationChannelGroup());
   }

   public void createNotificationChannelGroups(List var1) {
      if (VERSION.SDK_INT >= 26) {
         this.mNotificationManager.createNotificationChannelGroups(var1);
      }

   }

   public void createNotificationChannelGroupsCompat(List var1) {
      if (VERSION.SDK_INT >= 26 && !var1.isEmpty()) {
         ArrayList var2 = new ArrayList(var1.size());
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            var2.add(((NotificationChannelGroupCompat)var3.next()).getNotificationChannelGroup());
         }

         this.mNotificationManager.createNotificationChannelGroups(var2);
      }

   }

   public void createNotificationChannels(List var1) {
      if (VERSION.SDK_INT >= 26) {
         this.mNotificationManager.createNotificationChannels(var1);
      }

   }

   public void createNotificationChannelsCompat(List var1) {
      if (VERSION.SDK_INT >= 26 && !var1.isEmpty()) {
         ArrayList var2 = new ArrayList(var1.size());
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            var2.add(((NotificationChannelCompat)var3.next()).getNotificationChannel());
         }

         this.mNotificationManager.createNotificationChannels(var2);
      }

   }

   public void deleteNotificationChannel(String var1) {
      if (VERSION.SDK_INT >= 26) {
         this.mNotificationManager.deleteNotificationChannel(var1);
      }

   }

   public void deleteNotificationChannelGroup(String var1) {
      if (VERSION.SDK_INT >= 26) {
         this.mNotificationManager.deleteNotificationChannelGroup(var1);
      }

   }

   public void deleteUnlistedNotificationChannels(Collection var1) {
      if (VERSION.SDK_INT >= 26) {
         Iterator var2 = this.mNotificationManager.getNotificationChannels().iterator();

         while(true) {
            NotificationChannel var3;
            do {
               do {
                  if (!var2.hasNext()) {
                     return;
                  }

                  var3 = (NotificationChannel)var2.next();
               } while(var1.contains(var3.getId()));
            } while(VERSION.SDK_INT >= 30 && var1.contains(var3.getParentChannelId()));

            this.mNotificationManager.deleteNotificationChannel(var3.getId());
         }
      }
   }

   public int getImportance() {
      return VERSION.SDK_INT >= 24 ? this.mNotificationManager.getImportance() : -1000;
   }

   public NotificationChannel getNotificationChannel(String var1) {
      return VERSION.SDK_INT >= 26 ? this.mNotificationManager.getNotificationChannel(var1) : null;
   }

   public NotificationChannel getNotificationChannel(String var1, String var2) {
      return VERSION.SDK_INT >= 30 ? this.mNotificationManager.getNotificationChannel(var1, var2) : this.getNotificationChannel(var1);
   }

   public NotificationChannelCompat getNotificationChannelCompat(String var1) {
      if (VERSION.SDK_INT >= 26) {
         NotificationChannel var2 = this.getNotificationChannel(var1);
         if (var2 != null) {
            return new NotificationChannelCompat(var2);
         }
      }

      return null;
   }

   public NotificationChannelCompat getNotificationChannelCompat(String var1, String var2) {
      if (VERSION.SDK_INT >= 26) {
         NotificationChannel var3 = this.getNotificationChannel(var1, var2);
         if (var3 != null) {
            return new NotificationChannelCompat(var3);
         }
      }

      return null;
   }

   public NotificationChannelGroup getNotificationChannelGroup(String var1) {
      if (VERSION.SDK_INT >= 28) {
         return this.mNotificationManager.getNotificationChannelGroup(var1);
      } else {
         if (VERSION.SDK_INT >= 26) {
            Iterator var3 = this.getNotificationChannelGroups().iterator();

            while(var3.hasNext()) {
               NotificationChannelGroup var2 = (NotificationChannelGroup)var3.next();
               if (var2.getId().equals(var1)) {
                  return var2;
               }
            }
         }

         return null;
      }
   }

   public NotificationChannelGroupCompat getNotificationChannelGroupCompat(String var1) {
      NotificationChannelGroup var2;
      if (VERSION.SDK_INT >= 28) {
         var2 = this.getNotificationChannelGroup(var1);
         if (var2 != null) {
            return new NotificationChannelGroupCompat(var2);
         }
      } else if (VERSION.SDK_INT >= 26) {
         var2 = this.getNotificationChannelGroup(var1);
         if (var2 != null) {
            return new NotificationChannelGroupCompat(var2, this.getNotificationChannels());
         }
      }

      return null;
   }

   public List getNotificationChannelGroups() {
      return VERSION.SDK_INT >= 26 ? this.mNotificationManager.getNotificationChannelGroups() : Collections.emptyList();
   }

   public List getNotificationChannelGroupsCompat() {
      if (VERSION.SDK_INT >= 26) {
         List var2 = this.getNotificationChannelGroups();
         if (!var2.isEmpty()) {
            List var1;
            if (VERSION.SDK_INT >= 28) {
               var1 = Collections.emptyList();
            } else {
               var1 = this.getNotificationChannels();
            }

            ArrayList var3 = new ArrayList(var2.size());
            Iterator var5 = var2.iterator();

            while(var5.hasNext()) {
               NotificationChannelGroup var4 = (NotificationChannelGroup)var5.next();
               if (VERSION.SDK_INT >= 28) {
                  var3.add(new NotificationChannelGroupCompat(var4));
               } else {
                  var3.add(new NotificationChannelGroupCompat(var4, var1));
               }
            }

            return var3;
         }
      }

      return Collections.emptyList();
   }

   public List getNotificationChannels() {
      return VERSION.SDK_INT >= 26 ? this.mNotificationManager.getNotificationChannels() : Collections.emptyList();
   }

   public List getNotificationChannelsCompat() {
      if (VERSION.SDK_INT >= 26) {
         List var2 = this.getNotificationChannels();
         if (!var2.isEmpty()) {
            ArrayList var1 = new ArrayList(var2.size());
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
               var1.add(new NotificationChannelCompat((NotificationChannel)var3.next()));
            }

            return var1;
         }
      }

      return Collections.emptyList();
   }

   public void notify(int var1, Notification var2) {
      this.notify((String)null, var1, var2);
   }

   public void notify(String var1, int var2, Notification var3) {
      if (useSideChannelForNotification(var3)) {
         this.pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), var2, var1, var3));
         this.mNotificationManager.cancel(var1, var2);
      } else {
         this.mNotificationManager.notify(var1, var2, var3);
      }

   }

   private static class CancelTask implements Task {
      final boolean all;
      final int id;
      final String packageName;
      final String tag;

      CancelTask(String var1) {
         this.packageName = var1;
         this.id = 0;
         this.tag = null;
         this.all = true;
      }

      CancelTask(String var1, int var2, String var3) {
         this.packageName = var1;
         this.id = var2;
         this.tag = var3;
         this.all = false;
      }

      public void send(INotificationSideChannel var1) throws RemoteException {
         if (this.all) {
            var1.cancelAll(this.packageName);
         } else {
            var1.cancel(this.packageName, this.id, this.tag);
         }

      }

      public String toString() {
         StringBuilder var1 = new StringBuilder("CancelTask[");
         var1.append("packageName:").append(this.packageName);
         var1.append(", id:").append(this.id);
         var1.append(", tag:").append(this.tag);
         var1.append(", all:").append(this.all);
         var1.append("]");
         return var1.toString();
      }
   }

   private static class NotifyTask implements Task {
      final int id;
      final Notification notif;
      final String packageName;
      final String tag;

      NotifyTask(String var1, int var2, String var3, Notification var4) {
         this.packageName = var1;
         this.id = var2;
         this.tag = var3;
         this.notif = var4;
      }

      public void send(INotificationSideChannel var1) throws RemoteException {
         var1.notify(this.packageName, this.id, this.tag, this.notif);
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder("NotifyTask[");
         var1.append("packageName:").append(this.packageName);
         var1.append(", id:").append(this.id);
         var1.append(", tag:").append(this.tag);
         var1.append("]");
         return var1.toString();
      }
   }

   private static class ServiceConnectedEvent {
      final ComponentName componentName;
      final IBinder iBinder;

      ServiceConnectedEvent(ComponentName var1, IBinder var2) {
         this.componentName = var1;
         this.iBinder = var2;
      }
   }

   private static class SideChannelManager implements Handler.Callback, ServiceConnection {
      private static final int MSG_QUEUE_TASK = 0;
      private static final int MSG_RETRY_LISTENER_QUEUE = 3;
      private static final int MSG_SERVICE_CONNECTED = 1;
      private static final int MSG_SERVICE_DISCONNECTED = 2;
      private Set mCachedEnabledPackages = new HashSet();
      private final Context mContext;
      private final Handler mHandler;
      private final HandlerThread mHandlerThread;
      private final Map mRecordMap = new HashMap();

      SideChannelManager(Context var1) {
         this.mContext = var1;
         HandlerThread var2 = new HandlerThread("NotificationManagerCompat");
         this.mHandlerThread = var2;
         var2.start();
         this.mHandler = new Handler(var2.getLooper(), this);
      }

      private boolean ensureServiceBound(ListenerRecord var1) {
         if (var1.bound) {
            return true;
         } else {
            Intent var2 = (new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL")).setComponent(var1.componentName);
            var1.bound = this.mContext.bindService(var2, this, 33);
            if (var1.bound) {
               var1.retryCount = 0;
            } else {
               Log.w("NotifManCompat", "Unable to bind to listener " + var1.componentName);
               this.mContext.unbindService(this);
            }

            return var1.bound;
         }
      }

      private void ensureServiceUnbound(ListenerRecord var1) {
         if (var1.bound) {
            this.mContext.unbindService(this);
            var1.bound = false;
         }

         var1.service = null;
      }

      private void handleQueueTask(Task var1) {
         this.updateListenerMap();
         Iterator var3 = this.mRecordMap.values().iterator();

         while(var3.hasNext()) {
            ListenerRecord var2 = (ListenerRecord)var3.next();
            var2.taskQueue.add(var1);
            this.processListenerQueue(var2);
         }

      }

      private void handleRetryListenerQueue(ComponentName var1) {
         ListenerRecord var2 = (ListenerRecord)this.mRecordMap.get(var1);
         if (var2 != null) {
            this.processListenerQueue(var2);
         }

      }

      private void handleServiceConnected(ComponentName var1, IBinder var2) {
         ListenerRecord var3 = (ListenerRecord)this.mRecordMap.get(var1);
         if (var3 != null) {
            var3.service = INotificationSideChannel.Stub.asInterface(var2);
            var3.retryCount = 0;
            this.processListenerQueue(var3);
         }

      }

      private void handleServiceDisconnected(ComponentName var1) {
         ListenerRecord var2 = (ListenerRecord)this.mRecordMap.get(var1);
         if (var2 != null) {
            this.ensureServiceUnbound(var2);
         }

      }

      private void processListenerQueue(ListenerRecord var1) {
         if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Processing component " + var1.componentName + ", " + var1.taskQueue.size() + " queued tasks");
         }

         if (!var1.taskQueue.isEmpty()) {
            if (this.ensureServiceBound(var1) && var1.service != null) {
               while(true) {
                  Task var2 = (Task)var1.taskQueue.peek();
                  if (var2 == null) {
                     break;
                  }

                  try {
                     if (Log.isLoggable("NotifManCompat", 3)) {
                        StringBuilder var3 = new StringBuilder();
                        Log.d("NotifManCompat", var3.append("Sending task ").append(var2).toString());
                     }

                     var2.send(var1.service);
                     var1.taskQueue.remove();
                  } catch (DeadObjectException var4) {
                     if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Remote service has died: " + var1.componentName);
                     }
                     break;
                  } catch (RemoteException var5) {
                     Log.w("NotifManCompat", "RemoteException communicating with " + var1.componentName, var5);
                     break;
                  }
               }

               if (!var1.taskQueue.isEmpty()) {
                  this.scheduleListenerRetry(var1);
               }

            } else {
               this.scheduleListenerRetry(var1);
            }
         }
      }

      private void scheduleListenerRetry(ListenerRecord var1) {
         if (!this.mHandler.hasMessages(3, var1.componentName)) {
            ++var1.retryCount;
            if (var1.retryCount > 6) {
               Log.w("NotifManCompat", "Giving up on delivering " + var1.taskQueue.size() + " tasks to " + var1.componentName + " after " + var1.retryCount + " retries");
               var1.taskQueue.clear();
            } else {
               int var2 = (1 << var1.retryCount - 1) * 1000;
               if (Log.isLoggable("NotifManCompat", 3)) {
                  Log.d("NotifManCompat", "Scheduling retry for " + var2 + " ms");
               }

               Message var3 = this.mHandler.obtainMessage(3, var1.componentName);
               this.mHandler.sendMessageDelayed(var3, (long)var2);
            }
         }
      }

      private void updateListenerMap() {
         Set var2 = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
         if (!var2.equals(this.mCachedEnabledPackages)) {
            this.mCachedEnabledPackages = var2;
            List var3 = this.mContext.getPackageManager().queryIntentServices((new Intent()).setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet var1 = new HashSet();
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
               ResolveInfo var7 = (ResolveInfo)var4.next();
               if (var2.contains(var7.serviceInfo.packageName)) {
                  ComponentName var5 = new ComponentName(var7.serviceInfo.packageName, var7.serviceInfo.name);
                  if (var7.serviceInfo.permission != null) {
                     Log.w("NotifManCompat", "Permission present on component " + var5 + ", not adding listener record.");
                  } else {
                     var1.add(var5);
                  }
               }
            }

            Iterator var6 = var1.iterator();

            while(var6.hasNext()) {
               ComponentName var8 = (ComponentName)var6.next();
               if (!this.mRecordMap.containsKey(var8)) {
                  if (Log.isLoggable("NotifManCompat", 3)) {
                     Log.d("NotifManCompat", "Adding listener record for " + var8);
                  }

                  this.mRecordMap.put(var8, new ListenerRecord(var8));
               }
            }

            var6 = this.mRecordMap.entrySet().iterator();

            while(var6.hasNext()) {
               Map.Entry var9 = (Map.Entry)var6.next();
               if (!var1.contains(var9.getKey())) {
                  if (Log.isLoggable("NotifManCompat", 3)) {
                     Log.d("NotifManCompat", "Removing listener record for " + var9.getKey());
                  }

                  this.ensureServiceUnbound((ListenerRecord)var9.getValue());
                  var6.remove();
               }
            }

         }
      }

      public boolean handleMessage(Message var1) {
         int var2 = var1.what;
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     return false;
                  } else {
                     this.handleRetryListenerQueue((ComponentName)var1.obj);
                     return true;
                  }
               } else {
                  this.handleServiceDisconnected((ComponentName)var1.obj);
                  return true;
               }
            } else {
               ServiceConnectedEvent var3 = (ServiceConnectedEvent)var1.obj;
               this.handleServiceConnected(var3.componentName, var3.iBinder);
               return true;
            }
         } else {
            this.handleQueueTask((Task)var1.obj);
            return true;
         }
      }

      public void onServiceConnected(ComponentName var1, IBinder var2) {
         if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Connected to service " + var1);
         }

         this.mHandler.obtainMessage(1, new ServiceConnectedEvent(var1, var2)).sendToTarget();
      }

      public void onServiceDisconnected(ComponentName var1) {
         if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Disconnected from service " + var1);
         }

         this.mHandler.obtainMessage(2, var1).sendToTarget();
      }

      public void queueTask(Task var1) {
         this.mHandler.obtainMessage(0, var1).sendToTarget();
      }

      private static class ListenerRecord {
         boolean bound = false;
         final ComponentName componentName;
         int retryCount = 0;
         INotificationSideChannel service;
         ArrayDeque taskQueue = new ArrayDeque();

         ListenerRecord(ComponentName var1) {
            this.componentName = var1;
         }
      }
   }

   private interface Task {
      void send(INotificationSideChannel var1) throws RemoteException;
   }
}
