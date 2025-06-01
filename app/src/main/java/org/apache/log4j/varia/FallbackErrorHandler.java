package org.apache.log4j.varia;

import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class FallbackErrorHandler implements ErrorHandler {
   Appender backup;
   Vector loggers;
   Appender primary;

   public void activateOptions() {
   }

   public void error(String var1) {
   }

   public void error(String var1, Exception var2, int var3) {
      this.error(var1, var2, var3, (LoggingEvent)null);
   }

   public void error(String var1, Exception var2, int var3, LoggingEvent var4) {
      LogLog.debug("FB: The following error reported: " + var1, var2);
      LogLog.debug("FB: INITIATING FALLBACK PROCEDURE.");
      if (this.loggers != null) {
         for(var3 = 0; var3 < this.loggers.size(); ++var3) {
            Logger var5 = (Logger)this.loggers.elementAt(var3);
            LogLog.debug("FB: Searching for [" + this.primary.getName() + "] in logger [" + var5.getName() + "].");
            LogLog.debug("FB: Replacing [" + this.primary.getName() + "] by [" + this.backup.getName() + "] in logger [" + var5.getName() + "].");
            var5.removeAppender(this.primary);
            LogLog.debug("FB: Adding appender [" + this.backup.getName() + "] to logger " + var5.getName());
            var5.addAppender(this.backup);
         }
      }

   }

   public void setAppender(Appender var1) {
      LogLog.debug("FB: Setting primary appender to [" + var1.getName() + "].");
      this.primary = var1;
   }

   public void setBackupAppender(Appender var1) {
      LogLog.debug("FB: Setting backup appender to [" + var1.getName() + "].");
      this.backup = var1;
   }

   public void setLogger(Logger var1) {
      LogLog.debug("FB: Adding logger [" + var1.getName() + "].");
      if (this.loggers == null) {
         this.loggers = new Vector();
      }

      this.loggers.addElement(var1);
   }
}
