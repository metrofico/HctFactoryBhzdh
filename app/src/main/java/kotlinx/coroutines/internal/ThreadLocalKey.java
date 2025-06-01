package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0011\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u0007\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"},
   d2 = {"Lkotlinx/coroutines/internal/ThreadLocalKey;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/internal/ThreadLocalElement;", "threadLocal", "Ljava/lang/ThreadLocal;", "(Ljava/lang/ThreadLocal;)V", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ThreadLocalKey implements CoroutineContext.Key {
   private final ThreadLocal threadLocal;

   public ThreadLocalKey(ThreadLocal var1) {
      this.threadLocal = var1;
   }

   private final ThreadLocal component1() {
      return this.threadLocal;
   }

   // $FF: synthetic method
   public static ThreadLocalKey copy$default(ThreadLocalKey var0, ThreadLocal var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.threadLocal;
      }

      return var0.copy(var1);
   }

   public final ThreadLocalKey copy(ThreadLocal var1) {
      return new ThreadLocalKey(var1);
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (!(var1 instanceof ThreadLocalKey)) {
            return false;
         }

         ThreadLocalKey var2 = (ThreadLocalKey)var1;
         if (!Intrinsics.areEqual((Object)this.threadLocal, (Object)var2.threadLocal)) {
            return false;
         }
      }

      return true;
   }

   public int hashCode() {
      ThreadLocal var2 = this.threadLocal;
      int var1;
      if (var2 != null) {
         var1 = var2.hashCode();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public String toString() {
      return "ThreadLocalKey(threadLocal=" + this.threadLocal + ")";
   }
}
