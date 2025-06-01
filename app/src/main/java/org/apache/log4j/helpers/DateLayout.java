package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public abstract class DateLayout extends Layout {
   public static final String DATE_FORMAT_OPTION = "DateFormat";
   public static final String NULL_DATE_FORMAT = "NULL";
   public static final String RELATIVE_TIME_DATE_FORMAT = "RELATIVE";
   public static final String TIMEZONE_OPTION = "TimeZone";
   protected Date date = new Date();
   protected DateFormat dateFormat;
   private String dateFormatOption;
   protected FieldPosition pos = new FieldPosition(0);
   private String timeZoneID;

   public void activateOptions() {
      this.setDateFormat(this.dateFormatOption);
      String var2 = this.timeZoneID;
      if (var2 != null) {
         DateFormat var1 = this.dateFormat;
         if (var1 != null) {
            var1.setTimeZone(TimeZone.getTimeZone(var2));
         }
      }

   }

   public void dateFormat(StringBuffer var1, LoggingEvent var2) {
      if (this.dateFormat != null) {
         this.date.setTime(var2.timeStamp);
         this.dateFormat.format(this.date, var1, this.pos);
         var1.append(' ');
      }

   }

   public String getDateFormat() {
      return this.dateFormatOption;
   }

   public String[] getOptionStrings() {
      return new String[]{"DateFormat", "TimeZone"};
   }

   public String getTimeZone() {
      return this.timeZoneID;
   }

   public void setDateFormat(String var1) {
      if (var1 != null) {
         this.dateFormatOption = var1;
      }

      this.setDateFormat(this.dateFormatOption, TimeZone.getDefault());
   }

   public void setDateFormat(String var1, TimeZone var2) {
      if (var1 == null) {
         this.dateFormat = null;
      } else {
         if (var1.equalsIgnoreCase("NULL")) {
            this.dateFormat = null;
         } else if (var1.equalsIgnoreCase("RELATIVE")) {
            this.dateFormat = new RelativeTimeDateFormat();
         } else if (var1.equalsIgnoreCase("ABSOLUTE")) {
            this.dateFormat = new AbsoluteTimeDateFormat(var2);
         } else if (var1.equalsIgnoreCase("DATE")) {
            this.dateFormat = new DateTimeDateFormat(var2);
         } else if (var1.equalsIgnoreCase("ISO8601")) {
            this.dateFormat = new ISO8601DateFormat(var2);
         } else {
            SimpleDateFormat var3 = new SimpleDateFormat(var1);
            this.dateFormat = var3;
            var3.setTimeZone(var2);
         }

      }
   }

   public void setDateFormat(DateFormat var1, TimeZone var2) {
      this.dateFormat = var1;
      var1.setTimeZone(var2);
   }

   public void setOption(String var1, String var2) {
      if (var1.equalsIgnoreCase("DateFormat")) {
         this.dateFormatOption = var2.toUpperCase();
      } else if (var1.equalsIgnoreCase("TimeZone")) {
         this.timeZoneID = var2;
      }

   }

   public void setTimeZone(String var1) {
      this.timeZoneID = var1;
   }
}
