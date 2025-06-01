package kotlinx.coroutines.flow;

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

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aV\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000323\b\u0004\u0010\u0004\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005H\u0080Hø\u0001\u0000¢\u0006\u0002\u0010\f\u001a$\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f\u001aH\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a+\u0010\u0012\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00132\u0006\u0010\b\u001a\u0002H\u0002H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a$\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f\u001aH\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001ar\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0019*\b\u0012\u0004\u0012\u0002H\u00020\u00032D\b\u0001\u0010\u001a\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u0013\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u001b¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"},
   d2 = {"collectWhile", "", "T", "Lkotlinx/coroutines/flow/Flow;", "predicate", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "drop", "count", "", "dropWhile", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "emitAbort", "Lkotlinx/coroutines/flow/FlowCollector;", "emitAbort$FlowKt__LimitKt", "(Lkotlinx/coroutines/flow/FlowCollector;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "take", "takeWhile", "transformWhile", "R", "transform", "Lkotlin/Function3;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__LimitKt {
   public static final Object collectWhile(Flow var0, Function2 var1, Continuation var2) {
      Object var12;
      label45: {
         if (var2 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var2;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var12 = var4;
               break label45;
            }
         }

         var12 = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.collectWhile((Flow)null, (Function2)null, this);
            }
         };
      }

      Object var13 = ((<undefinedtype>)var12).result;
      Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var3 = ((<undefinedtype>)var12).label;
      Object var9;
      AbortFlowException var11;
      if (var3 != 0) {
         if (var3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var9 = (<undefinedtype>)((<undefinedtype>)var12).L$2;
         var1 = (Function2)((<undefinedtype>)var12).L$1;
         Flow var10 = (Flow)((<undefinedtype>)var12).L$0;

         try {
            ResultKt.throwOnFailure(var13);
            return Unit.INSTANCE;
         } catch (AbortFlowException var7) {
            var11 = var7;
         }
      } else {
         label59: {
            ResultKt.throwOnFailure(var13);
            FlowCollector var14 = new FlowCollector(var1) {
               final Function2 $predicate;

               public {
                  this.$predicate = var1;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var7;
                  label29: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var7 = var4;
                           break label29;
                        }
                     }

                     var7 = new ContinuationImpl(this, var2) {
                        Object L$0;
                        Object L$1;
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

                  Object var8 = ((<undefinedtype>)var7).result;
                  Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var7).label;
                  <undefinedtype> var6;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     var1 = ((<undefinedtype>)var7).L$1;
                     var6 = (<undefinedtype>)((<undefinedtype>)var7).L$0;
                     ResultKt.throwOnFailure(var8);
                     var7 = var8;
                  } else {
                     ResultKt.throwOnFailure(var8);
                     Function2 var9 = this.$predicate;
                     ((<undefinedtype>)var7).L$0 = this;
                     ((<undefinedtype>)var7).L$1 = var1;
                     ((<undefinedtype>)var7).label = 1;
                     var7 = var9.invoke(var1, var7);
                     if (var7 == var5) {
                        return var5;
                     }

                     var6 = this;
                  }

                  if ((Boolean)var7) {
                     return Unit.INSTANCE;
                  } else {
                     throw (Throwable)(new AbortFlowException((FlowCollector)var6));
                  }
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
                  InlineMarker.mark(4);
                  ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                     Object L$0;
                     Object L$1;
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
                  if ((Boolean)this.$predicate.invoke(var1, var2)) {
                     return Unit.INSTANCE;
                  } else {
                     throw (Throwable)(new AbortFlowException((FlowCollector)this));
                  }
               }
            };

            try {
               FlowCollector var6 = (FlowCollector)var14;
               ((<undefinedtype>)var12).L$0 = var0;
               ((<undefinedtype>)var12).L$1 = var1;
               ((<undefinedtype>)var12).L$2 = var14;
               ((<undefinedtype>)var12).label = 1;
               var9 = var0.collect(var6, (Continuation)var12);
            } catch (AbortFlowException var8) {
               var11 = var8;
               var9 = var14;
               break label59;
            }

            if (var9 == var5) {
               return var5;
            }

            return Unit.INSTANCE;
         }
      }

      FlowExceptions_commonKt.checkOwnership(var11, (FlowCollector)var9);
      return Unit.INSTANCE;
   }

   private static final Object collectWhile$$forInline(Flow var0, Function2 var1, Continuation var2) {
      FlowCollector var5 = new FlowCollector(var1) {
         final Function2 $predicate;

         public {
            this.$predicate = var1;
         }

         public Object emit(Object var1, Continuation var2) {
            Object var7;
            label29: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var7 = var4;
                     break label29;
                  }
               }

               var7 = new ContinuationImpl(this, var2) {
                  Object L$0;
                  Object L$1;
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

            Object var8 = ((<undefinedtype>)var7).result;
            Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var7).label;
            <undefinedtype> var6;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var1 = ((<undefinedtype>)var7).L$1;
               var6 = (<undefinedtype>)((<undefinedtype>)var7).L$0;
               ResultKt.throwOnFailure(var8);
               var7 = var8;
            } else {
               ResultKt.throwOnFailure(var8);
               Function2 var9 = this.$predicate;
               ((<undefinedtype>)var7).L$0 = this;
               ((<undefinedtype>)var7).L$1 = var1;
               ((<undefinedtype>)var7).label = 1;
               var7 = var9.invoke(var1, var7);
               if (var7 == var5) {
                  return var5;
               }

               var6 = this;
            }

            if ((Boolean)var7) {
               return Unit.INSTANCE;
            } else {
               throw (Throwable)(new AbortFlowException((FlowCollector)var6));
            }
         }

         public Object emit$$forInline(Object var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
               Object L$0;
               Object L$1;
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
            if ((Boolean)this.$predicate.invoke(var1, var2)) {
               return Unit.INSTANCE;
            } else {
               throw (Throwable)(new AbortFlowException((FlowCollector)this));
            }
         }
      };

      try {
         FlowCollector var3 = (FlowCollector)var5;
         InlineMarker.mark(0);
         var0.collect(var3, var2);
         InlineMarker.mark(2);
         InlineMarker.mark(1);
      } catch (AbortFlowException var4) {
         FlowExceptions_commonKt.checkOwnership(var4, (FlowCollector)var5);
      }

      return Unit.INSTANCE;
   }

   public static final Flow drop(Flow var0, int var1) {
      boolean var2;
      if (var1 >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2) {
         return (Flow)(new Flow(var0, var1) {
            final int $count$inlined;
            final Flow $this_drop$inlined;

            public {
               this.$this_drop$inlined = var1;
               this.$count$inlined = var2;
            }

            public Object collect(FlowCollector var1, Continuation var2) {
               Ref.IntRef var3 = new Ref.IntRef();
               var3.element = 0;
               Object var4 = this.$this_drop$inlined.collect((FlowCollector)(new FlowCollector(var1, var3, this) {
                  final Ref.IntRef $skipped$inlined;
                  final FlowCollector $this_unsafeFlow$inlined;
                  final <undefinedtype> this$0;

                  public {
                     this.$this_unsafeFlow$inlined = var1;
                     this.$skipped$inlined = var2;
                     this.this$0 = var3;
                  }

                  public Object emit(Object var1, Continuation var2) {
                     if (this.$skipped$inlined.element >= this.this$0.$count$inlined) {
                        var1 = this.$this_unsafeFlow$inlined.emit(var1, var2);
                        if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                           return var1;
                        }
                     } else {
                        Ref.IntRef var4 = this.$skipped$inlined;
                        ++var4.element;
                        int var3 = var4.element;
                     }

                     return Unit.INSTANCE;
                  }
               }), var2);
               return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
            }
         });
      } else {
         throw (Throwable)(new IllegalArgumentException(("Drop count should be non-negative, but had " + var1).toString()));
      }
   }

   public static final Flow dropWhile(Flow var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function2 $predicate$inlined;
         final Flow $this_dropWhile$inlined;

         public {
            this.$this_dropWhile$inlined = var1;
            this.$predicate$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Ref.BooleanRef var3 = new Ref.BooleanRef();
            var3.element = false;
            Object var4 = this.$this_dropWhile$inlined.collect((FlowCollector)(new FlowCollector(var1, var3, this) {
               final Ref.BooleanRef $matched$inlined;
               final FlowCollector $this_unsafeFlow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.$this_unsafeFlow$inlined = var1;
                  this.$matched$inlined = var2;
                  this.this$0 = var3;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var11;
                  label47: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var11 = var4;
                           break label47;
                        }
                     }

                     var11 = new ContinuationImpl(this, var2) {
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

                  Object var7 = ((<undefinedtype>)var11).result;
                  Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var11).label;
                  <undefinedtype> var5;
                  Object var6;
                  Continuation var10;
                  Object var14;
                  if (var3 != 0) {
                     label60: {
                        if (var3 != 1) {
                           if (var3 == 2) {
                              var14 = ((<undefinedtype>)var11).L$3;
                              var10 = (Continuation)((<undefinedtype>)var11).L$2;
                              var6 = ((<undefinedtype>)var11).L$1;
                              var5 = (<undefinedtype>)((<undefinedtype>)var11).L$0;
                              ResultKt.throwOnFailure(var7);
                              break label60;
                           }

                           if (var3 != 3) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }
                        }

                        var1 = ((<undefinedtype>)var11).L$3;
                        var10 = (Continuation)((<undefinedtype>)var11).L$2;
                        var1 = ((<undefinedtype>)var11).L$1;
                        <undefinedtype> var12 = (<undefinedtype>)((<undefinedtype>)var11).L$0;
                        ResultKt.throwOnFailure(var7);
                        return Unit.INSTANCE;
                     }
                  } else {
                     ResultKt.throwOnFailure(var7);
                     Continuation var8 = (Continuation)var11;
                     if (this.$matched$inlined.element) {
                        FlowCollector var15 = this.$this_unsafeFlow$inlined;
                        ((<undefinedtype>)var11).L$0 = this;
                        ((<undefinedtype>)var11).L$1 = var1;
                        ((<undefinedtype>)var11).L$2 = var8;
                        ((<undefinedtype>)var11).L$3 = var1;
                        ((<undefinedtype>)var11).label = 1;
                        if (var15.emit(var1, (Continuation)var11) == var9) {
                           return var9;
                        }

                        return Unit.INSTANCE;
                     }

                     Function2 var13 = this.this$0.$predicate$inlined;
                     ((<undefinedtype>)var11).L$0 = this;
                     ((<undefinedtype>)var11).L$1 = var1;
                     ((<undefinedtype>)var11).L$2 = var8;
                     ((<undefinedtype>)var11).L$3 = var1;
                     ((<undefinedtype>)var11).label = 2;
                     InlineMarker.mark(6);
                     var7 = var13.invoke(var1, var11);
                     InlineMarker.mark(7);
                     if (var7 == var9) {
                        return var9;
                     }

                     var5 = this;
                     var6 = var1;
                     var14 = var1;
                     var10 = var8;
                  }

                  if (!(Boolean)var7) {
                     var5.$matched$inlined.element = true;
                     FlowCollector var16 = var5.$this_unsafeFlow$inlined;
                     ((<undefinedtype>)var11).L$0 = var5;
                     ((<undefinedtype>)var11).L$1 = var6;
                     ((<undefinedtype>)var11).L$2 = var10;
                     ((<undefinedtype>)var11).L$3 = var14;
                     ((<undefinedtype>)var11).label = 3;
                     if (var16.emit(var14, (Continuation)var11) == var9) {
                        return var9;
                     }
                  }

                  return Unit.INSTANCE;
               }
            }), var2);
            return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
         }
      });
   }

   // $FF: synthetic method
   static final Object emitAbort$FlowKt__LimitKt(FlowCollector var0, Object var1, Continuation var2) {
      Object var8;
      label23: {
         if (var2 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var2;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var8 = var4;
               break label23;
            }
         }

         var8 = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt__LimitKt.emitAbort$FlowKt__LimitKt((FlowCollector)null, (Object)null, this);
            }
         };
      }

      Object var5 = ((<undefinedtype>)var8).result;
      Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var3 = ((<undefinedtype>)var8).label;
      FlowCollector var9;
      if (var3 != 0) {
         if (var3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         Object var7 = ((<undefinedtype>)var8).L$1;
         var9 = (FlowCollector)((<undefinedtype>)var8).L$0;
         ResultKt.throwOnFailure(var5);
      } else {
         ResultKt.throwOnFailure(var5);
         ((<undefinedtype>)var8).L$0 = var0;
         ((<undefinedtype>)var8).L$1 = var1;
         ((<undefinedtype>)var8).label = 1;
         var9 = var0;
         if (var0.emit(var1, (Continuation)var8) == var6) {
            return var6;
         }
      }

      throw (Throwable)(new AbortFlowException(var9));
   }

   public static final Flow take(Flow var0, int var1) {
      boolean var2;
      if (var1 > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2) {
         return (Flow)(new Flow(var0, var1) {
            final int $count$inlined;
            final Flow $this_take$inlined;

            public {
               this.$this_take$inlined = var1;
               this.$count$inlined = var2;
            }

            public Object collect(FlowCollector var1, Continuation var2) {
               Object var26;
               label133: {
                  if (var2 instanceof <undefinedtype>) {
                     <undefinedtype> var4 = (<undefinedtype>)var2;
                     if ((var4.label & Integer.MIN_VALUE) != 0) {
                        var4.label += Integer.MIN_VALUE;
                        var26 = var4;
                        break label133;
                     }
                  }

                  var26 = new ContinuationImpl(this, var2) {
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

               Object var6 = ((<undefinedtype>)var26).result;
               Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               int var3 = ((<undefinedtype>)var26).label;
               AbortFlowException var10000;
               boolean var10001;
               FlowCollector var30;
               if (var3 != 0) {
                  if (var3 != 1) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  Flow var23 = (Flow)((<undefinedtype>)var26).L$5;
                  Ref.IntRef var24 = (Ref.IntRef)((<undefinedtype>)var26).L$4;
                  var30 = (FlowCollector)((<undefinedtype>)var26).L$3;
                  Continuation var25 = (Continuation)((<undefinedtype>)var26).L$2;
                  var1 = (FlowCollector)((<undefinedtype>)var26).L$1;
                  <undefinedtype> var27 = (<undefinedtype>)((<undefinedtype>)var26).L$0;

                  try {
                     ResultKt.throwOnFailure(var6);
                     return Unit.INSTANCE;
                  } catch (AbortFlowException var10) {
                     var10000 = var10;
                     var10001 = false;
                  }
               } else {
                  label147: {
                     ResultKt.throwOnFailure(var6);
                     Continuation var31 = (Continuation)var26;
                     Ref.IntRef var8 = new Ref.IntRef();
                     var8.element = 0;
                     var30 = var1;

                     Flow var7;
                     try {
                        var7 = this.$this_take$inlined;
                     } catch (AbortFlowException var22) {
                        var10000 = var22;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     FlowCollector var9;
                     try {
                        var9 = new FlowCollector() {
                           final Ref.IntRef $consumed$inlined;
                           final FlowCollector $this_unsafeFlow$inlined;
                           final <undefinedtype> this$0;

                           public {
                              this.$this_unsafeFlow$inlined = var1;
                              this.$consumed$inlined = var2;
                              this.this$0 = var3;
                           }

                           public Object emit(Object var1, Continuation var2) {
                              Ref.IntRef var3 = this.$consumed$inlined;
                              ++var3.element;
                              Unit var4;
                              if (var3.element < this.this$0.$count$inlined) {
                                 var1 = this.$this_unsafeFlow$inlined.emit(var1, var2);
                                 if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    return var1;
                                 }

                                 var4 = Unit.INSTANCE;
                              } else {
                                 var1 = FlowKt__LimitKt.emitAbort$FlowKt__LimitKt(this.$this_unsafeFlow$inlined, var1, var2);
                                 if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    return var1;
                                 }

                                 var4 = Unit.INSTANCE;
                              }

                              return var4;
                           }
                        };
                     } catch (AbortFlowException var21) {
                        var10000 = var21;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     try {
                        var9.<init>(var1, var8, this);
                     } catch (AbortFlowException var20) {
                        var10000 = var20;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     try {
                        var9 = (FlowCollector)var9;
                     } catch (AbortFlowException var19) {
                        var10000 = var19;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     try {
                        ((<undefinedtype>)var26).L$0 = this;
                     } catch (AbortFlowException var18) {
                        var10000 = var18;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     try {
                        ((<undefinedtype>)var26).L$1 = var1;
                     } catch (AbortFlowException var17) {
                        var10000 = var17;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     try {
                        ((<undefinedtype>)var26).L$2 = var31;
                     } catch (AbortFlowException var16) {
                        var10000 = var16;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     try {
                        ((<undefinedtype>)var26).L$3 = var1;
                     } catch (AbortFlowException var15) {
                        var10000 = var15;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     try {
                        ((<undefinedtype>)var26).L$4 = var8;
                     } catch (AbortFlowException var14) {
                        var10000 = var14;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     try {
                        ((<undefinedtype>)var26).L$5 = var7;
                     } catch (AbortFlowException var13) {
                        var10000 = var13;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     try {
                        ((<undefinedtype>)var26).label = 1;
                     } catch (AbortFlowException var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label147;
                     }

                     var30 = var1;

                     Object var29;
                     try {
                        var29 = var7.collect(var9, (Continuation)var26);
                     } catch (AbortFlowException var11) {
                        var10000 = var11;
                        var10001 = false;
                        break label147;
                     }

                     if (var29 == var5) {
                        return var5;
                     }

                     return Unit.INSTANCE;
                  }
               }

               AbortFlowException var28 = var10000;
               FlowExceptions_commonKt.checkOwnership(var28, var30);
               return Unit.INSTANCE;
            }
         });
      } else {
         throw (Throwable)(new IllegalArgumentException(("Requested element count " + var1 + " should be positive").toString()));
      }
   }

   public static final Flow takeWhile(Flow var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function2 $predicate$inlined;
         final Flow $this_takeWhile$inlined;

         public {
            this.$this_takeWhile$inlined = var1;
            this.$predicate$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var13;
            label45: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var13 = var4;
                     break label45;
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

            Object var5 = ((<undefinedtype>)var13).result;
            Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var13).label;
            AbortFlowException var15;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               <undefinedtype> var17 = (<undefinedtype>)((<undefinedtype>)var13).L$5;
               Flow var11 = (Flow)((<undefinedtype>)var13).L$4;
               var1 = (FlowCollector)((<undefinedtype>)var13).L$3;
               Continuation var12 = (Continuation)((<undefinedtype>)var13).L$2;
               var1 = (FlowCollector)((<undefinedtype>)var13).L$1;
               <undefinedtype> var14 = (<undefinedtype>)((<undefinedtype>)var13).L$0;

               try {
                  ResultKt.throwOnFailure(var5);
                  return Unit.INSTANCE;
               } catch (AbortFlowException var9) {
                  var15 = var9;
                  var13 = var17;
               }
            } else {
               label59: {
                  ResultKt.throwOnFailure(var5);
                  Continuation var19 = (Continuation)var13;
                  Flow var7 = this.$this_takeWhile$inlined;
                  FlowCollector var18 = new FlowCollector(var1, this) {
                     final FlowCollector $this_unsafeFlow$inlined;
                     final <undefinedtype> this$0;

                     public {
                        this.$this_unsafeFlow$inlined = var1;
                        this.this$0 = var2;
                     }

                     public Object emit(Object var1, Continuation var2) {
                        Object var5;
                        label41: {
                           if (var2 instanceof <undefinedtype>) {
                              var5 = (<undefinedtype>)var2;
                              if ((((<undefinedtype>)var5).label & Integer.MIN_VALUE) != 0) {
                                 ((<undefinedtype>)var5).label += Integer.MIN_VALUE;
                                 break label41;
                              }
                           }

                           var5 = new ContinuationImpl(this, var2) {
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

                        boolean var4;
                        <undefinedtype> var11;
                        label36: {
                           Object var9 = ((<undefinedtype>)var5).result;
                           Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                           int var3 = ((<undefinedtype>)var5).label;
                           var4 = true;
                           Object var6;
                           Object var7;
                           Continuation var8;
                           Object var13;
                           if (var3 != 0) {
                              if (var3 != 1) {
                                 if (var3 != 2) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                 }

                                 var1 = ((<undefinedtype>)var5).L$3;
                                 Continuation var12 = (Continuation)((<undefinedtype>)var5).L$2;
                                 var1 = ((<undefinedtype>)var5).L$1;
                                 var11 = (<undefinedtype>)((<undefinedtype>)var5).L$0;
                                 ResultKt.throwOnFailure(var9);
                                 break label36;
                              }

                              var13 = ((<undefinedtype>)var5).L$3;
                              var8 = (Continuation)((<undefinedtype>)var5).L$2;
                              var7 = ((<undefinedtype>)var5).L$1;
                              var11 = (<undefinedtype>)((<undefinedtype>)var5).L$0;
                              ResultKt.throwOnFailure(var9);
                              var6 = var13;
                           } else {
                              ResultKt.throwOnFailure(var9);
                              var8 = (Continuation)var5;
                              Function2 var14 = this.this$0.$predicate$inlined;
                              ((<undefinedtype>)var5).L$0 = this;
                              ((<undefinedtype>)var5).L$1 = var1;
                              ((<undefinedtype>)var5).L$2 = var8;
                              ((<undefinedtype>)var5).L$3 = var1;
                              ((<undefinedtype>)var5).label = 1;
                              InlineMarker.mark(6);
                              var9 = var14.invoke(var1, var5);
                              InlineMarker.mark(7);
                              if (var9 == var10) {
                                 return var10;
                              }

                              var13 = var1;
                              var11 = this;
                              var6 = var13;
                              var7 = var13;
                           }

                           if ((Boolean)var9) {
                              FlowCollector var15 = var11.$this_unsafeFlow$inlined;
                              ((<undefinedtype>)var5).L$0 = var11;
                              ((<undefinedtype>)var5).L$1 = var7;
                              ((<undefinedtype>)var5).L$2 = var8;
                              ((<undefinedtype>)var5).L$3 = var6;
                              ((<undefinedtype>)var5).label = 2;
                              if (var15.emit(var6, (Continuation)var5) == var10) {
                                 return var10;
                              }
                           } else {
                              var4 = false;
                           }
                        }

                        if (Boxing.boxBoolean(var4)) {
                           return Unit.INSTANCE;
                        } else {
                           throw (Throwable)(new AbortFlowException((FlowCollector)var11));
                        }
                     }
                  };

                  Object var16;
                  try {
                     FlowCollector var8 = (FlowCollector)var18;
                     ((<undefinedtype>)var13).L$0 = this;
                     ((<undefinedtype>)var13).L$1 = var1;
                     ((<undefinedtype>)var13).L$2 = var19;
                     ((<undefinedtype>)var13).L$3 = var1;
                     ((<undefinedtype>)var13).L$4 = var7;
                     ((<undefinedtype>)var13).L$5 = var18;
                     ((<undefinedtype>)var13).label = 1;
                     var16 = var7.collect(var8, (Continuation)var13);
                  } catch (AbortFlowException var10) {
                     var15 = var10;
                     var13 = var18;
                     break label59;
                  }

                  if (var16 == var6) {
                     return var6;
                  }

                  return Unit.INSTANCE;
               }
            }

            FlowExceptions_commonKt.checkOwnership(var15, (FlowCollector)var13);
            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow transformWhile(Flow var0, Function3 var1) {
      return FlowKt.flow((Function2)(new Function2(var0, var1, (Continuation)null) {
         final Flow $this_transformWhile;
         final Function3 $transform;
         Object L$0;
         Object L$1;
         Object L$2;
         int label;
         private FlowCollector p$;

         {
            this.$this_transformWhile = var1;
            this.$transform = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$this_transformWhile, this.$transform, var2);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            Object var3;
            AbortFlowException var9;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var3 = (<undefinedtype>)this.L$2;
               Flow var11 = (Flow)this.L$1;
               FlowCollector var12 = (FlowCollector)this.L$0;

               try {
                  ResultKt.throwOnFailure(var1);
                  return Unit.INSTANCE;
               } catch (AbortFlowException var7) {
                  var9 = var7;
               }
            } else {
               label48: {
                  ResultKt.throwOnFailure(var1);
                  FlowCollector var10 = this.p$;
                  Flow var5 = this.$this_transformWhile;
                  var3 = new FlowCollector(this, var10) {
                     final FlowCollector $this_flow$inlined;
                     final <undefinedtype> this$0;

                     public {
                        this.this$0 = var1;
                        this.$this_flow$inlined = var2;
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
                        <undefinedtype> var10;
                        if (var3 != 0) {
                           if (var3 != 1) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           var1 = ((<undefinedtype>)var9).L$3;
                           Continuation var8 = (Continuation)((<undefinedtype>)var9).L$2;
                           var1 = ((<undefinedtype>)var9).L$1;
                           var10 = (<undefinedtype>)((<undefinedtype>)var9).L$0;
                           ResultKt.throwOnFailure(var11);
                           var1 = var11;
                        } else {
                           ResultKt.throwOnFailure(var11);
                           Continuation var7 = (Continuation)var9;
                           Function3 var12 = this.this$0.$transform;
                           FlowCollector var6 = this.$this_flow$inlined;
                           ((<undefinedtype>)var9).L$0 = this;
                           ((<undefinedtype>)var9).L$1 = var1;
                           ((<undefinedtype>)var9).L$2 = var7;
                           ((<undefinedtype>)var9).L$3 = var1;
                           ((<undefinedtype>)var9).label = 1;
                           InlineMarker.mark(6);
                           var1 = var12.invoke(var6, var1, var9);
                           InlineMarker.mark(7);
                           if (var1 == var5) {
                              return var5;
                           }

                           var10 = this;
                        }

                        if ((Boolean)var1) {
                           return Unit.INSTANCE;
                        } else {
                           throw (Throwable)(new AbortFlowException((FlowCollector)var10));
                        }
                     }
                  };

                  try {
                     FlowCollector var6 = (FlowCollector)var3;
                     this.L$0 = var10;
                     this.L$1 = var5;
                     this.L$2 = var3;
                     this.label = 1;
                     var1 = var5.collect(var6, this);
                  } catch (AbortFlowException var8) {
                     var9 = var8;
                     break label48;
                  }

                  if (var1 == var4) {
                     return var4;
                  }

                  return Unit.INSTANCE;
               }
            }

            FlowExceptions_commonKt.checkOwnership(var9, (FlowCollector)var3);
            return Unit.INSTANCE;
         }
      }));
   }
}
