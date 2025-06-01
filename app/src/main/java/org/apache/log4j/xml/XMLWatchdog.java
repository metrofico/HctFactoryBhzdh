package org.apache.log4j.xml;

import org.apache.log4j.LogManager;
import org.apache.log4j.helpers.FileWatchdog;

class XMLWatchdog extends FileWatchdog {
   XMLWatchdog(String var1) {
      super(var1);
   }

   public void doOnChange() {
      (new DOMConfigurator()).doConfigure(super.filename, LogManager.getLoggerRepository());
   }
}
