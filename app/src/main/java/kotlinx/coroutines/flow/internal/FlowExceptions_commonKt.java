package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0081\b\u001a\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0000¨\u0006\b"},
   d2 = {"checkIndexOverflow", "", "index", "checkOwnership", "", "Lkotlinx/coroutines/flow/internal/AbortFlowException;", "owner", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class FlowExceptions_commonKt {
   public static final int checkIndexOverflow(int var0) {
      if (var0 >= 0) {
         return var0;
      } else {
         throw (Throwable)(new ArithmeticException("Index overflow has happened"));
      }
   }

   public static final void checkOwnership(AbortFlowException var0, FlowCollector var1) {
      if (var0.getOwner() != var1) {
         throw (Throwable)var0;
      }
   }
}
