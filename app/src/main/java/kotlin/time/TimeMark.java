package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0014\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0001J\u0014\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0001\u0082\u0002\b\n\u0002\b!\n\u0002\b\u0019¨\u0006\n"},
   d2 = {"Lkotlin/time/TimeMark;", "", "elapsedNow", "Lkotlin/time/Duration;", "hasNotPassedNow", "", "hasPassedNow", "minus", "duration", "plus", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public interface TimeMark {
   long elapsedNow_UwyO8pc();

   boolean hasNotPassedNow();

   boolean hasPassedNow();

   TimeMark minus_LRDsOJo(long var1);

   TimeMark plus_LRDsOJo(long var1);

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class DefaultImpls {
      public static boolean hasNotPassedNow(TimeMark var0) {
         return Duration.isNegative_impl(var0.elapsedNow_UwyO8pc());
      }

      public static boolean hasPassedNow(TimeMark var0) {
         return Duration.isNegative_impl(var0.elapsedNow_UwyO8pc()) ^ true;
      }

      public static TimeMark minus_LRDsOJo(TimeMark var0, long var1) {
         return var0.plus_LRDsOJo(Duration.unaryMinus_UwyO8pc(var1));
      }

      public static TimeMark plus_LRDsOJo(TimeMark var0, long var1) {
         return (TimeMark)(new AdjustedTimeMark(var0, var1, (DefaultConstructorMarker)null));
      }
   }
}
