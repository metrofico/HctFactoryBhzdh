package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AbsoluteTimeDateFormat extends DateFormat {
   public static final String ABS_TIME_DATE_FORMAT = "ABSOLUTE";
   public static final String DATE_AND_TIME_DATE_FORMAT = "DATE";
   public static final String ISO8601_DATE_FORMAT = "ISO8601";
   private static long previousTime;
   private static char[] previousTimeWithoutMillis = new char[9];

   public AbsoluteTimeDateFormat() {
      this.setCalendar(Calendar.getInstance());
   }

   public AbsoluteTimeDateFormat(TimeZone var1) {
      this.setCalendar(Calendar.getInstance(var1));
   }

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      long var7 = var1.getTime();
      int var4 = (int)(var7 % 1000L);
      var7 -= (long)var4;
      if (var7 != previousTime) {
         super.calendar.setTime(var1);
         int var5 = var2.length();
         int var6 = super.calendar.get(11);
         if (var6 < 10) {
            var2.append('0');
         }

         var2.append(var6);
         var2.append(':');
         var6 = super.calendar.get(12);
         if (var6 < 10) {
            var2.append('0');
         }

         var2.append(var6);
         var2.append(':');
         var6 = super.calendar.get(13);
         if (var6 < 10) {
            var2.append('0');
         }

         var2.append(var6);
         var2.append(',');
         var2.getChars(var5, var2.length(), previousTimeWithoutMillis, 0);
         previousTime = var7;
      } else {
         var2.append(previousTimeWithoutMillis);
      }

      if (var4 < 100) {
         var2.append('0');
      }

      if (var4 < 10) {
         var2.append('0');
      }

      var2.append(var4);
      return var2;
   }

   public Date parse(String var1, ParsePosition var2) {
      return null;
   }
}
