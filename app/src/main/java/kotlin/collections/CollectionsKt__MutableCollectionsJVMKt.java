package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u001a!\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0087\b\u001a \u0010\t\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a6\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\r0\fH\u0087\bø\u0001\u0000\u001a5\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u000e\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000fj\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0010H\u0087\b\u001a2\u0010\u0011\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u000e\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000fj\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"},
   d2 = {"fill", "", "T", "", "value", "(Ljava/util/List;Ljava/lang/Object;)V", "shuffle", "random", "Ljava/util/Random;", "sort", "", "comparison", "Lkotlin/Function2;", "", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "sortWith", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/CollectionsKt"
)
class CollectionsKt__MutableCollectionsJVMKt extends CollectionsKt__IteratorsKt {
   public CollectionsKt__MutableCollectionsJVMKt() {
   }

   private static final void fill(List var0, Object var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Collections.fill(var0, var1);
   }

   private static final void shuffle(List var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Collections.shuffle(var0);
   }

   private static final void shuffle(List var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");
      Collections.shuffle(var0, var1);
   }

   public static final void sort(List var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var0.size() > 1) {
         Collections.sort(var0);
      }

   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use sortWith(comparator) instead.",
      replaceWith = @ReplaceWith(
   expression = "this.sortWith(comparator)",
   imports = {}
)
   )
   private static final void sort(List var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      throw new NotImplementedError((String)null, 1, (DefaultConstructorMarker)null);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use sortWith(Comparator(comparison)) instead.",
      replaceWith = @ReplaceWith(
   expression = "this.sortWith(Comparator(comparison))",
   imports = {}
)
   )
   private static final void sort(List var0, Function2 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparison");
      throw new NotImplementedError((String)null, 1, (DefaultConstructorMarker)null);
   }

   public static final void sortWith(List var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      if (var0.size() > 1) {
         Collections.sort(var0, var1);
      }

   }
}
