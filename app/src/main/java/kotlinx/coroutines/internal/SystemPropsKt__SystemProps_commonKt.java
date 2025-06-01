package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.text.StringsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a,\u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u0000\u001a,\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\b2\b\b\u0002\u0010\u0006\u001a\u00020\b2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\t"},
   d2 = {"systemProp", "", "propertyName", "", "defaultValue", "", "minValue", "maxValue", "", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/internal/SystemPropsKt"
)
final class SystemPropsKt__SystemProps_commonKt {
   public static final int systemProp(String var0, int var1, int var2, int var3) {
      return (int)SystemPropsKt.systemProp(var0, (long)var1, (long)var2, (long)var3);
   }

   public static final long systemProp(String var0, long var1, long var3, long var5) {
      String var8 = SystemPropsKt.systemProp(var0);
      if (var8 != null) {
         Long var7 = StringsKt.toLongOrNull(var8);
         if (var7 != null) {
            var1 = var7;
            if (var3 <= var1 && var5 >= var1) {
               return var1;
            } else {
               throw (Throwable)(new IllegalStateException(("System property '" + var0 + "' should be in range " + var3 + ".." + var5 + ", but is '" + var1 + '\'').toString()));
            }
         } else {
            throw (Throwable)(new IllegalStateException(("System property '" + var0 + "' has unrecognized value '" + var8 + '\'').toString()));
         }
      } else {
         return var1;
      }
   }

   public static final boolean systemProp(String var0, boolean var1) {
      var0 = SystemPropsKt.systemProp(var0);
      if (var0 != null) {
         var1 = Boolean.parseBoolean(var0);
      }

      return var1;
   }

   // $FF: synthetic method
   public static int systemProp$default(String var0, int var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var2 = 1;
      }

      if ((var4 & 8) != 0) {
         var3 = Integer.MAX_VALUE;
      }

      return SystemPropsKt.systemProp(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static long systemProp$default(String var0, long var1, long var3, long var5, int var7, Object var8) {
      if ((var7 & 4) != 0) {
         var3 = 1L;
      }

      if ((var7 & 8) != 0) {
         var5 = Long.MAX_VALUE;
      }

      return SystemPropsKt.systemProp(var0, var1, var3, var5);
   }
}
