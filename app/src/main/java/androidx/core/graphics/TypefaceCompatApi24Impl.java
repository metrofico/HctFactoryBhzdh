package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl {
   private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
   private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
   private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
   private static final String TAG = "TypefaceCompatApi24Impl";
   private static final Method sAddFontWeightStyle;
   private static final Method sCreateFromFamiliesWithDefault;
   private static final Class sFontFamily;
   private static final Constructor sFontFamilyCtor;

   static {
      Constructor var2 = null;

      Method var1;
      Class var3;
      Method var7;
      label18: {
         Method var4;
         Constructor var8;
         label17: {
            Object var0;
            try {
               var3 = Class.forName("android.graphics.FontFamily");
               var8 = var3.getConstructor();
               var4 = var3.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
               var7 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(var3, 1).getClass());
               break label17;
            } catch (ClassNotFoundException var5) {
               var0 = var5;
            } catch (NoSuchMethodException var6) {
               var0 = var6;
            }

            Log.e("TypefaceCompatApi24Impl", var0.getClass().getName(), (Throwable)var0);
            var3 = null;
            var1 = null;
            var7 = var1;
            break label18;
         }

         var2 = var8;
         var1 = var7;
         var7 = var4;
      }

      sFontFamilyCtor = var2;
      sFontFamily = var3;
      sAddFontWeightStyle = var7;
      sCreateFromFamiliesWithDefault = var1;
   }

   private static boolean addFontWeightStyle(Object var0, ByteBuffer var1, int var2, int var3, boolean var4) {
      try {
         var4 = (Boolean)sAddFontWeightStyle.invoke(var0, var1, var2, null, var3, var4);
         return var4;
      } catch (InvocationTargetException | IllegalAccessException var5) {
         return false;
      }
   }

   private static Typeface createFromFamiliesWithDefault(Object var0) {
      try {
         Object var1 = Array.newInstance(sFontFamily, 1);
         Array.set(var1, 0, var0);
         Typeface var3 = (Typeface)sCreateFromFamiliesWithDefault.invoke((Object)null, var1);
         return var3;
      } catch (InvocationTargetException | IllegalAccessException var2) {
         return null;
      }
   }

   public static boolean isUsable() {
      Method var1 = sAddFontWeightStyle;
      if (var1 == null) {
         Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
      }

      boolean var0;
      if (var1 != null) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   private static Object newFamily() {
      try {
         Object var0 = sFontFamilyCtor.newInstance();
         return var0;
      } catch (InstantiationException | InvocationTargetException | IllegalAccessException var1) {
         return null;
      }
   }

   public Typeface createFromFontFamilyFilesResourceEntry(Context var1, FontResourcesParserCompat.FontFamilyFilesResourceEntry var2, Resources var3, int var4) {
      Object var6 = newFamily();
      if (var6 == null) {
         return null;
      } else {
         FontResourcesParserCompat.FontFileResourceEntry[] var9 = var2.getEntries();
         int var5 = var9.length;

         for(var4 = 0; var4 < var5; ++var4) {
            FontResourcesParserCompat.FontFileResourceEntry var8 = var9[var4];
            ByteBuffer var7 = TypefaceCompatUtil.copyToDirectBuffer(var1, var3, var8.getResourceId());
            if (var7 == null) {
               return null;
            }

            if (!addFontWeightStyle(var6, var7, var8.getTtcIndex(), var8.getWeight(), var8.isItalic())) {
               return null;
            }
         }

         return createFromFamiliesWithDefault(var6);
      }
   }

   public Typeface createFromFontInfo(Context var1, CancellationSignal var2, FontsContractCompat.FontInfo[] var3, int var4) {
      Object var12 = newFamily();
      if (var12 == null) {
         return null;
      } else {
         SimpleArrayMap var11 = new SimpleArrayMap();
         int var6 = var3.length;

         for(int var5 = 0; var5 < var6; ++var5) {
            FontsContractCompat.FontInfo var10 = var3[var5];
            Uri var9 = var10.getUri();
            ByteBuffer var8 = (ByteBuffer)var11.get(var9);
            ByteBuffer var7 = var8;
            if (var8 == null) {
               var7 = TypefaceCompatUtil.mmap(var1, var2, var9);
               var11.put(var9, var7);
            }

            if (var7 == null) {
               return null;
            }

            if (!addFontWeightStyle(var12, var7, var10.getTtcIndex(), var10.getWeight(), var10.isItalic())) {
               return null;
            }
         }

         Typeface var13 = createFromFamiliesWithDefault(var12);
         if (var13 == null) {
            return null;
         } else {
            return Typeface.create(var13, var4);
         }
      }
   }
}
