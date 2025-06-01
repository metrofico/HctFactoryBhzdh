package org.apache.log4j.lf5.viewer;

class LogBrokerMonitor$6 {
   private final LogBrokerMonitor this$0;
   private final FilteredLogTableModel val$model;

   LogBrokerMonitor$6(LogBrokerMonitor var1, FilteredLogTableModel var2) {
      this.this$0 = var1;
      this.val$model = var2;
   }

   public String toString() {
      return "Maximum number of displayed LogRecords: " + this.val$model._maxNumberOfLogRecords;
   }
}
