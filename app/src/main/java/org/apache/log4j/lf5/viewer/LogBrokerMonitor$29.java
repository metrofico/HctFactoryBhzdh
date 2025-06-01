package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.lf5.LogRecord;

class LogBrokerMonitor$29 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$29(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._table.clearLogRecords();
      this.this$0._categoryExplorerTree.getExplorerModel().resetAllNodeCounts();
      this.this$0.updateStatusLabel();
      this.this$0.clearDetailTextArea();
      LogRecord.resetSequenceNumber();
   }
}
