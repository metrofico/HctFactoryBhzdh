package org.apache.log4j.nt;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.TTCCLayout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class NTEventLogAppender extends AppenderSkeleton {
   private static final int DEBUG;
   private static final int ERROR;
   private static final int FATAL;
   private static final int INFO;
   private static final int WARN;
   private int _handle;
   private String server;
   private String source;

   static {
      FATAL = Level.FATAL.toInt();
      ERROR = Level.ERROR.toInt();
      WARN = Level.WARN.toInt();
      INFO = Level.INFO.toInt();
      DEBUG = Level.DEBUG.toInt();
      System.loadLibrary("NTEventLogAppender");
   }

   public NTEventLogAppender() {
      this((String)null, (String)null, (Layout)null);
   }

   public NTEventLogAppender(String var1) {
      this((String)null, var1, (Layout)null);
   }

   public NTEventLogAppender(String var1, String var2) {
      this(var1, var2, (Layout)null);
   }

   public NTEventLogAppender(String var1, String var2, Layout var3) {
      this._handle = 0;
      this.source = null;
      this.server = null;
      String var4 = var2;
      if (var2 == null) {
         var4 = "Log4j";
      }

      if (var3 == null) {
         super.layout = new TTCCLayout();
      } else {
         super.layout = var3;
      }

      try {
         this._handle = this.registerEventSource(var1, var4);
      } catch (Exception var5) {
         var5.printStackTrace();
         this._handle = 0;
      }

   }

   public NTEventLogAppender(String var1, Layout var2) {
      this((String)null, var1, var2);
   }

   public NTEventLogAppender(Layout var1) {
      this((String)null, (String)null, var1);
   }

   private native void deregisterEventSource(int var1);

   private native int registerEventSource(String var1, String var2);

   private native void reportEvent(int var1, String var2, int var3);

   public void activateOptions() {
      String var1 = this.source;
      if (var1 != null) {
         try {
            this._handle = this.registerEventSource(this.server, var1);
         } catch (Exception var2) {
            LogLog.error("Could not register event source.", var2);
            this._handle = 0;
         }
      }

   }

   public void append(LoggingEvent var1) {
      StringBuffer var4 = new StringBuffer();
      var4.append(super.layout.format(var1));
      int var2;
      if (super.layout.ignoresThrowable()) {
         String[] var5 = var1.getThrowableStrRep();
         if (var5 != null) {
            int var3 = var5.length;

            for(var2 = 0; var2 < var3; ++var2) {
               var4.append(var5[var2]);
            }
         }
      }

      var2 = var1.getLevel().toInt();
      this.reportEvent(this._handle, var4.toString(), var2);
   }

   public void close() {
   }

   public void finalize() {
      this.deregisterEventSource(this._handle);
      this._handle = 0;
   }

   public String getSource() {
      return this.source;
   }

   public boolean requiresLayout() {
      return true;
   }

   public void setSource(String var1) {
      this.source = var1.trim();
   }
}
