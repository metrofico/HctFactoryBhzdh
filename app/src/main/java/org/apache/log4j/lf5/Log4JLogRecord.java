package org.apache.log4j.lf5;

import org.apache.log4j.spi.ThrowableInformation;

public class Log4JLogRecord extends LogRecord {
   public boolean isSevereLevel() {
      boolean var1;
      if (!LogLevel.ERROR.equals(this.getLevel()) && !LogLevel.FATAL.equals(this.getLevel())) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public void setThrownStackTrace(ThrowableInformation var1) {
      String[] var3 = var1.getThrowableStrRep();
      StringBuffer var4 = new StringBuffer();

      for(int var2 = 0; var2 < var3.length; ++var2) {
         var4.append(var3[var2] + "\n");
      }

      super._thrownStackTrace = var4.toString();
   }
}
