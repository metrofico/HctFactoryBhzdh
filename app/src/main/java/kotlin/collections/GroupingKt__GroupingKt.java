package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000@\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u009e\u0001\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\bø\u0001\u0000\u001a·\u0001\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001aI\u0010\u0014\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0016\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u0010H\u0007¢\u0006\u0002\u0010\u0016\u001a¿\u0001\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u000526\u0010\u0018\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\bø\u0001\u0000\u001a\u007f\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u001b\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001aØ\u0001\u0010\u001d\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u001026\u0010\u0018\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0093\u0001\u0010\u001d\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102\u0006\u0010\u001b\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001a\u008b\u0001\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0001\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001aH\u0087\bø\u0001\u0000\u001a¤\u0001\u0010\"\u001a\u0002H\u0010\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001aH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010#\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006$"},
   d2 = {"aggregate", "", "K", "R", "T", "Lkotlin/collections/Grouping;", "operation", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "key", "accumulator", "element", "", "first", "aggregateTo", "M", "", "destination", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "eachCountTo", "", "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "fold", "initialValueSelector", "Lkotlin/Function2;", "Lkotlin/Function3;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "reduce", "S", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/GroupingKt"
)
class GroupingKt__GroupingKt extends GroupingKt__GroupingJVMKt {
   public GroupingKt__GroupingKt() {
   }

   public static final Map aggregate(Grouping var0, Function4 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "operation");
      Map var4 = (Map)(new LinkedHashMap());

      boolean var2;
      Object var3;
      Object var6;
      Object var7;
      for(Iterator var5 = var0.sourceIterator(); var5.hasNext(); var4.put(var3, var1.invoke(var3, var7, var6, var2))) {
         var6 = var5.next();
         var3 = var0.keyOf(var6);
         var7 = var4.get(var3);
         if (var7 == null && !var4.containsKey(var3)) {
            var2 = true;
         } else {
            var2 = false;
         }
      }

      return var4;
   }

   public static final Map aggregateTo(Grouping var0, Map var1, Function4 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      Intrinsics.checkNotNullParameter(var2, "operation");

      boolean var3;
      Object var5;
      Object var6;
      Object var7;
      for(Iterator var4 = var0.sourceIterator(); var4.hasNext(); var1.put(var5, var2.invoke(var5, var6, var7, var3))) {
         var7 = var4.next();
         var5 = var0.keyOf(var7);
         var6 = var1.get(var5);
         if (var6 == null && !var1.containsKey(var5)) {
            var3 = true;
         } else {
            var3 = false;
         }
      }

      return var1;
   }

   public static final Map eachCountTo(Grouping var0, Map var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");

      Object var3;
      Object var4;
      for(Iterator var5 = var0.sourceIterator(); var5.hasNext(); var1.put(var4, ((Number)var3).intValue() + 1)) {
         var4 = var0.keyOf(var5.next());
         var3 = var1.get(var4);
         boolean var2;
         if (var3 == null && !var1.containsKey(var4)) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            var3 = 0;
         }
      }

      return var1;
   }

   public static final Map fold(Grouping var0, Object var1, Function2 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "operation");
      Map var6 = (Map)(new LinkedHashMap());

      Object var4;
      Object var7;
      Object var8;
      for(Iterator var5 = var0.sourceIterator(); var5.hasNext(); var6.put(var7, var2.invoke(var4, var8))) {
         var8 = var5.next();
         var7 = var0.keyOf(var8);
         var4 = var6.get(var7);
         boolean var3;
         if (var4 == null && !var6.containsKey(var7)) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3) {
            var4 = var1;
         }
      }

      return var6;
   }

   public static final Map fold(Grouping var0, Function2 var1, Function3 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "initialValueSelector");
      Intrinsics.checkNotNullParameter(var2, "operation");
      Map var8 = (Map)(new LinkedHashMap());

      Object var4;
      Object var6;
      Object var7;
      for(Iterator var5 = var0.sourceIterator(); var5.hasNext(); var8.put(var6, var2.invoke(var6, var4, var7))) {
         var7 = var5.next();
         var6 = var0.keyOf(var7);
         var4 = var8.get(var6);
         boolean var3;
         if (var4 == null && !var8.containsKey(var6)) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3) {
            var4 = var1.invoke(var6, var7);
         }
      }

      return var8;
   }

   public static final Map foldTo(Grouping var0, Map var1, Object var2, Function2 var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      Intrinsics.checkNotNullParameter(var3, "operation");

      Object var5;
      Object var6;
      Object var8;
      for(Iterator var7 = var0.sourceIterator(); var7.hasNext(); var1.put(var8, var3.invoke(var5, var6))) {
         var6 = var7.next();
         var8 = var0.keyOf(var6);
         var5 = var1.get(var8);
         boolean var4;
         if (var5 == null && !var1.containsKey(var8)) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (var4) {
            var5 = var2;
         }
      }

      return var1;
   }

   public static final Map foldTo(Grouping var0, Map var1, Function2 var2, Function3 var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      Intrinsics.checkNotNullParameter(var2, "initialValueSelector");
      Intrinsics.checkNotNullParameter(var3, "operation");

      Object var5;
      Object var6;
      Object var8;
      for(Iterator var7 = var0.sourceIterator(); var7.hasNext(); var1.put(var6, var3.invoke(var6, var5, var8))) {
         var8 = var7.next();
         var6 = var0.keyOf(var8);
         var5 = var1.get(var6);
         boolean var4;
         if (var5 == null && !var1.containsKey(var6)) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (var4) {
            var5 = var2.invoke(var6, var8);
         }
      }

      return var1;
   }

   public static final Map reduce(Grouping var0, Function3 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "operation");
      Map var5 = (Map)(new LinkedHashMap());

      Object var3;
      Object var6;
      for(Iterator var4 = var0.sourceIterator(); var4.hasNext(); var5.put(var6, var3)) {
         var3 = var4.next();
         var6 = var0.keyOf(var3);
         Object var7 = var5.get(var6);
         boolean var2;
         if (var7 == null && !var5.containsKey(var6)) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            var3 = var1.invoke(var6, var7, var3);
         }
      }

      return var5;
   }

   public static final Map reduceTo(Grouping var0, Map var1, Function3 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      Intrinsics.checkNotNullParameter(var2, "operation");

      Object var4;
      Object var6;
      for(Iterator var5 = var0.sourceIterator(); var5.hasNext(); var1.put(var6, var4)) {
         var4 = var5.next();
         var6 = var0.keyOf(var4);
         Object var7 = var1.get(var6);
         boolean var3;
         if (var7 == null && !var1.containsKey(var6)) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (!var3) {
            var4 = var2.invoke(var6, var7, var4);
         }
      }

      return var1;
   }
}
