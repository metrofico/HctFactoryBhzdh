package org.apache.log4j.lf5;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

public abstract class LogRecord implements Serializable {
   protected static long _seqCount;
   protected String _category = "Debug";
   protected LogLevel _level;
   protected String _location;
   protected String _message = "";
   protected long _millis = System.currentTimeMillis();
   protected String _ndc;
   protected long _sequenceNumber;
   protected String _thread;
   protected Throwable _thrown;
   protected String _thrownStackTrace;

   public LogRecord() {
      this._level = LogLevel.INFO;
      this._sequenceNumber = getNextId();
      this._thread = Thread.currentThread().toString();
      this._ndc = "";
      this._location = "";
   }

   protected static long getNextId() {
      synchronized(LogRecord.class){}

      long var0;
      try {
         var0 = _seqCount + 1L;
         _seqCount = var0;
      } finally {
         ;
      }

      return var0;
   }

   public static void resetSequenceNumber() {
      synchronized(LogRecord.class){}

      try {
         _seqCount = 0L;
      } finally {
         ;
      }

   }

   public String getCategory() {
      return this._category;
   }

   public LogLevel getLevel() {
      return this._level;
   }

   public String getLocation() {
      return this._location;
   }

   public String getMessage() {
      return this._message;
   }

   public long getMillis() {
      return this._millis;
   }

   public String getNDC() {
      return this._ndc;
   }

   public long getSequenceNumber() {
      return this._sequenceNumber;
   }

   public String getThreadDescription() {
      return this._thread;
   }

   public Throwable getThrown() {
      return this._thrown;
   }

   public String getThrownStackTrace() {
      return this._thrownStackTrace;
   }

   public boolean hasThrown() {
      Throwable var3 = this.getThrown();
      boolean var2 = false;
      if (var3 == null) {
         return false;
      } else {
         String var4 = var3.toString();
         boolean var1 = var2;
         if (var4 != null) {
            var1 = var2;
            if (var4.trim().length() != 0) {
               var1 = true;
            }
         }

         return var1;
      }
   }

   public boolean isFatal() {
      boolean var1;
      if (!this.isSevereLevel() && !this.hasThrown()) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public abstract boolean isSevereLevel();

   public void setCategory(String var1) {
      this._category = var1;
   }

   public void setLevel(LogLevel var1) {
      this._level = var1;
   }

   public void setLocation(String var1) {
      this._location = var1;
   }

   public void setMessage(String var1) {
      this._message = var1;
   }

   public void setMillis(long var1) {
      this._millis = var1;
   }

   public void setNDC(String var1) {
      this._ndc = var1;
   }

   public void setSequenceNumber(long var1) {
      this._sequenceNumber = var1;
   }

   public void setThreadDescription(String var1) {
      this._thread = var1;
   }

   public void setThrown(Throwable var1) {
      if (var1 != null) {
         this._thrown = var1;
         StringWriter var3 = new StringWriter();
         PrintWriter var2 = new PrintWriter(var3);
         var1.printStackTrace(var2);
         var2.flush();
         this._thrownStackTrace = var3.toString();

         try {
            var2.close();
            var3.close();
         } catch (IOException var4) {
         }

      }
   }

   public void setThrownStackTrace(String var1) {
      this._thrownStackTrace = var1;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      var1.append("LogRecord: [" + this._level + ", " + this._message + "]");
      return var1.toString();
   }
}
