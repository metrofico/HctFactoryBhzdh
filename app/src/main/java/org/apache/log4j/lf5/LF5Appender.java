package org.apache.log4j.lf5;

import java.awt.Toolkit;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class LF5Appender extends AppenderSkeleton {
   protected static LogBrokerMonitor _defaultLogMonitor;
   protected static AppenderFinalizer _finalizer;
   protected LogBrokerMonitor _logMonitor;

   public LF5Appender() {
      this(getDefaultInstance());
   }

   public LF5Appender(LogBrokerMonitor var1) {
      if (var1 != null) {
         this._logMonitor = var1;
      }

   }

   protected static LogBrokerMonitor getDefaultInstance() {
      // $FF: Couldn't be decompiled
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

   public static void main(String[] var0) {
      new LF5Appender();
   }

   public void append(LoggingEvent var1) {
      String var5 = var1.getLoggerName();
      String var7 = var1.getRenderedMessage();
      String var10 = var1.getNDC();
      String var8 = var1.getThreadName();
      String var9 = var1.getLevel().toString();
      long var2 = var1.timeStamp;
      LocationInfo var6 = var1.getLocationInformation();
      Log4JLogRecord var4 = new Log4JLogRecord();
      var4.setCategory(var5);
      var4.setMessage(var7);
      var4.setLocation(var6.fullInfo);
      var4.setMillis(var2);
      var4.setThreadDescription(var8);
      if (var10 != null) {
         var4.setNDC(var10);
      } else {
         var4.setNDC("");
      }

      if (var1.getThrowableInformation() != null) {
         var4.setThrownStackTrace(var1.getThrowableInformation());
      }

      try {
         var4.setLevel(LogLevel.valueOf(var9));
      } catch (LogLevelFormatException var11) {
         var4.setLevel(LogLevel.WARN);
      }

      LogBrokerMonitor var12 = this._logMonitor;
      if (var12 != null) {
         var12.addMessage(var4);
      }

   }

   public void close() {
   }

   public boolean equals(LF5Appender var1) {
      boolean var2;
      if (this._logMonitor == var1.getLogBrokerMonitor()) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public LogBrokerMonitor getLogBrokerMonitor() {
      return this._logMonitor;
   }

   public boolean requiresLayout() {
      return false;
   }

   public void setCallSystemExitOnClose(boolean var1) {
      this._logMonitor.setCallSystemExitOnClose(var1);
   }

   public void setMaxNumberOfRecords(int var1) {
      _defaultLogMonitor.setMaxNumberOfLogRecords(var1);
   }
}
