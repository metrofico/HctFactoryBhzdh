package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0002\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\",\u0010\u0006\u001a \u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"},
   d2 = {"ZERO", "Lkotlinx/coroutines/internal/Symbol;", "countAll", "Lkotlin/Function2;", "", "Lkotlin/coroutines/CoroutineContext$Element;", "findOne", "Lkotlinx/coroutines/ThreadContextElement;", "restoreState", "Lkotlinx/coroutines/internal/ThreadState;", "updateState", "restoreThreadContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "oldState", "threadContextElements", "updateThreadContext", "countOrElement", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ThreadContextKt {
   private static final Symbol ZERO = new Symbol("ZERO");
   private static final Function2 countAll;
   private static final Function2 findOne;
   private static final Function2 restoreState;
   private static final Function2 updateState;

   static {
      countAll = (Function2)null.INSTANCE;
      findOne = (Function2)null.INSTANCE;
      updateState = (Function2)null.INSTANCE;
      restoreState = (Function2)null.INSTANCE;
   }

   public static final void restoreThreadContext(CoroutineContext var0, Object var1) {
      if (var1 != ZERO) {
         if (var1 instanceof ThreadState) {
            ((ThreadState)var1).start();
            var0.fold(var1, restoreState);
         } else {
            Object var2 = var0.fold((Object)null, findOne);
            if (var2 == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            }

            ((ThreadContextElement)var2).restoreThreadContext(var0, var1);
         }

      }
   }

   public static final Object threadContextElements(CoroutineContext var0) {
      Object var1 = var0.fold(0, countAll);
      Intrinsics.checkNotNull(var1);
      return var1;
   }

   public static final Object updateThreadContext(CoroutineContext var0, Object var1) {
      if (var1 == null) {
         var1 = threadContextElements(var0);
      }

      Object var2;
      if (var1 == 0) {
         var2 = ZERO;
      } else if (var1 instanceof Integer) {
         var2 = var0.fold(new ThreadState(var0, ((Number)var1).intValue()), updateState);
      } else {
         if (var1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
         }

         var2 = ((ThreadContextElement)var1).updateThreadContext(var0);
      }

      return var2;
   }
}
