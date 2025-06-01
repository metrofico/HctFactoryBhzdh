package com.hzbhd.ui.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000fJ\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"},
   d2 = {"Lcom/hzbhd/ui/util/ResourcesUtil;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "carSystemContext", "getCarSystemContext", "()Landroid/content/Context;", "mCarSystemUIContext", "mContext", "mPackageManager", "Landroid/content/pm/PackageManager;", "getDrawable", "Landroid/graphics/drawable/Drawable;", "packageName", "", "idName", "id", "", "getPackageView", "Landroid/view/View;", "packageContext", "type", "name", "getResources", "Landroid/content/res/Resources;", "Companion", "java-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ResourcesUtil {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String PACKAGE_CAR_SYSTEMUI = "com.hzbhd.carsystemui";
   private static ResourcesUtil mResourcesUtil;
   private final Context mCarSystemUIContext;
   private final Context mContext;
   private final PackageManager mPackageManager;

   private ResourcesUtil(Context var1) {
      Context var2 = var1.getApplicationContext();
      Intrinsics.checkNotNullExpressionValue(var2, "context.applicationContext");
      this.mContext = var2;
      PackageManager var3 = var1.getPackageManager();
      Intrinsics.checkNotNullExpressionValue(var3, "context.packageManager");
      this.mPackageManager = var3;
   }

   // $FF: synthetic method
   public ResourcesUtil(Context var1, DefaultConstructorMarker var2) {
      this(var1);
   }

   public final Context getCarSystemContext() {
      if (this.mCarSystemUIContext == null) {
         try {
            this.mContext.createPackageContext("com.hzbhd.carsystemui", 3);
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

      return this.mCarSystemUIContext;
   }

   public final Drawable getDrawable(String var1, int var2) {
      Resources var4 = this.getResources(var1);

      boolean var10001;
      Drawable var3;
      try {
         Intrinsics.checkNotNull(var4);
         var3 = var4.getDrawableForDensity(var2, 480, (Resources.Theme)null);
      } catch (Exception var8) {
         var10001 = false;
         return null;
      }

      Drawable var9 = var3;
      if (var3 == null) {
         try {
            var9 = var4.getDrawableForDensity(var2, 320, (Resources.Theme)null);
         } catch (Exception var7) {
            var10001 = false;
            return null;
         }
      }

      var3 = var9;
      if (var9 == null) {
         try {
            var3 = var4.getDrawableForDensity(var2, 240, (Resources.Theme)null);
         } catch (Exception var6) {
            var10001 = false;
            return null;
         }
      }

      var9 = var3;
      if (var3 != null) {
         return var9;
      } else {
         try {
            var9 = var4.getDrawableForDensity(var2, 120, (Resources.Theme)null);
            return var9;
         } catch (Exception var5) {
            var10001 = false;
            return null;
         }
      }
   }

   public final Drawable getDrawable(String var1, String var2) {
      Intrinsics.checkNotNullParameter(var1, "packageName");
      Resources var3 = this.getResources(var1);
      Log.d("ResourcesUtil", "getDrawable: " + var1 + " : " + var2);
      Intrinsics.checkNotNull(var3);
      Drawable var4 = var3.getDrawable(var3.getIdentifier(var2, "drawable", var1), (Resources.Theme)null);
      Intrinsics.checkNotNullExpressionValue(var4, "resources!!.getDrawable(…ble\", packageName), null)");
      return var4;
   }

   public final View getPackageView(Context var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var1, "packageContext");
      View var4 = View.inflate(var1, var1.getResources().getIdentifier(var1.getPackageName(), var2, var3), (ViewGroup)null);
      Intrinsics.checkNotNullExpressionValue(var4, "inflate(packageContext,\n…e),\n                null)");
      return var4;
   }

   public final Resources getResources(String var1) {
      try {
         Resources var3 = this.mPackageManager.getResourcesForApplication(var1);
         return var3;
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"},
      d2 = {"Lcom/hzbhd/ui/util/ResourcesUtil$Companion;", "", "()V", "PACKAGE_CAR_SYSTEMUI", "", "mResourcesUtil", "Lcom/hzbhd/ui/util/ResourcesUtil;", "getResourcesUtil", "context", "Landroid/content/Context;", "java-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      public final ResourcesUtil getResourcesUtil(Context var1) {
         Intrinsics.checkNotNullParameter(var1, "context");
         if (ResourcesUtil.mResourcesUtil == null) {
            ResourcesUtil.mResourcesUtil = new ResourcesUtil(var1, (DefaultConstructorMarker)null);
         }

         return ResourcesUtil.mResourcesUtil;
      }
   }
}
