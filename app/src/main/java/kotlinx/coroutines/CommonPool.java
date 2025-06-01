package kotlinx.coroutines;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0011\u001a\u0004\u0018\u0001H\u0012\"\u0004\b\u0000\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0014H\u0082\b¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u001c\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\n\u0010\u0013\u001a\u00060\u001ej\u0002`\u001fH\u0016J\b\u0010 \u001a\u00020\u0006H\u0002J!\u0010!\u001a\u00020\u00102\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#2\u0006\u0010\u0005\u001a\u00020\u0019H\u0000¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\u0017H\u0000¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)H\u0000¢\u0006\u0002\b*J\b\u0010+\u001a\u00020\u0004H\u0016J\r\u0010\u000f\u001a\u00020\u0017H\u0000¢\u0006\u0002\b,R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"},
   d2 = {"Lkotlinx/coroutines/CommonPool;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "()V", "DEFAULT_PARALLELISM_PROPERTY_NAME", "", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "parallelism", "", "getParallelism", "()I", "pool", "requestedParallelism", "usePrivatePool", "", "Try", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "close", "", "createPlainPool", "Ljava/util/concurrent/ExecutorService;", "createPool", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "getOrCreatePoolSync", "isGoodCommonPool", "fjpClass", "Ljava/lang/Class;", "isGoodCommonPool$kotlinx_coroutines_core", "restore", "restore$kotlinx_coroutines_core", "shutdown", "timeout", "", "shutdown$kotlinx_coroutines_core", "toString", "usePrivatePool$kotlinx_coroutines_core", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class CommonPool extends ExecutorCoroutineDispatcher {
   public static final String DEFAULT_PARALLELISM_PROPERTY_NAME = "kotlinx.coroutines.default.parallelism";
   public static final CommonPool INSTANCE = new CommonPool();
   private static volatile Executor pool;
   private static final int requestedParallelism;
   private static boolean usePrivatePool;

   static {
      String var1;
      try {
         var1 = System.getProperty("kotlinx.coroutines.default.parallelism");
      } finally {
         ;
      }

      int var0;
      if (var1 != null) {
         Integer var2 = StringsKt.toIntOrNull(var1);
         if (var2 == null || var2 < 1) {
            throw (Throwable)(new IllegalStateException(("Expected positive number in kotlinx.coroutines.default.parallelism, but has " + var1).toString()));
         }

         var0 = var2;
      } else {
         var0 = -1;
      }

      requestedParallelism = var0;
   }

   private CommonPool() {
   }

   private final Object Try(Function0 var1) {
      Object var4;
      try {
         var4 = var1.invoke();
      } finally {
         ;
      }

      return var4;
   }

   private final ExecutorService createPlainPool() {
      AtomicInteger var1 = new AtomicInteger();
      return Executors.newFixedThreadPool(this.getParallelism(), (ThreadFactory)(new ThreadFactory(var1) {
         final AtomicInteger $threadId;

         {
            this.$threadId = var1;
         }

         public final Thread newThread(Runnable var1) {
            Thread var2 = new Thread(var1, "CommonPool-worker-" + this.$threadId.incrementAndGet());
            var2.setDaemon(true);
            return var2;
         }
      }));
   }

   private final ExecutorService createPool() {
      if (System.getSecurityManager() != null) {
         return this.createPlainPool();
      } else {
         Object var4 = null;

         Class var2;
         try {
            var2 = Class.forName("java.util.concurrent.ForkJoinPool");
         } finally {
            ;
         }

         if (var2 == null) {
            return this.createPlainPool();
         } else {
            boolean var10001;
            Object var77;
            ExecutorService var79;
            if (!usePrivatePool && requestedParallelism < 0) {
               label866: {
                  label875: {
                     Method var1;
                     try {
                        var1 = var2.getMethod("commonPool");
                     } catch (Throwable var76) {
                        var10001 = false;
                        break label875;
                     }

                     if (var1 != null) {
                        try {
                           var77 = var1.invoke((Object)null);
                        } catch (Throwable var75) {
                           var10001 = false;
                           break label875;
                        }
                     } else {
                        var77 = null;
                     }

                     Object var3 = var77;

                     label856: {
                        try {
                           if (var77 instanceof ExecutorService) {
                              break label856;
                           }
                        } catch (Throwable var74) {
                           var10001 = false;
                           break label875;
                        }

                        var3 = null;
                     }

                     label851:
                     try {
                        var79 = (ExecutorService)var3;
                        break label866;
                     } catch (Throwable var73) {
                        var10001 = false;
                        break label851;
                     }
                  }

                  var79 = null;
               }

               if (var79 != null) {
                  if (!INSTANCE.isGoodCommonPool$kotlinx_coroutines_core(var2, var79)) {
                     var79 = null;
                  }

                  if (var79 != null) {
                     return var79;
                  }
               }
            }

            label876: {
               Object var78;
               try {
                  var78 = var2.getConstructor(Integer.TYPE).newInstance(INSTANCE.getParallelism());
               } catch (Throwable var72) {
                  var10001 = false;
                  break label876;
               }

               var77 = var78;

               label841: {
                  try {
                     if (var78 instanceof ExecutorService) {
                        break label841;
                     }
                  } catch (Throwable var71) {
                     var10001 = false;
                     break label876;
                  }

                  var77 = null;
               }

               label836:
               try {
                  var79 = (ExecutorService)var77;
                  return var79 != null ? var79 : this.createPlainPool();
               } catch (Throwable var70) {
                  var10001 = false;
                  break label836;
               }
            }

            var79 = (ExecutorService)var4;
            return var79 != null ? var79 : this.createPlainPool();
         }
      }
   }

   private final Executor getOrCreatePoolSync() {
      synchronized(this){}

      Throwable var10000;
      label76: {
         Executor var1;
         boolean var10001;
         try {
            var1 = pool;
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
            ExecutorService var9 = this.createPool();
            pool = (Executor)var9;
            var1 = (Executor)var9;
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

   private final int getParallelism() {
      Integer var2 = requestedParallelism;
      boolean var1;
      if (((Number)var2).intValue() > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (!var1) {
         var2 = null;
      }

      int var3;
      if (var2 != null) {
         var3 = var2;
      } else {
         var3 = RangesKt.coerceAtLeast(Runtime.getRuntime().availableProcessors() - 1, 1);
      }

      return var3;
   }

   public void close() {
      throw (Throwable)(new IllegalStateException("Close cannot be invoked on CommonPool".toString()));
   }

   public void dispatch(CoroutineContext var1, Runnable var2) {
      label57: {
         boolean var10001;
         Executor var9;
         try {
            var9 = pool;
         } catch (RejectedExecutionException var8) {
            var10001 = false;
            break label57;
         }

         if (var9 == null) {
            try {
               var9 = this.getOrCreatePoolSync();
            } catch (RejectedExecutionException var7) {
               var10001 = false;
               break label57;
            }
         }

         TimeSource var3;
         try {
            var3 = TimeSourceKt.getTimeSource();
         } catch (RejectedExecutionException var6) {
            var10001 = false;
            break label57;
         }

         Runnable var11;
         label42: {
            if (var3 != null) {
               try {
                  var11 = var3.wrapTask(var2);
               } catch (RejectedExecutionException var5) {
                  var10001 = false;
                  break label57;
               }

               if (var11 != null) {
                  break label42;
               }
            }

            var11 = var2;
         }

         try {
            var9.execute(var11);
            return;
         } catch (RejectedExecutionException var4) {
            var10001 = false;
         }
      }

      TimeSource var10 = TimeSourceKt.getTimeSource();
      if (var10 != null) {
         var10.unTrackTask();
      }

      DefaultExecutor.INSTANCE.enqueue(var2);
   }

   public Executor getExecutor() {
      Executor var1 = pool;
      if (var1 == null) {
         var1 = this.getOrCreatePoolSync();
      }

      return var1;
   }

   public final boolean isGoodCommonPool$kotlinx_coroutines_core(Class var1, ExecutorService var2) {
      var2.submit((Runnable)null.INSTANCE);
      Object var5 = null;
      boolean var4 = false;

      Integer var19;
      label152: {
         label156: {
            boolean var10001;
            Object var20;
            try {
               var20 = var1.getMethod("getPoolSize").invoke(var2);
            } catch (Throwable var17) {
               var10001 = false;
               break label156;
            }

            Object var18 = var20;

            label146: {
               try {
                  if (var20 instanceof Integer) {
                     break label146;
                  }
               } catch (Throwable var16) {
                  var10001 = false;
                  break label156;
               }

               var18 = null;
            }

            label141:
            try {
               var19 = (Integer)var18;
               break label152;
            } catch (Throwable var15) {
               var10001 = false;
               break label141;
            }
         }

         var19 = (Integer)var5;
      }

      boolean var3 = var4;
      if (var19 != null) {
         var3 = var4;
         if (var19 >= 1) {
            var3 = true;
         }
      }

      return var3;
   }

   public final void restore$kotlinx_coroutines_core() {
      synchronized(this){}

      try {
         this.shutdown$kotlinx_coroutines_core(0L);
         usePrivatePool = false;
         Executor var1 = (Executor)null;
         pool = null;
      } finally {
         ;
      }

   }

   public final void shutdown$kotlinx_coroutines_core(long var1) {
      synchronized(this){}

      Throwable var10000;
      label602: {
         Executor var4;
         boolean var10001;
         try {
            var4 = pool;
         } catch (Throwable var76) {
            var10000 = var76;
            var10001 = false;
            break label602;
         }

         Executor var3 = var4;

         label592: {
            try {
               if (var4 instanceof ExecutorService) {
                  break label592;
               }
            } catch (Throwable var75) {
               var10000 = var75;
               var10001 = false;
               break label602;
            }

            var3 = null;
         }

         ExecutorService var77;
         try {
            var77 = (ExecutorService)var3;
         } catch (Throwable var74) {
            var10000 = var74;
            var10001 = false;
            break label602;
         }

         if (var77 != null) {
            try {
               var77.shutdown();
            } catch (Throwable var72) {
               var10000 = var72;
               var10001 = false;
               break label602;
            }

            if (var1 > 0L) {
               try {
                  var77.awaitTermination(var1, TimeUnit.MILLISECONDS);
               } catch (Throwable var71) {
                  var10000 = var71;
                  var10001 = false;
                  break label602;
               }
            }

            Iterator var78;
            try {
               var78 = ((Iterable)var77.shutdownNow()).iterator();
            } catch (Throwable var70) {
               var10000 = var70;
               var10001 = false;
               break label602;
            }

            while(true) {
               try {
                  if (!var78.hasNext()) {
                     break;
                  }

                  Runnable var79 = (Runnable)var78.next();
                  DefaultExecutor.INSTANCE.enqueue(var79);
               } catch (Throwable var73) {
                  var10000 = var73;
                  var10001 = false;
                  break label602;
               }
            }
         }

         label568:
         try {
            pool = (Executor)null.INSTANCE;
            return;
         } catch (Throwable var69) {
            var10000 = var69;
            var10001 = false;
            break label568;
         }
      }

      Throwable var80 = var10000;
      throw var80;
   }

   public String toString() {
      return "CommonPool";
   }

   public final void usePrivatePool$kotlinx_coroutines_core() {
      synchronized(this){}

      try {
         this.shutdown$kotlinx_coroutines_core(0L);
         usePrivatePool = true;
         Executor var1 = (Executor)null;
         pool = null;
      } finally {
         ;
      }

   }
}
