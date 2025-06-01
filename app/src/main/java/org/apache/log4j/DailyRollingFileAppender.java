package org.apache.log4j;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class DailyRollingFileAppender extends FileAppender {
   static final int HALF_DAY = 2;
   static final int TOP_OF_DAY = 3;
   static final int TOP_OF_HOUR = 1;
   static final int TOP_OF_MINUTE = 0;
   static final int TOP_OF_MONTH = 5;
   static final int TOP_OF_TROUBLE = -1;
   static final int TOP_OF_WEEK = 4;
   static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
   int checkPeriod = -1;
   private String datePattern = "'.'yyyy-MM-dd";
   private long nextCheck = System.currentTimeMillis() - 1L;
   Date now = new Date();
   RollingCalendar rc = new RollingCalendar();
   private String scheduledFilename;
   SimpleDateFormat sdf;

   public DailyRollingFileAppender() {
   }

   public DailyRollingFileAppender(Layout var1, String var2, String var3) throws IOException {
      super(var1, var2, true);
      this.datePattern = var3;
      this.activateOptions();
   }

   public void activateOptions() {
      super.activateOptions();
      if (this.datePattern != null && super.fileName != null) {
         this.now.setTime(System.currentTimeMillis());
         this.sdf = new SimpleDateFormat(this.datePattern);
         int var1 = this.computeCheckPeriod();
         this.printPeriodicity(var1);
         this.rc.setType(var1);
         File var2 = new File(super.fileName);
         this.scheduledFilename = super.fileName + this.sdf.format(new Date(var2.lastModified()));
      } else {
         LogLog.error("Either File or DatePattern options are not set for appender [" + super.name + "].");
      }

   }

   int computeCheckPeriod() {
      RollingCalendar var2 = new RollingCalendar(gmtTimeZone, Locale.ENGLISH);
      Date var4 = new Date(0L);
      if (this.datePattern != null) {
         for(int var1 = 0; var1 <= 5; ++var1) {
            SimpleDateFormat var5 = new SimpleDateFormat(this.datePattern);
            var5.setTimeZone(gmtTimeZone);
            String var3 = var5.format(var4);
            var2.setType(var1);
            String var6 = var5.format(new Date(var2.getNextCheckMillis(var4)));
            if (var3 != null && var6 != null && !var3.equals(var6)) {
               return var1;
            }
         }
      }

      return -1;
   }

   public String getDatePattern() {
      return this.datePattern;
   }

   void printPeriodicity(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        LogLog.warn("Unknown periodicity for appender [" + super.name + "].");
                     } else {
                        LogLog.debug("Appender [" + super.name + "] to be rolled at start of every month.");
                     }
                  } else {
                     LogLog.debug("Appender [" + super.name + "] to be rolled at start of week.");
                  }
               } else {
                  LogLog.debug("Appender [" + super.name + "] to be rolled at midnight.");
               }
            } else {
               LogLog.debug("Appender [" + super.name + "] to be rolled at midday and midnight.");
            }
         } else {
            LogLog.debug("Appender [" + super.name + "] to be rolled on top of every hour.");
         }
      } else {
         LogLog.debug("Appender [" + super.name + "] to be rolled every minute.");
      }

   }

   void rollOver() throws IOException {
      if (this.datePattern == null) {
         super.errorHandler.error("Missing DatePattern option in rollOver().");
      } else {
         String var1 = super.fileName + this.sdf.format(this.now);
         if (!this.scheduledFilename.equals(var1)) {
            this.closeFile();
            File var2 = new File(this.scheduledFilename);
            if (var2.exists()) {
               var2.delete();
            }

            if ((new File(super.fileName)).renameTo(var2)) {
               LogLog.debug(super.fileName + " -> " + this.scheduledFilename);
            } else {
               LogLog.error("Failed to rename [" + super.fileName + "] to [" + this.scheduledFilename + "].");
            }

            try {
               this.setFile(super.fileName, false, super.bufferedIO, super.bufferSize);
            } catch (IOException var3) {
               super.errorHandler.error("setFile(" + super.fileName + ", false) call failed.");
            }

            this.scheduledFilename = var1;
         }
      }
   }

   public void setDatePattern(String var1) {
      this.datePattern = var1;
   }

   protected void subAppend(LoggingEvent var1) {
      long var2 = System.currentTimeMillis();
      if (var2 >= this.nextCheck) {
         this.now.setTime(var2);
         this.nextCheck = this.rc.getNextCheckMillis(this.now);

         try {
            this.rollOver();
         } catch (IOException var5) {
            LogLog.error("rollOver() failed.", var5);
         }
      }

      super.subAppend(var1);
   }
}
