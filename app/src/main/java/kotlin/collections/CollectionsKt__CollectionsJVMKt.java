package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.collections.builders.ListBuilder;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000R\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\b\f\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\t\u0010\u0000\u001a\u00020\u0001H\u0080\b\u001a\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0006H\u0001\u001a?\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\b\u001a\u00020\t2\u001d\u0010\n\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0006\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0081\bø\u0001\u0000\u001a7\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u001d\u0010\n\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0006\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0081\bø\u0001\u0000\u001a\u0011\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0081\b\u001a\u0011\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0081\b\u001a\"\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00132\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0081\b¢\u0006\u0002\u0010\u0017\u001a4\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0013\"\u0004\b\u0000\u0010\u00182\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0013H\u0081\b¢\u0006\u0002\u0010\u001a\u001a\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0006\"\u0004\b\u0000\u0010\u0004H\u0001\u001a\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0006\"\u0004\b\u0000\u0010\u00042\u0006\u0010\b\u001a\u00020\tH\u0001\u001a\u001f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0003\"\u0004\b\u0000\u0010\u00182\u0006\u0010\u001d\u001a\u0002H\u0018¢\u0006\u0002\u0010\u001e\u001a1\u0010\u001f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00140\u0013\"\u0004\b\u0000\u0010\u0018*\n\u0012\u0006\b\u0001\u0012\u0002H\u00180\u00132\u0006\u0010 \u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010!\u001a\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0003\"\u0004\b\u0000\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00180#H\u0007\u001a&\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0003\"\u0004\b\u0000\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00180#2\u0006\u0010$\u001a\u00020%H\u0007\u001a\u001f\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0003\"\u0004\b\u0000\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00180'H\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006("},
   d2 = {"brittleContainsOptimizationEnabled", "", "build", "", "E", "builder", "", "buildListInternal", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "checkCountOverflow", "count", "checkIndexOverflow", "index", "copyToArrayImpl", "", "", "collection", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", "T", "array", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "createListBuilder", "listOf", "element", "(Ljava/lang/Object;)Ljava/util/List;", "copyToArrayOfAny", "isVarargs", "([Ljava/lang/Object;Z)[Ljava/lang/Object;", "shuffled", "", "random", "Ljava/util/Random;", "toList", "Ljava/util/Enumeration;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/CollectionsKt"
)
class CollectionsKt__CollectionsJVMKt {
   public CollectionsKt__CollectionsJVMKt() {
   }

   public static final boolean brittleContainsOptimizationEnabled() {
      return CollectionSystemProperties.brittleContainsOptimizationEnabled;
   }

   public static final List build(List var0) {
      Intrinsics.checkNotNullParameter(var0, "builder");
      return ((ListBuilder)var0).build();
   }

   private static final List buildListInternal(int var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "builderAction");
      List var2 = CollectionsKt.createListBuilder(var0);
      var1.invoke(var2);
      return CollectionsKt.build(var2);
   }

   private static final List buildListInternal(Function1 var0) {
      Intrinsics.checkNotNullParameter(var0, "builderAction");
      List var1 = CollectionsKt.createListBuilder();
      var0.invoke(var1);
      return CollectionsKt.build(var1);
   }

   private static final int checkCountOverflow(int var0) {
      if (var0 < 0) {
         if (!PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            throw new ArithmeticException("Count overflow has happened.");
         }

         CollectionsKt.throwCountOverflow();
      }

      return var0;
   }

   private static final int checkIndexOverflow(int var0) {
      if (var0 < 0) {
         if (!PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            throw new ArithmeticException("Index overflow has happened.");
         }

         CollectionsKt.throwIndexOverflow();
      }

      return var0;
   }

   private static final Object[] copyToArrayImpl(Collection var0) {
      Intrinsics.checkNotNullParameter(var0, "collection");
      return CollectionToArray.toArray(var0);
   }

   private static final Object[] copyToArrayImpl(Collection var0, Object[] var1) {
      Intrinsics.checkNotNullParameter(var0, "collection");
      Intrinsics.checkNotNullParameter(var1, "array");
      Object[] var2 = CollectionToArray.toArray(var0, var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.CollectionsKt__CollectionsJVMKt.copyToArrayImpl>");
      return var2;
   }

   public static final Object[] copyToArrayOfAny(Object[] var0, boolean var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var1 || !Intrinsics.areEqual((Object)var0.getClass(), (Object)Object[].class)) {
         var0 = Arrays.copyOf(var0, var0.length, Object[].class);
         Intrinsics.checkNotNullExpressionValue(var0, "copyOf(this, this.size, Array<Any?>::class.java)");
      }

      return var0;
   }

   public static final List createListBuilder() {
      return (List)(new ListBuilder());
   }

   public static final List createListBuilder(int var0) {
      return (List)(new ListBuilder(var0));
   }

   public static final List listOf(Object var0) {
      List var1 = Collections.singletonList(var0);
      Intrinsics.checkNotNullExpressionValue(var1, "singletonList(element)");
      return var1;
   }

   public static final List shuffled(Iterable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      List var1 = CollectionsKt.toMutableList(var0);
      Collections.shuffle(var1);
      return var1;
   }

   public static final List shuffled(Iterable var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");
      List var2 = CollectionsKt.toMutableList(var0);
      Collections.shuffle(var2, var1);
      return var2;
   }

   private static final List toList(Enumeration var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      ArrayList var1 = Collections.list(var0);
      Intrinsics.checkNotNullExpressionValue(var1, "list(this)");
      return (List)var1;
   }
}
