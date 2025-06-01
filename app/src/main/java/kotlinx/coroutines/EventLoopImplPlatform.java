package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0004J\b\u0010\r\u001a\u00020\bH\u0004R\u0012\u0010\u0003\u001a\u00020\u0004X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"},
   d2 = {"Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/EventLoop;", "()V", "thread", "Ljava/lang/Thread;", "getThread", "()Ljava/lang/Thread;", "reschedule", "", "now", "", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "unpark", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class EventLoopImplPlatform extends EventLoop {
   protected abstract Thread getThread();

   protected final void reschedule(long var1, EventLoopImplBase.DelayedTask var3) {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         EventLoopImplPlatform var5 = (EventLoopImplPlatform)this;
         boolean var4;
         if (this != DefaultExecutor.INSTANCE) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (!var4) {
            throw (Throwable)(new AssertionError());
         }
      }

      DefaultExecutor.INSTANCE.schedule(var1, var3);
   }

   protected final void unpark() {
      Thread var1 = this.getThread();
      if (Thread.currentThread() != var1) {
         TimeSource var2 = TimeSourceKt.getTimeSource();
         if (var2 != null) {
            var2.unpark(var1);
         } else {
            LockSupport.unpark(var1);
         }
      }

   }
}
