package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000*\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\bø\u0001\u0000\u001a \u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\b\u001a\u00020\u0007H\u0001\u001a\u001f\u0010\t\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001¢\u0006\u0002\u0010\n\u001a\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001a@\u0010\r\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\f0\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000f*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000f0\u000e0\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"},
   d2 = {"Iterable", "", "T", "iterator", "Lkotlin/Function0;", "", "collectionSizeOrDefault", "", "default", "collectionSizeOrNull", "(Ljava/lang/Iterable;)Ljava/lang/Integer;", "flatten", "", "unzip", "Lkotlin/Pair;", "R", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/CollectionsKt"
)
class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {
   public CollectionsKt__IterablesKt() {
   }

   private static final Iterable Iterable(Function0 var0) {
      Intrinsics.checkNotNullParameter(var0, "iterator");
      return (Iterable)(new Iterable(var0) {
         final Function0 $iterator;

         public {
            this.$iterator = var1;
         }

         public Iterator iterator() {
            return (Iterator)this.$iterator.invoke();
         }
      });
   }

   public static final int collectionSizeOrDefault(Iterable var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var0 instanceof Collection) {
         var1 = ((Collection)var0).size();
      }

      return var1;
   }

   public static final Integer collectionSizeOrNull(Iterable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Integer var1;
      if (var0 instanceof Collection) {
         var1 = ((Collection)var0).size();
      } else {
         var1 = null;
      }

      return var1;
   }

   public static final List flatten(Iterable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      ArrayList var1 = new ArrayList();
      Iterator var3 = var0.iterator();

      while(var3.hasNext()) {
         Iterable var2 = (Iterable)var3.next();
         CollectionsKt.addAll((Collection)var1, var2);
      }

      return (List)var1;
   }

   public static final Pair unzip(Iterable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int var1 = CollectionsKt.collectionSizeOrDefault(var0, 10);
      ArrayList var3 = new ArrayList(var1);
      ArrayList var2 = new ArrayList(var1);
      Iterator var4 = var0.iterator();

      while(var4.hasNext()) {
         Pair var5 = (Pair)var4.next();
         var3.add(var5.getFirst());
         var2.add(var5.getSecond());
      }

      return TuplesKt.to(var3, var2);
   }
}
