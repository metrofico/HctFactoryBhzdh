package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.lf5.LogLevel;

class LogBrokerMonitor$10 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$10(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      LogLevel.resetLogLevelColorMap();
      this.this$0._table.getFilteredLogTableModel().refresh();
   }
}
