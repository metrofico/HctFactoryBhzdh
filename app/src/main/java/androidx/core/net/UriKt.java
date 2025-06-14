package androidx.core.net;

import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0086\b\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0004H\u0086\b¨\u0006\u0005"},
   d2 = {"toFile", "Ljava/io/File;", "Landroid/net/Uri;", "toUri", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class UriKt {
   public static final File toFile(Uri var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toFile");
      if (Intrinsics.areEqual((Object)var0.getScheme(), (Object)"file")) {
         return new File(var0.getPath());
      } else {
         throw (Throwable)(new IllegalArgumentException(("Uri lacks 'file' scheme: " + var0).toString()));
      }
   }

   public static final Uri toUri(File var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toUri");
      Uri var1 = Uri.fromFile(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Uri.fromFile(this)");
      return var1;
   }

   public static final Uri toUri(String var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toUri");
      Uri var1 = Uri.parse(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Uri.parse(this)");
      return var1;
   }
}
