package androidx.emoji2.text;

import java.util.concurrent.ThreadFactory;

public final class ConcurrencyHelpers$$ExternalSyntheticLambda0 implements ThreadFactory {
   public final String f$0;

   // $FF: synthetic method
   public ConcurrencyHelpers$$ExternalSyntheticLambda0(String var1) {
      this.f$0 = var1;
   }

   public final Thread newThread(Runnable var1) {
      return ConcurrencyHelpers.lambda$createBackgroundPriorityExecutor$0(this.f$0, var1);
   }
}
