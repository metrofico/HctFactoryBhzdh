package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0002\u0018\u00002#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00050\u001bj\u0002`\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0096\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u0007R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0002\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0015R\u001e\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00160\u00168\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"},
   d2 = {"Lkotlinx/coroutines/ThreadState;", "Lkotlinx/coroutines/Job;", "job", "<init>", "(Lkotlinx/coroutines/Job;)V", "", "clearInterrupt", "()V", "", "state", "", "invalidState", "(I)Ljava/lang/Void;", "", "cause", "invoke", "(Ljava/lang/Throwable;)V", "setup", "Lkotlinx/coroutines/DisposableHandle;", "cancelHandle", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/Job;", "Ljava/lang/Thread;", "kotlin.jvm.PlatformType", "targetThread", "Ljava/lang/Thread;", "kotlinx-coroutines-core", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;"},
   k = 1,
   mv = {1, 4, 0}
)
final class ThreadState implements Function1 {
   private static final AtomicIntegerFieldUpdater _state$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadState.class, "_state");
   private volatile int _state;
   private DisposableHandle cancelHandle;
   private final Job job;
   private final Thread targetThread;

   public ThreadState(Job var1) {
      this.job = var1;
      this._state = 0;
      this.targetThread = Thread.currentThread();
   }

   private final Void invalidState(int var1) {
      throw (Throwable)(new IllegalStateException(("Illegal state " + var1).toString()));
   }

   public final void clearInterrupt() {
      while(true) {
         int var1 = this._state;
         if (var1 != 0) {
            if (var1 != 2) {
               if (var1 == 3) {
                  Thread.interrupted();
                  return;
               }

               this.invalidState(var1);
               throw new KotlinNothingValueException();
            }
         } else if (_state$FU.compareAndSet(this, var1, 1)) {
            DisposableHandle var2 = this.cancelHandle;
            if (var2 != null) {
               var2.dispose();
            }

            return;
         }
      }
   }

   public void invoke(Throwable var1) {
      int var2;
      do {
         var2 = this._state;
         if (var2 != 0) {
            if (var2 != 1 && var2 != 2 && var2 != 3) {
               this.invalidState(var2);
               throw new KotlinNothingValueException();
            }

            return;
         }
      } while(!_state$FU.compareAndSet(this, var2, 2));

      this.targetThread.interrupt();
      this._state = 3;
   }

   public final void setup() {
      this.cancelHandle = this.job.invokeOnCompletion(true, true, (Function1)this);

      int var1;
      do {
         var1 = this._state;
         if (var1 != 0) {
            if (var1 != 2 && var1 != 3) {
               this.invalidState(var1);
               throw new KotlinNothingValueException();
            } else {
               return;
            }
         }
      } while(!_state$FU.compareAndSet(this, var1, 0));

   }
}
