package kotlin.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000(\n\u0000\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ah\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00062\u001e\u0010\u0007\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0004\u0012\u0002H\u00040\bH\u0087\bø\u0001\u0000\u001ai\u0010\t\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000622\u0010\n\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00010\u000bj\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001`\fH\u0087\b\u001ah\u0010\r\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00062\u001e\u0010\u0007\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0004\u0012\u0002H\u00040\bH\u0087\bø\u0001\u0000\u001ah\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000622\u0010\n\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00010\u000bj\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001`\fH\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000f"},
   d2 = {"maxBy", "", "K", "V", "R", "", "", "selector", "Lkotlin/Function1;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "minBy", "minWith", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/MapsKt"
)
class MapsKt___MapsJvmKt extends MapsKt__MapsKt {
   public MapsKt___MapsJvmKt() {
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
   private static final Map.Entry maxBy(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var7 = ((Iterable)var0.entrySet()).iterator();
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
               if (var3.compareTo(var5) < 0) {
                  var8 = var6;
                  var2 = var5;
               }

               var4 = var8;
               var3 = var2;
            } while(var7.hasNext());
         }
      }

      return (Map.Entry)var8;
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
   private static final Map.Entry maxWith(Map var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return (Map.Entry)CollectionsKt.maxWithOrNull((Iterable)var0.entrySet(), var1);
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
   public static final Map.Entry minBy(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      Iterator var7 = ((Iterable)var0.entrySet()).iterator();
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

      return (Map.Entry)var8;
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
   public static final Map.Entry minWith(Map var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return (Map.Entry)CollectionsKt.minWithOrNull((Iterable)var0.entrySet(), var1);
   }
}
