package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TintContextWrapper extends ContextWrapper {
   private static final Object CACHE_LOCK = new Object();
   private static ArrayList sCache;
   private final Resources mResources;
   private final Resources.Theme mTheme;

   private TintContextWrapper(Context var1) {
      super(var1);
      if (VectorEnabledTintResources.shouldBeUsed()) {
         VectorEnabledTintResources var2 = new VectorEnabledTintResources(this, var1.getResources());
         this.mResources = var2;
         Resources.Theme var3 = var2.newTheme();
         this.mTheme = var3;
         var3.setTo(var1.getTheme());
      } else {
         this.mResources = new TintResources(this, var1.getResources());
         this.mTheme = null;
      }

   }

   private static boolean shouldWrap(Context var0) {
      boolean var3 = var0 instanceof TintContextWrapper;
      boolean var2 = false;
      boolean var1 = var2;
      if (!var3) {
         var1 = var2;
         if (!(var0.getResources() instanceof TintResources)) {
            if (var0.getResources() instanceof VectorEnabledTintResources) {
               var1 = var2;
            } else {
               if (VERSION.SDK_INT >= 21) {
                  var1 = var2;
                  if (!VectorEnabledTintResources.shouldBeUsed()) {
                     return var1;
                  }
               }

               var1 = true;
            }
         }
      }

      return var1;
   }

   public static Context wrap(Context var0) {
      if (shouldWrap(var0)) {
         Object var3 = CACHE_LOCK;
         synchronized(var3){}

         Throwable var10000;
         boolean var10001;
         label1300: {
            ArrayList var2;
            try {
               var2 = sCache;
            } catch (Throwable var160) {
               var10000 = var160;
               var10001 = false;
               break label1300;
            }

            TintContextWrapper var164;
            if (var2 == null) {
               try {
                  var2 = new ArrayList();
                  sCache = var2;
               } catch (Throwable var158) {
                  var10000 = var158;
                  var10001 = false;
                  break label1300;
               }
            } else {
               int var1;
               try {
                  var1 = var2.size() - 1;
               } catch (Throwable var157) {
                  var10000 = var157;
                  var10001 = false;
                  break label1300;
               }

               WeakReference var163;
               for(; var1 >= 0; --var1) {
                  try {
                     var163 = (WeakReference)sCache.get(var1);
                  } catch (Throwable var156) {
                     var10000 = var156;
                     var10001 = false;
                     break label1300;
                  }

                  if (var163 != null) {
                     try {
                        if (var163.get() != null) {
                           continue;
                        }
                     } catch (Throwable var159) {
                        var10000 = var159;
                        var10001 = false;
                        break label1300;
                     }
                  }

                  try {
                     sCache.remove(var1);
                  } catch (Throwable var155) {
                     var10000 = var155;
                     var10001 = false;
                     break label1300;
                  }
               }

               try {
                  var1 = sCache.size() - 1;
               } catch (Throwable var154) {
                  var10000 = var154;
                  var10001 = false;
                  break label1300;
               }

               for(; var1 >= 0; --var1) {
                  try {
                     var163 = (WeakReference)sCache.get(var1);
                  } catch (Throwable var153) {
                     var10000 = var153;
                     var10001 = false;
                     break label1300;
                  }

                  if (var163 != null) {
                     try {
                        var164 = (TintContextWrapper)var163.get();
                     } catch (Throwable var152) {
                        var10000 = var152;
                        var10001 = false;
                        break label1300;
                     }
                  } else {
                     var164 = null;
                  }

                  if (var164 != null) {
                     try {
                        if (var164.getBaseContext() == var0) {
                           return var164;
                        }
                     } catch (Throwable var151) {
                        var10000 = var151;
                        var10001 = false;
                        break label1300;
                     }
                  }
               }
            }

            label1256:
            try {
               var164 = new TintContextWrapper(var0);
               ArrayList var4 = sCache;
               WeakReference var162 = new WeakReference(var164);
               var4.add(var162);
               return var164;
            } catch (Throwable var150) {
               var10000 = var150;
               var10001 = false;
               break label1256;
            }
         }

         while(true) {
            Throwable var161 = var10000;

            try {
               throw var161;
            } catch (Throwable var149) {
               var10000 = var149;
               var10001 = false;
               continue;
            }
         }
      } else {
         return var0;
      }
   }

   public AssetManager getAssets() {
      return this.mResources.getAssets();
   }

   public Resources getResources() {
      return this.mResources;
   }

   public Resources.Theme getTheme() {
      Resources.Theme var2 = this.mTheme;
      Resources.Theme var1 = var2;
      if (var2 == null) {
         var1 = super.getTheme();
      }

      return var1;
   }

   public void setTheme(int var1) {
      Resources.Theme var2 = this.mTheme;
      if (var2 == null) {
         super.setTheme(var1);
      } else {
         var2.applyStyle(var1, true);
      }

   }
}
