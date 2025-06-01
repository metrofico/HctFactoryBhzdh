package kotlinx.coroutines.scheduling;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.TimeSourceKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0000\u0018\u0000 \\2\u00020`2\u00020a:\u0003\\]^B+\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0004H\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u001f\u001a\u00020\n2\n\u0010\u001a\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010 \u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b \u0010\u0011J\u0015\u0010\"\u001a\b\u0018\u00010!R\u00020\u0000H\u0002¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0013H\u0082\b¢\u0006\u0004\b$\u0010\u0015J\u0010\u0010%\u001a\u00020\u0001H\u0082\b¢\u0006\u0004\b%\u0010\u0017J-\u0010'\u001a\u00020\u00132\n\u0010\u001a\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010&\u001a\u00020\f¢\u0006\u0004\b'\u0010(J\u001b\u0010*\u001a\u00020\u00132\n\u0010)\u001a\u00060\u0018j\u0002`\u0019H\u0016¢\u0006\u0004\b*\u0010+J\u0010\u0010,\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u0001H\u0082\b¢\u0006\u0004\b.\u0010\u0017J\u001b\u00100\u001a\u00020\u00012\n\u0010/\u001a\u00060!R\u00020\u0000H\u0002¢\u0006\u0004\b0\u00101J\u0015\u00102\u001a\b\u0018\u00010!R\u00020\u0000H\u0002¢\u0006\u0004\b2\u0010#J\u001b\u00105\u001a\u00020\f2\n\u0010/\u001a\u00060!R\u00020\u0000H\u0000¢\u0006\u0004\b3\u00104J+\u0010:\u001a\u00020\u00132\n\u0010/\u001a\u00060!R\u00020\u00002\u0006\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u00020\u0001H\u0000¢\u0006\u0004\b8\u00109J\u0010\u0010;\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b;\u0010-J\u0015\u0010<\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b<\u0010=J\u0015\u0010?\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\u0004¢\u0006\u0004\b?\u0010@J\u0017\u0010B\u001a\u00020\u00132\u0006\u0010A\u001a\u00020\fH\u0002¢\u0006\u0004\bB\u0010CJ\u000f\u0010E\u001a\u00020\u0013H\u0000¢\u0006\u0004\bD\u0010\u0015J\u000f\u0010F\u001a\u00020\u0006H\u0016¢\u0006\u0004\bF\u0010GJ\u0010\u0010H\u001a\u00020\fH\u0082\b¢\u0006\u0004\bH\u0010IJ\u0019\u0010J\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u0004H\u0002¢\u0006\u0004\bJ\u0010KJ\u000f\u0010L\u001a\u00020\fH\u0002¢\u0006\u0004\bL\u0010IJ+\u0010M\u001a\u0004\u0018\u00010\n*\b\u0018\u00010!R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010&\u001a\u00020\fH\u0002¢\u0006\u0004\bM\u0010NR\u0017\u0010\u0010\u001a\u00020\u00018Â\u0002@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bO\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00018\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010PR\u0017\u0010 \u001a\u00020\u00018Â\u0002@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010\u0017R\u0016\u0010S\u001a\u00020R8\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010U\u001a\u00020R8\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\bU\u0010TR\u0016\u0010\u0005\u001a\u00020\u00048\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010VR\u0013\u0010W\u001a\u00020\f8F@\u0006¢\u0006\u0006\u001a\u0004\bW\u0010IR\u0016\u0010\u0003\u001a\u00020\u00018\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010PR\u0016\u0010\u0007\u001a\u00020\u00068\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010XR\"\u0010Z\u001a\u000e\u0012\n\u0012\b\u0018\u00010!R\u00020\u00000Y8\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\bZ\u0010[¨\u0006_"},
   d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "", "corePoolSize", "maxPoolSize", "", "idleWorkerKeepAliveNs", "", "schedulerName", "<init>", "(IIJLjava/lang/String;)V", "Lkotlinx/coroutines/scheduling/Task;", "task", "", "addToGlobalQueue", "(Lkotlinx/coroutines/scheduling/Task;)Z", "state", "availableCpuPermits", "(J)I", "blockingTasks", "", "close", "()V", "createNewWorker", "()I", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "Lkotlinx/coroutines/scheduling/TaskContext;", "taskContext", "createTask$kotlinx_coroutines_core", "(Ljava/lang/Runnable;Lkotlinx/coroutines/scheduling/TaskContext;)Lkotlinx/coroutines/scheduling/Task;", "createTask", "createdWorkers", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "currentWorker", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "decrementBlockingTasks", "decrementCreatedWorkers", "tailDispatch", "dispatch", "(Ljava/lang/Runnable;Lkotlinx/coroutines/scheduling/TaskContext;Z)V", "command", "execute", "(Ljava/lang/Runnable;)V", "incrementBlockingTasks", "()J", "incrementCreatedWorkers", "worker", "parkedWorkersStackNextIndex", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;)I", "parkedWorkersStackPop", "parkedWorkersStackPush$kotlinx_coroutines_core", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;)Z", "parkedWorkersStackPush", "oldIndex", "newIndex", "parkedWorkersStackTopUpdate$kotlinx_coroutines_core", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;II)V", "parkedWorkersStackTopUpdate", "releaseCpuPermit", "runSafely", "(Lkotlinx/coroutines/scheduling/Task;)V", "timeout", "shutdown", "(J)V", "skipUnpark", "signalBlockingWork", "(Z)V", "signalCpuWork$kotlinx_coroutines_core", "signalCpuWork", "toString", "()Ljava/lang/String;", "tryAcquireCpuPermit", "()Z", "tryCreateWorker", "(J)Z", "tryUnpark", "submitToLocalQueue", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;Lkotlinx/coroutines/scheduling/Task;Z)Lkotlinx/coroutines/scheduling/Task;", "getAvailableCpuPermits", "I", "getCreatedWorkers", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalBlockingQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalCpuQueue", "J", "isTerminated", "Ljava/lang/String;", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "workers", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "Companion", "Worker", "WorkerState", "kotlinx-coroutines-core", "Ljava/util/concurrent/Executor;", "Ljava/io/Closeable;"},
   k = 1,
   mv = {1, 4, 0}
)
public final class CoroutineScheduler implements Executor, Closeable {
   private static final long BLOCKING_MASK = 4398044413952L;
   private static final int BLOCKING_SHIFT = 21;
   private static final int CLAIMED = 0;
   private static final long CPU_PERMITS_MASK = 9223367638808264704L;
   private static final int CPU_PERMITS_SHIFT = 42;
   private static final long CREATED_MASK = 2097151L;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final int MAX_SUPPORTED_POOL_SIZE = 2097150;
   public static final int MIN_SUPPORTED_POOL_SIZE = 1;
   public static final Symbol NOT_IN_STACK = new Symbol("NOT_IN_STACK");
   private static final int PARKED = -1;
   private static final long PARKED_INDEX_MASK = 2097151L;
   private static final long PARKED_VERSION_INC = 2097152L;
   private static final long PARKED_VERSION_MASK = -2097152L;
   private static final int TERMINATED = 1;
   private static final AtomicIntegerFieldUpdater _isTerminated$FU = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");
   static final AtomicLongFieldUpdater controlState$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");
   private static final AtomicLongFieldUpdater parkedWorkersStack$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");
   private volatile int _isTerminated;
   volatile long controlState;
   public final int corePoolSize;
   public final GlobalQueue globalBlockingQueue;
   public final GlobalQueue globalCpuQueue;
   public final long idleWorkerKeepAliveNs;
   public final int maxPoolSize;
   private volatile long parkedWorkersStack;
   public final String schedulerName;
   public final AtomicReferenceArray workers;

