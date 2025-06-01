package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aC\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u00072\u0006\u0010\f\u001a\u00020\u00062!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u00070\u000eH\u0087\bø\u0001\u0000\u001aC\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u00072\u0006\u0010\f\u001a\u00020\u00062!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u00070\u000eH\u0087\bø\u0001\u0000\u001a\u001f\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u0002H\u00070\u0015j\b\u0012\u0004\u0012\u0002H\u0007`\u0016\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001a5\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u0002H\u00070\u0015j\b\u0012\u0004\u0012\u0002H\u0007`\u0016\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007¢\u0006\u0002\u0010\u0019\u001aN\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\b\"\u0004\b\u0000\u0010\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u001f\b\u0001\u0010\u001d\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0\u0013\u0012\u0004\u0012\u00020\u001e0\u000e¢\u0006\u0002\b\u001fH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001aF\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\b\"\u0004\b\u0000\u0010\u001b2\u001f\b\u0001\u0010\u001d\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0\u0013\u0012\u0004\u0012\u00020\u001e0\u000e¢\u0006\u0002\b\u001fH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u0012\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007\u001a\u0015\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001a+\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007¢\u0006\u0002\u0010\"\u001a%\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\b\b\u0000\u0010\u0007*\u00020$2\b\u0010%\u001a\u0004\u0018\u0001H\u0007¢\u0006\u0002\u0010&\u001a3\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\b\b\u0000\u0010\u0007*\u00020$2\u0016\u0010\u0017\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u00070\u0018\"\u0004\u0018\u0001H\u0007¢\u0006\u0002\u0010\"\u001a\u0015\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001a+\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007¢\u0006\u0002\u0010\"\u001a%\u0010(\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0002¢\u0006\u0002\b+\u001a\b\u0010,\u001a\u00020\u001eH\u0001\u001a\b\u0010-\u001a\u00020\u001eH\u0001\u001a%\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0002\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018H\u0000¢\u0006\u0002\u0010/\u001aS\u00100\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\u0006\u0010%\u001a\u0002H\u00072\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000702j\n\u0012\u0006\b\u0000\u0012\u0002H\u0007`32\b\b\u0002\u0010)\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u0006¢\u0006\u0002\u00104\u001a>\u00100\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\b\b\u0002\u0010)\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u00062\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00060\u000e\u001aE\u00100\u001a\u00020\u0006\"\u000e\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u000706*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00070\b2\b\u0010%\u001a\u0004\u0018\u0001H\u00072\b\b\u0002\u0010)\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u0006¢\u0006\u0002\u00107\u001ag\u00108\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007\"\u000e\b\u0001\u00109*\b\u0012\u0004\u0012\u0002H906*\b\u0012\u0004\u0012\u0002H\u00070\b2\b\u0010:\u001a\u0004\u0018\u0001H92\b\b\u0002\u0010)\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u00062\u0016\b\u0004\u0010;\u001a\u0010\u0012\u0004\u0012\u0002H\u0007\u0012\u0006\u0012\u0004\u0018\u0001H90\u000eH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010<\u001a,\u0010=\u001a\u00020>\"\t\b\u0000\u0010\u0007¢\u0006\u0002\b?*\b\u0012\u0004\u0012\u0002H\u00070\u00022\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0002H\u0087\b\u001a;\u0010@\u001a\u0002HA\"\u0010\b\u0000\u0010B*\u0006\u0012\u0002\b\u00030\u0002*\u0002HA\"\u0004\b\u0001\u0010A*\u0002HB2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002HA0DH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010E\u001a\u0019\u0010F\u001a\u00020>\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0002H\u0087\b\u001a,\u0010G\u001a\u00020>\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\u001e\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\bH\u0000\u001a!\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0002\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u0002H\u0087\b\u001a!\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\bH\u0087\b\u001a&\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070K2\u0006\u0010L\u001a\u00020MH\u0007\"\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"!\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006N"},
   d2 = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/util/Collection;)Lkotlin/ranges/IntRange;", "lastIndex", "", "T", "", "getLastIndex", "(Ljava/util/List;)I", "List", "size", "init", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "MutableList", "", "arrayListOf", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "elements", "", "([Ljava/lang/Object;)Ljava/util/ArrayList;", "buildList", "E", "capacity", "builderAction", "", "Lkotlin/ExtensionFunctionType;", "emptyList", "listOf", "([Ljava/lang/Object;)Ljava/util/List;", "listOfNotNull", "", "element", "(Ljava/lang/Object;)Ljava/util/List;", "mutableListOf", "rangeCheck", "fromIndex", "toIndex", "rangeCheck$CollectionsKt__CollectionsKt", "throwCountOverflow", "throwIndexOverflow", "asCollection", "([Ljava/lang/Object;)Ljava/util/Collection;", "binarySearch", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;II)I", "comparison", "", "(Ljava/util/List;Ljava/lang/Comparable;II)I", "binarySearchBy", "K", "key", "selector", "(Ljava/util/List;Ljava/lang/Comparable;IILkotlin/jvm/functions/Function1;)I", "containsAll", "", "Lkotlin/internal/OnlyInputTypes;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "isNullOrEmpty", "optimizeReadOnlyList", "orEmpty", "shuffled", "", "random", "Lkotlin/random/Random;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/CollectionsKt"
)
class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {
   public CollectionsKt__CollectionsKt() {
   }

   private static final List List(int var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "init");
      ArrayList var3 = new ArrayList(var0);

      for(int var2 = 0; var2 < var0; ++var2) {
         var3.add(var1.invoke(var2));
      }

      return (List)var3;
   }

   private static final List MutableList(int var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "init");
      ArrayList var3 = new ArrayList(var0);

      for(int var2 = 0; var2 < var0; ++var2) {
         var3.add(var1.invoke(var2));
      }

      return (List)var3;
   }

   private static final ArrayList arrayListOf() {
      return new ArrayList();
   }

   public static final ArrayList arrayListOf(Object... var0) {
      Intrinsics.checkNotNullParameter(var0, "elements");
      ArrayList var1;
      if (var0.length == 0) {
         var1 = new ArrayList();
      } else {
         var1 = new ArrayList((Collection)(new ArrayAsCollection(var0, true)));
      }

      return var1;
   }

   public static final Collection asCollection(Object[] var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (Collection)(new ArrayAsCollection(var0, false));
   }

   public static final int binarySearch(List var0, int var1, int var2, Function1 var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var3, "comparison");
      rangeCheck$CollectionsKt__CollectionsKt(var0.size(), var1, var2);
      --var2;

      while(var1 <= var2) {
         int var5 = var1 + var2 >>> 1;
         int var4 = ((Number)var3.invoke(var0.get(var5))).intValue();
         if (var4 < 0) {
            var1 = var5 + 1;
         } else {
            if (var4 <= 0) {
               return var5;
            }

            var2 = var5 - 1;
         }
      }

      return -(var1 + 1);
   }

   public static final int binarySearch(List var0, Comparable var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      rangeCheck$CollectionsKt__CollectionsKt(var0.size(), var2, var3);
      --var3;

      while(var2 <= var3) {
         int var4 = var2 + var3 >>> 1;
         int var5 = ComparisonsKt.compareValues((Comparable)var0.get(var4), var1);
         if (var5 < 0) {
            var2 = var4 + 1;
         } else {
            if (var5 <= 0) {
               return var4;
            }

            var3 = var4 - 1;
         }
      }

      return -(var2 + 1);
   }

   public static final int binarySearch(List var0, Object var1, Comparator var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "comparator");
      rangeCheck$CollectionsKt__CollectionsKt(var0.size(), var3, var4);
      --var4;

      while(var3 <= var4) {
         int var5 = var3 + var4 >>> 1;
         int var6 = var2.compare(var0.get(var5), var1);
         if (var6 < 0) {
            var3 = var5 + 1;
         } else {
            if (var6 <= 0) {
               return var5;
            }

            var4 = var5 - 1;
         }
      }

      return -(var3 + 1);
   }

   // $FF: synthetic method
   public static int binarySearch$default(List var0, int var1, int var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.size();
      }

      return CollectionsKt.binarySearch(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static int binarySearch$default(List var0, Comparable var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = var0.size();
      }

      return CollectionsKt.binarySearch(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static int binarySearch$default(List var0, Object var1, Comparator var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 4) != 0) {
         var3 = 0;
      }

      if ((var5 & 8) != 0) {
         var4 = var0.size();
      }

      return CollectionsKt.binarySearch(var0, var1, var2, var3, var4);
   }

   public static final int binarySearchBy(List var0, Comparable var1, int var2, int var3, Function1 var4) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var4, "selector");
      return CollectionsKt.binarySearch(var0, var2, var3, (Function1)(new Function1(var4, var1) {
         final Comparable $key;
         final Function1 $selector;

         public {
            this.$selector = var1;
            this.$key = var2;
         }

         public final Integer invoke(Object var1) {
            return ComparisonsKt.compareValues((Comparable)this.$selector.invoke(var1), this.$key);
         }
      }));
   }

   // $FF: synthetic method
   public static int binarySearchBy$default(List var0, Comparable var1, int var2, int var3, Function1 var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 0;
      }

      if ((var5 & 4) != 0) {
         var3 = var0.size();
      }

      return CollectionsKt.binarySearch(var0, var2, var3, (Function1)(new Function1(var4, var1) {
         final Comparable $key;
         final Function1 $selector;

         public {
            this.$selector = var1;
            this.$key = var2;
         }

         public final Integer invoke(Object var1) {
            return ComparisonsKt.compareValues((Comparable)this.$selector.invoke(var1), this.$key);
         }
      }));
   }

   private static final List buildList(int var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "builderAction");
      List var2 = CollectionsKt.createListBuilder(var0);
      var1.invoke(var2);
      return CollectionsKt.build(var2);
   }

   private static final List buildList(Function1 var0) {
      Intrinsics.checkNotNullParameter(var0, "builderAction");
      List var1 = CollectionsKt.createListBuilder();
      var0.invoke(var1);
      return CollectionsKt.build(var1);
   }

   private static final boolean containsAll(Collection var0, Collection var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "elements");
      return var0.containsAll(var1);
   }

   public static final List emptyList() {
      return (List)EmptyList.INSTANCE;
   }

   public static final IntRange getIndices(Collection var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new IntRange(0, var0.size() - 1);
   }

   public static final int getLastIndex(List var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.size() - 1;
   }

   private static final Object ifEmpty(Collection var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "defaultValue");
      Object var2 = var0;
      if (var0.isEmpty()) {
         var2 = var1.invoke();
      }

      return var2;
   }

   private static final boolean isNotEmpty(Collection var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.isEmpty() ^ true;
   }

   private static final boolean isNullOrEmpty(Collection var0) {
      boolean var1;
      if (var0 != null && !var0.isEmpty()) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private static final List listOf() {
      return CollectionsKt.emptyList();
   }

   public static final List listOf(Object... var0) {
      Intrinsics.checkNotNullParameter(var0, "elements");
      List var1;
      if (var0.length > 0) {
         var1 = ArraysKt.asList(var0);
      } else {
         var1 = CollectionsKt.emptyList();
      }

      return var1;
   }

   public static final List listOfNotNull(Object var0) {
      List var1;
      if (var0 != null) {
         var1 = CollectionsKt.listOf(var0);
      } else {
         var1 = CollectionsKt.emptyList();
      }

      return var1;
   }

   public static final List listOfNotNull(Object... var0) {
      Intrinsics.checkNotNullParameter(var0, "elements");
      return ArraysKt.filterNotNull(var0);
   }

   private static final List mutableListOf() {
      return (List)(new ArrayList());
   }

   public static final List mutableListOf(Object... var0) {
      Intrinsics.checkNotNullParameter(var0, "elements");
      List var1;
      if (var0.length == 0) {
         var1 = (List)(new ArrayList());
      } else {
         var1 = (List)(new ArrayList((Collection)(new ArrayAsCollection(var0, true))));
      }

      return var1;
   }

   public static final List optimizeReadOnlyList(List var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int var1 = var0.size();
      if (var1 != 0) {
         if (var1 == 1) {
            var0 = CollectionsKt.listOf(var0.get(0));
         }
      } else {
         var0 = CollectionsKt.emptyList();
      }

      return var0;
   }

   private static final Collection orEmpty(Collection var0) {
      Collection var1 = var0;
      if (var0 == null) {
         var1 = (Collection)CollectionsKt.emptyList();
      }

      return var1;
   }

   private static final List orEmpty(List var0) {
      List var1 = var0;
      if (var0 == null) {
         var1 = CollectionsKt.emptyList();
      }

      return var1;
   }

   private static final void rangeCheck$CollectionsKt__CollectionsKt(int var0, int var1, int var2) {
      if (var1 <= var2) {
         if (var1 >= 0) {
            if (var2 > var0) {
               throw new IndexOutOfBoundsException("toIndex (" + var2 + ") is greater than size (" + var0 + ").");
            }
         } else {
            throw new IndexOutOfBoundsException("fromIndex (" + var1 + ") is less than zero.");
         }
      } else {
         throw new IllegalArgumentException("fromIndex (" + var1 + ") is greater than toIndex (" + var2 + ").");
      }
   }

   public static final List shuffled(Iterable var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");
      List var2 = CollectionsKt.toMutableList(var0);
      CollectionsKt.shuffle(var2, var1);
      return var2;
   }

   public static final void throwCountOverflow() {
      throw new ArithmeticException("Count overflow has happened.");
   }

   public static final void throwIndexOverflow() {
      throw new ArithmeticException("Index overflow has happened.");
   }
}
