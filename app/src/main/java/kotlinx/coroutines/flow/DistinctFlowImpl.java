package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002Be\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012:\u0010\u0007\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\b¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0097@ø\u0001\u0000¢\u0006\u0002\u0010\u0013RD\u0010\u0007\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"},
   d2 = {"Lkotlinx/coroutines/flow/DistinctFlowImpl;", "T", "Lkotlinx/coroutines/flow/Flow;", "upstream", "keySelector", "Lkotlin/Function1;", "", "areEquivalent", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "old", "new", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class DistinctFlowImpl implements Flow {
   public final Function2 areEquivalent;
   public final Function1 keySelector;
   private final Flow upstream;

   public DistinctFlowImpl(Flow var1, Function1 var2, Function2 var3) {
      this.upstream = var1;
      this.keySelector = var2;
      this.areEquivalent = var3;
   }

   public Object collect(FlowCollector var1, Continuation var2) {
      Ref.ObjectRef var3 = new Ref.ObjectRef();
      var3.element = NullSurrogateKt.NULL;
      Object var4 = this.upstream.collect((FlowCollector)(new FlowCollector(this, var3, var1) {
         final FlowCollector $collector$inlined;
         final Ref.ObjectRef $previousKey$inlined;
         final DistinctFlowImpl this$0;

         public {
            this.this$0 = var1;
            this.$previousKey$inlined = var2;
            this.$collector$inlined = var3;
         }

         public Object emit(Object var1, Continuation var2) {
            Object var9;
            label29: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var9 = var4;
                     break label29;
                  }
               }

               var9 = new ContinuationImpl(this, var2) {
                  Object L$0;
                  Object L$1;
                  Object L$2;
                  Object L$3;
                  Object L$4;
                  int label;
                  Object result;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Object invokeSuspend(Object var1) {
                     this.result = var1;
                     this.label |= Integer.MIN_VALUE;
                     return this.this$0.emit((Object)null, this);
                  }
               };
            }

            Object var11 = ((<undefinedtype>)var9).result;
            Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var9).label;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var1 = ((<undefinedtype>)var9).L$4;
               var1 = ((<undefinedtype>)var9).L$3;
               Continuation var8 = (Continuation)((<undefinedtype>)var9).L$2;
               var1 = ((<undefinedtype>)var9).L$1;
               <undefinedtype> var10 = (<undefinedtype>)((<undefinedtype>)var9).L$0;
               ResultKt.throwOnFailure(var11);
            } else {
               ResultKt.throwOnFailure(var11);
               Continuation var7 = (Continuation)var9;
               var11 = this.this$0.keySelector.invoke(var1);
               if (this.$previousKey$inlined.element == NullSurrogateKt.NULL || !(Boolean)this.this$0.areEquivalent.invoke(this.$previousKey$inlined.element, var11)) {
                  this.$previousKey$inlined.element = var11;
                  FlowCollector var6 = this.$collector$inlined;
                  ((<undefinedtype>)var9).L$0 = this;
                  ((<undefinedtype>)var9).L$1 = var1;
                  ((<undefinedtype>)var9).L$2 = var7;
                  ((<undefinedtype>)var9).L$3 = var1;
                  ((<undefinedtype>)var9).L$4 = var11;
                  ((<undefinedtype>)var9).label = 1;
                  if (var6.emit(var1, (Continuation)var9) == var5) {
                     return var5;
                  }
               }
            }

            return Unit.INSTANCE;
         }
      }), var2);
      return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
   }
}
