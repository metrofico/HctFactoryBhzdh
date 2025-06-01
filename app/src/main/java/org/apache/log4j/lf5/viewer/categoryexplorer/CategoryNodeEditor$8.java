package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CategoryNodeEditor$8 implements ActionListener {
   private final CategoryNodeEditor this$0;

   CategoryNodeEditor$8(CategoryNodeEditor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      while(this.this$0.removeUnusedNodes() > 0) {
      }

   }
}
