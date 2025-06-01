package androidx.emoji2.text;

import android.content.Context;
import android.os.Build.VERSION;
import androidx.core.os.TraceCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class EmojiCompatInitializer implements Initializer {
   private static final long STARTUP_THREAD_CREATION_DELAY_MS = 500L;
   private static final String S_INITIALIZER_THREAD_NAME = "EmojiCompatInitializer";

   public Boolean create(Context var1) {
      if (VERSION.SDK_INT >= 19) {
         EmojiCompat.init((EmojiCompat.Config)(new BackgroundDefaultConfig(var1)));
         this.delayUntilFirstResume(var1);
         return true;
      } else {
         return false;
      }
   }

   void delayUntilFirstResume(Context var1) {
      Lifecycle var2 = ((LifecycleOwner)AppInitializer.getInstance(var1).initializeComponent(ProcessLifecycleInitializer.class)).getLifecycle();
      var2.addObserver(new DefaultLifecycleObserver(this, var2) {
         final EmojiCompatInitializer this$0;
         final Lifecycle val$lifecycle;

         {
            this.this$0 = var1;
            this.val$lifecycle = var2;
         }

         public void onResume(LifecycleOwner var1) {
            this.this$0.loadEmojiCompatAfterDelay();
            this.val$lifecycle.removeObserver(this);
         }
      });
   }

   public List dependencies() {
      return Collections.singletonList(ProcessLifecycleInitializer.class);
   }

   void loadEmojiCompatAfterDelay() {
      ConcurrencyHelpers.mainHandlerAsync().postDelayed(new LoadEmojiCompatRunnable(), 500L);
   }

   static class BackgroundDefaultConfig extends EmojiCompat.Config {
      protected BackgroundDefaultConfig(Context var1) {
         super(new BackgroundDefaultLoader(var1));
         this.setMetadataLoadStrategy(1);
      }
   }

   static class BackgroundDefaultLoader implements EmojiCompat.MetadataRepoLoader {
      private final Context mContext;

      BackgroundDefaultLoader(Context var1) {
         this.mContext = var1.getApplicationContext();
      }

      void doLoad(EmojiCompat.MetadataRepoLoaderCallback var1, ThreadPoolExecutor var2) {
         Throwable var10000;
         label95: {
            FontRequestEmojiCompatConfig var3;
            boolean var10001;
            try {
               var3 = DefaultEmojiCompatConfig.create(this.mContext);
            } catch (Throwable var16) {
               var10000 = var16;
               var10001 = false;
               break label95;
            }

            if (var3 != null) {
               label91:
               try {
                  var3.setLoadingExecutor(var2);
                  EmojiCompat.MetadataRepoLoader var17 = var3.getMetadataRepoLoader();
                  EmojiCompat.MetadataRepoLoaderCallback var4 = new EmojiCompat.MetadataRepoLoaderCallback(this, var1, var2) {
                     final BackgroundDefaultLoader this$0;
                     final ThreadPoolExecutor val$executor;
                     final EmojiCompat.MetadataRepoLoaderCallback val$loaderCallback;

                     {
                        this.this$0 = var1;
                        this.val$loaderCallback = var2;
                        this.val$executor = var3;
                     }

                     public void onFailed(Throwable var1) {
                        try {
                           this.val$loaderCallback.onFailed(var1);
                        } finally {
                           this.val$executor.shutdown();
                        }

                     }

                     public void onLoaded(MetadataRepo var1) {
                        try {
                           this.val$loaderCallback.onLoaded(var1);
                        } finally {
                           this.val$executor.shutdown();
                        }

                     }
                  };
                  var17.load(var4);
                  return;
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label91;
               }
            } else {
               label88:
               try {
                  RuntimeException var19 = new RuntimeException("EmojiCompat font provider not available on this device.");
                  throw var19;
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label88;
               }
            }
         }

         Throwable var18 = var10000;
         var1.onFailed(var18);
         var2.shutdown();
      }

      // $FF: synthetic method
      void lambda$load$0$androidx_emoji2_text_EmojiCompatInitializer$BackgroundDefaultLoader(EmojiCompat.MetadataRepoLoaderCallback var1, ThreadPoolExecutor var2) {
         this.doLoad(var1, var2);
      }

      public void load(EmojiCompat.MetadataRepoLoaderCallback var1) {
         ThreadPoolExecutor var2 = ConcurrencyHelpers.createBackgroundPriorityExecutor("EmojiCompatInitializer");
         var2.execute(new EmojiCompatInitializer$BackgroundDefaultLoader$$ExternalSyntheticLambda0(this, var1, var2));
      }
   }

   static class LoadEmojiCompatRunnable implements Runnable {
      public void run() {
         try {
            TraceCompat.beginSection("EmojiCompat.EmojiCompatInitializer.run");
            if (EmojiCompat.isConfigured()) {
               EmojiCompat.get().load();
            }
         } finally {
            TraceCompat.endSection();
         }

      }
   }
}
