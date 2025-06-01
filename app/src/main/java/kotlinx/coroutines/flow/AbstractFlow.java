package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.internal.SafeCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"},
   d2 = {"Lkotlinx/coroutines/flow/AbstractFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "()V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectSafely", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class AbstractFlow implements Flow, CancellableFlow {
   public final Object collect(FlowCollector var1, Continuation var2) {
      Object var14;
      label133: {
         if (var2 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var2;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var14 = var4;
               break label133;
            }
         }

         var14 = new ContinuationImpl(this, var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            Object result;
            final AbstractFlow this$0;

            {
               this.this$0 = var1;
            }

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.collect((FlowCollector)null, this);
            }
         };
      }

      SafeCollector var13;
      label128: {
         Object var16 = ((<undefinedtype>)var14).result;
         Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         int var3 = ((<undefinedtype>)var14).label;
         SafeCollector var17;
         if (var3 != 0) {
            if (var3 != 1) {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            var13 = (SafeCollector)((<undefinedtype>)var14).L$2;
            FlowCollector var18 = (FlowCollector)((<undefinedtype>)var14).L$1;
            var14 = (AbstractFlow)((<undefinedtype>)var14).L$0;

            try {
               ResultKt.throwOnFailure(var16);
            } finally {
               break label128;
            }
         } else {
            ResultKt.throwOnFailure(var16);
            var17 = new SafeCollector(var1, ((Continuation)var14).getContext());

            Object var15;
            try {
               FlowCollector var6 = (FlowCollector)var17;
               ((<undefinedtype>)var14).L$0 = this;
               ((<undefinedtype>)var14).L$1 = var1;
               ((<undefinedtype>)var14).L$2 = var17;
               ((<undefinedtype>)var14).label = 1;
               var15 = this.collectSafely(var6, (Continuation)var14);
            } catch (Throwable var11) {
               var14 = var11;
               var13 = var17;
               break label128;
            }

            if (var15 == var5) {
               return var5;
            }
         }

         var17.releaseIntercepted();
         return Unit.INSTANCE;
      }

      var13.releaseIntercepted();
      throw var14;
   }

   public abstract Object collectSafely(FlowCollector var1, Continuation var2);
}
