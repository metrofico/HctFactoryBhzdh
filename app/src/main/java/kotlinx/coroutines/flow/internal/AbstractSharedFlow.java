package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u00060\u0003j\u0002`\u0004B\u0005¢\u0006\u0002\u0010\u0005J\r\u0010\u0018\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0019J\r\u0010\u001a\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000e2\u0006\u0010\u001c\u001a\u00020\bH$¢\u0006\u0002\u0010\u001dJ\u001d\u0010\u001e\u001a\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001f0!H\u0084\bJ\u0015\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010$R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R:\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u000e2\u0010\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u000e@BX\u0084\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0010\u0010\u0005\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"},
   d2 = {"Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "S", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "()V", "_subscriptionCount", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "<set-?>", "nCollectors", "getNCollectors", "()I", "nextIndex", "", "slots", "getSlots$annotations", "getSlots", "()[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "getSubscriptionCount", "()Lkotlinx/coroutines/flow/StateFlow;", "allocateSlot", "()Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "createSlot", "createSlotArray", "size", "(I)[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "forEachSlotLocked", "", "block", "Lkotlin/Function1;", "freeSlot", "slot", "(Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;)V", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class AbstractSharedFlow {
   private MutableStateFlow _subscriptionCount;
   private int nCollectors;
   private int nextIndex;
   private AbstractSharedFlowSlot[] slots;

   // $FF: synthetic method
   public static final int access$getNCollectors$p(AbstractSharedFlow var0) {
      return var0.nCollectors;
   }

   // $FF: synthetic method
   public static final AbstractSharedFlowSlot[] access$getSlots$p(AbstractSharedFlow var0) {
      return var0.slots;
   }

   // $FF: synthetic method
   public static final void access$setNCollectors$p(AbstractSharedFlow var0, int var1) {
      var0.nCollectors = var1;
   }

   // $FF: synthetic method
   public static final void access$setSlots$p(AbstractSharedFlow var0, AbstractSharedFlowSlot[] var1) {
      var0.slots = var1;
   }

   // $FF: synthetic method
   protected static void getSlots$annotations() {
   }

   protected final AbstractSharedFlowSlot allocateSlot() {
      MutableStateFlow var3 = (MutableStateFlow)null;
      synchronized(this){}

      Throwable var10000;
      label702: {
         AbstractSharedFlowSlot[] var4;
         boolean var10001;
         try {
            var4 = this.slots;
         } catch (Throwable var74) {
            var10000 = var74;
            var10001 = false;
            break label702;
         }

         AbstractSharedFlowSlot[] var77;
         if (var4 == null) {
            try {
               var77 = this.createSlotArray(2);
               this.slots = var77;
            } catch (Throwable var73) {
               var10000 = var73;
               var10001 = false;
               break label702;
            }
         } else {
            var77 = var4;

            try {
               if (this.nCollectors >= var4.length) {
                  Object[] var78 = Arrays.copyOf(var4, var4.length * 2);
                  Intrinsics.checkNotNullExpressionValue(var78, "java.util.Arrays.copyOf(this, newSize)");
                  this.slots = (AbstractSharedFlowSlot[])var78;
                  var77 = (AbstractSharedFlowSlot[])var78;
               }
            } catch (Throwable var72) {
               var10000 = var72;
               var10001 = false;
               break label702;
            }
         }

         int var2;
         try {
            var2 = this.nextIndex;
         } catch (Throwable var71) {
            var10000 = var71;
            var10001 = false;
            break label702;
         }

         while(true) {
            AbstractSharedFlowSlot var79 = var77[var2];
            if (var79 == null) {
               try {
                  var79 = this.createSlot();
               } catch (Throwable var70) {
                  var10000 = var70;
                  var10001 = false;
                  break;
               }

               var77[var2] = var79;
            }

            ++var2;
            int var1 = var2;

            label692: {
               try {
                  if (var2 < var77.length) {
                     break label692;
                  }
               } catch (Throwable var76) {
                  var10000 = var76;
                  var10001 = false;
                  break;
               }

               var1 = 0;
            }

            if (var79 != null) {
               var2 = var1;

               try {
                  if (!var79.allocateLocked(this)) {
                     continue;
                  }

                  this.nextIndex = var1;
                  ++this.nCollectors;
                  var3 = this._subscriptionCount;
               } catch (Throwable var75) {
                  var10000 = var75;
                  var10001 = false;
                  break;
               }

               if (var3 != null) {
                  StateFlowKt.increment(var3, 1);
               }

               return var79;
            }

            try {
               NullPointerException var80 = new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
               throw var80;
            } catch (Throwable var69) {
               var10000 = var69;
               var10001 = false;
               break;
            }
         }
      }

      Throwable var81 = var10000;
      throw var81;
   }

   protected abstract AbstractSharedFlowSlot createSlot();

   protected abstract AbstractSharedFlowSlot[] createSlotArray(int var1);

   protected final void forEachSlotLocked(Function1 var1) {
      if (access$getNCollectors$p(this) != 0) {
         AbstractSharedFlowSlot[] var4 = access$getSlots$p(this);
         if (var4 != null) {
            int var3 = var4.length;

            for(int var2 = 0; var2 < var3; ++var2) {
               AbstractSharedFlowSlot var5 = var4[var2];
               if (var5 != null) {
                  var1.invoke(var5);
               }
            }
         }

      }
   }

   protected final void freeSlot(AbstractSharedFlowSlot var1) {
      MutableStateFlow var4 = (MutableStateFlow)null;
      synchronized(this){}

      Throwable var10000;
      label258: {
         int var3;
         boolean var10001;
         try {
            var3 = this.nCollectors - 1;
            this.nCollectors = var3;
            var4 = this._subscriptionCount;
         } catch (Throwable var27) {
            var10000 = var27;
            var10001 = false;
            break label258;
         }

         int var2 = 0;
         if (var3 == 0) {
            try {
               this.nextIndex = 0;
            } catch (Throwable var26) {
               var10000 = var26;
               var10001 = false;
               break label258;
            }
         }

         if (var1 != null) {
            label262: {
               Continuation[] var7;
               try {
                  var7 = var1.freeLocked(this);
               } catch (Throwable var24) {
                  var10000 = var24;
                  var10001 = false;
                  break label262;
               }

               for(var3 = var7.length; var2 < var3; ++var2) {
                  Continuation var5 = var7[var2];
                  if (var5 != null) {
                     Unit var28 = Unit.INSTANCE;
                     Result.Companion var6 = Result.Companion;
                     var5.resumeWith(Result.constructor_impl(var28));
                  }
               }

               if (var4 != null) {
                  StateFlowKt.increment(var4, -1);
               }

               return;
            }
         } else {
            label246:
            try {
               NullPointerException var30 = new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
               throw var30;
            } catch (Throwable var25) {
               var10000 = var25;
               var10001 = false;
               break label246;
            }
         }
      }

      Throwable var29 = var10000;
      throw var29;
   }

   protected final int getNCollectors() {
      return this.nCollectors;
   }

   protected final AbstractSharedFlowSlot[] getSlots() {
      return this.slots;
   }

   public final StateFlow getSubscriptionCount() {
      synchronized(this){}

      Throwable var10000;
      label76: {
         MutableStateFlow var1;
         boolean var10001;
         try {
            var1 = this._subscriptionCount;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label76;
         }

         if (var1 != null) {
            return (StateFlow)var1;
         }

         label67:
         try {
            var1 = StateFlowKt.MutableStateFlow(this.nCollectors);
            this._subscriptionCount = var1;
            return (StateFlow)var1;
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label67;
         }
      }

      Throwable var8 = var10000;
      throw var8;
   }
}
