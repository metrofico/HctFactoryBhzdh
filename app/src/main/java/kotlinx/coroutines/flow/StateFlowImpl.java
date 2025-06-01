package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u0010052\b\u0012\u0004\u0012\u00028\u0000062\b\u0012\u0004\u0012\u00028\u0000072\b\u0012\u0004\u0012\u00028\u000008B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\t\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0014¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ-\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\bH\u0016¢\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0004\b%\u0010&J!\u0010)\u001a\u00020\r2\b\u0010'\u001a\u0004\u0018\u00010\u00022\u0006\u0010(\u001a\u00020\u0002H\u0002¢\u0006\u0004\b)\u0010\u000fR\u001c\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000*8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0016\u0010.\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R*\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00028\u00008V@VX\u0096\u000e¢\u0006\u0012\u0012\u0004\b3\u0010$\u001a\u0004\b0\u00101\"\u0004\b2\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"},
   d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", "T", "", "initialState", "<init>", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expect", "update", "", "compareAndSet", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/flow/StateFlowSlot;", "createSlot", "()Lkotlinx/coroutines/flow/StateFlowSlot;", "", "size", "", "createSlotArray", "(I)[Lkotlinx/coroutines/flow/StateFlowSlot;", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "context", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlinx/coroutines/flow/Flow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)Lkotlinx/coroutines/flow/Flow;", "resetReplayCache", "()V", "tryEmit", "(Ljava/lang/Object;)Z", "expectedState", "newState", "updateState", "", "getReplayCache", "()Ljava/util/List;", "replayCache", "sequence", "I", "getValue", "()Ljava/lang/Object;", "setValue", "getValue$annotations", "kotlinx-coroutines-core", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;"},
   k = 1,
   mv = {1, 4, 0}
)
final class StateFlowImpl extends AbstractSharedFlow implements MutableStateFlow, CancellableFlow, FusibleFlow {
   private volatile Object _state;
   private int sequence;

   public StateFlowImpl(Object var1) {
      this._state = var1;
   }

   // $FF: synthetic method
   public static void getValue$annotations() {
   }

   private final boolean updateState(Object var1, Object var2) {
      StateFlowSlot[] var7 = (StateFlowSlot[])this.getSlots();
      synchronized(this){}

      Throwable var99;
      Throwable var10000;
      label1036: {
         Object var102;
         boolean var10001;
         try {
            var102 = this._state;
         } catch (Throwable var97) {
            var10000 = var97;
            var10001 = false;
            break label1036;
         }

         boolean var6;
         if (var1 != null) {
            try {
               var6 = Intrinsics.areEqual(var102, var1);
            } catch (Throwable var96) {
               var10000 = var96;
               var10001 = false;
               break label1036;
            }

            if (var6 ^ true) {
               return false;
            }
         }

         try {
            var6 = Intrinsics.areEqual(var102, var2);
         } catch (Throwable var95) {
            var10000 = var95;
            var10001 = false;
            break label1036;
         }

         if (var6) {
            return true;
         }

         int var3;
         try {
            this._state = var2;
            var3 = this.sequence;
         } catch (Throwable var94) {
            var10000 = var94;
            var10001 = false;
            break label1036;
         }

         if ((var3 & 1) == 0) {
            label1037: {
               ++var3;

               StateFlowSlot[] var98;
               Unit var100;
               try {
                  this.sequence = var3;
                  var98 = (StateFlowSlot[])this.getSlots();
                  var100 = Unit.INSTANCE;
               } catch (Throwable var92) {
                  var10000 = var92;
                  var10001 = false;
                  break label1037;
               }

               while(true) {
                  int var4;
                  if (var98 != null) {
                     int var5 = var98.length;

                     for(var4 = 0; var4 < var5; ++var4) {
                        StateFlowSlot var101 = var98[var4];
                        if (var101 != null) {
                           var101.makePending();
                        }
                     }
                  }

                  synchronized(this){}

                  label1001: {
                     try {
                        var4 = this.sequence;
                     } catch (Throwable var91) {
                        var10000 = var91;
                        var10001 = false;
                        break label1001;
                     }

                     if (var4 == var3) {
                        label994:
                        try {
                           this.sequence = var3 + 1;
                           return true;
                        } catch (Throwable var89) {
                           var10000 = var89;
                           var10001 = false;
                           break label994;
                        }
                     } else {
                        label997: {
                           try {
                              var98 = (StateFlowSlot[])this.getSlots();
                              var100 = Unit.INSTANCE;
                           } catch (Throwable var90) {
                              var10000 = var90;
                              var10001 = false;
                              break label997;
                           }

                           var3 = var4;
                           continue;
                        }
                     }
                  }

                  var99 = var10000;
                  throw var99;
               }
            }
         } else {
            label1015: {
               try {
                  this.sequence = var3 + 2;
               } catch (Throwable var93) {
                  var10000 = var93;
                  var10001 = false;
                  break label1015;
               }

               return true;
            }
         }
      }

      var99 = var10000;
      throw var99;
   }

   public Object collect(FlowCollector param1, Continuation param2) {
      // $FF: Couldn't be decompiled
   }

   public boolean compareAndSet(Object var1, Object var2) {
      if (var1 == null) {
         var1 = NullSurrogateKt.NULL;
      }

      if (var2 == null) {
         var2 = NullSurrogateKt.NULL;
      }

      return this.updateState(var1, var2);
   }

   protected StateFlowSlot createSlot() {
      return new StateFlowSlot();
   }

   protected StateFlowSlot[] createSlotArray(int var1) {
      return new StateFlowSlot[var1];
   }

   public Object emit(Object var1, Continuation var2) {
      this.setValue(var1);
      return Unit.INSTANCE;
   }

   public Flow fuse(CoroutineContext var1, int var2, BufferOverflow var3) {
      return StateFlowKt.fuseStateFlow(this, var1, var2, var3);
   }

   public List getReplayCache() {
      return CollectionsKt.listOf(this.getValue());
   }

   public Object getValue() {
      Symbol var3 = NullSurrogateKt.NULL;
      Object var2 = this._state;
      Object var1 = var2;
      if (var2 == var3) {
         var1 = null;
      }

      return var1;
   }

   public void resetReplayCache() {
      throw (Throwable)(new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported"));
   }

   public void setValue(Object var1) {
      if (var1 == null) {
         var1 = NullSurrogateKt.NULL;
      }

      this.updateState((Object)null, var1);
   }

   public boolean tryEmit(Object var1) {
      this.setValue(var1);
      return true;
   }
}
