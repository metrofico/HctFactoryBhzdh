package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.SystemClock;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class FontRequestEmojiCompatConfig extends EmojiCompat.Config {
   private static final FontProviderHelper DEFAULT_FONTS_CONTRACT = new FontProviderHelper();

   public FontRequestEmojiCompatConfig(Context var1, FontRequest var2) {
      super(new FontRequestMetadataLoader(var1, var2, DEFAULT_FONTS_CONTRACT));
   }

   public FontRequestEmojiCompatConfig(Context var1, FontRequest var2, FontProviderHelper var3) {
      super(new FontRequestMetadataLoader(var1, var2, var3));
   }

   @Deprecated
   public FontRequestEmojiCompatConfig setHandler(Handler var1) {
      if (var1 == null) {
         return this;
      } else {
         this.setLoadingExecutor(ConcurrencyHelpers.convertHandlerToExecutor(var1));
         return this;
      }
   }

   public FontRequestEmojiCompatConfig setLoadingExecutor(Executor var1) {
      ((FontRequestMetadataLoader)this.getMetadataRepoLoader()).setExecutor(var1);
      return this;
   }

   public FontRequestEmojiCompatConfig setRetryPolicy(RetryPolicy var1) {
      ((FontRequestMetadataLoader)this.getMetadataRepoLoader()).setRetryPolicy(var1);
      return this;
   }

   public static class ExponentialBackoffRetryPolicy extends RetryPolicy {
      private long mRetryOrigin;
      private final long mTotalMs;

      public ExponentialBackoffRetryPolicy(long var1) {
         this.mTotalMs = var1;
      }

      public long getRetryDelay() {
         if (this.mRetryOrigin == 0L) {
            this.mRetryOrigin = SystemClock.uptimeMillis();
            return 0L;
         } else {
            long var1 = SystemClock.uptimeMillis() - this.mRetryOrigin;
            return var1 > this.mTotalMs ? -1L : Math.min(Math.max(var1, 1000L), this.mTotalMs - var1);
         }
      }
   }

   public static class FontProviderHelper {
      public Typeface buildTypeface(Context var1, FontsContractCompat.FontInfo var2) throws PackageManager.NameNotFoundException {
         return FontsContractCompat.buildTypeface(var1, (CancellationSignal)null, new FontsContractCompat.FontInfo[]{var2});
      }

      public FontsContractCompat.FontFamilyResult fetchFonts(Context var1, FontRequest var2) throws PackageManager.NameNotFoundException {
         return FontsContractCompat.fetchFonts(var1, (CancellationSignal)null, var2);
      }

      public void registerObserver(Context var1, Uri var2, ContentObserver var3) {
         var1.getContentResolver().registerContentObserver(var2, false, var3);
      }

      public void unregisterObserver(Context var1, ContentObserver var2) {
         var1.getContentResolver().unregisterContentObserver(var2);
      }
   }

   private static class FontRequestMetadataLoader implements EmojiCompat.MetadataRepoLoader {
      private static final String S_TRACE_BUILD_TYPEFACE = "EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface";
      EmojiCompat.MetadataRepoLoaderCallback mCallback;
      private final Context mContext;
      private Executor mExecutor;
      private final FontProviderHelper mFontProviderHelper;
      private final Object mLock = new Object();
      private Handler mMainHandler;
      private Runnable mMainHandlerLoadCallback;
      private ThreadPoolExecutor mMyThreadPoolExecutor;
      private ContentObserver mObserver;
      private final FontRequest mRequest;
      private RetryPolicy mRetryPolicy;

      FontRequestMetadataLoader(Context var1, FontRequest var2, FontProviderHelper var3) {
         Preconditions.checkNotNull(var1, "Context cannot be null");
         Preconditions.checkNotNull(var2, "FontRequest cannot be null");
         this.mContext = var1.getApplicationContext();
         this.mRequest = var2;
         this.mFontProviderHelper = var3;
      }

      private void cleanUp() {
         Object var1 = this.mLock;
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label532: {
            ContentObserver var2;
            try {
               this.mCallback = null;
               var2 = this.mObserver;
            } catch (Throwable var74) {
               var10000 = var74;
               var10001 = false;
               break label532;
            }

            if (var2 != null) {
               try {
                  this.mFontProviderHelper.unregisterObserver(this.mContext, var2);
                  this.mObserver = null;
               } catch (Throwable var73) {
                  var10000 = var73;
                  var10001 = false;
                  break label532;
               }
            }

            Handler var75;
            try {
               var75 = this.mMainHandler;
            } catch (Throwable var72) {
               var10000 = var72;
               var10001 = false;
               break label532;
            }

            if (var75 != null) {
               try {
                  var75.removeCallbacks(this.mMainHandlerLoadCallback);
               } catch (Throwable var71) {
                  var10000 = var71;
                  var10001 = false;
                  break label532;
               }
            }

            ThreadPoolExecutor var76;
            try {
               this.mMainHandler = null;
               var76 = this.mMyThreadPoolExecutor;
            } catch (Throwable var70) {
               var10000 = var70;
               var10001 = false;
               break label532;
            }

            if (var76 != null) {
               try {
                  var76.shutdown();
               } catch (Throwable var69) {
                  var10000 = var69;
                  var10001 = false;
                  break label532;
               }
            }

            label507:
            try {
               this.mExecutor = null;
               this.mMyThreadPoolExecutor = null;
               return;
            } catch (Throwable var68) {
               var10000 = var68;
               var10001 = false;
               break label507;
            }
         }

         while(true) {
            Throwable var77 = var10000;

            try {
               throw var77;
            } catch (Throwable var67) {
               var10000 = var67;
               var10001 = false;
               continue;
            }
         }
      }

      private FontsContractCompat.FontInfo retrieveFontInfo() {
         FontsContractCompat.FontFamilyResult var1;
         try {
            var1 = this.mFontProviderHelper.fetchFonts(this.mContext, this.mRequest);
         } catch (PackageManager.NameNotFoundException var2) {
            throw new RuntimeException("provider not found", var2);
         }

         if (var1.getStatusCode() == 0) {
            FontsContractCompat.FontInfo[] var3 = var1.getFonts();
            if (var3 != null && var3.length != 0) {
               return var3[0];
            } else {
               throw new RuntimeException("fetchFonts failed (empty result)");
            }
         } else {
            throw new RuntimeException("fetchFonts failed (" + var1.getStatusCode() + ")");
         }
      }

      private void scheduleRetry(Uri var1, long var2) {
         Object var6 = this.mLock;
         synchronized(var6){}

         Throwable var10000;
         boolean var10001;
         label358: {
            Handler var5;
            try {
               var5 = this.mMainHandler;
            } catch (Throwable var48) {
               var10000 = var48;
               var10001 = false;
               break label358;
            }

            Handler var4 = var5;
            if (var5 == null) {
               try {
                  var4 = ConcurrencyHelpers.mainHandlerAsync();
                  this.mMainHandler = var4;
               } catch (Throwable var47) {
                  var10000 = var47;
                  var10001 = false;
                  break label358;
               }
            }

            try {
               if (this.mObserver == null) {
                  ContentObserver var51 = new ContentObserver(this, var4) {
                     final FontRequestMetadataLoader this$0;

                     {
                        this.this$0 = var1;
                     }

                     public void onChange(boolean var1, Uri var2) {
                        this.this$0.loadInternal();
                     }
                  };
                  this.mObserver = var51;
                  this.mFontProviderHelper.registerObserver(this.mContext, var1, var51);
               }
            } catch (Throwable var46) {
               var10000 = var46;
               var10001 = false;
               break label358;
            }

            try {
               if (this.mMainHandlerLoadCallback == null) {
                  FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda1 var49 = new FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda1(this);
                  this.mMainHandlerLoadCallback = var49;
               }
            } catch (Throwable var45) {
               var10000 = var45;
               var10001 = false;
               break label358;
            }

            label341:
            try {
               var4.postDelayed(this.mMainHandlerLoadCallback, var2);
               return;
            } catch (Throwable var44) {
               var10000 = var44;
               var10001 = false;
               break label341;
            }
         }

         while(true) {
            Throwable var50 = var10000;

            try {
               throw var50;
            } catch (Throwable var43) {
               var10000 = var43;
               var10001 = false;
               continue;
            }
         }
      }

      void createMetadata() {
         // $FF: Couldn't be decompiled
      }

      public void load(EmojiCompat.MetadataRepoLoaderCallback param1) {
         // $FF: Couldn't be decompiled
      }

      void loadInternal() {
         Object var1 = this.mLock;
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label197: {
            try {
               if (this.mCallback == null) {
                  return;
               }
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label197;
            }

            try {
               if (this.mExecutor == null) {
                  ThreadPoolExecutor var2 = ConcurrencyHelpers.createBackgroundPriorityExecutor("emojiCompat");
                  this.mMyThreadPoolExecutor = var2;
                  this.mExecutor = var2;
               }
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label197;
            }

            label187:
            try {
               Executor var25 = this.mExecutor;
               FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda0 var3 = new FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda0(this);
               var25.execute(var3);
               return;
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label187;
            }
         }

         while(true) {
            Throwable var24 = var10000;

            try {
               throw var24;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               continue;
            }
         }
      }

      public void setExecutor(Executor param1) {
         // $FF: Couldn't be decompiled
      }

      public void setRetryPolicy(RetryPolicy param1) {
         // $FF: Couldn't be decompiled
      }
   }

   public abstract static class RetryPolicy {
      public abstract long getRetryDelay();
   }
}
