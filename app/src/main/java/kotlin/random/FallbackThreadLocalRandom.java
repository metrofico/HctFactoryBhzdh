package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\b\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\n"},
   d2 = {"Lkotlin/random/FallbackThreadLocalRandom;", "Lkotlin/random/AbstractPlatformRandom;", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "implStorage", "kotlin/random/FallbackThreadLocalRandom$implStorage$1", "Lkotlin/random/FallbackThreadLocalRandom$implStorage$1;", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {
   private final <undefinedtype> implStorage = new ThreadLocal() {
      protected java.util.Random initialValue() {
         return new java.util.Random();
      }
   };

   public java.util.Random getImpl() {
      Object var1 = this.implStorage.get();
      Intrinsics.checkNotNullExpressionValue(var1, "implStorage.get()");
      return (java.util.Random)var1;
   }
}
