package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class LogBrokerMonitor$14 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$14(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.selectAllLogTableColumns(true);
      List var2 = this.this$0.updateView();
      this.this$0._table.setView(var2);
   }
}
