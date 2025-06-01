package kotlin.time;

import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0087@\u0018\u0000 ¤\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002¤\u0001B\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010D\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\u0003H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bG\u0010HJ\u001b\u0010I\u001a\u00020\t2\u0006\u0010J\u001a\u00020\u0000H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\bK\u0010LJ\u001e\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u000fH\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010PJ\u001e\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\tH\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010QJ\u001b\u0010M\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ\u001a\u0010T\u001a\u00020U2\b\u0010J\u001a\u0004\u0018\u00010VHÖ\u0003¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020\tHÖ\u0001¢\u0006\u0004\bZ\u0010\rJ\r\u0010[\u001a\u00020U¢\u0006\u0004\b\\\u0010]J\u000f\u0010^\u001a\u00020UH\u0002¢\u0006\u0004\b_\u0010]J\u000f\u0010`\u001a\u00020UH\u0002¢\u0006\u0004\ba\u0010]J\r\u0010b\u001a\u00020U¢\u0006\u0004\bc\u0010]J\r\u0010d\u001a\u00020U¢\u0006\u0004\be\u0010]J\r\u0010f\u001a\u00020U¢\u0006\u0004\bg\u0010]J\u001b\u0010h\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bi\u0010jJ\u001b\u0010k\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bl\u0010jJ\u001e\u0010m\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u000fH\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bn\u0010PJ\u001e\u0010m\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\tH\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bn\u0010QJ\u009d\u0001\u0010o\u001a\u0002Hp\"\u0004\b\u0000\u0010p2u\u0010q\u001aq\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(u\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(v\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0rH\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bz\u0010{J\u0088\u0001\u0010o\u001a\u0002Hp\"\u0004\b\u0000\u0010p2`\u0010q\u001a\\\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(v\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0|H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bz\u0010}Js\u0010o\u001a\u0002Hp\"\u0004\b\u0000\u0010p2K\u0010q\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0~H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bz\u0010\u007fJ`\u0010o\u001a\u0002Hp\"\u0004\b\u0000\u0010p27\u0010q\u001a3\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0\u0080\u0001H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0005\bz\u0010\u0081\u0001J\u0019\u0010\u0082\u0001\u001a\u00020\u000f2\u0007\u0010\u0083\u0001\u001a\u00020=¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u0019\u0010\u0086\u0001\u001a\u00020\t2\u0007\u0010\u0083\u0001\u001a\u00020=¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J\u0011\u0010\u0089\u0001\u001a\u00030\u008a\u0001¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001J\u0019\u0010\u008d\u0001\u001a\u00020\u00032\u0007\u0010\u0083\u0001\u001a\u00020=¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J\u0011\u0010\u0090\u0001\u001a\u00020\u0003H\u0007¢\u0006\u0005\b\u0091\u0001\u0010\u0005J\u0011\u0010\u0092\u0001\u001a\u00020\u0003H\u0007¢\u0006\u0005\b\u0093\u0001\u0010\u0005J\u0013\u0010\u0094\u0001\u001a\u00030\u008a\u0001H\u0016¢\u0006\u0006\b\u0095\u0001\u0010\u008c\u0001J%\u0010\u0094\u0001\u001a\u00030\u008a\u00012\u0007\u0010\u0083\u0001\u001a\u00020=2\t\b\u0002\u0010\u0096\u0001\u001a\u00020\t¢\u0006\u0006\b\u0095\u0001\u0010\u0097\u0001J\u0018\u0010\u0098\u0001\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0005\b\u0099\u0001\u0010\u0005JK\u0010\u009a\u0001\u001a\u00030\u009b\u0001*\b0\u009c\u0001j\u0003`\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020\t2\u0007\u0010\u009f\u0001\u001a\u00020\t2\u0007\u0010 \u0001\u001a\u00020\t2\b\u0010\u0083\u0001\u001a\u00030\u008a\u00012\u0007\u0010¡\u0001\u001a\u00020UH\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001R\u0017\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\u0012R\u001a\u0010\u001c\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\u0012R\u001a\u0010\u001f\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\u0012R\u001a\u0010\"\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\u0012R\u0011\u0010%\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b&\u0010\u0005R\u0011\u0010'\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b(\u0010\u0005R\u0011\u0010)\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b*\u0010\u0005R\u0011\u0010+\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b,\u0010\u0005R\u0011\u0010-\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b.\u0010\u0005R\u0011\u0010/\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b0\u0010\u0005R\u0011\u00101\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b2\u0010\u0005R\u001a\u00103\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b4\u0010\u000b\u001a\u0004\b5\u0010\rR\u001a\u00106\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b7\u0010\u000b\u001a\u0004\b8\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b:\u0010\u000b\u001a\u0004\b;\u0010\rR\u0014\u0010<\u001a\u00020=8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0015\u0010@\u001a\u00020\t8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bA\u0010\rR\u0014\u0010B\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\u0005\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u009920\u0001¨\u0006¥\u0001"},
   d2 = {"Lkotlin/time/Duration;", "", "rawValue", "", "constructor-impl", "(J)J", "absoluteValue", "getAbsoluteValue-UwyO8pc", "hoursComponent", "", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "(J)I", "inDays", "", "getInDays$annotations", "getInDays-impl", "(J)D", "inHours", "getInHours$annotations", "getInHours-impl", "inMicroseconds", "getInMicroseconds$annotations", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds$annotations", "getInMilliseconds-impl", "inMinutes", "getInMinutes$annotations", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds$annotations", "getInNanoseconds-impl", "inSeconds", "getInSeconds$annotations", "getInSeconds-impl", "inWholeDays", "getInWholeDays-impl", "inWholeHours", "getInWholeHours-impl", "inWholeMicroseconds", "getInWholeMicroseconds-impl", "inWholeMilliseconds", "getInWholeMilliseconds-impl", "inWholeMinutes", "getInWholeMinutes-impl", "inWholeNanoseconds", "getInWholeNanoseconds-impl", "inWholeSeconds", "getInWholeSeconds-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "storageUnit", "Lkotlin/time/DurationUnit;", "getStorageUnit-impl", "(J)Lkotlin/time/DurationUnit;", "unitDiscriminator", "getUnitDiscriminator-impl", "value", "getValue-impl", "addValuesMixedRanges", "thisMillis", "otherNanos", "addValuesMixedRanges-UwyO8pc", "(JJJ)J", "compareTo", "other", "compareTo-LRDsOJo", "(JJ)I", "div", "scale", "div-UwyO8pc", "(JD)J", "(JI)J", "div-LRDsOJo", "(JJ)D", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "isFinite", "isFinite-impl", "(J)Z", "isInMillis", "isInMillis-impl", "isInNanos", "isInNanos-impl", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "(JJ)J", "plus", "plus-LRDsOJo", "times", "times-UwyO8pc", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(JLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(JLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(JLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "toDouble-impl", "(JLkotlin/time/DurationUnit;)D", "toInt", "toInt-impl", "(JLkotlin/time/DurationUnit;)I", "toIsoString", "", "toIsoString-impl", "(J)Ljava/lang/String;", "toLong", "toLong-impl", "(JLkotlin/time/DurationUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(JLkotlin/time/DurationUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-UwyO8pc", "appendFractional", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "whole", "fractional", "fractionalSize", "isoZeroes", "appendFractional-impl", "(JLjava/lang/StringBuilder;IIILjava/lang/String;Z)V", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
@JvmInline
public final class Duration implements Comparable {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final long INFINITE = DurationKt.access$durationOfMillis(4611686018427387903L);
   private static final long NEG_INFINITE = DurationKt.access$durationOfMillis(-4611686018427387903L);
   private static final long ZERO = constructor_impl(0L);
   private final long rawValue;

   // $FF: synthetic method
   private Duration(long var1) {
      this.rawValue = var1;
   }

   private static final long addValuesMixedRanges_UwyO8pc(long var0, long var2, long var4) {
      var0 = DurationKt.access$nanosToMillis(var4);
      var2 += var0;
      if ((new LongRange(-4611686018426L, 4611686018426L)).contains(var2)) {
         var0 = DurationKt.access$millisToNanos(var0);
         var0 = DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(var2) + (var4 - var0));
      } else {
         var0 = DurationKt.access$durationOfMillis(RangesKt.coerceIn(var2, -4611686018427387903L, 4611686018427387903L));
      }

      return var0;
   }

   private static final void appendFractional_impl(long var0, StringBuilder var2, int var3, int var4, int var5, String var6, boolean var7) {
      var2.append(var3);
      if (var4 != 0) {
         var2.append('.');
         CharSequence var9 = (CharSequence)StringsKt.padStart(String.valueOf(var4), var5, '0');
         var3 = var9.length();
         byte var11 = -1;
         var4 = var3 - 1;
         var3 = var11;
         if (var4 >= 0) {
            var3 = var4;

            while(true) {
               int var8 = var3 - 1;
               boolean var10;
               if (var9.charAt(var3) != '0') {
                  var10 = true;
               } else {
                  var10 = false;
               }

               if (var10) {
                  break;
               }

               if (var8 < 0) {
                  var3 = var11;
                  break;
               }

               var3 = var8;
            }
         }

         ++var3;
         if (!var7 && var3 < 3) {
            Intrinsics.checkNotNullExpressionValue(var2.append(var9, 0, var3), "this.append(value, startIndex, endIndex)");
         } else {
            Intrinsics.checkNotNullExpressionValue(var2.append(var9, 0, (var3 + 2) / 3 * 3), "this.append(value, startIndex, endIndex)");
         }
      }

      var2.append(var6);
   }

   // $FF: synthetic method
   public static final Duration box_impl(long var0) {
      return new Duration(var0);
   }

   public static int compareTo_LRDsOJo(long var0, long var2) {
      long var6 = var0 ^ var2;
      if (var6 >= 0L && ((int)var6 & 1) != 0) {
         int var5 = ((int)var0 & 1) - ((int)var2 & 1);
         int var4 = var5;
         if (isNegative_impl(var0)) {
            var4 = -var5;
         }

         return var4;
      } else {
         return Intrinsics.compare(var0, var2);
      }
   }

   public static long constructor_impl(long var0) {
      if (DurationJvmKt.getDurationAssertionsEnabled()) {
         if (isInNanos_impl(var0)) {
            if (!(new LongRange(-4611686018426999999L, 4611686018426999999L)).contains(getValue_impl(var0))) {
               throw new AssertionError(getValue_impl(var0) + " ns is out of nanoseconds range");
            }
         } else {
            if (!(new LongRange(-4611686018427387903L, 4611686018427387903L)).contains(getValue_impl(var0))) {
               throw new AssertionError(getValue_impl(var0) + " ms is out of milliseconds range");
            }

            if ((new LongRange(-4611686018426L, 4611686018426L)).contains(getValue_impl(var0))) {
               throw new AssertionError(getValue_impl(var0) + " ms is denormalized");
            }
         }
      }

      return var0;
   }

   public static final double div_LRDsOJo(long var0, long var2) {
      DurationUnit var4 = (DurationUnit)ComparisonsKt.maxOf((Comparable)getStorageUnit_impl(var0), (Comparable)getStorageUnit_impl(var2));
      return toDouble_impl(var0, var4) / toDouble_impl(var2, var4);
   }

   public static final long div_UwyO8pc(long var0, double var2) {
      int var5 = MathKt.roundToInt(var2);
      boolean var4;
      if ((double)var5 == var2) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (var4 && var5 != 0) {
         return div_UwyO8pc(var0, var5);
      } else {
         DurationUnit var6 = getStorageUnit_impl(var0);
         return DurationKt.toDuration(toDouble_impl(var0, var6) / var2, var6);
      }
   }

   public static final long div_UwyO8pc(long var0, int var2) {
      if (var2 == 0) {
         if (isPositive_impl(var0)) {
            var0 = INFINITE;
         } else {
            if (!isNegative_impl(var0)) {
               throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
            }

            var0 = NEG_INFINITE;
         }

         return var0;
      } else if (isInNanos_impl(var0)) {
         return DurationKt.access$durationOfNanos(getValue_impl(var0) / (long)var2);
      } else if (isInfinite_impl(var0)) {
         return times_UwyO8pc(var0, MathKt.getSign(var2));
      } else {
         long var3 = getValue_impl(var0);
         long var5 = (long)var2;
         var3 /= var5;
         if ((new LongRange(-4611686018426L, 4611686018426L)).contains(var3)) {
            var0 = DurationKt.access$millisToNanos(getValue_impl(var0) - var3 * var5) / var5;
            return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(var3) + var0);
         } else {
            return DurationKt.access$durationOfMillis(var3);
         }
      }
   }

   public static boolean equals_impl(long var0, Object var2) {
      if (!(var2 instanceof Duration)) {
         return false;
      } else {
         return var0 == ((Duration)var2).unbox_impl();
      }
   }

   public static final boolean equals_impl0(long var0, long var2) {
      boolean var4;
      if (var0 == var2) {
         var4 = true;
      } else {
         var4 = false;
      }

      return var4;
   }

   public static final long getAbsoluteValue_UwyO8pc(long var0) {
      long var2 = var0;
      if (isNegative_impl(var0)) {
         var2 = unaryMinus_UwyO8pc(var0);
      }

      return var2;
   }

   // $FF: synthetic method
   public static void getHoursComponent$annotations() {
   }

   public static final int getHoursComponent_impl(long var0) {
      int var2;
      if (isInfinite_impl(var0)) {
         var2 = 0;
      } else {
         var2 = (int)(getInWholeHours_impl(var0) % (long)24);
      }

      return var2;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeDays property instead or convert toDouble(DAYS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.DAYS)",
   imports = {}
)
   )
   public static void getInDays$annotations() {
   }

   public static final double getInDays_impl(long var0) {
      return toDouble_impl(var0, DurationUnit.DAYS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeHours property instead or convert toDouble(HOURS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.HOURS)",
   imports = {}
)
   )
   public static void getInHours$annotations() {
   }

   public static final double getInHours_impl(long var0) {
      return toDouble_impl(var0, DurationUnit.HOURS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeMicroseconds property instead or convert toDouble(MICROSECONDS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.MICROSECONDS)",
   imports = {}
)
   )
   public static void getInMicroseconds$annotations() {
   }

   public static final double getInMicroseconds_impl(long var0) {
      return toDouble_impl(var0, DurationUnit.MICROSECONDS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeMilliseconds property instead or convert toDouble(MILLISECONDS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.MILLISECONDS)",
   imports = {}
)
   )
   public static void getInMilliseconds$annotations() {
   }

   public static final double getInMilliseconds_impl(long var0) {
      return toDouble_impl(var0, DurationUnit.MILLISECONDS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeMinutes property instead or convert toDouble(MINUTES) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.MINUTES)",
   imports = {}
)
   )
   public static void getInMinutes$annotations() {
   }

   public static final double getInMinutes_impl(long var0) {
      return toDouble_impl(var0, DurationUnit.MINUTES);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeNanoseconds property instead or convert toDouble(NANOSECONDS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.NANOSECONDS)",
   imports = {}
)
   )
   public static void getInNanoseconds$annotations() {
   }

   public static final double getInNanoseconds_impl(long var0) {
      return toDouble_impl(var0, DurationUnit.NANOSECONDS);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeSeconds property instead or convert toDouble(SECONDS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.SECONDS)",
   imports = {}
)
   )
   public static void getInSeconds$annotations() {
   }

   public static final double getInSeconds_impl(long var0) {
      return toDouble_impl(var0, DurationUnit.SECONDS);
   }

   public static final long getInWholeDays_impl(long var0) {
      return toLong_impl(var0, DurationUnit.DAYS);
   }

   public static final long getInWholeHours_impl(long var0) {
      return toLong_impl(var0, DurationUnit.HOURS);
   }

   public static final long getInWholeMicroseconds_impl(long var0) {
      return toLong_impl(var0, DurationUnit.MICROSECONDS);
   }

   public static final long getInWholeMilliseconds_impl(long var0) {
      if (isInMillis_impl(var0) && isFinite_impl(var0)) {
         var0 = getValue_impl(var0);
      } else {
         var0 = toLong_impl(var0, DurationUnit.MILLISECONDS);
      }

      return var0;
   }

   public static final long getInWholeMinutes_impl(long var0) {
      return toLong_impl(var0, DurationUnit.MINUTES);
   }

   public static final long getInWholeNanoseconds_impl(long var0) {
      long var2 = getValue_impl(var0);
      if (isInNanos_impl(var0)) {
         var0 = var2;
      } else if (var2 > 9223372036854L) {
         var0 = Long.MAX_VALUE;
      } else if (var2 < -9223372036854L) {
         var0 = Long.MIN_VALUE;
      } else {
         var0 = DurationKt.access$millisToNanos(var2);
      }

      return var0;
   }

   public static final long getInWholeSeconds_impl(long var0) {
      return toLong_impl(var0, DurationUnit.SECONDS);
   }

   // $FF: synthetic method
   public static void getMinutesComponent$annotations() {
   }

   public static final int getMinutesComponent_impl(long var0) {
      int var2;
      if (isInfinite_impl(var0)) {
         var2 = 0;
      } else {
         var2 = (int)(getInWholeMinutes_impl(var0) % (long)60);
      }

      return var2;
   }

   // $FF: synthetic method
   public static void getNanosecondsComponent$annotations() {
   }

   public static final int getNanosecondsComponent_impl(long var0) {
      int var2;
      if (isInfinite_impl(var0)) {
         var2 = 0;
      } else {
         if (isInMillis_impl(var0)) {
            var0 = DurationKt.access$millisToNanos(getValue_impl(var0) % (long)1000);
         } else {
            var0 = getValue_impl(var0) % (long)1000000000;
         }

         var2 = (int)var0;
      }

      return var2;
   }

   // $FF: synthetic method
   public static void getSecondsComponent$annotations() {
   }

   public static final int getSecondsComponent_impl(long var0) {
      int var2;
      if (isInfinite_impl(var0)) {
         var2 = 0;
      } else {
         var2 = (int)(getInWholeSeconds_impl(var0) % (long)60);
      }

      return var2;
   }

   private static final DurationUnit getStorageUnit_impl(long var0) {
      DurationUnit var2;
      if (isInNanos_impl(var0)) {
         var2 = DurationUnit.NANOSECONDS;
      } else {
         var2 = DurationUnit.MILLISECONDS;
      }

      return var2;
   }

   private static final int getUnitDiscriminator_impl(long var0) {
      return (int)var0 & 1;
   }

   private static final long getValue_impl(long var0) {
      return var0 >> 1;
   }

   public static int hashCode_impl(long var0) {
      return (int)(var0 ^ var0 >>> 32);
   }

   public static final boolean isFinite_impl(long var0) {
      return isInfinite_impl(var0) ^ true;
   }

   private static final boolean isInMillis_impl(long var0) {
      int var2 = (int)var0;
      boolean var3 = true;
      if ((var2 & 1) != 1) {
         var3 = false;
      }

      return var3;
   }

   private static final boolean isInNanos_impl(long var0) {
      int var2 = (int)var0;
      boolean var3 = true;
      if ((var2 & 1) != 0) {
         var3 = false;
      }

      return var3;
   }

   public static final boolean isInfinite_impl(long var0) {
      boolean var2;
      if (var0 != INFINITE && var0 != NEG_INFINITE) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public static final boolean isNegative_impl(long var0) {
      boolean var2;
      if (var0 < 0L) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean isPositive_impl(long var0) {
      boolean var2;
      if (var0 > 0L) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final long minus_LRDsOJo(long var0, long var2) {
      return plus_LRDsOJo(var0, unaryMinus_UwyO8pc(var2));
   }

   public static final long plus_LRDsOJo(long var0, long var2) {
      if (isInfinite_impl(var0)) {
         if (!isFinite_impl(var2) && (var2 ^ var0) < 0L) {
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
         } else {
            return var0;
         }
      } else if (isInfinite_impl(var2)) {
         return var2;
      } else {
         if (((int)var0 & 1) == ((int)var2 & 1)) {
            var2 = getValue_impl(var0) + getValue_impl(var2);
            if (isInNanos_impl(var0)) {
               var0 = DurationKt.access$durationOfNanosNormalized(var2);
            } else {
               var0 = DurationKt.access$durationOfMillisNormalized(var2);
            }
         } else if (isInMillis_impl(var0)) {
            var0 = addValuesMixedRanges_UwyO8pc(var0, getValue_impl(var0), getValue_impl(var2));
         } else {
            var0 = addValuesMixedRanges_UwyO8pc(var0, getValue_impl(var2), getValue_impl(var0));
         }

         return var0;
      }
   }

   public static final long times_UwyO8pc(long var0, double var2) {
      int var5 = MathKt.roundToInt(var2);
      boolean var4;
      if ((double)var5 == var2) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (var4) {
         return times_UwyO8pc(var0, var5);
      } else {
         DurationUnit var6 = getStorageUnit_impl(var0);
         return DurationKt.toDuration(toDouble_impl(var0, var6) * var2, var6);
      }
   }

   public static final long times_UwyO8pc(long var0, int var2) {
      if (isInfinite_impl(var0)) {
         if (var2 != 0) {
            if (var2 <= 0) {
               var0 = unaryMinus_UwyO8pc(var0);
            }

            return var0;
         } else {
            throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
         }
      } else if (var2 == 0) {
         return ZERO;
      } else {
         long var5 = getValue_impl(var0);
         long var3 = (long)var2;
         long var7 = var5 * var3;
         if (isInNanos_impl(var0)) {
            if ((new LongRange(-2147483647L, 2147483647L)).contains(var5)) {
               var0 = DurationKt.access$durationOfNanos(var7);
            } else if (var7 / var3 == var5) {
               var0 = DurationKt.access$durationOfNanosNormalized(var7);
            } else {
               var7 = DurationKt.access$nanosToMillis(var5);
               long var9 = DurationKt.access$millisToNanos(var7);
               var0 = var7 * var3;
               var9 = DurationKt.access$nanosToMillis((var5 - var9) * var3) + var0;
               if (var0 / var3 == var7 && (var9 ^ var0) >= 0L) {
                  var0 = DurationKt.access$durationOfMillis(RangesKt.coerceIn(var9, (ClosedRange)(new LongRange(-4611686018427387903L, 4611686018427387903L))));
               } else if (MathKt.getSign(var5) * MathKt.getSign(var2) > 0) {
                  var0 = INFINITE;
               } else {
                  var0 = NEG_INFINITE;
               }
            }
         } else if (var7 / var3 == var5) {
            var0 = DurationKt.access$durationOfMillis(RangesKt.coerceIn(var7, (ClosedRange)(new LongRange(-4611686018427387903L, 4611686018427387903L))));
         } else if (MathKt.getSign(var5) * MathKt.getSign(var2) > 0) {
            var0 = INFINITE;
         } else {
            var0 = NEG_INFINITE;
         }

         return var0;
      }
   }

   public static final Object toComponents_impl(long var0, Function2 var2) {
      Intrinsics.checkNotNullParameter(var2, "action");
      return var2.invoke(getInWholeSeconds_impl(var0), getNanosecondsComponent_impl(var0));
   }

   public static final Object toComponents_impl(long var0, Function3 var2) {
      Intrinsics.checkNotNullParameter(var2, "action");
      return var2.invoke(getInWholeMinutes_impl(var0), getSecondsComponent_impl(var0), getNanosecondsComponent_impl(var0));
   }

   public static final Object toComponents_impl(long var0, Function4 var2) {
      Intrinsics.checkNotNullParameter(var2, "action");
      return var2.invoke(getInWholeHours_impl(var0), getMinutesComponent_impl(var0), getSecondsComponent_impl(var0), getNanosecondsComponent_impl(var0));
   }

   public static final Object toComponents_impl(long var0, Function5 var2) {
      Intrinsics.checkNotNullParameter(var2, "action");
      return var2.invoke(getInWholeDays_impl(var0), getHoursComponent_impl(var0), getMinutesComponent_impl(var0), getSecondsComponent_impl(var0), getNanosecondsComponent_impl(var0));
   }

   public static final double toDouble_impl(long var0, DurationUnit var2) {
      Intrinsics.checkNotNullParameter(var2, "unit");
      double var3;
      if (var0 == INFINITE) {
         var3 = Double.POSITIVE_INFINITY;
      } else if (var0 == NEG_INFINITE) {
         var3 = Double.NEGATIVE_INFINITY;
      } else {
         var3 = DurationUnitKt.convertDurationUnit((double)getValue_impl(var0), getStorageUnit_impl(var0), var2);
      }

      return var3;
   }

   public static final int toInt_impl(long var0, DurationUnit var2) {
      Intrinsics.checkNotNullParameter(var2, "unit");
      return (int)RangesKt.coerceIn(toLong_impl(var0, var2), -2147483648L, 2147483647L);
   }

   public static final String toIsoString_impl(long var0) {
      StringBuilder var13 = new StringBuilder();
      if (isNegative_impl(var0)) {
         var13.append('-');
      }

      var13.append("PT");
      long var11 = getAbsoluteValue_UwyO8pc(var0);
      long var9 = getInWholeHours_impl(var11);
      int var7 = getMinutesComponent_impl(var11);
      int var6 = getSecondsComponent_impl(var11);
      int var8 = getNanosecondsComponent_impl(var11);
      if (isInfinite_impl(var0)) {
         var9 = 9999999999999L;
      }

      boolean var5 = true;
      boolean var2;
      if (var9 != 0L) {
         var2 = true;
      } else {
         var2 = false;
      }

      boolean var3;
      if (var6 == 0 && var8 == 0) {
         var3 = false;
      } else {
         var3 = true;
      }

      boolean var4 = var5;
      if (var7 == 0) {
         if (var3 && var2) {
            var4 = var5;
         } else {
            var4 = false;
         }
      }

      if (var2) {
         var13.append(var9).append('H');
      }

      if (var4) {
         var13.append(var7).append('M');
      }

      if (var3 || !var2 && !var4) {
         appendFractional_impl(var0, var13, var6, var8, 9, "S", true);
      }

      String var14 = var13.toString();
      Intrinsics.checkNotNullExpressionValue(var14, "StringBuilder().apply(builderAction).toString()");
      return var14;
   }

   public static final long toLong_impl(long var0, DurationUnit var2) {
      Intrinsics.checkNotNullParameter(var2, "unit");
      if (var0 == INFINITE) {
         var0 = Long.MAX_VALUE;
      } else if (var0 == NEG_INFINITE) {
         var0 = Long.MIN_VALUE;
      } else {
         var0 = DurationUnitKt.convertDurationUnit(getValue_impl(var0), getStorageUnit_impl(var0), var2);
      }

      return var0;
   }

   @Deprecated(
      message = "Use inWholeMilliseconds property instead.",
      replaceWith = @ReplaceWith(
   expression = "this.inWholeMilliseconds",
   imports = {}
)
   )
   public static final long toLongMilliseconds_impl(long var0) {
      return getInWholeMilliseconds_impl(var0);
   }

   @Deprecated(
      message = "Use inWholeNanoseconds property instead.",
      replaceWith = @ReplaceWith(
   expression = "this.inWholeNanoseconds",
   imports = {}
)
   )
   public static final long toLongNanoseconds_impl(long var0) {
      return getInWholeNanoseconds_impl(var0);
   }

   public static String toString_impl(long var0) {
      String var17;
      if (var0 == 0L) {
         var17 = "0s";
      } else if (var0 == INFINITE) {
         var17 = "Infinity";
      } else if (var0 == NEG_INFINITE) {
         var17 = "-Infinity";
      } else {
         boolean var12 = isNegative_impl(var0);
         StringBuilder var18 = new StringBuilder();
         if (var12) {
            var18.append('-');
         }

         long var15 = getAbsoluteValue_UwyO8pc(var0);
         long var13 = getInWholeDays_impl(var15);
         int var11 = getHoursComponent_impl(var15);
         int var10 = getMinutesComponent_impl(var15);
         int var9 = getSecondsComponent_impl(var15);
         int var8 = getNanosecondsComponent_impl(var15);
         int var3 = 0;
         boolean var4;
         if (var13 != 0L) {
            var4 = true;
         } else {
            var4 = false;
         }

         boolean var5;
         if (var11 != 0) {
            var5 = true;
         } else {
            var5 = false;
         }

         boolean var6;
         if (var10 != 0) {
            var6 = true;
         } else {
            var6 = false;
         }

         boolean var7;
         if (var9 == 0 && var8 == 0) {
            var7 = false;
         } else {
            var7 = true;
         }

         if (var4) {
            var18.append(var13).append('d');
            var3 = 1;
         }

         int var2;
         label120: {
            if (!var5) {
               var2 = var3;
               if (!var4) {
                  break label120;
               }

               if (!var6) {
                  var2 = var3;
                  if (!var7) {
                     break label120;
                  }
               }
            }

            if (var3 > 0) {
               var18.append(' ');
            }

            var18.append(var11).append('h');
            var2 = var3 + 1;
         }

         label121: {
            if (!var6) {
               var3 = var2;
               if (!var7) {
                  break label121;
               }

               if (!var5) {
                  var3 = var2;
                  if (!var4) {
                     break label121;
                  }
               }
            }

            if (var2 > 0) {
               var18.append(' ');
            }

            var18.append(var10).append('m');
            var3 = var2 + 1;
         }

         var2 = var3;
         if (var7) {
            if (var3 > 0) {
               var18.append(' ');
            }

            if (var9 == 0 && !var4 && !var5 && !var6) {
               if (var8 >= 1000000) {
                  appendFractional_impl(var0, var18, var8 / 1000000, var8 % 1000000, 6, "ms", false);
               } else if (var8 >= 1000) {
                  appendFractional_impl(var0, var18, var8 / 1000, var8 % 1000, 3, "us", false);
               } else {
                  var18.append(var8).append("ns");
               }
            } else {
               appendFractional_impl(var0, var18, var9, var8, 9, "s", false);
            }

            var2 = var3 + 1;
         }

         if (var12 && var2 > 1) {
            var18.insert(1, '(').append(')');
         }

         var17 = var18.toString();
         Intrinsics.checkNotNullExpressionValue(var17, "StringBuilder().apply(builderAction).toString()");
      }

      return var17;
   }

   public static final String toString_impl(long var0, DurationUnit var2, int var3) {
      Intrinsics.checkNotNullParameter(var2, "unit");
      boolean var6;
      if (var3 >= 0) {
         var6 = true;
      } else {
         var6 = false;
      }

      if (var6) {
         double var4 = toDouble_impl(var0, var2);
         return Double.isInfinite(var4) ? String.valueOf(var4) : DurationJvmKt.formatToExactDecimals(var4, RangesKt.coerceAtMost(var3, 12)) + DurationUnitKt.shortName(var2);
      } else {
         throw new IllegalArgumentException(("decimals must be not negative, but was " + var3).toString());
      }
   }

   // $FF: synthetic method
   public static String toString_impl$default(long var0, DurationUnit var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var3 = 0;
      }

      return toString_impl(var0, var2, var3);
   }

   public static final long unaryMinus_UwyO8pc(long var0) {
      return DurationKt.access$durationOf(-getValue_impl(var0), (int)var0 & 1);
   }

   public int compareTo_LRDsOJo(long var1) {
      return compareTo_LRDsOJo(this.rawValue, var1);
   }

   public boolean equals(Object var1) {
      return equals_impl(this.rawValue, var1);
   }

   public int hashCode() {
      return hashCode_impl(this.rawValue);
   }

   public String toString() {
      return toString_impl(this.rawValue);
   }

   // $FF: synthetic method
   public final long unbox_impl() {
      return this.rawValue;
   }

   @Metadata(
      d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0007J\u001d\u0010\f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0011J\u001d\u0010\f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0014J\u001d\u0010\f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b0\u0010\u0011J\u001d\u0010\u0018\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b0\u0010\u0014J\u001d\u0010\u0018\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b0\u0010\u0017J\u001d\u0010\u001b\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0011J\u001d\u0010\u001b\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0014J\u001d\u0010\u001b\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0017J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u0010\u0011J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u0010\u0014J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u0010\u0017J\u001d\u0010!\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u0010\u0011J\u001d\u0010!\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u0010\u0014J\u001d\u0010!\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u0010\u0017J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u0010\u0011J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u0010\u0014J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u0010\u0017J\u001b\u00105\u001a\u00020\u00042\u0006\u0010+\u001a\u000206ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00042\u0006\u0010+\u001a\u000206ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b:\u00108J\u001b\u0010;\u001a\u0004\u0018\u00010\u00042\u0006\u0010+\u001a\u000206ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b<J\u001b\u0010=\u001a\u0004\u0018\u00010\u00042\u0006\u0010+\u001a\u000206ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b>J\u001d\u0010'\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b?\u0010\u0011J\u001d\u0010'\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b?\u0010\u0014J\u001d\u0010'\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b?\u0010\u0017R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R%\u0010\f\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R%\u0010\f\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000e\u0010\u0013\u001a\u0004\b\u0010\u0010\u0014R%\u0010\f\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000e\u0010\u0016\u001a\u0004\b\u0010\u0010\u0017R%\u0010\u0018\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u001a\u0010\u0011R%\u0010\u0018\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u001a\u0010\u0014R%\u0010\u0018\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0017R%\u0010\u001b\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001d\u0010\u0011R%\u0010\u001b\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001d\u0010\u0014R%\u0010\u001b\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001c\u0010\u0016\u001a\u0004\b\u001d\u0010\u0017R%\u0010\u001e\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001f\u0010\u000f\u001a\u0004\b \u0010\u0011R%\u0010\u001e\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001f\u0010\u0013\u001a\u0004\b \u0010\u0014R%\u0010\u001e\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010\u0017R%\u0010!\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\"\u0010\u000f\u001a\u0004\b#\u0010\u0011R%\u0010!\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010\u0014R%\u0010!\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\"\u0010\u0016\u001a\u0004\b#\u0010\u0017R%\u0010$\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b%\u0010\u000f\u001a\u0004\b&\u0010\u0011R%\u0010$\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b%\u0010\u0013\u001a\u0004\b&\u0010\u0014R%\u0010$\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b%\u0010\u0016\u001a\u0004\b&\u0010\u0017R%\u0010'\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b(\u0010\u000f\u001a\u0004\b)\u0010\u0011R%\u0010'\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b(\u0010\u0013\u001a\u0004\b)\u0010\u0014R%\u0010'\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b(\u0010\u0016\u001a\u0004\b)\u0010\u0017\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006@"},
      d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE-UwyO8pc", "()J", "J", "NEG_INFINITE", "getNEG_INFINITE-UwyO8pc$kotlin_stdlib", "ZERO", "getZERO-UwyO8pc", "days", "", "getDays-UwyO8pc$annotations", "(D)V", "getDays-UwyO8pc", "(D)J", "", "(I)V", "(I)J", "", "(J)V", "(J)J", "hours", "getHours-UwyO8pc$annotations", "getHours-UwyO8pc", "microseconds", "getMicroseconds-UwyO8pc$annotations", "getMicroseconds-UwyO8pc", "milliseconds", "getMilliseconds-UwyO8pc$annotations", "getMilliseconds-UwyO8pc", "minutes", "getMinutes-UwyO8pc$annotations", "getMinutes-UwyO8pc", "nanoseconds", "getNanoseconds-UwyO8pc$annotations", "getNanoseconds-UwyO8pc", "seconds", "getSeconds-UwyO8pc$annotations", "getSeconds-UwyO8pc", "convert", "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "days-UwyO8pc", "hours-UwyO8pc", "microseconds-UwyO8pc", "milliseconds-UwyO8pc", "minutes-UwyO8pc", "nanoseconds-UwyO8pc", "parse", "", "parse-UwyO8pc", "(Ljava/lang/String;)J", "parseIsoString", "parseIsoString-UwyO8pc", "parseIsoStringOrNull", "parseIsoStringOrNull-FghU774", "parseOrNull", "parseOrNull-FghU774", "seconds-UwyO8pc", "kotlin-stdlib"},
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

      private final long getDays_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.DAYS);
      }

      private final long getDays_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.DAYS);
      }

      private final long getDays_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.DAYS);
      }

      // $FF: synthetic method
      public static void getDays_UwyO8pc$annotations(double var0) {
      }

      // $FF: synthetic method
      public static void getDays_UwyO8pc$annotations(int var0) {
      }

      // $FF: synthetic method
      public static void getDays_UwyO8pc$annotations(long var0) {
      }

      private final long getHours_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.HOURS);
      }

      private final long getHours_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.HOURS);
      }

      private final long getHours_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.HOURS);
      }

      // $FF: synthetic method
      public static void getHours_UwyO8pc$annotations(double var0) {
      }

      // $FF: synthetic method
      public static void getHours_UwyO8pc$annotations(int var0) {
      }

      // $FF: synthetic method
      public static void getHours_UwyO8pc$annotations(long var0) {
      }

      private final long getMicroseconds_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.MICROSECONDS);
      }

      private final long getMicroseconds_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.MICROSECONDS);
      }

      private final long getMicroseconds_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.MICROSECONDS);
      }

      // $FF: synthetic method
      public static void getMicroseconds_UwyO8pc$annotations(double var0) {
      }

      // $FF: synthetic method
      public static void getMicroseconds_UwyO8pc$annotations(int var0) {
      }

      // $FF: synthetic method
      public static void getMicroseconds_UwyO8pc$annotations(long var0) {
      }

      private final long getMilliseconds_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.MILLISECONDS);
      }

      private final long getMilliseconds_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.MILLISECONDS);
      }

      private final long getMilliseconds_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.MILLISECONDS);
      }

      // $FF: synthetic method
      public static void getMilliseconds_UwyO8pc$annotations(double var0) {
      }

      // $FF: synthetic method
      public static void getMilliseconds_UwyO8pc$annotations(int var0) {
      }

      // $FF: synthetic method
      public static void getMilliseconds_UwyO8pc$annotations(long var0) {
      }

      private final long getMinutes_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.MINUTES);
      }

      private final long getMinutes_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.MINUTES);
      }

      private final long getMinutes_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.MINUTES);
      }

      // $FF: synthetic method
      public static void getMinutes_UwyO8pc$annotations(double var0) {
      }

      // $FF: synthetic method
      public static void getMinutes_UwyO8pc$annotations(int var0) {
      }

      // $FF: synthetic method
      public static void getMinutes_UwyO8pc$annotations(long var0) {
      }

      private final long getNanoseconds_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.NANOSECONDS);
      }

      private final long getNanoseconds_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.NANOSECONDS);
      }

      private final long getNanoseconds_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.NANOSECONDS);
      }

      // $FF: synthetic method
      public static void getNanoseconds_UwyO8pc$annotations(double var0) {
      }

      // $FF: synthetic method
      public static void getNanoseconds_UwyO8pc$annotations(int var0) {
      }

      // $FF: synthetic method
      public static void getNanoseconds_UwyO8pc$annotations(long var0) {
      }

      private final long getSeconds_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.SECONDS);
      }

      private final long getSeconds_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.SECONDS);
      }

      private final long getSeconds_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.SECONDS);
      }

      // $FF: synthetic method
      public static void getSeconds_UwyO8pc$annotations(double var0) {
      }

      // $FF: synthetic method
      public static void getSeconds_UwyO8pc$annotations(int var0) {
      }

      // $FF: synthetic method
      public static void getSeconds_UwyO8pc$annotations(long var0) {
      }

      public final double convert(double var1, DurationUnit var3, DurationUnit var4) {
         Intrinsics.checkNotNullParameter(var3, "sourceUnit");
         Intrinsics.checkNotNullParameter(var4, "targetUnit");
         return DurationUnitKt.convertDurationUnit(var1, var3, var4);
      }

      @Deprecated(
         message = "Use 'Double.days' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long days_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.DAYS);
      }

      @Deprecated(
         message = "Use 'Int.days' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long days_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.DAYS);
      }

      @Deprecated(
         message = "Use 'Long.days' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long days_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.DAYS);
      }

      public final long getINFINITE_UwyO8pc() {
         return Duration.INFINITE;
      }

      public final long getNEG_INFINITE_UwyO8pc$kotlin_stdlib() {
         return Duration.NEG_INFINITE;
      }

      public final long getZERO_UwyO8pc() {
         return Duration.ZERO;
      }

      @Deprecated(
         message = "Use 'Double.hours' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long hours_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.HOURS);
      }

      @Deprecated(
         message = "Use 'Int.hours' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long hours_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.HOURS);
      }

      @Deprecated(
         message = "Use 'Long.hours' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long hours_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.HOURS);
      }

      @Deprecated(
         message = "Use 'Double.microseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long microseconds_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.MICROSECONDS);
      }

      @Deprecated(
         message = "Use 'Int.microseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long microseconds_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.MICROSECONDS);
      }

      @Deprecated(
         message = "Use 'Long.microseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long microseconds_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.MICROSECONDS);
      }

      @Deprecated(
         message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long milliseconds_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.MILLISECONDS);
      }

      @Deprecated(
         message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long milliseconds_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.MILLISECONDS);
      }

      @Deprecated(
         message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long milliseconds_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.MILLISECONDS);
      }

      @Deprecated(
         message = "Use 'Double.minutes' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long minutes_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.MINUTES);
      }

      @Deprecated(
         message = "Use 'Int.minutes' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long minutes_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.MINUTES);
      }

      @Deprecated(
         message = "Use 'Long.minutes' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long minutes_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.MINUTES);
      }

      @Deprecated(
         message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long nanoseconds_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.NANOSECONDS);
      }

      @Deprecated(
         message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long nanoseconds_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.NANOSECONDS);
      }

      @Deprecated(
         message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long nanoseconds_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.NANOSECONDS);
      }

      public final long parse_UwyO8pc(String var1) {
         Intrinsics.checkNotNullParameter(var1, "value");

         try {
            long var2 = DurationKt.access$parseDuration(var1, false);
            return var2;
         } catch (IllegalArgumentException var5) {
            throw new IllegalArgumentException("Invalid duration string format: '" + var1 + "'.", (Throwable)var5);
         }
      }

      public final long parseIsoString_UwyO8pc(String var1) {
         Intrinsics.checkNotNullParameter(var1, "value");

         try {
            long var2 = DurationKt.access$parseDuration(var1, true);
            return var2;
         } catch (IllegalArgumentException var5) {
            throw new IllegalArgumentException("Invalid ISO duration string format: '" + var1 + "'.", (Throwable)var5);
         }
      }

      public final Duration parseIsoStringOrNull_FghU774(String var1) {
         Intrinsics.checkNotNullParameter(var1, "value");

         Duration var4;
         try {
            var4 = Duration.box_impl(DurationKt.access$parseDuration(var1, true));
         } catch (IllegalArgumentException var3) {
            var4 = null;
            Duration var2 = (Duration)null;
         }

         return var4;
      }

      public final Duration parseOrNull_FghU774(String var1) {
         Intrinsics.checkNotNullParameter(var1, "value");

         Duration var4;
         try {
            var4 = Duration.box_impl(DurationKt.access$parseDuration(var1, false));
         } catch (IllegalArgumentException var3) {
            var4 = null;
            Duration var2 = (Duration)null;
         }

         return var4;
      }

      @Deprecated(
         message = "Use 'Double.seconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long seconds_UwyO8pc(double var1) {
         return DurationKt.toDuration(var1, DurationUnit.SECONDS);
      }

      @Deprecated(
         message = "Use 'Int.seconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long seconds_UwyO8pc(int var1) {
         return DurationKt.toDuration(var1, DurationUnit.SECONDS);
      }

      @Deprecated(
         message = "Use 'Long.seconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6"
      )
      public final long seconds_UwyO8pc(long var1) {
         return DurationKt.toDuration(var1, DurationUnit.SECONDS);
      }
   }
}
