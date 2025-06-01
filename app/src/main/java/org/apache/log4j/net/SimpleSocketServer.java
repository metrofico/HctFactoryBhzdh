package org.apache.log4j.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class SimpleSocketServer {
   static Logger cat;
   static Class class$org$apache$log4j$net$SimpleSocketServer;
   static int port;

   static {
      Class var1 = class$org$apache$log4j$net$SimpleSocketServer;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.net.SimpleSocketServer");
         class$org$apache$log4j$net$SimpleSocketServer = var0;
      }

      cat = Logger.getLogger(var0);
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

   static void init(String var0, String var1) {
      try {
         port = Integer.parseInt(var0);
      } catch (NumberFormatException var3) {
         var3.printStackTrace();
         usage("Could not interpret port number [" + var0 + "].");
      }

      if (var1.endsWith(".xml")) {
         new DOMConfigurator();
         DOMConfigurator.configure(var1);
      } else {
         new PropertyConfigurator();
         PropertyConfigurator.configure(var1);
      }

   }

   public static void main(String[] var0) {
      if (var0.length == 2) {
         init(var0[0], var0[1]);
      } else {
         usage("Wrong number of arguments.");
      }

      Exception var10000;
      label24: {
         ServerSocket var9;
         boolean var10001;
         try {
            Logger var1 = cat;
            StringBuffer var6 = new StringBuffer();
            var1.info(var6.append("Listening on port ").append(port).toString());
            var9 = new ServerSocket(port);
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label24;
         }

         while(true) {
            try {
               cat.info("Waiting to accept a new client.");
               Socket var8 = var9.accept();
               Logger var3 = cat;
               StringBuffer var2 = new StringBuffer();
               var3.info(var2.append("Connected to client at ").append(var8.getInetAddress()).toString());
               cat.info("Starting new socket node.");
               SocketNode var10 = new SocketNode(var8, LogManager.getLoggerRepository());
               Thread var11 = new Thread(var10);
               var11.start();
            } catch (Exception var4) {
               var10000 = var4;
               var10001 = false;
               break;
            }
         }
      }

      Exception var7 = var10000;
      var7.printStackTrace();
   }

   static void usage(String var0) {
      System.err.println(var0);
      PrintStream var2 = System.err;
      StringBuffer var3 = (new StringBuffer()).append("Usage: java ");
      Class var1 = class$org$apache$log4j$net$SimpleSocketServer;
      Class var4 = var1;
      if (var1 == null) {
         var4 = class$("org.apache.log4j.net.SimpleSocketServer");
         class$org$apache$log4j$net$SimpleSocketServer = var4;
      }

      var2.println(var3.append(var4.getName()).append(" port configFile").toString());
      System.exit(1);
   }
}
