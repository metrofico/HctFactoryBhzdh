package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u0015\u0010\u000b\u001a\u00020\u0004H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u0007J\u001b\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0011"},
   d2 = {"Lkotlin/time/AdjustedTimeMark;", "Lkotlin/time/TimeMark;", "mark", "adjustment", "Lkotlin/time/Duration;", "(Lkotlin/time/TimeMark;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAdjustment-UwyO8pc", "()J", "J", "getMark", "()Lkotlin/time/TimeMark;", "elapsedNow", "elapsedNow-UwyO8pc", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/TimeMark;", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class AdjustedTimeMark implements TimeMark {
   private final long adjustment;
   private final TimeMark mark;

   private AdjustedTimeMark(TimeMark var1, long var2) {
      this.mark = var1;
      this.adjustment = var2;
   }

   // $FF: synthetic method
   public AdjustedTimeMark(TimeMark var1, long var2, DefaultConstructorMarker var4) {
      this(var1, var2);
   }

   public long elapsedNow_UwyO8pc() {
      return Duration.minus_LRDsOJo(this.mark.elapsedNow_UwyO8pc(), this.adjustment);
   }

   public final long getAdjustment_UwyO8pc() {
      return this.adjustment;
   }

   public final TimeMark getMark() {
      return this.mark;
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
      return (TimeMark)(new AdjustedTimeMark(this.mark, Duration.plus_LRDsOJo(this.adjustment, var1), (DefaultConstructorMarker)null));
   }
}
