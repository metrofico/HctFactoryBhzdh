package kotlin.text;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\f\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"},
   d2 = {"titlecaseImpl", "", "", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class _OneToManyTitlecaseMappingsKt {
   public static final String titlecaseImpl(char var0) {
      String var1 = String.valueOf(var0);
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.String");
      var1 = var1.toUpperCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).toUpperCase(Locale.ROOT)");
      if (var1.length() > 1) {
         if (var0 != 329) {
            var0 = var1.charAt(0);
            Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.String");
            var1 = var1.substring(1);
            Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).substring(startIndex)");
            Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.String");
            var1 = var1.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            var1 = var0 + var1;
         }

         return var1;
      } else {
         return String.valueOf(Character.toTitleCase(var0));
      }
   }
}
