package androidx.lifecycle;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u0004H\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0007J\b\u0010\u0010\u001a\u00020\fH\u0007J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\tH\u0003J\b\u0010\u0012\u001a\u00020\fH\u0007J\b\u0010\u0013\u001a\u00020\fH\u0007J\b\u0010\u0014\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"},
   d2 = {"Landroidx/lifecycle/DispatchQueue;", "", "()V", "finished", "", "isDraining", "paused", "queue", "Ljava/util/Queue;", "Ljava/lang/Runnable;", "canRun", "dispatchAndEnqueue", "", "context", "Lkotlin/coroutines/CoroutineContext;", "runnable", "drainQueue", "enqueue", "finish", "pause", "resume", "lifecycle-runtime-ktx_release"},
   k = 1,
   mv = {1, 4, 1}
)
public final class DispatchQueue {
   private boolean finished;
   private boolean isDraining;
   private boolean paused = true;
   private final Queue queue = (Queue)(new ArrayDeque());

   private final void enqueue(Runnable var1) {
      if (this.queue.offer(var1)) {
         this.drainQueue();
      } else {
         throw (Throwable)(new IllegalStateException("cannot enqueue any more runnables".toString()));
      }
   }

   public final boolean canRun() {
      boolean var1;
      if (!this.finished && this.paused) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public final void dispatchAndEnqueue(CoroutineContext var1, Runnable var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "runnable");
      MainCoroutineDispatcher var3 = Dispatchers.getMain().getImmediate();
      if (!var3.isDispatchNeeded(var1) && !this.canRun()) {
         this.enqueue(var2);
      } else {
         var3.dispatch(var1, (Runnable)(new Runnable(this, var1, var2) {
            final CoroutineContext $context$inlined;
            final Runnable $runnable$inlined;
            final DispatchQueue this$0;

            {
               this.this$0 = var1;
               this.$context$inlined = var2;
               this.$runnable$inlined = var3;
            }

            public final void run() {
               this.this$0.enqueue(this.$runnable$inlined);
            }
         }));
      }

   }

   public final void drainQueue() {
      if (!this.isDraining) {
         label206: {
            Throwable var10000;
            label205: {
               boolean var10001;
               try {
                  this.isDraining = true;
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label205;
               }

               while(true) {
                  try {
                     if (!(((Collection)this.queue).isEmpty() ^ true) || !this.canRun()) {
                        break label206;
                     }
                  } catch (Throwable var20) {
                     var10000 = var20;
                     var10001 = false;
                     break;
                  }

                  Runnable var1;
                  try {
                     var1 = (Runnable)this.queue.poll();
                  } catch (Throwable var19) {
                     var10000 = var19;
                     var10001 = false;
                     break;
                  }

                  if (var1 != null) {
                     try {
                        var1.run();
                     } catch (Throwable var18) {
                        var10000 = var18;
                        var10001 = false;
                        break;
                     }
                  }
               }
            }

            Throwable var22 = var10000;
            this.isDraining = false;
            throw var22;
         }

         this.isDraining = false;
      }
   }

   public final void finish() {
      this.finished = true;
      this.drainQueue();
   }

   public final void pause() {
      this.paused = true;
   }

   public final void resume() {
      if (this.paused) {
         if (this.finished ^ true) {
            this.paused = false;
            this.drainQueue();
         } else {
            throw (Throwable)(new IllegalStateException("Cannot resume a finished dispatcher".toString()));
         }
      }
   }
}
