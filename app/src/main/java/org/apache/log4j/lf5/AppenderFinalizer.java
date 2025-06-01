package org.apache.log4j.lf5;

import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public class AppenderFinalizer {
   protected LogBrokerMonitor _defaultMonitor;

   public AppenderFinalizer(LogBrokerMonitor var1) {
      this._defaultMonitor = var1;
   }

   protected void finalize() throws Throwable {
      System.out.println("Disposing of the default LogBrokerMonitor instance");
      this._defaultMonitor.dispose();
   }
}
