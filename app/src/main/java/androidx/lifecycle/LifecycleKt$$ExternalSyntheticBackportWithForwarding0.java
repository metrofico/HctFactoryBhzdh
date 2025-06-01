package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

public final class LifecycleKt$$ExternalSyntheticBackportWithForwarding0 {
   // $FF: synthetic method
   public static boolean m(AtomicReference var0, Object var1, Object var2) {
      do {
         if (var0.compareAndSet(var1, var2)) {
            return true;
         }
      } while(var0.get() == var1);

      return false;
   }
}
