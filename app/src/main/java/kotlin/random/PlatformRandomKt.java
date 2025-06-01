package kotlin.random;

import kotlin.Metadata;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\t\u0010\u0000\u001a\u00020\u0001H\u0081\b\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0007\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0007¨\u0006\n"},
   d2 = {"defaultPlatformRandom", "Lkotlin/random/Random;", "doubleFromParts", "", "hi26", "", "low27", "asJavaRandom", "Ljava/util/Random;", "asKotlinRandom", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class PlatformRandomKt {
   public static final java.util.Random asJavaRandom(Random var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      AbstractPlatformRandom var1;
      if (var0 instanceof AbstractPlatformRandom) {
         var1 = (AbstractPlatformRandom)var0;
      } else {
         var1 = null;
      }

      java.util.Random var3;
      if (var1 != null) {
         java.util.Random var2 = var1.getImpl();
         var3 = var2;
         if (var2 != null) {
            return var3;
         }
      }

      var3 = (java.util.Random)(new KotlinRandom(var0));
      return var3;
   }

   public static final Random asKotlinRandom(java.util.Random var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      KotlinRandom var1;
      if (var0 instanceof KotlinRandom) {
         var1 = (KotlinRandom)var0;
      } else {
         var1 = null;
      }

      Random var3;
      if (var1 != null) {
         Random var2 = var1.getImpl();
         var3 = var2;
         if (var2 != null) {
            return var3;
         }
      }

      var3 = (Random)(new PlatformRandom(var0));
      return var3;
   }

   private static final Random defaultPlatformRandom() {
      return PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
   }

   public static final double doubleFromParts(int var0, int var1) {
      return (double)(((long)var0 << 27) + (long)var1) / 9.007199254740992E15;
   }
}
