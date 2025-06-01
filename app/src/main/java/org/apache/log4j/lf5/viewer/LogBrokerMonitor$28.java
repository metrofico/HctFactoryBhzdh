package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LogBrokerMonitor$28 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$28(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._table.getFilteredLogTableModel().setLogRecordFilter(this.this$0.createLogRecordFilter());
      this.this$0.setNDCTextFilter("");
      this.this$0._table.getFilteredLogTableModel().refresh();
      this.this$0.updateStatusLabel();
   }
}
