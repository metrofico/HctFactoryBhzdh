package org.apache.log4j.helpers;

import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeDateFormat extends AbsoluteTimeDateFormat {
   String[] shortMonths;

   public DateTimeDateFormat() {
      this.shortMonths = (new DateFormatSymbols()).getShortMonths();
   }

   public DateTimeDateFormat(TimeZone var1) {
      this();
      this.setCalendar(Calendar.getInstance(var1));
   }

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      super.calendar.setTime(var1);
      int var4 = super.calendar.get(5);
      if (var4 < 10) {
         var2.append('0');
      }

      var2.append(var4);
      var2.append(' ');
      var2.append(this.shortMonths[super.calendar.get(2)]);
      var2.append(' ');
      var2.append(super.calendar.get(1));
      var2.append(' ');
      return super.format(var1, var2, var3);
   }

   public Date parse(String var1, ParsePosition var2) {
      return null;
   }
}
