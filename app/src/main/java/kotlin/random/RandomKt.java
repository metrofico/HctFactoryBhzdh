package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;

@Metadata(
   d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0007\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\fH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0000\u001a\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u000f\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u001a\u0014\u0010\u0012\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0013H\u0007\u001a\u0014\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0000¨\u0006\u0016"},
   d2 = {"Random", "Lkotlin/random/Random;", "seed", "", "", "boundsErrorMessage", "", "from", "", "until", "checkRangeBounds", "", "", "fastLog2", "value", "nextInt", "range", "Lkotlin/ranges/IntRange;", "nextLong", "Lkotlin/ranges/LongRange;", "takeUpperBits", "bitCount", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class RandomKt {
   public static final Random Random(int var0) {
      return (Random)(new XorWowRandom(var0, var0 >> 31));
   }

   public static final Random Random(long var0) {
      return (Random)(new XorWowRandom((int)var0, (int)(var0 >> 32)));
   }

   public static final String boundsErrorMessage(Object var0, Object var1) {
      Intrinsics.checkNotNullParameter(var0, "from");
      Intrinsics.checkNotNullParameter(var1, "until");
      return "Random range is empty: [" + var0 + ", " + var1 + ").";
   }

   public static final void checkRangeBounds(double var0, double var2) {
      boolean var4;
      if (var2 > var0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4) {
         throw new IllegalArgumentException(boundsErrorMessage(var0, var2).toString());
      }
   }

   public static final void checkRangeBounds(int var0, int var1) {
      boolean var2;
      if (var1 > var0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (!var2) {
         throw new IllegalArgumentException(boundsErrorMessage(var0, var1).toString());
      }
   }

   public static final void checkRangeBounds(long var0, long var2) {
      boolean var4;
      if (var2 > var0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4) {
         throw new IllegalArgumentException(boundsErrorMessage(var0, var2).toString());
      }
   }

   public static final int fastLog2(int var0) {
      return 31 - Integer.numberOfLeadingZeros(var0);
   }

   public static final int nextInt(Random var0, IntRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      if (!var1.isEmpty()) {
         int var2;
         if (var1.getLast() < Integer.MAX_VALUE) {
            var2 = var0.nextInt(var1.getFirst(), var1.getLast() + 1);
         } else if (var1.getFirst() > Integer.MIN_VALUE) {
            var2 = var0.nextInt(var1.getFirst() - 1, var1.getLast()) + 1;
         } else {
            var2 = var0.nextInt();
         }

         return var2;
      } else {
         throw new IllegalArgumentException("Cannot get random in empty range: " + var1);
      }
   }

   public static final long nextLong(Random var0, LongRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      if (!var1.isEmpty()) {
         long var2;
         if (var1.getLast() < Long.MAX_VALUE) {
            var2 = var0.nextLong(var1.getFirst(), var1.getLast() + 1L);
         } else if (var1.getFirst() > Long.MIN_VALUE) {
            var2 = var0.nextLong(var1.getFirst() - 1L, var1.getLast()) + 1L;
         } else {
            var2 = var0.nextLong();
         }

         return var2;
      } else {
         throw new IllegalArgumentException("Cannot get random in empty range: " + var1);
      }
   }

   public static final int takeUpperBits(int var0, int var1) {
      return var0 >>> 32 - var1 & -var1 >> 31;
   }
}
