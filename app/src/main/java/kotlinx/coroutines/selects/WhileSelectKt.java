package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u00020\u00012\u001f\b\u0004\u0010\u0002\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0006H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"},
   d2 = {"whileSelect", "", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class WhileSelectKt {
   public static final Object whileSelect(Function1 var0, Continuation var1) {
      Object var9;
      label89: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var9 = var3;
               break label89;
            }
         }

         var9 = new ContinuationImpl(var1) {
            Object L$0;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return WhileSelectKt.whileSelect((Function1)null, this);
            }
         };
      }

      Object var10 = ((<undefinedtype>)var9).result;
      Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var9).label;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Function1)((<undefinedtype>)var9).L$0;
         ResultKt.throwOnFailure(var10);
         if (!(Boolean)var10) {
            return Unit.INSTANCE;
         }
      } else {
         ResultKt.throwOnFailure(var10);
      }

      do {
         ((<undefinedtype>)var9).L$0 = var0;
         ((<undefinedtype>)var9).label = 1;
         Continuation var5 = (Continuation)var9;
         SelectBuilderImpl var11 = new SelectBuilderImpl(var5);

         label83:
         try {
            var0.invoke(var11);
         } catch (Throwable var8) {
            var11.handleBuilderException(var8);
            break label83;
         }

         var10 = var11.getResult();
         if (var10 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var5);
         }

         if (var10 == var4) {
            return var4;
         }
      } while((Boolean)var10);

      return Unit.INSTANCE;
   }

   private static final Object whileSelect$$forInline(Function1 var0, Continuation var1) {
      Object var6;
      do {
         InlineMarker.mark(0);
         SelectBuilderImpl var2 = new SelectBuilderImpl(var1);

         label40:
         try {
            var0.invoke(var2);
         } catch (Throwable var5) {
            var2.handleBuilderException(var5);
            break label40;
         }

         var6 = var2.getResult();
         if (var6 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var1);
         }

         InlineMarker.mark(1);
      } while((Boolean)var6);

      return Unit.INSTANCE;
   }
}
