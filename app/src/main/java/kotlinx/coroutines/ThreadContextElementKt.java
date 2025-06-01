package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.ThreadLocalElement;
import kotlinx.coroutines.internal.ThreadLocalKey;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u0003H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u0019\u0010\t\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u0003H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"},
   d2 = {"asContextElement", "Lkotlinx/coroutines/ThreadContextElement;", "T", "Ljava/lang/ThreadLocal;", "value", "(Ljava/lang/ThreadLocal;Ljava/lang/Object;)Lkotlinx/coroutines/ThreadContextElement;", "ensurePresent", "", "(Ljava/lang/ThreadLocal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPresent", "", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ThreadContextElementKt {
   public static final ThreadContextElement asContextElement(ThreadLocal var0, Object var1) {
      return (ThreadContextElement)(new ThreadLocalElement(var1, var0));
   }

   // $FF: synthetic method
   public static ThreadContextElement asContextElement$default(ThreadLocal var0, Object var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.get();
      }

      return asContextElement(var0, var1);
   }

   public static final Object ensurePresent(ThreadLocal var0, Continuation var1) {
      boolean var2;
      if (var1.getContext().get((CoroutineContext.Key)(new ThreadLocalKey(var0))) != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (Boxing.boxBoolean(var2)) {
         return Unit.INSTANCE;
      } else {
         throw (Throwable)(new IllegalStateException(("ThreadLocal " + var0 + " is missing from context " + var1.getContext()).toString()));
      }
   }

   private static final Object ensurePresent$$forInline(ThreadLocal var0, Continuation var1) {
      InlineMarker.mark(3);
      throw new NullPointerException();
   }

   public static final Object isPresent(ThreadLocal var0, Continuation var1) {
      boolean var2;
      if (var1.getContext().get((CoroutineContext.Key)(new ThreadLocalKey(var0))) != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      return Boxing.boxBoolean(var2);
   }

   private static final Object isPresent$$forInline(ThreadLocal var0, Continuation var1) {
      InlineMarker.mark(3);
      throw new NullPointerException();
   }
}
