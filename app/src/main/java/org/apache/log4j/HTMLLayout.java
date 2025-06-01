package org.apache.log4j;

import java.util.Date;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class HTMLLayout extends Layout {
   public static final String LOCATION_INFO_OPTION = "LocationInfo";
   public static final String TITLE_OPTION = "Title";
   static String TRACE_PREFIX;
   protected final int BUF_SIZE = 256;
   protected final int MAX_CAPACITY = 1024;
   boolean locationInfo = false;
   private StringBuffer sbuf = new StringBuffer(256);
   String title = "Log4J Log Messages";

   public void activateOptions() {
   }

   void appendThrowableAsHTML(String[] var1, StringBuffer var2) {
      if (var1 != null) {
         int var4 = var1.length;
         if (var4 == 0) {
            return;
         }

         var2.append(Transform.escapeTags(var1[0]));
         var2.append(Layout.LINE_SEP);

         for(int var3 = 1; var3 < var4; ++var3) {
            var2.append(TRACE_PREFIX);
            var2.append(Transform.escapeTags(var1[var3]));
            var2.append(Layout.LINE_SEP);
         }
      }

   }

   public String format(LoggingEvent var1) {
      if (this.sbuf.capacity() > 1024) {
         this.sbuf = new StringBuffer(256);
      } else {
         this.sbuf.setLength(0);
      }

      this.sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);
      this.sbuf.append("<td>");
      this.sbuf.append(var1.timeStamp - LoggingEvent.getStartTime());
      this.sbuf.append("</td>" + Layout.LINE_SEP);
      this.sbuf.append("<td title=\"" + var1.getThreadName() + " thread\">");
      this.sbuf.append(Transform.escapeTags(var1.getThreadName()));
      this.sbuf.append("</td>" + Layout.LINE_SEP);
      this.sbuf.append("<td title=\"Level\">");
      if (var1.getLevel().equals(Level.DEBUG)) {
         this.sbuf.append("<font color=\"#339933\">");
         this.sbuf.append(var1.getLevel());
         this.sbuf.append("</font>");
      } else if (var1.getLevel().isGreaterOrEqual(Level.WARN)) {
         this.sbuf.append("<font color=\"#993300\"><strong>");
         this.sbuf.append(var1.getLevel());
         this.sbuf.append("</strong></font>");
      } else {
         this.sbuf.append(var1.getLevel());
      }

      this.sbuf.append("</td>" + Layout.LINE_SEP);
      this.sbuf.append("<td title=\"" + var1.getLoggerName() + " category\">");
      this.sbuf.append(Transform.escapeTags(var1.getLoggerName()));
      this.sbuf.append("</td>" + Layout.LINE_SEP);
      if (this.locationInfo) {
         LocationInfo var2 = var1.getLocationInformation();
         this.sbuf.append("<td>");
         this.sbuf.append(Transform.escapeTags(var2.getFileName()));
         this.sbuf.append(':');
         this.sbuf.append(var2.getLineNumber());
         this.sbuf.append("</td>" + Layout.LINE_SEP);
      }

      this.sbuf.append("<td title=\"Message\">");
      this.sbuf.append(Transform.escapeTags(var1.getRenderedMessage()));
      this.sbuf.append("</td>" + Layout.LINE_SEP);
      this.sbuf.append("</tr>" + Layout.LINE_SEP);
      if (var1.getNDC() != null) {
         this.sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : xx-small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
         this.sbuf.append("NDC: " + Transform.escapeTags(var1.getNDC()));
         this.sbuf.append("</td></tr>" + Layout.LINE_SEP);
      }

      String[] var3 = var1.getThrowableStrRep();
      if (var3 != null) {
         this.sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : xx-small;\" colspan=\"6\">");
         this.appendThrowableAsHTML(var3, this.sbuf);
         this.sbuf.append("</td></tr>" + Layout.LINE_SEP);
      }

      return this.sbuf.toString();
   }

   public String getContentType() {
      return "text/html";
   }

   public String getFooter() {
      StringBuffer var1 = new StringBuffer();
      var1.append("</table>" + Layout.LINE_SEP);
      var1.append("<br>" + Layout.LINE_SEP);
      var1.append("</body></html>");
      return var1.toString();
   }

   public String getHeader() {
      StringBuffer var1 = new StringBuffer();
      var1.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" + Layout.LINE_SEP);
      var1.append("<html>" + Layout.LINE_SEP);
      var1.append("<head>" + Layout.LINE_SEP);
      var1.append("<title>" + this.title + "</title>" + Layout.LINE_SEP);
      var1.append("<style type=\"text/css\">" + Layout.LINE_SEP);
      var1.append("<!--" + Layout.LINE_SEP);
      var1.append("body, table {font-family: arial,sans-serif; font-size: x-small;}" + Layout.LINE_SEP);
      var1.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);
      var1.append("-->" + Layout.LINE_SEP);
      var1.append("</style>" + Layout.LINE_SEP);
      var1.append("</head>" + Layout.LINE_SEP);
      var1.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" + Layout.LINE_SEP);
      var1.append("<hr size=\"1\" noshade>" + Layout.LINE_SEP);
      var1.append("Log session start time " + new Date() + "<br>" + Layout.LINE_SEP);
      var1.append("<br>" + Layout.LINE_SEP);
      var1.append("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" + Layout.LINE_SEP);
      var1.append("<tr>" + Layout.LINE_SEP);
      var1.append("<th>Time</th>" + Layout.LINE_SEP);
      var1.append("<th>Thread</th>" + Layout.LINE_SEP);
      var1.append("<th>Level</th>" + Layout.LINE_SEP);
      var1.append("<th>Category</th>" + Layout.LINE_SEP);
      if (this.locationInfo) {
         var1.append("<th>File:Line</th>" + Layout.LINE_SEP);
      }

      var1.append("<th>Message</th>" + Layout.LINE_SEP);
      var1.append("</tr>" + Layout.LINE_SEP);
      return var1.toString();
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public String getTitle() {
      return this.title;
   }

   public boolean ignoresThrowable() {
      return false;
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public void setTitle(String var1) {
      this.title = var1;
   }
}
