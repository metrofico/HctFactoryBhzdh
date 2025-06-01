package androidx.concurrent.futures;

import java.util.concurrent.Executor;

enum DirectExecutor implements Executor {
   private static final DirectExecutor[] $VALUES;
   INSTANCE;

   static {
      DirectExecutor var0 = new DirectExecutor("INSTANCE", 0);
      INSTANCE = var0;
      $VALUES = new DirectExecutor[]{var0};
   }

   public void execute(Runnable var1) {
      var1.run();
   }

   public String toString() {
      return "DirectExecutor";
   }
}
