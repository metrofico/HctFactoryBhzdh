package org.apache.log4j.lf5.viewer;

import org.apache.log4j.lf5.LogRecord;

class LogBrokerMonitor$2 implements Runnable {
   private final LogBrokerMonitor this$0;
   private final LogRecord val$lr;

   LogBrokerMonitor$2(LogBrokerMonitor var1, LogRecord var2) {
      this.this$0 = var1;
      this.val$lr = var2;
   }

   public void run() {
      this.this$0._categoryExplorerTree.getExplorerModel().addLogRecord(this.val$lr);
      this.this$0._table.getFilteredLogTableModel().addLogRecord(this.val$lr);
      this.this$0.updateStatusLabel();
   }
}
