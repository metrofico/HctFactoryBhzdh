package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"kotlinx/coroutines/internal/SystemPropsKt__SystemPropsKt", "kotlinx/coroutines/internal/SystemPropsKt__SystemProps_commonKt"},
   k = 4,
   mv = {1, 4, 0}
)
public final class SystemPropsKt {
   public static final int getAVAILABLE_PROCESSORS() {
      return SystemPropsKt__SystemPropsKt.getAVAILABLE_PROCESSORS();
   }

   public static final int systemProp(String var0, int var1, int var2, int var3) {
      return SystemPropsKt__SystemProps_commonKt.systemProp(var0, var1, var2, var3);
   }

   public static final long systemProp(String var0, long var1, long var3, long var5) {
      return SystemPropsKt__SystemProps_commonKt.systemProp(var0, var1, var3, var5);
   }

   public static final String systemProp(String var0) {
      return SystemPropsKt__SystemPropsKt.systemProp(var0);
   }

   public static final boolean systemProp(String var0, boolean var1) {
      return SystemPropsKt__SystemProps_commonKt.systemProp(var0, var1);
   }

   // $FF: synthetic method
   public static int systemProp$default(String var0, int var1, int var2, int var3, int var4, Object var5) {
      return SystemPropsKt__SystemProps_commonKt.systemProp$default(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static long systemProp$default(String var0, long var1, long var3, long var5, int var7, Object var8) {
      return SystemPropsKt__SystemProps_commonKt.systemProp$default(var0, var1, var3, var5, var7, var8);
   }
}
