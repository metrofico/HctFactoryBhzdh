package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import org.apache.log4j.lf5.LogLevel;

class LogBrokerMonitor$32 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$32(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      LogLevel var2 = (LogLevel)((JComboBox)var1.getSource()).getSelectedItem();
      this.this$0.setLeastSevereDisplayedLogLevel(var2);
   }
}
