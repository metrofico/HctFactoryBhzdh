package kotlin.time;

import kotlin.Metadata;

@Metadata(
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u0006H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0004H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0015"},
   d2 = {"Lkotlin/time/MonotonicTimeSource;", "Lkotlin/time/TimeSource;", "()V", "zero", "", "adjustReading", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "timeMark", "duration", "Lkotlin/time/Duration;", "adjustReading-6QKq23U", "(JJ)J", "elapsedFrom", "elapsedFrom-6eNON_k", "(J)J", "markNow", "markNow-z9LOYto", "()J", "read", "toString", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MonotonicTimeSource implements TimeSource {
   public static final MonotonicTimeSource INSTANCE = new MonotonicTimeSource();
   private static final long zero = System.nanoTime();

   private MonotonicTimeSource() {
   }

   private final long read() {
      return System.nanoTime() - zero;
   }

   public final long adjustReading_6QKq23U(long var1, long var3) {
      return Monotonic.ValueTimeMark.constructor_impl(LongSaturatedMathKt.saturatingAdd_pTJri5U(var1, var3));
   }

   public final long elapsedFrom_6eNON_k(long var1) {
      return LongSaturatedMathKt.saturatingDiff(this.read(), var1);
   }

   public long markNow_z9LOYto() {
      return Monotonic.ValueTimeMark.constructor_impl(this.read());
   }

   public String toString() {
      return "TimeSource(System.nanoTime())";
   }
}
