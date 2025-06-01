package org.apache.log4j.lf5.viewer.categoryexplorer;

class CategoryExplorerModel$1 implements Runnable {
   private final CategoryExplorerModel this$0;
   private final CategoryNode val$node;

   CategoryExplorerModel$1(CategoryExplorerModel var1, CategoryNode var2) {
      this.this$0 = var1;
      this.val$node = var2;
   }

   public void run() {
      this.this$0.nodeChanged(this.val$node);
   }
}
