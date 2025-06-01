package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000Z\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\u0082\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0003H\u0087\b\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u000e\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u0003H\u0007\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0003H\u0087\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\r\u0010\u0012\u001a\u00020\u0013*\u00020\u0003H\u0087\b\u001a\u0014\u0010\u0012\u001a\u00020\u0013*\u0004\u0018\u00010\u0003H\u0087\b¢\u0006\u0002\b\u0014\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0015\u001a\u00020\u0016*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0018*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001a\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001c*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001e\u001a\r\u0010\u001f\u001a\u00020\u0010*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u001f\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010 \u001a\u00020!*\u00020\u0003H\u0087\b\u001a\u0015\u0010 \u001a\u00020!*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\"\u001a\u00020#*\u00020\u0003H\u0087\b\u001a\u0015\u0010\"\u001a\u00020#*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020!2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020#2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b¨\u0006%"},
   d2 = {"screenFloatValue", "T", "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "toBigInteger", "Ljava/math/BigInteger;", "radix", "", "toBigIntegerOrNull", "toBoolean", "", "toBooleanNullable", "toByte", "", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toLong", "", "toShort", "", "toString", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/StringsKt"
)
class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
   public StringsKt__StringNumberConversionsJVMKt() {
   }

   private static final Object screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String var0, Function1 var1) {
      Object var3 = null;
      Object var2 = var3;

      try {
         if (ScreenFloatValueRegEx.value.matches((CharSequence)var0)) {
            var2 = var1.invoke(var0);
         }
      } catch (NumberFormatException var4) {
         var2 = var3;
      }

      return var2;
   }

   private static final BigDecimal toBigDecimal(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new BigDecimal(var0);
   }

   private static final BigDecimal toBigDecimal(String var0, MathContext var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "mathContext");
      return new BigDecimal(var0, var1);
   }

   public static final BigDecimal toBigDecimalOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Object var2 = null;
      BigDecimal var1 = (BigDecimal)var2;

      try {
         if (ScreenFloatValueRegEx.value.matches((CharSequence)var0)) {
            var1 = new BigDecimal(var0);
         }
      } catch (NumberFormatException var3) {
         var1 = (BigDecimal)var2;
      }

      return var1;
   }

   public static final BigDecimal toBigDecimalOrNull(String var0, MathContext var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "mathContext");
      Object var3 = null;
      BigDecimal var2 = (BigDecimal)var3;

      try {
         if (ScreenFloatValueRegEx.value.matches((CharSequence)var0)) {
            var2 = new BigDecimal(var0, var1);
         }
      } catch (NumberFormatException var4) {
         var2 = (BigDecimal)var3;
      }

      return var2;
   }

   private static final BigInteger toBigInteger(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new BigInteger(var0);
   }

   private static final BigInteger toBigInteger(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new BigInteger(var0, CharsKt.checkRadix(var1));
   }

   public static final BigInteger toBigIntegerOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.toBigIntegerOrNull(var0, 10);
   }

   public static final BigInteger toBigIntegerOrNull(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      CharsKt.checkRadix(var1);
      int var3 = var0.length();
      if (var3 == 0) {
         return null;
      } else {
         int var2 = 0;
         if (var3 != 1) {
            if (var0.charAt(0) == '-') {
               var2 = 1;
            }

            while(var2 < var3) {
               if (CharsKt.digitOf(var0.charAt(var2), var1) < 0) {
                  return null;
               }

               ++var2;
            }
         } else if (CharsKt.digitOf(var0.charAt(0), var1) < 0) {
            return null;
         }

         return new BigInteger(var0, CharsKt.checkRadix(var1));
      }
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use Kotlin compiler 1.4 to avoid deprecation warning."
   )
   @DeprecatedSinceKotlin(
      hiddenSince = "1.4"
   )
   private static final boolean toBoolean(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Boolean.parseBoolean(var0);
   }

   private static final boolean toBooleanNullable(String var0) {
      return Boolean.parseBoolean(var0);
   }

   private static final byte toByte(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Byte.parseByte(var0);
   }

   private static final byte toByte(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Byte.parseByte(var0, CharsKt.checkRadix(var1));
   }

   private static final double toDouble(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Double.parseDouble(var0);
   }

   public static final Double toDoubleOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Object var4 = null;
      Double var3 = (Double)var4;

      double var1;
      try {
         if (!ScreenFloatValueRegEx.value.matches((CharSequence)var0)) {
            return var3;
         }

         var1 = Double.parseDouble(var0);
      } catch (NumberFormatException var5) {
         var3 = (Double)var4;
         return var3;
      }

      var3 = var1;
      return var3;
   }

   private static final float toFloat(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Float.parseFloat(var0);
   }

   public static final Float toFloatOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Object var3 = null;
      Float var2 = (Float)var3;

      float var1;
      try {
         if (!ScreenFloatValueRegEx.value.matches((CharSequence)var0)) {
            return var2;
         }

         var1 = Float.parseFloat(var0);
      } catch (NumberFormatException var4) {
         var2 = (Float)var3;
         return var2;
      }

      var2 = var1;
      return var2;
   }

   private static final int toInt(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Integer.parseInt(var0);
   }

   private static final int toInt(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Integer.parseInt(var0, CharsKt.checkRadix(var1));
   }

   private static final long toLong(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Long.parseLong(var0);
   }

   private static final long toLong(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Long.parseLong(var0, CharsKt.checkRadix(var1));
   }

   private static final short toShort(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Short.parseShort(var0);
   }

   private static final short toShort(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Short.parseShort(var0, CharsKt.checkRadix(var1));
   }

   private static final String toString(byte var0, int var1) {
      String var2 = Integer.toString(var0, CharsKt.checkRadix(CharsKt.checkRadix(var1)));
      Intrinsics.checkNotNullExpressionValue(var2, "toString(this, checkRadix(radix))");
      return var2;
   }

   private static final String toString(int var0, int var1) {
      String var2 = Integer.toString(var0, CharsKt.checkRadix(var1));
      Intrinsics.checkNotNullExpressionValue(var2, "toString(this, checkRadix(radix))");
      return var2;
   }

   private static final String toString(long var0, int var2) {
      String var3 = Long.toString(var0, CharsKt.checkRadix(var2));
      Intrinsics.checkNotNullExpressionValue(var3, "toString(this, checkRadix(radix))");
      return var3;
   }

   private static final String toString(short var0, int var1) {
      String var2 = Integer.toString(var0, CharsKt.checkRadix(CharsKt.checkRadix(var1)));
      Intrinsics.checkNotNullExpressionValue(var2, "toString(this, checkRadix(radix))");
      return var2;
   }
}
