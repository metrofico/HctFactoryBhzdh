package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\b\u0010\r\u001a\u00020\u000bH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"},
   d2 = {"Lkotlinx/coroutines/NodeList;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/Incomplete;", "()V", "isActive", "", "()Z", "list", "getList", "()Lkotlinx/coroutines/NodeList;", "getString", "", "state", "toString", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class NodeList extends LockFreeLinkedListHead implements Incomplete {
   public NodeList getList() {
      return this;
   }

   public final String getString(String var1) {
      StringBuilder var4 = new StringBuilder();
      var4.append("List{");
      var4.append(var1);
      var4.append("}[");
      LockFreeLinkedListHead var5 = (LockFreeLinkedListHead)this;
      Object var7 = var5.getNext();
      if (var7 != null) {
         LockFreeLinkedListNode var8 = (LockFreeLinkedListNode)var7;

         boolean var3;
         for(boolean var2 = true; Intrinsics.areEqual((Object)var8, (Object)var5) ^ true; var2 = var3) {
            var3 = var2;
            if (var8 instanceof JobNode) {
               JobNode var6 = (JobNode)var8;
               if (var2) {
                  var2 = false;
               } else {
                  var4.append(", ");
               }

               var4.append(var6);
               var3 = var2;
            }

            var8 = var8.getNextNode();
         }

         var4.append("]");
         var1 = var4.toString();
         Intrinsics.checkNotNullExpressionValue(var1, "StringBuilder().apply(builderAction).toString()");
         return var1;
      } else {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      }
   }

   public boolean isActive() {
      return true;
   }

   public String toString() {
      String var1;
      if (DebugKt.getDEBUG()) {
         var1 = this.getString("Active");
      } else {
         var1 = super.toString();
      }

      return var1;
   }
}
