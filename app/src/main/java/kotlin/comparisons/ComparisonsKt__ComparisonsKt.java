package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\bø\u0001\u0000\u001aY\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u000226\u0010\u0007\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\b\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005¢\u0006\u0002\u0010\t\u001aZ\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\bø\u0001\u0000\u001a>\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\bø\u0001\u0000\u001aZ\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\bø\u0001\u0000\u001a-\u0010\r\u001a\u00020\u000e\"\f\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00062\b\u0010\u000f\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0010\u001a\u0004\u0018\u0001H\u0002¢\u0006\u0002\u0010\u0011\u001aA\u0010\u0012\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u00022\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001aY\u0010\u0012\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u000226\u0010\u0007\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\b\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005¢\u0006\u0002\u0010\u0014\u001a]\u0010\u0012\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u00022\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aG\u0010\u0016\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u00022 \u0010\u0007\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\bH\u0002¢\u0006\u0004\b\u0017\u0010\u0014\u001a&\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a-\u0010\u0019\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087\b\u001a@\u0010\u0019\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001a2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003\u001a-\u0010\u001b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087\b\u001a@\u0010\u001b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001a2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003\u001a&\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a0\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\u001aO\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003H\u0086\u0004\u001aR\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\bø\u0001\u0000\u001an\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\bø\u0001\u0000\u001aR\u0010 \u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\bø\u0001\u0000\u001an\u0010 \u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\bø\u0001\u0000\u001ap\u0010!\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u000328\b\u0004\u0010\"\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000e0#H\u0087\bø\u0001\u0000\u001aO\u0010&\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003H\u0086\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006'"},
   d2 = {"compareBy", "Ljava/util/Comparator;", "T", "Lkotlin/Comparator;", "selector", "Lkotlin/Function1;", "", "selectors", "", "([Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "K", "comparator", "compareByDescending", "compareValues", "", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)I", "compareValuesBy", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;[Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)I", "compareValuesByImpl", "compareValuesByImpl$ComparisonsKt__ComparisonsKt", "naturalOrder", "nullsFirst", "", "nullsLast", "reverseOrder", "reversed", "then", "thenBy", "thenByDescending", "thenComparator", "comparison", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "thenDescending", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/comparisons/ComparisonsKt"
)
class ComparisonsKt__ComparisonsKt {
   public ComparisonsKt__ComparisonsKt() {
   }

