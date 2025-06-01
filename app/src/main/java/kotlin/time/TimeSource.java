package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;

@Metadata(
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0006"},
   d2 = {"Lkotlin/time/TimeSource;", "", "markNow", "Lkotlin/time/TimeMark;", "Companion", "Monotonic", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public interface TimeSource {
   Companion Companion = TimeSource.Companion.$$INSTANCE;

   TimeMark markNow();

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"},
      d2 = {"Lkotlin/time/TimeSource$Companion;", "", "()V", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      static final Companion $$INSTANCE = new Companion();

      private Companion() {
      }
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016\u0082\u0002\b\n\u0002\b!\n\u0002\b\u0019¨\u0006\n"},
      d2 = {"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource;", "()V", "markNow", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "markNow-z9LOYto", "()J", "toString", "", "ValueTimeMark", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Monotonic implements TimeSource {
      public static final Monotonic INSTANCE = new Monotonic();

      private Monotonic() {
      }

      public long markNow_z9LOYto() {
         return MonotonicTimeSource.INSTANCE.markNow_z9LOYto();
      }

      public String toString() {
         return MonotonicTimeSource.INSTANCE.toString();
      }

      @Metadata(
         d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u0018\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\bH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0006J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0014\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\bH\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\bH\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001cJ\u0010\u0010\u001f\u001a\u00020 HÖ\u0001¢\u0006\u0004\b!\u0010\"R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00060\u0003j\u0002`\u0004ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006#"},
         d2 = {"Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "Lkotlin/time/TimeMark;", "reading", "", "Lkotlin/time/ValueTimeMarkReading;", "constructor-impl", "(J)J", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "equals", "", "other", "", "equals-impl", "(JLjava/lang/Object;)Z", "hasNotPassedNow", "hasNotPassedNow-impl", "(J)Z", "hasPassedNow", "hasPassedNow-impl", "hashCode", "", "hashCode-impl", "(J)I", "minus", "duration", "minus-LRDsOJo", "(JJ)J", "plus", "plus-LRDsOJo", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "kotlin-stdlib"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      @JvmInline
      public static final class ValueTimeMark implements TimeMark {
         private final long reading;

         // $FF: synthetic method
         private ValueTimeMark(long var1) {
            this.reading = var1;
         }

         // $FF: synthetic method
         public static final ValueTimeMark box_impl(long var0) {
            return new ValueTimeMark(var0);
         }

         public static long constructor_impl(long var0) {
            return var0;
         }

         public static long elapsedNow_UwyO8pc(long var0) {
            return MonotonicTimeSource.INSTANCE.elapsedFrom_6eNON_k(var0);
         }

         public static boolean equals_impl(long var0, Object var2) {
            if (!(var2 instanceof ValueTimeMark)) {
               return false;
            } else {
               return var0 == ((ValueTimeMark)var2).unbox_impl();
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

         public static boolean hasNotPassedNow_impl(long var0) {
            return Duration.isNegative_impl(elapsedNow_UwyO8pc(var0));
         }

         public static boolean hasPassedNow_impl(long var0) {
            return Duration.isNegative_impl(elapsedNow_UwyO8pc(var0)) ^ true;
         }

         public static int hashCode_impl(long var0) {
            return (int)(var0 ^ var0 >>> 32);
         }

         public static long minus_LRDsOJo(long var0, long var2) {
            return MonotonicTimeSource.INSTANCE.adjustReading_6QKq23U(var0, Duration.unaryMinus_UwyO8pc(var2));
         }

         public static long plus_LRDsOJo(long var0, long var2) {
            return MonotonicTimeSource.INSTANCE.adjustReading_6QKq23U(var0, var2);
         }

         public static String toString_impl(long var0) {
            return "ValueTimeMark(reading=" + var0 + ')';
         }

         public long elapsedNow_UwyO8pc() {
            return elapsedNow_UwyO8pc(this.reading);
         }

         public boolean equals(Object var1) {
            return equals_impl(this.reading, var1);
         }

         public boolean hasNotPassedNow() {
            return hasNotPassedNow_impl(this.reading);
         }

         public boolean hasPassedNow() {
            return hasPassedNow_impl(this.reading);
         }

         public int hashCode() {
            return hashCode_impl(this.reading);
         }

         public long minus_LRDsOJo(long var1) {
            return minus_LRDsOJo(this.reading, var1);
         }

         public long plus_LRDsOJo(long var1) {
            return plus_LRDsOJo(this.reading, var1);
         }

         public String toString() {
            return toString_impl(this.reading);
         }

         // $FF: synthetic method
         public final long unbox_impl() {
            return this.reading;
         }
      }
   }
}
