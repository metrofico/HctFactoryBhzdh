package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowMerge;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000T\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b2\u001e\u0010\f\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\r\"\b\u0012\u0004\u0012\u0002H\u000b0\nH\u0007¢\u0006\u0002\u0010\u000e\u001ae\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000b0\n27\u0010\u0011\u001a3\b\u0001\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001ah\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000b0\n29\b\u0005\u0010\u0011\u001a3\b\u0001\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001ao\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000b0\n2\b\b\u0002\u0010\u001b\u001a\u00020\u000127\u0010\u0011\u001a3\b\u0001\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a$\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\nH\u0007\u001a.\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\n2\b\b\u0002\u0010\u001b\u001a\u00020\u0001H\u0007\u001aa\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000b0\n23\b\u0001\u0010\u0011\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a$\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0 H\u0007\u001ar\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000b0\n2D\b\u0001\u0010\u0011\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100#\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\"¢\u0006\u0002\b%H\u0007ø\u0001\u0000¢\u0006\u0002\u0010&\"\u001c\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"},
   d2 = {"DEFAULT_CONCURRENCY", "", "getDEFAULT_CONCURRENCY$annotations", "()V", "getDEFAULT_CONCURRENCY", "()I", "DEFAULT_CONCURRENCY_PROPERTY_NAME", "", "getDEFAULT_CONCURRENCY_PROPERTY_NAME$annotations", "merge", "Lkotlinx/coroutines/flow/Flow;", "T", "flows", "", "([Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/flow/Flow;", "flatMapConcat", "R", "transform", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "flatMapLatest", "flatMapMerge", "concurrency", "(Lkotlinx/coroutines/flow/Flow;ILkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "flattenConcat", "flattenMerge", "mapLatest", "", "transformLatest", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__MergeKt {
   private static final int DEFAULT_CONCURRENCY = SystemPropsKt.systemProp("kotlinx.coroutines.flow.defaultConcurrency", 16, 1, Integer.MAX_VALUE);

   public static final Flow flatMapConcat(Flow var0, Function2 var1) {
      return FlowKt.flattenConcat((Flow)(new Flow(var0, var1) {
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

                  Object var7 = ((<undefinedtype>)var15).result;
                  Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var15).label;
                  Object var5;
                  <undefinedtype> var6;
                  FlowCollector var8;
                  Object var9;
                  Object var10;
                  FlowCollector var11;
                  Object var18;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        if (var3 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var14 = (FlowCollector)((<undefinedtype>)var15).L$6;
                        var1 = ((<undefinedtype>)var15).L$5;
                        <undefinedtype> var16 = (<undefinedtype>)((<undefinedtype>)var15).L$4;
                        var1 = ((<undefinedtype>)var15).L$3;
                        var16 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                        var1 = ((<undefinedtype>)var15).L$1;
                        <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                        ResultKt.throwOnFailure(var7);
                        return Unit.INSTANCE;
                     }

                     var11 = (FlowCollector)((<undefinedtype>)var15).L$7;
                     var8 = (FlowCollector)((<undefinedtype>)var15).L$6;
                     var1 = ((<undefinedtype>)var15).L$5;
                     var18 = (<undefinedtype>)((<undefinedtype>)var15).L$4;
                     var5 = ((<undefinedtype>)var15).L$3;
                     var10 = (<undefinedtype>)((<undefinedtype>)var15).L$2;
                     var9 = ((<undefinedtype>)var15).L$1;
                     var6 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
                     ResultKt.throwOnFailure(var7);
                  } else {
                     ResultKt.throwOnFailure(var7);
                     FlowCollector var20 = this.$this_unsafeFlow$inlined;
                     Function2 var19 = this.this$0.$transform$inlined$1;
                     ((<undefinedtype>)var15).L$0 = this;
                     ((<undefinedtype>)var15).L$1 = var1;
                     ((<undefinedtype>)var15).L$2 = var15;
                     ((<undefinedtype>)var15).L$3 = var1;
                     ((<undefinedtype>)var15).L$4 = var15;
                     ((<undefinedtype>)var15).L$5 = var1;
                     ((<undefinedtype>)var15).L$6 = var20;
                     ((<undefinedtype>)var15).L$7 = var20;
                     ((<undefinedtype>)var15).label = 1;
                     var7 = var19.invoke(var1, var15);
                     if (var7 == var13) {
                        return var13;
                     }

                     var5 = var1;
                     var9 = var1;
                     var18 = var15;
                     var8 = var20;
                     var11 = var20;
                     var6 = this;
                     var10 = var15;
                  }

                  ((<undefinedtype>)var15).L$0 = var6;
                  ((<undefinedtype>)var15).L$1 = var9;
                  ((<undefinedtype>)var15).L$2 = var10;
                  ((<undefinedtype>)var15).L$3 = var5;
                  ((<undefinedtype>)var15).L$4 = var18;
                  ((<undefinedtype>)var15).L$5 = var1;
                  ((<undefinedtype>)var15).L$6 = var8;
                  ((<undefinedtype>)var15).label = 2;
                  if (var11.emit(var7, (Continuation)var15) == var13) {
                     return var13;
                  } else {
                     return Unit.INSTANCE;
                  }
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      }));
   }

   public static final Flow flatMapLatest(Flow var0, Function2 var1) {
      return FlowKt.transformLatest(var0, (Function3)(new Function3(var1, (Continuation)null) {
         final Function2 $transform;
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
         int label;
         private FlowCollector p$;
         private Object p$0;

         public {
            this.$transform = var1;
         }

         public final Continuation create(FlowCollector var1, Object var2, Continuation var3) {
            Function3 var4 = new <anonymous constructor>(this.$transform, var3);
            var4.p$ = var1;
            var4.p$0 = var2;
            return var4;
         }

         public final Object invoke(Object var1, Object var2, Object var3) {
            return ((<undefinedtype>)this.create((FlowCollector)var1, var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            FlowCollector var3;
            Object var4;
            FlowCollector var5;
            Object var6;
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  Flow var10 = (Flow)this.L$3;
                  var3 = (FlowCollector)this.L$2;
                  var3 = (FlowCollector)this.L$0;
                  ResultKt.throwOnFailure(var1);
                  return Unit.INSTANCE;
               }

               var5 = (FlowCollector)this.L$2;
               var4 = this.L$1;
               var3 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
               var6 = var1;
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var8 = this.p$;
               var4 = this.p$0;
               Function2 var11 = this.$transform;
               this.L$0 = var8;
               this.L$1 = var4;
               this.L$2 = var8;
               this.label = 1;
               var6 = var11.invoke(var4, this);
               if (var6 == var7) {
                  return var7;
               }

               var3 = var8;
               var5 = var8;
            }

            Flow var9 = (Flow)var6;
            this.L$0 = var3;
            this.L$1 = var4;
            this.L$2 = var5;
            this.L$3 = var9;
            this.label = 2;
            if (var9.collect(var5, this) == var7) {
               return var7;
            } else {
               return Unit.INSTANCE;
            }
         }

         public final Object invokeSuspend$$forInline(Object var1) {
            FlowCollector var3 = this.p$;
            Object var2 = this.p$0;
            Flow var4 = (Flow)this.$transform.invoke(var2, this);
            InlineMarker.mark(0);
            var4.collect(var3, this);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      }));
   }

   public static final Flow flatMapMerge(Flow var0, int var1, Function2 var2) {
      return FlowKt.flattenMerge((Flow)(new Flow(var0, var2) {
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
                  label34: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var14 = var4;
                           break label34;
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

                  Object var7 = ((<undefinedtype>)var14).result;
                  Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var14).label;
                  Object var5;
                  FlowCollector var6;
                  Object var8;
                  FlowCollector var9;
                  <undefinedtype> var10;
                  Object var11;
                  Object var17;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        if (var3 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var13 = (FlowCollector)((<undefinedtype>)var14).L$6;
                        var1 = ((<undefinedtype>)var14).L$5;
                        <undefinedtype> var15 = (<undefinedtype>)((<undefinedtype>)var14).L$4;
                        var1 = ((<undefinedtype>)var14).L$3;
                        var15 = (<undefinedtype>)((<undefinedtype>)var14).L$2;
                        var1 = ((<undefinedtype>)var14).L$1;
                        <undefinedtype> var16 = (<undefinedtype>)((<undefinedtype>)var14).L$0;
                        ResultKt.throwOnFailure(var7);
                        return Unit.INSTANCE;
                     }

                     var6 = (FlowCollector)((<undefinedtype>)var14).L$7;
                     var9 = (FlowCollector)((<undefinedtype>)var14).L$6;
                     var1 = ((<undefinedtype>)var14).L$5;
                     var17 = (<undefinedtype>)((<undefinedtype>)var14).L$4;
                     var5 = ((<undefinedtype>)var14).L$3;
                     var11 = (<undefinedtype>)((<undefinedtype>)var14).L$2;
                     var8 = ((<undefinedtype>)var14).L$1;
                     var10 = (<undefinedtype>)((<undefinedtype>)var14).L$0;
                     ResultKt.throwOnFailure(var7);
                  } else {
                     ResultKt.throwOnFailure(var7);
                     var6 = this.$this_unsafeFlow$inlined;
                     Function2 var18 = this.this$0.$transform$inlined$1;
                     ((<undefinedtype>)var14).L$0 = this;
                     ((<undefinedtype>)var14).L$1 = var1;
                     ((<undefinedtype>)var14).L$2 = var14;
                     ((<undefinedtype>)var14).L$3 = var1;
                     ((<undefinedtype>)var14).L$4 = var14;
                     ((<undefinedtype>)var14).L$5 = var1;
                     ((<undefinedtype>)var14).L$6 = var6;
                     ((<undefinedtype>)var14).L$7 = var6;
                     ((<undefinedtype>)var14).label = 1;
                     var7 = var18.invoke(var1, var14);
                     if (var7 == var12) {
                        return var12;
                     }

                     var10 = this;
                     var5 = var1;
                     var8 = var1;
                     var17 = var14;
                     var11 = var14;
                     var9 = var6;
                  }

                  ((<undefinedtype>)var14).L$0 = var10;
                  ((<undefinedtype>)var14).L$1 = var8;
                  ((<undefinedtype>)var14).L$2 = var11;
                  ((<undefinedtype>)var14).L$3 = var5;
                  ((<undefinedtype>)var14).L$4 = var17;
                  ((<undefinedtype>)var14).L$5 = var1;
                  ((<undefinedtype>)var14).L$6 = var9;
                  ((<undefinedtype>)var14).label = 2;
                  if (var6.emit(var7, (Continuation)var14) == var12) {
                     return var12;
                  } else {
                     return Unit.INSTANCE;
                  }
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      }), var1);
   }

   // $FF: synthetic method
   public static Flow flatMapMerge$default(Flow var0, int var1, Function2 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = DEFAULT_CONCURRENCY;
      }

      return FlowKt.flatMapMerge(var0, var1, var2);
   }

   public static final Flow flattenConcat(Flow var0) {
      return (Flow)(new Flow(var0) {
         final Flow $this_flattenConcat$inlined;

         public {
            this.$this_flattenConcat$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = this.$this_flattenConcat$inlined.collect((FlowCollector)(new FlowCollector(var1) {
               final FlowCollector $this_unsafeFlow$inlined;

               public {
                  this.$this_unsafeFlow$inlined = var1;
               }

               public Object emit(Object var1, Continuation var2) {
                  var1 = ((Flow)var1).collect(this.$this_unsafeFlow$inlined, var2);
                  return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }

   public static final Flow flattenMerge(Flow var0, int var1) {
      boolean var2;
      if (var1 > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2) {
         if (var1 == 1) {
            var0 = FlowKt.flattenConcat(var0);
         } else {
            var0 = (Flow)(new ChannelFlowMerge(var0, var1, (CoroutineContext)null, 0, (BufferOverflow)null, 28, (DefaultConstructorMarker)null));
         }

         return var0;
      } else {
         throw (Throwable)(new IllegalArgumentException(("Expected positive concurrency level, but had " + var1).toString()));
      }
   }

   // $FF: synthetic method
   public static Flow flattenMerge$default(Flow var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = DEFAULT_CONCURRENCY;
      }

      return FlowKt.flattenMerge(var0, var1);
   }

   public static final int getDEFAULT_CONCURRENCY() {
      return DEFAULT_CONCURRENCY;
   }

   // $FF: synthetic method
   public static void getDEFAULT_CONCURRENCY$annotations() {
   }

   public static final Flow mapLatest(Flow var0, Function2 var1) {
      return FlowKt.transformLatest(var0, (Function3)(new Function3(var1, (Continuation)null) {
         final Function2 $transform;
         Object L$0;
         Object L$1;
         Object L$2;
         int label;
         private FlowCollector p$;
         private Object p$0;

         {
            this.$transform = var1;
         }

         public final Continuation create(FlowCollector var1, Object var2, Continuation var3) {
            Function3 var4 = new <anonymous constructor>(this.$transform, var3);
            var4.p$ = var1;
            var4.p$0 = var2;
            return var4;
         }

         public final Object invoke(Object var1, Object var2, Object var3) {
            return ((<undefinedtype>)this.create((FlowCollector)var1, var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            FlowCollector var3;
            Object var4;
            FlowCollector var5;
            Object var6;
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var3 = (FlowCollector)this.L$0;
                  ResultKt.throwOnFailure(var1);
                  return Unit.INSTANCE;
               }

               var3 = (FlowCollector)this.L$2;
               var4 = this.L$1;
               var5 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
               var6 = var1;
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var8 = this.p$;
               Object var9 = this.p$0;
               Function2 var10 = this.$transform;
               this.L$0 = var8;
               this.L$1 = var9;
               this.L$2 = var8;
               this.label = 1;
               var6 = var10.invoke(var9, this);
               if (var6 == var7) {
                  return var7;
               }

               var5 = var8;
               var4 = var9;
               var3 = var8;
            }

            this.L$0 = var5;
            this.L$1 = var4;
            this.label = 2;
            if (var3.emit(var6, this) == var7) {
               return var7;
            } else {
               return Unit.INSTANCE;
            }
         }
      }));
   }

   public static final Flow merge(Iterable var0) {
      return (Flow)(new ChannelLimitedFlowMerge(var0, (CoroutineContext)null, 0, (BufferOverflow)null, 14, (DefaultConstructorMarker)null));
   }

   public static final Flow merge(Flow... var0) {
      return FlowKt.merge(ArraysKt.asIterable(var0));
   }

   public static final Flow transformLatest(Flow var0, Function3 var1) {
      return (Flow)(new ChannelFlowTransformLatest(var1, var0, (CoroutineContext)null, 0, (BufferOverflow)null, 28, (DefaultConstructorMarker)null));
   }
}
