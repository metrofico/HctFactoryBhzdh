package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\f"},
   d2 = {"Lkotlinx/coroutines/Empty;", "Lkotlinx/coroutines/Incomplete;", "isActive", "", "(Z)V", "()Z", "list", "Lkotlinx/coroutines/NodeList;", "getList", "()Lkotlinx/coroutines/NodeList;", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class Empty implements Incomplete {
   private final boolean isActive;

   public Empty(boolean var1) {
      this.isActive = var1;
   }

   public NodeList getList() {
      return null;
   }

   public boolean isActive() {
      return this.isActive;
   }

   public String toString() {
      StringBuilder var2 = (new StringBuilder()).append("Empty{");
      String var1;
      if (this.isActive()) {
         var1 = "Active";
      } else {
         var1 = "New";
      }

      return var2.append(var1).append('}').toString();
   }
}
