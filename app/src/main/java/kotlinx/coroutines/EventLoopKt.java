package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0007¨\u0006\u0004"},
   d2 = {"createEventLoop", "Lkotlinx/coroutines/EventLoop;", "processNextEventInCurrentThread", "", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class EventLoopKt {
   public static final EventLoop createEventLoop() {
      return (EventLoop)(new BlockingEventLoop(Thread.currentThread()));
   }

   public static final long processNextEventInCurrentThread() {
      EventLoop var2 = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
      long var0;
      if (var2 != null) {
         var0 = var2.processNextEvent();
      } else {
         var0 = Long.MAX_VALUE;
      }

      return var0;
   }
}