   public CoroutineScheduler(int var1, int var2, long var3, String var5) {
      this.corePoolSize = var1;
      this.maxPoolSize = var2;
      this.idleWorkerKeepAliveNs = var3;
      this.schedulerName = var5;
      boolean var6;
      if (var1 >= 1) {
         var6 = true;
      } else {
         var6 = false;
      }

      if (var6) {
         if (var2 >= var1) {
            var6 = true;
         } else {
            var6 = false;
         }

         if (var6) {
            if (var2 <= 2097150) {
               var6 = true;
            } else {
               var6 = false;
            }

            if (var6) {
               if (var3 > 0L) {
                  var6 = true;
               } else {
                  var6 = false;
               }

               if (var6) {
                  this.globalCpuQueue = new GlobalQueue();
                  this.globalBlockingQueue = new GlobalQueue();
                  this.parkedWorkersStack = 0L;
                  this.workers = new AtomicReferenceArray(var2 + 1);
                  this.controlState = (long)var1 << 42;
                  this._isTerminated = 0;
               } else {
                  throw (Throwable)(new IllegalArgumentException(("Idle worker keep alive time " + var3 + " must be positive").toString()));
               }
            } else {
               throw (Throwable)(new IllegalArgumentException(("Max pool size " + var2 + " should not exceed maximal supported number of threads 2097150").toString()));
            }
         } else {
            throw (Throwable)(new IllegalArgumentException(("Max pool size " + var2 + " should be greater than or equals to core pool size " + var1).toString()));
         }
      } else {
         throw (Throwable)(new IllegalArgumentException(("Core pool size " + var1 + " should be at least 1").toString()));
      }
   }

