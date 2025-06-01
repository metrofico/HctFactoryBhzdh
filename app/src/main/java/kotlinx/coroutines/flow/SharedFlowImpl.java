package kotlinx.coroutines.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\b\u0012\u0004\u0012\u0002H\u00010\u0006:\u0001bB\u001d\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0019\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010)J\u0010\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020'H\u0002J\u001f\u0010.\u001a\u00020'2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u000000H\u0096@ø\u0001\u0000¢\u0006\u0002\u00101J\u0010\u00102\u001a\u00020'2\u0006\u00103\u001a\u00020\u0012H\u0002J\b\u00104\u001a\u00020\u0003H\u0014J\u001d\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000e2\u0006\u00106\u001a\u00020\bH\u0014¢\u0006\u0002\u00107J\b\u00108\u001a\u00020'H\u0002J\u0019\u00109\u001a\u00020'2\u0006\u0010:\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010;J\u0019\u0010<\u001a\u00020'2\u0006\u0010:\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010;J\u0012\u0010=\u001a\u00020'2\b\u0010>\u001a\u0004\u0018\u00010\u000fH\u0002J1\u0010?\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020'\u0018\u00010@0\u000e2\u0014\u0010A\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020'\u0018\u00010@0\u000eH\u0002¢\u0006\u0002\u0010BJ&\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010H\u001a\u0004\u0018\u00010\u000f2\u0006\u0010I\u001a\u00020\u0012H\u0002J7\u0010J\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\u0010\u0010K\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000e2\u0006\u0010L\u001a\u00020\b2\u0006\u0010M\u001a\u00020\bH\u0002¢\u0006\u0002\u0010NJ\b\u0010O\u001a\u00020'H\u0016J\u0015\u0010P\u001a\u00020Q2\u0006\u0010:\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010RJ\u0015\u0010S\u001a\u00020Q2\u0006\u0010:\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010RJ\u0015\u0010T\u001a\u00020Q2\u0006\u0010:\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010RJ\u0010\u0010U\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u0003H\u0002J\u0012\u0010V\u001a\u0004\u0018\u00010\u000f2\u0006\u0010(\u001a\u00020\u0003H\u0002J(\u0010W\u001a\u00020'2\u0006\u0010X\u001a\u00020\u00122\u0006\u0010Y\u001a\u00020\u00122\u0006\u0010Z\u001a\u00020\u00122\u0006\u0010[\u001a\u00020\u0012H\u0002J%\u0010\\\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020'\u0018\u00010@0\u000e2\u0006\u0010]\u001a\u00020\u0012H\u0000¢\u0006\u0004\b^\u0010_J\r\u0010`\u001a\u00020\u0012H\u0000¢\u0006\u0002\baR\u001a\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014R\u000e\u0010\u0018\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0014R\u000e\u0010\u001b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006c"},
   d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl;", "T", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/SharedFlowSlot;", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "replay", "", "bufferCapacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(IILkotlinx/coroutines/channels/BufferOverflow;)V", "buffer", "", "", "[Ljava/lang/Object;", "bufferEndIndex", "", "getBufferEndIndex", "()J", "bufferSize", "head", "getHead", "minCollectorIndex", "queueEndIndex", "getQueueEndIndex", "queueSize", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "replayIndex", "replaySize", "getReplaySize", "()I", "totalSize", "getTotalSize", "awaitValue", "", "slot", "(Lkotlinx/coroutines/flow/SharedFlowSlot;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelEmitter", "emitter", "Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "cleanupTailLocked", "collect", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "correctCollectorIndexesOnDropOldest", "newHead", "createSlot", "createSlotArray", "size", "(I)[Lkotlinx/coroutines/flow/SharedFlowSlot;", "dropOldestLocked", "emit", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitSuspend", "enqueueLocked", "item", "findSlotsToResumeLocked", "Lkotlin/coroutines/Continuation;", "resumesIn", "([Lkotlin/coroutines/Continuation;)[Lkotlin/coroutines/Continuation;", "fuse", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "getPeekedValueLockedAt", "index", "growBuffer", "curBuffer", "curSize", "newSize", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "resetReplayCache", "tryEmit", "", "(Ljava/lang/Object;)Z", "tryEmitLocked", "tryEmitNoCollectorsLocked", "tryPeekLocked", "tryTakeValue", "updateBufferLocked", "newReplayIndex", "newMinCollectorIndex", "newBufferEndIndex", "newQueueEndIndex", "updateCollectorIndexLocked", "oldIndex", "updateCollectorIndexLocked$kotlinx_coroutines_core", "(J)[Lkotlin/coroutines/Continuation;", "updateNewCollectorIndexLocked", "updateNewCollectorIndexLocked$kotlinx_coroutines_core", "Emitter", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class SharedFlowImpl extends AbstractSharedFlow implements MutableSharedFlow, CancellableFlow, FusibleFlow {
   private Object[] buffer;
   private final int bufferCapacity;
   private int bufferSize;
   private long minCollectorIndex;
   private final BufferOverflow onBufferOverflow;
   private int queueSize;
   private final int replay;
   private long replayIndex;

   public SharedFlowImpl(int var1, int var2, BufferOverflow var3) {
      this.replay = var1;
      this.bufferCapacity = var2;
      this.onBufferOverflow = var3;
   }

   // $FF: synthetic method
   public static final void access$enqueueLocked(SharedFlowImpl var0, Object var1) {
      var0.enqueueLocked(var1);
   }

   // $FF: synthetic method
   public static final Continuation[] access$findSlotsToResumeLocked(SharedFlowImpl var0, Continuation[] var1) {
      return var0.findSlotsToResumeLocked(var1);
   }

   // $FF: synthetic method
   public static final int access$getBufferCapacity$p(SharedFlowImpl var0) {
      return var0.bufferCapacity;
   }

   // $FF: synthetic method
   public static final long access$getHead$p(SharedFlowImpl var0) {
      return var0.getHead();
   }

   // $FF: synthetic method
   public static final int access$getQueueSize$p(SharedFlowImpl var0) {
      return var0.queueSize;
   }

   // $FF: synthetic method
   public static final int access$getTotalSize$p(SharedFlowImpl var0) {
      return var0.getTotalSize();
   }

   // $FF: synthetic method
   public static final void access$setQueueSize$p(SharedFlowImpl var0, int var1) {
      var0.queueSize = var1;
   }

   // $FF: synthetic method
   public static final boolean access$tryEmitLocked(SharedFlowImpl var0, Object var1) {
      return var0.tryEmitLocked(var1);
   }

   // $FF: synthetic method
   public static final long access$tryPeekLocked(SharedFlowImpl var0, SharedFlowSlot var1) {
      return var0.tryPeekLocked(var1);
   }

   private final void cancelEmitter(Emitter var1) {
      synchronized(this){}

      Throwable var10000;
      label140: {
         long var2;
         long var4;
         boolean var10001;
         try {
            var2 = var1.index;
            var4 = this.getHead();
         } catch (Throwable var19) {
            var10000 = var19;
            var10001 = false;
            break label140;
         }

         if (var2 < var4) {
            return;
         }

         Object[] var6;
         Object var7;
         try {
            var6 = this.buffer;
            Intrinsics.checkNotNull(var6);
            var7 = SharedFlowKt.access$getBufferAt(var6, var1.index);
         } catch (Throwable var18) {
            var10000 = var18;
            var10001 = false;
            break label140;
         }

         if (var7 != var1) {
            return;
         }

         try {
            SharedFlowKt.access$setBufferAt(var6, var1.index, SharedFlowKt.NO_VALUE);
            this.cleanupTailLocked();
            Unit var21 = Unit.INSTANCE;
         } catch (Throwable var17) {
            var10000 = var17;
            var10001 = false;
            break label140;
         }

         return;
      }

      Throwable var20 = var10000;
      throw var20;
   }

   private final void cleanupTailLocked() {
      if (this.bufferCapacity != 0 || this.queueSize > 1) {
         Object[] var1 = this.buffer;
         Intrinsics.checkNotNull(var1);

         while(this.queueSize > 0 && SharedFlowKt.access$getBufferAt(var1, this.getHead() + (long)this.getTotalSize() - 1L) == SharedFlowKt.NO_VALUE) {
            --this.queueSize;
            SharedFlowKt.access$setBufferAt(var1, this.getHead() + (long)this.getTotalSize(), (Object)null);
         }

      }
   }

   private final void correctCollectorIndexesOnDropOldest(long var1) {
      AbstractSharedFlow var5 = (AbstractSharedFlow)this;
      if (AbstractSharedFlow.access$getNCollectors$p(var5) != 0) {
         AbstractSharedFlowSlot[] var7 = AbstractSharedFlow.access$getSlots$p(var5);
         if (var7 != null) {
            int var4 = var7.length;

            for(int var3 = 0; var3 < var4; ++var3) {
               AbstractSharedFlowSlot var6 = var7[var3];
               if (var6 != null) {
                  SharedFlowSlot var8 = (SharedFlowSlot)var6;
                  if (var8.index >= 0L && var8.index < var1) {
                     var8.index = var1;
                  }
               }
            }
         }
      }

      this.minCollectorIndex = var1;
   }

   private final void dropOldestLocked() {
      Object[] var4 = this.buffer;
      Intrinsics.checkNotNull(var4);
      SharedFlowKt.access$setBufferAt(var4, this.getHead(), (Object)null);
      --this.bufferSize;
      long var2 = this.getHead() + 1L;
      if (this.replayIndex < var2) {
         this.replayIndex = var2;
      }

      if (this.minCollectorIndex < var2) {
         this.correctCollectorIndexesOnDropOldest(var2);
      }

      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var1;
         if (this.getHead() == var2) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (!var1) {
            throw (Throwable)(new AssertionError());
         }
      }

   }

   private final void enqueueLocked(Object var1) {
      int var2 = this.getTotalSize();
      Object[] var4 = this.buffer;
      Object[] var3;
      if (var4 == null) {
         var3 = this.growBuffer((Object[])null, 0, 2);
      } else {
         var3 = var4;
         if (var2 >= var4.length) {
            var3 = this.growBuffer(var4, var2, var4.length * 2);
         }
      }

      SharedFlowKt.access$setBufferAt(var3, this.getHead() + (long)var2, var1);
   }

   private final Continuation[] findSlotsToResumeLocked(Continuation[] var1) {
      int var4 = var1.length;
      AbstractSharedFlow var6 = (AbstractSharedFlow)this;
      Continuation[] var12;
      if (AbstractSharedFlow.access$getNCollectors$p(var6) == 0) {
         var12 = var1;
      } else {
         AbstractSharedFlowSlot[] var7 = AbstractSharedFlow.access$getSlots$p(var6);
         var12 = var1;
         if (var7 != null) {
            int var5 = var7.length;
            int var2 = 0;

            while(true) {
               var12 = var1;
               if (var2 >= var5) {
                  break;
               }

               AbstractSharedFlowSlot var8 = var7[var2];
               int var3 = var4;
               var12 = var1;
               if (var8 != null) {
                  SharedFlowSlot var13 = (SharedFlowSlot)var8;
                  Continuation var9 = var13.cont;
                  var3 = var4;
                  var12 = var1;
                  if (var9 != null) {
                     if (this.tryPeekLocked(var13) < 0L) {
                        var3 = var4;
                        var12 = var1;
                     } else {
                        var12 = var1;
                        if (var4 >= var1.length) {
                           Object[] var10 = Arrays.copyOf(var1, Math.max(2, var1.length * 2));
                           Intrinsics.checkNotNullExpressionValue(var10, "java.util.Arrays.copyOf(this, newSize)");
                           var12 = (Continuation[])var10;
                        }

                        var12[var4] = var9;
                        Continuation var11 = (Continuation)null;
                        var13.cont = null;
                        var3 = var4 + 1;
                     }
                  }
               }

               ++var2;
               var4 = var3;
               var1 = var12;
            }
         }
      }

      return var12;
   }

   private final long getBufferEndIndex() {
      return this.getHead() + (long)this.bufferSize;
   }

   private final long getHead() {
      return Math.min(this.minCollectorIndex, this.replayIndex);
   }

   private final Object getPeekedValueLockedAt(long var1) {
      Object[] var3 = this.buffer;
      Intrinsics.checkNotNull(var3);
      Object var4 = SharedFlowKt.access$getBufferAt(var3, var1);
      Object var5 = var4;
      if (var4 instanceof Emitter) {
         var5 = ((Emitter)var4).value;
      }

      return var5;
   }

   private final long getQueueEndIndex() {
      return this.getHead() + (long)this.bufferSize + (long)this.queueSize;
   }

   private final int getReplaySize() {
      return (int)(this.getHead() + (long)this.bufferSize - this.replayIndex);
   }

   private final int getTotalSize() {
      return this.bufferSize + this.queueSize;
   }

   private final Object[] growBuffer(Object[] var1, int var2, int var3) {
      byte var5 = 0;
      boolean var4;
      if (var3 > 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4) {
         throw (Throwable)(new IllegalStateException("Buffer size overflow".toString()));
      } else {
         Object[] var10 = new Object[var3];
         this.buffer = var10;
         if (var1 == null) {
            return var10;
         } else {
            long var6 = this.getHead();

            for(var3 = var5; var3 < var2; ++var3) {
               long var8 = (long)var3 + var6;
               SharedFlowKt.access$setBufferAt(var10, var8, SharedFlowKt.access$getBufferAt(var1, var8));
            }

            return var10;
         }
      }
   }

   private final boolean tryEmitLocked(Object var1) {
      if (this.getNCollectors() == 0) {
         return this.tryEmitNoCollectorsLocked(var1);
      } else {
         int var2;
         if (this.bufferSize >= this.bufferCapacity && this.minCollectorIndex <= this.replayIndex) {
            BufferOverflow var3 = this.onBufferOverflow;
            var2 = SharedFlowImpl$WhenMappings.$EnumSwitchMapping$0[var3.ordinal()];
            if (var2 == 1) {
               return false;
            }

            if (var2 == 2) {
               return true;
            }
         }

         this.enqueueLocked(var1);
         var2 = this.bufferSize + 1;
         this.bufferSize = var2;
         if (var2 > this.bufferCapacity) {
            this.dropOldestLocked();
         }

         if (this.getReplaySize() > this.replay) {
            this.updateBufferLocked(this.replayIndex + 1L, this.minCollectorIndex, this.getBufferEndIndex(), this.getQueueEndIndex());
         }

         return true;
      }
   }

   private final boolean tryEmitNoCollectorsLocked(Object var1) {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var2;
         if (this.getNCollectors() == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (this.replay == 0) {
         return true;
      } else {
         this.enqueueLocked(var1);
         int var3 = this.bufferSize + 1;
         this.bufferSize = var3;
         if (var3 > this.replay) {
            this.dropOldestLocked();
         }

         this.minCollectorIndex = this.getHead() + (long)this.bufferSize;
         return true;
      }
   }

   private final long tryPeekLocked(SharedFlowSlot var1) {
      long var2 = var1.index;
      if (var2 < this.getBufferEndIndex()) {
         return var2;
      } else if (this.bufferCapacity > 0) {
         return -1L;
      } else if (var2 > this.getHead()) {
         return -1L;
      } else {
         return this.queueSize == 0 ? -1L : var2;
      }
   }

   private final Object tryTakeValue(SharedFlowSlot var1) {
      Continuation[] var8 = AbstractSharedFlowKt.EMPTY_RESUMES;
      synchronized(this){}

      Object var24;
      label161: {
         Throwable var10000;
         label160: {
            long var6;
            boolean var10001;
            try {
               var6 = this.tryPeekLocked(var1);
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label160;
            }

            if (var6 < 0L) {
               label153:
               try {
                  var24 = SharedFlowKt.NO_VALUE;
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label153;
               }
            } else {
               label156: {
                  Object var9;
                  try {
                     long var4 = var1.index;
                     var9 = this.getPeekedValueLockedAt(var6);
                     var1.index = var6 + 1L;
                     var8 = this.updateCollectorIndexLocked$kotlinx_coroutines_core(var4);
                  } catch (Throwable var22) {
                     var10000 = var22;
                     var10001 = false;
                     break label156;
                  }

                  var24 = var9;
               }
            }
            break label161;
         }

         Throwable var25 = var10000;
         throw var25;
      }

      int var3 = var8.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         Continuation var26 = var8[var2];
         if (var26 != null) {
            Unit var10 = Unit.INSTANCE;
            Result.Companion var11 = Result.Companion;
            var26.resumeWith(Result.constructor_impl(var10));
         }
      }

      return var24;
   }

   private final void updateBufferLocked(long var1, long var3, long var5, long var7) {
      long var14 = Math.min(var3, var1);
      boolean var11 = DebugKt.getASSERTIONS_ENABLED();
      boolean var10 = true;
      boolean var9;
      if (var11) {
         if (var14 >= this.getHead()) {
            var9 = true;
         } else {
            var9 = false;
         }

         if (!var9) {
            throw (Throwable)(new AssertionError());
         }
      }

      for(long var12 = this.getHead(); var12 < var14; ++var12) {
         Object[] var16 = this.buffer;
         Intrinsics.checkNotNull(var16);
         SharedFlowKt.access$setBufferAt(var16, var12, (Object)null);
      }

      this.replayIndex = var1;
      this.minCollectorIndex = var3;
      this.bufferSize = (int)(var5 - var14);
      this.queueSize = (int)(var7 - var5);
      if (DebugKt.getASSERTIONS_ENABLED()) {
         if (this.bufferSize >= 0) {
            var9 = true;
         } else {
            var9 = false;
         }

         if (!var9) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (DebugKt.getASSERTIONS_ENABLED()) {
         if (this.queueSize >= 0) {
            var9 = true;
         } else {
            var9 = false;
         }

         if (!var9) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (DebugKt.getASSERTIONS_ENABLED()) {
         if (this.replayIndex <= this.getHead() + (long)this.bufferSize) {
            var9 = var10;
         } else {
            var9 = false;
         }

         if (!var9) {
            throw (Throwable)(new AssertionError());
         }
      }

   }

   // $FF: synthetic method
   final Object awaitValue(SharedFlowSlot var1, Continuation var2) {
      CancellableContinuationImpl var3 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var2), 1);
      var3.initCancellability();
      CancellableContinuation var4 = (CancellableContinuation)var3;
      synchronized(this){}

      label144: {
         Throwable var10000;
         label148: {
            boolean var10001;
            label141: {
               try {
                  if (access$tryPeekLocked(this, var1) < 0L) {
                     var1.cont = (Continuation)var4;
                     var1.cont = (Continuation)var4;
                     break label141;
                  }
               } catch (Throwable var17) {
                  var10000 = var17;
                  var10001 = false;
                  break label148;
               }

               try {
                  Continuation var18 = (Continuation)var4;
                  Unit var22 = Unit.INSTANCE;
                  Result.Companion var5 = Result.Companion;
                  var18.resumeWith(Result.constructor_impl(var22));
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label148;
               }
            }

            label132:
            try {
               Unit var20 = Unit.INSTANCE;
               break label144;
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label132;
            }
         }

         Throwable var19 = var10000;
         throw var19;
      }

      Object var21 = var3.getResult();
      if (var21 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var2);
      }

      return var21;
   }

   public Object collect(FlowCollector param1, Continuation param2) {
      // $FF: Couldn't be decompiled
   }

   protected SharedFlowSlot createSlot() {
      return new SharedFlowSlot();
   }

   protected SharedFlowSlot[] createSlotArray(int var1) {
      return new SharedFlowSlot[var1];
   }

   public Object emit(Object var1, Continuation var2) {
      if (this.tryEmit(var1)) {
         return Unit.INSTANCE;
      } else {
         var1 = this.emitSuspend(var1, var2);
         return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
      }
   }

   // $FF: synthetic method
   final Object emitSuspend(Object var1, Continuation var2) {
      CancellableContinuationImpl var9 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var2), 1);
      var9.initCancellability();
      CancellableContinuation var10 = (CancellableContinuation)var9;
      Continuation[] var7 = AbstractSharedFlowKt.EMPTY_RESUMES;
      synchronized(this){}

      Continuation[] var24;
      Emitter var27;
      label243: {
         label242: {
            Throwable var10000;
            label247: {
               boolean var10001;
               try {
                  if (access$tryEmitLocked(this, var1)) {
                     Continuation var11 = (Continuation)var10;
                     Unit var26 = Unit.INSTANCE;
                     Result.Companion var28 = Result.Companion;
                     var11.resumeWith(Result.constructor_impl(var26));
                     var24 = access$findSlotsToResumeLocked(this, var7);
                     break label242;
                  }
               } catch (Throwable var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label247;
               }

               Emitter var8;
               try {
                  long var5 = access$getHead$p(this);
                  var8 = new Emitter(this, (long)access$getTotalSize$p(this) + var5, var1, (Continuation)var10);
                  access$enqueueLocked(this, var8);
                  access$setQueueSize$p(this, access$getQueueSize$p(this) + 1);
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label247;
               }

               var24 = var7;

               try {
                  if (access$getBufferCapacity$p(this) == 0) {
                     var24 = access$findSlotsToResumeLocked(this, var7);
                  }
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label247;
               }

               var27 = var8;
               break label243;
            }

            Throwable var25 = var10000;
            throw var25;
         }

         var27 = null;
      }

      if (var27 != null) {
         CancellableContinuationKt.disposeOnCancellation(var10, (DisposableHandle)var27);
      }

      int var4 = var24.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Continuation var30 = var24[var3];
         if (var30 != null) {
            Unit var31 = Unit.INSTANCE;
            Result.Companion var29 = Result.Companion;
            var30.resumeWith(Result.constructor_impl(var31));
         }
      }

      var1 = var9.getResult();
      if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var2);
      }

      return var1;
   }

   public Flow fuse(CoroutineContext var1, int var2, BufferOverflow var3) {
      return SharedFlowKt.fuseSharedFlow(this, var1, var2, var3);
   }

   public List getReplayCache() {
      synchronized(this){}

      Throwable var10000;
      label206: {
         int var2;
         boolean var10001;
         try {
            var2 = this.getReplaySize();
         } catch (Throwable var24) {
            var10000 = var24;
            var10001 = false;
            break label206;
         }

         if (var2 == 0) {
            label192: {
               List var3;
               try {
                  var3 = CollectionsKt.emptyList();
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label192;
               }

               return var3;
            }
         } else {
            label210: {
               ArrayList var4;
               Object[] var25;
               try {
                  var4 = new ArrayList(var2);
                  var25 = this.buffer;
                  Intrinsics.checkNotNull(var25);
               } catch (Throwable var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label210;
               }

               int var1 = 0;

               while(true) {
                  if (var1 >= var2) {
                     return (List)var4;
                  }

                  try {
                     ((Collection)var4).add(SharedFlowKt.access$getBufferAt(var25, this.replayIndex + (long)var1));
                  } catch (Throwable var22) {
                     var10000 = var22;
                     var10001 = false;
                     break;
                  }

                  ++var1;
               }
            }
         }
      }

      Throwable var26 = var10000;
      throw var26;
   }

   public void resetReplayCache() {
      synchronized(this){}

      try {
         this.updateBufferLocked(this.getBufferEndIndex(), this.minCollectorIndex, this.getBufferEndIndex(), this.getQueueEndIndex());
         Unit var1 = Unit.INSTANCE;
      } finally {
         ;
      }

   }

   public boolean tryEmit(Object var1) {
      Continuation[] var5 = AbstractSharedFlowKt.EMPTY_RESUMES;
      synchronized(this){}

      int var2;
      boolean var4;
      Continuation[] var15;
      label115: {
         Throwable var10000;
         label119: {
            boolean var10001;
            try {
               var4 = this.tryEmitLocked(var1);
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label119;
            }

            var2 = 0;
            if (!var4) {
               var4 = false;
               var15 = var5;
               break label115;
            }

            try {
               var15 = this.findSlotsToResumeLocked(var5);
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break label119;
            }

            var4 = true;
            break label115;
         }

         Throwable var14 = var10000;
         throw var14;
      }

      for(int var3 = var15.length; var2 < var3; ++var2) {
         Continuation var6 = var15[var2];
         if (var6 != null) {
            Unit var16 = Unit.INSTANCE;
            Result.Companion var7 = Result.Companion;
            var6.resumeWith(Result.constructor_impl(var16));
         }
      }

      return var4;
   }

   public final Continuation[] updateCollectorIndexLocked$kotlinx_coroutines_core(long var1) {
      boolean var3;
      if (DebugKt.getASSERTIONS_ENABLED()) {
         if (var1 >= this.minCollectorIndex) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (!var3) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (var1 > this.minCollectorIndex) {
         return AbstractSharedFlowKt.EMPTY_RESUMES;
      } else {
         long var10 = this.getHead();
         var1 = (long)this.bufferSize + var10;
         long var6 = var1;
         if (this.bufferCapacity == 0) {
            var6 = var1;
            if (this.queueSize > 0) {
               var6 = var1 + 1L;
            }
         }

         AbstractSharedFlow var16 = (AbstractSharedFlow)this;
         int var4;
         int var19;
         if (AbstractSharedFlow.access$getNCollectors$p(var16) == 0) {
            var1 = var6;
         } else {
            AbstractSharedFlowSlot[] var20 = AbstractSharedFlow.access$getSlots$p(var16);
            var1 = var6;
            if (var20 != null) {
               var4 = var20.length;
               var19 = 0;

               while(true) {
                  var1 = var6;
                  if (var19 >= var4) {
                     break;
                  }

                  AbstractSharedFlowSlot var17 = var20[var19];
                  var1 = var6;
                  if (var17 != null) {
                     SharedFlowSlot var22 = (SharedFlowSlot)var17;
                     var1 = var6;
                     if (var22.index >= 0L) {
                        var1 = var6;
                        if (var22.index < var6) {
                           var1 = var22.index;
                        }
                     }
                  }

                  ++var19;
                  var6 = var1;
               }
            }
         }

         if (DebugKt.getASSERTIONS_ENABLED()) {
            if (var1 >= this.minCollectorIndex) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (!var3) {
               throw (Throwable)(new AssertionError());
            }
         }

         if (var1 <= this.minCollectorIndex) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
         } else {
            var6 = this.getBufferEndIndex();
            if (this.getNCollectors() > 0) {
               var19 = (int)(var6 - var1);
               var19 = Math.min(this.queueSize, this.bufferCapacity - var19);
            } else {
               var19 = this.queueSize;
            }

            Continuation[] var21 = AbstractSharedFlowKt.EMPTY_RESUMES;
            long var14 = (long)this.queueSize + var6;
            long var8;
            Object[] var23;
            if (var19 > 0) {
               var21 = new Continuation[var19];
               var23 = this.buffer;
               Intrinsics.checkNotNull(var23);
               var8 = var6;

               for(var4 = 0; var6 < var14; ++var6) {
                  Object var18 = SharedFlowKt.access$getBufferAt(var23, var6);
                  if (var18 != SharedFlowKt.NO_VALUE) {
                     if (var18 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter");
                     }

                     Emitter var24 = (Emitter)var18;
                     int var5 = var4 + 1;
                     var21[var4] = var24.cont;
                     SharedFlowKt.access$setBufferAt(var23, var6, SharedFlowKt.NO_VALUE);
                     SharedFlowKt.access$setBufferAt(var23, var8, var24.value);
                     ++var8;
                     if (var5 >= var19) {
                        break;
                     }

                     var4 = var5;
                  }
               }

               var6 = var1;
               var1 = var8;
               var8 = var6;
            } else {
               var8 = var1;
               var1 = var6;
            }

            var19 = (int)(var1 - var10);
            if (this.getNCollectors() == 0) {
               var6 = var1;
            } else {
               var6 = var8;
            }

            long var12 = Math.max(this.replayIndex, var1 - (long)Math.min(this.replay, var19));
            var10 = var12;
            var8 = var1;
            if (this.bufferCapacity == 0) {
               var10 = var12;
               var8 = var1;
               if (var12 < var14) {
                  var23 = this.buffer;
                  Intrinsics.checkNotNull(var23);
                  var10 = var12;
                  var8 = var1;
                  if (Intrinsics.areEqual((Object)SharedFlowKt.access$getBufferAt(var23, var12), (Object)SharedFlowKt.NO_VALUE)) {
                     var8 = var1 + 1L;
                     var10 = var12 + 1L;
                  }
               }
            }

            this.updateBufferLocked(var10, var6, var8, var14);
            this.cleanupTailLocked();
            if (var21.length == 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            Continuation[] var25 = var21;
            if (true ^ var3) {
               var25 = this.findSlotsToResumeLocked(var21);
            }

            return var25;
         }
      }
   }

   public final long updateNewCollectorIndexLocked$kotlinx_coroutines_core() {
      long var1 = this.replayIndex;
      if (var1 < this.minCollectorIndex) {
         this.minCollectorIndex = var1;
      }

      return var1;
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B1\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\nH\u0016R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"},
      d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "Lkotlinx/coroutines/DisposableHandle;", "flow", "Lkotlinx/coroutines/flow/SharedFlowImpl;", "index", "", "value", "", "cont", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/SharedFlowImpl;JLjava/lang/Object;Lkotlin/coroutines/Continuation;)V", "dispose", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class Emitter implements DisposableHandle {
      public final Continuation cont;
      public final SharedFlowImpl flow;
      public long index;
      public final Object value;

      public Emitter(SharedFlowImpl var1, long var2, Object var4, Continuation var5) {
         this.flow = var1;
         this.index = var2;
         this.value = var4;
         this.cont = var5;
      }

      public void dispose() {
         this.flow.cancelEmitter(this);
      }
   }
}
