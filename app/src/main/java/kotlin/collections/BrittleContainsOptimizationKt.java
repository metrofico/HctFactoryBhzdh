package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(
   d1 = {"\u0000 \n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0000¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0000\u001a,\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0000\u001a\u0018\u0010\t\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0002¨\u0006\u000b"},
   d2 = {"convertToSetForSetOperation", "", "T", "", "([Ljava/lang/Object;)Ljava/util/Collection;", "", "Lkotlin/sequences/Sequence;", "convertToSetForSetOperationWith", "source", "safeToConvertToSet", "", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class BrittleContainsOptimizationKt {
   public static final Collection convertToSetForSetOperation(Iterable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Collection var2;
      if (var0 instanceof Set) {
         var2 = (Collection)var0;
      } else if (var0 instanceof Collection) {
         Collection var1 = (Collection)var0;
         if (safeToConvertToSet(var1)) {
            var2 = (Collection)CollectionsKt.toHashSet(var0);
         } else {
            var2 = var1;
         }
      } else {
         Object var3;
         if (CollectionSystemProperties.brittleContainsOptimizationEnabled) {
            var3 = CollectionsKt.toHashSet(var0);
         } else {
            var3 = CollectionsKt.toList(var0);
         }

         var2 = (Collection)var3;
      }

      return var2;
   }

   public static final Collection convertToSetForSetOperation(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Object var1;
      if (CollectionSystemProperties.brittleContainsOptimizationEnabled) {
         var1 = SequencesKt.toHashSet(var0);
      } else {
         var1 = SequencesKt.toList(var0);
      }

      return (Collection)var1;
   }

   public static final Collection convertToSetForSetOperation(Object[] var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Object var1;
      if (CollectionSystemProperties.brittleContainsOptimizationEnabled) {
         var1 = ArraysKt.toHashSet(var0);
      } else {
         var1 = ArraysKt.asList(var0);
      }

      return (Collection)var1;
   }

   public static final Collection convertToSetForSetOperationWith(Iterable var0, Iterable var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "source");
      Collection var2;
      if (var0 instanceof Set) {
         var2 = (Collection)var0;
      } else if (var0 instanceof Collection) {
         if (var1 instanceof Collection && ((Collection)var1).size() < 2) {
            var2 = (Collection)var0;
         } else {
            Collection var3 = (Collection)var0;
            if (safeToConvertToSet(var3)) {
               var2 = (Collection)CollectionsKt.toHashSet(var0);
            } else {
               var2 = var3;
            }
         }
      } else {
         Object var4;
         if (CollectionSystemProperties.brittleContainsOptimizationEnabled) {
            var4 = CollectionsKt.toHashSet(var0);
         } else {
            var4 = CollectionsKt.toList(var0);
         }

         var2 = (Collection)var4;
      }

      return var2;
   }

   private static final boolean safeToConvertToSet(Collection var0) {
      boolean var1;
      if (CollectionSystemProperties.brittleContainsOptimizationEnabled && var0.size() > 2 && var0 instanceof ArrayList) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }
}
