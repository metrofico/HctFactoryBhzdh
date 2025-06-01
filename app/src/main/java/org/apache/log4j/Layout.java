package org.apache.log4j;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class Layout implements OptionHandler {
   public static final String LINE_SEP;
   public static final int LINE_SEP_LEN;

   static {
      String var0 = System.getProperty("line.separator");
      LINE_SEP = var0;
      LINE_SEP_LEN = var0.length();
   }

   public abstract void activateOptions();

   public abstract String format(LoggingEvent var1);

   public String getContentType() {
      return "text/plain";
   }

   public String getFooter() {
      return null;
   }

   public String getHeader() {
      return null;
   }

   public abstract boolean ignoresThrowable();
}
