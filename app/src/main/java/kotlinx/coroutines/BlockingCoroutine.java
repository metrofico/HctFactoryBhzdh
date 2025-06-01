package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u000b\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\f¨\u0006\u0013"},
   d2 = {"Lkotlinx/coroutines/BlockingCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "blockedThread", "Ljava/lang/Thread;", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Thread;Lkotlinx/coroutines/EventLoop;)V", "isScopedCoroutine", "", "()Z", "afterCompletion", "", "state", "", "joinBlocking", "()Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class BlockingCoroutine extends AbstractCoroutine {
   private final Thread blockedThread;
   private final EventLoop eventLoop;

   public BlockingCoroutine(CoroutineContext var1, Thread var2, EventLoop var3) {
      super(var1, true);
      this.blockedThread = var2;
      this.eventLoop = var3;
   }

   protected void afterCompletion(Object var1) {
      if (Intrinsics.areEqual((Object)Thread.currentThread(), (Object)this.blockedThread) ^ true) {
         LockSupport.unpark(this.blockedThread);
      }

   }

   protected boolean isScopedCoroutine() {
      return true;
   }

   public final Object joinBlocking() {
      TimeSource var4 = TimeSourceKt.getTimeSource();
      if (var4 != null) {
         var4.registerTimeLoopThread();
      }

      Throwable var10000;
      TimeSource var220;
      label1930: {
         EventLoop var5;
         boolean var10001;
         try {
            var5 = this.eventLoop;
         } catch (Throwable var215) {
            var10000 = var215;
            var10001 = false;
            break label1930;
         }

         Object var216 = null;
         if (var5 != null) {
            try {
               EventLoop.incrementUseCount$default(var5, false, 1, (Object)null);
            } catch (Throwable var208) {
               var10000 = var208;
               var10001 = false;
               break label1930;
            }
         }

         while(true) {
            label1918: {
               try {
                  if (!Thread.interrupted()) {
                     var5 = this.eventLoop;
                     break label1918;
                  }
               } catch (Throwable var214) {
                  var10000 = var214;
                  var10001 = false;
                  break;
               }

               try {
                  InterruptedException var217 = new InterruptedException();
                  this.cancelCoroutine((Throwable)var217);
                  throw (Throwable)var217;
               } catch (Throwable var207) {
                  var10000 = var207;
                  var10001 = false;
                  break;
               }
            }

            long var1;
            if (var5 != null) {
               try {
                  var1 = var5.processNextEvent();
               } catch (Throwable var213) {
                  var10000 = var213;
                  var10001 = false;
                  break;
               }
            } else {
               var1 = Long.MAX_VALUE;
            }

            boolean var3;
            try {
               var3 = this.isCompleted();
            } catch (Throwable var212) {
               var10000 = var212;
               var10001 = false;
               break;
            }

            if (var3) {
               try {
                  var5 = this.eventLoop;
               } catch (Throwable var206) {
                  var10000 = var206;
                  var10001 = false;
                  break label1930;
               }

               if (var5 != null) {
                  try {
                     EventLoop.decrementUseCount$default(var5, false, 1, (Object)null);
                  } catch (Throwable var205) {
                     var10000 = var205;
                     var10001 = false;
                     break label1930;
                  }
               }

               var220 = TimeSourceKt.getTimeSource();
               if (var220 != null) {
                  var220.unregisterTimeLoopThread();
               }

               Object var223 = JobSupportKt.unboxState(this.getState$kotlinx_coroutines_core());
               if (var223 instanceof CompletedExceptionally) {
                  var216 = var223;
               }

               CompletedExceptionally var222 = (CompletedExceptionally)var216;
               if (var222 == null) {
                  return var223;
               }

               throw var222.cause;
            }

            try {
               var220 = TimeSourceKt.getTimeSource();
            } catch (Throwable var211) {
               var10000 = var211;
               var10001 = false;
               break;
            }

            if (var220 != null) {
               try {
                  var220.parkNanos(this, var1);
               } catch (Throwable var209) {
                  var10000 = var209;
                  var10001 = false;
                  break;
               }
            } else {
               try {
                  LockSupport.parkNanos(this, var1);
               } catch (Throwable var210) {
                  var10000 = var210;
                  var10001 = false;
                  break;
               }
            }
         }

         Throwable var221 = var10000;

         EventLoop var218;
         try {
            var218 = this.eventLoop;
         } catch (Throwable var204) {
            var10000 = var204;
            var10001 = false;
            break label1930;
         }

         if (var218 != null) {
            try {
               EventLoop.decrementUseCount$default(var218, false, 1, (Object)null);
            } catch (Throwable var203) {
               var10000 = var203;
               var10001 = false;
               break label1930;
            }
         }

         label1870:
         try {
            throw var221;
         } catch (Throwable var202) {
            var10000 = var202;
            var10001 = false;
            break label1870;
         }
      }

      Throwable var219 = var10000;
      var220 = TimeSourceKt.getTimeSource();
      if (var220 != null) {
         var220.unregisterTimeLoopThread();
      }

      throw var219;
   }
}
