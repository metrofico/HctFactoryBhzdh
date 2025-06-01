package org.apache.log4j;

import java.util.TimeZone;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.spi.LoggingEvent;

public class TTCCLayout extends DateLayout {
   protected final StringBuffer buf = new StringBuffer(256);
   private boolean categoryPrefixing = true;
   private boolean contextPrinting = true;
   private boolean threadPrinting = true;

   public TTCCLayout() {
      this.setDateFormat((String)"RELATIVE", (TimeZone)null);
   }

   public TTCCLayout(String var1) {
      this.setDateFormat(var1);
   }

   public String format(LoggingEvent var1) {
      this.buf.setLength(0);
      this.dateFormat(this.buf, var1);
      if (this.threadPrinting) {
         this.buf.append('[');
         this.buf.append(var1.getThreadName());
         this.buf.append("] ");
      }

      this.buf.append(var1.getLevel().toString());
      this.buf.append(' ');
      if (this.categoryPrefixing) {
         this.buf.append(var1.getLoggerName());
         this.buf.append(' ');
      }

      if (this.contextPrinting) {
         String var2 = var1.getNDC();
         if (var2 != null) {
            this.buf.append(var2);
            this.buf.append(' ');
         }
      }

      this.buf.append("- ");
      this.buf.append(var1.getRenderedMessage());
      this.buf.append(Layout.LINE_SEP);
      return this.buf.toString();
   }

   public boolean getCategoryPrefixing() {
      return this.categoryPrefixing;
   }

   public boolean getContextPrinting() {
      return this.contextPrinting;
   }

   public boolean getThreadPrinting() {
      return this.threadPrinting;
   }

   public boolean ignoresThrowable() {
      return true;
   }

   public void setCategoryPrefixing(boolean var1) {
      this.categoryPrefixing = var1;
   }

   public void setContextPrinting(boolean var1) {
      this.contextPrinting = var1;
   }

   public void setThreadPrinting(boolean var1) {
      this.threadPrinting = var1;
   }
}
