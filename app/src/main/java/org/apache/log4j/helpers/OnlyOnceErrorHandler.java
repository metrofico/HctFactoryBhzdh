package org.apache.log4j.helpers;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class OnlyOnceErrorHandler implements ErrorHandler {
   final String ERROR_PREFIX = "log4j error: ";
   final String WARN_PREFIX = "log4j warning: ";
   boolean firstTime = true;

   public void activateOptions() {
   }

   public void error(String var1) {
      if (this.firstTime) {
         LogLog.error(var1);
         this.firstTime = false;
      }

   }

   public void error(String var1, Exception var2, int var3) {
      this.error(var1, var2, var3, (LoggingEvent)null);
   }

   public void error(String var1, Exception var2, int var3, LoggingEvent var4) {
      if (this.firstTime) {
         LogLog.error(var1, var2);
         this.firstTime = false;
      }

   }

   public void setAppender(Appender var1) {
   }

   public void setBackupAppender(Appender var1) {
   }

   public void setLogger(Logger var1) {
   }
}
