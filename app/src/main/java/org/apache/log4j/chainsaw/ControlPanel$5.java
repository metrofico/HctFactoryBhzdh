package org.apache.log4j.chainsaw;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class ControlPanel$5 implements DocumentListener {
   private final ControlPanel this$0;
   private final MyTableModel val$aModel;
   private final JTextField val$msgField;

   ControlPanel$5(ControlPanel var1, MyTableModel var2, JTextField var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$msgField = var3;
   }

   public void changedUpdate(DocumentEvent var1) {
      this.val$aModel.setMessageFilter(this.val$msgField.getText());
   }

   public void insertUpdate(DocumentEvent var1) {
      this.val$aModel.setMessageFilter(this.val$msgField.getText());
   }

   public void removeUpdate(DocumentEvent var1) {
      this.val$aModel.setMessageFilter(this.val$msgField.getText());
   }
}
