package org.apache.log4j.varia;

import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.helpers.LogLog;

class HUP extends Thread {
   ExternallyRolledFileAppender er;
   int port;

   HUP(ExternallyRolledFileAppender var1, int var2) {
      this.er = var1;
      this.port = var2;
   }

   public void run() {
      while(!this.isInterrupted()) {
         Exception var10000;
         label22: {
            ServerSocket var1;
            boolean var10001;
            try {
               var1 = new ServerSocket(this.port);
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
               break label22;
            }

            while(true) {
               try {
                  Socket var2 = var1.accept();
                  StringBuffer var3 = new StringBuffer();
                  LogLog.debug(var3.append("Connected to client at ").append(var2.getInetAddress()).toString());
                  HUPNode var8 = new HUPNode(var2, this.er);
                  Thread var4 = new Thread(var8);
                  var4.start();
               } catch (Exception var5) {
                  var10000 = var5;
                  var10001 = false;
                  break;
               }
            }
         }

         Exception var7 = var10000;
         var7.printStackTrace();
      }

   }
}
