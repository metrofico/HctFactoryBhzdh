package kotlinx.coroutines.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;

@Deprecated(
   level = DeprecationLevel.WARNING,
   message = "This API has been deprecated to integrate with Structured Concurrency.",
   replaceWith = @ReplaceWith(
   expression = "TestCoroutineScope",
   imports = {"kotlin.coroutines.test"}
)
)
@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001:\u0001<B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J$\u0010\u001d\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J$\u0010\"\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J*\u0010#\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0018\u0010\u001f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020!0 J$\u0010$\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J\u0006\u0010%\u001a\u00020\u001bJ\u0014\u0010&\u001a\u00020\u001b2\n\u0010'\u001a\u00060(j\u0002`)H\u0002J5\u0010*\u001a\u0002H+\"\u0004\b\u0000\u0010+2\u0006\u0010,\u001a\u0002H+2\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u0002H+\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u0002H+0.H\u0016¢\u0006\u0002\u00100J(\u00101\u001a\u0004\u0018\u0001H2\"\b\b\u0000\u00102*\u00020/2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H204H\u0096\u0002¢\u0006\u0002\u00105J\u0014\u00106\u001a\u00020\u00012\n\u00103\u001a\u0006\u0012\u0002\b\u000304H\u0016J\u0010\u00107\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J\u001c\u00108\u001a\u00020\u00122\n\u0010'\u001a\u00060(j\u0002`)2\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\b\u00109\u001a\u00020\u0006H\u0002J\b\u0010:\u001a\u00020\u0003H\u0016J\u0006\u0010;\u001a\u00020\u001bJ\u0010\u0010;\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="},
   d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext;", "Lkotlin/coroutines/CoroutineContext;", "name", "", "(Ljava/lang/String;)V", "counter", "", "ctxDispatcher", "Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "ctxHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "exceptions", "", "", "getExceptions", "()Ljava/util/List;", "queue", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/test/TimedRunnableObsolete;", "time", "uncaughtExceptions", "", "advanceTimeBy", "delayTime", "unit", "Ljava/util/concurrent/TimeUnit;", "advanceTimeTo", "", "targetTime", "assertAllUnhandledExceptions", "message", "predicate", "Lkotlin/Function1;", "", "assertAnyUnhandledException", "assertExceptions", "assertUnhandledException", "cancelAllActions", "enqueue", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "now", "postDelayed", "processNextEvent", "toString", "triggerActions", "Dispatcher", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class TestCoroutineContext implements CoroutineContext {
   private long counter;
   private final Dispatcher ctxDispatcher;
   private final CoroutineExceptionHandler ctxHandler;
   private final String name;
   private final ThreadSafeHeap queue;
   private long time;
   private final List uncaughtExceptions;

   public TestCoroutineContext() {
      this((String)null, 1, (DefaultConstructorMarker)null);
   }

   public TestCoroutineContext(String var1) {
      this.name = var1;
      this.uncaughtExceptions = (List)(new ArrayList());
      this.ctxDispatcher = new Dispatcher(this);
      this.ctxHandler = (CoroutineExceptionHandler)(new CoroutineExceptionHandler((Key)CoroutineExceptionHandler.Key, this) {
         final TestCoroutineContext this$0;

         public {
            this.this$0 = var2;
         }

         public void handleException(CoroutineContext var1, Throwable var2) {
            ((Collection)this.this$0.uncaughtExceptions).add(var2);
         }
      });
      this.queue = new ThreadSafeHeap();
   }

   // $FF: synthetic method
   public TestCoroutineContext(String var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         String var4 = (String)null;
      }

      this(var1);
   }

   // $FF: synthetic method
   public static long advanceTimeBy$default(TestCoroutineContext var0, long var1, TimeUnit var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var3 = TimeUnit.MILLISECONDS;
      }

      return var0.advanceTimeBy(var1, var3);
   }

   // $FF: synthetic method
   public static void advanceTimeTo$default(TestCoroutineContext var0, long var1, TimeUnit var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var3 = TimeUnit.MILLISECONDS;
      }

      var0.advanceTimeTo(var1, var3);
   }

   // $FF: synthetic method
   public static void assertAllUnhandledExceptions$default(TestCoroutineContext var0, String var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = "";
      }

      var0.assertAllUnhandledExceptions(var1, var2);
   }

   // $FF: synthetic method
   public static void assertAnyUnhandledException$default(TestCoroutineContext var0, String var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = "";
      }

      var0.assertAnyUnhandledException(var1, var2);
   }

   // $FF: synthetic method
   public static void assertExceptions$default(TestCoroutineContext var0, String var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = "";
      }

      var0.assertExceptions(var1, var2);
   }

   // $FF: synthetic method
   public static void assertUnhandledException$default(TestCoroutineContext var0, String var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = "";
      }

      var0.assertUnhandledException(var1, var2);
   }

   private final void enqueue(Runnable var1) {
      ThreadSafeHeap var4 = this.queue;
      long var2 = (long)(this.counter++);
      var4.addLast((ThreadSafeHeapNode)(new TimedRunnableObsolete(var1, var2, 0L, 4, (DefaultConstructorMarker)null)));
   }

   // $FF: synthetic method
   public static long now$default(TestCoroutineContext var0, TimeUnit var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = TimeUnit.MILLISECONDS;
      }

      return var0.now(var1);
   }

   private final TimedRunnableObsolete postDelayed(Runnable var1, long var2) {
      long var4 = (long)(this.counter++);
      TimedRunnableObsolete var6 = new TimedRunnableObsolete(var1, var4, this.time + TimeUnit.MILLISECONDS.toNanos(var2));
      this.queue.addLast((ThreadSafeHeapNode)var6);
      return var6;
   }

   private final long processNextEvent() {
      TimedRunnableObsolete var3 = (TimedRunnableObsolete)this.queue.peek();
      if (var3 != null) {
         this.triggerActions(var3.time);
      }

      long var1;
      if (this.queue.isEmpty()) {
         var1 = Long.MAX_VALUE;
      } else {
         var1 = 0L;
      }

      return var1;
   }

   private final void triggerActions(long var1) {
      while(true) {
         ThreadSafeHeap var6 = this.queue;
         synchronized(var6){}

         ThreadSafeHeapNode var4;
         label206: {
            Throwable var10000;
            label212: {
               ThreadSafeHeapNode var7;
               boolean var10001;
               try {
                  var7 = var6.firstImpl();
               } catch (Throwable var19) {
                  var10000 = var19;
                  var10001 = false;
                  break label212;
               }

               Object var5 = null;
               var4 = null;
               if (var7 == null) {
                  var4 = (ThreadSafeHeapNode)var5;
                  break label206;
               }

               boolean var3;
               label200: {
                  label199: {
                     try {
                        if (((TimedRunnableObsolete)var7).time <= var1) {
                           break label199;
                        }
                     } catch (Throwable var18) {
                        var10000 = var18;
                        var10001 = false;
                        break label212;
                     }

                     var3 = false;
                     break label200;
                  }

                  var3 = true;
               }

               if (!var3) {
                  break label206;
               }

               label192:
               try {
                  var4 = var6.removeAtImpl(0);
                  break label206;
               } catch (Throwable var17) {
                  var10000 = var17;
                  var10001 = false;
                  break label192;
               }
            }

            Throwable var20 = var10000;
            throw var20;
         }

         TimedRunnableObsolete var21 = (TimedRunnableObsolete)var4;
         if (var21 == null) {
            return;
         }

         if (var21.time != 0L) {
            this.time = var21.time;
         }

         var21.run();
      }
   }

   public final long advanceTimeBy(long var1, TimeUnit var3) {
      long var4 = this.time;
      this.advanceTimeTo(var3.toNanos(var1) + var4, TimeUnit.NANOSECONDS);
      return var3.convert(this.time - var4, TimeUnit.NANOSECONDS);
   }

   public final void advanceTimeTo(long var1, TimeUnit var3) {
      var1 = var3.toNanos(var1);
      this.triggerActions(var1);
      if (var1 > this.time) {
         this.time = var1;
      }

   }

   public final void assertAllUnhandledExceptions(String var1, Function1 var2) {
      Iterable var6 = (Iterable)this.uncaughtExceptions;
      boolean var5 = var6 instanceof Collection;
      boolean var4 = true;
      boolean var3;
      if (var5 && ((Collection)var6).isEmpty()) {
         var3 = var4;
      } else {
         Iterator var7 = var6.iterator();

         while(true) {
            var3 = var4;
            if (!var7.hasNext()) {
               break;
            }

            if (!(Boolean)var2.invoke(var7.next())) {
               var3 = false;
               break;
            }
         }
      }

      if (var3) {
         this.uncaughtExceptions.clear();
      } else {
         throw (Throwable)(new AssertionError(var1));
      }
   }

   public final void assertAnyUnhandledException(String var1, Function1 var2) {
      Iterable var6 = (Iterable)this.uncaughtExceptions;
      boolean var5 = var6 instanceof Collection;
      boolean var4 = false;
      boolean var3;
      if (var5 && ((Collection)var6).isEmpty()) {
         var3 = var4;
      } else {
         Iterator var7 = var6.iterator();

         while(true) {
            var3 = var4;
            if (!var7.hasNext()) {
               break;
            }

            if ((Boolean)var2.invoke(var7.next())) {
               var3 = true;
               break;
            }
         }
      }

      if (var3) {
         this.uncaughtExceptions.clear();
      } else {
         throw (Throwable)(new AssertionError(var1));
      }
   }

   public final void assertExceptions(String var1, Function1 var2) {
      if ((Boolean)var2.invoke(this.uncaughtExceptions)) {
         this.uncaughtExceptions.clear();
      } else {
         throw (Throwable)(new AssertionError(var1));
      }
   }

   public final void assertUnhandledException(String var1, Function1 var2) {
      if (this.uncaughtExceptions.size() == 1 && (Boolean)var2.invoke(this.uncaughtExceptions.get(0))) {
         this.uncaughtExceptions.clear();
      } else {
         throw (Throwable)(new AssertionError(var1));
      }
   }

   public final void cancelAllActions() {
      if (!this.queue.isEmpty()) {
         this.queue.clear();
      }

   }

   public Object fold(Object var1, Function2 var2) {
      return var2.invoke(var2.invoke(var1, this.ctxDispatcher), this.ctxHandler);
   }

   public Element get(Key var1) {
      Element var3;
      if (var1 == ContinuationInterceptor.Key) {
         Dispatcher var2 = this.ctxDispatcher;
         if (var2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type E");
         }

         var3 = (Element)var2;
      } else if (var1 == CoroutineExceptionHandler.Key) {
         CoroutineExceptionHandler var4 = this.ctxHandler;
         if (var4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type E");
         }

         var3 = (Element)var4;
      } else {
         var3 = null;
      }

      return var3;
   }

   public final List getExceptions() {
      return this.uncaughtExceptions;
   }

   public CoroutineContext minusKey(Key var1) {
      CoroutineContext var2;
      if (var1 == ContinuationInterceptor.Key) {
         var2 = (CoroutineContext)this.ctxHandler;
      } else if (var1 == CoroutineExceptionHandler.Key) {
         var2 = (CoroutineContext)this.ctxDispatcher;
      } else {
         var2 = (CoroutineContext)this;
      }

      return var2;
   }

   public final long now(TimeUnit var1) {
      return var1.convert(this.time, TimeUnit.NANOSECONDS);
   }

   public CoroutineContext plus(CoroutineContext var1) {
      return DefaultImpls.plus(this, var1);
   }

   public String toString() {
      String var1 = this.name;
      if (var1 == null) {
         var1 = "TestCoroutineContext@" + DebugStringsKt.getHexAddress(this);
      }

      return var1;
   }

   public final void triggerActions() {
      this.triggerActions(this.time);
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0016J$\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\u0010\b\u001a\u00060\tj\u0002`\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u001e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016¨\u0006\u0017"},
      d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "Lkotlinx/coroutines/EventLoop;", "Lkotlinx/coroutines/Delay;", "(Lkotlinx/coroutines/test/TestCoroutineContext;)V", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "processNextEvent", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "shouldBeProcessedFromContext", "", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class Dispatcher extends EventLoop implements Delay {
      final TestCoroutineContext this$0;

      public Dispatcher(TestCoroutineContext var1) {
         this.this$0 = var1;
         EventLoop.incrementUseCount$default(this, false, 1, (Object)null);
      }

      public Object delay(long var1, Continuation var3) {
         return Delay.DefaultImpls.delay(this, var1, var3);
      }

      public void dispatch(CoroutineContext var1, Runnable var2) {
         this.this$0.enqueue(var2);
      }

      public DisposableHandle invokeOnTimeout(long var1, Runnable var3, CoroutineContext var4) {
         return (DisposableHandle)(new DisposableHandle(this, this.this$0.postDelayed(var3, var1)) {
            final TimedRunnableObsolete $node;
            final Dispatcher this$0;

            {
               this.this$0 = var1;
               this.$node = var2;
            }

            public void dispose() {
               this.this$0.this$0.queue.remove((ThreadSafeHeapNode)this.$node);
            }
         });
      }

      public long processNextEvent() {
         return this.this$0.processNextEvent();
      }

      public void scheduleResumeAfterDelay(long var1, CancellableContinuation var3) {
         this.this$0.postDelayed((Runnable)(new Runnable(this, var3) {
            final CancellableContinuation $continuation$inlined;
            final Dispatcher this$0;

            public {
               this.this$0 = var1;
               this.$continuation$inlined = var2;
            }

            public final void run() {
               this.$continuation$inlined.resumeUndispatched(this.this$0, Unit.INSTANCE);
            }
         }), var1);
      }

      public boolean shouldBeProcessedFromContext() {
         return true;
      }

      public String toString() {
         return "Dispatcher(" + this.this$0 + ')';
      }
   }
}
