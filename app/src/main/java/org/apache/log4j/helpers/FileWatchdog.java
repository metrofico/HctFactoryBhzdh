package org.apache.log4j.helpers;

import java.io.File;

public abstract class FileWatchdog extends Thread {
   public static final long DEFAULT_DELAY = 60000L;
   protected long delay = 60000L;
   File file;
   protected String filename;
   boolean interrupted = false;
   long lastModif = 0L;
   boolean warnedAlready = false;

   protected FileWatchdog(String var1) {
      this.filename = var1;
      this.file = new File(var1);
      this.setDaemon(true);
      this.checkAndConfigure();
   }

   protected void checkAndConfigure() {
      boolean var3;
      try {
         var3 = this.file.exists();
      } catch (SecurityException var5) {
         LogLog.warn("Was not allowed to read check file existance, file:[" + this.filename + "].");
         this.interrupted = true;
         return;
      }

      if (var3) {
         long var1 = this.file.lastModified();
         if (var1 > this.lastModif) {
            this.lastModif = var1;
            this.doOnChange();
            this.warnedAlready = false;
         }
      } else if (!this.warnedAlready) {
         LogLog.debug("[" + this.filename + "] does not exist.");
         this.warnedAlready = true;
      }

   }

   protected abstract void doOnChange();

   public void run() {
      for(; !this.interrupted; this.checkAndConfigure()) {
         try {
            Thread.currentThread();
            Thread.sleep(this.delay);
         } catch (InterruptedException var2) {
         }
      }

   }

   public void setDelay(long var1) {
      this.delay = var1;
   }
}
