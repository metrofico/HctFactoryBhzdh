package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build.VERSION;

public final class AppOpsManagerCompat {
   public static final int MODE_ALLOWED = 0;
   public static final int MODE_DEFAULT = 3;
   public static final int MODE_ERRORED = 2;
   public static final int MODE_IGNORED = 1;

   private AppOpsManagerCompat() {
   }

   public static int checkOrNoteProxyOp(Context var0, int var1, String var2, String var3) {
      if (VERSION.SDK_INT >= 29) {
         AppOpsManager var5 = AppOpsManagerCompat.Api29Impl.getSystemService(var0);
         int var4 = AppOpsManagerCompat.Api29Impl.checkOpNoThrow(var5, var2, Binder.getCallingUid(), var3);
         return var4 != 0 ? var4 : AppOpsManagerCompat.Api29Impl.checkOpNoThrow(var5, var2, var1, var0.getOpPackageName());
      } else {
         return noteProxyOpNoThrow(var0, var2, var3);
      }
   }

   public static int noteOp(Context var0, String var1, int var2, String var3) {
      return VERSION.SDK_INT >= 19 ? ((AppOpsManager)var0.getSystemService("appops")).noteOp(var1, var2, var3) : 1;
   }

   public static int noteOpNoThrow(Context var0, String var1, int var2, String var3) {
      return VERSION.SDK_INT >= 19 ? ((AppOpsManager)var0.getSystemService("appops")).noteOpNoThrow(var1, var2, var3) : 1;
   }

   public static int noteProxyOp(Context var0, String var1, String var2) {
      return VERSION.SDK_INT >= 23 ? ((AppOpsManager)var0.getSystemService(AppOpsManager.class)).noteProxyOp(var1, var2) : 1;
   }

   public static int noteProxyOpNoThrow(Context var0, String var1, String var2) {
      return VERSION.SDK_INT >= 23 ? ((AppOpsManager)var0.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(var1, var2) : 1;
   }

   public static String permissionToOp(String var0) {
      return VERSION.SDK_INT >= 23 ? AppOpsManager.permissionToOp(var0) : null;
   }

   static class Api29Impl {
      private Api29Impl() {
      }

      static int checkOpNoThrow(AppOpsManager var0, String var1, int var2, String var3) {
         return var0 == null ? 1 : var0.checkOpNoThrow(var1, var2, var3);
      }

      static String getOpPackageName(Context var0) {
         return var0.getOpPackageName();
      }

      static AppOpsManager getSystemService(Context var0) {
         return (AppOpsManager)var0.getSystemService(AppOpsManager.class);
      }
   }
}
