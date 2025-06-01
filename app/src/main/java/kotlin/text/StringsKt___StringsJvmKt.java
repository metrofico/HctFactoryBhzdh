package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u0000B\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0006\u001a;\u0010\u0007\u001a\u0004\u0018\u00010\u0001\"\u000e\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t*\u00020\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\b0\u000bH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\f\u001a/\u0010\r\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u001a\u0010\u000e\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010\u000fj\n\u0012\u0006\b\u0000\u0012\u00020\u0001`\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0006\u001a;\u0010\u0013\u001a\u0004\u0018\u00010\u0001\"\u000e\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t*\u00020\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\b0\u000bH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\f\u001a/\u0010\u0014\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u001a\u0010\u000e\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010\u000fj\n\u0012\u0006\b\u0000\u0012\u00020\u0001`\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a)\u0010\u0015\u001a\u00020\u0016*\u00020\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u000bH\u0087\bø\u0001\u0000¢\u0006\u0002\b\u0017\u001a)\u0010\u0015\u001a\u00020\u0018*\u00020\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00180\u000bH\u0087\bø\u0001\u0000¢\u0006\u0002\b\u0019\u001a\u0010\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u001b*\u00020\u0002\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001c"},
   d2 = {"elementAt", "", "", "index", "", "max", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "maxBy", "R", "", "selector", "Lkotlin/Function1;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "Ljava/math/BigInteger;", "sumOfBigInteger", "toSortedSet", "Ljava/util/SortedSet;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/StringsKt"
)
class StringsKt___StringsJvmKt extends StringsKt__StringsKt {
   public StringsKt___StringsJvmKt() {
   }

   private static final char elementAt(CharSequence var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.charAt(var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final Character max(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.maxOrNull(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final Character maxBy(CharSequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      boolean var4;
      if (var0.length() == 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      Character var8;
      if (var4) {
         var8 = null;
      } else {
         char var2 = var0.charAt(0);
         int var9 = StringsKt.getLastIndex(var0);
         if (var9 == 0) {
            var8 = var2;
         } else {
            Comparable var5 = (Comparable)var1.invoke(var2);
            IntIterator var7 = (new IntRange(1, var9)).iterator();

            while(var7.hasNext()) {
               char var3 = var0.charAt(var7.nextInt());
               Comparable var6 = (Comparable)var1.invoke(var3);
               if (var5.compareTo(var6) < 0) {
                  var2 = var3;
                  var5 = var6;
               }
            }

            var8 = var2;
         }
      }

      return var8;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final Character maxWith(CharSequence var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return StringsKt.maxWithOrNull(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final Character min(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.minOrNull(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final Character minBy(CharSequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      boolean var4;
      if (var0.length() == 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      Character var8;
      if (var4) {
         var8 = null;
      } else {
         char var2 = var0.charAt(0);
         int var9 = StringsKt.getLastIndex(var0);
         if (var9 == 0) {
            var8 = var2;
         } else {
            Comparable var5 = (Comparable)var1.invoke(var2);
            IntIterator var7 = (new IntRange(1, var9)).iterator();

            while(var7.hasNext()) {
               char var3 = var0.charAt(var7.nextInt());
               Comparable var6 = (Comparable)var1.invoke(var3);
               if (var5.compareTo(var6) > 0) {
                  var2 = var3;
                  var5 = var6;
               }
            }

            var8 = var2;
         }
      }

      return var8;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final Character minWith(CharSequence var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return StringsKt.minWithOrNull(var0, var1);
   }

   private static final BigDecimal sumOfBigDecimal(CharSequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigDecimal var3 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var3, "valueOf(this.toLong())");

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         var3 = var3.add((BigDecimal)var1.invoke(var0.charAt(var2)));
         Intrinsics.checkNotNullExpressionValue(var3, "this.add(other)");
      }

      return var3;
   }

   private static final BigInteger sumOfBigInteger(CharSequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigInteger var3 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var3, "valueOf(this.toLong())");

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         var3 = var3.add((BigInteger)var1.invoke(var0.charAt(var2)));
         Intrinsics.checkNotNullExpressionValue(var3, "this.add(other)");
      }

      return var3;
   }

   public static final SortedSet toSortedSet(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (SortedSet)StringsKt.toCollection(var0, (Collection)(new TreeSet()));
   }
}
