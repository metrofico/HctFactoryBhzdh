package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CategoryNodeEditor$1 implements ActionListener {
   private final CategoryNodeEditor this$0;

   CategoryNodeEditor$1(CategoryNodeEditor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._categoryModel.update(this.this$0._lastEditedNode, this.this$0._checkBox.isSelected());
      this.this$0.stopCellEditing();
   }
}
