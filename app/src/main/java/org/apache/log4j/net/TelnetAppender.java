package org.apache.log4j.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class TelnetAppender extends AppenderSkeleton {
   private int port = 23;
   private SocketHandler sh;

   public void activateOptions() {
      try {
         SocketHandler var1 = new SocketHandler(this.port);
         this.sh = var1;
         var1.start();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   protected void append(LoggingEvent var1) {
      this.sh.send(super.layout.format(var1));
      if (super.layout.ignoresThrowable()) {
         String[] var4 = var1.getThrowableStrRep();
         if (var4 != null) {
            int var3 = var4.length;

            for(int var2 = 0; var2 < var3; ++var2) {
               this.sh.send(var4[var2]);
               this.sh.send(Layout.LINE_SEP);
            }
         }
      }

   }

   public void close() {
      this.sh.finalize();
   }

   public int getPort() {
      return this.port;
   }

   public boolean requiresLayout() {
      return true;
   }

   public void setPort(int var1) {
      this.port = var1;
   }

   protected class SocketHandler extends Thread {
      private int MAX_CONNECTIONS = 20;
      private Vector connections = new Vector();
      private boolean done = false;
      private ServerSocket serverSocket;
      private Vector writers = new Vector();

      public SocketHandler(int var2) throws IOException {
         this.serverSocket = new ServerSocket(var2);
      }

      public void finalize() {
         Enumeration var2 = this.connections.elements();

         while(var2.hasMoreElements()) {
            try {
               ((Socket)var2.nextElement()).close();
            } catch (Exception var4) {
            }
         }

         try {
            this.serverSocket.close();
         } catch (Exception var3) {
         }

         this.done = true;
      }

      public void run() {
         while(!this.done) {
            try {
               Socket var2 = this.serverSocket.accept();
               PrintWriter var1 = new PrintWriter(var2.getOutputStream());
               if (this.connections.size() < this.MAX_CONNECTIONS) {
                  this.connections.addElement(var2);
                  this.writers.addElement(var1);
                  StringBuffer var4 = new StringBuffer();
                  var1.print(var4.append("TelnetAppender v1.0 (").append(this.connections.size()).append(" active connections)\r\n\r\n").toString());
                  var1.flush();
               } else {
                  var1.print("Too many connections.\r\n");
                  var1.flush();
                  var2.close();
               }
            } catch (Exception var3) {
               LogLog.error("Encountered error while in SocketHandler loop.", var3);
            }
         }

      }

      public void send(String var1) {
         Enumeration var5 = this.connections.elements();
         Enumeration var2 = this.writers.elements();

         while(var2.hasMoreElements()) {
            Socket var4 = (Socket)var5.nextElement();
            PrintWriter var3 = (PrintWriter)var2.nextElement();
            var3.print(var1);
            if (var3.checkError()) {
               this.connections.remove(var4);
               this.writers.remove(var3);
            }
         }

      }
   }
}
