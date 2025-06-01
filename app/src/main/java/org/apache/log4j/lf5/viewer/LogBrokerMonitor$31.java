package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

class LogBrokerMonitor$31 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$31(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = Integer.valueOf((String)((JComboBox)var1.getSource()).getSelectedItem());
      this.this$0.setFontSizeSilently(var2);
      this.this$0.refreshDetailTextArea();
      this.this$0._fontSize = var2;
   }
}
