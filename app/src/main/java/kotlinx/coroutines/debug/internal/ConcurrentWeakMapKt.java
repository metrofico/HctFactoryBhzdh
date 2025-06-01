package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\b\u0010\b\u001a\u00020\tH\u0002\u001a\u000e\u0010\n\u001a\u00020\u0003*\u0004\u0018\u00010\u000bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"},
   d2 = {"MAGIC", "", "MARKED_NULL", "Lkotlinx/coroutines/debug/internal/Marked;", "MARKED_TRUE", "MIN_CAPACITY", "REHASH", "Lkotlinx/coroutines/internal/Symbol;", "noImpl", "", "mark", "", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ConcurrentWeakMapKt {
   private static final int MAGIC = -1640531527;
   private static final Marked MARKED_NULL = new Marked((Object)null);
   private static final Marked MARKED_TRUE = new Marked(true);
   private static final int MIN_CAPACITY = 16;
   private static final Symbol REHASH = new Symbol("REHASH");

   // $FF: synthetic method
   public static final Symbol access$getREHASH$p() {
      return REHASH;
   }

   // $FF: synthetic method
   public static final Marked access$mark(Object var0) {
      return mark(var0);
   }

   // $FF: synthetic method
   public static final Void access$noImpl() {
      return noImpl();
   }

   private static final Marked mark(Object var0) {
      Marked var1;
      if (var0 == null) {
         var1 = MARKED_NULL;
      } else if (Intrinsics.areEqual((Object)var0, (Object)true)) {
         var1 = MARKED_TRUE;
      } else {
         var1 = new Marked(var0);
      }

      return var1;
   }

   private static final Void noImpl() {
      throw (Throwable)(new UnsupportedOperationException("not implemented"));
   }
}
