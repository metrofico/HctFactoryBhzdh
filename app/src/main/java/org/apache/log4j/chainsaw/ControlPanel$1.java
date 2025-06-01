package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import org.apache.log4j.Priority;

class ControlPanel$1 implements ActionListener {
   private final ControlPanel this$0;
   private final MyTableModel val$aModel;
   private final JComboBox val$priorities;

   ControlPanel$1(ControlPanel var1, MyTableModel var2, JComboBox var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$priorities = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      this.val$aModel.setPriorityFilter((Priority)this.val$priorities.getSelectedItem());
   }
}
