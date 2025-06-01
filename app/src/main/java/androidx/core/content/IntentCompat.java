package androidx.core.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import androidx.core.util.Preconditions;

public final class IntentCompat {
   public static final String ACTION_CREATE_REMINDER = "android.intent.action.CREATE_REMINDER";
   public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
   public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
   public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";
   public static final String EXTRA_TIME = "android.intent.extra.TIME";

   private IntentCompat() {
   }

   public static Intent createManageUnusedAppRestrictionsIntent(Context var0, String var1) {
      if (PackageManagerCompat.areUnusedAppRestrictionsAvailable(var0.getPackageManager())) {
         if (VERSION.SDK_INT >= 31) {
            return (new Intent("android.settings.APPLICATION_DETAILS_SETTINGS")).setData(Uri.fromParts("package", var1, (String)null));
         } else {
            Intent var2 = (new Intent("android.intent.action.AUTO_REVOKE_PERMISSIONS")).setData(Uri.fromParts("package", var1, (String)null));
            return VERSION.SDK_INT >= 30 ? var2 : var2.setPackage((String)Preconditions.checkNotNull(PackageManagerCompat.getPermissionRevocationVerifierApp(var0.getPackageManager())));
         }
      } else {
         throw new UnsupportedOperationException("Unused App Restriction features are not available on this device");
      }
   }

   public static Intent makeMainSelectorActivity(String var0, String var1) {
      if (VERSION.SDK_INT >= 15) {
         return Intent.makeMainSelectorActivity(var0, var1);
      } else {
         Intent var2 = new Intent(var0);
         var2.addCategory(var1);
         return var2;
      }
   }
}
