package kotlinx.coroutines.flow;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.SafeCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001as\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032D\u0010\u0004\u001a@\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0003\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005¢\u0006\u0002\b\f2\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001aj\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00102D\u0010\u0004\u001a@\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0003\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001aS\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00102-\u0010\u0004\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0013¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001aS\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00102-\u0010\u0004\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0013¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001as\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00020\u00102D\b\u0005\u0010\u0016\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u0003\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005¢\u0006\u0002\b\fH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001as\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00020\u00102D\b\u0005\u0010\u0016\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u0003\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005¢\u0006\u0002\b\fH\u0081\bø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"},
   d2 = {"invokeSafely", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "action", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "invokeSafely$FlowKt__EmittersKt", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/jvm/functions/Function3;Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCompletion", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "onEmpty", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "onStart", "transform", "R", "value", "unsafeTransform", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__EmittersKt {
   // $FF: synthetic method
   static final Object invokeSafely$FlowKt__EmittersKt(FlowCollector var0, Function3 var1, Throwable var2, Continuation var3) {
      Object var53;
      label542: {
         if (var3 instanceof <undefinedtype>) {
            <undefinedtype> var5 = (<undefinedtype>)var3;
            if ((var5.label & Integer.MIN_VALUE) != 0) {
               var5.label += Integer.MIN_VALUE;
               var53 = var5;
               break label542;
            }
         }

         var53 = new ContinuationImpl(var3) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt((FlowCollector)null, (Function3)null, (Throwable)null, this);
            }
         };
      }

      Object var6 = ((<undefinedtype>)var53).result;
      Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var4 = ((<undefinedtype>)var53).label;
      Throwable var10000;
      boolean var10001;
      Throwable var54;
      if (var4 != 0) {
         if (var4 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var54 = (Throwable)((<undefinedtype>)var53).L$2;
         Function3 var50 = (Function3)((<undefinedtype>)var53).L$1;
         var0 = (FlowCollector)((<undefinedtype>)var53).L$0;

         label512:
         try {
            ResultKt.throwOnFailure(var6);
            return Unit.INSTANCE;
         } catch (Throwable var44) {
            var10000 = var44;
            var10001 = false;
            break label512;
         }
      } else {
         label554: {
            ResultKt.throwOnFailure(var6);
            var54 = var2;

            try {
               ((<undefinedtype>)var53).L$0 = var0;
            } catch (Throwable var49) {
               var10000 = var49;
               var10001 = false;
               break label554;
            }

            var54 = var2;

            try {
               ((<undefinedtype>)var53).L$1 = var1;
            } catch (Throwable var48) {
               var10000 = var48;
               var10001 = false;
               break label554;
            }

            var54 = var2;

            try {
               ((<undefinedtype>)var53).L$2 = var2;
            } catch (Throwable var47) {
               var10000 = var47;
               var10001 = false;
               break label554;
            }

            var54 = var2;

            try {
               ((<undefinedtype>)var53).label = 1;
            } catch (Throwable var46) {
               var10000 = var46;
               var10001 = false;
               break label554;
            }

            var54 = var2;

            Object var52;
            try {
               var52 = var1.invoke(var0, var2, var53);
            } catch (Throwable var45) {
               var10000 = var45;
               var10001 = false;
               break label554;
            }

            if (var52 == var7) {
               return var7;
            }

            return Unit.INSTANCE;
         }
      }

      Throwable var51 = var10000;
      if (var54 != null && var54 != var51) {
         ExceptionsKt.addSuppressed(var51, var54);
      }

      throw var51;
   }

   public static final Flow onCompletion(Flow var0, Function3 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function3 $action$inlined;
         final Flow $this_onCompletion$inlined;

         public {
            this.$this_onCompletion$inlined = var1;
            this.$action$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var4;
            label418: {
               if (var2 instanceof <undefinedtype>) {
                  var4 = (<undefinedtype>)var2;
                  if ((((<undefinedtype>)var4).label & Integer.MIN_VALUE) != 0) {
                     ((<undefinedtype>)var4).label += Integer.MIN_VALUE;
                     break label418;
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
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
            }

            SafeCollector var41;
            label431: {
               Continuation var5;
               FlowCollector var7;
               Object var9;
               Throwable var32;
               FlowCollector var33;
               Throwable var37;
               <undefinedtype> var40;
               label411: {
                  SafeCollector var34;
                  Object var36;
                  label421: {
                     Object var6 = ((<undefinedtype>)var4).result;
                     var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var3 = ((<undefinedtype>)var4).label;
                     <undefinedtype> var8;
                     if (var3 != 0) {
                        <undefinedtype> var31;
                        if (var3 != 1) {
                           if (var3 == 2) {
                              var37 = (Throwable)((<undefinedtype>)var4).L$4;
                              var1 = (FlowCollector)((<undefinedtype>)var4).L$3;
                              Continuation var35 = (Continuation)((<undefinedtype>)var4).L$2;
                              var1 = (FlowCollector)((<undefinedtype>)var4).L$1;
                              var31 = (<undefinedtype>)((<undefinedtype>)var4).L$0;
                              ResultKt.throwOnFailure(var6);
                              throw var37;
                           }

                           if (var3 != 3) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           var34 = (SafeCollector)((<undefinedtype>)var4).L$4;
                           var33 = (FlowCollector)((<undefinedtype>)var4).L$3;
                           var2 = (Continuation)((<undefinedtype>)var4).L$2;
                           var33 = (FlowCollector)((<undefinedtype>)var4).L$1;
                           var36 = (<undefinedtype>)((<undefinedtype>)var4).L$0;

                           try {
                              ResultKt.throwOnFailure(var6);
                              break label431;
                           } finally {
                              break label421;
                           }
                        }

                        var7 = (FlowCollector)((<undefinedtype>)var4).L$3;
                        var5 = (Continuation)((<undefinedtype>)var4).L$2;
                        var33 = (FlowCollector)((<undefinedtype>)var4).L$1;
                        var31 = (<undefinedtype>)((<undefinedtype>)var4).L$0;

                        try {
                           ResultKt.throwOnFailure(var6);
                        } catch (Throwable var30) {
                           var40 = var31;
                           var32 = var30;
                           break label411;
                        }

                        var8 = var31;
                        var1 = var7;
                     } else {
                        ResultKt.throwOnFailure(var6);
                        var5 = (Continuation)var4;

                        try {
                           Flow var38 = this.$this_onCompletion$inlined;
                           ((<undefinedtype>)var4).L$0 = this;
                           ((<undefinedtype>)var4).L$1 = var1;
                           ((<undefinedtype>)var4).L$2 = var5;
                           ((<undefinedtype>)var4).L$3 = var1;
                           ((<undefinedtype>)var4).label = 1;
                           var36 = var38.collect(var1, (Continuation)var4);
                        } catch (Throwable var29) {
                           var40 = this;
                           var33 = var1;
                           var32 = var29;
                           var7 = var33;
                           break label411;
                        }

                        if (var36 == var9) {
                           return var9;
                        }

                        var8 = this;
                        var33 = var1;
                     }

                     var41 = new SafeCollector(var1, ((Continuation)var4).getContext());

                     Object var39;
                     try {
                        Function3 var42 = var8.$action$inlined;
                        ((<undefinedtype>)var4).L$0 = var8;
                        ((<undefinedtype>)var4).L$1 = var33;
                        ((<undefinedtype>)var4).L$2 = var5;
                        ((<undefinedtype>)var4).L$3 = var1;
                        ((<undefinedtype>)var4).L$4 = var41;
                        ((<undefinedtype>)var4).label = 3;
                        InlineMarker.mark(6);
                        var39 = var42.invoke(var41, (Object)null, var4);
                        InlineMarker.mark(7);
                     } catch (Throwable var28) {
                        var36 = var28;
                        var34 = var41;
                        break label421;
                     }

                     if (var39 == var9) {
                        return var9;
                     }
                     break label431;
                  }

                  var34.releaseIntercepted();
                  throw var36;
               }

               FlowCollector var10 = (FlowCollector)(new ThrowingCollector(var32));
               Function3 var43 = var40.$action$inlined;
               ((<undefinedtype>)var4).L$0 = var40;
               ((<undefinedtype>)var4).L$1 = var33;
               ((<undefinedtype>)var4).L$2 = var5;
               ((<undefinedtype>)var4).L$3 = var7;
               ((<undefinedtype>)var4).L$4 = var32;
               ((<undefinedtype>)var4).label = 2;
               var37 = var32;
               if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(var10, var43, var32, (Continuation)var4) == var9) {
                  return var9;
               }

               throw var37;
            }

            var41.releaseIntercepted();
            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow onEmpty(Flow var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function2 $action$inlined;
         final Flow $this_onEmpty$inlined;

         public {
            this.$this_onEmpty$inlined = var1;
            this.$action$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var20;
            label172: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var20 = var4;
                     break label172;
                  }
               }

               var20 = new ContinuationImpl(this, var2) {
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

            Object var8 = ((<undefinedtype>)var20).result;
            Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var20).label;
            FlowCollector var5;
            FlowCollector var6;
            <undefinedtype> var7;
            Continuation var18;
            Ref.BooleanRef var23;
            if (var3 != 0) {
               if (var3 != 1) {
                  if (var3 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  SafeCollector var19 = (SafeCollector)((<undefinedtype>)var20).L$5;
                  var23 = (Ref.BooleanRef)((<undefinedtype>)var20).L$4;
                  FlowCollector var24 = (FlowCollector)((<undefinedtype>)var20).L$3;
                  Continuation var26 = (Continuation)((<undefinedtype>)var20).L$2;
                  var24 = (FlowCollector)((<undefinedtype>)var20).L$1;
                  <undefinedtype> var22 = (<undefinedtype>)((<undefinedtype>)var20).L$0;

                  try {
                     ResultKt.throwOnFailure(var8);
                     return Unit.INSTANCE;
                  } finally {
                     var19.releaseIntercepted();
                  }
               }

               Flow var17 = (Flow)((<undefinedtype>)var20).L$5;
               var23 = (Ref.BooleanRef)((<undefinedtype>)var20).L$4;
               var6 = (FlowCollector)((<undefinedtype>)var20).L$3;
               var18 = (Continuation)((<undefinedtype>)var20).L$2;
               var5 = (FlowCollector)((<undefinedtype>)var20).L$1;
               var7 = (<undefinedtype>)((<undefinedtype>)var20).L$0;
               ResultKt.throwOnFailure(var8);
            } else {
               ResultKt.throwOnFailure(var8);
               Continuation var27 = (Continuation)var20;
               var23 = new Ref.BooleanRef();
               var23.element = true;
               Flow var25 = this.$this_onEmpty$inlined;
               var6 = (FlowCollector)(new FlowCollector(var1, var23) {
                  final Ref.BooleanRef $isEmpty$inlined;
                  final FlowCollector $this_unsafeFlow$inlined;

                  public {
                     this.$this_unsafeFlow$inlined = var1;
                     this.$isEmpty$inlined = var2;
                  }

                  public Object emit(Object var1, Continuation var2) {
                     this.$isEmpty$inlined.element = false;
                     var1 = this.$this_unsafeFlow$inlined.emit(var1, var2);
                     return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
                  }
               });
               ((<undefinedtype>)var20).L$0 = this;
               ((<undefinedtype>)var20).L$1 = var1;
               ((<undefinedtype>)var20).L$2 = var27;
               ((<undefinedtype>)var20).L$3 = var1;
               ((<undefinedtype>)var20).L$4 = var23;
               ((<undefinedtype>)var20).L$5 = var25;
               ((<undefinedtype>)var20).label = 1;
               if (var25.collect(var6, (Continuation)var20) == var9) {
                  return var9;
               }

               var7 = this;
               var6 = var1;
               var5 = var1;
               var18 = var27;
            }

            if (var23.element) {
               SafeCollector var28 = new SafeCollector(var6, ((Continuation)var20).getContext());

               Object var21;
               try {
                  Function2 var10 = var7.$action$inlined;
                  ((<undefinedtype>)var20).L$0 = var7;
                  ((<undefinedtype>)var20).L$1 = var5;
                  ((<undefinedtype>)var20).L$2 = var18;
                  ((<undefinedtype>)var20).L$3 = var6;
                  ((<undefinedtype>)var20).L$4 = var23;
                  ((<undefinedtype>)var20).L$5 = var28;
                  ((<undefinedtype>)var20).label = 2;
                  InlineMarker.mark(6);
                  var21 = var10.invoke(var28, var20);
                  InlineMarker.mark(7);
               } finally {
                  ;
               }

               if (var21 == var9) {
                  return var9;
               }
            }

            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow onStart(Flow var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function2 $action$inlined;
         final Flow $this_onStart$inlined;

         public {
            this.$this_onStart$inlined = var1;
            this.$action$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var18;
            label161: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var18 = var4;
                     break label161;
                  }
               }

               var18 = new ContinuationImpl(this, var2) {
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
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
            }

            Object var22 = ((<undefinedtype>)var18).result;
            Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var18).label;
            FlowCollector var5;
            FlowCollector var6;
            Continuation var7;
            <undefinedtype> var8;
            SafeCollector var23;
            if (var3 != 0) {
               SafeCollector var17;
               if (var3 != 1) {
                  if (var3 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var17 = (SafeCollector)((<undefinedtype>)var18).L$4;
                  var1 = (FlowCollector)((<undefinedtype>)var18).L$3;
                  Continuation var19 = (Continuation)((<undefinedtype>)var18).L$2;
                  var1 = (FlowCollector)((<undefinedtype>)var18).L$1;
                  <undefinedtype> var20 = (<undefinedtype>)((<undefinedtype>)var18).L$0;
                  ResultKt.throwOnFailure(var22);
                  return Unit.INSTANCE;
               }

               var17 = (SafeCollector)((<undefinedtype>)var18).L$4;
               var5 = (FlowCollector)((<undefinedtype>)var18).L$3;
               var7 = (Continuation)((<undefinedtype>)var18).L$2;
               var6 = (FlowCollector)((<undefinedtype>)var18).L$1;
               var8 = (<undefinedtype>)((<undefinedtype>)var18).L$0;
               boolean var14 = false;

               try {
                  var14 = true;
                  ResultKt.throwOnFailure(var22);
                  var14 = false;
               } finally {
                  if (var14) {
                     var17.releaseIntercepted();
                  }
               }

               var23 = var17;
            } else {
               ResultKt.throwOnFailure(var22);
               var7 = (Continuation)var18;
               var23 = new SafeCollector(var1, ((Continuation)var18).getContext());

               Object var25;
               try {
                  Function2 var24 = this.$action$inlined;
                  ((<undefinedtype>)var18).L$0 = this;
                  ((<undefinedtype>)var18).L$1 = var1;
                  ((<undefinedtype>)var18).L$2 = var7;
                  ((<undefinedtype>)var18).L$3 = var1;
                  ((<undefinedtype>)var18).L$4 = var23;
                  ((<undefinedtype>)var18).label = 1;
                  InlineMarker.mark(6);
                  var25 = var24.invoke(var23, var18);
                  InlineMarker.mark(7);
               } finally {
                  ;
               }

               if (var25 == var9) {
                  return var9;
               }

               var8 = this;
               var6 = var1;
               var5 = var1;
            }

            var23.releaseIntercepted();
            Flow var21 = var8.$this_onStart$inlined;
            ((<undefinedtype>)var18).L$0 = var8;
            ((<undefinedtype>)var18).L$1 = var6;
            ((<undefinedtype>)var18).L$2 = var7;
            ((<undefinedtype>)var18).L$3 = var5;
            ((<undefinedtype>)var18).L$4 = var23;
            ((<undefinedtype>)var18).label = 2;
            if (var21.collect(var5, (Continuation)var18) == var9) {
               return var9;
            } else {
               return Unit.INSTANCE;
            }
         }
      });
   }

   public static final Flow transform(Flow var0, Function3 var1) {
      return FlowKt.flow((Function2)(new Function2(var0, var1, (Continuation)null) {
         final Flow $this_transform;
         final Function3 $transform;
         Object L$0;
         Object L$1;
         int label;
         private FlowCollector p$;

         public {
            this.$this_transform = var1;
            this.$transform = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$this_transform, this.$transform, var2);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Flow var7 = (Flow)this.L$1;
               FlowCollector var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var6 = this.p$;
               Flow var5 = this.$this_transform;
               FlowCollector var4 = (FlowCollector)(new FlowCollector(this, var6) {
                  final FlowCollector $this_flow$inlined;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                     this.$this_flow$inlined = var2;
                  }

                  public Object emit(Object var1, Continuation var2) {
                     return this.this$0.$transform.invoke(this.$this_flow$inlined, var1, var2);
                  }

                  public Object emit$$forInline(Object var1, Continuation var2) {
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
                           return this.this$0.emit((Object)null, this);
                        }
                     };
                     InlineMarker.mark(5);
                     return this.this$0.$transform.invoke(this.$this_flow$inlined, var1, var2);
                  }
               });
               this.L$0 = var6;
               this.L$1 = var5;
               this.label = 1;
               if (var5.collect(var4, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }

         public final Object invokeSuspend$$forInline(Object var1) {
            FlowCollector var2 = this.p$;
            Flow var3 = this.$this_transform;
            var2 = (FlowCollector)(new FlowCollector(this, var2) {
               final FlowCollector $this_flow$inlined;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
                  this.$this_flow$inlined = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  return this.this$0.$transform.invoke(this.$this_flow$inlined, var1, var2);
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
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
                        return this.this$0.emit((Object)null, this);
                     }
                  };
                  InlineMarker.mark(5);
                  return this.this$0.$transform.invoke(this.$this_flow$inlined, var1, var2);
               }
            });
            InlineMarker.mark(0);
            var3.collect(var2, this);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      }));
   }

   public static final Flow unsafeTransform(Flow var0, Function3 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Flow $this_unsafeTransform$inlined;
         final Function3 $transform$inlined;

         public {
            this.$this_unsafeTransform$inlined = var1;
            this.$transform$inlined = var2;
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
                  return this.this$0.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, var1, var2);
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
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
                        return this.this$0.emit((Object)null, this);
                     }
                  };
                  InlineMarker.mark(5);
                  return this.this$0.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, var1, var2);
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
                  return this.this$0.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, var1, var2);
               }

               public Object emit$$forInline(Object var1, Continuation var2) {
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
                        return this.this$0.emit((Object)null, this);
                     }
                  };
                  InlineMarker.mark(5);
                  return this.this$0.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, var1, var2);
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
}
