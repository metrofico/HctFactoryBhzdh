package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\nH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\rH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000eH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000fH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u0001*\u00020\u0001H\u0087\n¨\u0006\u0011"},
   d2 = {"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/NumbersKt"
)
class NumbersKt__BigDecimalsKt {
   public NumbersKt__BigDecimalsKt() {
   }

   private static final BigDecimal dec(BigDecimal var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.subtract(BigDecimal.ONE);
      Intrinsics.checkNotNullExpressionValue(var0, "this.subtract(BigDecimal.ONE)");
      return var0;
   }

   private static final BigDecimal div(BigDecimal var0, BigDecimal var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      var0 = var0.divide(var1, RoundingMode.HALF_EVEN);
      Intrinsics.checkNotNullExpressionValue(var0, "this.divide(other, RoundingMode.HALF_EVEN)");
      return var0;
   }

   private static final BigDecimal inc(BigDecimal var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.add(BigDecimal.ONE);
      Intrinsics.checkNotNullExpressionValue(var0, "this.add(BigDecimal.ONE)");
      return var0;
   }

   private static final BigDecimal minus(BigDecimal var0, BigDecimal var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      var0 = var0.subtract(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this.subtract(other)");
      return var0;
   }

   private static final BigDecimal plus(BigDecimal var0, BigDecimal var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      var0 = var0.add(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this.add(other)");
      return var0;
   }

   private static final BigDecimal rem(BigDecimal var0, BigDecimal var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      var0 = var0.remainder(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this.remainder(other)");
      return var0;
   }

   private static final BigDecimal times(BigDecimal var0, BigDecimal var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      var0 = var0.multiply(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this.multiply(other)");
      return var0;
   }

   private static final BigDecimal toBigDecimal(double var0) {
      return new BigDecimal(String.valueOf(var0));
   }

   private static final BigDecimal toBigDecimal(double var0, MathContext var2) {
      Intrinsics.checkNotNullParameter(var2, "mathContext");
      return new BigDecimal(String.valueOf(var0), var2);
   }

   private static final BigDecimal toBigDecimal(float var0) {
      return new BigDecimal(String.valueOf(var0));
   }

   private static final BigDecimal toBigDecimal(float var0, MathContext var1) {
      Intrinsics.checkNotNullParameter(var1, "mathContext");
      return new BigDecimal(String.valueOf(var0), var1);
   }

   private static final BigDecimal toBigDecimal(int var0) {
      BigDecimal var1 = BigDecimal.valueOf((long)var0);
      Intrinsics.checkNotNullExpressionValue(var1, "valueOf(this.toLong())");
      return var1;
   }

   private static final BigDecimal toBigDecimal(int var0, MathContext var1) {
      Intrinsics.checkNotNullParameter(var1, "mathContext");
      return new BigDecimal(var0, var1);
   }

   private static final BigDecimal toBigDecimal(long var0) {
      BigDecimal var2 = BigDecimal.valueOf(var0);
      Intrinsics.checkNotNullExpressionValue(var2, "valueOf(this)");
      return var2;
   }

   private static final BigDecimal toBigDecimal(long var0, MathContext var2) {
      Intrinsics.checkNotNullParameter(var2, "mathContext");
      return new BigDecimal(var0, var2);
   }

   private static final BigDecimal unaryMinus(BigDecimal var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.negate();
      Intrinsics.checkNotNullExpressionValue(var0, "this.negate()");
      return var0;
   }
}
