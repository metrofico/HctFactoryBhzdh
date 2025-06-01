package kotlinx.coroutines.flow;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001aE\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a#\u0010\n\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001aG\u0010\n\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001ay\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\f*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\r\u001a\u0002H\f2H\b\u0004\u0010\u000e\u001aB\b\u0001\u0012\u0013\u0012\u0011H\f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000fH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001as\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0000\u0010\u0016\"\b\b\u0001\u0010\u0001*\u0002H\u0016*\b\u0012\u0004\u0012\u0002H\u00010\u00022F\u0010\u000e\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a!\u0010\u0019\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a#\u0010\u001a\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"},
   d2 = {"first", "T", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firstOrNull", "fold", "R", "initial", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "acc", "value", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reduce", "S", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "single", "singleOrNull", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__ReduceKt {
   public static final Object first(Flow var0, Continuation var1) {
      Object var10;
      label51: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var10 = var3;
               break label51;
            }
         }

         var10 = new ContinuationImpl(var1) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.first((Flow)null, this);
            }
         };
      }

      Ref.ObjectRef var9;
      label45: {
         Object var4 = ((<undefinedtype>)var10).result;
         Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         int var2 = ((<undefinedtype>)var10).label;
         AbortFlowException var13;
         Object var14;
         if (var2 != 0) {
            if (var2 != 1) {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            var14 = (<undefinedtype>)((<undefinedtype>)var10).L$3;
            var0 = (Flow)((<undefinedtype>)var10).L$2;
            var9 = (Ref.ObjectRef)((<undefinedtype>)var10).L$1;
            Flow var12 = (Flow)((<undefinedtype>)var10).L$0;

            try {
               ResultKt.throwOnFailure(var4);
               break label45;
            } catch (AbortFlowException var7) {
               var13 = var7;
            }
         } else {
            label56: {
               ResultKt.throwOnFailure(var4);
               Ref.ObjectRef var15 = new Ref.ObjectRef();
               var15.element = NullSurrogateKt.NULL;
               FlowCollector var16 = new FlowCollector(var15) {
                  final Ref.ObjectRef $result$inlined;

                  public {
                     this.$result$inlined = var1;
                  }

                  public Object emit(Object var1, Continuation var2) {
                     this.$result$inlined.element = var1;
                     if (Boxing.boxBoolean(false)) {
                        return Unit.INSTANCE;
                     } else {
                        throw (Throwable)(new AbortFlowException((FlowCollector)this));
                     }
                  }
               };

               Object var11;
               try {
                  FlowCollector var6 = (FlowCollector)var16;
                  ((<undefinedtype>)var10).L$0 = var0;
                  ((<undefinedtype>)var10).L$1 = var15;
                  ((<undefinedtype>)var10).L$2 = var0;
                  ((<undefinedtype>)var10).L$3 = var16;
                  ((<undefinedtype>)var10).label = 1;
                  var11 = var0.collect(var6, (Continuation)var10);
               } catch (AbortFlowException var8) {
                  var13 = var8;
                  var9 = var15;
                  var14 = var16;
                  break label56;
               }

               if (var11 == var5) {
                  return var5;
               }

               var9 = var15;
               break label45;
            }
         }

         FlowExceptions_commonKt.checkOwnership(var13, (FlowCollector)var14);
      }

      if (var9.element != NullSurrogateKt.NULL) {
         return var9.element;
      } else {
         throw (Throwable)(new NoSuchElementException("Expected at least one element"));
      }
   }

   public static final Object first(Flow var0, Function2 var1, Continuation var2) {
      Object var13;
      label51: {
         if (var2 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var2;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var13 = var4;
               break label51;
            }
         }

         var13 = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.first((Flow)null, (Function2)null, this);
            }
         };
      }

      Function2 var10;
      Ref.ObjectRef var12;
      label45: {
         Object var5 = ((<undefinedtype>)var13).result;
         Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         int var3 = ((<undefinedtype>)var13).label;
         AbortFlowException var15;
         Object var16;
         if (var3 != 0) {
            if (var3 != 1) {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            var16 = (<undefinedtype>)((<undefinedtype>)var13).L$4;
            var0 = (Flow)((<undefinedtype>)var13).L$3;
            var12 = (Ref.ObjectRef)((<undefinedtype>)var13).L$2;
            var10 = (Function2)((<undefinedtype>)var13).L$1;
            Flow var14 = (Flow)((<undefinedtype>)var13).L$0;

            try {
               ResultKt.throwOnFailure(var5);
               break label45;
            } catch (AbortFlowException var8) {
               var15 = var8;
            }
         } else {
            label56: {
               ResultKt.throwOnFailure(var5);
               Ref.ObjectRef var17 = new Ref.ObjectRef();
               var17.element = NullSurrogateKt.NULL;
               FlowCollector var18 = new FlowCollector(var1, var17) {
                  final Function2 $predicate$inlined;
                  final Ref.ObjectRef $result$inlined;

                  public {
                     this.$predicate$inlined = var1;
                     this.$result$inlined = var2;
                  }

                  public Object emit(Object var1, Continuation var2) {
                     Object var8;
                     label33: {
                        if (var2 instanceof <undefinedtype>) {
                           <undefinedtype> var5 = (<undefinedtype>)var2;
                           if ((var5.label & Integer.MIN_VALUE) != 0) {
                              var5.label += Integer.MIN_VALUE;
                              var8 = var5;
                              break label33;
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

                     Object var10 = ((<undefinedtype>)var8).result;
                     Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var3 = ((<undefinedtype>)var8).label;
                     boolean var4 = true;
                     <undefinedtype> var9;
                     if (var3 != 0) {
                        if (var3 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        var1 = ((<undefinedtype>)var8).L$3;
                        Continuation var12 = (Continuation)((<undefinedtype>)var8).L$2;
                        var6 = ((<undefinedtype>)var8).L$1;
                        var9 = (<undefinedtype>)((<undefinedtype>)var8).L$0;
                        ResultKt.throwOnFailure(var10);
                     } else {
                        ResultKt.throwOnFailure(var10);
                        Continuation var11 = (Continuation)var8;
                        Function2 var7 = this.$predicate$inlined;
                        ((<undefinedtype>)var8).L$0 = this;
                        ((<undefinedtype>)var8).L$1 = var1;
                        ((<undefinedtype>)var8).L$2 = var11;
                        ((<undefinedtype>)var8).L$3 = var1;
                        ((<undefinedtype>)var8).label = 1;
                        InlineMarker.mark(6);
                        var10 = var7.invoke(var1, var8);
                        InlineMarker.mark(7);
                        if (var10 == var6) {
                           return var6;
                        }

                        var9 = this;
                     }

                     if ((Boolean)var10) {
                        var9.$result$inlined.element = var1;
                        var4 = false;
                     }

                     if (Boxing.boxBoolean(var4)) {
                        return Unit.INSTANCE;
                     } else {
                        throw (Throwable)(new AbortFlowException((FlowCollector)var9));
                     }
                  }
               };

               Object var11;
               try {
                  FlowCollector var7 = (FlowCollector)var18;
                  ((<undefinedtype>)var13).L$0 = var0;
                  ((<undefinedtype>)var13).L$1 = var1;
                  ((<undefinedtype>)var13).L$2 = var17;
                  ((<undefinedtype>)var13).L$3 = var0;
                  ((<undefinedtype>)var13).L$4 = var18;
                  ((<undefinedtype>)var13).label = 1;
                  var11 = var0.collect(var7, (Continuation)var13);
               } catch (AbortFlowException var9) {
                  var15 = var9;
                  var10 = var1;
                  var12 = var17;
                  var16 = var18;
                  break label56;
               }

               if (var11 == var6) {
                  return var6;
               }

               var10 = var1;
               var12 = var17;
               break label45;
            }
         }

         FlowExceptions_commonKt.checkOwnership(var15, (FlowCollector)var16);
      }

      if (var12.element != NullSurrogateKt.NULL) {
         return var12.element;
      } else {
         throw (Throwable)(new NoSuchElementException("Expected at least one element matching the predicate " + var10));
      }
   }

   public static final Object firstOrNull(Flow var0, Continuation var1) {
      Object var10;
      label47: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var10 = var3;
               break label47;
            }
         }

         var10 = new ContinuationImpl(var1) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.firstOrNull((Flow)null, this);
            }
         };
      }

      Object var4 = ((<undefinedtype>)var10).result;
      Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var10).label;
      Ref.ObjectRef var9;
      AbortFlowException var13;
      Object var14;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var14 = (<undefinedtype>)((<undefinedtype>)var10).L$3;
         var0 = (Flow)((<undefinedtype>)var10).L$2;
         var9 = (Ref.ObjectRef)((<undefinedtype>)var10).L$1;
         Flow var12 = (Flow)((<undefinedtype>)var10).L$0;

         try {
            ResultKt.throwOnFailure(var4);
            return var9.element;
         } catch (AbortFlowException var7) {
            var13 = var7;
         }
      } else {
         label61: {
            ResultKt.throwOnFailure(var4);
            Ref.ObjectRef var15 = new Ref.ObjectRef();
            var15.element = null;
            FlowCollector var16 = new FlowCollector(var15) {
               final Ref.ObjectRef $result$inlined;

               public {
                  this.$result$inlined = var1;
               }

               public Object emit(Object var1, Continuation var2) {
                  this.$result$inlined.element = var1;
                  if (Boxing.boxBoolean(false)) {
                     return Unit.INSTANCE;
                  } else {
                     throw (Throwable)(new AbortFlowException((FlowCollector)this));
                  }
               }
            };

            Object var11;
            try {
               FlowCollector var6 = (FlowCollector)var16;
               ((<undefinedtype>)var10).L$0 = var0;
               ((<undefinedtype>)var10).L$1 = var15;
               ((<undefinedtype>)var10).L$2 = var0;
               ((<undefinedtype>)var10).L$3 = var16;
               ((<undefinedtype>)var10).label = 1;
               var11 = var0.collect(var6, (Continuation)var10);
            } catch (AbortFlowException var8) {
               var13 = var8;
               var9 = var15;
               var14 = var16;
               break label61;
            }

            if (var11 == var5) {
               return var5;
            }

            var9 = var15;
            return var9.element;
         }
      }

      FlowExceptions_commonKt.checkOwnership(var13, (FlowCollector)var14);
      return var9.element;
   }

   public static final Object firstOrNull(Flow var0, Function2 var1, Continuation var2) {
      Object var13;
      label47: {
         if (var2 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var2;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var13 = var4;
               break label47;
            }
         }

         var13 = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.firstOrNull((Flow)null, (Function2)null, this);
            }
         };
      }

      Object var5 = ((<undefinedtype>)var13).result;
      Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var3 = ((<undefinedtype>)var13).label;
      Ref.ObjectRef var10;
      AbortFlowException var15;
      if (var3 != 0) {
         if (var3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         <undefinedtype> var14 = (<undefinedtype>)((<undefinedtype>)var13).L$4;
         var0 = (Flow)((<undefinedtype>)var13).L$3;
         var10 = (Ref.ObjectRef)((<undefinedtype>)var13).L$2;
         var1 = (Function2)((<undefinedtype>)var13).L$1;
         Flow var12 = (Flow)((<undefinedtype>)var13).L$0;

         try {
            ResultKt.throwOnFailure(var5);
            return var10.element;
         } catch (AbortFlowException var8) {
            var13 = var14;
            var15 = var8;
         }
      } else {
         label61: {
            ResultKt.throwOnFailure(var5);
            Ref.ObjectRef var16 = new Ref.ObjectRef();
            var16.element = null;
            FlowCollector var17 = new FlowCollector(var1, var16) {
               final Function2 $predicate$inlined;
               final Ref.ObjectRef $result$inlined;

               public {
                  this.$predicate$inlined = var1;
                  this.$result$inlined = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var8;
                  label33: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var5 = (<undefinedtype>)var2;
                        if ((var5.label & Integer.MIN_VALUE) != 0) {
                           var5.label += Integer.MIN_VALUE;
                           var8 = var5;
                           break label33;
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

                  Object var10 = ((<undefinedtype>)var8).result;
                  Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var8).label;
                  boolean var4 = true;
                  <undefinedtype> var9;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     var1 = ((<undefinedtype>)var8).L$3;
                     Continuation var12 = (Continuation)((<undefinedtype>)var8).L$2;
                     var6 = ((<undefinedtype>)var8).L$1;
                     var9 = (<undefinedtype>)((<undefinedtype>)var8).L$0;
                     ResultKt.throwOnFailure(var10);
                  } else {
                     ResultKt.throwOnFailure(var10);
                     Continuation var7 = (Continuation)var8;
                     Function2 var11 = this.$predicate$inlined;
                     ((<undefinedtype>)var8).L$0 = this;
                     ((<undefinedtype>)var8).L$1 = var1;
                     ((<undefinedtype>)var8).L$2 = var7;
                     ((<undefinedtype>)var8).L$3 = var1;
                     ((<undefinedtype>)var8).label = 1;
                     InlineMarker.mark(6);
                     var10 = var11.invoke(var1, var8);
                     InlineMarker.mark(7);
                     if (var10 == var6) {
                        return var6;
                     }

                     var9 = this;
                  }

                  if ((Boolean)var10) {
                     var9.$result$inlined.element = var1;
                     var4 = false;
                  }

                  if (Boxing.boxBoolean(var4)) {
                     return Unit.INSTANCE;
                  } else {
                     throw (Throwable)(new AbortFlowException((FlowCollector)var9));
                  }
               }
            };

            Object var11;
            try {
               FlowCollector var7 = (FlowCollector)var17;
               ((<undefinedtype>)var13).L$0 = var0;
               ((<undefinedtype>)var13).L$1 = var1;
               ((<undefinedtype>)var13).L$2 = var16;
               ((<undefinedtype>)var13).L$3 = var0;
               ((<undefinedtype>)var13).L$4 = var17;
               ((<undefinedtype>)var13).label = 1;
               var11 = var0.collect(var7, (Continuation)var13);
            } catch (AbortFlowException var9) {
               var10 = var16;
               var15 = var9;
               var13 = var17;
               break label61;
            }

            if (var11 == var6) {
               return var6;
            }

            var10 = var16;
            return var10.element;
         }
      }

      FlowExceptions_commonKt.checkOwnership(var15, (FlowCollector)var13);
      return var10.element;
   }

   public static final Object fold(Flow var0, Object var1, Function3 var2, Continuation var3) {
      Object var11;
      label25: {
         if (var3 instanceof <undefinedtype>) {
            <undefinedtype> var5 = (<undefinedtype>)var3;
            if ((var5.label & Integer.MIN_VALUE) != 0) {
               var5.label += Integer.MIN_VALUE;
               var11 = var5;
               break label25;
            }
         }

         var11 = new ContinuationImpl(var3) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.fold((Flow)null, (Object)null, (Function3)null, this);
            }
         };
      }

      Object var12 = ((<undefinedtype>)var11).result;
      Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var4 = ((<undefinedtype>)var11).label;
      Ref.ObjectRef var8;
      if (var4 != 0) {
         if (var4 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Flow)((<undefinedtype>)var11).L$4;
         var8 = (Ref.ObjectRef)((<undefinedtype>)var11).L$3;
         Function3 var9 = (Function3)((<undefinedtype>)var11).L$2;
         var1 = ((<undefinedtype>)var11).L$1;
         Flow var10 = (Flow)((<undefinedtype>)var11).L$0;
         ResultKt.throwOnFailure(var12);
      } else {
         ResultKt.throwOnFailure(var12);
         Ref.ObjectRef var13 = new Ref.ObjectRef();
         var13.element = var1;
         FlowCollector var7 = (FlowCollector)(new FlowCollector(var13, var2) {
            final Ref.ObjectRef $accumulator$inlined;
            final Function3 $operation$inlined;

            public {
               this.$accumulator$inlined = var1;
               this.$operation$inlined = var2;
            }

            public Object emit(Object var1, Continuation var2) {
               Object var10;
               label25: {
                  if (var2 instanceof <undefinedtype>) {
                     <undefinedtype> var4 = (<undefinedtype>)var2;
                     if ((var4.label & Integer.MIN_VALUE) != 0) {
                        var4.label += Integer.MIN_VALUE;
                        var10 = var4;
                        break label25;
                     }
                  }

                  var10 = new ContinuationImpl(this, var2) {
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

               Object var12 = ((<undefinedtype>)var10).result;
               Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               int var3 = ((<undefinedtype>)var10).label;
               Ref.ObjectRef var9;
               if (var3 != 0) {
                  if (var3 != 1) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var9 = (Ref.ObjectRef)((<undefinedtype>)var10).L$4;
                  var5 = ((<undefinedtype>)var10).L$3;
                  Continuation var14 = (Continuation)((<undefinedtype>)var10).L$2;
                  var5 = ((<undefinedtype>)var10).L$1;
                  <undefinedtype> var11 = (<undefinedtype>)((<undefinedtype>)var10).L$0;
                  ResultKt.throwOnFailure(var12);
                  var10 = var12;
               } else {
                  ResultKt.throwOnFailure(var12);
                  Continuation var7 = (Continuation)var10;
                  Ref.ObjectRef var13 = this.$accumulator$inlined;
                  Function3 var6 = this.$operation$inlined;
                  Object var8 = var13.element;
                  ((<undefinedtype>)var10).L$0 = this;
                  ((<undefinedtype>)var10).L$1 = var1;
                  ((<undefinedtype>)var10).L$2 = var7;
                  ((<undefinedtype>)var10).L$3 = var1;
                  ((<undefinedtype>)var10).L$4 = var13;
                  ((<undefinedtype>)var10).label = 1;
                  var10 = var6.invoke(var8, var1, var10);
                  if (var10 == var5) {
                     return var5;
                  }

                  var9 = var13;
               }

               var9.element = var10;
               return Unit.INSTANCE;
            }

            public Object emit$$forInline(Object var1, Continuation var2) {
               InlineMarker.mark(4);
               ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
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
               InlineMarker.mark(5);
               Ref.ObjectRef var3 = this.$accumulator$inlined;
               var3.element = this.$operation$inlined.invoke(var3.element, var1, var2);
               return Unit.INSTANCE;
            }
         });
         ((<undefinedtype>)var11).L$0 = var0;
         ((<undefinedtype>)var11).L$1 = var1;
         ((<undefinedtype>)var11).L$2 = var2;
         ((<undefinedtype>)var11).L$3 = var13;
         ((<undefinedtype>)var11).L$4 = var0;
         ((<undefinedtype>)var11).label = 1;
         if (var0.collect(var7, (Continuation)var11) == var6) {
            return var6;
         }

         var8 = var13;
      }

      return var8.element;
   }

   private static final Object fold$$forInline(Flow var0, Object var1, Function3 var2, Continuation var3) {
      Ref.ObjectRef var4 = new Ref.ObjectRef();
      var4.element = var1;
      FlowCollector var5 = (FlowCollector)(new FlowCollector(var4, var2) {
         final Ref.ObjectRef $accumulator$inlined;
         final Function3 $operation$inlined;

         public {
            this.$accumulator$inlined = var1;
            this.$operation$inlined = var2;
         }

         public Object emit(Object var1, Continuation var2) {
            Object var10;
            label25: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var10 = var4;
                     break label25;
                  }
               }

               var10 = new ContinuationImpl(this, var2) {
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

            Object var12 = ((<undefinedtype>)var10).result;
            Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var10).label;
            Ref.ObjectRef var9;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var9 = (Ref.ObjectRef)((<undefinedtype>)var10).L$4;
               var5 = ((<undefinedtype>)var10).L$3;
               Continuation var14 = (Continuation)((<undefinedtype>)var10).L$2;
               var5 = ((<undefinedtype>)var10).L$1;
               <undefinedtype> var11 = (<undefinedtype>)((<undefinedtype>)var10).L$0;
               ResultKt.throwOnFailure(var12);
               var10 = var12;
            } else {
               ResultKt.throwOnFailure(var12);
               Continuation var7 = (Continuation)var10;
               Ref.ObjectRef var13 = this.$accumulator$inlined;
               Function3 var6 = this.$operation$inlined;
               Object var8 = var13.element;
               ((<undefinedtype>)var10).L$0 = this;
               ((<undefinedtype>)var10).L$1 = var1;
               ((<undefinedtype>)var10).L$2 = var7;
               ((<undefinedtype>)var10).L$3 = var1;
               ((<undefinedtype>)var10).L$4 = var13;
               ((<undefinedtype>)var10).label = 1;
               var10 = var6.invoke(var8, var1, var10);
               if (var10 == var5) {
                  return var5;
               }

               var9 = var13;
            }

            var9.element = var10;
            return Unit.INSTANCE;
         }

         public Object emit$$forInline(Object var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
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
            InlineMarker.mark(5);
            Ref.ObjectRef var3 = this.$accumulator$inlined;
            var3.element = this.$operation$inlined.invoke(var3.element, var1, var2);
            return Unit.INSTANCE;
         }
      });
      InlineMarker.mark(0);
      var0.collect(var5, var3);
      InlineMarker.mark(2);
      InlineMarker.mark(1);
      return var4.element;
   }

   public static final Object reduce(Flow var0, Function3 var1, Continuation var2) {
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
               return FlowKt.reduce((Flow)null, (Function3)null, this);
            }
         };
      }

      Object var10 = ((<undefinedtype>)var9).result;
      Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var3 = ((<undefinedtype>)var9).label;
      Ref.ObjectRef var7;
      if (var3 != 0) {
         if (var3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Flow)((<undefinedtype>)var9).L$3;
         var7 = (Ref.ObjectRef)((<undefinedtype>)var9).L$2;
         var1 = (Function3)((<undefinedtype>)var9).L$1;
         Flow var8 = (Flow)((<undefinedtype>)var9).L$0;
         ResultKt.throwOnFailure(var10);
      } else {
         ResultKt.throwOnFailure(var10);
         Ref.ObjectRef var11 = new Ref.ObjectRef();
         var11.element = NullSurrogateKt.NULL;
         FlowCollector var6 = (FlowCollector)(new FlowCollector(var11, var1) {
            final Ref.ObjectRef $accumulator$inlined;
            final Function3 $operation$inlined;

            public {
               this.$accumulator$inlined = var1;
               this.$operation$inlined = var2;
            }

            public Object emit(Object var1, Continuation var2) {
               Object var4;
               label25: {
                  if (var2 instanceof <undefinedtype>) {
                     var4 = (<undefinedtype>)var2;
                     if ((((<undefinedtype>)var4).label & Integer.MIN_VALUE) != 0) {
                        ((<undefinedtype>)var4).label += Integer.MIN_VALUE;
                        break label25;
                     }
                  }

                  var4 = new ContinuationImpl(this, var2) {
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

               Object var10 = ((<undefinedtype>)var4).result;
               Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               int var3 = ((<undefinedtype>)var4).label;
               Ref.ObjectRef var5;
               if (var3 != 0) {
                  if (var3 != 1) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var5 = (Ref.ObjectRef)((<undefinedtype>)var4).L$4;
                  var1 = ((<undefinedtype>)var4).L$3;
                  Continuation var9 = (Continuation)((<undefinedtype>)var4).L$2;
                  var1 = ((<undefinedtype>)var4).L$1;
                  <undefinedtype> var11 = (<undefinedtype>)((<undefinedtype>)var4).L$0;
                  ResultKt.throwOnFailure(var10);
               } else {
                  ResultKt.throwOnFailure(var10);
                  Continuation var8 = (Continuation)var4;
                  Ref.ObjectRef var6 = this.$accumulator$inlined;
                  var5 = var6;
                  var10 = var1;
                  if (var6.element != NullSurrogateKt.NULL) {
                     Function3 var12 = this.$operation$inlined;
                     Object var13 = this.$accumulator$inlined.element;
                     ((<undefinedtype>)var4).L$0 = this;
                     ((<undefinedtype>)var4).L$1 = var1;
                     ((<undefinedtype>)var4).L$2 = var8;
                     ((<undefinedtype>)var4).L$3 = var1;
                     ((<undefinedtype>)var4).L$4 = var6;
                     ((<undefinedtype>)var4).label = 1;
                     InlineMarker.mark(6);
                     var1 = var12.invoke(var13, var1, var4);
                     InlineMarker.mark(7);
                     var5 = var6;
                     var10 = var1;
                     if (var1 == var7) {
                        return var7;
                     }
                  }
               }

               var5.element = var10;
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

      if (var7.element != NullSurrogateKt.NULL) {
         return var7.element;
      } else {
         throw (Throwable)(new NoSuchElementException("Empty flow can't be reduced"));
      }
   }

   public static final Object single(Flow var0, Continuation var1) {
      Object var7;
      label29: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var7 = var3;
               break label29;
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
               return FlowKt.single((Flow)null, this);
            }
         };
      }

      Object var9 = ((<undefinedtype>)var7).result;
      Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var7).label;
      Ref.ObjectRef var6;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Flow)((<undefinedtype>)var7).L$2;
         var6 = (Ref.ObjectRef)((<undefinedtype>)var7).L$1;
         Flow var8 = (Flow)((<undefinedtype>)var7).L$0;
         ResultKt.throwOnFailure(var9);
      } else {
         ResultKt.throwOnFailure(var9);
         Ref.ObjectRef var10 = new Ref.ObjectRef();
         var10.element = NullSurrogateKt.NULL;
         FlowCollector var5 = (FlowCollector)(new FlowCollector(var10) {
            final Ref.ObjectRef $result$inlined;

            public {
               this.$result$inlined = var1;
            }

            public Object emit(Object var1, Continuation var2) {
               boolean var3;
               if (this.$result$inlined.element == NullSurrogateKt.NULL) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               if (var3) {
                  this.$result$inlined.element = var1;
                  return Unit.INSTANCE;
               } else {
                  throw (Throwable)(new IllegalArgumentException("Flow has more than one element".toString()));
               }
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

      if (var6.element != NullSurrogateKt.NULL) {
         return var6.element;
      } else {
         throw (Throwable)(new NoSuchElementException("Flow is empty"));
      }
   }

   public static final Object singleOrNull(Flow var0, Continuation var1) {
      Object var10;
      label52: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var10 = var3;
               break label52;
            }
         }

         var10 = new ContinuationImpl(var1) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.singleOrNull((Flow)null, this);
            }
         };
      }

      Ref.ObjectRef var9;
      Object var11;
      label46: {
         Object var4 = ((<undefinedtype>)var10).result;
         Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         int var2 = ((<undefinedtype>)var10).label;
         AbortFlowException var13;
         Object var14;
         if (var2 != 0) {
            if (var2 != 1) {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            var14 = (<undefinedtype>)((<undefinedtype>)var10).L$3;
            var0 = (Flow)((<undefinedtype>)var10).L$2;
            var9 = (Ref.ObjectRef)((<undefinedtype>)var10).L$1;
            Flow var12 = (Flow)((<undefinedtype>)var10).L$0;

            try {
               ResultKt.throwOnFailure(var4);
               break label46;
            } catch (AbortFlowException var7) {
               var13 = var7;
            }
         } else {
            label57: {
               ResultKt.throwOnFailure(var4);
               Ref.ObjectRef var15 = new Ref.ObjectRef();
               var15.element = NullSurrogateKt.NULL;
               FlowCollector var16 = new FlowCollector(var15) {
                  final Ref.ObjectRef $result$inlined;

                  public {
                     this.$result$inlined = var1;
                  }

                  public Object emit(Object var1, Continuation var2) {
                     boolean var3;
                     if (this.$result$inlined.element == NullSurrogateKt.NULL) {
                        this.$result$inlined.element = var1;
                        var3 = true;
                     } else {
                        this.$result$inlined.element = NullSurrogateKt.NULL;
                        var3 = false;
                     }

                     if (Boxing.boxBoolean(var3)) {
                        return Unit.INSTANCE;
                     } else {
                        throw (Throwable)(new AbortFlowException((FlowCollector)this));
                     }
                  }
               };

               try {
                  FlowCollector var6 = (FlowCollector)var16;
                  ((<undefinedtype>)var10).L$0 = var0;
                  ((<undefinedtype>)var10).L$1 = var15;
                  ((<undefinedtype>)var10).L$2 = var0;
                  ((<undefinedtype>)var10).L$3 = var16;
                  ((<undefinedtype>)var10).label = 1;
                  var11 = var0.collect(var6, (Continuation)var10);
               } catch (AbortFlowException var8) {
                  var13 = var8;
                  var9 = var15;
                  var14 = var16;
                  break label57;
               }

               if (var11 == var5) {
                  return var5;
               }

               var9 = var15;
               break label46;
            }
         }

         FlowExceptions_commonKt.checkOwnership(var13, (FlowCollector)var14);
      }

      if (var9.element == NullSurrogateKt.NULL) {
         var11 = null;
      } else {
         var11 = var9.element;
      }

      return var11;
   }
}
