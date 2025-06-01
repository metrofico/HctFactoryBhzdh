package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LogBrokerMonitor$18 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$18(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.requestClose();
   }
}
