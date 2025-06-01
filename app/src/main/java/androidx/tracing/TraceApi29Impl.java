package androidx.tracing;

final class TraceApi29Impl {
   private TraceApi29Impl() {
   }

   public static void beginAsyncSection(String var0, int var1) {
      android.os.Trace.beginAsyncSection(var0, var1);
   }

   public static void endAsyncSection(String var0, int var1) {
      android.os.Trace.endAsyncSection(var0, var1);
   }

   public static void setCounter(String var0, int var1) {
      android.os.Trace.setCounter(var0, (long)var1);
   }
}
