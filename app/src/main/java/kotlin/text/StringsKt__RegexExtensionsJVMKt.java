package kotlin.text;

import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b¨\u0006\u0003"},
   d2 = {"toRegex", "Lkotlin/text/Regex;", "Ljava/util/regex/Pattern;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/StringsKt"
)
class StringsKt__RegexExtensionsJVMKt extends StringsKt__IndentKt {
   public StringsKt__RegexExtensionsJVMKt() {
   }

   private static final Regex toRegex(Pattern var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new Regex(var0);
   }
}
