package androidx.core.content;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.Build.VERSION;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.ExecutorCompat;
import androidx.core.util.ObjectsCompat;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.Executor;

public class ContextCompat {
   private static final String TAG = "ContextCompat";
   private static final Object sLock = new Object();
   private static final Object sSync = new Object();
   private static TypedValue sTempValue;

   protected ContextCompat() {
   }

   public static int checkSelfPermission(Context var0, String var1) {
      ObjectsCompat.requireNonNull(var1, "permission must be non-null");
      return var0.checkPermission(var1, Process.myPid(), Process.myUid());
   }

   public static Context createDeviceProtectedStorageContext(Context var0) {
      return VERSION.SDK_INT >= 24 ? var0.createDeviceProtectedStorageContext() : null;
   }

   private static File createFilesDir(File var0) {
      Object var1 = sSync;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label198: {
         label197: {
            try {
               if (var0.exists()) {
                  break label197;
               }

               if (var0.mkdirs()) {
                  return var0;
               }
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label198;
            }

            try {
               StringBuilder var2 = new StringBuilder();
               Log.w("ContextCompat", var2.append("Unable to create files subdir ").append(var0.getPath()).toString());
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label198;
            }
         }

         label187:
         try {
            return var0;
         } catch (Throwable var20) {
            var10000 = var20;
            var10001 = false;
            break label187;
         }
      }

      while(true) {
         Throwable var23 = var10000;

         try {
            throw var23;
         } catch (Throwable var19) {
            var10000 = var19;
            var10001 = false;
            continue;
         }
      }
   }

   public static String getAttributionTag(Context var0) {
      return VERSION.SDK_INT >= 30 ? var0.getAttributionTag() : null;
   }

   public static File getCodeCacheDir(Context var0) {
      return VERSION.SDK_INT >= 21 ? var0.getCodeCacheDir() : createFilesDir(new File(var0.getApplicationInfo().dataDir, "code_cache"));
   }

   public static int getColor(Context var0, int var1) {
      return VERSION.SDK_INT >= 23 ? var0.getColor(var1) : var0.getResources().getColor(var1);
   }

   public static ColorStateList getColorStateList(Context var0, int var1) {
      return ResourcesCompat.getColorStateList(var0.getResources(), var1, var0.getTheme());
   }

   public static File getDataDir(Context var0) {
      if (VERSION.SDK_INT >= 24) {
         return var0.getDataDir();
      } else {
         String var1 = var0.getApplicationInfo().dataDir;
         File var2;
         if (var1 != null) {
            var2 = new File(var1);
         } else {
            var2 = null;
         }

         return var2;
      }
   }

