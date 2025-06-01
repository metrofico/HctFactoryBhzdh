package org.apache.log4j;

import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.BoundedFIFO;

class Dispatcher extends Thread {
   private AppenderAttachableImpl aai;
   private BoundedFIFO bf;
   AsyncAppender container;
   private boolean interrupted = false;

   Dispatcher(BoundedFIFO var1, AsyncAppender var2) {
      this.bf = var1;
      this.container = var2;
      this.aai = var2.aai;
      this.setDaemon(true);
      this.setPriority(1);
      this.setName("Dispatcher-" + this.getName());
   }

   void close() {
      BoundedFIFO var2 = this.bf;
      synchronized(var2) {
         this.interrupted = true;
         if (this.bf.length() == 0) {
            this.bf.notify();
         }

      }
   }

   public void run() {
      // $FF: Couldn't be decompiled
   }
}
