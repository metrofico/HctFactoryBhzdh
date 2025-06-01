package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractResolvableFuture implements ListenableFuture {
   static final AtomicHelper ATOMIC_HELPER;
   static final boolean GENERATE_CANCELLATION_CAUSES;
   private static final Object NULL;
   private static final long SPIN_THRESHOLD_NANOS = 1000L;
   private static final Logger log;
   volatile Listener listeners;
   volatile Object value;
   volatile Waiter waiters;

   static {
      // $FF: Couldn't be decompiled
   }

   protected AbstractResolvableFuture() {
   }

   private void addDoneString(StringBuilder var1) {
      try {
         Object var2 = getUninterruptibly(this);
         var1.append("SUCCESS, result=[").append(this.userObjectToString(var2)).append("]");
      } catch (ExecutionException var3) {
         var1.append("FAILURE, cause=[").append(var3.getCause()).append("]");
      } catch (CancellationException var4) {
         var1.append("CANCELLED");
      } catch (RuntimeException var5) {
         var1.append("UNKNOWN, cause=[").append(var5.getClass()).append(" thrown from get()]");
      }

   }

   private static CancellationException cancellationExceptionWithCause(String var0, Throwable var1) {
      CancellationException var2 = new CancellationException(var0);
      var2.initCause(var1);
      return var2;
   }

   static Object checkNotNull(Object var0) {
      var0.getClass();
      return var0;
   }

   private Listener clearListeners(Listener var1) {
      Listener var3;
      do {
         var3 = this.listeners;
      } while(!ATOMIC_HELPER.casListeners(this, var3, AbstractResolvableFuture.Listener.TOMBSTONE));

      Listener var2 = var1;

      for(var1 = var3; var1 != null; var1 = var3) {
         var3 = var1.next;
         var1.next = var2;
         var2 = var1;
      }

      return var2;
   }

   static void complete(AbstractResolvableFuture var0) {
      Listener var1 = null;

      label24:
      while(true) {
         var0.releaseWaiters();
         var0.afterDone();

         Listener var4;
         for(var1 = var0.clearListeners(var1); var1 != null; var1 = var4) {
            var4 = var1.next;
            Runnable var2 = var1.task;
            if (var2 instanceof SetFuture) {
               SetFuture var3 = (SetFuture)var2;
               AbstractResolvableFuture var6 = var3.owner;
               if (var6.value == var3) {
                  Object var5 = getFutureValue(var3.future);
                  if (ATOMIC_HELPER.casValue(var6, var3, var5)) {
                     var1 = var4;
                     var0 = var6;
                     continue label24;
                  }
               }
            } else {
               executeListener(var2, var1.executor);
            }
         }

         return;
      }
   }

   private static void executeListener(Runnable var0, Executor var1) {
      try {
         var1.execute(var0);
      } catch (RuntimeException var3) {
         log.log(Level.SEVERE, "RuntimeException while executing runnable " + var0 + " with executor " + var1, var3);
      }

   }

   private Object getDoneValue(Object var1) throws ExecutionException {
      if (!(var1 instanceof Cancellation)) {
         if (!(var1 instanceof Failure)) {
            Object var2 = var1;
            if (var1 == NULL) {
               var2 = null;
            }

            return var2;
         } else {
            throw new ExecutionException(((Failure)var1).exception);
         }
      } else {
         throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation)var1).cause);
      }
   }

   static Object getFutureValue(ListenableFuture param0) {
      // $FF: Couldn't be decompiled
   }

   private static Object getUninterruptibly(Future var0) throws ExecutionException {
      boolean var1 = false;

      Object var2;
      while(true) {
         boolean var5 = false;

         try {
            var5 = true;
            var2 = var0.get();
            var5 = false;
            break;
         } catch (InterruptedException var6) {
            var5 = false;
         } finally {
            if (var5) {
               if (var1) {
                  Thread.currentThread().interrupt();
               }

            }
         }

         var1 = true;
      }

      if (var1) {
         Thread.currentThread().interrupt();
      }

      return var2;
   }

   private void releaseWaiters() {
      Waiter var1;
      do {
         var1 = this.waiters;
      } while(!ATOMIC_HELPER.casWaiters(this, var1, AbstractResolvableFuture.Waiter.TOMBSTONE));

      while(var1 != null) {
         var1.unpark();
         var1 = var1.next;
      }

   }

   private void removeWaiter(Waiter var1) {
      var1.thread = null;

      label30:
      while(true) {
         var1 = this.waiters;
         if (var1 == AbstractResolvableFuture.Waiter.TOMBSTONE) {
            return;
         }

         Waiter var3;
         for(Waiter var2 = null; var1 != null; var2 = var3) {
            Waiter var4 = var1.next;
            if (var1.thread != null) {
               var3 = var1;
            } else if (var2 != null) {
               var2.next = var4;
               var3 = var2;
               if (var2.thread == null) {
                  continue label30;
               }
            } else {
               var3 = var2;
               if (!ATOMIC_HELPER.casWaiters(this, var1, var4)) {
                  continue label30;
               }
            }

            var1 = var4;
         }

         return;
      }
   }

   private String userObjectToString(Object var1) {
      return var1 == this ? "this future" : String.valueOf(var1);
   }

   public final void addListener(Runnable var1, Executor var2) {
      checkNotNull(var1);
      checkNotNull(var2);
      Listener var3 = this.listeners;
      if (var3 != AbstractResolvableFuture.Listener.TOMBSTONE) {
         Listener var5 = new Listener(var1, var2);

         Listener var4;
         do {
            var5.next = var3;
            if (ATOMIC_HELPER.casListeners(this, var3, var5)) {
               return;
            }

            var4 = this.listeners;
            var3 = var4;
         } while(var4 != AbstractResolvableFuture.Listener.TOMBSTONE);
      }

      executeListener(var1, var2);
   }

   protected void afterDone() {
   }

   public final boolean cancel(boolean var1) {
      Object var6 = this.value;
      boolean var4 = true;
      boolean var2;
      if (var6 == null) {
         var2 = true;
      } else {
         var2 = false;
      }

      boolean var3;
      if (var2 | var6 instanceof SetFuture) {
         Cancellation var5;
         if (GENERATE_CANCELLATION_CAUSES) {
            var5 = new Cancellation(var1, new CancellationException("Future.cancel() was called."));
         } else if (var1) {
            var5 = AbstractResolvableFuture.Cancellation.CAUSELESS_INTERRUPTED;
         } else {
            var5 = AbstractResolvableFuture.Cancellation.CAUSELESS_CANCELLED;
         }

         AbstractResolvableFuture var7 = this;
         var3 = false;

         while(true) {
            while(!ATOMIC_HELPER.casValue(var7, var6, var5)) {
               Object var8 = var7.value;
               var6 = var8;
               if (!(var8 instanceof SetFuture)) {
                  return var3;
               }
            }

            if (var1) {
               var7.interruptTask();
            }

            complete(var7);
            var3 = var4;
            if (!(var6 instanceof SetFuture)) {
               break;
            }

            ListenableFuture var9 = ((SetFuture)var6).future;
            if (!(var9 instanceof AbstractResolvableFuture)) {
               var9.cancel(var1);
               var3 = var4;
               break;
            }

            var7 = (AbstractResolvableFuture)var9;
            var6 = var7.value;
            if (var6 == null) {
               var2 = true;
            } else {
               var2 = false;
            }

            var3 = var4;
            if (!(var2 | var6 instanceof SetFuture)) {
               break;
            }

            var3 = true;
         }
      } else {
         var3 = false;
      }

      return var3;
   }

   public final Object get() throws InterruptedException, ExecutionException {
      if (!Thread.interrupted()) {
         Object var2 = this.value;
         boolean var1;
         if (var2 != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1 & (var2 instanceof SetFuture ^ true)) {
            return this.getDoneValue(var2);
         } else {
            Waiter var5 = this.waiters;
            if (var5 != AbstractResolvableFuture.Waiter.TOMBSTONE) {
               Waiter var4 = new Waiter();

               Waiter var3;
               do {
                  var4.setNext(var5);
                  if (ATOMIC_HELPER.casWaiters(this, var5, var4)) {
                     do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                           this.removeWaiter(var4);
                           throw new InterruptedException();
                        }

                        var2 = this.value;
                        if (var2 != null) {
                           var1 = true;
                        } else {
                           var1 = false;
                        }
                     } while(!(var1 & (var2 instanceof SetFuture ^ true)));

                     return this.getDoneValue(var2);
                  }

                  var3 = this.waiters;
                  var5 = var3;
               } while(var3 != AbstractResolvableFuture.Waiter.TOMBSTONE);
            }

            return this.getDoneValue(this.value);
         }
      } else {
         throw new InterruptedException();
      }
   }

   public final Object get(long var1, TimeUnit var3) throws InterruptedException, TimeoutException, ExecutionException {
      long var10 = var3.toNanos(var1);
      if (!Thread.interrupted()) {
         Object var12 = this.value;
         boolean var4;
         if (var12 != null) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (var4 & (var12 instanceof SetFuture ^ true)) {
            return this.getDoneValue(var12);
         } else {
            long var8;
            if (var10 > 0L) {
               var8 = System.nanoTime() + var10;
            } else {
               var8 = 0L;
            }

            long var6 = var10;
            if (var10 >= 1000L) {
               label116: {
                  Waiter var17 = this.waiters;
                  if (var17 != AbstractResolvableFuture.Waiter.TOMBSTONE) {
                     Waiter var14 = new Waiter();

                     Waiter var13;
                     do {
                        var14.setNext(var17);
                        if (ATOMIC_HELPER.casWaiters(this, var17, var14)) {
                           do {
                              LockSupport.parkNanos(this, var10);
                              if (Thread.interrupted()) {
                                 this.removeWaiter(var14);
                                 throw new InterruptedException();
                              }

                              var12 = this.value;
                              if (var12 != null) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              if (var4 & (var12 instanceof SetFuture ^ true)) {
                                 return this.getDoneValue(var12);
                              }

                              var6 = var8 - System.nanoTime();
                              var10 = var6;
                           } while(var6 >= 1000L);

                           this.removeWaiter(var14);
                           break label116;
                        }

                        var13 = this.waiters;
                        var17 = var13;
                     } while(var13 != AbstractResolvableFuture.Waiter.TOMBSTONE);
                  }

                  return this.getDoneValue(this.value);
               }
            }

            while(var6 > 0L) {
               var12 = this.value;
               if (var12 != null) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               if (var4 & (var12 instanceof SetFuture ^ true)) {
                  return this.getDoneValue(var12);
               }

               if (Thread.interrupted()) {
                  throw new InterruptedException();
               }

               var6 = var8 - System.nanoTime();
            }

            String var20 = this.toString();
            String var15 = var3.toString().toLowerCase(Locale.ROOT);
            String var18 = "Waited " + var1 + " " + var3.toString().toLowerCase(Locale.ROOT);
            String var19 = var18;
            if (var6 + 1000L < 0L) {
               var19 = var18 + " (plus ";
               var6 = -var6;
               var1 = var3.convert(var6, TimeUnit.NANOSECONDS);
               var6 -= var3.toNanos(var1);
               long var21;
               int var5 = (var21 = var1 - 0L) == 0L ? 0 : (var21 < 0L ? -1 : 1);
               if (var5 != 0 && var6 <= 1000L) {
                  var4 = false;
               } else {
                  var4 = true;
               }

               String var16 = var19;
               if (var5 > 0) {
                  var19 = var19 + var1 + " " + var15;
                  var16 = var19;
                  if (var4) {
                     var16 = var19 + ",";
                  }

                  var16 = var16 + " ";
               }

               var19 = var16;
               if (var4) {
                  var19 = var16 + var6 + " nanoseconds ";
               }

               var19 = var19 + "delay)";
            }

            if (this.isDone()) {
               throw new TimeoutException(var19 + " but future completed as timeout expired");
            } else {
               throw new TimeoutException(var19 + " for " + var20);
            }
         }
      } else {
         throw new InterruptedException();
      }
   }

   protected void interruptTask() {
   }

   public final boolean isCancelled() {
      return this.value instanceof Cancellation;
   }

   public final boolean isDone() {
      Object var2 = this.value;
      boolean var1;
      if (var2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return (var2 instanceof SetFuture ^ true) & var1;
   }

   final void maybePropagateCancellationTo(Future var1) {
      boolean var2;
      if (var1 != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2 & this.isCancelled()) {
         var1.cancel(this.wasInterrupted());
      }

   }

   protected String pendingToString() {
      Object var1 = this.value;
      if (var1 instanceof SetFuture) {
         return "setFuture=[" + this.userObjectToString(((SetFuture)var1).future) + "]";
      } else {
         return this instanceof ScheduledFuture ? "remaining delay=[" + ((ScheduledFuture)this).getDelay(TimeUnit.MILLISECONDS) + " ms]" : null;
      }
   }

   protected boolean set(Object var1) {
      Object var2 = var1;
      if (var1 == null) {
         var2 = NULL;
      }

      if (ATOMIC_HELPER.casValue(this, (Object)null, var2)) {
         complete(this);
         return true;
      } else {
         return false;
      }
   }

   protected boolean setException(Throwable var1) {
      Failure var2 = new Failure((Throwable)checkNotNull(var1));
      if (ATOMIC_HELPER.casValue(this, (Object)null, var2)) {
         complete(this);
         return true;
      } else {
         return false;
      }
   }

   protected boolean setFuture(ListenableFuture var1) {
      checkNotNull(var1);
      Object var3 = this.value;
      Object var2 = var3;
      if (var3 == null) {
         if (var1.isDone()) {
            Object var11 = getFutureValue(var1);
            if (ATOMIC_HELPER.casValue(this, (Object)null, var11)) {
               complete(this);
               return true;
            }

            return false;
         }

         SetFuture var12 = new SetFuture(this, var1);
         if (ATOMIC_HELPER.casValue(this, (Object)null, var12)) {
            try {
               var1.addListener(var12, DirectExecutor.INSTANCE);
            } catch (Throwable var9) {
               Throwable var13 = var9;

               Failure var10;
               try {
                  var10 = new Failure(var13);
               } finally {
                  ;
               }

               ATOMIC_HELPER.casValue(this, var12, var10);
               return true;
            }

            return true;
         }

         var2 = this.value;
      }

      if (var2 instanceof Cancellation) {
         var1.cancel(((Cancellation)var2).wasInterrupted);
      }

      return false;
   }

   public String toString() {
      StringBuilder var2 = (new StringBuilder()).append(super.toString()).append("[status=");
      if (this.isCancelled()) {
         var2.append("CANCELLED");
      } else if (this.isDone()) {
         this.addDoneString(var2);
      } else {
         String var1;
         try {
            var1 = this.pendingToString();
         } catch (RuntimeException var3) {
            var1 = "Exception thrown from implementation: " + var3.getClass();
         }

         if (var1 != null && !var1.isEmpty()) {
            var2.append("PENDING, info=[").append(var1).append("]");
         } else if (this.isDone()) {
            this.addDoneString(var2);
         } else {
            var2.append("PENDING");
         }
      }

      return var2.append("]").toString();
   }

   protected final boolean wasInterrupted() {
      Object var2 = this.value;
      boolean var1;
      if (var2 instanceof Cancellation && ((Cancellation)var2).wasInterrupted) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private abstract static class AtomicHelper {
      private AtomicHelper() {
      }

      // $FF: synthetic method
      AtomicHelper(Object var1) {
         this();
      }

      abstract boolean casListeners(AbstractResolvableFuture var1, Listener var2, Listener var3);

      abstract boolean casValue(AbstractResolvableFuture var1, Object var2, Object var3);

      abstract boolean casWaiters(AbstractResolvableFuture var1, Waiter var2, Waiter var3);

      abstract void putNext(Waiter var1, Waiter var2);

      abstract void putThread(Waiter var1, Thread var2);
   }

   private static final class Cancellation {
      static final Cancellation CAUSELESS_CANCELLED;
      static final Cancellation CAUSELESS_INTERRUPTED;
      final Throwable cause;
      final boolean wasInterrupted;

      static {
         if (AbstractResolvableFuture.GENERATE_CANCELLATION_CAUSES) {
            CAUSELESS_CANCELLED = null;
            CAUSELESS_INTERRUPTED = null;
         } else {
            CAUSELESS_CANCELLED = new Cancellation(false, (Throwable)null);
            CAUSELESS_INTERRUPTED = new Cancellation(true, (Throwable)null);
         }

      }

      Cancellation(boolean var1, Throwable var2) {
         this.wasInterrupted = var1;
         this.cause = var2;
      }
   }

   private static final class Failure {
      static final Failure FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") {
         public Throwable fillInStackTrace() {
            synchronized(this){}
            return this;
         }
      });
      final Throwable exception;

      Failure(Throwable var1) {
         this.exception = (Throwable)AbstractResolvableFuture.checkNotNull(var1);
      }
   }

   private static final class Listener {
      static final Listener TOMBSTONE = new Listener((Runnable)null, (Executor)null);
      final Executor executor;
      Listener next;
      final Runnable task;

      Listener(Runnable var1, Executor var2) {
         this.task = var1;
         this.executor = var2;
      }
   }

   private static final class SafeAtomicHelper extends AtomicHelper {
      final AtomicReferenceFieldUpdater listenersUpdater;
      final AtomicReferenceFieldUpdater valueUpdater;
      final AtomicReferenceFieldUpdater waiterNextUpdater;
      final AtomicReferenceFieldUpdater waiterThreadUpdater;
      final AtomicReferenceFieldUpdater waitersUpdater;

      SafeAtomicHelper(AtomicReferenceFieldUpdater var1, AtomicReferenceFieldUpdater var2, AtomicReferenceFieldUpdater var3, AtomicReferenceFieldUpdater var4, AtomicReferenceFieldUpdater var5) {
         super(null);
         this.waiterThreadUpdater = var1;
         this.waiterNextUpdater = var2;
         this.waitersUpdater = var3;
         this.listenersUpdater = var4;
         this.valueUpdater = var5;
      }

      boolean casListeners(AbstractResolvableFuture var1, Listener var2, Listener var3) {
         return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(this.listenersUpdater, var1, var2, var3);
      }

      boolean casValue(AbstractResolvableFuture var1, Object var2, Object var3) {
         return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(this.valueUpdater, var1, var2, var3);
      }

      boolean casWaiters(AbstractResolvableFuture var1, Waiter var2, Waiter var3) {
         return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(this.waitersUpdater, var1, var2, var3);
      }

      void putNext(Waiter var1, Waiter var2) {
         this.waiterNextUpdater.lazySet(var1, var2);
      }

      void putThread(Waiter var1, Thread var2) {
         this.waiterThreadUpdater.lazySet(var1, var2);
      }
   }

   private static final class SetFuture implements Runnable {
      final ListenableFuture future;
      final AbstractResolvableFuture owner;

      SetFuture(AbstractResolvableFuture var1, ListenableFuture var2) {
         this.owner = var1;
         this.future = var2;
      }

      public void run() {
         if (this.owner.value == this) {
            Object var1 = AbstractResolvableFuture.getFutureValue(this.future);
            if (AbstractResolvableFuture.ATOMIC_HELPER.casValue(this.owner, this, var1)) {
               AbstractResolvableFuture.complete(this.owner);
            }

         }
      }
   }

   private static final class SynchronizedHelper extends AtomicHelper {
      SynchronizedHelper() {
         super(null);
      }

      boolean casListeners(AbstractResolvableFuture var1, Listener var2, Listener var3) {
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label123: {
            try {
               if (var1.listeners == var2) {
                  var1.listeners = var3;
                  return true;
               }
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label123;
            }

            label117:
            try {
               return false;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label117;
            }
         }

         while(true) {
            Throwable var16 = var10000;

            try {
               throw var16;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               continue;
            }
         }
      }

      boolean casValue(AbstractResolvableFuture var1, Object var2, Object var3) {
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label123: {
            try {
               if (var1.value == var2) {
                  var1.value = var3;
                  return true;
               }
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label123;
            }

            label117:
            try {
               return false;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label117;
            }
         }

         while(true) {
            Throwable var16 = var10000;

            try {
               throw var16;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               continue;
            }
         }
      }

      boolean casWaiters(AbstractResolvableFuture var1, Waiter var2, Waiter var3) {
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label123: {
            try {
               if (var1.waiters == var2) {
                  var1.waiters = var3;
                  return true;
               }
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label123;
            }

            label117:
            try {
               return false;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label117;
            }
         }

         while(true) {
            Throwable var16 = var10000;

            try {
               throw var16;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               continue;
            }
         }
      }

      void putNext(Waiter var1, Waiter var2) {
         var1.next = var2;
      }

      void putThread(Waiter var1, Thread var2) {
         var1.thread = var2;
      }
   }

   private static final class Waiter {
      static final Waiter TOMBSTONE = new Waiter(false);
      volatile Waiter next;
      volatile Thread thread;

      Waiter() {
         AbstractResolvableFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
      }

      Waiter(boolean var1) {
      }

      void setNext(Waiter var1) {
         AbstractResolvableFuture.ATOMIC_HELPER.putNext(this, var1);
      }

      void unpark() {
         Thread var1 = this.thread;
         if (var1 != null) {
            this.thread = null;
            LockSupport.unpark(var1);
         }

      }
   }
}