   // $FF: synthetic method
   public CoroutineScheduler(int var1, int var2, long var3, String var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 4) != 0) {
         var3 = TasksKt.IDLE_WORKER_KEEP_ALIVE_NS;
      }

      if ((var6 & 8) != 0) {
         var5 = "DefaultDispatcher";
      }

      this(var1, var2, var3, var5);
   }

   // $FF: synthetic method
   public static final int access$createdWorkers(CoroutineScheduler var0, long var1) {
      return var0.createdWorkers(var1);
   }

   // $FF: synthetic method
   public static final void access$decrementBlockingTasks(CoroutineScheduler var0) {
      var0.decrementBlockingTasks();
   }

   // $FF: synthetic method
   public static final int access$decrementCreatedWorkers(CoroutineScheduler var0) {
      return var0.decrementCreatedWorkers();
   }

   // $FF: synthetic method
   public static final long access$releaseCpuPermit(CoroutineScheduler var0) {
      return var0.releaseCpuPermit();
   }

   // $FF: synthetic method
   public static final boolean access$tryAcquireCpuPermit(CoroutineScheduler var0) {
      return var0.tryAcquireCpuPermit();
   }

   private final boolean addToGlobalQueue(Task var1) {
      int var3 = var1.taskContext.getTaskMode();
      boolean var2 = true;
      if (var3 != 1) {
         var2 = false;
      }

      boolean var4;
      if (var2) {
         var4 = this.globalBlockingQueue.addLast(var1);
      } else {
         var4 = this.globalCpuQueue.addLast(var1);
      }

      return var4;
   }

   private final int blockingTasks(long var1) {
      return (int)((var1 & 4398044413952L) >> 21);
   }

   private final int createNewWorker() {
      AtomicReferenceArray var8 = this.workers;
      synchronized(var8){}

      Throwable var10000;
      label1187: {
         boolean var7;
         boolean var10001;
         try {
            var7 = this.isTerminated();
         } catch (Throwable var141) {
            var10000 = var141;
            var10001 = false;
            break label1187;
         }

         if (var7) {
            return -1;
         }

         long var5;
         try {
            var5 = this.controlState;
         } catch (Throwable var140) {
            var10000 = var140;
            var10001 = false;
            break label1187;
         }

         int var1 = (int)(var5 & 2097151L);
         int var3 = (int)((var5 & 4398044413952L) >> 21);
         boolean var2 = false;

         int var4;
         try {
            var3 = RangesKt.coerceAtLeast(var1 - var3, 0);
            var4 = this.corePoolSize;
         } catch (Throwable var139) {
            var10000 = var139;
            var10001 = false;
            break label1187;
         }

         if (var3 >= var4) {
            return 0;
         }

         try {
            var4 = this.maxPoolSize;
         } catch (Throwable var138) {
            var10000 = var138;
            var10001 = false;
            break label1187;
         }

         if (var1 >= var4) {
            return 0;
         }

         try {
            var4 = (int)(this.controlState & 2097151L) + 1;
         } catch (Throwable var137) {
            var10000 = var137;
            var10001 = false;
            break label1187;
         }

         boolean var142;
         label1160: {
            label1159: {
               if (var4 > 0) {
                  try {
                     if (this.workers.get(var4) == null) {
                        break label1159;
                     }
                  } catch (Throwable var136) {
                     var10000 = var136;
                     var10001 = false;
                     break label1187;
                  }
               }

               var142 = false;
               break label1160;
            }

            var142 = true;
         }

         IllegalArgumentException var143;
         if (var142) {
            label1188: {
               Worker var9;
               try {
                  var9 = new Worker(this, var4);
                  this.workers.set(var4, var9);
               } catch (Throwable var134) {
                  var10000 = var134;
                  var10001 = false;
                  break label1188;
               }

               var142 = var2;

               label1144: {
                  try {
                     if (var4 != (int)(2097151L & controlState$FU.incrementAndGet(this))) {
                        break label1144;
                     }
                  } catch (Throwable var133) {
                     var10000 = var133;
                     var10001 = false;
                     break label1188;
                  }

                  var142 = true;
               }

               if (var142) {
                  label1136: {
                     try {
                        var9.start();
                     } catch (Throwable var131) {
                        var10000 = var131;
                        var10001 = false;
                        break label1136;
                     }

                     return var3 + 1;
                  }
               } else {
                  label1138:
                  try {
                     var143 = new IllegalArgumentException("Failed requirement.".toString());
                     throw (Throwable)var143;
                  } catch (Throwable var132) {
                     var10000 = var132;
                     var10001 = false;
                     break label1138;
                  }
               }
            }
         } else {
            label1151:
            try {
               var143 = new IllegalArgumentException("Failed requirement.".toString());
               throw (Throwable)var143;
            } catch (Throwable var135) {
               var10000 = var135;
               var10001 = false;
               break label1151;
            }
         }
      }

      Throwable var144 = var10000;
      throw var144;
   }

   private final int createdWorkers(long var1) {
      return (int)(var1 & 2097151L);
   }

   private final Worker currentWorker() {
      Thread var2 = Thread.currentThread();
      boolean var1 = var2 instanceof Worker;
      Object var3 = null;
      if (!var1) {
         var2 = null;
      }

      Worker var4 = (Worker)var2;
      Worker var6 = (Worker)var3;
      if (var4 != null) {
         CoroutineScheduler var5 = var4.this$0;
         CoroutineScheduler var7 = (CoroutineScheduler)this;
         var6 = (Worker)var3;
         if (Intrinsics.areEqual((Object)var5, (Object)this)) {
            var6 = var4;
         }
      }

      return var6;
   }

   private final void decrementBlockingTasks() {
      controlState$FU.addAndGet(this, -2097152L);
   }

   private final int decrementCreatedWorkers() {
      return (int)(controlState$FU.getAndDecrement(this) & 2097151L);
   }

   // $FF: synthetic method
   public static void dispatch$default(CoroutineScheduler var0, Runnable var1, TaskContext var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = (TaskContext)NonBlockingContext.INSTANCE;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      var0.dispatch(var1, var2, var3);
   }

   private final int getAvailableCpuPermits() {
      return (int)((this.controlState & 9223367638808264704L) >> 42);
   }

   private final int getCreatedWorkers() {
      return (int)(this.controlState & 2097151L);
   }

   private final long incrementBlockingTasks() {
      return controlState$FU.addAndGet(this, 2097152L);
   }

   private final int incrementCreatedWorkers() {
      return (int)(controlState$FU.incrementAndGet(this) & 2097151L);
   }

   private final int parkedWorkersStackNextIndex(Worker var1) {
      for(Object var3 = var1.getNextParkedWorker(); var3 != NOT_IN_STACK; var3 = var1.getNextParkedWorker()) {
         if (var3 == null) {
            return 0;
         }

         var1 = (Worker)var3;
         int var2 = var1.getIndexInArray();
         if (var2 != 0) {
            return var2;
         }
      }

      return -1;
   }

   private final Worker parkedWorkersStackPop() {
      while(true) {
         long var2 = this.parkedWorkersStack;
         int var1 = (int)(2097151L & var2);
         Worker var4 = (Worker)this.workers.get(var1);
         if (var4 != null) {
            var1 = this.parkedWorkersStackNextIndex(var4);
            if (var1 < 0 || !parkedWorkersStack$FU.compareAndSet(this, var2, (long)var1 | 2097152L + var2 & -2097152L)) {
               continue;
            }

            var4.setNextParkedWorker(NOT_IN_STACK);
            return var4;
         }

         return null;
      }
   }

   private final long releaseCpuPermit() {
      return controlState$FU.addAndGet(this, 4398046511104L);
   }

   private final void signalBlockingWork(boolean var1) {
      long var2 = controlState$FU.addAndGet(this, 2097152L);
      if (!var1) {
         if (!this.tryUnpark()) {
            if (!this.tryCreateWorker(var2)) {
               this.tryUnpark();
            }
         }
      }
   }

   private final Task submitToLocalQueue(Worker var1, Task var2, boolean var3) {
      if (var1 == null) {
         return var2;
      } else if (var1.state == WorkerState.TERMINATED) {
         return var2;
      } else if (var2.taskContext.getTaskMode() == 0 && var1.state == WorkerState.BLOCKING) {
         return var2;
      } else {
         var1.mayHaveLocalTasks = true;
         return var1.localQueue.add(var2, var3);
      }
   }

   private final boolean tryAcquireCpuPermit() {
      long var1;
      do {
         var1 = this.controlState;
         if ((int)((9223367638808264704L & var1) >> 42) == 0) {
            return false;
         }
      } while(!controlState$FU.compareAndSet(this, var1, var1 - 4398046511104L));

      return true;
   }

   private final boolean tryCreateWorker(long var1) {
      if (RangesKt.coerceAtLeast((int)(2097151L & var1) - (int)((var1 & 4398044413952L) >> 21), 0) < this.corePoolSize) {
         int var3 = this.createNewWorker();
         if (var3 == 1 && this.corePoolSize > 1) {
            this.createNewWorker();
         }

         if (var3 > 0) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static boolean tryCreateWorker$default(CoroutineScheduler var0, long var1, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.controlState;
      }

      return var0.tryCreateWorker(var1);
   }

   private final boolean tryUnpark() {
      while(true) {
         Worker var1 = this.parkedWorkersStackPop();
         if (var1 != null) {
            if (!Worker.workerCtl$FU.compareAndSet(var1, -1, 0)) {
               continue;
            }

            LockSupport.unpark((Thread)var1);
            return true;
         }

         return false;
      }
   }

   public final int availableCpuPermits(long var1) {
      return (int)((var1 & 9223367638808264704L) >> 42);
   }

   public void close() {
      this.shutdown(10000L);
   }

   public final Task createTask$kotlinx_coroutines_core(Runnable var1, TaskContext var2) {
      long var3 = TasksKt.schedulerTimeSource.nanoTime();
      if (var1 instanceof Task) {
         Task var5 = (Task)var1;
         var5.submissionTime = var3;
         var5.taskContext = var2;
         return var5;
      } else {
         return (Task)(new TaskImpl(var1, var3, var2));
      }
   }

   public final void dispatch(Runnable var1, TaskContext var2, boolean var3) {
      kotlinx.coroutines.TimeSource var4 = TimeSourceKt.getTimeSource();
      if (var4 != null) {
         var4.trackTask();
      }

      Task var5 = this.createTask$kotlinx_coroutines_core(var1, var2);
      Worker var6 = this.currentWorker();
      Task var7 = this.submitToLocalQueue(var6, var5, var3);
      if (var7 != null && !this.addToGlobalQueue(var7)) {
         throw (Throwable)(new RejectedExecutionException(this.schedulerName + " was terminated"));
      } else {
         if (var3 && var6 != null) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var5.taskContext.getTaskMode() == 0) {
            if (var3) {
               return;
            }

            this.signalCpuWork$kotlinx_coroutines_core();
         } else {
            this.signalBlockingWork(var3);
         }

      }
   }

   public void execute(Runnable var1) {
      dispatch$default(this, var1, (TaskContext)null, false, 6, (Object)null);
   }

   public final boolean isTerminated() {
      return (boolean)this._isTerminated;
   }

   public final boolean parkedWorkersStackPush$kotlinx_coroutines_core(Worker var1) {
      if (var1.getNextParkedWorker() != NOT_IN_STACK) {
         return false;
      } else {
         int var4;
         long var5;
         do {
            var5 = this.parkedWorkersStack;
            int var3 = (int)(2097151L & var5);
            var4 = var1.getIndexInArray();
            if (DebugKt.getASSERTIONS_ENABLED()) {
               boolean var2;
               if (var4 != 0) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (!var2) {
                  throw (Throwable)(new AssertionError());
               }
            }

            var1.setNextParkedWorker(this.workers.get(var3));
         } while(!parkedWorkersStack$FU.compareAndSet(this, var5, (long)var4 | 2097152L + var5 & -2097152L));

         return true;
      }
   }

   public final void parkedWorkersStackTopUpdate$kotlinx_coroutines_core(Worker var1, int var2, int var3) {
      int var4;
      long var6;
      do {
         var6 = this.parkedWorkersStack;
         int var5 = (int)(2097151L & var6);
         var4 = var5;
         if (var5 == var2) {
            if (var3 == 0) {
               var4 = this.parkedWorkersStackNextIndex(var1);
            } else {
               var4 = var3;
            }
         }
      } while(var4 < 0 || !parkedWorkersStack$FU.compareAndSet(this, var6, 2097152L + var6 & -2097152L | (long)var4));

   }

   public final void runSafely(Task param1) {
      // $FF: Couldn't be decompiled
   }

   public final void shutdown(long var1) {
      AtomicIntegerFieldUpdater var9 = _isTerminated$FU;
      boolean var5 = false;
      if (var9.compareAndSet(this, 0, 1)) {
         Worker var10 = this.currentWorker();
         AtomicReferenceArray var16 = this.workers;
         synchronized(var16){}
         boolean var13 = false;

         long var7;
         try {
            var13 = true;
            var7 = this.controlState;
            var13 = false;
         } finally {
            if (var13) {
               ;
            }
         }

         int var6 = (int)(var7 & 2097151L);
         if (1 <= var6) {
            int var3 = 1;

            while(true) {
               Object var17 = this.workers.get(var3);
               Intrinsics.checkNotNull(var17);
               Worker var18 = (Worker)var17;
               if (var18 != var10) {
                  while(var18.isAlive()) {
                     LockSupport.unpark((Thread)var18);
                     var18.join(var1);
                  }

                  WorkerState var11 = var18.state;
                  if (DebugKt.getASSERTIONS_ENABLED()) {
                     boolean var4;
                     if (var11 == WorkerState.TERMINATED) {
                        var4 = true;
                     } else {
                        var4 = false;
                     }

                     if (!var4) {
                        throw (Throwable)(new AssertionError());
                     }
                  }

                  var18.localQueue.offloadAllWorkTo(this.globalBlockingQueue);
               }

               if (var3 == var6) {
                  break;
               }

               ++var3;
            }
         }

         this.globalBlockingQueue.close();
         this.globalCpuQueue.close();

         while(true) {
            Task var19;
            label153: {
               if (var10 != null) {
                  var19 = var10.findTask(true);
                  if (var19 != null) {
                     break label153;
                  }
               }

               var19 = (Task)this.globalCpuQueue.removeFirstOrNull();
            }

            if (var19 == null) {
               var19 = (Task)this.globalBlockingQueue.removeFirstOrNull();
            }

            if (var19 == null) {
               if (var10 != null) {
                  var10.tryReleaseCpu$kotlinx_coroutines_core(WorkerState.TERMINATED);
               }

               if (DebugKt.getASSERTIONS_ENABLED()) {
                  boolean var15 = var5;
                  if ((int)((this.controlState & 9223367638808264704L) >> 42) == this.corePoolSize) {
                     var15 = true;
                  }

                  if (!var15) {
                     throw (Throwable)(new AssertionError());
                  }
               }

               this.parkedWorkersStack = 0L;
               this.controlState = 0L;
               return;
            }

            this.runSafely(var19);
         }
      }
   }

   public final void signalCpuWork$kotlinx_coroutines_core() {
      if (!this.tryUnpark()) {
         if (!tryCreateWorker$default(this, 0L, 1, (Object)null)) {
            this.tryUnpark();
         }
      }
   }

   public String toString() {
      ArrayList var16 = new ArrayList();
      int var12 = this.workers.length();
      int var6 = 0;
      int var5 = 0;
      int var4 = 0;
      int var1 = var4;
      int var3 = var4;

      int var9;
      for(int var2 = 1; var2 < var12; var3 = var9) {
         Worker var17 = (Worker)this.workers.get(var2);
         int var7 = var6;
         int var10 = var5;
         int var11 = var4;
         int var8 = var1;
         var9 = var3;
         if (var17 != null) {
            int var13 = var17.localQueue.getSize$kotlinx_coroutines_core();
            WorkerState var18 = var17.state;
            var7 = CoroutineScheduler$WhenMappings.$EnumSwitchMapping$0[var18.ordinal()];
            if (var7 != 1) {
               if (var7 != 2) {
                  if (var7 != 3) {
                     if (var7 != 4) {
                        if (var7 != 5) {
                           var7 = var6;
                           var10 = var5;
                           var11 = var4;
                           var8 = var1;
                           var9 = var3;
                        } else {
                           var9 = var3 + 1;
                           var7 = var6;
                           var10 = var5;
                           var11 = var4;
                           var8 = var1;
                        }
                     } else {
                        ++var1;
                        var7 = var6;
                        var10 = var5;
                        var11 = var4;
                        var8 = var1;
                        var9 = var3;
                        if (var13 > 0) {
                           ((Collection)var16).add(var13 + "d");
                           var7 = var6;
                           var10 = var5;
                           var11 = var4;
                           var8 = var1;
                           var9 = var3;
                        }
                     }
                  } else {
                     var7 = var6 + 1;
                     ((Collection)var16).add(var13 + "c");
                     var10 = var5;
                     var11 = var4;
                     var8 = var1;
                     var9 = var3;
                  }
               } else {
                  var10 = var5 + 1;
                  ((Collection)var16).add(var13 + "b");
                  var7 = var6;
                  var11 = var4;
                  var8 = var1;
                  var9 = var3;
               }
            } else {
               var11 = var4 + 1;
               var9 = var3;
               var8 = var1;
               var10 = var5;
               var7 = var6;
            }
         }

         ++var2;
         var6 = var7;
         var5 = var10;
         var4 = var11;
         var1 = var8;
      }

      long var14 = this.controlState;
      return this.schedulerName + '@' + DebugStringsKt.getHexAddress(this) + '[' + "Pool Size {" + "core = " + this.corePoolSize + ", " + "max = " + this.maxPoolSize + "}, " + "Worker States {" + "CPU = " + var6 + ", " + "blocking = " + var5 + ", " + "parked = " + var4 + ", " + "dormant = " + var1 + ", " + "terminated = " + var3 + "}, " + "running workers queues = " + var16 + ", " + "global CPU queue size = " + this.globalCpuQueue.getSize() + ", " + "global blocking queue size = " + this.globalBlockingQueue.getSize() + ", " + "Control State {" + "created workers= " + (int)(2097151L & var14) + ", " + "blocking tasks = " + (int)((4398044413952L & var14) >> 21) + ", " + "CPUs acquired = " + (this.corePoolSize - (int)((9223367638808264704L & var14) >> 42)) + "}]";
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"},
      d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Companion;", "", "()V", "BLOCKING_MASK", "", "BLOCKING_SHIFT", "", "CLAIMED", "CPU_PERMITS_MASK", "CPU_PERMITS_SHIFT", "CREATED_MASK", "MAX_SUPPORTED_POOL_SIZE", "MIN_SUPPORTED_POOL_SIZE", "NOT_IN_STACK", "Lkotlinx/coroutines/internal/Symbol;", "PARKED", "PARKED_INDEX_MASK", "PARKED_VERSION_INC", "PARKED_VERSION_MASK", "TERMINATED", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\b\u0080\u0004\u0018\u00002\u00020IB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\n\u0010\tJ\u0017\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0013\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0015\u0010\tJ\u000f\u0010\u0016\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001e\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0007H\u0016¢\u0006\u0004\b \u0010\u001dJ\u000f\u0010!\u001a\u00020\u0007H\u0002¢\u0006\u0004\b!\u0010\u001dJ\u000f\u0010\"\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\"\u0010\u0017J\u000f\u0010#\u001a\u00020\u0007H\u0002¢\u0006\u0004\b#\u0010\u001dJ\u0017\u0010(\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020$H\u0000¢\u0006\u0004\b&\u0010'J\u0019\u0010*\u001a\u0004\u0018\u00010\u000b2\u0006\u0010)\u001a\u00020\u000fH\u0002¢\u0006\u0004\b*\u0010\u0012J\u000f\u0010+\u001a\u00020\u0007H\u0002¢\u0006\u0004\b+\u0010\u001dR*\u0010,\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00018\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u0010\tR\u0016\u00102\u001a\u0002018\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b4\u00105R\u0016\u00107\u001a\u0002068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108R$\u0010:\u001a\u0004\u0018\u0001098\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010-R\u0014\u0010D\u001a\u00020A8Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0016\u0010E\u001a\u00020$8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u0002068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u00108¨\u0006H"},
      d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "", "index", "<init>", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;I)V", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;)V", "taskMode", "", "afterTask", "(I)V", "beforeTask", "Lkotlinx/coroutines/scheduling/Task;", "task", "executeTask", "(Lkotlinx/coroutines/scheduling/Task;)V", "", "scanLocalQueue", "findAnyTask", "(Z)Lkotlinx/coroutines/scheduling/Task;", "findTask", "mode", "idleReset", "inStack", "()Z", "upperBound", "nextInt$kotlinx_coroutines_core", "(I)I", "nextInt", "park", "()V", "pollGlobalQueues", "()Lkotlinx/coroutines/scheduling/Task;", "run", "runWorker", "tryAcquireCpuPermit", "tryPark", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "newState", "tryReleaseCpu$kotlinx_coroutines_core", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;)Z", "tryReleaseCpu", "blockingOnly", "trySteal", "tryTerminateWorker", "indexInArray", "I", "getIndexInArray", "()I", "setIndexInArray", "Lkotlinx/coroutines/scheduling/WorkQueue;", "localQueue", "Lkotlinx/coroutines/scheduling/WorkQueue;", "mayHaveLocalTasks", "Z", "", "minDelayUntilStealableTaskNs", "J", "", "nextParkedWorker", "Ljava/lang/Object;", "getNextParkedWorker", "()Ljava/lang/Object;", "setNextParkedWorker", "(Ljava/lang/Object;)V", "rngState", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "getScheduler", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "scheduler", "state", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "terminationDeadline", "kotlinx-coroutines-core", "Ljava/lang/Thread;"},
      k = 1,
      mv = {1, 4, 0}
   )
   public final class Worker extends Thread {
      static final AtomicIntegerFieldUpdater workerCtl$FU = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "workerCtl");
      private volatile int indexInArray;
      public final WorkQueue localQueue;
      public boolean mayHaveLocalTasks;
      private long minDelayUntilStealableTaskNs;
      private volatile Object nextParkedWorker;
      private int rngState;
      public WorkerState state;
      private long terminationDeadline;
      final CoroutineScheduler this$0;
      volatile int workerCtl;

      private Worker(CoroutineScheduler var1) {
         this.this$0 = var1;
         this.setDaemon(true);
         this.localQueue = new WorkQueue();
         this.state = WorkerState.DORMANT;
         this.workerCtl = 0;
         this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
         this.rngState = Random.Default.nextInt();
      }

      public Worker(CoroutineScheduler var1, int var2) {
         this(var1);
         this.setIndexInArray(var2);
      }

      private final void afterTask(int var1) {
         if (var1 != 0) {
            CoroutineScheduler var2 = this.this$0;
            CoroutineScheduler.controlState$FU.addAndGet(var2, -2097152L);
            WorkerState var4 = this.state;
            if (var4 != WorkerState.TERMINATED) {
               if (DebugKt.getASSERTIONS_ENABLED()) {
                  boolean var3;
                  if (var4 == WorkerState.BLOCKING) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  if (!var3) {
                     throw (Throwable)(new AssertionError());
                  }
               }

               this.state = WorkerState.DORMANT;
            }

         }
      }

      private final void beforeTask(int var1) {
         if (var1 != 0) {
            if (this.tryReleaseCpu$kotlinx_coroutines_core(WorkerState.BLOCKING)) {
               this.this$0.signalCpuWork$kotlinx_coroutines_core();
            }

         }
      }

      private final void executeTask(Task var1) {
         int var2 = var1.taskContext.getTaskMode();
         this.idleReset(var2);
         this.beforeTask(var2);
         this.this$0.runSafely(var1);
         this.afterTask(var2);
      }

      private final Task findAnyTask(boolean var1) {
         Task var3;
         if (var1) {
            boolean var2;
            if (this.nextInt$kotlinx_coroutines_core(this.this$0.corePoolSize * 2) == 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               var3 = this.pollGlobalQueues();
               if (var3 != null) {
                  return var3;
               }
            }

            var3 = this.localQueue.poll();
            if (var3 != null) {
               return var3;
            }

            if (!var2) {
               var3 = this.pollGlobalQueues();
               if (var3 != null) {
                  return var3;
               }
            }
         } else {
            var3 = this.pollGlobalQueues();
            if (var3 != null) {
               return var3;
            }
         }

         return this.trySteal(false);
      }

      private final void idleReset(int var1) {
         this.terminationDeadline = 0L;
         if (this.state == WorkerState.PARKING) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
               boolean var2 = true;
               boolean var3;
               if (var1 == 1) {
                  var3 = var2;
               } else {
                  var3 = false;
               }

               if (!var3) {
                  throw (Throwable)(new AssertionError());
               }
            }

            this.state = WorkerState.BLOCKING;
         }

      }

      private final boolean inStack() {
         boolean var1;
         if (this.nextParkedWorker != CoroutineScheduler.NOT_IN_STACK) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      private final void park() {
         if (this.terminationDeadline == 0L) {
            this.terminationDeadline = System.nanoTime() + this.this$0.idleWorkerKeepAliveNs;
         }

         LockSupport.parkNanos(this.this$0.idleWorkerKeepAliveNs);
         if (System.nanoTime() - this.terminationDeadline >= 0L) {
            this.terminationDeadline = 0L;
            this.tryTerminateWorker();
         }

      }

      private final Task pollGlobalQueues() {
         Task var1;
         if (this.nextInt$kotlinx_coroutines_core(2) == 0) {
            var1 = (Task)this.this$0.globalCpuQueue.removeFirstOrNull();
            return var1 != null ? var1 : (Task)this.this$0.globalBlockingQueue.removeFirstOrNull();
         } else {
            var1 = (Task)this.this$0.globalBlockingQueue.removeFirstOrNull();
            return var1 != null ? var1 : (Task)this.this$0.globalCpuQueue.removeFirstOrNull();
         }
      }

      private final void runWorker() {
         label28:
         while(true) {
            boolean var1 = false;

            while(!this.this$0.isTerminated() && this.state != WorkerState.TERMINATED) {
               Task var2 = this.findTask(this.mayHaveLocalTasks);
               if (var2 != null) {
                  this.minDelayUntilStealableTaskNs = 0L;
                  this.executeTask(var2);
                  continue label28;
               }

               this.mayHaveLocalTasks = false;
               if (this.minDelayUntilStealableTaskNs != 0L) {
                  if (var1) {
                     this.tryReleaseCpu$kotlinx_coroutines_core(WorkerState.PARKING);
                     Thread.interrupted();
                     LockSupport.parkNanos(this.minDelayUntilStealableTaskNs);
                     this.minDelayUntilStealableTaskNs = 0L;
                     continue label28;
                  }

                  var1 = true;
               } else {
                  this.tryPark();
               }
            }

            this.tryReleaseCpu$kotlinx_coroutines_core(WorkerState.TERMINATED);
            return;
         }
      }

      private final boolean tryAcquireCpuPermit() {
         WorkerState var5 = this.state;
         WorkerState var6 = WorkerState.CPU_ACQUIRED;
         boolean var4 = false;
         if (var5 != var6) {
            CoroutineScheduler var7 = this.this$0;

            boolean var1;
            while(true) {
               long var2 = var7.controlState;
               if ((int)((9223367638808264704L & var2) >> 42) == 0) {
                  var1 = false;
                  break;
               }

               if (CoroutineScheduler.controlState$FU.compareAndSet(var7, var2, var2 - 4398046511104L)) {
                  var1 = true;
                  break;
               }
            }

            if (!var1) {
               return var4;
            }

            this.state = WorkerState.CPU_ACQUIRED;
         }

         var4 = true;
         return var4;
      }

      private final void tryPark() {
         if (!this.inStack()) {
            this.this$0.parkedWorkersStackPush$kotlinx_coroutines_core(this);
         } else {
            if (DebugKt.getASSERTIONS_ENABLED()) {
               boolean var1;
               if (this.localQueue.getSize$kotlinx_coroutines_core() == 0) {
                  var1 = true;
               } else {
                  var1 = false;
               }

               if (!var1) {
                  throw (Throwable)(new AssertionError());
               }
            }

            this.workerCtl = -1;

            while(this.inStack() && !this.this$0.isTerminated() && this.state != WorkerState.TERMINATED) {
               this.tryReleaseCpu$kotlinx_coroutines_core(WorkerState.PARKING);
               Thread.interrupted();
               this.park();
            }

         }
      }

      private final Task trySteal(boolean var1) {
         if (DebugKt.getASSERTIONS_ENABLED()) {
            boolean var2;
            if (this.localQueue.getSize$kotlinx_coroutines_core() == 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (!var2) {
               throw (Throwable)(new AssertionError());
            }
         }

         int var5 = this.this$0.getCreatedWorkers();
         if (var5 < 2) {
            return null;
         } else {
            int var14 = this.nextInt$kotlinx_coroutines_core(var5);
            int var3 = 0;

            long var6;
            long var8;
            for(var6 = Long.MAX_VALUE; var3 < var5; var6 = var8) {
               int var4 = var14 + 1;
               var14 = var4;
               if (var4 > var5) {
                  var14 = 1;
               }

               Worker var13 = (Worker)this.this$0.workers.get(var14);
               var8 = var6;
               if (var13 != null) {
                  Worker var12 = (Worker)this;
                  var8 = var6;
                  if (var13 != this) {
                     if (DebugKt.getASSERTIONS_ENABLED()) {
                        boolean var15;
                        if (this.localQueue.getSize$kotlinx_coroutines_core() == 0) {
                           var15 = true;
                        } else {
                           var15 = false;
                        }

                        if (!var15) {
                           throw (Throwable)(new AssertionError());
                        }
                     }

                     long var10;
                     if (var1) {
                        var10 = this.localQueue.tryStealBlockingFrom(var13.localQueue);
                     } else {
                        var10 = this.localQueue.tryStealFrom(var13.localQueue);
                     }

                     if (var10 == -1L) {
                        return this.localQueue.poll();
                     }

                     var8 = var6;
                     if (var10 > 0L) {
                        var8 = Math.min(var6, var10);
                     }
                  }
               }

               ++var3;
            }

            if (var6 == Long.MAX_VALUE) {
               var6 = 0L;
            }

            this.minDelayUntilStealableTaskNs = var6;
            return null;
         }
      }

      private final void tryTerminateWorker() {
         AtomicReferenceArray var4 = this.this$0.workers;
         synchronized(var4){}

         Throwable var10000;
         label392: {
            boolean var3;
            boolean var10001;
            try {
               var3 = this.this$0.isTerminated();
            } catch (Throwable var47) {
               var10000 = var47;
               var10001 = false;
               break label392;
            }

            if (var3) {
               return;
            }

            int var1;
            int var2;
            try {
               var2 = this.this$0.getCreatedWorkers();
               var1 = this.this$0.corePoolSize;
            } catch (Throwable var46) {
               var10000 = var46;
               var10001 = false;
               break label392;
            }

            if (var2 <= var1) {
               return;
            }

            try {
               var3 = workerCtl$FU.compareAndSet(this, -1, 1);
            } catch (Throwable var45) {
               var10000 = var45;
               var10001 = false;
               break label392;
            }

            if (!var3) {
               return;
            }

            try {
               var2 = this.indexInArray;
               this.setIndexInArray(0);
               this.this$0.parkedWorkersStackTopUpdate$kotlinx_coroutines_core(this, var2, 0);
               CoroutineScheduler var5 = this.this$0;
               var1 = (int)(CoroutineScheduler.controlState$FU.getAndDecrement(var5) & 2097151L);
            } catch (Throwable var44) {
               var10000 = var44;
               var10001 = false;
               break label392;
            }

            if (var1 != var2) {
               try {
                  Object var48 = this.this$0.workers.get(var1);
                  Intrinsics.checkNotNull(var48);
                  Worker var49 = (Worker)var48;
                  this.this$0.workers.set(var2, var49);
                  var49.setIndexInArray(var2);
                  this.this$0.parkedWorkersStackTopUpdate$kotlinx_coroutines_core(var49, var1, var2);
               } catch (Throwable var43) {
                  var10000 = var43;
                  var10001 = false;
                  break label392;
               }
            }

            try {
               this.this$0.workers.set(var1, (Object)null);
               Unit var51 = Unit.INSTANCE;
            } catch (Throwable var42) {
               var10000 = var42;
               var10001 = false;
               break label392;
            }

            this.state = WorkerState.TERMINATED;
            return;
         }

         Throwable var50 = var10000;
         throw var50;
      }

      public final Task findTask(boolean var1) {
         if (this.tryAcquireCpuPermit()) {
            return this.findAnyTask(var1);
         } else {
            Task var2;
            if (var1) {
               var2 = this.localQueue.poll();
               if (var2 == null) {
                  var2 = (Task)this.this$0.globalBlockingQueue.removeFirstOrNull();
               }
            } else {
               var2 = (Task)this.this$0.globalBlockingQueue.removeFirstOrNull();
            }

            if (var2 == null) {
               var2 = this.trySteal(true);
            }

            return var2;
         }
      }

      public final int getIndexInArray() {
         return this.indexInArray;
      }

      public final Object getNextParkedWorker() {
         return this.nextParkedWorker;
      }

      public final CoroutineScheduler getScheduler() {
         return this.this$0;
      }

      public final int nextInt$kotlinx_coroutines_core(int var1) {
         int var2 = this.rngState;
         var2 ^= var2 << 13;
         var2 ^= var2 >> 17;
         var2 ^= var2 << 5;
         this.rngState = var2;
         int var3 = var1 - 1;
         return (var3 & var1) == 0 ? var2 & var3 : (var2 & Integer.MAX_VALUE) % var1;
      }

      public void run() {
         this.runWorker();
      }

      public final void setIndexInArray(int var1) {
         StringBuilder var3 = (new StringBuilder()).append(this.this$0.schedulerName).append("-worker-");
         String var2;
         if (var1 == 0) {
            var2 = "TERMINATED";
         } else {
            var2 = String.valueOf(var1);
         }

         this.setName(var3.append(var2).toString());
         this.indexInArray = var1;
      }

      public final void setNextParkedWorker(Object var1) {
         this.nextParkedWorker = var1;
      }

      public final boolean tryReleaseCpu$kotlinx_coroutines_core(WorkerState var1) {
         WorkerState var4 = this.state;
         boolean var2;
         if (var4 == WorkerState.CPU_ACQUIRED) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            CoroutineScheduler var3 = this.this$0;
            CoroutineScheduler.controlState$FU.addAndGet(var3, 4398046511104L);
         }

         if (var4 != var1) {
            this.state = var1;
         }

         return var2;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"},
      d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "", "(Ljava/lang/String;I)V", "CPU_ACQUIRED", "BLOCKING", "PARKING", "DORMANT", "TERMINATED", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static enum WorkerState {
      private static final WorkerState[] $VALUES;
      BLOCKING,
      CPU_ACQUIRED,
      DORMANT,
      PARKING,
      TERMINATED;

      static {
         WorkerState var4 = new WorkerState("CPU_ACQUIRED", 0);
         CPU_ACQUIRED = var4;
         WorkerState var0 = new WorkerState("BLOCKING", 1);
         BLOCKING = var0;
         WorkerState var1 = new WorkerState("PARKING", 2);
         PARKING = var1;
         WorkerState var3 = new WorkerState("DORMANT", 3);
         DORMANT = var3;
         WorkerState var2 = new WorkerState("TERMINATED", 4);
         TERMINATED = var2;
         $VALUES = new WorkerState[]{var4, var0, var1, var3, var2};
      }
   }
}
