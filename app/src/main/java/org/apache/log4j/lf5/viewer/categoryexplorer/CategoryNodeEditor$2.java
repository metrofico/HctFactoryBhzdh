package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class CategoryNodeEditor$2 extends MouseAdapter {
   private final CategoryNodeEditor this$0;

   CategoryNodeEditor$2(CategoryNodeEditor var1) {
      this.this$0 = var1;
   }

   public void mousePressed(MouseEvent var1) {
      if ((var1.getModifiers() & 4) != 0) {
         CategoryNodeEditor var2 = this.this$0;
         var2.showPopup(var2._lastEditedNode, var1.getX(), var1.getY());
      }

      this.this$0.stopCellEditing();
   }
}
