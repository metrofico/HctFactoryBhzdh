package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0002¨\u0006\u0006"},
   d2 = {"constructMessage", "", "file", "Ljava/io/File;", "other", "reason", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class ExceptionsKt {
   // $FF: synthetic method
   public static final String access$constructMessage(File var0, File var1, String var2) {
      return constructMessage(var0, var1, var2);
   }

   private static final String constructMessage(File var0, File var1, String var2) {
      StringBuilder var3 = new StringBuilder(var0.toString());
      if (var1 != null) {
         var3.append(" -> " + var1);
      }

      if (var2 != null) {
         var3.append(": " + var2);
      }

      String var4 = var3.toString();
      Intrinsics.checkNotNullExpressionValue(var4, "sb.toString()");
      return var4;
   }
}
