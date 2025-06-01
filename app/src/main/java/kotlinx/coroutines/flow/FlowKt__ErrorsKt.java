package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001ah\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012B\u0010\u0003\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0004¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a1\u0010\u000f\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u0019\u0010\u0012\u001a\u00020\u0013*\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¢\u0006\u0002\b\u0016\u001a\u001b\u0010\u0017\u001a\u00020\u0013*\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\b\u0019\u001aB\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dH\u0007\u001a>\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dH\u0007\u001ac\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020!23\b\u0002\u0010\u001c\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\"ø\u0001\u0000¢\u0006\u0002\u0010#\u001a}\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012W\u0010\u001c\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0%¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010'*\\\b\u0007\u0010(\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001d2\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dB6\b)\u0012\n\b*\u0012\u0006\b\n0+8,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u001c\b/\u0012\u0018\b\u000bB\u0014\b0\u0012\u0006\b1\u0012\u0002\b\f\u0012\b\b2\u0012\u0004\b\b(3\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"},
   d2 = {"catch", "Lkotlinx/coroutines/flow/Flow;", "T", "action", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "catchImpl", "collector", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCancellationCause", "", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "isCancellationCause$FlowKt__ErrorsKt", "isSameExceptionAs", "other", "isSameExceptionAs$FlowKt__ErrorsKt", "onErrorCollect", "fallback", "predicate", "Lkotlin/Function1;", "retry", "retries", "", "", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;JLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "retryWhen", "Lkotlin/Function4;", "attempt", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "ExceptionPredicate", "Lkotlin/Deprecated;", "level", "Lkotlin/DeprecationLevel;", "ERROR", "message", "Use (Throwable) -> Boolean functional type", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "(Throwable) -> Boolean", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__ErrorsKt {
   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use (Throwable) -> Boolean functional type",
      replaceWith = @ReplaceWith(
   expression = "(Throwable) -> Boolean",
   imports = {}
)
   )
   public static void ExceptionPredicate$annotations() {
   }

   public static final Flow catch(Flow var0, Function3 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function3 $action$inlined;
         final Flow $this_catch$inlined;

         public {
            this.$this_catch$inlined = var1;
            this.$action$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var11;
            label36: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var11 = var4;
                     break label36;
                  }
               }

               var11 = new ContinuationImpl(this, var2) {
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

            Object var7 = ((<undefinedtype>)var11).result;
            Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var11).label;
            <undefinedtype> var5;
            Continuation var6;
            FlowCollector var15;
            if (var3 != 0) {
               if (var3 != 1) {
                  if (var3 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  Throwable var10 = (Throwable)((<undefinedtype>)var11).L$4;
                  var1 = (FlowCollector)((<undefinedtype>)var11).L$3;
                  Continuation var12 = (Continuation)((<undefinedtype>)var11).L$2;
                  var1 = (FlowCollector)((<undefinedtype>)var11).L$1;
                  <undefinedtype> var13 = (<undefinedtype>)((<undefinedtype>)var11).L$0;
                  ResultKt.throwOnFailure(var7);
                  return Unit.INSTANCE;
               }

               var15 = (FlowCollector)((<undefinedtype>)var11).L$3;
               var6 = (Continuation)((<undefinedtype>)var11).L$2;
               var1 = (FlowCollector)((<undefinedtype>)var11).L$1;
               var5 = (<undefinedtype>)((<undefinedtype>)var11).L$0;
               ResultKt.throwOnFailure(var7);
            } else {
               ResultKt.throwOnFailure(var7);
               Continuation var8 = (Continuation)var11;
               Flow var16 = this.$this_catch$inlined;
               ((<undefinedtype>)var11).L$0 = this;
               ((<undefinedtype>)var11).L$1 = var1;
               ((<undefinedtype>)var11).L$2 = var8;
               ((<undefinedtype>)var11).L$3 = var1;
               ((<undefinedtype>)var11).label = 1;
               var7 = FlowKt.catchImpl(var16, var1, (Continuation)var11);
               if (var7 == var9) {
                  return var9;
               }

               var5 = this;
               var15 = var1;
               var1 = var1;
               var6 = var8;
            }

            Throwable var17 = (Throwable)var7;
            if (var17 != null) {
               Function3 var18 = var5.$action$inlined;
               ((<undefinedtype>)var11).L$0 = var5;
               ((<undefinedtype>)var11).L$1 = var1;
               ((<undefinedtype>)var11).L$2 = var6;
               ((<undefinedtype>)var11).L$3 = var15;
               ((<undefinedtype>)var11).L$4 = var17;
               ((<undefinedtype>)var11).label = 2;
               InlineMarker.mark(6);
               Object var14 = var18.invoke(var15, var17, var11);
               InlineMarker.mark(7);
               if (var14 == var9) {
                  return var9;
               }
            }

            return Unit.INSTANCE;
         }
      });
   }

   public static final Object catchImpl(Flow var0, FlowCollector var1, Continuation var2) {
      Object var16;
      label141: {
         if (var2 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var2;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var16 = var4;
               break label141;
            }
         }

         var16 = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.catchImpl((Flow)null, (FlowCollector)null, this);
            }
         };
      }

      Object var17 = ((<undefinedtype>)var16).result;
      Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var3 = ((<undefinedtype>)var16).label;
      if (var3 != 0) {
         if (var3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Flow)((<undefinedtype>)var16).L$3;
         Ref.ObjectRef var15 = (Ref.ObjectRef)((<undefinedtype>)var16).L$2;
         FlowCollector var13 = (FlowCollector)((<undefinedtype>)var16).L$1;
         var0 = (Flow)((<undefinedtype>)var16).L$0;

         try {
            ResultKt.throwOnFailure(var17);
         } catch (Throwable var12) {
            if (!isSameExceptionAs$FlowKt__ErrorsKt(var12, (Throwable)var15.element) && !isCancellationCause$FlowKt__ErrorsKt(var12, ((Continuation)var16).getContext())) {
               return var12;
            }

            throw var12;
         }
      } else {
         ResultKt.throwOnFailure(var17);
         Ref.ObjectRef var18 = new Ref.ObjectRef();
         Throwable var6 = (Throwable)null;
         var18.element = null;

         Object var14;
         try {
            FlowCollector var19 = new FlowCollector(var1, var18) {
               final FlowCollector $collector$inlined;
               final Ref.ObjectRef $fromDownstream$inlined;

               public {
                  this.$collector$inlined = var1;
                  this.$fromDownstream$inlined = var2;
               }

               public Object emit(Object var1, Continuation var2) {
                  Object var14;
                  label121: {
                     if (var2 instanceof <undefinedtype>) {
                        <undefinedtype> var4 = (<undefinedtype>)var2;
                        if ((var4.label & Integer.MIN_VALUE) != 0) {
                           var4.label += Integer.MIN_VALUE;
                           var14 = var4;
                           break label121;
                        }
                     }

                     var14 = new ContinuationImpl(this, var2) {
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

                  Object var16 = ((<undefinedtype>)var14).result;
                  Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var3 = ((<undefinedtype>)var14).label;
                  if (var3 != 0) {
                     if (var3 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     var1 = ((<undefinedtype>)var14).L$3;
                     Continuation var13 = (Continuation)((<undefinedtype>)var14).L$2;
                     var1 = ((<undefinedtype>)var14).L$1;
                     <undefinedtype> var15 = (<undefinedtype>)((<undefinedtype>)var14).L$0;

                     try {
                        ResultKt.throwOnFailure(var16);
                     } catch (Throwable var12) {
                        var15.$fromDownstream$inlined.element = var12;
                        throw var12;
                     }
                  } else {
                     ResultKt.throwOnFailure(var16);
                     Continuation var6 = (Continuation)var14;

                     try {
                        FlowCollector var17 = this.$collector$inlined;
                        ((<undefinedtype>)var14).L$0 = this;
                        ((<undefinedtype>)var14).L$1 = var1;
                        ((<undefinedtype>)var14).L$2 = var6;
                        ((<undefinedtype>)var14).L$3 = var1;
                        ((<undefinedtype>)var14).label = 1;
                        var1 = var17.emit(var1, (Continuation)var14);
                     } finally {
                        ;
                     }

                     if (var1 == var5) {
                        return var5;
                     }
                  }

                  return Unit.INSTANCE;
               }
            };
            var19 = (FlowCollector)var19;
            ((<undefinedtype>)var16).L$0 = var0;
            ((<undefinedtype>)var16).L$1 = var1;
            ((<undefinedtype>)var16).L$2 = var18;
            ((<undefinedtype>)var16).L$3 = var0;
            ((<undefinedtype>)var16).label = 1;
            var14 = var0.collect(var19, (Continuation)var16);
         } finally {
            ;
         }

         if (var14 == var5) {
            return var5;
         }
      }

      return null;
   }

   private static final boolean isCancellationCause$FlowKt__ErrorsKt(Throwable var0, CoroutineContext var1) {
      Job var2 = (Job)var1.get((CoroutineContext.Key)Job.Key);
      return var2 != null && var2.isCancelled() ? isSameExceptionAs$FlowKt__ErrorsKt(var0, (Throwable)var2.getCancellationException()) : false;
   }

   private static final boolean isSameExceptionAs$FlowKt__ErrorsKt(Throwable var0, Throwable var1) {
      boolean var2;
      if (var1 != null) {
         if (DebugKt.getRECOVER_STACK_TRACES()) {
            var1 = StackTraceRecoveryKt.unwrapImpl(var1);
         }

         if (DebugKt.getRECOVER_STACK_TRACES()) {
            var0 = StackTraceRecoveryKt.unwrapImpl(var0);
         }

         if (Intrinsics.areEqual((Object)var1, (Object)var0)) {
            var2 = true;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use catch { e -> if (predicate(e)) emitAll(fallback) else throw e }",
      replaceWith = @ReplaceWith(
   expression = "catch { e -> if (predicate(e)) emitAll(fallback) else throw e }",
   imports = {}
)
   )
   public static final Flow onErrorCollect(Flow var0, Flow var1, Function1 var2) {
      return FlowKt.catch(var0, (Function3)(new Function3(var2, var1, (Continuation)null) {
         final Flow $fallback;
         final Function1 $predicate;
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
         int label;
         private FlowCollector p$;
         private Throwable p$0;

         {
            this.$predicate = var1;
            this.$fallback = var2;
         }

         public final Continuation create(FlowCollector var1, Throwable var2, Continuation var3) {
            Function3 var4 = new <anonymous constructor>(this.$predicate, this.$fallback, var3);
            var4.p$ = var1;
            var4.p$0 = var2;
            return var4;
         }

         public final Object invoke(Object var1, Object var2, Object var3) {
            return ((<undefinedtype>)this.create((FlowCollector)var1, (Throwable)var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Flow var7 = (Flow)this.L$3;
               FlowCollector var8 = (FlowCollector)this.L$2;
               Throwable var9 = (Throwable)this.L$1;
               var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var6 = this.p$;
               Throwable var4 = this.p$0;
               if (!(Boolean)this.$predicate.invoke(var4)) {
                  throw var4;
               }

               Flow var5 = this.$fallback;
               this.L$0 = var6;
               this.L$1 = var4;
               this.L$2 = var6;
               this.L$3 = var5;
               this.label = 1;
               if (var5.collect(var6, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   // $FF: synthetic method
   public static Flow onErrorCollect$default(Flow var0, Flow var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = (Function1)null.INSTANCE;
      }

      return FlowKt.onErrorCollect(var0, var1, var2);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "binary compatibility with retries: Int preview version"
   )
   public static final Flow retry(Flow var0, int var1, Function1 var2) {
      boolean var3;
      if (var1 > 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         return FlowKt.retryWhen(var0, (Function4)(new Function4(var1, var2, (Continuation)null) {
            final Function1 $predicate;
            final int $retries;
            int label;
            private FlowCollector p$;
            private Throwable p$0;
            private long p$1;

            {
               this.$retries = var1;
               this.$predicate = var2;
            }

            public final Continuation create(FlowCollector var1, Throwable var2, long var3, Continuation var5) {
               Function4 var6 = new <anonymous constructor>(this.$retries, this.$predicate, var5);
               var6.p$ = var1;
               var6.p$0 = var2;
               var6.p$1 = var3;
               return var6;
            }

            public final Object invoke(Object var1, Object var2, Object var3, Object var4) {
               return ((<undefinedtype>)this.create((FlowCollector)var1, (Throwable)var2, ((Number)var3).longValue(), (Continuation)var4)).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object var1) {
               IntrinsicsKt.getCOROUTINE_SUSPENDED();
               if (this.label != 0) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               } else {
                  ResultKt.throwOnFailure(var1);
                  Throwable var5 = this.p$0;
                  long var2 = this.p$1;
                  boolean var4;
                  if ((Boolean)this.$predicate.invoke(var5) && var2 < (long)this.$retries) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  return Boxing.boxBoolean(var4);
               }
            }
         }));
      } else {
         throw (Throwable)(new IllegalArgumentException(("Expected positive amount of retries, but had " + var1).toString()));
      }
   }

   public static final Flow retry(Flow var0, long var1, Function2 var3) {
      boolean var4;
      if (var1 > 0L) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (var4) {
         return FlowKt.retryWhen(var0, (Function4)(new Function4(var1, var3, (Continuation)null) {
            final Function2 $predicate;
            final long $retries;
            long J$0;
            Object L$0;
            Object L$1;
            int label;
            private FlowCollector p$;
            private Throwable p$0;
            private long p$1;

            {
               this.$retries = var1;
               this.$predicate = var3;
            }

            public final Continuation create(FlowCollector var1, Throwable var2, long var3, Continuation var5) {
               Function4 var6 = new <anonymous constructor>(this.$retries, this.$predicate, var5);
               var6.p$ = var1;
               var6.p$0 = var2;
               var6.p$1 = var3;
               return var6;
            }

            public final Object invoke(Object var1, Object var2, Object var3, Object var4) {
               return ((<undefinedtype>)this.create((FlowCollector)var1, (Throwable)var2, ((Number)var3).longValue(), (Continuation)var4)).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object var1) {
               boolean var5;
               label24: {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  var5 = true;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     Throwable var6 = (Throwable)this.L$1;
                     FlowCollector var10 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var8 = this.p$;
                     Throwable var9 = this.p$0;
                     long var3 = this.p$1;
                     if (var3 >= this.$retries) {
                        break label24;
                     }

                     Function2 var11 = this.$predicate;
                     this.L$0 = var8;
                     this.L$1 = var9;
                     this.J$0 = var3;
                     this.label = 1;
                     Object var12 = var11.invoke(var9, this);
                     var1 = var12;
                     if (var12 == var7) {
                        return var7;
                     }
                  }

                  if ((Boolean)var1) {
                     return Boxing.boxBoolean(var5);
                  }
               }

               var5 = false;
               return Boxing.boxBoolean(var5);
            }
         }));
      } else {
         throw (Throwable)(new IllegalArgumentException(("Expected positive amount of retries, but had " + var1).toString()));
      }
   }

   // $FF: synthetic method
   public static Flow retry$default(Flow var0, int var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = Integer.MAX_VALUE;
      }

      if ((var3 & 2) != 0) {
         var2 = (Function1)null.INSTANCE;
      }

      return FlowKt.retry(var0, var1, var2);
   }

   // $FF: synthetic method
   public static Flow retry$default(Flow var0, long var1, Function2 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = Long.MAX_VALUE;
      }

      if ((var4 & 2) != 0) {
         var3 = (Function2)(new Function2((Continuation)null) {
            int label;
            private Throwable p$0;

            public final Continuation create(Object var1, Continuation var2) {
               Function2 var3 = new <anonymous constructor>(var2);
               var3.p$0 = (Throwable)var1;
               return var3;
            }

            public final Object invoke(Object var1, Object var2) {
               return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object var1) {
               IntrinsicsKt.getCOROUTINE_SUSPENDED();
               if (this.label == 0) {
                  ResultKt.throwOnFailure(var1);
                  return Boxing.boxBoolean(true);
               } else {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }
            }
         });
      }

      return FlowKt.retry(var0, var1, var3);
   }

   public static final Flow retryWhen(Flow var0, Function4 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Function4 $predicate$inlined;
         final Flow $this_retryWhen$inlined;

         public {
            this.$this_retryWhen$inlined = var1;
            this.$predicate$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var20;
            label41: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var8 = (<undefinedtype>)var2;
                  if ((var8.label & Integer.MIN_VALUE) != 0) {
                     var8.label += Integer.MIN_VALUE;
                     var20 = var8;
                     break label41;
                  }
               }

               var20 = new ContinuationImpl(this, var2) {
                  int I$0;
                  long J$0;
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

            Object var12 = ((<undefinedtype>)var20).result;
            Object var22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var20).label;
            long var4;
            long var6;
            Continuation var9;
            FlowCollector var10;
            <undefinedtype> var11;
            Object var19;
            boolean var21;
            FlowCollector var23;
            Continuation var24;
            FlowCollector var27;
            Flow var28;
            Object var29;
            FlowCollector var30;
            FlowCollector var31;
            if (var3 != 0) {
               if (var3 != 1) {
                  if (var3 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  Throwable var13 = (Throwable)((<undefinedtype>)var20).L$4;
                  var4 = ((<undefinedtype>)var20).J$0;
                  FlowCollector var14 = (FlowCollector)((<undefinedtype>)var20).L$3;
                  var9 = (Continuation)((<undefinedtype>)var20).L$2;
                  var10 = (FlowCollector)((<undefinedtype>)var20).L$1;
                  var11 = (<undefinedtype>)((<undefinedtype>)var20).L$0;
                  ResultKt.throwOnFailure(var12);
                  if (!(Boolean)var12) {
                     throw var13;
                  }

                  var6 = var4 + 1L;
                  var21 = true;
                  var10 = var10;
                  var20 = var20;
                  if (!var21) {
                     return Unit.INSTANCE;
                  }

                  var19 = var22;
                  var4 = var6;
                  var28 = var11.$this_retryWhen$inlined;
                  ((<undefinedtype>)var20).L$0 = var11;
                  ((<undefinedtype>)var20).L$1 = var10;
                  ((<undefinedtype>)var20).L$2 = var9;
                  ((<undefinedtype>)var20).L$3 = var14;
                  ((<undefinedtype>)var20).J$0 = var6;
                  ((<undefinedtype>)var20).I$0 = 0;
                  ((<undefinedtype>)var20).label = 1;
                  var29 = FlowKt.catchImpl(var28, var14, (Continuation)var20);
                  if (var29 == var22) {
                     return var22;
                  }

                  var3 = 0;
                  var11 = var11;
                  var9 = var9;
                  var23 = var14;
               } else {
                  var3 = ((<undefinedtype>)var20).I$0;
                  var4 = ((<undefinedtype>)var20).J$0;
                  var31 = (FlowCollector)((<undefinedtype>)var20).L$3;
                  var9 = (Continuation)((<undefinedtype>)var20).L$2;
                  var10 = (FlowCollector)((<undefinedtype>)var20).L$1;
                  var11 = (<undefinedtype>)((<undefinedtype>)var20).L$0;
                  ResultKt.throwOnFailure(var12);
                  var19 = var22;
                  var23 = var31;
                  var29 = var12;
               }
            } else {
               ResultKt.throwOnFailure(var12);
               Continuation var26 = (Continuation)var20;
               var4 = 0L;
               var12 = var22;
               var24 = var26;
               var10 = var1;
               var27 = var1;
               var19 = var12;
               var28 = this.$this_retryWhen$inlined;
               ((<undefinedtype>)var20).L$0 = this;
               ((<undefinedtype>)var20).L$1 = var10;
               ((<undefinedtype>)var20).L$2 = var24;
               ((<undefinedtype>)var20).L$3 = var27;
               ((<undefinedtype>)var20).J$0 = var4;
               ((<undefinedtype>)var20).I$0 = 0;
               ((<undefinedtype>)var20).label = 1;
               var29 = FlowKt.catchImpl(var28, var27, (Continuation)var20);
               if (var29 == var19) {
                  return var19;
               }

               var30 = var27;
               var3 = 0;
               var11 = this;
               var9 = var24;
               var23 = var30;
            }

            while(true) {
               Throwable var18 = (Throwable)var29;
               var31 = var23;
               <undefinedtype> var15 = var11;
               <undefinedtype> var25;
               if (var18 != null) {
                  Function4 var32 = var11.$predicate$inlined;
                  Long var33 = Boxing.boxLong(var4);
                  ((<undefinedtype>)var20).L$0 = var11;
                  ((<undefinedtype>)var20).L$1 = var10;
                  ((<undefinedtype>)var20).L$2 = var9;
                  ((<undefinedtype>)var20).L$3 = var23;
                  ((<undefinedtype>)var20).J$0 = var4;
                  ((<undefinedtype>)var20).L$4 = var18;
                  ((<undefinedtype>)var20).label = 2;
                  InlineMarker.mark(6);
                  var12 = var32.invoke(var23, var18, var33, var20);
                  InlineMarker.mark(7);
                  if (var12 == var19) {
                     return var19;
                  }

                  if (!(Boolean)var12) {
                     throw var18;
                  }

                  var6 = var4 + 1L;
                  var21 = true;
                  var31 = var23;
                  var15 = var11;
                  var10 = var10;
                  var20 = var20;
                  var24 = var9;
                  if (!var21) {
                     return Unit.INSTANCE;
                  }

                  var27 = var31;
                  var19 = var19;
                  var4 = var6;
                  var25 = var15;
               } else {
                  var10 = var10;
                  var20 = var20;
                  var24 = var9;
                  if (var3 == 0) {
                     return Unit.INSTANCE;
                  }

                  var27 = var31;
                  var19 = var19;
                  var4 = var4;
                  var25 = var15;
               }

               var28 = var25.$this_retryWhen$inlined;
               ((<undefinedtype>)var20).L$0 = var25;
               ((<undefinedtype>)var20).L$1 = var10;
               ((<undefinedtype>)var20).L$2 = var24;
               ((<undefinedtype>)var20).L$3 = var27;
               ((<undefinedtype>)var20).J$0 = var4;
               ((<undefinedtype>)var20).I$0 = 0;
               ((<undefinedtype>)var20).label = 1;
               var29 = FlowKt.catchImpl(var28, var27, (Continuation)var20);
               if (var29 == var19) {
                  return var19;
               }

               var30 = var27;
               var3 = 0;
               var11 = var25;
               var9 = var24;
               var23 = var30;
            }
         }
      });
   }
}
