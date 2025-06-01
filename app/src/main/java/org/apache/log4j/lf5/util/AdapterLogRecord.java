package org.apache.log4j.lf5.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;

public class AdapterLogRecord extends LogRecord {
   private static PrintWriter pw;
   private static LogLevel severeLevel;
   private static StringWriter sw = new StringWriter();

   static {
      pw = new PrintWriter(sw);
   }

   public static LogLevel getSevereLevel() {
      return severeLevel;
   }

   public static void setSevereLevel(LogLevel var0) {
      severeLevel = var0;
   }

   protected String getLocationInfo(String var1) {
      return this.parseLine(this.stackTraceToString(new Throwable()), var1);
   }

   public boolean isSevereLevel() {
      LogLevel var1 = severeLevel;
      return var1 == null ? false : var1.equals(this.getLevel());
   }

   protected String parseLine(String var1, String var2) {
      int var3 = var1.indexOf(var2);
      if (var3 == -1) {
         return null;
      } else {
         var1 = var1.substring(var3);
         return var1.substring(0, var1.indexOf(")") + 1);
      }
   }

   public void setCategory(String var1) {
      super.setCategory(var1);
      super.setLocation(this.getLocationInfo(var1));
   }

   protected String stackTraceToString(Throwable var1) {
      StringWriter var2 = sw;
      synchronized(var2) {
         var1.printStackTrace(pw);
         String var4 = sw.toString();
         sw.getBuffer().setLength(0);
         return var4;
      }
   }
}
