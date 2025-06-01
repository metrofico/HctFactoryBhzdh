package org.apache.log4j.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class SocketAppender extends AppenderSkeleton {
   static final int DEFAULT_PORT = 4560;
   static final int DEFAULT_RECONNECTION_DELAY = 30000;
   private static final int RESET_FREQUENCY = 1;
   InetAddress address;
   private Connector connector;
   int counter;
   boolean locationInfo;
   ObjectOutputStream oos;
   int port;
   int reconnectionDelay;
   String remoteHost;

   public SocketAppender() {
      this.port = 4560;
      this.reconnectionDelay = 30000;
      this.locationInfo = false;
      this.counter = 0;
   }

   public SocketAppender(String var1, int var2) {
      this.reconnectionDelay = 30000;
      this.locationInfo = false;
      this.counter = 0;
      this.port = var2;
      InetAddress var3 = getAddressByName(var1);
      this.address = var3;
      this.remoteHost = var1;
      this.connect(var3, var2);
   }

   public SocketAppender(InetAddress var1, int var2) {
      this.port = 4560;
      this.reconnectionDelay = 30000;
      this.locationInfo = false;
      this.counter = 0;
      this.address = var1;
      this.remoteHost = var1.getHostName();
      this.port = var2;
      this.connect(var1, var2);
   }

   // $FF: synthetic method
   static Connector access$002(SocketAppender var0, Connector var1) {
      var0.connector = var1;
      return var1;
   }

   static InetAddress getAddressByName(String var0) {
      try {
         InetAddress var1 = InetAddress.getByName(var0);
         return var1;
      } catch (Exception var2) {
         LogLog.error("Could not find address of [" + var0 + "].", var2);
         return null;
      }
   }

   public void activateOptions() {
      this.connect(this.address, this.port);
   }

   public void append(LoggingEvent var1) {
      if (var1 != null) {
         if (this.address == null) {
            super.errorHandler.error("No remote host is set for SocketAppender named \"" + super.name + "\".");
         } else {
            if (this.oos != null) {
               IOException var10000;
               label48: {
                  boolean var10001;
                  try {
                     if (this.locationInfo) {
                        var1.getLocationInformation();
                     }
                  } catch (IOException var5) {
                     var10000 = var5;
                     var10001 = false;
                     break label48;
                  }

                  int var2;
                  try {
                     this.oos.writeObject(var1);
                     this.oos.flush();
                     var2 = this.counter + 1;
                     this.counter = var2;
                  } catch (IOException var4) {
                     var10000 = var4;
                     var10001 = false;
                     break label48;
                  }

                  if (var2 < 1) {
                     return;
                  }

                  try {
                     this.counter = 0;
                     this.oos.reset();
                     return;
                  } catch (IOException var3) {
                     var10000 = var3;
                     var10001 = false;
                  }
               }

               IOException var6 = var10000;
               this.oos = null;
               LogLog.warn("Detected problem with connection: " + var6);
               if (this.reconnectionDelay > 0) {
                  this.fireConnector();
               }
            }

         }
      }
   }

   public void cleanUp() {
      ObjectOutputStream var1 = this.oos;
      if (var1 != null) {
         try {
            var1.close();
         } catch (IOException var2) {
            LogLog.error("Could not close oos.", var2);
         }

         this.oos = null;
      }

      Connector var3 = this.connector;
      if (var3 != null) {
         var3.interrupted = true;
         this.connector = null;
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
            super.closed = true;
            this.cleanUp();
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

   void connect(InetAddress var1, int var2) {
      if (this.address != null) {
         try {
            this.cleanUp();
            Socket var4 = new Socket(var1, var2);
            ObjectOutputStream var7 = new ObjectOutputStream(var4.getOutputStream());
            this.oos = var7;
         } catch (IOException var5) {
            String var3 = "Could not connect to remote log4j server at [" + var1.getHostName() + "].";
            String var6 = var3;
            if (this.reconnectionDelay > 0) {
               var6 = var3 + " We will try again later.";
               this.fireConnector();
            }

            LogLog.error(var6, var5);
         }

      }
   }

   void fireConnector() {
      if (this.connector == null) {
         LogLog.debug("Starting a new connector thread.");
         Connector var1 = new Connector();
         this.connector = var1;
         var1.setDaemon(true);
         this.connector.setPriority(1);
         this.connector.start();
      }

   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public int getPort() {
      return this.port;
   }

   public int getReconnectionDelay() {
      return this.reconnectionDelay;
   }

   public String getRemoteHost() {
      return this.remoteHost;
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

   public void setReconnectionDelay(int var1) {
      this.reconnectionDelay = var1;
   }

   public void setRemoteHost(String var1) {
      this.address = getAddressByName(var1);
      this.remoteHost = var1;
   }

   class Connector extends Thread {
      boolean interrupted = false;

      public void run() {
         // $FF: Couldn't be decompiled
      }
   }
}
