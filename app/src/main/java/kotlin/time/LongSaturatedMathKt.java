package kotlin.time;

import kotlin.Metadata;

@Metadata(
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\"\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\"\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\n\u001a \u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"},
   d2 = {"checkInfiniteSumDefined", "", "longNs", "duration", "Lkotlin/time/Duration;", "durationNs", "checkInfiniteSumDefined-PjuGub4", "(JJJ)J", "saturatingAdd", "saturatingAdd-pTJri5U", "(JJ)J", "saturatingAddInHalves", "saturatingAddInHalves-pTJri5U", "saturatingDiff", "valueNs", "originNs", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class LongSaturatedMathKt {
   private static final long checkInfiniteSumDefined_PjuGub4(long var0, long var2, long var4) {
      if (Duration.isInfinite_impl(var2) && (var0 ^ var4) < 0L) {
         throw new IllegalArgumentException("Summing infinities of different signs");
      } else {
         return var0;
      }
   }

   public static final long saturatingAdd_pTJri5U(long var0, long var2) {
      long var6 = Duration.getInWholeNanoseconds_impl(var2);
      long var4 = Long.MAX_VALUE;
      if ((var0 - 1L | 1L) == Long.MAX_VALUE) {
         return checkInfiniteSumDefined_PjuGub4(var0, var2, var6);
      } else if ((1L | var6 - 1L) == Long.MAX_VALUE) {
         return saturatingAddInHalves_pTJri5U(var0, var2);
      } else {
         var2 = var0 + var6;
         if (((var0 ^ var2) & (var6 ^ var2)) < 0L) {
            var2 = var4;
            if (var0 < 0L) {
               var2 = Long.MIN_VALUE;
            }

            return var2;
         } else {
            return var2;
         }
      }
   }

   private static final long saturatingAddInHalves_pTJri5U(long var0, long var2) {
      long var4 = Duration.div_UwyO8pc(var2, 2);
      return (Duration.getInWholeNanoseconds_impl(var4) - 1L | 1L) == Long.MAX_VALUE ? (long)((double)var0 + Duration.toDouble_impl(var2, DurationUnit.NANOSECONDS)) : saturatingAdd_pTJri5U(saturatingAdd_pTJri5U(var0, var4), var4);
   }

   public static final long saturatingDiff(long var0, long var2) {
      if ((1L | var2 - 1L) == Long.MAX_VALUE) {
         return Duration.unaryMinus_UwyO8pc(DurationKt.toDuration(var2, DurationUnit.DAYS));
      } else {
         long var4 = var0 - var2;
         Duration.Companion var10;
         if (((var4 ^ var0) & ~(var4 ^ var2)) < 0L) {
            var4 = (long)1000000;
            long var8 = var0 / var4;
            long var6 = var2 / var4;
            var10 = Duration.Companion;
            var6 = DurationKt.toDuration(var8 - var6, DurationUnit.MILLISECONDS);
            var10 = Duration.Companion;
            return Duration.plus_LRDsOJo(var6, DurationKt.toDuration(var0 % var4 - var2 % var4, DurationUnit.NANOSECONDS));
         } else {
            var10 = Duration.Companion;
            return DurationKt.toDuration(var4, DurationUnit.NANOSECONDS);
         }
      }
   }
}