   private static final Comparator compareBy(Comparator var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "comparator");
      Intrinsics.checkNotNullParameter(var1, "selector");
      return (Comparator)(new Comparator(var0, var1) {
         final Comparator $comparator;
         final Function1 $selector;

         public {
            this.$comparator = var1;
            this.$selector = var2;
         }

         public final int compare(Object var1, Object var2) {
            Comparator var4 = this.$comparator;
            Function1 var3 = this.$selector;
            return var4.compare(var3.invoke(var1), var3.invoke(var2));
         }
      });
   }

   private static final Comparator compareBy(Function1 var0) {
      Intrinsics.checkNotNullParameter(var0, "selector");
      return (Comparator)(new Comparator(var0) {
         final Function1 $selector;

         public {
            this.$selector = var1;
         }

         public final int compare(Object var1, Object var2) {
            Function1 var3 = this.$selector;
            return ComparisonsKt.compareValues((Comparable)var3.invoke(var1), (Comparable)var3.invoke(var2));
         }
      });
   }

   public static final Comparator compareBy(Function1... var0) {
      Intrinsics.checkNotNullParameter(var0, "selectors");
      boolean var1;
      if (var0.length > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (var1) {
         return (Comparator)(new Comparator(var0) {
            final Function1[] $selectors;

            {
               this.$selectors = var1;
            }

            public final int compare(Object var1, Object var2) {
               return ComparisonsKt__ComparisonsKt.compareValuesByImpl$ComparisonsKt__ComparisonsKt(var1, var2, this.$selectors);
            }
         });
      } else {
         throw new IllegalArgumentException("Failed requirement.".toString());
      }
   }

   private static final Comparator compareByDescending(Comparator var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "comparator");
      Intrinsics.checkNotNullParameter(var1, "selector");
      return (Comparator)(new Comparator(var0, var1) {
         final Comparator $comparator;
         final Function1 $selector;

         public {
            this.$comparator = var1;
            this.$selector = var2;
         }

         public final int compare(Object var1, Object var2) {
            Comparator var4 = this.$comparator;
            Function1 var3 = this.$selector;
            return var4.compare(var3.invoke(var2), var3.invoke(var1));
         }
      });
   }

   private static final Comparator compareByDescending(Function1 var0) {
      Intrinsics.checkNotNullParameter(var0, "selector");
      return (Comparator)(new Comparator(var0) {
         final Function1 $selector;

         public {
            this.$selector = var1;
         }

         public final int compare(Object var1, Object var2) {
            Function1 var3 = this.$selector;
            return ComparisonsKt.compareValues((Comparable)var3.invoke(var2), (Comparable)var3.invoke(var1));
         }
      });
   }

   public static final int compareValues(Comparable var0, Comparable var1) {
      if (var0 == var1) {
         return 0;
      } else if (var0 == null) {
         return -1;
      } else {
         return var1 == null ? 1 : var0.compareTo(var1);
      }
   }

   private static final int compareValuesBy(Object var0, Object var1, Comparator var2, Function1 var3) {
      Intrinsics.checkNotNullParameter(var2, "comparator");
      Intrinsics.checkNotNullParameter(var3, "selector");
      return var2.compare(var3.invoke(var0), var3.invoke(var1));
   }

   private static final int compareValuesBy(Object var0, Object var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var2, "selector");
      return ComparisonsKt.compareValues((Comparable)var2.invoke(var0), (Comparable)var2.invoke(var1));
   }

   public static final int compareValuesBy(Object var0, Object var1, Function1... var2) {
      Intrinsics.checkNotNullParameter(var2, "selectors");
      boolean var3;
      if (var2.length > 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         return compareValuesByImpl$ComparisonsKt__ComparisonsKt(var0, var1, var2);
      } else {
         throw new IllegalArgumentException("Failed requirement.".toString());
      }
   }

   private static final int compareValuesByImpl$ComparisonsKt__ComparisonsKt(Object var0, Object var1, Function1[] var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Function1 var6 = var2[var3];
         int var5 = ComparisonsKt.compareValues((Comparable)var6.invoke(var0), (Comparable)var6.invoke(var1));
         if (var5 != 0) {
            return var5;
         }
      }

      return 0;
   }

   public static final Comparator naturalOrder() {
      NaturalOrderComparator var0 = NaturalOrderComparator.INSTANCE;
      Intrinsics.checkNotNull(var0, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder> }");
      return (Comparator)var0;
   }

   private static final Comparator nullsFirst() {
      return ComparisonsKt.nullsFirst(ComparisonsKt.naturalOrder());
   }

   public static final Comparator nullsFirst(Comparator var0) {
      Intrinsics.checkNotNullParameter(var0, "comparator");
      return (Comparator)(new Comparator(var0) {
         final Comparator $comparator;

         {
            this.$comparator = var1;
         }

         public final int compare(Object var1, Object var2) {
            int var3;
            if (var1 == var2) {
               var3 = 0;
            } else if (var1 == null) {
               var3 = -1;
            } else if (var2 == null) {
               var3 = 1;
            } else {
               var3 = this.$comparator.compare(var1, var2);
            }

            return var3;
         }
      });
   }

   private static final Comparator nullsLast() {
      return ComparisonsKt.nullsLast(ComparisonsKt.naturalOrder());
   }

   public static final Comparator nullsLast(Comparator var0) {
      Intrinsics.checkNotNullParameter(var0, "comparator");
      return (Comparator)(new Comparator(var0) {
         final Comparator $comparator;

         {
            this.$comparator = var1;
         }

         public final int compare(Object var1, Object var2) {
            int var3;
            if (var1 == var2) {
               var3 = 0;
            } else if (var1 == null) {
               var3 = 1;
            } else if (var2 == null) {
               var3 = -1;
            } else {
               var3 = this.$comparator.compare(var1, var2);
            }

            return var3;
         }
      });
   }

   public static final Comparator reverseOrder() {
      ReverseOrderComparator var0 = ReverseOrderComparator.INSTANCE;
      Intrinsics.checkNotNull(var0, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reverseOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reverseOrder> }");
      return (Comparator)var0;
   }

   public static final Comparator reversed(Comparator var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var0 instanceof ReversedComparator) {
         var0 = ((ReversedComparator)var0).getComparator();
      } else if (Intrinsics.areEqual((Object)var0, (Object)NaturalOrderComparator.INSTANCE)) {
         ReverseOrderComparator var1 = ReverseOrderComparator.INSTANCE;
         Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed> }");
         var0 = (Comparator)var1;
      } else if (Intrinsics.areEqual((Object)var0, (Object)ReverseOrderComparator.INSTANCE)) {
         NaturalOrderComparator var2 = NaturalOrderComparator.INSTANCE;
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed> }");
         var0 = (Comparator)var2;
      } else {
         var0 = (Comparator)(new ReversedComparator(var0));
      }

      return var0;
   }

   public static final Comparator then(Comparator var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return (Comparator)(new Comparator(var0, var1) {
         final Comparator $comparator;
         final Comparator $this_then;

         {
            this.$this_then = var1;
            this.$comparator = var2;
         }

         public final int compare(Object var1, Object var2) {
            int var3 = this.$this_then.compare(var1, var2);
            if (var3 == 0) {
               var3 = this.$comparator.compare(var1, var2);
            }

            return var3;
         }
      });
   }

   private static final Comparator thenBy(Comparator var0, Comparator var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      Intrinsics.checkNotNullParameter(var2, "selector");
      return (Comparator)(new Comparator(var0, var1, var2) {
         final Comparator $comparator;
         final Function1 $selector;
         final Comparator $this_thenBy;

         public {
            this.$this_thenBy = var1;
            this.$comparator = var2;
            this.$selector = var3;
         }

         public final int compare(Object var1, Object var2) {
            int var3 = this.$this_thenBy.compare(var1, var2);
            if (var3 == 0) {
               Comparator var4 = this.$comparator;
               Function1 var5 = this.$selector;
               var3 = var4.compare(var5.invoke(var1), var5.invoke(var2));
            }

            return var3;
         }
      });
   }

   private static final Comparator thenBy(Comparator var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      return (Comparator)(new Comparator(var0, var1) {
         final Function1 $selector;
         final Comparator $this_thenBy;

         public {
            this.$this_thenBy = var1;
            this.$selector = var2;
         }

         public final int compare(Object var1, Object var2) {
            int var3 = this.$this_thenBy.compare(var1, var2);
            if (var3 == 0) {
               Function1 var4 = this.$selector;
               var3 = ComparisonsKt.compareValues((Comparable)var4.invoke(var1), (Comparable)var4.invoke(var2));
            }

            return var3;
         }
      });
   }

   private static final Comparator thenByDescending(Comparator var0, Comparator var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      Intrinsics.checkNotNullParameter(var2, "selector");
      return (Comparator)(new Comparator(var0, var1, var2) {
         final Comparator $comparator;
         final Function1 $selector;
         final Comparator $this_thenByDescending;

         public {
            this.$this_thenByDescending = var1;
            this.$comparator = var2;
            this.$selector = var3;
         }

         public final int compare(Object var1, Object var2) {
            int var3 = this.$this_thenByDescending.compare(var1, var2);
            if (var3 == 0) {
               Comparator var5 = this.$comparator;
               Function1 var4 = this.$selector;
               var3 = var5.compare(var4.invoke(var2), var4.invoke(var1));
            }

            return var3;
         }
      });
   }

   private static final Comparator thenByDescending(Comparator var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "selector");
      return (Comparator)(new Comparator(var0, var1) {
         final Function1 $selector;
         final Comparator $this_thenByDescending;

         public {
            this.$this_thenByDescending = var1;
            this.$selector = var2;
         }

         public final int compare(Object var1, Object var2) {
            int var3 = this.$this_thenByDescending.compare(var1, var2);
            if (var3 == 0) {
               Function1 var4 = this.$selector;
               var3 = ComparisonsKt.compareValues((Comparable)var4.invoke(var2), (Comparable)var4.invoke(var1));
            }

            return var3;
         }
      });
   }

   private static final Comparator thenComparator(Comparator var0, Function2 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparison");
      return (Comparator)(new Comparator(var0, var1) {
         final Function2 $comparison;
         final Comparator $this_thenComparator;

         public {
            this.$this_thenComparator = var1;
            this.$comparison = var2;
         }

         public final int compare(Object var1, Object var2) {
            int var3 = this.$this_thenComparator.compare(var1, var2);
            if (var3 == 0) {
               var3 = ((Number)this.$comparison.invoke(var1, var2)).intValue();
            }

            return var3;
         }
      });
   }

   public static final Comparator thenDescending(Comparator var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return (Comparator)(new Comparator(var0, var1) {
         final Comparator $comparator;
         final Comparator $this_thenDescending;

         {
            this.$this_thenDescending = var1;
            this.$comparator = var2;
         }

         public final int compare(Object var1, Object var2) {
            int var3 = this.$this_thenDescending.compare(var1, var2);
            if (var3 == 0) {
               var3 = this.$comparator.compare(var2, var1);
            }

            return var3;
         }
      });
   }
}
