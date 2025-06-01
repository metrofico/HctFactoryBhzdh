package androidx.tracing;

final class TraceApi18Impl {
   private TraceApi18Impl() {
   }

   public static void beginSection(String var0) {
      android.os.Trace.beginSection(var0);
   }

   public static void endSection() {
      android.os.Trace.endSection();
   }
}
