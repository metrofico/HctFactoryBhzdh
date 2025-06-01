package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
   public static final int ID_NULL = 0;
   private static final String TAG = "ResourcesCompat";
   private static final Object sColorStateCacheLock = new Object();
   private static final WeakHashMap sColorStateCaches = new WeakHashMap(0);
   private static final ThreadLocal sTempTypedValue = new ThreadLocal();

   private ResourcesCompat() {
   }

   private static void addColorStateListToCache(ColorStateListCacheKey var0, int var1, ColorStateList var2) {
      Object var5 = sColorStateCacheLock;
      synchronized(var5){}

      Throwable var10000;
      boolean var10001;
      label176: {
         SparseArray var4;
         WeakHashMap var6;
         try {
            var6 = sColorStateCaches;
            var4 = (SparseArray)var6.get(var0);
         } catch (Throwable var26) {
            var10000 = var26;
            var10001 = false;
            break label176;
         }

         SparseArray var3 = var4;
         if (var4 == null) {
            try {
               var3 = new SparseArray();
               var6.put(var0, var3);
            } catch (Throwable var25) {
               var10000 = var25;
               var10001 = false;
               break label176;
            }
         }

         label165:
         try {
            ColorStateListCacheEntry var28 = new ColorStateListCacheEntry(var2, var0.mResources.getConfiguration());
            var3.append(var1, var28);
            return;
         } catch (Throwable var24) {
            var10000 = var24;
            var10001 = false;
            break label165;
         }
      }

      while(true) {
         Throwable var27 = var10000;

         try {
            throw var27;
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            continue;
         }
      }
   }

   private static ColorStateList getCachedColorStateList(ColorStateListCacheKey var0, int var1) {
      Object var2 = sColorStateCacheLock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label404: {
         SparseArray var4;
         try {
            var4 = (SparseArray)sColorStateCaches.get(var0);
         } catch (Throwable var44) {
            var10000 = var44;
            var10001 = false;
            break label404;
         }

         if (var4 != null) {
            label402: {
               ColorStateListCacheEntry var3;
               try {
                  if (var4.size() <= 0) {
                     break label402;
                  }

                  var3 = (ColorStateListCacheEntry)var4.get(var1);
               } catch (Throwable var45) {
                  var10000 = var45;
                  var10001 = false;
                  break label404;
               }

               if (var3 != null) {
                  try {
                     if (var3.mConfiguration.equals(var0.mResources.getConfiguration())) {
                        ColorStateList var47 = var3.mValue;
                        return var47;
                     }
                  } catch (Throwable var46) {
                     var10000 = var46;
                     var10001 = false;
                     break label404;
                  }

                  try {
                     var4.remove(var1);
                  } catch (Throwable var43) {
                     var10000 = var43;
                     var10001 = false;
                     break label404;
                  }
               }
            }
         }

         label375:
         try {
            return null;
         } catch (Throwable var42) {
            var10000 = var42;
            var10001 = false;
            break label375;
         }
      }

      while(true) {
         Throwable var48 = var10000;

         try {
            throw var48;
         } catch (Throwable var41) {
            var10000 = var41;
            var10001 = false;
            continue;
         }
      }
   }

   public static Typeface getCachedFont(Context var0, int var1) throws Resources.NotFoundException {
      return var0.isRestricted() ? null : loadFont(var0, var1, new TypedValue(), 0, (FontCallback)null, (Handler)null, false, true);
   }

   public static int getColor(Resources var0, int var1, Resources.Theme var2) throws Resources.NotFoundException {
      return VERSION.SDK_INT >= 23 ? var0.getColor(var1, var2) : var0.getColor(var1);
   }

   public static ColorStateList getColorStateList(Resources var0, int var1, Resources.Theme var2) throws Resources.NotFoundException {
      ColorStateListCacheKey var3 = new ColorStateListCacheKey(var0, var2);
      ColorStateList var4 = getCachedColorStateList(var3, var1);
      if (var4 != null) {
         return var4;
      } else {
         var4 = inflateColorStateList(var0, var1, var2);
         if (var4 != null) {
            addColorStateListToCache(var3, var1, var4);
            return var4;
         } else {
            return VERSION.SDK_INT >= 23 ? var0.getColorStateList(var1, var2) : var0.getColorStateList(var1);
         }
      }
   }

   public static Drawable getDrawable(Resources var0, int var1, Resources.Theme var2) throws Resources.NotFoundException {
      return VERSION.SDK_INT >= 21 ? var0.getDrawable(var1, var2) : var0.getDrawable(var1);
   }

   public static Drawable getDrawableForDensity(Resources var0, int var1, int var2, Resources.Theme var3) throws Resources.NotFoundException {
      if (VERSION.SDK_INT >= 21) {
         return var0.getDrawableForDensity(var1, var2, var3);
      } else {
         return VERSION.SDK_INT >= 15 ? var0.getDrawableForDensity(var1, var2) : var0.getDrawable(var1);
      }
   }

   public static float getFloat(Resources var0, int var1) {
      if (VERSION.SDK_INT >= 29) {
         return var0.getFloat(var1);
      } else {
         TypedValue var2 = getTypedValue();
         var0.getValue(var1, var2, true);
         if (var2.type == 4) {
            return var2.getFloat();
         } else {
            throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(var1) + " type #0x" + Integer.toHexString(var2.type) + " is not valid");
         }
      }
   }

   public static Typeface getFont(Context var0, int var1) throws Resources.NotFoundException {
      return var0.isRestricted() ? null : loadFont(var0, var1, new TypedValue(), 0, (FontCallback)null, (Handler)null, false, false);
   }

   public static Typeface getFont(Context var0, int var1, TypedValue var2, int var3, FontCallback var4) throws Resources.NotFoundException {
      return var0.isRestricted() ? null : loadFont(var0, var1, var2, var3, var4, (Handler)null, true, false);
   }

   public static void getFont(Context var0, int var1, FontCallback var2, Handler var3) throws Resources.NotFoundException {
      Preconditions.checkNotNull(var2);
      if (var0.isRestricted()) {
         var2.callbackFailAsync(-4, var3);
      } else {
         loadFont(var0, var1, new TypedValue(), 0, var2, var3, false, false);
      }
   }

   private static TypedValue getTypedValue() {
      ThreadLocal var2 = sTempTypedValue;
      TypedValue var1 = (TypedValue)var2.get();
      TypedValue var0 = var1;
      if (var1 == null) {
         var0 = new TypedValue();
         var2.set(var0);
      }

      return var0;
   }

   private static ColorStateList inflateColorStateList(Resources var0, int var1, Resources.Theme var2) {
      if (isColorInt(var0, var1)) {
         return null;
      } else {
         XmlResourceParser var3 = var0.getXml(var1);

         try {
            ColorStateList var5 = ColorStateListInflaterCompat.createFromXml(var0, var3, var2);
            return var5;
         } catch (Exception var4) {
            Log.w("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", var4);
            return null;
         }
      }
   }

   private static boolean isColorInt(Resources var0, int var1) {
      TypedValue var3 = getTypedValue();
      boolean var2 = true;
      var0.getValue(var1, var3, true);
      if (var3.type < 28 || var3.type > 31) {
         var2 = false;
      }

      return var2;
   }

   private static Typeface loadFont(Context var0, int var1, TypedValue var2, int var3, FontCallback var4, Handler var5, boolean var6, boolean var7) {
      Resources var8 = var0.getResources();
      var8.getValue(var1, var2, true);
      Typeface var9 = loadFont(var0, var8, var2, var1, var3, var4, var5, var6, var7);
      if (var9 == null && var4 == null && !var7) {
         throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(var1) + " could not be retrieved.");
      } else {
         return var9;
      }
   }

   private static Typeface loadFont(Context var0, Resources var1, TypedValue var2, int var3, int var4, FontCallback var5, Handler var6, boolean var7, boolean var8) {
      if (var2.string != null) {
         String var27 = var2.string.toString();
         if (!var27.startsWith("res/")) {
            if (var5 != null) {
               var5.callbackFailAsync(-3, var6);
            }

            return null;
         } else {
            Typeface var9 = TypefaceCompat.findFromCache(var1, var3, var4);
            if (var9 != null) {
               if (var5 != null) {
                  var5.callbackSuccessAsync(var9, var6);
               }

               return var9;
            } else if (var8) {
               return null;
            } else {
               label135: {
                  XmlPullParserException var29;
                  label105: {
                     IOException var10000;
                     label117: {
                        boolean var10001;
                        label103: {
                           FontResourcesParserCompat.FamilyResourceEntry var28;
                           try {
                              if (!var27.toLowerCase().endsWith(".xml")) {
                                 break label103;
                              }

                              var28 = FontResourcesParserCompat.parse(var1.getXml(var3), var1);
                           } catch (XmlPullParserException var22) {
                              var29 = var22;
                              var10001 = false;
                              break label105;
                           } catch (IOException var23) {
                              var10000 = var23;
                              var10001 = false;
                              break label117;
                           }

                           if (var28 == null) {
                              try {
                                 Log.e("ResourcesCompat", "Failed to find font-family tag");
                              } catch (XmlPullParserException var12) {
                                 var29 = var12;
                                 var10001 = false;
                                 break label105;
                              } catch (IOException var13) {
                                 var10000 = var13;
                                 var10001 = false;
                                 break label117;
                              }

                              if (var5 == null) {
                                 return null;
                              }

                              try {
                                 var5.callbackFailAsync(-3, var6);
                                 return null;
                              } catch (XmlPullParserException var10) {
                                 var29 = var10;
                                 var10001 = false;
                                 break label105;
                              } catch (IOException var11) {
                                 var10000 = var11;
                                 var10001 = false;
                                 break label117;
                              }
                           } else {
                              try {
                                 return TypefaceCompat.createFromResourcesFamilyXml(var0, var28, var1, var3, var4, var5, var6, var7);
                              } catch (XmlPullParserException var14) {
                                 var29 = var14;
                                 var10001 = false;
                                 break label105;
                              } catch (IOException var15) {
                                 var10000 = var15;
                                 var10001 = false;
                                 break label117;
                              }
                           }
                        }

                        Typeface var24;
                        try {
                           var24 = TypefaceCompat.createFromResourcesFontFile(var0, var1, var3, var27, var4);
                        } catch (XmlPullParserException var20) {
                           var29 = var20;
                           var10001 = false;
                           break label105;
                        } catch (IOException var21) {
                           var10000 = var21;
                           var10001 = false;
                           break label117;
                        }

                        if (var5 == null) {
                           return var24;
                        }

                        if (var24 != null) {
                           try {
                              var5.callbackSuccessAsync(var24, var6);
                              return var24;
                           } catch (XmlPullParserException var16) {
                              var29 = var16;
                              var10001 = false;
                              break label105;
                           } catch (IOException var17) {
                              var10000 = var17;
                              var10001 = false;
                           }
                        } else {
                           try {
                              var5.callbackFailAsync(-3, var6);
                              return var24;
                           } catch (XmlPullParserException var18) {
                              var29 = var18;
                              var10001 = false;
                              break label105;
                           } catch (IOException var19) {
                              var10000 = var19;
                              var10001 = false;
                           }
                        }
                     }

                     IOException var25 = var10000;
                     Log.e("ResourcesCompat", "Failed to read xml resource " + var27, var25);
                     break label135;
                  }

                  XmlPullParserException var26 = var29;
                  Log.e("ResourcesCompat", "Failed to parse xml resource " + var27, var26);
               }

               if (var5 != null) {
                  var5.callbackFailAsync(-3, var6);
               }

               return null;
            }
         }
      } else {
         throw new Resources.NotFoundException("Resource \"" + var1.getResourceName(var3) + "\" (" + Integer.toHexString(var3) + ") is not a Font: " + var2);
      }
   }

   static class Api23Impl {
      private Api23Impl() {
      }

      static ColorStateList getColorStateList(Resources var0, int var1, Resources.Theme var2) {
         return var0.getColorStateList(var1, var2);
      }
   }

   private static class ColorStateListCacheEntry {
      final Configuration mConfiguration;
      final ColorStateList mValue;

      ColorStateListCacheEntry(ColorStateList var1, Configuration var2) {
         this.mValue = var1;
         this.mConfiguration = var2;
      }
   }

   private static final class ColorStateListCacheKey {
      final Resources mResources;
      final Resources.Theme mTheme;

      ColorStateListCacheKey(Resources var1, Resources.Theme var2) {
         this.mResources = var1;
         this.mTheme = var2;
      }

      public boolean equals(Object var1) {
         boolean var2 = true;
         if (this == var1) {
            return true;
         } else if (var1 != null && this.getClass() == var1.getClass()) {
            ColorStateListCacheKey var3 = (ColorStateListCacheKey)var1;
            if (!this.mResources.equals(var3.mResources) || !ObjectsCompat.equals(this.mTheme, var3.mTheme)) {
               var2 = false;
            }

            return var2;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return ObjectsCompat.hash(this.mResources, this.mTheme);
      }
   }

   public abstract static class FontCallback {
      public static Handler getHandler(Handler var0) {
         Handler var1 = var0;
         if (var0 == null) {
            var1 = new Handler(Looper.getMainLooper());
         }

         return var1;
      }

      public final void callbackFailAsync(int var1, Handler var2) {
         getHandler(var2).post(new Runnable(this, var1) {
            final FontCallback this$0;
            final int val$reason;

            {
               this.this$0 = var1;
               this.val$reason = var2;
            }

            public void run() {
               this.this$0.onFontRetrievalFailed(this.val$reason);
            }
         });
      }

      public final void callbackSuccessAsync(Typeface var1, Handler var2) {
         getHandler(var2).post(new Runnable(this, var1) {
            final FontCallback this$0;
            final Typeface val$typeface;

            {
               this.this$0 = var1;
               this.val$typeface = var2;
            }

            public void run() {
               this.this$0.onFontRetrieved(this.val$typeface);
            }
         });
      }

      public abstract void onFontRetrievalFailed(int var1);

      public abstract void onFontRetrieved(Typeface var1);
   }

   static class ImplApi29 {
      private ImplApi29() {
      }

      static float getFloat(Resources var0, int var1) {
         return var0.getFloat(var1);
      }
   }

   public static final class ThemeCompat {
      private ThemeCompat() {
      }

      public static void rebase(Resources.Theme var0) {
         if (VERSION.SDK_INT >= 29) {
            var0.rebase();
         } else if (VERSION.SDK_INT >= 23) {
            ResourcesCompat.ThemeCompat.ImplApi23.rebase(var0);
         }

      }

      static class ImplApi23 {
         private static Method sRebaseMethod;
         private static boolean sRebaseMethodFetched;
         private static final Object sRebaseMethodLock = new Object();

         private ImplApi23() {
         }

         static void rebase(Resources.Theme param0) {
            // $FF: Couldn't be decompiled
         }
      }

      static class ImplApi29 {
         private ImplApi29() {
         }

         static void rebase(Resources.Theme var0) {
            var0.rebase();
         }
      }
   }
}
