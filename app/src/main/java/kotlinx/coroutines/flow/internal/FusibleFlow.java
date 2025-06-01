package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J,\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH&¨\u0006\n"},
   d2 = {"Lkotlinx/coroutines/flow/internal/FusibleFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "fuse", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface FusibleFlow extends Flow {
   Flow fuse(CoroutineContext var1, int var2, BufferOverflow var3);

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      public static Flow fuse$default(FusibleFlow var0, CoroutineContext var1, int var2, BufferOverflow var3, int var4, Object var5) {
         if (var5 == null) {
            if ((var4 & 1) != 0) {
               var1 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
            }

            if ((var4 & 2) != 0) {
               var2 = -3;
            }

            if ((var4 & 4) != 0) {
               var3 = BufferOverflow.SUSPEND;
            }

            return var0.fuse(var1, var2, var3);
         } else {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fuse");
         }
      }
   }
}
