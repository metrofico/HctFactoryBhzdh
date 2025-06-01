package org.apache.log4j.lf5.viewer;

class LogBrokerMonitor$1 implements Runnable {
   private final LogBrokerMonitor this$0;
   private final int val$delay;

   LogBrokerMonitor$1(LogBrokerMonitor var1, int var2) {
      this.this$0 = var1;
      this.val$delay = var2;
   }

   public void run() {
      Thread.yield();
      this.this$0.pause(this.val$delay);
      this.this$0._logMonitorFrame.setVisible(true);
   }
}
