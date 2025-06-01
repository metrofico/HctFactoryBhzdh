package kotlin.time;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001c\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0011"},
   d2 = {"durationAssertionsEnabled", "", "getDurationAssertionsEnabled", "()Z", "precisionFormats", "", "Ljava/lang/ThreadLocal;", "Ljava/text/DecimalFormat;", "[Ljava/lang/ThreadLocal;", "createFormatForDecimals", "decimals", "", "formatToExactDecimals", "", "value", "", "formatUpToDecimals", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class DurationJvmKt {
   private static final boolean durationAssertionsEnabled;
   private static final ThreadLocal[] precisionFormats;

   static {
      int var0 = 0;
      durationAssertionsEnabled = false;

      ThreadLocal[] var1;
      for(var1 = new ThreadLocal[4]; var0 < 4; ++var0) {
         var1[var0] = new ThreadLocal();
      }

      precisionFormats = var1;
   }

   private static final DecimalFormat createFormatForDecimals(int var0) {
      DecimalFormat var1 = new DecimalFormat("0");
      if (var0 > 0) {
         var1.setMinimumFractionDigits(var0);
      }

      var1.setRoundingMode(RoundingMode.HALF_UP);
      return var1;
   }

   public static final String formatToExactDecimals(double var0, int var2) {
      ThreadLocal[] var3 = precisionFormats;
      DecimalFormat var7;
      if (var2 < var3.length) {
         ThreadLocal var4 = var3[var2];
         Object var6 = var4.get();
         if (var6 == null) {
            var6 = createFormatForDecimals(var2);
            var4.set(var6);
         } else {
            Intrinsics.checkNotNullExpressionValue(var6, "get() ?: default().also(this::set)");
         }

         var7 = (DecimalFormat)var6;
      } else {
         var7 = createFormatForDecimals(var2);
      }

      String var5 = var7.format(var0);
      Intrinsics.checkNotNullExpressionValue(var5, "format.format(value)");
      return var5;
   }

   public static final String formatUpToDecimals(double var0, int var2) {
      DecimalFormat var3 = createFormatForDecimals(0);
      var3.setMaximumFractionDigits(var2);
      String var4 = var3.format(var0);
      Intrinsics.checkNotNullExpressionValue(var4, "createFormatForDecimals(… }\n        .format(value)");
      return var4;
   }

   public static final boolean getDurationAssertionsEnabled() {
      return durationAssertionsEnabled;
   }
}
