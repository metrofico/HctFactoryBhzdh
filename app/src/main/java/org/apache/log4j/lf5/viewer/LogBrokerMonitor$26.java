package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class LogBrokerMonitor$26 implements ActionListener {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$26(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = JOptionPane.showInputDialog(this.this$0._logMonitorFrame, "Find text: ", "Search Record Messages", 3);
      this.this$0.setSearchText(var2);
      this.this$0.findSearchText();
   }
}
