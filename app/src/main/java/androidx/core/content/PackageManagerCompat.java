package androidx.core.content;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.os.UserManagerCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Executors;

public final class PackageManagerCompat {
   public static final String ACTION_PERMISSION_REVOCATION_SETTINGS = "android.intent.action.AUTO_REVOKE_PERMISSIONS";
   public static final String LOG_TAG = "PackageManagerCompat";

   private PackageManagerCompat() {
   }

   public static boolean areUnusedAppRestrictionsAvailable(PackageManager var0) {
      int var1 = VERSION.SDK_INT;
      boolean var5 = true;
      boolean var6;
      if (var1 >= 30) {
         var6 = true;
      } else {
         var6 = false;
      }

      boolean var2;
      if (VERSION.SDK_INT >= 23 && VERSION.SDK_INT < 30) {
         var2 = true;
      } else {
         var2 = false;
      }

      boolean var3;
      if (getPermissionRevocationVerifierApp(var0) != null) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var4 = var5;
      if (!var6) {
         if (var2 && var3) {
            var4 = var5;
         } else {
            var4 = false;
         }
      }

      return var4;
   }

   public static String getPermissionRevocationVerifierApp(PackageManager var0) {
      Intent var2 = new Intent("android.intent.action.AUTO_REVOKE_PERMISSIONS");
      String var1 = null;
      Iterator var3 = var0.queryIntentActivities(var2.setData(Uri.fromParts("package", "com.example", (String)null)), 0).iterator();

      while(var3.hasNext()) {
         String var4 = ((ResolveInfo)var3.next()).activityInfo.packageName;
         if (var0.checkPermission("android.permission.PACKAGE_VERIFICATION_AGENT", var4) == 0) {
            if (var1 != null) {
               return var1;
            }

            var1 = var4;
         }
      }

      return var1;
   }

   public static ListenableFuture getUnusedAppRestrictionsStatus(Context var0) {
      ResolvableFuture var5 = ResolvableFuture.create();
      boolean var4 = UserManagerCompat.isUserUnlocked(var0);
      Integer var6 = 0;
      if (!var4) {
         var5.set(var6);
         Log.e("PackageManagerCompat", "User is in locked direct boot mode");
         return var5;
      } else if (!areUnusedAppRestrictionsAvailable(var0.getPackageManager())) {
         var5.set(1);
         return var5;
      } else {
         int var2 = var0.getApplicationInfo().targetSdkVersion;
         if (var2 < 30) {
            var5.set(var6);
            Log.e("PackageManagerCompat", "Target SDK version below API 30");
            return var5;
         } else {
            int var3 = VERSION.SDK_INT;
            byte var1 = 4;
            if (var3 >= 31) {
               if (PackageManagerCompat.Api30Impl.areUnusedAppRestrictionsEnabled(var0)) {
                  if (var2 >= 31) {
                     var1 = 5;
                  }

                  var5.set(Integer.valueOf(var1));
               } else {
                  var5.set(2);
               }

               return var5;
            } else if (VERSION.SDK_INT == 30) {
               if (!PackageManagerCompat.Api30Impl.areUnusedAppRestrictionsEnabled(var0)) {
                  var1 = 2;
               }

               var5.set(Integer.valueOf(var1));
               return var5;
            } else {
               UnusedAppRestrictionsBackportServiceConnection var7 = new UnusedAppRestrictionsBackportServiceConnection(var0);
               Objects.requireNonNull(var7);
               var5.addListener(new PackageManagerCompat$$ExternalSyntheticLambda0(var7), Executors.newSingleThreadExecutor());
               var7.connectAndFetchResult(var5);
               return var5;
            }
         }
      }
   }

   private static class Api30Impl {
      static boolean areUnusedAppRestrictionsEnabled(Context var0) {
         return var0.getPackageManager().isAutoRevokeWhitelisted() ^ true;
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface UnusedAppRestrictionsStatus {
   }
}
