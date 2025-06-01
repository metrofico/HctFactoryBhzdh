package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlinx.coroutines.DebugStringsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0000J\u0014\u0010\n\u001a\u0004\u0018\u00010\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H&J\b\u0010\f\u001a\u00020\rH\u0016R\u0018\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"},
   d2 = {"Lkotlinx/coroutines/internal/OpDescriptor;", "", "()V", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "isEarlierThan", "", "that", "perform", "affected", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class OpDescriptor {
   public abstract AtomicOp getAtomicOp();

   public final boolean isEarlierThan(OpDescriptor var1) {
      AtomicOp var4 = this.getAtomicOp();
      boolean var3 = false;
      boolean var2 = var3;
      if (var4 != null) {
         AtomicOp var5 = var1.getAtomicOp();
         var2 = var3;
         if (var5 != null) {
            var2 = var3;
            if (var4.getOpSequence() < var5.getOpSequence()) {
               var2 = true;
            }
         }
      }

      return var2;
   }

   public abstract Object perform(Object var1);

   public String toString() {
      return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this);
   }
}
