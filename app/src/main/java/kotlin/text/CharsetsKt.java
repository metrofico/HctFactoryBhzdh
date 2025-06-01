package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b¨\u0006\u0004"},
   d2 = {"charset", "Ljava/nio/charset/Charset;", "charsetName", "", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class CharsetsKt {
   private static final Charset charset(String var0) {
      Intrinsics.checkNotNullParameter(var0, "charsetName");
      Charset var1 = Charset.forName(var0);
      Intrinsics.checkNotNullExpressionValue(var1, "forName(charsetName)");
      return var1;
   }
}
