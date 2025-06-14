package kotlinx.android.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"},
   d2 = {"Lkotlinx/android/extensions/CacheImplementation;", "", "(Ljava/lang/String;I)V", "SPARSE_ARRAY", "HASH_MAP", "NO_CACHE", "Companion", "kotlin-android-extensions-runtime"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public enum CacheImplementation {
   private static final CacheImplementation[] $VALUES;
   public static final Companion Companion;
   private static final CacheImplementation DEFAULT;
   HASH_MAP,
   NO_CACHE,
   SPARSE_ARRAY;

   // $FF: synthetic method
   private static final CacheImplementation[] $values() {
      return new CacheImplementation[]{SPARSE_ARRAY, HASH_MAP, NO_CACHE};
   }

   static {
      CacheImplementation var0 = new CacheImplementation("HASH_MAP", 1);
      HASH_MAP = var0;
      NO_CACHE = new CacheImplementation("NO_CACHE", 2);
      $VALUES = $values();
      Companion = new Companion((DefaultConstructorMarker)null);
      DEFAULT = var0;
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
      d2 = {"Lkotlinx/android/extensions/CacheImplementation$Companion;", "", "()V", "DEFAULT", "Lkotlinx/android/extensions/CacheImplementation;", "getDEFAULT", "()Lkotlinx/android/extensions/CacheImplementation;", "kotlin-android-extensions-runtime"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      public final CacheImplementation getDEFAULT() {
         return CacheImplementation.DEFAULT;
      }
   }
}
