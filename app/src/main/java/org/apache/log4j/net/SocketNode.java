package org.apache.log4j.net;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

public class SocketNode implements Runnable {
   static Class class$org$apache$log4j$net$SocketNode;
   static Logger logger;
   LoggerRepository hierarchy;
   ObjectInputStream ois;
   Socket socket;

   static {
      Class var1 = class$org$apache$log4j$net$SocketNode;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.net.SocketNode");
         class$org$apache$log4j$net$SocketNode = var0;
      }

      logger = Logger.getLogger(var0);
   }

   public SocketNode(Socket var1, LoggerRepository var2) {
      this.socket = var1;
      this.hierarchy = var2;

      try {
         BufferedInputStream var3 = new BufferedInputStream(var1.getInputStream());
         ObjectInputStream var5 = new ObjectInputStream(var3);
         this.ois = var5;
      } catch (Exception var4) {
         logger.error("Could not open ObjectInputStream to " + var1, var4);
      }

   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   public void run() {
      while(true) {
         try {
            while(true) {
               LoggingEvent var1 = (LoggingEvent)this.ois.readObject();
               Logger var2 = this.hierarchy.getLogger(var1.getLoggerName());
               if (var1.getLevel().isGreaterOrEqual(var2.getEffectiveLevel())) {
                  var2.callAppenders(var1);
               }
            }
         } catch (EOFException var4) {
            logger.info("Caught java.io.EOFException closing conneciton.");
         } catch (SocketException var5) {
            logger.info("Caught java.net.SocketException closing conneciton.");
         } catch (IOException var6) {
            logger.info("Caught java.io.IOException: " + var6);
            logger.info("Closing connection.");
         } catch (Exception var7) {
            logger.error("Unexpected exception. Closing conneciton.", var7);
         }

         try {
            this.ois.close();
         } catch (Exception var3) {
            logger.info("Could not close connection.", var3);
         }

         return;
      }
   }
}
