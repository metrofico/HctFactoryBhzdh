package org.apache.log4j.lf5.viewer.categoryexplorer;

import javax.swing.event.TreeModelEvent;

class CategoryExplorerTree$1 extends TreeModelAdapter {
   private final CategoryExplorerTree this$0;

   CategoryExplorerTree$1(CategoryExplorerTree var1) {
      this.this$0 = var1;
   }

   public void treeNodesInserted(TreeModelEvent var1) {
      this.this$0.expandRootNode();
   }
}
