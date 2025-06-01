package org.apache.log4j.spi;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;

public final class RootLogger extends Logger {
   public RootLogger(Level var1) {
      super("root");
      this.setLevel(var1);
   }

   public final Level getChainedLevel() {
      return super.level;
   }

   public final void setLevel(Level var1) {
      if (var1 == null) {
         LogLog.error("You have tried to set a null level to root.", new Throwable());
      } else {
         super.level = var1;
      }

   }
}
