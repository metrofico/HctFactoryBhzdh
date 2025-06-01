package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0001\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0001¨\u0006\t"},
   d2 = {"durationUnitByIsoChar", "Lkotlin/time/DurationUnit;", "isoChar", "", "isTimeComponent", "", "durationUnitByShortName", "shortName", "", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/time/DurationUnitKt"
)
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {
   public DurationUnitKt__DurationUnitKt() {
   }

   public static final DurationUnit durationUnitByIsoChar(char var0, boolean var1) {
      DurationUnit var2;
      if (!var1) {
         if (var0 != 'D') {
            throw new IllegalArgumentException("Invalid or unsupported duration ISO non-time unit: " + var0);
         }

         var2 = DurationUnit.DAYS;
      } else if (var0 == 'H') {
         var2 = DurationUnit.HOURS;
      } else if (var0 == 'M') {
         var2 = DurationUnit.MINUTES;
      } else {
         if (var0 != 'S') {
            throw new IllegalArgumentException("Invalid duration ISO time unit: " + var0);
         }

         var2 = DurationUnit.SECONDS;
      }

      return var2;
   }

   public static final DurationUnit durationUnitByShortName(String var0) {
      Intrinsics.checkNotNullParameter(var0, "shortName");
      int var1 = var0.hashCode();
      DurationUnit var2;
      if (var1 != 100) {
         if (var1 != 104) {
            if (var1 != 109) {
               if (var1 != 115) {
                  if (var1 != 3494) {
                     if (var1 != 3525) {
                        if (var1 == 3742 && var0.equals("us")) {
                           var2 = DurationUnit.MICROSECONDS;
                           return var2;
                        }
                     } else if (var0.equals("ns")) {
                        var2 = DurationUnit.NANOSECONDS;
                        return var2;
                     }
                  } else if (var0.equals("ms")) {
                     var2 = DurationUnit.MILLISECONDS;
                     return var2;
                  }
               } else if (var0.equals("s")) {
                  var2 = DurationUnit.SECONDS;
                  return var2;
               }
            } else if (var0.equals("m")) {
               var2 = DurationUnit.MINUTES;
               return var2;
            }
         } else if (var0.equals("h")) {
            var2 = DurationUnit.HOURS;
            return var2;
         }
      } else if (var0.equals("d")) {
         var2 = DurationUnit.DAYS;
         return var2;
      }

      throw new IllegalArgumentException("Unknown duration unit short name: " + var0);
   }

   public static final String shortName(DurationUnit var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      String var1;
      switch (WhenMappings.$EnumSwitchMapping$0[var0.ordinal()]) {
         case 1:
            var1 = "ns";
            break;
         case 2:
            var1 = "us";
            break;
         case 3:
            var1 = "ms";
            break;
         case 4:
            var1 = "s";
            break;
         case 5:
            var1 = "m";
            break;
         case 6:
            var1 = "h";
            break;
         case 7:
            var1 = "d";
            break;
         default:
            throw new IllegalStateException(("Unknown unit: " + var0).toString());
      }

      return var1;
   }

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class WhenMappings {
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[DurationUnit.values().length];
         var0[DurationUnit.NANOSECONDS.ordinal()] = 1;
         var0[DurationUnit.MICROSECONDS.ordinal()] = 2;
         var0[DurationUnit.MILLISECONDS.ordinal()] = 3;
         var0[DurationUnit.SECONDS.ordinal()] = 4;
         var0[DurationUnit.MINUTES.ordinal()] = 5;
         var0[DurationUnit.HOURS.ordinal()] = 6;
         var0[DurationUnit.DAYS.ordinal()] = 7;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
