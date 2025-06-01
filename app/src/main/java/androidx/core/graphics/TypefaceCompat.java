package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Build.VERSION;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.provider.FontsContractCompat;

public class TypefaceCompat {
   private static final LruCache sTypefaceCache;
   private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

   static {
      if (VERSION.SDK_INT >= 29) {
         sTypefaceCompatImpl = new TypefaceCompatApi29Impl();
      } else if (VERSION.SDK_INT >= 28) {
         sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
      } else if (VERSION.SDK_INT >= 26) {
         sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
      } else if (VERSION.SDK_INT >= 24 && TypefaceCompatApi24Impl.isUsable()) {
         sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
      } else if (VERSION.SDK_INT >= 21) {
         sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
      } else {
         sTypefaceCompatImpl = new TypefaceCompatBaseImpl();
      }

      sTypefaceCache = new LruCache(16);
   }

   private TypefaceCompat() {
   }

   public static void clearCache() {
      sTypefaceCache.evictAll();
   }

   public static Typeface create(Context var0, Typeface var1, int var2) {
      if (var0 != null) {
         if (VERSION.SDK_INT < 21) {
            Typeface var3 = getBestFontFromFamily(var0, var1, var2);
            if (var3 != null) {
               return var3;
            }
         }

         return Typeface.create(var1, var2);
      } else {
         throw new IllegalArgumentException("Context cannot be null");
      }
   }

   public static Typeface createFromFontInfo(Context var0, CancellationSignal var1, FontsContractCompat.FontInfo[] var2, int var3) {
      return sTypefaceCompatImpl.createFromFontInfo(var0, var1, var2, var3);
   }

   public static Typeface createFromResourcesFamilyXml(Context var0, FontResourcesParserCompat.FamilyResourceEntry var1, Resources var2, int var3, int var4, ResourcesCompat.FontCallback var5, Handler var6, boolean var7) {
      Typeface var11;
      if (var1 instanceof FontResourcesParserCompat.ProviderResourceEntry) {
         FontResourcesParserCompat.ProviderResourceEntry var12 = (FontResourcesParserCompat.ProviderResourceEntry)var1;
         Typeface var10 = getSystemFontFamily(var12.getSystemFontFamilyName());
         if (var10 != null) {
            if (var5 != null) {
               var5.callbackSuccessAsync(var10, var6);
            }

            return var10;
         }

         boolean var9;
         label41: {
            label40: {
               if (var7) {
                  if (var12.getFetchStrategy() == 0) {
                     break label40;
                  }
               } else if (var5 == null) {
                  break label40;
               }

               var9 = false;
               break label41;
            }

            var9 = true;
         }

         int var8;
         if (var7) {
            var8 = var12.getTimeout();
         } else {
            var8 = -1;
         }

         var6 = ResourcesCompat.FontCallback.getHandler(var6);
         ResourcesCallbackAdapter var14 = new ResourcesCallbackAdapter(var5);
         var11 = FontsContractCompat.requestFont(var0, var12.getRequest(), var4, var9, var8, var6, var14);
      } else {
         Typeface var13 = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(var0, (FontResourcesParserCompat.FontFamilyFilesResourceEntry)var1, var2, var4);
         var11 = var13;
         if (var5 != null) {
            if (var13 != null) {
               var5.callbackSuccessAsync(var13, var6);
               var11 = var13;
            } else {
               var5.callbackFailAsync(-3, var6);
               var11 = var13;
            }
         }
      }

      if (var11 != null) {
         sTypefaceCache.put(createResourceUid(var2, var3, var4), var11);
      }

      return var11;
   }

   public static Typeface createFromResourcesFontFile(Context var0, Resources var1, int var2, String var3, int var4) {
      Typeface var5 = sTypefaceCompatImpl.createFromResourcesFontFile(var0, var1, var2, var3, var4);
      if (var5 != null) {
         String var6 = createResourceUid(var1, var2, var4);
         sTypefaceCache.put(var6, var5);
      }

      return var5;
   }

   private static String createResourceUid(Resources var0, int var1, int var2) {
      return var0.getResourcePackageName(var1) + "-" + var1 + "-" + var2;
   }

   public static Typeface findFromCache(Resources var0, int var1, int var2) {
      return (Typeface)sTypefaceCache.get(createResourceUid(var0, var1, var2));
   }

   private static Typeface getBestFontFromFamily(Context var0, Typeface var1, int var2) {
      TypefaceCompatBaseImpl var3 = sTypefaceCompatImpl;
      FontResourcesParserCompat.FontFamilyFilesResourceEntry var4 = var3.getFontFamily(var1);
      return var4 == null ? null : var3.createFromFontFamilyFilesResourceEntry(var0, var4, var0.getResources(), var2);
   }

   private static Typeface getSystemFontFamily(String var0) {
      Object var2 = null;
      Typeface var1 = (Typeface)var2;
      if (var0 != null) {
         if (var0.isEmpty()) {
            var1 = (Typeface)var2;
         } else {
            Typeface var4 = Typeface.create(var0, 0);
            Typeface var3 = Typeface.create(Typeface.DEFAULT, 0);
            var1 = (Typeface)var2;
            if (var4 != null) {
               var1 = (Typeface)var2;
               if (!var4.equals(var3)) {
                  var1 = var4;
               }
            }
         }
      }

      return var1;
   }

   public static class ResourcesCallbackAdapter extends FontsContractCompat.FontRequestCallback {
      private ResourcesCompat.FontCallback mFontCallback;

      public ResourcesCallbackAdapter(ResourcesCompat.FontCallback var1) {
         this.mFontCallback = var1;
      }

      public void onTypefaceRequestFailed(int var1) {
         ResourcesCompat.FontCallback var2 = this.mFontCallback;
         if (var2 != null) {
            var2.onFontRetrievalFailed(var1);
         }

      }

      public void onTypefaceRetrieved(Typeface var1) {
         ResourcesCompat.FontCallback var2 = this.mFontCallback;
         if (var2 != null) {
            var2.onFontRetrieved(var1);
         }

      }
   }
}
