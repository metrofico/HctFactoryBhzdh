package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.R;
import androidx.core.provider.FontRequest;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class FontResourcesParserCompat {
   private static final int DEFAULT_TIMEOUT_MILLIS = 500;
   public static final int FETCH_STRATEGY_ASYNC = 1;
   public static final int FETCH_STRATEGY_BLOCKING = 0;
   public static final int INFINITE_TIMEOUT_VALUE = -1;
   private static final int ITALIC = 1;
   private static final int NORMAL_WEIGHT = 400;

   private FontResourcesParserCompat() {
   }

   private static int getType(TypedArray var0, int var1) {
      if (VERSION.SDK_INT >= 21) {
         return var0.getType(var1);
      } else {
         TypedValue var2 = new TypedValue();
         var0.getValue(var1, var2);
         return var2.type;
      }
   }

   public static FamilyResourceEntry parse(XmlPullParser var0, Resources var1) throws XmlPullParserException, IOException {
      int var2;
      do {
         var2 = var0.next();
      } while(var2 != 2 && var2 != 1);

      if (var2 == 2) {
         return readFamilies(var0, var1);
      } else {
         throw new XmlPullParserException("No start tag found");
      }
   }

   public static List readCerts(Resources var0, int var1) {
      if (var1 == 0) {
         return Collections.emptyList();
      } else {
         TypedArray var3 = var0.obtainTypedArray(var1);

         List var36;
         label358: {
            Throwable var10000;
            label364: {
               boolean var10001;
               try {
                  if (var3.length() == 0) {
                     var36 = Collections.emptyList();
                     break label358;
                  }
               } catch (Throwable var34) {
                  var10000 = var34;
                  var10001 = false;
                  break label364;
               }

               ArrayList var4;
               label365: {
                  label348: {
                     try {
                        var4 = new ArrayList();
                        if (getType(var3, 0) == 1) {
                           break label348;
                        }
                     } catch (Throwable var33) {
                        var10000 = var33;
                        var10001 = false;
                        break label364;
                     }

                     try {
                        var4.add(toByteArrayList(var0.getStringArray(var1)));
                        break label365;
                     } catch (Throwable var30) {
                        var10000 = var30;
                        var10001 = false;
                        break label364;
                     }
                  }

                  var1 = 0;

                  while(true) {
                     int var2;
                     try {
                        if (var1 >= var3.length()) {
                           break;
                        }

                        var2 = var3.getResourceId(var1, 0);
                     } catch (Throwable var32) {
                        var10000 = var32;
                        var10001 = false;
                        break label364;
                     }

                     if (var2 != 0) {
                        try {
                           var4.add(toByteArrayList(var0.getStringArray(var2)));
                        } catch (Throwable var31) {
                           var10000 = var31;
                           var10001 = false;
                           break label364;
                        }
                     }

                     ++var1;
                  }
               }

               var3.recycle();
               return var4;
            }

            Throwable var35 = var10000;
            var3.recycle();
            throw var35;
         }

         var3.recycle();
         return var36;
      }
   }

   private static FamilyResourceEntry readFamilies(XmlPullParser var0, Resources var1) throws XmlPullParserException, IOException {
      var0.require(2, (String)null, "font-family");
      if (var0.getName().equals("font-family")) {
         return readFamily(var0, var1);
      } else {
         skip(var0);
         return null;
      }
   }

   private static FamilyResourceEntry readFamily(XmlPullParser var0, Resources var1) throws XmlPullParserException, IOException {
      TypedArray var5 = var1.obtainAttributes(Xml.asAttributeSet(var0), R.styleable.FontFamily);
      String var6 = var5.getString(R.styleable.FontFamily_fontProviderAuthority);
      String var8 = var5.getString(R.styleable.FontFamily_fontProviderPackage);
      String var9 = var5.getString(R.styleable.FontFamily_fontProviderQuery);
      int var3 = var5.getResourceId(R.styleable.FontFamily_fontProviderCerts, 0);
      int var4 = var5.getInteger(R.styleable.FontFamily_fontProviderFetchStrategy, 1);
      int var2 = var5.getInteger(R.styleable.FontFamily_fontProviderFetchTimeout, 500);
      String var7 = var5.getString(R.styleable.FontFamily_fontProviderSystemFontFamily);
      var5.recycle();
      if (var6 != null && var8 != null && var9 != null) {
         while(var0.next() != 3) {
            skip(var0);
         }

         return new ProviderResourceEntry(new FontRequest(var6, var8, var9, readCerts(var1, var3)), var4, var2, var7);
      } else {
         ArrayList var10 = new ArrayList();

         while(var0.next() != 3) {
            if (var0.getEventType() == 2) {
               if (var0.getName().equals("font")) {
                  var10.add(readFont(var0, var1));
               } else {
                  skip(var0);
               }
            }
         }

         if (var10.isEmpty()) {
            return null;
         } else {
            return new FontFamilyFilesResourceEntry((FontFileResourceEntry[])var10.toArray(new FontFileResourceEntry[var10.size()]));
         }
      }
   }

   private static FontFileResourceEntry readFont(XmlPullParser var0, Resources var1) throws XmlPullParserException, IOException {
      TypedArray var7 = var1.obtainAttributes(Xml.asAttributeSet(var0), R.styleable.FontFamilyFont);
      int var2;
      if (var7.hasValue(R.styleable.FontFamilyFont_fontWeight)) {
         var2 = R.styleable.FontFamilyFont_fontWeight;
      } else {
         var2 = R.styleable.FontFamilyFont_android_fontWeight;
      }

      int var4 = var7.getInt(var2, 400);
      if (var7.hasValue(R.styleable.FontFamilyFont_fontStyle)) {
         var2 = R.styleable.FontFamilyFont_fontStyle;
      } else {
         var2 = R.styleable.FontFamilyFont_android_fontStyle;
      }

      boolean var6;
      if (1 == var7.getInt(var2, 0)) {
         var6 = true;
      } else {
         var6 = false;
      }

      if (var7.hasValue(R.styleable.FontFamilyFont_ttcIndex)) {
         var2 = R.styleable.FontFamilyFont_ttcIndex;
      } else {
         var2 = R.styleable.FontFamilyFont_android_ttcIndex;
      }

      int var3;
      if (var7.hasValue(R.styleable.FontFamilyFont_fontVariationSettings)) {
         var3 = R.styleable.FontFamilyFont_fontVariationSettings;
      } else {
         var3 = R.styleable.FontFamilyFont_android_fontVariationSettings;
      }

      String var9 = var7.getString(var3);
      var3 = var7.getInt(var2, 0);
      if (var7.hasValue(R.styleable.FontFamilyFont_font)) {
         var2 = R.styleable.FontFamilyFont_font;
      } else {
         var2 = R.styleable.FontFamilyFont_android_font;
      }

      int var5 = var7.getResourceId(var2, 0);
      String var8 = var7.getString(var2);
      var7.recycle();

      while(var0.next() != 3) {
         skip(var0);
      }

      return new FontFileResourceEntry(var8, var4, var6, var9, var3, var5);
   }

   private static void skip(XmlPullParser var0) throws XmlPullParserException, IOException {
      int var1 = 1;

      while(var1 > 0) {
         int var2 = var0.next();
         if (var2 != 2) {
            if (var2 == 3) {
               --var1;
            }
         } else {
            ++var1;
         }
      }

   }

   private static List toByteArrayList(String[] var0) {
      ArrayList var3 = new ArrayList();
      int var2 = var0.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         var3.add(Base64.decode(var0[var1], 0));
      }

      return var3;
   }

   public interface FamilyResourceEntry {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface FetchStrategy {
   }

   public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
      private final FontFileResourceEntry[] mEntries;

      public FontFamilyFilesResourceEntry(FontFileResourceEntry[] var1) {
         this.mEntries = var1;
      }

      public FontFileResourceEntry[] getEntries() {
         return this.mEntries;
      }
   }

   public static final class FontFileResourceEntry {
      private final String mFileName;
      private boolean mItalic;
      private int mResourceId;
      private int mTtcIndex;
      private String mVariationSettings;
      private int mWeight;

      public FontFileResourceEntry(String var1, int var2, boolean var3, String var4, int var5, int var6) {
         this.mFileName = var1;
         this.mWeight = var2;
         this.mItalic = var3;
         this.mVariationSettings = var4;
         this.mTtcIndex = var5;
         this.mResourceId = var6;
      }

      public String getFileName() {
         return this.mFileName;
      }

      public int getResourceId() {
         return this.mResourceId;
      }

      public int getTtcIndex() {
         return this.mTtcIndex;
      }

      public String getVariationSettings() {
         return this.mVariationSettings;
      }

      public int getWeight() {
         return this.mWeight;
      }

      public boolean isItalic() {
         return this.mItalic;
      }
   }

   public static final class ProviderResourceEntry implements FamilyResourceEntry {
      private final FontRequest mRequest;
      private final int mStrategy;
      private final String mSystemFontFamilyName;
      private final int mTimeoutMs;

      public ProviderResourceEntry(FontRequest var1, int var2, int var3) {
         this(var1, var2, var3, (String)null);
      }

      public ProviderResourceEntry(FontRequest var1, int var2, int var3, String var4) {
         this.mRequest = var1;
         this.mStrategy = var2;
         this.mTimeoutMs = var3;
         this.mSystemFontFamilyName = var4;
      }

      public int getFetchStrategy() {
         return this.mStrategy;
      }

      public FontRequest getRequest() {
         return this.mRequest;
      }

      public String getSystemFontFamilyName() {
         return this.mSystemFontFamilyName;
      }

      public int getTimeout() {
         return this.mTimeoutMs;
      }
   }
}
