package org.apache.log4j.net;

import java.io.File;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RootLogger;

public class SocketServer {
   static String CONFIG_FILE_EXT;
   static String GENERIC;
   static Logger cat;
   static Class class$org$apache$log4j$net$SocketServer;
   static int port;
   static SocketServer server;
   File dir;
   LoggerRepository genericHierarchy;
   Hashtable hierarchyMap;

   static {
      Class var1 = class$org$apache$log4j$net$SocketServer;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.net.SocketServer");
         class$org$apache$log4j$net$SocketServer = var0;
      }

      cat = Logger.getLogger(var0);
   }

   public SocketServer(File var1) {
      this.dir = var1;
      this.hierarchyMap = new Hashtable(11);
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

   static void init(String var0, String var1, String var2) {
      try {
         port = Integer.parseInt(var0);
      } catch (NumberFormatException var4) {
         var4.printStackTrace();
         usage("Could not interpret port number [" + var0 + "].");
      }

      PropertyConfigurator.configure(var1);
      File var5 = new File(var2);
      if (!var5.isDirectory()) {
         usage("[" + var2 + "] is not a directory.");
      }

      server = new SocketServer(var5);
   }

   public static void main(String[] var0) {
      if (var0.length == 3) {
         init(var0[0], var0[1], var0[2]);
      } else {
         usage("Wrong number of arguments.");
      }

      Exception var10000;
      label36: {
         ServerSocket var2;
         boolean var10001;
         try {
            Logger var9 = cat;
            StringBuffer var1 = new StringBuffer();
            var9.info(var1.append("Listening on port ").append(port).toString());
            var2 = new ServerSocket(port);
         } catch (Exception var8) {
            var10000 = var8;
            var10001 = false;
            break label36;
         }

         while(true) {
            Socket var3;
            InetAddress var4;
            LoggerRepository var14;
            try {
               cat.info("Waiting to accept a new client.");
               var3 = var2.accept();
               var4 = var3.getInetAddress();
               Logger var12 = cat;
               StringBuffer var10 = new StringBuffer();
               var12.info(var10.append("Connected to client at ").append(var4).toString());
               var14 = (LoggerRepository)server.hierarchyMap.get(var4);
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
               break;
            }

            LoggerRepository var11 = var14;
            if (var14 == null) {
               try {
                  var11 = server.configureHierarchy(var4);
               } catch (Exception var6) {
                  var10000 = var6;
                  var10001 = false;
                  break;
               }
            }

            try {
               cat.info("Starting new socket node.");
               SocketNode var16 = new SocketNode(var3, var11);
               Thread var15 = new Thread(var16);
               var15.start();
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break;
            }
         }
      }

      Exception var13 = var10000;
      var13.printStackTrace();
   }

   static void usage(String var0) {
      System.err.println(var0);
      PrintStream var2 = System.err;
      StringBuffer var3 = (new StringBuffer()).append("Usage: java ");
      Class var1 = class$org$apache$log4j$net$SocketServer;
      Class var4 = var1;
      if (var1 == null) {
         var4 = class$("org.apache.log4j.net.SocketServer");
         class$org$apache$log4j$net$SocketServer = var4;
      }

      var2.println(var3.append(var4.getName()).append(" port configFile directory").toString());
      System.exit(1);
   }

   LoggerRepository configureHierarchy(InetAddress var1) {
      cat.info("Locating configuration file for " + var1);
      String var3 = var1.toString();
      int var2 = var3.indexOf("/");
      if (var2 == -1) {
         cat.warn("Could not parse the inetAddress [" + var1 + "]. Using default hierarchy.");
         return this.genericHierarchy();
      } else {
         var3 = var3.substring(0, var2);
         File var4 = new File(this.dir, var3 + CONFIG_FILE_EXT);
         if (var4.exists()) {
            Hierarchy var5 = new Hierarchy(new RootLogger((Level)Priority.DEBUG));
            this.hierarchyMap.put(var1, var5);
            (new PropertyConfigurator()).doConfigure((String)var4.getAbsolutePath(), var5);
            return var5;
         } else {
            cat.warn("Could not find config file [" + var4 + "].");
            return this.genericHierarchy();
         }
      }
   }

   LoggerRepository genericHierarchy() {
      if (this.genericHierarchy == null) {
         File var1 = new File(this.dir, GENERIC + CONFIG_FILE_EXT);
         if (var1.exists()) {
            this.genericHierarchy = new Hierarchy(new RootLogger((Level)Priority.DEBUG));
            (new PropertyConfigurator()).doConfigure(var1.getAbsolutePath(), this.genericHierarchy);
         } else {
            cat.warn("Could not find config file [" + var1 + "]. Will use the default hierarchy.");
            this.genericHierarchy = LogManager.getLoggerRepository();
         }
      }

      return this.genericHierarchy;
   }
}
