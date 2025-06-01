package androidx.legacy.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import java.util.Arrays;

@Deprecated
public class FragmentCompat {
   static final FragmentCompatImpl IMPL;
   private static PermissionCompatDelegate sDelegate;

   static {
      if (VERSION.SDK_INT >= 24) {
         IMPL = new FragmentCompatApi24Impl();
      } else if (VERSION.SDK_INT >= 23) {
         IMPL = new FragmentCompatApi23Impl();
      } else if (VERSION.SDK_INT >= 15) {
         IMPL = new FragmentCompatApi15Impl();
      } else {
         IMPL = new FragmentCompatBaseImpl();
      }

   }

   @Deprecated
   public static PermissionCompatDelegate getPermissionCompatDelegate() {
      return sDelegate;
   }

   @Deprecated
   public static void requestPermissions(Fragment var0, String[] var1, int var2) {
      PermissionCompatDelegate var3 = sDelegate;
      if (var3 == null || !var3.requestPermissions(var0, var1, var2)) {
         IMPL.requestPermissions(var0, var1, var2);
      }
   }

   @Deprecated
   public static void setMenuVisibility(Fragment var0, boolean var1) {
      var0.setMenuVisibility(var1);
   }

   @Deprecated
   public static void setPermissionCompatDelegate(PermissionCompatDelegate var0) {
      sDelegate = var0;
   }

   @Deprecated
   public static void setUserVisibleHint(Fragment var0, boolean var1) {
      IMPL.setUserVisibleHint(var0, var1);
   }

   @Deprecated
   public static boolean shouldShowRequestPermissionRationale(Fragment var0, String var1) {
      return IMPL.shouldShowRequestPermissionRationale(var0, var1);
   }

   static class FragmentCompatApi15Impl extends FragmentCompatBaseImpl {
      public void setUserVisibleHint(Fragment var1, boolean var2) {
         var1.setUserVisibleHint(var2);
      }
   }

   static class FragmentCompatApi23Impl extends FragmentCompatApi15Impl {
      public void requestPermissions(Fragment var1, String[] var2, int var3) {
         var1.requestPermissions(var2, var3);
      }

      public boolean shouldShowRequestPermissionRationale(Fragment var1, String var2) {
         return var1.shouldShowRequestPermissionRationale(var2);
      }
   }

   static class FragmentCompatApi24Impl extends FragmentCompatApi23Impl {
      public void setUserVisibleHint(Fragment var1, boolean var2) {
         var1.setUserVisibleHint(var2);
      }
   }

   static class FragmentCompatBaseImpl implements FragmentCompatImpl {
      public void requestPermissions(Fragment var1, String[] var2, int var3) {
         (new Handler(Looper.getMainLooper())).post(new Runnable(this, var2, var1, var3) {
            final FragmentCompatBaseImpl this$0;
            final Fragment val$fragment;
            final String[] val$permissions;
            final int val$requestCode;

            {
               this.this$0 = var1;
               this.val$permissions = var2;
               this.val$fragment = var3;
               this.val$requestCode = var4;
            }

            public void run() {
               int[] var3 = new int[this.val$permissions.length];
               Activity var5 = this.val$fragment.getActivity();
               if (var5 != null) {
                  PackageManager var4 = var5.getPackageManager();
                  String var6 = var5.getPackageName();
                  int var2 = this.val$permissions.length;

                  for(int var1 = 0; var1 < var2; ++var1) {
                     var3[var1] = var4.checkPermission(this.val$permissions[var1], var6);
                  }
               } else {
                  Arrays.fill(var3, -1);
               }

               ((OnRequestPermissionsResultCallback)this.val$fragment).onRequestPermissionsResult(this.val$requestCode, this.val$permissions, var3);
            }
         });
      }

      public void setUserVisibleHint(Fragment var1, boolean var2) {
      }

      public boolean shouldShowRequestPermissionRationale(Fragment var1, String var2) {
         return false;
      }
   }

   interface FragmentCompatImpl {
      void requestPermissions(Fragment var1, String[] var2, int var3);

      void setUserVisibleHint(Fragment var1, boolean var2);

      boolean shouldShowRequestPermissionRationale(Fragment var1, String var2);
   }

   @Deprecated
   public interface OnRequestPermissionsResultCallback {
      @Deprecated
      void onRequestPermissionsResult(int var1, String[] var2, int[] var3);
   }

   @Deprecated
   public interface PermissionCompatDelegate {
      @Deprecated
      boolean requestPermissions(Fragment var1, String[] var2, int var3);
   }
}
