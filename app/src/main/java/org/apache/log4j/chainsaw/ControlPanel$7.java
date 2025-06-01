package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

class ControlPanel$7 implements ActionListener {
   private final ControlPanel this$0;
   private final MyTableModel val$aModel;
   private final JButton val$toggleButton;

   ControlPanel$7(ControlPanel var1, MyTableModel var2, JButton var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$toggleButton = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      this.val$aModel.toggle();
      JButton var2 = this.val$toggleButton;
      String var3;
      if (this.val$aModel.isPaused()) {
         var3 = "Resume";
      } else {
         var3 = "Pause";
      }

      var2.setText(var3);
   }
}
