package androidx.core.util;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\u0016\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a0\u0010\u0007\u001a\u00020\b*\u00020\u00022!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\b0\nH\u0087\b\u001a\u0014\u0010\u000f\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0001H\u0007\u001a\u001e\u0010\u0011\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0013"},
   d2 = {"readBytes", "", "Landroid/util/AtomicFile;", "readText", "", "charset", "Ljava/nio/charset/Charset;", "tryWrite", "", "block", "Lkotlin/Function1;", "Ljava/io/FileOutputStream;", "Lkotlin/ParameterName;", "name", "out", "writeBytes", "array", "writeText", "text", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class AtomicFileKt {
   public static final byte[] readBytes(android.util.AtomicFile var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$readBytes");
      byte[] var1 = var0.readFully();
      Intrinsics.checkExpressionValueIsNotNull(var1, "readFully()");
      return var1;
   }

   public static final String readText(android.util.AtomicFile var0, Charset var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$readText");
      Intrinsics.checkParameterIsNotNull(var1, "charset");
      byte[] var2 = var0.readFully();
      Intrinsics.checkExpressionValueIsNotNull(var2, "readFully()");
      return new String(var2, var1);
   }

   // $FF: synthetic method
   public static String readText$default(android.util.AtomicFile var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      return readText(var0, var1);
   }

   public static final void tryWrite(android.util.AtomicFile var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$tryWrite");
      Intrinsics.checkParameterIsNotNull(var1, "block");
      FileOutputStream var2 = var0.startWrite();
      boolean var4 = false;

      try {
         var4 = true;
         Intrinsics.checkExpressionValueIsNotNull(var2, "stream");
         var1.invoke(var2);
         var4 = false;
      } finally {
         if (var4) {
            InlineMarker.finallyStart(1);
            var0.failWrite(var2);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      var0.finishWrite(var2);
      InlineMarker.finallyEnd(1);
   }

   public static final void writeBytes(android.util.AtomicFile var0, byte[] var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$writeBytes");
      Intrinsics.checkParameterIsNotNull(var1, "array");
      FileOutputStream var2 = var0.startWrite();
      boolean var4 = false;

      try {
         var4 = true;
         Intrinsics.checkExpressionValueIsNotNull(var2, "stream");
         var2.write(var1);
         var4 = false;
      } finally {
         if (var4) {
            var0.failWrite(var2);
         }
      }

      var0.finishWrite(var2);
   }

   public static final void writeText(android.util.AtomicFile var0, String var1, Charset var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$writeText");
      Intrinsics.checkParameterIsNotNull(var1, "text");
      Intrinsics.checkParameterIsNotNull(var2, "charset");
      byte[] var3 = var1.getBytes(var2);
      Intrinsics.checkExpressionValueIsNotNull(var3, "(this as java.lang.String).getBytes(charset)");
      writeBytes(var0, var3);
   }

   // $FF: synthetic method
   public static void writeText$default(android.util.AtomicFile var0, String var1, Charset var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      writeText(var0, var1, var2);
   }
}
