package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u001a3\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\n\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u000b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\f\u001a7\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a7\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\u000b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\r"},
   d2 = {"measureTime", "Lkotlin/time/Duration;", "block", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)J", "measureTimedValue", "Lkotlin/time/TimedValue;", "T", "Lkotlin/time/TimeSource;", "(Lkotlin/time/TimeSource;Lkotlin/jvm/functions/Function0;)J", "Lkotlin/time/TimeSource$Monotonic;", "(Lkotlin/time/TimeSource$Monotonic;Lkotlin/jvm/functions/Function0;)J", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class MeasureTimeKt {
   public static final long measureTime(Function0 var0) {
      Intrinsics.checkNotNullParameter(var0, "block");
      long var1 = TimeSource.Monotonic.INSTANCE.markNow_z9LOYto();
      var0.invoke();
      return TimeSource.Monotonic.ValueTimeMark.elapsedNow_UwyO8pc(var1);
   }

   public static final long measureTime(TimeSource.Monotonic var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "block");
      long var2 = var0.markNow_z9LOYto();
      var1.invoke();
      return TimeSource.Monotonic.ValueTimeMark.elapsedNow_UwyO8pc(var2);
   }

   public static final long measureTime(TimeSource var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "block");
      TimeMark var2 = var0.markNow();
      var1.invoke();
      return var2.elapsedNow_UwyO8pc();
   }

   public static final TimedValue measureTimedValue(Function0 var0) {
      Intrinsics.checkNotNullParameter(var0, "block");
      long var1 = TimeSource.Monotonic.INSTANCE.markNow_z9LOYto();
      return new TimedValue(var0.invoke(), TimeSource.Monotonic.ValueTimeMark.elapsedNow_UwyO8pc(var1), (DefaultConstructorMarker)null);
   }

   public static final TimedValue measureTimedValue(TimeSource.Monotonic var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "block");
      long var2 = var0.markNow_z9LOYto();
      return new TimedValue(var1.invoke(), TimeSource.Monotonic.ValueTimeMark.elapsedNow_UwyO8pc(var2), (DefaultConstructorMarker)null);
   }

   public static final TimedValue measureTimedValue(TimeSource var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "block");
      TimeMark var2 = var0.markNow();
      return new TimedValue(var1.invoke(), var2.elapsedNow_UwyO8pc(), (DefaultConstructorMarker)null);
   }
}
