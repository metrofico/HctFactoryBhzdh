package kotlinx.coroutines;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.ranges.RangesKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0010H\u0002J\r\u0010\u001e\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001fJ$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\b2\n\u0010#\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u0014H\u0002J\b\u0010'\u001a\u00020\u001cH\u0016J\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0004R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00148@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u00108TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006*"},
   d2 = {"Lkotlinx/coroutines/DefaultExecutor;", "Lkotlinx/coroutines/EventLoopImplBase;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "()V", "ACTIVE", "", "DEFAULT_KEEP_ALIVE", "", "FRESH", "KEEP_ALIVE_NANOS", "SHUTDOWN_ACK", "SHUTDOWN_REQ", "THREAD_NAME", "", "_thread", "Ljava/lang/Thread;", "get_thread$annotations", "debugStatus", "isShutdownRequested", "", "()Z", "isThreadPresent", "isThreadPresent$kotlinx_coroutines_core", "thread", "getThread", "()Ljava/lang/Thread;", "acknowledgeShutdownIfNeeded", "", "createThreadSync", "ensureStarted", "ensureStarted$kotlinx_coroutines_core", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", "context", "Lkotlin/coroutines/CoroutineContext;", "notifyStartup", "run", "shutdown", "timeout", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class DefaultExecutor extends EventLoopImplBase implements Runnable {
   private static final int ACTIVE = 1;
   private static final long DEFAULT_KEEP_ALIVE = 1000L;
   private static final int FRESH = 0;
   public static final DefaultExecutor INSTANCE;
   private static final long KEEP_ALIVE_NANOS;
   private static final int SHUTDOWN_ACK = 3;
   private static final int SHUTDOWN_REQ = 2;
   public static final String THREAD_NAME = "kotlinx.coroutines.DefaultExecutor";
   private static volatile Thread _thread;
   private static volatile int debugStatus;

   static {
      DefaultExecutor var0 = new DefaultExecutor();
      INSTANCE = var0;
      EventLoop.incrementUseCount$default(var0, false, 1, (Object)null);
      TimeUnit var1 = TimeUnit.MILLISECONDS;

      Long var3;
      try {
         var3 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
      } catch (SecurityException var2) {
         var3 = 1000L;
      }

      KEEP_ALIVE_NANOS = var1.toNanos(var3);
   }

   private DefaultExecutor() {
   }

   private final void acknowledgeShutdownIfNeeded() {
      synchronized(this){}

      Throwable var10000;
      label78: {
         boolean var1;
         boolean var10001;
         try {
            var1 = this.isShutdownRequested();
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label78;
         }

         if (!var1) {
            return;
         }

         try {
            debugStatus = 3;
            this.resetAll();
            ((Object)this).notifyAll();
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         return;
      }

      Throwable var2 = var10000;
      throw var2;
   }

   private final Thread createThreadSync() {
      synchronized(this){}

      Throwable var10000;
      label76: {
         Thread var1;
         boolean var10001;
         try {
            var1 = _thread;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label76;
         }

         if (var1 != null) {
            return var1;
         }

         label67:
         try {
            var1 = new Thread((Runnable)this, "kotlinx.coroutines.DefaultExecutor");
            _thread = var1;
            var1.setDaemon(true);
            var1.start();
            return var1;
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label67;
         }
      }

      Throwable var8 = var10000;
      throw var8;
   }

   // $FF: synthetic method
   private static void get_thread$annotations() {
   }

   private final boolean isShutdownRequested() {
      int var1 = debugStatus;
      boolean var2;
      if (var1 != 2 && var1 != 3) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private final boolean notifyStartup() {
      synchronized(this){}

      Throwable var10000;
      label78: {
         boolean var1;
         boolean var10001;
         try {
            var1 = this.isShutdownRequested();
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label78;
         }

         if (var1) {
            return false;
         }

         try {
            debugStatus = 1;
            ((Object)this).notifyAll();
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         return true;
      }

      Throwable var2 = var10000;
      throw var2;
   }

   public final void ensureStarted$kotlinx_coroutines_core() {
      synchronized(this){}

      Throwable var10000;
      label784: {
         boolean var3;
         boolean var10001;
         try {
            var3 = DebugKt.getASSERTIONS_ENABLED();
         } catch (Throwable var76) {
            var10000 = var76;
            var10001 = false;
            break label784;
         }

         boolean var2 = true;
         boolean var1;
         AssertionError var4;
         if (var3) {
            label774: {
               label773: {
                  try {
                     if (_thread == null) {
                        break label773;
                     }
                  } catch (Throwable var75) {
                     var10000 = var75;
                     var10001 = false;
                     break label784;
                  }

                  var1 = false;
                  break label774;
               }

               var1 = true;
            }

            if (!var1) {
               try {
                  var4 = new AssertionError();
                  throw (Throwable)var4;
               } catch (Throwable var69) {
                  var10000 = var69;
                  var10001 = false;
                  break label784;
               }
            }
         }

         label785: {
            try {
               if (!DebugKt.getASSERTIONS_ENABLED()) {
                  break label785;
               }
            } catch (Throwable var74) {
               var10000 = var74;
               var10001 = false;
               break label784;
            }

            var1 = var2;

            label759: {
               label758: {
                  try {
                     if (debugStatus == 0) {
                        break label759;
                     }

                     if (debugStatus == 3) {
                        break label758;
                     }
                  } catch (Throwable var73) {
                     var10000 = var73;
                     var10001 = false;
                     break label784;
                  }

                  var1 = false;
                  break label759;
               }

               var1 = var2;
            }

            if (!var1) {
               try {
                  var4 = new AssertionError();
                  throw (Throwable)var4;
               } catch (Throwable var70) {
                  var10000 = var70;
                  var10001 = false;
                  break label784;
               }
            }
         }

         try {
            debugStatus = 0;
            this.createThreadSync();
         } catch (Throwable var72) {
            var10000 = var72;
            var10001 = false;
            break label784;
         }

         while(true) {
            try {
               if (debugStatus == 0) {
                  ((Object)this).wait();
                  continue;
               }
            } catch (Throwable var71) {
               var10000 = var71;
               var10001 = false;
               break;
            }

            return;
         }
      }

      Throwable var77 = var10000;
      throw var77;
   }

   protected Thread getThread() {
      Thread var1 = _thread;
      if (var1 == null) {
         var1 = this.createThreadSync();
      }

      return var1;
   }

   public DisposableHandle invokeOnTimeout(long var1, Runnable var3, CoroutineContext var4) {
      return this.scheduleInvokeOnTimeout(var1, var3);
   }

   public final boolean isThreadPresent$kotlinx_coroutines_core() {
      boolean var1;
      if (_thread != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void run() {
      ThreadLocalEventLoop.INSTANCE.setEventLoop$kotlinx_coroutines_core((EventLoop)this);
      TimeSource var10 = TimeSourceKt.getTimeSource();
      if (var10 != null) {
         var10.registerTimeLoopThread();
      }

      Throwable var10000;
      label1521: {
         boolean var1;
         boolean var10001;
         try {
            var1 = this.notifyStartup();
         } catch (Throwable var143) {
            var10000 = var143;
            var10001 = false;
            break label1521;
         }

         Thread var144;
         if (!var1) {
            var144 = (Thread)null;
            _thread = null;
            this.acknowledgeShutdownIfNeeded();
            var10 = TimeSourceKt.getTimeSource();
            if (var10 != null) {
               var10.unregisterTimeLoopThread();
            }

            if (!this.isEmpty()) {
               this.getThread();
            }

            return;
         }

         long var2 = Long.MAX_VALUE;

         while(true) {
            long var8;
            try {
               Thread.interrupted();
               var8 = this.processNextEvent();
            } catch (Throwable var142) {
               var10000 = var142;
               var10001 = false;
               break;
            }

            long var4;
            long var6;
            if (var8 == Long.MAX_VALUE) {
               try {
                  var10 = TimeSourceKt.getTimeSource();
               } catch (Throwable var141) {
                  var10000 = var141;
                  var10001 = false;
                  break;
               }

               if (var10 != null) {
                  try {
                     var6 = var10.nanoTime();
                  } catch (Throwable var140) {
                     var10000 = var140;
                     var10001 = false;
                     break;
                  }
               } else {
                  try {
                     var6 = System.nanoTime();
                  } catch (Throwable var139) {
                     var10000 = var139;
                     var10001 = false;
                     break;
                  }
               }

               var4 = var2;
               if (var2 == Long.MAX_VALUE) {
                  try {
                     var2 = KEEP_ALIVE_NANOS;
                  } catch (Throwable var138) {
                     var10000 = var138;
                     var10001 = false;
                     break;
                  }

                  var4 = var2 + var6;
               }

               var2 = var4 - var6;
               if (var2 <= 0L) {
                  var144 = (Thread)null;
                  _thread = null;
                  this.acknowledgeShutdownIfNeeded();
                  var10 = TimeSourceKt.getTimeSource();
                  if (var10 != null) {
                     var10.unregisterTimeLoopThread();
                  }

                  if (!this.isEmpty()) {
                     this.getThread();
                  }

                  return;
               }

               try {
                  var6 = RangesKt.coerceAtMost(var8, var2);
               } catch (Throwable var137) {
                  var10000 = var137;
                  var10001 = false;
                  break;
               }
            } else {
               var4 = Long.MAX_VALUE;
               var6 = var8;
            }

            var2 = var4;
            if (var6 > 0L) {
               try {
                  var1 = this.isShutdownRequested();
               } catch (Throwable var136) {
                  var10000 = var136;
                  var10001 = false;
                  break;
               }

               if (var1) {
                  var144 = (Thread)null;
                  _thread = null;
                  this.acknowledgeShutdownIfNeeded();
                  var10 = TimeSourceKt.getTimeSource();
                  if (var10 != null) {
                     var10.unregisterTimeLoopThread();
                  }

                  if (!this.isEmpty()) {
                     this.getThread();
                  }

                  return;
               }

               try {
                  var10 = TimeSourceKt.getTimeSource();
               } catch (Throwable var135) {
                  var10000 = var135;
                  var10001 = false;
                  break;
               }

               if (var10 != null) {
                  try {
                     var10.parkNanos(this, var6);
                  } catch (Throwable var133) {
                     var10000 = var133;
                     var10001 = false;
                     break;
                  }

                  var2 = var4;
               } else {
                  try {
                     LockSupport.parkNanos(this, var6);
                  } catch (Throwable var134) {
                     var10000 = var134;
                     var10001 = false;
                     break;
                  }

                  var2 = var4;
               }
            }
         }
      }

      Throwable var146 = var10000;
      Thread var11 = (Thread)null;
      _thread = null;
      this.acknowledgeShutdownIfNeeded();
      TimeSource var145 = TimeSourceKt.getTimeSource();
      if (var145 != null) {
         var145.unregisterTimeLoopThread();
      }

      if (!this.isEmpty()) {
         this.getThread();
      }

      throw var146;
   }

   public final void shutdown(long var1) {
      synchronized(this){}

      Throwable var10000;
      label636: {
         long var3;
         boolean var10001;
         try {
            var3 = System.currentTimeMillis();
            if (!this.isShutdownRequested()) {
               debugStatus = 2;
            }
         } catch (Throwable var72) {
            var10000 = var72;
            var10001 = false;
            break label636;
         }

         while(true) {
            Thread var5;
            try {
               if (debugStatus == 3 || _thread == null) {
                  break;
               }

               var5 = _thread;
            } catch (Throwable var77) {
               var10000 = var77;
               var10001 = false;
               break label636;
            }

            if (var5 != null) {
               TimeSource var6;
               try {
                  var6 = TimeSourceKt.getTimeSource();
               } catch (Throwable var76) {
                  var10000 = var76;
                  var10001 = false;
                  break label636;
               }

               if (var6 != null) {
                  try {
                     var6.unpark(var5);
                  } catch (Throwable var75) {
                     var10000 = var75;
                     var10001 = false;
                     break label636;
                  }
               } else {
                  try {
                     LockSupport.unpark(var5);
                  } catch (Throwable var74) {
                     var10000 = var74;
                     var10001 = false;
                     break label636;
                  }
               }
            }

            label625:
            try {
               if (var3 + var1 - System.currentTimeMillis() > 0L) {
                  break label625;
               }
               break;
            } catch (Throwable var78) {
               var10000 = var78;
               var10001 = false;
               break label636;
            }

            try {
               ((Object)this).wait(var1);
            } catch (Throwable var73) {
               var10000 = var73;
               var10001 = false;
               break label636;
            }
         }

         label602:
         try {
            debugStatus = 0;
            return;
         } catch (Throwable var71) {
            var10000 = var71;
            var10001 = false;
            break label602;
         }
      }

      Throwable var79 = var10000;
      throw var79;
   }
}
