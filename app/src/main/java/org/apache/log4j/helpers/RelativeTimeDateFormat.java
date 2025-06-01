package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

public class RelativeTimeDateFormat extends DateFormat {
   protected final long startTime = System.currentTimeMillis();

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      return var2.append(var1.getTime() - this.startTime);
   }

   public Date parse(String var1, ParsePosition var2) {
      return null;
   }
}
