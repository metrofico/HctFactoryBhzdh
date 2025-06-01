package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.util.Log;

public final class NavUtils {
   public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
   private static final String TAG = "NavUtils";

   private NavUtils() {
   }

   public static Intent getParentActivityIntent(Activity var0) {
      if (VERSION.SDK_INT >= 16) {
         Intent var1 = var0.getParentActivityIntent();
         if (var1 != null) {
            return var1;
         }
      }

      String var5 = getParentActivityName(var0);
      if (var5 == null) {
         return null;
      } else {
         ComponentName var2 = new ComponentName(var0, var5);

         try {
            Intent var4;
            if (getParentActivityName(var0, var2) == null) {
               var4 = Intent.makeMainActivity(var2);
            } else {
               var4 = new Intent();
               var4 = var4.setComponent(var2);
            }

            return var4;
         } catch (PackageManager.NameNotFoundException var3) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + var5 + "' in manifest");
            return null;
         }
      }
   }

   public static Intent getParentActivityIntent(Context var0, ComponentName var1) throws PackageManager.NameNotFoundException {
      String var2 = getParentActivityName(var0, var1);
      if (var2 == null) {
         return null;
      } else {
         var1 = new ComponentName(var1.getPackageName(), var2);
         Intent var3;
         if (getParentActivityName(var0, var1) == null) {
            var3 = Intent.makeMainActivity(var1);
         } else {
            var3 = (new Intent()).setComponent(var1);
         }

         return var3;
      }
   }

   public static Intent getParentActivityIntent(Context var0, Class var1) throws PackageManager.NameNotFoundException {
      String var3 = getParentActivityName(var0, new ComponentName(var0, var1));
      if (var3 == null) {
         return null;
      } else {
         ComponentName var4 = new ComponentName(var0, var3);
         Intent var2;
         if (getParentActivityName(var0, var4) == null) {
            var2 = Intent.makeMainActivity(var4);
         } else {
            var2 = (new Intent()).setComponent(var4);
         }

         return var2;
      }
   }

   public static String getParentActivityName(Activity var0) {
      try {
         String var2 = getParentActivityName(var0, var0.getComponentName());
         return var2;
      } catch (PackageManager.NameNotFoundException var1) {
         throw new IllegalArgumentException(var1);
      }
   }

   public static String getParentActivityName(Context var0, ComponentName var1) throws PackageManager.NameNotFoundException {
      PackageManager var3 = var0.getPackageManager();
      int var2 = VERSION.SDK_INT;
      var2 = 640;
      if (VERSION.SDK_INT >= 29) {
         var2 = 269222528;
      } else if (VERSION.SDK_INT >= 24) {
         var2 = 787072;
      }

      ActivityInfo var5 = var3.getActivityInfo(var1, var2);
      String var4;
      if (VERSION.SDK_INT >= 16) {
         var4 = var5.parentActivityName;
         if (var4 != null) {
            return var4;
         }
      }

      if (var5.metaData == null) {
         return null;
      } else {
         String var6 = var5.metaData.getString("android.support.PARENT_ACTIVITY");
         if (var6 == null) {
            return null;
         } else {
            var4 = var6;
            if (var6.charAt(0) == '.') {
               var4 = var0.getPackageName() + var6;
            }

            return var4;
         }
      }
   }

   public static void navigateUpFromSameTask(Activity var0) {
      Intent var1 = getParentActivityIntent(var0);
      if (var1 != null) {
         navigateUpTo(var0, var1);
      } else {
         throw new IllegalArgumentException("Activity " + var0.getClass().getSimpleName() + " does not have a parent activity name specified. (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data>  element in your manifest?)");
      }
   }

   public static void navigateUpTo(Activity var0, Intent var1) {
      if (VERSION.SDK_INT >= 16) {
         var0.navigateUpTo(var1);
      } else {
         var1.addFlags(67108864);
         var0.startActivity(var1);
         var0.finish();
      }

   }

   public static boolean shouldUpRecreateTask(Activity var0, Intent var1) {
      if (VERSION.SDK_INT >= 16) {
         return var0.shouldUpRecreateTask(var1);
      } else {
         String var3 = var0.getIntent().getAction();
         boolean var2;
         if (var3 != null && !var3.equals("android.intent.action.MAIN")) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }
   }
}
