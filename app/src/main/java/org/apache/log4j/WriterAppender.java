package org.apache.log4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class WriterAppender extends AppenderSkeleton {
   protected String encoding;
   protected boolean immediateFlush;
   protected QuietWriter qw;

   public WriterAppender() {
      this.immediateFlush = true;
   }

   public WriterAppender(Layout var1, OutputStream var2) {
      this(var1, (Writer)(new OutputStreamWriter(var2)));
   }

   public WriterAppender(Layout var1, Writer var2) {
      this.immediateFlush = true;
      super.layout = var1;
      this.setWriter(var2);
   }

   public void activateOptions() {
   }

   public void append(LoggingEvent var1) {
      if (this.checkEntryConditions()) {
         this.subAppend(var1);
      }
   }

   protected boolean checkEntryConditions() {
      if (super.closed) {
         LogLog.warn("Not allowed to write to a closed appender.");
         return false;
      } else if (this.qw == null) {
         super.errorHandler.error("No output stream or file set for the appender named [" + super.name + "].");
         return false;
      } else if (super.layout == null) {
         super.errorHandler.error("No layout set for the appender named [" + super.name + "].");
         return false;
      } else {
         return true;
      }
   }

   public void close() {
      synchronized(this){}

      Throwable var10000;
      label78: {
         boolean var1;
         boolean var10001;
         try {
            var1 = super.closed;
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label78;
         }

         if (var1) {
            return;
         }

         try {
            super.closed = true;
            this.writeFooter();
            this.reset();
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         return;
      }

      Throwable var2 = var10000;
      throw var2;
   }

   protected void closeWriter() {
      QuietWriter var1 = this.qw;
      if (var1 != null) {
         try {
            var1.close();
         } catch (IOException var2) {
            LogLog.error("Could not close " + this.qw, var2);
         }
      }

   }

   protected OutputStreamWriter createWriter(OutputStream var1) {
      OutputStreamWriter var2;
      label20: {
         String var3 = this.getEncoding();
         if (var3 != null) {
            try {
               var2 = new OutputStreamWriter(var1, var3);
               break label20;
            } catch (IOException var4) {
               LogLog.warn("Error initializing output writer.");
               LogLog.warn("Unsupported encoding?");
            }
         }

         var2 = null;
      }

      OutputStreamWriter var5 = var2;
      if (var2 == null) {
         var5 = new OutputStreamWriter(var1);
      }

      return var5;
   }

   public String getEncoding() {
      return this.encoding;
   }

   public boolean getImmediateFlush() {
      return this.immediateFlush;
   }

   public boolean requiresLayout() {
      return true;
   }

   protected void reset() {
      this.closeWriter();
      this.qw = null;
   }

   public void setEncoding(String var1) {
      this.encoding = var1;
   }

   public void setErrorHandler(ErrorHandler var1) {
      synchronized(this){}
      Throwable var10000;
      boolean var10001;
      if (var1 == null) {
         label103:
         try {
            LogLog.warn("You have tried to set a null error-handler.");
            return;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            break label103;
         }
      } else {
         label113: {
            QuietWriter var2;
            try {
               super.errorHandler = var1;
               var2 = this.qw;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label113;
            }

            if (var2 == null) {
               return;
            }

            label105:
            try {
               var2.setErrorHandler(var1);
               return;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label105;
            }
         }
      }

      Throwable var15 = var10000;
      throw var15;
   }

   public void setImmediateFlush(boolean var1) {
      this.immediateFlush = var1;
   }

   public void setWriter(Writer var1) {
      synchronized(this){}

      try {
         this.reset();
         QuietWriter var2 = new QuietWriter(var1, super.errorHandler);
         this.qw = var2;
         this.writeHeader();
      } finally {
         ;
      }

   }

   protected void subAppend(LoggingEvent var1) {
      this.qw.write(super.layout.format(var1));
      if (super.layout.ignoresThrowable()) {
         String[] var4 = var1.getThrowableStrRep();
         if (var4 != null) {
            int var3 = var4.length;

            for(int var2 = 0; var2 < var3; ++var2) {
               this.qw.write(var4[var2]);
               this.qw.write(Layout.LINE_SEP);
            }
         }
      }

      if (this.immediateFlush) {
         this.qw.flush();
      }

   }

   protected void writeFooter() {
      if (super.layout != null) {
         String var1 = super.layout.getFooter();
         if (var1 != null) {
            QuietWriter var2 = this.qw;
            if (var2 != null) {
               var2.write(var1);
               this.qw.flush();
            }
         }
      }

   }

   protected void writeHeader() {
      if (super.layout != null) {
         String var2 = super.layout.getHeader();
         if (var2 != null) {
            QuietWriter var1 = this.qw;
            if (var1 != null) {
               var1.write(var2);
            }
         }
      }

   }
}
