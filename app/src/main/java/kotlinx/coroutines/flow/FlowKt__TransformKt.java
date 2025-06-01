package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001aM\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012$\b\u0004\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0006\b\u0000\u0010\n\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0001H\u0086\b\u001aM\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012$\b\u0004\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\"\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0007*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\u001ab\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u000123\b\u0004\u0010\u000e\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u001ah\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\n*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00020\u000125\b\u0004\u0010\u000e\u001a/\b\u0001\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\n0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u001aH\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\"\u0010\u0014\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004ø\u0001\u0000¢\u0006\u0002\u0010\b\u001an\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012F\u0010\u0017\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0018H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a~\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u001c\u001a\u0002H\n2H\b\u0001\u0010\u0017\u001aB\b\u0001\u0012\u0013\u0012\u0011H\n¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0018H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\"\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u001f0\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "},
   d2 = {"filter", "Lkotlinx/coroutines/flow/Flow;", "T", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "filterIsInstance", "R", "filterNot", "filterNotNull", "map", "transform", "Lkotlin/ParameterName;", "name", "value", "mapNotNull", "onEach", "action", "", "runningReduce", "operation", "Lkotlin/Function3;", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "scan", "initial", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "withIndex", "Lkotlin/collections/IndexedValue;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__TransformKt {
   public static final Flow filter(Flow var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function2 $predicate$inlined;
         final Flow $this_unsafeTransform$inlined;

         public {
            this.$this_unsafeTransform$inlined = var1;
            this.$predicate$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = this.$this_unsafeTransform$inlined.collect((FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var15;
                  label41: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var15 = var4;
                           break label41;
                        }
                     }

                     var15 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
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

                  Unit var21;
                  label44: {
                     Object var8 = ((<undefinedtype>)var15).result;
                     Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var3 = ((<undefinedtype>)var15).label;
                     <undefinedtype> var5;
                     FlowCollector var6;
                     Continuation var7;
                     Object var10;
                     Object var11;
                     Object var19;
                     if (var3 != 0) {
                        if (var3 != 1) {
                           if (var3 != 2) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           FlowCollector var14 = (FlowCollector)((<undefinedtype>)var15).L$6;
                           var1 = ((<undefinedtype>)var15).L$5;
                           Continuation var16 = (Continuation)((<undefinedtype>)var15).L$4;
                           var1 = ((<undefinedtype>)var15).L$3;
                           <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                           var1 = ((<undefinedtype>)var15).L$1;
                           <undefinedtype> var18 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                           ResultKt.throwOnFailure(var8);
                           break label44;
                        }

                        var6 = (FlowCollector)((<undefinedtype>)var15).L$6;
                        var1 = ((<undefinedtype>)var15).L$5;
                        var7 = (Continuation)((<undefinedtype>)var15).L$4;
                        var19 = ((<undefinedtype>)var15).L$3;
                        var10 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                        var11 = ((<undefinedtype>)var15).L$1;
                        var5 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                        ResultKt.throwOnFailure(var8);
                     } else {
                        ResultKt.throwOnFailure(var8);
                        var6 = this.$this_unsafeFlow$inlined;
                        Continuation var12 = (Continuation)var15;
                        Function2 var20 = this.this$0.$predicate$inlined;
                        ((<undefinedtype>)var15).L$0 = this;
                        ((<undefinedtype>)var15).L$1 = var1;
                        ((<undefinedtype>)var15).L$2 = var15;
                        ((<undefinedtype>)var15).L$3 = var1;
                        ((<undefinedtype>)var15).L$4 = var12;
                        ((<undefinedtype>)var15).L$5 = var1;
                        ((<undefinedtype>)var15).L$6 = var6;
                        ((<undefinedtype>)var15).label = 1;
                        var8 = var20.invoke(var1, var15);
                        if (var8 == var13) {
                           return var13;
                        }

                        var5 = this;
                        var11 = var1;
                        var10 = var15;
                        var19 = var1;
                        var7 = var12;
                        var1 = var1;
                     }

                     if (!(Boolean)var8) {
                        var21 = Unit.INSTANCE;
                        return var21;
                     }

                     ((<undefinedtype>)var15).L$0 = var5;
                     ((<undefinedtype>)var15).L$1 = var11;
                     ((<undefinedtype>)var15).L$2 = var10;
                     ((<undefinedtype>)var15).L$3 = var19;
                     ((<undefinedtype>)var15).L$4 = var7;
                     ((<undefinedtype>)var15).L$5 = var1;
                     ((<undefinedtype>)var15).L$6 = var6;
                     ((<undefinedtype>)var15).label = 2;
                     if (var6.emit(var1, (Continuation)var15) == var13) {
                        return var13;
                     }
                  }

                  var21 = Unit.INSTANCE;
                  return var21;
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  if ((Boolean)this.this$0.$predicate$inlined.invoke(var1, var2)) {
                     InlineMarker.mark(0);
                     var1 = var3.emit(var1, var2);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                  } else {
                     var1 = Unit.INSTANCE;
                  }

                  return var1;
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }

         public Object collect$$forInline(FlowCollector var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
               int label;
               Object result;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object invokeSuspend(Object var1) {
                  this.result = var1;
                  this.label |= Integer.MIN_VALUE;
                  return this.this$0.collect((FlowCollector)null, this);
               }
            };
            InlineMarker.mark(5);
            Flow var3 = this.$this_unsafeTransform$inlined;
            var1 = (FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var15;
                  label41: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var15 = var4;
                           break label41;
                        }
                     }

                     var15 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
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

                  Unit var21;
                  label44: {
                     Object var8 = ((<undefinedtype>)var15).result;
                     Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var3 = ((<undefinedtype>)var15).label;
                     <undefinedtype> var5;
                     FlowCollector var6;
                     Continuation var7;
                     Object var10;
                     Object var11;
                     Object var19;
                     if (var3 != 0) {
                        if (var3 != 1) {
                           if (var3 != 2) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           FlowCollector var14 = (FlowCollector)((<undefinedtype>)var15).L$6;
                           var1 = ((<undefinedtype>)var15).L$5;
                           Continuation var16 = (Continuation)((<undefinedtype>)var15).L$4;
                           var1 = ((<undefinedtype>)var15).L$3;
                           <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                           var1 = ((<undefinedtype>)var15).L$1;
                           <undefinedtype> var18 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                           ResultKt.throwOnFailure(var8);
                           break label44;
                        }

                        var6 = (FlowCollector)((<undefinedtype>)var15).L$6;
                        var1 = ((<undefinedtype>)var15).L$5;
                        var7 = (Continuation)((<undefinedtype>)var15).L$4;
                        var19 = ((<undefinedtype>)var15).L$3;
                        var10 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                        var11 = ((<undefinedtype>)var15).L$1;
                        var5 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                        ResultKt.throwOnFailure(var8);
                     } else {
                        ResultKt.throwOnFailure(var8);
                        var6 = this.$this_unsafeFlow$inlined;
                        Continuation var12 = (Continuation)var15;
                        Function2 var20 = this.this$0.$predicate$inlined;
                        ((<undefinedtype>)var15).L$0 = this;
                        ((<undefinedtype>)var15).L$1 = var1;
                        ((<undefinedtype>)var15).L$2 = var15;
                        ((<undefinedtype>)var15).L$3 = var1;
                        ((<undefinedtype>)var15).L$4 = var12;
                        ((<undefinedtype>)var15).L$5 = var1;
                        ((<undefinedtype>)var15).L$6 = var6;
                        ((<undefinedtype>)var15).label = 1;
                        var8 = var20.invoke(var1, var15);
                        if (var8 == var13) {
                           return var13;
                        }

                        var5 = this;
                        var11 = var1;
                        var10 = var15;
                        var19 = var1;
                        var7 = var12;
                        var1 = var1;
                     }

                     if (!(Boolean)var8) {
                        var21 = Unit.INSTANCE;
                        return var21;
                     }

                     ((<undefinedtype>)var15).L$0 = var5;
                     ((<undefinedtype>)var15).L$1 = var11;
                     ((<undefinedtype>)var15).L$2 = var10;
                     ((<undefinedtype>)var15).L$3 = var19;
                     ((<undefinedtype>)var15).L$4 = var7;
                     ((<undefinedtype>)var15).L$5 = var1;
                     ((<undefinedtype>)var15).L$6 = var6;
                     ((<undefinedtype>)var15).label = 2;
                     if (var6.emit(var1, (Continuation)var15) == var13) {
                        return var13;
                     }
                  }

                  var21 = Unit.INSTANCE;
                  return var21;
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  if ((Boolean)this.this$0.$predicate$inlined.invoke(var1, var2)) {
                     InlineMarker.mark(0);
                     var1 = var3.emit(var1, var2);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                  } else {
                     var1 = Unit.INSTANCE;
                  }

                  return var1;
               }
            });
            InlineMarker.mark(0);
            var3.collect(var1, var2);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      });
   }

   // $FF: synthetic method
   public static final Flow filterIsInstance(Flow var0) {
      Intrinsics.needClassReification();
      return (Flow)(new Flow(var0) {
         final Flow $this_unsafeTransform$inlined;

         public {
            this.$this_unsafeTransform$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Flow var3 = this.$this_unsafeTransform$inlined;
            Intrinsics.needClassReification();
            Object var4 = var3.collect((FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var9;
                  label30: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var9 = var4;
                           break label30;
                        }
                     }

                     var9 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
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

                  Object var12 = ((<undefinedtype>)var9).result;
                  Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var9).label;
                  Unit var11;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     FlowCollector var7 = (FlowCollector)((<undefinedtype>)var9).L$6;
                     var1 = ((<undefinedtype>)var9).L$5;
                     <undefinedtype> var8 = (<undefinedtype>)((<undefinedtype>)var9).L$4;
                     var1 = ((<undefinedtype>)var9).L$3;
                     var8 = (<undefinedtype>)((<undefinedtype>)var9).L$2;
                     var1 = ((<undefinedtype>)var9).L$1;
                     <undefinedtype> var10 = (<undefinedtype>)((<undefinedtype>)var9).L$0;
                     ResultKt.throwOnFailure(var12);
                  } else {
                     ResultKt.throwOnFailure(var12);
                     FlowCollector var6 = this.$this_unsafeFlow$inlined;
                     Continuation var13 = (Continuation)var9;
                     Intrinsics.reifiedOperationMarker(3, "R");
                     if (!Boxing.boxBoolean(var1 instanceof Object)) {
                        var11 = Unit.INSTANCE;
                        return var11;
                     }

                     ((<undefinedtype>)var9).L$0 = this;
                     ((<undefinedtype>)var9).L$1 = var1;
                     ((<undefinedtype>)var9).L$2 = var9;
                     ((<undefinedtype>)var9).L$3 = var1;
                     ((<undefinedtype>)var9).L$4 = var9;
                     ((<undefinedtype>)var9).L$5 = var1;
                     ((<undefinedtype>)var9).L$6 = var6;
                     ((<undefinedtype>)var9).label = 1;
                     if (var6.emit(var1, (Continuation)var9) == var5) {
                        return var5;
                     }
                  }

                  var11 = Unit.INSTANCE;
                  return var11;
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  Intrinsics.reifiedOperationMarker(3, "R");
                  if (var1 instanceof Object) {
                     InlineMarker.mark(0);
                     var1 = var3.emit(var1, var2);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                  } else {
                     var1 = Unit.INSTANCE;
                  }

                  return var1;
               }
            }), var2);
            return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
         }

         public Object collect$$forInline(FlowCollector var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
               int label;
               Object result;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object invokeSuspend(Object var1) {
                  this.result = var1;
                  this.label |= Integer.MIN_VALUE;
                  return this.this$0.collect((FlowCollector)null, this);
               }
            };
            InlineMarker.mark(5);
            Flow var3 = this.$this_unsafeTransform$inlined;
            Intrinsics.needClassReification();
            var1 = (FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var9;
                  label30: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var9 = var4;
                           break label30;
                        }
                     }

                     var9 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
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

                  Object var12 = ((<undefinedtype>)var9).result;
                  Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var9).label;
                  Unit var11;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     FlowCollector var7 = (FlowCollector)((<undefinedtype>)var9).L$6;
                     var1 = ((<undefinedtype>)var9).L$5;
                     <undefinedtype> var8 = (<undefinedtype>)((<undefinedtype>)var9).L$4;
                     var1 = ((<undefinedtype>)var9).L$3;
                     var8 = (<undefinedtype>)((<undefinedtype>)var9).L$2;
                     var1 = ((<undefinedtype>)var9).L$1;
                     <undefinedtype> var10 = (<undefinedtype>)((<undefinedtype>)var9).L$0;
                     ResultKt.throwOnFailure(var12);
                  } else {
                     ResultKt.throwOnFailure(var12);
                     FlowCollector var6 = this.$this_unsafeFlow$inlined;
                     Continuation var13 = (Continuation)var9;
                     Intrinsics.reifiedOperationMarker(3, "R");
                     if (!Boxing.boxBoolean(var1 instanceof Object)) {
                        var11 = Unit.INSTANCE;
                        return var11;
                     }

                     ((<undefinedtype>)var9).L$0 = this;
                     ((<undefinedtype>)var9).L$1 = var1;
                     ((<undefinedtype>)var9).L$2 = var9;
                     ((<undefinedtype>)var9).L$3 = var1;
                     ((<undefinedtype>)var9).L$4 = var9;
                     ((<undefinedtype>)var9).L$5 = var1;
                     ((<undefinedtype>)var9).L$6 = var6;
                     ((<undefinedtype>)var9).label = 1;
                     if (var6.emit(var1, (Continuation)var9) == var5) {
                        return var5;
                     }
                  }

                  var11 = Unit.INSTANCE;
                  return var11;
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  Intrinsics.reifiedOperationMarker(3, "R");
                  if (var1 instanceof Object) {
                     InlineMarker.mark(0);
                     var1 = var3.emit(var1, var2);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                  } else {
                     var1 = Unit.INSTANCE;
                  }

                  return var1;
               }
            });
            InlineMarker.mark(0);
            var3.collect(var1, var2);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow filterNot(Flow var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function2 $predicate$inlined;
         final Flow $this_unsafeTransform$inlined;

         public {
            this.$this_unsafeTransform$inlined = var1;
            this.$predicate$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = this.$this_unsafeTransform$inlined.collect((FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var15;
                  label41: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var15 = var4;
                           break label41;
                        }
                     }

                     var15 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
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

                  Unit var20;
                  label44: {
                     Object var6 = ((<undefinedtype>)var15).result;
                     Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var3 = ((<undefinedtype>)var15).label;
                     Object var5;
                     Object var7;
                     FlowCollector var8;
                     <undefinedtype> var9;
                     Object var10;
                     Continuation var13;
                     Object var18;
                     if (var3 != 0) {
                        if (var3 != 1) {
                           if (var3 != 2) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           FlowCollector var14 = (FlowCollector)((<undefinedtype>)var15).L$6;
                           var1 = ((<undefinedtype>)var15).L$5;
                           var13 = (Continuation)((<undefinedtype>)var15).L$4;
                           var1 = ((<undefinedtype>)var15).L$3;
                           <undefinedtype> var16 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                           var1 = ((<undefinedtype>)var15).L$1;
                           <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                           ResultKt.throwOnFailure(var6);
                           break label44;
                        }

                        var8 = (FlowCollector)((<undefinedtype>)var15).L$6;
                        var7 = ((<undefinedtype>)var15).L$5;
                        var13 = (Continuation)((<undefinedtype>)var15).L$4;
                        var5 = ((<undefinedtype>)var15).L$3;
                        var10 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                        var18 = ((<undefinedtype>)var15).L$1;
                        var9 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                        ResultKt.throwOnFailure(var6);
                     } else {
                        ResultKt.throwOnFailure(var6);
                        var8 = this.$this_unsafeFlow$inlined;
                        Continuation var11 = (Continuation)var15;
                        Function2 var19 = this.this$0.$predicate$inlined;
                        ((<undefinedtype>)var15).L$0 = this;
                        ((<undefinedtype>)var15).L$1 = var1;
                        ((<undefinedtype>)var15).L$2 = var15;
                        ((<undefinedtype>)var15).L$3 = var1;
                        ((<undefinedtype>)var15).L$4 = var11;
                        ((<undefinedtype>)var15).L$5 = var1;
                        ((<undefinedtype>)var15).L$6 = var8;
                        ((<undefinedtype>)var15).label = 1;
                        var6 = var19.invoke(var1, var15);
                        if (var6 == var12) {
                           return var12;
                        }

                        var9 = this;
                        var18 = var1;
                        var10 = var15;
                        var7 = var1;
                        var5 = var1;
                        var13 = var11;
                     }

                     if ((Boolean)var6) {
                        var20 = Unit.INSTANCE;
                        return var20;
                     }

                     ((<undefinedtype>)var15).L$0 = var9;
                     ((<undefinedtype>)var15).L$1 = var18;
                     ((<undefinedtype>)var15).L$2 = var10;
                     ((<undefinedtype>)var15).L$3 = var5;
                     ((<undefinedtype>)var15).L$4 = var13;
                     ((<undefinedtype>)var15).L$5 = var7;
                     ((<undefinedtype>)var15).L$6 = var8;
                     ((<undefinedtype>)var15).label = 2;
                     if (var8.emit(var7, (Continuation)var15) == var12) {
                        return var12;
                     }
                  }

                  var20 = Unit.INSTANCE;
                  return var20;
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  if (!(Boolean)this.this$0.$predicate$inlined.invoke(var1, var2)) {
                     InlineMarker.mark(0);
                     var1 = var3.emit(var1, var2);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                  } else {
                     var1 = Unit.INSTANCE;
                  }

                  return var1;
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }

         public Object collect$$forInline(FlowCollector var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
               int label;
               Object result;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object invokeSuspend(Object var1) {
                  this.result = var1;
                  this.label |= Integer.MIN_VALUE;
                  return this.this$0.collect((FlowCollector)null, this);
               }
            };
            InlineMarker.mark(5);
            Flow var3 = this.$this_unsafeTransform$inlined;
            var1 = (FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var15;
                  label41: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var15 = var4;
                           break label41;
                        }
                     }

                     var15 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
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

                  Unit var20;
                  label44: {
                     Object var6 = ((<undefinedtype>)var15).result;
                     Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var3 = ((<undefinedtype>)var15).label;
                     Object var5;
                     Object var7;
                     FlowCollector var8;
                     <undefinedtype> var9;
                     Object var10;
                     Continuation var13;
                     Object var18;
                     if (var3 != 0) {
                        if (var3 != 1) {
                           if (var3 != 2) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           FlowCollector var14 = (FlowCollector)((<undefinedtype>)var15).L$6;
                           var1 = ((<undefinedtype>)var15).L$5;
                           var13 = (Continuation)((<undefinedtype>)var15).L$4;
                           var1 = ((<undefinedtype>)var15).L$3;
                           <undefinedtype> var16 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                           var1 = ((<undefinedtype>)var15).L$1;
                           <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                           ResultKt.throwOnFailure(var6);
                           break label44;
                        }

                        var8 = (FlowCollector)((<undefinedtype>)var15).L$6;
                        var7 = ((<undefinedtype>)var15).L$5;
                        var13 = (Continuation)((<undefinedtype>)var15).L$4;
                        var5 = ((<undefinedtype>)var15).L$3;
                        var10 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                        var18 = ((<undefinedtype>)var15).L$1;
                        var9 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                        ResultKt.throwOnFailure(var6);
                     } else {
                        ResultKt.throwOnFailure(var6);
                        var8 = this.$this_unsafeFlow$inlined;
                        Continuation var11 = (Continuation)var15;
                        Function2 var19 = this.this$0.$predicate$inlined;
                        ((<undefinedtype>)var15).L$0 = this;
                        ((<undefinedtype>)var15).L$1 = var1;
                        ((<undefinedtype>)var15).L$2 = var15;
                        ((<undefinedtype>)var15).L$3 = var1;
                        ((<undefinedtype>)var15).L$4 = var11;
                        ((<undefinedtype>)var15).L$5 = var1;
                        ((<undefinedtype>)var15).L$6 = var8;
                        ((<undefinedtype>)var15).label = 1;
                        var6 = var19.invoke(var1, var15);
                        if (var6 == var12) {
                           return var12;
                        }

                        var9 = this;
                        var18 = var1;
                        var10 = var15;
                        var7 = var1;
                        var5 = var1;
                        var13 = var11;
                     }

                     if ((Boolean)var6) {
                        var20 = Unit.INSTANCE;
                        return var20;
                     }

                     ((<undefinedtype>)var15).L$0 = var9;
                     ((<undefinedtype>)var15).L$1 = var18;
                     ((<undefinedtype>)var15).L$2 = var10;
                     ((<undefinedtype>)var15).L$3 = var5;
                     ((<undefinedtype>)var15).L$4 = var13;
                     ((<undefinedtype>)var15).L$5 = var7;
                     ((<undefinedtype>)var15).L$6 = var8;
                     ((<undefinedtype>)var15).label = 2;
                     if (var8.emit(var7, (Continuation)var15) == var12) {
                        return var12;
                     }
                  }

                  var20 = Unit.INSTANCE;
                  return var20;
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  if (!(Boolean)this.this$0.$predicate$inlined.invoke(var1, var2)) {
                     InlineMarker.mark(0);
                     var1 = var3.emit(var1, var2);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                  } else {
                     var1 = Unit.INSTANCE;
                  }

                  return var1;
               }
            });
            InlineMarker.mark(0);
            var3.collect(var1, var2);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow filterNotNull(Flow var0) {
      return (Flow)(new Flow(var0) {
         final Flow $this_unsafeTransform$inlined;

         public {
            this.$this_unsafeTransform$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = this.$this_unsafeTransform$inlined.collect((FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  Unit var4;
                  if (var1 != null) {
                     var1 = var3.emit(var1, var2);
                     if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return var1;
                     }

                     var4 = Unit.INSTANCE;
                  } else {
                     var4 = Unit.INSTANCE;
                  }

                  return var4;
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }

   public static final Flow map(Flow var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Flow $this_unsafeTransform$inlined;
         final Function2 $transform$inlined$1;

         public {
            this.$this_unsafeTransform$inlined = var1;
            this.$transform$inlined$1 = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = this.$this_unsafeTransform$inlined.collect((FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var15;
                  label34: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var15 = var4;
                           break label34;
                        }
                     }

                     var15 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
                        Object L$7;
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

                  Object var11 = ((<undefinedtype>)var15).result;
                  Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var15).label;
                  Object var5;
                  <undefinedtype> var6;
                  FlowCollector var7;
                  Object var8;
                  FlowCollector var9;
                  Continuation var10;
                  Object var19;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        if (var3 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var14 = (FlowCollector)((<undefinedtype>)var15).L$6;
                        var1 = ((<undefinedtype>)var15).L$5;
                        Continuation var16 = (Continuation)((<undefinedtype>)var15).L$4;
                        var1 = ((<undefinedtype>)var15).L$3;
                        <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                        var1 = ((<undefinedtype>)var15).L$1;
                        <undefinedtype> var18 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                        ResultKt.throwOnFailure(var11);
                        return Unit.INSTANCE;
                     }

                     var9 = (FlowCollector)((<undefinedtype>)var15).L$7;
                     var7 = (FlowCollector)((<undefinedtype>)var15).L$6;
                     var1 = ((<undefinedtype>)var15).L$5;
                     var10 = (Continuation)((<undefinedtype>)var15).L$4;
                     var19 = ((<undefinedtype>)var15).L$3;
                     var8 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                     var5 = ((<undefinedtype>)var15).L$1;
                     var6 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                     ResultKt.throwOnFailure(var11);
                  } else {
                     ResultKt.throwOnFailure(var11);
                     FlowCollector var21 = this.$this_unsafeFlow$inlined;
                     Continuation var12 = (Continuation)var15;
                     Function2 var20 = this.this$0.$transform$inlined$1;
                     ((<undefinedtype>)var15).L$0 = this;
                     ((<undefinedtype>)var15).L$1 = var1;
                     ((<undefinedtype>)var15).L$2 = var15;
                     ((<undefinedtype>)var15).L$3 = var1;
                     ((<undefinedtype>)var15).L$4 = var12;
                     ((<undefinedtype>)var15).L$5 = var1;
                     ((<undefinedtype>)var15).L$6 = var21;
                     ((<undefinedtype>)var15).L$7 = var21;
                     ((<undefinedtype>)var15).label = 1;
                     var11 = var20.invoke(var1, var15);
                     if (var11 == var13) {
                        return var13;
                     }

                     var6 = this;
                     var19 = var1;
                     var7 = var21;
                     var9 = var21;
                     var5 = var1;
                     var8 = var15;
                     var10 = var12;
                  }

                  ((<undefinedtype>)var15).L$0 = var6;
                  ((<undefinedtype>)var15).L$1 = var5;
                  ((<undefinedtype>)var15).L$2 = var8;
                  ((<undefinedtype>)var15).L$3 = var19;
                  ((<undefinedtype>)var15).L$4 = var10;
                  ((<undefinedtype>)var15).L$5 = var1;
                  ((<undefinedtype>)var15).L$6 = var7;
                  ((<undefinedtype>)var15).label = 2;
                  if (var9.emit(var11, (Continuation)var15) == var13) {
                     return var13;
                  } else {
                     return Unit.INSTANCE;
                  }
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
                     Object L$7;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  var1 = this.this$0.$transform$inlined$1.invoke(var1, var2);
                  InlineMarker.mark(0);
                  var1 = var3.emit(var1, var2);
                  InlineMarker.mark(2);
                  InlineMarker.mark(1);
                  return var1;
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }

         public Object collect$$forInline(FlowCollector var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
               int label;
               Object result;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object invokeSuspend(Object var1) {
                  this.result = var1;
                  this.label |= Integer.MIN_VALUE;
                  return this.this$0.collect((FlowCollector)null, this);
               }
            };
            InlineMarker.mark(5);
            Flow var3 = this.$this_unsafeTransform$inlined;
            var1 = (FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var15;
                  label34: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var15 = var4;
                           break label34;
                        }
                     }

                     var15 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
                        Object L$7;
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

                  Object var11 = ((<undefinedtype>)var15).result;
                  Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var15).label;
                  Object var5;
                  <undefinedtype> var6;
                  FlowCollector var7;
                  Object var8;
                  FlowCollector var9;
                  Continuation var10;
                  Object var19;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        if (var3 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var14 = (FlowCollector)((<undefinedtype>)var15).L$6;
                        var1 = ((<undefinedtype>)var15).L$5;
                        Continuation var16 = (Continuation)((<undefinedtype>)var15).L$4;
                        var1 = ((<undefinedtype>)var15).L$3;
                        <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                        var1 = ((<undefinedtype>)var15).L$1;
                        <undefinedtype> var18 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                        ResultKt.throwOnFailure(var11);
                        return Unit.INSTANCE;
                     }

                     var9 = (FlowCollector)((<undefinedtype>)var15).L$7;
                     var7 = (FlowCollector)((<undefinedtype>)var15).L$6;
                     var1 = ((<undefinedtype>)var15).L$5;
                     var10 = (Continuation)((<undefinedtype>)var15).L$4;
                     var19 = ((<undefinedtype>)var15).L$3;
                     var8 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                     var5 = ((<undefinedtype>)var15).L$1;
                     var6 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                     ResultKt.throwOnFailure(var11);
                  } else {
                     ResultKt.throwOnFailure(var11);
                     FlowCollector var21 = this.$this_unsafeFlow$inlined;
                     Continuation var12 = (Continuation)var15;
                     Function2 var20 = this.this$0.$transform$inlined$1;
                     ((<undefinedtype>)var15).L$0 = this;
                     ((<undefinedtype>)var15).L$1 = var1;
                     ((<undefinedtype>)var15).L$2 = var15;
                     ((<undefinedtype>)var15).L$3 = var1;
                     ((<undefinedtype>)var15).L$4 = var12;
                     ((<undefinedtype>)var15).L$5 = var1;
                     ((<undefinedtype>)var15).L$6 = var21;
                     ((<undefinedtype>)var15).L$7 = var21;
                     ((<undefinedtype>)var15).label = 1;
                     var11 = var20.invoke(var1, var15);
                     if (var11 == var13) {
                        return var13;
                     }

                     var6 = this;
                     var19 = var1;
                     var7 = var21;
                     var9 = var21;
                     var5 = var1;
                     var8 = var15;
                     var10 = var12;
                  }

                  ((<undefinedtype>)var15).L$0 = var6;
                  ((<undefinedtype>)var15).L$1 = var5;
                  ((<undefinedtype>)var15).L$2 = var8;
                  ((<undefinedtype>)var15).L$3 = var19;
                  ((<undefinedtype>)var15).L$4 = var10;
                  ((<undefinedtype>)var15).L$5 = var1;
                  ((<undefinedtype>)var15).L$6 = var7;
                  ((<undefinedtype>)var15).label = 2;
                  if (var9.emit(var11, (Continuation)var15) == var13) {
                     return var13;
                  } else {
                     return Unit.INSTANCE;
                  }
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
                     Object L$7;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  var1 = this.this$0.$transform$inlined$1.invoke(var1, var2);
                  InlineMarker.mark(0);
                  var1 = var3.emit(var1, var2);
                  InlineMarker.mark(2);
                  InlineMarker.mark(1);
                  return var1;
               }
            });
            InlineMarker.mark(0);
            var3.collect(var1, var2);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow mapNotNull(Flow var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Flow $this_unsafeTransform$inlined;
         final Function2 $transform$inlined$1;

         public {
            this.$this_unsafeTransform$inlined = var1;
            this.$transform$inlined$1 = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = this.$this_unsafeTransform$inlined.collect((FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var14;
                  label41: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var14 = var4;
                           break label41;
                        }
                     }

                     var14 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
                        Object L$7;
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

                  Unit var20;
                  label44: {
                     Object var11 = ((<undefinedtype>)var14).result;
                     Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var3 = ((<undefinedtype>)var14).label;
                     <undefinedtype> var5;
                     Object var6;
                     Object var7;
                     FlowCollector var9;
                     Continuation var10;
                     Object var18;
                     if (var3 != 0) {
                        if (var3 != 1) {
                           if (var3 != 2) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           var1 = ((<undefinedtype>)var14).L$7;
                           FlowCollector var13 = (FlowCollector)((<undefinedtype>)var14).L$6;
                           var1 = ((<undefinedtype>)var14).L$5;
                           Continuation var15 = (Continuation)((<undefinedtype>)var14).L$4;
                           var1 = ((<undefinedtype>)var14).L$3;
                           <undefinedtype> var16 = (<undefinedtype>)((<undefinedtype>)var14).L$2;
                           var1 = ((<undefinedtype>)var14).L$1;
                           <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var14).L$0;
                           ResultKt.throwOnFailure(var11);
                           break label44;
                        }

                        var9 = (FlowCollector)((<undefinedtype>)var14).L$6;
                        var1 = ((<undefinedtype>)var14).L$5;
                        var10 = (Continuation)((<undefinedtype>)var14).L$4;
                        var18 = ((<undefinedtype>)var14).L$3;
                        var6 = (<undefinedtype>)((<undefinedtype>)var14).L$2;
                        var7 = ((<undefinedtype>)var14).L$1;
                        var5 = (<undefinedtype>)((<undefinedtype>)var14).L$0;
                        ResultKt.throwOnFailure(var11);
                     } else {
                        ResultKt.throwOnFailure(var11);
                        var9 = this.$this_unsafeFlow$inlined;
                        var10 = (Continuation)var14;
                        Function2 var19 = this.this$0.$transform$inlined$1;
                        ((<undefinedtype>)var14).L$0 = this;
                        ((<undefinedtype>)var14).L$1 = var1;
                        ((<undefinedtype>)var14).L$2 = var14;
                        ((<undefinedtype>)var14).L$3 = var1;
                        ((<undefinedtype>)var14).L$4 = var10;
                        ((<undefinedtype>)var14).L$5 = var1;
                        ((<undefinedtype>)var14).L$6 = var9;
                        ((<undefinedtype>)var14).label = 1;
                        var11 = var19.invoke(var1, var14);
                        if (var11 == var12) {
                           return var12;
                        }

                        var5 = this;
                        var6 = var14;
                        var7 = var1;
                        var18 = var1;
                        var1 = var1;
                     }

                     if (var11 == null) {
                        var20 = Unit.INSTANCE;
                        return var20;
                     }

                     ((<undefinedtype>)var14).L$0 = var5;
                     ((<undefinedtype>)var14).L$1 = var7;
                     ((<undefinedtype>)var14).L$2 = var6;
                     ((<undefinedtype>)var14).L$3 = var18;
                     ((<undefinedtype>)var14).L$4 = var10;
                     ((<undefinedtype>)var14).L$5 = var1;
                     ((<undefinedtype>)var14).L$6 = var9;
                     ((<undefinedtype>)var14).L$7 = var11;
                     ((<undefinedtype>)var14).label = 2;
                     if (var9.emit(var11, (Continuation)var14) == var12) {
                        return var12;
                     }
                  }

                  var20 = Unit.INSTANCE;
                  return var20;
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
                     Object L$7;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  var1 = this.this$0.$transform$inlined$1.invoke(var1, var2);
                  if (var1 != null) {
                     InlineMarker.mark(0);
                     var1 = var3.emit(var1, var2);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                  } else {
                     var1 = Unit.INSTANCE;
                  }

                  return var1;
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }

         public Object collect$$forInline(FlowCollector var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
               int label;
               Object result;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object invokeSuspend(Object var1) {
                  this.result = var1;
                  this.label |= Integer.MIN_VALUE;
                  return this.this$0.collect((FlowCollector)null, this);
               }
            };
            InlineMarker.mark(5);
            Flow var3 = this.$this_unsafeTransform$inlined;
            var1 = (FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var14;
                  label41: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var14 = var4;
                           break label41;
                        }
                     }

                     var14 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
                        Object L$7;
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

                  Unit var20;
                  label44: {
                     Object var11 = ((<undefinedtype>)var14).result;
                     Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var3 = ((<undefinedtype>)var14).label;
                     <undefinedtype> var5;
                     Object var6;
                     Object var7;
                     FlowCollector var9;
                     Continuation var10;
                     Object var18;
                     if (var3 != 0) {
                        if (var3 != 1) {
                           if (var3 != 2) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           var1 = ((<undefinedtype>)var14).L$7;
                           FlowCollector var13 = (FlowCollector)((<undefinedtype>)var14).L$6;
                           var1 = ((<undefinedtype>)var14).L$5;
                           Continuation var15 = (Continuation)((<undefinedtype>)var14).L$4;
                           var1 = ((<undefinedtype>)var14).L$3;
                           <undefinedtype> var16 = (<undefinedtype>)((<undefinedtype>)var14).L$2;
                           var1 = ((<undefinedtype>)var14).L$1;
                           <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var14).L$0;
                           ResultKt.throwOnFailure(var11);
                           break label44;
                        }

                        var9 = (FlowCollector)((<undefinedtype>)var14).L$6;
                        var1 = ((<undefinedtype>)var14).L$5;
                        var10 = (Continuation)((<undefinedtype>)var14).L$4;
                        var18 = ((<undefinedtype>)var14).L$3;
                        var6 = (<undefinedtype>)((<undefinedtype>)var14).L$2;
                        var7 = ((<undefinedtype>)var14).L$1;
                        var5 = (<undefinedtype>)((<undefinedtype>)var14).L$0;
                        ResultKt.throwOnFailure(var11);
                     } else {
                        ResultKt.throwOnFailure(var11);
                        var9 = this.$this_unsafeFlow$inlined;
                        var10 = (Continuation)var14;
                        Function2 var19 = this.this$0.$transform$inlined$1;
                        ((<undefinedtype>)var14).L$0 = this;
                        ((<undefinedtype>)var14).L$1 = var1;
                        ((<undefinedtype>)var14).L$2 = var14;
                        ((<undefinedtype>)var14).L$3 = var1;
                        ((<undefinedtype>)var14).L$4 = var10;
                        ((<undefinedtype>)var14).L$5 = var1;
                        ((<undefinedtype>)var14).L$6 = var9;
                        ((<undefinedtype>)var14).label = 1;
                        var11 = var19.invoke(var1, var14);
                        if (var11 == var12) {
                           return var12;
                        }

                        var5 = this;
                        var6 = var14;
                        var7 = var1;
                        var18 = var1;
                        var1 = var1;
                     }

                     if (var11 == null) {
                        var20 = Unit.INSTANCE;
                        return var20;
                     }

                     ((<undefinedtype>)var14).L$0 = var5;
                     ((<undefinedtype>)var14).L$1 = var7;
                     ((<undefinedtype>)var14).L$2 = var6;
                     ((<undefinedtype>)var14).L$3 = var18;
                     ((<undefinedtype>)var14).L$4 = var10;
                     ((<undefinedtype>)var14).L$5 = var1;
                     ((<undefinedtype>)var14).L$6 = var9;
                     ((<undefinedtype>)var14).L$7 = var11;
                     ((<undefinedtype>)var14).label = 2;
                     if (var9.emit(var11, (Continuation)var14) == var12) {
                        return var12;
                     }
                  }

                  var20 = Unit.INSTANCE;
                  return var20;
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
                     Object L$2;
                     Object L$3;
                     Object L$4;
                     Object L$5;
                     Object L$6;
                     Object L$7;
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
                  FlowCollector var3 = this.$this_unsafeFlow$inlined;
                  var1 = this.this$0.$transform$inlined$1.invoke(var1, var2);
                  if (var1 != null) {
                     InlineMarker.mark(0);
                     var1 = var3.emit(var1, var2);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                  } else {
                     var1 = Unit.INSTANCE;
                  }

                  return var1;
               }
            });
            InlineMarker.mark(0);
            var3.collect(var1, var2);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow onEach(Flow var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function2 $action$inlined;
         final Flow $this_unsafeTransform$inlined;

         public {
            this.$this_unsafeTransform$inlined = var1;
            this.$action$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = this.$this_unsafeTransform$inlined.collect((FlowCollector)(new FlowCollector(var1, this) {
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.this$0 = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var13;
                  label34: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var13 = var4;
                           break label34;
                        }
                     }

                     var13 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        Object L$5;
                        Object L$6;
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

                  Object var11 = ((<undefinedtype>)var13).result;
                  Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var13).label;
                  Object var5;
                  Continuation var6;
                  Object var7;
                  FlowCollector var8;
                  <undefinedtype> var9;
                  Object var17;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        if (var3 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var12 = (FlowCollector)((<undefinedtype>)var13).L$6;
                        var1 = ((<undefinedtype>)var13).L$5;
                        Continuation var14 = (Continuation)((<undefinedtype>)var13).L$4;
                        var1 = ((<undefinedtype>)var13).L$3;
                        <undefinedtype> var15 = (<undefinedtype>)((<undefinedtype>)var13).L$2;
                        var1 = ((<undefinedtype>)var13).L$1;
                        <undefinedtype> var16 = (<undefinedtype>)((<undefinedtype>)var13).L$0;
                        ResultKt.throwOnFailure(var11);
                        return Unit.INSTANCE;
                     }

                     var8 = (FlowCollector)((<undefinedtype>)var13).L$6;
                     var5 = ((<undefinedtype>)var13).L$5;
                     var6 = (Continuation)((<undefinedtype>)var13).L$4;
                     var1 = ((<undefinedtype>)var13).L$3;
                     var7 = (<undefinedtype>)((<undefinedtype>)var13).L$2;
                     var17 = ((<undefinedtype>)var13).L$1;
                     var9 = (<undefinedtype>)((<undefinedtype>)var13).L$0;
                     ResultKt.throwOnFailure(var11);
                  } else {
                     ResultKt.throwOnFailure(var11);
                     var8 = this.$this_unsafeFlow$inlined;
                     var6 = (Continuation)var13;
                     Function2 var18 = this.this$0.$action$inlined;
                     ((<undefinedtype>)var13).L$0 = this;
                     ((<undefinedtype>)var13).L$1 = var1;
                     ((<undefinedtype>)var13).L$2 = var13;
                     ((<undefinedtype>)var13).L$3 = var1;
                     ((<undefinedtype>)var13).L$4 = var6;
                     ((<undefinedtype>)var13).L$5 = var1;
                     ((<undefinedtype>)var13).L$6 = var8;
                     ((<undefinedtype>)var13).label = 1;
                     InlineMarker.mark(6);
                     var17 = var18.invoke(var1, var13);
                     InlineMarker.mark(7);
                     if (var17 == var10) {
                        return var10;
                     }

                     var9 = this;
                     var17 = var1;
                     var7 = var13;
                     var5 = var1;
                  }

                  ((<undefinedtype>)var13).L$0 = var9;
                  ((<undefinedtype>)var13).L$1 = var17;
                  ((<undefinedtype>)var13).L$2 = var7;
                  ((<undefinedtype>)var13).L$3 = var1;
                  ((<undefinedtype>)var13).L$4 = var6;
                  ((<undefinedtype>)var13).L$5 = var5;
                  ((<undefinedtype>)var13).L$6 = var8;
                  ((<undefinedtype>)var13).label = 2;
                  if (var8.emit(var5, (Continuation)var13) == var10) {
                     return var10;
                  } else {
                     return Unit.INSTANCE;
                  }
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }

   public static final Flow runningReduce(Flow var0, Function3 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function3 $operation$inlined;
         final Flow $this_runningReduce$inlined;

         public {
            this.$this_runningReduce$inlined = var1;
            this.$operation$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Ref.ObjectRef var3 = new Ref.ObjectRef();
            var3.element = NullSurrogateKt.NULL;
            Object var4 = this.$this_runningReduce$inlined.collect((FlowCollector)(new FlowCollector(var1, var3, this) {
               final Ref.ObjectRef $accumulator$inlined;
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.$accumulator$inlined = var2;
                  this.this$0 = var3;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var4;
                  label38: {
                     if (var2 instanceof <undefinedtype>) {
                        var4 = (<undefinedtype>)var2;
                        if ((((<undefinedtype>)var4).label & Integer.MIN_VALUE) != 0) {
                           ((<undefinedtype>)var4).label += Integer.MIN_VALUE;
                           break label38;
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

                  Object var8 = ((<undefinedtype>)var4).result;
                  Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var4).label;
                  Ref.ObjectRef var5;
                  Object var6;
                  <undefinedtype> var7;
                  Object var9;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        if (var3 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        var1 = ((<undefinedtype>)var4).L$3;
                        Continuation var11 = (Continuation)((<undefinedtype>)var4).L$2;
                        var1 = ((<undefinedtype>)var4).L$1;
                        <undefinedtype> var13 = (<undefinedtype>)((<undefinedtype>)var4).L$0;
                        ResultKt.throwOnFailure(var8);
                        return Unit.INSTANCE;
                     }

                     var5 = (Ref.ObjectRef)((<undefinedtype>)var4).L$4;
                     var6 = ((<undefinedtype>)var4).L$3;
                     var2 = (Continuation)((<undefinedtype>)var4).L$2;
                     var1 = ((<undefinedtype>)var4).L$1;
                     var7 = (<undefinedtype>)((<undefinedtype>)var4).L$0;
                     ResultKt.throwOnFailure(var8);
                     var9 = var8;
                  } else {
                     ResultKt.throwOnFailure(var8);
                     Continuation var16 = (Continuation)var4;
                     var5 = this.$accumulator$inlined;
                     Object var12;
                     if (var5.element == NullSurrogateKt.NULL) {
                        var12 = var1;
                     } else {
                        Function3 var15 = this.this$0.$operation$inlined;
                        var12 = this.$accumulator$inlined.element;
                        ((<undefinedtype>)var4).L$0 = this;
                        ((<undefinedtype>)var4).L$1 = var1;
                        ((<undefinedtype>)var4).L$2 = var16;
                        ((<undefinedtype>)var4).L$3 = var1;
                        ((<undefinedtype>)var4).L$4 = var5;
                        ((<undefinedtype>)var4).label = 1;
                        InlineMarker.mark(6);
                        var12 = var15.invoke(var12, var1, var4);
                        InlineMarker.mark(7);
                        if (var12 == var10) {
                           return var10;
                        }
                     }

                     var6 = var12;
                     var7 = this;
                     var2 = var16;
                     var9 = var6;
                     var6 = var1;
                  }

                  var5.element = var9;
                  FlowCollector var14 = var7.$this_unsafeFlow$inlined;
                  var8 = var7.$accumulator$inlined.element;
                  ((<undefinedtype>)var4).L$0 = var7;
                  ((<undefinedtype>)var4).L$1 = var1;
                  ((<undefinedtype>)var4).L$2 = var2;
                  ((<undefinedtype>)var4).L$3 = var6;
                  ((<undefinedtype>)var4).label = 2;
                  if (var14.emit(var8, (Continuation)var4) == var10) {
                     return var10;
                  } else {
                     return Unit.INSTANCE;
                  }
               }
            }), var2);
            return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
         }
      });
   }

   public static final Flow scan(Flow var0, Object var1, Function3 var2) {
      return (Flow)(new Flow(var0, var1, var2) {
         final Object $initial$inlined;
         final Function3 $operation$inlined;
         final Flow $this_scan$inlined;

         public {
            this.$this_scan$inlined = var1;
            this.$initial$inlined = var2;
            this.$operation$inlined = var3;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var13;
            label34: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var13 = var4;
                     break label34;
                  }
               }

               var13 = new ContinuationImpl(this, var2) {
                  Object L$0;
                  Object L$1;
                  Object L$2;
                  Object L$3;
                  Object L$4;
                  Object L$5;
                  int label;
                  Object result;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Object invokeSuspend(Object var1) {
                     this.result = var1;
                     this.label |= Integer.MIN_VALUE;
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
            }

            Object var8 = ((<undefinedtype>)var13).result;
            Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var13).label;
            FlowCollector var5;
            Continuation var6;
            Ref.ObjectRef var7;
            <undefinedtype> var16;
            if (var3 != 0) {
               if (var3 != 1) {
                  if (var3 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  Flow var11 = (Flow)((<undefinedtype>)var13).L$5;
                  Ref.ObjectRef var12 = (Ref.ObjectRef)((<undefinedtype>)var13).L$4;
                  var1 = (FlowCollector)((<undefinedtype>)var13).L$3;
                  Continuation var14 = (Continuation)((<undefinedtype>)var13).L$2;
                  var1 = (FlowCollector)((<undefinedtype>)var13).L$1;
                  <undefinedtype> var15 = (<undefinedtype>)((<undefinedtype>)var13).L$0;
                  ResultKt.throwOnFailure(var8);
                  return Unit.INSTANCE;
               }

               var7 = (Ref.ObjectRef)((<undefinedtype>)var13).L$4;
               var5 = (FlowCollector)((<undefinedtype>)var13).L$3;
               var6 = (Continuation)((<undefinedtype>)var13).L$2;
               var1 = (FlowCollector)((<undefinedtype>)var13).L$1;
               var16 = (<undefinedtype>)((<undefinedtype>)var13).L$0;
               ResultKt.throwOnFailure(var8);
            } else {
               ResultKt.throwOnFailure(var8);
               var6 = (Continuation)var13;
               Ref.ObjectRef var18 = new Ref.ObjectRef();
               var18.element = this.$initial$inlined;
               Object var17 = var18.element;
               ((<undefinedtype>)var13).L$0 = this;
               ((<undefinedtype>)var13).L$1 = var1;
               ((<undefinedtype>)var13).L$2 = var6;
               ((<undefinedtype>)var13).L$3 = var1;
               ((<undefinedtype>)var13).L$4 = var18;
               ((<undefinedtype>)var13).label = 1;
               if (var1.emit(var17, (Continuation)var13) == var9) {
                  return var9;
               }

               var16 = this;
               var5 = var1;
               var1 = var1;
               var7 = var18;
            }

            Flow var19 = var16.$this_scan$inlined;
            FlowCollector var10 = (FlowCollector)(new FlowCollector(var5, var7, var16) {
               final Ref.ObjectRef $accumulator$inlined;
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.$accumulator$inlined = var2;
                  this.this$0 = var3;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var12;
                  label34: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var12 = var4;
                           break label34;
                        }
                     }

                     var12 = new ContinuationImpl(this, var2) {
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

                  Object var9 = ((<undefinedtype>)var12).result;
                  Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var12).label;
                  Object var5;
                  <undefinedtype> var6;
                  Ref.ObjectRef var7;
                  Continuation var11;
                  Object var14;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        if (var3 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        var1 = ((<undefinedtype>)var12).L$3;
                        var11 = (Continuation)((<undefinedtype>)var12).L$2;
                        var1 = ((<undefinedtype>)var12).L$1;
                        <undefinedtype> var13 = (<undefinedtype>)((<undefinedtype>)var12).L$0;
                        ResultKt.throwOnFailure(var9);
                        return Unit.INSTANCE;
                     }

                     var7 = (Ref.ObjectRef)((<undefinedtype>)var12).L$4;
                     var14 = ((<undefinedtype>)var12).L$3;
                     var11 = (Continuation)((<undefinedtype>)var12).L$2;
                     var5 = ((<undefinedtype>)var12).L$1;
                     var6 = (<undefinedtype>)((<undefinedtype>)var12).L$0;
                     ResultKt.throwOnFailure(var9);
                  } else {
                     ResultKt.throwOnFailure(var9);
                     Continuation var8 = (Continuation)var12;
                     var7 = this.$accumulator$inlined;
                     Function3 var15 = this.this$0.$operation$inlined;
                     var14 = this.$accumulator$inlined.element;
                     ((<undefinedtype>)var12).L$0 = this;
                     ((<undefinedtype>)var12).L$1 = var1;
                     ((<undefinedtype>)var12).L$2 = var8;
                     ((<undefinedtype>)var12).L$3 = var1;
                     ((<undefinedtype>)var12).L$4 = var7;
                     ((<undefinedtype>)var12).label = 1;
                     InlineMarker.mark(6);
                     var9 = var15.invoke(var14, var1, var12);
                     InlineMarker.mark(7);
                     if (var9 == var10) {
                        return var10;
                     }

                     var6 = this;
                     var14 = var1;
                     var5 = var1;
                     var11 = var8;
                  }

                  var7.element = var9;
                  FlowCollector var17 = var6.$this_unsafeFlow$inlined;
                  Object var16 = var6.$accumulator$inlined.element;
                  ((<undefinedtype>)var12).L$0 = var6;
                  ((<undefinedtype>)var12).L$1 = var5;
                  ((<undefinedtype>)var12).L$2 = var11;
                  ((<undefinedtype>)var12).L$3 = var14;
                  ((<undefinedtype>)var12).label = 2;
                  if (var17.emit(var16, (Continuation)var12) == var10) {
                     return var10;
                  } else {
                     return Unit.INSTANCE;
                  }
               }
            });
            ((<undefinedtype>)var13).L$0 = var16;
            ((<undefinedtype>)var13).L$1 = var1;
            ((<undefinedtype>)var13).L$2 = var6;
            ((<undefinedtype>)var13).L$3 = var5;
            ((<undefinedtype>)var13).L$4 = var7;
            ((<undefinedtype>)var13).L$5 = var19;
            ((<undefinedtype>)var13).label = 2;
            if (var19.collect(var10, (Continuation)var13) == var9) {
               return var9;
            } else {
               return Unit.INSTANCE;
            }
         }
      });
   }

   public static final Flow withIndex(Flow var0) {
      return (Flow)(new Flow(var0) {
         final Flow $this_withIndex$inlined;

         public {
            this.$this_withIndex$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Ref.IntRef var3 = new Ref.IntRef();
            var3.element = 0;
            Object var4 = this.$this_withIndex$inlined.collect((FlowCollector)(new FlowCollector(var1, var3) {
               final Ref.IntRef $index$inlined;
               final FlowCollector $this_unsafeFlow$inlined;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.$index$inlined = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  FlowCollector var4 = this.$this_unsafeFlow$inlined;
                  Ref.IntRef var5 = this.$index$inlined;
                  int var3 = var5.element++;
                  if (var3 >= 0) {
                     var1 = var4.emit(new IndexedValue(var3, var1), var2);
                     return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
                  } else {
                     throw (Throwable)(new ArithmeticException("Index overflow has happened"));
                  }
               }
            }), var2);
            return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
         }
      });
   }
}
