package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\f\b\u0017\u0018\u00002\u00020C:\u0005JKLMNB\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u0019\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u00060\u0000j\u0002`\u0003¢\u0006\u0004\b\u0006\u0010\u0007J,\u0010\u000b\u001a\u00020\t2\n\u0010\u0004\u001a\u00060\u0000j\u0002`\u00032\u000e\b\u0004\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086\b¢\u0006\u0004\b\u000b\u0010\fJ4\u0010\u000f\u001a\u00020\t2\n\u0010\u0004\u001a\u00060\u0000j\u0002`\u00032\u0016\u0010\u000e\u001a\u0012\u0012\b\u0012\u00060\u0000j\u0002`\u0003\u0012\u0004\u0012\u00020\t0\rH\u0086\b¢\u0006\u0004\b\u000f\u0010\u0010JD\u0010\u0011\u001a\u00020\t2\n\u0010\u0004\u001a\u00060\u0000j\u0002`\u00032\u0016\u0010\u000e\u001a\u0012\u0012\b\u0012\u00060\u0000j\u0002`\u0003\u0012\u0004\u0012\u00020\t0\r2\u000e\b\u0004\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086\b¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0014\u001a\u00020\t2\n\u0010\u0004\u001a\u00060\u0000j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0000j\u0002`\u0003H\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\t2\n\u0010\u0004\u001a\u00060\u0000j\u0002`\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\"\u0010\u001a\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0082\u0010¢\u0006\u0004\b\u001a\u0010\u001bJ)\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d\"\f\b\u0000\u0010\u001c*\u00060\u0000j\u0002`\u00032\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\f\u0012\b\u0012\u00060\u0000j\u0002`\u00030 ¢\u0006\u0004\b!\u0010\"J \u0010$\u001a\u00060\u0000j\u0002`\u00032\n\u0010#\u001a\u00060\u0000j\u0002`\u0003H\u0082\u0010¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00052\n\u0010\u0013\u001a\u00060\u0000j\u0002`\u0003H\u0002¢\u0006\u0004\b&\u0010\u0007J\r\u0010'\u001a\u00020\u0005¢\u0006\u0004\b'\u0010\u0002J\u000f\u0010(\u001a\u00020\u0005H\u0001¢\u0006\u0004\b(\u0010\u0002J,\u0010*\u001a\u00020)2\n\u0010\u0004\u001a\u00060\u0000j\u0002`\u00032\u000e\b\u0004\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0081\b¢\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u0003H\u0014¢\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\tH\u0016¢\u0006\u0004\b.\u0010/J.\u00100\u001a\u0004\u0018\u00018\u0000\"\u0006\b\u0000\u0010\u001c\u0018\u00012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\rH\u0086\b¢\u0006\u0004\b0\u00101J\u0015\u00102\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u0003¢\u0006\u0004\b2\u0010-J\u0017\u00103\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u0003H\u0001¢\u0006\u0004\b3\u0010-J\u000f\u00105\u001a\u000204H\u0002¢\u0006\u0004\b5\u00106J\u000f\u00108\u001a\u000207H\u0016¢\u0006\u0004\b8\u00109J/\u0010<\u001a\u00020;2\n\u0010\u0004\u001a\u00060\u0000j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0000j\u0002`\u00032\u0006\u0010:\u001a\u00020)H\u0001¢\u0006\u0004\b<\u0010=J'\u0010A\u001a\u00020\u00052\n\u0010>\u001a\u00060\u0000j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0000j\u0002`\u0003H\u0000¢\u0006\u0004\b?\u0010@R\u0016\u0010B\u001a\u00020\t8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010/R\u0013\u0010\u0013\u001a\u00020C8F@\u0006¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0017\u0010G\u001a\u00060\u0000j\u0002`\u00038F@\u0006¢\u0006\u0006\u001a\u0004\bF\u0010-R\u0017\u0010I\u001a\u00060\u0000j\u0002`\u00038F@\u0006¢\u0006\u0006\u001a\u0004\bH\u0010-¨\u0006O"},
   d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "<init>", "()V", "Lkotlinx/coroutines/internal/Node;", "node", "", "addLast", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "Lkotlin/Function0;", "", "condition", "addLastIf", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlin/jvm/functions/Function0;)Z", "Lkotlin/Function1;", "predicate", "addLastIfPrev", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlin/jvm/functions/Function1;)Z", "addLastIfPrevAndIf", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)Z", "next", "addNext", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Z", "addOneIfEmpty", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Z", "Lkotlinx/coroutines/internal/OpDescriptor;", "op", "correctPrev", "(Lkotlinx/coroutines/internal/OpDescriptor;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "T", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "describeAddLast", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "describeRemoveFirst", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "current", "findPrevNonRemoved", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "finishAdd", "helpRemove", "helpRemovePrev", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "makeCondAddOp", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "nextIfRemoved", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "remove", "()Z", "removeFirstIfIsInstanceOfOrPeekIf", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "removeFirstOrNull", "removeOrNext", "Lkotlinx/coroutines/internal/Removed;", "removed", "()Lkotlinx/coroutines/internal/Removed;", "", "toString", "()Ljava/lang/String;", "condAdd", "", "tryCondAddNext", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;)I", "prev", "validateNode$kotlinx_coroutines_core", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "validateNode", "isRemoved", "", "getNext", "()Ljava/lang/Object;", "getNextNode", "nextNode", "getPrevNode", "prevNode", "AbstractAtomicDesc", "AddLastDesc", "CondAddOp", "PrepareOp", "RemoveFirstDesc", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class LockFreeLinkedListNode {
   static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next");
   static final AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev");
   private static final AtomicReferenceFieldUpdater _removedRef$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef");
   volatile Object _next = this;
   volatile Object _prev = this;
   private volatile Object _removedRef = null;

   private final LockFreeLinkedListNode correctPrev(OpDescriptor var1) {
      label59:
      while(true) {
         LockFreeLinkedListNode var4 = (LockFreeLinkedListNode)this._prev;
         LockFreeLinkedListNode var2 = (LockFreeLinkedListNode)null;
         var2 = var4;

         while(true) {
            LockFreeLinkedListNode var3 = null;

            while(true) {
               Object var6 = var2._next;
               LockFreeLinkedListNode var5 = (LockFreeLinkedListNode)this;
               if (var6 == this) {
                  if (var4 == var2) {
                     return var2;
                  }

                  if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_prev$FU, this, var4, var2)) {
                     continue label59;
                  }

                  return var2;
               }

               if (this.isRemoved()) {
                  return null;
               }

               if (var6 == var1) {
                  return var2;
               }

               if (var6 instanceof OpDescriptor) {
                  if (var1 != null && var1.isEarlierThan((OpDescriptor)var6)) {
                     return null;
                  }

                  ((OpDescriptor)var6).perform(var2);
                  continue label59;
               }

               if (var6 instanceof Removed) {
                  if (var3 != null) {
                     if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, var3, var2, ((Removed)var6).ref)) {
                        continue label59;
                     }

                     var2 = var3;
                     break;
                  }

                  var2 = (LockFreeLinkedListNode)var2._prev;
               } else {
                  if (var6 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                  }

                  var5 = (LockFreeLinkedListNode)var6;
                  var3 = var2;
                  var2 = var5;
               }
            }
         }
      }
   }

   private final LockFreeLinkedListNode findPrevNonRemoved(LockFreeLinkedListNode var1) {
      while(var1.isRemoved()) {
         var1 = (LockFreeLinkedListNode)var1._prev;
      }

      return var1;
   }

   private final void finishAdd(LockFreeLinkedListNode var1) {
      LockFreeLinkedListNode var2;
      do {
         var2 = (LockFreeLinkedListNode)var1._prev;
         if (this.getNext() != var1) {
            return;
         }
      } while(!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_prev$FU, var1, var2, this));

      if (this.isRemoved()) {
         var1.correctPrev((OpDescriptor)null);
      }

   }

   private final Removed removed() {
      Removed var1 = (Removed)this._removedRef;
      if (var1 == null) {
         var1 = new Removed(this);
         _removedRef$FU.lazySet(this, var1);
      }

      return var1;
   }

   public final void addLast(LockFreeLinkedListNode var1) {
      while(!this.getPrevNode().addNext(var1, this)) {
      }

   }

   public final boolean addLastIf(LockFreeLinkedListNode var1, Function0 var2) {
      CondAddOp var4 = (CondAddOp)(new CondAddOp(var2, var1, var1) {
         final Function0 $condition;
         final LockFreeLinkedListNode $node;

         public {
            this.$condition = var1;
            this.$node = var2;
         }

         public Object prepare(LockFreeLinkedListNode var1) {
            Object var2;
            if ((Boolean)this.$condition.invoke()) {
               var2 = null;
            } else {
               var2 = LockFreeLinkedListKt.getCONDITION_FALSE();
            }

            return var2;
         }
      });

      int var3;
      do {
         var3 = this.getPrevNode().tryCondAddNext(var1, this, var4);
         if (var3 == 1) {
            return true;
         }
      } while(var3 != 2);

      return false;
   }

   public final boolean addLastIfPrev(LockFreeLinkedListNode var1, Function1 var2) {
      LockFreeLinkedListNode var3;
      do {
         var3 = this.getPrevNode();
         if (!(Boolean)var2.invoke(var3)) {
            return false;
         }
      } while(!var3.addNext(var1, this));

      return true;
   }

   public final boolean addLastIfPrevAndIf(LockFreeLinkedListNode var1, Function1 var2, Function0 var3) {
      CondAddOp var5 = (CondAddOp)(new CondAddOp(var3, var1, var1) {
         final Function0 $condition;
         final LockFreeLinkedListNode $node;

         public {
            this.$condition = var1;
            this.$node = var2;
         }

         public Object prepare(LockFreeLinkedListNode var1) {
            Object var2;
            if ((Boolean)this.$condition.invoke()) {
               var2 = null;
            } else {
               var2 = LockFreeLinkedListKt.getCONDITION_FALSE();
            }

            return var2;
         }
      });

      int var4;
      do {
         LockFreeLinkedListNode var6 = this.getPrevNode();
         if (!(Boolean)var2.invoke(var6)) {
            return false;
         }

         var4 = var6.tryCondAddNext(var1, this, var5);
         if (var4 == 1) {
            return true;
         }
      } while(var4 != 2);

      return false;
   }

   public final boolean addNext(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2) {
      _prev$FU.lazySet(var1, this);
      AtomicReferenceFieldUpdater var3 = _next$FU;
      var3.lazySet(var1, var2);
      if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(var3, this, var2, var1)) {
         return false;
      } else {
         var1.finishAdd(var2);
         return true;
      }
   }

   public final boolean addOneIfEmpty(LockFreeLinkedListNode var1) {
      _prev$FU.lazySet(var1, this);
      _next$FU.lazySet(var1, this);

      do {
         Object var3 = this.getNext();
         LockFreeLinkedListNode var2 = (LockFreeLinkedListNode)this;
         if (var3 != this) {
            return false;
         }
      } while(!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, this, var1));

      var1.finishAdd(this);
      return true;
   }

   public final AddLastDesc describeAddLast(LockFreeLinkedListNode var1) {
      return new AddLastDesc(this, var1);
   }

   public final RemoveFirstDesc describeRemoveFirst() {
      return new RemoveFirstDesc(this);
   }

   public final Object getNext() {
      while(true) {
         Object var1 = this._next;
         if (!(var1 instanceof OpDescriptor)) {
            return var1;
         }

         ((OpDescriptor)var1).perform(this);
      }
   }

   public final LockFreeLinkedListNode getNextNode() {
      return LockFreeLinkedListKt.unwrap(this.getNext());
   }

   public final LockFreeLinkedListNode getPrevNode() {
      LockFreeLinkedListNode var1 = this.correctPrev((OpDescriptor)null);
      if (var1 == null) {
         var1 = this.findPrevNonRemoved((LockFreeLinkedListNode)this._prev);
      }

      return var1;
   }

   public final void helpRemove() {
      Object var1 = this.getNext();
      if (var1 != null) {
         ((Removed)var1).ref.correctPrev((OpDescriptor)null);
      } else {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Removed");
      }
   }

   public final void helpRemovePrev() {
      LockFreeLinkedListNode var1 = (LockFreeLinkedListNode)this;
      var1 = this;

      while(true) {
         Object var2 = var1.getNext();
         if (!(var2 instanceof Removed)) {
            var1.correctPrev((OpDescriptor)null);
            return;
         }

         var1 = ((Removed)var2).ref;
      }
   }

   public boolean isRemoved() {
      return this.getNext() instanceof Removed;
   }

   public final CondAddOp makeCondAddOp(LockFreeLinkedListNode var1, Function0 var2) {
      return (CondAddOp)(new CondAddOp(var2, var1, var1) {
         final Function0 $condition;
         final LockFreeLinkedListNode $node;

         public {
            this.$condition = var1;
            this.$node = var2;
         }

         public Object prepare(LockFreeLinkedListNode var1) {
            Object var2;
            if ((Boolean)this.$condition.invoke()) {
               var2 = null;
            } else {
               var2 = LockFreeLinkedListKt.getCONDITION_FALSE();
            }

            return var2;
         }
      });
   }

   protected LockFreeLinkedListNode nextIfRemoved() {
      Object var2 = this.getNext();
      boolean var1 = var2 instanceof Removed;
      Object var3 = null;
      if (!var1) {
         var2 = null;
      }

      Removed var4 = (Removed)var2;
      LockFreeLinkedListNode var5 = (LockFreeLinkedListNode)var3;
      if (var4 != null) {
         var5 = var4.ref;
      }

      return var5;
   }

   public boolean remove() {
      boolean var1;
      if (this.removeOrNext() == null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public final Object removeFirstIfIsInstanceOfOrPeekIf(Function1 var1) {
      while(true) {
         Object var2 = this.getNext();
         if (var2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
         }

         LockFreeLinkedListNode var4 = (LockFreeLinkedListNode)var2;
         LockFreeLinkedListNode var3 = (LockFreeLinkedListNode)this;
         if (var4 == this) {
            return null;
         }

         Intrinsics.reifiedOperationMarker(3, "T");
         if (!(var4 instanceof Object)) {
            return null;
         }

         if ((Boolean)var1.invoke(var4) && !var4.isRemoved()) {
            return var4;
         }

         var3 = var4.removeOrNext();
         if (var3 == null) {
            return var4;
         }

         var3.helpRemovePrev();
      }
   }

   public final LockFreeLinkedListNode removeFirstOrNull() {
      while(true) {
         Object var1 = this.getNext();
         if (var1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
         }

         LockFreeLinkedListNode var3 = (LockFreeLinkedListNode)var1;
         LockFreeLinkedListNode var2 = (LockFreeLinkedListNode)this;
         if (var3 == this) {
            return null;
         }

         if (var3.remove()) {
            return var3;
         }

         var3.helpRemove();
      }
   }

   public final LockFreeLinkedListNode removeOrNext() {
      while(true) {
         Object var1 = this.getNext();
         if (var1 instanceof Removed) {
            return ((Removed)var1).ref;
         }

         LockFreeLinkedListNode var2 = (LockFreeLinkedListNode)this;
         if (var1 == this) {
            return (LockFreeLinkedListNode)var1;
         }

         if (var1 != null) {
            var2 = (LockFreeLinkedListNode)var1;
            Removed var3 = var2.removed();
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, var1, var3)) {
               continue;
            }

            var2.correctPrev((OpDescriptor)null);
            return null;
         }

         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      }
   }

   public String toString() {
      return this.getClass().getSimpleName() + '@' + Integer.toHexString(System.identityHashCode(this));
   }

   public final int tryCondAddNext(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2, CondAddOp var3) {
      _prev$FU.lazySet(var1, this);
      AtomicReferenceFieldUpdater var5 = _next$FU;
      var5.lazySet(var1, var2);
      var3.oldNext = var2;
      if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(var5, this, var2, var3)) {
         return 0;
      } else {
         byte var4;
         if (var3.perform(this) == null) {
            var4 = 1;
         } else {
            var4 = 2;
         }

         return var4;
      }
   }

   public final void validateNode$kotlinx_coroutines_core(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2) {
      boolean var5 = DebugKt.getASSERTIONS_ENABLED();
      boolean var4 = true;
      boolean var3;
      if (var5) {
         if (var1 == (LockFreeLinkedListNode)this._prev) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (!var3) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (DebugKt.getASSERTIONS_ENABLED()) {
         if (var2 == this._next) {
            var3 = var4;
         } else {
            var3 = false;
         }

         if (!var3) {
            throw (Throwable)(new AssertionError());
         }
      }

   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u0005H\u0014J \u0010\u0011\u001a\u00020\u000b2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0012\u001a\u00060\u0004j\u0002`\u0005H$J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0014\u0010\u0017\u001a\u00020\u000b2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u0005H\u0016J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rJ\u001c\u0010\u0019\u001a\u00020\u001a2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0012\u001a\u00020\u000fH\u0014J\u0018\u0010\u001b\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00052\u0006\u0010\f\u001a\u00020\u001cH\u0014J \u0010\u001d\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0012\u001a\u00060\u0004j\u0002`\u0005H&R\u001a\u0010\u0003\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u001e"},
      d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "Lkotlinx/coroutines/internal/AtomicDesc;", "()V", "affectedNode", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "complete", "", "op", "Lkotlinx/coroutines/internal/AtomicOp;", "failure", "", "affected", "finishOnSuccess", "next", "finishPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "onPrepare", "onRemoved", "prepare", "retry", "", "takeAffectedNode", "Lkotlinx/coroutines/internal/OpDescriptor;", "updatedNext", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public abstract static class AbstractAtomicDesc extends AtomicDesc {
      public final void complete(AtomicOp var1, Object var2) {
         boolean var3;
         if (var2 == null) {
            var3 = true;
         } else {
            var3 = false;
         }

         LockFreeLinkedListNode var5 = this.getAffectedNode();
         AbstractAtomicDesc var6;
         if (var5 != null) {
            LockFreeLinkedListNode var4 = this.getOriginalNext();
            if (var4 != null) {
               if (var3) {
                  var2 = this.updatedNext(var5, var4);
               } else {
                  var2 = var4;
               }

               if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, var5, var1, var2) && var3) {
                  this.finishOnSuccess(var5, var4);
               }

            } else {
               var6 = (AbstractAtomicDesc)this;
               if (DebugKt.getASSERTIONS_ENABLED() && !(var3 ^ true)) {
                  throw (Throwable)(new AssertionError());
               }
            }
         } else {
            var6 = (AbstractAtomicDesc)this;
            if (DebugKt.getASSERTIONS_ENABLED() && !(var3 ^ true)) {
               throw (Throwable)(new AssertionError());
            }
         }
      }

      protected Object failure(LockFreeLinkedListNode var1) {
         return null;
      }

      protected abstract void finishOnSuccess(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2);

      public abstract void finishPrepare(PrepareOp var1);

      protected abstract LockFreeLinkedListNode getAffectedNode();

      protected abstract LockFreeLinkedListNode getOriginalNext();

      public Object onPrepare(PrepareOp var1) {
         this.finishPrepare(var1);
         return null;
      }

      public void onRemoved(LockFreeLinkedListNode var1) {
      }

      public final Object prepare(AtomicOp var1) {
         while(true) {
            LockFreeLinkedListNode var3 = this.takeAffectedNode((OpDescriptor)var1);
            if (var3 != null) {
               Object var4 = var3._next;
               if (var4 == var1) {
                  return null;
               }

               if (var1.isDecided()) {
                  return null;
               }

               if (var4 instanceof OpDescriptor) {
                  OpDescriptor var21 = (OpDescriptor)var4;
                  if (var1.isEarlierThan(var21)) {
                     return AtomicKt.RETRY_ATOMIC;
                  }

                  var21.perform(var3);
                  continue;
               }

               Object var5 = this.failure(var3);
               if (var5 != null) {
                  return var5;
               }

               if (this.retry(var3, var4)) {
                  continue;
               }

               if (var4 != null) {
                  PrepareOp var22 = new PrepareOp(var3, (LockFreeLinkedListNode)var4, this);
                  if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, var3, var4, var22)) {
                     continue;
                  }

                  Throwable var10000;
                  label290: {
                     Object var6;
                     boolean var10001;
                     try {
                        var6 = var22.perform(var3);
                        if (var6 == LockFreeLinkedList_commonKt.REMOVE_PREPARED) {
                           continue;
                        }
                     } catch (Throwable var17) {
                        var10000 = var17;
                        var10001 = false;
                        break label290;
                     }

                     try {
                        if (!DebugKt.getASSERTIONS_ENABLED()) {
                           return null;
                        }
                     } catch (Throwable var18) {
                        var10000 = var18;
                        var10001 = false;
                        break label290;
                     }

                     boolean var2;
                     if (var6 == null) {
                        var2 = true;
                     } else {
                        var2 = false;
                     }

                     if (var2) {
                        return null;
                     }

                     try {
                        AssertionError var20 = new AssertionError();
                        throw (Throwable)var20;
                     } catch (Throwable var16) {
                        var10000 = var16;
                        var10001 = false;
                        break label290;
                     }
                  }

                  Throwable var19 = var10000;
                  AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, var3, var22, var4);
                  throw var19;
               }

               throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }

            return AtomicKt.RETRY_ATOMIC;
         }
      }

      protected boolean retry(LockFreeLinkedListNode var1, Object var2) {
         return false;
      }

      protected LockFreeLinkedListNode takeAffectedNode(OpDescriptor var1) {
         LockFreeLinkedListNode var2 = this.getAffectedNode();
         Intrinsics.checkNotNull(var2);
         return var2;
      }

      public abstract Object updatedNext(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2);
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\b\u0016\u0018\u0000*\f\b\u0000\u0010\u0003*\u00060\u0001j\u0002`\u00022\u00020!B\u001b\u0012\n\u0010\u0004\u001a\u00060\u0001j\u0002`\u0002\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u000b\u001a\u00020\n2\n\u0010\b\u001a\u00060\u0001j\u0002`\u00022\n\u0010\t\u001a\u00060\u0001j\u0002`\u0002H\u0014¢\u0006\u0004\b\u000b\u0010\u0007J\u0017\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0012\u001a\u00020\u00112\n\u0010\b\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\t\u001a\u00020\u0010H\u0014¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0016\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u00022\u0006\u0010\u0015\u001a\u00020\u0014H\u0004¢\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u0018\u001a\u00020\u00102\n\u0010\b\u001a\u00060\u0001j\u0002`\u00022\n\u0010\t\u001a\u00060\u0001j\u0002`\u0002H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001c\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u00028D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0005\u001a\u00028\u00008\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\u001e\u0010\u001f\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u00028D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001bR\u001a\u0010\u0004\u001a\u00060\u0001j\u0002`\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001d¨\u0006 "},
      d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "T", "queue", "node", "<init>", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "affected", "next", "", "finishOnSuccess", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "prepareOp", "finishPrepare", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)V", "", "", "retry", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/internal/OpDescriptor;", "op", "takeAffectedNode", "(Lkotlinx/coroutines/internal/OpDescriptor;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "updatedNext", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "affectedNode", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "getOriginalNext", "originalNext", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static class AddLastDesc extends AbstractAtomicDesc {
      private static final AtomicReferenceFieldUpdater _affectedNode$FU = AtomicReferenceFieldUpdater.newUpdater(AddLastDesc.class, Object.class, "_affectedNode");
      private volatile Object _affectedNode;
      public final LockFreeLinkedListNode node;
      public final LockFreeLinkedListNode queue;

      public AddLastDesc(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2) {
         this.queue = var1;
         this.node = var2;
         if (DebugKt.getASSERTIONS_ENABLED()) {
            boolean var3;
            if (var2._next == var2 && (LockFreeLinkedListNode)var2._prev == var2) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (!var3) {
               throw (Throwable)(new AssertionError());
            }
         }

         this._affectedNode = null;
      }

      protected void finishOnSuccess(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2) {
         this.node.finishAdd(this.queue);
      }

      public void finishPrepare(PrepareOp var1) {
         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_affectedNode$FU, this, (Object)null, var1.affected);
      }

      protected final LockFreeLinkedListNode getAffectedNode() {
         return (LockFreeLinkedListNode)this._affectedNode;
      }

      protected final LockFreeLinkedListNode getOriginalNext() {
         return this.queue;
      }

      protected boolean retry(LockFreeLinkedListNode var1, Object var2) {
         boolean var3;
         if (var2 != this.queue) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      protected final LockFreeLinkedListNode takeAffectedNode(OpDescriptor var1) {
         return this.queue.correctPrev(var1);
      }

      public Object updatedNext(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2) {
         var2 = this.node;
         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._prev$FU, var2, this.node, var1);
         var1 = this.node;
         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, var1, this.node, this.queue);
         return this.node;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b!\u0018\u00002\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B\u0011\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0005J\u001e\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0002j\u0002`\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0014\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\f"},
      d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "newNode", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "oldNext", "complete", "", "affected", "failure", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public abstract static class CondAddOp extends AtomicOp {
      public final LockFreeLinkedListNode newNode;
      public LockFreeLinkedListNode oldNext;

      public CondAddOp(LockFreeLinkedListNode var1) {
         this.newNode = var1;
      }

      public void complete(LockFreeLinkedListNode var1, Object var2) {
         boolean var3;
         if (var2 == null) {
            var3 = true;
         } else {
            var3 = false;
         }

         LockFreeLinkedListNode var4;
         if (var3) {
            var4 = this.newNode;
         } else {
            var4 = this.oldNext;
         }

         if (var4 != null && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, var1, this, var4) && var3) {
            var1 = this.newNode;
            var4 = this.oldNext;
            Intrinsics.checkNotNull(var4);
            var1.finishAdd(var4);
         }

      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B%\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\n\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0002\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0002\u001a\u00060\u0003j\u0002`\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00060\u0003j\u0002`\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"},
      d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "next", "desc", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;)V", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "finishPrepare", "", "perform", "", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class PrepareOp extends OpDescriptor {
      public final LockFreeLinkedListNode affected;
      public final AbstractAtomicDesc desc;
      public final LockFreeLinkedListNode next;

      public PrepareOp(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2, AbstractAtomicDesc var3) {
         this.affected = var1;
         this.next = var2;
         this.desc = var3;
      }

      public final void finishPrepare() {
         this.desc.finishPrepare(this);
      }

      public AtomicOp getAtomicOp() {
         return this.desc.getAtomicOp();
      }

      public Object perform(Object var1) {
         if (DebugKt.getASSERTIONS_ENABLED()) {
            boolean var2;
            if (var1 == this.affected) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (!var2) {
               throw (Throwable)(new AssertionError());
            }
         }

         if (var1 != null) {
            LockFreeLinkedListNode var3 = (LockFreeLinkedListNode)var1;
            var1 = this.desc.onPrepare(this);
            if (var1 == LockFreeLinkedList_commonKt.REMOVE_PREPARED) {
               LockFreeLinkedListNode var5 = this.next;
               Removed var4 = var5.removed();
               if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, var3, this, var4)) {
                  this.desc.onRemoved(var3);
                  var5.correctPrev((OpDescriptor)null);
               }

               return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            } else {
               if (var1 != null) {
                  var1 = this.getAtomicOp().decide(var1);
               } else {
                  var1 = this.getAtomicOp().getConsensus();
               }

               if (var1 == AtomicKt.NO_DECISION) {
                  var1 = this.getAtomicOp();
               } else if (var1 == null) {
                  var1 = this.desc.updatedNext(var3, this.next);
               } else {
                  var1 = this.next;
               }

               AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, var3, this, var1);
               return null;
            }
         } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
         }
      }

      public String toString() {
         return "PrepareOp(op=" + this.getAtomicOp() + ')';
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020(B\u0013\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\u0004\u0018\u00010\b2\n\u0010\u0007\u001a\u00060\u0002j\u0002`\u0003H\u0014¢\u0006\u0004\b\t\u0010\nJ'\u0010\r\u001a\u00020\f2\n\u0010\u0007\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u000b\u001a\u00060\u0002j\u0002`\u0003H\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0014\u001a\u00020\u00132\n\u0010\u0007\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00032\u0006\u0010\u0017\u001a\u00020\u0016H\u0004¢\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001a\u001a\u00020\b2\n\u0010\u0007\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u000b\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001e\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010 \u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001dR\u001a\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010!R\u0019\u0010&\u001a\u00028\u00008F@\u0006¢\u0006\f\u0012\u0004\b$\u0010%\u001a\u0004\b\"\u0010#¨\u0006'"},
      d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "T", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "queue", "<init>", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "affected", "", "failure", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "next", "", "finishOnSuccess", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "prepareOp", "finishPrepare", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)V", "", "retry", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/internal/OpDescriptor;", "op", "takeAffectedNode", "(Lkotlinx/coroutines/internal/OpDescriptor;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "updatedNext", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "affectedNode", "getOriginalNext", "originalNext", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "getResult", "()Ljava/lang/Object;", "getResult$annotations", "()V", "result", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static class RemoveFirstDesc extends AbstractAtomicDesc {
      private static final AtomicReferenceFieldUpdater _affectedNode$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_affectedNode");
      private static final AtomicReferenceFieldUpdater _originalNext$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_originalNext");
      private volatile Object _affectedNode;
      private volatile Object _originalNext;
      public final LockFreeLinkedListNode queue;

      public RemoveFirstDesc(LockFreeLinkedListNode var1) {
         this.queue = var1;
         this._affectedNode = null;
         this._originalNext = null;
      }

      // $FF: synthetic method
      public static void getResult$annotations() {
      }

      protected Object failure(LockFreeLinkedListNode var1) {
         Object var2;
         if (var1 == this.queue) {
            var2 = LockFreeLinkedListKt.getLIST_EMPTY();
         } else {
            var2 = null;
         }

         return var2;
      }

      protected final void finishOnSuccess(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2) {
         var2.correctPrev((OpDescriptor)null);
      }

      public void finishPrepare(PrepareOp var1) {
         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_affectedNode$FU, this, (Object)null, var1.affected);
         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_originalNext$FU, this, (Object)null, var1.next);
      }

      protected final LockFreeLinkedListNode getAffectedNode() {
         return (LockFreeLinkedListNode)this._affectedNode;
      }

      protected final LockFreeLinkedListNode getOriginalNext() {
         return (LockFreeLinkedListNode)this._originalNext;
      }

      public final Object getResult() {
         LockFreeLinkedListNode var1 = this.getAffectedNode();
         Intrinsics.checkNotNull(var1);
         return (Object)var1;
      }

      protected final boolean retry(LockFreeLinkedListNode var1, Object var2) {
         if (!(var2 instanceof Removed)) {
            return false;
         } else {
            ((Removed)var2).ref.helpRemovePrev();
            return true;
         }
      }

      protected final LockFreeLinkedListNode takeAffectedNode(OpDescriptor var1) {
         LockFreeLinkedListNode var2 = this.queue;

         while(true) {
            Object var3 = var2._next;
            if (!(var3 instanceof OpDescriptor)) {
               if (var3 != null) {
                  return (LockFreeLinkedListNode)var3;
               }

               throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }

            OpDescriptor var4 = (OpDescriptor)var3;
            if (var1.isEarlierThan(var4)) {
               return null;
            }

            var4.perform(this.queue);
         }
      }

      public final Object updatedNext(LockFreeLinkedListNode var1, LockFreeLinkedListNode var2) {
         return var2.removed();
      }
   }
}
