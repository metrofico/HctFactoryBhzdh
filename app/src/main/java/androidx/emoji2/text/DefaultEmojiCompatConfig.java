package androidx.emoji2.text;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.core.provider.FontRequest;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DefaultEmojiCompatConfig {
   private DefaultEmojiCompatConfig() {
   }

   public static FontRequestEmojiCompatConfig create(Context var0) {
      return (FontRequestEmojiCompatConfig)(new DefaultEmojiCompatConfigFactory((DefaultEmojiCompatConfigHelper)null)).create(var0);
   }

   public static class DefaultEmojiCompatConfigFactory {
      private static final String DEFAULT_EMOJI_QUERY = "emojicompat-emoji-font";
      private static final String INTENT_LOAD_EMOJI_FONT = "androidx.content.action.LOAD_EMOJI_FONT";
      private static final String TAG = "emoji2.text.DefaultEmojiConfig";
      private final DefaultEmojiCompatConfigHelper mHelper;

      public DefaultEmojiCompatConfigFactory(DefaultEmojiCompatConfigHelper var1) {
         if (var1 == null) {
            var1 = getHelperForApi();
         }

         this.mHelper = var1;
      }

      private EmojiCompat.Config configOrNull(Context var1, FontRequest var2) {
         return var2 == null ? null : new FontRequestEmojiCompatConfig(var1, var2);
      }

      private List convertToByteArray(Signature[] var1) {
         ArrayList var4 = new ArrayList();
         int var3 = var1.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            var4.add(var1[var2].toByteArray());
         }

         return Collections.singletonList(var4);
      }

      private FontRequest generateFontRequestFrom(ProviderInfo var1, PackageManager var2) throws PackageManager.NameNotFoundException {
         String var3 = var1.authority;
         String var4 = var1.packageName;
         return new FontRequest(var3, var4, "emojicompat-emoji-font", this.convertToByteArray(this.mHelper.getSigningSignatures(var2, var4)));
      }

      private static DefaultEmojiCompatConfigHelper getHelperForApi() {
         if (VERSION.SDK_INT >= 28) {
            return new DefaultEmojiCompatConfigHelper_API28();
         } else {
            return (DefaultEmojiCompatConfigHelper)(VERSION.SDK_INT >= 19 ? new DefaultEmojiCompatConfigHelper_API19() : new DefaultEmojiCompatConfigHelper());
         }
      }

      private boolean hasFlagSystem(ProviderInfo var1) {
         boolean var2 = true;
         if (var1 == null || var1.applicationInfo == null || (var1.applicationInfo.flags & 1) != 1) {
            var2 = false;
         }

         return var2;
      }

      private ProviderInfo queryDefaultInstalledContentProvider(PackageManager var1) {
         Iterator var3 = this.mHelper.queryIntentContentProviders(var1, new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();

         ProviderInfo var4;
         do {
            if (!var3.hasNext()) {
               return null;
            }

            ResolveInfo var2 = (ResolveInfo)var3.next();
            var4 = this.mHelper.getProviderInfo(var2);
         } while(!this.hasFlagSystem(var4));

         return var4;
      }

      public EmojiCompat.Config create(Context var1) {
         return this.configOrNull(var1, this.queryForDefaultFontRequest(var1));
      }

      FontRequest queryForDefaultFontRequest(Context var1) {
         PackageManager var2 = var1.getPackageManager();
         Preconditions.checkNotNull(var2, "Package manager required to locate emoji font provider");
         ProviderInfo var4 = this.queryDefaultInstalledContentProvider(var2);
         if (var4 == null) {
            return null;
         } else {
            try {
               FontRequest var5 = this.generateFontRequestFrom(var4, var2);
               return var5;
            } catch (PackageManager.NameNotFoundException var3) {
               Log.wtf("emoji2.text.DefaultEmojiConfig", var3);
               return null;
            }
         }
      }
   }

   public static class DefaultEmojiCompatConfigHelper {
      public ProviderInfo getProviderInfo(ResolveInfo var1) {
         throw new IllegalStateException("Unable to get provider info prior to API 19");
      }

      public Signature[] getSigningSignatures(PackageManager var1, String var2) throws PackageManager.NameNotFoundException {
         return var1.getPackageInfo(var2, 64).signatures;
      }

      public List queryIntentContentProviders(PackageManager var1, Intent var2, int var3) {
         return Collections.emptyList();
      }
   }

   public static class DefaultEmojiCompatConfigHelper_API19 extends DefaultEmojiCompatConfigHelper {
      public ProviderInfo getProviderInfo(ResolveInfo var1) {
         return var1.providerInfo;
      }

      public List queryIntentContentProviders(PackageManager var1, Intent var2, int var3) {
         return var1.queryIntentContentProviders(var2, var3);
      }
   }

   public static class DefaultEmojiCompatConfigHelper_API28 extends DefaultEmojiCompatConfigHelper_API19 {
      public Signature[] getSigningSignatures(PackageManager var1, String var2) throws PackageManager.NameNotFoundException {
         return var1.getPackageInfo(var2, 64).signatures;
      }
   }
}
