package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0018\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a8\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u001f\b\u0004\u0010\u0002\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"},
   d2 = {"selectUnbiased", "R", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class SelectUnbiasedKt {
   public static final Object selectUnbiased(Function1 var0, Continuation var1) {
      UnbiasedSelectBuilderImpl var2 = new UnbiasedSelectBuilderImpl(var1);

      label32:
      try {
         var0.invoke(var2);
      } catch (Throwable var4) {
         var2.handleBuilderException(var4);
         break label32;
      }

      Object var5 = var2.initSelectResult();
      if (var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var5;
   }

   private static final Object selectUnbiased$$forInline(Function1 var0, Continuation var1) {
      InlineMarker.mark(0);
      UnbiasedSelectBuilderImpl var2 = new UnbiasedSelectBuilderImpl(var1);

      label32:
      try {
         var0.invoke(var2);
      } catch (Throwable var4) {
         var2.handleBuilderException(var4);
         break label32;
      }

      Object var5 = var2.initSelectResult();
      if (var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      InlineMarker.mark(1);
      return var5;
   }
}
