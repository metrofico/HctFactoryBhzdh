package kotlin.sequences;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001aA\u0010\u0005\u001a\u0002H\u0006\"\u0010\b\u0000\u0010\u0006*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0007\"\u0004\b\u0001\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\b\u001a\u0002H\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004¢\u0006\u0002\u0010\t\u001a)\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\u000e\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f*\b\u0012\u0004\u0012\u0002H\u000b0\u0001H\u0007¢\u0006\u0002\u0010\r\u001a\u0019\u0010\n\u001a\u0004\u0018\u00010\u000e*\b\u0012\u0004\u0012\u00020\u000e0\u0001H\u0007¢\u0006\u0002\u0010\u000f\u001a\u0019\u0010\n\u001a\u0004\u0018\u00010\u0010*\b\u0012\u0004\u0012\u00020\u00100\u0001H\u0007¢\u0006\u0002\u0010\u0011\u001aG\u0010\u0012\u001a\u0004\u0018\u0001H\u000b\"\u0004\b\u0000\u0010\u000b\"\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\f*\b\u0012\u0004\u0012\u0002H\u000b0\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a;\u0010\u0016\u001a\u0004\u0018\u0001H\u000b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00012\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000b0\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u000b`\u0019H\u0007¢\u0006\u0002\u0010\u001a\u001a)\u0010\u001b\u001a\u0004\u0018\u0001H\u000b\"\u000e\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f*\b\u0012\u0004\u0012\u0002H\u000b0\u0001H\u0007¢\u0006\u0002\u0010\r\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u000e*\b\u0012\u0004\u0012\u00020\u000e0\u0001H\u0007¢\u0006\u0002\u0010\u000f\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u0010*\b\u0012\u0004\u0012\u00020\u00100\u0001H\u0007¢\u0006\u0002\u0010\u0011\u001aG\u0010\u001c\u001a\u0004\u0018\u0001H\u000b\"\u0004\b\u0000\u0010\u000b\"\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\f*\b\u0012\u0004\u0012\u0002H\u000b0\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a;\u0010\u001d\u001a\u0004\u0018\u0001H\u000b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00012\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000b0\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u000b`\u0019H\u0007¢\u0006\u0002\u0010\u001a\u001a5\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u001f0\u0014H\u0087\bø\u0001\u0000¢\u0006\u0002\b \u001a5\u0010\u001e\u001a\u00020!\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020!0\u0014H\u0087\bø\u0001\u0000¢\u0006\u0002\b\"\u001a&\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u000b0$\"\u000e\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f*\b\u0012\u0004\u0012\u0002H\u000b0\u0001\u001a8\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u000b0$\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00012\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000b0\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u000b`\u0019\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006%"},
   d2 = {"filterIsInstance", "Lkotlin/sequences/Sequence;", "R", "klass", "Ljava/lang/Class;", "filterIsInstanceTo", "C", "", "destination", "(Lkotlin/sequences/Sequence;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "max", "T", "", "(Lkotlin/sequences/Sequence;)Ljava/lang/Comparable;", "", "(Lkotlin/sequences/Sequence;)Ljava/lang/Double;", "", "(Lkotlin/sequences/Sequence;)Ljava/lang/Float;", "maxBy", "selector", "Lkotlin/Function1;", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlin/sequences/Sequence;Ljava/util/Comparator;)Ljava/lang/Object;", "min", "minBy", "minWith", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "Ljava/math/BigInteger;", "sumOfBigInteger", "toSortedSet", "Ljava/util/SortedSet;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/sequences/SequencesKt"
)
class SequencesKt___SequencesJvmKt extends SequencesKt__SequencesKt {
   public SequencesKt___SequencesJvmKt() {
   }

   public static final Sequence filterIsInstance(Sequence var0, Class var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "klass");
      var0 = SequencesKt.filter(var0, (Function1)(new Function1(var1) {
         final Class $klass;

         {
            this.$klass = var1;
         }

         public final Boolean invoke(Object var1) {
            return this.$klass.isInstance(var1);
         }
      }));
      Intrinsics.checkNotNull(var0, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesJvmKt.filterIsInstance>");
      return var0;
   }

   public static final Collection filterIsInstanceTo(Sequence var0, Collection var1, Class var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      Intrinsics.checkNotNullParameter(var2, "klass");
      Iterator var3 = var0.iterator();

      while(var3.hasNext()) {
         Object var4 = var3.next();
         if (var2.isInstance(var4)) {
            var1.add(var4);
         }
      }

      return var1;
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
   public static final Comparable max(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.maxOrNull(var0);
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
   public static final Double max(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.maxOrNull(var0);
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
   public static final Float max(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.maxOrNull(var0);
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
   public static final Object maxBy(Sequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var7 = var0.iterator();
      Object var8;
      if (!var7.hasNext()) {
         var8 = null;
      } else {
         var8 = var7.next();
         if (var7.hasNext()) {
            Comparable var2 = (Comparable)var1.invoke(var8);
            Object var4 = var8;

            do {
               Object var6 = var7.next();
               Comparable var5 = (Comparable)var1.invoke(var6);
               var8 = var4;
               Comparable var3 = var2;
               if (var2.compareTo(var5) < 0) {
                  var8 = var6;
                  var3 = var5;
               }

               var4 = var8;
               var2 = var3;
            } while(var7.hasNext());
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
   public static final Object maxWith(Sequence var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return SequencesKt.maxWithOrNull(var0, var1);
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
   public static final Comparable min(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.minOrNull(var0);
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
   public static final Double min(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.minOrNull(var0);
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
   public static final Float min(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.minOrNull(var0);
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
   public static final Object minBy(Sequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var7 = var0.iterator();
      Object var8;
      if (!var7.hasNext()) {
         var8 = null;
      } else {
         var8 = var7.next();
         if (var7.hasNext()) {
            Comparable var3 = (Comparable)var1.invoke(var8);
            Object var4 = var8;

            do {
               Object var6 = var7.next();
               Comparable var5 = (Comparable)var1.invoke(var6);
               var8 = var4;
               Comparable var2 = var3;
               if (var3.compareTo(var5) > 0) {
                  var8 = var6;
                  var2 = var5;
               }

               var4 = var8;
               var3 = var2;
            } while(var7.hasNext());
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
   public static final Object minWith(Sequence var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return SequencesKt.minWithOrNull(var0, var1);
   }

   private static final BigDecimal sumOfBigDecimal(Sequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigDecimal var2 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var2, "valueOf(this.toLong())");
      Iterator var3 = var0.iterator();
      BigDecimal var4 = var2;

      while(var3.hasNext()) {
         var4 = var4.add((BigDecimal)var1.invoke(var3.next()));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }

   private static final BigInteger sumOfBigInteger(Sequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigInteger var2 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var2, "valueOf(this.toLong())");
      Iterator var3 = var0.iterator();
      BigInteger var4 = var2;

      while(var3.hasNext()) {
         var4 = var4.add((BigInteger)var1.invoke(var3.next()));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }

   public static final SortedSet toSortedSet(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (SortedSet)SequencesKt.toCollection(var0, (Collection)(new TreeSet()));
   }

   public static final SortedSet toSortedSet(Sequence var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return (SortedSet)SequencesKt.toCollection(var0, (Collection)(new TreeSet(var1)));
   }
}
