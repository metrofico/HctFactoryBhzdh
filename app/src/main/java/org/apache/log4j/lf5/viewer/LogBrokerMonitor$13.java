package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class LogBrokerMonitor$13 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$13(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      List var2 = this.this$0.updateView();
      this.this$0._table.setView(var2);
   }
}
