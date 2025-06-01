package org.apache.log4j;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.LoggingEvent;

public class RollingFileAppender extends FileAppender {
   protected int maxBackupIndex = 1;
   protected long maxFileSize = 10485760L;

   public RollingFileAppender() {
   }

   public RollingFileAppender(Layout var1, String var2) throws IOException {
      super(var1, var2);
   }

   public RollingFileAppender(Layout var1, String var2, boolean var3) throws IOException {
      super(var1, var2, var3);
   }

   public int getMaxBackupIndex() {
      return this.maxBackupIndex;
   }

   public long getMaximumFileSize() {
      return this.maxFileSize;
   }

   public void rollOver() {
      if (super.qw != null) {
         LogLog.debug("rolling over count=" + ((CountingQuietWriter)super.qw).getCount());
      }

      LogLog.debug("maxBackupIndex=" + this.maxBackupIndex);
      if (this.maxBackupIndex > 0) {
         File var2 = new File(super.fileName + '.' + this.maxBackupIndex);
         if (var2.exists()) {
            var2.delete();
         }

         File var3;
         for(int var1 = this.maxBackupIndex - 1; var1 >= 1; --var1) {
            var3 = new File(super.fileName + "." + var1);
            if (var3.exists()) {
               var2 = new File(super.fileName + '.' + (var1 + 1));
               LogLog.debug("Renaming file " + var3 + " to " + var2);
               var3.renameTo(var2);
            }
         }

         var2 = new File(super.fileName + "." + 1);
         this.closeFile();
         var3 = new File(super.fileName);
         LogLog.debug("Renaming file " + var3 + " to " + var2);
         var3.renameTo(var2);
      }

      try {
         this.setFile(super.fileName, false, super.bufferedIO, super.bufferSize);
      } catch (IOException var4) {
         LogLog.error("setFile(" + super.fileName + ", false) call failed.", var4);
      }

   }

   public void setFile(String var1, boolean var2, boolean var3, int var4) throws IOException {
      synchronized(this){}

      Throwable var10000;
      label75: {
         boolean var10001;
         try {
            super.setFile(var1, var2, super.bufferedIO, super.bufferSize);
         } catch (Throwable var11) {
            var10000 = var11;
            var10001 = false;
            break label75;
         }

         if (!var2) {
            return;
         }

         label66:
         try {
            File var5 = new File(var1);
            ((CountingQuietWriter)super.qw).setCount(var5.length());
            return;
         } catch (Throwable var10) {
            var10000 = var10;
            var10001 = false;
            break label66;
         }
      }

      Throwable var12 = var10000;
      throw var12;
   }

   public void setMaxBackupIndex(int var1) {
      this.maxBackupIndex = var1;
   }

   public void setMaxFileSize(String var1) {
      this.maxFileSize = OptionConverter.toFileSize(var1, this.maxFileSize + 1L);
   }

   public void setMaximumFileSize(long var1) {
      this.maxFileSize = var1;
   }

   protected void setQWForFiles(Writer var1) {
      super.qw = new CountingQuietWriter(var1, super.errorHandler);
   }

   protected void subAppend(LoggingEvent var1) {
      super.subAppend(var1);
      if (super.fileName != null && ((CountingQuietWriter)super.qw).getCount() >= this.maxFileSize) {
         this.rollOver();
      }

   }
}
