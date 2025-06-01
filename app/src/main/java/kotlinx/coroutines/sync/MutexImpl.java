package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00112\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00110 :\u0006$%&'()B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\n\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\f\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0082@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000bJT\u0010\u0014\u001a\u00020\t\"\u0004\b\u0000\u0010\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\"\u0010\u0013\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0010H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\u0019\u0010\bJ\u0019\u0010\u001a\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00018V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u00018@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001dR$\u0010#\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00110 8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"},
   d2 = {"Lkotlinx/coroutines/sync/MutexImpl;", "", "locked", "<init>", "(Z)V", "", "owner", "holdsLock", "(Ljava/lang/Object;)Z", "", "lock", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockSuspend", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/Function2;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectClause2", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "", "toString", "()Ljava/lang/String;", "tryLock", "unlock", "(Ljava/lang/Object;)V", "isLocked", "()Z", "isLockedEmptyQueueState$kotlinx_coroutines_core", "isLockedEmptyQueueState", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnLock", "()Lkotlinx/coroutines/selects/SelectClause2;", "onLock", "LockCont", "LockSelect", "LockWaiter", "LockedQueue", "TryLockDesc", "UnlockOp", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class MutexImpl implements Mutex, SelectClause2 {
   static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");
   volatile Object _state;

   public MutexImpl(boolean var1) {
      Empty var2;
      if (var1) {
         var2 = MutexKt.access$getEMPTY_LOCKED$p();
      } else {
         var2 = MutexKt.access$getEMPTY_UNLOCKED$p();
      }

      this._state = var2;
   }

   public SelectClause2 getOnLock() {
      return (SelectClause2)this;
   }

   public boolean holdsLock(Object var1) {
      Object var4 = this._state;
      boolean var3 = var4 instanceof Empty;
      boolean var2 = true;
      if (var3) {
         if (((Empty)var4).locked == var1) {
            return var2;
         }
      } else if (var4 instanceof LockedQueue && ((LockedQueue)var4).owner == var1) {
         return var2;
      }

      var2 = false;
      return var2;
   }

   public boolean isLocked() {
      while(true) {
         Object var3 = this._state;
         boolean var2 = var3 instanceof Empty;
         boolean var1 = true;
         if (var2) {
            if (((Empty)var3).locked == MutexKt.access$getUNLOCKED$p()) {
               var1 = false;
            }

            return var1;
         }

         if (var3 instanceof LockedQueue) {
            return true;
         }

         if (!(var3 instanceof OpDescriptor)) {
            throw (Throwable)(new IllegalStateException(("Illegal state " + var3).toString()));
         }

         ((OpDescriptor)var3).perform(this);
      }
   }

   public final boolean isLockedEmptyQueueState$kotlinx_coroutines_core() {
      Object var2 = this._state;
      boolean var1;
      if (var2 instanceof LockedQueue && ((LockedQueue)var2).isEmpty()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public Object lock(Object var1, Continuation var2) {
      if (this.tryLock(var1)) {
         return Unit.INSTANCE;
      } else {
         var1 = this.lockSuspend(var1, var2);
         return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
      }
   }

   // $FF: synthetic method
   final Object lockSuspend(Object var1, Continuation var2) {
      CancellableContinuationImpl var7 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(var2));
      CancellableContinuation var9 = (CancellableContinuation)var7;
      LockCont var8 = new LockCont(this, var1, var9);

      while(true) {
         Object var10 = this._state;
         if (var10 instanceof Empty) {
            Empty var14 = (Empty)var10;
            if (var14.locked != MutexKt.access$getUNLOCKED$p()) {
               AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var10, new LockedQueue(var14.locked));
            } else {
               if (var1 == null) {
                  var14 = MutexKt.access$getEMPTY_LOCKED$p();
               } else {
                  var14 = new Empty(var1);
               }

               if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var10, var14)) {
                  Continuation var16 = (Continuation)var9;
                  Unit var15 = Unit.INSTANCE;
                  Result.Companion var12 = Result.Companion;
                  var16.resumeWith(Result.constructor_impl(var15));
                  break;
               }
            }
         } else if (!(var10 instanceof LockedQueue)) {
            if (!(var10 instanceof OpDescriptor)) {
               throw (Throwable)(new IllegalStateException(("Illegal state " + var10).toString()));
            }

            ((OpDescriptor)var10).perform(this);
         } else {
            LockedQueue var6 = (LockedQueue)var10;
            Object var11 = var6.owner;
            boolean var4 = true;
            boolean var3;
            if (var11 != var1) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (!var3) {
               throw (Throwable)(new IllegalStateException(("Already locked by " + var1).toString()));
            }

            LockFreeLinkedListNode var13 = (LockFreeLinkedListNode)var6;
            LockFreeLinkedListNode var18 = (LockFreeLinkedListNode)var8;
            LockFreeLinkedListNode.CondAddOp var17 = (LockFreeLinkedListNode.CondAddOp)(new LockFreeLinkedListNode.CondAddOp(var18, var18, var10, var9, var8, this, var1) {
               final CancellableContinuation $cont$inlined;
               final LockFreeLinkedListNode $node;
               final Object $owner$inlined;
               final Object $state$inlined;
               final LockCont $waiter$inlined;
               final MutexImpl this$0;

               public {
                  this.$node = var1;
                  this.$state$inlined = var3;
                  this.$cont$inlined = var4;
                  this.$waiter$inlined = var5;
                  this.this$0 = var6;
                  this.$owner$inlined = var7;
               }

               public Object prepare(LockFreeLinkedListNode var1) {
                  boolean var2;
                  if (this.this$0._state == this.$state$inlined) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  Object var3;
                  if (var2) {
                     var3 = null;
                  } else {
                     var3 = LockFreeLinkedListKt.getCONDITION_FALSE();
                  }

                  return var3;
               }
            });

            while(true) {
               int var5 = var13.getPrevNode().tryCondAddNext(var18, var13, var17);
               var3 = var4;
               if (var5 == 1) {
                  break;
               }

               if (var5 == 2) {
                  var3 = false;
                  break;
               }
            }

            if (var3) {
               CancellableContinuationKt.removeOnCancellation(var9, var18);
               break;
            }
         }
      }

      var1 = var7.getResult();
      if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var2);
      }

      return var1;
   }

   public void registerSelectClause2(SelectInstance var1, Object var2, Function2 var3) {
      while(!var1.isSelected()) {
         Object var7 = this._state;
         if (var7 instanceof Empty) {
            Empty var13 = (Empty)var7;
            if (var13.locked != MutexKt.access$getUNLOCKED$p()) {
               AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var7, new LockedQueue(var13.locked));
            } else {
               var7 = var1.performAtomicTrySelect((AtomicDesc)(new TryLockDesc(this, var2)));
               if (var7 == null) {
                  UndispatchedKt.startCoroutineUnintercepted(var3, this, var1.getCompletion());
                  return;
               }

               if (var7 == SelectKt.getALREADY_SELECTED()) {
                  return;
               }

               if (var7 != MutexKt.access$getLOCK_FAIL$p() && var7 != AtomicKt.RETRY_ATOMIC) {
                  throw (Throwable)(new IllegalStateException(("performAtomicTrySelect(TryLockDesc) returned " + var7).toString()));
               }
            }
         } else if (!(var7 instanceof LockedQueue)) {
            if (!(var7 instanceof OpDescriptor)) {
               throw (Throwable)(new IllegalStateException(("Illegal state " + var7).toString()));
            }

            ((OpDescriptor)var7).perform(this);
         } else {
            LockedQueue var9 = (LockedQueue)var7;
            Object var8 = var9.owner;
            boolean var5 = false;
            boolean var4;
            if (var8 != var2) {
               var4 = true;
            } else {
               var4 = false;
            }

            if (!var4) {
               throw (Throwable)(new IllegalStateException(("Already locked by " + var2).toString()));
            }

            LockSelect var12 = new LockSelect(this, var2, var1, var3);
            LockFreeLinkedListNode var14 = (LockFreeLinkedListNode)var9;
            LockFreeLinkedListNode var10 = (LockFreeLinkedListNode)var12;
            LockFreeLinkedListNode.CondAddOp var11 = (LockFreeLinkedListNode.CondAddOp)(new LockFreeLinkedListNode.CondAddOp(var10, var10, this, var7) {
               final LockFreeLinkedListNode $node;
               final Object $state$inlined;
               final MutexImpl this$0;

               public {
                  this.$node = var1;
                  this.this$0 = var3;
                  this.$state$inlined = var4;
               }

               public Object prepare(LockFreeLinkedListNode var1) {
                  boolean var2;
                  if (this.this$0._state == this.$state$inlined) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  Object var3;
                  if (var2) {
                     var3 = null;
                  } else {
                     var3 = LockFreeLinkedListKt.getCONDITION_FALSE();
                  }

                  return var3;
               }
            });

            while(true) {
               int var6 = var14.getPrevNode().tryCondAddNext(var10, var14, var11);
               if (var6 != 1) {
                  var4 = var5;
                  if (var6 != 2) {
                     continue;
                  }
                  break;
               }

               var4 = true;
               break;
            }

            if (var4) {
               var1.disposeOnSelect((DisposableHandle)var12);
               return;
            }
         }
      }

   }

   public String toString() {
      while(true) {
         Object var1 = this._state;
         if (var1 instanceof Empty) {
            return "Mutex[" + ((Empty)var1).locked + ']';
         }

         if (!(var1 instanceof OpDescriptor)) {
            if (var1 instanceof LockedQueue) {
               return "Mutex[" + ((LockedQueue)var1).owner + ']';
            }

            throw (Throwable)(new IllegalStateException(("Illegal state " + var1).toString()));
         }

         ((OpDescriptor)var1).perform(this);
      }
   }

   public boolean tryLock(Object var1) {
      while(true) {
         Object var5 = this._state;
         boolean var3 = var5 instanceof Empty;
         boolean var2 = true;
         if (var3) {
            if (((Empty)var5).locked != MutexKt.access$getUNLOCKED$p()) {
               return false;
            }

            Empty var4;
            if (var1 == null) {
               var4 = MutexKt.access$getEMPTY_LOCKED$p();
            } else {
               var4 = new Empty(var1);
            }

            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var5, var4)) {
               return true;
            }
         } else {
            if (var5 instanceof LockedQueue) {
               if (((LockedQueue)var5).owner == var1) {
                  var2 = false;
               }

               if (var2) {
                  return false;
               }

               throw (Throwable)(new IllegalStateException(("Already locked by " + var1).toString()));
            }

            if (!(var5 instanceof OpDescriptor)) {
               throw (Throwable)(new IllegalStateException(("Illegal state " + var5).toString()));
            }

            ((OpDescriptor)var5).perform(this);
         }
      }
   }

   public void unlock(Object var1) {
      while(true) {
         Object var7 = this._state;
         boolean var5 = var7 instanceof Empty;
         boolean var3 = true;
         boolean var2 = true;
         boolean var4 = true;
         if (var5) {
            if (var1 == null) {
               if (((Empty)var7).locked != MutexKt.access$getUNLOCKED$p()) {
                  var2 = var4;
               } else {
                  var2 = false;
               }

               if (!var2) {
                  throw (Throwable)(new IllegalStateException("Mutex is not locked".toString()));
               }
            } else {
               Empty var10 = (Empty)var7;
               if (var10.locked == var1) {
                  var2 = var3;
               } else {
                  var2 = false;
               }

               if (!var2) {
                  throw (Throwable)(new IllegalStateException(("Mutex is locked by " + var10.locked + " but expected " + var1).toString()));
               }
            }

            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var7, MutexKt.access$getEMPTY_UNLOCKED$p())) {
               return;
            }
         } else if (var7 instanceof OpDescriptor) {
            ((OpDescriptor)var7).perform(this);
         } else {
            if (var7 instanceof LockedQueue) {
               LockedQueue var6;
               if (var1 != null) {
                  var6 = (LockedQueue)var7;
                  if (var6.owner != var1) {
                     var2 = false;
                  }

                  if (!var2) {
                     throw (Throwable)(new IllegalStateException(("Mutex is locked by " + var6.owner + " but expected " + var1).toString()));
                  }
               }

               var6 = (LockedQueue)var7;
               LockFreeLinkedListNode var8 = var6.removeFirstOrNull();
               if (var8 == null) {
                  UnlockOp var9 = new UnlockOp(var6);
                  if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var7, var9) || var9.perform(this) != null) {
                     continue;
                  }

                  return;
               }

               LockWaiter var11 = (LockWaiter)var8;
               var7 = var11.tryResumeLockWaiter();
               if (var7 == null) {
                  continue;
               }

               var1 = var11.owner;
               if (var1 == null) {
                  var1 = MutexKt.access$getLOCKED$p();
               }

               var6.owner = var1;
               var11.completeResumeLockWaiter(var7);
               return;
            }

            throw (Throwable)(new IllegalStateException(("Illegal state " + var7).toString()));
         }
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u001d\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"},
      d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockCont;", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeLockWaiter", "token", "toString", "", "tryResumeLockWaiter", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class LockCont extends LockWaiter {
      public final CancellableContinuation cont;
      final MutexImpl this$0;

      public LockCont(MutexImpl var1, Object var2, CancellableContinuation var3) {
         super(var1, var2);
         this.this$0 = var1;
         this.cont = var3;
      }

      public void completeResumeLockWaiter(Object var1) {
         this.cont.completeResume(var1);
      }

      public String toString() {
         return "LockCont[" + this.owner + ", " + this.cont + "] for " + this.this$0;
      }

      public Object tryResumeLockWaiter() {
         return this.cont.tryResume(Unit.INSTANCE, (Object)null, (Function1)(new Function1(this) {
            final LockCont this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(Throwable var1) {
               this.this$0.this$0.unlock(this.this$0.owner);
            }
         }));
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002R\u00020\u0003BD\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\tø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u0016R1\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"},
      d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockSelect;", "R", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "completeResumeLockWaiter", "", "token", "toString", "", "tryResumeLockWaiter", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class LockSelect extends LockWaiter {
      public final Function2 block;
      public final SelectInstance select;
      final MutexImpl this$0;

      public LockSelect(MutexImpl var1, Object var2, SelectInstance var3, Function2 var4) {
         super(var1, var2);
         this.this$0 = var1;
         this.select = var3;
         this.block = var4;
      }

      public void completeResumeLockWaiter(Object var1) {
         if (DebugKt.getASSERTIONS_ENABLED()) {
            boolean var2;
            if (var1 == MutexKt.access$getSELECT_SUCCESS$p()) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (!var2) {
               throw (Throwable)(new AssertionError());
            }
         }

         CancellableKt.startCoroutineCancellable(this.block, this.this$0, this.select.getCompletion(), (Function1)(new Function1(this) {
            final LockSelect this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke(Throwable var1) {
               this.this$0.this$0.unlock(this.this$0.owner);
            }
         }));
      }

      public String toString() {
         return "LockSelect[" + this.owner + ", " + this.select + "] for " + this.this$0;
      }

      public Object tryResumeLockWaiter() {
         Symbol var1;
         if (this.select.trySelect()) {
            var1 = MutexKt.access$getSELECT_SUCCESS$p();
         } else {
            var1 = null;
         }

         return var1;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H&J\u0006\u0010\t\u001a\u00020\u0007J\n\u0010\n\u001a\u0004\u0018\u00010\u0004H&R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
      d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/DisposableHandle;", "owner", "", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;)V", "completeResumeLockWaiter", "", "token", "dispose", "tryResumeLockWaiter", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {
      public final Object owner;
      final MutexImpl this$0;

      public LockWaiter(MutexImpl var1, Object var2) {
         this.this$0 = var1;
         this.owner = var2;
      }

      public abstract void completeResumeLockWaiter(Object var1);

      public final void dispose() {
         this.remove();
      }

      public abstract Object tryResumeLockWaiter();
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u0012\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"},
      d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "owner", "", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class LockedQueue extends LockFreeLinkedListHead {
      public Object owner;

      public LockedQueue(Object var1) {
         this.owner = var1;
      }

      public String toString() {
         return "LockedQueue[" + this.owner + ']';
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010\f\u001a\u0004\u0018\u00010\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"},
      d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;", "Lkotlinx/coroutines/internal/AtomicDesc;", "mutex", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;)V", "complete", "", "op", "Lkotlinx/coroutines/internal/AtomicOp;", "failure", "prepare", "PrepareOp", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class TryLockDesc extends AtomicDesc {
      public final MutexImpl mutex;
      public final Object owner;

      public TryLockDesc(MutexImpl var1, Object var2) {
         this.mutex = var1;
         this.owner = var2;
      }

      public void complete(AtomicOp var1, Object var2) {
         Empty var4;
         if (var2 != null) {
            var4 = MutexKt.access$getEMPTY_UNLOCKED$p();
         } else if (this.owner == null) {
            var4 = MutexKt.access$getEMPTY_LOCKED$p();
         } else {
            var4 = new Empty(this.owner);
         }

         MutexImpl var3 = this.mutex;
         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl._state$FU, var3, var1, var4);
      }

      public Object prepare(AtomicOp var1) {
         PrepareOp var2 = new PrepareOp(this, var1);
         MutexImpl var3 = this.mutex;
         return !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl._state$FU, var3, MutexKt.access$getEMPTY_UNLOCKED$p(), var2) ? MutexKt.access$getLOCK_FAIL$p() : var2.perform(this.mutex);
      }

      @Metadata(
         bv = {1, 0, 3},
         d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"},
         d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc$PrepareOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "(Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;Lkotlinx/coroutines/internal/AtomicOp;)V", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "perform", "", "affected", "kotlinx-coroutines-core"},
         k = 1,
         mv = {1, 4, 0}
      )
      private final class PrepareOp extends OpDescriptor {
         private final AtomicOp atomicOp;
         final TryLockDesc this$0;

         public PrepareOp(TryLockDesc var1, AtomicOp var2) {
            this.this$0 = var1;
            this.atomicOp = var2;
         }

         public AtomicOp getAtomicOp() {
            return this.atomicOp;
         }

         public Object perform(Object var1) {
            Object var2;
            if (this.getAtomicOp().isDecided()) {
               var2 = MutexKt.access$getEMPTY_UNLOCKED$p();
            } else {
               var2 = this.getAtomicOp();
            }

            if (var1 != null) {
               MutexImpl var3 = (MutexImpl)var1;
               AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl._state$FU, var3, this, var2);
               return null;
            } else {
               throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
            }
         }
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\b\u001a\u00020\u0002H\u0016R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"},
      d2 = {"Lkotlinx/coroutines/sync/MutexImpl$UnlockOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "Lkotlinx/coroutines/sync/MutexImpl;", "queue", "Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "(Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;)V", "complete", "", "affected", "failure", "", "prepare", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class UnlockOp extends AtomicOp {
      public final LockedQueue queue;

      public UnlockOp(LockedQueue var1) {
         this.queue = var1;
      }

      public void complete(MutexImpl var1, Object var2) {
         if (var2 == null) {
            var2 = MutexKt.access$getEMPTY_UNLOCKED$p();
         } else {
            var2 = this.queue;
         }

         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl._state$FU, var1, this, var2);
      }

      public Object prepare(MutexImpl var1) {
         Symbol var2;
         if (this.queue.isEmpty()) {
            var2 = null;
         } else {
            var2 = MutexKt.access$getUNLOCK_FAIL$p();
         }

         return var2;
      }
   }
}
