package kotlinx.coroutines.flow.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BroadcastKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u001f\u0010\u001d\u001a\u00020\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010 J\u001f\u0010!\u001a\u00020\u000e2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\fH¤@ø\u0001\u0000¢\u0006\u0002\u0010\"J&\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH$J\u0010\u0010$\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010%H\u0016J&\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000%2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000(2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010)\u001a\u00020\u0016H\u0016R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R9\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000b8@X\u0080\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"},
   d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlow;", "T", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "collectToFun", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "getCollectToFun$kotlinx_coroutines_core", "()Lkotlin/jvm/functions/Function2;", "produceCapacity", "getProduceCapacity", "()I", "additionalToStringProps", "", "broadcastImpl", "Lkotlinx/coroutines/channels/BroadcastChannel;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "start", "Lkotlinx/coroutines/CoroutineStart;", "collect", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "dropChannelOperators", "Lkotlinx/coroutines/flow/Flow;", "fuse", "produceImpl", "Lkotlinx/coroutines/channels/ReceiveChannel;", "toString", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class ChannelFlow implements FusibleFlow {
   public final int capacity;
   public final CoroutineContext context;
   public final BufferOverflow onBufferOverflow;

   public ChannelFlow(CoroutineContext var1, int var2, BufferOverflow var3) {
      this.context = var1;
      this.capacity = var2;
      this.onBufferOverflow = var3;
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var4;
         if (var2 != -1) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (!var4) {
            throw (Throwable)(new AssertionError());
         }
      }

   }

   // $FF: synthetic method
   static Object collect$suspendImpl(ChannelFlow var0, FlowCollector var1, Continuation var2) {
      Object var3 = CoroutineScopeKt.coroutineScope((Function2)(new Function2(var0, var1, (Continuation)null) {
         final FlowCollector $collector;
         Object L$0;
         int label;
         private CoroutineScope p$;
         final ChannelFlow this$0;

         {
            this.this$0 = var1;
            this.$collector = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.this$0, this.$collector, var2);
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

               CoroutineScope var7 = (CoroutineScope)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               CoroutineScope var5 = this.p$;
               FlowCollector var6 = this.$collector;
               ReceiveChannel var4 = this.this$0.produceImpl(var5);
               this.L$0 = var5;
               this.label = 1;
               if (FlowKt.emitAll(var6, (ReceiveChannel)var4, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }), var2);
      return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
   }

   private final int getProduceCapacity() {
      int var2 = this.capacity;
      int var1 = var2;
      if (var2 == -3) {
         var1 = -2;
      }

      return var1;
   }

   protected String additionalToStringProps() {
      return null;
   }

   public BroadcastChannel broadcastImpl(CoroutineScope var1, CoroutineStart var2) {
      BufferOverflow var4 = this.onBufferOverflow;
      int var3 = ChannelFlow$WhenMappings.$EnumSwitchMapping$0[var4.ordinal()];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               throw new NoWhenBranchMatchedException();
            }

            throw (Throwable)(new IllegalArgumentException("Broadcast channel does not support BufferOverflow.DROP_LATEST"));
         }

         var3 = -1;
      } else {
         var3 = this.getProduceCapacity();
      }

      return BroadcastKt.broadcast$default(var1, this.context, var3, var2, (Function1)null, this.getCollectToFun$kotlinx_coroutines_core(), 8, (Object)null);
   }

   public Object collect(FlowCollector var1, Continuation var2) {
      return collect$suspendImpl(this, var1, var2);
   }

   protected abstract Object collectTo(ProducerScope var1, Continuation var2);

   protected abstract ChannelFlow create(CoroutineContext var1, int var2, BufferOverflow var3);

   public Flow dropChannelOperators() {
      return null;
   }

   public Flow fuse(CoroutineContext var1, int var2, BufferOverflow var3) {
      boolean var6 = DebugKt.getASSERTIONS_ENABLED();
      boolean var5 = true;
      boolean var4;
      if (var6) {
         if (var2 != -1) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (!var4) {
            throw (Throwable)(new AssertionError());
         }
      }

      var1 = var1.plus(this.context);
      if (var3 == BufferOverflow.SUSPEND) {
         int var7 = this.capacity;
         if (var7 != -3) {
            if (var2 == -3) {
               var2 = var7;
            } else if (var7 != -2) {
               if (var2 == -2) {
                  var2 = var7;
               } else {
                  if (DebugKt.getASSERTIONS_ENABLED()) {
                     if (this.capacity >= 0) {
                        var4 = true;
                     } else {
                        var4 = false;
                     }

                     if (!var4) {
                        throw (Throwable)(new AssertionError());
                     }
                  }

                  if (DebugKt.getASSERTIONS_ENABLED()) {
                     if (var2 >= 0) {
                        var4 = var5;
                     } else {
                        var4 = false;
                     }

                     if (!var4) {
                        throw (Throwable)(new AssertionError());
                     }
                  }

                  var2 += this.capacity;
                  if (var2 < 0) {
                     var2 = Integer.MAX_VALUE;
                  }
               }
            }
         }

         var3 = this.onBufferOverflow;
      }

      return Intrinsics.areEqual((Object)var1, (Object)this.context) && var2 == this.capacity && var3 == this.onBufferOverflow ? (Flow)this : (Flow)this.create(var1, var2, var3);
   }

   public final Function2 getCollectToFun$kotlinx_coroutines_core() {
      return (Function2)(new Function2(this, (Continuation)null) {
         Object L$0;
         int label;
         private ProducerScope p$0;
         final ChannelFlow this$0;

         {
            this.this$0 = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.this$0, var2);
            var3.p$0 = (ProducerScope)var1;
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

               ProducerScope var6 = (ProducerScope)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               ProducerScope var5 = this.p$0;
               ChannelFlow var4 = this.this$0;
               this.L$0 = var5;
               this.label = 1;
               if (var4.collectTo(var5, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      });
   }

   public ReceiveChannel produceImpl(CoroutineScope var1) {
      return ProduceKt.produce$default(var1, this.context, this.getProduceCapacity(), this.onBufferOverflow, CoroutineStart.ATOMIC, (Function1)null, this.getCollectToFun$kotlinx_coroutines_core(), 16, (Object)null);
   }

   public String toString() {
      ArrayList var1 = new ArrayList(4);
      String var2 = this.additionalToStringProps();
      if (var2 != null) {
         var1.add(var2);
      }

      if (this.context != EmptyCoroutineContext.INSTANCE) {
         var1.add("context=" + this.context);
      }

      if (this.capacity != -3) {
         var1.add("capacity=" + this.capacity);
      }

      if (this.onBufferOverflow != BufferOverflow.SUSPEND) {
         var1.add("onBufferOverflow=" + this.onBufferOverflow);
      }

      return DebugStringsKt.getClassSimpleName(this) + '[' + CollectionsKt.joinToString$default((Iterable)var1, (CharSequence)", ", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null) + ']';
   }
}
