package org.apache.log4j.chainsaw;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

class LoggingReceiver extends Thread {
   private static final Logger LOG;
   static Class class$org$apache$log4j$chainsaw$LoggingReceiver;
   private MyTableModel mModel;
   private ServerSocket mSvrSock;

   static {
      Class var1 = class$org$apache$log4j$chainsaw$LoggingReceiver;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.chainsaw.LoggingReceiver");
         class$org$apache$log4j$chainsaw$LoggingReceiver = var0;
      }

      LOG = Logger.getLogger(var0);
   }

   LoggingReceiver(MyTableModel var1, int var2) throws IOException {
      this.setDaemon(true);
      this.mModel = var1;
      this.mSvrSock = new ServerSocket(var2);
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
      LOG.info("Thread started");

      while(true) {
         try {
            Logger var2 = LOG;
            var2.debug("Waiting for a connection");
            Socket var1 = this.mSvrSock.accept();
            StringBuffer var3 = new StringBuffer();
            var2.debug(var3.append("Got a connection from ").append(var1.getInetAddress().getHostName()).toString());
            Slurper var5 = new Slurper(var1);
            Thread var6 = new Thread(var5);
            var6.setDaemon(true);
            var6.start();
         } catch (IOException var4) {
            LOG.error("Error in accepting connections, stopping.", var4);
            return;
         }
      }
   }

   private class Slurper implements Runnable {
      private final Socket mClient;

      Slurper(Socket var2) {
         this.mClient = var2;
      }

      public void run() {
         LoggingReceiver.LOG.debug("Starting to get data");

         label51: {
            label50: {
               label49: {
                  IOException var16;
                  label48: {
                     ClassNotFoundException var10000;
                     label47: {
                        ObjectInputStream var4;
                        boolean var10001;
                        try {
                           var4 = new ObjectInputStream(this.mClient.getInputStream());
                        } catch (EOFException var10) {
                           var10001 = false;
                           break label50;
                        } catch (SocketException var11) {
                           var10001 = false;
                           break label49;
                        } catch (IOException var12) {
                           var16 = var12;
                           var10001 = false;
                           break label48;
                        } catch (ClassNotFoundException var13) {
                           var10000 = var13;
                           var10001 = false;
                           break label47;
                        }

                        while(true) {
                           try {
                              LoggingEvent var15 = (LoggingEvent)var4.readObject();
                              MyTableModel var2 = LoggingReceiver.this.mModel;
                              EventDetails var3 = new EventDetails(var15);
                              var2.addEvent(var3);
                           } catch (EOFException var6) {
                              var10001 = false;
                              break label50;
                           } catch (SocketException var7) {
                              var10001 = false;
                              break label49;
                           } catch (IOException var8) {
                              var16 = var8;
                              var10001 = false;
                              break label48;
                           } catch (ClassNotFoundException var9) {
                              var10000 = var9;
                              var10001 = false;
                              break;
                           }
                        }
                     }

                     ClassNotFoundException var1 = var10000;
                     LoggingReceiver.LOG.warn("Got ClassNotFoundException, closing connection", var1);
                     break label51;
                  }

                  IOException var14 = var16;
                  LoggingReceiver.LOG.warn("Got IOException, closing connection", var14);
                  break label51;
               }

               LoggingReceiver.LOG.info("Caught SocketException, closing connection");
               break label51;
            }

            LoggingReceiver.LOG.info("Reached EOF, closing connection");
         }

         try {
            this.mClient.close();
         } catch (IOException var5) {
            LoggingReceiver.LOG.warn("Error closing connection", var5);
         }

      }
   }
}
