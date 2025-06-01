package kotlinx.coroutines.flow;

import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\u001c\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aM\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aM\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u0012\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001aK\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0010\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0011\u001a+\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0013\"\u0002H\u0002¢\u0006\u0002\u0010\u0014\u001aT\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u001724\b\u0001\u0010\u0003\u001a.\u0012\u0004\u0012\u00020\u0018\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00020\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0002\b\tH\u0007\u001a\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001eH\u0007\u001a!\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0013¢\u0006\u0002\u0010\u0014\u001a\u0010\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u0001*\u00020\u001f\u001a\u0010\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020 0\u0001*\u00020!\u001a\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\"\u001a\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020#\u001a\u0010\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u0001*\u00020$\u001a\u0010\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020 0\u0001*\u00020%\u001a\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020&\u001a6\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"},
   d2 = {"callbackFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "channelFlow", "emptyFlow", "flow", "Lkotlinx/coroutines/flow/FlowCollector;", "flowOf", "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/flow/Flow;", "elements", "", "([Ljava/lang/Object;)Lkotlinx/coroutines/flow/Flow;", "flowViaChannel", "bufferSize", "", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/ParameterName;", "name", "channel", "asFlow", "Lkotlin/Function0;", "", "", "", "", "", "Lkotlin/ranges/IntRange;", "Lkotlin/ranges/LongRange;", "Lkotlin/sequences/Sequence;", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__BuildersKt {
   public static final Flow asFlow(Iterable var0) {
      return (Flow)(new Flow(var0) {
         final Iterable $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var13;
            label28: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var13 = var4;
                     break label28;
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
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
            }

            Object var9 = ((<undefinedtype>)var13).result;
            Object var11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var13).label;
            FlowCollector var5;
            Iterator var6;
            <undefinedtype> var7;
            Continuation var8;
            Iterable var14;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Object var12 = ((<undefinedtype>)var13).L$7;
               var12 = ((<undefinedtype>)var13).L$6;
               var6 = (Iterator)((<undefinedtype>)var13).L$5;
               var14 = (Iterable)((<undefinedtype>)var13).L$4;
               var1 = (FlowCollector)((<undefinedtype>)var13).L$3;
               var8 = (Continuation)((<undefinedtype>)var13).L$2;
               var5 = (FlowCollector)((<undefinedtype>)var13).L$1;
               var7 = (<undefinedtype>)((<undefinedtype>)var13).L$0;
               ResultKt.throwOnFailure(var9);
            } else {
               ResultKt.throwOnFailure(var9);
               Continuation var15 = (Continuation)var13;
               Iterable var10 = this.$this_asFlow$inlined;
               var6 = var10.iterator();
               var7 = this;
               var8 = var15;
               var14 = var10;
               var5 = var1;
            }

            do {
               if (!var6.hasNext()) {
                  return Unit.INSTANCE;
               }

               var9 = var6.next();
               ((<undefinedtype>)var13).L$0 = var7;
               ((<undefinedtype>)var13).L$1 = var5;
               ((<undefinedtype>)var13).L$2 = var8;
               ((<undefinedtype>)var13).L$3 = var1;
               ((<undefinedtype>)var13).L$4 = var14;
               ((<undefinedtype>)var13).L$5 = var6;
               ((<undefinedtype>)var13).L$6 = var9;
               ((<undefinedtype>)var13).L$7 = var9;
               ((<undefinedtype>)var13).label = 1;
            } while(var1.emit(var9, (Continuation)var13) != var11);

            return var11;
         }
      });
   }

   public static final Flow asFlow(Iterator var0) {
      return (Flow)(new Flow(var0) {
         final Iterator $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var4;
            label28: {
               if (var2 instanceof <undefinedtype>) {
                  var4 = (<undefinedtype>)var2;
                  if ((((<undefinedtype>)var4).label & Integer.MIN_VALUE) != 0) {
                     ((<undefinedtype>)var4).label += Integer.MIN_VALUE;
                     break label28;
                  }
               }

               var4 = new ContinuationImpl(this, var2) {
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
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
            }

            Object var10 = ((<undefinedtype>)var4).result;
            Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var4).label;
            Continuation var5;
            <undefinedtype> var6;
            FlowCollector var7;
            Iterator var8;
            Iterator var12;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Object var11 = ((<undefinedtype>)var4).L$7;
               var11 = ((<undefinedtype>)var4).L$6;
               var8 = (Iterator)((<undefinedtype>)var4).L$5;
               var12 = (Iterator)((<undefinedtype>)var4).L$4;
               var1 = (FlowCollector)((<undefinedtype>)var4).L$3;
               var5 = (Continuation)((<undefinedtype>)var4).L$2;
               var7 = (FlowCollector)((<undefinedtype>)var4).L$1;
               var6 = (<undefinedtype>)((<undefinedtype>)var4).L$0;
               ResultKt.throwOnFailure(var10);
            } else {
               ResultKt.throwOnFailure(var10);
               var5 = (Continuation)var4;
               var12 = this.$this_asFlow$inlined;
               var6 = this;
               var8 = var12;
               var7 = var1;
            }

            do {
               if (!var8.hasNext()) {
                  return Unit.INSTANCE;
               }

               var10 = var8.next();
               ((<undefinedtype>)var4).L$0 = var6;
               ((<undefinedtype>)var4).L$1 = var7;
               ((<undefinedtype>)var4).L$2 = var5;
               ((<undefinedtype>)var4).L$3 = var1;
               ((<undefinedtype>)var4).L$4 = var12;
               ((<undefinedtype>)var4).L$5 = var8;
               ((<undefinedtype>)var4).L$6 = var10;
               ((<undefinedtype>)var4).L$7 = var10;
               ((<undefinedtype>)var4).label = 1;
            } while(var1.emit(var10, (Continuation)var4) != var9);

            return var9;
         }
      });
   }

   public static final Flow asFlow(Function0 var0) {
      return (Flow)(new Flow(var0) {
         final Function0 $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = var1.emit(this.$this_asFlow$inlined.invoke(), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }

   public static final Flow asFlow(Function1 var0) {
      return (Flow)(new Flow(var0) {
         final Function1 $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var4;
            label34: {
               if (var2 instanceof <undefinedtype>) {
                  var4 = (<undefinedtype>)var2;
                  if ((((<undefinedtype>)var4).label & Integer.MIN_VALUE) != 0) {
                     ((<undefinedtype>)var4).label += Integer.MIN_VALUE;
                     break label34;
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

            Object var8 = ((<undefinedtype>)var4).result;
            Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var4).label;
            FlowCollector var5;
            FlowCollector var6;
            <undefinedtype> var9;
            Continuation var11;
            FlowCollector var12;
            if (var3 != 0) {
               if (var3 != 1) {
                  if (var3 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var1 = (FlowCollector)((<undefinedtype>)var4).L$3;
                  var11 = (Continuation)((<undefinedtype>)var4).L$2;
                  var1 = (FlowCollector)((<undefinedtype>)var4).L$1;
                  <undefinedtype> var14 = (<undefinedtype>)((<undefinedtype>)var4).L$0;
                  ResultKt.throwOnFailure(var8);
                  return Unit.INSTANCE;
               }

               var5 = (FlowCollector)((<undefinedtype>)var4).L$4;
               var6 = (FlowCollector)((<undefinedtype>)var4).L$3;
               var11 = (Continuation)((<undefinedtype>)var4).L$2;
               var12 = (FlowCollector)((<undefinedtype>)var4).L$1;
               var9 = (<undefinedtype>)((<undefinedtype>)var4).L$0;
               ResultKt.throwOnFailure(var8);
            } else {
               ResultKt.throwOnFailure(var8);
               Continuation var7 = (Continuation)var4;
               Function1 var13 = this.$this_asFlow$inlined;
               ((<undefinedtype>)var4).L$0 = this;
               ((<undefinedtype>)var4).L$1 = var1;
               ((<undefinedtype>)var4).L$2 = var7;
               ((<undefinedtype>)var4).L$3 = var1;
               ((<undefinedtype>)var4).L$4 = var1;
               ((<undefinedtype>)var4).label = 1;
               var8 = var13.invoke(var4);
               if (var8 == var10) {
                  return var10;
               }

               var9 = this;
               var12 = var1;
               var6 = var1;
               var5 = var1;
               var11 = var7;
            }

            ((<undefinedtype>)var4).L$0 = var9;
            ((<undefinedtype>)var4).L$1 = var12;
            ((<undefinedtype>)var4).L$2 = var11;
            ((<undefinedtype>)var4).L$3 = var6;
            ((<undefinedtype>)var4).label = 2;
            if (var5.emit(var8, (Continuation)var4) == var10) {
               return var10;
            } else {
               return Unit.INSTANCE;
            }
         }
      });
   }

   public static final Flow asFlow(IntRange var0) {
      return (Flow)(new Flow(var0) {
         final IntRange $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var14;
            label28: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var14 = var4;
                     break label28;
                  }
               }

               var14 = new ContinuationImpl(this, var2) {
                  int I$0;
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
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
            }

            Object var9 = ((<undefinedtype>)var14).result;
            Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var14).label;
            Iterable var5;
            FlowCollector var6;
            <undefinedtype> var7;
            Iterator var8;
            Continuation var13;
            FlowCollector var15;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var3 = ((<undefinedtype>)var14).I$0;
               Object var12 = ((<undefinedtype>)var14).L$6;
               var8 = (Iterator)((<undefinedtype>)var14).L$5;
               var5 = (Iterable)((<undefinedtype>)var14).L$4;
               var6 = (FlowCollector)((<undefinedtype>)var14).L$3;
               var13 = (Continuation)((<undefinedtype>)var14).L$2;
               var15 = (FlowCollector)((<undefinedtype>)var14).L$1;
               var7 = (<undefinedtype>)((<undefinedtype>)var14).L$0;
               ResultKt.throwOnFailure(var9);
            } else {
               ResultKt.throwOnFailure(var9);
               Continuation var16 = (Continuation)var14;
               var5 = (Iterable)this.$this_asFlow$inlined;
               var8 = var5.iterator();
               var7 = this;
               FlowCollector var17 = var1;
               var6 = var1;
               var13 = var16;
               var15 = var17;
            }

            Integer var18;
            do {
               if (!var8.hasNext()) {
                  return Unit.INSTANCE;
               }

               Object var11 = var8.next();
               var3 = ((Number)var11).intValue();
               var18 = Boxing.boxInt(var3);
               ((<undefinedtype>)var14).L$0 = var7;
               ((<undefinedtype>)var14).L$1 = var15;
               ((<undefinedtype>)var14).L$2 = var13;
               ((<undefinedtype>)var14).L$3 = var6;
               ((<undefinedtype>)var14).L$4 = var5;
               ((<undefinedtype>)var14).L$5 = var8;
               ((<undefinedtype>)var14).L$6 = var11;
               ((<undefinedtype>)var14).I$0 = var3;
               ((<undefinedtype>)var14).label = 1;
            } while(var6.emit(var18, (Continuation)var14) != var10);

            return var10;
         }
      });
   }

   public static final Flow asFlow(LongRange var0) {
      return (Flow)(new Flow(var0) {
         final LongRange $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var15;
            label28: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var6 = (<undefinedtype>)var2;
                  if ((var6.label & Integer.MIN_VALUE) != 0) {
                     var6.label += Integer.MIN_VALUE;
                     var15 = var6;
                     break label28;
                  }
               }

               var15 = new ContinuationImpl(this, var2) {
                  long J$0;
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
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
            }

            Object var11 = ((<undefinedtype>)var15).result;
            Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var15).label;
            long var4;
            Iterable var7;
            Continuation var8;
            Iterator var9;
            FlowCollector var10;
            <undefinedtype> var16;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var4 = ((<undefinedtype>)var15).J$0;
               Object var14 = ((<undefinedtype>)var15).L$6;
               var9 = (Iterator)((<undefinedtype>)var15).L$5;
               var7 = (Iterable)((<undefinedtype>)var15).L$4;
               var10 = (FlowCollector)((<undefinedtype>)var15).L$3;
               var8 = (Continuation)((<undefinedtype>)var15).L$2;
               var1 = (FlowCollector)((<undefinedtype>)var15).L$1;
               var16 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
               ResultKt.throwOnFailure(var11);
            } else {
               ResultKt.throwOnFailure(var11);
               var8 = (Continuation)var15;
               var7 = (Iterable)this.$this_asFlow$inlined;
               var9 = var7.iterator();
               var16 = this;
               var10 = var1;
               var1 = var1;
            }

            Long var13;
            do {
               if (!var9.hasNext()) {
                  return Unit.INSTANCE;
               }

               var11 = var9.next();
               var4 = ((Number)var11).longValue();
               var13 = Boxing.boxLong(var4);
               ((<undefinedtype>)var15).L$0 = var16;
               ((<undefinedtype>)var15).L$1 = var1;
               ((<undefinedtype>)var15).L$2 = var8;
               ((<undefinedtype>)var15).L$3 = var10;
               ((<undefinedtype>)var15).L$4 = var7;
               ((<undefinedtype>)var15).L$5 = var9;
               ((<undefinedtype>)var15).L$6 = var11;
               ((<undefinedtype>)var15).J$0 = var4;
               ((<undefinedtype>)var15).label = 1;
            } while(var10.emit(var13, (Continuation)var15) != var12);

            return var12;
         }
      });
   }

   public static final Flow asFlow(Sequence var0) {
      return (Flow)(new Flow(var0) {
         final Sequence $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var12;
            label28: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var4 = (<undefinedtype>)var2;
                  if ((var4.label & Integer.MIN_VALUE) != 0) {
                     var4.label += Integer.MIN_VALUE;
                     var12 = var4;
                     break label28;
                  }
               }

               var12 = new ContinuationImpl(this, var2) {
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
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
            }

            Object var9 = ((<undefinedtype>)var12).result;
            Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var12).label;
            FlowCollector var5;
            <undefinedtype> var6;
            Sequence var7;
            Iterator var8;
            Continuation var13;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Object var11 = ((<undefinedtype>)var12).L$7;
               var11 = ((<undefinedtype>)var12).L$6;
               var8 = (Iterator)((<undefinedtype>)var12).L$5;
               var7 = (Sequence)((<undefinedtype>)var12).L$4;
               var1 = (FlowCollector)((<undefinedtype>)var12).L$3;
               var13 = (Continuation)((<undefinedtype>)var12).L$2;
               var5 = (FlowCollector)((<undefinedtype>)var12).L$1;
               var6 = (<undefinedtype>)((<undefinedtype>)var12).L$0;
               ResultKt.throwOnFailure(var9);
            } else {
               ResultKt.throwOnFailure(var9);
               Continuation var14 = (Continuation)var12;
               var7 = this.$this_asFlow$inlined;
               var8 = var7.iterator();
               var6 = this;
               var13 = var14;
               var5 = var1;
            }

            do {
               if (!var8.hasNext()) {
                  return Unit.INSTANCE;
               }

               var9 = var8.next();
               ((<undefinedtype>)var12).L$0 = var6;
               ((<undefinedtype>)var12).L$1 = var5;
               ((<undefinedtype>)var12).L$2 = var13;
               ((<undefinedtype>)var12).L$3 = var1;
               ((<undefinedtype>)var12).L$4 = var7;
               ((<undefinedtype>)var12).L$5 = var8;
               ((<undefinedtype>)var12).L$6 = var9;
               ((<undefinedtype>)var12).L$7 = var9;
               ((<undefinedtype>)var12).label = 1;
            } while(var1.emit(var9, (Continuation)var12) != var10);

            return var10;
         }
      });
   }

   public static final Flow asFlow(int[] var0) {
      return (Flow)(new Flow(var0) {
         final int[] $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var17;
            label26: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var7 = (<undefinedtype>)var2;
                  if ((var7.label & Integer.MIN_VALUE) != 0) {
                     var7.label += Integer.MIN_VALUE;
                     var17 = var7;
                     break label26;
                  }
               }

               var17 = new ContinuationImpl(this, var2) {
                  int I$0;
                  int I$1;
                  int I$2;
                  int I$3;
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

            Object var13 = ((<undefinedtype>)var17).result;
            Object var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var17).label;
            int var4;
            <undefinedtype> var9;
            Continuation var10;
            int[] var11;
            int[] var12;
            Object var14;
            FlowCollector var18;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var3 = ((<undefinedtype>)var17).I$3;
               var3 = ((<undefinedtype>)var17).I$2;
               var3 = ((<undefinedtype>)var17).I$1;
               var4 = ((<undefinedtype>)var17).I$0;
               var11 = (int[])((<undefinedtype>)var17).L$5;
               var12 = (int[])((<undefinedtype>)var17).L$4;
               var1 = (FlowCollector)((<undefinedtype>)var17).L$3;
               var10 = (Continuation)((<undefinedtype>)var17).L$2;
               var18 = (FlowCollector)((<undefinedtype>)var17).L$1;
               var9 = (<undefinedtype>)((<undefinedtype>)var17).L$0;
               ResultKt.throwOnFailure(var13);
               ++var3;
               var14 = var17;
               var13 = var8;
               var1 = var1;
            } else {
               ResultKt.throwOnFailure(var13);
               var10 = (Continuation)var17;
               int[] var19 = this.$this_asFlow$inlined;
               var4 = var19.length;
               var9 = this;
               var14 = var17;
               var3 = 0;
               var11 = var19;
               var12 = var19;
               var13 = var8;
               var18 = var1;
            }

            while(var3 < var4) {
               int var5 = var11[var3];
               int var6 = ((Number)Boxing.boxInt(var5)).intValue();
               Integer var16 = Boxing.boxInt(var6);
               ((<undefinedtype>)var14).L$0 = var9;
               ((<undefinedtype>)var14).L$1 = var18;
               ((<undefinedtype>)var14).L$2 = var10;
               ((<undefinedtype>)var14).L$3 = var1;
               ((<undefinedtype>)var14).L$4 = var12;
               ((<undefinedtype>)var14).L$5 = var11;
               ((<undefinedtype>)var14).I$0 = var4;
               ((<undefinedtype>)var14).I$1 = var3;
               ((<undefinedtype>)var14).I$2 = var5;
               ((<undefinedtype>)var14).I$3 = var6;
               ((<undefinedtype>)var14).label = 1;
               if (var1.emit(var16, (Continuation)var14) == var13) {
                  return var13;
               }

               ++var3;
               var14 = var14;
               var13 = var13;
               var1 = var1;
            }

            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow asFlow(long[] var0) {
      return (Flow)(new Flow(var0) {
         final long[] $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var20;
            label28: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var10 = (<undefinedtype>)var2;
                  if ((var10.label & Integer.MIN_VALUE) != 0) {
                     var10.label += Integer.MIN_VALUE;
                     var20 = var10;
                     break label28;
                  }
               }

               var20 = new ContinuationImpl(this, var2) {
                  int I$0;
                  int I$1;
                  long J$0;
                  long J$1;
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

            Object var17 = ((<undefinedtype>)var20).result;
            Object var11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var20).label;
            int var4;
            int var5;
            long var6;
            FlowCollector var12;
            long[] var14;
            Object var22;
            FlowCollector var24;
            <undefinedtype> var26;
            Continuation var28;
            long[] var29;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var6 = ((<undefinedtype>)var20).J$1;
               var6 = ((<undefinedtype>)var20).J$0;
               var4 = ((<undefinedtype>)var20).I$1;
               var3 = ((<undefinedtype>)var20).I$0;
               var14 = (long[])((<undefinedtype>)var20).L$5;
               long[] var16 = (long[])((<undefinedtype>)var20).L$4;
               var12 = (FlowCollector)((<undefinedtype>)var20).L$3;
               Continuation var13 = (Continuation)((<undefinedtype>)var20).L$2;
               FlowCollector var21 = (FlowCollector)((<undefinedtype>)var20).L$1;
               <undefinedtype> var15 = (<undefinedtype>)((<undefinedtype>)var20).L$0;
               ResultKt.throwOnFailure(var17);
               Object var18 = var11;
               var24 = var21;
               var22 = var20;
               var5 = var4 + 1;
               var20 = var18;
               var4 = var3;
               var3 = var5;
               var29 = var14;
               var14 = var16;
               var28 = var13;
               var26 = var15;
            } else {
               ResultKt.throwOnFailure(var17);
               var28 = (Continuation)var20;
               long[] var23 = this.$this_asFlow$inlined;
               var4 = var23.length;
               var3 = 0;
               var26 = this;
               var14 = var23;
               Object var27 = var11;
               Object var25 = var20;
               var24 = var1;
               var29 = var23;
               var20 = var27;
               var22 = var25;
               var12 = var1;
            }

            while(var3 < var4) {
               long var8 = var29[var3];
               var6 = ((Number)Boxing.boxLong(var8)).longValue();
               Long var19 = Boxing.boxLong(var6);
               ((<undefinedtype>)var22).L$0 = var26;
               ((<undefinedtype>)var22).L$1 = var24;
               ((<undefinedtype>)var22).L$2 = var28;
               ((<undefinedtype>)var22).L$3 = var12;
               ((<undefinedtype>)var22).L$4 = var14;
               ((<undefinedtype>)var22).L$5 = var29;
               var5 = var4;
               ((<undefinedtype>)var22).I$0 = var4;
               ((<undefinedtype>)var22).I$1 = var3;
               ((<undefinedtype>)var22).J$0 = var8;
               ((<undefinedtype>)var22).J$1 = var6;
               ((<undefinedtype>)var22).label = 1;
               if (var12.emit(var19, (Continuation)var22) == var20) {
                  return var20;
               }

               var4 = var3;
               var3 = var5;
               var5 = var4 + 1;
               var20 = var20;
               var4 = var3;
               var3 = var5;
               var29 = var29;
               var14 = var14;
               var28 = var28;
               var26 = var26;
            }

            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow asFlow(Object[] var0) {
      return (Flow)(new Flow(var0) {
         final Object[] $this_asFlow$inlined;

         public {
            this.$this_asFlow$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var16;
            label26: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var5 = (<undefinedtype>)var2;
                  if ((var5.label & Integer.MIN_VALUE) != 0) {
                     var5.label += Integer.MIN_VALUE;
                     var16 = var5;
                     break label26;
                  }
               }

               var16 = new ContinuationImpl(this, var2) {
                  int I$0;
                  int I$1;
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
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
            }

            Object var18 = ((<undefinedtype>)var16).result;
            Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var16).label;
            int var4;
            <undefinedtype> var7;
            Continuation var8;
            Object[] var9;
            Object[] var10;
            Object var12;
            FlowCollector var17;
            Object var20;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Object var15 = ((<undefinedtype>)var16).L$7;
               var15 = ((<undefinedtype>)var16).L$6;
               var3 = ((<undefinedtype>)var16).I$1;
               var4 = ((<undefinedtype>)var16).I$0;
               var9 = (Object[])((<undefinedtype>)var16).L$5;
               var10 = (Object[])((<undefinedtype>)var16).L$4;
               var1 = (FlowCollector)((<undefinedtype>)var16).L$3;
               var8 = (Continuation)((<undefinedtype>)var16).L$2;
               FlowCollector var11 = (FlowCollector)((<undefinedtype>)var16).L$1;
               var7 = (<undefinedtype>)((<undefinedtype>)var16).L$0;
               ResultKt.throwOnFailure(var18);
               var17 = var11;
               ++var3;
               var12 = var16;
               var20 = var6;
               var1 = var1;
            } else {
               ResultKt.throwOnFailure(var18);
               var8 = (Continuation)var16;
               Object[] var19 = this.$this_asFlow$inlined;
               var4 = var19.length;
               var7 = this;
               var12 = var16;
               var3 = 0;
               var17 = var1;
               var9 = var19;
               var10 = var19;
               var20 = var6;
            }

            while(var3 < var4) {
               Object var14 = var9[var3];
               ((<undefinedtype>)var12).L$0 = var7;
               ((<undefinedtype>)var12).L$1 = var17;
               ((<undefinedtype>)var12).L$2 = var8;
               ((<undefinedtype>)var12).L$3 = var1;
               ((<undefinedtype>)var12).L$4 = var10;
               ((<undefinedtype>)var12).L$5 = var9;
               ((<undefinedtype>)var12).I$0 = var4;
               ((<undefinedtype>)var12).I$1 = var3;
               ((<undefinedtype>)var12).L$6 = var14;
               ((<undefinedtype>)var12).L$7 = var14;
               ((<undefinedtype>)var12).label = 1;
               if (var1.emit(var14, (Continuation)var12) == var20) {
                  return var20;
               }

               ++var3;
               var12 = var12;
               var20 = var20;
               var1 = var1;
            }

            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow callbackFlow(Function2 var0) {
      return (Flow)(new CallbackFlowBuilder(var0, (CoroutineContext)null, 0, (BufferOverflow)null, 14, (DefaultConstructorMarker)null));
   }

   public static final Flow channelFlow(Function2 var0) {
      return (Flow)(new ChannelFlowBuilder(var0, (CoroutineContext)null, 0, (BufferOverflow)null, 14, (DefaultConstructorMarker)null));
   }

   public static final Flow emptyFlow() {
      return (Flow)EmptyFlow.INSTANCE;
   }

   public static final Flow flow(Function2 var0) {
      return (Flow)(new SafeFlow(var0));
   }

   public static final Flow flowOf(Object var0) {
      return (Flow)(new Flow(var0) {
         final Object $value$inlined;

         public {
            this.$value$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = var1.emit(this.$value$inlined, var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }

   public static final Flow flowOf(Object... var0) {
      return (Flow)(new Flow(var0) {
         final Object[] $elements$inlined;

         public {
            this.$elements$inlined = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var15;
            label26: {
               if (var2 instanceof <undefinedtype>) {
                  <undefinedtype> var5 = (<undefinedtype>)var2;
                  if ((var5.label & Integer.MIN_VALUE) != 0) {
                     var5.label += Integer.MIN_VALUE;
                     var15 = var5;
                     break label26;
                  }
               }

               var15 = new ContinuationImpl(this, var2) {
                  int I$0;
                  int I$1;
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

            Object var7 = ((<undefinedtype>)var15).result;
            Object var16 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var3 = ((<undefinedtype>)var15).label;
            int var4;
            <undefinedtype> var6;
            Continuation var8;
            Object[] var9;
            Object var11;
            FlowCollector var17;
            Object var18;
            if (var3 != 0) {
               if (var3 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Object var14 = ((<undefinedtype>)var15).L$5;
               var3 = ((<undefinedtype>)var15).I$1;
               var4 = ((<undefinedtype>)var15).I$0;
               var9 = (Object[])((<undefinedtype>)var15).L$4;
               var1 = (FlowCollector)((<undefinedtype>)var15).L$3;
               var8 = (Continuation)((<undefinedtype>)var15).L$2;
               FlowCollector var10 = (FlowCollector)((<undefinedtype>)var15).L$1;
               var6 = (<undefinedtype>)((<undefinedtype>)var15).L$0;
               ResultKt.throwOnFailure(var7);
               var17 = var10;
               ++var3;
               var11 = var15;
               var18 = var16;
               var1 = var1;
            } else {
               ResultKt.throwOnFailure(var7);
               var8 = (Continuation)var15;
               var9 = this.$elements$inlined;
               var4 = var9.length;
               var6 = this;
               var3 = 0;
               var18 = var16;
               var17 = var1;
               var11 = var15;
            }

            while(var3 < var4) {
               Object var13 = var9[var3];
               ((<undefinedtype>)var11).L$0 = var6;
               ((<undefinedtype>)var11).L$1 = var17;
               ((<undefinedtype>)var11).L$2 = var8;
               ((<undefinedtype>)var11).L$3 = var1;
               ((<undefinedtype>)var11).L$4 = var9;
               ((<undefinedtype>)var11).I$0 = var4;
               ((<undefinedtype>)var11).I$1 = var3;
               ((<undefinedtype>)var11).L$5 = var13;
               ((<undefinedtype>)var11).label = 1;
               if (var1.emit(var13, (Continuation)var11) == var18) {
                  return var18;
               }

               ++var3;
               var11 = var11;
               var18 = var18;
               var1 = var1;
            }

            return Unit.INSTANCE;
         }
      });
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use channelFlow with awaitClose { } instead of flowViaChannel and invokeOnClose { }."
   )
   public static final Flow flowViaChannel(int var0, Function2 var1) {
      return FlowKt.buffer$default(FlowKt.channelFlow((Function2)(new Function2(var1, (Continuation)null) {
         final Function2 $block;
         Object L$0;
         int label;
         private ProducerScope p$;

         {
            this.$block = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$block, var2);
            var3.p$ = (ProducerScope)var1;
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

               ProducerScope var5 = (ProducerScope)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               ProducerScope var4 = this.p$;
               this.$block.invoke(var4, var4.getChannel());
               this.L$0 = var4;
               this.label = 1;
               if (ProduceKt.awaitClose$default(var4, (Function0)null, this, 1, (Object)null) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      })), var0, (BufferOverflow)null, 2, (Object)null);
   }

   // $FF: synthetic method
   public static Flow flowViaChannel$default(int var0, Function2 var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var0 = -2;
      }

      return FlowKt.flowViaChannel(var0, var1);
   }
}
