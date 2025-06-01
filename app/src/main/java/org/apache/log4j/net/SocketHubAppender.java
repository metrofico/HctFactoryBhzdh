package org.apache.log4j.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class SocketHubAppender extends AppenderSkeleton {
   static final int DEFAULT_PORT = 4560;
   private boolean locationInfo = false;
   private Vector oosList = new Vector();
   private int port = 4560;
   private ServerMonitor serverMonitor = null;

   public SocketHubAppender() {
   }

   public SocketHubAppender(int var1) {
      this.port = var1;
      this.startServer();
   }

   private void startServer() {
      this.serverMonitor = new ServerMonitor(this.port, this.oosList);
   }

   public void activateOptions() {
      this.startServer();
   }

   public void append(LoggingEvent var1) {
      if (var1 != null && this.oosList.size() != 0) {
         if (this.locationInfo) {
            var1.getLocationInformation();
         }

         for(int var2 = 0; var2 < this.oosList.size(); ++var2) {
            ObjectOutputStream var3 = null;

            label37: {
               ObjectOutputStream var4;
               try {
                  var4 = (ObjectOutputStream)this.oosList.elementAt(var2);
               } catch (ArrayIndexOutOfBoundsException var6) {
                  break label37;
               }

               var3 = var4;
            }

            if (var3 == null) {
               break;
            }

            try {
               var3.writeObject(var1);
               var3.flush();
               var3.reset();
            } catch (IOException var5) {
               this.oosList.removeElementAt(var2);
               LogLog.debug("dropped connection");
               --var2;
            }
         }

      }
   }

   public void cleanUp() {
      LogLog.debug("stopping ServerSocket");
      this.serverMonitor.stopMonitor();
      this.serverMonitor = null;
      LogLog.debug("closing client connections");

      while(this.oosList.size() != 0) {
         ObjectOutputStream var1 = (ObjectOutputStream)this.oosList.elementAt(0);
         if (var1 != null) {
            try {
               var1.close();
            } catch (IOException var2) {
               LogLog.error("could not close oos.", var2);
            }

            this.oosList.removeElementAt(0);
         }
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
            StringBuffer var9 = new StringBuffer();
            LogLog.debug(var9.append("closing SocketHubAppender ").append(this.getName()).toString());
            super.closed = true;
            this.cleanUp();
            var9 = new StringBuffer();
            LogLog.debug(var9.append("SocketHubAppender ").append(this.getName()).append(" closed").toString());
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

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public int getPort() {
      return this.port;
   }

   public boolean requiresLayout() {
      return false;
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public void setPort(int var1) {
      this.port = var1;
   }

   private class ServerMonitor implements Runnable {
      private boolean keepRunning;
      private Thread monitorThread;
      private Vector oosList;
      private int port;

      public ServerMonitor(int var2, Vector var3) {
         this.port = var2;
         this.oosList = var3;
         this.keepRunning = true;
         Thread var4 = new Thread(this);
         this.monitorThread = var4;
         var4.setDaemon(true);
         this.monitorThread.start();
      }

      public void run() {
         // $FF: Couldn't be decompiled
      }

      public void stopMonitor() {
         synchronized(this){}

         try {
            if (this.keepRunning) {
               LogLog.debug("server monitor thread shutting down");
               this.keepRunning = false;

               try {
                  this.monitorThread.join();
               } catch (InterruptedException var4) {
               }

               this.monitorThread = null;
               LogLog.debug("server monitor thread shut down");
            }
         } finally {
            ;
         }

      }
   }
}
