package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.NopCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001aV\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u000223\b\u0004\u0010\u0005\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\f\u001ak\u0010\r\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00022H\b\u0004\u0010\u0005\u001aB\b\u0001\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u000eH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001aT\u0010\u0012\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u000221\u0010\u0005\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a/\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0002H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u001e\u0010\u0017\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00022\u0006\u0010\u0019\u001a\u00020\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"},
   d2 = {"collect", "", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "T", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectIndexed", "Lkotlin/Function3;", "", "index", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectLatest", "emitAll", "Lkotlinx/coroutines/flow/FlowCollector;", "flow", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchIn", "Lkotlinx/coroutines/Job;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__CollectKt {
   public static final Object collect(Flow var0, Continuation var1) {
      Object var2 = var0.collect((FlowCollector)NopCollector.INSTANCE, var1);
      return var2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var2 : Unit.INSTANCE;
   }

   public static final Object collect(Flow var0, Function2 var1, Continuation var2) {
      Object var3 = var0.collect((FlowCollector)(new FlowCollector(var1) {
         final Function2 $action;

         public {
            this.$action = var1;
         }

         public Object emit(Object var1, Continuation var2) {
            var1 = this.$action.invoke(var1, var2);
            return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
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
            return this.$action.invoke(var1, var2);
         }
      }), var2);
      return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
   }

   private static final Object collect$$forInline(Flow var0, Function2 var1, Continuation var2) {
      FlowCollector var4 = (FlowCollector)(new FlowCollector(var1) {
         final Function2 $action;

         public {
            this.$action = var1;
         }

         public Object emit(Object var1, Continuation var2) {
            var1 = this.$action.invoke(var1, var2);
            return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
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
            return this.$action.invoke(var1, var2);
         }
      });
      InlineMarker.mark(0);
      Object var3 = var0.collect(var4, var2);
      InlineMarker.mark(2);
      InlineMarker.mark(1);
      return var3;
   }

   public static final Object collectIndexed(Flow var0, Function3 var1, Continuation var2) {
      Object var3 = var0.collect((FlowCollector)(new FlowCollector(var1) {
         final Function3 $action;
         private int index;

         public {
            this.$action = var1;
         }

         public Object emit(Object var1, Continuation var2) {
            Function3 var4 = this.$action;
            int var3 = this.index++;
            if (var3 >= 0) {
               var1 = var4.invoke(Boxing.boxInt(var3), var1, var2);
               return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
            } else {
               throw (Throwable)(new ArithmeticException("Index overflow has happened"));
            }
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
            Function3 var4 = this.$action;
            int var3 = this.index++;
            if (var3 >= 0) {
               return var4.invoke(var3, var1, var2);
            } else {
               throw (Throwable)(new ArithmeticException("Index overflow has happened"));
            }
         }
      }), var2);
      return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
   }

   private static final Object collectIndexed$$forInline(Flow var0, Function3 var1, Continuation var2) {
      FlowCollector var4 = (FlowCollector)(new FlowCollector(var1) {
         final Function3 $action;
         private int index;

         public {
            this.$action = var1;
         }

         public Object emit(Object var1, Continuation var2) {
            Function3 var4 = this.$action;
            int var3 = this.index++;
            if (var3 >= 0) {
               var1 = var4.invoke(Boxing.boxInt(var3), var1, var2);
               return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
            } else {
               throw (Throwable)(new ArithmeticException("Index overflow has happened"));
            }
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
            Function3 var4 = this.$action;
            int var3 = this.index++;
            if (var3 >= 0) {
               return var4.invoke(var3, var1, var2);
            } else {
               throw (Throwable)(new ArithmeticException("Index overflow has happened"));
            }
         }
      });
      InlineMarker.mark(0);
      Object var3 = var0.collect(var4, var2);
      InlineMarker.mark(2);
      InlineMarker.mark(1);
      return var3;
   }

   public static final Object collectLatest(Flow var0, Function2 var1, Continuation var2) {
      Object var3 = FlowKt.collect(FlowKt.buffer$default(FlowKt.mapLatest(var0, var1), 0, (BufferOverflow)null, 2, (Object)null), var2);
      return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
   }

   public static final Object emitAll(FlowCollector var0, Flow var1, Continuation var2) {
      Object var3 = var1.collect(var0, var2);
      return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
   }

   private static final Object emitAll$$forInline(FlowCollector var0, Flow var1, Continuation var2) {
      InlineMarker.mark(0);
      Object var3 = var1.collect(var0, var2);
      InlineMarker.mark(2);
      InlineMarker.mark(1);
      return var3;
   }

   public static final Job launchIn(Flow var0, CoroutineScope var1) {
      return BuildersKt.launch$default(var1, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(var0, (Continuation)null) {
         final Flow $this_launchIn;
         Object L$0;
         int label;
         private CoroutineScope p$;

         {
            this.$this_launchIn = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$this_launchIn, var2);
            var3.p$ = (CoroutineScope)var1;
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

               CoroutineScope var6 = (CoroutineScope)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               CoroutineScope var4 = this.p$;
               Flow var5 = this.$this_launchIn;
               this.L$0 = var4;
               this.label = 1;
               if (FlowKt.collect(var5, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }), 3, (Object)null);
   }
}
