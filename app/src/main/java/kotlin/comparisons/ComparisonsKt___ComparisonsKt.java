package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0018\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u001aG\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\b\u001a?\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\t\u001aK\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u000b\"\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\f\u001aG\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\b\u001a?\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\t\u001aK\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u000b\"\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\f¨\u0006\u000e"},
   d2 = {"maxOf", "T", "a", "b", "c", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "other", "", "(Ljava/lang/Object;[Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "minOf", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/comparisons/ComparisonsKt"
)
class ComparisonsKt___ComparisonsKt extends ComparisonsKt___ComparisonsJvmKt {
   public ComparisonsKt___ComparisonsKt() {
   }

   public static final Object maxOf(Object var0, Object var1, Object var2, Comparator var3) {
      Intrinsics.checkNotNullParameter(var3, "comparator");
      return ComparisonsKt.maxOf(var0, ComparisonsKt.maxOf(var1, var2, var3), var3);
   }

   public static final Object maxOf(Object var0, Object var1, Comparator var2) {
      Intrinsics.checkNotNullParameter(var2, "comparator");
      if (var2.compare(var0, var1) < 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final Object maxOf(Object var0, Object[] var1, Comparator var2) {
      Intrinsics.checkNotNullParameter(var1, "other");
      Intrinsics.checkNotNullParameter(var2, "comparator");
      int var4 = var1.length;

      Object var5;
      for(int var3 = 0; var3 < var4; var0 = var5) {
         Object var6 = var1[var3];
         var5 = var0;
         if (var2.compare(var0, var6) < 0) {
            var5 = var6;
         }

         ++var3;
      }

      return var0;
   }

   public static final Object minOf(Object var0, Object var1, Object var2, Comparator var3) {
      Intrinsics.checkNotNullParameter(var3, "comparator");
      return ComparisonsKt.minOf(var0, ComparisonsKt.minOf(var1, var2, var3), var3);
   }

   public static final Object minOf(Object var0, Object var1, Comparator var2) {
      Intrinsics.checkNotNullParameter(var2, "comparator");
      if (var2.compare(var0, var1) > 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final Object minOf(Object var0, Object[] var1, Comparator var2) {
      Intrinsics.checkNotNullParameter(var1, "other");
      Intrinsics.checkNotNullParameter(var2, "comparator");
      int var4 = var1.length;

      Object var5;
      for(int var3 = 0; var3 < var4; var0 = var5) {
         Object var6 = var1[var3];
         var5 = var0;
         if (var2.compare(var0, var6) > 0) {
            var5 = var6;
         }

         ++var3;
      }

      return var0;
   }
}
