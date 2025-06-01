package org.apache.log4j.lf5.util;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public class LogMonitorAdapter {
   public static final int JDK14_LOG_LEVELS = 1;
   public static final int LOG4J_LOG_LEVELS = 0;
   private LogLevel _defaultLevel = null;
   private LogBrokerMonitor _logMonitor;

   private LogMonitorAdapter(List var1) {
      this._defaultLevel = (LogLevel)var1.get(0);
      LogBrokerMonitor var2 = new LogBrokerMonitor(var1);
      this._logMonitor = var2;
      var2.setFrameSize(getDefaultMonitorWidth(), getDefaultMonitorHeight());
      this._logMonitor.setFontSize(12);
      this._logMonitor.show();
   }

   protected static int getDefaultMonitorHeight() {
      return getScreenHeight() * 3 / 4;
   }

   protected static int getDefaultMonitorWidth() {
      return getScreenWidth() * 3 / 4;
   }

   protected static int getScreenHeight() {
      try {
         int var0 = Toolkit.getDefaultToolkit().getScreenSize().height;
         return var0;
      } finally {
         ;
      }
   }

   protected static int getScreenWidth() {
      try {
         int var0 = Toolkit.getDefaultToolkit().getScreenSize().width;
         return var0;
      } finally {
         ;
      }
   }

   public static LogMonitorAdapter newInstance(int var0) {
      LogMonitorAdapter var1;
      if (var0 == 1) {
         var1 = newInstance(LogLevel.getJdk14Levels());
         var1.setDefaultLevel(LogLevel.FINEST);
         var1.setSevereLevel(LogLevel.SEVERE);
      } else {
         var1 = newInstance(LogLevel.getLog4JLevels());
         var1.setDefaultLevel(LogLevel.DEBUG);
         var1.setSevereLevel(LogLevel.FATAL);
      }

      return var1;
   }

   public static LogMonitorAdapter newInstance(List var0) {
      return new LogMonitorAdapter(var0);
   }

   public static LogMonitorAdapter newInstance(LogLevel[] var0) {
      return var0 == null ? null : newInstance(Arrays.asList(var0));
   }

   public void addMessage(LogRecord var1) {
      this._logMonitor.addMessage(var1);
   }

   public LogLevel getDefaultLevel() {
      return this._defaultLevel;
   }

   public LogLevel getSevereLevel() {
      return AdapterLogRecord.getSevereLevel();
   }

   public void log(String var1, String var2) {
      this.log(var1, (LogLevel)null, var2);
   }

   public void log(String var1, LogLevel var2, String var3) {
      this.log(var1, var2, var3, (Throwable)null, (String)null);
   }

   public void log(String var1, LogLevel var2, String var3, String var4) {
      this.log(var1, var2, var3, (Throwable)null, var4);
   }

   public void log(String var1, LogLevel var2, String var3, Throwable var4) {
      this.log(var1, var2, var3, var4, (String)null);
   }

   public void log(String var1, LogLevel var2, String var3, Throwable var4, String var5) {
      AdapterLogRecord var6 = new AdapterLogRecord();
      var6.setCategory(var1);
      var6.setMessage(var3);
      var6.setNDC(var5);
      var6.setThrown(var4);
      if (var2 == null) {
         var6.setLevel(this.getDefaultLevel());
      } else {
         var6.setLevel(var2);
      }

      this.addMessage(var6);
   }

   public void setDefaultLevel(LogLevel var1) {
      this._defaultLevel = var1;
   }

   public void setMaxNumberOfRecords(int var1) {
      this._logMonitor.setMaxNumberOfLogRecords(var1);
   }

   public void setSevereLevel(LogLevel var1) {
      AdapterLogRecord.setSevereLevel(var1);
   }
}
