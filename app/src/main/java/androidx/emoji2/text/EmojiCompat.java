package androidx.emoji2.text;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EmojiCompat {
   private static final Object CONFIG_LOCK = new Object();
   public static final String EDITOR_INFO_METAVERSION_KEY = "android.support.text.emoji.emojiCompat_metadataVersion";
   public static final String EDITOR_INFO_REPLACE_ALL_KEY = "android.support.text.emoji.emojiCompat_replaceAll";
   static final int EMOJI_COUNT_UNLIMITED = Integer.MAX_VALUE;
   private static final Object INSTANCE_LOCK = new Object();
   public static final int LOAD_STATE_DEFAULT = 3;
   public static final int LOAD_STATE_FAILED = 2;
   public static final int LOAD_STATE_LOADING = 0;
   public static final int LOAD_STATE_SUCCEEDED = 1;
   public static final int LOAD_STRATEGY_DEFAULT = 0;
   public static final int LOAD_STRATEGY_MANUAL = 1;
   private static final String NOT_INITIALIZED_ERROR_TEXT = "EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.";
   public static final int REPLACE_STRATEGY_ALL = 1;
   public static final int REPLACE_STRATEGY_DEFAULT = 0;
   public static final int REPLACE_STRATEGY_NON_EXISTENT = 2;
   private static volatile boolean sHasDoneDefaultConfigLookup;
   private static volatile EmojiCompat sInstance;
   final int[] mEmojiAsDefaultStyleExceptions;
   private final int mEmojiSpanIndicatorColor;
   private final boolean mEmojiSpanIndicatorEnabled;
   private final GlyphChecker mGlyphChecker;
   private final CompatInternal mHelper;
   private final Set mInitCallbacks;
   private final ReadWriteLock mInitLock = new ReentrantReadWriteLock();
   private volatile int mLoadState = 3;
   private final Handler mMainHandler;
   private final int mMetadataLoadStrategy;
   final MetadataRepoLoader mMetadataLoader;
   final boolean mReplaceAll;
   final boolean mUseEmojiAsDefaultStyle;

   private EmojiCompat(Config var1) {
      this.mReplaceAll = var1.mReplaceAll;
      this.mUseEmojiAsDefaultStyle = var1.mUseEmojiAsDefaultStyle;
      this.mEmojiAsDefaultStyleExceptions = var1.mEmojiAsDefaultStyleExceptions;
      this.mEmojiSpanIndicatorEnabled = var1.mEmojiSpanIndicatorEnabled;
      this.mEmojiSpanIndicatorColor = var1.mEmojiSpanIndicatorColor;
      this.mMetadataLoader = var1.mMetadataLoader;
      this.mMetadataLoadStrategy = var1.mMetadataLoadStrategy;
      this.mGlyphChecker = var1.mGlyphChecker;
      this.mMainHandler = new Handler(Looper.getMainLooper());
      ArraySet var2 = new ArraySet();
      this.mInitCallbacks = var2;
      if (var1.mInitCallbacks != null && !var1.mInitCallbacks.isEmpty()) {
         var2.addAll(var1.mInitCallbacks);
      }

      Object var3;
      if (VERSION.SDK_INT < 19) {
         var3 = new CompatInternal(this);
      } else {
         var3 = new CompatInternal19(this);
      }

      this.mHelper = (CompatInternal)var3;
      this.loadMetadata();
   }

   public static EmojiCompat get() {
      Object var1 = INSTANCE_LOCK;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label133: {
         EmojiCompat var2;
         try {
            var2 = sInstance;
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label133;
         }

         boolean var0;
         if (var2 != null) {
            var0 = true;
         } else {
            var0 = false;
         }

         label125:
         try {
            Preconditions.checkState(var0, "EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
            return var2;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label125;
         }
      }

      while(true) {
         Throwable var15 = var10000;

         try {
            throw var15;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public static boolean handleDeleteSurroundingText(InputConnection var0, Editable var1, int var2, int var3, boolean var4) {
      return VERSION.SDK_INT >= 19 ? EmojiProcessor.handleDeleteSurroundingText(var0, var1, var2, var3, var4) : false;
   }

   public static boolean handleOnKeyDown(Editable var0, int var1, KeyEvent var2) {
      return VERSION.SDK_INT >= 19 ? EmojiProcessor.handleOnKeyDown(var0, var1, var2) : false;
   }

   public static EmojiCompat init(Context var0) {
      return init(var0, (DefaultEmojiCompatConfig.DefaultEmojiCompatConfigFactory)null);
   }

   public static EmojiCompat init(Context var0, DefaultEmojiCompatConfig.DefaultEmojiCompatConfigFactory var1) {
      if (sHasDoneDefaultConfigLookup) {
         return sInstance;
      } else {
         if (var1 == null) {
            var1 = new DefaultEmojiCompatConfig.DefaultEmojiCompatConfigFactory((DefaultEmojiCompatConfig.DefaultEmojiCompatConfigHelper)null);
         }

         Config var33 = var1.create(var0);
         Object var32 = CONFIG_LOCK;
         synchronized(var32){}

         Throwable var10000;
         boolean var10001;
         label283: {
            label290: {
               try {
                  if (sHasDoneDefaultConfigLookup) {
                     break label290;
                  }
               } catch (Throwable var31) {
                  var10000 = var31;
                  var10001 = false;
                  break label283;
               }

               if (var33 != null) {
                  try {
                     init(var33);
                  } catch (Throwable var30) {
                     var10000 = var30;
                     var10001 = false;
                     break label283;
                  }
               }

               try {
                  sHasDoneDefaultConfigLookup = true;
               } catch (Throwable var29) {
                  var10000 = var29;
                  var10001 = false;
                  break label283;
               }
            }

            label273:
            try {
               EmojiCompat var35 = sInstance;
               return var35;
            } catch (Throwable var28) {
               var10000 = var28;
               var10001 = false;
               break label273;
            }
         }

         while(true) {
            Throwable var34 = var10000;

            try {
               throw var34;
            } catch (Throwable var27) {
               var10000 = var27;
               var10001 = false;
               continue;
            }
         }
      }
   }

   public static EmojiCompat init(Config var0) {
      EmojiCompat var2 = sInstance;
      EmojiCompat var1 = var2;
      if (var2 == null) {
         Object var3 = INSTANCE_LOCK;
         synchronized(var3){}

         Throwable var10000;
         boolean var10001;
         label206: {
            try {
               var2 = sInstance;
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label206;
            }

            var1 = var2;
            if (var2 == null) {
               try {
                  var1 = new EmojiCompat(var0);
                  sInstance = var1;
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label206;
               }
            }

            label193:
            try {
               return var1;
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label193;
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
      } else {
         return var1;
      }
   }

   public static boolean isConfigured() {
      boolean var0;
      if (sInstance != null) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   private boolean isInitialized() {
      int var1 = this.getLoadState();
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   private void loadMetadata() {
      this.mInitLock.writeLock().lock();

      try {
         if (this.mMetadataLoadStrategy == 0) {
            this.mLoadState = 0;
         }
      } finally {
         this.mInitLock.writeLock().unlock();
      }

      if (this.getLoadState() == 0) {
         this.mHelper.loadMetadata();
      }

   }

   public static EmojiCompat reset(Config param0) {
      // $FF: Couldn't be decompiled
   }

   public static EmojiCompat reset(EmojiCompat param0) {
      // $FF: Couldn't be decompiled
   }

   public static void skipDefaultConfigurationLookup(boolean param0) {
      // $FF: Couldn't be decompiled
   }

   public String getAssetSignature() {
      Preconditions.checkState(this.isInitialized(), "Not initialized yet");
      return this.mHelper.getAssetSignature();
   }

   public int getEmojiSpanIndicatorColor() {
      return this.mEmojiSpanIndicatorColor;
   }

   public int getLoadState() {
      this.mInitLock.readLock().lock();

      int var1;
      try {
         var1 = this.mLoadState;
      } finally {
         this.mInitLock.readLock().unlock();
      }

      return var1;
   }

   public boolean hasEmojiGlyph(CharSequence var1) {
      Preconditions.checkState(this.isInitialized(), "Not initialized yet");
      Preconditions.checkNotNull(var1, "sequence cannot be null");
      return this.mHelper.hasEmojiGlyph(var1);
   }

   public boolean hasEmojiGlyph(CharSequence var1, int var2) {
      Preconditions.checkState(this.isInitialized(), "Not initialized yet");
      Preconditions.checkNotNull(var1, "sequence cannot be null");
      return this.mHelper.hasEmojiGlyph(var1, var2);
   }

   public boolean isEmojiSpanIndicatorEnabled() {
      return this.mEmojiSpanIndicatorEnabled;
   }

   public void load() {
      int var1 = this.mMetadataLoadStrategy;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      Preconditions.checkState(var2, "Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
      if (!this.isInitialized()) {
         this.mInitLock.writeLock().lock();

         Throwable var10000;
         label103: {
            boolean var10001;
            try {
               var1 = this.mLoadState;
            } catch (Throwable var9) {
               var10000 = var9;
               var10001 = false;
               break label103;
            }

            if (var1 == 0) {
               this.mInitLock.writeLock().unlock();
               return;
            }

            try {
               this.mLoadState = 0;
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label103;
            }

            this.mInitLock.writeLock().unlock();
            this.mHelper.loadMetadata();
            return;
         }

         Throwable var3 = var10000;
         this.mInitLock.writeLock().unlock();
         throw var3;
      }
   }

   void onMetadataLoadFailed(Throwable var1) {
      ArrayList var2 = new ArrayList();
      this.mInitLock.writeLock().lock();

      try {
         this.mLoadState = 2;
         var2.addAll(this.mInitCallbacks);
         this.mInitCallbacks.clear();
      } finally {
         this.mInitLock.writeLock().unlock();
      }

      this.mMainHandler.post(new ListenerDispatcher(var2, this.mLoadState, var1));
   }

   void onMetadataLoadSuccess() {
      ArrayList var1 = new ArrayList();
      this.mInitLock.writeLock().lock();

      try {
         this.mLoadState = 1;
         var1.addAll(this.mInitCallbacks);
         this.mInitCallbacks.clear();
      } finally {
         this.mInitLock.writeLock().unlock();
      }

      this.mMainHandler.post(new ListenerDispatcher(var1, this.mLoadState));
   }

   public CharSequence process(CharSequence var1) {
      int var2;
      if (var1 == null) {
         var2 = 0;
      } else {
         var2 = var1.length();
      }

      return this.process(var1, 0, var2);
   }

   public CharSequence process(CharSequence var1, int var2, int var3) {
      return this.process(var1, var2, var3, Integer.MAX_VALUE);
   }

   public CharSequence process(CharSequence var1, int var2, int var3, int var4) {
      return this.process(var1, var2, var3, var4, 0);
   }

   public CharSequence process(CharSequence var1, int var2, int var3, int var4, int var5) {
      Preconditions.checkState(this.isInitialized(), "Not initialized yet");
      Preconditions.checkArgumentNonnegative(var2, "start cannot be negative");
      Preconditions.checkArgumentNonnegative(var3, "end cannot be negative");
      Preconditions.checkArgumentNonnegative(var4, "maxEmojiCount cannot be negative");
      boolean var7 = false;
      boolean var6;
      if (var2 <= var3) {
         var6 = true;
      } else {
         var6 = false;
      }

      Preconditions.checkArgument(var6, "start should be <= than end");
      if (var1 == null) {
         return null;
      } else {
         if (var2 <= var1.length()) {
            var6 = true;
         } else {
            var6 = false;
         }

         Preconditions.checkArgument(var6, "start should be < than charSequence length");
         if (var3 <= var1.length()) {
            var6 = true;
         } else {
            var6 = false;
         }

         Preconditions.checkArgument(var6, "end should be < than charSequence length");
         CharSequence var8 = var1;
         if (var1.length() != 0) {
            if (var2 == var3) {
               var8 = var1;
            } else {
               if (var5 != 1) {
                  var6 = var7;
                  if (var5 != 2) {
                     var6 = this.mReplaceAll;
                  }
               } else {
                  var6 = true;
               }

               var8 = this.mHelper.process(var1, var2, var3, var4, var6);
            }
         }

         return var8;
      }
   }

   public void registerInitCallback(InitCallback var1) {
      Preconditions.checkNotNull(var1, "initCallback cannot be null");
      this.mInitLock.writeLock().lock();

      label134: {
         Throwable var10000;
         label133: {
            boolean var10001;
            label138: {
               try {
                  if (this.mLoadState != 1 && this.mLoadState != 2) {
                     break label138;
                  }
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label133;
               }

               try {
                  Handler var2 = this.mMainHandler;
                  ListenerDispatcher var3 = new ListenerDispatcher(var1, this.mLoadState);
                  var2.post(var3);
                  break label134;
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label133;
               }
            }

            label121:
            try {
               this.mInitCallbacks.add(var1);
               break label134;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label121;
            }
         }

         Throwable var16 = var10000;
         this.mInitLock.writeLock().unlock();
         throw var16;
      }

      this.mInitLock.writeLock().unlock();
   }

   public void unregisterInitCallback(InitCallback var1) {
      Preconditions.checkNotNull(var1, "initCallback cannot be null");
      this.mInitLock.writeLock().lock();

      try {
         this.mInitCallbacks.remove(var1);
      } finally {
         this.mInitLock.writeLock().unlock();
      }

   }

   public void updateEditorInfo(EditorInfo var1) {
      if (this.isInitialized() && var1 != null) {
         if (var1.extras == null) {
            var1.extras = new Bundle();
         }

         this.mHelper.updateEditorInfoAttrs(var1);
      }

   }

   private static class CompatInternal {
      final EmojiCompat mEmojiCompat;

      CompatInternal(EmojiCompat var1) {
         this.mEmojiCompat = var1;
      }

      String getAssetSignature() {
         return "";
      }

      boolean hasEmojiGlyph(CharSequence var1) {
         return false;
      }

      boolean hasEmojiGlyph(CharSequence var1, int var2) {
         return false;
      }

      void loadMetadata() {
         this.mEmojiCompat.onMetadataLoadSuccess();
      }

      CharSequence process(CharSequence var1, int var2, int var3, int var4, boolean var5) {
         return var1;
      }

      void updateEditorInfoAttrs(EditorInfo var1) {
      }
   }

   private static final class CompatInternal19 extends CompatInternal {
      private volatile MetadataRepo mMetadataRepo;
      private volatile EmojiProcessor mProcessor;

      CompatInternal19(EmojiCompat var1) {
         super(var1);
      }

      String getAssetSignature() {
         String var2 = this.mMetadataRepo.getMetadataList().sourceSha();
         String var1 = var2;
         if (var2 == null) {
            var1 = "";
         }

         return var1;
      }

      boolean hasEmojiGlyph(CharSequence var1) {
         boolean var2;
         if (this.mProcessor.getEmojiMetadata(var1) != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      boolean hasEmojiGlyph(CharSequence var1, int var2) {
         EmojiMetadata var4 = this.mProcessor.getEmojiMetadata(var1);
         boolean var3;
         if (var4 != null && var4.getCompatAdded() <= var2) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      void loadMetadata() {
         try {
            MetadataRepoLoaderCallback var1 = new MetadataRepoLoaderCallback(this) {
               final CompatInternal19 this$0;

               {
                  this.this$0 = var1;
               }

               public void onFailed(Throwable var1) {
                  this.this$0.mEmojiCompat.onMetadataLoadFailed(var1);
               }

               public void onLoaded(MetadataRepo var1) {
                  this.this$0.onMetadataLoadSuccess(var1);
               }
            };
            this.mEmojiCompat.mMetadataLoader.load(var1);
         } catch (Throwable var3) {
            this.mEmojiCompat.onMetadataLoadFailed(var3);
            return;
         }

      }

      void onMetadataLoadSuccess(MetadataRepo var1) {
         if (var1 == null) {
            this.mEmojiCompat.onMetadataLoadFailed(new IllegalArgumentException("metadataRepo cannot be null"));
         } else {
            this.mMetadataRepo = var1;
            this.mProcessor = new EmojiProcessor(this.mMetadataRepo, new SpanFactory(), this.mEmojiCompat.mGlyphChecker, this.mEmojiCompat.mUseEmojiAsDefaultStyle, this.mEmojiCompat.mEmojiAsDefaultStyleExceptions);
            this.mEmojiCompat.onMetadataLoadSuccess();
         }
      }

      CharSequence process(CharSequence var1, int var2, int var3, int var4, boolean var5) {
         return this.mProcessor.process(var1, var2, var3, var4, var5);
      }

      void updateEditorInfoAttrs(EditorInfo var1) {
         var1.extras.putInt("android.support.text.emoji.emojiCompat_metadataVersion", this.mMetadataRepo.getMetadataVersion());
         var1.extras.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", this.mEmojiCompat.mReplaceAll);
      }
   }

   public abstract static class Config {
      int[] mEmojiAsDefaultStyleExceptions;
      int mEmojiSpanIndicatorColor = -16711936;
      boolean mEmojiSpanIndicatorEnabled;
      GlyphChecker mGlyphChecker = new EmojiProcessor.DefaultGlyphChecker();
      Set mInitCallbacks;
      int mMetadataLoadStrategy = 0;
      final MetadataRepoLoader mMetadataLoader;
      boolean mReplaceAll;
      boolean mUseEmojiAsDefaultStyle;

      protected Config(MetadataRepoLoader var1) {
         Preconditions.checkNotNull(var1, "metadataLoader cannot be null.");
         this.mMetadataLoader = var1;
      }

      protected final MetadataRepoLoader getMetadataRepoLoader() {
         return this.mMetadataLoader;
      }

      public Config registerInitCallback(InitCallback var1) {
         Preconditions.checkNotNull(var1, "initCallback cannot be null");
         if (this.mInitCallbacks == null) {
            this.mInitCallbacks = new ArraySet();
         }

         this.mInitCallbacks.add(var1);
         return this;
      }

      public Config setEmojiSpanIndicatorColor(int var1) {
         this.mEmojiSpanIndicatorColor = var1;
         return this;
      }

      public Config setEmojiSpanIndicatorEnabled(boolean var1) {
         this.mEmojiSpanIndicatorEnabled = var1;
         return this;
      }

      public Config setGlyphChecker(GlyphChecker var1) {
         Preconditions.checkNotNull(var1, "GlyphChecker cannot be null");
         this.mGlyphChecker = var1;
         return this;
      }

      public Config setMetadataLoadStrategy(int var1) {
         this.mMetadataLoadStrategy = var1;
         return this;
      }

      public Config setReplaceAll(boolean var1) {
         this.mReplaceAll = var1;
         return this;
      }

      public Config setUseEmojiAsDefaultStyle(boolean var1) {
         return this.setUseEmojiAsDefaultStyle(var1, (List)null);
      }

      public Config setUseEmojiAsDefaultStyle(boolean var1, List var2) {
         this.mUseEmojiAsDefaultStyle = var1;
         if (var1 && var2 != null) {
            this.mEmojiAsDefaultStyleExceptions = new int[var2.size()];
            int var3 = 0;

            for(Iterator var5 = var2.iterator(); var5.hasNext(); ++var3) {
               Integer var4 = (Integer)var5.next();
               this.mEmojiAsDefaultStyleExceptions[var3] = var4;
            }

            Arrays.sort(this.mEmojiAsDefaultStyleExceptions);
         } else {
            this.mEmojiAsDefaultStyleExceptions = null;
         }

         return this;
      }

      public Config unregisterInitCallback(InitCallback var1) {
         Preconditions.checkNotNull(var1, "initCallback cannot be null");
         Set var2 = this.mInitCallbacks;
         if (var2 != null) {
            var2.remove(var1);
         }

         return this;
      }
   }

   public interface GlyphChecker {
      boolean hasGlyph(CharSequence var1, int var2, int var3, int var4);
   }

   public abstract static class InitCallback {
      public void onFailed(Throwable var1) {
      }

      public void onInitialized() {
      }
   }

   private static class ListenerDispatcher implements Runnable {
      private final List mInitCallbacks;
      private final int mLoadState;
      private final Throwable mThrowable;

      ListenerDispatcher(InitCallback var1, int var2) {
         this(Arrays.asList((InitCallback)Preconditions.checkNotNull(var1, "initCallback cannot be null")), var2, (Throwable)null);
      }

      ListenerDispatcher(Collection var1, int var2) {
         this(var1, var2, (Throwable)null);
      }

      ListenerDispatcher(Collection var1, int var2, Throwable var3) {
         Preconditions.checkNotNull(var1, "initCallbacks cannot be null");
         this.mInitCallbacks = new ArrayList(var1);
         this.mLoadState = var2;
         this.mThrowable = var3;
      }

      public void run() {
         int var3 = this.mInitCallbacks.size();
         int var4 = this.mLoadState;
         int var1 = 0;
         byte var2 = 0;
         if (var4 != 1) {
            for(var1 = var2; var1 < var3; ++var1) {
               ((InitCallback)this.mInitCallbacks.get(var1)).onFailed(this.mThrowable);
            }
         } else {
            while(var1 < var3) {
               ((InitCallback)this.mInitCallbacks.get(var1)).onInitialized();
               ++var1;
            }
         }

      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface LoadStrategy {
   }

   public interface MetadataRepoLoader {
      void load(MetadataRepoLoaderCallback var1);
   }

   public abstract static class MetadataRepoLoaderCallback {
      public abstract void onFailed(Throwable var1);

      public abstract void onLoaded(MetadataRepo var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ReplaceStrategy {
   }

   static class SpanFactory {
      EmojiSpan createSpan(EmojiMetadata var1) {
         return new TypefaceEmojiSpan(var1);
      }
   }
}
