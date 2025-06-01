package org.apache.log4j.lf5.viewer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

class LogBrokerMonitor$30 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$30(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = (String)((JComboBox)var1.getSource()).getSelectedItem();
      this.this$0._table.setFont(new Font(var2, 0, this.this$0._fontSize));
      this.this$0._fontName = var2;
   }
}
