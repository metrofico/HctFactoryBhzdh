package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000:\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a5\u0010\u0000\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0005\"\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007\u001a9\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u0006\u0010\n\u001a\u0002H\u00012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fH\u0000¢\u0006\u0002\u0010\r\u001a\u0015\u0010\u000e\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u0003H\u0087\b\u001a\u001d\u0010\u000e\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u000fH\u0087\b\u001a\u001f\u0010\u000e\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a7\u0010\u0010\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"},
   d2 = {"append", "T", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "appendElement", "", "element", "transform", "Lkotlin/Function1;", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "appendLine", "", "appendRange", "startIndex", "", "endIndex", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/StringsKt"
)
class StringsKt__AppendableKt {
   public StringsKt__AppendableKt() {
   }

   public static final Appendable append(Appendable var0, CharSequence... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "value");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0.append(var1[var2]);
      }

      return var0;
   }

   public static final void appendElement(Appendable var0, Object var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var2 != null) {
         var0.append((CharSequence)var2.invoke(var1));
      } else {
         boolean var3;
         if (var1 == null) {
            var3 = true;
         } else {
            var3 = var1 instanceof CharSequence;
         }

         if (var3) {
            var0.append((CharSequence)var1);
         } else if (var1 instanceof Character) {
            var0.append((Character)var1);
         } else {
            var0.append((CharSequence)String.valueOf(var1));
         }
      }

   }

   private static final Appendable appendLine(Appendable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   private static final Appendable appendLine(Appendable var0, char var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "append(value)");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   private static final Appendable appendLine(Appendable var0, CharSequence var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.append(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "append(value)");
      var0 = var0.append('\n');
      Intrinsics.checkNotNullExpressionValue(var0, "append('\\n')");
      return var0;
   }

   public static final Appendable appendRange(Appendable var0, CharSequence var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "value");
      var0 = var0.append(var1, var2, var3);
      Intrinsics.checkNotNull(var0, "null cannot be cast to non-null type T of kotlin.text.StringsKt__AppendableKt.appendRange");
      return var0;
   }
}
