package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00004\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\r\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0007\u001a\r\u0010\u0010\u001a\u00020\u000e*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0010\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0087\b\u001a\u0015\u0010\u0010\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\u0007\"!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0005\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"},
   d2 = {"stackTrace", "", "Ljava/lang/StackTraceElement;", "", "getStackTrace$annotations", "(Ljava/lang/Throwable;)V", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "suppressedExceptions", "", "getSuppressedExceptions$annotations", "getSuppressedExceptions", "(Ljava/lang/Throwable;)Ljava/util/List;", "addSuppressed", "", "exception", "printStackTrace", "stream", "Ljava/io/PrintStream;", "writer", "Ljava/io/PrintWriter;", "stackTraceToString", "", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/ExceptionsKt"
)
class ExceptionsKt__ExceptionsKt {
   public ExceptionsKt__ExceptionsKt() {
   }

   public static final void addSuppressed(Throwable var0, Throwable var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "exception");
      if (var0 != var1) {
         PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(var0, var1);
      }

   }

   public static final StackTraceElement[] getStackTrace(Throwable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      StackTraceElement[] var1 = var0.getStackTrace();
      Intrinsics.checkNotNull(var1);
      return var1;
   }

   // $FF: synthetic method
   public static void getStackTrace$annotations(Throwable var0) {
   }

   public static final List getSuppressedExceptions(Throwable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return PlatformImplementationsKt.IMPLEMENTATIONS.getSuppressed(var0);
   }

   // $FF: synthetic method
   public static void getSuppressedExceptions$annotations(Throwable var0) {
   }

   private static final void printStackTrace(Throwable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0.printStackTrace();
   }

   private static final void printStackTrace(Throwable var0, PrintStream var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "stream");
      var0.printStackTrace(var1);
   }

   private static final void printStackTrace(Throwable var0, PrintWriter var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "writer");
      var0.printStackTrace(var1);
   }

   public static final String stackTraceToString(Throwable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      StringWriter var1 = new StringWriter();
      PrintWriter var2 = new PrintWriter((Writer)var1);
      var0.printStackTrace(var2);
      var2.flush();
      String var3 = var1.toString();
      Intrinsics.checkNotNullExpressionValue(var3, "sw.toString()");
      return var3;
   }
}
