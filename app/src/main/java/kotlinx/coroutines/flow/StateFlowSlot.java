package kotlinx.coroutines.flow;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0013B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u001b\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ)\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f0\u000b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\u0002J\r\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"},
   d2 = {"Lkotlinx/coroutines/flow/StateFlowSlot;", "<init>", "()V", "Lkotlinx/coroutines/flow/StateFlowImpl;", "flow", "", "allocateLocked", "(Lkotlinx/coroutines/flow/StateFlowImpl;)Z", "", "awaitPending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "Lkotlin/coroutines/Continuation;", "freeLocked", "(Lkotlinx/coroutines/flow/StateFlowImpl;)[Lkotlin/coroutines/Continuation;", "makePending", "takePending", "()Z", "kotlinx-coroutines-core", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;"},
   k = 1,
   mv = {1, 4, 0}
)
final class StateFlowSlot extends AbstractSharedFlowSlot {
   static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowSlot.class, Object.class, "_state");
   volatile Object _state = null;

   public StateFlowSlot() {
   }

   public boolean allocateLocked(StateFlowImpl var1) {
      if (this._state != null) {
         return false;
      } else {
         this._state = StateFlowKt.access$getNONE$p();
         return true;
      }
   }

   public final Object awaitPending(Continuation var1) {
      Continuation var3 = IntrinsicsKt.intercepted(var1);
      boolean var2 = true;
      CancellableContinuationImpl var7 = new CancellableContinuationImpl(var3, 1);
      var7.initCancellability();
      CancellableContinuation var4 = (CancellableContinuation)var7;
      if (DebugKt.getASSERTIONS_ENABLED() && !Boxing.boxBoolean(this._state instanceof CancellableContinuationImpl ^ true)) {
         throw (Throwable)(new AssertionError());
      } else {
         if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, StateFlowKt.access$getNONE$p(), var4)) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
               if (this._state != StateFlowKt.access$getPENDING$p()) {
                  var2 = false;
               }

               if (!Boxing.boxBoolean(var2)) {
                  throw (Throwable)(new AssertionError());
               }
            }

            Continuation var5 = (Continuation)var4;
            Unit var9 = Unit.INSTANCE;
            Result.Companion var6 = Result.Companion;
            var5.resumeWith(Result.constructor_impl(var9));
         }

         Object var8 = var7.getResult();
         if (var8 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var1);
         }

         return var8;
      }
   }

   public Continuation[] freeLocked(StateFlowImpl var1) {
      this._state = null;
      return AbstractSharedFlowKt.EMPTY_RESUMES;
   }

   public final void makePending() {
      while(true) {
         Object var1 = this._state;
         if (var1 == null) {
            return;
         }

         if (var1 == StateFlowKt.access$getPENDING$p()) {
            return;
         }

         if (var1 == StateFlowKt.access$getNONE$p()) {
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, StateFlowKt.access$getPENDING$p())) {
               return;
            }
         } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, StateFlowKt.access$getNONE$p())) {
            Continuation var4 = (Continuation)((CancellableContinuationImpl)var1);
            Unit var2 = Unit.INSTANCE;
            Result.Companion var3 = Result.Companion;
            var4.resumeWith(Result.constructor_impl(var2));
            return;
         }
      }
   }

   public final boolean takePending() {
      Object var3 = _state$FU.getAndSet(this, StateFlowKt.access$getNONE$p());
      Intrinsics.checkNotNull(var3);
      boolean var2 = DebugKt.getASSERTIONS_ENABLED();
      boolean var1 = true;
      if (var2 && !(var3 instanceof CancellableContinuationImpl ^ true)) {
         throw (Throwable)(new AssertionError());
      } else {
         if (var3 != StateFlowKt.access$getPENDING$p()) {
            var1 = false;
         }

         return var1;
      }
   }
}
