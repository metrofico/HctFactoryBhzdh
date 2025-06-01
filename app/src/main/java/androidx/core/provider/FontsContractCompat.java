package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.Executor;

public class FontsContractCompat {
   @Deprecated
   public static final String PARCEL_FONT_RESULTS = "font_results";
   @Deprecated
   static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
   @Deprecated
   static final int RESULT_CODE_WRONG_CERTIFICATES = -2;

   private FontsContractCompat() {
   }

   public static Typeface buildTypeface(Context var0, CancellationSignal var1, FontInfo[] var2) {
      return TypefaceCompat.createFromFontInfo(var0, var1, var2, 0);
   }

   public static FontFamilyResult fetchFonts(Context var0, CancellationSignal var1, FontRequest var2) throws PackageManager.NameNotFoundException {
      return FontProvider.getFontFamilyResult(var0, var2, var1);
   }

   @Deprecated
   public static Typeface getFontSync(Context var0, FontRequest var1, ResourcesCompat.FontCallback var2, Handler var3, boolean var4, int var5, int var6) {
      TypefaceCompat.ResourcesCallbackAdapter var7 = new TypefaceCompat.ResourcesCallbackAdapter(var2);
      return requestFont(var0, var1, var6, var4, var5, ResourcesCompat.FontCallback.getHandler(var3), var7);
   }

   @Deprecated
   public static ProviderInfo getProvider(PackageManager var0, FontRequest var1, Resources var2) throws PackageManager.NameNotFoundException {
      return FontProvider.getProvider(var0, var1, var2);
   }

   @Deprecated
   public static Map prepareFontData(Context var0, FontInfo[] var1, CancellationSignal var2) {
      return TypefaceCompatUtil.readFontInfoIntoByteBuffer(var0, var1, var2);
   }

   public static Typeface requestFont(Context var0, FontRequest var1, int var2, boolean var3, int var4, Handler var5, FontRequestCallback var6) {
      CallbackWithHandler var7 = new CallbackWithHandler(var6, var5);
      return var3 ? FontRequestWorker.requestFontSync(var0, var1, var7, var2, var4) : FontRequestWorker.requestFontAsync(var0, var1, var2, (Executor)null, var7);
   }

   public static void requestFont(Context var0, FontRequest var1, FontRequestCallback var2, Handler var3) {
      CallbackWithHandler var4 = new CallbackWithHandler(var2);
      Executor var5 = RequestExecutor.createHandlerExecutor(var3);
      FontRequestWorker.requestFontAsync(var0.getApplicationContext(), var1, 0, var5, var4);
   }

   @Deprecated
   public static void resetCache() {
      FontRequestWorker.resetTypefaceCache();
   }

   public static void resetTypefaceCache() {
      FontRequestWorker.resetTypefaceCache();
   }

   public static final class Columns implements BaseColumns {
      public static final String FILE_ID = "file_id";
      public static final String ITALIC = "font_italic";
      public static final String RESULT_CODE = "result_code";
      public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
      public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
      public static final int RESULT_CODE_MALFORMED_QUERY = 3;
      public static final int RESULT_CODE_OK = 0;
      public static final String TTC_INDEX = "font_ttc_index";
      public static final String VARIATION_SETTINGS = "font_variation_settings";
      public static final String WEIGHT = "font_weight";
   }

   public static class FontFamilyResult {
      public static final int STATUS_OK = 0;
      public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
      public static final int STATUS_WRONG_CERTIFICATES = 1;
      private final FontInfo[] mFonts;
      private final int mStatusCode;

      @Deprecated
      public FontFamilyResult(int var1, FontInfo[] var2) {
         this.mStatusCode = var1;
         this.mFonts = var2;
      }

      static FontFamilyResult create(int var0, FontInfo[] var1) {
         return new FontFamilyResult(var0, var1);
      }

      public FontInfo[] getFonts() {
         return this.mFonts;
      }

      public int getStatusCode() {
         return this.mStatusCode;
      }
   }

   public static class FontInfo {
      private final boolean mItalic;
      private final int mResultCode;
      private final int mTtcIndex;
      private final Uri mUri;
      private final int mWeight;

      @Deprecated
      public FontInfo(Uri var1, int var2, int var3, boolean var4, int var5) {
         this.mUri = (Uri)Preconditions.checkNotNull(var1);
         this.mTtcIndex = var2;
         this.mWeight = var3;
         this.mItalic = var4;
         this.mResultCode = var5;
      }

      static FontInfo create(Uri var0, int var1, int var2, boolean var3, int var4) {
         return new FontInfo(var0, var1, var2, var3, var4);
      }

      public int getResultCode() {
         return this.mResultCode;
      }

      public int getTtcIndex() {
         return this.mTtcIndex;
      }

      public Uri getUri() {
         return this.mUri;
      }

      public int getWeight() {
         return this.mWeight;
      }

      public boolean isItalic() {
         return this.mItalic;
      }
   }

   public static class FontRequestCallback {
      public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
      public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
      public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
      public static final int FAIL_REASON_MALFORMED_QUERY = 3;
      public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
      public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
      public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
      @Deprecated
      public static final int RESULT_OK = 0;
      static final int RESULT_SUCCESS = 0;

      public void onTypefaceRequestFailed(int var1) {
      }

      public void onTypefaceRetrieved(Typeface var1) {
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface FontRequestFailReason {
      }
   }
}
