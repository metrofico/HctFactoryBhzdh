package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001aE\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"},
   d2 = {"count", "", "T", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__CountKt {
   public static final Object count(Flow var0, Continuation var1) {
      Object var7;
      label25: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var7 = var3;
               break label25;
            }
         }

         var7 = new ContinuationImpl(var1) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.count((Flow)null, this);
            }
         };
      }

      Object var9 = ((<undefinedtype>)var7).result;
      Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var7).label;
      Ref.IntRef var6;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Flow)((<undefinedtype>)var7).L$2;
         var6 = (Ref.IntRef)((<undefinedtype>)var7).L$1;
         Flow var8 = (Flow)((<undefinedtype>)var7).L$0;
         ResultKt.throwOnFailure(var9);
      } else {
         ResultKt.throwOnFailure(var9);
         Ref.IntRef var10 = new Ref.IntRef();
         var10.element = 0;
         FlowCollector var5 = (FlowCollector)(new FlowCollector(var10) {
            final Ref.IntRef $i$inlined;

            public {
               this.$i$inlined = var1;
            }

            public Object emit(Object var1, Continuation var2) {
               Ref.IntRef var4 = this.$i$inlined;
               ++var4.element;
               int var3 = var4.element;
               return Unit.INSTANCE;
            }
         });
         ((<undefinedtype>)var7).L$0 = var0;
         ((<undefinedtype>)var7).L$1 = var10;
         ((<undefinedtype>)var7).L$2 = var0;
         ((<undefinedtype>)var7).label = 1;
         if (var0.collect(var5, (Continuation)var7) == var4) {
            return var4;
         }

         var6 = var10;
      }

      return Boxing.boxInt(var6.element);
   }

   public static final Object count(Flow var0, Function2 var1, Continuation var2) {
      Object var9;
      label25: {
         if (var2 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var2;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var9 = var4;
               break label25;
            }
         }

         var9 = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.count((Flow)null, (Function2)null, this);
            }
         };
      }

      Object var10 = ((<undefinedtype>)var9).result;
      Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var3 = ((<undefinedtype>)var9).label;
      Ref.IntRef var7;
      if (var3 != 0) {
         if (var3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Flow)((<undefinedtype>)var9).L$3;
         var7 = (Ref.IntRef)((<undefinedtype>)var9).L$2;
         var1 = (Function2)((<undefinedtype>)var9).L$1;
         Flow var8 = (Flow)((<undefinedtype>)var9).L$0;
         ResultKt.throwOnFailure(var10);
      } else {
         ResultKt.throwOnFailure(var10);
         Ref.IntRef var11 = new Ref.IntRef();
         var11.element = 0;
         FlowCollector var6 = (FlowCollector)(new FlowCollector(var1, var11) {
            final Ref.IntRef $i$inlined;
            final Function2 $predicate$inlined;

            public {
               this.$predicate$inlined = var1;
               this.$i$inlined = var2;
            }

            public Object emit(Object var1, Continuation var2) {
               Object var8;
               label29: {
                  if (var2 instanceof <undefinedtype>) {
                     <undefinedtype> var4 = (<undefinedtype>)var2;
                     if ((var4.label & Integer.MIN_VALUE) != 0) {
                        var4.label += Integer.MIN_VALUE;
                        var8 = var4;
                        break label29;
                     }
                  }

                  var8 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
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

               Object var11 = ((<undefinedtype>)var8).result;
               Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               int var3 = ((<undefinedtype>)var8).label;
               <undefinedtype> var9;
               if (var3 != 0) {
                  if (var3 != 1) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var1 = ((<undefinedtype>)var8).L$3;
                  Continuation var7 = (Continuation)((<undefinedtype>)var8).L$2;
                  var1 = ((<undefinedtype>)var8).L$1;
                  var9 = (<undefinedtype>)((<undefinedtype>)var8).L$0;
                  ResultKt.throwOnFailure(var11);
                  var8 = var11;
               } else {
                  ResultKt.throwOnFailure(var11);
                  Continuation var6 = (Continuation)var8;
                  Function2 var12 = this.$predicate$inlined;
                  ((<undefinedtype>)var8).L$0 = this;
                  ((<undefinedtype>)var8).L$1 = var1;
                  ((<undefinedtype>)var8).L$2 = var6;
                  ((<undefinedtype>)var8).L$3 = var1;
                  ((<undefinedtype>)var8).label = 1;
                  InlineMarker.mark(6);
                  var8 = var12.invoke(var1, var8);
                  InlineMarker.mark(7);
                  if (var8 == var5) {
                     return var5;
                  }

                  var9 = this;
               }

               if ((Boolean)var8) {
                  Ref.IntRef var10 = var9.$i$inlined;
                  ++var10.element;
                  var3 = var10.element;
               }

               return Unit.INSTANCE;
            }
         });
         ((<undefinedtype>)var9).L$0 = var0;
         ((<undefinedtype>)var9).L$1 = var1;
         ((<undefinedtype>)var9).L$2 = var11;
         ((<undefinedtype>)var9).L$3 = var0;
         ((<undefinedtype>)var9).label = 1;
         if (var0.collect(var6, (Continuation)var9) == var5) {
            return var5;
         }

         var7 = var11;
      }

      return Boxing.boxInt(var7.element);
   }
}
