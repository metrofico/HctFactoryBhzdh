package org.apache.log4j.helpers;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.TimeZone;

public class ISO8601DateFormat extends AbsoluteTimeDateFormat {
   private static long lastTime;
   private static char[] lastTimeString = new char[20];

   public ISO8601DateFormat() {
   }

   public ISO8601DateFormat(TimeZone var1) {
      super(var1);
   }

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      long var7 = var1.getTime();
      int var5 = (int)(var7 % 1000L);
      var7 -= (long)var5;
      if (var7 != lastTime) {
         super.calendar.setTime(var1);
         int var4 = var2.length();
         var2.append(super.calendar.get(1));
         String var9;
         switch (super.calendar.get(2)) {
            case 0:
               var9 = "-01-";
               break;
            case 1:
               var9 = "-02-";
               break;
            case 2:
               var9 = "-03-";
               break;
            case 3:
               var9 = "-04-";
               break;
            case 4:
               var9 = "-05-";
               break;
            case 5:
               var9 = "-06-";
               break;
            case 6:
               var9 = "-07-";
               break;
            case 7:
               var9 = "-08-";
               break;
            case 8:
               var9 = "-09-";
               break;
            case 9:
               var9 = "-10-";
               break;
            case 10:
               var9 = "-11-";
               break;
            case 11:
               var9 = "-12-";
               break;
            default:
               var9 = "-NA-";
         }

         var2.append(var9);
         int var6 = super.calendar.get(5);
         if (var6 < 10) {
            var2.append('0');
         }

         var2.append(var6);
         var2.append(' ');
         var6 = super.calendar.get(11);
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
         var2.getChars(var4, var2.length(), lastTimeString, 0);
         lastTime = var7;
      } else {
         var2.append(lastTimeString);
      }

      if (var5 < 100) {
         var2.append('0');
      }

      if (var5 < 10) {
         var2.append('0');
      }

      var2.append(var5);
      return var2;
   }

   public Date parse(String var1, ParsePosition var2) {
      return null;
   }
}
