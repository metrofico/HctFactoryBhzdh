package androidx.emoji2.text;

import java.util.concurrent.ThreadPoolExecutor;

public final class EmojiCompatInitializer$BackgroundDefaultLoader$$ExternalSyntheticLambda0 implements Runnable {
   public final EmojiCompatInitializer.BackgroundDefaultLoader f$0;
   public final EmojiCompat.MetadataRepoLoaderCallback f$1;
   public final ThreadPoolExecutor f$2;

   // $FF: synthetic method
   public EmojiCompatInitializer$BackgroundDefaultLoader$$ExternalSyntheticLambda0(EmojiCompatInitializer.BackgroundDefaultLoader var1, EmojiCompat.MetadataRepoLoaderCallback var2, ThreadPoolExecutor var3) {
      this.f$0 = var1;
      this.f$1 = var2;
      this.f$2 = var3;
   }

   public final void run() {
      this.f$0.lambda$load$0$androidx_emoji2_text_EmojiCompatInitializer$BackgroundDefaultLoader(this.f$1, this.f$2);
   }
}
