package org.apache.log4j;

import org.apache.log4j.helpers.FileWatchdog;

class PropertyWatchdog extends FileWatchdog {
   PropertyWatchdog(String var1) {
      super(var1);
   }

   public void doOnChange() {
      (new PropertyConfigurator()).doConfigure(super.filename, LogManager.getLoggerRepository());
   }
}
