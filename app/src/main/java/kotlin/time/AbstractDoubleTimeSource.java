package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH$R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"},
   d2 = {"Lkotlin/time/AbstractDoubleTimeSource;", "Lkotlin/time/TimeSource;", "unit", "Lkotlin/time/DurationUnit;", "(Lkotlin/time/DurationUnit;)V", "getUnit", "()Lkotlin/time/DurationUnit;", "markNow", "Lkotlin/time/TimeMark;", "read", "", "DoubleTimeMark", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class AbstractDoubleTimeSource implements TimeSource {
   private final DurationUnit unit;

   public AbstractDoubleTimeSource(DurationUnit var1) {
      Intrinsics.checkNotNullParameter(var1, "unit");
      super();
      this.unit = var1;
   }

   protected final DurationUnit getUnit() {
      return this.unit;
   }

   public TimeMark markNow() {
      return (TimeMark)(new DoubleTimeMark(this.read(), this, Duration.Companion.getZERO_UwyO8pc(), (DefaultConstructorMarker)null));
   }

   protected abstract double read();

   @Metadata(
      d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0015\u0010\n\u001a\u00020\u0007H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0007H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u0007X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0011"},
      d2 = {"Lkotlin/time/AbstractDoubleTimeSource$DoubleTimeMark;", "Lkotlin/time/TimeMark;", "startedAt", "", "timeSource", "Lkotlin/time/AbstractDoubleTimeSource;", "offset", "Lkotlin/time/Duration;", "(DLkotlin/time/AbstractDoubleTimeSource;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "elapsedNow", "elapsedNow-UwyO8pc", "()J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/TimeMark;", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class DoubleTimeMark implements TimeMark {
      private final long offset;
      private final double startedAt;
      private final AbstractDoubleTimeSource timeSource;

      private DoubleTimeMark(double var1, AbstractDoubleTimeSource var3, long var4) {
         this.startedAt = var1;
         this.timeSource = var3;
         this.offset = var4;
      }

      // $FF: synthetic method
      public DoubleTimeMark(double var1, AbstractDoubleTimeSource var3, long var4, DefaultConstructorMarker var6) {
         this(var1, var3, var4);
      }

      public long elapsedNow_UwyO8pc() {
         return Duration.minus_LRDsOJo(DurationKt.toDuration(this.timeSource.read() - this.startedAt, this.timeSource.getUnit()), this.offset);
      }

      public boolean hasNotPassedNow() {
         return DefaultImpls.hasNotPassedNow(this);
      }

      public boolean hasPassedNow() {
         return DefaultImpls.hasPassedNow(this);
      }

      public TimeMark minus_LRDsOJo(long var1) {
         return DefaultImpls.minus_LRDsOJo(this, var1);
      }

      public TimeMark plus_LRDsOJo(long var1) {
         return (TimeMark)(new DoubleTimeMark(this.startedAt, this.timeSource, Duration.plus_LRDsOJo(this.offset, var1), (DefaultConstructorMarker)null));
      }
   }
}
