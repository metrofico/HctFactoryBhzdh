package org.apache.log4j.xml;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class XMLLayout extends Layout {
   private final int DEFAULT_SIZE = 256;
   private final int UPPER_LIMIT = 2048;
   private StringBuffer buf = new StringBuffer(256);
   private boolean locationInfo = false;

   public void activateOptions() {
   }

   public String format(LoggingEvent var1) {
      int var3 = this.buf.capacity();
      int var2 = 0;
      if (var3 > 2048) {
         this.buf = new StringBuffer(256);
      } else {
         this.buf.setLength(0);
      }

      this.buf.append("<log4j:event logger=\"");
      this.buf.append(var1.getLoggerName());
      this.buf.append("\" timestamp=\"");
      this.buf.append(var1.timeStamp);
      this.buf.append("\" level=\"");
      this.buf.append(var1.getLevel());
      this.buf.append("\" thread=\"");
      this.buf.append(var1.getThreadName());
      this.buf.append("\">\r\n");
      this.buf.append("<log4j:message><![CDATA[");
      Transform.appendEscapingCDATA(this.buf, var1.getRenderedMessage());
      this.buf.append("]]></log4j:message>\r\n");
      String var4 = var1.getNDC();
      if (var4 != null) {
         this.buf.append("<log4j:NDC><![CDATA[");
         this.buf.append(var4);
         this.buf.append("]]></log4j:NDC>\r\n");
      }

      String[] var6 = var1.getThrowableStrRep();
      if (var6 != null) {
         this.buf.append("<log4j:throwable><![CDATA[");

         while(var2 < var6.length) {
            this.buf.append(var6[var2]);
            this.buf.append("\r\n");
            ++var2;
         }

         this.buf.append("]]></log4j:throwable>\r\n");
      }

      if (this.locationInfo) {
         LocationInfo var5 = var1.getLocationInformation();
         this.buf.append("<log4j:locationInfo class=\"");
         this.buf.append(Transform.escapeTags(var5.getClassName()));
         this.buf.append("\" method=\"");
         this.buf.append(Transform.escapeTags(var5.getMethodName()));
         this.buf.append("\" file=\"");
         this.buf.append(var5.getFileName());
         this.buf.append("\" line=\"");
         this.buf.append(var5.getLineNumber());
         this.buf.append("\"/>\r\n");
      }

      this.buf.append("</log4j:event>\r\n\r\n");
      return this.buf.toString();
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public boolean ignoresThrowable() {
      return false;
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }
}
