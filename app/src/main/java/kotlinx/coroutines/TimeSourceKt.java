package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\t\u0010\u0006\u001a\u00020\u0007H\u0081\b\u001a\t\u0010\b\u001a\u00020\u0007H\u0081\b\u001a\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0081\b\u001a\t\u0010\u000e\u001a\u00020\nH\u0081\b\u001a\t\u0010\u000f\u001a\u00020\nH\u0081\b\u001a\t\u0010\u0010\u001a\u00020\nH\u0081\b\u001a\u0011\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0081\b\u001a\t\u0010\u0014\u001a\u00020\nH\u0081\b\u001a\u0019\u0010\u0015\u001a\u00060\u0016j\u0002`\u00172\n\u0010\u0018\u001a\u00060\u0016j\u0002`\u0017H\u0081\b\"\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u0019"},
   d2 = {"timeSource", "Lkotlinx/coroutines/TimeSource;", "getTimeSource", "()Lkotlinx/coroutines/TimeSource;", "setTimeSource", "(Lkotlinx/coroutines/TimeSource;)V", "currentTimeMillis", "", "nanoTime", "parkNanos", "", "blocker", "", "nanos", "registerTimeLoopThread", "trackTask", "unTrackTask", "unpark", "thread", "Ljava/lang/Thread;", "unregisterTimeLoopThread", "wrapTask", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class TimeSourceKt {
   private static TimeSource timeSource;

   private static final long currentTimeMillis() {
      TimeSource var2 = getTimeSource();
      long var0;
      if (var2 != null) {
         var0 = var2.currentTimeMillis();
      } else {
         var0 = System.currentTimeMillis();
      }

      return var0;
   }

   public static final TimeSource getTimeSource() {
      return timeSource;
   }

   private static final long nanoTime() {
      TimeSource var2 = getTimeSource();
      long var0;
      if (var2 != null) {
         var0 = var2.nanoTime();
      } else {
         var0 = System.nanoTime();
      }

      return var0;
   }

   private static final void parkNanos(Object var0, long var1) {
      TimeSource var3 = getTimeSource();
      if (var3 != null) {
         var3.parkNanos(var0, var1);
      } else {
         LockSupport.parkNanos(var0, var1);
      }

   }

   private static final void registerTimeLoopThread() {
      TimeSource var0 = getTimeSource();
      if (var0 != null) {
         var0.registerTimeLoopThread();
      }

   }

   public static final void setTimeSource(TimeSource var0) {
      timeSource = var0;
   }

   private static final void trackTask() {
      TimeSource var0 = getTimeSource();
      if (var0 != null) {
         var0.trackTask();
      }

   }

   private static final void unTrackTask() {
      TimeSource var0 = getTimeSource();
      if (var0 != null) {
         var0.unTrackTask();
      }

   }

   private static final void unpark(Thread var0) {
      TimeSource var1 = getTimeSource();
      if (var1 != null) {
         var1.unpark(var0);
      } else {
         LockSupport.unpark(var0);
      }

   }

   private static final void unregisterTimeLoopThread() {
      TimeSource var0 = getTimeSource();
      if (var0 != null) {
         var0.unregisterTimeLoopThread();
      }

   }

   private static final Runnable wrapTask(Runnable var0) {
      TimeSource var2 = getTimeSource();
      Runnable var1 = var0;
      if (var2 != null) {
         Runnable var3 = var2.wrapTask(var0);
         var1 = var0;
         if (var3 != null) {
            var1 = var3;
         }
      }

      return var1;
   }
}
