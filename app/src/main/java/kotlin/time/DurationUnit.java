package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

@Metadata(
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"},
   d2 = {"Lkotlin/time/DurationUnit;", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(Ljava/lang/String;ILjava/util/concurrent/TimeUnit;)V", "getTimeUnit$kotlin_stdlib", "()Ljava/util/concurrent/TimeUnit;", "NANOSECONDS", "MICROSECONDS", "MILLISECONDS", "SECONDS", "MINUTES", "HOURS", "DAYS", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public enum DurationUnit {
   private static final DurationUnit[] $VALUES = $values();
   DAYS(TimeUnit.DAYS),
   HOURS(TimeUnit.HOURS),
   MICROSECONDS(TimeUnit.MICROSECONDS),
   MILLISECONDS(TimeUnit.MILLISECONDS),
   MINUTES(TimeUnit.MINUTES),
   NANOSECONDS(TimeUnit.NANOSECONDS),
   SECONDS(TimeUnit.SECONDS);

   private final TimeUnit timeUnit;

   // $FF: synthetic method
   private static final DurationUnit[] $values() {
      return new DurationUnit[]{NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS, MINUTES, HOURS, DAYS};
   }

   private DurationUnit(TimeUnit var3) {
      this.timeUnit = var3;
   }

   public final TimeUnit getTimeUnit$kotlin_stdlib() {
      return this.timeUnit;
   }
}
