package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\r\n\u0000\u001a>\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a6\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u001f\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0087\b\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u000e\"\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u000f\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000e\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0010\u001a\u0015\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007H\u0087\b\u001a\u001f\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0087\b\u001a\u001d\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\r\u001a\u00020\u0012H\u0087\b\u001a\u001d\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\r\u001a\u00020\u0013H\u0087\b\u001a\u001d\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\r\u001a\u00020\u0014H\u0087\b\u001a\u001f\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0015H\u0087\b\u001a\u001f\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"},
   d2 = {"buildString", "", "capacity", "", "builderAction", "Lkotlin/Function1;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "append", "obj", "", "value", "", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "appendLine", "", "", "", "", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/StringsKt"
)
class StringsKt__StringBuilderKt extends StringsKt__StringBuilderJVMKt {
   public StringsKt__StringBuilderKt() {
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "Use append(value: Any?) instead",
      replaceWith = @ReplaceWith(
   expression = "append(value = obj)",
   imports = {}
)
   )
   private static final StringBuilder append(StringBuilder var0, Object var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this.append(obj)");
      return var0;
   }

   public static final StringBuilder append(StringBuilder var0, Object... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "value");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0.append(var1[var2]);
      }

      return var0;
   }

   public static final StringBuilder append(StringBuilder var0, String... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "value");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0.append(var1[var2]);
      }

      return var0;
   }

   private static final StringBuilder appendLine(StringBuilder var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   private static final StringBuilder appendLine(StringBuilder var0, char var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "append(value)");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   private static final StringBuilder appendLine(StringBuilder var0, CharSequence var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "append(value)");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   private static final StringBuilder appendLine(StringBuilder var0, Object var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "append(value)");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   private static final StringBuilder appendLine(StringBuilder var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "append(value)");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   private static final StringBuilder appendLine(StringBuilder var0, boolean var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "append(value)");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   private static final StringBuilder appendLine(StringBuilder var0, char[] var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "value");
      var0 = var0.append(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "append(value)");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   private static final String buildString(int var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "builderAction");
      StringBuilder var2 = new StringBuilder(var0);
      var1.invoke(var2);
      String var3 = var2.toString();
      Intrinsics.checkNotNullExpressionValue(var3, "StringBuilder(capacity).…builderAction).toString()");
      return var3;
   }

   private static final String buildString(Function1 var0) {
      Intrinsics.checkNotNullParameter(var0, "builderAction");
      StringBuilder var1 = new StringBuilder();
      var0.invoke(var1);
      String var2 = var1.toString();
      Intrinsics.checkNotNullExpressionValue(var2, "StringBuilder().apply(builderAction).toString()");
      return var2;
   }
}
