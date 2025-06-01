package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a \u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a\f\u0010\b\u001a\u00020\u0004*\u00020\tH\u0007\u001a\f\u0010\n\u001a\u00020\t*\u00020\u0004H\u0007¨\u0006\u000b"},
   d2 = {"convertDurationUnit", "", "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "", "convertDurationUnitOverflow", "toDurationUnit", "Ljava/util/concurrent/TimeUnit;", "toTimeUnit", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/time/DurationUnitKt"
)
class DurationUnitKt__DurationUnitJvmKt {
   public DurationUnitKt__DurationUnitJvmKt() {
   }

   public static final double convertDurationUnit(double var0, DurationUnit var2, DurationUnit var3) {
      Intrinsics.checkNotNullParameter(var2, "sourceUnit");
      Intrinsics.checkNotNullParameter(var3, "targetUnit");
      long var4 = var3.getTimeUnit$kotlin_stdlib().convert(1L, var2.getTimeUnit$kotlin_stdlib());
      return var4 > 0L ? var0 * (double)var4 : var0 / (double)var2.getTimeUnit$kotlin_stdlib().convert(1L, var3.getTimeUnit$kotlin_stdlib());
   }

   public static final long convertDurationUnit(long var0, DurationUnit var2, DurationUnit var3) {
      Intrinsics.checkNotNullParameter(var2, "sourceUnit");
      Intrinsics.checkNotNullParameter(var3, "targetUnit");
      return var3.getTimeUnit$kotlin_stdlib().convert(var0, var2.getTimeUnit$kotlin_stdlib());
   }

   public static final long convertDurationUnitOverflow(long var0, DurationUnit var2, DurationUnit var3) {
      Intrinsics.checkNotNullParameter(var2, "sourceUnit");
      Intrinsics.checkNotNullParameter(var3, "targetUnit");
      return var3.getTimeUnit$kotlin_stdlib().convert(var0, var2.getTimeUnit$kotlin_stdlib());
   }

   public static final DurationUnit toDurationUnit(TimeUnit var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      DurationUnit var1;
      switch (WhenMappings.$EnumSwitchMapping$0[var0.ordinal()]) {
         case 1:
            var1 = DurationUnit.NANOSECONDS;
            break;
         case 2:
            var1 = DurationUnit.MICROSECONDS;
            break;
         case 3:
            var1 = DurationUnit.MILLISECONDS;
            break;
         case 4:
            var1 = DurationUnit.SECONDS;
            break;
         case 5:
            var1 = DurationUnit.MINUTES;
            break;
         case 6:
            var1 = DurationUnit.HOURS;
            break;
         case 7:
            var1 = DurationUnit.DAYS;
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var1;
   }

   public static final TimeUnit toTimeUnit(DurationUnit var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.getTimeUnit$kotlin_stdlib();
   }

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class WhenMappings {
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[TimeUnit.values().length];
         var0[TimeUnit.NANOSECONDS.ordinal()] = 1;
         var0[TimeUnit.MICROSECONDS.ordinal()] = 2;
         var0[TimeUnit.MILLISECONDS.ordinal()] = 3;
         var0[TimeUnit.SECONDS.ordinal()] = 4;
         var0[TimeUnit.MINUTES.ordinal()] = 5;
         var0[TimeUnit.HOURS.ordinal()] = 6;
         var0[TimeUnit.DAYS.ordinal()] = 7;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
