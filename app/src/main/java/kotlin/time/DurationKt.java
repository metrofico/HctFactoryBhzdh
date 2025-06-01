package kotlin.time;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.CharRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000>\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b*\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0005H\u0002ø\u0001\u0000¢\u0006\u0002\u0010&\u001a\u0018\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0010\u0010/\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0001H\u0002\u001a\u0010\u00100\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u0001H\u0002\u001a \u00101\u001a\u00020\u00072\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000¢\u0006\u0002\u00106\u001a\u0010\u00107\u001a\u00020\u00012\u0006\u00102\u001a\u000203H\u0002\u001a)\u00108\u001a\u00020\u0005*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\u0082\b\u001a)\u0010=\u001a\u000203*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\u0082\b\u001a\u001f\u0010>\u001a\u00020\u0007*\u00020\b2\u0006\u0010?\u001a\u00020\u0007H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a\u001f\u0010>\u001a\u00020\u0007*\u00020\u00052\u0006\u0010?\u001a\u00020\u0007H\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\b2\u0006\u0010E\u001a\u00020FH\u0007ø\u0001\u0000¢\u0006\u0002\u0010G\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\u00052\u0006\u0010E\u001a\u00020FH\u0007ø\u0001\u0000¢\u0006\u0002\u0010H\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\u00012\u0006\u0010E\u001a\u00020FH\u0007ø\u0001\u0000¢\u0006\u0002\u0010I\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"!\u0010\u0006\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\r\u001a\u0004\b\u000b\u0010\u000e\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\u000f\u001a\u0004\b\u000b\u0010\u0010\"!\u0010\u0011\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\f\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000e\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0010\"!\u0010\u0014\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\f\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000e\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0010\"!\u0010\u0017\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\n\u001a\u0004\b\u0019\u0010\f\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\r\u001a\u0004\b\u0019\u0010\u000e\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0010\"!\u0010\u001a\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\f\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\r\u001a\u0004\b\u001c\u0010\u000e\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001c\u0010\u0010\"!\u0010\u001d\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\n\u001a\u0004\b\u001f\u0010\f\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\r\u001a\u0004\b\u001f\u0010\u000e\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001f\u0010\u0010\"!\u0010 \u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\n\u001a\u0004\b\"\u0010\f\"!\u0010 \u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\r\u001a\u0004\b\"\u0010\u000e\"!\u0010 \u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\u000f\u001a\u0004\b\"\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006J"},
   d2 = {"MAX_MILLIS", "", "MAX_NANOS", "MAX_NANOS_IN_MILLIS", "NANOS_IN_MILLIS", "", "days", "Lkotlin/time/Duration;", "", "getDays$annotations", "(D)V", "getDays", "(D)J", "(I)V", "(I)J", "(J)V", "(J)J", "hours", "getHours$annotations", "getHours", "microseconds", "getMicroseconds$annotations", "getMicroseconds", "milliseconds", "getMilliseconds$annotations", "getMilliseconds", "minutes", "getMinutes$annotations", "getMinutes", "nanoseconds", "getNanoseconds$annotations", "getNanoseconds", "seconds", "getSeconds$annotations", "getSeconds", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfMillis", "normalMillis", "durationOfMillisNormalized", "millis", "durationOfNanos", "normalNanos", "durationOfNanosNormalized", "nanos", "millisToNanos", "nanosToMillis", "parseDuration", "value", "", "strictIso", "", "(Ljava/lang/String;Z)J", "parseOverLongIsoComponent", "skipWhile", "startIndex", "predicate", "Lkotlin/Function1;", "", "substringWhile", "times", "duration", "times-kIfJnKk", "(DJ)J", "times-mvk6XK0", "(IJ)J", "toDuration", "unit", "Lkotlin/time/DurationUnit;", "(DLkotlin/time/DurationUnit;)J", "(ILkotlin/time/DurationUnit;)J", "(JLkotlin/time/DurationUnit;)J", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class DurationKt {
   public static final long MAX_MILLIS = 4611686018427387903L;
   public static final long MAX_NANOS = 4611686018426999999L;
   private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
   public static final int NANOS_IN_MILLIS = 1000000;

   // $FF: synthetic method
   public static final long access$durationOf(long var0, int var2) {
      return durationOf(var0, var2);
   }

   // $FF: synthetic method
   public static final long access$durationOfMillis(long var0) {
      return durationOfMillis(var0);
   }

   // $FF: synthetic method
   public static final long access$durationOfMillisNormalized(long var0) {
      return durationOfMillisNormalized(var0);
   }

   // $FF: synthetic method
   public static final long access$durationOfNanos(long var0) {
      return durationOfNanos(var0);
   }

   // $FF: synthetic method
   public static final long access$durationOfNanosNormalized(long var0) {
      return durationOfNanosNormalized(var0);
   }

   // $FF: synthetic method
   public static final long access$millisToNanos(long var0) {
      return millisToNanos(var0);
   }

   // $FF: synthetic method
   public static final long access$nanosToMillis(long var0) {
      return nanosToMillis(var0);
   }

   // $FF: synthetic method
   public static final long access$parseDuration(String var0, boolean var1) {
      return parseDuration(var0, var1);
   }

   private static final long durationOf(long var0, int var2) {
      return Duration.constructor_impl((var0 << 1) + (long)var2);
   }

   private static final long durationOfMillis(long var0) {
      return Duration.constructor_impl((var0 << 1) + 1L);
   }

   private static final long durationOfMillisNormalized(long var0) {
      if ((new LongRange(-4611686018426L, 4611686018426L)).contains(var0)) {
         var0 = durationOfNanos(millisToNanos(var0));
      } else {
         var0 = durationOfMillis(RangesKt.coerceIn(var0, -4611686018427387903L, 4611686018427387903L));
      }

      return var0;
   }

   private static final long durationOfNanos(long var0) {
      return Duration.constructor_impl(var0 << 1);
   }

   private static final long durationOfNanosNormalized(long var0) {
      if ((new LongRange(-4611686018426999999L, 4611686018426999999L)).contains(var0)) {
         var0 = durationOfNanos(var0);
      } else {
         var0 = durationOfMillis(nanosToMillis(var0));
      }

      return var0;
   }

   public static final long getDays(double var0) {
      return toDuration(var0, DurationUnit.DAYS);
   }

   public static final long getDays(int var0) {
      return toDuration(var0, DurationUnit.DAYS);
   }

   public static final long getDays(long var0) {
      return toDuration(var0, DurationUnit.DAYS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.days' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getDays$annotations(double var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.days' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getDays$annotations(int var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.days' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getDays$annotations(long var0) {
   }

   public static final long getHours(double var0) {
      return toDuration(var0, DurationUnit.HOURS);
   }

   public static final long getHours(int var0) {
      return toDuration(var0, DurationUnit.HOURS);
   }

   public static final long getHours(long var0) {
      return toDuration(var0, DurationUnit.HOURS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.hours' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getHours$annotations(double var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.hours' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getHours$annotations(int var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.hours' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getHours$annotations(long var0) {
   }

   public static final long getMicroseconds(double var0) {
      return toDuration(var0, DurationUnit.MICROSECONDS);
   }

   public static final long getMicroseconds(int var0) {
      return toDuration(var0, DurationUnit.MICROSECONDS);
   }

   public static final long getMicroseconds(long var0) {
      return toDuration(var0, DurationUnit.MICROSECONDS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.microseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getMicroseconds$annotations(double var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.microseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getMicroseconds$annotations(int var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.microseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getMicroseconds$annotations(long var0) {
   }

   public static final long getMilliseconds(double var0) {
      return toDuration(var0, DurationUnit.MILLISECONDS);
   }

   public static final long getMilliseconds(int var0) {
      return toDuration(var0, DurationUnit.MILLISECONDS);
   }

   public static final long getMilliseconds(long var0) {
      return toDuration(var0, DurationUnit.MILLISECONDS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getMilliseconds$annotations(double var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getMilliseconds$annotations(int var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getMilliseconds$annotations(long var0) {
   }

   public static final long getMinutes(double var0) {
      return toDuration(var0, DurationUnit.MINUTES);
   }

   public static final long getMinutes(int var0) {
      return toDuration(var0, DurationUnit.MINUTES);
   }

   public static final long getMinutes(long var0) {
      return toDuration(var0, DurationUnit.MINUTES);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.minutes' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getMinutes$annotations(double var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.minutes' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getMinutes$annotations(int var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.minutes' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getMinutes$annotations(long var0) {
   }

   public static final long getNanoseconds(double var0) {
      return toDuration(var0, DurationUnit.NANOSECONDS);
   }

   public static final long getNanoseconds(int var0) {
      return toDuration(var0, DurationUnit.NANOSECONDS);
   }

   public static final long getNanoseconds(long var0) {
      return toDuration(var0, DurationUnit.NANOSECONDS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getNanoseconds$annotations(double var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getNanoseconds$annotations(int var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getNanoseconds$annotations(long var0) {
   }

   public static final long getSeconds(double var0) {
      return toDuration(var0, DurationUnit.SECONDS);
   }

   public static final long getSeconds(int var0) {
      return toDuration(var0, DurationUnit.SECONDS);
   }

   public static final long getSeconds(long var0) {
      return toDuration(var0, DurationUnit.SECONDS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.seconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getSeconds$annotations(double var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.seconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getSeconds$annotations(int var0) {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.seconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static void getSeconds$annotations(long var0) {
   }

   private static final long millisToNanos(long var0) {
      return var0 * (long)1000000;
   }

   private static final long nanosToMillis(long var0) {
      return var0 / (long)1000000;
   }

   private static final long parseDuration(String var0, boolean var1) {
      int var7 = var0.length();
      if (var7 == 0) {
         throw new IllegalArgumentException("The string is empty");
      } else {
         long var11 = Duration.Companion.getZERO_UwyO8pc();
         int var5 = var0.charAt(0);
         int var6;
         if (var5 != 43 && var5 != 45) {
            var6 = 0;
         } else {
            var6 = 1;
         }

         boolean var8;
         if (var6 > 0) {
            var8 = true;
         } else {
            var8 = false;
         }

         boolean var20;
         if (var8 && StringsKt.startsWith$default((CharSequence)var0, '-', false, 2, (Object)null)) {
            var20 = true;
         } else {
            var20 = false;
         }

         if (var7 <= var6) {
            throw new IllegalArgumentException("No components");
         } else {
            char var9 = var0.charAt(var6);
            char var3 = '9';
            char var2 = '0';
            long var13;
            String var17;
            int var22;
            boolean var23;
            String var24;
            DurationUnit var25;
            if (var9 != 'P') {
               var23 = var20;
               if (var1) {
                  throw new IllegalArgumentException();
               }

               var5 = Math.max(var7 - var6, 8);
               var24 = "Unexpected order of duration components";
               if (StringsKt.regionMatches(var0, var6, "Infinity", 0, var5, true)) {
                  var11 = Duration.Companion.getINFINITE_UwyO8pc();
                  var8 = var20;
               } else {
                  boolean var10 = var8 ^ true;
                  boolean var21;
                  if (var8 && var0.charAt(var6) == '(' && StringsKt.last((CharSequence)var0) == ')') {
                     var5 = var6 + 1;
                     var6 = var7 - 1;
                     if (var5 == var6) {
                        throw new IllegalArgumentException("No components");
                     }

                     var21 = true;
                  } else {
                     var5 = var6;
                     var6 = var7;
                     var21 = var10;
                  }

                  var25 = null;
                  var10 = false;
                  var13 = var11;

                  while(true) {
                     var11 = var13;
                     var8 = var23;
                     if (var5 >= var6) {
                        break;
                     }

                     var22 = var5;
                     if (var10) {
                        var22 = var5;
                        if (var21) {
                           while(true) {
                              var22 = var5;
                              if (var5 >= var0.length()) {
                                 break;
                              }

                              if (var0.charAt(var5) == ' ') {
                                 var10 = true;
                              } else {
                                 var10 = false;
                              }

                              var22 = var5;
                              if (!var10) {
                                 break;
                              }

                              ++var5;
                           }
                        }
                     }

                     for(var5 = var22; var5 < var0.length(); ++var5) {
                        var2 = var0.charAt(var5);
                        if (!(new CharRange('0', '9')).contains(var2) && var2 != '.') {
                           var10 = false;
                        } else {
                           var10 = true;
                        }

                        if (!var10) {
                           break;
                        }
                     }

                     Intrinsics.checkNotNull(var0, "null cannot be cast to non-null type java.lang.String");
                     String var28 = var0.substring(var22, var5);
                     Intrinsics.checkNotNullExpressionValue(var28, "this as java.lang.String…ing(startIndex, endIndex)");
                     CharSequence var19 = (CharSequence)var28;
                     if (var19.length() == 0) {
                        var20 = true;
                     } else {
                        var20 = false;
                     }

                     if (var20) {
                        throw new IllegalArgumentException();
                     }

                     var22 += var28.length();

                     for(var5 = var22; var5 < var0.length(); ++var5) {
                        var2 = var0.charAt(var5);
                        if (!(new CharRange('a', 'z')).contains(var2)) {
                           break;
                        }
                     }

                     Intrinsics.checkNotNull(var0, "null cannot be cast to non-null type java.lang.String");
                     var17 = var0.substring(var22, var5);
                     Intrinsics.checkNotNullExpressionValue(var17, "this as java.lang.String…ing(startIndex, endIndex)");
                     var5 = var22 + var17.length();
                     DurationUnit var27 = DurationUnitKt.durationUnitByShortName(var17);
                     if (var25 != null && var25.compareTo((Enum)var27) <= 0) {
                        throw new IllegalArgumentException(var24);
                     }

                     var22 = StringsKt.indexOf$default(var19, '.', 0, false, 6, (Object)null);
                     if (var22 > 0) {
                        Intrinsics.checkNotNull(var28, "null cannot be cast to non-null type java.lang.String");
                        String var26 = var28.substring(0, var22);
                        Intrinsics.checkNotNullExpressionValue(var26, "this as java.lang.String…ing(startIndex, endIndex)");
                        var11 = Duration.plus_LRDsOJo(var13, toDuration(Long.parseLong(var26), var27));
                        Intrinsics.checkNotNull(var28, "null cannot be cast to non-null type java.lang.String");
                        var26 = var28.substring(var22);
                        Intrinsics.checkNotNullExpressionValue(var26, "this as java.lang.String).substring(startIndex)");
                        var13 = Duration.plus_LRDsOJo(var11, toDuration(Double.parseDouble(var26), var27));
                        if (var5 < var6) {
                           throw new IllegalArgumentException("Fractional component must be last");
                        }
                     } else {
                        var13 = Duration.plus_LRDsOJo(var13, toDuration(Long.parseLong(var28), var27));
                     }

                     var25 = var27;
                     var10 = true;
                  }
               }
            } else {
               ++var6;
               if (var6 == var7) {
                  throw new IllegalArgumentException();
               }

               var1 = false;
               DurationUnit var15 = null;

               while(true) {
                  if (var6 >= var7) {
                     var8 = var20;
                     break;
                  }

                  if (var0.charAt(var6) == 'T') {
                     if (var1) {
                        throw new IllegalArgumentException();
                     }

                     ++var6;
                     if (var6 == var7) {
                        throw new IllegalArgumentException();
                     }

                     var1 = true;
                  } else {
                     for(var22 = var6; var22 < var0.length(); var3 = '9') {
                        char var4 = var0.charAt(var22);
                        if (!(new CharRange(var2, var3)).contains(var4) && !StringsKt.contains$default((CharSequence)"+-.", var4, false, 2, (Object)null)) {
                           var23 = false;
                        } else {
                           var23 = true;
                        }

                        if (!var23) {
                           break;
                        }

                        ++var22;
                        var2 = '0';
                     }

                     Intrinsics.checkNotNull(var0, "null cannot be cast to non-null type java.lang.String");
                     var17 = var0.substring(var6, var22);
                     Intrinsics.checkNotNullExpressionValue(var17, "this as java.lang.String…ing(startIndex, endIndex)");
                     CharSequence var18 = (CharSequence)var17;
                     if (var18.length() == 0) {
                        var8 = true;
                     } else {
                        var8 = false;
                     }

                     if (var8) {
                        throw new IllegalArgumentException();
                     }

                     var6 += var17.length();
                     CharSequence var16 = (CharSequence)var0;
                     if (var6 < 0 || var6 > StringsKt.getLastIndex(var16)) {
                        throw new IllegalArgumentException("Missing unit for value " + var17);
                     }

                     var2 = var16.charAt(var6);
                     ++var6;
                     var25 = DurationUnitKt.durationUnitByIsoChar(var2, var1);
                     if (var15 != null && var15.compareTo((Enum)var25) <= 0) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                     }

                     var22 = StringsKt.indexOf$default(var18, '.', 0, false, 6, (Object)null);
                     if (var25 == DurationUnit.SECONDS && var22 > 0) {
                        Intrinsics.checkNotNull(var17, "null cannot be cast to non-null type java.lang.String");
                        var24 = var17.substring(0, var22);
                        Intrinsics.checkNotNullExpressionValue(var24, "this as java.lang.String…ing(startIndex, endIndex)");
                        var11 = Duration.plus_LRDsOJo(var11, toDuration(parseOverLongIsoComponent(var24), var25));
                        Intrinsics.checkNotNull(var17, "null cannot be cast to non-null type java.lang.String");
                        var24 = var17.substring(var22);
                        Intrinsics.checkNotNullExpressionValue(var24, "this as java.lang.String).substring(startIndex)");
                        var11 = Duration.plus_LRDsOJo(var11, toDuration(Double.parseDouble(var24), var25));
                     } else {
                        var11 = Duration.plus_LRDsOJo(var11, toDuration(parseOverLongIsoComponent(var17), var25));
                     }

                     var15 = var25;
                     var2 = '0';
                     var3 = '9';
                  }
               }
            }

            var13 = var11;
            if (var8) {
               var13 = Duration.unaryMinus_UwyO8pc(var11);
            }

            return var13;
         }
      }
   }

   private static final long parseOverLongIsoComponent(String var0) {
      int var2 = var0.length();
      int var1;
      if (var2 > 0 && StringsKt.contains$default((CharSequence)"+-", var0.charAt(0), false, 2, (Object)null)) {
         var1 = 1;
      } else {
         var1 = 0;
      }

      if (var2 - var1 > 16) {
         boolean var6;
         label52: {
            Iterable var5 = (Iterable)(new IntRange(var1, StringsKt.getLastIndex((CharSequence)var0)));
            if (!(var5 instanceof Collection) || !((Collection)var5).isEmpty()) {
               Iterator var7 = var5.iterator();

               while(var7.hasNext()) {
                  var1 = ((IntIterator)var7).nextInt();
                  if (!(new CharRange('0', '9')).contains(var0.charAt(var1))) {
                     var6 = false;
                     break label52;
                  }
               }
            }

            var6 = true;
         }

         if (var6) {
            long var3;
            if (var0.charAt(0) == '-') {
               var3 = Long.MIN_VALUE;
            } else {
               var3 = Long.MAX_VALUE;
            }

            return var3;
         }
      }

      String var8 = var0;
      if (StringsKt.startsWith$default(var0, "+", false, 2, (Object)null)) {
         var8 = StringsKt.drop(var0, 1);
      }

      return Long.parseLong(var8);
   }

   private static final int skipWhile(String var0, int var1, Function1 var2) {
      while(var1 < var0.length() && (Boolean)var2.invoke(var0.charAt(var1))) {
         ++var1;
      }

      return var1;
   }

   private static final String substringWhile(String var0, int var1, Function1 var2) {
      int var3;
      for(var3 = var1; var3 < var0.length() && (Boolean)var2.invoke(var0.charAt(var3)); ++var3) {
      }

      Intrinsics.checkNotNull(var0, "null cannot be cast to non-null type java.lang.String");
      var0 = var0.substring(var1, var3);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String…ing(startIndex, endIndex)");
      return var0;
   }

   private static final long times_kIfJnKk(double var0, long var2) {
      return Duration.times_UwyO8pc(var2, var0);
   }

   private static final long times_mvk6XK0(int var0, long var1) {
      return Duration.times_UwyO8pc(var1, var0);
   }

   public static final long toDuration(double var0, DurationUnit var2) {
      Intrinsics.checkNotNullParameter(var2, "unit");
      double var3 = DurationUnitKt.convertDurationUnit(var0, var2, DurationUnit.NANOSECONDS);
      if (Double.isNaN(var3) ^ true) {
         long var5 = MathKt.roundToLong(var3);
         if ((new LongRange(-4611686018426999999L, 4611686018426999999L)).contains(var5)) {
            var5 = durationOfNanos(var5);
         } else {
            var5 = durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt.convertDurationUnit(var0, var2, DurationUnit.MILLISECONDS)));
         }

         return var5;
      } else {
         throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
      }
   }

   public static final long toDuration(int var0, DurationUnit var1) {
      Intrinsics.checkNotNullParameter(var1, "unit");
      long var2;
      if (var1.compareTo((Enum)DurationUnit.SECONDS) <= 0) {
         var2 = durationOfNanos(DurationUnitKt.convertDurationUnitOverflow((long)var0, var1, DurationUnit.NANOSECONDS));
      } else {
         var2 = toDuration((long)var0, var1);
      }

      return var2;
   }

   public static final long toDuration(long var0, DurationUnit var2) {
      Intrinsics.checkNotNullParameter(var2, "unit");
      long var3 = DurationUnitKt.convertDurationUnitOverflow(4611686018426999999L, DurationUnit.NANOSECONDS, var2);
      return (new LongRange(-var3, var3)).contains(var0) ? durationOfNanos(DurationUnitKt.convertDurationUnitOverflow(var0, var2, DurationUnit.NANOSECONDS)) : durationOfMillis(RangesKt.coerceIn(DurationUnitKt.convertDurationUnit(var0, var2, DurationUnit.MILLISECONDS), -4611686018427387903L, 4611686018427387903L));
   }
}
