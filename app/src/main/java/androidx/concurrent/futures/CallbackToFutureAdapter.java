package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class CallbackToFutureAdapter {
   private CallbackToFutureAdapter() {
   }

   public static ListenableFuture getFuture(Resolver var0) {
      Completer var2 = new Completer();
      SafeFuture var1 = new SafeFuture(var2);
      var2.future = var1;
      var2.tag = var0.getClass();

      Exception var10000;
      label28: {
         boolean var10001;
         Object var5;
         try {
            var5 = var0.attachCompleter(var2);
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label28;
         }

         if (var5 == null) {
            return var1;
         }

         try {
            var2.tag = var5;
            return var1;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var1.setException(var6);
      return var1;
   }

   public static final class Completer {
      private boolean attemptedSetting;
      private ResolvableFuture cancellationFuture = ResolvableFuture.create();
      SafeFuture future;
      Object tag;

      Completer() {
      }

      private void setCompletedNormally() {
         this.tag = null;
         this.future = null;
         this.cancellationFuture = null;
      }

      public void addCancellationListener(Runnable var1, Executor var2) {
         ResolvableFuture var3 = this.cancellationFuture;
         if (var3 != null) {
            var3.addListener(var1, var2);
         }

      }

      protected void finalize() {
         SafeFuture var1 = this.future;
         if (var1 != null && !var1.isDone()) {
            var1.setException(new FutureGarbageCollectedException("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.tag));
         }

         if (!this.attemptedSetting) {
            ResolvableFuture var2 = this.cancellationFuture;
            if (var2 != null) {
               var2.set((Object)null);
            }
         }

      }

      void fireCancellationListeners() {
         this.tag = null;
         this.future = null;
         this.cancellationFuture.set((Object)null);
      }

      public boolean set(Object var1) {
         boolean var2 = true;
         this.attemptedSetting = true;
         SafeFuture var3 = this.future;
         if (var3 == null || !var3.set(var1)) {
            var2 = false;
         }

         if (var2) {
            this.setCompletedNormally();
         }

         return var2;
      }

      public boolean setCancelled() {
         boolean var1 = true;
         this.attemptedSetting = true;
         SafeFuture var2 = this.future;
         if (var2 == null || !var2.cancelWithoutNotifyingCompleter(true)) {
            var1 = false;
         }

         if (var1) {
            this.setCompletedNormally();
         }

         return var1;
      }

      public boolean setException(Throwable var1) {
         boolean var2 = true;
         this.attemptedSetting = true;
         SafeFuture var3 = this.future;
         if (var3 == null || !var3.setException(var1)) {
            var2 = false;
         }

         if (var2) {
            this.setCompletedNormally();
         }

         return var2;
      }
   }

   static final class FutureGarbageCollectedException extends Throwable {
      FutureGarbageCollectedException(String var1) {
         super(var1);
      }

      public Throwable fillInStackTrace() {
         synchronized(this){}
         return this;
      }
   }

   public interface Resolver {
      Object attachCompleter(Completer var1) throws Exception;
   }

   private static final class SafeFuture implements ListenableFuture {
      final WeakReference completerWeakReference;
      private final AbstractResolvableFuture delegate = new AbstractResolvableFuture(this) {
         final SafeFuture this$0;

         {
            this.this$0 = var1;
         }

         protected String pendingToString() {
            Completer var1 = (Completer)this.this$0.completerWeakReference.get();
            return var1 == null ? "Completer object has been garbage collected, future will fail soon" : "tag=[" + var1.tag + "]";
         }
      };

      SafeFuture(Completer var1) {
         this.completerWeakReference = new WeakReference(var1);
      }

      public void addListener(Runnable var1, Executor var2) {
         this.delegate.addListener(var1, var2);
      }

      public boolean cancel(boolean var1) {
         Completer var2 = (Completer)this.completerWeakReference.get();
         var1 = this.delegate.cancel(var1);
         if (var1 && var2 != null) {
            var2.fireCancellationListeners();
         }

         return var1;
      }

      boolean cancelWithoutNotifyingCompleter(boolean var1) {
         return this.delegate.cancel(var1);
      }

      public Object get() throws InterruptedException, ExecutionException {
         return this.delegate.get();
      }

      public Object get(long var1, TimeUnit var3) throws InterruptedException, ExecutionException, TimeoutException {
         return this.delegate.get(var1, var3);
      }

      public boolean isCancelled() {
         return this.delegate.isCancelled();
      }

      public boolean isDone() {
         return this.delegate.isDone();
      }

      boolean set(Object var1) {
         return this.delegate.set(var1);
      }

      boolean setException(Throwable var1) {
         return this.delegate.setException(var1);
      }

      public String toString() {
         return this.delegate.toString();
      }
   }
}
