package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001c\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0016J\u0013\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001e\u0010 \u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00100\"H\u0016J\b\u0010#\u001a\u00020\u0006H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"},
   d2 = {"Lkotlinx/coroutines/android/HandlerContext;", "Lkotlinx/coroutines/android/HandlerDispatcher;", "Lkotlinx/coroutines/Delay;", "handler", "Landroid/os/Handler;", "name", "", "(Landroid/os/Handler;Ljava/lang/String;)V", "invokeImmediately", "", "(Landroid/os/Handler;Ljava/lang/String;Z)V", "_immediate", "immediate", "getImmediate", "()Lkotlinx/coroutines/android/HandlerContext;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "equals", "other", "", "hashCode", "", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "isDispatchNeeded", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "kotlinx-coroutines-android"},
   k = 1,
   mv = {1, 4, 0}
)
public final class HandlerContext extends HandlerDispatcher implements Delay {
   private volatile HandlerContext _immediate;
   private final Handler handler;
   private final HandlerContext immediate;
   private final boolean invokeImmediately;
   private final String name;

   public HandlerContext(Handler var1, String var2) {
      this(var1, var2, false);
   }

   // $FF: synthetic method
   public HandlerContext(Handler var1, String var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
         String var5 = (String)null;
      }

      this(var1, var2);
   }

   private HandlerContext(Handler var1, String var2, boolean var3) {
      HandlerContext var4 = null;
      super((DefaultConstructorMarker)null);
      this.handler = var1;
      this.name = var2;
      this.invokeImmediately = var3;
      if (var3) {
         var4 = this;
      }

      this._immediate = var4;
      var4 = this._immediate;
      HandlerContext var5;
      if (var4 != null) {
         var5 = var4;
      } else {
         var5 = new HandlerContext(var1, var2, true);
         this._immediate = var5;
         Unit var6 = Unit.INSTANCE;
      }

      this.immediate = var5;
   }

   public void dispatch(CoroutineContext var1, Runnable var2) {
      this.handler.post(var2);
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof HandlerContext && ((HandlerContext)var1).handler == this.handler) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public HandlerContext getImmediate() {
      return this.immediate;
   }

   public int hashCode() {
      return System.identityHashCode(this.handler);
   }

   public DisposableHandle invokeOnTimeout(long var1, Runnable var3, CoroutineContext var4) {
      this.handler.postDelayed(var3, RangesKt.coerceAtMost(var1, 4611686018427387903L));
      return (DisposableHandle)(new DisposableHandle(this, var3) {
         final Runnable $block;
         final HandlerContext this$0;

         {
            this.this$0 = var1;
            this.$block = var2;
         }

         public void dispose() {
            this.this$0.handler.removeCallbacks(this.$block);
         }
      });
   }

   public boolean isDispatchNeeded(CoroutineContext var1) {
      boolean var4 = this.invokeImmediately;
      boolean var3 = true;
      boolean var2 = var3;
      if (var4) {
         if (Intrinsics.areEqual((Object)Looper.myLooper(), (Object)this.handler.getLooper()) ^ true) {
            var2 = var3;
         } else {
            var2 = false;
         }
      }

      return var2;
   }

   public void scheduleResumeAfterDelay(long var1, CancellableContinuation var3) {
      Runnable var4 = (Runnable)(new Runnable(this, var3) {
         final CancellableContinuation $continuation$inlined;
         final HandlerContext this$0;

         public {
            this.this$0 = var1;
            this.$continuation$inlined = var2;
         }

         public final void run() {
            this.$continuation$inlined.resumeUndispatched(this.this$0, Unit.INSTANCE);
         }
      });
      this.handler.postDelayed(var4, RangesKt.coerceAtMost(var1, 4611686018427387903L));
      var3.invokeOnCancellation((Function1)(new Function1(this, var4) {
         final Runnable $block;
         final HandlerContext this$0;

         {
            this.this$0 = var1;
            this.$block = var2;
         }

         public final void invoke(Throwable var1) {
            this.this$0.handler.removeCallbacks(this.$block);
         }
      }));
   }

   public String toString() {
      String var2 = this.toStringInternalImpl();
      if (var2 == null) {
         HandlerContext var1 = (HandlerContext)this;
         String var3 = this.name;
         if (var3 == null) {
            var3 = this.handler.toString();
         }

         var2 = var3;
         if (this.invokeImmediately) {
            var2 = var3 + ".immediate";
         }
      }

      return var2;
   }
}
