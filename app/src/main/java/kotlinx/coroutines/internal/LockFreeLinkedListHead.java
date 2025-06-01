package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0007\u001a\u00020\b\"\u000e\b\u0000\u0010\t\u0018\u0001*\u00060\u0001j\u0002`\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\b0\fH\u0086\bJ\u0010\u0010\r\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\nH\u0014J\u0006\u0010\u000e\u001a\u00020\u0004J\r\u0010\u000f\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0010R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005¨\u0006\u0011"},
   d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "()V", "isEmpty", "", "()Z", "isRemoved", "forEach", "", "T", "Lkotlinx/coroutines/internal/Node;", "block", "Lkotlin/Function1;", "nextIfRemoved", "remove", "validate", "validate$kotlinx_coroutines_core", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
   // $FF: synthetic method
   public final void forEach(Function1 var1) {
      Object var2 = this.getNext();
      if (var2 != null) {
         LockFreeLinkedListNode var4 = (LockFreeLinkedListNode)var2;

         while(true) {
            LockFreeLinkedListHead var3 = (LockFreeLinkedListHead)this;
            if (!(Intrinsics.areEqual((Object)var4, (Object)this) ^ true)) {
               return;
            }

            Intrinsics.reifiedOperationMarker(3, "T");
            if (var4 instanceof LockFreeLinkedListNode) {
               var1.invoke(var4);
            }

            var4 = var4.getNextNode();
         }
      } else {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      }
   }

   public final boolean isEmpty() {
      Object var2 = this.getNext();
      LockFreeLinkedListHead var3 = (LockFreeLinkedListHead)this;
      boolean var1;
      if (var2 == this) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isRemoved() {
      return false;
   }

   protected LockFreeLinkedListNode nextIfRemoved() {
      return null;
   }

   public final boolean remove() {
      throw (Throwable)(new IllegalStateException("head cannot be removed".toString()));
   }

   public final void validate$kotlinx_coroutines_core() {
      LockFreeLinkedListNode var2 = (LockFreeLinkedListNode)this;
      Object var1 = this.getNext();
      if (var1 == null) {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      } else {
         LockFreeLinkedListNode var4 = (LockFreeLinkedListNode)var1;

         while(true) {
            LockFreeLinkedListHead var3 = (LockFreeLinkedListHead)this;
            if (!(Intrinsics.areEqual((Object)var4, (Object)this) ^ true)) {
               var1 = this.getNext();
               if (var1 != null) {
                  this.validateNode$kotlinx_coroutines_core(var2, (LockFreeLinkedListNode)var1);
                  return;
               } else {
                  throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
               }
            }

            LockFreeLinkedListNode var5 = var4.getNextNode();
            var4.validateNode$kotlinx_coroutines_core(var2, var5);
            var2 = var4;
            var4 = var5;
         }
      }
   }
}
