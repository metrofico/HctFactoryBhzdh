package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u0006\u0010\t\u001a\u00020\u0002\u001aM\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\r¢\u0006\u0002\b\u0010H\u0086@ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0011\u001a\u0011\u0010\u0012\u001a\u00020\bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\u001e\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0010\b\u0002\u0010\u0018\u001a\n\u0018\u00010\u001aj\u0004\u0018\u0001`\u001b\u001a\n\u0010\u001c\u001a\u00020\u0015*\u00020\u0002\u001a\u0015\u0010\u001d\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002\"\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0000\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"},
   d2 = {"isActive", "", "Lkotlinx/coroutines/CoroutineScope;", "isActive$annotations", "(Lkotlinx/coroutines/CoroutineScope;)V", "(Lkotlinx/coroutines/CoroutineScope;)Z", "CoroutineScope", "context", "Lkotlin/coroutines/CoroutineContext;", "MainScope", "coroutineScope", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentCoroutineContext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "", "message", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "ensureActive", "plus", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CoroutineScopeKt {
   public static final CoroutineScope CoroutineScope(CoroutineContext var0) {
      if (var0.get((CoroutineContext.Key)Job.Key) == null) {
         var0 = var0.plus((CoroutineContext)JobKt.Job$default((Job)null, 1, (Object)null));
      }

      return (CoroutineScope)(new ContextScope(var0));
   }

   public static final CoroutineScope MainScope() {
      return (CoroutineScope)(new ContextScope(SupervisorKt.SupervisorJob$default((Job)null, 1, (Object)null).plus((CoroutineContext)Dispatchers.getMain())));
   }

   public static final void cancel(CoroutineScope var0, String var1, Throwable var2) {
      cancel(var0, ExceptionsKt.CancellationException(var1, var2));
   }

   public static final void cancel(CoroutineScope var0, CancellationException var1) {
      Job var2 = (Job)var0.getCoroutineContext().get((CoroutineContext.Key)Job.Key);
      if (var2 != null) {
         var2.cancel(var1);
      } else {
         throw (Throwable)(new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + var0).toString()));
      }
   }

   // $FF: synthetic method
   public static void cancel$default(CoroutineScope var0, String var1, Throwable var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
         Throwable var5 = (Throwable)null;
      }

      cancel(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void cancel$default(CoroutineScope var0, CancellationException var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         CancellationException var4 = (CancellationException)null;
      }

      cancel(var0, var1);
   }

   public static final Object coroutineScope(Function2 var0, Continuation var1) {
      ScopeCoroutine var2 = new ScopeCoroutine(var1.getContext(), var1);
      Object var3 = UndispatchedKt.startUndispatchedOrReturn(var2, var2, var0);
      if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var3;
   }

   public static final Object currentCoroutineContext(Continuation var0) {
      return var0.getContext();
   }

   private static final Object currentCoroutineContext$$forInline(Continuation var0) {
      InlineMarker.mark(3);
      throw new NullPointerException();
   }

   public static final void ensureActive(CoroutineScope var0) {
      JobKt.ensureActive(var0.getCoroutineContext());
   }

   public static final boolean isActive(CoroutineScope var0) {
      Job var2 = (Job)var0.getCoroutineContext().get((CoroutineContext.Key)Job.Key);
      boolean var1;
      if (var2 != null) {
         var1 = var2.isActive();
      } else {
         var1 = true;
      }

      return var1;
   }

   // $FF: synthetic method
   public static void isActive$annotations(CoroutineScope var0) {
   }

   public static final CoroutineScope plus(CoroutineScope var0, CoroutineContext var1) {
      return (CoroutineScope)(new ContextScope(var0.getCoroutineContext().plus(var1)));
   }
}
