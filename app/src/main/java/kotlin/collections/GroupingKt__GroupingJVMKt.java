package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(
   d1 = {"\u0000&\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\u001a0\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007\u001aZ\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00072\u001e\u0010\n\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f\u0012\u0004\u0012\u0002H\b0\u000bH\u0081\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"},
   d2 = {"eachCount", "", "K", "", "T", "Lkotlin/collections/Grouping;", "mapValuesInPlace", "", "R", "V", "f", "Lkotlin/Function1;", "", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/GroupingKt"
)
class GroupingKt__GroupingJVMKt {
   public GroupingKt__GroupingJVMKt() {
   }

   public static final Map eachCount(Grouping var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Map var3 = (Map)(new LinkedHashMap());
      Iterator var4 = var0.sourceIterator();

      while(var4.hasNext()) {
         Object var5 = var0.keyOf(var4.next());
         Object var2 = var3.get(var5);
         boolean var1;
         if (var2 == null && !var3.containsKey(var5)) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            var2 = new Ref.IntRef();
         }

         Ref.IntRef var7 = (Ref.IntRef)var2;
         ++var7.element;
         var3.put(var5, var7);
      }

      Iterator var8 = ((Iterable)var3.entrySet()).iterator();

      while(var8.hasNext()) {
         Map.Entry var6 = (Map.Entry)var8.next();
         Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda-4, R of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda-4>");
         TypeIntrinsics.asMutableMapEntry(var6).setValue(((Ref.IntRef)var6.getValue()).element);
      }

      return TypeIntrinsics.asMutableMap(var3);
   }

   private static final Map mapValuesInPlace(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "f");
      Iterator var2 = ((Iterable)var0.entrySet()).iterator();

      while(var2.hasNext()) {
         Map.Entry var3 = (Map.Entry)var2.next();
         Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda-4, R of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda-4>");
         TypeIntrinsics.asMutableMapEntry(var3).setValue(var1.invoke(var3));
      }

      return TypeIntrinsics.asMutableMap(var0);
   }
}
