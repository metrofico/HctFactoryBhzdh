package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
   private static final String ABORT_CREATION_METHOD = "abortCreation";
   private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
   private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
   private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
   private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
   private static final String FREEZE_METHOD = "freeze";
   private static final int RESOLVE_BY_FONT_TABLE = -1;
   private static final String TAG = "TypefaceCompatApi26Impl";
   protected final Method mAbortCreation;
   protected final Method mAddFontFromAssetManager;
   protected final Method mAddFontFromBuffer;
   protected final Method mCreateFromFamiliesWithDefault;
   protected final Class mFontFamily;
   protected final Constructor mFontFamilyCtor;
   protected final Method mFreeze;

   public TypefaceCompatApi26Impl() {
      Object var8 = null;

      Method var2;
      Method var3;
      Method var4;
      Class var5;
      Method var6;
      Method var7;
      Constructor var11;
      label16: {
         Object var1;
         try {
            var5 = this.obtainFontFamily();
            var11 = this.obtainFontFamilyCtor(var5);
            var2 = this.obtainAddFontFromAssetManagerMethod(var5);
            var7 = this.obtainAddFontFromBufferMethod(var5);
            var3 = this.obtainFreezeMethod(var5);
            var4 = this.obtainAbortCreationMethod(var5);
            var6 = this.obtainCreateFromFamiliesWithDefaultMethod(var5);
            break label16;
         } catch (ClassNotFoundException var9) {
            var1 = var9;
         } catch (NoSuchMethodException var10) {
            var1 = var10;
         }

         Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + var1.getClass().getName(), (Throwable)var1);
         var6 = null;
         var5 = null;
         var3 = var5;
         var4 = var5;
         var7 = var5;
         var2 = var5;
         var11 = var5;
         var5 = (Class)var8;
      }

      this.mFontFamily = var5;
      this.mFontFamilyCtor = var11;
      this.mAddFontFromAssetManager = var2;
      this.mAddFontFromBuffer = var7;
      this.mFreeze = var3;
      this.mAbortCreation = var4;
      this.mCreateFromFamiliesWithDefault = var6;
   }

   private void abortCreation(Object var1) {
      try {
         this.mAbortCreation.invoke(var1);
      } catch (InvocationTargetException | IllegalAccessException var2) {
      }

   }

   private boolean addFontFromAssetManager(Context var1, Object var2, String var3, int var4, int var5, int var6, FontVariationAxis[] var7) {
      try {
         boolean var8 = (Boolean)this.mAddFontFromAssetManager.invoke(var2, var1.getAssets(), var3, 0, false, var4, var5, var6, var7);
         return var8;
      } catch (InvocationTargetException | IllegalAccessException var9) {
         return false;
      }
   }

   private boolean addFontFromBuffer(Object var1, ByteBuffer var2, int var3, int var4, int var5) {
      try {
         boolean var6 = (Boolean)this.mAddFontFromBuffer.invoke(var1, var2, var3, null, var4, var5);
         return var6;
      } catch (InvocationTargetException | IllegalAccessException var7) {
         return false;
      }
   }

   private boolean freeze(Object var1) {
      try {
         boolean var2 = (Boolean)this.mFreeze.invoke(var1);
         return var2;
      } catch (InvocationTargetException | IllegalAccessException var3) {
         return false;
      }
   }

   private boolean isFontFamilyPrivateAPIAvailable() {
      if (this.mAddFontFromAssetManager == null) {
         Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
      }

      boolean var1;
      if (this.mAddFontFromAssetManager != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private Object newFamily() {
      try {
         Object var1 = this.mFontFamilyCtor.newInstance();
         return var1;
      } catch (InstantiationException | InvocationTargetException | IllegalAccessException var2) {
         return null;
      }
   }

   protected Typeface createFromFamiliesWithDefault(Object var1) {
      try {
         Object var2 = Array.newInstance(this.mFontFamily, 1);
         Array.set(var2, 0, var1);
         Typeface var4 = (Typeface)this.mCreateFromFamiliesWithDefault.invoke((Object)null, var2, -1, -1);
         return var4;
      } catch (InvocationTargetException | IllegalAccessException var3) {
         return null;
      }
   }

   public Typeface createFromFontFamilyFilesResourceEntry(Context var1, FontResourcesParserCompat.FontFamilyFilesResourceEntry var2, Resources var3, int var4) {
      if (!this.isFontFamilyPrivateAPIAvailable()) {
         return super.createFromFontFamilyFilesResourceEntry(var1, var2, var3, var4);
      } else {
         Object var8 = this.newFamily();
         if (var8 == null) {
            return null;
         } else {
            FontResourcesParserCompat.FontFileResourceEntry[] var7 = var2.getEntries();
            int var5 = var7.length;

            for(var4 = 0; var4 < var5; ++var4) {
               FontResourcesParserCompat.FontFileResourceEntry var6 = var7[var4];
               if (!this.addFontFromAssetManager(var1, var8, var6.getFileName(), var6.getTtcIndex(), var6.getWeight(), var6.isItalic(), FontVariationAxis.fromFontVariationSettings(var6.getVariationSettings()))) {
                  this.abortCreation(var8);
                  return null;
               }
            }

            if (!this.freeze(var8)) {
               return null;
            } else {
               return this.createFromFamiliesWithDefault(var8);
            }
         }
      }
   }

   public Typeface createFromFontInfo(Context var1, CancellationSignal var2, FontsContractCompat.FontInfo[] var3, int var4) {
      if (var3.length < 1) {
         return null;
      } else if (!this.isFontFamilyPrivateAPIAvailable()) {
         FontsContractCompat.FontInfo var39 = this.findBestInfo(var3, var4);
         ContentResolver var33 = var1.getContentResolver();

         ParcelFileDescriptor var35;
         boolean var10001;
         try {
            var35 = var33.openFileDescriptor(var39.getUri(), "r", var2);
         } catch (IOException var30) {
            var10001 = false;
            return null;
         }

         if (var35 == null) {
            if (var35 != null) {
               try {
                  var35.close();
               } catch (IOException var27) {
                  var10001 = false;
                  return null;
               }
            }

            return null;
         } else {
            Typeface var40;
            try {
               Typeface.Builder var38 = new Typeface.Builder(var35.getFileDescriptor());
               var40 = var38.setWeight(var39.getWeight()).setItalic(var39.isItalic()).build();
            } catch (Throwable var29) {
               Throwable var36 = var29;
               if (var35 != null) {
                  try {
                     var35.close();
                  } catch (Throwable var26) {
                     Throwable var37 = var26;

                     label265:
                     try {
                        var36.addSuppressed(var37);
                        break label265;
                     } catch (IOException var25) {
                        var10001 = false;
                        return null;
                     }
                  }
               }

               try {
                  throw var36;
               } catch (IOException var24) {
                  var10001 = false;
                  return null;
               }
            }

            if (var35 != null) {
               try {
                  var35.close();
               } catch (IOException var28) {
                  var10001 = false;
                  return null;
               }
            }

            return var40;
         }
      } else {
         Map var8 = TypefaceCompatUtil.readFontInfoIntoByteBuffer(var1, var3, var2);
         Object var34 = this.newFamily();
         if (var34 == null) {
            return null;
         } else {
            int var7 = var3.length;
            boolean var6 = false;

            for(int var5 = 0; var5 < var7; ++var5) {
               FontsContractCompat.FontInfo var31 = var3[var5];
               ByteBuffer var9 = (ByteBuffer)var8.get(var31.getUri());
               if (var9 != null) {
                  if (!this.addFontFromBuffer(var34, var9, var31.getTtcIndex(), var31.getWeight(), var31.isItalic())) {
                     this.abortCreation(var34);
                     return null;
                  }

                  var6 = true;
               }
            }

            if (!var6) {
               this.abortCreation(var34);
               return null;
            } else if (!this.freeze(var34)) {
               return null;
            } else {
               Typeface var32 = this.createFromFamiliesWithDefault(var34);
               if (var32 == null) {
                  return null;
               } else {
                  return Typeface.create(var32, var4);
               }
            }
         }
      }
   }

   public Typeface createFromResourcesFontFile(Context var1, Resources var2, int var3, String var4, int var5) {
      if (!this.isFontFamilyPrivateAPIAvailable()) {
         return super.createFromResourcesFontFile(var1, var2, var3, var4, var5);
      } else {
         Object var6 = this.newFamily();
         if (var6 == null) {
            return null;
         } else if (!this.addFontFromAssetManager(var1, var6, var4, 0, -1, -1, (FontVariationAxis[])null)) {
            this.abortCreation(var6);
            return null;
         } else {
            return !this.freeze(var6) ? null : this.createFromFamiliesWithDefault(var6);
         }
      }
   }

   protected Method obtainAbortCreationMethod(Class var1) throws NoSuchMethodException {
      return var1.getMethod("abortCreation");
   }

   protected Method obtainAddFontFromAssetManagerMethod(Class var1) throws NoSuchMethodException {
      return var1.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
   }

   protected Method obtainAddFontFromBufferMethod(Class var1) throws NoSuchMethodException {
      return var1.getMethod("addFontFromBuffer", ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
   }

   protected Method obtainCreateFromFamiliesWithDefaultMethod(Class var1) throws NoSuchMethodException {
      Method var2 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(var1, 1).getClass(), Integer.TYPE, Integer.TYPE);
      var2.setAccessible(true);
      return var2;
   }

   protected Class obtainFontFamily() throws ClassNotFoundException {
      return Class.forName("android.graphics.FontFamily");
   }

   protected Constructor obtainFontFamilyCtor(Class var1) throws NoSuchMethodException {
      return var1.getConstructor();
   }

   protected Method obtainFreezeMethod(Class var1) throws NoSuchMethodException {
      return var1.getMethod("freeze");
   }
}
