package kotlinx.coroutines.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0016J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u001b\u001a\u00020\fH\u0002J\u001e\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u000e2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\b\u0010 \u001a\u00020\u0006H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"},
   d2 = {"Lkotlinx/coroutines/internal/MissingMainCoroutineDispatcher;", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "cause", "", "errorHint", "", "(Ljava/lang/Throwable;Ljava/lang/String;)V", "immediate", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "delay", "", "time", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "isDispatchNeeded", "", "missing", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "toString", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class MissingMainCoroutineDispatcher extends MainCoroutineDispatcher implements Delay {
   private final Throwable cause;
   private final String errorHint;

   public MissingMainCoroutineDispatcher(Throwable var1, String var2) {
      this.cause = var1;
      this.errorHint = var2;
   }

   // $FF: synthetic method
   public MissingMainCoroutineDispatcher(Throwable var1, String var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
         String var5 = (String)null;
      }

      this(var1, var2);
   }

   private final Void missing() {
      if (this.cause == null) {
         MainDispatchersKt.throwMissingMainDispatcherException();
         throw new KotlinNothingValueException();
      } else {
         StringBuilder var2 = (new StringBuilder()).append("Module with the Main dispatcher had failed to initialize");
         String var1 = this.errorHint;
         if (var1 != null) {
            var1 = ". " + var1;
            if (var1 != null) {
               throw (Throwable)(new IllegalStateException(var2.append(var1).toString(), this.cause));
            }
         }

         var1 = "";
         throw (Throwable)(new IllegalStateException(var2.append(var1).toString(), this.cause));
      }
   }

   public Object delay(long var1, Continuation var3) {
      this.missing();
      throw new KotlinNothingValueException();
   }

   public Void dispatch(CoroutineContext var1, Runnable var2) {
      this.missing();
      throw new KotlinNothingValueException();
   }

   public MainCoroutineDispatcher getImmediate() {
      return (MainCoroutineDispatcher)this;
   }

   public DisposableHandle invokeOnTimeout(long var1, Runnable var3, CoroutineContext var4) {
      this.missing();
      throw new KotlinNothingValueException();
   }

   public boolean isDispatchNeeded(CoroutineContext var1) {
      this.missing();
      throw new KotlinNothingValueException();
   }

   public Void scheduleResumeAfterDelay(long var1, CancellableContinuation var3) {
      this.missing();
      throw new KotlinNothingValueException();
   }

   public String toString() {
      StringBuilder var2 = (new StringBuilder()).append("Dispatchers.Main[missing");
      String var1;
      if (this.cause != null) {
         var1 = ", cause=" + this.cause;
      } else {
         var1 = "";
      }

      return var2.append(var1).append(']').toString();
   }
}
