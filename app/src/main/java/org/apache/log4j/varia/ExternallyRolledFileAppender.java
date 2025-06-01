package org.apache.log4j.varia;

import org.apache.log4j.RollingFileAppender;

public class ExternallyRolledFileAppender extends RollingFileAppender {
   public static final String OK = "OK";
   public static final String ROLL_OVER = "RollOver";
   HUP hup;
   int port = 0;

   public void activateOptions() {
      super.activateOptions();
      if (this.port != 0) {
         HUP var1 = this.hup;
         if (var1 != null) {
            var1.interrupt();
         }

         var1 = new HUP(this, this.port);
         this.hup = var1;
         var1.setDaemon(true);
         this.hup.start();
      }

   }

   public int getPort() {
      return this.port;
   }

   public void setPort(int var1) {
      this.port = var1;
   }
}
