package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ControlPanel$6 implements ActionListener {
   private final ControlPanel this$0;
   private final MyTableModel val$aModel;

   ControlPanel$6(ControlPanel var1, MyTableModel var2) {
      this.this$0 = var1;
      this.val$aModel = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      this.val$aModel.clear();
   }
}
