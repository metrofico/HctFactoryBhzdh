package org.apache.log4j;

import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;

public class FileAppender extends WriterAppender {
   protected int bufferSize;
   protected boolean bufferedIO;
   protected boolean fileAppend;
   protected String fileName;

   public FileAppender() {
      this.fileAppend = true;
      this.fileName = null;
      this.bufferedIO = false;
      this.bufferSize = 8192;
   }

   public FileAppender(Layout var1, String var2) throws IOException {
      this(var1, var2, true);
   }

   public FileAppender(Layout var1, String var2, boolean var3) throws IOException {
      this.fileAppend = true;
      this.fileName = null;
      this.bufferedIO = false;
      this.bufferSize = 8192;
      super.layout = var1;
      this.setFile(var2, var3, false, this.bufferSize);
   }

   public FileAppender(Layout var1, String var2, boolean var3, boolean var4, int var5) throws IOException {
      this.fileAppend = true;
      this.fileName = null;
      this.bufferedIO = false;
      this.bufferSize = 8192;
      super.layout = var1;
      this.setFile(var2, var3, var4, var5);
   }

   public void activateOptions() {
      String var1 = this.fileName;
      if (var1 != null) {
         try {
            this.setFile(var1, this.fileAppend, this.bufferedIO, this.bufferSize);
         } catch (IOException var2) {
            super.errorHandler.error("setFile(" + this.fileName + "," + this.fileAppend + ") call failed.", var2, 4);
         }
      } else {
         LogLog.warn("File option not set for appender [" + super.name + "].");
         LogLog.warn("Are you using FileAppender instead of ConsoleAppender?");
      }

   }

   protected void closeFile() {
      if (super.qw != null) {
         try {
            super.qw.close();
         } catch (IOException var2) {
            LogLog.error("Could not close " + super.qw, var2);
         }
      }

   }

   public boolean getAppend() {
      return this.fileAppend;
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public boolean getBufferedIO() {
      return this.bufferedIO;
   }

   public String getFile() {
      return this.fileName;
   }

   protected void reset() {
      this.closeFile();
      this.fileName = null;
      super.reset();
   }

   public void setAppend(boolean var1) {
      this.fileAppend = var1;
   }

   public void setBufferSize(int var1) {
      this.bufferSize = var1;
   }

   public void setBufferedIO(boolean var1) {
      this.bufferedIO = var1;
      if (var1) {
         super.immediateFlush = false;
      }

   }

   public void setFile(String var1) {
      this.fileName = var1.trim();
   }

   public void setFile(String param1, boolean param2, boolean param3, int param4) throws IOException {
      // $FF: Couldn't be decompiled
   }

   protected void setQWForFiles(Writer var1) {
      super.qw = new QuietWriter(var1, super.errorHandler);
   }
}
