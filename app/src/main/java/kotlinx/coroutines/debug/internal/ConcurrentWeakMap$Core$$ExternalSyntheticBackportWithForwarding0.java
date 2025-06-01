package kotlinx.coroutines.debug.internal;

import java.util.concurrent.atomic.AtomicReferenceArray;

public final class ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0 {
   // $FF: synthetic method
   public static boolean m(AtomicReferenceArray var0, int var1, Object var2, Object var3) {
      do {
         if (var0.compareAndSet(var1, var2, var3)) {
            return true;
         }
      } while(var0.get(var1) == var2);

      return false;
   }
}
