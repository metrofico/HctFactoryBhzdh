package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\">\u0010\u0000\u001a,\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007¨\u0006\b"},
   d2 = {"emitFun", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/coroutines/Continuation;", "", "getEmitFun$annotations", "()V", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class SafeCollectorKt {
   private static final Function3 emitFun = (Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(new Function3() {
      public final Object invoke(FlowCollector var1, Object var2, Continuation var3) {
         InlineMarker.mark(0);
         Object var4 = var1.emit(var2, var3);
         InlineMarker.mark(2);
         InlineMarker.mark(1);
         return var4;
      }
   }, 3);

   // $FF: synthetic method
   public static final Function3 access$getEmitFun$p() {
      return emitFun;
   }

   // $FF: synthetic method
   private static void getEmitFun$annotations() {
   }
}
