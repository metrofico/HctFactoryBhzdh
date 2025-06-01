package androidx.core.content.pm;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Preconditions;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ShortcutManagerCompat {
   static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
   private static final int DEFAULT_MAX_ICON_DIMENSION_DP = 96;
   private static final int DEFAULT_MAX_ICON_DIMENSION_LOWRAM_DP = 48;
   public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
   public static final int FLAG_MATCH_CACHED = 8;
   public static final int FLAG_MATCH_DYNAMIC = 2;
   public static final int FLAG_MATCH_MANIFEST = 1;
   public static final int FLAG_MATCH_PINNED = 4;
   static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
   private static final String SHORTCUT_LISTENER_INTENT_FILTER_ACTION = "androidx.core.content.pm.SHORTCUT_LISTENER";
   private static final String SHORTCUT_LISTENER_META_DATA_KEY = "androidx.core.content.pm.shortcut_listener_impl";
   private static volatile List sShortcutInfoChangeListeners;
   private static volatile ShortcutInfoCompatSaver sShortcutInfoCompatSaver;

   private ShortcutManagerCompat() {
   }

   public static boolean addDynamicShortcuts(Context var0, List var1) {
      if (VERSION.SDK_INT <= 29) {
         convertUriIconsToBitmapIcons(var0, var1);
      }

      if (VERSION.SDK_INT >= 25) {
         ArrayList var2 = new ArrayList();
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            var2.add(((ShortcutInfoCompat)var3.next()).toShortcutInfo());
         }

         if (!((ShortcutManager)var0.getSystemService(ShortcutManager.class)).addDynamicShortcuts(var2)) {
            return false;
         }
      }

      getShortcutInfoSaverInstance(var0).addShortcuts(var1);
      Iterator var4 = getShortcutInfoListeners(var0).iterator();

      while(var4.hasNext()) {
         ((ShortcutInfoChangeListener)var4.next()).onShortcutAdded(var1);
      }

      return true;
   }

   static boolean convertUriIconToBitmapIcon(Context var0, ShortcutInfoCompat var1) {
      if (var1.mIcon == null) {
         return false;
      } else {
         int var2 = var1.mIcon.mType;
         if (var2 != 6 && var2 != 4) {
            return true;
         } else {
            InputStream var3 = var1.mIcon.getUriInputStream(var0);
            if (var3 == null) {
               return false;
            } else {
               Bitmap var4 = BitmapFactory.decodeStream(var3);
               if (var4 == null) {
                  return false;
               } else {
                  IconCompat var5;
                  if (var2 == 6) {
                     var5 = IconCompat.createWithAdaptiveBitmap(var4);
                  } else {
                     var5 = IconCompat.createWithBitmap(var4);
                  }

                  var1.mIcon = var5;
                  return true;
               }
            }
         }
      }
   }

   static void convertUriIconsToBitmapIcons(Context var0, List var1) {
      Iterator var2 = (new ArrayList(var1)).iterator();

      while(var2.hasNext()) {
         ShortcutInfoCompat var3 = (ShortcutInfoCompat)var2.next();
         if (!convertUriIconToBitmapIcon(var0, var3)) {
            var1.remove(var3);
         }
      }

   }

   public static Intent createShortcutResultIntent(Context var0, ShortcutInfoCompat var1) {
      Intent var3;
      if (VERSION.SDK_INT >= 26) {
         var3 = ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).createShortcutResultIntent(var1.toShortcutInfo());
      } else {
         var3 = null;
      }

      Intent var2 = var3;
      if (var3 == null) {
         var2 = new Intent();
      }

      return var1.addToIntent(var2);
   }

   public static void disableShortcuts(Context var0, List var1, CharSequence var2) {
      if (VERSION.SDK_INT >= 25) {
         ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).disableShortcuts(var1, var2);
      }

      getShortcutInfoSaverInstance(var0).removeShortcuts(var1);
      Iterator var3 = getShortcutInfoListeners(var0).iterator();

      while(var3.hasNext()) {
         ((ShortcutInfoChangeListener)var3.next()).onShortcutRemoved(var1);
      }

   }

   public static void enableShortcuts(Context var0, List var1) {
      if (VERSION.SDK_INT >= 25) {
         ArrayList var3 = new ArrayList(var1.size());
         Iterator var2 = var1.iterator();

         while(var2.hasNext()) {
            var3.add(((ShortcutInfoCompat)var2.next()).mId);
         }

         ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).enableShortcuts(var3);
      }

      getShortcutInfoSaverInstance(var0).addShortcuts(var1);
      Iterator var4 = getShortcutInfoListeners(var0).iterator();

      while(var4.hasNext()) {
         ((ShortcutInfoChangeListener)var4.next()).onShortcutAdded(var1);
      }

   }

   public static List getDynamicShortcuts(Context var0) {
      if (VERSION.SDK_INT < 25) {
         try {
            List var4 = getShortcutInfoSaverInstance(var0).getShortcuts();
            return var4;
         } catch (Exception var3) {
            return new ArrayList();
         }
      } else {
         List var2 = ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).getDynamicShortcuts();
         ArrayList var1 = new ArrayList(var2.size());
         Iterator var5 = var2.iterator();

         while(var5.hasNext()) {
            var1.add((new ShortcutInfoCompat.Builder(var0, (ShortcutInfo)var5.next())).build());
         }

         return var1;
      }
   }

   private static int getIconDimensionInternal(Context var0, boolean var1) {
      ActivityManager var4 = (ActivityManager)var0.getSystemService("activity");
      boolean var3;
      if (VERSION.SDK_INT >= 19 && var4 != null && !var4.isLowRamDevice()) {
         var3 = false;
      } else {
         var3 = true;
      }

      int var6;
      if (var3) {
         var6 = 48;
      } else {
         var6 = 96;
      }

      var6 = Math.max(1, var6);
      DisplayMetrics var5 = var0.getResources().getDisplayMetrics();
      float var2;
      if (var1) {
         var2 = var5.xdpi;
      } else {
         var2 = var5.ydpi;
      }

      var2 /= 160.0F;
      return (int)((float)var6 * var2);
   }

   public static int getIconMaxHeight(Context var0) {
      Preconditions.checkNotNull(var0);
      return VERSION.SDK_INT >= 25 ? ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).getIconMaxHeight() : getIconDimensionInternal(var0, false);
   }

   public static int getIconMaxWidth(Context var0) {
      Preconditions.checkNotNull(var0);
      return VERSION.SDK_INT >= 25 ? ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).getIconMaxWidth() : getIconDimensionInternal(var0, true);
   }

   public static int getMaxShortcutCountPerActivity(Context var0) {
      Preconditions.checkNotNull(var0);
      return VERSION.SDK_INT >= 25 ? ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).getMaxShortcutCountPerActivity() : 5;
   }

   static List getShortcutInfoChangeListeners() {
      return sShortcutInfoChangeListeners;
   }

   private static String getShortcutInfoCompatWithLowestRank(List var0) {
      Iterator var2 = var0.iterator();
      int var1 = -1;
      String var4 = null;

      while(var2.hasNext()) {
         ShortcutInfoCompat var3 = (ShortcutInfoCompat)var2.next();
         if (var3.getRank() > var1) {
            var4 = var3.getId();
            var1 = var3.getRank();
         }
      }

      return var4;
   }

   private static List getShortcutInfoListeners(Context var0) {
      if (sShortcutInfoChangeListeners == null) {
         ArrayList var1 = new ArrayList();
         if (VERSION.SDK_INT >= 21) {
            PackageManager var3 = var0.getPackageManager();
            Intent var2 = new Intent("androidx.core.content.pm.SHORTCUT_LISTENER");
            var2.setPackage(var0.getPackageName());
            Iterator var5 = var3.queryIntentActivities(var2, 128).iterator();

            while(var5.hasNext()) {
               ActivityInfo var6 = ((ResolveInfo)var5.next()).activityInfo;
               if (var6 != null) {
                  Bundle var7 = var6.metaData;
                  if (var7 != null) {
                     String var8 = var7.getString("androidx.core.content.pm.shortcut_listener_impl");
                     if (var8 != null) {
                        try {
                           var1.add((ShortcutInfoChangeListener)Class.forName(var8, false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke((Object)null, var0));
                        } catch (Exception var4) {
                        }
                     }
                  }
               }
            }
         }

         if (sShortcutInfoChangeListeners == null) {
            sShortcutInfoChangeListeners = var1;
         }
      }

      return sShortcutInfoChangeListeners;
   }

   private static ShortcutInfoCompatSaver getShortcutInfoSaverInstance(Context var0) {
      if (sShortcutInfoCompatSaver == null) {
         if (VERSION.SDK_INT >= 23) {
            try {
               sShortcutInfoCompatSaver = (ShortcutInfoCompatSaver)Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke((Object)null, var0);
            } catch (Exception var1) {
            }
         }

         if (sShortcutInfoCompatSaver == null) {
            sShortcutInfoCompatSaver = new ShortcutInfoCompatSaver.NoopImpl();
         }
      }

      return sShortcutInfoCompatSaver;
   }

   public static List getShortcuts(Context var0, int var1) {
      if (VERSION.SDK_INT >= 30) {
         return ShortcutInfoCompat.fromShortcuts(var0, ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).getShortcuts(var1));
      } else if (VERSION.SDK_INT >= 25) {
         ShortcutManager var3 = (ShortcutManager)var0.getSystemService(ShortcutManager.class);
         ArrayList var2 = new ArrayList();
         if ((var1 & 1) != 0) {
            var2.addAll(var3.getManifestShortcuts());
         }

         if ((var1 & 2) != 0) {
            var2.addAll(var3.getDynamicShortcuts());
         }

         if ((var1 & 4) != 0) {
            var2.addAll(var3.getPinnedShortcuts());
         }

         return ShortcutInfoCompat.fromShortcuts(var0, var2);
      } else {
         if ((var1 & 2) != 0) {
            try {
               List var5 = getShortcutInfoSaverInstance(var0).getShortcuts();
               return var5;
            } catch (Exception var4) {
            }
         }

         return Collections.emptyList();
      }
   }

   public static boolean isRateLimitingActive(Context var0) {
      Preconditions.checkNotNull(var0);
      if (VERSION.SDK_INT >= 25) {
         return ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).isRateLimitingActive();
      } else {
         boolean var1;
         if (getShortcuts(var0, 3).size() == getMaxShortcutCountPerActivity(var0)) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }
   }

   public static boolean isRequestPinShortcutSupported(Context var0) {
      if (VERSION.SDK_INT >= 26) {
         return ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).isRequestPinShortcutSupported();
      } else if (ContextCompat.checkSelfPermission(var0, "com.android.launcher.permission.INSTALL_SHORTCUT") != 0) {
         return false;
      } else {
         Iterator var2 = var0.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0).iterator();

         String var1;
         do {
            if (!var2.hasNext()) {
               return false;
            }

            var1 = ((ResolveInfo)var2.next()).activityInfo.permission;
         } while(!TextUtils.isEmpty(var1) && !"com.android.launcher.permission.INSTALL_SHORTCUT".equals(var1));

         return true;
      }
   }

   public static boolean pushDynamicShortcut(Context var0, ShortcutInfoCompat var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      int var2 = getMaxShortcutCountPerActivity(var0);
      if (var2 == 0) {
         return false;
      } else {
         if (VERSION.SDK_INT <= 29) {
            convertUriIconToBitmapIcon(var0, var1);
         }

         if (VERSION.SDK_INT >= 30) {
            ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).pushDynamicShortcut(var1.toShortcutInfo());
         } else if (VERSION.SDK_INT >= 25) {
            ShortcutManager var4 = (ShortcutManager)var0.getSystemService(ShortcutManager.class);
            if (var4.isRateLimitingActive()) {
               return false;
            }

            List var3 = var4.getDynamicShortcuts();
            if (var3.size() >= var2) {
               var4.removeDynamicShortcuts(Arrays.asList(ShortcutManagerCompat.Api25Impl.getShortcutInfoWithLowestRank(var3)));
            }

            var4.addDynamicShortcuts(Arrays.asList(var1.toShortcutInfo()));
         }

         ShortcutInfoCompatSaver var9 = getShortcutInfoSaverInstance(var0);

         try {
            List var11 = var9.getShortcuts();
            if (var11.size() >= var2) {
               var9.removeShortcuts(Arrays.asList(getShortcutInfoCompatWithLowestRank(var11)));
            }

            var9.addShortcuts(Arrays.asList(var1));
            return true;
         } catch (Exception var7) {
         } finally {
            Iterator var10 = getShortcutInfoListeners(var0).iterator();

            while(var10.hasNext()) {
               ((ShortcutInfoChangeListener)var10.next()).onShortcutAdded(Collections.singletonList(var1));
            }

            reportShortcutUsed(var0, var1.getId());
         }

         return false;
      }
   }

   public static void removeAllDynamicShortcuts(Context var0) {
      if (VERSION.SDK_INT >= 25) {
         ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).removeAllDynamicShortcuts();
      }

      getShortcutInfoSaverInstance(var0).removeAllShortcuts();
      Iterator var1 = getShortcutInfoListeners(var0).iterator();

      while(var1.hasNext()) {
         ((ShortcutInfoChangeListener)var1.next()).onAllShortcutsRemoved();
      }

   }

   public static void removeDynamicShortcuts(Context var0, List var1) {
      if (VERSION.SDK_INT >= 25) {
         ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).removeDynamicShortcuts(var1);
      }

      getShortcutInfoSaverInstance(var0).removeShortcuts(var1);
      Iterator var2 = getShortcutInfoListeners(var0).iterator();

      while(var2.hasNext()) {
         ((ShortcutInfoChangeListener)var2.next()).onShortcutRemoved(var1);
      }

   }

   public static void removeLongLivedShortcuts(Context var0, List var1) {
      if (VERSION.SDK_INT < 30) {
         removeDynamicShortcuts(var0, var1);
      } else {
         ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).removeLongLivedShortcuts(var1);
         getShortcutInfoSaverInstance(var0).removeShortcuts(var1);
         Iterator var2 = getShortcutInfoListeners(var0).iterator();

         while(var2.hasNext()) {
            ((ShortcutInfoChangeListener)var2.next()).onShortcutRemoved(var1);
         }

      }
   }

   public static void reportShortcutUsed(Context var0, String var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      if (VERSION.SDK_INT >= 25) {
         ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).reportShortcutUsed(var1);
      }

      Iterator var2 = getShortcutInfoListeners(var0).iterator();

      while(var2.hasNext()) {
         ((ShortcutInfoChangeListener)var2.next()).onShortcutUsageReported(Collections.singletonList(var1));
      }

   }

   public static boolean requestPinShortcut(Context var0, ShortcutInfoCompat var1, IntentSender var2) {
      if (VERSION.SDK_INT >= 26) {
         return ((ShortcutManager)var0.getSystemService(ShortcutManager.class)).requestPinShortcut(var1.toShortcutInfo(), var2);
      } else if (!isRequestPinShortcutSupported(var0)) {
         return false;
      } else {
         Intent var3 = var1.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
         if (var2 == null) {
            var0.sendBroadcast(var3);
            return true;
         } else {
            var0.sendOrderedBroadcast(var3, (String)null, new BroadcastReceiver(var2) {
               final IntentSender val$callback;

               {
                  this.val$callback = var1;
               }

               public void onReceive(Context var1, Intent var2) {
                  try {
                     this.val$callback.sendIntent(var1, 0, (Intent)null, (IntentSender.OnFinished)null, (Handler)null);
                  } catch (IntentSender.SendIntentException var3) {
                  }

               }
            }, (Handler)null, -1, (String)null, (Bundle)null);
            return true;
         }
      }
   }

   public static boolean setDynamicShortcuts(Context var0, List var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      if (VERSION.SDK_INT >= 25) {
         ArrayList var2 = new ArrayList(var1.size());
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            var2.add(((ShortcutInfoCompat)var3.next()).toShortcutInfo());
         }

         if (!((ShortcutManager)var0.getSystemService(ShortcutManager.class)).setDynamicShortcuts(var2)) {
            return false;
         }
      }

      getShortcutInfoSaverInstance(var0).removeAllShortcuts();
      getShortcutInfoSaverInstance(var0).addShortcuts(var1);
      Iterator var4 = getShortcutInfoListeners(var0).iterator();

      while(var4.hasNext()) {
         ShortcutInfoChangeListener var5 = (ShortcutInfoChangeListener)var4.next();
         var5.onAllShortcutsRemoved();
         var5.onShortcutAdded(var1);
      }

      return true;
   }

   static void setShortcutInfoChangeListeners(List var0) {
      sShortcutInfoChangeListeners = var0;
   }

   static void setShortcutInfoCompatSaver(ShortcutInfoCompatSaver var0) {
      sShortcutInfoCompatSaver = var0;
   }

   public static boolean updateShortcuts(Context var0, List var1) {
      if (VERSION.SDK_INT <= 29) {
         convertUriIconsToBitmapIcons(var0, var1);
      }

      if (VERSION.SDK_INT >= 25) {
         ArrayList var2 = new ArrayList();
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            var2.add(((ShortcutInfoCompat)var3.next()).toShortcutInfo());
         }

         if (!((ShortcutManager)var0.getSystemService(ShortcutManager.class)).updateShortcuts(var2)) {
            return false;
         }
      }

      getShortcutInfoSaverInstance(var0).addShortcuts(var1);
      Iterator var4 = getShortcutInfoListeners(var0).iterator();

      while(var4.hasNext()) {
         ((ShortcutInfoChangeListener)var4.next()).onShortcutUpdated(var1);
      }

      return true;
   }

   private static class Api25Impl {
      static String getShortcutInfoWithLowestRank(List var0) {
         Iterator var2 = var0.iterator();
         int var1 = -1;
         String var4 = null;

         while(var2.hasNext()) {
            ShortcutInfo var3 = (ShortcutInfo)var2.next();
            if (var3.getRank() > var1) {
               var4 = var3.getId();
               var1 = var3.getRank();
            }
         }

         return var4;
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ShortcutMatchFlags {
   }
}