   public static Drawable getDrawable(Context var0, int var1) {
      if (VERSION.SDK_INT >= 21) {
         return var0.getDrawable(var1);
      } else if (VERSION.SDK_INT >= 16) {
         return var0.getResources().getDrawable(var1);
      } else {
         Object var2 = sLock;
         synchronized(var2){}

         Throwable var10000;
         boolean var10001;
         label160: {
            try {
               if (sTempValue == null) {
                  TypedValue var3 = new TypedValue();
                  sTempValue = var3;
               }
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label160;
            }

            label157:
            try {
               var0.getResources().getValue(var1, sTempValue, true);
               var1 = sTempValue.resourceId;
               return var0.getResources().getDrawable(var1);
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label157;
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
   }

   public static File[] getExternalCacheDirs(Context var0) {
      return VERSION.SDK_INT >= 19 ? var0.getExternalCacheDirs() : new File[]{var0.getExternalCacheDir()};
   }

   public static File[] getExternalFilesDirs(Context var0, String var1) {
      return VERSION.SDK_INT >= 19 ? var0.getExternalFilesDirs(var1) : new File[]{var0.getExternalFilesDir(var1)};
   }

   public static Executor getMainExecutor(Context var0) {
      return VERSION.SDK_INT >= 28 ? var0.getMainExecutor() : ExecutorCompat.create(new Handler(var0.getMainLooper()));
   }

   public static File getNoBackupFilesDir(Context var0) {
      return VERSION.SDK_INT >= 21 ? var0.getNoBackupFilesDir() : createFilesDir(new File(var0.getApplicationInfo().dataDir, "no_backup"));
   }

   public static File[] getObbDirs(Context var0) {
      return VERSION.SDK_INT >= 19 ? var0.getObbDirs() : new File[]{var0.getObbDir()};
   }

   public static Object getSystemService(Context var0, Class var1) {
      if (VERSION.SDK_INT >= 23) {
         return var0.getSystemService(var1);
      } else {
         String var3 = getSystemServiceName(var0, var1);
         Object var2;
         if (var3 != null) {
            var2 = var0.getSystemService(var3);
         } else {
            var2 = null;
         }

         return var2;
      }
   }

   public static String getSystemServiceName(Context var0, Class var1) {
      return VERSION.SDK_INT >= 23 ? var0.getSystemServiceName(var1) : (String)ContextCompat.LegacyServiceMapHolder.SERVICES.get(var1);
   }

   public static boolean isDeviceProtectedStorage(Context var0) {
      return VERSION.SDK_INT >= 24 ? var0.isDeviceProtectedStorage() : false;
   }

   public static boolean startActivities(Context var0, Intent[] var1) {
      return startActivities(var0, var1, (Bundle)null);
   }

   public static boolean startActivities(Context var0, Intent[] var1, Bundle var2) {
      if (VERSION.SDK_INT >= 16) {
         var0.startActivities(var1, var2);
      } else {
         var0.startActivities(var1);
      }

      return true;
   }

   public static void startActivity(Context var0, Intent var1, Bundle var2) {
      if (VERSION.SDK_INT >= 16) {
         var0.startActivity(var1, var2);
      } else {
         var0.startActivity(var1);
      }

   }

   public static void startForegroundService(Context var0, Intent var1) {
      if (VERSION.SDK_INT >= 26) {
         var0.startForegroundService(var1);
      } else {
         var0.startService(var1);
      }

   }

   static class Api16Impl {
      private Api16Impl() {
      }

      static void startActivities(Context var0, Intent[] var1, Bundle var2) {
         var0.startActivities(var1, var2);
      }

      static void startActivity(Context var0, Intent var1, Bundle var2) {
         var0.startActivity(var1, var2);
      }
   }

   static class Api19Impl {
      private Api19Impl() {
      }

      static File[] getExternalCacheDirs(Context var0) {
         return var0.getExternalCacheDirs();
      }

      static File[] getExternalFilesDirs(Context var0, String var1) {
         return var0.getExternalFilesDirs(var1);
      }

      static File[] getObbDirs(Context var0) {
         return var0.getObbDirs();
      }
   }

   static class Api21Impl {
      private Api21Impl() {
      }

      static File getCodeCacheDir(Context var0) {
         return var0.getCodeCacheDir();
      }

      static Drawable getDrawable(Context var0, int var1) {
         return var0.getDrawable(var1);
      }

      static File getNoBackupFilesDir(Context var0) {
         return var0.getNoBackupFilesDir();
      }
   }

   static class Api23Impl {
      private Api23Impl() {
      }

      static int getColor(Context var0, int var1) {
         return var0.getColor(var1);
      }

      static Object getSystemService(Context var0, Class var1) {
         return var0.getSystemService(var1);
      }

      static String getSystemServiceName(Context var0, Class var1) {
         return var0.getSystemServiceName(var1);
      }
   }

   static class Api24Impl {
      private Api24Impl() {
      }

      static Context createDeviceProtectedStorageContext(Context var0) {
         return var0.createDeviceProtectedStorageContext();
      }

      static File getDataDir(Context var0) {
         return var0.getDataDir();
      }

      static boolean isDeviceProtectedStorage(Context var0) {
         return var0.isDeviceProtectedStorage();
      }
   }

   static class Api26Impl {
      private Api26Impl() {
      }

      static ComponentName startForegroundService(Context var0, Intent var1) {
         return var0.startForegroundService(var1);
      }
   }

   static class Api28Impl {
      private Api28Impl() {
      }

      static Executor getMainExecutor(Context var0) {
         return var0.getMainExecutor();
      }
   }

   static class Api30Impl {
      private Api30Impl() {
      }

      static String getAttributionTag(Context var0) {
         return var0.getAttributionTag();
      }
   }

   private static final class LegacyServiceMapHolder {
      static final HashMap SERVICES;

      static {
         HashMap var0 = new HashMap();
         SERVICES = var0;
         if (VERSION.SDK_INT >= 22) {
            var0.put(SubscriptionManager.class, "telephony_subscription_service");
            var0.put(UsageStatsManager.class, "usagestats");
         }

         if (VERSION.SDK_INT >= 21) {
            var0.put(AppWidgetManager.class, "appwidget");
            var0.put(BatteryManager.class, "batterymanager");
            var0.put(CameraManager.class, "camera");
            var0.put(JobScheduler.class, "jobscheduler");
            var0.put(LauncherApps.class, "launcherapps");
            var0.put(MediaProjectionManager.class, "media_projection");
            var0.put(MediaSessionManager.class, "media_session");
            var0.put(RestrictionsManager.class, "restrictions");
            var0.put(TelecomManager.class, "telecom");
            var0.put(TvInputManager.class, "tv_input");
         }

         if (VERSION.SDK_INT >= 19) {
            var0.put(AppOpsManager.class, "appops");
            var0.put(CaptioningManager.class, "captioning");
            var0.put(ConsumerIrManager.class, "consumer_ir");
            var0.put(PrintManager.class, "print");
         }

         if (VERSION.SDK_INT >= 18) {
            var0.put(BluetoothManager.class, "bluetooth");
         }

         if (VERSION.SDK_INT >= 17) {
            var0.put(DisplayManager.class, "display");
            var0.put(UserManager.class, "user");
         }

         if (VERSION.SDK_INT >= 16) {
            var0.put(InputManager.class, "input");
            var0.put(MediaRouter.class, "media_router");
            var0.put(NsdManager.class, "servicediscovery");
         }

         var0.put(AccessibilityManager.class, "accessibility");
         var0.put(AccountManager.class, "account");
         var0.put(ActivityManager.class, "activity");
         var0.put(AlarmManager.class, "alarm");
         var0.put(AudioManager.class, "audio");
         var0.put(ClipboardManager.class, "clipboard");
         var0.put(ConnectivityManager.class, "connectivity");
         var0.put(DevicePolicyManager.class, "device_policy");
         var0.put(DownloadManager.class, "download");
         var0.put(DropBoxManager.class, "dropbox");
         var0.put(InputMethodManager.class, "input_method");
         var0.put(KeyguardManager.class, "keyguard");
         var0.put(LayoutInflater.class, "layout_inflater");
         var0.put(LocationManager.class, "location");
         var0.put(NfcManager.class, "nfc");
         var0.put(NotificationManager.class, "notification");
         var0.put(PowerManager.class, "power");
         var0.put(SearchManager.class, "search");
         var0.put(SensorManager.class, "sensor");
         var0.put(StorageManager.class, "storage");
         var0.put(TelephonyManager.class, "phone");
         var0.put(TextServicesManager.class, "textservices");
         var0.put(UiModeManager.class, "uimode");
         var0.put(UsbManager.class, "usb");
         var0.put(Vibrator.class, "vibrator");
         var0.put(WallpaperManager.class, "wallpaper");
         var0.put(WifiP2pManager.class, "wifip2p");
         var0.put(WifiManager.class, "wifi");
         var0.put(WindowManager.class, "window");
      }
   }
}
